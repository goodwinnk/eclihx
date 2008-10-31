package eclihx.core.haxe.model.core;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Interface for the haXe package element.
 */
public interface IHaxePackage extends IHaxeElement {

	/**
	 * The name for the default package.
	 */
	String DEFAULT_PACKAGE_NAME = "(Default Package)";

	/**
	 * Return the base object.
	 * 
	 * @return the base IFolder object.
	 */
	IFolder getBase();

	/**
	 * Get source folder.
	 * 
	 * @return the haXe source folder this package is situated in.
	 */
	IHaxeSourceFolder getSourceFolder();
	
	/**
	 * Returns all children packages at all depth.
	 * 
	 * @return an array of children packages.
	 */
	IHaxePackage[] getChildrenPackages();

	/**
	 * Checks if file with the given filename exists in the package.
	 * 
	 * @param haxeFileName the name of the haXe file.
	 * @return <code>true</code> if file exists in the folder.
	 *         <code>false</code> in both cases file is not a haXe file or it
	 *         doesn't exist in the package.
	 */
	boolean hasHaxeFile(String haxeFileName);

	/**
	 * Create a haXe file in the package.
	 * 
	 * @param haxeFileName the name of the file to create.
	 * @param monitor the operation monitor. <code>null</code> value is allowed.
	 * 
	 * @throws CoreException if there are some errors during file creation.
	 */
	public void createHaxeFile(String haxeFileName, IProgressMonitor monitor)
			throws CoreException;

}
