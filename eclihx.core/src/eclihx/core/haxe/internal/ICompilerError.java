package eclihx.core.haxe.internal;

/**
 * A compilation error. Stores a full error position and a error message. 
 */
public interface ICompilerError {
	
	/**
	 * Path of the file with the error.
	 * 
	 * @return error file path.
	 */
	String getFilePath();
	
	/**
	 * Get line number of the error.
	 * 
	 * @return error line number.
	 */
	int getLineNumber();
	
	/**
	 * Start column of the error.
	 * @return start column of the error.
	 */
	int getStartCharacter();
	
	/**
	 * End column of the error.
	 * @return end column of the error.
	 */
	int getEndCharacter();
	
	/**
	 * Message with the error description.
	 * 
	 * @return error message.
	 */
	String getMessage();
	
	/**
	 * Start line of the error.
	 * 
	 * @return error message.
	 */
	int getStartLine();
	
	/**
	 * Start line of the error.
	 * 
	 * @return error message.
	 */
	int getEndLine();
}
