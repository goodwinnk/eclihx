package eclihx.core.util.console.parser.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

// TODO 5 Implement default implementation for file parameter
public class Parser {
	
	private Hashtable<String, Parameter> parametersStore = null;
	
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

	public Parser() {}
	
	public void parse(String[] args) throws ParseError {
		
		HashSet<String> notActivatedParams = 
			new HashSet<String>(parametersStore.keySet());
		
		int i = 0; // current argument index
		
		while (i < args.length) {
			
			String currentPrefix = args[i];
			Parameter param = parametersStore.get(currentPrefix);
			
			if (param != null) {
				// We found parameter. Let index pass prefix
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
