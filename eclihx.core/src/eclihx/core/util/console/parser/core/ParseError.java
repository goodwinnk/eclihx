package eclihx.core.util.console.parser.core;

/**
 * Denotes error in the parsing
 */
public class ParseError extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ParseError(String message) {
		super(message);
	}
}
