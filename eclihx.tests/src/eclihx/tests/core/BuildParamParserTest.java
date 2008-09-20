package eclihx.tests.core;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.HaxeConfigurationList;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
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
	 * @throws ParseError 
	 * @throws InvalidConfigurationException 
	 */
	@Test
	public void testParseFile() throws ParseError, InvalidConfigurationException {
		String path = 
			System.getProperty("user.dir") + "\\Resources\\test1.hxml";
		HaxeConfigurationList configurationList = parser.parseFile(path);
		HaxeConfiguration configuration = 
			configurationList.getMainConfiguration();
		
		Assert.assertNotNull(configuration);
		Assert.assertEquals("TestWhile", configuration.getStartupClass());
		Assert.assertTrue(configuration.isDebug());
		Assert.assertTrue(configuration.hasCompilationFlags("fdb"));
	}
	
	/**
	 * This test checks that parser can accept files with comments.
	 * @throws ParseError
	 */
	@Test
	public void testParseFileWithComments() 
		throws ParseError {
		
		String path = System.getProperty("user.dir") + 
			"\\Resources\\testComments.hxml";
		parser.parseFile(path);
	}

	/**
	 * Tests debug configuration 
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parse(java.lang.String)}.
	 * @throws ParseError 
	 */
	@Test
	public void debugConfigurationParse() throws ParseError {
		parser.parseString("-debug 	-D fdb -swf Test.swf -swf-version 9");
	}
	
	/**
	 * Invalid configuration.
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parseString(java.lang.String)}.
	 */
	@Test
	public void badConfigurationParse() throws ParseError {
		parser.parseString("-debug -D fdb -swf Test.swf -swf-version 9 TestWhile");
	}
	
	/**
	 * Test --display option.
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parseString(java.lang.String)}.
	 */
	@Test
	public void testDisplayOption() throws ParseError {
		parser.parseString("--display hihihihi@1");
	}
	
	@Test
	public void testParse() throws ParseError, InvalidConfigurationException {
		
		String filePath = String.format(
			"%s\\Resources\\part.hxml", System.getProperty("user.dir")); 
		
		HaxeConfiguration config = 
			parser.parseString(
				String.format(
					"--no-traces --no-output -D fdb -debug %s",
					filePath)).getMainConfiguration();
		
		Assert.assertTrue(config.isDebug());
		Assert.assertTrue(config.hasCompilationFlags("fdb"));
		Assert.assertTrue(config.isNoOutputMode());
		Assert.assertTrue(config.isNoTracesMode());
		
	}
}
