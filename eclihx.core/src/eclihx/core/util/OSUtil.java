package eclihx.core.util;

/**
 * Class for storing operation system specific options.
 */
public final class OSUtil {
	
	/**
	 * Add extension separator to the parameter if there are no any.
	 * 
	 * @param original extension
	 * @return extension with separator
	 */
	static public String getFullFileExtension(String extension) {
		if (!extension.startsWith("."))
			return ("." + extension);
		
		return extension;
	}
	
	/**
	 * Checks if path isn't quoted yet and adds quotation marks to the both
	 * ends of the string if path has spaces characters.
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
	 * Gets file filter for the compiler.
	 * It can be something like "*.*" or "*.exe" or even "haxe.exe". 
	 * @return File filter for compiler file dialog.
	 */
	static public String getCompilerExtensionFilter() {
		// TODO 8 Support other operation systems
		return "haxe.exe";
	}
	
	
}
