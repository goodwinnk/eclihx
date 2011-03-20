package eclihx.tests.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eclihx.core.haxe.internal.configuration.FlashConfiguration;
import eclihx.core.haxe.internal.configuration.InvalidConfigurationException;

/**
 * Test for flash configuration class.
 * {@link FlashConfiguration}
 * @author GoodwinNK
 *
 */
public class FlashConfigurationTest {
	
	/**
	 * Test flash configuration.
	 */
	private FlashConfiguration flashConfiguration;
	
	/**
	 * Initialize flash configuration.
	 */
	@Before
	public void setUp() {
		flashConfiguration = new FlashConfiguration();
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldPrintSimpleConfiguration() {
		flashConfiguration.setHeader("testHeader");
		flashConfiguration.setOutputFile("out.swf");
		flashConfiguration.setVersion(7);
		
		try {
			Assert.assertEquals(
				"-swf out.swf -swf-version 7 -swf-header testHeader ", 
				flashConfiguration.printConfiguration()
			);
		} catch (InvalidConfigurationException e) {
			Assert.fail();
		}
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldPrintFilesQuoted() {
		
		flashConfiguration.setOutputFile("a a\\out.swf");
		flashConfiguration.setVersion(8);
		
		try {
			Assert.assertEquals(
				"-swf \"a a\\out.swf\" -swf-version 8 ",
				flashConfiguration.printConfiguration()
			);
		} catch (InvalidConfigurationException e) {
			Assert.fail();
		}
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 * Test libraries with spaces are quoted.
	 * @throws InvalidConfigurationException If test is trying to test an invalid configuration.
	 */
	@Test
	public void shouldPrintQuotedLibs() throws InvalidConfigurationException {
		flashConfiguration.setOutputFile("some.swf");
		flashConfiguration.setVersion(8);
		flashConfiguration.addLibrary("some dir\\swflib.swf");
		
		Assert.assertEquals(
				"-swf some.swf -swf-version 8 -swf-lib \"some dir\\swflib.swf\" ", 
				flashConfiguration.printConfiguration());
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldValidateSuccess() {
		
		flashConfiguration.setHeader(null);
		flashConfiguration.setOutputFile("out.swf");
		flashConfiguration.setVersion(7);
		
		Assert.assertTrue(flashConfiguration.isValid());
	}

	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldFailIfVersionTooSmall() {
		flashConfiguration.setHeader("header");
		flashConfiguration.setOutputFile("out.swf");
		
		// Invalid version.
		flashConfiguration.setVersion(5);
		Assert.assertFalse(flashConfiguration.isValid());
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldFailIfNoOutputFile() {

		// No output file test.
		flashConfiguration.setVersion(7);
		Assert.assertFalse(flashConfiguration.isValid());
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldAcceptVersionAboveSix() {
		flashConfiguration.setVersion(11);
		flashConfiguration.setOutputFile("out.swf");
		Assert.assertTrue(flashConfiguration.isValid());
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldAcceptDoubleVersion() {
		flashConfiguration.setVersion(10.2);
		flashConfiguration.setOutputFile("out.swf");
		Assert.assertTrue(flashConfiguration.isValid());
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldPrintDoubleVersionAsIntIfPossible() {
		flashConfiguration.setOutputFile("some.swf");
		flashConfiguration.setVersion(10);
		
		try {
			Assert.assertEquals("-swf9 some.swf -swf-version 10 ", 
					flashConfiguration.printConfiguration());
		} catch (InvalidConfigurationException e) {
			Assert.fail();
		}
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldPrintDoubleVersionProperly() {
		flashConfiguration.setOutputFile("some.swf");
		flashConfiguration.setVersion(10.2);
		
		try {
			Assert.assertEquals("-swf9 some.swf -swf-version 10.2 ", 
					flashConfiguration.printConfiguration());
		} catch (InvalidConfigurationException e) {
			Assert.fail();
		}
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldUseSwf9ForVersionsAbove9() {
		flashConfiguration.setOutputFile("some.swf");
		flashConfiguration.setVersion(11);
		
		try {
			Assert.assertEquals("-swf9 some.swf -swf-version 11 ", 
					flashConfiguration.printConfiguration());
		} catch (InvalidConfigurationException e) {
			Assert.fail();
		}
	}
	
	/**
	 * Test for {@link FlashConfiguration#printConfiguration()}
	 */
	@Test
	public void shouldUseSwf9OptionAloneIfPossible() {
		flashConfiguration.setOutputFile("some.swf");
		flashConfiguration.setVersion(9);
		
		try {
			Assert.assertEquals("-swf9 some.swf ", 
					flashConfiguration.printConfiguration());
		} catch (InvalidConfigurationException e) {
			Assert.fail();
		}
	}
}
