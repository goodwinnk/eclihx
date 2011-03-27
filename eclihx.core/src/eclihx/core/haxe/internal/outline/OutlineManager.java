package eclihx.core.haxe.internal.outline;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;

import eclihx.core.CorePreferenceInitializer;
import eclihx.core.EclihxCore;
import eclihx.core.haxe.HaxeLauncher;
import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.HaxeConfigurationList;
import eclihx.core.haxe.internal.parser.BuildParamParser;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeSourceFile;
import eclihx.core.util.SimpleTemplateProcessor;
import eclihx.core.util.SimpleTemplateProcessor.ReplacePair;

/**
 * Class is responsible for getting information about classes in specified
 * modules.
 */
public class OutlineManager {
	
	private ArrayList<String> errors = new ArrayList<String>();
	
	/**
	 * Get the information for the haxe file.
	 * @param sourceFile the file
	 * @return {@link Module} with info or null.
	 */
	public OutlineInfo getModuleInfo(IHaxeSourceFile sourceFile) {
		
		if (sourceFile == null) {
			return null;
		}
		
		errors.clear();
		
		File tmpDir = new File(System.getProperty("java.io.tmpdir"));		
		
		
		sourceFile.getPackage().toString();
		
		// Prepare replacements
		ArrayList<ReplacePair> replacements = new ArrayList<SimpleTemplateProcessor.ReplacePair>();
		replacements.add(new SimpleTemplateProcessor.ReplacePair("module", sourceFile.getDefaultClassName()));
		
		File outputFile = new File(tmpDir, "OutlineOutput.xml");
		replacements.add(new SimpleTemplateProcessor.ReplacePair("output", outputFile.getAbsolutePath().replace("\\", "\\\\")));
		
		// Execute processor
		SimpleTemplateProcessor processor = new SimpleTemplateProcessor(replacements);
		
		String filePath = getTemplatePath();
		if (filePath == null) {
			return new OutlineInfo(errors);
		}
		processor.resolveTemplate(new File(filePath), new File(tmpDir, "OutlineMacro.hx"));
		
		// Prepare to execute haxe compiler
		HaxeConfiguration haxeConfig = getOutlineConfiguration(sourceFile.getHaxeProject());
		if (haxeConfig == null) {
			return new OutlineInfo(errors);
		}
		
		String haxeCompilerPath = getHaxeCompilerPath();
		if (haxeCompilerPath == null) {
			return new OutlineInfo(errors);
		}
		
		HaxeLauncher haxeLauncher = new HaxeLauncher();
		try {
			haxeLauncher.run(haxeConfig, null, haxeCompilerPath, tmpDir);
		} catch (CoreException e) {
			errors.add(e.getMessage());
			return new OutlineInfo(errors);
		}
		
		if (!haxeLauncher.getErrorString().isEmpty()) {
			errors.add(haxeLauncher.getErrorString());
			return new OutlineInfo(errors);
		}
		
		Module module = null;
		
		// Unmarshal prepared xml
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Module.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			module = (Module) unmarshaller.unmarshal(new FileInputStream(outputFile));
		} catch (Exception e) {
			errors.add(e.getMessage());
			return new OutlineInfo(errors);
		}
		
		
		return new OutlineInfo(module);
	}
	
	private String getTemplatePath() {
		try {
			return FileLocator.toFileURL(new URL("platform:/plugin/eclihx.core/haxe/OutlineMacro.hx")).getPath();
		} catch (MalformedURLException e) {
			errors.add(e.getMessage());
		} catch (IOException e) {
			errors.add(e.getMessage());
		}
		
		return null;
	}
	
	private String getHaxeCompilerPath() {
		final String haxePath = 
			EclihxCore.getDefault().getPluginPreferences().getString(
				CorePreferenceInitializer.HAXE_COMPILER_PATH);
		
		if (haxePath == null || haxePath.isEmpty()) {
			errors.add("No haxe compiler path defined");
		}
		
		return haxePath;
	}
	
	private HaxeConfiguration getOutlineConfiguration(IHaxeProject project) {		
		
		String absoluteContentAssistFilePath = project.getContentAssistBuildFileAbsolute();
		
		if (absoluteContentAssistFilePath != null) {
			
			BuildParamParser buildParamParser = new BuildParamParser();
			
			HaxeConfigurationList configurations;
			try {
				HaxeConfiguration configuration;
				configurations = buildParamParser.parseFile(absoluteContentAssistFilePath, 
						project.getBaseResource().getLocation().toOSString());
				
				configuration = configurations.getMainConfiguration();
				configuration.makePathsAbsolute(project.getProjectBase().getLocation().toFile());
				configuration.setExplicitNoOutput();
				configuration.getMacroCalls().clear();
				configuration.addMacroCall("OutlineMacro.traceClassInfo()");
				configuration.setStartupClass("OutlineMacro");
				
				return configuration;
			} catch (Exception e) {
				errors.add(e.getMessage());
				return null;
			}
			
			
		}
		
		errors.add("No haxe compiler path defined");		
		return null;
	}
}
