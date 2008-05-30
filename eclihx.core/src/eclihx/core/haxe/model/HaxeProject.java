package eclihx.core.haxe.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import eclihx.core.EclihxLogger;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IProjectPathManager;

/**
 * Extend eclipse project with haXe functionality.
 */
public class HaxeProject implements IHaxeProject {

	IProject fProject;
	
	/**
	 * For to store project paths
	 */
	IProjectPathManager fPathManager; 

	public HaxeProject(IProject project) {
		fProject = project;
		
		if (fProject.isOpen()) {
			try {
				addHaxeNature(); // Say eclipse, that it's a haXe project
			} catch (CoreException e) {
				EclihxLogger.logError(e);
			} 			
		}		
	}
	
	/**
	 * Stores project
	 */
	public void store() {
		// TODO 7 Make properties change listener
		if (fPathManager != null) {
			fPathManager.store();
		}
	}

	/**
	 * Returns true if current project has haXe nature
	 * 
	 * @param project
	 * @return true if current project has haXe nature
	 */
	public static boolean isHaxeProject(IProject project) {
		
		try {
			return project.isOpen() && project.hasNature(HAXE_PROJECT_NATURE_ID);
		} catch (CoreException e) {
			// Project doesn't exist or isn't opened.
			// In fact we should never get here, because we check isOpen first. Catch block
			// was added to prevent appearing exception in the method signature.
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeProject#getPathManager()
	 */
	public IProjectPathManager getPathManager() {
		if (fPathManager == null) {
			fPathManager = ProjectPathManager.create(fProject);
			return fPathManager;
		}
		
		return fPathManager;
	}

	/* (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeProject#getProjectBase()
	 */
	public IProject getProjectBase() {
		return fProject;
	}
	
	
	/* (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeProject#isOpen()
	 */
	public boolean isOpen() {
		return fProject.isOpen();
	}
	
	/* (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeProject#open(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void open(IProgressMonitor monitor) throws CoreException { 
		
		fProject.open(monitor);
		addNature(HAXE_PROJECT_NATURE_ID); // Say eclipse, that it's a haXe project

	}
	
	/**
	 * Add haXe nature to the project, if it hasn't been added before
	 * 
	 * @throws CoreException if project hasn't been opened
	 */
	protected void addHaxeNature() throws CoreException {
		addNature(HAXE_PROJECT_NATURE_ID);
	}
	
	/**
	 * Add nature to the project
	 * @param natureID
	 * @throws CoreException if base project hasn't been opened
	 */
	protected void addNature(String natureID) throws CoreException  {
		
		assert(fProject.isOpen()); // The project should be opened
		IProjectDescription description = fProject.getDescription();

		if (!description.hasNature(natureID)) {
			// if project doesn't have current nature yet

			String[] natures = description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = natureID;
			description.setNatureIds(newNatures);

			fProject.setDescription(description, null);

		}
		
	}
	

}
