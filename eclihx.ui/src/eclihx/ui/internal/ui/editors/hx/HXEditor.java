package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.ui.editors.text.TextEditor;

public class HXEditor extends TextEditor {

	private ColorManager colorManager;

	public HXEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new HXSourceViewerConfiguration(colorManager));
		setDocumentProvider(new HXDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
