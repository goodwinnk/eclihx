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
 * @author GoodwinNK
 *
 */
public class JSConfigurationTest {
	
	private JSConfiguration config;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		config = new JSConfiguration();
	}

	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.JSConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException 
	 */
	@Test
	public void testPrintConfiguration() 
			throws InvalidConfigurationException {
		config.setOutputFile("hi.my");
		
		Assert.assertEquals("-js hi.my ", config.printConfiguration());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.configuration.JSConfiguration#printConfiguration()}.
	 * @throws InvalidConfigurationException 
	 */
	@Test
	public void testPrintConfigurationCompound() 
			throws InvalidConfigurationException {
		config.setOutputFile("hi hi.my");
		
		Assert.assertEquals("-js \"hi hi.my\" ", config.printConfiguration());
	}

}
