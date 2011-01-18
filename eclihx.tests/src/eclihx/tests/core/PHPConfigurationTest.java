package eclihx.tests.core;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.haxe.internal.configuration.PHPConfiguration;

/**
 * Test for {@link eclihx.core.haxe.internal.configuration.PHPConfiguration} 
 */
public class PHPConfigurationTest {
	
	private PHPConfiguration config;

	/**
	 * Setting up the test environment.
	 */
	@Before
	public void setUp() {
		config = new PHPConfiguration();
	}

	/**
	 * Test for {@ling PHPConfiguration#printConfiguration()}
	 * @throws InvalidConfigurationException invalid configuration test.
	 */
	@Test
	public void testPrintConfiguration() throws InvalidConfigurationException {
		config.setFrontFile("test.php");
		config.setOutputDirectory("myPHP");
		
		Assert.assertEquals(
			"-php myPHP --php-front test.php ", 
			config.printConfiguration());
	}
	
	
	/**
	 * Test for {@link PHPConfiguration#printConfiguration()}
	 * @throws InvalidConfigurationException invalid configuration test.
	 */
	@Test
	public void testPrintConfigurationCompounds() throws InvalidConfigurationException {
		config.setFrontFile("\\test.php");
		config.setOutputDirectory("..\\hi hello\\myPHP");
		
		Assert.assertEquals(
			"-php \"..\\hi hello\\myPHP\" --php-front \\test.php ", 
			config.printConfiguration());
	}

}
