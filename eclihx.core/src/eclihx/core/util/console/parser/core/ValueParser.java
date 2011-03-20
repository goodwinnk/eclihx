package eclihx.core.util.console.parser.core;

import eclihx.core.util.console.parser.IValue;

/**
 * Implements default behavior for parsing parameters
 * Uses actions defined in <code>IValue</code> interface
 * 
 * @param <T> Type of the parsed parameter.
 */
public final class ValueParser<T> implements IValueParser {
	
	private final IValue<T> valueActions;
	
	/**
	 * Default constructor.
	 * 
	 * @param valueAction An action should be done on parameter saving. Null is NOT valid.
	 */
	public ValueParser(IValue<T> valueAction) {
		this.valueActions = valueAction;
	}
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.util.console.parser.core.IValueParser#parse(java.lang.String)
	 */
	public void parse(String str) throws ParseError {
		valueActions.save(valueActions.deserialize(str));
	}
}
