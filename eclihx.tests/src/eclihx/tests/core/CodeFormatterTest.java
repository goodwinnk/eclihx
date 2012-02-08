/**
 * 
 */
package eclihx.tests.core;

import junit.framework.Assert;

import org.junit.Test;

import eclihx.core.haxe.model.CodeFormatter;

/**
 * Formatter tests.
 */
public class CodeFormatterTest {

	/**
	 * Test method for {@link eclihx.core.haxe.model.CodeFormatter#formatAll(java.lang.String, eclihx.core.haxe.model.CodeFormatter.FormatOptions)}.
	 */
	@Test
	public void testFormat() {
		
		CodeFormatter.FormatOptions options = new CodeFormatter.FormatOptions();
		options.setBracketNewLines(true);
		options.setInsertTabs(false);
		options.setIntendWidth(2);
		
		String str = CodeFormatter.formatAll(
				"class A { public static function (){ if (a==1){}}}", 
				options);
		
		String outStr = "class A\n" +
				        "{\n" +
				        "  public static function ()\n" +
				        "  {\n" +
				        "    if (a==1)\n" +
				        "    {\n" +
				        "    }\n" +
				        "  }\n" +
				        "}";
		
		Assert.assertEquals(outStr, str);		
	}
	
	@Test
	public void formatTwoStatements() {
		CodeFormatter.FormatOptions options = new CodeFormatter.FormatOptions();
		options.setBracketNewLines(false);
		options.setInsertTabs(false);
		options.setIntendWidth(3);
		options.setIndentOnEmptyLines(true);
		
		String str = CodeFormatter.formatAll(
				"class Some {\n" +
		        "function some() {\n" +
				"var a = 1;\n" +
		        "\n" +
				"var b = 2;}}",
				options);
		
		String outStr = 
				"class Some {\n" +
				"   function some() {\n" +
				"      var a = 1;\n" +
				"      \n" +
				"      var b = 2;\n"+
				"   }\n"+
				"}";
		
		Assert.assertEquals(outStr, str);		
	}
	
	@Test
	public void noNewLinesFile() {
		CodeFormatter.FormatOptions options = new CodeFormatter.FormatOptions();
		String str = CodeFormatter.formatAll("test", options);
		Assert.assertEquals("test", str);		
	}
	
	@Test
	public void formatVarTemplate() {
		CodeFormatter.FormatOptions options = new CodeFormatter.FormatOptions();
		options.setBracketNewLines(false);
		options.setInsertTabs(false);
		options.setIntendWidth(2);
		options.setIndentOnEmptyLines(false);
		
		String str = CodeFormatter.formatSelection(
				"var test();\n",
				options,
				"    ");
		
		String outStr = "var test();\n" + 
		                "    ";
		
		Assert.assertEquals(outStr, str);		
	}
}
