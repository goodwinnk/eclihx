package eclihx.ui.internal.ui.editors.hx.indent;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import eclihx.core.haxe.model.CodeFormatter;
import eclihx.core.haxe.model.CodeFormatter.FormatOptions;
import eclihx.ui.actions.FormatAllAction;

/**
 * Haxe standard formatter options
 */
public class HaxeIndenter implements IBlockIndenter, IPropertyChangeListener {
	
	private FormatOptions preferenceOptions;
	
	/**
	 * Default indenter.
	 */
	public HaxeIndenter() {
		preferenceOptions = FormatAllAction.getPreferenceOptions();
	}
	
	@Override
	public String getSingleBlockIndent() {
		return preferenceOptions.isInsertTabs() ? "\t" : CodeFormatter.multiply(" ", preferenceOptions.getIntendWidth());
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		preferenceOptions = FormatAllAction.getPreferenceOptions();
	}

	@Override
	public boolean isBraceOnNextLine() {
		return preferenceOptions.isBracketNewLines();
	}
}
