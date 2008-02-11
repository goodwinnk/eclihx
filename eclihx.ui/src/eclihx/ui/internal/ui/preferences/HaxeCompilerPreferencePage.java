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
import eclihx.launching.LauncherPreferenceInitializer;
import eclihx.ui.internal.ui.EclihxPlugin;

public class HaxeCompilerPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	// TODO 6 validation for the compiler path
	
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
				
		compilerPathField = new FileFieldEditor(LauncherPreferenceInitializer.ECLIHAXE_HAXE_COMPILER_PATH, "haXe compiler:", true, top);
		compilerPathField.setStringValue(EclihxLauncher.getDefault().getPluginPreferences().getString(LauncherPreferenceInitializer.ECLIHAXE_HAXE_COMPILER_PATH));
		compilerPathField.setEmptyStringAllowed(true);
		
		// TODO 7 Support of different OSes
		compilerPathField.setFileExtensions(new String[]{"*.exe"});
		compilerPathField.load();
				
		return top;
	}

	@Override
	protected void performDefaults() {
		compilerPathField.setStringValue(EclihxLauncher.getDefault().getPluginPreferences().getDefaultString(LauncherPreferenceInitializer.ECLIHAXE_HAXE_COMPILER_PATH));
		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		EclihxLauncher.getDefault().getPluginPreferences().setValue(LauncherPreferenceInitializer.ECLIHAXE_HAXE_COMPILER_PATH, compilerPathField.getStringValue());
		return super.performOk();
	}
}
