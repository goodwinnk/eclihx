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
	 * @param configuration
	 * @param launch
	 * @param monitor
	 * @throws CoreException
	 */
	public void run(HaxeRunnerConfiguration configuration, ILaunch launch, IProgressMonitor monitor) throws CoreException;	
}
