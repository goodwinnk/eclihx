package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.BasicTextEditorActionContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.RetargetTextEditorAction;

/**
 * haXe editor actions contributor.
 */
public class HxEditorActionContributor extends BasicTextEditorActionContributor {

	private final RetargetTextEditorAction fContentAssist;
	private final RetargetTextEditorAction fContentAssistTip;

	/**
	 * Default constructor.
	 */
	public HxEditorActionContributor() {
		super();
		
		fContentAssist = new RetargetTextEditorAction(
				HaxeEditorMessages.getBundle(),
				HXEditor.CONTENT_PROPSALS_ACTION_RESOURCE_PREFIX);
		
		fContentAssistTip = new RetargetTextEditorAction(
				HaxeEditorMessages.getBundle(),
				HXEditor.CONTENT_TIPS_ACTION_RESOURCE_PREFIX);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.texteditor.BasicTextEditorActionContributor#setActiveEditor
	 * (org.eclipse.ui.IEditorPart)
	 */
	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);
		
		ITextEditor editor = null;
		if (part instanceof ITextEditor) {
			editor = (ITextEditor)part;
		}
		
		fContentAssist.setAction(getAction(editor, HXEditor.CONTENT_PROPSALS_ACTION_ID));
		fContentAssistTip.setAction(getAction(editor, HXEditor.CONTENT_TIPS_ACTION_ID));
	}
}
