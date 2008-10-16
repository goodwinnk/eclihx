package eclihx.core.haxe.model.core;


public interface IHaxeWorkspace extends IHaxeElement {

	/**
	 * Get the array with the all haXe project names.
	 * @return an array with the haXe project names.
	 */
	String[] getHaxeProjectsNames();

	/**
	 * Saves haXe projects in workspace 
	 */
	void close();

	/**
	 * Gets haXe project by name.
	 * @param name
	 */
	IHaxeProject getHaxeProject(String name);
	
	/**
	 * Get all haXe projects in the workspace.
	 * @return An array of haXe projects.
	 */
	IHaxeProject[] getHaxeProjects();

}