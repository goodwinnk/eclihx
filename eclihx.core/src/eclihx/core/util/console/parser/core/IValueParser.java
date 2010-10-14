package eclihx.core.util.console.parser.core;

/**
 * Defines how to parse value string
 */
public interface IValueParser {

	/**
	 * Parameter parsing method.
	 *  
	 * @param str raw string value to parse. 
	 * 
	 * @throws ParseError Error in parsing. 
	 */
	void parse(String str) throws ParseError;
}
