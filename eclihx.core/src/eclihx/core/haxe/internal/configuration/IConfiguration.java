package eclihx.core.haxe.internal.configuration;

/**
 * Common interface for configurations. 
 */
public interface IConfiguration {	
	
	/**
	 * Validates the configuration.
	 * @return <code>true</code> if configuration is valid.
	 */
	boolean validate();
	
	/**
	 * Validates the configuration and throws an exception if configuration
	 * invalid.
	 *
	 * @throws InvalidConfigurationException 
	 * 			exception with the errors descriptions.
	 */
	void validateException() throws InvalidConfigurationException;
	
	/**
	 * Prints the configuration.
	 * @return String representation of the configuration.
	 * 
	 * @throws InvalidConfigurationException 
	 * 		exception with the errors descriptions if configuration is invalid
	 */
	String printConfiguration() throws InvalidConfigurationException;
	
}
