package eclihx.core.util.console.parser.core.params;

import eclihx.core.util.console.parser.IIntValue;
import eclihx.core.util.console.parser.IValue;
import eclihx.core.util.console.parser.core.ParseError;

public class IntValue implements IValue<Integer> {

	final IIntValue intValueActions;
	
	public IntValue(IIntValue intValueActions) {
		this.intValueActions = intValueActions;
	}
	
	public Integer deserialize(String value) throws ParseError {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new ParseError(String.format("Can't convert '%1$s' to int", value));
		}
	}

	public void save(Integer value) throws ParseError {
		intValueActions.save(value);
	}	
}