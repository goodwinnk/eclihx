package eclihx.ui.internal.ui.utils;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeSourceFolder;
import eclihx.ui.PluginImages;

/**
 * Get the elements view description.
 * 
 * TODO 6 Make it work not only for the IHaxeProject and IHaxeSourceFolder
 */
public class HaxeElementsLabelProvider extends LabelProvider {
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof IHaxeProject) {
			return ((IHaxeProject)element).getName();
		} else if (element instanceof IHaxeSourceFolder) {
			return ((IHaxeSourceFolder)element).getBase().
					getProjectRelativePath().toString();
		}

		return super.getText(element);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof IHaxeProject) {
			return PluginImages.get(PluginImages.IMG_PROJECT);
		} else if (element instanceof IHaxeSourceFolder) {
			return PluginImages.get(PluginImages.IMG_SOURCE_FOLDER);
		}
		
		return super.getImage(element);
	}
}
