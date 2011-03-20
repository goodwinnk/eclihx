package eclihx.tests.core;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import eclihx.core.haxe.internal.configuration.CPPConfiguration;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;

public class CPPConfigurationTest {
	private CPPConfiguration config;

	@Before
	public void setUp() {
		config = new CPPConfiguration();
	}

	@Test
	public void shouldPrintSimpleOutputFolder() throws InvalidConfigurationException {
		config.setOutputDirectory("output");
		Assert.assertEquals("-cpp output ", config.printConfiguration());
	}
	
	@Test
	public void shouldQuoteDirsWithSpaces() throws InvalidConfigurationException {
		config.setOutputDirectory("C:\\My folder\\Test");		
		Assert.assertEquals("-cpp \"C:\\My folder\\Test\" ", config.printConfiguration());
	}
}
