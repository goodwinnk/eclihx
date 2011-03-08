package eclihx.core.util.console.parser.core.params;

import eclihx.core.util.console.parser.IDoubleValue;
import eclihx.core.util.console.parser.IValue;
import eclihx.core.util.console.parser.core.ParseError;

/**
 * Class representing double value. It knows how to deserialize value and 
 * call external double saver for performing saving value.
 */
public class DoubleValue implements IValue<Double> {
	/**
	 * Callback for storing found value.
	 */
	private final IDoubleValue doubleValueAction;
	
	
	/**
	 * Default constructor.
	 * @param doubleValueAction interface with callback for storing found value. 
	 */
	public DoubleValue(IDoubleValue doubleValueAction) {
		this.doubleValueAction = doubleValueAction;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.util.console.parser.IValue#deserialize(java.lang.String)
	 */
	@Override
	public Double deserialize(String value) throws ParseError {
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			throw new ParseError(
					String.format("Can't convert '%1$s' to double", value));
		}
	}
	

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.util.console.parser.IValue#save(java.lang.Object)
	 */
	@Override
	public void save(Double value) throws ParseError {
		doubleValueAction.save(value);
	}	
}
