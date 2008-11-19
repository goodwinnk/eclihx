package eclihx.core.haxe.model;

import org.eclipse.core.runtime.PlatformObject;

import eclihx.core.haxe.model.core.IHaxeElement;

/**
 * Common behavior for all haXe elements.
 */
abstract class HaxeElement extends PlatformObject implements IHaxeElement {

	/**
	 * The parent element of this one.
	 */
	protected IHaxeElement parent;
	
	/**
	 * Constructor with parent element.
	 * @param parent the parent element. <code>null</code> for root elements.
	 */
	public HaxeElement(IHaxeElement parent) {
		this.parent = parent;
	}
	
	/* (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getParent()
	 */
	@Override
	public final IHaxeElement getParent() {
		return parent;
	}
}
