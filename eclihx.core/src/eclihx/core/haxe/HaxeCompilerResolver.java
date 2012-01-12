package eclihx.core.haxe;

import eclihx.core.CorePreferenceInitializer;
import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxeProject;

/**
 * Haxe compiler manager.
 */
public class HaxeCompilerResolver {
	/**
	 * Get default haXe compiler from preferences.
	 * @return global haxe compiler from preferences.
	 */
	public static String getDefaultGlobalCompiler() {
		return EclihxCore.getDefault().getPluginPreferences().getString(
				CorePreferenceInitializer.HAXE_COMPILER_PATH);
	}
	
	/**
	 * Get special haxe compiler for project. It could be same as global compiler.
	 * @param haxeProject haxe project.
	 * @return haxe compiler for project.
	 */
	public static String getProjectCompiler(IHaxeProject haxeProject) {
		return getDefaultGlobalCompiler();
	}
}
