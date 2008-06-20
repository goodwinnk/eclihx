package eclihx.core.haxe.internal.configuration;

public class FlashConfiguration {
	
	private String outFile;
	private int version;
	private String header;
	
	/**
	 * @return the outFile
	 */
	public String getOutFile() {
		return outFile;
	}
	/**
	 * @param outFile the outFile to set
	 */
	public void setOutFile(String outFile) {
		this.outFile = outFile;
	}
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}	
}
