package eclihx.core.haxe.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class is an wrapper for storing strings with the folders paths.
 */
@XmlRootElement(name="ProjectPaths")
public class ProjectPathsSerializer {
	
	/**
	 * Output folder.
	 */
	@XmlElement(name="OutputFolder")
	public String outputFolder;
	
	/**
	 * An array of source folders.
	 */
	@XmlElement(name="SourceFolder")
	public String[] sourceFolders;
	
	/**
	 * An array with library folders.
	 */
	@XmlElement(name="LibraryFolder")
	public String[] libFolders;
	
	/**
	 * An array with library files.
	 */
	@XmlElement(name="LibraryFile")
	public String[] libFiles;
}
