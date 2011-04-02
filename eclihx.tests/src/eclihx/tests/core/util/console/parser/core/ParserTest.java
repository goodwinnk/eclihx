package eclihx.tests.core.util.console.parser.core;

import junit.framework.Assert;

import org.junit.Test;

import eclihx.core.util.console.parser.core.Parser;

public class ParserTest {

	@Test
	public void shouldSplitSimpleStringParams() {
		String[] params = Parser.splitToParams("some test params");
		Assert.assertTrue(params.length == 3);
		Assert.assertEquals("some", params[0]);
		Assert.assertEquals("test", params[1]);
		Assert.assertEquals("params", params[2]);
	}
	
	@Test
	public void shouldSplitParamsWithNumbers() {
		String[] params = Parser.splitToParams("some 12 13.0 14,9");
		Assert.assertTrue(params.length == 4);
		Assert.assertEquals("some", params[0]);
		Assert.assertEquals("12", params[1]);
		Assert.assertEquals("13.0", params[2]);
		Assert.assertEquals("14,9", params[3]);
	}
	
	@Test
	public void shouldSplitWinFileParam() {
		String[] params = Parser.splitToParams("-file F:\\Programs\\haXe\\runtime-EclipseApplication\\..\\Polygonal\\");
		Assert.assertTrue(params.length == 2);
		Assert.assertEquals("-file", params[0]);
		Assert.assertEquals("F:\\Programs\\haXe\\runtime-EclipseApplication\\..\\Polygonal\\", params[1]);
	}
	
	@Test
	public void shouldSplitLinuxStringParams() {
		String[] params = Parser.splitToParams("--hi ../res/res.swf");
		Assert.assertTrue(params.length == 2);
		Assert.assertEquals("--hi", params[0]);
		Assert.assertEquals("../res/res.swf", params[1]);
	}
	
	@Test
	public void shouldSplitQuotedParams() {
		String[] params = Parser.splitToParams("--hi \"../res to /res.swf\"");
		Assert.assertTrue(params.length == 2);
		Assert.assertEquals("--hi", params[0]);
		Assert.assertEquals("../res to /res.swf", params[1]);
	}
	
	@Test
	public void shouldSplitWithManySpaces() {
		String[] params = Parser.splitToParams("--hi   \t\t \"some\"");
		Assert.assertTrue(params.length == 2);
		Assert.assertEquals("--hi", params[0]);
		Assert.assertEquals("some", params[1]);
	}
	
	@Test
	public void shouldSplitWithUnfinishedQuote() {
		String[] params = Parser.splitToParams("--hi   \t\t \"some str");
		Assert.assertTrue(params.length == 2);
		Assert.assertEquals("--hi", params[0]);
		Assert.assertEquals("some str", params[1]);
	}
	
	@Test
	public void shouldSplitMixedParams() {
		String[] params = Parser.splitToParams("--version 10.0 -some hello,hi -file \"../res to /res.swf\" -int -1");
		Assert.assertTrue(params.length == 8);
		Assert.assertEquals("--version", params[0]);
		Assert.assertEquals("10.0", params[1]);
		Assert.assertEquals("-some", params[2]);
		Assert.assertEquals("hello,hi", params[3]);
		Assert.assertEquals("-file", params[4]);
		Assert.assertEquals("../res to /res.swf", params[5]);
		Assert.assertEquals("-int", params[6]);
		Assert.assertEquals("-1", params[7]);
	}
	
	@Test
	public void shouldSplitWithEscapedQuotes() {
		String[] params = Parser.splitToParams("-cmd \"mdkir \\\"my dir\\\"\"");
		Assert.assertTrue(params.length == 2);
		Assert.assertEquals("-cmd", params[0]);
		Assert.assertEquals("mdkir \"my dir\"", params[1]);
	}
	
	@Test
	public void shouldSplitAndIgnoreEscapeWhileNotInSting() {
		String[] params = Parser.splitToParams("-string \\\"ssr");
		Assert.assertTrue(params.length == 2);
		Assert.assertEquals("-string", params[0]);
		Assert.assertEquals("\\\"ssr", params[1]);
	}
	
	public void shouldSplitPathsWithEscapeChar() {
		String[] params = Parser.splitToParams("-file \"\\some\\some dir.test\"");
		Assert.assertTrue(params.length == 2);
		Assert.assertEquals("-file", params[0]);
		Assert.assertEquals("\\some\\some dir.test", params[1]);
	}
}
