package eclihx.core.haxe.internal.configuration;

import java.util.ArrayList;

import eclihx.core.haxe.internal.HaxePreferencesManager;
import eclihx.core.util.OSUtil;

/**
 * Class stores and manipulates with the settings of 
 * ActionScript target platform. 
 */
public class ASConfiguration extends AbstractConfiguration {
	
	/**
	 * Action script output directory.
	 */
	private String outputDirectory;

	
	/**
	 * Get the output directory for the ActionScript configuration.
	 * @return path of the output directory.
	 */
	public String getDirectory() {
		return outputDirectory;
	}

	
	/**
	 * Set the output ActionScript directory.
	 * @param directory of the output ActionScript source files.
	 */
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
	
	/* (non-Javadoc)
	 * @see eclihx.core.haxe.internal.configuration.AbstractConfiguration#internalValidate()
	 */
	@Override
	protected ArrayList<String> internalValidate() {
		return new ArrayList<String>();
	}

	/* (non-Javadoc)
	 * @see eclihx.core.haxe.internal.configuration.AbstractConfiguration#printConfiguration()
	 */
	@Override
	public String printConfiguration() throws InvalidConfigurationException {
		
		String output = "";
		
		output += HaxeConfiguration.GenerateParameter(
			HaxePreferencesManager.PARAM_PREFIX_ACTION_SCRIPT3_DIRECTORY, 
			OSUtil.quoteCompoundPath(outputDirectory));
		
		return output;
	}
}
