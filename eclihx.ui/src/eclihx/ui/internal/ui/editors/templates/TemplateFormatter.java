package eclihx.ui.internal.ui.editors.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

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
		
		private String unmarkedString;
		private TemplateVariable[] updatedVariables;
		
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
		
		/**
		 * Remove markers from string and update template variables offsets.
		 * @param formattedString already formatted string.
		 */
		public void unmark(String formattedString) {
			StringBuffer unmarkedBuffer = new StringBuffer(formattedString);
			
			int variableOffset = unmarkedBuffer.indexOf(MARKER_PREFIX, 0);
			
			// Collection on new offsets for each variable
			final HashMap<TemplateVariable, ArrayList<Integer>> variablesOffsets = 
					new HashMap<TemplateVariable, ArrayList<Integer>>();
			
			// Remove markers and collect information about offsets.
			while (variableOffset != -1) {
				String idString = unmarkedBuffer.substring(variableOffset + MARKER_PREFIX.length(), variableOffset + MARKER_SIZE);
				int id = Integer.parseInt(idString);
				
				TemplateVariable variable = variablesIds.get(id);
				
				if (!variablesOffsets.containsKey(variable)) {
					variablesOffsets.put(variable, new ArrayList<Integer>());
				}
				
				
				variablesOffsets.get(variable).add(variableOffset);
				
				// Remove marker
				unmarkedBuffer.replace(variableOffset, variableOffset + MARKER_SIZE, "");
				
				// Update loop condition variable - move to next variable
				variableOffset = unmarkedBuffer.indexOf(MARKER_PREFIX, variableOffset);
			}
			
			// Update variables
			for (Entry<TemplateVariable, ArrayList<Integer>> variableToOffsets : variablesOffsets.entrySet()) {
				TemplateVariable variable = variableToOffsets.getKey();
				ArrayList<Integer> offsets = variableToOffsets.getValue();
				
				variable.setOffsets(toIntArray(offsets));
			}
			
			// Store results
			unmarkedString = unmarkedBuffer.toString();
			updatedVariables = variablesOffsets.keySet().toArray(new TemplateVariable[variablesOffsets.keySet().size()]);
		}

		public String getMarkedString() {
			return markedString;
		}
		
		public String getUnmarkedString() {
			if (unmarkedString == null) {
				throw new IllegalStateException("should be unmarked before");
			}
			
			return unmarkedString;
		}
		
		public TemplateVariable[] getCorrectedVariables() {
			if (updatedVariables == null) {
				throw new IllegalStateException("should be unmarked before");
			}
			
			return updatedVariables;
		}
		
		private static int[] toIntArray(List<Integer> list) {
			int[] ret = new int[list.size()];
			for (int i = 0; i < ret.length; i++)
				ret[i] = list.get(i);
			return ret;
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
		
		VariableOffsetsTracker offsetsTracker = VariableOffsetsTracker.mark(buffer.getString(), buffer.getVariables());
		
		String formatted = CodeFormatter.formatSelection(offsetsTracker.getMarkedString(), formatOptions, lineIndentation);
		
		offsetsTracker.unmark(formatted);
		
		buffer.setContent(offsetsTracker.getUnmarkedString(), offsetsTracker.getCorrectedVariables());
	}	
}
