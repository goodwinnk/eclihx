package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.ParseError;

/**
 * Interface for the int console parameter
 */
public interface IIntValue {
	
	
	void save(int value) throws ParseError;
}
