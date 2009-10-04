package eclihx.ui.internal.navigator.dragdrop;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.resources.ResourceDropAdapterAssistant;

import eclihx.core.haxe.model.HaxePackage;
import eclihx.core.haxe.model.HaxeSourceFile;

public class ResourceDropAdapter extends ResourceDropAdapterAssistant {

	public static final String ID = "eclihx.ui.internal.navigator.dragdrop.resourceDropAdapter";

	@Override
	public IStatus handleDrop(CommonDropAdapter aDropAdapter,
			DropTargetEvent aDropTargetEvent, Object aTarget) {
		// System.out.println("handleDrop" + aDropAdapter + " - " + aDropTargetEvent + "-" + aTarget);
		final HaxePackage haxePackageTarget = (HaxePackage) aTarget;
		
		for (Iterator iterator = ((TreeSelection) aDropTargetEvent.data).iterator(); iterator.hasNext();) {
			Object data = iterator.next();
			if (data instanceof HaxeSourceFile) {
				final HaxeSourceFile haxeSourceFile = (HaxeSourceFile) data;
				final IPath haxeSourceFileTarget = haxePackageTarget.getBaseResource().getFullPath().append(haxeSourceFile.getName());
	
				Job job = new WorkspaceJob("Move haxe file " + haxeSourceFile.getName() + " to " + haxePackageTarget.getName()) {
					public IStatus runInWorkspace(IProgressMonitor monitor)
							throws CoreException {
						
						// FIXME : cast haxeSourceFileTarget to IResource not possible
						haxeSourceFile.getBaseFile().move(haxeSourceFileTarget, true, monitor);
						haxeSourceFile.move(haxePackageTarget);
						return Status.OK_STATUS;
					}
				};
				job.schedule();
			}
			if (data instanceof HaxePackage) {
				final HaxePackage haxeSourcePackage = (HaxePackage) data;
				final IPath haxeSourceFileTarget = haxePackageTarget.getBaseResource().getFullPath().append(haxeSourcePackage.getName());
	
				Job job = new WorkspaceJob("Move haxe package " + haxeSourcePackage.getName() + " to " + haxePackageTarget.getName()) {
					public IStatus runInWorkspace(IProgressMonitor monitor)
							throws CoreException {
						haxeSourcePackage.getBaseFolder().move(haxeSourceFileTarget, true, monitor);
						return Status.OK_STATUS;
					}
				};
				job.schedule();
			}
		}

		return super.handleDrop(aDropAdapter, aDropTargetEvent, aTarget);
	}

	@Override
	public IStatus validateDrop(Object target, int aDropOperation,
			TransferData transferType) {
		if (target instanceof HaxePackage) {
			// System.out.println("validateDrop" + target + "-" + aDropOperation
			// + "- " + transferType.result);
			return new IStatus() {
				@Override
				public boolean matches(int arg0) {
					return true;
				}

				@Override
				public boolean isOK() {
					return true;
				}

				@Override
				public boolean isMultiStatus() {
					return true;
				}

				@Override
				public int getSeverity() {
					return 0;
				}

				@Override
				public String getPlugin() {
					return null;
				}

				@Override
				public String getMessage() {
					return null;
				}

				@Override
				public Throwable getException() {
					return null;
				}

				@Override
				public int getCode() {
					return 0;
				}

				@Override
				public IStatus[] getChildren() {
					return null;
				}
			};
		}

		return super.validateDrop(target, aDropOperation, transferType);
	}
}
