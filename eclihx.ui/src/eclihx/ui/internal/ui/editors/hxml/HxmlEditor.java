package eclihx.ui.internal.ui.editors.hxml;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;

import eclihx.ui.internal.ui.EclihxUIPlugin;
import eclihx.ui.internal.ui.editors.ColorManager;
import eclihx.ui.internal.ui.editors.hx.HXSourceViewerConfiguration;

/**
 * An editor for hxml-files.
 */
public class HxmlEditor extends TextEditor {
	
	// TODO: Move to a separate common class
	/**
	 * Combine eclihx.ui preference store with EditorUI store.
	 * 
	 * @return combined preference store.
	 */
	private IPreferenceStore createCombinedPreferenceStore() {
		List<IPreferenceStore> stores = new ArrayList<IPreferenceStore>(2);

		stores.add(EclihxUIPlugin.getDefault().getPreferenceStore());
		stores.add(EditorsUI.getPreferenceStore());

		return new ChainedPreferenceStore(stores.toArray(new IPreferenceStore[stores.size()]));
	}
	
	private ColorManager colorManager;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.editors.text.TextEditor#initializeEditor()
	 */
	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		
		setPreferenceStore(createCombinedPreferenceStore());
		colorManager = new ColorManager();
		
		setSourceViewerConfiguration(new HxmlSourceViewerConfiguration(colorManager));
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#affectsTextPresentation(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	@Override
	protected boolean affectsTextPresentation(PropertyChangeEvent event) {

		if (getSourceViewerConfiguration() instanceof HxmlSourceViewerConfiguration) {
			((HxmlSourceViewerConfiguration) getSourceViewerConfiguration()).adaptToPreferenceChange(event);
			return true;
		}

		return false;
	}
}
