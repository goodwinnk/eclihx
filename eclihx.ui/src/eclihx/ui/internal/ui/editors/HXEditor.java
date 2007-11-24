package eclihx.ui.internal.ui.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class HXEditor extends TextEditor {

	private ColorManager colorManager;

	public HXEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
