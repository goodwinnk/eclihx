package eclihx.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;

import eclihx.core.haxe.model.CodeFormatter;
import eclihx.ui.internal.ui.EclihxUIPlugin;
import eclihx.ui.internal.ui.editors.hx.HXEditor;

/**
 * Formats the code in the particular haXe file.
 */
public class FormatAllAction implements IEditorActionDelegate {
	
	/**
	 * Current editor for the action.
	 */
	private HXEditor haxeEditor;
	
	/**
	 * Default constructor.
	 */
	public FormatAllAction() {}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorActionDelegate#setActiveEditor(org.eclipse.jface.action.IAction, org.eclipse.ui.IEditorPart)
	 */
	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		if (!(targetEditor instanceof HXEditor)) {
			haxeEditor = null;
			return;
		}
		
		haxeEditor = (HXEditor)targetEditor;			
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		IDocumentProvider dp = haxeEditor.getDocumentProvider();
		IDocument doc = dp.getDocument(haxeEditor.getEditorInput());
		String text = doc.get();
		String outputText = CodeFormatter.format(text,
					new CodeFormatter.FormatOptions());

		try {
			doc.replace(0, doc.getLength(), outputText);
		} catch (BadLocationException e) {
			EclihxUIPlugin.getLogHelper().logError(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}
}
