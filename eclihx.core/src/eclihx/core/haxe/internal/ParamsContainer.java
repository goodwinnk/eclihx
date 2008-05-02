package eclihx.core.haxe.internal;

public class ParamsContainer implements IBuildParamsContainer {

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	private IBuildParamsContainer.TargetPlatform platform = null;
	private String fileName;
	
	@Override
	public String getOutputFileName() {
		return fileName;
	}

	@Override
	public TargetPlatform getTargetPlatform() {
		return platform;
	}

	@Override
	public void merge(IBuildParamsContainer container) {
		// TODO Implement this
		return;
	}

	@Override
	public void setOutputFileName(String fileName_) {
		fileName = fileName_;
	}

	@Override
	public void setTargetPlatform(TargetPlatform platform_) {
		platform = platform_;
	}
	
	

}
