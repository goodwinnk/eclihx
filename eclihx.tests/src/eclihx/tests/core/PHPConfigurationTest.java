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

	@Before
	public void setUp() throws Exception {
		config = new PHPConfiguration();
	}

	@Test
	public void testPrintConfiguration() throws InvalidConfigurationException {
		config.setFrontFile("test.php");
		config.setOutputDirectory("myPHP");
		
		Assert.assertEquals(
			"-php myPHP --php-front test.php ", 
			config.printConfiguration());
	}
	
	@Test
	public void testPrintConfigurationCompounds() throws InvalidConfigurationException {
		config.setFrontFile("\\test.php");
		config.setOutputDirectory("..\\hi hello\\myPHP");
		
		Assert.assertEquals(
			"-php \"..\\hi hello\\myPHP\" --php-front \\test.php ", 
			config.printConfiguration());
	}

}
