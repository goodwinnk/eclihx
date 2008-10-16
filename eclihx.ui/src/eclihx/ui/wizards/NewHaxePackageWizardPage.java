package eclihx.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.internal.HaxeElementValidator;
import eclihx.core.haxe.model.HaxeProject;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeSourceFolder;
import eclihx.ui.internal.ui.EclihxUIPlugin;
import eclihx.ui.internal.ui.utils.StandardDialogs;

/**
 * Page for the package creation.
 */
public final class NewHaxePackageWizardPage extends WizardPage {
	
	/**
	 * The haXe source folder.
	 */
	private IHaxeSourceFolder sourceFolder;
	
	/**
	 * Source folder text.
	 */
	private Text sourceFolderText;
	
	/**
	 * Package name text editor.
	 */
	private Text packageText;
	
	/**
	 * Selection of the wizard.
	 */
	private final ISelection selection;
	
	private final ModifyListener fUpdateDialogListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent modifyEvent) {
			dialogChangedHandler();
		}
	};

	/**
	 * Default constructor with the parameter of the selection.
	 * 
	 * @param selection original selection.
	 */
	public NewHaxePackageWizardPage(ISelection selection) {
		super("New haXe Package");
		
		setTitle("New haXe Package");
		setDescription("This wizard creates a new haXe package.");
		
		this.selection = selection;
	}

	/**
	 * Handler of the source folder browse button.
	 */
	private void onSourceFolderBrowseButton() {
		ElementTreeSelectionDialog dialog = 
			StandardDialogs.createHaxeSourceFoldersDialog(getShell());
		
		if (dialog.open() == Window.OK) {
			
			Object result = dialog.getFirstResult();
			
			if (result instanceof IHaxeSourceFolder) {
				
				IHaxeSourceFolder haxeSourceFolder = (IHaxeSourceFolder)result;
				
				updateSourceFolderField(haxeSourceFolder);

			} else {
				EclihxUIPlugin.getLogHelper().logError(
						"Ivalid result value in " + 
						"NewHaxePackageWizardPage.onSourceFolderBrowseButton");
			}			
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		
		Composite top = new Composite(parent, SWT.LEFT);

		top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		top.setLayout(new GridLayout(3, false));

		{
			// Add source folder group
			
			Label label = new Label(top, SWT.NULL);
			label.setText("Source folder:");
	
			sourceFolderText = new Text(top, SWT.BORDER);
			sourceFolderText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			sourceFolderText.setEditable(false); // Do not allow modification.
			sourceFolderText.addModifyListener(fUpdateDialogListener);
			
			
			Button sourceFolderBrowse = new Button(top, SWT.NULL);
			sourceFolderBrowse.setText("Browse...");
			
			sourceFolderBrowse.addSelectionListener(
					new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							onSourceFolderBrowseButton();
							//handleBrowse();
						}
					});		
		}
		
		{
			// Package name group
			
			Label label = new Label(top, SWT.NULL);
			label.setText("Package Name:");
	
			packageText = new Text(top, SWT.BORDER | SWT.SINGLE);
			
			packageText.setLayoutData(
					new GridData(GridData.FILL_HORIZONTAL));
			packageText.addModifyListener(fUpdateDialogListener);			
		}
		
		initialize();
		
		// Force remove message if the situation is invalid.
		setErrorMessage(null);
		
		setControl(top);
	}

	/**
	 * Method sets initial values of the fields according to the current
	 * selection.
	 */
	private void initialize() {
		
		// Reset fields.
		sourceFolderText.setText("");
		packageText.setText("");
		
		// Examine selection.
		if (!(selection == null || selection.isEmpty())) {
			
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structedSelection = 
						(IStructuredSelection) selection;
				
				// For the case when single element was selected
				if (structedSelection.size() == 1) {
					
					Object selectedElement = 
						structedSelection.getFirstElement();
					
					if (selectedElement instanceof IResource) {
						
						IProject project = 
								((IResource)selectedElement).getProject();
						
						if (HaxeProject.isHaxeProject(project)) {
							IHaxeProject haxeProject = 
								EclihxCore.getDefault().getHaxeWorkspace()
									.getHaxeProject(project.getName());
						
							IHaxeSourceFolder[] folders = 
									haxeProject.getSourceFolders();
							
							updateSourceFolderField(
									folders.length == 0 ? null : folders[0] );							
						}
						
						// TODO 7 Set initial package
					}
				}	
			}			
		}
	}

	/**
	 * Checks the changed dialog and validate the new state.
	 */
	private void dialogChangedHandler() {
		
		{
			// Source folder validate
			
			if (sourceFolder == null) {
				updateStatus("Source folder name is empty");
				return;
			}
			
			if (!sourceFolder.getBase().isAccessible()) {
				updateStatus("Source folder doesn't exist");
				
				EclihxUIPlugin.getLogHelper().logError(
						"Source folder doesn't exist");
				
				return;
			}
		}
		
		{
			String packageName = getPackageName();
			
			IStatus validateStatus = 
					HaxeElementValidator.validatePackageName(packageName);
			
			if (!validateStatus.isOK()) {
				updateStatus(validateStatus.getMessage());
				return;
			}
			
			// At this point sourceFolder shouldn't be null
			assert(sourceFolder != null);
			
			if (sourceFolder.hasPackage(packageName)) {
				updateStatus("Package already exists");
				return;
			}
			
		}
		
		updateStatus(null);
	}

	/**
	 * Updates status of the dialog. If there is no error message then the
	 * page is considered to be complete.
	 * 
	 * @param errorMessage the error message. If message is <code>null</code>
	 *        then the page is complete.
	 */
	private void updateStatus(String errorMessage) {
		setErrorMessage(errorMessage);
		setPageComplete(errorMessage == null);
	}
	
	/**
	 * Generate text representation for the haXe source folder.
	 * 
	 * @param folder new folder.
	 */
	public void updateSourceFolderField(IHaxeSourceFolder folder) {
		
		sourceFolder = folder;
		
		if (sourceFolder != null) {
			sourceFolderText.setText(String.format("%s/%s", 
					sourceFolder.getHaxeProject().getName(),
					sourceFolder.getBase().getProjectRelativePath().toString()));
		} else {
			sourceFolderText.setText("");
		}
		
	}

	/**
	 * Returns the the source folder of the package.
	 * @return the haXe source folder of the package.
	 */
	public IHaxeSourceFolder getSourceFolder() {
		return sourceFolder;
	}

	/**
	 * Returns the entered package name.
	 * @return the package name.
	 */
	public String getPackageName() {
		return packageText.getText();
	}
}