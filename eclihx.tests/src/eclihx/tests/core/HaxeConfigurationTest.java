package eclihx.tests.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationOperationException;
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
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		configuration = new HaxeConfiguration();
	}

	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException 
	 */
	@Test
	public void testPrintConfigurationSimple() throws InvalidConfigurationException {
		configuration.enableDebug();
		configuration.setStartupClass("TestWhile");
		
		Assert.assertEquals(
			"-main TestWhile -debug ", configuration.printConfiguration());
	}
	
	/**
	 * Checks printing of the source directories.
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException 
	 */
	@Test
	public void testPrintConfigurationSourceDirectory() 
		throws InvalidConfigurationException {
		
		configuration.enableDebug();
		configuration.setStartupClass("TestWhile");
		configuration.addSourceDirectory("Hello Directory");
		configuration.addSourceDirectory("bin");
		
		Assert.assertEquals(
			"-main TestWhile -cp \"Hello Directory\" -cp bin -debug ", 
			configuration.printConfiguration());
	}
	
	/**
	 * Successful platform setting.
	 * 
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#setPlatform(eclihx.core.haxe.internal.configuration.HaxeConfiguration.Platform)}.
	 */
	@Test
	public void testSetPlatformSuccessful() 
		throws InvalidConfigurationOperationException {
		
		configuration.setExplicitNoOutput();
		configuration.setPlatform(Platform.Flash);
		configuration.setExplicitNoOutput();
	}
	
	/**
	 * This test checks that is't not valid to set output platform for several
	 * times.
	 * 
	 * Test method for {@link eclihx.core.haxe.internal.configuration.HaxeConfiguration#setPlatform(eclihx.core.haxe.internal.configuration.HaxeConfiguration.Platform)}.
	 */
	@Test(expected = InvalidConfigurationOperationException.class)
	public void testSetPlatformMultiFail() 
		throws InvalidConfigurationOperationException {
		
		configuration.setPlatform(Platform.Flash);
		configuration.setExplicitNoOutput();
		configuration.setExplicitNoOutput();
		configuration.setPlatform(Platform.ActionScript);		
	}

	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.AbstractConfiguration#validate()}.
	 */
	@Test
	public void testValidate() {
		// TODO 9 Finish this method
		Assert.assertTrue(configuration.validate());
	}

}
