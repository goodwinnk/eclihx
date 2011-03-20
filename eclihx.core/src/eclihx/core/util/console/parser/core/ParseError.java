package eclihx.core.util.console.parser.core;

/**
 * Denotes error in the parsing
 */
public class ParseError extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor with the description of the error in parsing.
	 * 
	 * @param message Description of the error in parsing.
	 */
	public ParseError(String message) {
		super(message);
	}
}
