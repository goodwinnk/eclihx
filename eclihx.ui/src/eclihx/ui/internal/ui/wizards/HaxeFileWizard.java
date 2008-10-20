package eclihx.ui.internal.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import eclihx.core.haxe.model.core.IHaxePackage;
import eclihx.ui.wizards.NewHaxeFileWizardPage;

/**
 * Wizard for the new haXe file.
 */
public class HaxeFileWizard extends AbstractMonitorWizard implements INewWizard {

	/**
	 * The only page for haXe file creation.
	 */
	NewHaxeFileWizardPage haxeFilePage;
	
	/**
	 * The workbench selection.
	 */
	IStructuredSelection selection;
	
	/**
	 * Default constructor.
	 */
	public HaxeFileWizard() {
		super();
		
		setWindowTitle("New haXe file");
	}
	
	@Override
	protected void doCancel(IProgressMonitor monitor) {
	}

	@Override
	protected void doFinish(IProgressMonitor monitor) {
		
		final IHaxePackage haxePackage = haxeFilePage.getHaxePackage();
		final String haxeFileName = haxeFilePage.getFileName();
		
		monitor.beginTask("Creating haXe file " + haxeFileName, 1);
		
		try {
			haxePackage.createHaxeFile(haxeFileName, monitor);
		} catch (CoreException e) {
			showErrorBox(e.getMessage());
		}
		
		monitor.worked(1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		
		super.addPages();

		haxeFilePage = new NewHaxeFileWizardPage(
				"NewBuildFileWizardPage", selection);
		addPage(haxeFilePage);
	}	
}