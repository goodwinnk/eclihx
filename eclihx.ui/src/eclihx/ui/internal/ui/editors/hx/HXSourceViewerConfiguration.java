/* Seems should be deleted */

package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.util.PropertyChangeEvent;

import eclihx.ui.PreferenceConstants;
import eclihx.ui.internal.ui.EclihxPlugin;
import eclihx.ui.internal.ui.editors.AbstractScanner;
import eclihx.ui.internal.ui.editors.SingleTokenScanner;

public class HXSourceViewerConfiguration extends SourceViewerConfiguration {
	private HXDoubleClickStrategy doubleClickStrategy;
	//private ColorManager colorManager;
	
	private final HXScanner hxCodeScanner;
	private final AbstractScanner singleLineCommentScanner;
	private final AbstractScanner multiLineCommentScanner;
	private final AbstractScanner stringScanner;
	private final AbstractScanner regexprScanner;
	//private final AbstractScanner hxDocScanner;
	private final AbstractScanner hxConditionCompilationScanner;
	
	public void adaptToPreferenceChange(PropertyChangeEvent event) {
		hxCodeScanner.adaptToPreferenceChange(event);
		singleLineCommentScanner.adaptToPreferenceChange(event);
		multiLineCommentScanner.adaptToPreferenceChange(event);
		stringScanner.adaptToPreferenceChange(event);
		regexprScanner.adaptToPreferenceChange(event);
		hxConditionCompilationScanner.adaptToPreferenceChange(event);
	}

	public HXSourceViewerConfiguration(ColorManager colorManager) {
		//this.colorManager = colorManager;
		
		IPreferenceStore store = EclihxPlugin.getDefault().getPreferenceStore();
		hxCodeScanner = new HXScanner(colorManager, store);
		singleLineCommentScanner = new SingleTokenScanner(colorManager, store, PreferenceConstants.HX_EDITOR_COMMENT_COLOR, PreferenceConstants.HX_EDITOR_COMMENT_BOLD, PreferenceConstants.HX_EDITOR_COMMENT_ITALIC);
		multiLineCommentScanner = new SingleTokenScanner(colorManager, store, PreferenceConstants.HX_EDITOR_MULTILINE_COMMENT_COLOR, PreferenceConstants.HX_EDITOR_MULTILINE_COMMENT_BOLD, PreferenceConstants.HX_EDITOR_MULTILINE_COMMENT_ITALIC);
		stringScanner = new SingleTokenScanner(colorManager, store, PreferenceConstants.HX_EDITOR_STRING_COLOR, PreferenceConstants.HX_EDITOR_STRING_BOLD, PreferenceConstants.HX_EDITOR_STRING_ITALIC);
		regexprScanner = new SingleTokenScanner(colorManager, store, PreferenceConstants.HX_EDITOR_REGEXPR_COLOR, PreferenceConstants.HX_EDITOR_REGEXPR_BOLD, PreferenceConstants.HX_EDITOR_REGEXPR_ITALIC);
		hxConditionCompilationScanner = new SingleTokenScanner(colorManager, store, PreferenceConstants.HX_EDITOR_CONDITIONAL_COMPILATION_COLOR, PreferenceConstants.HX_EDITOR_CONDITIONAL_COMPILATION_BOLD, PreferenceConstants.HX_EDITOR_CONDITIONAL_COMPILATION_ITALIC);
	}
	
	
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			IHXPartitions.HX_SINGLE_LINE_COMMENT,
			IHXPartitions.HX_MULTI_LINE_COMMENT,
			IHXPartitions.HX_STRING,
			IHXPartitions.HX_REGEXPR,
			IHXPartitions.HX_PREPROCESSOR
		};
	}
	
	
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new HXDoubleClickStrategy();
		return doubleClickStrategy;
	}
	

	protected HXScanner getHXScanner() {
		return hxCodeScanner;
	}
	
	
	protected AbstractScanner getSingleLineCommentScanner() {
		return singleLineCommentScanner;
	}
	
	
	protected AbstractScanner getMultiLineCommentScanner() {
		return multiLineCommentScanner;
	}
	
	
	protected AbstractScanner getStringScanner() {
		return stringScanner;
	}
	
	
	protected AbstractScanner getRegexprScanner() {
		return regexprScanner;
	}
	
	
	/*protected AbstractScanner getHXDocScanner() {
		return hxDocScanner;
	}*/
	
	
	protected AbstractScanner getHXConditionCompilationScanner() {
		return hxConditionCompilationScanner;
	}
	

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getHXScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		dr = new DefaultDamagerRepairer(getSingleLineCommentScanner());
		reconciler.setDamager(dr, IHXPartitions.HX_SINGLE_LINE_COMMENT);
		reconciler.setRepairer(dr, IHXPartitions.HX_SINGLE_LINE_COMMENT);
		
		dr = new DefaultDamagerRepairer(getMultiLineCommentScanner());
		reconciler.setDamager(dr, IHXPartitions.HX_MULTI_LINE_COMMENT);
		reconciler.setRepairer(dr, IHXPartitions.HX_MULTI_LINE_COMMENT);
		
		dr = new DefaultDamagerRepairer(getStringScanner());
		reconciler.setDamager(dr, IHXPartitions.HX_STRING);
		reconciler.setRepairer(dr, IHXPartitions.HX_STRING);
		
		dr = new DefaultDamagerRepairer(getRegexprScanner());
		reconciler.setDamager(dr, IHXPartitions.HX_REGEXPR);
		reconciler.setRepairer(dr, IHXPartitions.HX_REGEXPR);
		
		dr = new DefaultDamagerRepairer(getHXConditionCompilationScanner());
		reconciler.setDamager(dr, IHXPartitions.HX_PREPROCESSOR);
		reconciler.setRepairer(dr, IHXPartitions.HX_PREPROCESSOR);
		
		return reconciler;
	}
}