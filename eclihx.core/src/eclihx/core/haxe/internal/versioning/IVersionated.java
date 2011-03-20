package eclihx.core.haxe.internal.versioning;

/**
 * Interface for getting the information about supported versions.    
 */
public interface IVersionated {
	
	/**
	 * Get information about supported versions.
	 * @return version information.
	 */
	VersionInfo getVersionInfo();
}
