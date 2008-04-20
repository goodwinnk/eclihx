package eclihx.debug.flash;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;

import flash.tools.debugger.NoResponseException;
import flash.tools.debugger.NotConnectedException;
import flash.tools.debugger.NotSuspendedException;
import flash.tools.debugger.Session;
import flash.tools.debugger.VersionException;

public class FlashDebugTarget implements IDebugTarget {
	
	private ILaunch fLaunch;
	private IProcess fProcess;
	private String fUrl;
	private Session fSession;
	private IThread fThread;
	private IDebugTarget fTarget;
	private IThread[] fThreads;
	
	private DebugException generateError(String message, Throwable exception) {
		return new DebugException(new Status(IStatus.OK, "debug", message, exception));		
	}

	public FlashDebugTarget(String url, ILaunch launch, Session session, IProcess process) throws CoreException, VersionException {

		fTarget = this;
		fThread = new FlashThread(this);
		fThreads = new IThread[] { fThread };
		fUrl = url;
		fLaunch = launch;
		fProcess = process;
		fSession = session;
		
		fSession.bind();

		// Debugger support
		DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(this);
		// TODO 7 Possible need this 
		//DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(this);
		
		//resume();
	}
	
	@Override
	public String getName() throws DebugException {
		return "Haxe debug: " + fUrl; // TODO 5 make it beautiful
	}

	@Override
	public IProcess getProcess() {
		return fProcess;
	}

	@Override
	public IThread[] getThreads() throws DebugException {
		return fThreads;
	}

	@Override
	public boolean hasThreads() throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IDebugTarget getDebugTarget() {
		return this;
	}

	@Override
	public ILaunch getLaunch() {
		return fLaunch;
	}

	@Override
	public String getModelIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canTerminate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void terminate() throws DebugException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean canResume() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSuspend() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSuspended() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resume() throws DebugException {
		// TODO Auto-generated method stub
		try {
			fSession.stepContinue();
		} catch (NotSuspendedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw generateError("Can't resume", e);
		} catch (NoResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw generateError("Can't resume", e);
		} catch (NotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw generateError("Can't resume", e);
		}
		
	}

	@Override
	public void suspend() throws DebugException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void breakpointAdded(IBreakpoint breakpoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canDisconnect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void disconnect() throws DebugException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDisconnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IMemoryBlock getMemoryBlock(long startAddress, long length)
			throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsStorageRetrieval() {
		// TODO Auto-generated method stub
		return false;
	}

}
