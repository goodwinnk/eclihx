package eclihx.ui.internal.ui.utils;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eclihx.core.haxe.model.core.IHaxeElement;
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.core.haxe.model.core.IHaxeWorkspace;

/**
 * Class provides an information for tree representation of the 
 * all haXe elements.
 * 
 * TODO 6 Make it work not only for the IHaxeProject and IHaxeSourceFolder
 */
final class HaxeElementsContentProvider implements ITreeContentProvider {
	
	/**
	 *  Default value for the elements without children.
	 */
	private final static Object[] NO_CHILDREN = new Object[0];
	
	/**
	 * Filters the haXe elements to show.
	 */
	private final HaxeElementFilter elementsFilter;


	public HaxeElementsContentProvider(
			HaxeElementFilter elementsFilter) {
		
		if (elementsFilter == null) {
			throw new NullPointerException("Filter parameter can't be null");
		}

		this.elementsFilter = elementsFilter;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		
		if (parentElement instanceof IHaxeWorkspace) { 
			return ((IHaxeWorkspace)parentElement).getHaxeProjects();
		} else if (parentElement instanceof IHaxeProject) {
			return ((IHaxeProject)parentElement).getSourceFolders();					
		}

		return NO_CHILDREN;	
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		if (element instanceof IHaxeElement) {
			return ((IHaxeElement)element).getParent();
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		
		return ((element instanceof IHaxeWorkspace && 
						elementsFilter.isShowWorkspaceChildren()) || 
				(element instanceof IHaxeProject && 
						elementsFilter.isShowProjectsChildren()));
	
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof IHaxeWorkspace) { 
			return ((IHaxeWorkspace)inputElement).getHaxeProjects();
		} else if (inputElement instanceof IHaxeProject) {
			return ((IHaxeProject)inputElement).getSourceFolders();					
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
		// Do nothing
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// Do nothing
	}
	
	/*
	public ISelectionStatusValidator getValidator() {
		
		return new ISelectionStatusValidator() {

			private final Status errorStatus = 
					new Status(IStatus.ERROR, EclihxUIPlugin.PLUGIN_ID, "");
			
			private final Status okStatus = 
					new Status(IStatus.OK, EclihxUIPlugin.PLUGIN_ID, "");
			
			@Override
			public IStatus validate(Object[] selection) {
				if (selection.length == 1) {
					
					Object selectedObject = selection[0];
					
					if (sourceFolder && 
							selectedObject instanceof IHaxeSourceFolder) {
						return okStatus;
					}
					
				}
	
				return errorStatus;
			}
		
		};
	}*/
}
