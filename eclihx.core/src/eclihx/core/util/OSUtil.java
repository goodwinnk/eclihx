package eclihx.core.util;


/**
 * Not sure yet will this class be usefull
 * 
 * Going to be used to make OS specific operations
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
	
}
