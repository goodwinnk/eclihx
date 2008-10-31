package eclihx.ui.launch.handlers;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.launching.HaxeLaunchDelegate.FinishLaunchInfo;

/**
 * Handlers the end of launch operation. 
 */
public final class FinishLaunchHandler implements IStatusHandler {
	
	private MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		// no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}
	
	private void printOutputToConsole(String output) throws PartInitException {
		
		 MessageConsole myConsole = findConsole("EclihxLaunchConsole");
		 MessageConsoleStream out = myConsole.newMessageStream();
		 out.println(output);
		 
	}


	private void refreshOutputFolder(String projectName) throws CoreException {
		IHaxeProject haxeProject = 
			EclihxCore.getDefault().getHaxeWorkspace().getHaxeProject(
					projectName);          
		
	    IFolder outputFolder = haxeProject.getPathManager().getOutputFolder();
	    
	    outputFolder.refreshLocal(
	    		IResource.DEPTH_INFINITE, new NullProgressMonitor());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.IStatusHandler#handleStatus(org.eclipse.core.runtime.IStatus, java.lang.Object)
	 */
	@Override
	public Object handleStatus(IStatus status, Object source) throws CoreException {
		FinishLaunchInfo finishInfo = (FinishLaunchInfo)source;
		
		refreshOutputFolder(finishInfo.getProjectName());
		
		printOutputToConsole(finishInfo.getOutput());
		
		return null;
	}
}
