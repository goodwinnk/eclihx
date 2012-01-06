package eclihx.tests.ui.internal.ui.editors.hx;

import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Test;

import eclihx.ui.PreferenceConstants;
import eclihx.ui.internal.ui.EclihxUIPlugin;

public class AutoIndentTest extends HXEditorTestBase {
	
	protected IPreferenceStore getStore() {
		return EclihxUIPlugin.getDefault().getPreferenceStore();
	}
	
	@Test
    public void beforeBraces() {
		getStore().setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INSERT_TABS, false);
		getStore().setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_WIDTH, 4);		
		
   		configureFromText("{" + CARET + "}");    		
    	type("\n");
    	assertByText("{\r\n" + 
    	             "    " + CARET + "\r\n" + 
    			     "}");
    }
	
	@Test
    public void openBraceOnOtherLine() {
		getStore().setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INSERT_TABS, false);
		getStore().setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_WIDTH, 2);
		
   		configureFromText("{\r\n" + 
                          "    " + CARET + "\r\n" + 
                          "}");    		
    	type("\n");
    	
    	// No indentation - copy the default one
    	assertByText("{\r\n" + 
                     "    \r\n" + 
                     "    " + CARET + "\r\n" + 
                     "}");
    }
	
	@Test
    public void onlyOpenBrace() {
		
		getStore().setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INSERT_TABS, false);
		getStore().setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_WIDTH, 3);
		
   		configureFromText("    aaa {" + CARET + "bbb");    		
    	type("\n");
    	assertByText("    aaa {\r\n" +
    	             "       " + CARET + "bbb");
    }
	
	@Test
    public void insertTabs() {
		
		getStore().setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INSERT_TABS, true);
		getStore().setValue(PreferenceConstants.HX_FORMAT_OPTION_PROPERTIES_INDENT_WIDTH, 8);
		
   		configureFromText("    aaa {" + CARET + "bbb");    		
    	type("\n");
    	assertByText("    aaa {\r\n" +
    	             "    \t" + CARET + "bbb");
    }
	
	@Test
    public void simpleLineBreak() {
   		configureFromText("  \taaa" + CARET);    		
    	type("\n");
    	assertByText("  \taaa\r\n" + 
    	             "  \t" + CARET);
    }
	
	@Test
    public void lineBreakInEmpty() {
   		configureFromText(CARET);    		
    	type("\n");
    	assertByText("\r\n" + 
    	             CARET);
    }
}
