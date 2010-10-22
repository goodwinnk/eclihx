/* Based on org.eclipse.jdt.internal.ui.text.AbstractJavaScanner.java */

package eclihx.ui.internal.ui.editors;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Initialized with a color manager and a preference store, its subclasses are
 * only responsible for providing a list of preference keys for based on which tokens
 * are generated and to use this tokens to define the rules controlling this scanner.
 */
public abstract class AbstractScanner extends BufferedRuleBasedScanner {
	
	/**
	 * Class for storing keys for text attribute 
	 */
	public static class TextAttributesKey {
		
		/**
		 * Default constructor with all keys.
		 * @param colorKey the color key.
		 * @param boldKey the bold key.
		 * @param italicKey the italic key.
		 * @param strikethroughKey the strike through key.
		 * @param underlineKey the underline key.
		 */
		public TextAttributesKey(
				String colorKey, 
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
	
	private final ColorManager fColorManager;
	private final IPreferenceStore fPreferenceStore;

	private final HashMap<String, Token> fTokenMap = new HashMap<String, Token>();
	
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
	 * @param manager color manager.
	 * @param store the store with the preferences.
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
		
		// User doesn't see anything now, so defer loading of resources 
		fNeedsLazyColorLoading = (Display.getCurrent() == null);
		
		for (TextAttributesKey key: fAttributesKeys) {
			if (fNeedsLazyColorLoading) {
				addTokenWithProxyAttribute(key);
			} else {
				addToken(key);
			}
		}

		initializeRules();
	}
	
	
	/**
	 * Returns the next token in the document
	 */
	@Override
	public IToken nextToken() {
		if (fNeedsLazyColorLoading) {
			// Now we need real attributes...
			resolveProxyAttributes();
		}
		
		return super.nextToken();
	}

	
	/**
	 * Adds real tokens
	 */	
	private void resolveProxyAttributes() {
		if (fNeedsLazyColorLoading && Display.getCurrent() != null) {
			for (TextAttributesKey keys: fAttributesKeys) {
				addToken(keys);
			}
			
			// We don't need it anymore
			fNeedsLazyColorLoading = false;
		}
	}
	
