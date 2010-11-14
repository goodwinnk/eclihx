package eclihx.core.util;

/**
 * Special exception for the cases which aggregates several reasons.
 */
public abstract class MultiMessageException extends Exception {
	
	private static final long serialVersionUID = 8790699727114281192L;
	
	/**
	 * Error messages with explanations while configuration was considered to
	 * be invalid.
	 */
	private final String[] errorMessages;
	
	/**
	 * Exception with one error message.
	 * @param errorMessage the reason of the exception. 
	 */
	public MultiMessageException(String errorMessage) {
		this(new String[] {errorMessage} );
	}	
	
	/**
	 * Multi-error constructor. 
	 * @param errorMessages the reasons of the exception.
	 */
	public MultiMessageException(String[] errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	/**
	 * Gets the compound reason of the exception by enumeration of 
	 * the all errors in one string.
	 * @return String with the all exception reasons.
	 */
	@Override
	public final String getMessage() {
		
		if (errorMessages.length == 1) {
			// Don't enable numbering for the case of only one reason.
			return errorMessages[0];
		}
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < errorMessages.length; ++i) {
			builder.append(String.format("%d. %s\n", i, errorMessages[i]));
		}
		
		return builder.toString();
	}
	
	/**
	 * String descriptions of configuration errors.
	 * @return descriptions of errors.
	 */
	public final String[] getErrorMessages() {
		return errorMessages;
	}
}
