package eclihx.core;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

/**
 * Initializer of the preferences for the core plug-in.
 */
public final class CorePreferenceInitializer 
		extends AbstractPreferenceInitializer {

	/**
	 * Common prefix for all core preferences.
	 */
	private static final String PREFERENCE_PREFIX = "eclihx.core.";
	
	/**
	 * Storage of the haXe compiler path.  
	 */
	public static final String HAXE_COMPILER_PATH = 
		PREFERENCE_PREFIX + "haxe_compiler_path";
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		Preferences store = EclihxCore.getDefault().getPluginPreferences();
		store.setDefault(PREFERENCE_PREFIX, "");
	}
}
