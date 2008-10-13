package eclihx.ui.internal.ui.editors;

import java.util.ArrayList;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IRule;

import eclihx.ui.internal.ui.editors.hx.ColorManager;

public class SingleTokenScanner extends AbstractScanner {

	private final TextAttributesKey[] fAttributesKeys;

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

	@Override
	protected ArrayList<IRule> createRules() {
		setDefaultReturnToken(getToken(fAttributesKeys[0].propertyNameColor));
		return null;
	}

	@Override
	protected TextAttributesKey[] getAttributesKeys() {
		return fAttributesKeys;
	}
}
