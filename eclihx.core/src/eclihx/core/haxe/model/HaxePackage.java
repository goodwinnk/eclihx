package eclihx.core.haxe.model;

import eclihx.core.haxe.model.core.IHaxeSourceFolder;

/**
 * First implementation of the haXe package element.
 */
public class HaxePackage extends HaxeElement {

	/**
	 * Default constructor.
	 * @param parent source folder where this package is situated.
	 */
	public HaxePackage(IHaxeSourceFolder parent) {
		super(parent);
	}
	
	
}
