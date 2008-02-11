package eclihx.launching;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import eclihx.launching.EclihxLauncher;


public class LauncherPreferenceInitializer extends
		AbstractPreferenceInitializer {
	
	// TODO 2 Maybe move ids declaration to somewhere else 
	public static final String ECLIHAXE_HAXE_COMPILER_PATH = "eclihx.launching.haxe_compiler_path";
	
	public LauncherPreferenceInitializer() {
		super();
	}

	@Override
	public void initializeDefaultPreferences() {
		Preferences store = EclihxLauncher.getDefault().getPluginPreferences();
		store.setDefault(ECLIHAXE_HAXE_COMPILER_PATH, "");
	}
}
