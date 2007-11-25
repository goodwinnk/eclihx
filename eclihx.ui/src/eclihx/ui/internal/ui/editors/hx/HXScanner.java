package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;
import org.eclipse.swt.SWT;

import java.util.ArrayList;

public class HXScanner extends RuleBasedScanner {

	/**
	 * Word detector
	 */
	static class WordDetector implements IWordDetector {

		public boolean isWordStart(char c) {
			return Character.isLetter(c);
		}

		public boolean isWordPart(char c) {
			return Character.isLetter(c);
		}
	}
	
	public HXScanner(ColorManager manager) {
		initializeRules(manager);
	}
	
	
	private void initializeRules(ColorManager manager) {
		ArrayList<IRule> rules= createRules(manager);
		if (rules != null) {
			IRule[] result= new IRule[rules.size()];
			rules.toArray(result);
			setRules(result);
		}
	}
	
	
	private ArrayList<IRule> createRules(ColorManager manager) {
		ArrayList<IRule> rules = new ArrayList<IRule>();

		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new HXWhitespaceDetector()));

		// Add rule for haxe keywords
		IToken wordToken = new Token(new TextAttribute(manager.getColor(IHXColorConstants.DEFAULT)));
		WordRule wr = new WordRule(new WordDetector(), wordToken);
		
		IToken keyWordToken = new Token(new TextAttribute(manager.getColor(IHXColorConstants.KEYWORD), null, SWT.BOLD));
		String[] keyWords = {"var", "function", "new", "delete", "cast", "return", "break", "continue",
							 "if", "else", "for", "while", "do", "switch", "case", "with",
							 "try", "catch", "throw"};
		for (int i = 0; i < keyWords.length; i++)
			wr.addWord(keyWords[i], keyWordToken);
		
		String[] constWords = {"null", "undefined", "true", "false"};
		for (int i = 0; i < constWords.length; i++)
			wr.addWord(constWords[i], keyWordToken);
		
		String[] typeWords = {"Void", "Float", "Int"};
		IToken typeWordToken = new Token(new TextAttribute(manager.getColor(IHXColorConstants.TYPE)));
		for (int i = 0; i < typeWords.length; i++)
			wr.addWord(typeWords[i], typeWordToken);

		rules.add(wr);
		
		return rules;
	}
}
