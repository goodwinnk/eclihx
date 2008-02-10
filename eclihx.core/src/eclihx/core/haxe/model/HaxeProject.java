package eclihx.core.haxe.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;

import eclihx.core.EclihxLogger;
import eclihx.core.haxe.internal.IClasspathManager;
import eclihx.core.haxe.model.core.IHaxeProject;

/**
 * Extend project with haxe functionality Use agregation as subclassing isn't
 * available in this case
 * 
 */
public class HaxeProject implements IHaxeProject {

	IProject fProject;

	public HaxeProject(IProject project) {
		fProject = project;
		AddHaxeNature(HAXE_PROJECT_NATURE_ID);
	}

	public void AddHaxeNature(String natureID) {
		try {
			assert(fProject.isOpen()); // The project should be opened
			IProjectDescription description = fProject.getDescription();
			String[] natures = description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = natureID;
			description.setNatureIds(newNatures);
			fProject.setDescription(description, null);
		} catch (CoreException e) {
			EclihxLogger.logError(e);
		}
	}

	/**
	 * Returns true if current project has haXe nature
	 * 
	 * @param project
	 *            IProject
	 * @return boolean
	 */
	public static boolean hasHaxeNature(IProject project) {
		try {
			return project.hasNature(HAXE_PROJECT_NATURE_ID);
		} catch (CoreException e) {
			// if
			// (ExternalJavaProject.EXTERNAL_PROJECT_NAME.equals(project.getName()))
			return false;
			// project does not exist or is not open
		}
	}

	public IClasspathManager getClassPathManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public IProject getProjectBase() {
		return fProject;
	}
}
