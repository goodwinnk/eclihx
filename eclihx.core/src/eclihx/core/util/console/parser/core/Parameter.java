package eclihx.core.util.console.parser.core;

import eclihx.core.util.console.parser.IParamExistense;

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
	
	
	public Parameter(String prefix, IParamExistense flag, IValueParser[] valueProcessors) {
		this.prefix = prefix;
		this.existenceFlag = new ParamExistenceChecker(flag);
		this.valueProcessors = valueProcessors;
	}
	
	public int numberOfParameters() {
		return (valueProcessors == null ? 0 : valueProcessors.length);
	}
	
	public final String getPrefix() {
		return prefix;
	}
	
	public final void parse(String[] values) throws ParseError {
		
		// Some internal error
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
	
	public void absence() throws ParseError {
		existenceFlag.absence();
	}
}
