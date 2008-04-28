package eclihx.debug.flash;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.LineBreakpoint;

public class FlashBreakpoint extends LineBreakpoint {

	public FlashBreakpoint(IResource resource, int lineNumber) throws CoreException {
		// TODO 7 finish this method
		IMarker marker = resource.createMarker("eclihx.debug.flash.FlashBreakpointMarker");
		setMarker(marker);
		setEnabled(true);
		ensureMarker().setAttribute(IMarker.LINE_NUMBER, lineNumber);
		ensureMarker().setAttribute(IBreakpoint.ID, getModelIdentifier());
	}
	
	@Override
	public String getModelIdentifier() {
		return FlashConstants.ID_FLASH_DEBUG_MODEL;
	}

}
