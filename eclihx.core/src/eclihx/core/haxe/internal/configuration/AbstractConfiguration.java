package eclihx.core.haxe.internal.configuration;

import java.util.ArrayList;

/**
 * Class contains common implementation for the configurations. 
 */
public abstract class AbstractConfiguration implements IConfiguration {

	/**
	 * Prints the configuration.
	 * 
	 * @throws InvalidConfigurationException exception with the errors 
	 * 		   descriptions if configuration is invalid.
	 */
	abstract public String printConfiguration() 
		throws InvalidConfigurationException;

	/**
	 * Performs validation of the configuration and returns description of 
	 * the configuration errors.
	 * 
	 * @return Configuration errors.
	 */
	abstract protected ArrayList<String> internalValidate();
	
	/**
	 * Validates the configuration.
	 * @return <code>true</code> if configuration is valid.
	 */
	@Override
	public final boolean validate() {
		// True if there are no error messages
		return (internalValidate().isEmpty());
	}

	/**
	 * Validates the configuration and throws an exception if configuration
	 * invalid.
	 *
	 * @throws InvalidConfigurationException exception with 
	 * 		   the errors descriptions.
	 */
	@Override
	public final void validateException() throws InvalidConfigurationException {
		ArrayList<String> errors = internalValidate();
		
		// Throw an exception if there are errors
		if (!errors.isEmpty()) {
			throw new InvalidConfigurationException(
				errors.toArray(new String[0])
			);
		}		
	}

}
