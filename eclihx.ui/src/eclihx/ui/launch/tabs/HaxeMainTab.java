package eclihx.ui.launch.tabs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.AbstractElementListSelectionDialog;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import eclihx.launching.EclihxLauncher;
import eclihx.launching.IHaxeLaunchConfigurationConstants;
import eclihx.launching.LauncherPreferenceInitializer;
import eclihx.core.EclihxCore;
import eclihx.core.EclihxLogger;
import eclihx.core.haxe.model.HaxeWorkspace;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IProjectPathManager;


/**
 * This class contains functionality for the main launch configuration tab
 */
public final class HaxeMainTab extends AbstractLaunchConfigurationTab {
	
	// TODO 3 move strings constants to configure file

	/**
	 *  If this variable is <code>null</code> it's mean that we hadn't found the project.
	 */
	IHaxeProject haxeProject;
	String[] buildFilesCache;
	
	/* Controls */
	private Text projectNameText;
	private Text buildFileNameText;
	private Text sourceDirectoryText;
	private Text outputDirectoryText;
	private Button projectBuildButton;
	
	private ModifyListener fModifyListener = 
		new ModifyListener() { public void modifyText(ModifyEvent modifyEvent) { updateLaunchConfigurationDialog(); } };
	
	/* Base selection listener. It reacts to events ignoring them */	
	private class SelectionListenerClass implements SelectionListener { 
		public void widgetDefaultSelected(SelectionEvent e) {/*do nothing*/}
		public void widgetSelected(SelectionEvent e) { /*do nothing*/ }
	}	
	
	/**
	 * Get a project selector dialog
	 * @return
	 */
	private AbstractElementListSelectionDialog getHaxeProjectsDialog() {
		// TODO 4 make icons for project selection
		
		ILabelProvider labelProvider = new LabelProvider();
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
		
		dialog.setTitle("Select Haxe Project");
		dialog.setMessage("Enter a string to search for a project:");
		
		dialog.setElements(new HaxeWorkspace(getWorkspaceRoot()).getHaxeProjectsNames());
		
		dialog.setInitialSelections(new Object[] { projectNameText.getText() });
			
		return dialog;
	}	
	
	/**
	 * Choose a haXe build file dialog
	 * @return
	 */
	private AbstractElementListSelectionDialog getBuildFilesDialog() {
		// TODO 4 make icons for project selection
		
		assert(haxeProject != null); // Select build file should be disabled in this case
		
		ILabelProvider labelProvider = new LabelProvider();
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
		
		dialog.setTitle("Select Haxe Build File");
		dialog.setMessage("Enter a string to search for a file:");
		

		try {
			dialog.setElements(getAbsolutePaths(haxeProject.getBuildFiles()));
		} catch (CoreException e) {
			EclihxLogger.logError(e);
			dialog.setElements(new Object[0]);
		}
		
		dialog.setInitialSelections(new Object[]{buildFileNameText.getText()});
		
		return dialog;
	}		
	
