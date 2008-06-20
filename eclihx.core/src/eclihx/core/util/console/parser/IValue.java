package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.ParseError;

public interface IValue<T> {
	void save(T value) throws ParseError;
	T deserialize(String str) throws ParseError;
}
