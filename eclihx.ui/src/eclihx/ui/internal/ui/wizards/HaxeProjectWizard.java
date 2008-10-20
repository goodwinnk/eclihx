package eclihx.ui.internal.ui.wizards;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.ui.internal.ui.EclihxUIPlugin;
import eclihx.ui.wizards.NewHaxeProjectWizardFirstPage;

/**
 * Wizard for the haXe project creation. 
 */
public class HaxeProjectWizard extends AbstractMonitorWizard 
		implements INewWizard, IExecutableExtension {

	/**
	 * The wizard page with the new project data definition fields.
	 */
	private NewHaxeProjectWizardFirstPage firstPage; 
	
	/**
	 * Store configuration element.
	 */
	private IConfigurationElement fConfigElement;
	
	/**
	 * Default constructor.
	 */
	public HaxeProjectWizard() {
		super();
		
		setWindowTitle("New haXe project");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setInitializationData(
			IConfigurationElement config, 
			String propertyName, 
			Object data) throws CoreException {
		
		fConfigElement = config;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		firstPage = new NewHaxeProjectWizardFirstPage();
		
		super.addPage(firstPage);
	}
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.ui.internal.ui.wizards.AbstractMonitorWizard#doCancel(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doCancel(IProgressMonitor monitor) {
		// TODO 6 Test with unreadable folders 
	}
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.ui.internal.ui.wizards.AbstractMonitorWizard#doFinish(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doFinish(IProgressMonitor monitor) {
		
		monitor.beginTask("haXe Project creation", 4);
		
		try {
			
			monitor.setTaskName("Creation of project: " + 
					firstPage.getProjectName());
			
			IHaxeProject haxeProject = 
					EclihxCore.getDefault().getHaxeWorkspace().
							createHaxeProject(
									firstPage.getProjectName(), monitor);
			
			monitor.worked(1);
			
			monitor.setTaskName("Creation of build file: " + 
					firstPage.getBuildFileName());
			
			haxeProject.createBuildFile(firstPage.getBuildFileName(), monitor);
			
			monitor.worked(1);
			
			monitor.setTaskName("Creation of output folder: " + 
					firstPage.getOutputFolderName());
			
			haxeProject.createOutputFolder(
					firstPage.getOutputFolderName(), monitor);
			
			monitor.worked(1);
			
			monitor.setTaskName("Creation of source folder: " + 
					firstPage.getSourceFolderName());
			
			haxeProject.createSourceFolder(
					firstPage.getSourceFolderName(), monitor);
			
			monitor.worked(1);
			
			// Switch to haXe perspective
			BasicNewProjectResourceWizard.updatePerspective(fConfigElement);

			
		} catch (CoreException e) {
			EclihxUIPlugin.getLogHelper().logError(e);
			showErrorBox(e.getMessage());
		}		
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.ui.internal.ui.wizards.AbstractProjectRelativeWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// We don't need selection for this wizard.
	}
}
