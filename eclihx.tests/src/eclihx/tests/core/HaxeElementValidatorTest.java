/**
 * 
 */
package eclihx.tests.core;

import java.io.File;

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
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateBuildFileName(String)}.
	 */
	@Test
	public void testValidateBuildFileNameNull() {
		Assert.assertFalse(
				HaxeElementValidator.validateBuildFileName(null).isOK());		
		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateBuildFileName(String)}.
	 */
	@Test
	public void testValidateBuildFileNameEmpty() {
		Assert.assertFalse(
				HaxeElementValidator.validateBuildFileName("").isOK());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateBuildFileName(String)}.
	 */
	@Test
	public void testValidateBuildFileNameInvalidChars() {
		// TODO 2: add specific test for different OS systems 
		Assert.assertFalse(HaxeElementValidator.validateBuildFileName("text/.hxml").isOK());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateBuildFileName(String)}.
	 */
	@Test
	public void testValidateBuildFileNameInvalidExtension() {
		Assert.assertFalse(
				HaxeElementValidator.validateBuildFileName("ha.txt").isOK());		
		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateBuildFileName(String)}.
	 */
	@Test
	public void testValidateBuildFileNameBackSlash() {
		Assert.assertFalse(
				HaxeElementValidator.validateBuildFileName(
						"hi" + File.separatorChar + "a.hxml").isOK());
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateBuildFileName(String)}.
	 */
	@Test
	public void testValidateBuildFileNameSlash() {
		Assert.assertFalse(
				HaxeElementValidator.validateBuildFileName(
						"hi/ha.hxml").isOK());		
		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateHaxeFileName(String)}.
	 */
	@Test
	public void testValidateHaxeFileNameSuccess() {
		Assert.assertTrue(HaxeElementValidator.validateHaxeFileName("Test.hx").isOK());		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateHaxeFileName(String)}.
	 */
	@Test
	public void testValidateHaxeFileNameLowFirstLetter() {
		Assert.assertFalse(HaxeElementValidator.validateBuildFileName("some.hx").isOK());		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateHaxeFileName(String)}.
	 */
	@Test
	public void testValidateHaxeFileNameEmpty() {
		Assert.assertFalse(HaxeElementValidator.validateHaxeFileName("").isOK());		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateHaxeFileName(String)}.
	 */
	@Test
	public void testValidateHaxeFileNameEmptyClassName() {
		Assert.assertFalse(HaxeElementValidator.validateHaxeFileName(".hx").isOK());		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateHaxeFileName(String)}.
	 */
	@Test
	public void testValidateHaxeFileNameSpaces() {
		Assert.assertFalse(HaxeElementValidator.validateHaxeFileName("Bad name.hx").isOK());		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateHaxeFileName(String)}.
	 */
	@Test
	public void testValidateHaxeFileNameInvalidExtension() {
		Assert.assertFalse(HaxeElementValidator.validateHaxeFileName("SomeOther.txt").isOK());		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateHaxeFileName(String)}.
	 */
	@Test
	public void testValidateHaxeFileNameNoExtension() {
		Assert.assertFalse(HaxeElementValidator.validateHaxeFileName("Test").isOK());		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateHaxeFileName(String)}.
	 */
	@Test
	public void testValidateHaxeFileNameStartWithNumber() {
		Assert.assertFalse(HaxeElementValidator.validateHaxeFileName("4Build.hx").isOK());		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateHaxeFileName(String)}.
	 */
	@Test
	public void testValidateHaxeFileNameStartWithUnderscore() {
		Assert.assertFalse(HaxeElementValidator.validateHaxeFileName("_Build.hx").isOK());		
	}

}
