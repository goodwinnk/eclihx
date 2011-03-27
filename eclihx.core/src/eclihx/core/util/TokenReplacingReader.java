package eclihx.core.util;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;

/**
 * Class for replace number of tokens while reading from the some source.
 * 
 * From http://tutorials.jenkov.com/java-howto/replace-strings-in-streams-arrays-files.html article with minor changes
 */
public final class TokenReplacingReader extends Reader {

	protected Map<String, String> replacementPairs = new HashMap<String, String>();

	protected PushbackReader pushbackReader = null;
	protected StringBuilder tokenNameBuffer = new StringBuilder();
	protected String tokenValue = null;
	protected int tokenValueIndex = 0;

	/**
	 * Constructor with source of reading and number of replacements.
	 * 
	 * @param source The source of reading.
	 * @param replacementPairs A map with replacement strings.
	 */
	public TokenReplacingReader(Reader source, Map<String, String> replacementPairs) {
		this.pushbackReader = new PushbackReader(source, 2);
		this.replacementPairs = replacementPairs;
	}

	@Override
	public int read(CharBuffer target) throws IOException {
		throw new RuntimeException("Operation Not Supported");
	}

	@Override
	public int read() throws IOException {

		// We are in token currently - so return a char from the stored token
		// replacement
		if (tokenValue != null) {
			if (tokenValueIndex < tokenValue.length()) {
				return tokenValue.charAt(tokenValueIndex++);
			}

			if (tokenValueIndex == tokenValue.length()) {
				tokenValue = null;
				tokenValueIndex = 0;
			}
		}

		// Read chars one by one and wait for the token begin prefix ${
		int data = pushbackReader.read();
		if (data != '$') {
			return data;
		}

		data = pushbackReader.read();
		if (data != '{') {
			pushbackReader.unread(data);
			return '$';
		}

		// From this point we have found a new token prefix and store the whole
		// token into buffer
		tokenNameBuffer.delete(0, tokenNameBuffer.length());
		data = pushbackReader.read();
		while (data != '}' && data != -1) {
			tokenNameBuffer.append((char) data);
			data = pushbackReader.read();
		}

		if (data == '}') {
			tokenValue = replacementPairs.get(tokenNameBuffer.toString());

			// There's no replacement - use original value as replacement value
			if (tokenValue == null) {
				tokenValue = "${" + tokenNameBuffer.toString() + "}";
			}
		} else {
			// We have found end of input before end of the token
			Assert.isTrue(data == -1);
			tokenValue = "${" + tokenNameBuffer.toString();
		}

		return this.tokenValue.charAt(tokenValueIndex++);

	}

	@Override
	public int read(char cbuf[]) throws IOException {
		return read(cbuf, 0, cbuf.length);
	}

	@Override
	public int read(char cbuf[], int off, int len) throws IOException {
		int charsRead = 0;
		for (int i = 0; i < len; i++) {
			charsRead = i;
			int nextChar = read();
			if (nextChar == -1) {
				break;
			}
			cbuf[off + i] = (char) nextChar;
		}
		return charsRead;
	}

	@Override
	public void close() throws IOException {
		this.pushbackReader.close();
	}

	@Override
	public boolean ready() throws IOException {
		return this.pushbackReader.ready();
	}

	@Override
	public long skip(long n) throws IOException {
		throw new RuntimeException("Operation Not Supported");
	}

	@Override
	public boolean markSupported() {
		return false;
	}

	@Override
	public void mark(int readAheadLimit) throws IOException {
		throw new RuntimeException("Operation Not Supported");
	}

	@Override
	public void reset() throws IOException {
		throw new RuntimeException("Operation Not Supported");
	}
}
