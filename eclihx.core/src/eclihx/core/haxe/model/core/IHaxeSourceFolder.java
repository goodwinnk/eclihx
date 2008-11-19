package eclihx.core.haxe.model.core;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A wrapper for the haXe source folder.
 */
public interface IHaxeSourceFolder extends IHaxeElement {
	
	/**
	 * Get the haXe project this folder belongs to.
	 * @return the haXe project.
	 */
	IHaxeProject getHaxeProject();
	
	/**
	 * Return the base object
	 * @return the base IFolder object.
	 */
	IFolder getBaseFolder();
	
	/**
	 * Method checks if this source folder already has the package.
	 * @param packageName the package name.
	 * @return <code>true</code> if source folder contains the specified
	 *         package.
	 */
	boolean hasPackage(String packageName);
	
	/**
	 * Creates new package with the given name.
	 * @param packageName the name of the package.
	 * @param monitor monitor for the operation. <code>null</code> 
	 *        value is allowed.
	 * 
	 * @throws CoreException if there are some errors during folders creation.
	 */
	void createPackage(String packageName, IProgressMonitor monitor) 
			throws CoreException;
	
	/**
	 * Get the array of the all haXe packages in the folder.
	 * @return the array with the packages.
	 */
	IHaxePackage[] getPackages();
}
