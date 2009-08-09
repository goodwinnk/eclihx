/* Class is based on org.eclipse.jdt.internal.ui.text.JavaColorManager
 
 ********************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************
 *
 */

package eclihx.ui.internal.ui.editors.hx;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;



public class ColorManager implements ISharedTextColors {
	
	protected HashMap<String, RGB> fKeyTable= new HashMap<String, RGB>(10);
	protected HashMap<Display, HashMap<RGB, Color>> fDisplayTable = 
			new HashMap<Display, HashMap<RGB, Color>>(2);

	/**
	 * Flag which tells if the colors are automatically disposed when
	 * the current display gets disposed.
	 */
	private final boolean fAutoDisposeOnDisplayDispose;


	/**
	 * Creates a new color manager which automatically
	 * disposes the allocated colors when the current display
	 * gets disposed.
	 */
	public ColorManager() {
		this(true);
	}
	
	/**
	 * For testing purposes only.
	 * @return the table viewer in the Favorites view
	 */
	public ColorManager getFavoritesViewer() {
		return new ColorManager();
	}


	/**
	 * Creates a new color manager.
	 *
	 * @param autoDisposeOnDisplayDispose 	if <code>true</code>  the color manager
	 * automatically disposes all managed colors when the current display gets disposed
	 * and all calls to {@link org.eclipse.jface.text.source.ISharedTextColors#dispose()} are ignored.
	 *
	 */
	public ColorManager(boolean autoDisposeOnDisplayDispose) {
		fAutoDisposeOnDisplayDispose= autoDisposeOnDisplayDispose;
	}

	public void dispose(Display display) {
		HashMap<RGB, Color> colorTable = fDisplayTable.get(display);
		if (colorTable != null) {
			Iterator<Color> e = colorTable.values().iterator();
			while (e.hasNext()) {
				Color color = e.next();
				if (color != null && !color.isDisposed())
					color.dispose();
			}
		}
	}

	public Color getColor(RGB rgb) {

		if (rgb == null)
			return null;

		final Display display= Display.getCurrent();
		HashMap<RGB, Color> colorTable= fDisplayTable.get(display);
		if (colorTable == null) {
			colorTable = new HashMap<RGB, Color>(10);
			fDisplayTable.put(display, colorTable);
			if (fAutoDisposeOnDisplayDispose) {
				display.disposeExec(new Runnable() {
					public void run() {
						dispose(display);
					}
				});
			}
		}

		Color color= colorTable.get(rgb);
		if (color == null) {
			color= new Color(Display.getCurrent(), rgb);
			colorTable.put(rgb, color);
		}

		return color;
	}

	public void dispose() {
		if (!fAutoDisposeOnDisplayDispose)
			dispose(Display.getCurrent());
	}

	public Color getColor(String key) {
		if (key == null)
			return null;

		RGB rgb= fKeyTable.get(key);
		return getColor(rgb);
	}

	public void bindColor(String key, RGB rgb) {
		RGB value= fKeyTable.get(key);
		if (value != null)
			throw new UnsupportedOperationException();

		fKeyTable.put(key, rgb);
	}

	public void unbindColor(String key) {
		fKeyTable.remove(key);
	}
}
