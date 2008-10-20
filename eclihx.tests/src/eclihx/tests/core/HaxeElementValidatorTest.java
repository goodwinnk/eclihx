/**
 * 
 */
package eclihx.tests.core;

import junit.framework.Assert;

import org.junit.Ignore;
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
	@Ignore
	@Test
	public void testValidateBuildFileNameInvalidChars() {
		Assert.assertFalse(
				HaxeElementValidator.validateBuildFileName("text?.hxml").isOK());		
		
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
	@Ignore
	@Test
	public void testValidateBuildFileNameBackSlash() {
		Assert.assertFalse(
				HaxeElementValidator.validateBuildFileName("hi\\ha.hxml").isOK());		
		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateBuildFileName(String)}.
	 */
	@Ignore
	@Test
	public void testValidateBuildFileNameSlash() {
		Assert.assertFalse(
				HaxeElementValidator.validateBuildFileName("hi/ha.hxml").isOK());		
		
	}
	
	/**
	 * Test method for {@link eclihx.core.haxe.internal.HaxeElementValidator#validateBuildFileName(String)}.
	 */
	@Ignore
	@Test
	public void testValidateBuildFileNameSuccess() {
		Assert.assertTrue(
				HaxeElementValidator.validateBuildFileName("build.hxml").isOK());		
	}
	

}
