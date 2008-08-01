package eclihx.core.haxe.model;

import java.util.ArrayList;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;

import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeWorkspace;

public class HaxeWorkspace implements IHaxeWorkspace{

	private IWorkspaceRoot fRoot;
	
	public HaxeWorkspace(IWorkspaceRoot root) {
		fRoot = root;
	}

	/* (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeWorkspace#getHaxeProjectsNames()
	 */
	public String[] getHaxeProjectsNames() {
		
		IProject[] allProjects = fRoot.getProjects();
		
		ArrayList<String> haxeProjectsStrings = new ArrayList<String>();
		
		for (IProject project : allProjects) {
			if (HaxeProject.isHaxeProject(project)) {
				haxeProjectsStrings.add(project.getName());
			}
		}
		
		return (String[])haxeProjectsStrings.toArray(new String[1]);
	}
	
		
	/* (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeWorkspace#close()
	 */
	public void close(){
		// store all projects info
		IProject[] allProjects = fRoot.getProjects();
		
		for (IProject project : allProjects) {
			if (HaxeProject.isHaxeProject(project)) {
				(new HaxeProject(project)).store();
			}
		}
		
	}	
	
	/* (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeWorkspace#getHaxeProject(java.lang.String)
	 */
	public IHaxeProject getHaxeProject(String name){
		
		IProject project= fRoot.getProject(name);
		if (project.exists()) {
			return (new HaxeProject(project));
		}
		
		return null;
	}
}
