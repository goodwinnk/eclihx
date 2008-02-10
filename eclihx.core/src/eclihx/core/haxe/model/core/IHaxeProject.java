package eclihx.core.haxe.model.core;

import org.eclipse.core.resources.IProject;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.internal.IClasspathManager;


public interface IHaxeProject {
	public final static String HAXE_PROJECT_NATURE_ID = EclihxCore.PLUGIN_ID + ".haxenature";
	
	IClasspathManager getClassPathManager(); 
	
	IProject getProjectBase();
}
