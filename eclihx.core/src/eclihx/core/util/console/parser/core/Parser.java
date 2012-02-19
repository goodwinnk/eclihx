package eclihx.core.util.console.parser.core;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import eclihx.core.EclihxCore;

/**
 * My (Nikolay Krasko) own attempt to implement console parameter parser. It's a little bit naive but gives 
 * an ability to parse in strong-typed manner. Probably it should be removed and replaced with the one of the 
 * well-known and well-supported third-party parser of input parameters.  
 */
public class Parser {
	
	private Hashtable<String, Parameter> parametersStore = null;
	
	/**
	 * Initialize parser with parameters.
     *
	 * @param parameters List of allowed parameters
	 * @throws InitializeParseError Error of parser initialization.
	 */
	public void initialize(Parameter[] parameters) throws InitializeParseError {

		parametersStore = new Hashtable<String, Parameter>(parameters.length);
		
		for (Parameter parameter : parameters) {
			if (parametersStore.containsKey(parameter.getPrefix())) {
				
				parametersStore.clear();
				throw new InitializeParseError(
					String.format(
						"Invalid parameters set: '%1$s' prefix appears more than once.",
						parameter.getPrefix()
					)
				);
				
			} else {
				parametersStore.put(parameter.getPrefix(), parameter);
			}
		}
	}
	
	/**
	 * Get list of parser parameters key.
	 * 
	 * @return An arbitrary sorted list of non-empty parameters keys. 
	 */
	public Iterable<String> getParametersKeys()
	{
		if (parametersStore == null)
		{
			return new ArrayList<String>();
		}
		
		Set<String> keys = parametersStore.keySet();
		keys.remove("");
		
		return keys;
	}
	
	/**
	 * Parse input as a console parameters.
	 * 
	 * @param input A string with input that should be interpreted as console parameters.
	 * @return Output parameters
	 */
	public static String[] splitToParams(String input) {
		// Didn't want to write this method... but is there exist a build-in splitter?
		ArrayList<String> params = new ArrayList<String>();
		
		final char QUOTE = '\"';
		final char ESCAPE = '\\';
		
		StringBuilder tempParam = new StringBuilder();
		boolean isInQuotedString = false;
		
		PushbackReader reader = new PushbackReader(new StringReader(input));
		
		try {		
			for (int ch = reader.read(); ch != -1; ch = reader.read()) {				
				if (ch == QUOTE) {
					
					// Don't enter in quoted state if we have some part of parameter
					if (isInQuotedString || tempParam.length() == 0) {
						isInQuotedString = !isInQuotedString;
					} else {
						// Or quote is ordinal character for parameter
						tempParam.append((char) ch);
					}
					
				} else if (!isInQuotedString && Character.isWhitespace(ch)) {
					
					if (tempParam.length() != 0) {
						params.add(tempParam.toString());
						
						// clear
						tempParam.delete(0, tempParam.length());
					}					
					
				} else {
					if (ch == ESCAPE && isInQuotedString) {
						int nextChar = reader.read();
						if (nextChar == QUOTE) {
							ch = nextChar;
						} else {
							reader.unread(nextChar);
						}
					}
					
					tempParam.append((char) ch);
				}
			}
			
			// If we were have unfinished parameter
			if (!tempParam.toString().isEmpty()) {
				
				String paramString = tempParam.toString();
				
				// Change last char to escape chart if it is quote 
				if (isInQuotedString && paramString.endsWith(Character.toString(QUOTE))) {
					paramString = paramString.substring(0, paramString.length() - 1) + ESCAPE;
				}
				
				params.add(paramString);
			}	
			
			reader.close();
			
		} catch (IOException e) {
			// Should never happen
			EclihxCore.getLogHelper().logError(e);
		}
		
		
		
		return params.toArray(new String[params.size()]);
	}
	
	/**
	 * Parse an input after splitting it with Parse.splitToParams().
	 * 
	 * @param input string that should be splitted like console parameters 
	 * @throws ParseError Error in parsing.
	 */
	public void parse(String input) throws ParseError {
		parse(Parser.splitToParams(input));
	}
	
	/**
	 * Parse an array of input arguments.
	 * 
	 * @param args Console parameters arguments.
	 * @throws ParseError Error in parsing.
	 */
	public void parse(String[] args) throws ParseError {
		
		HashSet<String> notActivatedParams = new HashSet<String>(parametersStore.keySet());
		
		int i = 0; // current argument index
		
		while (i < args.length) {
			
			String currentPrefix = args[i];
			Parameter param = parametersStore.get(currentPrefix);
			
			if (param != null) {
				// We found parameter. Let's index pass prefix
				++i;
			} else {
				// We didn't find the parameter. Try an empty prefix
				currentPrefix = "";
				param = parametersStore.get(currentPrefix);
			}
			
			if (param != null) {
				
				// Process parameter and move index
				int newIndex = Math.min(i + param.numberOfParameters(), args.length);
				param.parse(Arrays.copyOfRange(args, i, newIndex));
				
				notActivatedParams.remove(currentPrefix);
				
				i = newIndex; 				
				
			} else {
				// Bad thing here...We don't know how to proceed
				throw new ParseError(String.format("Uknown prefix '%1$s'", args[i]));
			}
		}	
		
		for (String prefix : notActivatedParams) {

			// Because we have built notActivatedParams from prefixes 
			assert(parametersStore.get(prefix) != null);			
			parametersStore.get(prefix).absence();
		}
	}
}
