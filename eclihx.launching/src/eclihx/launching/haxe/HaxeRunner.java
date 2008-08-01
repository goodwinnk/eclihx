package eclihx.launching.haxe;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.IStatusHandler;

import eclihx.launching.EclihxLauncher;
import eclihx.launching.HaxeRunnerConfiguration;
import eclihx.launching.IHaxeRunner;

public class HaxeRunner implements IHaxeRunner {
	

	private void throwState(int severity, int code, String message) throws CoreException {
		IStatus status = new Status(severity, EclihxLauncher.PLUGIN_ID, code, message, null); //$NON-NLS-1$
		IStatusHandler handler = DebugPlugin.getDefault().getStatusHandler(status);
	
		if (handler == null) {
			// if there is no handler, throw the exception
			throw new CoreException(status);
		} else {
			Object result = handler.handleStatus(status, null);
			
			if (result instanceof Boolean) {
				boolean stop = ((Boolean)result).booleanValue();
				if (stop) {
					throw (new CoreException(status));
				}
			}
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
	
	/**
	 * Check if we have valid configuration for launching
	 * 
	 * @param config
	 * @return
	 * @throws CoreException
	 */
	private void validateConfiguration(HaxeRunnerConfiguration config) throws CoreException {
		if (config.getCompilerPath() == null || config.getCompilerPath().isEmpty()) {
			throwState(IStatus.ERROR, IStatus.OK, "haXe compiler wasn't defined properly.");
		}
		
		// TODO 9 make it work not only in windows
		if (!config.getCompilerPath().endsWith("haxe.exe")) { 
			throwState(IStatus.ERROR, IStatus.OK, "There should choose haxe.exe in compiler.");
		}
	
		if (config.getOutputDirectory() == null || config.getOutputDirectory().isEmpty()) {
			throwState(IStatus.ERROR, IStatus.OK, "Output directory isn't defined.");
		}
		
		if (config.getSourceDirectory() == null || config.getSourceDirectory().isEmpty()) {
			throwState(IStatus.ERROR, IStatus.OK, "Source directory isn't defined.");
		}		
	}

	public void run(HaxeRunnerConfiguration configuration, 
					ILaunch launch, 
					IProgressMonitor monitor) throws CoreException {
		
		validateConfiguration(configuration);		
		
        // output directory
        File outputDirectory = new File(configuration.getOutputDirectory());
        
        String commandLine = quoteString(configuration.getCompilerPath()) + 
                             " -cp " + quoteString(configuration.getSourceDirectory()) +  
                             ' ' + quoteString(configuration.getBuildFile());
        Process systemProcess = DebugPlugin.exec(DebugPlugin.parseArguments(commandLine), outputDirectory);
        
        DebugPlugin.newProcess(launch, systemProcess, null);
	}

}
