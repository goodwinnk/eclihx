package eclihx.tests.core.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import eclihx.core.util.TokenReplacingReader;


public class TokenReplacingReaderTest {
	
	@Test
	public void shouldReplaceSimpleToken() throws IOException {
		StringReader reader = new StringReader("Hello, ${template}!");
		
		HashMap<String , String> replacements = new HashMap<String, String>();
		replacements.put("template", "World");
		
		TokenReplacingReader tokenReplacingReader = 
				new TokenReplacingReader(reader, replacements);
		
		StringBuilder builder = new StringBuilder();
		for (int data = tokenReplacingReader.read(); data != -1; data = tokenReplacingReader.read()) {
			builder.append((char)data);
		}
		
		tokenReplacingReader.close();
		reader.close();
		
		Assert.assertEquals("Hello, World!", builder.toString());
	}
	
	@Test
	public void shouldWorkForEmptyReader() throws IOException {
		StringReader reader = new StringReader("");
		TokenReplacingReader tokenReplacingReader = 
				new TokenReplacingReader(reader, new HashMap<String, String>());
		
		Assert.assertEquals(-1, tokenReplacingReader.read());
		
		tokenReplacingReader.close();
		reader.close();
	}
	
	@Test
	public void shouldGiveTokenIfNotFound() throws IOException {
		StringReader reader = new StringReader("Hello, ${template}!");
		
		TokenReplacingReader tokenReplacingReader = 
				new TokenReplacingReader(reader, new HashMap<String, String>());
		
		StringBuilder builder = new StringBuilder();
		for (int data = tokenReplacingReader.read(); data != -1; data = tokenReplacingReader.read()) {
			builder.append((char)data);
		}
		
		tokenReplacingReader.close();
		reader.close();
		
		Assert.assertEquals("Hello, ${template}!", builder.toString());
	}
	
	@Test
	public void shouldWorkIfTemplateIsNotFinished() throws IOException {
		StringReader reader = new StringReader("Hello, ${templ");
		
		TokenReplacingReader tokenReplacingReader = 
				new TokenReplacingReader(reader, new HashMap<String, String>());
		
		StringBuilder builder = new StringBuilder();
		for (int data = tokenReplacingReader.read(); data != -1; data = tokenReplacingReader.read()) {
			builder.append((char)data);
		}
		
		tokenReplacingReader.close();
		reader.close();
		
		Assert.assertEquals("Hello, ${templ", builder.toString());
	}
}
