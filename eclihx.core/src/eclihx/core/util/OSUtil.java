package eclihx.core.util;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import eclihx.core.EclihxCore;

/**
 * Class for storing operation system specific options.
 */
public final class OSUtil {

	/**
	 * Add extension separator to the parameter if there are no any.
	 * 
	 * @param extension string with extension where separator should be added
	 * 
	 * @return extension with separator.
	 */
	static public String getFullFileExtension(String extension) {
		if (!extension.startsWith("."))
			return ("." + extension);

		return extension;
	}

	/**
	 * Checks if path isn't quoted yet and adds quotation marks to the both ends
	 * of the string if path has spaces characters.
	 * 
	 * @param path file path.
	 * @return processed path.
	 */
	static public String quoteCompoundPath(String path) {

		if (path.indexOf(" ") != -1) {
			if (!(path.startsWith("\"") || (path.endsWith("\"")))) {
				return "\"" + path + "\"";
			}
		}

		return path;
	}

	/**
	 * Gets file filter for the compiler. It can be something like "*.*" or
	 * "*.exe" or even "haxe.exe".
	 * 
	 * @return File filter for compiler file dialog.
	 */
	static public String getCompilerExtensionFilter() {
		// TODO 8 Support other operation systems
		return "haxe.exe";
	}
	
	/**
	 * Method validates the compiler path.
	 * @param path the path to validate
	 * @return status of the check.
	 */
	static public IStatus validateCompilerPath(String path) {
		
		if (path == null) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"Compiler path can't be null");
		}
		
		if (path.isEmpty()) {
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"Compiler path can't be empty");
		}
		
		// TODO 9 make it work not only in windows
		if (!path.endsWith(getCompilerExtensionFilter())) { 
			return new Status(
					IStatus.ERROR, EclihxCore.PLUGIN_ID, 
					"Invalid extension for the choosen compiler.");
		}
		
		return new Status(IStatus.OK, EclihxCore.PLUGIN_ID, "");		
	}
}
