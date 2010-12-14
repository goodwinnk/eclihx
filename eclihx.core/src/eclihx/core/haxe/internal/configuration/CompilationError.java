package eclihx.core.haxe.internal.configuration;

import eclihx.core.haxe.internal.ICompilerError;
import eclihx.core.util.language.Pair;

/**
 * A compilation error. Stores a full error position and a error message.
 */
public class CompilationError implements ICompilerError {
	
	private final String filePath;
	private final int lineNumber;
	private final int startCharacter;
	private final int endCharacter;
	private final String message;
	private final int startLine;
	private final int endLine;

	/**
	 * Common constructor for both errors defined with line range or characters range.
	 * 
	 * @param filePath file path.
	 * @param lineNumber line number.
	 * @param startCharacter start error column in the line.
	 * @param endCharacter end error column in the line.
	 * @param startLine first line in error range.
	 * @param endLine last line in error range.
	 * @param message a error message.
	 */
	private CompilationError(String filePath, int lineNumber,
			int startCharacter, int endCharacter, int startLine, int endLine, String message) {
		
		super();
		this.filePath = filePath;
		this.lineNumber = lineNumber;
		this.startCharacter = startCharacter;
		this.endCharacter = endCharacter;
		this.message = message;
		this.startLine = startLine;
		this.endLine = endLine;
	}
	
	/**
	 * Get the error with defining exact line and characters range.
	 * 
	 * @param filePath file path.
	 * @param lineNumber line number.
	 * @param charactersRange a pair with the start and end column of the error.
	 * @param message a error message.
	 * @return Get the error with defining exact line and characters range.
	 */
	public static ICompilerError getCharRangeError(String filePath, int lineNumber,
			Pair<Integer, Integer> charactersRange, String message) {
		
		return new CompilationError(filePath, lineNumber, 
				charactersRange.getFirst(), charactersRange.getSecond(), 
				lineNumber, lineNumber, message);
	}
	
	/**
	 * Get the error with defining lines range.
	 * 
	 * @param filePath file path.
	 * @param lineNumber line number.
	 * @param linesRange a pair with the start and end line of the error.
	 * @param message a error message.
	 * @return Get the error with defining lines range.
	 */
	public static ICompilerError getLineRangeError(String filePath, int lineNumber,
			Pair<Integer, Integer> linesRange, String message) {
		
		return new CompilationError(filePath, lineNumber, 0, 0, 
				linesRange.getFirst(), linesRange.getSecond(), 
				message);
	}	

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IHaxeCompilerError#getFilePath()
	 */
	@Override
	public String getFilePath() {
		return filePath;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IHaxeCompilerError#getLineNumber()
	 */
	@Override
	public int getLineNumber() {
		return lineNumber;
	}
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IHaxeCompilerError#getStartCharacter()
	 */
	@Override
	public int getStartCharacter() {
		return startCharacter;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IHaxeCompilerError#getEndCharacter()
	 */
	@Override
	public int getEndCharacter() {
		return endCharacter;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IHaxeCompilerError#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s:%d: characters %d-%d, lines %d-%d: %s", filePath, 
				lineNumber, startCharacter, endCharacter, startLine, endLine, message);
	}

	@Override
	public int getStartLine() {
		return startLine;
	}

	@Override
	public int getEndLine() {
		return endLine;
	}
}
