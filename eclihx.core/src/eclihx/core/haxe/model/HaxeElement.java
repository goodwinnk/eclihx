package eclihx.core.haxe.model;

import eclihx.core.haxe.model.core.IHaxeElement;

/**
 * Common behavior for all haXe elements.
 */
abstract class HaxeElement implements IHaxeElement{

	/**
	 * The parent element of this one.
	 */
	protected IHaxeElement parent;
	
	public HaxeElement(IHaxeElement parent) {
		this.parent = parent;
	}
	
	/* (non-Javadoc)
	 * @see eclihx.core.haxe.model.core.IHaxeElement#getParent()
	 */
	@Override
	public IHaxeElement getParent() {
		return parent;
	}

}
