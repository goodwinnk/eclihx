package eclihx.core.util.console.parser.core.params;

import eclihx.core.util.console.parser.IIntValue;
import eclihx.core.util.console.parser.IValue;
import eclihx.core.util.console.parser.core.ParseError;

/**
 * Class representing integer value. It knows how to deserialize value and 
 * call external integer saver for performing saving value.
 */
public class IntValue implements IValue<Integer> {

	/**
	 * Callback for storing found integer value.
	 */
	private final IIntValue intValueActions;
	
	
	/**
	 * Default constructor.
	 * @param intValueActions interface with callback for storing found value. 
	 */
	public IntValue(IIntValue intValueActions) {
		this.intValueActions = intValueActions;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.util.console.parser.IValue#deserialize(java.lang.String)
	 */
	@Override
	public Integer deserialize(String value) throws ParseError {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new ParseError(
					String.format("Can't convert '%1$s' to integer", value));
		}
	}
	

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.util.console.parser.IValue#save(java.lang.Object)
	 */
	@Override
	public void save(Integer value) throws ParseError {
		intValueActions.save(value);
	}	
}