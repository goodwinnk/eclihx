package eclihx.tests.core;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.IConfiguration;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.haxe.internal.configuration.HaxeConfiguration.Platform;

/**
 * Test for eclihx.core.haxe.internal.configuration.HaxeConfiguration.	
 */
public class HaxeConfigurationTest {

	/**
	 * Field with the test configuration.
	 */
	private HaxeConfiguration configuration;
	
	/**
	 * Setting up.
	 * @throws java.lang.Exception exception.
	 */
	@Before
	public void setUp() throws Exception {
		configuration = new HaxeConfiguration();
	}

	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException If configuration is invalid.
	 */
	@Test
	public void testPrintConfigurationSimple() 
		throws InvalidConfigurationException {
		
		configuration.enableDebug();
		configuration.setStartupClass("TestWhile");
		
		Assert.assertEquals(
			"-main TestWhile -debug ", configuration.printConfiguration());
	
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException If configuration is invalid.
	 */
	@Test
	public void PrintConfigurationTips() throws InvalidConfigurationException {
		
		configuration.enableTips("Test.hx", 15);
		configuration.addClassName("Test");
		configuration.addSourceDirectory("..\\src");
		
		Assert.assertEquals(
			"-cp ..\\src --display Test.hx@15 --no-output Test ",
			configuration.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * Test configuration printing if file path has spaces.
	 * @throws InvalidConfigurationException if configuration is invalid.
	 */
	@Test
	public void testPrintConfigureationTipsWithSpaces() throws InvalidConfigurationException {
		configuration.enableTips("\\Project name\\src\\Test.hx", 15);
		configuration.addClassName("Test");
		configuration.addSourceDirectory("\\Project name\\src");
		
		Assert.assertEquals(
			"-cp \"\\Project name\\src\" --display \"\\Project name\\src\\Test.hx\"@15 --no-output Test ",
			configuration.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * Test configuration printing for quoting resource files with spaces.
	 * @throws InvalidConfigurationException if configuration is invalid.
	 */
	@Test
	public void testPrintConfigurationResourceQuoted() throws InvalidConfigurationException {
		configuration.setExplicitNoOutput();
		configuration.addResource("some resource.txt");
		
		Assert.assertEquals(
			"--no-output -resource \"some resource.txt\" ",
			configuration.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * Test configuration printing for quoting exclude files with spaces.
	 * @throws InvalidConfigurationException if configuration is invalid.
	 */
	@Test
	public void testPrintConfigurationExcludeFileQuoted() throws InvalidConfigurationException {
		configuration.setExplicitNoOutput();
		configuration.addExcludeFile("\\Long path\\some.hx");
		
		Assert.assertEquals(
			"--no-output -exclude \"\\Long path\\some.hx\" ",
			configuration.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * Test configuration printing for quoting output xml-file with spaces.
	 * @throws InvalidConfigurationException if configuration is invalid.
	 */
	@Test
	public void testPrintConfigurationOutXmlQuoted() throws InvalidConfigurationException {
		configuration.setExplicitNoOutput();
		configuration.setXmlDescriptionFile("\\some path\\my out.xml");
		
		Assert.assertEquals(
			"--no-output -xml \"\\some path\\my out.xml\" ",
			configuration.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * Test configuration printing for quoting the folder for swf headers.
	 * @throws InvalidConfigurationException if configuration is invalid.
	 */
	@Test
	public void testPrintConfigurationHeaderFolderQuoted() throws InvalidConfigurationException {
		configuration.setExplicitNoOutput();
		configuration.setSwfFileForHeaders("\\some path\\some folder");
		
		Assert.assertEquals(
			"--no-output --gen-hx-classes \"\\some path\\some folder\" ",
			configuration.printConfiguration());
	}
	
	
	/**
	 * Checks printing of the source directories.
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException If configuration is invalid.
	 */
	@Test
	public void testPrintConfigurationSourceDirectory() throws InvalidConfigurationException {
		
		configuration.enableDebug();
		configuration.setStartupClass("TestWhile");
		configuration.addSourceDirectory("Hello Directory");
		configuration.addSourceDirectory("bin");
		
		Assert.assertEquals(
			"-main TestWhile -cp \"Hello Directory\" -cp bin -debug ", 
			configuration.printConfiguration());
	}
	
	/**
	 * Checks printing of the js configuration.
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException invalid setting of the configuration. 
	 */
	@Test
	public void testPrintConfigurationJS() throws InvalidConfigurationException {

		configuration.setStartupClass("TestJs");
		configuration.setPlatform(Platform.JavaScript);
		configuration.getJSConfig().setOutputFile("output.js");
		
		Assert.assertEquals(
			"-main TestJs -js output.js ", 
			configuration.printConfiguration());
	}
	
	/**
	 * Successful platform setting.
	 * 
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#setPlatform(eclihx.core.haxe.internal.configuration.HaxeConfiguration.Platform)}.
	 */
	@Test
	public void testSetPlatformSuccessful() {
		
		configuration.setExplicitNoOutput();
		configuration.setPlatform(Platform.Flash);
		configuration.setExplicitNoOutput();
		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.AbstractConfiguration#validate()}.
	 */
	@Test
	public void testValidate() {
		ArrayList<String> errors = configuration.validate();
		
		Assert.assertTrue(!configuration.isValid());
		Assert.assertTrue(errors.size() == 1);
		Assert.assertTrue(errors.get(0).equals(IConfiguration.EMPTY_CONFIGURATION_ERROR));
	}
	
	@Test
	public void shouldPrintHelpConfiguration() throws InvalidConfigurationException {
		configuration.enableHelp();
		Assert.assertEquals("-help ", configuration.printConfiguration());
	}
	
	@Test
	public void shouldPrintClassesTipsConfiguration() throws InvalidConfigurationException {
		configuration.enableClassTips();
		Assert.assertEquals("--display classes ", configuration.printConfiguration());
	}
	
	@Test
	public void shouldPrintKeywordTipsConfiguration() throws InvalidConfigurationException {
		configuration.enableKeywordTips();
		Assert.assertEquals("--display keywords ", configuration.printConfiguration());
	}
}
