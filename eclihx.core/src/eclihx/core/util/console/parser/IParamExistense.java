package eclihx.core.util.console.parser;

import eclihx.core.util.console.parser.core.ParseError;

/**
 * Defines user actions for the parser when it's known
 * is some parameter exists or not.
 * 
 * Pay attention that save will be called each time parameter found 
 * during the parser process
 */
public interface IParamExistense {

	/**
	 * Method which defines how to save parameter
	 * @param exist is parameter exists
	 * @throws ParseError
	 */
	void save(boolean exist) throws ParseError;
	
}
