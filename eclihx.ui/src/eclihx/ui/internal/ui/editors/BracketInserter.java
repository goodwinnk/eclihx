// Based on http://e-p-i-c.cvs.sourceforge.net/viewvc/e-p-i-c/org.epic.perleditor/src/org/epic/perleditor/editors/PerlBracketInserter.java?view=log by jploski
// Under Common Public License 1.0

package eclihx.ui.internal.ui.editors;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;

import eclihx.core.IPluginLogger;

/**
 * Improve coding activity with auto inserting close brackets and quotes.
 */
public class BracketInserter implements VerifyKeyListener {
	private static final char NON_BRACKET = '\u0000';

	private final IPluginLogger log;

	// Enable all by default
	private boolean closeAngularBrackets = true;
	private boolean closeBraces = true;
	private boolean closeBrackets = true;
	private boolean closeParens = true;
	private boolean closeDoubleQuotes = true;
	private boolean closeSingleQuotes = true;

	private ISourceViewer viewer;

	/**
	 * Default constructor with logger dependency.
	 * 
	 * @param log plug-in logger.
	 */
	public BracketInserter(IPluginLogger log) {
		this.log = log;
	}

	private boolean isEnabled() {
		return viewer != null
				&& (closeAngularBrackets || closeBraces || closeBrackets
						|| closeDoubleQuotes || closeSingleQuotes || closeParens);
	}

	/**
	 * Set up source viewer. Not in constructor because it's should be possible
	 * to change it.
	 * 
	 * @param viewer Source viewer.
	 */
	public void setViewer(ISourceViewer viewer) {
		this.viewer = viewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.custom.VerifyKeyListener#verifyKey(org.eclipse.swt.events
	 * .VerifyEvent)
	 */
	@Override
	public void verifyKey(VerifyEvent event) {
		if (!event.doit || !isEnabled()) {
			return;
		}

		char closingChar = getClosingChar(event.character);
		if (closingChar == NON_BRACKET) {
			return;
		}

		event.doit = processBracketKeyStroke(viewer.getDocument(),
				viewer.getSelectedRange(), event.character, closingChar);
	}

	/**
	 * @return if <code>c</code> is one of the bracket characters for which
	 *         bracket insertion is enabled, the correspnding closing bracket
	 *         character; otherwise NON_BRACKET
	 */
	private char getClosingChar(char c) {
		switch (c) {
			case ')':
			case '(':
				return closeParens ? ')' : NON_BRACKET;
			case '>':
			case '<':
				return closeAngularBrackets ? '>' : NON_BRACKET;
			case '}':
			case '{':
				return closeBraces ? '}' : NON_BRACKET;
			case ']':
			case '[':
				return closeBrackets ? ']' : NON_BRACKET;
			case '\'':
				return closeSingleQuotes ? '\'' : NON_BRACKET;
			case '\"':
				return closeDoubleQuotes ? '"' : NON_BRACKET;
			default:
				return NON_BRACKET;
		}
	}

	/**
	 * @return true if the given character inserted at the given offset in the
	 *         document would act as a "closing" character; false otherwise
	 */
	private boolean isClosingChar(IDocument doc, int offset, char c) {
		if (c == '}' || c == ']' || c == '>' || c == ')') {
			return true; // easy
		} else if (offset == 0) {
			return false; // easy
		} else {
			// try
			// {
			// // A quote is a closing char when inserted to terminate a string
			// literal,
			// // otherwise it is an opening char:
			// String partitionType = PartitionTypes.getPerlPartition(doc,
			// offset-1).getType();
			// return PartitionTypes.LITERAL1.equals(partitionType) ||
			// PartitionTypes.LITERAL2.equals(partitionType);
			// }
			// catch (BadLocationException e)
			// {
			// logBadLocationException(e);
			// return false;
			// }
			return false;
		}
	}

	/**
	 * @param doc document to be modified in result of the key stroke
	 * @param selection selection in the document at the time of the key stroke
	 *        (x = offset, y = length) or caret position if there was no
	 *        selection (x, y = 0)
	 * @param keystrokeChar character entered by the user
	 * @param closingChar the corresponding "closing" character
	 * @return true if the key stroke event should be processed, false if it
	 *         should be discarded
	 */
	private boolean processBracketKeyStroke(IDocument doc, Point selection,
			char keystrokeChar, char closingChar) {
		final int offset = selection.x;
		final int length = selection.y;

		try {
			// // Duplication of apostrophes in a comment/POD is undesirable:
			// if (keystrokeChar == '\'' && offset > 0)
			// {
			// String partitionType = PartitionTypes.getPerlPartition(doc,
			// offset-1).getType();
			// if (PartitionTypes.POD.equals(partitionType) ||
			// PartitionTypes.COMMENT.equals(partitionType)) return true;
			// }

			if (isClosingChar(doc, offset, keystrokeChar)) {
				// The user has just typed a closing char

				if (offset + length < doc.getLength()
						&& doc.getChar(offset + length) == closingChar) {
					// There's already a closing char in front of us, so skip it
					skipChar();
					return false;
				}
			} else {
				// The user has just typed an opening char

				if (offset + length < doc.getLength()
						&& doc.getChar(offset + length) == keystrokeChar) {
					// There's already an opening char in front of us, so skip it
					skipChar();
					return false;
				} else {
					// Auto-insert the closing char just after it...
					char[] pair = new char[] { keystrokeChar, closingChar };
					doc.replace(offset, 0, String.valueOf(pair));
					// ...and position the caret after the entered char
					skipChar();
					return false;
				}
			}
			return true;
		} catch (BadLocationException e) {
			log.logError(e);
			return true;
		}
	}

	private void skipChar() {
		StyledText text = viewer.getTextWidget();
		text.setCaretOffset(text.getCaretOffset() + 1);
	}
}