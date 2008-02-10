package eclihx.core.haxe.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class HaxeProjectNature implements IProjectNature{
	
	private IProject fProject;

	public void configure() throws CoreException {
		// TODO Auto-generated method stub
		
	}

	public void deconfigure() throws CoreException {
		// TODO Auto-generated method stub
		
	}

	public IProject getProject() {
		// TODO Auto-generated method stub
		return fProject;
	}

	public void setProject(IProject project) {
		// TODO Auto-generated method stub
		fProject = project;
	}
}
