package eclihx.core.haxe.internal;

public class ParamsContainer implements IBuildParamsContainer {

	private IBuildParamsContainer.TargetPlatform platform = null;

	
	private int platformVersion;
	
	private String fileName = null;
	private boolean debugEnabled = false;
	private boolean debugInstructionsEnabled = false;
	
	public String getOutputFileName() {
		return fileName;
	}

	public TargetPlatform getTargetPlatform() {
		return platform;
	}

	public void merge(IBuildParamsContainer container) {
		// TODO Implement this
		return;
	}

	public void setOutputFileName(String fileName_) {
		fileName = fileName_;
	}

	public void setTargetPlatform(TargetPlatform platform_) {
		platform = platform_;
	}
	
	/* (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IBuildParamsContainer#isDebugEnabled()
	 */
	public boolean isDebugEnabled() {
		return debugEnabled;
	}

	/* (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IBuildParamsContainer#isDebugInstructionsEnabled()
	 */
	public boolean isDebugInstructionsEnabled() {
		return debugInstructionsEnabled;
	}

	/* (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IBuildParamsContainer#setDebugEnabled(boolean)
	 */
	public void setDebugEnabled(boolean debugEnabled) {
		this.debugEnabled = debugEnabled;
	}

	/* (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IBuildParamsContainer#setDebugInstructionsEnabled(boolean)
	 */
	public void setDebugInstructionsEnabled(boolean debugInstrucitonsEnabled) {
		this.debugInstructionsEnabled = debugInstrucitonsEnabled;
	}

	/* (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IBuildParamsContainer#getTargetPlatformVersion()
	 */
	public int getTargetPlatformVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see eclihx.core.haxe.internal.IBuildParamsContainer#setTargetPlatformVersion(int)
	 */
	public void setTargetPlatformVersion(int version) {
		this.platformVersion = version;		
	}
	
}
