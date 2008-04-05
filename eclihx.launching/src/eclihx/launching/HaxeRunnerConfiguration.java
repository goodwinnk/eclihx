package eclihx.launching;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * Class for managing configuration properties.
 * 
 */
public class HaxeRunnerConfiguration {
	
	private String[] fArguments;
	private String fWorkingDirectory;
	private String fBuildFile;
	private String fCompilerPath;
	private String fSourceDirectory;
	private String fOutputDirectory;
	
	/**
	 * Simple constructor. Main functionality moved to <code>load</code> method because we don't want
	 * out constructor pelt us with exceptions.
	 */
	public HaxeRunnerConfiguration() {}
	
	public void load(ILaunchConfiguration configuration) throws CoreException {

		setCompilerPath(configuration.getAttribute(IHaxeLaunchConfigurationConstants.HAXE_COMPILER_PATH, ""));
        
		setOutputDirectory(configuration.getAttribute(IHaxeLaunchConfigurationConstants.OUTPUT_DIRECTORY, (String) null));
        
		setSourceDirectory(configuration.getAttribute(IHaxeLaunchConfigurationConstants.WORKING_DIRECTORY, (String) null));
        
		setBuildFile(configuration.getAttribute(IHaxeLaunchConfigurationConstants.BUILD_FILE, (String) null));
        
        // TODO 8 get attributes
        setArguments(null);
	}

	
	
	public String[] getArguments() {
		return fArguments;
	}

	public void setArguments(String[] arguments) {
		fArguments = arguments;
	}

	
	public String getWorkingDirectory() {
		return fWorkingDirectory;
	}

	
	public void setWorkingDirectory(String workingDirectory) {
		fWorkingDirectory = workingDirectory;
	}

	public String getBuildFile() {
		return fBuildFile;
	}

	public void setBuildFile(String buildFile) {
		fBuildFile = buildFile;
	}

	public String getCompilerPath() {
		return fCompilerPath;
	}

	public void setCompilerPath(String compilePath) {
		fCompilerPath = compilePath;
	}

	public String getSourceDirectory() {
		return fSourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory) {
		fSourceDirectory = sourceDirectory;
		
        // TODO 7 dirty hack
        if (fSourceDirectory.endsWith("\\")) {
        	fSourceDirectory = fSourceDirectory.substring(0, fSourceDirectory.length() - 1);
        }
	}

	public String getOutputDirectory() {
		return fOutputDirectory;
	}

	public void setOutputDirectory(String outputDirectory) {
		fOutputDirectory = outputDirectory;
	}
	
	

}
