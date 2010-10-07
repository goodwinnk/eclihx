package eclihx.tests.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.eclipse.core.runtime.FileLocator;
import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.HaxeConfigurationList;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.haxe.internal.configuration.HaxeConfiguration.Platform;
import eclihx.core.haxe.internal.parser.BuildParamParser;
import eclihx.core.util.console.parser.core.ParseError;


/**
 * Parser tests.
 * {@link eclihx.core.haxe.internal.parser.BuildParamParser}
 */
public class BuildParamParserTest {

	private static BuildParamParser parser;
	
	/**
	 * Creating instance of the parser.
	 */
	@Before
	public void testSetup() {
		parser = new BuildParamParser();
	}	

	/**
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parseFile(java.lang.String)}.
	 * @throws ParseError errors in parsing. 
	 * @throws InvalidConfigurationException errors in configuration.
	 * @throws IOException test bug: file can't be read
	 * @throws MalformedURLException test bug: url cannot be resolved  
	 */
	@Test
	public void testParseFile() throws ParseError, InvalidConfigurationException, MalformedURLException, IOException {
		String filePath = 
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/test1.hxml")).getPath();
		String directoryPath =
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/")).getPath();
		
		HaxeConfigurationList configurationList = parser.parseFile(filePath, directoryPath);
		HaxeConfiguration configuration = 
			configurationList.getMainConfiguration();
		
		Assert.assertNotNull(configuration);
		Assert.assertEquals("TestWhile", configuration.getStartupClass());
		Assert.assertTrue(configuration.isDebug());
		Assert.assertTrue(configuration.hasCompilationFlags("fdb"));
	}
	
	/**
	 * This test checks that parser can accept files with comments.
	 * @throws ParseError errors in parsing.
	 * @throws IOException test bug: file can't be read
	 * @throws MalformedURLException test bug: url cannot be resolved  
	 */
	@Test
	public void testParseFileWithComments() 
		throws ParseError, MalformedURLException, IOException {
		
		String path = 
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/testComments.hxml")).getPath();
		
		String directoryPath =
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/")).getPath();
		
		parser.parseFile(path, directoryPath);
	}
	
	/**
	 * Test file with next separator.
	 * @throws ParseError errors in parsing.
	 * @throws IOException test bug: file can't be read
	 * @throws MalformedURLException test bug: url cannot be resolved  
	 */
	@Test
	public void testParseFileWithNext() 
		throws ParseError, MalformedURLException, IOException {
		
		String path = 
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/withNext.hxml")).getPath();
		
		String directoryPath =
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/")).getPath();

		parser.parseFile(path, directoryPath);
	}

	/**
	 * Tests debug configuration 
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parseString(java.lang.String)}.
	 * @throws ParseError errors in parsing.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void debugConfigurationParse() throws ParseError, MalformedURLException, IOException {
		String directoryPath =
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/")).getPath();
		
		parser.parseString("-debug 	-D fdb -swf Test.swf -swf-version 9", directoryPath);
	}
	
	/**
	 * Invalid configuration.
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parseString(java.lang.String)}.
	 * @throws ParseError errors in parsing.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void badConfigurationParse() throws ParseError, MalformedURLException, IOException {
		String directoryPath =
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/")).getPath();
		
		parser.parseString("-debug -D fdb -swf Test.swf -swf-version 9 TestWhile", directoryPath);
	}
	
	/**
	 * Test --display option.
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parseString(java.lang.String)}.
	 * @throws ParseError errors in configuration.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testDisplayOption() throws ParseError, MalformedURLException, IOException {		
		String directoryPath =
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/")).getPath();
		
		parser.parseString("--display hihihihi@1", directoryPath);
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parseString(java.lang.String)}.
	 * @throws ParseError errors in parsing.
	 * @throws InvalidConfigurationException errors in configuration.
	 * @throws IOException test bug: file can't be read
	 * @throws MalformedURLException test bug: url cannot be resolved  
	 */
	@Test
	public void testParse() throws ParseError, InvalidConfigurationException, MalformedURLException, IOException {
		
		String path = 
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/part.hxml")).getPath();
		
		String directoryPath =
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/")).getPath();
		
		HaxeConfiguration config = 
			parser.parseString(
				String.format(
					"--no-traces --no-output -D fdb -debug %s",
					path), directoryPath).getMainConfiguration();
		
		Assert.assertTrue(config.isDebug());
		Assert.assertTrue(config.hasCompilationFlags("fdb"));
		Assert.assertTrue(config.isNoOutputMode());
		Assert.assertTrue(config.isNoTracesMode());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.parser.BuildParamParser#parseString(java.lang.String)}.
	 * Test is't ok to have a main class without -main option
	 * 
	 * @throws ParseError errors in parsing.
	 * @throws InvalidConfigurationException errors in configuration.
	 * @throws IOException test bug: file can't be read
	 * @throws MalformedURLException test bug: url cannot be resolved  
	 */
	@Test
	public void testSeparateMain() throws ParseError, InvalidConfigurationException, MalformedURLException, IOException
	{
		String path = 
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/separateMain.hxml")).getPath();
		
		String directoryPath =
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/")).getPath();
		
		HaxeConfiguration config = parser.parseFile(path, directoryPath).getMainConfiguration();
		
		Assert.assertTrue(config.getStartupClass().equals("TestCode"));
	}
	
	/**
	 * Test internal configuration file
	 * 
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws InvalidConfigurationException
	 * @throws ParseError
	 */
	@Test
	public void testInternalBuildFile() throws MalformedURLException, IOException, InvalidConfigurationException, ParseError
	{
		String path = 
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/internalParent.hxml")).getPath();
		
		String directoryPath =
			FileLocator.toFileURL(new URL("platform:/plugin/eclihx.tests/Resources/")).getPath();
		
		HaxeConfiguration config = parser.parseFile(path, directoryPath).getMainConfiguration();
		
		Assert.assertTrue(config.isDebug());
		Assert.assertTrue(config.getPlatform() == Platform.Flash);		
	}
}
