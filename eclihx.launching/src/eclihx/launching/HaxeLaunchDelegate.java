package eclihx.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;

import org.eclipse.core.runtime.Path;
import java.io.File;
import java.io.IOException;

import eclihx.core.EclihxLogger;

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
            }
            
            
            
            // TODO 8 get attributes
            String arguments = "";
                
            // output directory
            String directoryPath = configuration.getAttribute(IHaxeLaunchConfigurationConstants.OUTPUT_DIRECTORY, (String) null);
            File directory = (directoryPath != null) ? new File(directoryPath) : null;
            
            // source directory
            String sourceDirectory = configuration.getAttribute(IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, (String) null);
            
            // TODO 5 dirty hack
            if (sourceDirectory.endsWith("\\")) {
            	sourceDirectory = sourceDirectory.substring(0, sourceDirectory.length() - 1);
            }
            
            // hxml file
            String buildFilePath = configuration.getAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, (String) null);
            
            // build the command line
            IPath executableLocation = new Path(executablePath);
            String commandLine = executableLocation.toString() + ' ' + arguments + ' ' + "-cp \"" + sourceDirectory + "\" "  + buildFilePath;
            
            
            EclihxLogger.logInfo(commandLine);
            
            Process systemProcess = Runtime.getRuntime().exec(commandLine, null, directory);
            
            DebugPlugin.newProcess(launch, systemProcess, null);
            
            // TODO 8 update bin folder
            // IFolder executeFolder = ResourcesPlugin.getWorkspace().getRoot().getFolder(executableLocation);
            // executeFolder.refreshLocal(2, new NullProgressMonitor());
            
            
            
        } catch (CoreException e) {
            EclihxLogger.logError(e);
        } catch (IOException e) {
            // stop all
            monitor.done();
        }
        
        
		
	}
	
}
