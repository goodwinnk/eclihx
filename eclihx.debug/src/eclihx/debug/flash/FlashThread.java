package eclihx.debug.flash;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;

public class FlashThread extends FlashDebugElement implements IThread {

	/**
	 * Breakpoints this thread is suspended at or <code>null</code>
	 * if none.
	 */
	private IBreakpoint[] fBreakpoints;
	
	/**
	 * Whether this thread is stepping
	 */
	private boolean fStepping = false;
	
	public FlashThread(FlashDebugTarget target) {
		super(target);
	}
	
	
	public IBreakpoint[] getBreakpoints() {
		if (fBreakpoints == null) {
			return new IBreakpoint[0];
		}
		return fBreakpoints;
	}
	
	/**
	 * Sets the breakpoints this thread is suspended at, or <code>null</code>
	 * if none.
	 * 
	 * @param breakpoints the breakpoints this thread is suspended at, or <code>null</code>
	 * if none
	 */
	protected void setBreakpoints(IBreakpoint[] breakpoints) {
		fBreakpoints = breakpoints;
	}

	
	public String getName() throws DebugException {
		return "Thread[1]";
	}

	
	public int getPriority() throws DebugException {
		// TODO 3 Understand what value would be best here
		return 0;
	}

	
	public IStackFrame[] getStackFrames() throws DebugException {
		if (isSuspended()) {
			return ((FlashDebugTarget)getDebugTarget()).getStackFrames();
		} else {
			return new IStackFrame[0];
		}
	}

	
	public IStackFrame getTopStackFrame() throws DebugException {
		IStackFrame[] frames = getStackFrames();
		if (frames.length > 0) {
			return frames[0];
		}
		return null;
	}

	
	public boolean hasStackFrames() throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean canResume() {
		return getDebugTarget().canResume();
	}

	
	public boolean canSuspend() {
		return getDebugTarget().canSuspend();
	}

	
	public boolean isSuspended() {
		return getDebugTarget().isSuspended();
	}

	
	public void resume() throws DebugException {
		getDebugTarget().resume();
	}

	
	public void suspend() throws DebugException {
		getDebugTarget().suspend();
	}

	
	public boolean canStepInto() {
		return false;
	}

	
	public boolean canStepOver() {
		return isSuspended();
	}

	
	public boolean canStepReturn() {
		return false;
	}

	
	public boolean isStepping() {
		return fStepping;
	}

	
	public void stepInto() throws DebugException {
	}

	
	public void stepOver() throws DebugException {
	}

	
	public void stepReturn() throws DebugException {
	}

	
	public boolean canTerminate() {
		return getDebugTarget().canTerminate();
	}

	
	public boolean isTerminated() {
		return getDebugTarget().isTerminated();
	}

	
	public void terminate() throws DebugException {
		getDebugTarget().terminate();
	}

}
