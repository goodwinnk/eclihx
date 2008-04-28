package eclihx.debug.flash;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.IBreakpointListener;
import org.eclipse.debug.core.IBreakpointManager;
import org.eclipse.debug.core.IBreakpointManagerListener;
import org.eclipse.debug.core.IBreakpointsListener;
import org.eclipse.debug.core.model.IBreakpoint;

public class FlashBreakpointManager implements IBreakpointManager {

	@Override
	public void addBreakpoint(IBreakpoint breakpoint) throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addBreakpointListener(IBreakpointListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addBreakpointListener(IBreakpointsListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addBreakpointManagerListener(IBreakpointManagerListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addBreakpoints(IBreakpoint[] breakpoints) throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void fireBreakpointChanged(IBreakpoint breakpoint) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBreakpoint getBreakpoint(IMarker marker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBreakpoint[] getBreakpoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBreakpoint[] getBreakpoints(String modelIdentifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeName(IBreakpoint breakpoint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasBreakpoints() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRegistered(IBreakpoint breakpoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeBreakpoint(IBreakpoint breakpoint, boolean delete)
			throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBreakpointListener(IBreakpointListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBreakpointListener(IBreakpointsListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBreakpointManagerListener(
			IBreakpointManagerListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBreakpoints(IBreakpoint[] breakpoints, boolean delete)
			throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub

	}

}
