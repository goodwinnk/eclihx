package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.ui.editors.text.TextEditor;

import eclihx.core.EclihxCore;
import eclihx.core.EclihxLogger;
import eclihx.ui.internal.ui.EclihxPlugin;

public class HXEditor extends TextEditor {

	private ColorManager colorManager;
	
	

	public HXEditor() {
		super();
		// Set preference store to the store of the ui plugin
		setPreferenceStore(EclihxPlugin.getDefault().getPreferenceStore());
		setDocumentProvider(new HXDocumentProvider());
		
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new HXSourceViewerConfiguration(colorManager));
	}
	
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}	
}
