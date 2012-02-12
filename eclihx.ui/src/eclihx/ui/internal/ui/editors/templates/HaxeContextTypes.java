package eclihx.ui.internal.ui.editors.templates;

import org.eclipse.jface.text.templates.GlobalTemplateVariables;
import org.eclipse.jface.text.templates.TemplateContextType;

/**
 * The context type for templates inside Java code.
 */
public class HaxeContextTypes extends TemplateContextType {
	/**
	 * The context type id for templates working on member locations.
	 */
	public static final String ID_MEMBERS= "haxe-members"; //$NON-NLS-1$

	/**
	 * The context type id for templates working on statement locations.
	 */
	public static final String ID_STATEMENTS= "haxe-statements"; //$NON-NLS-1$
	
	/**
	 * Default constructor.
	 */
	public HaxeContextTypes() {
		addResolver(new GlobalTemplateVariables.Cursor());
		addResolver(new GlobalTemplateVariables.WordSelection());
		addResolver(new GlobalTemplateVariables.Dollar());
		addResolver(new GlobalTemplateVariables.Date());
		addResolver(new GlobalTemplateVariables.Year());
		addResolver(new GlobalTemplateVariables.Time());
		addResolver(new GlobalTemplateVariables.User());
		
		addResolver(new LinkResolver("link", "empty"));
		// addResolver(new LinkResolver(ID_STATEMENTS, "empty"));
	}
}
