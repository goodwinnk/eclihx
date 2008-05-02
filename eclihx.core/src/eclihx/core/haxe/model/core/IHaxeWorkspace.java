package eclihx.core.haxe.model.core;


public interface IHaxeWorkspace {

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

}