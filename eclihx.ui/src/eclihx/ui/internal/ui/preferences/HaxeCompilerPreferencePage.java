package eclihx.ui.internal.ui.preferences;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eclihx.core.CorePreferenceInitializer;
import eclihx.core.EclihxCore;
import eclihx.core.util.OSUtil;

/**
 * Preference page for setting up the path to the haXe compiler.
 */
public class HaxeCompilerPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	// TODO 6 validation for the compiler path
	
	private FileFieldEditor compilerPathField;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
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
				
		compilerPathField = new FileFieldEditor(
				CorePreferenceInitializer.HAXE_COMPILER_PATH, 
				"haXe compiler:", true, top);
		
		String initialCompilerValue = 
				EclihxCore.getDefault().getPluginPreferences().getString(
						CorePreferenceInitializer.HAXE_COMPILER_PATH);  
		
		compilerPathField.setStringValue(initialCompilerValue);
		compilerPathField.setEmptyStringAllowed(true);
		
		compilerPathField.setFileExtensions(
				new String[]{OSUtil.getCompilerExtensionFilter()});
		compilerPathField.load();
				
		return top;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		compilerPathField.setStringValue(
				EclihxCore.getDefault().getPluginPreferences().
						getDefaultString(
								CorePreferenceInitializer.HAXE_COMPILER_PATH));
		
		super.performDefaults();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		
		EclihxCore.getDefault().getPluginPreferences().setValue(
				CorePreferenceInitializer.HAXE_COMPILER_PATH, 
				compilerPathField.getStringValue());
		
		return super.performOk();
	}
}
