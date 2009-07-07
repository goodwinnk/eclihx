package eclihx.core.haxe.internal;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;

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

/**
 * This class will generate content tips for the particular 
 * positions in the code. 
 */
public class HaxeContentAssistManager {

	/**
	 * Get the tips for the defined position in file and 
	 * selected configuration.
	 * @param haxeFile the haXe file for getting tip. 
	 * @param position offset in file where tip should be got.
	 * @return set of tips. 
	 */
	static public ArrayList<ContentInfo> getTips(IHaxeSourceFile haxeFile, int position) {

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
		
		//new HaxeLauncher().run(configuration, launch, compilerPath, outputPath)
		
		try {
			
			final StringBuilder errorString = new StringBuilder();
			final StringBuilder outputString = new StringBuilder();
			
			// TODO 9 need a warning here if preference hasn't been set. 
			final String haxePath = 
				EclihxCore.getDefault().getPluginPreferences().getString(
					CorePreferenceInitializer.HAXE_COMPILER_PATH); 
			
			File outputDirectory = new File(
					haxeFile.getHaxeProject().
							getOutputFolder().getBaseFolder().
									getLocation().toOSString());
			
			ProcessUtil.executeProcess(
				OSUtil.quoteCompoundPath(haxePath) 
				+ " " + configuration.printConfiguration(), 
				outputDirectory, errorString, outputString
			);
			
			final String errors = errorString.toString();
			final String output = outputString.toString();
			
			EclihxCore.getLogHelper().logInfo("Errors: " + errors);
			EclihxCore.getLogHelper().logInfo("Output: " + output);
			
			
			if (errors.indexOf("<list>") != -1) {
				
				String tipsString = errors;
				
				EclihxCore.getLogHelper().logInfo("Tips: " + tipsString);
				
				JAXBContext context = JAXBContext.newInstance(
						ContentInfoContainer.class);
				
				Unmarshaller unmarshaller = context.createUnmarshaller();
				StringReader xmlTipsReader = new StringReader(tipsString);		
		    
				return ((ContentInfoContainer)unmarshaller.
						unmarshal(xmlTipsReader)).contentInfos;
			
			} else if (errors.indexOf("<type>") != -1) {
				final ContentInfo callFunctionInfo = new ContentInfo();
				callFunctionInfo.type = errors.substring(  
						errors.indexOf("<type>") + 6, 
						errors.lastIndexOf("</type>"));
				
				final ArrayList<ContentInfo> infos = new ArrayList<ContentInfo>();
				infos.add(callFunctionInfo);
				
				return infos;
			}
			
		} catch (InvalidConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<ContentInfo>();
	}
	
}
