package eclihx.core.haxe.internal.configuration;

import java.util.ArrayList;
import java.util.Arrays;

import eclihx.core.haxe.internal.HaxePreferencesManager;
import eclihx.core.util.OSUtil;

/**
 * Stores and validates flash specific options.
 */
public final class FlashConfiguration extends AbstractConfiguration {
	
	/**
	 * Path of the output flash file.
	 */
	private String outputFile;
	
	
	/**
	 * Version of the output flash file.
	 */
	private Integer version;
	
	
	/**
	 * Flash file header.
	 */
	//TODO 3 Support more properties for this option.
	private String header;
	
	
	/**
	 * Names of files with SWF libraries.
	 */
	private final ArrayList<String> swfLibraries = new ArrayList<String>();

	
	/**
	 * Get the output file path. Can be null if it hasn't been set before.
	 * @return output file path.
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * Set output file. This method does no semantic checks of the path
	 * correctness. Use <code>validate</code> method.
	 * 
	 * @param outputFile the outFile to set. Can't be null. 
	 */
	public void setOutputFile(String outputFile) {
		if (outputFile == null) {
			throw new IllegalArgumentException(
				"Null value isn't allowed for outputFile parameter"); 
		}			
		
		this.outputFile = outputFile;		
	}

	/**
	 * The version of the flash format.
	 * @return the version of the flash format.
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version of output flash file. This method does no semantic 
	 * checks of the path correctness - use <code>validate</code> method.  
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Header for the generated swf file.
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * Set the header for swf file.
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		// TODO 3 add checks here
		this.header = header;
	}
	
	/**
	 * Method adds swf library file name to configuration.
	 * 
	 * @param libraryFileName The name of the library to add.
	 */
	public void addLibrary(String libraryFileName) {
		swfLibraries.add(libraryFileName);
	}

	/**
	 * Prints the configuration.
	 */
	@Override
	public String printConfiguration() throws InvalidConfigurationException {
		
		validateException(); // Possible exceptions here
		
		StringBuilder outputBuilder = new StringBuilder();
		
		if (version != null && version == 9) {
			// use special option for storing both version and output file
			outputBuilder.append(HaxeConfiguration.GenerateParameter(
				HaxePreferencesManager.PARAM_PREFIX_SWF9_OUTPUT,
				OSUtil.quoteCompoundPath(outputFile)			
			));
		} else {
			if (version != null) {
				// write version
				outputBuilder.append(HaxeConfiguration.GenerateParameter(
					HaxePreferencesManager.PARAM_PREFIX_SWF_VERSION,
					new Integer(version).toString()
				));
			}
			
			// write output file
			outputBuilder.append(HaxeConfiguration.GenerateParameter(
				HaxePreferencesManager.PARAM_PREFIX_SWF_OUTPUT,
				OSUtil.quoteCompoundPath(outputFile)				
			));			
		}
		
		// It's valid not to include header - so we can omit 
		// it in configuration.		
		if (header != null) {
			// Add header
			outputBuilder.append(HaxeConfiguration.GenerateParameter(
				HaxePreferencesManager.PARAM_PREFIX_SWF_HEADER,
				header				
			));
		}
		
		// SWF libraries
		for (String libFileName : swfLibraries) {
			outputBuilder.append(HaxeConfiguration.GenerateParameter(
				HaxePreferencesManager.PARAM_PREFIX_SWF_LIB, 
				OSUtil.quoteCompoundPath(libFileName)
			));		
		}
		
		return outputBuilder.toString();
	}
	
	/**
	 * Performs validation of the configuration and returns description of 
	 * the configuration errors.	
	 * @return Configuration errors.
	 */
	@Override
	protected ArrayList<String> internalValidate() {
		
		ArrayList<String> errorMessages = new ArrayList<String>();
		
		if (outputFile == null) {
			errorMessages.add("No output file for flash target platform.");
		} else {
			// TODO 5 Add file validation
		}
		
		// Absence of the version is a valid situation for haXe compiler.
		// But if we have changed the default value of the version 
		// we should check it.
		if (version != null) {		
			Arrays.sort(HaxePreferencesManager.SUPPORTED_VERSIONS);
			int index = Arrays.binarySearch(
				HaxePreferencesManager.SUPPORTED_VERSIONS, version);
			
			if (index < 0) {
				// Element wan't found.
				errorMessages.add(
					"Illegal flash version. Valid versions are: " +  
					HaxePreferencesManager.SUPPORTED_VERSIONS.toString()
				); 
			}			
		}
		
		// TODO 4 Add validation for header
				
		return errorMessages;
	}
}
