package eclihx.ui.internal.ui.editors;

import java.util.TreeSet;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * Detects the word which contains only from restricted number of characters
 */
class CharSetWordDetector implements IWordDetector {
	
	private final TreeSet<Character> charSet;

	public CharSetWordDetector(char[] chars) {
		charSet = new TreeSet<Character>();
		for (char ch : chars) {
			charSet.add(ch);
		}
	}
	
	public boolean isWordPart(char c) {
		return charSet.contains(c);
	}

	public boolean isWordStart(char c) {
		return isWordPart(c);
	}
}
