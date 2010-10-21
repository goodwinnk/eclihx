package eclihx.ui.internal.ui.editors;

import java.util.HashMap;

import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Color manager for haxe-file editor. 
 */
public class ColorManager implements ISharedTextColors {
	
	/**
	 * Map for storing same colors.  
	 */
	protected HashMap<RGB, Color> rgbTable = new HashMap<RGB, Color>(10);
	
	protected HashMap<String, RGB> keyBinding = new HashMap<String, RGB>(10);
	
	/**
	 * Creates a new color manager.
	 */
	public ColorManager() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ISharedTextColors#getColor(org.eclipse.swt.graphics.RGB)
	 */
	@Override
	public Color getColor(RGB rgb) {

		if (rgb == null) {
			return null;
		}

		Color color = rgbTable.get(rgb);
		
		if (color == null) {
			color= new Color(Display.getCurrent(), rgb);
			rgbTable.put(rgb, color);
		}

		return color;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.source.ISharedTextColors#dispose()
	 */
	@Override
	public void dispose() {
		for (Color color : rgbTable.values()) {
			color.dispose();
		}
		
		rgbTable.clear();
	}
}
