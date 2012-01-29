package eclihx.ui.internal.ui.preferences;

import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.texteditor.templates.TemplatePreferencePage;

import eclihx.ui.internal.ui.EclihxUIPlugin;
import eclihx.ui.internal.ui.editors.templates.CustomTemplateManager;

/**
 * Show global templates for haxe.
 */
public class HaxeTemplatesPreferencePage extends TemplatePreferencePage
		implements IWorkbenchPreferencePage {

	/**
	 * Default constructor.
	 */
	public HaxeTemplatesPreferencePage() {
		try {
			setPreferenceStore(EclihxUIPlugin.getDefault().getPreferenceStore());
			setTemplateStore(CustomTemplateManager.getInstance().getTemplateStore());
			setContextTypeRegistry(CustomTemplateManager.getInstance().getContextTypeRegistry());
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	protected boolean isShowFormatterSetting() {
		return false;
	}

	public boolean performOk() {
		boolean ok = super.performOk();
		EclihxUIPlugin.getDefault().savePluginPreferences();
		return ok;
	}
}
