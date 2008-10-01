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
	 * @param mainClass Startup class for the tip
	 * @param fileName file name where tip should be got. 
	 * @param position offset in file where tip should be got.
	 * @param outputDirectory project output directory. 
	 * @param sourceDirectory project source directory.
	 * @return set of tips. 
	 */
	static public ArrayList<ContentInfo> getTips(
			String mainClass, 
			String fileName, 
			int position,
			File outputDirectory,
			String sourceDirectory) {

		HaxeConfiguration configuration = new HaxeConfiguration();
		
		configuration.setStartupClass(mainClass);
		configuration.setExplicitNoOutput();
		configuration.enableTips(fileName, position);
		configuration.addSourceDirectory(sourceDirectory);
		
		//new HaxeLauncher().run(configuration, launch, compilerPath, outputPath)
		
		try {
			
			final StringBuilder errorString = new StringBuilder();
			final StringBuilder outputString = new StringBuilder();
			
			// TODO 9 need a warning here if preference hasn't been set. 
			final String haxePath = 
				EclihxCore.getDefault().getPluginPreferences().getString(
					CorePreferenceInitializer.HAXE_COMPILER_PATH); 
			
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
				
				String tipsString = errors.substring(  
						errors.indexOf("<list>"), 
						errors.lastIndexOf("</list>") + 7);
				
				EclihxCore.getLogHelper().logInfo("Tips: " + tipsString);
				
				JAXBContext context = JAXBContext.newInstance(
						ContentInfoContainer.class);
				
				Unmarshaller unmarshaller = context.createUnmarshaller();
				StringReader xmlTipsReader = new StringReader(tipsString);		
		    
				return ((ContentInfoContainer)unmarshaller.
						unmarshal(xmlTipsReader)).contentInfos;
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
