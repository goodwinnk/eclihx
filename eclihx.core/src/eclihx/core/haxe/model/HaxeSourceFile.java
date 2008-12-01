package eclihx.core.haxe.model;

import java.security.InvalidParameterException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;

import eclihx.core.haxe.internal.HaxeElementValidator;
import eclihx.core.haxe.internal.HaxePreferencesManager;
import eclihx.core.haxe.model.core.IHaxePackage;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeSourceFile;

/**
 * Class add additional functions to the IFile resource.
 */
public class HaxeSourceFile extends HaxeElement implements IHaxeSourceFile {

	/**
	 * Resource object for this haXe file.
	 */
	private final IFile fFile;

	/**
	 * Package of this file.
	 */
	private final IHaxePackage fPackage;

	/**
	 * Construct the haXe source file object on the base of the IFile resource.
	 * Resource should have a valid haXe file name.
	 * 
	 * @param file the original resource file.
	 * @param haxePackage haXe package where this file is situated.
	 */
	public HaxeSourceFile(IFile file, IHaxePackage haxePackage) {
		super(haxePackage);

		if (!HaxeElementValidator.validateHaxeFileName(file.getName()).isOK()) {
			throw new InvalidParameterException("File has invlid name.");
		}

		if (!HaxeProject.isHaxeProject(file.getProject())) {
			throw new InvalidParameterException(
					"Given file should be in the haXe project");
		}

		if (!file.getParent().equals(haxePackage.getBaseFolder())) {
			throw new InvalidParameterException(
					"Given file doesn't lay in the given package.");
		}

		this.fPackage = haxePackage;
		this.fFile = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getName()
	 */
	@Override
	public String getName() {
		return fFile.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eclihx.core.haxe.model.core.IHaxeSourceFile#getBase()
	 */
	@Override
	public IFile getBaseFile() {
		return fFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getBaseResource()
	 */
	@Override
	public IResource getBaseResource() {
		return getBaseFile();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eclihx.core.haxe.model.core.IHaxeSourceFile#getPackage()
	 */
	@Override
	public IHaxePackage getPackage() {
		return fPackage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eclihx.core.haxe.model.core.IHaxeSourceFile#getHaxeProject()
	 */
	@Override
	public IHaxeProject getHaxeProject() {
		return fPackage.getSourceFolder().getHaxeProject();
	}
	
	/**
	 * Get the default haXe class name for this file but without
	 * package prefix.
	 * 
	 * @return the string with the class name.
	 */
	private String getClassName() {
		String name = getName();
		
		int lastNameIndex = 
			name.length() - 
				HaxePreferencesManager.HAXE_FILE_EXTENSION.length() - 1;
		
		return name.substring(0, lastNameIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eclihx.core.haxe.model.core.IHaxeSourceFile#getDefaultClassName()
	 */
	@Override
	public String getDefaultClassName() {
		String packagePrefix = ""; // Prefix for default package.

		if (!fPackage.isDefault()) {
			packagePrefix = fPackage.getName() + ".";
		}
		
		return packagePrefix + getClassName();
	}

}
