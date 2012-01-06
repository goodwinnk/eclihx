package eclihx.ui.internal.ui.editors.hx.indent;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextUtilities;

import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Indent strategy for haxe code.
 */
public class HaxeAutoIndentStrategy implements IAutoEditStrategy {

	private final IBlockIndenter indenter;

	/**
	 * Creates a haxe indent strategy with block indenter. Given indenter should
	 * be responsible for monitoring user properties.
	 *  
	 * @param indenter Block indenter.
	 */
	public HaxeAutoIndentStrategy(IBlockIndenter indenter) {
		this.indenter = indenter;
	}
	
	/*
	 * Copied from {@see DefaultIndentLineAutoEditStrategy#findEndOfWhiteSpace()}
	 */
	protected int findEndOfWhiteSpaceAfter(IDocument document, int offset, int end) throws BadLocationException {
		while (offset < end) {
			char c = document.getChar(offset);
			if (c != ' ' && c != '\t') {
				return offset;
			}
			offset++;
		}
		return end;
	}
	
	protected int findEndOfWhiteSpaceBefore(IDocument document, int offset, int start) throws BadLocationException {
		while (offset >= start) {
			char c = document.getChar(offset);
			if (c != ' ' && c != '\t') {
				return offset;
			}
			offset--;
		}
		return start;
	}
	
	private boolean isAfterOpenBrace(IDocument document, int offset, int startLineOffset) throws BadLocationException {
		int nonEmptyOffset = findEndOfWhiteSpaceBefore(document, offset, startLineOffset);
		return document.getChar(nonEmptyOffset) == '{';
	}
	
	private boolean isBeforeCloseBrace(IDocument document, int offset, int endLineOffset) throws BadLocationException {
		int nonEmptyOffset = findEndOfWhiteSpaceAfter(document, offset, endLineOffset);
		return document.getChar(nonEmptyOffset) == '}';
	}

	private void autoIndentAfterNewLine(IDocument document, DocumentCommand command) {

		if (command.offset == -1 || document.getLength() == 0) {
			return;
		}

		try {
			// find start of line
			int p = (command.offset == document.getLength() ? command.offset  - 1 : command.offset);
			IRegion info = document.getLineInformationOfOffset(p);
			int start = info.getOffset();
			
			StringBuffer buf = new StringBuffer(command.text);

			// find white spaces
			int end = findEndOfWhiteSpaceAfter(document, start, command.offset);
			
			String lineSpaces = (end > start) ? document.get(start, end - start) : ""; 
			buf.append(lineSpaces);

			if (isAfterOpenBrace(document, command.offset - 1, start)) {				
				buf.append(indenter.getSingleBlockIndent());
				
				if (isBeforeCloseBrace(document, command.offset, info.getOffset() + info.getLength())) {					
					command.shiftsCaret = false;
					command.caretOffset = command.offset + buf.length();
					
					buf.append(command.text);
					buf.append(lineSpaces);					
				}
			}

			command.text= buf.toString();
			
			
		} catch (BadLocationException excp) {
			EclihxUIPlugin.getLogHelper().logError(excp);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.IAutoEditStrategy#customizeDocumentCommand(org.eclipse.jface.text.IDocument, org.eclipse.jface.text.DocumentCommand)
	 */
	@Override
	public void customizeDocumentCommand(IDocument document, DocumentCommand command) {
		if (command.length == 0 && command.text != null && TextUtilities.endsWith(document.getLegalLineDelimiters(), command.text) != -1) {
			autoIndentAfterNewLine(document, command);
		}
	}

}
