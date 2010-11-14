package eclihx.ui.internal.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
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

import eclihx.core.haxe.internal.HaxeContentManager;
import eclihx.ui.PreferenceConstants;
import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Preference page for setting up package properties.
 */
public class HaxePackagePreferencePage  extends PreferencePage 
		implements IWorkbenchPreferencePage {
	
	private Button defPackCheck;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */	
	@Override
	public void init(IWorkbench workbench) {
		
		HaxeContentManager contentManager = HaxeContentManager.getInstance();
		
		contentManager.setEmptyPackagesEnabled(
				PreferenceConstants.getPreferenceStore().getBoolean(
						PreferenceConstants.HX_PACKAGE_PROPERTIES_DEFAULT_PACKAGE));
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {

		Composite top = new Composite(parent, SWT.LEFT);
		
		// Sets the layout data for the top composite's 
		// place in its parent's layout.
		top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Sets the layout for the top composite's 
		// children to populate.
		top.setLayout(new GridLayout());
				
		defPackCheck = new Button(top, SWT.CHECK);
		defPackCheck.setText(PreferenceConstants.HX_PACKAGE_PROPERTIES_DEFAULT_PACKAGE);
		initializeValues();
		defPackCheck.addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {}
				});
		
		return top;
	}

	/*public void changeDefPackCheckValue(boolean newDefPackCheck) {
		isDefPackCheck = newDefPackCheck;
	}*/
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {		
	    defPackCheck.setSelection(getPreferenceStore().getDefaultBoolean(PreferenceConstants.HX_PACKAGE_PROPERTIES_DEFAULT_PACKAGE));
		
		super.performDefaults();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {		
		IPreferenceStore store = getPreferenceStore();
		store.setValue(PreferenceConstants.HX_PACKAGE_PROPERTIES_DEFAULT_PACKAGE, defPackCheck.getSelection());
		HaxeContentManager.getInstance().setEmptyPackagesEnabled(PreferenceConstants.getPreferenceStore().getBoolean(
			PreferenceConstants.HX_PACKAGE_PROPERTIES_DEFAULT_PACKAGE));
		
		return super.performOk();
	}	
		
	private void initializeValues() {
		IPreferenceStore store = getPreferenceStore();
		defPackCheck.setSelection(store.getBoolean(PreferenceConstants.HX_PACKAGE_PROPERTIES_DEFAULT_PACKAGE));		
	}	
		
	/**
	 * Gets EclihX UI plug-in preference store
	 */
	@Override
	public IPreferenceStore getPreferenceStore() {		
		return EclihxUIPlugin.getDefault().getPreferenceStore();
	}
}
