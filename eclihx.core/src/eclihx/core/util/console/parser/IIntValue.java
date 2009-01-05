package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.ParseError;

/**
 * Interface for the integer console parameter.
 */
public interface IIntValue {
	
	/**
	 * Saves integer value found in configuration.
	 * 
	 * @param value the found value.
	 * @throws ParseError if there's error in parsing value to integer.
	 */
	void save(int value) throws ParseError;
}
