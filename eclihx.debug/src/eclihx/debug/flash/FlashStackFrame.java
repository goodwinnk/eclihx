package eclihx.debug.flash;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

import flash.tools.debugger.Frame;

public class FlashStackFrame extends FlashDebugElement implements IStackFrame {

	private FlashThread fThread;
	private Frame fFlashFrame;
		
	/**
	 * Constructs a stack frame in the given thread with the given
	 * frame data.
	 * 
	 * @param thread
	 * @param flashFrame
	 */
	public FlashStackFrame(FlashThread thread, Frame flashFrame) {
		super(thread.getFlashDebugTarget());
		fThread = thread;
		fFlashFrame = flashFrame;
	}
	
	
	public int getCharEnd() throws DebugException {
		return -1;
	}

	
	public int getCharStart() throws DebugException {
		return -1;
	}

	
	public int getLineNumber() throws DebugException {
		fFlashFrame.getLocation().getLine();
		return 0;
	}

	
	public String getName() throws DebugException {
		return "FlashStack: " + fFlashFrame.getLocation().getFile().getFullPath();
	}

	
	public IRegisterGroup[] getRegisterGroups() throws DebugException {
		return null;
	}

	
	public IThread getThread() {
		return fThread;
	}

	
	public IVariable[] getVariables() throws DebugException {
		return null;
	}

	
	public boolean hasRegisterGroups() throws DebugException {
		return false;
	}

	
	public boolean hasVariables() throws DebugException {
		return false;
	}

	
	public boolean canStepInto() {
		return fThread.isSuspended();
	}

	
	public boolean canStepOver() {
		return fThread.isSuspended();
	}

	
	public boolean canStepReturn() {
		return false;
	}

	
	public boolean isStepping() {
		return fThread.isStepping();
	}

	
	public void stepInto() throws DebugException {
	}

	
	public void stepOver() throws DebugException {
	}

	
	public void stepReturn() throws DebugException {
	}

	
	public boolean canResume() {
		return fThread.canResume();
	}

	
	public boolean canSuspend() {
		return fThread.canSuspend();
	}

	
	public boolean isSuspended() {
		return fThread.isSuspended();
	}

	
	public void resume() throws DebugException {
		fThread.resume();
	}

	
	public void suspend() throws DebugException {
		fThread.suspend();
	}

	
	public boolean canTerminate() {
		return fThread.canTerminate();
	}

	
	public boolean isTerminated() {
		return fThread.isTerminated();
	}

	
	public void terminate() throws DebugException {
		fThread.terminate();
	}
	
	/**
	 * Returns the name of the source file this stack frame is associated
	 * with.
	 * 
	 * @return the name of the source file this stack frame is associated
	 * with
	 */
	public String getSourceName() {
		return fFlashFrame.getLocation().getFile().getName();
	}

}
