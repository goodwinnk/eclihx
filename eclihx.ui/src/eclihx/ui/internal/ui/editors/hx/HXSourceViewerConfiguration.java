package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class HXSourceViewerConfiguration extends SourceViewerConfiguration {
	private HXDoubleClickStrategy doubleClickStrategy;
	private HXScanner scanner;
	private ColorManager colorManager;

	public HXSourceViewerConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			HXPartitionScanner.HX_MULITILINE_COMMENT,
			HXPartitionScanner.HX_COMMENT };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new HXDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected HXScanner getHXScanner() {
		if (scanner == null) {
			scanner = new HXScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IHXColorConstants.DEFAULT))));
		}
		return scanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getHXScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(IHXColorConstants.HX_COMMENT)));
		reconciler.setDamager(ndr, HXPartitionScanner.HX_COMMENT);
		reconciler.setRepairer(ndr, HXPartitionScanner.HX_COMMENT);
		
		ndr = new NonRuleBasedDamagerRepairer(new TextAttribute(colorManager.getColor(IHXColorConstants.HX_MULTILLINE_COMMENT)));
		reconciler.setDamager(ndr, HXPartitionScanner.HX_MULITILINE_COMMENT);
		reconciler.setRepairer(ndr, HXPartitionScanner.HX_MULITILINE_COMMENT);

		return reconciler;
	}

}