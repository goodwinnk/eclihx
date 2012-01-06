package eclihx.tests.ui.internal.ui.editors.hx;

import org.junit.Test;

public class DocAutoIndentTest extends HXEditorTestBase {
	
	@Test
    public void newMultiLineComment() {
		configureFromText("  \t/*" + CARET + " ");    		
    	type("\n");
    	assertByText("  \t/*\r\n" + 
    			     "  \t * " + CARET + " \r\n" + 
    			     "  \t */");
    }
	
	@Test
    public void newDocComment() {
		configureFromText("  \t/**" + CARET + " ");    		
    	type("\n");
    	assertByText("  \t/**\r\n" + 
    			     "  \t * " + CARET + " \r\n" + 
    			     "  \t */");
    }
	
	@Test
    public void newLineInMultiLineComment() {
		configureFromText("  \t/*\r\n" + 
			              "  \t * " + CARET + "\r\n" + 
			              "  \t */");  		
    	type("\n");
    	assertByText("  \t/*\r\n" + 
    	             "  \t * \r\n" +
    			     "  \t * " + CARET + "\r\n" + 
    			     "  \t */");
    }
	
	@Test
    public void newLineInDocComment() {
		configureFromText("  \t/**\r\n" + 
			              "  \t * " + CARET + "\r\n" + 
			              "  \t */");    		
    	type("\n");
    	assertByText("  \t/**\r\n" + 
	                 "  \t * \r\n" +
			         "  \t * " + CARET + "\r\n" + 
			         "  \t */");
    }
	
	@Test
    public void newLineInFirstLineOfMultiLineComment() {
		configureFromText("  /* aaaa" + CARET + "bbbb\r\n" + 
			              "    * \r\n" + 
			              "   */");  		
    	type("\n");
		configureFromText("  /* aaaa" + CARET + "\r\n" +
				          "   * " + CARET + "bbbb\r\n" + 
	                      "    * \r\n" + 
	                      "   */");  
    }
	
	@Test
    public void newLineInUnfinishedDocComment() {
		configureFromText(" /**\r\n" + 
			              "  * aaa" + CARET + "bbb\r\n" + 
			              "Some text");    		
    	type("\n");
    	assertByText(" /**\r\n" + 
	                 "  * aaa\r\n" + 
	                 "  * " + CARET + "bbb\r\n" + 
	                 "Some text");
    }
	
	
}
