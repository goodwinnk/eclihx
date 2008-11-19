package eclihx.ui.internal.navigator;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.HaxeProject;
import eclihx.ui.internal.ui.utils.HaxeElementsLabelProvider;

/**
 * haXe elements label provider for the navigator view. 
 */
public class HaxeNavigatorLabelProvider extends HaxeElementsLabelProvider {

	@Override
	public Image getImage(Object element) {
		if (element instanceof IProject) {
			if (HaxeProject.isHaxeProject((IProject)element)) {
				return super.getImage(
						EclihxCore.getDefault().getHaxeWorkspace().getHaxeProject(
									(IProject)element));
			}
		}
		
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IProject) {
			if (HaxeProject.isHaxeProject((IProject)element)) {
				return super.getText(
						EclihxCore.getDefault().getHaxeWorkspace().getHaxeProject(
									(IProject)element));
			}
		}
		return super.getText(element);
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
