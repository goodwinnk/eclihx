package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.editors.text.TextEditor;

import eclihx.ui.internal.ui.EclihxUIPlugin;

public class HXEditor extends TextEditor {

	@Override
	protected boolean affectsTextPresentation(PropertyChangeEvent event) {
		
		if (getSourceViewerConfiguration() instanceof HXSourceViewerConfiguration) 
		{
			((HXSourceViewerConfiguration)getSourceViewerConfiguration()).adaptToPreferenceChange(event);
			return true;
		}
		
		return false;
	}

	private ColorManager colorManager;
	
	public HXEditor() {
		super();
		
		// Set preference store to the store of the ui plugin
		setPreferenceStore(EclihxUIPlugin.getDefault().getPreferenceStore());
		setDocumentProvider(new HXDocumentProvider());
		
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new HXSourceViewerConfiguration(colorManager));
	}	
	
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}	
}
