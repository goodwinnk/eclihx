package eclihx.core.haxe.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ProjectPaths")
class ProjectPathsSerializer {
	
	@XmlElement(name="OutputFolder")
	public String outputFolder;
	
	@XmlElement(name="SourceFolder")
	public String[] sourceFolders;
	
	@XmlElement(name="BuildFile")
	public String[] buildFiles;
	
	@XmlElement(name="LibraryFolder")
	public String[] libFolders;
	
	@XmlElement(name="LibraryFile")
	public String[] libFiles;
}
