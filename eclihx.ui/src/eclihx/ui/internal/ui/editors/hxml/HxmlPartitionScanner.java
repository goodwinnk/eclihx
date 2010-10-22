package eclihx.ui.internal.ui.editors.hxml;

import java.util.ArrayList;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

/**
 * Dividing the hxml-document into non-overlapping regions called partitions.
 */
public class HxmlPartitionScanner extends RuleBasedPartitionScanner implements
		IDocumentSetupParticipant {
	
	/**
	 * The identifier of the single-line end comment partition content type.
	 */
	public static final String HXML_SINGLE_LINE_COMMENT = "__hxml_singleline_comment";
	
	/**
	 * Initialize scanner with partitions matching rules.
	 */
	public HxmlPartitionScanner() 
	{
		super();
		
		ArrayList<IPredicateRule> rules = new ArrayList<IPredicateRule>();
	
		rules.add(new EndOfLineRule("#", new Token(HXML_SINGLE_LINE_COMMENT)));
	
		setPredicateRules(rules.toArray(new IPredicateRule[rules.size()]));
	}
	
	@Override
	public void setup(IDocument document) {
		IDocumentPartitioner partitioner = new FastPartitioner(
				this, 
				new String[] {
						HXML_SINGLE_LINE_COMMENT
				} );
		partitioner.connect(document);
		
		document.setDocumentPartitioner(partitioner);
	}
	
	
}
