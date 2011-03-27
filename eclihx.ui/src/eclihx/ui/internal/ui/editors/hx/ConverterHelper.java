package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.model.core.IHaxeElement;
import eclihx.core.haxe.model.core.IHaxeSourceFile;

/**
 * Number of static methods for converting eclipse file UI classes to haxe elements.
 */
public final class ConverterHelper {

	/**
	 * Gets the {@link IHaxeSourceFile} from the input. If input has another
	 * source this method will return null.
	 * 
	 * @param input the editor input.
	 * @return {@link IHaxeSourceFile} object or <code>null</code>.
	 */
	public static IHaxeSourceFile getHaxeSourceFile(IEditorInput input) {
		if (input instanceof IFileEditorInput) {
			IHaxeElement haxeElement = EclihxCore.getDefault().getHaxeWorkspace().getHaxeElement(
							((IFileEditorInput) input).getFile());

			if (haxeElement instanceof IHaxeSourceFile) {
				return (IHaxeSourceFile) haxeElement;
			}
		}

		return null;
	}
}
