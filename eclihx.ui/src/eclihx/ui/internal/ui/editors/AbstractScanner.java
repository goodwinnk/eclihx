/* Based on org.eclipse.jdt.internal.ui.text.AbstractJavaScanner.java 
 *******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************
 */

package eclihx.ui.internal.ui.editors;

import java.util.HashMap;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.util.PropertyChangeEvent;

import eclihx.ui.internal.ui.editors.hx.ColorManager;


/**
 * Initialized with a color manager and a preference store, its subclasses are
 * only responsible for providing a list of preference keys for based on which tokens
 * are generated and to use this tokens to define the rules controlling this scanner.
 * <p>
 * This scanner stores the color defined by the color preference key into
 * the color manager under the same key.
 * </p>
 * <p>
 * Preference color key + {@link PreferenceConstants#EDITOR_BOLD_SUFFIX} are used
 * to retrieve whether the token is rendered in bold.
 * </p>
 * <p>
 * Preference color key + {@link PreferenceConstants#EDITOR_ITALIC_SUFFIX} are used
 * to retrieve whether the token is rendered in italic.
 * </p>
 * <p>
 * Preference color key + {@link PreferenceConstants#EDITOR_STRIKETHROUGH_SUFFIX} are used
 * to retrieve whether the token is rendered in strikethrough.
 * </p>
 * <p>
 * Preference color key + {@link PreferenceConstants#EDITOR_UNDERLINE_SUFFIX} are used
 * to retrieve whether the token is rendered in underline.
 * </p>
 */
public abstract class AbstractScanner extends BufferedRuleBasedScanner {
	
	/**
	 * Class for storing keys for text attribute 
	 */
	public static class TextAttributesKey {
		
		public TextAttributesKey(String colorKey, 
				           String boldKey, 
				           String italicKey, 
				           String strikethroughKey,
				           String underlineKey) {
			
			this.propertyNameColor = colorKey;
			this.propertyNameBold = boldKey;
			this.propertyNameItalic = italicKey;
			this.propertyNameStrikethrough = strikethroughKey;
			this.propertyNameUnderline = underlineKey;			
		}
		
		/**
		 * Preference keys for colors
		 */
		public String propertyNameColor;
		
		/**
		 * Preference keys for boolean preferences which are <code>true</code>,
		 * if the corresponding token should be rendered bold.
		 */
		public String propertyNameBold;
		
		/**
		 * Preference keys for boolean preferences which are <code>true</code>,
		 * if the corresponding token should be rendered italic.
		 */
		public String propertyNameItalic;
		
		/**
		 * Preference keys for boolean preferences which are <code>true</code>,
		 * if the corresponding token should be rendered strikethrough.
		 */
		public String propertyNameStrikethrough;
		
		/**
		 * Preference keys for boolean preferences which are <code>true</code>,
		 * if the corresponding token should be rendered underline.
		 */
		public String propertyNameUnderline;
	}
	
	private ColorManager fColorManager;
	private IPreferenceStore fPreferenceStore;

	private HashMap<String, Token> fTokenMap= new HashMap<String, Token>();
	
	private TextAttributesKey[] fAttributesKeys;
	
	private boolean fNeedsLazyColorLoading;

	/**
	 * Returns an array of preference keys which define the TextAttributes
	 * used in the rules of this scanner.
	 */
	abstract protected TextAttributesKey[] getAttributesKeys();

	/**
	 * Creates the list of rules controlling this scanner.
	 */
	abstract protected ArrayList<IRule> createRules();


	/**
	 * Creates an abstract scanner.
	 */
	public AbstractScanner(ColorManager manager, IPreferenceStore store) {
		super();
		fColorManager = manager;
		fPreferenceStore = store;
	}

	/**
	 * Must be called after the constructor has been called.
	 */
	public final void initialize() {

		fAttributesKeys = getAttributesKeys();
		fNeedsLazyColorLoading = (Display.getCurrent() == null);
		
		for (TextAttributesKey key: fAttributesKeys) {
			if (fNeedsLazyColorLoading)
				addTokenWithProxyAttribute(key);
			else
				addToken(key);
		}

		initializeRules();
	}
	
	public IToken nextToken() {
		if (fNeedsLazyColorLoading)
			resolveProxyAttributes();
		return super.nextToken();
	}

	private void resolveProxyAttributes() {
		if (fNeedsLazyColorLoading && Display.getCurrent() != null) {
			for (TextAttributesKey keys: fAttributesKeys) {
				addToken(keys);
			}
			fNeedsLazyColorLoading= false;
		}
	}

	private void addTokenWithProxyAttribute(TextAttributesKey keys) {
		TextAttributesKey nullColorAttributes = new TextAttributesKey(null, 
				                                                        keys.propertyNameBold, 
				                                                        keys.propertyNameItalic,
				                                                        keys.propertyNameStrikethrough,
				                                                        keys.propertyNameUnderline);
		fTokenMap.put(keys.propertyNameColor, new Token(createTextAttribute(nullColorAttributes)));
	}

	private void addToken(TextAttributesKey key) {
		if (fColorManager != null && key.propertyNameColor != null && fColorManager.getColor(key.propertyNameColor) == null) {
			RGB rgb= PreferenceConverter.getColor(fPreferenceStore, key.propertyNameColor);
			fColorManager.getColor(key.propertyNameColor);
			fColorManager.unbindColor(key.propertyNameColor);
			fColorManager.bindColor(key.propertyNameColor, rgb);
		}

		if (!fNeedsLazyColorLoading)
			fTokenMap.put(key.propertyNameColor, new Token(createTextAttribute(key)));
		else {
			Token token= fTokenMap.get(key.propertyNameColor);
			if (token != null)
				token.setData(createTextAttribute(key));
		}
	}

