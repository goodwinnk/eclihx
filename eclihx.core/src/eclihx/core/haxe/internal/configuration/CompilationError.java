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

	/**
	 * Constructor.
	 * 
	 * @param filePath file path.
	 * @param lineNumber line number.
	 * @param startCharacter start error column in the line.
	 * @param endCharacter end error column in the line.
	 * @param message a error message.
	 */
	public CompilationError(String filePath, int lineNumber,
			int startCharacter, int endCharacter, String message) {
		
		super();
		this.filePath = filePath;
		this.lineNumber = lineNumber;
		this.startCharacter = startCharacter;
		this.endCharacter = endCharacter;
		this.message = message;
	}
	
	/**
	 * Constructor with the position within the single pair.
	 * 
	 * @param filePath file path.
	 * @param lineNumber line number.
	 * @param characterPair a pair with the start and end column of the error.
	 * @param message a error message.
	 */
	public CompilationError(String filePath, int lineNumber,
			Pair<Integer, Integer> characterPair, String message) {
		
		this(filePath, lineNumber, characterPair.getFirst(), 
				characterPair.getSecond(), message);
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
		return String.format("%s:%d: characters %d-%d: %s", filePath, 
				lineNumber, startCharacter, endCharacter, message);
	}
}
