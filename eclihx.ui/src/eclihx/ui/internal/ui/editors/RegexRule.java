package eclihx.ui.internal.ui.editors;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

/**
 * This rule reads words defined by the <code>IWordDetector</code> and performs 
 * additional check for the matching with regular expression.
 * 
 * Pay attention to the fact that <b>whole</b> word should match the expression. 
 * This means that if you have forgotten to add '^' and '$' they would be 
 * added automatically.
 * 
 * Note that some regular expressions can be processed for <b>too long</b> and 
 * it could be a problem for the text rule.
 */
public class RegexRule implements IRule {
	
	/**
	 * Pairs class of regular expression and token to return if matching is
	 * successful.
	 */
	private static class TokenPair {
		
		/**
		 * Token.
		 */
		private final IToken token;
		
		/**
		 * Pattern.
		 */
		private final Pattern pattern;
		
		/**
		 * Dummy constructor 
		 * @param regex the pattern for pair.
		 * @param token the token.
		 */
		public TokenPair(String regex, IToken token) {
			this.token = token;
			this.pattern = Pattern.compile(regex);
		}
		
		/**
		 * Get the token.
		 * @return the token.
		 */
		public IToken getToken() {
			return token;
		}

		/**
		 * Get the pattern.
		 * @return the pattern.
		 */
		public Pattern getPattern() {
			return pattern;
		}
	}

	/**
	 * Detector of the words.
	 */
	private final IWordDetector wordDetector;
	
	/**
	 * Token to return if matching isn't successful.
	 */
	private final IToken defaultToken;
	
	/**
	 * Pairs of regular expression and token to return if matching is
	 * successful.
	 */
	private final ArrayList<TokenPair> tokenPairs = new ArrayList<TokenPair>();

	/**
	 * Constructor with a word detector and default token of the case of
	 * unsuccessful matching.
	 * @param wordDetector detector for the valid words.
	 * @param defaultToken token which should be returned if matching is
	 *        unsuccessful.
	 */
	public RegexRule(IWordDetector wordDetector, IToken defaultToken) {
		{ // Parameters validation
			if (wordDetector == null) {
				throw new NullPointerException(
						"wordDetector parameter mustn't be null");
			}
			
			if (defaultToken == null) {
				throw new NullPointerException(
						"defaultToken parameter mustn't be null");
			}
		}
		
		this.wordDetector = wordDetector;
		this.defaultToken = defaultToken;		
	}
	
	/**
	 * Constructor with a word detector. It sets default 
	 * token to Token.UNDEFINED.
	 * @param wordDetector detector for the valid words.
	 */
	public RegexRule(IWordDetector wordDetector) {
		this(wordDetector, Token.UNDEFINED);
	}
	
	/**
	 * Method adds new pair of regular expression with the correspondent token
	 * to the rule.
	 *  
	 * @param regex the regular expression string.
	 * @param token the correspondent token.
	 */
	public void addRegex(String regex, IToken token) {
		
		{ // Parameters validation
			if (regex == null) {
				throw new NullPointerException(
						"regex parameter mustn't be null");
			}
			
			if (token == null) {
				throw new NullPointerException(
						"token parameter mustn't be null");
			}
		}
		
		String regexPattern = regex;
		
		if (!regexPattern.startsWith("^")) {
			regexPattern = "^" + regexPattern;
		}
		
		if (!regexPattern.endsWith("$")) {
			regexPattern = regexPattern + "$";
		}
			
		tokenPairs.add(new TokenPair(regexPattern, token));
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
	 */
	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		
		{ // Parameters validation
			if (scanner == null) {
				throw new NullPointerException(
						"scanner parameter mustn't be null");
			}
		}
		
		ScannerController sc = new ScannerController(scanner);
		
		String str = sc.readString(this.wordDetector);
		
		if (str != null) {
			
			for (TokenPair pair : tokenPairs ) {
				if (pair.getPattern().matcher(str).matches()) {
					return pair.getToken();
				}
			}			
		}
		
		sc.unreadAll();
		return defaultToken;
	}

}
