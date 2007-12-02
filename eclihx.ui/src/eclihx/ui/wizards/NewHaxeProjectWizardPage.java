package eclihx.ui.wizards;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class NewHaxeProjectWizardPage extends WizardNewProjectCreationPage{

	public NewHaxeProjectWizardPage() {
		super("PageName");
		setTitle("Project");
		setDescription("Hello");
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
	}
}
