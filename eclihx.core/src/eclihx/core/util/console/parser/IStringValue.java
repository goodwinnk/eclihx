package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.ParseError;

/**
 * Interface for the String console parameter 
 */
public interface IStringValue {
	
	/**
	 * An action which should be done for saving parsed string value.
	 * 
	 * @param value row string value.
	 * @throws ParseError Error of parsing.
	 */
	void save(String value) throws ParseError;
}
