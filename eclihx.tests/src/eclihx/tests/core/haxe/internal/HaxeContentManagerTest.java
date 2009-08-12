package eclihx.tests.core.haxe.internal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eclihx.core.haxe.internal.HaxeContentManager;
import eclihx.core.haxe.model.core.IHaxePackage;
import eclihx.core.haxe.model.core.IHaxeSourceFile;

import org.easymock.EasyMock;

import junit.framework.TestCase;

/**
 * Test for eclihx.core.haxe.internal.HaxeContentManagerTest.
 * @see HaxeContentManagerTest
 */
public class HaxeContentManagerTest extends TestCase {
	
	private IHaxePackage haxePackage;
	private IHaxeSourceFile haxeFile;
	
	@Before
	public void setUp() throws Exception {
		haxePackage = EasyMock.createMock(IHaxePackage.class);
		haxeFile = EasyMock.createMock(IHaxeSourceFile.class);
	}
	
	/**
	 * Implicit default package string option enabled.
	 */
	@Test
	public void testImplicitDefaultPackage() {
		EasyMock.expect(haxeFile.getPackage()).andReturn(haxePackage);
		EasyMock.expect(haxePackage.isDefault()).andReturn(true);
		EasyMock.expect(haxeFile.getName()).andReturn("Test.hx");
				
		EasyMock.replay(haxePackage);
		EasyMock.replay(haxeFile);
		
		HaxeContentManager.getInstance().setEmptyPackagesEnabled(true);
		String generatedContent = HaxeContentManager.getInstance().createHaxeContent(haxeFile);
		Assert.assertEquals("package ;\n\nclass Test {\n\n}", generatedContent);
	}
	
	/**
	 * Empty default package string option enabled.
	 */
	@Test
	public void testEmptyDefaultPackage() {
		EasyMock.expect(haxeFile.getPackage()).andReturn(haxePackage);
		EasyMock.expect(haxePackage.isDefault()).andReturn(true);
		EasyMock.expect(haxeFile.getName()).andReturn("SomeOtherTest.hx");
				
		EasyMock.replay(haxePackage);
		EasyMock.replay(haxeFile);
		
		HaxeContentManager.getInstance().setEmptyPackagesEnabled(false);
		String generatedContent = HaxeContentManager.getInstance().createHaxeContent(haxeFile);
		Assert.assertEquals("class SomeOtherTest {\n\n}", generatedContent);
	}
	
	/**
	 * Non-default package with empty package option disabled.
	 */
	@Test
	public void testNonDefaultPackageEmptyDisabled() {
		EasyMock.expect(haxeFile.getPackage()).andReturn(haxePackage);
		EasyMock.expect(haxePackage.isDefault()).andReturn(false);
		EasyMock.expect(haxePackage.getName()).andReturn("test.nice");
		EasyMock.expect(haxeFile.getName()).andReturn("OtherTest.hx");
				
		EasyMock.replay(haxePackage);
		EasyMock.replay(haxeFile);
		
		HaxeContentManager.getInstance().setEmptyPackagesEnabled(false);
		String generatedContent = HaxeContentManager.getInstance().createHaxeContent(haxeFile);
		Assert.assertEquals("package test.nice;\n\nclass OtherTest {\n\n}", generatedContent);
	}
	
	/**
	 * Non-default package with empty package option enabled
	 */
	@Test
	public void testNonDefaultPackage() {
		EasyMock.expect(haxeFile.getPackage()).andReturn(haxePackage);
		EasyMock.expect(haxePackage.isDefault()).andReturn(false);
		EasyMock.expect(haxePackage.getName()).andReturn("nice.test");
		EasyMock.expect(haxeFile.getName()).andReturn("NiceTest.hx");
				
		EasyMock.replay(haxePackage);
		EasyMock.replay(haxeFile);
		
		HaxeContentManager.getInstance().setEmptyPackagesEnabled(true);
		String generatedContent = HaxeContentManager.getInstance().createHaxeContent(haxeFile);
		Assert.assertEquals("package nice.test;\n\nclass NiceTest {\n\n}", generatedContent);
	}
}
