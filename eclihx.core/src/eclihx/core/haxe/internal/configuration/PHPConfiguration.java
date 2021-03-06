package eclihx.core.haxe.internal.configuration;

import java.util.ArrayList;

import eclihx.core.haxe.internal.HaxePreferencesManager;
import eclihx.core.util.OSUtil;

/**
 * Stores specific PHP target platform options.
 */
public class PHPConfiguration extends AbstractConfiguration {
	
	/**
	 * Output directory name for the generated PHP code.
	 */
	private String outputDirectory;
	
	/**
	 * Front file of the PHP target.
	 */
	private String frontFile;
	
	/**
	 * PHP library folder path
	 */
	private String libFolderPath;
	
	/**
	 * Sets the output directory for the PHP platform.
	 * @param outputDirectory
	 * 		Name of the directory.
	 */
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
	
	/**
	 * Gets the output directory name for PHP platform. 
	 * @return The path of the directory.
	 */
	public String getOutputDirectory() {
		return outputDirectory;
	}

	/**
	 * Sets the front file for the PHP platform.
	 * @param frontFile
	 * 		Name of the frontFile.
	 */
	public void setFrontFile(String frontFile) {
		this.frontFile = frontFile;
	}
	
	/**
	 * Gets the name of the front file. 
	 * @return The name of the front file.
	 */
	public String getFrontFile() {
		return frontFile;
	}
	
	/**
	 * Sets the PHP library folder path.
	 * 
	 * @param phpLibFolderPath library folder path.
	 */
	public void setLibFolderPath(String phpLibFolderPath) {
		this.libFolderPath = phpLibFolderPath;
	}

	/**
	 * Get PHP library folder path.
	 * 
	 * @return PHP library folder path.
	 */
	public String getLibFolderPath() {
		return libFolderPath;
	}	

	
	/* (non-Javadoc)
	 * @see eclihx.core.haxe.internal.configuration.AbstractConfiguration#internalValidate()
	 */
	@Override
	protected ArrayList<String> internalValidate() {
		// No errors because validating of the parameter should be done in
		// setter.
		return new ArrayList<String>();
	}

	/* (non-Javadoc)
	 * @see eclihx.core.haxe.internal.configuration.AbstractConfiguration#printConfiguration()
	 */
	@Override
	public String printConfiguration() throws InvalidConfigurationException {
		
		String output = "";
		
		output += HaxeConfiguration.generateParameter(
			HaxePreferencesManager.PARAM_PREFIX_PHP_DIRECTORY, 
			OSUtil.quoteCompoundPath(outputDirectory));
		
		if (frontFile != null) {
			output += HaxeConfiguration.generateParameter(
					HaxePreferencesManager.PARAM_PREFIX_PHP_FRONT_FILE, 
					OSUtil.quoteCompoundPath(frontFile));
		}
		
		if (libFolderPath != null) {
			output += HaxeConfiguration.generateParameter(
					HaxePreferencesManager.PARAM_PREFIX_PHP_LIB_FOLDER, 
					OSUtil.quoteCompoundPath(libFolderPath));
		}
		
		
		return output;
	}
}
