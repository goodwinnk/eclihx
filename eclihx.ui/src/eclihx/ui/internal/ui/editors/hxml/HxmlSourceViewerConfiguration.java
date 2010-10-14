package eclihx.ui.internal.ui.editors.hxml;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

/**
 * Configuration for hxml-files code editor.
 */
public class HxmlSourceViewerConfiguration extends SourceViewerConfiguration {
	
	/**
	 * Get hxml content assist manager
	 */
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant assistant = new ContentAssistant();
		
		HxmlContextAssistimplements hxmlContextAssist = new HxmlContextAssistimplements();
		
		assistant.enableAutoActivation(true);
		assistant.setAutoActivationDelay(300);
		assistant.setContentAssistProcessor(hxmlContextAssist, IDocument.DEFAULT_CONTENT_TYPE);
		assistant.setShowEmptyList(true);
		
		return assistant;
	}
}
