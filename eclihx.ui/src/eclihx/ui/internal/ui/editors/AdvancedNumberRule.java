package eclihx.ui.internal.ui.editors;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * Founds not only integer numbers but also hex and float numbers
 * Examples:
 * 		0; // Int
 *    134; // Int
 * 0xFF00; // Int
 *  123.0; // Float
 * .14179; // Float
 *  13e50; // Float
 *  1e-99; // Float
 */
public class AdvancedNumberRule implements IRule {
	
	RegexRule regexRule;

	public AdvancedNumberRule(IToken token) {

		CharSetWordDetector numberDetector = new CharSetWordDetector(
			new char[]{
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'.', 'e', 'E', '+', '-', 
				'x', 'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F'
			}
		);
			
		regexRule = new RegexRule(numberDetector, Token.UNDEFINED);
		
		// Simple int: ['0'-'9']+
		regexRule.addRegex("^\\d+$", token);
		
		// Hex number: "0x" ['0'-'9' 'a'-'f' 'A'-'F']+
		regexRule.addRegex("^0x[0-9a-fA-F]+$", token);
		
		// Standard float: ['0'-'9']+ '.' ['0'-'9']*
		regexRule.addRegex("^\\d+\\.\\d*$", token);
		
		// Restricted float: '.' ['0'-'9']+
		regexRule.addRegex("^\\.\\d+$", token);
		
		/* Advanced float: 
		 * ['0'-'9']+ ['e' 'E'] ['+' '-']? ['0'-'9']+
		 * ['0'-'9']+ '.' ['0'-'9']* ['e' 'E'] ['+' '-']? ['0'-'9']+
		 */
		regexRule.addRegex( "^\\d+(\\.\\d*)?[eE][+-]?\\d+$", token);	
	}
	

	public IToken evaluate(ICharacterScanner scanner) {
		return regexRule.evaluate(scanner);
	}
}
