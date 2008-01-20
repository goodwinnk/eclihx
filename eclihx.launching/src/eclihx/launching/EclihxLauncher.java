package eclihx.launching;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class EclihxLauncher extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eclihx.launching";

	// The shared instance
	private static EclihxLauncher plugin;
	
	/**
	 * The constructor
	 */
	public EclihxLauncher() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		savePluginPreferences();
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static EclihxLauncher getDefault() {
		return plugin;
	}

}
