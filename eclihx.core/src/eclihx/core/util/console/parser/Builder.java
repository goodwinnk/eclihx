package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.IValueParser;
import eclihx.core.util.console.parser.core.Parameter;
import eclihx.core.util.console.parser.core.ValueParser;
import eclihx.core.util.console.parser.core.params.IntValue;
import eclihx.core.util.console.parser.core.params.StringValue;

/**
 * Contains handy method for creating parameters
 */
public class Builder {
	
	public static Parameter createFlagParam(String prefix, IParamExistense actions) {
		return new Parameter(prefix, actions, null);
	}
	
	public static <T> Parameter createParam(
		String prefix, 
		IParamExistense actions, 
		IValue<T> value
	) {
		return new Parameter(
			prefix, 
			actions, 
			new IValueParser[]{ new ValueParser<T>(value)}
		);
	}
	
	public static <T> Parameter createParam(String prefix , IValue<T> value) {
		return createParam(prefix, null, value);
	}
	
	public static <T> Parameter createEmptyParam(IValue<T> value) {
		return createParam("", null, value);
	}
	
	public static Parameter createStringParam(
		String prefix,
		IParamExistense actions,
		IStringValue value) {
		
		return createParam(prefix, actions, new StringValue(value));
	}
	
	public static Parameter createStringParam(
		String prefix,
		IStringValue value) {
			
		return createStringParam(prefix, null, value);
	}
	
	
	public static Parameter createEmptyStringParam(IStringValue value) {
		return createStringParam("", null, value);
	}
	
	public static Parameter createIntParam(
		String prefix,
		IParamExistense actions,
		IIntValue value) {
			
		return createParam(prefix, actions, new IntValue(value));
	}

	
	public static Parameter createIntParam(String prefix, IIntValue value) {
		return createIntParam(prefix, null, value);
	}

	
	public static <T> Parameter createEmptyIntParam(IIntValue value) {
		return createIntParam("", null, value);
	}
	
}

