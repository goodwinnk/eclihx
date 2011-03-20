package eclihx.debug.flash;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class FlashVariable implements IVariable {

	public String getName() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getReferenceTypeName() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IValue getValue() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean hasValueChanged() throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public IDebugTarget getDebugTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ILaunch getLaunch() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getModelIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setValue(String expression) throws DebugException {
		// TODO Auto-generated method stub

	}

	
	public void setValue(IValue value) throws DebugException {
		// TODO Auto-generated method stub

	}

	
	public boolean supportsValueModification() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean verifyValue(String expression) throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean verifyValue(IValue value) throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

}
