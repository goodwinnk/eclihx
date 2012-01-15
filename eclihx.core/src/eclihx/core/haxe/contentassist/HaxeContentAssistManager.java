package eclihx.core.haxe.contentassist;

import java.io.File;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.core.runtime.CoreException;

import eclihx.core.haxe.HaxeCompilerResolver;
import eclihx.core.haxe.HaxeLauncher;
import eclihx.core.haxe.internal.FunctionSignature;
import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.HaxeConfigurationList;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.haxe.internal.parser.BuildParamParser;
import eclihx.core.haxe.model.core.IHaxePackage;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeSourceFile;
import eclihx.core.util.console.parser.core.ParseError;

/**
 * This class will generate content tips for the particular 
 * positions in the code. 
 */
public class HaxeContentAssistManager {
	
	/**
	 * Exception is thrown if eclihx failed in determining haxe compiler version.
	 */
	@SuppressWarnings("serial")
	public static class TipsEvaluationException extends Exception {
		TipsEvaluationException() { super(); }
		TipsEvaluationException(String message, Throwable cause) { super(message, cause); }
		TipsEvaluationException(String message) { super(message); }
		TipsEvaluationException(Throwable cause) { super(cause); }
	}

	private static final String OPEN_LIST_TAG = "<list>";
	private static final String CLOSE_LIST_TAG = "</list>";
	
	private static final String OPEN_TYPE_TAG = "<type>";
	private static final String CLOSE_TYPE_TAG = "</type>";
	
	private static final String OPEN_ROOT_TAG = "<root>";
	private static final String CLOSE_ROOT_TAG = "</root>";
		
	
	/**
	 * Get the tips for the defined position.
	 * 
	 * @param haxeFile the haXe file for getting tip. 
	 * @param text full text of the file.
	 * @param charPosition char offset in file where tip should be shown.
	 * @return set of tips. 
	 * @throws TipsEvaluationException if there were errors in tips execution.
	 */
	static public ContentAssistResult getTips(IHaxeSourceFile haxeFile, String text, int charPosition) throws TipsEvaluationException {

		IHaxeProject project = haxeFile.getHaxeProject();
		if (project == null) {
			throw new TipsEvaluationException("Can't find a project for given file");
		}
		
		HaxeConfiguration projectConfiguration = prepareProjectTipsConfiguration(
				haxeFile, getByteOffset(haxeFile, text, charPosition));

		if (projectConfiguration == null) {
			throw new TipsEvaluationException("Can't find project tips configuation");
		}		

		return getTips(projectConfiguration, haxeFile);
	}
	
	private static int getByteOffset(IHaxeSourceFile haxeFile, String text, int charPosition) throws TipsEvaluationException {
		try {
			String charset = haxeFile.getBaseFile().getCharset(true);
			return text.substring(0, charPosition).getBytes(charset).length;
		} catch (CoreException e) {
			throw new TipsEvaluationException(e);
		} catch (UnsupportedEncodingException e) {
			throw new TipsEvaluationException(e);
		}
	}
	
	private static HaxeConfiguration getProjectTipsConfiguration(IHaxeProject project) throws TipsEvaluationException {
		String absoluteContentAssistFilePath = project.getContentAssistBuildFileAbsolute();
		
		if (absoluteContentAssistFilePath != null) {
			
			try {
				
				BuildParamParser buildParamParser = new BuildParamParser();
				
				HaxeConfigurationList configurations = buildParamParser.parseFile(absoluteContentAssistFilePath, 
						project.getBaseResource().getLocation().toOSString());
				
				return configurations.getMainConfiguration();
				
			} catch (ParseError e) {					
				throw new TipsEvaluationException("Errors in tips configuration: " + e.getMessage(), e);					
			} catch (InvalidConfigurationException e) {
				throw new TipsEvaluationException("No configurations in default project build file");
			}			
		}
		
		return null;
	}

	private static HaxeConfiguration prepareProjectTipsConfiguration(IHaxeSourceFile haxeFile, int position) 
			throws TipsEvaluationException {
		
		IHaxeProject project = haxeFile.getHaxeProject();
		HaxeConfiguration haxeConfiguration = getProjectTipsConfiguration(project);
		if (haxeConfiguration != null) {
			haxeConfiguration.setExplicitNoOutput();
			haxeConfiguration.enableTips(haxeFile.getBaseFile().getLocation().toOSString(),	position);
		}
		
		return haxeConfiguration;
	}
	