	/**
	 * Get current workspace
	 * @return
	 */
	private IWorkspaceRoot getWorkspaceRoot() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}
	
	/**
	 * Converts IFile to absolute paths strings
	 */
	private String[] getAbsolutePaths(IFile[] files) {
		List<String> paths = new ArrayList<String>(files.length);
		for (IFile file : files) {
			paths.add(file.getLocation().toString());			
		}
		return paths.toArray(new String[0]);		
	}
	

	/**
	 * Handler for the button of project selection
	 * 
	 * @param event
	 */
	protected void onProjectButtonSelected(SelectionEvent event) {
		AbstractElementListSelectionDialog dialog = getHaxeProjectsDialog();
		
		if (dialog.open() == Window.OK) {
			String projectNewName = dialog.getFirstResult().toString();
			
			projectNameText.setText(projectNewName);
			
			// After that current project should be updated
			if (haxeProject != null) {
				IProjectPathManager pathManager = haxeProject.getPathManager();
				
				assert(buildFilesCache != null); // Because we should have set it with updating project variable
				
				if (buildFilesCache.length > 0) {
					buildFileNameText.setText(buildFilesCache[0]);
				} else {
					buildFileNameText.setText("");
				}
				
				// Output folder
				outputDirectoryText.setText(pathManager.getOutputFolder().getLocation().toString());
				// Source folder
				sourceDirectoryText.setText(pathManager.getSourceFolders().get(0).getLocation().toString());
			}
		}
	}
	
	/**
	 * Handler for the button of project target file selector
	 * @param event
	 */
	protected void onBuildFileSelected(SelectionEvent event) {
		AbstractElementListSelectionDialog dialog = getBuildFilesDialog();
		
		if (dialog.open() == Window.OK) {
			String buildFileName = dialog.getFirstResult().toString();
			
			if (buildFileName != null) {
				buildFileNameText.setText(buildFileName);
			} else {
				EclihxLogger.logInfo("The selected file wasn't found.");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#getImage()
	 */
	@Override
	public Image getImage() {
		// TODO 2 Add image for the main page of the launcher configuration
		return super.getImage();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite top = new Composite(parent, SWT.LEFT);

		top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		top.setLayout(new GridLayout());
		
		GridData horizontantalGrid = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL);
		GridLayout layout = new GridLayout(1, false);
		
		// Add project group
		Group projectGroup = new Group(top, SWT.NONE);
		projectGroup.setText("Project");
		projectGroup.setLayout(new GridLayout(2, false));
		projectGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		projectNameText = new Text(projectGroup,  SWT.BORDER);
		projectNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		projectNameText.setEditable(false); // Do not allow modification.
		projectNameText.setText("");
		projectNameText.addModifyListener(fModifyListener);
		projectNameText.addModifyListener(
			new ModifyListener () {
				public void modifyText(ModifyEvent me) {
					UpdateCurrentProject(projectNameText.getText());
				}
			} 
		);
		
		Button projectButton = createPushButton(projectGroup, "Select Project...", null);
		projectButton.addSelectionListener(
			new SelectionListenerClass () {
				@Override
				public void widgetSelected(SelectionEvent e) {
					onProjectButtonSelected(e);
				}
			});
		
		// Add build file group
		Group buildFileGroup = new Group(top, SWT.NONE);
		buildFileGroup.setText("Build file");
		buildFileGroup.setLayout(new GridLayout(2, false));
		buildFileGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		buildFileNameText = new Text(buildFileGroup,  SWT.BORDER);
		buildFileNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		buildFileNameText.setEditable(false);
		buildFileNameText.setText("");
		buildFileNameText.addModifyListener(fModifyListener);
		
		projectBuildButton = createPushButton(buildFileGroup, "Build file...", null);
		projectBuildButton.addSelectionListener(
			new SelectionListenerClass () {
				@Override
				public void widgetSelected(SelectionEvent event) {
					onBuildFileSelected(event);
				}
			});		
		
		// Add working directory group
		Group workingDirectoryGroup = new Group(top, SWT.NONE);
		workingDirectoryGroup.setText("Working Directory");
		workingDirectoryGroup.setLayout(layout);
		workingDirectoryGroup.setLayoutData(horizontantalGrid);
		
		sourceDirectoryText = new Text(workingDirectoryGroup,  SWT.BORDER);
		sourceDirectoryText.setLayoutData(horizontantalGrid);
		sourceDirectoryText.setEditable(false);
		sourceDirectoryText.setText("");
		sourceDirectoryText.addModifyListener(fModifyListener);
		
		// Output working directory group
		Group outputDirectoryGroup = new Group(top, SWT.NONE);
		outputDirectoryGroup.setText("Output Directory");
		outputDirectoryGroup.setLayout(layout);
		outputDirectoryGroup.setLayoutData(horizontantalGrid);
		
		outputDirectoryText = new Text(outputDirectoryGroup,  SWT.BORDER);
		outputDirectoryText.setLayoutData(horizontantalGrid);
		outputDirectoryText.setEditable(false);
		outputDirectoryText.setText("");
		outputDirectoryText.addModifyListener(fModifyListener);
		
		setControl(top);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
	 */
	public String getName() {
		return "Main";
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	public void initializeFrom(ILaunchConfiguration configuration) {
		initializeProjectName(configuration);
		initializeBuildFile(configuration);
		initializeWorkingDirectory(configuration);
		initializeOutputDirectory(configuration);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.PROJECT_NAME, projectNameText.getText());
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, buildFileNameText.getText());
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, sourceDirectoryText.getText());
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.OUTPUT_DIRECTORY, outputDirectoryText.getText());
		
		// TODO 1 Make separate place for overriding initializer
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.HAXE_COMPILER_PATH, EclihxLauncher.getDefault().getPluginPreferences().getString(LauncherPreferenceInitializer.ECLIHAXE_HAXE_COMPILER_PATH));
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.PROJECT_NAME, "");
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, "");
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, "");
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.OUTPUT_DIRECTORY, "");
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.HAXE_COMPILER_PATH, "");
	}
	
	/**
	 * Initialize project name field from the configuration 
	 */
	private void initializeProjectName(ILaunchConfiguration configuration) {
		String projectNameString = "";
		try {
			projectNameString = configuration.getAttribute(IHaxeLaunchConfigurationConstants.PROJECT_NAME, projectNameString);
		} catch (CoreException e) {
			EclihxLogger.logError(e);
		}
		
		projectNameText.setText(projectNameString);
	}
	
	/**
	 * Initialize working directory field from the configuration
	 */
	private void initializeWorkingDirectory(ILaunchConfiguration configuration) {
		String workingDirectoryString = "";
		try {
			workingDirectoryString = configuration.getAttribute(IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, workingDirectoryString);
		} catch (CoreException e) {
			EclihxLogger.logError(e);
		}
		sourceDirectoryText.setText(workingDirectoryString);		
	}
	
	/**
	 * Initialize working directory field from the configuration
	 */
	private void initializeOutputDirectory(ILaunchConfiguration configuration) {
		String outputDirectoryString = "";
		try {
			outputDirectoryString = configuration.getAttribute(IHaxeLaunchConfigurationConstants.OUTPUT_DIRECTORY, outputDirectoryString);
		} catch (CoreException e) {
			EclihxLogger.logError(e);
		}
		outputDirectoryText.setText(outputDirectoryString);		
	}
	
	/**
	 * Initialize build file field from the configuration
	 */
	private void initializeBuildFile(ILaunchConfiguration configuration) {
		String buildFileString = "";
		try {
			buildFileString = configuration.getAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, buildFileString);
		} catch (CoreException e) {
			EclihxLogger.logError(e);
		}
		buildFileNameText.setText(buildFileString);		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
	 */
	@Override
	public boolean isValid(ILaunchConfiguration launchConfig) {
		
		// TODO 7 Make separate validator for configuration
		
		if (!super.isValid(launchConfig)) return false;
		
		try {
			
			// Project name
			String projectName = launchConfig.getAttribute(IHaxeLaunchConfigurationConstants.PROJECT_NAME, "");				
			if (projectName == null || projectName.isEmpty()) {
				setMessage("Choose the haXe project");
				setErrorMessage(null);
				return false;
			}
			
			// Build file check
			if (buildFilesCache != null && buildFilesCache.length == 0)
			{
				setErrorMessage("This version of EclihX only supports building based on hxml-file." +
						        "Please create one for this project before proceeding with launching");
				setMessage(null);
				return false;
			}
			
			String buildFilePath = launchConfig.getAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, "");
			if (buildFilePath.isEmpty()){
				setMessage("Please, select build file for this project.");
				setErrorMessage(null);
				return false;
			}
			
			// compiler check
			String executablePath = launchConfig.getAttribute(IHaxeLaunchConfigurationConstants.HAXE_COMPILER_PATH, "");
			if (executablePath.isEmpty()) {
				setErrorMessage("Please, define haXe compiler first (Preferences->EclihX->Compiler).");
				setMessage(null);
	            return false;
			} 
			
			if (haxeProject == null) {
				setErrorMessage("There is no project with given name.");
				setMessage(null);
				return false;
			}
			
		} catch (CoreException e) {
			return false;
		}	
		
		// Everything is OK
		setErrorMessage(null);
		setMessage(null);
		return true;
	}
	
	/**
	 * Method checks if we entered valid project name
	 * @return <code>true</code> if valid
	 */
	private void UpdateCurrentProject(String projectName) {
		try {		
			if (projectName.isEmpty()) {
				haxeProject = null;
			} else {
				haxeProject = EclihxCore.getDefault().getHaxeWorkspace().getHaxeProject(projectName);
			}
			
			if (haxeProject != null) {
				if (!haxeProject.isOpen()) {
					haxeProject.open(null);
				}				
	
				buildFilesCache = getAbsolutePaths(haxeProject.getBuildFiles());
			}		
		} catch (CoreException e) {
			haxeProject = null;
			buildFilesCache = null;
		}
	}
}
