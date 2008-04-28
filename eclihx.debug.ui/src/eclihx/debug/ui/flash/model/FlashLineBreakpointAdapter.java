package eclihx.debug.ui.flash.model;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

import eclihx.debug.flash.FlashBreakpoint;
import eclihx.debug.flash.FlashConstants;

public class FlashLineBreakpointAdapter implements IToggleBreakpointsTarget {

	@Override
	public boolean canToggleLineBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		
		return getEditor(part) != null;
		
	}

	@Override
	public boolean canToggleMethodBreakpoints(IWorkbenchPart part,
			ISelection selection) {
		return false;
	}

	@Override
	public boolean canToggleWatchpoints(IWorkbenchPart part,
			ISelection selection) {
		return false;
	}

	@Override
	public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {
		
		ITextEditor textEditor = getEditor(part);
		
		if (textEditor != null) {
			IResource resource = (IResource) textEditor.getEditorInput().getAdapter(IResource.class);
			ITextSelection textSelection = (ITextSelection) selection;
			int lineNumber = textSelection.getStartLine();
			IBreakpoint[] breakpoints = DebugPlugin.getDefault()
					.getBreakpointManager().getBreakpoints(FlashConstants.ID_FLASH_DEBUG_MODEL);
			for (int i = 0; i < breakpoints.length; i++) {
				IBreakpoint breakpoint = breakpoints[i];
				if (resource.equals(breakpoint.getMarker().getResource())) {
					if (((ILineBreakpoint) breakpoint).getLineNumber() == (lineNumber + 1)) {
						breakpoint.delete();
						return;
					}
				}
			}
			FlashBreakpoint lineBreakpoint = new FlashBreakpoint(resource, lineNumber + 1);
			DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(lineBreakpoint);
		}
	}

	@Override
	public void toggleMethodBreakpoints(IWorkbenchPart part,
			ISelection selection) throws CoreException {
	}

	@Override
	public void toggleWatchpoints(IWorkbenchPart part, ISelection selection)
			throws CoreException {
	}

	/**
	 * Returns the editor being used to edit a haXe file, associated with the
	 * given part, or <code>null</code> if none.
	 *  
	 * @param part workbench part
	 * @return the editor being used to edit a haXe file, associated with the
	 * given part, or <code>null</code> if none
	 */
	private ITextEditor getEditor(IWorkbenchPart part) {
		if (part instanceof ITextEditor) {
			ITextEditor editorPart = (ITextEditor) part;
			IResource resource = (IResource) editorPart.getEditorInput().getAdapter(IResource.class);
			if (resource != null) {
				String extension = resource.getFileExtension();
				if (extension != null && extension.equals("hx")) {
					return editorPart;
				}
			}
		}
		return null;		
	}
}
