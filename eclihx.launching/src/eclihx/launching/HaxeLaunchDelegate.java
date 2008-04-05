package eclihx.launching;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.ILaunchManager;

import java.io.File;

import eclihx.core.EclihxCore;
import eclihx.core.EclihxLogger;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.launching.haxe.HaxeRunner;


public class HaxeLaunchDelegate implements ILaunchConfigurationDelegate{
	
	private IHaxeRunner chooseHaxeRunner(String mode) {
		/*if (ILaunchManager.DEBUG_MODE.equals(mode)) {
			//TODO 10 implement debugger!
			return new FlashDebugRunner();
		} else */if (ILaunchManager.RUN_MODE.equals(mode)) {
			return new HaxeRunner();
		}
		
		//TODO 5 shout loudly in this place
		return null;
		
	}

	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
		
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		
		monitor.beginTask("Launching...", 3);
		
		try {
			
			IHaxeRunner runner = chooseHaxeRunner(mode);
			HaxeRunnerConfiguration runnerConfiguration = new HaxeRunnerConfiguration();
			
			runnerConfiguration.load(configuration);
			
			runner.run(runnerConfiguration, launch, monitor);
			
            /*
            IStatus status = new Status(IStatus.ERROR, EclihxLauncher.PLUGIN_ID, 112, "", null); //$NON-NLS-1$
            IStatusHandler handler = DebugPlugin.getDefault().getStatusHandler(status);
            String projectName = configuration.getAttribute(IHaxeLaunchConfigurationConstants.PROJECT_NAME, (String) null);
            if (handler != null) {
            	handler.handleStatus(status, projectName);
            }*/
            	
           
        } catch (CoreException e) {
            EclihxLogger.logError(e);
            throw e;
        }
        finally {
            monitor.done();        	
        }
	}
	
	/*
	private void refreshOutputFolder(String projectName) {
		IHaxeProject haxeProject = EclihxCore.getDefault().getHaxeWorkspace().getHaxeProject(projectName);
		if (haxeProject != null) {
			IFolder folder = haxeProject.getPathManager().getOutputFolder();
			if (folder != null) {
				try {
					folder.refreshLocal(IResource.DEPTH_INFINITE, null);
				} catch (CoreException e) {
					EclihxLogger.logError(e);
				}
			}
		}
	}*/
	
	
	/**
	 * Checks if string isn't quoted yet and adds quotation marks to the both ends of the string.
	 * @param str
	 * @return Quoted string
	 */
	/*
	private String quoteString(String str) {
		if ( !(str.startsWith("\"") || (str.endsWith("\""))) ) {
			return "\"" + str + "\"";
		}
		return str;				
	}*/
	
	/*
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
	*/
	
	
}
