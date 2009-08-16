package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import java.util.ArrayList;

import eclihx.ui.PreferenceConstants;
import eclihx.ui.internal.ui.editors.AbstractScanner;
import eclihx.ui.internal.ui.editors.AdvancedNumberRule;
import eclihx.ui.internal.ui.editors.BracketRule;

public class HXScanner extends AbstractScanner {
	
	private final String[] fDeclareKeyWords = {"var", "function", "new", "delete", "class", "package"};
	
	private final String[] fKeyWords = {"import", "extends", "implements", "extern", "private",
			              "public", "static", "try", "catch", "throw", "cast", "return", "break", "continue",
	                      "if", "else", "for", "while", "do", "switch", "case", "with"};
	
	private final String[] fTypeWords = {"Void", "Float", "Int"};
	
	private final String[] fTypeConstants = {"null", "undefined", "true", "false"};
	
	//private final String singleLineComment = "\\";
	
	
	@Override
	protected TextAttributesKey[] getAttributesKeys() {
		TextAttributesKey[] attributeKeys = {
			new TextAttributesKey(PreferenceConstants.HX_EDITOR_DEFAULT_COLOR, PreferenceConstants.HX_EDITOR_DEFAULT_BOLD, PreferenceConstants.HX_EDITOR_DEFAULT_ITALIC, null, null),
			new TextAttributesKey(PreferenceConstants.HX_EDITOR_DECLARE_KEYWORDS_COLOR, PreferenceConstants.HX_EDITOR_DECLARE_KEYWORDS_BOLD, PreferenceConstants.HX_EDITOR_DECLARE_KEYWORDS_ITALIC, null, null),
			new TextAttributesKey(PreferenceConstants.HX_EDITOR_KEYWORDS_COLOR, PreferenceConstants.HX_EDITOR_KEYWORDS_BOLD, PreferenceConstants.HX_EDITOR_KEYWORDS_ITALIC, null, null),
			new TextAttributesKey(PreferenceConstants.HX_EDITOR_TYPE_COLOR, PreferenceConstants.HX_EDITOR_TYPE_BOLD, PreferenceConstants.HX_EDITOR_TYPE_ITALIC, null, null),
			new TextAttributesKey(PreferenceConstants.HX_EDITOR_NUMBER_COLOR, PreferenceConstants.HX_EDITOR_NUMBER_BOLD, PreferenceConstants.HX_EDITOR_NUMBER_ITALIC, null, null),
			new TextAttributesKey(PreferenceConstants.HX_EDITOR_BRACKET_COLOR, PreferenceConstants.HX_EDITOR_BRACKET_BOLD, PreferenceConstants.HX_EDITOR_BRACKET_ITALIC, null, null),
			new TextAttributesKey(PreferenceConstants.HX_EDITOR_BRACE_COLOR, PreferenceConstants.HX_EDITOR_BRACE_BOLD, PreferenceConstants.HX_EDITOR_BRACE_ITALIC, null, null),
			new TextAttributesKey(PreferenceConstants.HX_EDITOR_STRING_COLOR, PreferenceConstants.HX_EDITOR_STRING_BOLD, PreferenceConstants.HX_EDITOR_STRING_ITALIC, null, null),
		};
		return attributeKeys;
	}


	/**
	 * Word detector
	 */
	static class WordDetector implements IWordDetector {

		public boolean isWordStart(char c) {
			return Character.isLetter(c) || c == '_';
		}

		public boolean isWordPart(char c) {
			return Character.isLetterOrDigit(c) || c == '_';
		}
	}
	
	public HXScanner(ColorManager manager, IPreferenceStore store) {
		super(manager, store);
		initialize();
	}
	
	public ArrayList<IRule> createRules() {
		ArrayList<IRule> rules = new ArrayList<IRule>();

		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new HXWhitespaceDetector()));
		
		IToken bracketToken = getToken(PreferenceConstants.HX_EDITOR_BRACKET_COLOR);
		rules.add(new BracketRule('(', ')', bracketToken));
		rules.add(new BracketRule('[', ']', bracketToken));
		
		IToken braceToken = getToken(PreferenceConstants.HX_EDITOR_BRACE_COLOR);
		rules.add(new BracketRule('{', '}', braceToken));
		
		{
			// Add rule for haXe keywords
			IToken wordToken = getToken(PreferenceConstants.HX_EDITOR_DEFAULT_COLOR); 
			WordRule wr = new WordRule(new WordDetector(), wordToken);
			
			IToken keyWordToken = getToken(PreferenceConstants.HX_EDITOR_KEYWORDS_COLOR);
			for (int i = 0; i < fKeyWords.length; i++)
				wr.addWord(fKeyWords[i], keyWordToken);
			
			IToken declareWordToken = getToken(PreferenceConstants.HX_EDITOR_DECLARE_KEYWORDS_COLOR);
			for (int i = 0; i < fDeclareKeyWords.length; i++)
				wr.addWord(fDeclareKeyWords[i], declareWordToken);
			
			IToken typeConstatToken = getToken(PreferenceConstants.HX_EDITOR_NUMBER_COLOR);
			for (int i = 0; i < fTypeConstants.length; i++)
				wr.addWord(fTypeConstants[i], typeConstatToken);
			
			IToken typeWordToken = getToken(PreferenceConstants.HX_EDITOR_TYPE_COLOR);
			for (int i = 0; i < fTypeWords.length; i++) {
				wr.addWord(fTypeWords[i], typeWordToken);
			}
	
			rules.add(wr);
		}

		// Numbers
		rules.add(new AdvancedNumberRule(getToken(PreferenceConstants.HX_EDITOR_NUMBER_COLOR)));
		
		return rules;
	}
}
