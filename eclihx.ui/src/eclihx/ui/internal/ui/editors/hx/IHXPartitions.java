package eclihx.ui.internal.ui.editors.hx;

/**
 * Definition of haXe partitions.
 */
public interface IHXPartitions {

	/**
	 * The identifier of the single-line end comment partition content type.
	 */
	String HX_SINGLE_LINE_COMMENT = "__hx_singleline_comment"; //$NON-NLS-1$

	/**
	 * The identifier multi-line comment partition content type.
	 */
	String HX_MULTI_LINE_COMMENT = "__hx_multiline_comment"; //$NON-NLS-1$

	/**
	 * The identifier of the haXe Doc partition content type.
	 */
	String HX_DOC = "__hx_haxedoc"; //$NON-NLS-1$

	/**
	 * The identifier of the haXe string partition content type.
	 */
	String HX_STRING = "__hx_string"; //$NON-NLS-1$
	
	/**
	 * The identifier of the haXe regular expression partition content type.
	 */
	String HX_REGEXPR = "__hx_regexpr"; //$NON-NLS-1$

	/**
	 * The identifier of the haXe character partition content type.
	 */
	String HX_CHARACTER = "__hx_character";  //$NON-NLS-1$
	
	/**
	 * haXe preprocessor
	 */
	String HX_PREPROCESSOR = "__hx_preprocessor";
}
