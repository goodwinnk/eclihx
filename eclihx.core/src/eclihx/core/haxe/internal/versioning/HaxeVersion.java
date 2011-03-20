package eclihx.core.haxe.internal.versioning;

/**
 * An abstraction for haXe compiler version. This class will be used
 * for working with compatibility.
 */
public class HaxeVersion {
	private String version;
	
	/**
	 * Default constructor with 
	 * @param version haXe version.
	 */
	public HaxeVersion(String version) {
		this.version = version;
	}
	
	/**
	 * Get the version.
	 * @return the version.
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Check if current version is previous to given.
	 * @param otherVersion version to compare.
	 * @return <b>true</b> if current version is previous to given. 
	 */
	public Boolean isLess(HaxeVersion otherVersion) {
		double currentVersion = Double.parseDouble(version);
		double givenVersion = Double.parseDouble(otherVersion.version);
		return currentVersion < givenVersion;
	}
}
