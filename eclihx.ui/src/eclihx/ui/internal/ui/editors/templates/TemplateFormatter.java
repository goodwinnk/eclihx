package eclihx.ui.internal.ui.editors.templates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateVariable;

import eclihx.core.haxe.model.CodeFormatter;
import eclihx.core.haxe.model.CodeFormatter.FormatOptions;
import eclihx.ui.actions.FormatAllAction;

/**
 * A special formatter for inserting templates.
 */
public class TemplateFormatter {
	
	/**
	 * Will mark given text with special markers steady to formatter manipulations. After formatting is done those
	 * markers will be removed with updating template variables offsets.
	 */
	static class VariableOffsetsTracker {
		
		private static final int MARKER_SIZE = 6;
		private static final String MARKER_PREFIX = "$$__";
		private static final String MARKER_FORMAT_STRING = "$$__%02d";
		
		static {
			assert MARKER_FORMAT_STRING.startsWith(MARKER_PREFIX);
			assert String.format(MARKER_FORMAT_STRING, 0).length() == MARKER_SIZE;
		}
		
		private final String markedString;
		
		// Marker id to variable
		private final HashMap<Integer, TemplateVariable> variablesIds = new HashMap<Integer, TemplateVariable>();
		
		private VariableOffsetsTracker(String text, TemplateVariable[] variables) {
			
			// Collect variables offsets
			SortedMap<Integer, TemplateVariable> initialOffsets = new TreeMap<Integer, TemplateVariable>();
			for (TemplateVariable variable : variables) {
				for (int offset : variable.getOffsets()) {
					initialOffsets.put(offset, variable);
				}
			}
			
			int id = 0;
			StringBuffer buffer = new StringBuffer(text);
			
			for (Entry<Integer, TemplateVariable> pair : initialOffsets.entrySet()) {
				int originalOffset = pair.getKey();
				int newOffset = originalOffset + id * MARKER_SIZE;
				
				buffer.insert(newOffset, String.format(MARKER_FORMAT_STRING, id));
				variablesIds.put(id, pair.getValue());
				id++;
			}
			
			markedString = buffer.toString();
		}
		
		public static VariableOffsetsTracker mark(String text, TemplateVariable[] varibles) {
			return new VariableOffsetsTracker(text, varibles);
		}
		
		public void unmark() {
			
		}

		public String getMarkedString() {
			return markedString;
		}

	}
	
	/**
	 * Format template.
	 * 
	 * @param buffer a buffer with template prepared for insertion.
	 * @param lineIndentation pattern for line indentation.
	 */
	public void format(TemplateBuffer buffer, String lineIndentation) {
		FormatOptions formatOptions = FormatAllAction.getPreferenceOptions();
		formatOptions.setIndentOnEmptyLines(true);
		
		// VariableOffsetsTracker offsetsTracker = VariableOffsetsTracker.mark(buffer.getString(), buffer.getVariables());
		
		String formatted = CodeFormatter.formatSelection(buffer.getString(), formatOptions, lineIndentation);
		buffer.setContent(formatted, buffer.getVariables());
	}	
}
