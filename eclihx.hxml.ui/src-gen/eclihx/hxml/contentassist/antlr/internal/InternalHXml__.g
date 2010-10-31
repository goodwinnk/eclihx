lexer grammar InternalHXml;
@header {
package eclihx.hxml.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.Lexer;
}

T11 : '-cp' ;
T12 : '-js' ;
T13 : '-swf' ;
T14 : '-swf9' ;
T15 : '-neko' ;
T16 : '-php' ;
T17 : '-cpp' ;
T18 : '-xml' ;
T19 : '-main' ;
T20 : '-lib' ;
T21 : '-D' ;
T22 : '-v' ;
T23 : '-debug' ;

// $ANTLR src "../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g" 1299
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g" 1301
RULE_INT : ('0'..'9')+;

// $ANTLR src "../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g" 1303
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g" 1305
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g" 1307
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g" 1309
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g" 1311
RULE_ANY_OTHER : .;


