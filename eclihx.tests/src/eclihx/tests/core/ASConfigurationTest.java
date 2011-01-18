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
	 * Creating action script configuration.
	 */
	@Before
	public void setUp() {
		config = new ASConfiguration();
	}

	/**
	 * {@link eclihx.core.haxe.internal.configuration.ASConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException in the case of invalid configuration.
	 */
	@Test
	public void testOutputDirectoryPrintConfiguration() 
			throws InvalidConfigurationException {
		
		config.setOutputDirectory("Hi");
		Assert.assertEquals("-as3 Hi ", config.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.ASConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException in the case of invalid configuration.
	 */
	@Test
	public void testPrintConfigurationCompoundDirectory() 
			throws InvalidConfigurationException {
		
		config.setOutputDirectory("Hi hello");
		Assert.assertEquals("-as3 \"Hi hello\" ", config.printConfiguration());
	}


}
