package eclihx.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.ltk.ui.refactoring.resource.RenameResourceWizard;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Open a wizard for refactoring the haxe source file name.
 * @author Sylvain MOUQUET
 *
 */
public class RenameActionDelegate implements IObjectActionDelegate {

	private static final String ID = "eclihx.ui.actions.renameActionDelegate";

	public void run(IAction action) {
		IEditorPart editor = EclihxUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		RenameResourceWizard refactoringWizard = new RenameResourceWizard(((IFileEditorInput) editor.getEditorInput()).getFile());
		RefactoringWizardOpenOperation op= new RefactoringWizardOpenOperation(refactoringWizard);
		
		try {
			op.run(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Rename resource");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setActivePart(IAction action, IWorkbenchPart part) {
		
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
