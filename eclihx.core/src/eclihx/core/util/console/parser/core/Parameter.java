package eclihx.core.util.console.parser.core;

import eclihx.core.util.console.parser.IParamExistense;

/**
 * A single parameter of the console to parse.
 */
public final class Parameter {
	
	/**
	 * Name of the parameter
	 */
	private final String prefix;
	
	/**
	 * Values which can contain this parameter
	 */
	private final IValueParser[] valueProcessors;
	
	/**
	 * 
	 */	
	private final ParamExistenceChecker existenceFlag;
	
	/**
	 * Create new console parameter.
	 * 
	 * @param prefix A string unique prefix for identifying parameter.
	 * @param flagBehavior Some special behavior should be activated when parser get current parameter.
	 * @param valueProcessors Processors for values which can contain this parameter.
	 */
	public Parameter(String prefix, IParamExistense flagBehavior, IValueParser[] valueProcessors) {
		this.prefix = prefix;
		this.existenceFlag = new ParamExistenceChecker(flagBehavior);
		this.valueProcessors = valueProcessors;
	}
	
	/**
	 * Number of values this parameter accepts.
	 * 
	 * @return Number of values this parameter accepts.
	 */
	public int numberOfParameters() {
		return (valueProcessors == null ? 0 : valueProcessors.length);
	}
	
	/**
	 * A string unique prefix for identifying parameter.
	 * 
	 * @return A string unique prefix for identifying parameter.
	 */
	public final String getPrefix() {
		return prefix;
	}
	
	/**
	 * Parse values.
	 * 
	 * @param values
	 * @throws ParseError
	 */
	final void parse(String[] values) throws ParseError {
		
		// Some internal parse error
		assert(numberOfParameters() < values.length);
		
		if (valueProcessors != null) {
			if (values.length < valueProcessors.length) {
				throw new ParseError(String.format(
						"Not enough parameters for '%1$s' prefix", prefix));
			}
		
			for (int i = 0; i < values.length; ++i) {
				valueProcessors[i].parse(values[i]);
			}
		}
		
		existenceFlag.exist();
	}
	
	/**
	 * Called with parser when current parameter wasn'te found in input. 
	 * @throws ParseError
	 */
	void absence() throws ParseError {
		existenceFlag.absence();
	}
}
