/**
 * 
 */
package eclihx.tests.core;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;
import eclihx.core.haxe.internal.configuration.JSConfiguration;

/**
 * Testing of the JS configuration container.
 * {@link eclihx.core.haxe.internal.configuration.JSConfiguration}
 */
public class JSConfigurationTest {
	
	private JSConfiguration config;

	/**
	 * Setting Up
	 * @throws java.lang.Exception exception
	 */
	@Before
	public void setUp() throws Exception {
		config = new JSConfiguration();
	}

	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.JSConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException configuration exception
	 */
	@Test
	public void testPrintConfiguration() 
			throws InvalidConfigurationException {
		config.setOutputFile("hi.my");
		
		Assert.assertEquals("-js hi.my ", config.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.JSConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException configuration exception
	 */
	@Test
	public void testPrintConfigurationCompound() 
			throws InvalidConfigurationException {
		config.setOutputFile("hi hi.my");
		
		Assert.assertEquals("-js \"hi hi.my\" ", config.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.JSConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException configuration exception
	 */
	@Test
	public void shouldPrintNamespace() throws InvalidConfigurationException {
		config.setNamespace("some");
		config.setOutputFile("some.js");
		
		Assert.assertEquals("-js some.js --js-namespace some ", config.printConfiguration());
	}
}
