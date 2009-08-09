package eclihx.ui.internal.ui.editors.hx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionListener;
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
public final class HXContextAssist implements IContentAssistProcessor, ICompletionListener {
	
	/**
	 * A very simple context which invalidates information after typing several
	 * chars.
	 */
	private class HaxeContextValidator implements IContextInformationValidator {
		private int initialOffset;
		
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.text.contentassist.IContextInformationValidator#install(org.eclipse.jface.text.contentassist.IContextInformation, org.eclipse.jface.text.ITextViewer, int)
		 */
		@Override
		public void install(IContextInformation info, ITextViewer viewer, int offset) {
			this.initialOffset = offset;
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.jface.text.contentassist.IContextInformationValidator#isContextInformationValid(int)
		 */
		@Override
		public boolean isContextInformationValid(int offset) {
			return Math.abs(initialOffset - offset) < 1;
		}		
	}
	
	/**
	 * Characters for auto activation proposal computation.
	 */
	private static final char[] VALID_HAXE_PROPOSALS_CHARS = new char[] { '.' };
	
	private static final char[] VALID_HAXE_INFO_CHARS = new char[] { '(' };
	
	/**
	 * Keyword proposals.
	 */
	// private static final List<ContentInfo> keywordProposals = 
	//		new ArrayList<ContentInfo>();
	
	/**
	 * Stores cached information from haXe compiler.
	 */
	final private ContentInfoCache infosCache = new ContentInfoCache();

	
	static {
		Arrays.sort(VALID_HAXE_PROPOSALS_CHARS);
		Arrays.sort(VALID_HAXE_INFO_CHARS);
	}
	
	/**
	 * Generate proposal object for the function.
	 * 
	 * @param offset The starting position of info in the file.
	 * @param offsetEnd The end offset for the substitution.
	 * @param contentInfo An object with information about given position. 
	 * 		Function object is expected. Can't be null.
	 * @return ICompletionProposal object which is ready to be added to computeCompletionProposals
	 * 		result collection.
	 */
	private ICompletionProposal generateFunctionProposal(final int offsetStart, final int offsetEnd,
			final ContentInfo contentInfo) {
		
		Assert.isLegal(contentInfo != null);
		Assert.isLegal(contentInfo.isFunction());
		
		final String insertString = contentInfo.getName() + "()";
		final String proposalText = contentInfo.getName() + " : " + contentInfo.getType();
		
		final boolean hasParameters = !contentInfo.getType().startsWith("Void");
		
		final IContextInformation proposalTooltip = hasParameters ? 
				new ContextInformation(insertString, contentInfo.getType()) : null;
		
		// Omit brackets for functions without parameters and step into for other functions.
		final int cursorOffset = hasParameters ? insertString.length() - 1 : insertString.length();
		
		return new CompletionProposal(insertString, offsetStart, offsetEnd - offsetStart, cursorOffset,
				PluginImages.get(PluginImages.IMG_MISC_PUBLIC), 
				proposalText, proposalTooltip, contentInfo.getDoc());
	}
	
	/**
	 * Generate proposal object for the non-function. It can be field or variable.
	 * 
	 * @param offsetStart The starting position of info in the file.
	 * @param offsetEnd The end offset for the substitution.
	 * @param contentInfo An object with information about given position. 
	 * 		Not-function object is expected. Can't be null.
	 * @return ICompletionProposal object which is ready to be added to computeCompletionProposals
	 * 		result collection.
	 */
	private ICompletionProposal generateFieldProposal(final int offsetStart, final int offsetEnd, 
			final ContentInfo contentInfo) {
		
		Assert.isLegal(contentInfo != null);
		
		return new CompletionProposal(
				contentInfo.getName(), //  the actual string to be inserted into the document
				offsetStart, // the offset of the text to be replaced
				offsetEnd - offsetStart, // the length of the text to be replaced
				contentInfo.getName().length(), // the position of the cursor following the insert relative to start offset
				PluginImages.get(PluginImages.IMG_FIELD_PUBLIC), // the image to display for this proposal
				contentInfo.getName() + " : " + contentInfo.getType(), // the string to be displayed for the proposal
				null, // the context information associated with this proposal
				contentInfo.getDoc() // the additional information associated with this proposal
		);
	}
	
