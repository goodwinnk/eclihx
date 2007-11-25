package eclihx.ui.internal.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class HaxeEditorPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public HaxeEditorPreferencePage() {
		super(GRID);
	}

	protected void createFieldEditors() {
	}

	public void init(IWorkbench workbench) {
	}
}