	/**
	 * Here we store tokens without colors
	 * @param keys Keys in preferences store for tokens
	 */
	private void addTokenWithProxyAttribute(TextAttributesKey keys) {
		TextAttributesKey nullColorAttributes = 
			new TextAttributesKey(null, 
				keys.propertyNameBold, 
				keys.propertyNameItalic,
				keys.propertyNameStrikethrough,
				keys.propertyNameUnderline
			);
		
		fTokenMap.put(keys.propertyNameColor, new Token(createTextAttribute(nullColorAttributes)));
	}

	
	/**
	 * Creates all field for the token
	 * @param key
	 */
	private void addToken(TextAttributesKey key) {
		if (!fNeedsLazyColorLoading) {
			// Store token without a color
			fTokenMap.put(key.propertyNameColor, new Token(createTextAttribute(key)));
		
		} else {
			
			// Gets token and set it's color value
			Token token= fTokenMap.get(key.propertyNameColor);
			if (token != null) {
				token.setData(createTextAttribute(key));
			}
		}
	}

	
	/**
	 * Create TextAttribute object from the bunch of preferences keys
	 * @param keys bunch of preferences keys for that attribute
	 * @return generated TextAttribute object
	 */
	private TextAttribute createTextAttribute(TextAttributesKey keys) {
		
		RGB rgbColor = null;
		if (keys.propertyNameColor != null) {
			rgbColor = PreferenceConverter.getColor(fPreferenceStore, keys.propertyNameColor);
		}

		int style = SWT.NORMAL;
		
		if (keys.propertyNameBold != null && fPreferenceStore.getBoolean(keys.propertyNameBold)) {			
			style |= SWT.BOLD;
		}
		
		if (keys.propertyNameItalic != null && fPreferenceStore.getBoolean(keys.propertyNameItalic)) {
			style |= SWT.ITALIC;
		}

		if (keys.propertyNameStrikethrough != null && fPreferenceStore.getBoolean(keys.propertyNameStrikethrough)) {
			style |= TextAttribute.STRIKETHROUGH;
		}

		if (keys.propertyNameUnderline != null && fPreferenceStore.getBoolean(keys.propertyNameUnderline)) {
			style |= TextAttribute.UNDERLINE;
		}

		return new TextAttribute(fColorManager.getColor(rgbColor), null, style);
	}
	
	
	/**
	 * Gets token by color key
	 * @param key
	 * @return found token
	 */
	protected Token getToken(String key) {
		if (fNeedsLazyColorLoading)
		{
			resolveProxyAttributes();
		}
		
		return fTokenMap.get(key);
	}

	
	/**
	 * Initializes rules
	 */
	private void initializeRules() {
		ArrayList<IRule> rules = createRules();
		if (rules != null) {
			IRule[] result = new IRule[rules.size()];
			rules.toArray(result);
			setRules(result);
		}
	}

	
	/**
	 * Find if there exist a token affected with the changing of the property
	 * @param propertyName name of the property
	 * @return affected TextAttributesKey objected or null if such object wasn't found
	 */
	private TextAttributesKey getAffectedKeys(String propertyName) {
		
		if (propertyName != null) {
			int length= fAttributesKeys.length;
			for (int i= 0; i < length; i++) {
				if (propertyName.equals(fAttributesKeys[i].propertyNameColor) || 
					propertyName.equals(fAttributesKeys[i].propertyNameBold) || 
					propertyName.equals(fAttributesKeys[i].propertyNameItalic) || 
					propertyName.equals(fAttributesKeys[i].propertyNameStrikethrough) || 
					propertyName.equals(fAttributesKeys[i].propertyNameUnderline)) {
					return fAttributesKeys[i];
				}
			}
		}
		
		return null;
	}

	
	/**
	 * Finds out if this property change affects to some text attribute
	 * @param event contains info about changed attribute
	 * @return true if this property event affect to this scanner
	 */
	public boolean affectsBehavior(PropertyChangeEvent event) {
		return getAffectedKeys(event.getProperty()) != null;
	}

	
	/**
	 * Adapt this scanner to preferences change 
	 * @param event Event of preference change
	 */
	public void adaptToPreferenceChange(PropertyChangeEvent event) {
		String propertyName = event.getProperty();
		TextAttributesKey affectedKeys = getAffectedKeys(propertyName);
		
		if (affectedKeys == null) {
			return;
		}
		
		Token token = getToken(affectedKeys.propertyNameColor);
		
		if (affectedKeys.propertyNameColor.equals(propertyName)) {
			adaptToColorChange(token, event);
		} else if (affectedKeys.propertyNameBold.equals(propertyName)) {
			adaptToStyleChange(token, event, SWT.BOLD);
		} else if (affectedKeys.propertyNameItalic.equals(propertyName)) {
			adaptToStyleChange(token, event, SWT.ITALIC);
		} else if (affectedKeys.propertyNameStrikethrough.equals(propertyName)) {
			adaptToStyleChange(token, event, TextAttribute.STRIKETHROUGH);
		} else if (affectedKeys.propertyNameUnderline.equals(propertyName)) {
			adaptToStyleChange(token, event, TextAttribute.UNDERLINE);
		}
	}

	
	/**
	 * Change color in token after property change event
	 * @param token Token which should be changed
	 * @param event Event containing info about event 
	 */
	private void adaptToColorChange(Token token, PropertyChangeEvent event) {

		// Retrieve value from the event
		RGB rgb = null;
		Object value = event.getNewValue();
		if (value instanceof RGB) {
			rgb = (RGB) value;
		} else if (value instanceof String) {
			rgb = StringConverter.asRGB((String) value);
		}

		// Replace color
		if (rgb != null) {
			Color color = fColorManager.getColor(rgb);

			Object data = token.getData();
			if (data instanceof TextAttribute) {
				TextAttribute oldAttr = (TextAttribute) data;
				token.setData(new TextAttribute(color, oldAttr.getBackground(), oldAttr.getStyle()));
			}
		}
	}

	
	/**
	 * Change token style option after property change event
	 * @param token Token which should be changed
	 * @param event Event containing info about event 
	 * @param styleAttribute Style attribute which should be changed
	 */
	private void adaptToStyleChange(Token token, PropertyChangeEvent event, int styleAttribute) {
		
		// Retrieve value from the event
		boolean eventValue= false;
		Object value = event.getNewValue();
		if (value instanceof Boolean) {
			eventValue = ((Boolean) value).booleanValue();
		} else if (IPreferenceStore.TRUE.equals(value)) {
			eventValue = true;
		}

		// Change style option
		Object data= token.getData();
		if (data instanceof TextAttribute) {
			TextAttribute oldAttr = (TextAttribute) data;
			boolean activeValue = (oldAttr.getStyle() & styleAttribute) == styleAttribute;
			if (activeValue != eventValue) {
				int newStyle = eventValue ? (oldAttr.getStyle() | styleAttribute) : (oldAttr.getStyle() & ~styleAttribute);
				token.setData(new TextAttribute(oldAttr.getForeground(), oldAttr.getBackground(), newStyle));
			}
		}
	}
	

	/**
	 * Returns the preference store.
	 * @return the preference store.
	 */
	protected IPreferenceStore getPreferenceStore() {
		return fPreferenceStore;
	}
}
