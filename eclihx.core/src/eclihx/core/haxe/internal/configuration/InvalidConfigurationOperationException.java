package eclihx.core.haxe.internal.configuration;

/**
 * This exception is thrown by the configurations to indicate that current
 * state is invalid for the operation calling.
 */
public class InvalidConfigurationOperationException extends Exception {
	
	private static final long serialVersionUID = -8144597611767753361L;

	/**
	 * Default constructor with the exception reason.
	 */
	public InvalidConfigurationOperationException(String message) {
		super(message);
	}
	
}
