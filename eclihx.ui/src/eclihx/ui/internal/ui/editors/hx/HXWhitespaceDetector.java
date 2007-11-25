package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class HXWhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
	}
}
