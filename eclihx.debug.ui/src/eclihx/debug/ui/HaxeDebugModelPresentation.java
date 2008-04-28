package eclihx.debug.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;

import org.eclipse.ui.part.FileEditorInput;

import eclihx.debug.flash.FlashBreakpoint;

public class HaxeDebugModelPresentation implements IDebugModelPresentation {

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.IDebugModelPresentation#computeDetail(org.eclipse.debug.core.model.IValue, org.eclipse.debug.ui.IValueDetailListener)
	 */
	@Override
	public void computeDetail(IValue value, IValueDetailListener listener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.IDebugModelPresentation#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.IDebugModelPresentation#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.IDebugModelPresentation#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String attribute, Object value) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IEditorInput getEditorInput(Object element) {
		if (element instanceof IFile)
			return new FileEditorInput((IFile) element);
		if (element instanceof ILineBreakpoint)
			return new FileEditorInput((IFile) ((ILineBreakpoint) element)
					.getMarker().getResource());
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.debug.ui.ISourcePresentation#getEditorId(org.eclipse.ui.IEditorInput,
	 *      java.lang.Object)
	 */
	@Override
	public String getEditorId(IEditorInput input, Object element) {
		if (element instanceof IFile) {
			IFile file = (IFile) element;
			//if (file.getFileExtension().endsWith(".hx")) {
				return "eclihx.ui.internal.ui.editors.HXEditor"; 
			//}
		} else if (element instanceof FlashBreakpoint ){
			return "eclihx.ui.internal.ui.editors.HXEditor";
		}
			
		//if (element instanceof IFile || element instanceof ILineBreakpoint)
			
		
		return null;
	}

	
	
}
