package eclihx.core.haxe.internal.parser;

import eclihx.core.util.console.parser.Builder;
import eclihx.core.util.console.parser.IValue;
import eclihx.core.util.console.parser.core.Parameter;

/**
 * Special container for the parsed console value. This class can store the
 * value and print it if necessary.
 */
public class ValueContainer<T> {

	private T value;
	private final String prefix;
	private final IValue<T> valueProcessor;

	public ValueContainer(String prefix, IValue<T> valueProcessor) {
		this.prefix = prefix;
		this.valueProcessor = valueProcessor;
	}

	public Parameter createParameter() {
		return Builder.createParam(prefix, valueProcessor);
	}

	public String print() {
		return prefix + " " + value;
	}

	public T getValue() throws Exception {
		if (value == null) {
			throw new Exception("No value in the container.");
		}

		return value;
	}

	public void setValue(T newValue) {
		if (newValue == null) {
			throw new IllegalArgumentException("Value can't be null");
		}

		value = newValue;
	}
}
