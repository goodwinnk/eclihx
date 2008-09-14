package eclihx.launching.haxe;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.IStatusHandler;

import eclihx.core.haxe.HaxeLauncher;
import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.haxe.internal.parser.BuildParamParser;
import eclihx.core.util.console.parser.core.ParseError;
import eclihx.launching.EclihxLauncher;
import eclihx.launching.HaxeRunnerConfiguration;
import eclihx.launching.IHaxeRunner;

public class HaxeRunner implements IHaxeRunner {
	
	private CoreException generateCoreException(Exception e) {
		return new CoreException(
			new Status(
				Status.ERROR, "eclihx.core", 
				"Invalid configuration for launch: " + e.getMessage())
		);
	}
	
	
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
        
        BuildParamParser parser = new BuildParamParser();
                
        HaxeConfiguration haxeConfig;
		try {
			haxeConfig = parser.parseFile(configuration.getBuildFile()).getMainConfiguration();
			haxeConfig.addSourceDirectory(configuration.getSourceDirectory());
			
			new HaxeLauncher().run(
			  	haxeConfig, launch, 
			   	configuration.getCompilerPath(), outputDirectory);
			
		} catch (InvalidConfigurationException e) {
			throw generateCoreException(e);
		} catch (ParseError e) {
			throw generateCoreException(e);
		}
	}
}
