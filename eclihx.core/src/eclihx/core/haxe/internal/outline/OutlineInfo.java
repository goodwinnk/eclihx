package eclihx.core.haxe.internal.outline;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Outline information storage.
 */
public class OutlineInfo {
	
	private List<String> errors;
	private Module module;
	
	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}
	/**
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}
	
	/**
	 * Constructor for the situation when there're no errors and module successfully found.
	 * 
	 * @param module a module with outline info
	 */
	public OutlineInfo(Module module) {
		this(new ArrayList<String>(), module);
	}	
	
	/**
	 * Constructor for the situation when there're errors in outline information retrieve.
	 * 
	 * @param errors list of errors.
	 */
	public OutlineInfo(List<String> errors) {
		this(errors, null);
	}
	
	private OutlineInfo(List<String> errors, Module module) {
		super();
		
		if (errors.isEmpty() && module == null) {
			throw new InvalidParameterException("Can't set module to null without errors");
		}
		
		this.errors = errors;
		this.module = module;
	}
}
