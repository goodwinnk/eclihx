package eclihx.core.haxe.model;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeWorkspace;

/**
 * Class extends original IWorskpaceRoot object with the haXe functionality.
 */
public class HaxeWorkspace extends HaxeElement implements IHaxeWorkspace {

	private final IWorkspaceRoot fRoot;

	/**
	 * Default constructor.
	 * 
	 * @param root original workspace root.
	 */
	public HaxeWorkspace(IWorkspaceRoot root) {
		super(null);
		this.fRoot = root;
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
		return getHaxeProject(project);
	}
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeWorkspace#getHaxeProject(org.eclipse.core.resources.IProject)
	 */
	@Override
	public IHaxeProject getHaxeProject(IProject project) {
		if (project.exists() && HaxeProject.isHaxeProject(project)) {
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
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getBaseResource()
	 */
	@Override
	public IResource getBaseResource() {
		return fRoot;
	}
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getName()
	 */
	@Override
	public String getName() {
		return fRoot.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeWorkspace#createHaxeProject(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IHaxeProject createHaxeProject(
			String projectName, IProgressMonitor monitor)
					throws CoreException {
		
		IProject project = fRoot.getProject(projectName);
		
		project.create(monitor);
		project.open(monitor);
		
		return new HaxeProject(project);
	}


}
