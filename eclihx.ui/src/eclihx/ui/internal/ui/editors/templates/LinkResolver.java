package eclihx.ui.internal.ui.editors.templates;

import java.util.List;

import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

/**
 * Resolver for the <code>link</code> variable. Resolves to a
 * first parameter puts the value into linked mode. Shows proposals
 * for each parameter.
 *
 * @since 3.4
 */
public class LinkResolver extends TemplateVariableResolver {

	private String[] fProposals;

	/**
	 * Constructor for {@link TemplateVariableResolver#TemplateVariableResolver()}
	 * @param type the name of the type
	 * @param description the description for the type
	 */
	public LinkResolver(String type, String description) {
		super(type, description);
	}

	/**
	 * Default ctor for instantiation by the extension point.
	 */
	public LinkResolver() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.templates.TemplateVariableResolver#resolve(org.eclipse.jface.text.templates.TemplateVariable, org.eclipse.jface.text.templates.TemplateContext)
	 */
	@Override
	public void resolve(TemplateVariable variable, TemplateContext context) {

		variable.setUnambiguous(false);

		@SuppressWarnings("unchecked")
		List<String> params= variable.getVariableType().getParams();
		if (params.size() > 0) {
			fProposals = params.toArray(new String[params.size()]);
			variable.setValues(fProposals);
			return;
		} else {
			fProposals = new String[] { variable.getDefaultValue() };
		}
		
		super.resolve(variable, context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.templates.TemplateVariableResolver#resolveAll(org.eclipse.jface.text.templates.TemplateContext)
	 */
	@Override
	protected String[] resolveAll(TemplateContext context) {
		return fProposals;
	}
}
