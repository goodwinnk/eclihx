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
	
	@Override
	public int getCharEnd() throws DebugException {
		return -1;
	}

	@Override
	public int getCharStart() throws DebugException {
		return -1;
	}

	@Override
	public int getLineNumber() throws DebugException {
		fFlashFrame.getLocation().getLine();
		return 0;
	}

	@Override
	public String getName() throws DebugException {
		return "FlashStack: " + fFlashFrame.getLocation().getFile().getFullPath();
	}

	@Override
	public IRegisterGroup[] getRegisterGroups() throws DebugException {
		return null;
	}

	@Override
	public IThread getThread() {
		return fThread;
	}

	@Override
	public IVariable[] getVariables() throws DebugException {
		return null;
	}

	@Override
	public boolean hasRegisterGroups() throws DebugException {
		return false;
	}

	@Override
	public boolean hasVariables() throws DebugException {
		return false;
	}

	@Override
	public boolean canStepInto() {
		return fThread.isSuspended();
	}

	@Override
	public boolean canStepOver() {
		return fThread.isSuspended();
	}

	@Override
	public boolean canStepReturn() {
		return false;
	}

	@Override
	public boolean isStepping() {
		return fThread.isStepping();
	}

	@Override
	public void stepInto() throws DebugException {
	}

	@Override
	public void stepOver() throws DebugException {
	}

	@Override
	public void stepReturn() throws DebugException {
	}

	@Override
	public boolean canResume() {
		return fThread.canResume();
	}

	@Override
	public boolean canSuspend() {
		return fThread.canSuspend();
	}

	@Override
	public boolean isSuspended() {
		return fThread.isSuspended();
	}

	@Override
	public void resume() throws DebugException {
		fThread.resume();
	}

	@Override
	public void suspend() throws DebugException {
		fThread.suspend();
	}

	@Override
	public boolean canTerminate() {
		return fThread.canTerminate();
	}

	@Override
	public boolean isTerminated() {
		return fThread.isTerminated();
	}

	@Override
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
