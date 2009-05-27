package eclihx.core.haxe.internal;

import java.util.List;

/**
 * Interface for the parser of the haXe compiler output which is aimed to
 * getting compile errors.
 */
public interface IHaxeOutputErrorsParser extends IHaxeVersionsInfo {
	
	/**
	 * Gives the list of compile errors from the compiler output.
	 * 
	 * @param output haXe compiler output.
	 * @param buildFile a build file line. 
	 * @return A list of errors.
	 */	
	List<ICompilerError> parseErrors(String output, String buildFile);
}
