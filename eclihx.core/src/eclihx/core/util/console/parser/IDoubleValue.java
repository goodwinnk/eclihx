package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.ParseError;

/**
 * Interface for parsing double console parameters. 
 */
public interface IDoubleValue {
	/**
	 * Saves double value found in configuration.
	 * 
	 * @param value the found value.
	 * @throws ParseError if there's error in parsing value to double.
	 */
	void save(double value) throws ParseError;
}
