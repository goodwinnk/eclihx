package eclihx.core.util.console.parser.core.params;

import eclihx.core.util.console.parser.IStringValue;
import eclihx.core.util.console.parser.IValue;
import eclihx.core.util.console.parser.core.ParseError;

public class StringValue implements IValue<String> {
	
	final IStringValue stringValueActions;
	
	public StringValue(IStringValue stringValueActions) {
		this.stringValueActions = stringValueActions;
	}

	public String deserialize(String value) throws ParseError {
		return value;
	}

	public void save(String value) throws ParseError {
		stringValueActions.save(value);
	}
}