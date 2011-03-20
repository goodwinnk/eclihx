package eclihx.core.haxe.internal.versioning;

import java.security.InvalidParameterException;

/**
 * Storage for version compatibility information for some element.
 */
public class VersionInfo {
	
	private enum Type {
		Any,
		Above,
		Period
	}
	
	private final Type type;
	private HaxeVersion minVersion;
	private HaxeVersion maxVersion;
	
	private VersionInfo(Type type, HaxeVersion minVersion, HaxeVersion maxVersion) {
		this.type = type;
		this.minVersion = minVersion;
		this.maxVersion = maxVersion;
	}	
	
	/**
	 * Element supports all versions
	 * @return Any version info.
	 */
	public static VersionInfo any() {
		return new VersionInfo(Type.Any, null, null);
	}
	
	/**
	 * Element supported since some version
	 * @param minVersionStr the first version where element is supported.
	 * @return Version info.
	 */
	public static VersionInfo above(String minVersionStr) {
		if (minVersionStr == null || minVersionStr.isEmpty()) {
			throw new InvalidParameterException();
		}
			
		return new VersionInfo(Type.Above, new HaxeVersion(minVersionStr), null);
	}
	
	/**
	 * Version is supported for several versions.
	 * @param minVersionStr minimal version.
	 * @param maxVersionStr maximum version.
	 * @return Version info.
	 */
	public static VersionInfo period(String minVersionStr, String maxVersionStr) {
		if (minVersionStr == null || minVersionStr.isEmpty()) {
			throw new InvalidParameterException();
		}
		
		if (maxVersionStr == null || maxVersionStr.isEmpty()) {
			throw new InvalidParameterException();
		}
		
		return new VersionInfo(Type.Period, new HaxeVersion(minVersionStr), new HaxeVersion(maxVersionStr));
	}
	
	/**
	 * Checks if given version is supported.
	 * @param version given version.
	 * @return <code>true</code> if version is supported.
	 */
	public Boolean isSupported(HaxeVersion version) {
		switch (type) {
			case Any:
				return true;
			case Above:
				// minVersion <= version
				return !version.isLess(minVersion);		
			case Period:
				// minVersion <= version && version <= maxVersion
				return !version.isLess(minVersion) && !maxVersion.isLess(version); 
		}
		
		return false;
	}
}
