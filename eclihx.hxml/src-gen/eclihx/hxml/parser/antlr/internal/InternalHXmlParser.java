package eclihx.hxml.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import eclihx.hxml.services.HXmlGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalHXmlParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'-cp'", "'-js'", "'-swf'", "'-swf9'", "'-neko'", "'-php'", "'-cpp'", "'-xml'", "'-main'", "'-lib'", "'-D'", "'-v'", "'-debug'"
    };
    public static final int RULE_ML_COMMENT=7;
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int EOF=-1;
    public static final int RULE_INT=6;
    public static final int RULE_STRING=4;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;

        public InternalHXmlParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g"; }


     
     	private HXmlGrammarAccess grammarAccess;
     	
        public InternalHXmlParser(TokenStream input, IAstFactory factory, HXmlGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("eclihx/hxml/parser/antlr/internal/InternalHXml.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Model";	
       	} 



    // $ANTLR start entryRuleModel
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:72:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:72:47: (iv_ruleModel= ruleModel EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:73:2: iv_ruleModel= ruleModel EOF
            {
             currentNode = createCompositeNode(grammarAccess.getModelRule(), currentNode); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel73);
            iv_ruleModel=ruleModel();
            _fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel83); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleModel


    // $ANTLR start ruleModel
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:80:1: ruleModel returns [EObject current=null] : (lv_elements_0= ruleType )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:85:6: ( (lv_elements_0= ruleType )* )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:86:1: (lv_elements_0= ruleType )*
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:86:1: (lv_elements_0= ruleType )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=11 && LA1_0<=23)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:89:6: lv_elements_0= ruleType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getModelAccess().getElementsTypeParserRuleCall_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleType_in_ruleModel141);
            	    lv_elements_0=ruleType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getModelRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "elements", lv_elements_0, "Type", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleModel


    // $ANTLR start entryRuleClasspath
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:114:1: entryRuleClasspath returns [EObject current=null] : iv_ruleClasspath= ruleClasspath EOF ;
    public final EObject entryRuleClasspath() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClasspath = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:114:51: (iv_ruleClasspath= ruleClasspath EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:115:2: iv_ruleClasspath= ruleClasspath EOF
            {
             currentNode = createCompositeNode(grammarAccess.getClasspathRule(), currentNode); 
            pushFollow(FOLLOW_ruleClasspath_in_entryRuleClasspath178);
            iv_ruleClasspath=ruleClasspath();
            _fsp--;

             current =iv_ruleClasspath; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClasspath188); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleClasspath


    // $ANTLR start ruleClasspath
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:122:1: ruleClasspath returns [EObject current=null] : ( '-cp' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleClasspath() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:127:6: ( ( '-cp' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:128:1: ( '-cp' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:128:1: ( '-cp' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:128:2: '-cp' (lv_value_1= RULE_STRING )
            {
            match(input,11,FOLLOW_11_in_ruleClasspath222); 

                    createLeafNode(grammarAccess.getClasspathAccess().getCpKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:132:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:134:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleClasspath244); 

            		createLeafNode(grammarAccess.getClasspathAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getClasspathRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleClasspath


    // $ANTLR start entryRuleJavascript
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:159:1: entryRuleJavascript returns [EObject current=null] : iv_ruleJavascript= ruleJavascript EOF ;
    public final EObject entryRuleJavascript() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJavascript = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:159:52: (iv_ruleJavascript= ruleJavascript EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:160:2: iv_ruleJavascript= ruleJavascript EOF
            {
             currentNode = createCompositeNode(grammarAccess.getJavascriptRule(), currentNode); 
            pushFollow(FOLLOW_ruleJavascript_in_entryRuleJavascript285);
            iv_ruleJavascript=ruleJavascript();
            _fsp--;

             current =iv_ruleJavascript; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJavascript295); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleJavascript


    // $ANTLR start ruleJavascript
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:167:1: ruleJavascript returns [EObject current=null] : ( '-js' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleJavascript() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:172:6: ( ( '-js' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:173:1: ( '-js' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:173:1: ( '-js' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:173:2: '-js' (lv_value_1= RULE_STRING )
            {
            match(input,12,FOLLOW_12_in_ruleJavascript329); 

                    createLeafNode(grammarAccess.getJavascriptAccess().getJsKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:177:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:179:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleJavascript351); 

            		createLeafNode(grammarAccess.getJavascriptAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getJavascriptRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleJavascript


    // $ANTLR start entryRuleSwf
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:204:1: entryRuleSwf returns [EObject current=null] : iv_ruleSwf= ruleSwf EOF ;
    public final EObject entryRuleSwf() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwf = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:204:45: (iv_ruleSwf= ruleSwf EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:205:2: iv_ruleSwf= ruleSwf EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSwfRule(), currentNode); 
            pushFollow(FOLLOW_ruleSwf_in_entryRuleSwf392);
            iv_ruleSwf=ruleSwf();
            _fsp--;

             current =iv_ruleSwf; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwf402); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleSwf


    // $ANTLR start ruleSwf
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:212:1: ruleSwf returns [EObject current=null] : ( '-swf' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleSwf() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:217:6: ( ( '-swf' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:218:1: ( '-swf' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:218:1: ( '-swf' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:218:2: '-swf' (lv_value_1= RULE_STRING )
            {
            match(input,13,FOLLOW_13_in_ruleSwf436); 

                    createLeafNode(grammarAccess.getSwfAccess().getSwfKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:222:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:224:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSwf458); 

            		createLeafNode(grammarAccess.getSwfAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getSwfRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleSwf


    // $ANTLR start entryRuleSwf9
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:249:1: entryRuleSwf9 returns [EObject current=null] : iv_ruleSwf9= ruleSwf9 EOF ;
    public final EObject entryRuleSwf9() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwf9 = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:249:46: (iv_ruleSwf9= ruleSwf9 EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:250:2: iv_ruleSwf9= ruleSwf9 EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSwf9Rule(), currentNode); 
            pushFollow(FOLLOW_ruleSwf9_in_entryRuleSwf9499);
            iv_ruleSwf9=ruleSwf9();
            _fsp--;

             current =iv_ruleSwf9; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwf9509); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleSwf9


    // $ANTLR start ruleSwf9
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:257:1: ruleSwf9 returns [EObject current=null] : ( '-swf9' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleSwf9() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:262:6: ( ( '-swf9' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:263:1: ( '-swf9' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:263:1: ( '-swf9' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:263:2: '-swf9' (lv_value_1= RULE_STRING )
            {
            match(input,14,FOLLOW_14_in_ruleSwf9543); 

                    createLeafNode(grammarAccess.getSwf9Access().getSwf9Keyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:267:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:269:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSwf9565); 

            		createLeafNode(grammarAccess.getSwf9Access().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getSwf9Rule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleSwf9


    // $ANTLR start entryRuleNeko
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:294:1: entryRuleNeko returns [EObject current=null] : iv_ruleNeko= ruleNeko EOF ;
    public final EObject entryRuleNeko() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNeko = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:294:46: (iv_ruleNeko= ruleNeko EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:295:2: iv_ruleNeko= ruleNeko EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNekoRule(), currentNode); 
            pushFollow(FOLLOW_ruleNeko_in_entryRuleNeko606);
            iv_ruleNeko=ruleNeko();
            _fsp--;

             current =iv_ruleNeko; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNeko616); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleNeko


    // $ANTLR start ruleNeko
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:302:1: ruleNeko returns [EObject current=null] : ( '-neko' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleNeko() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:307:6: ( ( '-neko' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:308:1: ( '-neko' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:308:1: ( '-neko' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:308:2: '-neko' (lv_value_1= RULE_STRING )
            {
            match(input,15,FOLLOW_15_in_ruleNeko650); 

                    createLeafNode(grammarAccess.getNekoAccess().getNekoKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:312:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:314:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleNeko672); 

            		createLeafNode(grammarAccess.getNekoAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNekoRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleNeko


    // $ANTLR start entryRulePhp
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:339:1: entryRulePhp returns [EObject current=null] : iv_rulePhp= rulePhp EOF ;
    public final EObject entryRulePhp() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePhp = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:339:45: (iv_rulePhp= rulePhp EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:340:2: iv_rulePhp= rulePhp EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPhpRule(), currentNode); 
            pushFollow(FOLLOW_rulePhp_in_entryRulePhp713);
            iv_rulePhp=rulePhp();
            _fsp--;

             current =iv_rulePhp; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePhp723); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePhp


    // $ANTLR start rulePhp
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:347:1: rulePhp returns [EObject current=null] : ( '-php' (lv_value_1= RULE_STRING ) ) ;
    public final EObject rulePhp() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:352:6: ( ( '-php' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:353:1: ( '-php' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:353:1: ( '-php' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:353:2: '-php' (lv_value_1= RULE_STRING )
            {
            match(input,16,FOLLOW_16_in_rulePhp757); 

                    createLeafNode(grammarAccess.getPhpAccess().getPhpKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:357:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:359:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePhp779); 

            		createLeafNode(grammarAccess.getPhpAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPhpRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePhp


    // $ANTLR start entryRuleCpp
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:384:1: entryRuleCpp returns [EObject current=null] : iv_ruleCpp= ruleCpp EOF ;
    public final EObject entryRuleCpp() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCpp = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:384:45: (iv_ruleCpp= ruleCpp EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:385:2: iv_ruleCpp= ruleCpp EOF
            {
             currentNode = createCompositeNode(grammarAccess.getCppRule(), currentNode); 
            pushFollow(FOLLOW_ruleCpp_in_entryRuleCpp820);
            iv_ruleCpp=ruleCpp();
            _fsp--;

             current =iv_ruleCpp; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCpp830); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleCpp


    // $ANTLR start ruleCpp
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:392:1: ruleCpp returns [EObject current=null] : ( '-cpp' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleCpp() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:397:6: ( ( '-cpp' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:398:1: ( '-cpp' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:398:1: ( '-cpp' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:398:2: '-cpp' (lv_value_1= RULE_STRING )
            {
            match(input,17,FOLLOW_17_in_ruleCpp864); 

                    createLeafNode(grammarAccess.getCppAccess().getCppKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:402:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:404:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleCpp886); 

            		createLeafNode(grammarAccess.getCppAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getCppRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleCpp


    // $ANTLR start entryRuleXml
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:429:1: entryRuleXml returns [EObject current=null] : iv_ruleXml= ruleXml EOF ;
    public final EObject entryRuleXml() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXml = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:429:45: (iv_ruleXml= ruleXml EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:430:2: iv_ruleXml= ruleXml EOF
            {
             currentNode = createCompositeNode(grammarAccess.getXmlRule(), currentNode); 
            pushFollow(FOLLOW_ruleXml_in_entryRuleXml927);
            iv_ruleXml=ruleXml();
            _fsp--;

             current =iv_ruleXml; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXml937); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleXml


    // $ANTLR start ruleXml
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:437:1: ruleXml returns [EObject current=null] : ( '-xml' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleXml() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:442:6: ( ( '-xml' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:443:1: ( '-xml' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:443:1: ( '-xml' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:443:2: '-xml' (lv_value_1= RULE_STRING )
            {
            match(input,18,FOLLOW_18_in_ruleXml971); 

                    createLeafNode(grammarAccess.getXmlAccess().getXmlKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:447:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:449:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleXml993); 

            		createLeafNode(grammarAccess.getXmlAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getXmlRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleXml


    // $ANTLR start entryRuleMain
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:474:1: entryRuleMain returns [EObject current=null] : iv_ruleMain= ruleMain EOF ;
    public final EObject entryRuleMain() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMain = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:474:46: (iv_ruleMain= ruleMain EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:475:2: iv_ruleMain= ruleMain EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMainRule(), currentNode); 
            pushFollow(FOLLOW_ruleMain_in_entryRuleMain1034);
            iv_ruleMain=ruleMain();
            _fsp--;

             current =iv_ruleMain; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMain1044); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleMain


    // $ANTLR start ruleMain
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:482:1: ruleMain returns [EObject current=null] : ( '-main' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleMain() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:487:6: ( ( '-main' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:488:1: ( '-main' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:488:1: ( '-main' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:488:2: '-main' (lv_value_1= RULE_STRING )
            {
            match(input,19,FOLLOW_19_in_ruleMain1078); 

                    createLeafNode(grammarAccess.getMainAccess().getMainKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:492:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:494:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleMain1100); 

            		createLeafNode(grammarAccess.getMainAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getMainRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleMain


    // $ANTLR start entryRuleLib
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:519:1: entryRuleLib returns [EObject current=null] : iv_ruleLib= ruleLib EOF ;
    public final EObject entryRuleLib() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLib = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:519:45: (iv_ruleLib= ruleLib EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:520:2: iv_ruleLib= ruleLib EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLibRule(), currentNode); 
            pushFollow(FOLLOW_ruleLib_in_entryRuleLib1141);
            iv_ruleLib=ruleLib();
            _fsp--;

             current =iv_ruleLib; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLib1151); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleLib


    // $ANTLR start ruleLib
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:527:1: ruleLib returns [EObject current=null] : ( '-lib' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleLib() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:532:6: ( ( '-lib' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:533:1: ( '-lib' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:533:1: ( '-lib' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:533:2: '-lib' (lv_value_1= RULE_STRING )
            {
            match(input,20,FOLLOW_20_in_ruleLib1185); 

                    createLeafNode(grammarAccess.getLibAccess().getLibKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:537:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:539:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLib1207); 

            		createLeafNode(grammarAccess.getLibAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getLibRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleLib


    // $ANTLR start entryRuleFlag
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:564:1: entryRuleFlag returns [EObject current=null] : iv_ruleFlag= ruleFlag EOF ;
    public final EObject entryRuleFlag() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFlag = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:564:46: (iv_ruleFlag= ruleFlag EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:565:2: iv_ruleFlag= ruleFlag EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFlagRule(), currentNode); 
            pushFollow(FOLLOW_ruleFlag_in_entryRuleFlag1248);
            iv_ruleFlag=ruleFlag();
            _fsp--;

             current =iv_ruleFlag; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFlag1258); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleFlag


    // $ANTLR start ruleFlag
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:572:1: ruleFlag returns [EObject current=null] : ( '-D' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleFlag() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:577:6: ( ( '-D' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:578:1: ( '-D' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:578:1: ( '-D' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:578:2: '-D' (lv_value_1= RULE_STRING )
            {
            match(input,21,FOLLOW_21_in_ruleFlag1292); 

                    createLeafNode(grammarAccess.getFlagAccess().getDKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:582:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:584:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFlag1314); 

            		createLeafNode(grammarAccess.getFlagAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFlagRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleFlag


    // $ANTLR start entryRuleVerbose
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:609:1: entryRuleVerbose returns [EObject current=null] : iv_ruleVerbose= ruleVerbose EOF ;
    public final EObject entryRuleVerbose() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVerbose = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:609:49: (iv_ruleVerbose= ruleVerbose EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:610:2: iv_ruleVerbose= ruleVerbose EOF
            {
             currentNode = createCompositeNode(grammarAccess.getVerboseRule(), currentNode); 
            pushFollow(FOLLOW_ruleVerbose_in_entryRuleVerbose1355);
            iv_ruleVerbose=ruleVerbose();
            _fsp--;

             current =iv_ruleVerbose; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVerbose1365); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleVerbose


    // $ANTLR start ruleVerbose
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:617:1: ruleVerbose returns [EObject current=null] : ( '-v' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleVerbose() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:622:6: ( ( '-v' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:623:1: ( '-v' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:623:1: ( '-v' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:623:2: '-v' (lv_value_1= RULE_STRING )
            {
            match(input,22,FOLLOW_22_in_ruleVerbose1399); 

                    createLeafNode(grammarAccess.getVerboseAccess().getVKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:627:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:629:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleVerbose1421); 

            		createLeafNode(grammarAccess.getVerboseAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getVerboseRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleVerbose


    // $ANTLR start entryRuleDebug
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:654:1: entryRuleDebug returns [EObject current=null] : iv_ruleDebug= ruleDebug EOF ;
    public final EObject entryRuleDebug() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDebug = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:654:47: (iv_ruleDebug= ruleDebug EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:655:2: iv_ruleDebug= ruleDebug EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDebugRule(), currentNode); 
            pushFollow(FOLLOW_ruleDebug_in_entryRuleDebug1462);
            iv_ruleDebug=ruleDebug();
            _fsp--;

             current =iv_ruleDebug; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDebug1472); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDebug


    // $ANTLR start ruleDebug
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:662:1: ruleDebug returns [EObject current=null] : ( '-debug' (lv_value_1= RULE_STRING ) ) ;
    public final EObject ruleDebug() throws RecognitionException {
        EObject current = null;

        Token lv_value_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:667:6: ( ( '-debug' (lv_value_1= RULE_STRING ) ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:668:1: ( '-debug' (lv_value_1= RULE_STRING ) )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:668:1: ( '-debug' (lv_value_1= RULE_STRING ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:668:2: '-debug' (lv_value_1= RULE_STRING )
            {
            match(input,23,FOLLOW_23_in_ruleDebug1506); 

                    createLeafNode(grammarAccess.getDebugAccess().getDebugKeyword_0(), null); 
                
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:672:1: (lv_value_1= RULE_STRING )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:674:6: lv_value_1= RULE_STRING
            {
            lv_value_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleDebug1528); 

            		createLeafNode(grammarAccess.getDebugAccess().getValueSTRINGTerminalRuleCall_1_0(), "value"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getDebugRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "value", lv_value_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDebug


    // $ANTLR start entryRuleType
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:699:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:699:46: (iv_ruleType= ruleType EOF )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:700:2: iv_ruleType= ruleType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleType_in_entryRuleType1569);
            iv_ruleType=ruleType();
            _fsp--;

             current =iv_ruleType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleType1579); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleType


    // $ANTLR start ruleType
    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:707:1: ruleType returns [EObject current=null] : (this_Classpath_0= ruleClasspath | this_Javascript_1= ruleJavascript | this_Swf_2= ruleSwf | this_Swf9_3= ruleSwf9 | this_Neko_4= ruleNeko | this_Php_5= rulePhp | this_Cpp_6= ruleCpp | this_Xml_7= ruleXml | this_Main_8= ruleMain | this_Lib_9= ruleLib | this_Flag_10= ruleFlag | this_Verbose_11= ruleVerbose | this_Debug_12= ruleDebug ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_Classpath_0 = null;

        EObject this_Javascript_1 = null;

        EObject this_Swf_2 = null;

        EObject this_Swf9_3 = null;

        EObject this_Neko_4 = null;

        EObject this_Php_5 = null;

        EObject this_Cpp_6 = null;

        EObject this_Xml_7 = null;

        EObject this_Main_8 = null;

        EObject this_Lib_9 = null;

        EObject this_Flag_10 = null;

        EObject this_Verbose_11 = null;

        EObject this_Debug_12 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:712:6: ( (this_Classpath_0= ruleClasspath | this_Javascript_1= ruleJavascript | this_Swf_2= ruleSwf | this_Swf9_3= ruleSwf9 | this_Neko_4= ruleNeko | this_Php_5= rulePhp | this_Cpp_6= ruleCpp | this_Xml_7= ruleXml | this_Main_8= ruleMain | this_Lib_9= ruleLib | this_Flag_10= ruleFlag | this_Verbose_11= ruleVerbose | this_Debug_12= ruleDebug ) )
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:713:1: (this_Classpath_0= ruleClasspath | this_Javascript_1= ruleJavascript | this_Swf_2= ruleSwf | this_Swf9_3= ruleSwf9 | this_Neko_4= ruleNeko | this_Php_5= rulePhp | this_Cpp_6= ruleCpp | this_Xml_7= ruleXml | this_Main_8= ruleMain | this_Lib_9= ruleLib | this_Flag_10= ruleFlag | this_Verbose_11= ruleVerbose | this_Debug_12= ruleDebug )
            {
            // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:713:1: (this_Classpath_0= ruleClasspath | this_Javascript_1= ruleJavascript | this_Swf_2= ruleSwf | this_Swf9_3= ruleSwf9 | this_Neko_4= ruleNeko | this_Php_5= rulePhp | this_Cpp_6= ruleCpp | this_Xml_7= ruleXml | this_Main_8= ruleMain | this_Lib_9= ruleLib | this_Flag_10= ruleFlag | this_Verbose_11= ruleVerbose | this_Debug_12= ruleDebug )
            int alt2=13;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt2=1;
                }
                break;
            case 12:
                {
                alt2=2;
                }
                break;
            case 13:
                {
                alt2=3;
                }
                break;
            case 14:
                {
                alt2=4;
                }
                break;
            case 15:
                {
                alt2=5;
                }
                break;
            case 16:
                {
                alt2=6;
                }
                break;
            case 17:
                {
                alt2=7;
                }
                break;
            case 18:
                {
                alt2=8;
                }
                break;
            case 19:
                {
                alt2=9;
                }
                break;
            case 20:
                {
                alt2=10;
                }
                break;
            case 21:
                {
                alt2=11;
                }
                break;
            case 22:
                {
                alt2=12;
                }
                break;
            case 23:
                {
                alt2=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("713:1: (this_Classpath_0= ruleClasspath | this_Javascript_1= ruleJavascript | this_Swf_2= ruleSwf | this_Swf9_3= ruleSwf9 | this_Neko_4= ruleNeko | this_Php_5= rulePhp | this_Cpp_6= ruleCpp | this_Xml_7= ruleXml | this_Main_8= ruleMain | this_Lib_9= ruleLib | this_Flag_10= ruleFlag | this_Verbose_11= ruleVerbose | this_Debug_12= ruleDebug )", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:714:5: this_Classpath_0= ruleClasspath
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getClasspathParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleClasspath_in_ruleType1626);
                    this_Classpath_0=ruleClasspath();
                    _fsp--;

                     
                            current = this_Classpath_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:724:5: this_Javascript_1= ruleJavascript
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getJavascriptParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleJavascript_in_ruleType1653);
                    this_Javascript_1=ruleJavascript();
                    _fsp--;

                     
                            current = this_Javascript_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 3 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:734:5: this_Swf_2= ruleSwf
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getSwfParserRuleCall_2(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleSwf_in_ruleType1680);
                    this_Swf_2=ruleSwf();
                    _fsp--;

                     
                            current = this_Swf_2; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 4 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:744:5: this_Swf9_3= ruleSwf9
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getSwf9ParserRuleCall_3(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleSwf9_in_ruleType1707);
                    this_Swf9_3=ruleSwf9();
                    _fsp--;

                     
                            current = this_Swf9_3; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 5 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:754:5: this_Neko_4= ruleNeko
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getNekoParserRuleCall_4(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleNeko_in_ruleType1734);
                    this_Neko_4=ruleNeko();
                    _fsp--;

                     
                            current = this_Neko_4; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 6 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:764:5: this_Php_5= rulePhp
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getPhpParserRuleCall_5(), currentNode); 
                        
                    pushFollow(FOLLOW_rulePhp_in_ruleType1761);
                    this_Php_5=rulePhp();
                    _fsp--;

                     
                            current = this_Php_5; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 7 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:774:5: this_Cpp_6= ruleCpp
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getCppParserRuleCall_6(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleCpp_in_ruleType1788);
                    this_Cpp_6=ruleCpp();
                    _fsp--;

                     
                            current = this_Cpp_6; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 8 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:784:5: this_Xml_7= ruleXml
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getXmlParserRuleCall_7(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleXml_in_ruleType1815);
                    this_Xml_7=ruleXml();
                    _fsp--;

                     
                            current = this_Xml_7; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 9 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:794:5: this_Main_8= ruleMain
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getMainParserRuleCall_8(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleMain_in_ruleType1842);
                    this_Main_8=ruleMain();
                    _fsp--;

                     
                            current = this_Main_8; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 10 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:804:5: this_Lib_9= ruleLib
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getLibParserRuleCall_9(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleLib_in_ruleType1869);
                    this_Lib_9=ruleLib();
                    _fsp--;

                     
                            current = this_Lib_9; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 11 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:814:5: this_Flag_10= ruleFlag
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getFlagParserRuleCall_10(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleFlag_in_ruleType1896);
                    this_Flag_10=ruleFlag();
                    _fsp--;

                     
                            current = this_Flag_10; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 12 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:824:5: this_Verbose_11= ruleVerbose
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getVerboseParserRuleCall_11(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleVerbose_in_ruleType1923);
                    this_Verbose_11=ruleVerbose();
                    _fsp--;

                     
                            current = this_Verbose_11; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 13 :
                    // ../eclihx.hxml/src-gen/eclihx/hxml/parser/antlr/internal/InternalHXml.g:834:5: this_Debug_12= ruleDebug
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getDebugParserRuleCall_12(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleDebug_in_ruleType1950);
                    this_Debug_12=ruleDebug();
                    _fsp--;

                     
                            current = this_Debug_12; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleType


 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel73 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_ruleModel141 = new BitSet(new long[]{0x0000000000FFF802L});
    public static final BitSet FOLLOW_ruleClasspath_in_entryRuleClasspath178 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClasspath188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleClasspath222 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleClasspath244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJavascript_in_entryRuleJavascript285 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJavascript295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleJavascript329 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleJavascript351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwf_in_entryRuleSwf392 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwf402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleSwf436 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSwf458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwf9_in_entryRuleSwf9499 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwf9509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleSwf9543 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSwf9565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNeko_in_entryRuleNeko606 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNeko616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleNeko650 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleNeko672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePhp_in_entryRulePhp713 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePhp723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rulePhp757 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePhp779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCpp_in_entryRuleCpp820 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCpp830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleCpp864 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleCpp886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXml_in_entryRuleXml927 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXml937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleXml971 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleXml993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMain_in_entryRuleMain1034 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMain1044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleMain1078 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleMain1100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLib_in_entryRuleLib1141 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLib1151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleLib1185 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLib1207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFlag_in_entryRuleFlag1248 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFlag1258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleFlag1292 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFlag1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVerbose_in_entryRuleVerbose1355 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVerbose1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleVerbose1399 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleVerbose1421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDebug_in_entryRuleDebug1462 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDebug1472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleDebug1506 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleDebug1528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType1569 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClasspath_in_ruleType1626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJavascript_in_ruleType1653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwf_in_ruleType1680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwf9_in_ruleType1707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNeko_in_ruleType1734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePhp_in_ruleType1761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCpp_in_ruleType1788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXml_in_ruleType1815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMain_in_ruleType1842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLib_in_ruleType1869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFlag_in_ruleType1896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVerbose_in_ruleType1923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDebug_in_ruleType1950 = new BitSet(new long[]{0x0000000000000002L});

}