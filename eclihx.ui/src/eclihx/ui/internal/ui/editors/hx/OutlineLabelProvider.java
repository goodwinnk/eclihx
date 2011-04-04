package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.swt.graphics.Image;

import eclihx.core.haxe.internal.outline.Member;
import eclihx.core.haxe.internal.outline.Module;
import eclihx.core.haxe.internal.outline.Type;
import eclihx.ui.PluginImages;

/**
 * Provides labels for haxe outline view.
 */
public class OutlineLabelProvider extends org.eclipse.jface.viewers.LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof Module) {
			return ((Module)element).getFile();
		} else if (element instanceof Type) {
			return ((Type)element).getName();
		} else if (element instanceof Member) {
			return ((Member)element).getName();
		}
		
		return element != null ? element.toString() : "";
	}
	
	@Override
	public Image getImage(Object element) {
		if (element instanceof Module) {
			return PluginImages.get(PluginImages.IMG_PACKAGE);
		} else if (element instanceof Type) {
			return PluginImages.get(PluginImages.IMG_CLASS_PUBLIC);
		} else if (element instanceof Member) {
			Member member = (Member) element;
			return member.isPublic() ? 
					PluginImages.get(PluginImages.IMG_FIELD_PUBLIC) :
					PluginImages.get(PluginImages.IMG_FIELD_PRIVATE);
		}
		
		return super.getImage(element);
	}
	
}
