package eclihx.ui.launch.tabs;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.core.runtime.CoreException;

import eclihx.launching.IHaxeLaunchConfigurationConstants;
import eclihx.core.EclihxLogger;

public class HaxeMainTab extends AbstractLaunchConfigurationTab {

	private Text projectNameText;
	private Text buildFileNameText;
	private Text workingDirectoryText;
	private Text outputDirectoryText;
	
	private ModifyListener fModifyListener = 
		new ModifyListener() { public void modifyText(ModifyEvent me) { updateLaunchConfigurationDialog(); } };
	
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return super.getImage();
	}
	
	public void createControl(Composite parent) {
		Composite top = new Composite(parent, SWT.LEFT);

		top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		top.setLayout(new GridLayout());
		
		GridData horizontantalGrid = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL);
		GridLayout layout = new GridLayout(1, false);
		
		// Add project group
		Group projectGroup = new Group(top, SWT.NONE);
		projectGroup.setText("Project");
		projectGroup.setLayout(layout);
		projectGroup.setLayoutData(horizontantalGrid);
		
		projectNameText = new Text(projectGroup,  SWT.BORDER);
		projectNameText.setLayoutData(horizontantalGrid);
		projectNameText.setText("");
		projectNameText.addModifyListener(fModifyListener);
		
		// Add build file group
		Group buildFileGroup = new Group(top, SWT.NONE);
		buildFileGroup.setText("Build file");
		buildFileGroup.setLayout(layout);
		buildFileGroup.setLayoutData(horizontantalGrid);
		
		buildFileNameText = new Text(buildFileGroup,  SWT.BORDER);
		buildFileNameText.setLayoutData(horizontantalGrid);
		buildFileNameText.setText("");
		buildFileNameText.addModifyListener(fModifyListener);
		
		// Add working directory group
		Group workingDirectoryGroup = new Group(top, SWT.NONE);
		workingDirectoryGroup.setText("Working Directory");
		workingDirectoryGroup.setLayout(layout);
		workingDirectoryGroup.setLayoutData(horizontantalGrid);
		
		workingDirectoryText = new Text(workingDirectoryGroup,  SWT.BORDER);
		workingDirectoryText.setLayoutData(horizontantalGrid);
		workingDirectoryText.setText("");
		workingDirectoryText.addModifyListener(fModifyListener);
		
		// Output working directory group
		Group outputDirectoryGroup = new Group(top, SWT.NONE);
		outputDirectoryGroup.setText("Working Directory");
		outputDirectoryGroup.setLayout(layout);
		outputDirectoryGroup.setLayoutData(horizontantalGrid);
		
		outputDirectoryText = new Text(outputDirectoryGroup,  SWT.BORDER);
		outputDirectoryText.setLayoutData(horizontantalGrid);
		outputDirectoryText.setText("");
		outputDirectoryText.addModifyListener(fModifyListener);
		
		setControl(top);
	}

	public String getName() {
		// TODO: move to constants
		return "Main";
	}

	public void initializeFrom(ILaunchConfiguration configuration) {
		initializeProjectName(configuration);
		initializeBuildFile(configuration);
		initializeWorkingDirectory(configuration);
		initializeOutputDirectory(configuration);
	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		// TODO Auto-generated method stub
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.PROJECT_NAME, projectNameText.getText());
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, buildFileNameText.getText());
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, workingDirectoryText.getText());
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.OUTPUT_DIRECTORY, outputDirectoryText.getText());
		
	}

	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.PROJECT_NAME, "");
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, "");
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, "");
		configuration.setAttribute(IHaxeLaunchConfigurationConstants.OUTPUT_DIRECTORY, "");
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
		workingDirectoryText.setText(workingDirectoryString);		
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
}
