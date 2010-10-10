/**
 * 
 */
package eclihx.core.haxe.model;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeWorkspace;

/**
 * A set of tests for {@link HaxeProject} class.
 */
public class HaxeProjectTest {

	/**
	 * Test project.
	 */
	private IHaxeProject testProject;
	
	@Before
	public void setUp() throws Exception {
		IHaxeWorkspace haxeWorkspace = 
				EclihxCore.getDefault().getHaxeWorkspace();
		
		testProject = haxeWorkspace.createHaxeProject("TestProject", null);
	}

	@After
	public void tearDown() throws Exception {
		testProject.getProjectBase().delete(true, null);
	}

	/**
	 * Test method for {@link eclihx.core.haxe.model.HaxeProject#createBuildFile(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)}.
	 */
	@Test
	@Ignore
	public void testCreateBuildFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link eclihx.core.haxe.model.HaxeProject#createOutputFolder(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)}.
	 */
	@Test
	@Ignore
	public void testCreateOutputFolder() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link eclihx.core.haxe.model.HaxeProject#createSourceFolder(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)}.
	 */
	@Test
	@Ignore
	public void testCreateSourceFolder() {
		fail("Not yet implemented");
	}

}
