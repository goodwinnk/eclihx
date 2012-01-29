package eclihx.ui.internal.ui.editors.templates;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.texteditor.templates.AbstractTemplatesPage;

import eclihx.ui.internal.ui.EclihxUIPlugin;
import eclihx.ui.internal.ui.editors.hx.HXEditor;

/**
 * A templates page of haxe editor for standard templates view.
 */
public class HaxeTemplatesPage extends AbstractTemplatesPage {

	/**
	 * Default constructor.
	 * @param editor Haxe editor.
	 */
	public HaxeTemplatesPage(HXEditor editor) {
		super(editor, editor.getViewer());
	}

	@Override
	protected void insertTemplate(Template template, IDocument document) {
		// TODO
	}

	@Override
	protected ContextTypeRegistry getContextTypeRegistry() {
		return CustomTemplateManager.getInstance().getContextTypeRegistry();
	}

	@Override
	public TemplateStore getTemplateStore() {
		return CustomTemplateManager.getInstance().getTemplateStore();
	}

	@Override
	protected IPreferenceStore getTemplatePreferenceStore() {
		return EclihxUIPlugin.getDefault().getPreferenceStore();
	}

	@Override
	protected String getPreferencePageId() {
		return "eclihx.ui.internal.ui.preferences.HaxeTemplatesPreferencePage";
	}

	@Override
	protected String[] getContextTypeIds(IDocument document, int offset) {
		return new String[] { HaxeContextTypes.ID_ALL, HaxeContextTypes.ID_MEMBERS, HaxeContextTypes.ID_STATEMENTS };
	}

	@Override
	protected boolean isValidTemplate(IDocument document, Template template,
			int offset, int length) {
		return true;
	}

}
