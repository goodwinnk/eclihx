package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.BasicTextEditorActionContributor;

/**
 * haXe editor actions contributor.
 */
public class HxEditorActionContributor extends BasicTextEditorActionContributor {

	/**
	 * Default constructor.
	 */
	public HxEditorActionContributor() {
		super();

		// new RetargetTextEditorAction();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.texteditor.BasicTextEditorActionContributor#contributeToMenu
	 * (org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void contributeToMenu(IMenuManager menu) {
		super.contributeToMenu(menu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.texteditor.BasicTextEditorActionContributor#setActiveEditor
	 * (org.eclipse.ui.IEditorPart)
	 */
	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);

	}

}
