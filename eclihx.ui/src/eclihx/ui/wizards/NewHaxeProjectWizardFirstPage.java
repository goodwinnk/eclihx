package eclihx.ui.wizards;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import eclihx.core.haxe.internal.HaxePreferencesManager;
import eclihx.core.util.OSUtil;

/**
 * First page of the haXe project creation wizard.
 */
public class NewHaxeProjectWizardFirstPage extends WizardNewProjectCreationPage {
	// TODO 3 move string constants to separate file

	/**
	 * Suffix for the build files.
	 */
	private static String buildExtenstionSuffix = OSUtil
			.getFullFileExtension(HaxePreferencesManager.BUILD_FILE_EXTENSION);

	// Editor fields.
	private Text buildFileField;
	private Text srcFolderField;
	private Text binFolderField;

	/*
	 * Validation on modifying attributes
	 */
	private final Listener propertiesModifyListener = new Listener() {
		public void handleEvent(Event e) {
			setPageComplete(validatePage());
		}
	};

	/**
	 * Default constructor.
	 */
	public NewHaxeProjectWizardFirstPage() {
		super("New haXe project");
		setTitle("Create a haXe project");
		setDescription("Create a haXe project in the workspace or in an external location.");
	}

	/**
	 * Returns the value of the project build file name field with leading and
	 * trailing spaces removed.
	 * 
	 * @return the value of the project build file name field
	 */
	private String getProjectBuildFileFieldValue() {
		if (buildFileField == null) {
			return "";
		}

		return buildFileField.getText().trim();
	}

	/**
	 * Returns project build file name with the .hxml extension
	 * 
	 * @return project file name field
	 */
	public String getProjectBuildFileName() {
		String buildFileName = getProjectBuildFileFieldValue();

		// If user has added extension we don't add it once more
		if (buildFileName.endsWith(buildExtenstionSuffix))
			return buildFileName;

		return (buildFileName + buildExtenstionSuffix);
	}

	/**
	 * Returns source folder with leading and trailing spaces removed.
	 * 
	 * @return source folder name
	 */
	public String getSourceFolder() {
		if (srcFolderField == null) {
			return "";
		}

		return srcFolderField.getText().trim();
	}

	/**
	 * Returns binary folder with leading and trailing spaces removed.
	 * 
	 * @return binary folder name
	 */
	public String getBinaryFolder() {
		if (srcFolderField == null) {
			return "";
		}

		return binFolderField.getText().trim();
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);

		Group group = new Group((Composite) getControl(), SWT.NONE);
		group.setText("Project settings");
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));

		// common data grid
		GridData dataFillHorizontal = new GridData(GridData.FILL_HORIZONTAL);

		// build file label
		Label buildFileLabel = new Label(group, SWT.NONE);
		buildFileLabel.setText("Build File (hxml):");
		buildFileLabel.setFont(parent.getFont());

		// build file entry field
		buildFileField = new Text(group, SWT.BORDER);
		buildFileField.setText("build");
		buildFileField.setFont(parent.getFont());
		buildFileField.setLayoutData(dataFillHorizontal);
		buildFileField.addListener(SWT.Modify, propertiesModifyListener);

		// source folder label
		Label srcFolderLabel = new Label(group, SWT.NONE);
		srcFolderLabel.setText("Source Folder:");
		srcFolderLabel.setFont(parent.getFont());

		// source folder entry field
		srcFolderField = new Text(group, SWT.BORDER);
		srcFolderField.setText("src");
		srcFolderField.setFont(parent.getFont());
		srcFolderField.setLayoutData(dataFillHorizontal);
		srcFolderField.addListener(SWT.Modify, propertiesModifyListener);

		// binary folder label
		Label binFolderLabel = new Label(group, SWT.NONE);
		binFolderLabel.setText("Binary Folder:");
		binFolderLabel.setFont(parent.getFont());

		// binary folder entry field
		binFolderField = new Text(group, SWT.BORDER);
		binFolderField.setText("bin");
		binFolderField.setFont(parent.getFont());
		binFolderField.setLayoutData(dataFillHorizontal);
		binFolderField.addListener(SWT.Modify, propertiesModifyListener);
	}

	@Override
	protected boolean validatePage() {
		if (!super.validatePage())
			return false;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		// Check project name is empty or not
		String projectBuildFileFieldContents = getProjectBuildFileFieldValue();
		if (projectBuildFileFieldContents.equals("")) {
			setErrorMessage(null);
			setMessage("Project build file name can't be empty");
			return false;
		}

		// Validate project name
		IStatus nameStatus = workspace.validateName(getProjectBuildFileName(),
				IResource.PROJECT);
		if (!nameStatus.isOK()) {
			setErrorMessage(nameStatus.getMessage());
			return false;
		}

		// Check source folder
		if (!getSourceFolder().equals("")) { // Source folder can be empty
			nameStatus = workspace.validateName(getSourceFolder(),
					IResource.FOLDER);
			if (!nameStatus.isOK()) {
				setErrorMessage(nameStatus.getMessage());
				return false;
			}
		}

		// Check binary folder
		if (!getBinaryFolder().equals("")) { // Binary folder can be empty
			nameStatus = workspace.validateName(getBinaryFolder(),
					IResource.FOLDER);
			if (!nameStatus.isOK()) {
				setErrorMessage(nameStatus.getMessage());
				return false;
			}
		}

		return true;
	}
}
