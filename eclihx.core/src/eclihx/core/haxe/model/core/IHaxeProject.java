package eclihx.core.haxe.model.core;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;


public interface IHaxeProject {
	
	public final static String HAXE_PROJECT_NATURE_ID = "eclihx.core.haxenature";
	
	/**
	 * Return the base object
	 * @return
	 */
	IProject getProjectBase();
	
	/**
	 * Get path manager. It can be used to find out info about folders, 
	 * like Source, Binary, Library
	 *  
	 * @return
	 */
	IProjectPathManager getPathManager();	
	
	/**
	 * Checks if project is opened. Project is considered to be opened if getProjectBase().isOpen()
	 * @return
	 * @see IProject.isOpen()
	 */
	boolean isOpen();
	
	/**
	 * Opens base project and do some advanced work for the haXe project
	 * @param monitor
	 * @throws CoreException
	 * @see IProject.open()
	 */
	void open(IProgressMonitor monitor) throws CoreException;
}
