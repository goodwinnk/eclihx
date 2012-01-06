package eclihx.tests.ui.internal.ui.editors.hx;

import org.junit.Ignore;
import org.junit.Test;

public class BracketInserterTest extends HXEditorTestBase {
	
	private static final String[] BRACKET_PAIRS = new String[] { "()", "<>", "[]", "{}", "\"\"" , "''" };
	
    @Test
    public void singleInserterTests() {
    	for (String pair : BRACKET_PAIRS) {
    		configureFromText(CARET); // Empty file
			type(pair.charAt(0)); // Type open bracket char
			assertByText(pair.charAt(0) + CARET + pair.charAt(1)); // Caret inside pair of brackets
		}    	
    }
    
    @Test
    public void doNotInsertCloseTwice() {
    	for (String pair : BRACKET_PAIRS) {
    		configureFromText(pair.charAt(0) + CARET + pair.charAt(1)); // Open bracket + cursor position
			type(pair.charAt(1)); // Type close bracket char
			
			// Caret just miss close character without inserting a new one
			assertByText(pair + CARET);
		}    	
    }       
    
    // TODO: This is a bug. Error behavior when cursor is at the end of partition.
    @Ignore
    @Test
    public void doNotInsertInSingleLineCommentAtEnd() {
    	for (String pair : BRACKET_PAIRS) {
    		configureFromText("// " + CARET);
    		type(pair.charAt(0)); // Type open bracket
			
			// In single line comment there shouldn't auto close bracket insertion
			assertByText("// " + pair.charAt(0) + CARET);
		}    	
    }    
    
    @Test
    public void doNotInsertInSingleLineComment() {
    	for (String pair : BRACKET_PAIRS) {
    		configureFromText("// " + CARET + " ");
    		type(pair.charAt(0)); // Type open bracket
			
			// In single line comment there shouldn't auto close bracket insertion
			assertByText("// " + pair.charAt(0) + CARET + " ");
		}    	
    }   
    
    @Test
    public void doNotInsertInMultiLineComment() {    	
    	
    	for (String pair : BRACKET_PAIRS) {
    		configureFromText("/* " + CARET + " */");
    		type(pair.charAt(0)); // Type open bracket char
			
			assertByText("/* " + pair.charAt(0) + CARET + " */");
		}    	
    }   
    
    @Test
    public void doNotInsertInString() {
    	for (String pair : BRACKET_PAIRS) {
    		configureFromText("\"a" + CARET + "a\"");    		
    		type(pair.charAt(0)); // Type open bracket char	

    		assertByText("\"a" + pair.charAt(0) + CARET + "a\"");
		}    	
    }   
    
}
