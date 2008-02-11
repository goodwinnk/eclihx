/** From Eclihaxe project
 *  06.01.2008 */

package eclihx.ui.internal.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;

public abstract class AbstractProjectRelativeWizard extends Wizard {

	protected IProject project = null;
	protected IResource resource = null;

	public AbstractProjectRelativeWizard() {
		setNeedsProgressMonitor(true);
	}

	public IProject getProject() {
		return project;
	}

	public IResource getResource() {
		return resource;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection != null) {
			Object firstElement = selection.getFirstElement();
			if (firstElement instanceof IResource) {
				resource = (IResource) firstElement;
				if (firstElement instanceof IProject) {
					project = (IProject) firstElement;
				} else {
					project = resource.getProject();
				}
			}
		}
	}

	public boolean performCancel() {
		return runCancelOperation();
	}

	public boolean performFinish() {
		return runFinishOperation();
	}

	protected abstract void doCancel(IProgressMonitor monitor);

	protected abstract void doFinish(IProgressMonitor monitor);

	/**
	 * Run doCancel with progress
	 * 
	 * @return
	 */
	public boolean runCancelOperation() {

		IRunnableWithProgress op = new WorkspaceModifyDelegatingOperation(
				new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException {
						try {
							doCancel(monitor);
						} catch (Exception e) {

						} finally {
							monitor.done();
						}
						return;
					}

				});
		try {
			getContainer().run(false, false, op);
		} catch (InvocationTargetException e) {
			return false;
		} catch (InterruptedException e) {
			return false;
		}
		return true;

	}

	/**
	 * Run doFinish with progress
	 * 
	 * @return
	 */
	public boolean runFinishOperation() {

		IRunnableWithProgress op = new WorkspaceModifyDelegatingOperation(
				new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException {
						try {
							doFinish(monitor);
						} catch (Exception e) {

						} finally {
							monitor.done();
						}
						return;
					}

				});
		try {
			getContainer().run(false, false, op);
		} catch (InvocationTargetException e) {

			return false;
		} catch (InterruptedException e) {
			return false;
		}
		return true;

	}

	/**
	 * Show async error messge
	 * 
	 * @param message
	 */
	protected void showErrorBox(final String message) {
		getShell().getDisplay().asyncExec(new Runnable() {

			public void run() {
				MessageDialog.openError(getShell(), "EHX", message);
			}

		});
	}
}
