package eclihx.ui.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ResourceAction;
import org.eclipse.ui.texteditor.TextEditorAction;

import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Toggle comment action class. Can be used for line/block comment/uncomment actions.
 */
public final class ToggleCommentAction extends TextEditorAction {

	/** The text operation target */
	private ITextOperationTarget operationTarget;
	
	/** The document partitioning */
	private String documentPartitioning;
	
	/** The comment prefixes */
	private Map<String, String[]> prefixesMap;

	/**
	 * Creates and initializes the action for the given text editor. The action
	 * configures its visual representation from the given resource bundle.
	 * 
	 * @param bundle the resource bundle
	 * @param prefix a prefix to be prepended to the various resource keys
	 *        (described in <code>ResourceAction</code> constructor), or
	 *        <code>null</code> if none
	 * @param editor the text editor
	 * @see ResourceAction#ResourceAction(ResourceBundle, String, int)
	 */
	public ToggleCommentAction(ResourceBundle bundle, String prefix, ITextEditor editor) {
		super(bundle, prefix, editor);
	}

	/**
	 * Implementation of the <code>IAction</code> prototype. Checks if the
	 * selected lines are all commented or not and uncomments/comments them
	 * respectively.
	 */
	public void run() {
		if (operationTarget == null || documentPartitioning == null || prefixesMap == null) {
			return;
		}

		ITextEditor editor = getTextEditor();
		if (editor == null) {
			return;
		}

		if (!validateEditorInputState()) {
			return;
		}

		final int operationCode;
		if (isSelectionCommented(editor.getSelectionProvider().getSelection())) {
			operationCode = ITextOperationTarget.STRIP_PREFIX;
		} else {
			operationCode = ITextOperationTarget.PREFIX;
		}

		Shell shell = editor.getSite().getShell();
		if (!operationTarget.canDoOperation(operationCode)) {
			if (shell != null) {
				MessageDialog.openError(shell, "Error", "An error during executing a toggle comment command");
			}
			return;
		}

		Display display = null;
		if (shell != null && !shell.isDisposed()) {
			display = shell.getDisplay();
		}

		BusyIndicator.showWhile(display, new Runnable() {
			public void run() {
				operationTarget.doOperation(operationCode);
			}
		});
	}

	/**
	 * Is the given selection single-line commented?
	 * 
	 * @param selection Selection to check
	 * @return <code>true</code> if all selected lines are commented
	 */
	private boolean isSelectionCommented(ISelection selection) {
		if (!(selection instanceof ITextSelection)) {
			return false;
		}

		ITextSelection textSelection = (ITextSelection) selection;
		if (textSelection.getStartLine() < 0 || textSelection.getEndLine() < 0) {
			return false;
		}
		
		IDocument document = getTextEditor().getDocumentProvider().getDocument(
				getTextEditor().getEditorInput());

		try {
			IRegion block = getTextBlockFromSelection(textSelection, document);
			ITypedRegion[] regions = TextUtilities.computePartitioning(
					document, documentPartitioning, block.getOffset(),
					block.getLength(), false);
			
			for (ITypedRegion typedRegion : regions) {
				int startLineNumber = getFirstCompleteLineOfRegion(typedRegion, document);
				int endLineNumber = -1;
				
				if (startLineNumber != -1) {
					// end line of region
					int length = typedRegion.getLength();
					int offset = typedRegion.getOffset() + length;
					if (length > 0) {
						offset--;
					}
					
					endLineNumber = document.getLineOfOffset(offset); 
				}
				
				String[] prefixes = (String[]) prefixesMap.get(typedRegion.getType());
				if (prefixes != null && prefixes.length > 0) {
					if (startLineNumber >= 0 && endLineNumber >= 0) {
						if (!isBlockCommented(startLineNumber, endLineNumber, prefixes, document)) {
							return false;
						}
					}
				}
			}

			return true;

		} catch (BadLocationException x) {
			// should not happen
			EclihxUIPlugin.getLogHelper().logError(x);
		}

		return false;
	}

	/**
	 * Creates a region describing the text block (something that starts at the
	 * beginning of a line) completely containing the current selection.
	 * 
	 * @param selection The selection to use
	 * @param document The document
	 * @return the region describing the text block comprising the given
	 *         selection
	 */
	private IRegion getTextBlockFromSelection(ITextSelection selection, IDocument document) {

		try {
			IRegion line = document.getLineInformationOfOffset(selection.getOffset());
			int length = selection.getLength() == 0 ? 
					line.getLength() : 
					selection.getLength() + (selection.getOffset() - line.getOffset());
			return new Region(line.getOffset(), length);
		} catch (BadLocationException x) {
			// should not happen
			EclihxUIPlugin.getLogHelper().logError(x);
		}

		return null;
	}

