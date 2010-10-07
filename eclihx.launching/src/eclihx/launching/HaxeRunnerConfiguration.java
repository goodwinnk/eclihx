package eclihx.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * Class for managing configuration properties.
 */
public class HaxeRunnerConfiguration {
	
	private String[] fArguments;
	private String fWorkingDirectory;
	private String fBuildFile;
	private String fCompilerPath;
	private String fSourceDirectory;
	private String fOutputDirectory;
	
	/**
	 * Simple constructor. Main functionality moved to <code>load</code> method 
	 * because we don't want out constructor pelt us with exceptions.
	 */
	public HaxeRunnerConfiguration() {}
	
	/**
	 * Method performs loading configuration from the untyped 
	 * {@link ILaunchConfiguration} to this configuration and performs initial
	 * validation and conversion.
	 * 
	 * @param configuration the configuration to load.
	 * @throws CoreException for the reasons see the 
	 * {@link ILaunchConfiguration#getAttribute(String, String)} throw block.
	 */
	public void load(ILaunchConfiguration configuration) throws CoreException {

		setCompilerPath(
				configuration.getAttribute( 
						IHaxeLaunchConfigurationConstants.HAXE_COMPILER_PATH, 
						""));
        
		setOutputDirectory(configuration.getAttribute(
				IHaxeLaunchConfigurationConstants.OUTPUT_DIRECTORY, 
				(String) null));
        
		setSourceDirectory(configuration.getAttribute(
				IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, 
				(String) null));
        
		setBuildFile(configuration.getAttribute(
				IHaxeLaunchConfigurationConstants.BUILD_FILE, 
				(String) null));
		
		setWorkingDirectory(configuration.getAttribute(
				IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, 
				(String) null));
        
        // TODO 8 get attributes
        setArguments(null);
	}

	/**
	 * Get arguments.
	 * @return an array with arguments.
	 */
	public String[] getArguments() {
		return fArguments;
	}

	/**
	 * Set arguments.
	 * @param arguments array with arguments.
	 */
	public void setArguments(String[] arguments) {
		fArguments = arguments;
	}

	/**
	 * The working directory string.
	 * @return The working directory string. 
	 */
	public String getWorkingDirectory() {
		return fWorkingDirectory;
	}

	/**
	 * Set the working directory
	 * @param workingDirectory the 
	 */
	public void setWorkingDirectory(String workingDirectory) {
		fWorkingDirectory = workingDirectory;
	}

	/**
	 * Get the build file.
	 * @return the build file string.
	 */
	public String getBuildFile() {
		return fBuildFile;
	}

	/**
	 * Set the build file.
	 * @param buildFile the build file string.
	 */
	public void setBuildFile(String buildFile) {
		fBuildFile = buildFile;
	}

	/**
	 * Get the path of the compiler.
	 * @return the compiler path.
	 */
	public String getCompilerPath() {
		return fCompilerPath;
	}

	/**
	 * Get the path of the compiler.
	 * @param compilePath the compiler path.
	 */
	public void setCompilerPath(String compilePath) {
		fCompilerPath = compilePath;
	}

	/**
	 * Get the source directory string. 
	 * @return the source directory string.
	 */
	public String getSourceDirectory() {
		return fSourceDirectory;
	}

	/**
	 * Set the source directory string.
	 * @param sourceDirectory the source directory string.
	 */
	public void setSourceDirectory(String sourceDirectory) {
		fSourceDirectory = sourceDirectory;
		
        // FIXME 7 dirty hack
        if (fSourceDirectory.endsWith("\\")) {
        	fSourceDirectory = fSourceDirectory.substring(
        			0, fSourceDirectory.length() - 1);
        }
	}

	/**
	 * Get the output directory path. 
	 * @return the output directory path.
	 */
	public String getOutputDirectory() {
		return fOutputDirectory;
	}

	/**
	 * Set the output directory.
	 * @param outputDirectory the output directory path.
	 */
	public void setOutputDirectory(String outputDirectory) {
		fOutputDirectory = outputDirectory;
	}
}
