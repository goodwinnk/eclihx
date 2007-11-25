package eclihx.ui.internal.ui.editors.hx;


import org.eclipse.jface.text.rules.*;

public class HXPartitionScanner extends RuleBasedPartitionScanner {
	public final static String HX_MULITILINE_COMMENT = "__hx_multiline_comment";
	public final static String HX_COMMENT = "__hx_comment";

	public HXPartitionScanner() {

		IToken hxMultilineComment = new Token(HX_MULITILINE_COMMENT);
		IToken hxComment = new Token(HX_COMMENT);

		IPredicateRule[] rules = new IPredicateRule[2];

		rules[0] = new MultiLineRule("/*", "*/", hxMultilineComment);
		rules[1] = new SingleLineRule("//", "", hxComment);

		setPredicateRules(rules);
	}
}
