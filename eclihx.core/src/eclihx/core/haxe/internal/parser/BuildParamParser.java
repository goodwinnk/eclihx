package eclihx.core.haxe.internal.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import eclihx.core.haxe.internal.configuration.HaxeConfiguration;
import eclihx.core.haxe.internal.configuration.HaxeConfigurationList;
import eclihx.core.haxe.internal.configuration.InvalidConfiguration;
import eclihx.core.util.console.parser.Builder;
import eclihx.core.util.console.parser.IParamExistense;
import eclihx.core.util.console.parser.IIntValue;
import eclihx.core.util.console.parser.IStringValue;
import eclihx.core.util.console.parser.core.InitializeParseError;
import eclihx.core.util.console.parser.core.Parameter;
import eclihx.core.util.console.parser.core.ParseError;
import eclihx.core.util.console.parser.core.Parser;

/**
 * Class which should parse haXe builder parameters and store them
 * into the <code>HaxeConfigurationList</code>
 */
public class BuildParamParser {
	
	private final Parser parser;
	private HaxeConfiguration currentConfig = null;
	private boolean parsingFile = false;
	private boolean confintueConfig = false;
	
	private class NextFlagParam implements IParamExistense {
		public void save(boolean exist) throws ParseError {
			// Should never be in this parser 
			assert(exist != true);		
		}		
	}
	
	private class LibraryParam implements IStringValue {
		public void save(String value) throws ParseError {
			currentConfig.addLibrary(value);
		}
	}
	
	private class CompilationFlagParam implements IStringValue {
		public void save(String value) throws ParseError {
			currentConfig.addCompilationFlag(value);
		}
	}
	
	private class DebugParam implements IParamExistense {
		public void save(boolean exist) throws ParseError {
			currentConfig.setDebug(exist);
		}
	}
	
	private class SwfOutput implements IStringValue {
		public void save(String value) throws ParseError {
			try {
				currentConfig.setFlashOutFile(value);
			} catch (InvalidConfiguration e) {
				throw new ParseError(e.getMessage());
			}
		}
	}
	
	private class SwfVersion implements IIntValue {
		public void save(int value) throws ParseError {
			// TODO 3 Implement check for version value lies between 6 and 10
			try {
				currentConfig.setFlashVersion(value);
			} catch (InvalidConfiguration e) {
				throw new ParseError(e.getMessage());
			}
		}
	}
	
	private class HxmlStringParam implements IStringValue {

		public void save(String value) throws ParseError {
			
			if (parsingFile) {
				
				// If we're already parsing file we shouldn't accept this parameter
				throw new ParseError("Invalid parameter in the hxml-file");
			
			} else {
				
				confintueConfig = true;
				parseFile(value);
				
			}			
		}		
	}
	
	
	private class NotImplementedString implements IStringValue {
		public void save(String value) throws ParseError {
			// TODO Auto-generated method stub
		}		
	}
	
	private class NotImplementedInt implements IIntValue {
		public void save(int value) throws ParseError {
			// TODO Auto-generated method stub
		}		
	}
	
	private class NotImpementedFlag implements IParamExistense {
		public void save(boolean exist) throws ParseError {
			// TODO Auto-generated method stub
		}
	}
	
