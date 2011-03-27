package eclihx.core.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;

/**
 * Make simple substitution in text files.
 */
public class SimpleTemplateProcessor {
	
	public static class ReplacePair {
		/**
		 * @param replaceFrom
		 * @param replaceTo
		 */
		public ReplacePair(String replaceFrom, String replaceTo) {
			super();
			this.replaceFrom = replaceFrom;
			this.replaceTo = replaceTo;
		}
		/**
		 * @return the replaceFrom
		 */
		public String getReplaceFrom() {
			return replaceFrom;
		}
		/**
		 * @param replaceFrom the replaceFrom to set
		 */
		public void setReplaceFrom(String replaceFrom) {
			this.replaceFrom = replaceFrom;
		}
		/**
		 * @return the replaceTo
		 */
		public String getReplaceTo() {
			return replaceTo;
		}
		/**
		 * @param replaceTo the replaceTo to set
		 */
		public void setReplaceTo(String replaceTo) {
			this.replaceTo = replaceTo;
		}
		
		private String replaceFrom;
		private String replaceTo;		
	}
	
	private List<ReplacePair> replacements;
	
	/**
	 * @param replacements
	 */
	public SimpleTemplateProcessor(List<ReplacePair> replacements) {
		super();
		this.replacements = replacements;
	}
	
	public void resolveTemplate(File templateFile, File outputFile) {
		try {
			if (templateFile.isDirectory() || !templateFile.exists()) {
				throw new InvalidParameterException("templateFile is not a file or doesn't exist");
			}
			
			HashMap<String, String> replacementMap = new HashMap<String, String>();
			for (ReplacePair replacementPair : replacements) {
				replacementMap.put(replacementPair.replaceFrom, replacementPair.replaceTo);
			}
			
			Reader reader = new TokenReplacingReader(new FileReader(templateFile), replacementMap);
			Writer writer = new FileWriter(outputFile);
			
			int data = reader.read(); 
			while (data != -1) {
				writer.write(data);
				data = reader.read();
			}
			
			reader.close();
			writer.close();
		} catch (IOException ex) {
			
		}
	}
}
