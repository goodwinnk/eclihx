package eclihx.ui.internal.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eclihx.core.haxe.model.HaxePackage;

public class HaxePackagePreferencePage  extends PreferencePage 
		implements IWorkbenchPreferencePage {
	
	private Button defPackCheck;
	private boolean isDefPackCheck = HaxePackage.isDefPack();	

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	
	@Override
	public void init(IWorkbench workbench) {		
	}
	
	protected Control createContents(Composite parent) {

		Composite top = new Composite(parent, SWT.LEFT);

		// Sets the layout data for the top composite's 
		// place in its parent's layout.
		top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Sets the layout for the top composite's 
		// children to populate.
		top.setLayout(new GridLayout());
				
		defPackCheck = new Button(top, SWT.CHECK);
		defPackCheck.setText("\"package ;\" string for default package");
		defPackCheck.setSelection(isDefPackCheck);
		defPackCheck.addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						changeDefPackCheckValue(defPackCheck.getSelection());
					}
				});
		
		return top;
	}

	public void changeDefPackCheckValue(boolean newDefPackCheck) {
		isDefPackCheck = newDefPackCheck;
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		HaxePackage.setDefPack(true);
		defPackCheck.setSelection(HaxePackage.isDefPack());
		
		super.performDefaults();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		
		HaxePackage.setDefPack(isDefPackCheck);
		
		return super.performOk();
	}
}
