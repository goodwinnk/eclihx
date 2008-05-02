package eclihx.debug.flash;

import java.io.IOException;
import java.util.LinkedList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IProcess;


import flash.tools.debugger.Bootstrap;
import flash.tools.debugger.Session;
import flash.tools.debugger.SessionManager;
import flash.tools.debugger.VersionException;

import eclihx.core.haxe.internal.BuildParamParser;
import eclihx.core.haxe.internal.IBuildParamsContainer;
import eclihx.core.haxe.internal.ParamsContainer;


public class FlashRunner {
	
	private CoreException generateError(String message, Throwable exception) {
		return new CoreException(new Status(IStatus.OK, "debug", message, exception));
	}
	
	public FlashRunner() {
		// TODO Auto-generated constructor stub
	}

	public void run(ILaunch launch, String fileName, String outputFolder) throws CoreException {
		try {
			
			LinkedList<IBuildParamsContainer> paramContainersList = BuildParamParser.parseFile(fileName);
			if (paramContainersList.isEmpty()) return;
			
			String uriPath = outputFolder + "/" + paramContainersList.getFirst().getOutputFileName();

			SessionManager flashManager = Bootstrap.sessionManager();

			if (flashManager.supportsLaunch()) {

				flashManager.startListening();
				Session debugSession = flashManager.launch(uriPath, null, true, null);
				//flashManager.accept(null);
				
				IProcess debugProcess = DebugPlugin.newProcess(launch,
						debugSession.getLaunchProcess(),
						"Haxe-Flash debug process");

				if (debugSession != null) {
					IDebugTarget target = new FlashDebugTarget(uriPath, launch,
							debugSession, debugProcess);
					launch.addDebugTarget(target);
				} else {					
				}
				
			} else {
				// TODO 8 the debugger will have to tell the user to manually launch the Flash player 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw generateError(e.toString(), e);
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw generateError(e.toString(), e);
		}
	}

}
