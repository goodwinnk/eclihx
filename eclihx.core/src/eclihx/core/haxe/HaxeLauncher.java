package eclihx.core.haxe;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.util.OSUtil;
import eclihx.core.util.ProcessUtil;

/**
 * Class launches the haXe process with the given configuration.
 */
public class HaxeLauncher {
	
	/**
	 * Errors of the execution.
	 */
	private String errorsString = "";

	/**
	 * Output string of the execution.
	 */
	private String outputString = "";
	
	/**
	 * Runs the give configuration.
	 * 
	 * @param configuration the configuration to run.
	 * @param launch launch object
	 * @param compilerPath the compiler path.
	 * @param outputDirectory output directory.
	 * @throws CoreException exception 
	 * 		   if there are some errors during execution.
	 */
	public synchronized void run(
		HaxeConfiguration configuration, 
		ILaunch launch,
		String compilerPath,
		File outputDirectory) throws CoreException {

		String commandLine;
		try {		
						
			commandLine = 
				OSUtil.quoteCompoundPath(compilerPath) + " " + 
				configuration.printConfiguration();
	
			StringBuilder errors = new StringBuilder();
			StringBuilder output = new StringBuilder();
						
			ProcessUtil.executeProcess(
					commandLine, outputDirectory, errors, output);
			
			errorsString = errors.toString();
			outputString = output.toString();
			
			//EclihxCore.getLogHelper().logError(errors.toString());
			//EclihxCore.getLogHelper().logInfo(errors.toString());
			
		} catch (InvalidConfigurationException e) {
			throw new CoreException(
				new Status(
					Status.ERROR, EclihxCore.PLUGIN_ID, 
					"Invalid configuration for launch: " + e.getMessage()));
		}
	}
	
	
	/**
	 * Get the errors of the execution.
	 * @return the errors
	 */
	public synchronized String getErrorString() {
		return errorsString;
	}

	/**
	 * Get the output of the execution.
	 * @return the output
	 */
	public synchronized String getOutputString() {
		return outputString;
	}

}
