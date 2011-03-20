package eclihx.launching;

import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;

/**
 * Source locator...
 * TODO 5 Understand how to finish this class.
 */
public class HaxeSourceLocator extends AbstractSourceLookupDirector {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.debug.core.sourcelookup.ISourceLookupDirector#initializeParticipants()
	 */
	@Override
	public void initializeParticipants() {
		addParticipants(new ISourceLookupParticipant[]{ 
				new HaxeSourceLookupParticipant() });		
	}
	
}
