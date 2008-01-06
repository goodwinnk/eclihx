package eclihx.ui.wizards;

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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;


public class NewHaxeProjectWizardFirstPage extends WizardNewProjectCreationPage {

	private static String buildExtenstionSuffix = ".hxml"; 
	
	private Text buildFileField;
	private Text srcFolderField;
	private Text binFolderField;
	
    private Listener propertiesModifyListener = new Listener() {
        public void handleEvent(Event e) {
            setPageComplete(validatePage());
        }
    };

	
	public NewHaxeProjectWizardFirstPage() {
		super("New haXe project");
		setTitle("Create a haXe project"); //$NON-NLS-1$
		setDescription("Create a haXe project in the workspace or in an external location."); //$NON-NLS-1$
	}
	
    /**
     * Returns the value of the project build file name field
     * with leading and trailing spaces removed.
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
     * Returns source folder
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
     * Returns binary folder
     * 
     * @return binary folder name
     */
    public String getBinaryFolder() {
    	if (srcFolderField == null) {
    		return "";
    	}    		
    	
    	return binFolderField.getText().trim();
    }
    


	public void createControl(Composite parent) {
		super.createControl(parent);
	
		Group group = new Group((Composite)getControl(),SWT.NONE);
		group.setText("Project settings"); //$NON-NLS-1$
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		
		GridData dataFillHorizontal = new GridData(GridData.FILL_HORIZONTAL);
		//GridData dataRight = new GridData(SWT.END, SWT.CENTER, true, false);
		
		// build file label
        Label buildFileLabel = new Label(group, SWT.NONE);
        buildFileLabel.setText("Build File (hxml):");
        buildFileLabel.setFont(parent.getFont());
        //buildFileLabel.setLayoutData(dataRight);
 
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
        //srcFolderLabel.setLayoutData(dataRight);

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
        //binFolderLabel.setLayoutData(dataRight);
        
        // binary folder entry field
        binFolderField = new Text(group, SWT.BORDER);
        binFolderField.setText("bin");
        binFolderField.setFont(parent.getFont());
        binFolderField.setLayoutData(dataFillHorizontal);
        binFolderField.addListener(SWT.Modify, propertiesModifyListener);
	}
	
	protected boolean validatePage() {
		if (!super.validatePage()) return false;
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();

        String projectBuildFileFieldContents = getProjectBuildFileFieldValue();
        if (projectBuildFileFieldContents.equals("")) { //$NON-NLS-1$
            setErrorMessage(null);
            setMessage("Project build file name can't be empty");
            return false;
        }

        IStatus nameStatus = workspace.validateName(getProjectBuildFileName(), IResource.FILE);
        if (!nameStatus.isOK()) {
            setErrorMessage(nameStatus.getMessage());
            return false;
        }
        
        
        // Check source folder
        if (!getSourceFolder().equals("")) { // Source folder can be empty
	        nameStatus = workspace.validateName(getSourceFolder(), IResource.FOLDER);
	        if (!nameStatus.isOK()) {
	            setErrorMessage(nameStatus.getMessage());
	            return false;
	        }
        }
        
        // Check binary folder
        if (!getBinaryFolder().equals("")) { // Binary folder can be empty
	        nameStatus = workspace.validateName(getBinaryFolder(), IResource.FOLDER);
	        if (!nameStatus.isOK()) {
	            setErrorMessage(nameStatus.getMessage());
	            return false;
	        }
        }
        
		return true;		
	}
}
