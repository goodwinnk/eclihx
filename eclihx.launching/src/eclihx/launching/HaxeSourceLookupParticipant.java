package eclihx.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;

import eclihx.debug.flash.FlashStackFrame;

/**
 * Source lookup participant...
 * TODO 5 Finish this class.
 */
public class HaxeSourceLookupParticipant extends 
		AbstractSourceLookupParticipant {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant#getSourceName(java.lang.Object)
	 */
	@Override
	public String getSourceName(Object object) throws CoreException {
		if (object instanceof FlashStackFrame) {
			return ((FlashStackFrame)object).getSourceName();
		}
	    return null;
	}	

}
