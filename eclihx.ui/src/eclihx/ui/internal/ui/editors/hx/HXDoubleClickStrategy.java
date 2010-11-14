package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultTextDoubleClickStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;

/**
 * A special double click strategy for the editor.
 * It has and advantage to see parts of identifiers  
 */
public class HXDoubleClickStrategy extends DefaultTextDoubleClickStrategy {
	
	@Override
	protected IRegion findWord(IDocument document, int offset) {
		try {
			
			int length = document.getLength();
			
			if (offset >= length)
			{
				return null;
			}
			
			if (!Character.isJavaIdentifierPart(document.getChar(offset)))
			{
				return new Region(offset, 1);
			}
			
			int pos;
			for (pos = offset; pos >= 0; --pos) {
				if (!Character.isJavaIdentifierPart(document.getChar(pos)))
				{
					break;
				}
		    }
			
			int startWordOffset = pos + 1 < length ? pos + 1 : pos;		    
		    
		    int endWordOffset;
		    for (endWordOffset = offset; endWordOffset < length; ++endWordOffset)
		    {
		    	if (!Character.isJavaIdentifierPart(document.getChar(endWordOffset)))
		    	{
		            break;
		    	}
		    }
		    
		    return new Region(startWordOffset, endWordOffset - startWordOffset);	 
		    
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}