	private void init() {
		
		// TODO 9 Remove this variables
		NotImplementedString nis = new NotImplementedString();
		NotImplementedInt nii = new NotImplementedInt();
		NotImpementedFlag nif = new NotImpementedFlag();
		
		Parameter params[] = new Parameter[] {
			
			// -cp <path> : add a directory to find source files
			Builder.createStringParam("-cp", nis),		
			
			// -js <file> : compile code to JavaScript file
			Builder.createStringParam("-js", nis),
			
			// -as3 <directory> : generate AS3 code into target directory
			Builder.createStringParam("-as3", nis),
			
			// -swf <file> : compile code to Flash SWF file
			Builder.createStringParam("-swf", nis),
			
			// -swf-version <version> : change the SWF version (6,7,8,9)
			Builder.createIntParam("-swf-version", nii),
			
			// -swf-header <header> : define SWF header (width:height:fps:color)
			Builder.createStringParam("-swf-header", nis),
			
			// -swf-lib <file> : add the SWF library to the compiled SWF
			Builder.createStringParam("-swf-lib", nis),
			
			// -neko <file> : compile code to Neko Binary
			Builder.createStringParam("-neko", nis),
			
			// -x <file> : shortcut for compiling and executing a neko file
			Builder.createStringParam("-x", nis),
			
			// -xml <file> : generate XML types description
			Builder.createStringParam("-xml", nis),
			
			// -main <class> : select startup class
			Builder.createStringParam("-main", nis),
			
			// -lib <library[:version]> : use an haxelib library
			Builder.createStringParam("-lib", new LibraryParam()),
			
			// -D <var> : define a conditional compilation flag
			Builder.createStringParam("-D", new CompilationFlagParam()),
			
			// -resource <file@name> : add a named resource file
			Builder.createStringParam("-resource", nis),
			
			// -exclude <filename> : don't generate code for classes listed in this file
			Builder.createStringParam("-exclude", nis),
			
			// -v : turn on verbose mode
			Builder.createFlagParam("-v", nif),
			
			// -debug : add debug informations to the compiled code
			Builder.createFlagParam("-debug", new DebugParam()),
			
			// -prompt : prompt on error
			Builder.createFlagParam("-prompt", nif),
			
			// -cmd : run the specified command after successful compilation
			Builder.createStringParam("-cmd", nis),
			
			// --flash-strict : more type strict flash API
			Builder.createFlagParam("--flash-strict", nif),
			
			// --override : ensure that overriden methods are declared with 'override'
			Builder.createFlagParam("--override", nif),
			
			// --no-traces : don't compile trace calls in the program
			Builder.createFlagParam("--no-traces", nif),
			
			// --flash-use-stage : place objects found on the stage of the SWF lib
			Builder.createFlagParam("--flash-use-stage", nif),
			
			// --neko-source : keep generated neko source
			Builder.createFlagParam("--neko-source", nif),
			
			//  --gen-hx-classes <file> : generate hx headers from SWF9 file
			Builder.createStringParam(" --gen-hx-classes", nis),
			
			//  --next : separate several haxe compilations
			Builder.createFlagParam("--next", new NextFlagParam()),
			
			//  --altfmt : use alternative error output format
			Builder.createFlagParam("--altfmt", nif),
			
			//  --auto-xml : automatically create an XML for each target
			Builder.createFlagParam("--auto-xml", nif),
			
			//  --display : display code tips
			Builder.createStringParam("--display", nis),
			
			//  --no-output : compiles but does not generate any file
			Builder.createFlagParam("--no-output", nif),
			
			//  --times : mesure compilation times
			Builder.createFlagParam("--times", nif),
			
			//  --no-inline : disable inlining
			Builder.createFlagParam("--no-inline", nif),
			
			//  -help  Display this list of options
			Builder.createFlagParam("-help", nif),
			
			//  --help  Display this list of options
			Builder.createFlagParam("--help", nif),
			
			// hxml file
			Builder.createStringParam("", new HxmlStringParam())
		};
		
		try {
			parser.initialize(params);
		} catch (InitializeParseError e) {
			e.printStackTrace();
		}
	}
	
	public BuildParamParser() {
		parser = new Parser();
		init();
	}
	
	private  HaxeConfiguration parseConfiguration(String strArray[]) throws ParseError {
		
		if (!confintueConfig) {
			currentConfig = new HaxeConfiguration();
		}
		
		parser.parse(strArray);
		
		return currentConfig;		
	}
	
	private HaxeConfigurationList parse(String str) throws ParseError {
		
		String configStrs[] = str.split("--next");
		
		HaxeConfigurationList configList = new HaxeConfigurationList();
	
		for (String configStr : configStrs) {
			
			configList.add(parseConfiguration(configStr.replaceAll("\\s+", " ").split(" ")));
			
		}
		
		return configList;
	}
	
	public HaxeConfigurationList parseString(String configStr) throws ParseError {
		this.parsingFile = false;
		return parse(configStr);
	}
	
	public HaxeConfigurationList parseFile(String fileName) throws ParseError
	{
		this.parsingFile = true;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String buffer, fileContent = new String();
			while((buffer = in.readLine())!= null) {
				fileContent += buffer + "";
			}
			in.close();
			
			// Remove all not necessary spaces and new line symbols
			return parse(fileContent.replaceAll("\\n+"," "));
			
		} catch (IOException e) {
			throw new ParseError("Can't open file!");
		}
	}
}
