package eclihx.core.haxe.model;

import java.util.ArrayList;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;

import eclihx.core.haxe.model.core.IHaxeProject;

public class HaxeWorkspace {

	private IWorkspaceRoot fRoot;
	
	public HaxeWorkspace(IWorkspaceRoot root) {
		fRoot = root;
	}
	
	public String[] getHaxeProjectsNames() {
		IProject[] allProjects = fRoot.getProjects();
		ArrayList<String> haxeProjectsStrings = new ArrayList<String>();
		for (IProject project : allProjects) {
			if (HaxeProject.hasHaxeNature(project)) {
				haxeProjectsStrings.add(project.getName());
			}
		}
		
		return (String[])haxeProjectsStrings.toArray(new String[1]);
	}
	
		
	
	/**
	 * Saves haXe projects in workspace 
	 */
	public void close(){
		// store all projects info
		IProject[] allProjects = fRoot.getProjects();
		for (IProject project : allProjects) {
			if (HaxeProject.hasHaxeNature(project)) {
				(new HaxeProject(project)).store();
			}
		}
		
	}
	
	/**
	 * Get haXe project by name.
	 * @param name
	 */
	public IHaxeProject getHaxeProject(String name){
		IProject project= fRoot.getProject(name);
		if (project.exists()) {
			return (new HaxeProject(project));
		}
		return null;
	}
}
