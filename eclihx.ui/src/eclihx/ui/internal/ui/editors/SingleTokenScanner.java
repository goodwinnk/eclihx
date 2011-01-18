package eclihx.ui.internal.ui.editors;

import java.util.ArrayList;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;


/**
 * Extension of the AbstractScanner which allows to redefine default token with
 * the generated one on the base of the plug-in code style preferences.
 */
public class SingleTokenScanner extends AbstractScanner {

	/**
	 * Preferences keys for the default token style. 
	 */
	private final TextAttributesKey[] fAttributesKeys;

	/**
	 * Constructor.
	 * @param manager the color manager.
	 * @param store the store where could be found the preferences for the
	 *        default token style.
	 * @param colorPropertyKey the color property key.
	 * @param boldPropertyKey the bold property key.
	 * @param italicPropertyKey the italic property key.
	 */
	public SingleTokenScanner(ColorManager manager, IPreferenceStore store, 
			                  String colorPropertyKey, 
			                  String boldPropertyKey, 
			                  String italicPropertyKey) {
		
		super(manager, store);
		
		TextAttributesKey keys = new TextAttributesKey(
										colorPropertyKey, 
										boldPropertyKey, 
										italicPropertyKey, 
										null, 
										null);
		
		fAttributesKeys = new TextAttributesKey[] { keys };
		
		initialize();
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.ui.internal.ui.editors.AbstractScanner#createRules()
	 */
	@Override
	protected ArrayList<IRule> createRules() {
		setDefaultReturnToken(getToken(fAttributesKeys[0].propertyNameColor));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see eclihx.ui.internal.ui.editors.AbstractScanner#getAttributesKeys()
	 */
	@Override
	protected TextAttributesKey[] getAttributesKeys() {
		return fAttributesKeys;
	}
}
