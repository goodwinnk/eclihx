package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;

import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Class extend functionality of the standard text editor to make it work with
 * haXe language source.
 */
public class HXEditor extends TextEditor {
	
	/**
	 * Content assist proposals action identifier.
	 */
	public static final String CONTENT_PROPSALS_ACTION_ID = "ContentAssistProposals";
	
	/**
	 * Content assist proposal action prefix in resource bundle resources.
	 */
	public static final String CONTENT_PROPSALS_ACTION_RESOURCE_PREFIX = "ContentAssistProposals.";
	
	/**
	 * Content assist tips action identifier.
	 */
	public static final String CONTENT_TIPS_ACTION_ID = "ContentAssistTips.";
	
	/**
	 * Content assist tips action prefix in resource bundle resources.
	 */
	public static final String CONTENT_TIPS_ACTION_RESOURCE_PREFIX = "ContentAssistTips";

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.editors.text.TextEditor#createActions()
	 */
	@Override
	protected void createActions() {
		
		super.createActions();
		
		IAction assistProposalsAction = new TextOperationAction(
				HaxeEditorMessages.getBundle(), 
				CONTENT_PROPSALS_ACTION_RESOURCE_PREFIX, 
				this,
				ISourceViewer.CONTENTASSIST_PROPOSALS);
		
		assistProposalsAction.setActionDefinitionId(
				ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		
		setAction(CONTENT_PROPSALS_ACTION_ID, assistProposalsAction);
		
		IAction assistContextTipsAction = new TextOperationAction(
				HaxeEditorMessages.getBundle(), 
				CONTENT_TIPS_ACTION_RESOURCE_PREFIX, 
				this,
				ISourceViewer.CONTENTASSIST_CONTEXT_INFORMATION);
		
		assistContextTipsAction.setActionDefinitionId(
				ITextEditorActionDefinitionIds.CONTENT_ASSIST_CONTEXT_INFORMATION);
		
		setAction(CONTENT_TIPS_ACTION_ID, assistContextTipsAction);		
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
		
		setEditorContextMenuId("#HaxeEditorContext"); //$NON-NLS-1$
	}	
	
	/*
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
	 */
	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(
				new String[] { "eclihx.ui.haxeEditorScope" }); //$NON-NLS-1$
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
