package eclihx.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;

import eclihx.ui.internal.ui.EclihxPlugin;

/**
 * Preference IDs and defualt values for EclihX-UI plugin
 */
public final class PreferenceConstants {
	
//========= Haxe Editor Style Settings =========//
	public final static String HX_EDITOR_COMMENT_COLOR = "hx_editor_comment_color";
	public final static String HX_EDITOR_COMMENT_BOLD = "hx_editor_comment_bold";
	public final static String HX_EDITOR_COMMENT_ITALIC = "hx_editor_comment_italic";
	
	public final static String HX_EDITOR_MULTILINE_COMMENT_COLOR = "hx_editor_multiline_comment_color";
	public final static String HX_EDITOR_MULTILINE_COMMENT_BOLD = "hx_editor_multiline_comment_bold";
	public final static String HX_EDITOR_MULTILINE_COMMENT_ITALIC = "hx_editor_multiline_comment_italic";
	
	public final static String HX_EDITOR_STRING_COLOR = "hx_editor_string_color";
	public final static String HX_EDITOR_STRING_BOLD = "hx_editor_string_bold";
	public final static String HX_EDITOR_STRING_ITALIC = "hx_editor_string_italic";
	
	public final static String HX_EDITOR_BRACKET_COLOR = "hx_editor_bracket_bold";
	public final static String HX_EDITOR_BRACKET_BOLD = "hx_editor_bracket_bold";
	public final static String HX_EDITOR_BRACKET_ITALIC = "hx_editor_bracket_italic";
	
	public final static String HX_EDITOR_BRACE_COLOR = "hx_editor_brace_color";
	public final static String HX_EDITOR_BRACE_BOLD = "hx_editor_brace_color";
	public final static String HX_EDITOR_BRACE_ITALIC = "hx_editor_brace_italic";
	
	public final static String HX_EDITOR_NUMBER_COLOR = "hx_editor_number_color";
	public final static String HX_EDITOR_NUMBER_BOLD = "hx_editor_number_bold";
	public final static String HX_EDITOR_NUMBER_ITALIC = "hx_editor_number_bold";
	
	public final static String HX_EDITOR_DECLARE_KEYWORDS_COLOR = "hx_editor_declare_keywords_color";
	public final static String HX_EDITOR_DECLARE_KEYWORDS_BOLD = "hx_editor_declare_keywords_bold";
	public final static String HX_EDITOR_DECLARE_KEYWORDS_ITALIC = "hx_editor_declare_keywords_bold";
	
	public final static String HX_EDITOR_OTHER_KEYWORDS_COLOR = "hx_editor_other_keywords_color";
	public final static String HX_EDITOR_OTHER_KEYWORDS_BOLD = "hx_editor_other_keywords_bold";
	public final static String HX_EDITOR_OTHER_KEYWORDS_ITALIC = "hx_editor_other_keywords_bold";
	
	public final static String HX_EDITOR_TYPE_COLOR = "hx_editor_type_color";
	public final static String HX_EDITOR_TYPE_BOLD = "hx_editor_type_bold";
	public final static String HX_EDITOR_TYPE_ITALIC = "hx_editor_type_bold";

	public final static String HX_EDITOR_CONDITIONAL_COMPILATION_COLOR = "hx_editor_conditional_compilation_color";
	public final static String HX_EDITOR_CONDITIONAL_COMPILATION_BOLD = "hx_editor_conditional_compilation_bold";
	public final static String HX_EDITOR_CONDITIONAL_COMPILATION_ITALIC = "hx_editor_conditional_compilation_italic";

	public final static String HX_EDITOR_DEFAULT_COLOR = "hx_editor_default_color";
	public final static String HX_EDITOR_DEFAULT_BOLD = "hx_editor_default_bold";
	public final static String HX_EDITOR_DEFAULT_ITALIC = "hx_editor_default_italic";
	
//==============================================//
	
	/**
	 * Initialize given preference store with the default values.
	 * @param store the preference store to be initialized
	 */
	public static void initializeDefaultValues(IPreferenceStore store) {
		PreferenceConverter.setDefault(store, HX_EDITOR_COMMENT_COLOR, new RGB(128, 128, 128));
		store.setDefault(HX_EDITOR_COMMENT_BOLD, false);
		store.setDefault(HX_EDITOR_COMMENT_ITALIC, false);
		
		PreferenceConverter.setDefault(store, HX_EDITOR_MULTILINE_COMMENT_COLOR, new RGB(128, 128, 128));
		store.setDefault(HX_EDITOR_MULTILINE_COMMENT_BOLD, false);
		store.setDefault(HX_EDITOR_MULTILINE_COMMENT_ITALIC, false);
		
		PreferenceConverter.setDefault(store, HX_EDITOR_STRING_COLOR, new RGB(255, 0, 0));
		store.setDefault(HX_EDITOR_STRING_BOLD, false);
		store.setDefault(HX_EDITOR_STRING_ITALIC, false);
		
		PreferenceConverter.setDefault(store, HX_EDITOR_BRACKET_COLOR, new RGB(102, 204, 102));
		store.setDefault(HX_EDITOR_BRACKET_BOLD, false);
		store.setDefault(HX_EDITOR_BRACKET_ITALIC, false);
		
		PreferenceConverter.setDefault(store, HX_EDITOR_BRACE_COLOR, new RGB(102, 204, 102));
		store.setDefault(HX_EDITOR_BRACE_BOLD, false);
		store.setDefault(HX_EDITOR_BRACE_ITALIC, false);
		
		PreferenceConverter.setDefault(store, HX_EDITOR_NUMBER_COLOR, new RGB(204, 102, 204));
		store.setDefault(HX_EDITOR_NUMBER_BOLD, false);
		store.setDefault(HX_EDITOR_NUMBER_ITALIC, false);
		
		PreferenceConverter.setDefault(store, HX_EDITOR_DECLARE_KEYWORDS_COLOR, new RGB(0, 0, 0));
		store.setDefault(HX_EDITOR_DECLARE_KEYWORDS_BOLD, true);
		store.setDefault(HX_EDITOR_DECLARE_KEYWORDS_ITALIC, false);
		
		PreferenceConverter.setDefault(store, HX_EDITOR_OTHER_KEYWORDS_COLOR, new RGB(0, 0, 102));
		store.setDefault(HX_EDITOR_OTHER_KEYWORDS_BOLD, false);
		store.setDefault(HX_EDITOR_OTHER_KEYWORDS_ITALIC, false);
		
		PreferenceConverter.setDefault(store, HX_EDITOR_TYPE_COLOR, new RGB(0, 0, 102));
		store.setDefault(HX_EDITOR_TYPE_BOLD, false);
		store.setDefault(HX_EDITOR_TYPE_ITALIC, false);
		
		PreferenceConverter.setDefault(store, HX_EDITOR_CONDITIONAL_COMPILATION_COLOR, new RGB(128, 128, 128));
		store.setDefault(HX_EDITOR_CONDITIONAL_COMPILATION_BOLD, false);
		store.setDefault(HX_EDITOR_CONDITIONAL_COMPILATION_ITALIC, false);
		
		PreferenceConverter.setDefault(store, HX_EDITOR_DEFAULT_COLOR, new RGB(128, 128, 128));
		store.setDefault(HX_EDITOR_DEFAULT_BOLD, false);
		store.setDefault(HX_EDITOR_DEFAULT_ITALIC, false);
	}
	
	public static IPreferenceStore getPreferenceStore() {
		return EclihxPlugin.getDefault().getPreferenceStore();
	}
	
}
