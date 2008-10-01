package eclihx.ui.internal.ui.editors.hx;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
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
import eclihx.core.haxe.model.core.IHaxeProject;
import eclihx.ui.PluginImages;
import eclihx.ui.internal.ui.EclihxUIPlugin;

public class HXContextAssist implements IContentAssistProcessor {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
	 */
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		
		// Get current file
		IEditorPart editor =  
			EclihxUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		
		editor.doSave(null);
		
		IEditorInput input = editor.getEditorInput();
		IFile file = null;
		if(input instanceof IFileEditorInput){
			file = ((IFileEditorInput)input).getFile();
		}
		
		if(file==null)
		   return null;
		
		IProject project = file.getProject();
		
		String fileFullName = file.getLocation().toOSString();
		
		IHaxeProject haxeProject = 
			EclihxCore.getDefault().getHaxeWorkspace().
			getHaxeProject(project.getName());
		
		if (haxeProject == null) {
			return null;
		}
		
		String fileName = file.getName();
		String extensionName = file.getFileExtension();
		String className = fileName.substring(0, fileName.lastIndexOf('.'));
		
		ArrayList<ContentInfo> tips =
			HaxeContentAssistManager.getTips(
				className, 
				fileFullName,
				offset,
				new java.io.File(haxeProject.getPathManager().getOutputFolder().getLocation().toOSString()),
				haxeProject.getPathManager().getSourceFolders().get(0).getLocation().toOSString());
		
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
