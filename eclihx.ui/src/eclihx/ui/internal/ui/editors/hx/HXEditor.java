package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;

import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Class extend functionality of the standard text editor to make it work with
 * haXe language source.
 */
public class HXEditor extends TextEditor {

	@Override
	protected void createActions() {
		
		super.createActions();
		
		/*
		IAction action = new ContentAssistAction(aResourceBundle, 
				"ContentAssistProposal.", this);
		action.setActionDefinitionId(
				ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction(actionId, action);
		markAsStateDependentAction(actionId, true);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(action, helpContextId); */
	}

	/**
	 * Color manager.
	 */
	private final ColorManager colorManager;

	/**
	 * Standard constructor.
	 */
	public HXEditor() {
		super();

		// Set preference store to the store of the ui plugin
		setPreferenceStore(EclihxUIPlugin.getDefault().getPreferenceStore());
		setDocumentProvider(new HXDocumentProvider());

		colorManager = new ColorManager();
		setSourceViewerConfiguration(new HXSourceViewerConfiguration(
				colorManager));
		
		setEditorContextMenuId("#HaxeEditorContext");
	}
	
	
	
	/*
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
	 */
	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(
				new String[] { "eclihx.ui.haxeEditorScope" });
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#affectsTextPresentation(org.eclipse.jface.util.PropertyChangeEvent)
	 */
	@Override
	protected boolean affectsTextPresentation(PropertyChangeEvent event) {

		if (getSourceViewerConfiguration() instanceof HXSourceViewerConfiguration) {
			((HXSourceViewerConfiguration) getSourceViewerConfiguration())
					.adaptToPreferenceChange(event);
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.editors.text.TextEditor#dispose()
	 */
	@Override
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}
}
