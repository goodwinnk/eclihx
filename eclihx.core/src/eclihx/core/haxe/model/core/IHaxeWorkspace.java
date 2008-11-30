package eclihx.core.haxe.model.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Interface for the workspace wrapper aimed to manage haXe resources.
 */
public interface IHaxeWorkspace extends IHaxeElement {

	/**
	 * Closes the workspace and saves haXe projects in workspace 
	 */
	void close();
	
	/**
	 * Gets the haXe element wrapper for resource.
	 * @param resource the resource which is suspected to be haXe element.
	 * @return IHaxeElement if resource is a haXe resource and 
	 * <code>null</code> in other case.
	 */
	IHaxeElement getHaxeElement(IResource resource);
	
	/**
	 * Get the array with the all haXe project names.
	 * @return an array with the haXe project names.
	 */
	String[] getHaxeProjectsNames();

	/**
	 * Gets haXe project by name.
	 * @param projectName the name of the project.
	 * 
	 * @return the reference to haXe project or null if 
	 *         such project doesn't exist.
	 */
	IHaxeProject getHaxeProject(String projectName);
	
	/**
	 * Gets haXe project by IProject object.
	 * @param project the project object.
	 * 
	 * @return the reference to haXe project or null if 
	 *         such project doesn't exist.
	 */
	IHaxeProject getHaxeProject(IProject project);
	
	/**
	 * Creates the haXe project.
	 * @param projectName the name of the project to create.
	 * @param monitor the operation monitor. <code>null</code> 
	 *        value is allowed.
	 *        
	 * @return the haXe project.
	 *        
	 * @throws CoreException if there are some errors during project creation.
	 */
	IHaxeProject createHaxeProject(String projectName, IProgressMonitor monitor) 
			throws CoreException;
	
	/**
	 * Get all haXe projects in the workspace.
	 * 
	 * @return An array of haXe projects.
	 */
	IHaxeProject[] getHaxeProjects();

	

}