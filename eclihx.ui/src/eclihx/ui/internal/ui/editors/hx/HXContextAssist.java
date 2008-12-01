package eclihx.ui.internal.ui.editors.hx;

import java.util.ArrayList;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import eclihx.core.EclihxCore;
import eclihx.core.haxe.internal.ContentInfo;
import eclihx.core.haxe.internal.HaxeContentAssistManager;
import eclihx.core.haxe.model.core.IHaxeElement;
import eclihx.core.haxe.model.core.IHaxeSourceFile;
import eclihx.ui.PluginImages;
import eclihx.ui.internal.ui.EclihxUIPlugin;

/**
 * Content assist for the haXe code.
 */
public class HXContextAssist implements IContentAssistProcessor {

	/**
	 * Gets the {@link IHaxeSourceFile} from the input. If input has another
	 * source this method will return null.
	 * @param input the editor input.
	 * @return {@link IHaxeSourceFile} object or <code>null</code>.
	 */
	private IHaxeSourceFile getHaxeSourceFile(IEditorInput input) {
		if(input instanceof IFileEditorInput){
			IHaxeElement haxeElement = EclihxCore.getDefault().
					getHaxeWorkspace().getHaxeElement(
							((IFileEditorInput)input).getFile());
			
			if (haxeElement instanceof IHaxeSourceFile) {
				return (IHaxeSourceFile)haxeElement;
			}
			
		}
		
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
	 */
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		
		// Get current file
		IEditorPart editor =  
			EclihxUIPlugin.getDefault().getWorkbench().
				getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		
		// Saving the file
		editor.doSave(null);
		
		IHaxeSourceFile haxeFile = getHaxeSourceFile(editor.getEditorInput());
		if(haxeFile == null) {
			return null;
		}
		   
		ArrayList<ContentInfo> tips =
			HaxeContentAssistManager.getTips(haxeFile, offset);
		
		final ArrayList<ICompletionProposal> result = 
			new ArrayList<ICompletionProposal>();
		for (ContentInfo contentInfo : tips) {
			
			contentInfo.isFunction();
			
			//contentInfo.
			
			IContextInformation info = 
				new ContextInformation(
					contentInfo.name, 
					"hello info"
				); //$NON-NLS-1$
			result.add( 
				new CompletionProposal(
					contentInfo.name, 
					offset, 
					0, 
					contentInfo.name.length(), 
					PluginImages.get(
							contentInfo.isFunction() ? 
									PluginImages.IMG_MISC_PUBLIC : 
									PluginImages.IMG_FIELD_PUBLIC), 
					contentInfo.name + " : " + contentInfo.type, 
					info, 
					"Hi?"
				)
			); //$NON-NLS-1$
		}
		
		return result.toArray(new ICompletionProposal[0]);
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		IContextInformation[] result = new IContextInformation[5];
		for (int i = 0; i < result.length; i++) {
			result[i] = new ContextInformation("hello", "hi!");
		}		
		return result;

	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[] { '.', '(' };
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

}
