package eclihx.ui.launch.handlers;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.IStatusHandler;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxeProject;

public class FinishStatusHandler implements IStatusHandler {

	@Override
	public Object handleStatus(IStatus status, Object source) throws CoreException {
		String projectName = (String)source;
		
		IHaxeProject haxeProject = EclihxCore.getDefault().getHaxeWorkspace().getHaxeProject(projectName);            
        IFolder outputFolder = haxeProject.getPathManager().getOutputFolder();
        outputFolder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		
		return null;
	}

}
