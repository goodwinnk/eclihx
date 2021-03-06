package eclihx.ui.internal.ui.editors.hx;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.swt.graphics.Image;

import eclihx.ui.PluginImages;
import eclihx.ui.internal.ui.editors.templates.HaxeCodeTemplateContext;
import eclihx.ui.internal.ui.editors.templates.HaxeCustomTemplateManager;
import eclihx.ui.internal.ui.editors.templates.HaxeContextTypes;

/**
 * Templates for haxe language.
 * @author Anybody
 *
 */
public class HXTemplateCompletionProcessor extends TemplateCompletionProcessor {
	
	protected TemplateContextType getContextType(ITextViewer viewer, IRegion region) {
		HaxeCustomTemplateManager manager = HaxeCustomTemplateManager.getInstance();
		return manager.getContextTypeRegistry().getContextType(HaxeContextTypes.ID_STATEMENTS);
	}

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		ITextSelection selection = (ITextSelection) viewer.getSelectionProvider().getSelection();
		
		// adjust offset to end of normalized selection
		if (selection.getOffset() == offset) {
			offset = selection.getOffset() + selection.getLength();
		}
		
		String prefix = extractPrefix(viewer, offset);
		if (!prefix.equals("") && prefix.charAt(0) == '<') {
			prefix = prefix.substring(1);
		}
		
		Region region = new Region(offset - prefix.length(), prefix.length());
		TemplateContext context = createContext(viewer, region);
		if (context == null) {
			return new ICompletionProposal[0];
		}
		
		context.setVariable("selection", selection.getText()); // name of the selection variables {line, word_selection //$NON-NLS-1$
		
		Template[] templates = getTemplates(null);
		List<ICompletionProposal> matches = new ArrayList<ICompletionProposal>();
		
		for (int i = 0; i < templates.length; i++) {
			Template template = templates[i];
			try {
				context.getContextType().validate(template.getPattern());
			} catch (TemplateException e) {
				continue;
			}
			
			if (!prefix.equals("") && (template.getName().startsWith(prefix))) {				
				matches.add(createProposal(template, context, (IRegion) region, getRelevance(template, prefix)));
			}
		}
		return matches.toArray(new ICompletionProposal[matches.size()]);
	}
	
	

	@Override
	protected TemplateContext createContext(ITextViewer viewer, IRegion region) {
		return new HaxeCodeTemplateContext(
				getContextType(viewer, region), 
				viewer.getDocument(), 
				new Position(region.getOffset(), region.getLength()));
	}

	@Override
	protected Image getImage(Template template) {
		return PluginImages.get(PluginImages.IMG_TEMPLATE_PROPOSAL);
	}

	@Override
	protected Template[] getTemplates(String contextTypeId) {
		HaxeCustomTemplateManager manager = HaxeCustomTemplateManager.getInstance();
		return manager.getTemplateStore().getTemplates(contextTypeId);
	}
}