package eclihx.core.haxe.internal;

import java.util.List;

/**
 * Interface for getting the information about supported versions.    
 */
public interface IHaxeVersionsInfo {
	
	/**
	 * Gives the list of the supported versions.
	 * 
	 * @return Gives the list of the supported versions.
	 */
	List<String> getSupportedVersions();
}
