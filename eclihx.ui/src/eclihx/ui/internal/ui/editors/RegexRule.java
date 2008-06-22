package eclihx.ui.internal.ui.editors;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

/**
 * This rule reads words defined by the <code>IWordDetector</code> and performs additional 
 * check for the matching with regular expression.
 * 
 * Pay attention to the fact that <b>all</b> word should match the expression. This means that
 * if you have forgotten to add '^' and '$' they would be added automatically
 * 
 * Note that some regular expressions can be processed for <b>too long</b> and it could be a problem
 * for the text rule.
 */
public class RegexRule implements IRule {
	
	private class TokenPair {
		private IToken token;
		private Pattern pattern;
		
		public TokenPair(String regex, IToken token) {
			this.token = token;
			this.pattern = Pattern.compile(regex);
		}
		
		public IToken getToken() {
			return token;
		}

		public Pattern getPattern() {
			return pattern;
		}
	}

	private final IWordDetector wordDetector;
	private final IToken defaultToken;
	private final ArrayList<TokenPair> tokenPairs = new ArrayList<TokenPair>();

	public RegexRule(IWordDetector wordDetector, IToken defaultToken) {
		this.wordDetector = wordDetector;
		this.defaultToken = defaultToken;		
	}
	
	public RegexRule(IWordDetector wordDetector) {
		this(wordDetector, Token.UNDEFINED);
	}
	
	public void addRegex(String regex, IToken token) {
		
		String regexPattern = regex;
		
		if (!regexPattern.startsWith("^")) {
			regexPattern = "^" + regexPattern;
		}
		
		if (!regexPattern.endsWith("$")) {
			regexPattern = regexPattern + "$";
		}
			
		tokenPairs.add(new TokenPair(regexPattern, token));
	}
	
	public IToken evaluate(ICharacterScanner scanner) {
		
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
