package eclihx.core.haxe.model.core;

/**
 * Common interface to simplify working with the haXe entities.
 */
public interface IHaxeElement {
	
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
	
}
