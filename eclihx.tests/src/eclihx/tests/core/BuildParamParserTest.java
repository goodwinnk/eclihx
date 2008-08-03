package eclihx.tests.core;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import eclihx.core.haxe.internal.parser.BuildParamParser;
import eclihx.core.util.console.parser.core.ParseError;


/**
 */
public class BuildParamParserTest {

	private static BuildParamParser parser;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		parser = new BuildParamParser();
	}	

	/**
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parseFile(java.lang.String)}.
	 */
	@Test
	public void testParseFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parse(java.lang.String[])}.
	 */
	@Test
	public void testParseStringArray() {
		fail("Not yet implemented");
	}

	/**
	 * Tests debug configuration 
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parse(java.lang.String)}.
	 */
	@Test
	public void debugConfigurationParse() {
		try {
			parser.parseString("-debug 	-D fdb   -swf Test.swf   -swf-version 9");
		} catch (ParseError e) {
			fail("Parse Exception: " + e.getMessage());
		}
	}
	
	/**
	 * Invalid configuration
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parse(java.lang.String)}.
	 */
	@Test
	public void badConfigurationParse() {
		try {
			parser.parseString("-debug 	-D fdb hahaha  -swf Test.swf   -swf-version 9");
			fail("Configuration is invalid");
		} catch (ParseError e) {
		}
	}
}
