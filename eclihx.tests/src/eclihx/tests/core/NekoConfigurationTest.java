package eclihx.tests.core;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.haxe.internal.configuration.NekoConfiguration;

/**
 * Test for {@link eclihx.core.haxe.internal.configuration.NekoConfiguration}
 */
public class NekoConfigurationTest {
	
	private NekoConfiguration config;

	/**
	 * Setting up the test environment.
	 */
	@Before
	public void setupTest() {
		config = new NekoConfiguration();
	}

	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.NekoConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException invalid configuration case. 
	 */
	@Test
	public void testPrintConfiguration() throws InvalidConfigurationException {
		config.setOutputFile("test.n");
		config.enableKeepNekoSource();
		
		Assert.assertEquals(
			"-neko test.n --neko-source ", 
			config.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.NekoConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException invalid configuration case.
	 */
	@Test
	public void testPrintConfigurationCompoudFile() throws InvalidConfigurationException {
		config.setOutputFile("c:\\Program files\\test.n");
		
		Assert.assertEquals(
				"-neko \"c:\\Program files\\test.n\" ", 
				config.printConfiguration());
	}

}
