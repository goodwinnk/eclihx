package eclihx.ui.launch.tabs;

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
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import eclihx.launching.EclihxLauncher;
import eclihx.launching.IHaxeLaunchConfigurationConstants;
import eclihx.launching.LauncherPreferenceInitializer;
import eclihx.core.EclihxCore;
import eclihx.core.EclihxLogger;
import eclihx.core.haxe.internal.IProjectPathManager;
import eclihx.core.haxe.model.HaxeWorkspace;
import eclihx.core.haxe.model.core.IHaxeProject;

public class HaxeMainTab extends AbstractLaunchConfigurationTab {
	
	// TODO 3 move strings constants to configure file

	
	/* Controls */
	private Text projectNameText;
	private Text buildFileNameText;
	private Text sourceDirectoryText;
	private Text outputDirectoryText;
	
	private ModifyListener fModifyListener = 
		new ModifyListener() { public void modifyText(ModifyEvent me) { updateLaunchConfigurationDialog(); } };
	
	/* Base selection listener. It reacts to events ignoring them */	
	class SelectionListenerClass implements SelectionListener { 
		public void widgetDefaultSelected(SelectionEvent e) {/*do nothing*/}
		public void widgetSelected(SelectionEvent e) { /*do nothing*/ }
	}
		
	
	
	/**
	 * Choose a haXe project dialog
	 * @return
	 */
	private String chooseHaxeProject() {
		// TODO 4 make icons for project selection
		
		ILabelProvider labelProvider = new LabelProvider();
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
		
		dialog.setTitle("Select Haxe Project");
		dialog.setMessage("Enter a string to search for a project:");
		try {
			dialog.setElements(new HaxeWorkspace(getWorkspaceRoot()).getHaxeProjectsNames());
		} catch (Exception e) {
			EclihxLogger.logError(e);
		}
		
		String haxeProjectName = projectNameText.getText();
		if (haxeProjectName != null) {
			dialog.setInitialSelections(new Object[] { haxeProjectName });
		}
	
		if (dialog.open() == Window.OK) {
			return (String) dialog.getFirstResult();
		}
		return null;
	}	
		
	/**
	 * Get current workspace
	 * @return
	 */
	private IWorkspaceRoot getWorkspaceRoot() {
		// TODO Auto-generated method stub
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	protected void onProjectButtonSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		String projectName = chooseHaxeProject();
		IHaxeProject haxeProject = EclihxCore.getDefault().getHaxeWorkspace().getHaxeProject(projectName);
		
		if (haxeProject != null) {
			projectNameText.setText(projectName);
			
			IProjectPathManager pathManager = haxeProject.getPathManager();
		
			// Sets build file as a first one in the projects paths
			buildFileNameText.setText(pathManager.getBuildFiles().get(0).getLocation().toString());
			
			// Output folder
			outputDirectoryText.setText(pathManager.getOutputFolder().getLocation().toString());
			
			// Source folder
			sourceDirectoryText.setText(pathManager.getSourceFolders().get(0).getLocation().toString());
			
		} else {
			EclihxLogger.logInfo("The selected project wasn't found.");
		}
		
		
	}

	@Override
	public Image getImage() {
		// TODO 2 Add image for the main page of the launcher configuration
		return super.getImage();
	}
	
	@Override
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
		
		Button projectButton = createPushButton(projectGroup, "Select Project...", null);
		projectButton.addSelectionListener(
			new SelectionListenerClass () {
				public void widgetSelected(SelectionEvent e) {
					onProjectButtonSelected(e);
				}
			});
		
		// Add build file group
		Group buildFileGroup = new Group(top, SWT.NONE);
		buildFileGroup.setText("Build file");
		buildFileGroup.setLayout(layout);
		buildFileGroup.setLayoutData(horizontantalGrid);
		
		buildFileNameText = new Text(buildFileGroup,  SWT.BORDER);
		buildFileNameText.setLayoutData(horizontantalGrid);
		buildFileNameText.setEditable(false);
		buildFileNameText.setText("");
		buildFileNameText.addModifyListener(fModifyListener);
		
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

	public String getName() {
		return "Main";
	}

	public void initializeFrom(ILaunchConfiguration configuration) {
		initializeProjectName(configuration);
		initializeBuildFile(configuration);
		initializeWorkingDirectory(configuration);
		initializeOutputDirectory(configuration);
	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.PROJECT_NAME, projectNameText.getText());
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, buildFileNameText.getText());
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, sourceDirectoryText.getText());
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.OUTPUT_DIRECTORY, outputDirectoryText.getText());
		
		// TODO 1 Make separate place for overriding initializer
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.HAXE_COMPILER_PATH, EclihxLauncher.getDefault().getPluginPreferences().getString(LauncherPreferenceInitializer.ECLIHAXE_HAXE_COMPILER_PATH));
		
	}

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

	@Override
	public boolean isValid(ILaunchConfiguration launchConfig) {
		if (!super.isValid(launchConfig)) return false;
		
		String executablePath;
		try {
			executablePath = launchConfig.getAttribute(IHaxeLaunchConfigurationConstants.HAXE_COMPILER_PATH, "");
			if (executablePath.isEmpty()) {
				setErrorMessage("Please, define haXe compiler first (Preferences->EclihX->Compiler).");
	            setMessage(null);
			}
		} catch (CoreException e) {
			// Do nothing
		}
		
		// Everything is ok
		return true;
	}
}
