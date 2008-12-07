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
import eclihx.core.haxe.internal.configuration.HaxeConfigurationList;
import eclihx.core.haxe.internal.parser.BuildParamParser;
import eclihx.core.util.OSUtil;
import eclihx.core.util.console.parser.core.ParseError;
import eclihx.launching.EclihxLauncher;
import eclihx.launching.HaxeRunnerConfiguration;
import eclihx.launching.IHaxeRunner;

/**
 * Class starts the haXe compiler process. 
 */
public class HaxeRunner implements IHaxeRunner {
	
	/**
	 * Method generates new core exception
	 * @param e exception to wrap
	 * @return new CoreException object.
	 */
	private CoreException generateCoreException(Exception e) {
		return new CoreException(
			new Status(
				Status.ERROR, EclihxLauncher.PLUGIN_ID, 
				e.getMessage())
		);
	}
	
	/**
	 * 
	 * @param severity
	 * @param code
	 * @param message
	 * @throws CoreException
	 */
	private void throwState(int severity, int code, String message) 
			throws CoreException {
		
		IStatus status = new Status(
				severity, EclihxLauncher.PLUGIN_ID, code, message, null);
		
		IStatusHandler handler = 
				DebugPlugin.getDefault().getStatusHandler(status);
	
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
	 * Check if we have valid configuration for launching.
	 * 
	 * @param config
	 * @throws CoreException
	 */
	private void validateConfiguration(HaxeRunnerConfiguration config) 
			throws CoreException {
		
		IStatus status = OSUtil.validateCompilerPath(config.getCompilerPath());
		if (!status.isOK()) {
			throwState(IStatus.ERROR, IStatus.ERROR, status.getMessage());
		}
		
		if (!(new File(config.getCompilerPath()).exists())) {
			throwState(IStatus.ERROR, IStatus.ERROR, 
					String.format("There are no compiler at '%s.'", 
								config.getCompilerPath()));
		}
	
		// TODO 6 Get the validation.
		if (config.getOutputDirectory() == null || 
				config.getOutputDirectory().isEmpty()) {
			throwState(IStatus.ERROR, IStatus.OK, 
					"Output directory isn't defined.");
		}
		
		if (config.getSourceDirectory() == null || 
				config.getSourceDirectory().isEmpty()) {
			throwState(IStatus.ERROR, IStatus.OK, 
					"Source directory isn't defined.");
		}		
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.launching.IHaxeRunner#run(eclihx.launching.HaxeRunnerConfiguration, org.eclipse.debug.core.ILaunch, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public String run(HaxeRunnerConfiguration configuration, 
					ILaunch launch, 
					IProgressMonitor monitor) throws CoreException {
		
		// Validates haXe configuration.
		validateConfiguration(configuration);
		
		try {

			// output directory
	        File outputDirectory = new File(configuration.getOutputDirectory());
	        
	        BuildParamParser parser = new BuildParamParser();
	                
	        //HaxeConfiguration haxeConfig;
			
			//haxeConfig = 
			//		parser.parseFile(
			//			configuration.getBuildFile()).getMainConfiguration();
	        HaxeConfigurationList executionList = 
	        		parser.parseFile(configuration.getBuildFile());
	        
	        String fullOutput = "";
	        
	        for (HaxeConfiguration haxeConfig : executionList) {
	        	haxeConfig.addSourceDirectory(configuration.getSourceDirectory());
				
				final HaxeLauncher launcher = new HaxeLauncher();
				
				launcher.run(haxeConfig, launch, configuration.getCompilerPath(), 
								outputDirectory);
				
				String errorsString = launcher.getErrorString();
				String outputString = launcher.getOutputString();
				
				fullOutput += outputString;
				
				if (!errorsString.isEmpty()) {
					return errorsString;
				}
	        }
			
			return "Building complete.\n" + fullOutput;
			
		} catch (ParseError e) {
			throw generateCoreException(e);
		}
	}
}
