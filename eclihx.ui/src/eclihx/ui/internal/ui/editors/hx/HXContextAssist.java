package eclihx.ui.internal.ui.editors.hx;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class HXContextAssist implements IContentAssistProcessor {

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		String[] fgProposals = new String[]{"hello", "hi", "bye"};
		ICompletionProposal[] result = new ICompletionProposal[fgProposals.length];
		for (int i= 0; i < fgProposals.length; i++) {
			IContextInformation info = 
				new ContextInformation(
					fgProposals[i], 
					"hello info"
				); //$NON-NLS-1$
			result[i]= 
				new CompletionProposal(
					fgProposals[i], 
					offset, 
					0, 
					fgProposals[i].length(), 
					null, 
					fgProposals[i], 
					info, 
					"Hi?"
				); //$NON-NLS-1$
		}
		return result;

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
	public char[] getContextInformationAutoActivationCharacters() {
		return new char[] { '#' };
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

}
