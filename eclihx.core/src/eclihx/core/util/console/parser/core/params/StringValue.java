package eclihx.core.util.console.parser.core.params;

import eclihx.core.util.console.parser.IStringValue;
import eclihx.core.util.console.parser.IValue;
import eclihx.core.util.console.parser.core.ParseError;

/**
 * Class representing string value. It knows how to deserialize value and 
 * call external user storing callback for saving value.
 */
public class StringValue implements IValue<String> {
	
	/**
	 * Callback for storing found value.
	 */
	final IStringValue stringValueActions;
	
	/**
	 * Default constructor.
	 * @param stringValueActions interface with callback for storing 
	 * 		found value. 
	 */
	public StringValue(IStringValue stringValueActions) {
		this.stringValueActions = stringValueActions;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.util.console.parser.IValue#deserialize(java.lang.String)
	 */
	public String deserialize(String value) throws ParseError {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.util.console.parser.IValue#save(java.lang.Object)
	 */
	public void save(String value) throws ParseError {
		stringValueActions.save(value);
	}
}