package eclihx.core.haxe.internal.hxml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Class which should parse hxml file and provide info about haxe build 
 * settings
 */
public class BuildParamParser {
	
	public static LinkedList<IBuildParamsContainer> parseFile(String fileName) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String buffer, fileContent = new String();
		while((buffer = in.readLine())!= null) {
			fileContent += buffer + "\n";
		}
		in.close();
		
		return parse(fileContent, "\n");

	}
	
	static LinkedList<IBuildParamsContainer> parse(String paramsContent, String paramsSplit) {
		// Brake file content of the files to lines
		String lines[] = paramsContent.split(paramsSplit);
		
		LinkedList<IBuildParamsContainer> containers = new LinkedList<IBuildParamsContainer>();
		LinkedList<String> trimLinesList = new LinkedList<String>();
		
		for (String line : lines) {
			
			String trimLine = line.trim();			
			
			if (trimLine.startsWith("--next")) { 
				
				// after Next directive we should start new list of the instructions
				containers.add(parseTargetLines(trimLinesList));
				trimLinesList.clear();
				
			} else {
				
				trimLinesList.add(trimLine);
				
			}				
		}
		
		// Add last container
		containers.add(parseTargetLines(trimLinesList));
		
		// Remove null elements 
		while (containers.remove(null)) {}
				
		return containers;
	}
	
	/**
	 * Parse the bunch of the haxe parameters
	 * 
	 * @param trimLinesList
	 * @return could be null if there are no params
	 */
	static IBuildParamsContainer parseTargetLines(LinkedList<String> trimLinesList) {
		
		ParamsContainer targetParams = null;
		
		for (String trimLine: trimLinesList) {
			
			// assert(trimLine.startsWith("--next")); should never be there
			
			if (trimLine.startsWith("-")) {
				if (targetParams==null) {
					targetParams = new ParamsContainer();
				}
				
				parseParamLine(targetParams, trimLine);
			}
			
			// Just ignore bad cases
		}
			
		return targetParams;		
	}
	
	
	static void parseParamLine(IBuildParamsContainer container, String trimLine) {
		
		int splitPlace = trimLine.indexOf(" ");
		
		if (splitPlace != -1) {
			String key = trimLine.substring(0, splitPlace);
			String value = trimLine.substring(splitPlace + 1, trimLine.length());
			
			if (key.equals("-swf")) {
				container.setTargetPlatform(IBuildParamsContainer.TargetPlatform.Flash);
				container.setOutputFileName(value);	
			} else if (key.equals("-swf-verstion")) {
				// set swf version
			}
		}
		
		// TODO: handle everything and throw exception here
	}
}
