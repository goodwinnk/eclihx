package eclihx.ui.internal.ui.editors.templates;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.DocumentTemplateContext;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.texteditor.templates.AbstractTemplatesPage;

import eclihx.ui.internal.ui.EclihxUIPlugin;
import eclihx.ui.internal.ui.editors.hx.HXContextAssist;
import eclihx.ui.internal.ui.editors.hx.HXEditor;
import eclihx.ui.internal.ui.preferences.HaxeTemplatesPreferencePage;

/**
 * A templates page of haxe editor for standard templates view.
 */
// Based on org.eclipse.jdt.internal.ui.javaeditor.JavaTemplatesPage
public class HaxeTemplatesPage extends AbstractTemplatesPage {

	private final HXEditor editor;
	
	/**
	 * Default constructor.
	 * @param editor Haxe editor.
	 */
	public HaxeTemplatesPage(HXEditor editor) {
		super(editor, editor.getViewer());
		
		this.editor = editor;
	}

	@Override
	protected void insertTemplate(Template template, IDocument document) {		
		if (!editor.validateEditorInputState()) {
			return;
		}

		ISourceViewer contextViewer= editor.getViewer();
		ITextSelection textSelection= (ITextSelection) contextViewer.getSelectionProvider().getSelection();
		if (!isValidTemplate(document, template, textSelection.getOffset(), textSelection.getLength())) {
			return;
		}
		
		beginCompoundChange(contextViewer);
		
		String savedText;
		try {
			savedText = document.get(textSelection.getOffset(), textSelection.getLength());
			if (savedText.length() == 0) {
				String prefix= HXContextAssist.getIndentifierPrefix(contextViewer, textSelection.getOffset());
				if (prefix.length() > 0 && !template.getName().startsWith(prefix.toString())) {
					return;
				}
				if (prefix.length() > 0) {
					contextViewer.setSelectedRange(textSelection.getOffset() - prefix.length(), prefix.length());
					textSelection= (ITextSelection) contextViewer.getSelectionProvider().getSelection();
				}
			}
			document.replace(textSelection.getOffset(), textSelection.getLength(), template.getName().substring(0, 1));
		} catch (BadLocationException e) {
			endCompoundChange(contextViewer);
			return;
		}
		
		int positionOffset = textSelection.getLength() == 0 ? textSelection.getOffset() : textSelection.getOffset() + 1;
		Position position= new Position(positionOffset, 0);
		Region region= new Region(positionOffset, 0);
		contextViewer.getSelectionProvider().setSelection(new TextSelection(textSelection.getOffset(), 1));

		TemplateContextType type = getContextTypeRegistry().getContextType(template.getContextTypeId());
		DocumentTemplateContext context = new HaxeCodeTemplateContext(type, document, position);
		context.setVariable("selection", savedText); //$NON-NLS-1$
		if (context.getKey().length() == 0) {
			try {
				document.replace(textSelection.getOffset(), 1, savedText);
			} catch (BadLocationException e) {
				endCompoundChange(contextViewer);
				return;
			}
		}
		
		TemplateProposal proposal= new TemplateProposal(template, context, region, null);
		editor.getSite().getPage().activate(editor);
		proposal.apply(editor.getViewer(), ' ', 0, region.getOffset());
		endCompoundChange(contextViewer);
	}
	
	@Override
	protected ContextTypeRegistry getContextTypeRegistry() {
		return HaxeCustomTemplateManager.getInstance().getContextTypeRegistry();
	}

	@Override
	public TemplateStore getTemplateStore() {
		return HaxeCustomTemplateManager.getInstance().getTemplateStore();
	}

	@Override
	protected IPreferenceStore getTemplatePreferenceStore() {
		return EclihxUIPlugin.getDefault().getPreferenceStore();
	}

	@Override
	protected String getPreferencePageId() {
		return HaxeTemplatesPreferencePage.ID;
	}

	@Override
	protected String[] getContextTypeIds(IDocument document, int offset) {
		return new String[] { HaxeContextTypes.ID_MEMBERS, HaxeContextTypes.ID_STATEMENTS };
	}

	@Override
	protected boolean isValidTemplate(IDocument document, Template template, int offset, int length) {
		return true;
	}
	
	/**
	 * Undomanager - end compound change
	 * 
	 * @param viewer the viewer
	 */
	private void endCompoundChange(ISourceViewer viewer) {
		if (viewer instanceof ITextViewerExtension) {
			((ITextViewerExtension) viewer).getRewriteTarget().endCompoundChange();
		}
	}

	/**
	 * Undomanager - begin a compound change
	 * 
	 * @param viewer the viewer
	 */
	private void beginCompoundChange(ISourceViewer viewer) {
		if (viewer instanceof ITextViewerExtension) {
			((ITextViewerExtension) viewer).getRewriteTarget().beginCompoundChange();
		}
	}
}
