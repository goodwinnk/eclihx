package eclihx.core.haxe.internal;

/**
 * Manager of haXe specific constants.
 */
public class HaxePreferencesManager {
	

	/**
	 * Returns the current value of the string-valued preference with the given id. 
	 *
	 * @param preference value 
	 * @return 
	 */
	static public String getString(EHaxePreferencesIds id) {
		switch (id) {
		case BUILD_FILE_EXTENSION:
			return "hxml";
		default:
			return "";
		}
	}
}
