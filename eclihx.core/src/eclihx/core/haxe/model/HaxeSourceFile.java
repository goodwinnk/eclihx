package eclihx.core.haxe.model;

import java.security.InvalidParameterException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import eclihx.core.haxe.model.core.IHaxeElement;
import eclihx.core.haxe.model.core.IHaxeSourceFile;

/**
 * Class add additional functions to the IFile resource.
 */
public class HaxeSourceFile extends HaxeElement implements IHaxeSourceFile {

	/**
	 * Resource object for this haXe file.
	 */
	private final IFile file;
	
	/**
	 * 
	 * @param file
	 */
	public HaxeSourceFile(IFile file) {
		super(null);
		
		IProject project = file.getProject();
		if (!HaxeProject.isHaxeProject(project)) {
			throw new InvalidParameterException(
					"Given file should be in the haXe project");
		}
		
		
		
		this.file = file;
		
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getName()
	 */
	@Override
	public String getName() {
		return file.getName();
	}

	@Override
	public IHaxeElement getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
