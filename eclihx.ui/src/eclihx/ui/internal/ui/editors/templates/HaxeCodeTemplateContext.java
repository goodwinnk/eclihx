package eclihx.ui.internal.ui.editors.templates;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.templates.DocumentTemplateContext;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;

/**
 * A context for inserting template into haxe code.
 */
public class HaxeCodeTemplateContext extends DocumentTemplateContext {
	/**
	 * Constructor.
	 * 
	 * @param type context type.
	 * @param document document.
	 * @param position position.
	 */
	public HaxeCodeTemplateContext(TemplateContextType type,
			IDocument document, Position position) {
		super(type, document, position);
	}

	@Override
	public TemplateBuffer evaluate(Template template)
			throws BadLocationException, TemplateException {
		
		TemplateBuffer evaluateBuffer = super.evaluate(template);
		(new TemplateFormatter()).format(evaluateBuffer, getLineIndentation());
		return evaluateBuffer;
	}
	
	private String getLineIndentation() {
		int start = getStart();
	    
		IDocument document = getDocument();
		try {
			IRegion region = document.getLineInformationOfOffset(start);
			return document.get(region.getOffset(), getStart() - region.getOffset());
		} catch (BadLocationException e) {
			return "";
		}
	}
}
