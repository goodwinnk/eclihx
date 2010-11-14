package eclihx.core.haxe.internal.configuration;

import java.util.ArrayList;

import eclihx.core.haxe.internal.HaxePreferencesManager;
import eclihx.core.util.OSUtil;

/**
 * Stores Neko platform options
 */
public class NekoConfiguration extends AbstractConfiguration {
	
	/**
	 * Stores the output file name for the Neko platform.
	 */
	private String outputFile;
	
	/**
	 * Flag for keeping neko sources.
	 */
	private boolean keepNekoSource;
	
	/**
	 * Sets the output for the Neko platform.
	 * @param outputFile
	 * 		Name of the file.
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	
	/**
	 * Gets the file name of Neko platform
	 * @return The full name of the file.
	 */
	public String getOutputFile() {
		return outputFile;
	}
	
	/**
	 * Checks if generated neko sources should be kept.
	 * @return status of "keeping neko sources" mode.
	 */
	public boolean isKeepNekoSource() {
		return keepNekoSource;
	}

	/**
	 * Enables "keeping neko sources" mode.
	 */
	public void enableKeepNekoSource() {
		this.keepNekoSource = true;
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
		
		output += HaxeConfiguration.generateParameter(
			HaxePreferencesManager.PARAM_PREFIX_NEKO_OUTPUT, 
			OSUtil.quoteCompoundPath(outputFile));
		
		output += HaxeConfiguration.generateFlagParameter(
			HaxePreferencesManager.PARAM_PREFIX_NEKO_SOURCE_FLAG, 
			keepNekoSource);
		 
		return output;
	}
}
