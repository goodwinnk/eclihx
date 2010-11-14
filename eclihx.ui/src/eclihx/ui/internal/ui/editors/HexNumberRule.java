package eclihx.ui.internal.ui.editors;

import java.util.TreeSet;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WordPatternRule;

/**
 * Reads hex number.
 */
public final class HexNumberRule implements IRule {
	
	/**
	 * Word detector for hex number words.
	 */
	private class HexNumberDetector implements IWordDetector {
		
		private final TreeSet<Character> hexChars;

		public HexNumberDetector() {
			hexChars = new TreeSet<Character>();
			
			hexChars.add('0');
			hexChars.add('1');
			hexChars.add('2');
			hexChars.add('3');
			hexChars.add('4');
			hexChars.add('5');
			hexChars.add('6');
			hexChars.add('7');
			hexChars.add('8');
			hexChars.add('9');
			hexChars.add('a');
			hexChars.add('b');
			hexChars.add('c');
			hexChars.add('d');
			hexChars.add('e');
			hexChars.add('f');
			hexChars.add('A');
			hexChars.add('B');
			hexChars.add('C');
			hexChars.add('D');
			hexChars.add('E');
			hexChars.add('F');
		}
		
		public boolean isWordPart(char c) {
			return hexChars.contains(c);
		}

		public boolean isWordStart(char c) {
			return hexChars.contains(c);
		}
		
	}

	/**
	 * Word rule which is used for hex number detecting.
	 */
	private final WordPatternRule wordRule;
	
	/**
	 * Default constructor with the token this detector should return in
	 * the case of success parsing.
	 * @param token the token for detector.
	 */
	public HexNumberRule(IToken token) {
		this.wordRule = new WordPatternRule(
				new HexNumberDetector(), "0x", null, token);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
	 */
	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		return wordRule.evaluate(scanner);
	}	
}
