package eclihx.core.haxe.internal.configuration;

import eclihx.core.util.MultiMessageException;


/**
 * Special exception which is used to denote inconsistent configuration state.  
 */
public class InvalidConfigurationException extends MultiMessageException {
	
	private static final long serialVersionUID = -72326432204925939L;

	/**
	 * Constuctor with one message. 
	 * @param errorMessages the reasons of the exception.
	 */
	public InvalidConfigurationException(String errorMessage) {
		super(errorMessage);
	}	
	
	/**
	 * Multi-error constructor. 
	 * @param errorMessages the reasons of the exception.
	 */
	public InvalidConfigurationException(String[] errorMessages) {
		super(errorMessages);
	}	
}
