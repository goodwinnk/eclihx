lexer grammar InternalHXml;
@header {
package eclihx.hxml.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
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

// $ANTLR src "../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g" 848
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g" 850
RULE_INT : ('0'..'9')+;

// $ANTLR src "../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g" 852
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g" 854
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g" 856
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g" 858
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g" 860
RULE_ANY_OTHER : .;


