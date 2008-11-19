/**
 * 
 */
package eclihx.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.HaxeProject;
import eclihx.core.haxe.model.core.IHaxeElement;
import eclihx.core.haxe.model.core.IHaxePackage;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeSourceFile;
import eclihx.core.haxe.model.core.IHaxeSourceFolder;

/**
 * Here situated some method for working with the navigator selection.
 */
public final class SelectionUtils {
	
	/**
	 * Do not allow to instantiate the objects of this class.
	 */
	private SelectionUtils() {}
	
	/**
	 * Method test selection and returns the IHaxeProject element if it can
	 * cast selection to it.
	 * 
	 * @param selectedElement the element in selection.
	 * 
	 * @return IHaxeProject if casting is success and <code>null</code> 
	 * otherwise.
	 */
	public static IHaxeProject getHaxeProjectFromSelection(
			Object selectedElement) {
		
		IResource resource = null;
		
		if (selectedElement instanceof IResource) {
			resource = ((IResource)selectedElement);
		} else if (selectedElement instanceof IHaxeElement) {
			resource = ((IHaxeElement)selectedElement).getBaseResource(); 
		}
		
		if (resource != null) {
			final IProject project = resource.getProject();
			
			if (HaxeProject.isHaxeProject(project)) {
				return EclihxCore.getDefault().getHaxeWorkspace()
						.getHaxeProject(project.getName());
			}
		}
		
		return null;
	}
	
	/**
	 * Method test selection and returns the IHaxeSourceFolder element if it can
	 * cast selection to it.
	 * 
	 * @param selectedElement the element in selection.
	 * 
	 * @return IHaxeSourceFolder if casting is success and <code>null</code> 
	 * otherwise.
	 */
	public static IHaxeSourceFolder getHaxeSourceFolderFromSelection(
			Object selectedElement) {
		
		IHaxeProject haxeProject = getHaxeProjectFromSelection(selectedElement);
	
		if (haxeProject != null) {
			IHaxeSourceFolder[] folders = haxeProject.getSourceFolders();

			// If there several source folders in the project we don't return
			// any of them.
			return folders.length == 0 ? null : folders[0];
		}
		
		return null;
	}
		
	/**
	 * Method test selection and returns the IHaxePackage element if it can
	 * cast selection to it.
	 * 
	 * @param selectedElement the element in selection.
	 * 
	 * @return IHaxePackage if casting is success and <code>null</code> 
	 * otherwise.
	 */
	public static IHaxePackage getHaxePackageFromSelection(
			Object selectedElement) {
		
		if (selectedElement instanceof IHaxePackage) {
			return (IHaxePackage)selectedElement;
		}
		
		if (selectedElement instanceof IHaxeSourceFile) {
			return ((IHaxeSourceFile)selectedElement).getPackage();
		}
		
		// TODO 4 Add searching for a package for a IResource case.
		
		return null;
	}
	
}
