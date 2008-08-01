package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.ParseError;

/**
 * Interface for the String console parameter 
 */
public interface IStringValue {
	void save(String value) throws ParseError;
}
