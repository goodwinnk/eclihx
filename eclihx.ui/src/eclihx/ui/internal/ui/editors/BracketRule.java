package eclihx.ui.internal.ui.editors;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;


/**
 * Rule to detect brackets.
 */
public final class BracketRule implements IRule {

	private final class BracketPair {
		public BracketPair(char openBracket, char closeBracket) {
			this.open = openBracket;
			this.close = closeBracket;
		}
		char open;
		char close;
	}

	BracketPair fBracket;
	
	/** Token to return for this rule */
	private final IToken fToken;

	/**
	 * Creates a new bracket rule.
	 * 
	 * @param openBracket char for open bracket
	 * @param closeBracket char for close bracket
	 * @param token Token to use for this rule
	 */
	public BracketRule(char openBracket, char closeBracket, IToken token) {
		fToken = token;
		fBracket = new BracketPair(openBracket, closeBracket);
	}

	/**
	 * Is this character a bracket character?
	 *
	 * @param character Character to determine whether it is a bracket character
	 * @return <code>true</code> iff the character is a bracket, <code>false</code> otherwise.
	 */
	public boolean isBracket(char character) {
		if (fBracket.open == character || fBracket.close == character)
			return true;
		return false;
	}

	/*
	 * @see org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
	 */
	public IToken evaluate(ICharacterScanner scanner) {

		int character = scanner.read();
		if (isBracket((char) character)) {
			do {
				character = scanner.read();
			} while (isBracket((char) character));
			scanner.unread();
			return fToken;
		} else {
			scanner.unread();
			return Token.UNDEFINED;
		}
	}
}

