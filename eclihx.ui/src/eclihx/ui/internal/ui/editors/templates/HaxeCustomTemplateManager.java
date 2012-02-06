package eclihx.ui.internal.ui.editors.templates;

import java.io.IOException;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.ui.editors.text.templates.ContributionContextTypeRegistry;
import org.eclipse.ui.editors.text.templates.ContributionTemplateStore;

import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Stores and manages template for haxe code.
 */
public class HaxeCustomTemplateManager {
	private static final String CUSTOM_TEMPLATES_KEY = EclihxUIPlugin.getPluginId() + ".customtemplates";
	private static HaxeCustomTemplateManager instance;
	private TemplateStore fStore;
	private ContributionContextTypeRegistry fRegistry;

	private HaxeCustomTemplateManager() {
	}

	/**
	 * @return Instance of manager.
	 */
	public static HaxeCustomTemplateManager getInstance() {
		if (instance == null) {
			instance = new HaxeCustomTemplateManager();
		}
		return instance;
	}

	/**
	 * @return Templates store.
	 */
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

	/**
	 * Get context type registry.
	 * 
	 * @return Get context type registry.
	 */
	public ContextTypeRegistry getContextTypeRegistry() {
		if (fRegistry == null) {
			fRegistry = new ContributionContextTypeRegistry();
		}
		
		fRegistry.addContextType(HaxeContextTypes.ID_ALL);
		fRegistry.addContextType(HaxeContextTypes.ID_MEMBERS);
		fRegistry.addContextType(HaxeContextTypes.ID_STATEMENTS);
		
		return fRegistry;
	}

	IPreferenceStore getPreferenceStore() {
		return EclihxUIPlugin.getDefault().getPreferenceStore();
	}

	void savePluginPreferences() {
		EclihxUIPlugin.flushInstanceScope();
	}
}