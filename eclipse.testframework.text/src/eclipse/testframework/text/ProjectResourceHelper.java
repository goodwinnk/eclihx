package eclipse.testframework.text;

import java.lang.reflect.Field;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Synchronizer;

public class ProjectResourceHelper {

	private static final int MAX_RETRY= 5;
	
	/**
	 * Removes a resource project.
	 *
	 * @param elem The element to remove
	 * @throws CoreException Removing failed
	 */
	public static void delete(final IProject project) throws CoreException {
		IWorkspaceRunnable runnable= new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				delete((IResource)project);
			}
		};
		ResourcesPlugin.getWorkspace().run(runnable, null);
		emptyDisplayLoop();
	}

	private static void delete(IResource resource) throws CoreException {
		for (int i= 0; i < MAX_RETRY; i++) {
			try {
				resource.delete(true, null);
				i= MAX_RETRY;
			} catch (CoreException e) {
				if (i == MAX_RETRY - 1) {
					throw e;
				}
				try {
					Thread.sleep(1000); // sleep a second
				} catch (InterruptedException e1) {
				}
			}
		}
	}

	/**
	 * Add nature to the project.
	 * 
	 * @param proj
	 * @param natureId
	 * @param monitor
	 * @throws CoreException
	 */
	public static void addNatureToProject(IProject proj, String natureId, IProgressMonitor monitor) throws CoreException {
		IProjectDescription description = proj.getDescription();
		String[] prevNatures= description.getNatureIds();
		String[] newNatures= new String[prevNatures.length + 1];
		System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
		newNatures[prevNatures.length]= natureId;
		description.setNatureIds(newNatures);
		proj.setDescription(description, monitor);
	}

	public static void emptyDisplayLoop() {
		boolean showDebugInfo= false;

		Display display= Display.getCurrent();
		if (display != null) {
			if (showDebugInfo) {
				try {
					Synchronizer synchronizer= display.getSynchronizer();
					Field field= Synchronizer.class.getDeclaredField("messageCount");
					field.setAccessible(true);
					System.out.println("Processing " + field.getInt(synchronizer) + " messages in queue");
				} catch (Exception e) {
					// ignore
					System.out.println(e);
				}
			}
			while (display.readAndDispatch()) { /*loop*/ }
		}
	}
}
