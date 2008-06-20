package eclihx.core.util.console.parser.core;

import eclihx.core.util.console.parser.IParamExistense;

/**
 * Class that can notify user about absence or 
 * existence of the parameter
 */
public final class ParamExistenceChecker {
	
	/**
	 * User defined actions
	 */
	private final IParamExistense saveAction;
	
	public ParamExistenceChecker(IParamExistense saveAction)
	{
		this.saveAction = saveAction; 
	}
	
	/**
	 * Parser should call this method if parameter found
	 * @throws ParseError 
	 */
	public final void exist() throws ParseError {
		if (saveAction != null) {
			saveAction.save(true);
		}
	}
	
	/**
	 * Parser should call this method if it sure that it won't find the parameter
	 * @throws ParseError 
	 */
	public final void absence() throws ParseError {
		if (saveAction != null) {
			saveAction.save(false);
		}
	}
}

