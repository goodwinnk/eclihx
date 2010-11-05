package eclihx.launching.flash;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.IStatusHandler;

import eclihx.core.util.OSUtil;
import eclihx.debug.flash.FlashRunner;
import eclihx.launching.EclihxLauncher;
import eclihx.launching.HaxeRunnerConfiguration;
import eclihx.launching.IHaxeRunner;

/**
 * Should connect to flash and allow to debug...
 * This class won't be very beautiful for some time.
 * TODO 5 refactor this class!!!
 */
public class FlashDebugRunner implements IHaxeRunner {

	// FIXME 3 Copied from HaxeRunner
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
	 * Check if we have valid configuration for launching
	 * 
	 * @param config
	 * @throws CoreException
	 */
	// FIXME 3 Copied from HaxeRunner
	private void validateConfiguration(
			HaxeRunnerConfiguration config) throws CoreException {
		
		IStatus status = OSUtil.validateCompilerPath(config.getCompilerPath());
		if (!status.isOK()) {
			throwState(IStatus.ERROR, IStatus.ERROR, status.getMessage());
		}
	}
	
	public String run(HaxeRunnerConfiguration configuration, ILaunch launch,
			IProgressMonitor monitor) throws CoreException {

		validateConfiguration(configuration);
		
		(new FlashRunner()).run(launch, configuration.getBuildFile(), configuration.getWorkingDirectory());

		return "Flash debug runner. This feature isn't ready!";
		
		/*
		int requestPort = -1;
		int eventPort = -1;
		
		requestPort = 3001; //findFreePort();
		eventPort = 3002;   //findFreePort();
		if (requestPort == -1 || eventPort == -1) {
			return; //abort("Unable to find free port", null);
		}*/
		
		// special debug parameters
		//commandList.add("-debug");
		//commandList.add("" + requestPort);
		//commandList.add("" + eventPort);
					
		//String[] commandLine = 
		//		(String[]) commandList.toArray(new String[commandList.size()]);
		
		//String[] commandLine = new String[]{""};
		
		
		
		//Process process = DebugPlugin.exec(commandLine, null);
		//IProcess p = 
		//		DebugPlugin.newProcess(launch, process, "label" /*path*/);
		
		
		//IDebugTarget target = 
		//		new FlashDebugTarget(/*launch, p, requestPort, eventPort*/);
		//launch.addDebugTarget(target);			
	}
}
