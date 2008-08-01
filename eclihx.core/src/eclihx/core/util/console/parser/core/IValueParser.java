package eclihx.core.util.console.parser.core;

/**
 * Defines how to parse value string
 */
public interface IValueParser {

	/**
	 * Parameter parsing method 
	 * @param str 
	 */
	void parse(String str) throws ParseError;
}
