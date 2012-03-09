package eclihx.launching;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

/**
 * Class for managing configuration properties.
 */
public class HaxeRunnerConfiguration {
	
	/**
	 * Helper class for working with configuration attributes
	 */
	public static class AttributesConverter {
		private AttributesConverter() {
		}
		
		/**
		 * Converts file to string for save.
		 * @param buildFile build file.
		 * @return file path.
		 */
		public static String getBuildFileString(IFile buildFile) {
			return buildFile.getLocation().toOSString();
		}
		
		/**
		 * Get string for working directory as directory of for given file.
		 */
		public static String getWorkingDirectory(IContainer directory) {
			return directory.getLocation().toOSString();
		}
	}
	
	private String[] arguments;
	private String workingDirectory = "";
	private String buildFile = "";
	private boolean isNonDefaultCompiler = true;
	private String compilerPath = "";
	private String projectName = "";
	
	/**
	 * Wrap launch configuration to haxe configuration. Null if given configuration is not haxe configuration.
	 * 
	 * @param configuration some launch configuration.
	 * @return haxe configuration wrapper.
	 */
	public static HaxeRunnerConfiguration tryWrap(ILaunchConfiguration configuration) {
		
		try {
			HaxeRunnerConfiguration haxeConfiguraiton = new HaxeRunnerConfiguration();
			haxeConfiguraiton.load(configuration);
			return haxeConfiguraiton;
		} catch (CoreException e) {
			return null;
		}
	}
	
	/**
	 * Wrap launch configuration to haxe configuration.
	 * 
	 * @param configuration some launch configuration.
	 * @return haxe configuration wrapper.
	 * @throws CoreException for the reasons see the 
	 * 		{@link ILaunchConfiguration#getAttribute(String, String)} throw block.
	 */
	public static HaxeRunnerConfiguration wrap(ILaunchConfiguration configuration) throws CoreException {
		HaxeRunnerConfiguration haxeConfiguraiton = new HaxeRunnerConfiguration();
		haxeConfiguraiton.load(configuration);
		return haxeConfiguraiton;
	}
	
	/**
	 * Simple constructor. Main functionality moved to <code>load</code> method 
	 * because we don't want constructor pelt us with exceptions.
	 */
	public HaxeRunnerConfiguration() {		
	}
	
	/**
	 * Method performs loading configuration from the untyped 
	 * {@link ILaunchConfiguration} to this configuration and performs initial
	 * validation and conversion.
	 * 
	 * @param configuration the configuration to load.
	 * @throws CoreException for the reasons see the 
	 * {@link ILaunchConfiguration#getAttribute(String, String)} throw block.
	 */
	private void load(ILaunchConfiguration configuration) throws CoreException {
		
		boolean nonDefaulCompiler = configuration.getAttribute(IHaxeLaunchConfigurationConstants.IS_ALTERNATIVE_COMPILER, false);
		setIsNonDefaultCompiler(nonDefaulCompiler);
		
		if (nonDefaulCompiler) {
			setCompilerPath(configuration.getAttribute( 
					IHaxeLaunchConfigurationConstants.HAXE_COMPILER_PATH, ""));
		} else {
			setCompilerPath("");
		}		
		
		setBuildFile(configuration.getAttribute(
				IHaxeLaunchConfigurationConstants.BUILD_FILE, 
				(String) null));
		
		setWorkingDirectory(configuration.getAttribute(
				IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, 
				(String) null));
		
		setProjectName(configuration.getAttribute(
				IHaxeLaunchConfigurationConstants.PROJECT_NAME, 
				(String) null));
		
        // TODO 8 get attributes
        setArguments(null);
	}
	
	/**
	 * Set haxe attributes in given launch. 
	 * @param workingLaunch launch configuration to fill.
	 */
	public void fillLaunchConfiguration(ILaunchConfigurationWorkingCopy workingLaunch) {
		workingLaunch.setAttribute(IHaxeLaunchConfigurationConstants.IS_ALTERNATIVE_COMPILER, isNonDefaultCompiler());
		workingLaunch.setAttribute(IHaxeLaunchConfigurationConstants.HAXE_COMPILER_PATH, isNonDefaultCompiler() ? getCompilerPath() : "");
		workingLaunch.setAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, getBuildFile());
		workingLaunch.setAttribute(IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, getWorkingDirectory());
		workingLaunch.setAttribute(IHaxeLaunchConfigurationConstants.PROJECT_NAME, getProjectName());
	}

	/**
	 * Get arguments.
	 * @return an array with arguments.
	 */
	public String[] getArguments() {
		return arguments;
	}

	/**
	 * Set arguments.
	 * @param arguments array with arguments.
	 */
	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

	/**
	 * The working directory string.
	 * @return The working directory string. 
	 */
	public String getWorkingDirectory() {
		return workingDirectory;
	}

	/**
	 * Set the working directory
	 * @param workingDirectory the 
	 */
	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	/**
	 * Get the build file.
	 * @return the build file string.
	 */
	public String getBuildFile() {
		return buildFile;
	}

	/**
	 * Set the build file.
	 * @param buildFile the build file string.
	 */
	public void setBuildFile(String buildFile) {
		this.buildFile = buildFile;
	}
	
	/**
	 * Get the path of the compiler.
	 * @return the compiler path.
	 */
	public String getCompilerPath() {
		return compilerPath;
	}
	
	/**
	 * Get the path of the compiler.
	 * @param compilePath the compiler path.
	 */
	public void setCompilerPath(String compilePath) {
		compilerPath = compilePath;
	}
	
	/**
	 * Check if non-default compiler should be used for this launch.
	 * @return <code>true</code> if non-default compiler should be used for this launch.
	 */
	public boolean isNonDefaultCompiler() {
		return isNonDefaultCompiler;
	}

	/**
	 * @param isNonDefaultCompiler <code>true</code> if non-default compiler should be used for this launch.
	 */
	public void setIsNonDefaultCompiler(boolean isNonDefaultCompiler) {
		this.isNonDefaultCompiler = isNonDefaultCompiler;
	}
	
	/**
	 * Get the project name.
	 * @return the project name.
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Set the project name.
	 * @param projectName project name.
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public int hashCode() {
		throw new IllegalStateException("Not implemented");
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof HaxeRunnerConfiguration)) {
			return false;
		}
		
		if (obj == this) {
			return true;
		}
		
		HaxeRunnerConfiguration otherConfig = (HaxeRunnerConfiguration) obj;
		
		return workingDirectory.equals(otherConfig.workingDirectory) &&
				buildFile.equals(otherConfig.buildFile) &&
				isNonDefaultCompiler == otherConfig.isNonDefaultCompiler &&
				compilerPath.equals(otherConfig.compilerPath) &&
				projectName.equals(otherConfig.projectName);
	}
	
	
}
