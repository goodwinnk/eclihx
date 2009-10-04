package eclihx.ui.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.ltk.ui.refactoring.resource.MoveResourcesWizard;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Open a wizard for moving a haxe file.
 * @author Sylvain MOUQUET
 *
 */
public class MoveActionDelegate implements IObjectActionDelegate {

	private static final String ID = "eclihx.ui.actions.moveActionDelegate";

	public void run(IAction action) {
		// TODO : select the file in the navigator and not the file opened in the editor

		IEditorPart editor = EclihxUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		MoveResourcesWizard refactoringWizard = new MoveResourcesWizard(new IResource[] {((IFileEditorInput) editor.getEditorInput()).getFile()});
		RefactoringWizardOpenOperation op= new RefactoringWizardOpenOperation(refactoringWizard);
		
		try {
			op.run(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Move resource");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setActivePart(IAction action, IWorkbenchPart part) {
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
