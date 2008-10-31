package eclihx.core.haxe.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.internal.HaxeElementValidator;
import eclihx.core.haxe.model.core.IHaxePackage;
import eclihx.core.haxe.model.core.IHaxeSourceFolder;

/**
 * First implementation of the haXe package element.
 */
public class HaxePackage extends HaxeElement implements IHaxePackage {
	
	/**
	 * The folder resource this package wraps.
	 */
	private final IFolder fFolder; 
	
	/**
	 * The source folder where this package is situated.
	 */
	private final IHaxeSourceFolder fSourceFolder;
	
	/**
	 * The name of the package.
	 */
	private final String name;

	/**
	 * Constructs default package in the selected source folder.
	 * @param parent source folder where this package is situated.
	 */
	public HaxePackage(IHaxeSourceFolder parent) {
		super(parent);
		
		fFolder = parent.getBase();
		fSourceFolder = parent;
		name = DEFAULT_PACKAGE_NAME;
	}
	
	/**
	 * Constructs root package in the selected source folder.
	 * 
	 * @param parent source folder where this package is situated.
	 * @param folder the folder of the package. <b>Note</b> that this folder 
	 * should be a direct sub-folder of the source folder, in otherwise you'll
	 * get an exception.
	 * 
	 * @throws InvalidParameterException if folder parameter is not the direct
	 * child of the parent folder parameter.
	 * 
	 */
	public HaxePackage(IHaxeSourceFolder parent, IFolder folder) {
		super(parent);
		
		if (!folder.getParent().equals(parent.getBase())) {
			throw new InvalidParameterException(
					"Folder parameter should be direct sub-folder of the " +
					"source folder in parent parameter.");
		}
		
		fFolder = folder;
		fSourceFolder = parent;
		name = fFolder.getName();
	}
	
	/**
	 * Constructs sub-package for the give haXe package.
	 * 
	 * @param parent source folder where this package is situated.
	 * @param folder the folder of the package. <b>Note</b> that this folder 
	 * should be a direct sub-folder of the source folder, in otherwise you'll
	 * get an exception.
	 * 
	 * @throws InvalidParameterException if folder parameter is not the direct
	 * child of the parent folder parameter.
	 */
	public HaxePackage(IHaxePackage parent, IFolder folder) {
		super(parent);
		
		if (!folder.getParent().equals(parent.getBase())) {
			throw new InvalidParameterException(
					"Folder parameter should be direct sub-folder of the " +
					"folder of parent package.");
		}
		
		fFolder = folder;
		fSourceFolder = parent.getSourceFolder();
		name = parent.getName() + "." + folder.getName();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxePackage#getBase()
	 */
	@Override
	public IFolder getBase() {
		return fFolder;
	}
	
	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxePackage#getSourceFolder()
	 */
	public IHaxeSourceFolder getSourceFolder() {
		return fSourceFolder;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxePackage#getChildrenPackages()
	 */
	@Override
	public IHaxePackage[] getChildrenPackages() {
		try {
			IResource[] resources = fFolder.members();
			
			ArrayList<IHaxePackage> haxePackages = 
					new ArrayList<IHaxePackage>();
			
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

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxePackage#hasHaxeFile(java.lang.String)
	 */
	@Override
	public boolean hasHaxeFile(String haxeFileName) {
		
		if (HaxeElementValidator.validateHaxeFileName(haxeFileName).isOK()) {
			return fFolder.exists(new Path(haxeFileName));
		}
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxePackage#createHaxeFile(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void createHaxeFile(String haxeFileName, IProgressMonitor monitor)
			throws CoreException {
		
		if (HaxeElementValidator.validateHaxeFileName(haxeFileName).isOK() &&  
				!hasHaxeFile(haxeFileName)) {
				
				InputStream stream = new ByteArrayInputStream(
						createHaxeFileHeader().getBytes());
				
				fFolder.getFile(haxeFileName).create(stream, true, monitor);
			}
	}
	
	/**
	 * Method creates a template haXe file header.
	 */
	protected String createHaxeFileHeader() {
		if (!isDefaultPackage()) {
			return String.format("package %s;", getName());
		}
		
		return "";
	}
	
	/**
	 * Checks if the package is default.
	 * @return <code>true</code> for default package.
	 */
	protected boolean isDefaultPackage() {
		return name.equals(DEFAULT_PACKAGE_NAME);
	}

}
