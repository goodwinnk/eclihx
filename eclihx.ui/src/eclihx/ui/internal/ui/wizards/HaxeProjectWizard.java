package eclihx.ui.internal.ui.wizards;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.MessageFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import eclihx.core.haxe.model.HaxeProject;
import eclihx.ui.wizards.NewHaxeProjectWizardFirstPage;


public class HaxeProjectWizard extends AbstractProjectRelativeWizard implements INewWizard, IExecutableExtension{
	// TODO 3 move string to constants 
	
	@Override
	protected void doCancel(IProgressMonitor monitor) {
		// TODO 6 Test with unreadable folders 
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		fConfigElement = config;
	}
	
	private IConfigurationElement fConfigElement;
	
	private NewHaxeProjectWizardFirstPage firstPage; 
	
	@Override
	public void addPages() {
		firstPage = new NewHaxeProjectWizardFirstPage();
		
		super.addPage(firstPage);
	}
	
	@Override
	 public boolean performFinish() {
		if (runFinishOperation()) {
			// Switch to haXe perspective
			BasicNewProjectResourceWizard.updatePerspective(fConfigElement);
		
			return true;
		}
		return false;
    }
	
	@Override
	protected void doFinish(IProgressMonitor monitor) {
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		project = firstPage.getProjectHandle();
		IProjectDescription description = workspace.newProjectDescription(project.getName());
		IPath path = Platform.getLocation();
		IPath projectPath = firstPage.getLocationPath();
		
		// Creation task
		monitor.beginTask(MessageFormat.format("Creating...", new Object[] { project.getName().toString() }), 4);
		
		// If location is out the workspace
		if (!path.equals(projectPath)) {
			path = projectPath;
			description.setLocation(path);
		}
		
		try {
			// Create the project if it doesn't exists
			if (!project.exists()) {
				project.create(description, new SubProgressMonitor(monitor, 1));
			}
			
			// Open the project
			if (!project.isOpen()) {
				project.open(new SubProgressMonitor(monitor, 1));
				
				// TODO 4 not clear enough
				new HaxeProject(project); // convert ordinal project to haXe project;
			}
			
			
			
			// Build file
			IFile file = project.getFile("/" + firstPage.getProjectBuildFileName());
			InputStream stream = new ByteArrayInputStream(("# " + project.getName() + " build file").getBytes());
			file.create(stream, true, monitor);
			
			// Src folder
			IFolder srcFolder = project.getFolder(firstPage.getSourceFolder());
			srcFolder.create(false, true, monitor);
			
			// Bin folder
			IFolder binFolder = project.getFolder(firstPage.getBinaryFolder());
			binFolder.create(false, true, monitor);
			
		} catch (CoreException e) {
		} 
	}

	public HaxeProjectWizard() {
		setWindowTitle("New haXe project");
	}
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		//fWorkbench = workbench;
		//fSelection = selection;
		
		//setDefaultPageImageDescriptor(JavaPluginImages.DESC_WIZBAN_NEWJPRJ);
		//setDialogSettings(JavaPlugin.getDefault().getDialogSettings());
		//setWindowTitle("Create haXe project");
	}
}