	/**
	 * Generate proposal object for the class name.
	 * 
	 * @param offsetStart The starting position of info in the file.
	 * @param offsetEnd The end offset for the substitution.
	 * @param contentInfo An object with information about given position. 
	 * 		Class name object is expected. Can't be null.
	 * @return ICompletionProposal object which is ready to be added to computeCompletionProposals
	 * 		result collection.
	 */
	private ICompletionProposal generateClassNameProposal(final int offsetStart, final int offsetEnd, 
			final ContentInfo contentInfo) {
		
		Assert.isLegal(contentInfo != null);
		Assert.isLegal(contentInfo.isClassName());
		
		return new CompletionProposal(
				contentInfo.getName(), offsetStart, offsetEnd - offsetStart,
				contentInfo.getName().length(), 
				PluginImages.get(PluginImages.IMG_CLASS_PUBLIC),
				contentInfo.getName(), null, contentInfo.getDoc());
	}
	
	/**
	 * Generate proposal object for the package.
	 * 
	 * @param offsetStart The starting position of info in the file.
	 * @param offsetEnd The end offset for the substitution.
	 * @param contentInfo An object with information about given position. 
	 * 		Not-function object is expected. Can't be null.
	 * @return ICompletionProposal object which is ready to be added to computeCompletionProposals
	 * 		result collection.
	 */
	private ICompletionProposal generatePackageProposal(final int offsetStart, final int offsetEnd, 
			final ContentInfo contentInfo) {
		
		Assert.isLegal(contentInfo != null);
		Assert.isLegal(contentInfo.isPackage());
		
		return new CompletionProposal(
				contentInfo.getName(), offsetStart, offsetEnd - offsetStart, 
				contentInfo.getName().length(), 
				PluginImages.get(PluginImages.IMG_PACKAGE),
				contentInfo.getName(), null, contentInfo.getDoc());
	}
	
	/**
	 * Generate proposal from the information about the given position.
	 * 
	 * @param offsetStart The starting position of info in the file.
	 * @param offsetEnd The end offset for the substitution.
	 * @param contentInfo An object with information about given position. Can't be null.
	 * @return ICompletionProposal object which is ready to be added to computeCompletionProposals
	 * 		result collection. Method returns null if it can't generate proposal for given
	 *      ContentInfo object.
	 */
	private ICompletionProposal generateProposal(final int offsetStart, final int offsetEnd, 
			final ContentInfo contentInfo) {
		
		if (contentInfo.isFunction()) {
			return generateFunctionProposal(offsetStart, offsetEnd, contentInfo);
		} else if (contentInfo.isPackage()) {
			return generatePackageProposal(offsetStart, offsetEnd, contentInfo);
		} else if (contentInfo.isClassName()) {
			return generateClassNameProposal(offsetStart, offsetEnd, contentInfo);
		} else if (contentInfo.isVariable()) {	
			return generateFieldProposal(offsetStart, offsetEnd, contentInfo); 
		} else {
			Assert.isTrue(contentInfo.isCallDescription());
			return null;
		}
	}
	
	/**
	 * Generate proposals list from the haXe content informations list.
	 * @param offsetStart The starting position of info in the file.
	 * @param offsetEnd The end offset for the substitution.
	 * @param infos a list with content informations.
	 * @return list with proposals.
	 */
	private ICompletionProposal[] generateProposalsList(final int offsetStart, final int offsetEnd, 
			List<ContentInfo> infos) {
		
		final ArrayList<ICompletionProposal> resultProposals = new ArrayList<ICompletionProposal>();
		for (ContentInfo contentInfo : infos) {
			ICompletionProposal proposal = generateProposal(offsetStart, offsetEnd, contentInfo);
			
			if (proposal != null) {
				resultProposals.add(proposal);
			}
		}
		
		return resultProposals.toArray(new ICompletionProposal[0]);		
	}
	
	/**
	 * Gets the {@link IHaxeSourceFile} from the input. If input has another
	 * source this method will return null.
	 * 
	 * @param input the editor input.
	 * @return {@link IHaxeSourceFile} object or <code>null</code>.
	 */
	private IHaxeSourceFile getHaxeSourceFile(IEditorInput input) {
		if (input instanceof IFileEditorInput) {
			IHaxeElement haxeElement = EclihxCore.getDefault()
					.getHaxeWorkspace().getHaxeElement(
							((IFileEditorInput) input).getFile());

			if (haxeElement instanceof IHaxeSourceFile) {
				return (IHaxeSourceFile) haxeElement;
			}
		}

		return null;
	}
	
