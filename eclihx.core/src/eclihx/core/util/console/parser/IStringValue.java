package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.ParseError;

public interface IStringValue {
	void save(String value) throws ParseError;
}
