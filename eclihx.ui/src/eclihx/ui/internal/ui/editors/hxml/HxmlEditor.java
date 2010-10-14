package eclihx.ui.internal.ui.editors.hxml;

import org.eclipse.ui.editors.text.TextEditor;

/**
 * An editor for hxml-files.
 */
public class HxmlEditor extends TextEditor {
	
	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setSourceViewerConfiguration(new HxmlSourceViewerConfiguration());
	}
}
