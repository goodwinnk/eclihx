package eclihx.ui.internal.ui.wizards;

//import org.eclipse.jface.wizard.Wizard;

//import eclihx.ui.wizards.NewHaxeProjectWizardPage;

//import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

public class HaxeProjectWizard extends BasicNewProjectResourceWizard{
	
	@Override
	public boolean performFinish() {
		return true;
	}
}
