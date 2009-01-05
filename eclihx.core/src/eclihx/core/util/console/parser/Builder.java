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
	
	/**
	 * Creation of the flag parameter.
	 * @param prefix prefix which appearance in configuration will mean 
	 * 		existence of the parameter. For example parameter "-debug" will mean
	 * 		that debug mode is enabled. 
	 * @param actions Provides save callback for storing parameter.
	 * @return New flag parameter.
	 */
	public static Parameter createFlagParam(
			String prefix, IParamExistense actions) {
		return new Parameter(prefix, actions, null);
	}
	
	
	/**
	 * Creation value parameter.
	 * @param <T> type of the desired value.
	 * @param prefix the parameter key.
	 * @param actions store action callback which should be done if 
	 * 		parameter exists.
	 * @param value value controller.
	 * @return new value parameter.
	 */
	public static <T> Parameter createParam(
		String prefix, 
		IParamExistense actions, 
		IValue<T> value
	) {
		return new Parameter(
			prefix, 
			actions, 
			new IValueParser[]{ new ValueParser<T>(value) }
		);
	}
	
	
	/**
	 * Creates new parameter for given key.
	 * 
	 * @param <T> type of the desired value.
	 * @param prefix the parameter key.
	 * @param value value controller.
	 * @return new value parameter.
	 */
	public static <T> Parameter createParam(String prefix , IValue<T> value) {
		return createParam(prefix, null, value);
	}
	
	/**
	 * Creates a parameter with empty key. Usually this parameter is used for
	 * reading last parameter in console command. For example in the string
	 * "haxe.exe build.hxml" empty parameter could be used to store "build.hxml"
	 * string.  
	 * 
	 * @param <T> type of the desired value.
	 * @param value value store controller.
	 * @return new empty value parameter.
	 */
	public static <T> Parameter createEmptyParam(IValue<T> value) {
		return createParam("", null, value);
	}
	
	
	/**
	 * Method creates a string parameter.
	 * 
	 * @param prefix key for the parameter for example in the case of 
	 * 		"haxe -swf9 out.swf" the key for the parameter is "-swf9"
	 * @param actions store action callback which works with the fact of
	 * 		parameter existence.
	 * @param value store callback for the value.
	 * @return new string parameter.
	 */
	public static Parameter createStringParam(
		String prefix,
		IParamExistense actions,
		IStringValue value) {
		
		return createParam(prefix, actions, new StringValue(value));
	}
	
	
	/**
	 * Method creates a string parameter.
	 * 
	 * @param prefix key for the parameter for example in the case of 
	 * 		"haxe -swf9 out.swf" the key for the parameter is "-swf9"
	 * @param value store callback for the value.
	 * @return new string parameter.
	 */
	public static Parameter createStringParam(
		String prefix,
		IStringValue value) {
			
		return createStringParam(prefix, null, value);
	}
	
	 
	/**
	 * Creating a string parameter with empty key.
	 * 
	 * @param value store callback for the value.
	 * @return new string parameter.
	 */
	public static Parameter createEmptyStringParam(IStringValue value) {
		return createStringParam("", null, value);
	}
	
	
	/**
	 * Method creates an integer parameter.
	 * 
	 * @param prefix key for the parameter for example in the case of 
	 * 		"haxe -swf-version 7" the key for the parameter is "-swf-version"
	 * @param actions store action callback which works with the fact of
	 * 		parameter existence.
	 * @param value store callback for the value.
	 * @return new integer parameter.
	 */
	public static Parameter createIntParam(
		String prefix,
		IParamExistense actions,
		IIntValue value) {
			
		return createParam(prefix, actions, new IntValue(value));
	}

	
	/**
	 * Method creates an integer parameter.
	 * 
	 * @param prefix key for the parameter for example in the case of
	 * 		"haxe -swf-version 7" the key for the parameter is "-swf-version"
	 * @param value store callback for the value.
	 * @return new integer parameter.
	 */
	public static Parameter createIntParam(String prefix, IIntValue value) {
		return createIntParam(prefix, null, value);
	}

	
	/**
	 * Method creates an integer parameter.
	 * 
	 * @param value store callback for the value.
	 * @return new integer parameter.
	 */
	public static Parameter createEmptyIntParam(IIntValue value) {
		return createIntParam("", null, value);
	}
	
}

