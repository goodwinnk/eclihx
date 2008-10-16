/**
 * 
 */
package eclihx.tests.core;

import junit.framework.Assert;

import org.junit.Test;

import eclihx.core.haxe.internal.HaxeElementValidator;

/**
 * Class HaxeElementValidator tests.
 */
public class HaxeElementValidatorTest {

	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validatePackageName(java.lang.String)}.
	 */
	@Test
	public void testValidatePackageNameNull() {
		Assert.assertFalse(
				HaxeElementValidator.validatePackageName(null).isOK());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validatePackageName(java.lang.String)}.
	 */
	@Test
	public void testValidatePackageNameEmpty() {
		Assert.assertFalse(
				HaxeElementValidator.validatePackageName("").isOK());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validatePackageName(java.lang.String)}.
	 */
	@Test
	public void testValidatePackageNameLeadingDot() {
		Assert.assertFalse(
				HaxeElementValidator.validatePackageName(".a.").isOK());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validatePackageName(java.lang.String)}.
	 */
	@Test
	public void testValidatePackageNameDoubleDots() {
		Assert.assertFalse(
				HaxeElementValidator.validatePackageName("a.c..b").isOK());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validatePackageName(java.lang.String)}.
	 */
	@Test
	public void testValidatePackageNameLeadingSpaces() {
		Assert.assertFalse(
				HaxeElementValidator.validatePackageName(" a.c..b ").isOK());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validatePackageName(java.lang.String)}.
	 */
	@Test
	public void testValidatePackageNameMiddleSpace() {
		Assert.assertFalse(
				HaxeElementValidator.validatePackageName("a.c. .b").isOK());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validatePackageName(java.lang.String)}.
	 */
	@Test
	public void testValidatePackageNameLeadingNowLow() {
		Assert.assertFalse(
				HaxeElementValidator.validatePackageName("a.Acc.b").isOK());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validatePackageName(java.lang.String)}.
	 */
	@Test
	public void testValidatePackageNameSuccess() {
		Assert.assertTrue(
				HaxeElementValidator.validatePackageName("my.lib.pack").isOK());		
		
	}

}
