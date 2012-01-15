package eclihx.tests.ui.internal.ui.editors.hx;

import java.util.ArrayList;

import junit.framework.Assert;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.junit.Test;

import eclihx.ui.internal.ui.editors.hx.HXContextAssist;
import eclipse.testframework.text.DisplayHelper;
import eclipse.testframework.text.EditorTestHelper;

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
	
	@Test
	public void byteOffsetUtf8Completion() throws CoreException {
		haxeFile.getBaseFile().setCharset("UTF-8", null);
		
		configureFromText("class Test { \n" +
		                  "  // Привет \n" + 
                		  "  public function test() { \n" +
		                  "    \"\".<caret>\n" +
                          "  } \n" +
		                  "}");
		
		ICompletionProposal[] collectProposals = collectProposals(new Region(getCaret(), 1));		
		assertProposalsExist(collectProposals, "charAt", "indexOf");		
	}
	
	protected void assertProposalsExist(ICompletionProposal[] proposals, String... expected) {
		int previousProposalIndex = -1;
		String previousExpectedProposal = "";
		
		for (String expectedProposal : expected) {
			int index = assertProposalExist(proposals, expectedProposal);
			
			// If this is not first loop
			if (previousProposalIndex != -1) {
				String message = String.format("'%s' proposal was before '%s' proposal in %s", 
						expectedProposal, previousExpectedProposal, proposalsStrings(proposals));
				
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
		
		Assert.fail(String.format("%s proposal wasn't found in %s", expectedProposal, proposalsStrings(proposals)));
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
					String.format("%s proposal should absent in the list %s", unexpectedProposal, proposalsStrings(proposals)),
					completionProposal.getDisplayString().startsWith(unexpectedProposal));
		}
	}
	
	private static ArrayList<String> proposalsStrings(ICompletionProposal[] proposals) {
		ArrayList<String> result = new ArrayList<String>(proposals.length);
		for (ICompletionProposal proposal : proposals) {
			result.add(proposal.getDisplayString());
		}
		
		return result;
	}
	 
	protected ICompletionProposal[] collectProposals(IRegion selection) {
		HXContextAssist haxeProcessor = new HXContextAssist();

		ICompletionProposal[] proposals = haxeProcessor.computeCompletionProposals(
				getEditor().getViewer(), selection.getOffset());
		
		DisplayHelper.sleep(EditorTestHelper.getActiveDisplay(), 300);
		
		return proposals;
	}
}
