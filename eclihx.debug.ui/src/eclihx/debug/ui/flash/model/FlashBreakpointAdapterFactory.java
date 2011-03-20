package eclihx.debug.ui.flash.model;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.ui.texteditor.ITextEditor;

public class FlashBreakpointAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof ITextEditor) {
			ITextEditor editorPart = (ITextEditor) adaptableObject;
			IResource resource = (IResource) editorPart.getEditorInput()
					.getAdapter(IResource.class);
			if (resource != null) {
				String extension = resource.getFileExtension();
				// TODO 4 move to core
				if (extension != null && extension.equals("hx")) {
					return new FlashLineBreakpointAdapter();
				}
			}
		}
		return null;
	}

	
	public Class[] getAdapterList() {
		return new Class[]{IToggleBreakpointsTarget.class};
	}

}
