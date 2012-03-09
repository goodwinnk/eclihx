package eclihx.launching;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;

/**
 * Helper class for launching haxe from hxml files.
 */
public class HaxeLaunchConfigurationUtils {
	
	/**
	 * Id of haxe launch configuration type
	 */
	public static final String CONFIGURATION_TYPE_ID = "eclihx.launching.HaxeLaunchDelegate";
	
	/**
	 * Creates or find existent configuration for given project and build file and lunches it.
	 * 
	 * @param buildFile build file with launch configuration
	 * @param mode Run or Debug
	 * @throws CoreException some
	 */
	public static void run(IFile buildFile, String mode) throws CoreException {
		
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType haxeConfigurationType = manager.getLaunchConfigurationType(HaxeLaunchConfigurationUtils.CONFIGURATION_TYPE_ID);
		
		ILaunchConfiguration[] existentHaxeConfigurations = manager.getLaunchConfigurations(haxeConfigurationType);
		ILaunchConfiguration toLaunch = null;
		
		IProject project = buildFile.getProject();
		
		HaxeRunnerConfiguration toCreate = new HaxeRunnerConfiguration();
		toCreate.setProjectName(project.getName());
		toCreate.setBuildFile(HaxeRunnerConfiguration.AttributesConverter.getBuildFileString(buildFile));
		toCreate.setIsNonDefaultCompiler(false);
		toCreate.setWorkingDirectory(HaxeRunnerConfiguration.AttributesConverter.getWorkingDirectory(buildFile.getParent()));
		
		for (ILaunchConfiguration haxeConfiguration : existentHaxeConfigurations) {
			HaxeRunnerConfiguration configWrapper = HaxeRunnerConfiguration.tryWrap(haxeConfiguration);
			if (configWrapper != null) {
				if (configWrapper.equals(toCreate)) {
					toLaunch = haxeConfiguration;
				}
			}			
		}
		
		if (toLaunch == null) {
			String newConfiugurationName = manager.generateLaunchConfigurationName(project.getName() + " - " + buildFile.getName());
			ILaunchConfigurationWorkingCopy newHaxeLaunch = haxeConfigurationType.newInstance(null, newConfiugurationName);
			toCreate.fillLaunchConfiguration(newHaxeLaunch);
			toLaunch = newHaxeLaunch.doSave();
		}
		
		toLaunch.launch(mode, null);
	}
}
