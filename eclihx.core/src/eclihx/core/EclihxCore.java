package eclihx.core;

import eclihx.core.haxe.model.HaxeWorkspace;
import eclihx.core.haxe.model.core.IHaxeWorkspace;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class EclihxCore extends Plugin {

	/*
	 * (non-javadoc)
	 */
	private IHaxeWorkspace haxeWorkspace;

	// The plug-in ID
	public static final String PLUGIN_ID = "eclihx.core";

	// The shared instance
	private static EclihxCore plugin;

	/**
	 * The constructor
	 */
	public EclihxCore() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);

		haxeWorkspace = new HaxeWorkspace(ResourcesPlugin.getWorkspace()
				.getRoot());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {

		haxeWorkspace.close();

		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static EclihxCore getDefault() {
		return plugin;
	}

	/**
	 * Getter of the property <tt>haxeWorkspace</tt>
	 * 
	 * @return Returns the haxeWorkspace.
	 * 
	 */
	public IHaxeWorkspace getHaxeWorkspace() {
		return haxeWorkspace;
	}

}