	/**
	 * Create a text attribute based on the given color, bold, italic, strikethrough and underline preference keys.
	 *
	 * @param colorKey the color preference key
	 * @param boldKey the bold preference key
	 * @param italicKey the italic preference key
	 * @param strikethroughKey the strikethrough preference key
	 * @param underlineKey the italic preference key
	 * @return the created text attribute
	 */
	private TextAttribute createTextAttribute(TextAttributesKey keys) {
		Color color = null;
		if (keys.propertyNameColor != null)
			color = fColorManager.getColor(keys.propertyNameColor);

		int style = SWT.NORMAL;
		
		if (keys.propertyNameBold != null && fPreferenceStore.getBoolean(keys.propertyNameBold))
			style |= SWT.BOLD;
		
		if (keys.propertyNameItalic != null && fPreferenceStore.getBoolean(keys.propertyNameItalic))
			style |= SWT.ITALIC;

		if (keys.propertyNameStrikethrough != null && fPreferenceStore.getBoolean(keys.propertyNameStrikethrough))
			style |= TextAttribute.STRIKETHROUGH;

		if (keys.propertyNameUnderline != null && fPreferenceStore.getBoolean(keys.propertyNameUnderline))
			style |= TextAttribute.UNDERLINE;

		return new TextAttribute(color, null, style);
	}

	protected Token getToken(String key) {
		if (fNeedsLazyColorLoading)
			resolveProxyAttributes();
		return fTokenMap.get(key);
	}

	private void initializeRules() {
		ArrayList<IRule> rules= createRules();
		if (rules != null) {
			IRule[] result= new IRule[rules.size()];
			rules.toArray(result);
			setRules(result);
		}
	}

	private int indexOf(String property) {
		if (property != null) {
			int length= fAttributesKeys.length;
			for (int i= 0; i < length; i++) {
				if (property.equals(fAttributesKeys[i].propertyNameColor) || 
					property.equals(fAttributesKeys[i].propertyNameBold) || 
					property.equals(fAttributesKeys[i].propertyNameItalic) || 
					property.equals(fAttributesKeys[i].propertyNameStrikethrough) || 
					property.equals(fAttributesKeys[i].propertyNameUnderline)) {
					return i;
				}
			}
		}
		return -1;
	}

	public boolean affectsBehavior(PropertyChangeEvent event) {
		return indexOf(event.getProperty()) >= 0;
	}

	public void adaptToPreferenceChange(PropertyChangeEvent event) {
		String p= event.getProperty();
		int index= indexOf(p);
		Token token= getToken(fAttributesKeys[index].propertyNameColor);
		if (fAttributesKeys[index].propertyNameColor.equals(p))
			adaptToColorChange(token, event);
		else if (fAttributesKeys[index].propertyNameBold.equals(p))
			adaptToStyleChange(token, event, SWT.BOLD);
		else if (fAttributesKeys[index].propertyNameItalic.equals(p))
			adaptToStyleChange(token, event, SWT.ITALIC);
		else if (fAttributesKeys[index].propertyNameStrikethrough.equals(p))
			adaptToStyleChange(token, event, TextAttribute.STRIKETHROUGH);
		else if (fAttributesKeys[index].propertyNameUnderline.equals(p))
			adaptToStyleChange(token, event, TextAttribute.UNDERLINE);
	}

	private void adaptToColorChange(Token token, PropertyChangeEvent event) {
		RGB rgb = null;

		Object value = event.getNewValue();
		if (value instanceof RGB)
			rgb = (RGB) value;
		else if (value instanceof String)
			rgb = StringConverter.asRGB((String) value);

		if (rgb != null) {

			String property = event.getProperty();
			Color color = fColorManager.getColor(property);

			if (color == null || !rgb.equals(color.getRGB())) {
				fColorManager.unbindColor(property);
				fColorManager.bindColor(property, rgb);
				color = fColorManager.getColor(property);
			}

			Object data= token.getData();
			if (data instanceof TextAttribute) {
				TextAttribute oldAttr= (TextAttribute) data;
				token.setData(new TextAttribute(color, oldAttr.getBackground(), oldAttr.getStyle()));
			}
		}
	}

	private void adaptToStyleChange(Token token, PropertyChangeEvent event, int styleAttribute) {
		boolean eventValue= false;
		Object value= event.getNewValue();
		if (value instanceof Boolean)
			eventValue= ((Boolean) value).booleanValue();
		else if (IPreferenceStore.TRUE.equals(value))
			eventValue= true;

		Object data= token.getData();
		if (data instanceof TextAttribute) {
			TextAttribute oldAttr= (TextAttribute) data;
			boolean activeValue= (oldAttr.getStyle() & styleAttribute) == styleAttribute;
			if (activeValue != eventValue)
				token.setData(new TextAttribute(oldAttr.getForeground(), oldAttr.getBackground(), eventValue ? oldAttr.getStyle() | styleAttribute : oldAttr.getStyle() & ~styleAttribute));
		}
	}

	/**
	 * Returns the preference store.
	 *
	 * @return the preference store.
	 */
	protected IPreferenceStore getPreferenceStore() {
		return fPreferenceStore;
	}
}
