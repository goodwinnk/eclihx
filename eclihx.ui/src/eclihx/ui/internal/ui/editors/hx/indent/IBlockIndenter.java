package eclihx.ui.internal.ui.editors.hx.indent;

/**
 * Interface for storing indentation properties and abstract it from
 * indenter itself.
 */
public interface IBlockIndenter {
	
	/**
	 * A single indentation string.
	 * 
	 * @return A single indentation string.
	 */
	String getSingleBlockIndent();
	
	/**
	 * Is open brace should be placed on the next line.
	 * 
	 * @return <code>true</code> if open brace should be placed on the next line.
	 */
	boolean isBraceOnNextLine();
}
