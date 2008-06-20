package eclihx.ui.internal.ui.editors.hx;


import java.util.ArrayList;

import org.eclipse.jface.text.rules.*;

public class HXPartitionScanner extends RuleBasedPartitionScanner {

	public HXPartitionScanner() {
		IToken hxMultilineComment = new Token(IHXPartitions.HX_MULTI_LINE_COMMENT);
		IToken hxComment = new Token(IHXPartitions.HX_SINGLE_LINE_COMMENT);
		IToken hxPreprocessor = new Token(IHXPartitions.HX_PREPROCESSOR);
		IToken hxDoc = new Token(IHXPartitions.HX_DOC);
		IToken hxString = new Token(IHXPartitions.HX_STRING);

		ArrayList<IRule> rules = new ArrayList<IRule>(2);

		rules.add(new MultiLineRule("/*", "*/", hxMultilineComment));
		rules.add(new EndOfLineRule("//", hxComment));
		rules.add(new EndOfLineRule("#", hxPreprocessor));
		rules.add(new MultiLineRule("/**", "**/", hxDoc));
		rules.add(new SingleLineRule("\"", "\"", hxString, '\\'));

		IPredicateRule[] result= new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
}
