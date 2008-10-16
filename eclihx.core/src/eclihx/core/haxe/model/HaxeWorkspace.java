package eclihx.core.haxe.model;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;

import eclihx.core.haxe.model.core.IHaxeElement;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeWorkspace;

/**
 * Class extends original IWorskpaceRoot object with the haXe functionality.
 */
public class HaxeWorkspace implements IHaxeWorkspace{

	private final IWorkspaceRoot fRoot;

	/**
	 * Default constructor.
	 * 
	 * @param root original workspace root.
	 */
	public HaxeWorkspace(IWorkspaceRoot root) {
		fRoot = root;
	}

	/* (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeWorkspace#getHaxeProjectsNames()
	 */
	public String[] getHaxeProjectsNames() {
		
		ArrayList<String> haxeProjectsStrings = new ArrayList<String>();
		
		for (IHaxeProject project : getHaxeProjects()) {
			haxeProjectsStrings.add(project.getName());
		}
		
		return haxeProjectsStrings.toArray(new String[0]);
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

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeWorkspace#getHaxeProjects()
	 */
	@Override
	public IHaxeProject[] getHaxeProjects() {
		IProject[] allProjects = fRoot.getProjects();
		
		ArrayList<IHaxeProject> haxeProjects = new ArrayList<IHaxeProject>();
		
		for (IProject project : allProjects) {
			if (HaxeProject.isHaxeProject(project)) {
				haxeProjects.add(new HaxeProject(project));
			}
		}
		
		return haxeProjects.toArray(new IHaxeProject[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getParent()
	 */
	@Override
	public IHaxeElement getParent() {
		// HaxeWorkspace doesn't have a parent.
		return null;
	}
	
	
}
