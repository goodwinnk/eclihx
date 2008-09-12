package eclihx.core.haxe.internal.parser;

import eclihx.core.util.console.parser.IParamExistense;
import eclihx.core.util.console.parser.core.ParseError;

public class BooleanContainer implements IParamExistense {

	private boolean value;
	private final String prefix;

	public BooleanContainer(String prefix) {
		this.prefix = prefix;
	}

	public String print() {
		return value ? prefix : "";
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean newValue) {
		value = newValue;
	}

	@Override
	public void save(boolean exist) throws ParseError {
		value = exist;
	}
}
