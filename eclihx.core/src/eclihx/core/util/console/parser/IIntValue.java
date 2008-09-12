package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.ParseError;

/**
 * Interface for the integer console parameter
 */
public interface IIntValue {
	void save(int value) throws ParseError;
}
