/**
 * 
 */
package eclihx.tests.core;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import eclihx.core.haxe.internal.configuration.ASConfiguration;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;

/**
 * Tests for {@link eclihx.core.haxe.internal.configuration.ASConfiguration}.
 */
public class ASConfigurationTest {
	
	private ASConfiguration config; 

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		config = new ASConfiguration();
	}

	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.ASConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException 
	 */
	@Test
	public void testPrintConfiguration() 
			throws InvalidConfigurationException {
		config.setOutputDirectory("Hi");
		Assert.assertEquals("-as3 Hi ", config.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.ASConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException 
	 */
	@Test
	public void testPrintConfigurationCompoundDirectory() 
			throws InvalidConfigurationException {
		config.setOutputDirectory("Hi hello");
		Assert.assertEquals("-as3 \"Hi hello\" ", config.printConfiguration());
	}


}
