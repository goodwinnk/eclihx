/**
 * 
 */
package eclihx.ui.internal.ui.views;

import static org.junit.Assert.fail;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeWorkspace;

/**
 * @author GoodwinNK
 *
 */
public class HaxeExplorerViewTest {

	
	private final static String VIEW_ID = 
			"eclihx.ui.internal.ui.views.PackageExplorerView";
	
	private HaxeExplorerView explorerView;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		HaxeExplorerView explorerView = (HaxeExplorerView) PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.showView(VIEW_ID);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link eclihx.ui.internal.ui.views.HaxeExplorerView#createPartControl(org.eclipse.swt.widgets.Composite)}.
	 */
	@Test
	@Ignore
	public void testCreatePartControlComposite() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@linb HaxeExplorerView#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent).
	 
	 * @throws CoreException */
	@Test
	public void testEmptyProjectOutputFolder() throws CoreException {
		IHaxeWorkspace workspace = EclihxCore.getDefault().getHaxeWorkspace(); 
		IHaxeProject project = workspace.createHaxeProject("Empty", null);
		
		//IResourceChangeEvent event = new ResourceChangeEvent(null, 0, null);
		
		//explorerView.resourceChanged()
		
	}

}
