package eclihx.core.haxe.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eclihx.core.haxe.internal.configuration.CompilationError;
import eclihx.core.util.language.Pair;

/**
 * Parses haXe compile errors.
 * 
 * Supported versions: 2.0
 */
public final class HaxeOutputErrorsParser implements IHaxeOutputErrorsParser {

	private static final String SUPPORTED_VERSIONS[] = { ">=2.0" };
	
	/**
	 * String which is given in successful build.
	 */
	private static final String SUCCESS_BUILD_STRING = "Building complete";
	
	/**
	 * A substring which split file name, line, characters and a message of 
	 * the error.
	 */
	private static final String LINE_ERROR_SEPARATOR = ":";
	
	/**
	 * A string printed before error characters. 
	 */
	private static final String CHARACTER_PREFIX = "characters";
	
	/**
	 * Characters separator. 
	 */
	private static final String CHARACTER_SEPARATOR = "-";
	
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IHaxeVersionsInfo#getSupportedVersions()
	 */
	@Override
	public final List<String> getSupportedVersions() {
		return Arrays.asList(SUPPORTED_VERSIONS);
	}
	
	/**
	 * Read the file name for the error file part.
	 * 
	 * @param fileNamePart a part with the file name.
	 * @return a file name
	 */
	protected String processFileName(String fileNamePart) {
		return fileNamePart;
	}
	
	/**
	 * Get the line number for the line number part.
	 * 
	 * @param linePart a substring with the line number.
	 * @return a error line number. null if there's some error.
	 */
	protected Integer processLineNumber(String linePart) {
		try {
			return Integer.parseInt(linePart);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	/**
	 * Read the start and end column of the error.
	 * 
	 * @param charactersPart a substring with the line characters.
	 * @return a pair with the start and end character.
	 */
	protected Pair<Integer, Integer> processCharacters(String charactersPart) {
		int indexOfPrefix = charactersPart.indexOf(CHARACTER_PREFIX);
		if (indexOfPrefix == -1) {
			return null;
		}
		
		String startEndStr = charactersPart.substring(
				indexOfPrefix + CHARACTER_PREFIX.length());
		
		String charactersIntegers[] = startEndStr.split(CHARACTER_SEPARATOR);
		
		if (charactersIntegers.length == 0 || charactersIntegers.length > 2) {
			// No or too many character integers. 
			return null;
		}
		
		try {
			int startChar = Integer.parseInt(charactersIntegers[0].trim());
			int endChar; 

			if (charactersIntegers.length == 2) {
				endChar = Integer.parseInt(charactersIntegers[1].trim());
			} else {
				assert(charactersIntegers.length == 1);
				endChar = startChar;
			}	
			return new Pair<Integer, Integer>(startChar, endChar);

		} catch (NumberFormatException numberFormatException) {
			return null;
		}
	}
	
	/**
	 * Gives the message from the message part of the error string 
	 * @param messagePart the part with the message.
	 * @return error message.
	 */
	protected String processMessage(String messagePart) {
		return messagePart;		
	}
	
	/**
	 * Method process a line with the compile error.
	 * 
	 * Example of the line with the error:
	 * SmallTest/src/Test.hx:3: characters 6-7 : Missing ;
	 * 
	 * @param errorLine a line with the error.
	 * @return new compile error object or null if parsing wan't success.
	 */
	protected ICompilerError processErrorLine(String errorLine) {
		String errorDescriptionParts[] = errorLine.split(LINE_ERROR_SEPARATOR);
		
		if (errorDescriptionParts.length != 4) {
			// This is not a haXe compilation error
			return null;
		}
		
		String filePath = processFileName(errorDescriptionParts[0]);
		Integer lineNumber = processLineNumber(errorDescriptionParts[1]);
		Pair<Integer, Integer> characters = processCharacters(errorDescriptionParts[2]);
		String message = processMessage(errorDescriptionParts[3]);
		
		if (!(filePath == null || lineNumber == null || characters == null || 
				message == null)) {
			
			return new CompilationError(filePath, lineNumber, characters, message);
		}
		
		return null;		
	}
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IHaxeOutputErrorsParser#parseErrors(java.lang.String)
	 */
	@Override
	public List<ICompilerError> parseErrors(String output, String buildFile) {
		
		ArrayList<ICompilerError> errorsList = 
				new ArrayList<ICompilerError>(); 
		
		// Check build is success
		if (!output.contains(SUCCESS_BUILD_STRING)) {
			
			// It's expected that each line contains a error.
			for (String line : output.split("\n")) {
				ICompilerError error = processErrorLine(line);
				if (error != null) {
					errorsList.add(error);
				} else {
					errorsList.add(new CompilationError(buildFile, 0, 
							new Pair<Integer, Integer>(0, 0), line));
				}
			}
		}		
		
		return errorsList;
	}
}
