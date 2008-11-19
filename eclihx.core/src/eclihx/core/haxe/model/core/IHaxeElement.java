package eclihx.core.haxe.model.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

/**
 * Common interface to simplify working with the haXe entities.
 */
public interface IHaxeElement extends IAdaptable {
	
	/**
	 * Get the parent of the element. If the are no parent than method will
	 * return <code>null</code>.
	 * @return The element parent or null if there are no parent.
	 */
	IHaxeElement getParent();
	
	/**
	 * Get the element name.
	 * @return the element name.
	 */
	String getName();
	
	/**
	 * Get base resource.
	 * @return the resource this haXe element wraps if any. <code>null</code>
	 * if there is no such resource. 
	 */
	IResource getBaseResource();	
}
