package eclihx.core.haxe.internal;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

public interface IProjectPathManager {
	
	/**
	 * Store paths
	 */
	public void store();
	
	/**
	 * Returns project output folder
	 * @return project output folder or null if not specified yet
	 */
	IFolder getOutputFolder();
	
	/**
	 * Sets project output location
	 * @param path
	 */
	void setOutputFolder(IFolder folder);
	
	/**
	 * Gets all project source directories
	 * @return array of directories with project sources
	 */
	List<IFolder> getSourceFolders();
	
	/**
	 * Replaces all source folders with new array
	 * @param folders
	 */
	void setSourceFolders(IFolder[] folders);
	
	/**
	 * Add new source folder
	 * @param folder
	 */
	void addSourceFolder(IFolder folder);
	
	/**
	 * Gets all project library directories
	 * @return array of directories with project libs
	 */
	List<IFolder> getLibFolders();
	
	/**
	 * Replaces all lib folders with new array
	 * @param folders
	 */
	void setLibFolders(IFolder[] folders);
	
	/**
	 * Add new lib folder
	 * @param folder
	 */
	void addLibFolder(IFolder folder);
	
	/**
	 * Get build files
	 * @return
	 */
	List<IFile> getBuildFiles();
	
	/**
	 * Override all build files
	 * @param files
	 */
	void setBuildFiles(IFile[] files);
	
	/**
	 * Add build file
	 * @param file
	 */
	void addBuildFile(IFile file);
	
	
}
