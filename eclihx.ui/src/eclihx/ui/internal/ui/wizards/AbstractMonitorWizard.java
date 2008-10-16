package eclihx.ui.internal.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;

/**
 * Extension for the standard {@link Wizard} class with implementation of the
 * progress monitor based operations.
 * 
 * Instead of using <code>performFinish()</code> and
 * <code>performCancel()</code> methods subclasses should override
 * <code>doCancel()</code> and <code>doFinish()</code>.
 */
public abstract class AbstractMonitorWizard extends Wizard {

	/**
	 * Default constructor which enables the using of the progress monitor.
	 */
	public AbstractMonitorWizard() {
		setNeedsProgressMonitor(true);
	}

	/**
	 * Run doFinish with progress.
	 * 
	 * @return <code>true</code> to indicate the cancel request
     * was accepted, and <code>false</code> to indicate
     * that the cancel request was refused.	     
	 */
	@Override
	public final boolean performCancel() {
		
		IRunnableWithProgress op = new WorkspaceModifyDelegatingOperation(
				
				new IRunnableWithProgress() {
					@Override
					public void run(IProgressMonitor monitor)
							throws InvocationTargetException {
						try {
							doCancel(monitor);
						} catch (Exception e) {

						} finally {
							monitor.done();
						}
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
	 * Run doFinish with progress.
	 * 
	 * @return <code>true</code> to indicate the finish request
     * was accepted, and <code>false</code> to indicate
     * that the finish request was refused.
	 */
	@Override
	public final boolean performFinish() {

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
	 * Override this method to specify cancel operations for the wizard.
	 */
	protected abstract void doCancel(IProgressMonitor monitor);

	/**
	 * Override this method to specify finish operations for the wizard.
	 */
	protected abstract void doFinish(IProgressMonitor monitor);

	/**
	 * Show asynchronous error message.
	 * 
	 * @param message
	 */
	protected void showErrorBox(final String message) {
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				MessageDialog.openError(getShell(), "EclihX", message);
			}
		});
	}
}
