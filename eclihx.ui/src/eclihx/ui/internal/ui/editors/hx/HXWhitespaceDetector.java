package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * Defines the spaces characters for haXe source files.
 * 
 * @see IWhitespaceDetector
 */
public class HXWhitespaceDetector implements IWhitespaceDetector {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IWhitespaceDetector#isWhitespace(char)
	 */
	@Override
	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
