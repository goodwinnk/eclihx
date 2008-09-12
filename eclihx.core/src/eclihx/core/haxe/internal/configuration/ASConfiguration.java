package eclihx.core.haxe.internal.configuration;

/**
 * Class stores and manipulates with the settings of 
 * ActionScript target platform. 
 */
public class ASConfiguration {
	
	/**
	 * Action script output directory.
	 */
	private String directory;

	
	/**
	 * Get the output directory for the ActionScript configuration.
	 * @return path of the output directory.
	 */
	public String getDirectory() {
		return directory;
	}

	
	/**
	 * Set the output ActionScript directory.
	 * @param directory of the output ActionScript source files.
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}	
}
