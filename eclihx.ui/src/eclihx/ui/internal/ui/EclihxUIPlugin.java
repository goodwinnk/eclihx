package eclihx.ui.internal.ui;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.service.prefs.BackingStoreException;

import eclihx.core.EclihxLogger;
import eclihx.core.IPluginLogger;
import eclihx.ui.PreferenceConstants;

/**
 * The activator class controls the plug-in life cycle
 */
public class EclihxUIPlugin extends AbstractUIPlugin {
	/**
	 * Plug-in identifier.
	 */
	public static final String PLUGIN_ID = "eclihx.ui";

	// The shared instance
	private static EclihxUIPlugin plugin;
	
	/**
	 * Logger helper.
	 */
	private static IPluginLogger logger;

	/**
	 * The constructor
	 */
	public EclihxUIPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static EclihxUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * Initialize plug-in default settings
	 */
	@Override
	protected void initializeDefaultPreferences(IPreferenceStore store) {
		PreferenceConstants.initializeDefaultValues(store);
	}
	
	/**
	 * Gets the plug-in unique id.
	 * @return plug-in unique id.
	 */
	public static String getPluginId() {
		return EclihxUIPlugin.PLUGIN_ID;
	}	

	/**
	 * Get plug-in log helper.  
	 * @return plug-in helper.
	 */
	public static IPluginLogger getLogHelper() {
		if (logger == null) {
			logger = new EclihxLogger(getDefault(), PLUGIN_ID);
		}
		
		return logger;
	}
	
	/**
	 * Flushes the instance scope of this plug-in.
	 * 
	 * @since 3.7
	 */
	public static void flushInstanceScope() {
		try {
			InstanceScope.INSTANCE.getNode(PLUGIN_ID).flush();
		} catch (BackingStoreException e) {
			getLogHelper().logError(e);
		}
	}
	
	
}