	static private ContentAssistResult getTips(HaxeConfiguration configuration, IHaxeSourceFile haxeFile) throws TipsEvaluationException {

		final String haxePath = HaxeCompilerResolver.getProjectCompiler(haxeFile.getHaxeProject());		
		if (haxePath == null || haxePath.isEmpty()) {
			throw new TipsEvaluationException("Haxe compiler isn't set in preferences");
		}
		
		File workingDirectory = haxeFile.getHaxeProject().getProjectBase().getLocation().toFile();		
		
		HaxeLauncher launcher = new HaxeLauncher();
		
		try {
			launcher.run(configuration, null, haxePath, workingDirectory);
		} catch (CoreException e) {
			throw new TipsEvaluationException("Error in haxe compiler execution: " + e.getMessage(), e);
		}
		
		final String errors = launcher.getErrorString();
		if (errors == null || errors.isEmpty()) {
			return new ContentAssistResult("No information from haxe compiler");
		}
		
		{
			// Read type content assist info.
			int startInfoListIndex = errors.indexOf(OPEN_LIST_TAG);
			int endInfoListIndex = errors.indexOf(CLOSE_LIST_TAG);
			
			if (startInfoListIndex != -1 && endInfoListIndex != -1) {
				final String typeTips = errors.substring(startInfoListIndex, endInfoListIndex + CLOSE_LIST_TAG.length());
				return new ContentAssistResult(processTypeTips(typeTips));
			}
		}
		
		{
			// Read call information.
			int startFunctionDescription = errors.indexOf(OPEN_TYPE_TAG);
			int endFunctionDescription = errors.indexOf(CLOSE_TYPE_TAG);
			
			if (startFunctionDescription != -1 && endFunctionDescription != -1) {
				final String callTips = errors.substring(startFunctionDescription, 
						endFunctionDescription + CLOSE_TYPE_TAG.length());
				return new ContentAssistResult(processCallTips(callTips));
			}
		}		
		
		return new ContentAssistResult(errors);
	}
	
	/**
	 * Get haxe classes tips for current package.
	 * @param haxePackage package request is interested in.
	 * @return List with the content information. Won't be null.
	 * @throws TipsEvaluationException if there were errors in tips execution.
	 */
	public static ContentAssistResult getClassTips(IHaxePackage haxePackage) throws TipsEvaluationException {
				
		HaxeConfiguration projectTipsConfiguration = getProjectTipsConfiguration(
				haxePackage.getSourceFolder().getHaxeProject());
		
		if (projectTipsConfiguration != null) {
			projectTipsConfiguration.setExplicitNoOutput();
			projectTipsConfiguration.enableClassTips();
			
			return getClassTips(projectTipsConfiguration, haxePackage);
		}
		
		throw new TipsEvaluationException("Can't find project tips configuation");
	}

	private static ContentAssistResult getClassTips(HaxeConfiguration haxeConfiguration, 
			IHaxePackage haxePackage) throws TipsEvaluationException {
		

		IHaxeProject haxeProject = haxePackage.getSourceFolder().getHaxeProject();
		
		final String haxeCompilerPath = HaxeCompilerResolver.getProjectCompiler(haxeProject);		
		
		HaxeLauncher launcher = new HaxeLauncher();
		
		try {
			launcher.run(haxeConfiguration, null, haxeCompilerPath, new File(haxeProject.getProjectBase().getLocation().toOSString()));
		} catch (CoreException e) {
			throw new TipsEvaluationException("Error in haxe compiler execution: " + e.getMessage(), e);
		}
		
		String errors = launcher.getErrorString();		
		
		// Read type content assist info.
		int startInfoListIndex = errors.indexOf(OPEN_LIST_TAG);
		int endInfoListIndex = errors.indexOf(CLOSE_LIST_TAG);
		
		if (startInfoListIndex != -1 && endInfoListIndex != -1) {
			final String typeTips = errors.substring(startInfoListIndex, endInfoListIndex + CLOSE_LIST_TAG.length());
			return new ContentAssistResult(processTypeTips(typeTips));
		}
		
		return new ContentAssistResult(errors);
	}

	private static List<ContentInfo> processCallTips(final String callTips) throws TipsEvaluationException {
		try {
			
			final String wrappedTip = OPEN_ROOT_TAG + callTips + CLOSE_ROOT_TAG;
			
			final JAXBContext context = JAXBContext.newInstance(
					FunctionSignature.class);
			
			final Unmarshaller unmarshaller = context.createUnmarshaller();
			final StringReader xmlTipsReader = new StringReader(wrappedTip);		
	   
			FunctionSignature functionSignature = 
					(FunctionSignature)unmarshaller.unmarshal(xmlTipsReader);
			
			// Move signature information to ContentInfo object.
			final ContentInfo callFunctionInfo = new ContentInfo();
			callFunctionInfo.setType(functionSignature.getFullType());
			final ArrayList<ContentInfo> infos = new ArrayList<ContentInfo>();
			infos.add(callFunctionInfo);
			
			return infos;
			
		} catch (JAXBException e) {
			throw new TipsEvaluationException("Errors in parsing function call tips");			
		} 
	}
	
	private static List<ContentInfo> processTypeTips(final String typeTips) throws TipsEvaluationException {
		
		try 
		{
			final JAXBContext context = JAXBContext.newInstance(
					ContentInfoContainer.class);
			
			final Unmarshaller unmarshaller = context.createUnmarshaller();
			final StringReader xmlTipsReader = new StringReader(typeTips);		
		
			return ((ContentInfoContainer)unmarshaller.
					unmarshal(xmlTipsReader)).contentInfos;
		} catch (JAXBException e) {
			throw new TipsEvaluationException("Errors in parsing type tips");
		}
		
	}		
}
