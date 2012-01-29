package eclihx.ui.internal.ui.editors.templates;

import java.io.IOException;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplatePersistenceData;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;

import eclihx.ui.internal.ui.EclihxUIPlugin;

public class CustomTemplateManager {
	private static final String CUSTOM_TEMPLATES_KEY = EclihxUIPlugin.getPluginId() + ".customtemplates";
	private static CustomTemplateManager instance;
	private TemplateStore fStore;
	private ContributionContextTypeRegistry fRegistry;
	private TemplatePersistenceData[] templateData;

	private CustomTemplateManager() {
	}

	public static CustomTemplateManager getInstance() {
		if (instance == null) {
			instance = new CustomTemplateManager();
		}
		return instance;
	}

	public TemplateStore getTemplateStore() {

		if (fStore == null) {

			fStore = new ContributionTemplateStore(getContextTypeRegistry(),
					EclihxUIPlugin.getDefault().getPreferenceStore(),
					CUSTOM_TEMPLATES_KEY);
			try {
				fStore.load();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		return fStore;
	}

	public ContextTypeRegistry getContextTypeRegistry() {
		if (fRegistry == null) {
			fRegistry = new ContributionContextTypeRegistry();
		}
		
		fRegistry.addContextType(HaxeContextTypes.ID_ALL);
		fRegistry.addContextType(HaxeContextTypes.ID_MEMBERS);
		fRegistry.addContextType(HaxeContextTypes.ID_STATEMENTS);
		
		return fRegistry;
	}

	public IPreferenceStore getPreferenceStore() {
		return EclihxUIPlugin.getDefault().getPreferenceStore();
	}

	public void savePluginPreferences() {
		EclihxUIPlugin.getDefault().savePluginPreferences();
	}

}