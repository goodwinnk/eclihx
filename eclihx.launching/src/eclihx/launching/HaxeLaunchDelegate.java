package eclihx.launching;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;

import java.io.File;

import eclihx.core.EclihxCore;
import eclihx.core.EclihxLogger;
import eclihx.core.haxe.model.core.IHaxeProject;


public class HaxeLaunchDelegate implements ILaunchConfigurationDelegate{

	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
		try {
			
            String executablePath = configuration.getAttribute(IHaxeLaunchConfigurationConstants.HAXE_COMPILER_PATH, "");
            
            if (executablePath == null || executablePath.isEmpty()) {
            	// If we have no special executable path - use default one
            	executablePath = EclihxLauncher.getDefault().getPluginPreferences().getString(LauncherPreferenceInitializer.ECLIHAXE_HAXE_COMPILER_PATH);
            }
            
            if (executablePath == null || executablePath.isEmpty()) {
            	// TODO 4 Move this message to configuration page
            	EclihxLogger.logInfo("You should choose haXe launcher first.");
            	return;
            }
            
            // TODO 8 get attributes
            String arguments = "";
                
            // output directory
            String outputPath = configuration.getAttribute(IHaxeLaunchConfigurationConstants.OUTPUT_DIRECTORY, (String) null);
            File directory = (outputPath != null) ? new File(outputPath) : null;
            
            // source directory
            String sourceDirectory = configuration.getAttribute(IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, (String) null);
                        
            // TODO 5 dirty hack
            if (sourceDirectory.endsWith("\\")) {
            	sourceDirectory = sourceDirectory.substring(0, sourceDirectory.length() - 1);
            }
            
            // hxml file
            String buildFilePath = configuration.getAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, (String) null);
            
            String commandLine = executablePath + ' ' + arguments + ' ' + "-cp " + quoteString(sourceDirectory) + " "  + buildFilePath;
            
            Process systemProcess = DebugPlugin.exec(DebugPlugin.parseArguments(commandLine), directory);
            
            DebugPlugin.newProcess(launch, systemProcess, null);
            
            // TODO 8 update bin folder
            String projectName = configuration.getAttribute(IHaxeLaunchConfigurationConstants.PROJECT_NAME, (String) null);
            IHaxeProject haxeProject = EclihxCore.getDefault().getHaxeWorkspace().getHaxeProject(projectName);            
            IFolder outputFolder = haxeProject.getPathManager().getOutputFolder();
            outputFolder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
            
        } catch (CoreException e) {
            EclihxLogger.logError(e);
        }
        finally {
            monitor.done();        	
        }
	}
	
	/**
	 * Checks if string isn't quoted yet and adds quotation marks to the both ends of the string.
	 * @param str
	 * @return Quoted string
	 */
	private String quoteString(String str) {
		if ( !(str.startsWith("\"") || (str.endsWith("\""))) ) {
			return "\"" + str + "\"";
		}
		return str;				
	}
}
