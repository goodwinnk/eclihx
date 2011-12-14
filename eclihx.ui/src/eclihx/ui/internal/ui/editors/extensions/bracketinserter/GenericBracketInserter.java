package eclihx.ui.internal.ui.editors.extensions.bracketinserter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;

/**
 * Generic auto bracket inserter.
 */
public class GenericBracketInserter implements VerifyKeyListener {
	
	/**
	 * Pair of brackets configured for inserted in filtered partitions.
	 */
	public static class PairConfiguration {
		private final char open;
		private final char close;
		private IFilter<String> openPartitionFilter;
		private IFilter<String> closePartitionFilter;
		private boolean isEnabled;

		/**
		 * Creates a default enabled configuration of brackets.
		 * 
		 * @param open open bracket char.
		 * @param close close bracket char.
		 * @param partitionNameFilter Filter of partitions where this configuration is acceptable.
		 */
		public PairConfiguration(char open, char close, IFilter<String> partitionNameFilter) {
			this(open, close, partitionNameFilter, partitionNameFilter);
		}
		
		/**
		 * Creates an enabled configuration of brackets with different partitions filters for open and close brackets.
		 * 
		 * @param open open bracket char.
		 * @param close close bracket char.
		 * @param openPartitionFilter Filter of partitions where this configuration is acceptable for open bracket.
		 * @param closePartitionFilter Filter of partitions where this configuration is acceptable for close bracket.
		 */
		public PairConfiguration(char open, char close, IFilter<String> openPartitionFilter, IFilter<String> closePartitionFilter) {
			this.open = open;
			this.close = close;
			this.openPartitionFilter = openPartitionFilter;
			this.closePartitionFilter =	closePartitionFilter;
			this.isEnabled = true;
		}
		
		/**
		 * @return Open bracket char.
		 */
		public char getOpen() {
			return open;
		}

		/**
		 * @return Close bracket char.
		 */
		public char getClose() {
			return close;
		}

		/**
		 * @return Is configuration enabled.
		 */
		public boolean isEnabled() {
			return isEnabled;
		}

		/**
		 * Change enable set of the configuration.
		 * @param isEnabled A new state for setting up.
		 */
		public void setEnabled(boolean isEnabled) {
			this.isEnabled = isEnabled;
		}
		
		/**
		 * @return Filter of partitions names for open bracket.
		 */
		public IFilter<String> getOpenPartitionFilter() {
			return openPartitionFilter;
		}

		/**
		 * @return Filter of partitions names for close bracket.
		 */
		public IFilter<String> getClosePartitionFilter() {
			return closePartitionFilter;
		}
	}
	
	private enum BracketType {
		OPEN, CLOSE
	}
	
	final Map<String, PairConfiguration> bracketsPairs = new HashMap<String, PairConfiguration>();
	private ISourceViewer viewer;
	
	/**
	 * Add pair brackets with the specified filter for partitions and a key, through which this
	 * configuration could be controlled in future.
	 * 
	 * @param key Key for getting and modification given configuration.
	 * @param configuration Pair of brackets configuration.
	 */
	public void addBrackets(String key, PairConfiguration configuration) {
		bracketsPairs.put(key, configuration);
	}
	
	/**
	 * Add a pair of brackets which is always enabled and active for all partitions.
	 * 
	 * @param open open bracket char.
	 * @param close close bracket char.
	 */
	public void addBrackets(char open, char close) {
		addBrackets(UUID.randomUUID().toString(), 
				new PairConfiguration(open, close, Filter.<String>any()));
	}
	
	/**
	 * Check if some bracket pair configuration is enabled.
	 * @return <code>true</code> if some configuration are enabled.
	 */
	public boolean isEnabled() {
		for (PairConfiguration configuration : bracketsPairs.values()) {
			if (configuration.isEnabled()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Set up source viewer. Not in constructor because it's should be possible
	 * to change it.
	 * 
	 * @param viewer Source viewer.
	 */
	public void setViewer(ISourceViewer viewer) {
		this.viewer = viewer;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.swt.custom.VerifyKeyListener#verifyKey(org.eclipse.swt.events.VerifyEvent)
	 */
	@Override
	public void verifyKey(VerifyEvent event) {
		if (!event.doit || !isEnabled()) {
			return;
		}
		
		String partitionName = "";
		try {
			viewer.getDocument().getPartition(event.keyLocation).getType();
		} catch (BadLocationException e) {
			event.doit = true;
			return;
		}
		
		if (!processCharAsType(event.character, BracketType.OPEN, partitionName) ||
				!processCharAsType(event.character, BracketType.CLOSE, partitionName)) {
			event.doit = false;
			return;
		}
	}
	
	/**
	 * @return true if the key stroke event should be processed, false if it
	 *         should be discarded
	 */
	private boolean processCharAsType(char ch, BracketType type, String partitionName) {
		PairConfiguration pair = getPair(ch, type, partitionName);
		return pair == null || processBracketKeyStroke(viewer.getDocument(), viewer.getSelectedRange(), type, pair);
	}
	
	private PairConfiguration getPair(char ch, BracketType type, String partitionName) {
		for (PairConfiguration pair : bracketsPairs.values()) {
			if (type == BracketType.OPEN) {
				if (ch == pair.getOpen() && pair.getOpenPartitionFilter().isAccepted(partitionName)) {
					return pair;
				}
			} else if (type == BracketType.CLOSE) {
				if (ch == pair.getClose() && pair.getClosePartitionFilter().isAccepted(partitionName)) {
					return pair;
				}
			}			
		}
		
		return null;
	}
	
	/**
	 * @param doc document to be modified in result of the key stroke
	 * @param selection selection in the document at the time of the key stroke
	 *        (x = offset, y = length) or caret position if there was no
	 *        selection (x, y = 0)
	 * @param bracketsPair a pair of chars selected for processing
	 * @return true if the key stroke event should be processed, false if it
	 *         should be discarded
	 */
	private boolean processBracketKeyStroke(IDocument doc, Point selection, BracketType type, PairConfiguration bracketsPair) {
		
		final int offset = selection.x;
		final int length = selection.y;
		
		try {
			if (type == BracketType.OPEN) {
				if (offset + length < doc.getLength() && doc.getChar(offset + length) == bracketsPair.getOpen()) {
					// There's already an opening char in front of us, so skip it
					skipChar();
					return false;
				} else {
					// Auto-insert the closing char just after it...
					char[] pair = new char[] { bracketsPair.getOpen(), bracketsPair.getClose() };
					doc.replace(offset, 0, String.valueOf(pair));
					// ...and position the caret after the entered char
					skipChar();
					return false;
				}
			} else if (type == BracketType.CLOSE) {
				if (offset + length < doc.getLength() && doc.getChar(offset + length) == bracketsPair.getClose()) {
					// There's already a closing char in front of us, so skip it
					skipChar();
					return false;
				}
			}	
		} catch (BadLocationException e) {
			// log.logError(e);
		}
		
		return true;
	}

	private void skipChar() {
		StyledText text = viewer.getTextWidget();
		text.setCaretOffset(text.getCaretOffset() + 1);
	}
}
