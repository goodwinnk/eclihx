package eclihx.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;
import eclihx.debug.flash.FlashStackFrame;

public class HaxeSourceLookupParticipant extends AbstractSourceLookupParticipant {

	public String getSourceName(Object object) throws CoreException {
		if (object instanceof FlashStackFrame) {
			return ((FlashStackFrame)object).getSourceName();
		}
	    return null;
	}	

}