	/**
	 * Method searches the beginning of the identifier 
	 * 
	 * @param text the text where search should be done.
	 * @param offset 
	 * @return
	 */
	private int getIdentifierStartOffset(String text, int offset) {
		int identStartOffset = offset;
		
		while ((identStartOffset != 0) && 
				Character.isUnicodeIdentifierPart(text.charAt(identStartOffset - 1))) {
			
			identStartOffset--;
		}
			
		return identStartOffset;
	}
	
	/**
	 * Get the information from the haXe compiler.
	 *
	 * @param viewer the viewer whose document is used to compute the proposals
	 * @param offset an offset within the document for which completions should be computed
	 * @return a list with computed content informations.
	 */
	private List<ContentInfo> getContentInfoForce(ITextViewer viewer, int offset) {
		// Get current file
		IEditorPart editor = EclihxUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		// Save the file
		// TODO 6: fix it. For example there should be a separate storage for 
		// the current file.		
		editor.doSave(null);
		
		IHaxeSourceFile haxeFile = getHaxeSourceFile(editor.getEditorInput());
		
		if (haxeFile == null) {
			return null;
		}
		
		return HaxeContentAssistManager.getTips(haxeFile, offset);
	}
	
	/*================== Begin IContentAssistProcessor methods ===================================*/
	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.jface.text.contentassist.IContentAssistProcessor#
	 * computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
	 */
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		
		final String fileText = viewer.getDocument().get();
		final int identOffset = getIdentifierStartOffset(fileText, offset);
		
		Assert.isTrue(identOffset <= offset);
		
		infosCache.updateState(identOffset);
		
		if (!infosCache.isValid()) {			
			List<ContentInfo> infos = getContentInfoForce(viewer, identOffset);
			infosCache.build(identOffset, infos);
		} 
		
		Assert.isTrue(infosCache.isValid());
		
		final String identifierPart = fileText.substring(identOffset, offset);
		return generateProposalsList(identOffset, offset, 
				infosCache.getFilteredInfos(identifierPart));
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
	 */
	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return VALID_HAXE_PROPOSALS_CHARS;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
	 */
	@Override
	public String getErrorMessage() {
		// It looks like in current version of Eclipse user won't see this message anyway
		return null; 
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer, int)
	 */
	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
		
		if (offset != 0) {
			char previousChar = viewer.getDocument().get().charAt(offset - 1);
			
			if (previousChar == ',') {
				// TODO 6: add context info for function parameters.
			}
			
			if (previousChar == '(') {
				// In current version we count informations only for the open bracket				
				List<ContentInfo> contextInfos = getContentInfoForce(viewer, offset);
				
				List<IContextInformation> resultInfos = new ArrayList<IContextInformation>();
				for (ContentInfo contextInfo : contextInfos) {
					resultInfos.add(new ContextInformation(contextInfo.getName(), 
							contextInfo.getType()));
				}
				
				return resultInfos.toArray(new IContextInformation[0]);
			}
		}
		
		return null;		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
	 */
	@Override
	public IContextInformationValidator getContextInformationValidator() {
		return new HaxeContextValidator();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationAutoActivationCharacters()
	 */
	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		return VALID_HAXE_INFO_CHARS;
	}
	/*================== end IContentAssistProcessor methods =====================================*/

	
	/*================== begin ICompletionListener methods =======================================*/
	/**
	 * Clear cached proposals.  
	 * @see org.eclipse.jface.text.contentassist.ICompletionListener#assistSessionEnded(org.eclipse.jface.text.contentassist.ContentAssistEvent)
	 */
	@Override
	public void assistSessionEnded(ContentAssistEvent event) {
		infosCache.invalidate();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.ICompletionListener#assistSessionStarted(org.eclipse.jface.text.contentassist.ContentAssistEvent)
	 */
	@Override
	public void assistSessionStarted(ContentAssistEvent event) {
		// do nothing
	}

	@Override
	public void selectionChanged(ICompletionProposal proposal, boolean smartToggle) {
		// do nothing
	}
	/*================== end ICompletionListener methods =========================================*/	
}
