package eclihx.ui.internal.ui.editors.hx;

import eclihx.core.haxe.internal.outline.Member;
import eclihx.core.haxe.internal.outline.Module;
import eclihx.core.haxe.internal.outline.Type;

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
	
}
