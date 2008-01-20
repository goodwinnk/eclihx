package eclihx.ui.internal.ui.preferences;

import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eclihx.launching.EclihxLauncher;
import eclihx.ui.internal.ui.EclihxPlugin;

public class HaxeCompilerPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	// TODO: validation for the compiler path
	
	private FileFieldEditor compilerPathField;
	
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
	}

	@Override
	protected Control createContents(Composite parent) {

		Composite top = new Composite(parent, SWT.LEFT);

		// Sets the layout data for the top composite's 
		// place in its parent's layout.
		top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Sets the layout for the top composite's 
		// children to populate.
		top.setLayout(new GridLayout());
				
		compilerPathField = new FileFieldEditor("eclihx_launcher_compiler", "haXe compiler:", true, top);
		compilerPathField.setStringValue(EclihxLauncher.getDefault().getPluginPreferences().getString("eclihx_launcher_compiler"));
		compilerPathField.setEmptyStringAllowed(true);
		compilerPathField.setFileExtensions(new String[]{"*.exe"});
		compilerPathField.load();
				
		return top;
	}

	@Override
	protected void performDefaults() {
		compilerPathField.setStringValue(EclihxLauncher.getDefault().getPluginPreferences().getDefaultString("eclihx_launcher_compiler"));
		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		EclihxLauncher.getDefault().getPluginPreferences().setValue("eclihx_launcher_compiler", compilerPathField.getStringValue());
		return super.performOk();
	}
}
