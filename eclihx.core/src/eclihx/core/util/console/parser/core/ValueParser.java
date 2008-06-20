package eclihx.core.util.console.parser.core;

import eclihx.core.util.console.parser.IValue;

/**
 * Implements default behaviour for parsing parameters
 * Uses actions defined in <code>IValue</code> interface
 */
public final class ValueParser<T> implements IValueParser {
	
	private final IValue<T> valueActions;
	
	public ValueParser(IValue<T> valueActins) {
		this.valueActions = valueActins;
	}
	
	public void parse(String str) throws ParseError {
		valueActions.save(valueActions.deserialize(str));
	}
}
