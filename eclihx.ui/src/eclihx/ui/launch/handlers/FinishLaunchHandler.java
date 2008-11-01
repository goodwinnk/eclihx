package eclihx.ui.launch.handlers;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.IStatusHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.util.OSUtil;
import eclihx.launching.HaxeLaunchDelegate.FinishLaunchInfo;
import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Handlers the end of launch operation.
 */
public final class FinishLaunchHandler implements IStatusHandler {

	/**
	 * Find the appreciate console.
	 * @param name the name of the console
	 * @return the console object.
	 */
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
	
	/**
	 * Makes paths in the output string relative to the project.
	 * @param output the output string.
	 * @param haxeProject the project.
	 * @return new string with paths relative to project.
	 */
	private String makeRelativePaths(String output, IHaxeProject haxeProject) {
		
		final String projectPath = OSUtil.repalceToHaxeOutputSlashes(
				haxeProject.getProjectBase().getLocation().toString());
		
		final int pathLength = projectPath.length();
		final String projectReplaceString = haxeProject.getName();
		
		final StringBuilder outputString = new StringBuilder(output);
		
		int tempIndex = -1;
		while((tempIndex = outputString.indexOf(projectPath)) != -1) {
			outputString.replace(
					tempIndex, tempIndex + pathLength, projectReplaceString);
		}
		
		return outputString.toString();
	}

	/**
	 * Method prints the output of the launching to console.
	 * @param output string to show on the console view.
	 * @param haxeProject the project this launch was performed for.
	 * @throws PartInitException 
	 */
	private void printOutputToConsole(String output, IHaxeProject haxeProject) 
			throws PartInitException {

		MessageConsole myConsole = findConsole("EclihxLaunchConsole");
		
		myConsole.clearConsole();
		MessageConsoleStream out = myConsole.newMessageStream();
		out.println(makeRelativePaths(output, haxeProject));
		
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().
						getActivePage().showView(IConsoleConstants.ID_CONSOLE_VIEW);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
 
		
		
	}

	/**
	 * Method refreshes the output folder of the given project.
	 * @param haxeProject the project for refreshing.
	 * @throws CoreException
	 */
	private void refreshOutputFolder(IHaxeProject haxeProject) 
			throws CoreException {
		
		IFolder outputFolder = haxeProject.getPathManager().getOutputFolder();

		outputFolder.refreshLocal(IResource.DEPTH_INFINITE,
				new NullProgressMonitor());
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.IStatusHandler#handleStatus(org.eclipse.core.runtime
	 * .IStatus, java.lang.Object)
	 */
	@Override
	public Object handleStatus(IStatus status, Object source)
			throws CoreException {
		FinishLaunchInfo finishInfo = (FinishLaunchInfo) source;

		IHaxeProject haxeProject = EclihxCore.getDefault().getHaxeWorkspace()
				.getHaxeProject(finishInfo.getProjectName());
		
		if (haxeProject != null) {
		
			refreshOutputFolder(haxeProject);
			printOutputToConsole(finishInfo.getOutput(), haxeProject);
		
		} else {
			
			EclihxUIPlugin.getLogHelper().logError(
				new CoreException(new Status(
						IStatus.ERROR, 
						EclihxUIPlugin.PLUGIN_ID, 
						"FinishLaunchHandler is called for invalid haXe project"
						)));
						
		}	

		return null;
	}
}
