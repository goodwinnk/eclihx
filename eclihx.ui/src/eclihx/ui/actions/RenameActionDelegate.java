package eclihx.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;
import org.eclipse.ltk.ui.refactoring.resource.RenameResourceWizard;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import eclihx.core.haxe.model.HaxeSourceFile;
import eclihx.ui.internal.ui.views.HaxeExplorerView;

/**
 * Open a wizard for refactoring the haxe source file name.
 * @author Sylvain MOUQUET
 *
 */
public class RenameActionDelegate implements IObjectActionDelegate {

	private static final String ID = "eclihx.ui.actions.renameActionDelegate";

	public void run(IAction action) {
		IViewPart view = getView(HaxeExplorerView.HAXE_EXPLORER_ID);
		HaxeExplorerView adapter = (HaxeExplorerView) view.getAdapter(HaxeExplorerView.class);
		TreeSelection selection = (TreeSelection) adapter.getCommonViewer().getSelection();
		HaxeSourceFile haxeSourceFileSelected = (HaxeSourceFile) selection.getFirstElement();
		
		RenameResourceWizard refactoringWizard = new RenameResourceWizard(haxeSourceFileSelected.getBaseFile());
		RefactoringWizardOpenOperation op= new RefactoringWizardOpenOperation(refactoringWizard);
		
		try {
			op.run(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Rename resource");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public IViewPart getView(String id) {
		IViewReference viewReferences[] = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
		for (int i = 0; i < viewReferences.length; i++) {
			if (id.equals(viewReferences[i].getId())) {
				return viewReferences[i].getView(false);
			}
		}
		return null;
	}
		
	public void setActivePart(IAction action, IWorkbenchPart part) {
		
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
