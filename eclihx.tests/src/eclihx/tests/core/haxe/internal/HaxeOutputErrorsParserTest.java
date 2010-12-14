package eclihx.tests.core.haxe.internal;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eclihx.core.haxe.internal.HaxeOutputErrorsParser;
import eclihx.core.haxe.internal.ICompilerError;
import eclihx.core.haxe.internal.IHaxeOutputErrorsParser;

public class HaxeOutputErrorsParserTest {
	
 	private IHaxeOutputErrorsParser errorsParser;
	
	@Before
	public void setUp() throws Exception {
		errorsParser = new HaxeOutputErrorsParser();
	}
	
	@Test
	public void shouldParseFilenameWithSeparators() {
		String filePath = (new File("some", "other")).getPath();
		String message = filePath + ":4: characters 15-33 : SomeMessage\r";
		
		ICompilerError compilerError = errorsParser.processErrorLine(message);
		
		Assert.assertEquals(filePath, compilerError.getFilePath());
	}
	
	@Test
	public void shouldParseFilenameLineNumber() {
		String message = "Hello.hx:4: characters 15-33 : SomeMessage";
		ICompilerError compilerError = errorsParser.processErrorLine(message);
		Assert.assertEquals(4, compilerError.getLineNumber());		
	}
	
	@Test
	public void shouldParsePositionInFile() {
		String message = "Hello.hx:4: characters 15-33 : SomeMessage";
		ICompilerError compilerError = errorsParser.processErrorLine(message);
		Assert.assertEquals(15, compilerError.getStartCharacter());
		Assert.assertEquals(33, compilerError.getEndCharacter());	
	}
	
	@Test
	public void shouldParseErrorMessage() {
		String message = "Hello.hx:4: characters 15-33 : You can't access the flash package with current compilation flags (for flash.Camera)";
		ICompilerError compilerError = errorsParser.processErrorLine(message);
		Assert.assertEquals(
				"You can't access the flash package with current compilation flags (for flash.Camera)", 
				compilerError.getMessage());
	}
	
	@Test
	public void shouldIgnoreCarrigeReturns() {
		String message = "Hello.hx:4: \rcharacters 15-33 : Some\r";
		ICompilerError compilerError = errorsParser.processErrorLine(message);
		
		Assert.assertNotNull(compilerError);
		Assert.assertEquals("Some", compilerError.getMessage());
	}
	
	@Test
	public void shouldParseToLines() {
		String message = "src/graphcore/Graph.hx:24: lines 24-26 : Invalid number of type parameters for graphcore.IVisitor\n";
		ICompilerError compilerError = errorsParser.processErrorLine(message);
		
		Assert.assertNotNull(compilerError);
		Assert.assertEquals(24, compilerError.getStartLine());
		Assert.assertEquals(26, compilerError.getEndLine());	
	}
	
	@Test
	public void shouldIgnoreNewLines() {
		String message = "Hello.hx:4: \ncharacters 15-33 : Some\n";
		ICompilerError compilerError = errorsParser.processErrorLine(message);
		
		Assert.assertNotNull(compilerError);
		Assert.assertEquals("Some", compilerError.getMessage());
	}
	
	@Test
	public void shouldSplitToLines() {
		String message1 = "Hello.hx:4: characters 15-33 : Some\n";
		String message2 = "Hi.hx:12: characters 15-33 : Some\n\n";
		
		List<ICompilerError> parseErrors = errorsParser.parseErrors(message1 + message2, "some.hxml");
		Assert.assertEquals(2, parseErrors.size());
	}	
}
