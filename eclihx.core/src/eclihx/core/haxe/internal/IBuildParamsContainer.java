package eclihx.core.haxe.internal;

/**
 * Interface for haXe build parameters
 */
public interface IBuildParamsContainer {
	
	/**
	 * haXe code available platforms
	 */
	enum TargetPlatform {
		Flash,
		AS,
		Neko
	}
	
	String getOutputFileName();
	void setOutputFileName(String fileName);
	
	TargetPlatform getTargetPlatform();
	void setTargetPlatform(TargetPlatform platform);
	
	int getTargetPlatformVersion();
	void setTargetPlatformVersion(int version);
	
	boolean isDebugEnabled();
	void setDebugEnabled(boolean debugEnabled);
	
	boolean isDebugInstructionsEnabled();
	void setDebugInstructionsEnabled(boolean debugInstrucitonsEnabled);
	
	void merge(IBuildParamsContainer container);	
}