	/**
	 * Returns the index of the first line whose start offset is in the given
	 * text range.
	 * 
	 * @param region the text range in characters where to find the line
	 * @param document The document
	 * @return the first line whose start index is in the given range, -1 if
	 *         there is no such line
	 */
	private int getFirstCompleteLineOfRegion(IRegion region, IDocument document) {
		try {
			final int startLine = document.getLineOfOffset(region.getOffset());

			int offset = document.getLineOffset(startLine);
			if (offset >= region.getOffset()) {				
				return startLine;
			}

			final int nextLine = startLine + 1;
			if (nextLine == document.getNumberOfLines()) {
				return -1;
			}

			offset = document.getLineOffset(nextLine);
			return (offset > region.getOffset() + region.getLength() ? -1 : nextLine);

		} catch (BadLocationException x) {
			// should not happen
			EclihxUIPlugin.getLogHelper().logError(x);
		}

		return -1;
	}

	/**
	 * Determines whether each line is prefixed by one of the prefixes.
	 * 
	 * @param startLine Start line in document
	 * @param endLine End line in document
	 * @param prefixes Possible comment prefixes
	 * @param document The document
	 * @return <code>true</code> if each line from <code>startLine</code> to
	 *         and including <code>endLine</code> is prepended by one of the
	 *         <code>prefixes</code>, ignoring whitespace at the begin of line
	 */
	private boolean isBlockCommented(int startLine, int endLine, String[] prefixes, IDocument document) {

		try {
			// check for occurrences of prefixes in the given lines
			for (int i = startLine; i <= endLine; i++) {

				IRegion line = document.getLineInformation(i);
				String text = document.get(line.getOffset(), line.getLength());

				int[] found = TextUtilities.indexOf(prefixes, text, 0);
				if (found[0] == -1) {
					return false;
				}
				
				String foundPrefix = prefixes[found[1]];
				String textLinePrefix = text.substring(0, foundPrefix.length());
				if (!textLinePrefix.equals(foundPrefix)) {
					return false;
				}
			}

			return true;

		} catch (BadLocationException x) {
			// should not happen
			EclihxUIPlugin.getLogHelper().logError(x);
		}

		return false;
	}

	/**
	 * Implementation of the <code>IUpdate</code> prototype method discovers the
	 * operation through the current editor's <code>ITextOperationTarget</code>
	 * adapter, and sets the enabled state accordingly.
	 */
	@Override
	public void update() {
		super.update();

		if (!canModifyEditor()) {
			setEnabled(false);
			return;
		}

		ITextEditor editor = getTextEditor();
		if (operationTarget == null && editor != null) {
			operationTarget = (ITextOperationTarget) editor.getAdapter(ITextOperationTarget.class);
		}

		boolean isEnabled = (operationTarget != null
				&& operationTarget.canDoOperation(ITextOperationTarget.PREFIX) && operationTarget
				.canDoOperation(ITextOperationTarget.STRIP_PREFIX));
		setEnabled(isEnabled);
	}

	/*
	 * @see TextEditorAction#setEditor(ITextEditor)
	 */
	public void setEditor(ITextEditor editor) {
		super.setEditor(editor);
		operationTarget = null;
	}

	/**
	 * Configure the action.
	 * @param sourceViewer Source viewer of the editor where action should be applied.
	 * @param configuration Configuration of the editor with the available content types.
	 */
	public void configure(ISourceViewer sourceViewer, SourceViewerConfiguration configuration) {
		this.prefixesMap = null;

		String[] types = configuration.getConfiguredContentTypes(sourceViewer);
		Map<String, String[]> prefixesMap = new HashMap<String, String[]>(types.length);
		
		for (String contentType : types) {
			
			String[] defaultPrefixes = configuration.getDefaultPrefixes(sourceViewer, contentType);
			
			if (defaultPrefixes != null) {
				List<String> prefixes = new ArrayList<String>(Arrays.asList(defaultPrefixes));
				
				// Delete empty prefixes
				Iterator<String> prefixesIterator = prefixes.iterator();
				while (prefixesIterator.hasNext()) {
					String prefix = prefixesIterator.next();
					if (prefix.isEmpty()) {
						prefixesIterator.remove();
					}
				}
				
				if (!prefixes.isEmpty()) {
					prefixesMap.put(contentType, prefixes.toArray(new String[prefixes.size()]));
				}
			}
		}
		
		documentPartitioning = configuration.getConfiguredDocumentPartitioning(sourceViewer);
		this.prefixesMap = prefixesMap;
	}
}
