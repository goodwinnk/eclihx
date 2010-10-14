package eclihx.core.util.console.parser.core;

import eclihx.core.util.console.parser.IParamExistense;

/**
 * Class that can notify user about absence or existence of the parameter
 */
public final class ParamExistenceChecker {

	/**
	 * User defined actions
	 */
	private final IParamExistense saveAction;

	/**
	 * An action which should be done if parameter will be found.
	 * 
	 * @param saveAction An action which should be done if parameter will be found. Null is valid.
	 */
	public ParamExistenceChecker(IParamExistense saveAction) {
		this.saveAction = saveAction;
	}

	/**
	 * Parser should call this method if parameter found
	 * 
	 * @throws ParseError Parser error.
	 */
	public final void exist() throws ParseError {
		if (saveAction != null) {
			saveAction.save(true);
		}
	}

	/**
	 * Parser should call this method if it sure that it won't find the
	 * parameter
	 * 
	 * @throws ParseError Parser error.
	 */
	public final void absence() throws ParseError {
		if (saveAction != null) {
			saveAction.save(false);
		}
	}
}
