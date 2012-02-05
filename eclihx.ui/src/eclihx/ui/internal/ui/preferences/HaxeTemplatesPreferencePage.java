package eclihx.ui.internal.ui.preferences;

import org.eclipse.ui.texteditor.templates.TemplatePreferencePage;

import eclihx.ui.internal.ui.EclihxUIPlugin;
import eclihx.ui.internal.ui.editors.templates.HaxeCustomTemplateManager;

/**
 * Show global templates for haxe.
 */
public class HaxeTemplatesPreferencePage extends TemplatePreferencePage {
	
	/**
	 * Identifier of the preference page.
	 */
	public static final String ID = "eclihx.ui.internal.ui.preferences.HaxeTemplatesPreferencePage";

	/**
	 * Default constructor.
	 */
	public HaxeTemplatesPreferencePage() {
		try {
			setPreferenceStore(EclihxUIPlugin.getDefault().getPreferenceStore());
			setTemplateStore(HaxeCustomTemplateManager.getInstance().getTemplateStore());
			setContextTypeRegistry(HaxeCustomTemplateManager.getInstance().getContextTypeRegistry());
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	protected boolean isShowFormatterSetting() {
		return true;
	}

	@Override
	public boolean performOk() {
		boolean ok = super.performOk();
		EclihxUIPlugin.flushInstanceScope();
		return ok;
	}
}
