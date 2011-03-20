package eclihx.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;

/**
 * This runner will starts the launch process  
 */
public interface IHaxeRunner {
	
	/**
	 * Run the process of launching
	 * 
	 * @param configuration the configuration of the current launch. 
	 * @param launch the lunch object.
	 * @param monitor the progress monitor.
	 * @return The string that console should show.
	 * @throws CoreException exception if something will go wrong.
	 */
	public String run(
			HaxeRunnerConfiguration configuration, 
			ILaunch launch, IProgressMonitor monitor) throws CoreException;	
}
