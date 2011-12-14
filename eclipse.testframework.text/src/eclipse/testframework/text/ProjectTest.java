package eclipse.testframework.text;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;

public abstract class ProjectTest<TProject> {

	protected abstract void createProject() throws CoreException;
	protected abstract IProject getResourceProject();
	protected abstract TProject getProject();
	
	@Before
	public void setUp() throws Exception {
		createProject();
	}

	@After
	public void tearDown () throws Exception {
		if (getProject() != null)
			ProjectResourceHelper.delete(getResourceProject());
	}
}
