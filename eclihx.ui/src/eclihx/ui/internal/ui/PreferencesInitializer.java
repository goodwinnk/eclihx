package eclihx.ui.internal.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import eclihx.ui.PreferenceConstants;

/**
 * Preferences initializer.
 */
public class PreferencesInitializer extends AbstractPreferenceInitializer {
	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = PreferenceConstants.getPreferenceStore();
		PreferenceConstants.initializeDefaultValues(store);
	}
}
