package eclihx.core.haxe.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import eclihx.core.haxe.model.core.IHaxeProject;

public class HaxeWorkplace {
	
	private IWorkspaceRoot fRoot;
	
	public HaxeWorkplace(IWorkspaceRoot root) {
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
}
