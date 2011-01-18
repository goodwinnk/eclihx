package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.ParseError;

/**
 * An interface for value converted. 
 *
 * @param <T> Type of the parsed parameter.
 */
public interface IValue<T> {
	
	/**
	 * A method for saving parameter to some place.
	 * @param value parsed value.
	 * @throws ParseError This is a error if saving failed.
	 */
	void save(T value) throws ParseError;
	
	/**
	 * A way for parsing raw value to typed value.
	 * @param str raw string value.
	 * @return parsed value.
	 * @throws ParseError
	 */
	T deserialize(String str) throws ParseError;
}
