package eclihx.debug.flash;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;

public class FlashDebugElement extends PlatformObject implements IDebugElement {	
	
	// containing target 
	private FlashDebugTarget fTarget;
	
	/**
	 * Constructs a new debug element contained in the given
	 * debug target.
	 * 
	 * @param target debug target (Flash VM)
	 */
	public FlashDebugElement(FlashDebugTarget target) {
		fTarget = target;
	}
	
	public IDebugTarget getDebugTarget() {
		return fTarget;
	}
	
	public FlashDebugTarget getFlashDebugTarget() {
		return fTarget;
	}

	public ILaunch getLaunch() {
		return fTarget.getLaunch();
	}

	public String getModelIdentifier() {
		return FlashConstants.ID_FLASH_DEBUG_MODEL;
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IDebugElement.class) {
			return this;
		}
		return super.getAdapter(adapter);
	}
	
	/**
	 * Fires a debug event
	 * @param event the event to be fired
	 */
	protected void fireEvent(DebugEvent event) {
		DebugPlugin.getDefault().fireDebugEventSet(new DebugEvent[] {event});
	}
	
	/**
	 * Fires a <code>RESUME</code> event for this element with
	 * the given detail.
	 * @param detail event detail code
	 */
	public void fireResumeEvent(int detail) {
		fireEvent(new DebugEvent(this, DebugEvent.RESUME, detail));
	}

	/**
	 * Fires a <code>SUSPEND</code> event for this element with
	 * the given detail.
	 * @param detail event detail code
	 */
	public void fireSuspendEvent(int detail) {
		fireEvent(new DebugEvent(this, DebugEvent.SUSPEND, detail));
	}
	
}
