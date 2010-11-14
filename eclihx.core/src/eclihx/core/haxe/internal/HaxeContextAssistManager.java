package eclihx.core.haxe.internal;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import eclihx.core.CorePreferenceInitializer;
import eclihx.core.EclihxCore;
import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.haxe.model.core.IHaxeSourceFile;
import eclihx.core.haxe.model.core.IHaxeSourceFolder;
import eclihx.core.util.OSUtil;
import eclihx.core.util.ProcessUtil;
import eclihx.core.util.ProcessUtil.ProcessExecResult;

/**
 * This class will generate content tips for the particular 
 * positions in the code. 
 */
public class HaxeContextAssistManager {

	private static final String OPEN_LIST_TAG = "<list>";
	private static final String CLOSE_LIST_TAG = "</list>";
	
	private static final String OPEN_TYPE_TAG = "<type>";
	private static final String CLOSE_TYPE_TAG = "</type>";
	
	private static final String OPEN_ROOT_TAG = "<root>";
	private static final String CLOSE_ROOT_TAG = "</root>";
		
	
	/**
	 * Get the tips for the defined position in file and 
	 * selected configuration.
	 * 
	 * @param haxeFile the haXe file for getting tip. 
	 * @param position offset in file where tip should be got.
	 * @return set of tips. 
	 */
	static public List<ContentInfo> getTips(IHaxeSourceFile haxeFile, int position) {

		HaxeConfiguration configuration = new HaxeConfiguration();
		
		configuration.addClassName(haxeFile.getDefaultClassName());
		configuration.setExplicitNoOutput();
		configuration.enableTips(haxeFile.getBaseFile().getLocation().toOSString(),	position);
		
		IHaxeSourceFolder[] sourceFolders = haxeFile.getHaxeProject().getSourceFolders();
		
		if (sourceFolders.length == 0) {
			EclihxCore.getLogHelper().logError(
					String.format(
							"Tips: There're no source folders in '%s' project",
							haxeFile.getHaxeProject().getName()));
			return new ArrayList<ContentInfo>();
		}
		
		for (IHaxeSourceFolder sourceFolder : sourceFolders) {
			configuration.addSourceDirectory(sourceFolder.getBaseFolder().getLocation().toOSString());
		}
		
		try {
			// TODO 9 need a warning here if preference hasn't been set. 
			final String haxePath = 
				EclihxCore.getDefault().getPluginPreferences().getString(
					CorePreferenceInitializer.HAXE_COMPILER_PATH); 
			
			File workingDirectory = haxeFile.getHaxeProject().getProjectBase().getLocation().toFile();
			
			ProcessExecResult executeProcessResult = ProcessUtil.executeProcess(
				OSUtil.quoteCompoundPath(haxePath) + " " + configuration.printConfiguration(), 
				workingDirectory
			);
			
			final String errors = executeProcessResult.getErrorsString();
			
			EclihxCore.getLogHelper().logInfo("Errors: " + errors);
			
			{
				// Read type content assist info.
				
				int startInfoListIndex = errors.indexOf(OPEN_LIST_TAG);
				int endInfoListIndex = errors.indexOf(CLOSE_LIST_TAG);
				
				if (startInfoListIndex != -1 && endInfoListIndex != -1) {
					
					final String typeTip = errors.substring(startInfoListIndex, 
							endInfoListIndex + CLOSE_LIST_TAG.length());
					
					final JAXBContext context = JAXBContext.newInstance(
							ContentInfoContainer.class);
					
					final Unmarshaller unmarshaller = context.createUnmarshaller();
					final StringReader xmlTipsReader = new StringReader(typeTip);		
			    
					return ((ContentInfoContainer)unmarshaller.
							unmarshal(xmlTipsReader)).contentInfos;
				
				}
			}
			
			{
				// Read call information.
				int startFunctionDescription = errors.indexOf(OPEN_TYPE_TAG);
				int endFunctionDescription = errors.indexOf(CLOSE_TYPE_TAG);
				
				if (startFunctionDescription != -1 && endFunctionDescription != -1) {
					
					final String callTip = errors.substring(startFunctionDescription, 
							endFunctionDescription + CLOSE_TYPE_TAG.length());
					
					final String wrappedTip = OPEN_ROOT_TAG + callTip + CLOSE_ROOT_TAG;
					
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
				}
			}
			
		} catch (InvalidConfigurationException e1) {
			e1.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<ContentInfo>();
	}
	
}
