package eclihx.core.haxe.contentassist;

import java.util.ArrayList;
import java.util.List;


/**
 * Content assist result for one particular execution.
 */
public class ContentAssistResult {
	
	private List<ContentInfo> contentInfos = new ArrayList<ContentInfo>();
	private String errors;
	
	/**
	 * Construct the result with errors. 
	 * 
	 * @param errors The errors output of content processor execution.
	 */
	public ContentAssistResult(String errors) {
		this.errors = errors;
	}

	/**
	 * Construct the result with found information.
	 * 
	 * @param contentInfos information about the context.
	 */
	public ContentAssistResult(List<ContentInfo> contentInfos) {
		super();
		this.contentInfos = contentInfos;
	}
	
	/**
	 * List of context 
	 * @return the contentInfos
	 */
	public List<ContentInfo> getContentInfos() {
		return contentInfos;
	}
	
	/**
	 * Checks if current result contains errors.
	 * 
	 * @return true if there're errors.
	 */
	public boolean hasErrors() {
		return (errors != null && !errors.isEmpty());
	}

	/**
	 * Errors in execution content assist processor.		 * 
	 * @return Errors in execution content assist processor.
	 */
	public String getErrors() {
		return errors;
	}
}
