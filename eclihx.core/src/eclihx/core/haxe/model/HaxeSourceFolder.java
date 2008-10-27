package eclihx.core.haxe.model;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.internal.HaxeElementValidator;
import eclihx.core.haxe.model.core.IHaxeElement;
import eclihx.core.haxe.model.core.IHaxePackage;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeSourceFolder;

/**
 * haXe source folder class. This is the basic wrapper for the IFolder.
 */
public final class HaxeSourceFolder implements IHaxeSourceFolder {

	/**
	 * Base folder object.
	 */
	private final IFolder fFolder;
	
	/**
	 * Owner haXe project. 
	 */
	private final IHaxeProject fHaxeProject;
	
	/**
	 * Default constructor.
	 * @param project the haXe owner project.
	 * @param folder the base IFolder object.
	 */
	public HaxeSourceFolder(IHaxeProject project, IFolder folder) {
		fFolder = folder;
		fHaxeProject = project;
	}
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeSourceFolder#getBase()
	 */
	@Override
	public IFolder getBase() {
		return fFolder;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeSourceFolder#getHaxeProject()
	 */
	@Override
	public IHaxeProject getHaxeProject() {
		return fHaxeProject;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getParent()
	 */
	@Override
	public IHaxeElement getParent() {
		return fHaxeProject;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeSourceFolder#hasPackage(java.lang.String)
	 */
	@Override
	public boolean hasPackage(String packageName) {
		
		if (packageName.isEmpty()) {
			// This is the default package and it always exists.
			return true;
		}
		
		if (HaxeElementValidator.validatePackageName(packageName).isOK()) {
			
			String packagePath = packageName.replaceAll("\\.", "\\\\"); 
			
			return fFolder.exists(new Path(packagePath));
		}

		// If package name is invalid
		return false;
	}
	
	/**
	 * Creates new package with the given name.
	 * @param packageName the name of the package.
	 * @param monitor monitor for the operation. <code>null</code> 
	 *        value is allowed.
	 * 
	 * @throws CoreException if there are some errors during folders creation.
	 */
	public void createPackage(
			String packageName, IProgressMonitor monitor) throws CoreException
	{
		String[] paths = packageName.split("\\.");
		
		IContainer container = fFolder;
		
		for(String folderPath : paths)
		{
			IFolder folder = container.getFolder(new Path(folderPath));
			
			if(!folder.exists()) {
				folder.create(true, true, monitor);
			}
			
			container = folder;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getName()
	 */
	@Override
	public String getName() {
		return fFolder.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeSourceFolder#getPackages()
	 */
	@Override
	public IHaxePackage[] getPackages() {
		try {
			IResource[] resources = fFolder.members();
			
			ArrayList<IHaxePackage> haxePackages = 
					new ArrayList<IHaxePackage>();
			
			// Add a default package.
			haxePackages.add(new HaxePackage(this));
			
			for (IResource resource : resources) {
				if (resource.getType() == IResource.FOLDER) {
					IHaxePackage curPackage = new HaxePackage(
							this, (IFolder)resource);
					haxePackages.add(curPackage);
					haxePackages.addAll(
							Arrays.asList(curPackage.getChildrenPackages()));					
				}
			}
			
			return haxePackages.toArray(new IHaxePackage[0]);
			
		} catch (CoreException e) {
			EclihxCore.getLogHelper().logError(e);
		}
		
		return new IHaxePackage[0];
	}
}