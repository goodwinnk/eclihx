package eclihx.launching;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import eclihx.launching.EclihxLauncher;

public class LauncherPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public LauncherPreferenceInitializer() {
		super();
	}

	@Override
	public void initializeDefaultPreferences() {
		Preferences store = EclihxLauncher.getDefault().getPluginPreferences();
		store.setDefault("eclihx_launcher_compiler", "");
	}
}
