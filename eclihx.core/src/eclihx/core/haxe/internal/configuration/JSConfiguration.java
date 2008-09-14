package eclihx.core.haxe.internal.configuration;

import java.util.ArrayList;

import eclihx.core.haxe.internal.HaxePreferencesManager;
import eclihx.core.util.OSUtil;

/**
 * Stores JavaScript platform options
 */
public class JSConfiguration extends AbstractConfiguration {
	
	/**
	 * Stores the output file name for the JavaScript target platform.
	 */
	private String outputFile;
	
	/**
	 * Sets the output for the JS platform.
	 * @param outputFile
	 * 		Name of the file.
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	
	/**
	 * Gets the file name of JS platform
	 * @return The full name of the file.
	 */
	public String getOutputFile() {
		return outputFile;
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
		return HaxeConfiguration.GenerateParameter(
			HaxePreferencesManager.PARAM_PREFIX_JAVA_SCRIPT_OUTPUT, 
			OSUtil.quoteCompoundPath(outputFile));
	}	
}
