package eclihx.launching;

import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;

public class HaxeSourceLocator extends AbstractSourceLookupDirector {

	public void initializeParticipants() {
		addParticipants(new ISourceLookupParticipant[]{ new HaxeSourceLookupParticipant() });		
	}
}
