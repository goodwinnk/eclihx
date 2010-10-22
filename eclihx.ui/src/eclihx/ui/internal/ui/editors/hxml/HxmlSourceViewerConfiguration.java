package eclihx.ui.internal.ui.editors.hxml;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.util.PropertyChangeEvent;

import eclihx.ui.PreferenceConstants;
import eclihx.ui.internal.ui.EclihxUIPlugin;
import eclihx.ui.internal.ui.editors.AbstractScanner;
import eclihx.ui.internal.ui.editors.ColorManager;
import eclihx.ui.internal.ui.editors.SingleTokenScanner;

/**
 * Configuration for hxml-files code editor.
 */
class HxmlSourceViewerConfiguration extends SourceViewerConfiguration {
	
	private final AbstractScanner hxmlCodeScanner;
	private final AbstractScanner hxmlCommentScanner;

	public HxmlSourceViewerConfiguration(ColorManager colorManager)
	{
		IPreferenceStore store = EclihxUIPlugin.getDefault().getPreferenceStore();		
		hxmlCodeScanner = new HxmlScanner(colorManager, store);		
		hxmlCommentScanner = new SingleTokenScanner(colorManager, store,
			PreferenceConstants.HXML_EDITOR_COMMENT_COLOR,
			PreferenceConstants.HXML_EDITOR_COMMENT_BOLD,
			PreferenceConstants.HXML_EDITOR_COMMENT_ITALIC);
	}
	
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
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(hxmlCodeScanner);
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		dr = new DefaultDamagerRepairer(hxmlCommentScanner);
		reconciler.setDamager(dr, HxmlPartitionScanner.HXML_SINGLE_LINE_COMMENT);
		reconciler.setRepairer(dr, HxmlPartitionScanner.HXML_SINGLE_LINE_COMMENT);
		
		return reconciler;
	}

	/**
	 * Refresh user scanners for changed properties
	 * @param event Property change event.
	 */
	public void adaptToPreferenceChange(PropertyChangeEvent event) {
		hxmlCodeScanner.adaptToPreferenceChange(event);
		hxmlCommentScanner.adaptToPreferenceChange(event);
	}
}
