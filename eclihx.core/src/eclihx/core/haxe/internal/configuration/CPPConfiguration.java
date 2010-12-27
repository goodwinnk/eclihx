package eclihx.core.haxe.internal.configuration;

import java.util.ArrayList;

import eclihx.core.haxe.internal.HaxePreferencesManager;
import eclihx.core.util.OSUtil;

/**
 * CPP target properties storage.
 */
public class CPPConfiguration extends AbstractConfiguration {
	
	/**
	 * Output directory name for the generated CPP code.
	 */
	private String outputDirectory;
	
	/**
	 * Sets the output directory for the CPP platform.
	 * @param outputDirectory Path for CPP output relative to the build folder. Shouldn't be null.
	 */
	public void setOutputDirectory(String outputDirectory) {
		if (outputDirectory == null) {
			throw new IllegalArgumentException("Null value isn't allowed for outputDirectory parameter"); 
		}
		this.outputDirectory = outputDirectory;
	}
	
	/**
	 * Gets the output directory name for CPP platform. 
	 * @return The path of the directory.
	 */
	public String getOutputDirectory() {
		return outputDirectory;
	}

	@Override
	protected ArrayList<String> internalValidate() {
		return new ArrayList<String>();
	}

	@Override
	public String printConfiguration() throws InvalidConfigurationException {
		return HaxeConfiguration.generateParameter(
				HaxePreferencesManager.PARAM_PREFIX_CPP_DIRECTORY, 
				OSUtil.quoteCompoundPath(outputDirectory));
	}	
}
