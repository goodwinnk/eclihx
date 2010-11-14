package eclihx.ui.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

/**
 * Extends wizard page with the selection based methods and provides method
 * for listening changes on the page.
 */
public abstract class AbstractSelectionPage extends WizardPage {

	/**
	 * Constructor with the page name.
	 * @param pageName the page name.
	 * @param selection the workbench selection on the period of wizard
	 *        activation.
	 */
	public AbstractSelectionPage(String pageName, 
			IStructuredSelection selection) {
		
		super(pageName);
		
		this.selection = selection;
	}
	
	/**
	 * The workbench selection.
	 */
	IStructuredSelection selection;
	
	/**
	 * Modify listener which is used to update the page state.
	 */
	protected final ModifyListener fUpdateDialogListener = 
			new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent modifyEvent) {
			dialogChangedHandler();
		}
	};

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	abstract public void createControl(Composite parent);
	
	/**
	 * Method is used for initial initialization of the controls. This method
	 * is planned to be used for processing the selection.
	 */
	abstract protected void initialize();
	
	/**
	 * Checks the changed dialog and validate the new state. Default 
	 * implementation calls <code>updateStatus</code> method with the empty
	 * message.
	 */
	protected void dialogChangedHandler() {
		updateStatus(null);
	}
	
	/**
	 * Updates status of the dialog. If there is no error message then the
	 * page is considered to be complete.
	 * 
	 * @param errorMessage the error message. If message is <code>null</code>
	 *        then the page is complete.
	 */
	protected final void updateStatus(String errorMessage) {
		setErrorMessage(errorMessage);
		setPageComplete(errorMessage == null);
	}
}
