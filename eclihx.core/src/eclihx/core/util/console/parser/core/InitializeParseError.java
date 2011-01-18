package eclihx.core.util.console.parser.core;

/**
 * Error in parser initialization. 
 */
public class InitializeParseError extends ParseError {
	
	private static final long serialVersionUID = 4344007793214996719L;

	/**
	 * Default constructor with the description of the initialize problem.
	 * 
	 * @param message Description of the initialize problem.
	 */
	public InitializeParseError(String message) {
		super(message);
	}	
}
