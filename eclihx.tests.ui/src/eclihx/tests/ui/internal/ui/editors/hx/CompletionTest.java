package eclihx.tests.ui.internal.ui.editors.hx;

import junit.framework.Assert;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.junit.Test;

import eclihx.ui.internal.ui.editors.hx.HXContextAssist;

public class CompletionTest extends HXEditorTestBase {
	
	@Test
	public void simpleCompletion() {
		configureFromText("class Test { \n" +
	                      "  public function test() { \n" +
				          "    haxe.i<caret>\n" +
	                      "  } \n" +
				          "}");
		
		ICompletionProposal[] collectProposals = collectProposals(new Region(getCaret(), 1));
		
		assertProposalsExist(collectProposals, "Int32", "Int64", "io", "FastList");
		assertProposalAbsent(collectProposals, "Http", "Resource");
	}
	
	@Test
	public void abbreviationCompletion() {
		configureFromText("class Test { \n" +
	                      "  public function test() { \n" +
				          "    haxe.remoting.DC<caret>\n" +
	                      "  } \n" +
				          "}");
		
		ICompletionProposal[] collectProposals = collectProposals(new Region(getCaret(), 1));
		
		assertProposalsExist(collectProposals, "DelayedConnection", "AsyncDebugConnection");
	}
	
	@Test
	public void caseIndependentCompletion() {
		configureFromText("class Test { \n" +
	                      "  public function test() { \n" +
				          "    haxe.remoting.conn<caret>\n" +
	                      "  } \n" +
				          "}");
		
		ICompletionProposal[] collectProposals = collectProposals(new Region(getCaret(), 1));
		
		assertProposalsExist(collectProposals, "Connection", "HttpConnection");
	}
	
	protected void assertProposalsExist(ICompletionProposal[] proposals, String... expected) {
		int previousProposalIndex = -1;
		String previousExpectedProposal = "";
		
		for (String expectedProposal : expected) {
			int index = assertProposalExist(proposals, expectedProposal);
			
			// If this is not first loop
			if (previousProposalIndex != -1) {
				String message = String.format("'%s' proposal was before '%s' proposal", 
						expectedProposal, previousExpectedProposal);
				
				Assert.assertTrue(message, index > previousProposalIndex);
			}
			
			previousProposalIndex = index;
			previousExpectedProposal = expectedProposal;
		}
	}

	protected int assertProposalExist(ICompletionProposal[] proposals, String expectedProposal) {
		for (int i = 0; i < proposals.length; i++) {
			ICompletionProposal proposal = proposals[i];
			if (proposal.getDisplayString().startsWith(expectedProposal)) {
				return i;
			}
			
		}
		
		Assert.fail(String.format("%s proposal wasn't found", expectedProposal));
		return -1;
	}
	
	protected void assertProposalAbsent(ICompletionProposal[] proposals, String... absent) {
		for (String absentProposal : absent) {
			assertProposalAbsent(proposals, absentProposal);
		}
	}
	
	protected void assertProposalAbsent(ICompletionProposal[] proposals, String unexpectedProposal) {
		for (ICompletionProposal completionProposal : proposals) {
			Assert.assertFalse(
					String.format("%s proposal should absent in the list", unexpectedProposal),
					completionProposal.getDisplayString().startsWith(unexpectedProposal));
		}
	}
	
	protected ICompletionProposal[] collectProposals(IRegion selection) {
		HXContextAssist haxeProcessor = new HXContextAssist();

		ICompletionProposal[] proposals = haxeProcessor.computeCompletionProposals(
				getEditor().getViewer(), selection.getOffset());
		
		return proposals;
	}
}
