package eclihx.ui.internal.ui.editors.hx;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eclihx.core.haxe.internal.outline.Module;
import eclihx.core.haxe.internal.outline.OutlineInfo;
import eclihx.core.haxe.internal.outline.OutlineManager;
import eclihx.core.haxe.internal.outline.Type;

/**
 * Provides tree for haxe file content outline view.
 */
public class OutlineContentProvider implements ITreeContentProvider {
	
	private OutlineManager outlineManager;
	private OutlineInfo outlineInfo;

	/**
	 * Default constructor.
	 */
	public OutlineContentProvider() {
		outlineManager = new OutlineManager();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		if (newInput instanceof HXEditor) {
			HXEditor editor = (HXEditor) newInput;
			outlineInfo = outlineManager.getModuleInfo(
					ConverterHelper.getHaxeSourceFile(editor.getEditorInput()));
		} else {
			outlineInfo = null;
		}
		
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		if (outlineInfo != null) {
			
			if (outlineInfo.getErrors().isEmpty()) {
				return new Object[] { outlineInfo.getModule() };
			} else {
				
				ArrayList<Object> errors = new ArrayList<Object>();
				for (String error : outlineInfo.getErrors()) {
					errors.add(error);
				}
				
				return errors.toArray();
			}			
			
		} else {
			return new Object[] { "No information" };
		}
		
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Module) {
			List<Type> types = ((Module)parentElement).getTypes();
			
			ArrayList<Object> validTypes = new ArrayList<Object>();
			for (Type type : types) {
				if (type.getName() != null && type.getMembers() != null) {
					validTypes.add(type);
				}
				
			}
			
			return validTypes.toArray();
		} else if (parentElement instanceof Type) {
			return ((Type)parentElement).getMembers().toArray();
		}
		
		return new Object[] {};
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Module) {
			return !((Module)element).getTypes().isEmpty();
		} else if (element instanceof Type) {
			return !((Type)element).getMembers().isEmpty();
		}
		return false;
	}	
}