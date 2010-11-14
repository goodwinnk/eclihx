package eclihx.ui.internal.ui.editors;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IWordDetector;

/**
 * Simple class which extend number of operations available for 
 * ICharacterScanner.
 * Note that you <b>can't</b> unread more character you have read before.
 * 
 * Class is not finished yet.
 */
public final class ScannerController {
	
	/**
	 * Original scanner.
	 */
	private final ICharacterScanner scanner;
	
	/**
	 * Number of characters we had read.
	 */
	private int readLength; 
	
	/**
	 * Constructor which wrap the original scanner.
	 * @param scanner base scanner.
	 */
	public ScannerController(ICharacterScanner scanner) {
		this.scanner = scanner;
	}
	
	/**
	 * Reading defined number of characters
	 * 
	 * @param length number of characters to read.
	 * @return the string we had read.
	 */
	public String readString(int length) {
		
		StringBuilder builder = new StringBuilder();
		
		for (int readResult = read(), counter = 0; counter < length; ++counter, readResult = read()) {

			if (notEOF(readResult)) { // We can't read anymore
				break;
			}
			
			// Good! We have read a character. Append it to the string
			builder.append((char)readResult);		
		}
		
		return builder.toString();
	}

	
	/**
	 * Read the string defined by the word detector
	 * @param wordDetectror the word detector.
	 * @return String we had read or <code>null</code> if scanner was at the end 
	 * 		   or start character is invalid.
	 */
	public String readString(IWordDetector wordDetectror) {
		int readResult = read();
		
		if (notEOF(readResult) && wordDetectror.isWordStart((char)readResult)) {
			StringBuilder builder = new StringBuilder();
			
			do {
				builder.append((char)readResult);
				readResult = read();
			} while (notEOF(readResult) && 
					 wordDetectror.isWordPart((char)readResult));
			
			// We should unread last character because it doesn't belong to the string
			unread(); 
			
			return builder.toString();

		} else {
			return null;
		}		
	}
	
	/**
	 * Read one character.
	 * @return result of the ICharacterScanner.read() operation.
	 */
	public int read() {
		int result = scanner.read();
		readLength += 1;
		
		return result;
	}	
	
	/**
	 * Unread one character.
	 * Note that you can't unread more character you have read before.
	 * @return <code>true</code> if we unread something
	 */
	private boolean unread() {
		if (readLength == 0) return false;
		
		scanner.unread();
		--readLength;
		
		return true;
	}
	
	/**
	 * Unread all characters scanner had read before.
	 * @return <code>true</code> if we unread something.
	 */
	public boolean unreadAll() {
		if (readLength == 0) return false;
		
		for(; readLength > 0; --readLength) {
			scanner.unread();
		}
		
		return true;
	}
	
	/**
	 * Check if the read result isn't end of file flag.
	 * @param readResult the result of the read operation.
	 * @return <code>true</code> if parameter <b>isn't</b> end of file.  
	 */
	private static boolean notEOF(int readResult) {
		return readResult != ICharacterScanner.EOF;
	}
}
