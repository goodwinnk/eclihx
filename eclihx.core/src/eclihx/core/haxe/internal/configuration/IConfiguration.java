package eclihx.core.haxe.internal.configuration;

import java.util.ArrayList;

/**
 * Common interface for configurations. 
 */
public interface IConfiguration {	
	
	/**
	 * Error message is configuration is empty.
	 */
	public static final String EMPTY_CONFIGURATION_ERROR = "Configuration is empty";
	
	/**
	 * Validates the configuration.
	 * @return <code>true</code> if configuration is valid.
	 */
	boolean isValid();
	
	/**
	 * Validates the configuration and gets the errors.
	 * Configuration is valid if errors list is empty
	 * @return List of errors in configurations
	 */
	ArrayList<String> validate();
	
	/**
	 * Prints the configuration.
	 * @return String representation of the configuration.
	 * 
	 * @throws InvalidConfigurationException 
	 * 		exception with the errors descriptions if configuration is invalid
	 */
	String printConfiguration() throws InvalidConfigurationException;
	
}
