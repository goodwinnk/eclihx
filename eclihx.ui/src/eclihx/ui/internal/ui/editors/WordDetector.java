package eclihx.ui.internal.ui.editors;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * Word detector
 */
public class WordDetector implements IWordDetector {

	public boolean isWordStart(char c) {
		return Character.isLetter(c) || c == '_';
	}

	public boolean isWordPart(char c) {
		return Character.isLetterOrDigit(c) || c == '_';
	}
}
