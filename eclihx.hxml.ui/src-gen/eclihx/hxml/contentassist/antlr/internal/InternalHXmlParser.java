package eclihx.hxml.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import eclihx.hxml.services.HXmlGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalHXmlParser extends AbstractInternalContentAssistParser {
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
    public String getGrammarFileName() { return "../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g"; }


     
     	private HXmlGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(HXmlGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start entryRuleModel
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:60:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:60:16: ( ruleModel EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:61:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel60);
            ruleModel();
            _fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel67); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleModel


    // $ANTLR start ruleModel
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:68:1: ruleModel : ( ( rule__Model__ElementsAssignment )* ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:72:2: ( ( ( rule__Model__ElementsAssignment )* ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:73:1: ( ( rule__Model__ElementsAssignment )* )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:73:1: ( ( rule__Model__ElementsAssignment )* )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:74:1: ( rule__Model__ElementsAssignment )*
            {
             before(grammarAccess.getModelAccess().getElementsAssignment()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:75:1: ( rule__Model__ElementsAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=11 && LA1_0<=23)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:75:2: rule__Model__ElementsAssignment
            	    {
            	    pushFollow(FOLLOW_rule__Model__ElementsAssignment_in_ruleModel94);
            	    rule__Model__ElementsAssignment();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getElementsAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleModel


    // $ANTLR start entryRuleClasspath
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:87:1: entryRuleClasspath : ruleClasspath EOF ;
    public final void entryRuleClasspath() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:87:20: ( ruleClasspath EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:88:1: ruleClasspath EOF
            {
             before(grammarAccess.getClasspathRule()); 
            pushFollow(FOLLOW_ruleClasspath_in_entryRuleClasspath121);
            ruleClasspath();
            _fsp--;

             after(grammarAccess.getClasspathRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClasspath128); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleClasspath


    // $ANTLR start ruleClasspath
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:95:1: ruleClasspath : ( ( rule__Classpath__Group__0 ) ) ;
    public final void ruleClasspath() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:99:2: ( ( ( rule__Classpath__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:100:1: ( ( rule__Classpath__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:100:1: ( ( rule__Classpath__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:101:1: ( rule__Classpath__Group__0 )
            {
             before(grammarAccess.getClasspathAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:102:1: ( rule__Classpath__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:102:2: rule__Classpath__Group__0
            {
            pushFollow(FOLLOW_rule__Classpath__Group__0_in_ruleClasspath155);
            rule__Classpath__Group__0();
            _fsp--;


            }

             after(grammarAccess.getClasspathAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleClasspath


    // $ANTLR start entryRuleJavascript
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:114:1: entryRuleJavascript : ruleJavascript EOF ;
    public final void entryRuleJavascript() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:114:21: ( ruleJavascript EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:115:1: ruleJavascript EOF
            {
             before(grammarAccess.getJavascriptRule()); 
            pushFollow(FOLLOW_ruleJavascript_in_entryRuleJavascript181);
            ruleJavascript();
            _fsp--;

             after(grammarAccess.getJavascriptRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJavascript188); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleJavascript


    // $ANTLR start ruleJavascript
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:122:1: ruleJavascript : ( ( rule__Javascript__Group__0 ) ) ;
    public final void ruleJavascript() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:126:2: ( ( ( rule__Javascript__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:127:1: ( ( rule__Javascript__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:127:1: ( ( rule__Javascript__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:128:1: ( rule__Javascript__Group__0 )
            {
             before(grammarAccess.getJavascriptAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:129:1: ( rule__Javascript__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:129:2: rule__Javascript__Group__0
            {
            pushFollow(FOLLOW_rule__Javascript__Group__0_in_ruleJavascript215);
            rule__Javascript__Group__0();
            _fsp--;


            }

             after(grammarAccess.getJavascriptAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleJavascript


    // $ANTLR start entryRuleSwf
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:141:1: entryRuleSwf : ruleSwf EOF ;
    public final void entryRuleSwf() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:141:14: ( ruleSwf EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:142:1: ruleSwf EOF
            {
             before(grammarAccess.getSwfRule()); 
            pushFollow(FOLLOW_ruleSwf_in_entryRuleSwf241);
            ruleSwf();
            _fsp--;

             after(grammarAccess.getSwfRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwf248); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleSwf


    // $ANTLR start ruleSwf
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:149:1: ruleSwf : ( ( rule__Swf__Group__0 ) ) ;
    public final void ruleSwf() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:153:2: ( ( ( rule__Swf__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:154:1: ( ( rule__Swf__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:154:1: ( ( rule__Swf__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:155:1: ( rule__Swf__Group__0 )
            {
             before(grammarAccess.getSwfAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:156:1: ( rule__Swf__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:156:2: rule__Swf__Group__0
            {
            pushFollow(FOLLOW_rule__Swf__Group__0_in_ruleSwf275);
            rule__Swf__Group__0();
            _fsp--;


            }

             after(grammarAccess.getSwfAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleSwf


    // $ANTLR start entryRuleSwf9
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:168:1: entryRuleSwf9 : ruleSwf9 EOF ;
    public final void entryRuleSwf9() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:168:15: ( ruleSwf9 EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:169:1: ruleSwf9 EOF
            {
             before(grammarAccess.getSwf9Rule()); 
            pushFollow(FOLLOW_ruleSwf9_in_entryRuleSwf9301);
            ruleSwf9();
            _fsp--;

             after(grammarAccess.getSwf9Rule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwf9308); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleSwf9


    // $ANTLR start ruleSwf9
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:176:1: ruleSwf9 : ( ( rule__Swf9__Group__0 ) ) ;
    public final void ruleSwf9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:180:2: ( ( ( rule__Swf9__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:181:1: ( ( rule__Swf9__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:181:1: ( ( rule__Swf9__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:182:1: ( rule__Swf9__Group__0 )
            {
             before(grammarAccess.getSwf9Access().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:183:1: ( rule__Swf9__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:183:2: rule__Swf9__Group__0
            {
            pushFollow(FOLLOW_rule__Swf9__Group__0_in_ruleSwf9335);
            rule__Swf9__Group__0();
            _fsp--;


            }

             after(grammarAccess.getSwf9Access().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleSwf9


    // $ANTLR start entryRuleNeko
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:195:1: entryRuleNeko : ruleNeko EOF ;
    public final void entryRuleNeko() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:195:15: ( ruleNeko EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:196:1: ruleNeko EOF
            {
             before(grammarAccess.getNekoRule()); 
            pushFollow(FOLLOW_ruleNeko_in_entryRuleNeko361);
            ruleNeko();
            _fsp--;

             after(grammarAccess.getNekoRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNeko368); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleNeko


    // $ANTLR start ruleNeko
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:203:1: ruleNeko : ( ( rule__Neko__Group__0 ) ) ;
    public final void ruleNeko() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:207:2: ( ( ( rule__Neko__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:208:1: ( ( rule__Neko__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:208:1: ( ( rule__Neko__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:209:1: ( rule__Neko__Group__0 )
            {
             before(grammarAccess.getNekoAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:210:1: ( rule__Neko__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:210:2: rule__Neko__Group__0
            {
            pushFollow(FOLLOW_rule__Neko__Group__0_in_ruleNeko395);
            rule__Neko__Group__0();
            _fsp--;


            }

             after(grammarAccess.getNekoAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleNeko


    // $ANTLR start entryRulePhp
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:222:1: entryRulePhp : rulePhp EOF ;
    public final void entryRulePhp() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:222:14: ( rulePhp EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:223:1: rulePhp EOF
            {
             before(grammarAccess.getPhpRule()); 
            pushFollow(FOLLOW_rulePhp_in_entryRulePhp421);
            rulePhp();
            _fsp--;

             after(grammarAccess.getPhpRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePhp428); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRulePhp


    // $ANTLR start rulePhp
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:230:1: rulePhp : ( ( rule__Php__Group__0 ) ) ;
    public final void rulePhp() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:234:2: ( ( ( rule__Php__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:235:1: ( ( rule__Php__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:235:1: ( ( rule__Php__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:236:1: ( rule__Php__Group__0 )
            {
             before(grammarAccess.getPhpAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:237:1: ( rule__Php__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:237:2: rule__Php__Group__0
            {
            pushFollow(FOLLOW_rule__Php__Group__0_in_rulePhp455);
            rule__Php__Group__0();
            _fsp--;


            }

             after(grammarAccess.getPhpAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rulePhp


    // $ANTLR start entryRuleCpp
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:249:1: entryRuleCpp : ruleCpp EOF ;
    public final void entryRuleCpp() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:249:14: ( ruleCpp EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:250:1: ruleCpp EOF
            {
             before(grammarAccess.getCppRule()); 
            pushFollow(FOLLOW_ruleCpp_in_entryRuleCpp481);
            ruleCpp();
            _fsp--;

             after(grammarAccess.getCppRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCpp488); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleCpp


    // $ANTLR start ruleCpp
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:257:1: ruleCpp : ( ( rule__Cpp__Group__0 ) ) ;
    public final void ruleCpp() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:261:2: ( ( ( rule__Cpp__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:262:1: ( ( rule__Cpp__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:262:1: ( ( rule__Cpp__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:263:1: ( rule__Cpp__Group__0 )
            {
             before(grammarAccess.getCppAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:264:1: ( rule__Cpp__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:264:2: rule__Cpp__Group__0
            {
            pushFollow(FOLLOW_rule__Cpp__Group__0_in_ruleCpp515);
            rule__Cpp__Group__0();
            _fsp--;


            }

             after(grammarAccess.getCppAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleCpp


    // $ANTLR start entryRuleXml
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:276:1: entryRuleXml : ruleXml EOF ;
    public final void entryRuleXml() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:276:14: ( ruleXml EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:277:1: ruleXml EOF
            {
             before(grammarAccess.getXmlRule()); 
            pushFollow(FOLLOW_ruleXml_in_entryRuleXml541);
            ruleXml();
            _fsp--;

             after(grammarAccess.getXmlRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleXml548); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleXml


    // $ANTLR start ruleXml
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:284:1: ruleXml : ( ( rule__Xml__Group__0 ) ) ;
    public final void ruleXml() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:288:2: ( ( ( rule__Xml__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:289:1: ( ( rule__Xml__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:289:1: ( ( rule__Xml__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:290:1: ( rule__Xml__Group__0 )
            {
             before(grammarAccess.getXmlAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:291:1: ( rule__Xml__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:291:2: rule__Xml__Group__0
            {
            pushFollow(FOLLOW_rule__Xml__Group__0_in_ruleXml575);
            rule__Xml__Group__0();
            _fsp--;


            }

             after(grammarAccess.getXmlAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleXml


    // $ANTLR start entryRuleMain
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:303:1: entryRuleMain : ruleMain EOF ;
    public final void entryRuleMain() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:303:15: ( ruleMain EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:304:1: ruleMain EOF
            {
             before(grammarAccess.getMainRule()); 
            pushFollow(FOLLOW_ruleMain_in_entryRuleMain601);
            ruleMain();
            _fsp--;

             after(grammarAccess.getMainRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMain608); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleMain


    // $ANTLR start ruleMain
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:311:1: ruleMain : ( ( rule__Main__Group__0 ) ) ;
    public final void ruleMain() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:315:2: ( ( ( rule__Main__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:316:1: ( ( rule__Main__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:316:1: ( ( rule__Main__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:317:1: ( rule__Main__Group__0 )
            {
             before(grammarAccess.getMainAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:318:1: ( rule__Main__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:318:2: rule__Main__Group__0
            {
            pushFollow(FOLLOW_rule__Main__Group__0_in_ruleMain635);
            rule__Main__Group__0();
            _fsp--;


            }

             after(grammarAccess.getMainAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleMain


    // $ANTLR start entryRuleLib
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:330:1: entryRuleLib : ruleLib EOF ;
    public final void entryRuleLib() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:330:14: ( ruleLib EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:331:1: ruleLib EOF
            {
             before(grammarAccess.getLibRule()); 
            pushFollow(FOLLOW_ruleLib_in_entryRuleLib661);
            ruleLib();
            _fsp--;

             after(grammarAccess.getLibRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLib668); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleLib


    // $ANTLR start ruleLib
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:338:1: ruleLib : ( ( rule__Lib__Group__0 ) ) ;
    public final void ruleLib() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:342:2: ( ( ( rule__Lib__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:343:1: ( ( rule__Lib__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:343:1: ( ( rule__Lib__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:344:1: ( rule__Lib__Group__0 )
            {
             before(grammarAccess.getLibAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:345:1: ( rule__Lib__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:345:2: rule__Lib__Group__0
            {
            pushFollow(FOLLOW_rule__Lib__Group__0_in_ruleLib695);
            rule__Lib__Group__0();
            _fsp--;


            }

             after(grammarAccess.getLibAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleLib


    // $ANTLR start entryRuleFlag
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:357:1: entryRuleFlag : ruleFlag EOF ;
    public final void entryRuleFlag() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:357:15: ( ruleFlag EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:358:1: ruleFlag EOF
            {
             before(grammarAccess.getFlagRule()); 
            pushFollow(FOLLOW_ruleFlag_in_entryRuleFlag721);
            ruleFlag();
            _fsp--;

             after(grammarAccess.getFlagRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFlag728); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleFlag


    // $ANTLR start ruleFlag
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:365:1: ruleFlag : ( ( rule__Flag__Group__0 ) ) ;
    public final void ruleFlag() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:369:2: ( ( ( rule__Flag__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:370:1: ( ( rule__Flag__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:370:1: ( ( rule__Flag__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:371:1: ( rule__Flag__Group__0 )
            {
             before(grammarAccess.getFlagAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:372:1: ( rule__Flag__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:372:2: rule__Flag__Group__0
            {
            pushFollow(FOLLOW_rule__Flag__Group__0_in_ruleFlag755);
            rule__Flag__Group__0();
            _fsp--;


            }

             after(grammarAccess.getFlagAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleFlag


    // $ANTLR start entryRuleVerbose
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:384:1: entryRuleVerbose : ruleVerbose EOF ;
    public final void entryRuleVerbose() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:384:18: ( ruleVerbose EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:385:1: ruleVerbose EOF
            {
             before(grammarAccess.getVerboseRule()); 
            pushFollow(FOLLOW_ruleVerbose_in_entryRuleVerbose781);
            ruleVerbose();
            _fsp--;

             after(grammarAccess.getVerboseRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVerbose788); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleVerbose


    // $ANTLR start ruleVerbose
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:392:1: ruleVerbose : ( ( rule__Verbose__Group__0 ) ) ;
    public final void ruleVerbose() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:396:2: ( ( ( rule__Verbose__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:397:1: ( ( rule__Verbose__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:397:1: ( ( rule__Verbose__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:398:1: ( rule__Verbose__Group__0 )
            {
             before(grammarAccess.getVerboseAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:399:1: ( rule__Verbose__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:399:2: rule__Verbose__Group__0
            {
            pushFollow(FOLLOW_rule__Verbose__Group__0_in_ruleVerbose815);
            rule__Verbose__Group__0();
            _fsp--;


            }

             after(grammarAccess.getVerboseAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleVerbose


    // $ANTLR start entryRuleDebug
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:411:1: entryRuleDebug : ruleDebug EOF ;
    public final void entryRuleDebug() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:411:16: ( ruleDebug EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:412:1: ruleDebug EOF
            {
             before(grammarAccess.getDebugRule()); 
            pushFollow(FOLLOW_ruleDebug_in_entryRuleDebug841);
            ruleDebug();
            _fsp--;

             after(grammarAccess.getDebugRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDebug848); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleDebug


    // $ANTLR start ruleDebug
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:419:1: ruleDebug : ( ( rule__Debug__Group__0 ) ) ;
    public final void ruleDebug() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:423:2: ( ( ( rule__Debug__Group__0 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:424:1: ( ( rule__Debug__Group__0 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:424:1: ( ( rule__Debug__Group__0 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:425:1: ( rule__Debug__Group__0 )
            {
             before(grammarAccess.getDebugAccess().getGroup()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:426:1: ( rule__Debug__Group__0 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:426:2: rule__Debug__Group__0
            {
            pushFollow(FOLLOW_rule__Debug__Group__0_in_ruleDebug875);
            rule__Debug__Group__0();
            _fsp--;


            }

             after(grammarAccess.getDebugAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleDebug


    // $ANTLR start entryRuleType
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:438:1: entryRuleType : ruleType EOF ;
    public final void entryRuleType() throws RecognitionException {
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:438:15: ( ruleType EOF )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:439:1: ruleType EOF
            {
             before(grammarAccess.getTypeRule()); 
            pushFollow(FOLLOW_ruleType_in_entryRuleType901);
            ruleType();
            _fsp--;

             after(grammarAccess.getTypeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleType908); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end entryRuleType


    // $ANTLR start ruleType
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:446:1: ruleType : ( ( rule__Type__Alternatives ) ) ;
    public final void ruleType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:450:2: ( ( ( rule__Type__Alternatives ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:451:1: ( ( rule__Type__Alternatives ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:451:1: ( ( rule__Type__Alternatives ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:452:1: ( rule__Type__Alternatives )
            {
             before(grammarAccess.getTypeAccess().getAlternatives()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:453:1: ( rule__Type__Alternatives )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:453:2: rule__Type__Alternatives
            {
            pushFollow(FOLLOW_rule__Type__Alternatives_in_ruleType935);
            rule__Type__Alternatives();
            _fsp--;


            }

             after(grammarAccess.getTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end ruleType


    // $ANTLR start rule__Type__Alternatives
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:465:1: rule__Type__Alternatives : ( ( ruleClasspath ) | ( ruleJavascript ) | ( ruleSwf ) | ( ruleSwf9 ) | ( ruleNeko ) | ( rulePhp ) | ( ruleCpp ) | ( ruleXml ) | ( ruleMain ) | ( ruleLib ) | ( ruleFlag ) | ( ruleVerbose ) | ( ruleDebug ) );
    public final void rule__Type__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:469:1: ( ( ruleClasspath ) | ( ruleJavascript ) | ( ruleSwf ) | ( ruleSwf9 ) | ( ruleNeko ) | ( rulePhp ) | ( ruleCpp ) | ( ruleXml ) | ( ruleMain ) | ( ruleLib ) | ( ruleFlag ) | ( ruleVerbose ) | ( ruleDebug ) )
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
                    new NoViableAltException("465:1: rule__Type__Alternatives : ( ( ruleClasspath ) | ( ruleJavascript ) | ( ruleSwf ) | ( ruleSwf9 ) | ( ruleNeko ) | ( rulePhp ) | ( ruleCpp ) | ( ruleXml ) | ( ruleMain ) | ( ruleLib ) | ( ruleFlag ) | ( ruleVerbose ) | ( ruleDebug ) );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:470:1: ( ruleClasspath )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:470:1: ( ruleClasspath )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:471:1: ruleClasspath
                    {
                     before(grammarAccess.getTypeAccess().getClasspathParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleClasspath_in_rule__Type__Alternatives971);
                    ruleClasspath();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getClasspathParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:476:6: ( ruleJavascript )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:476:6: ( ruleJavascript )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:477:1: ruleJavascript
                    {
                     before(grammarAccess.getTypeAccess().getJavascriptParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleJavascript_in_rule__Type__Alternatives988);
                    ruleJavascript();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getJavascriptParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:482:6: ( ruleSwf )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:482:6: ( ruleSwf )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:483:1: ruleSwf
                    {
                     before(grammarAccess.getTypeAccess().getSwfParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleSwf_in_rule__Type__Alternatives1005);
                    ruleSwf();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getSwfParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:488:6: ( ruleSwf9 )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:488:6: ( ruleSwf9 )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:489:1: ruleSwf9
                    {
                     before(grammarAccess.getTypeAccess().getSwf9ParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleSwf9_in_rule__Type__Alternatives1022);
                    ruleSwf9();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getSwf9ParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:494:6: ( ruleNeko )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:494:6: ( ruleNeko )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:495:1: ruleNeko
                    {
                     before(grammarAccess.getTypeAccess().getNekoParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleNeko_in_rule__Type__Alternatives1039);
                    ruleNeko();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getNekoParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:500:6: ( rulePhp )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:500:6: ( rulePhp )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:501:1: rulePhp
                    {
                     before(grammarAccess.getTypeAccess().getPhpParserRuleCall_5()); 
                    pushFollow(FOLLOW_rulePhp_in_rule__Type__Alternatives1056);
                    rulePhp();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getPhpParserRuleCall_5()); 

                    }


                    }
                    break;
                case 7 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:506:6: ( ruleCpp )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:506:6: ( ruleCpp )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:507:1: ruleCpp
                    {
                     before(grammarAccess.getTypeAccess().getCppParserRuleCall_6()); 
                    pushFollow(FOLLOW_ruleCpp_in_rule__Type__Alternatives1073);
                    ruleCpp();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getCppParserRuleCall_6()); 

                    }


                    }
                    break;
                case 8 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:512:6: ( ruleXml )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:512:6: ( ruleXml )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:513:1: ruleXml
                    {
                     before(grammarAccess.getTypeAccess().getXmlParserRuleCall_7()); 
                    pushFollow(FOLLOW_ruleXml_in_rule__Type__Alternatives1090);
                    ruleXml();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getXmlParserRuleCall_7()); 

                    }


                    }
                    break;
                case 9 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:518:6: ( ruleMain )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:518:6: ( ruleMain )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:519:1: ruleMain
                    {
                     before(grammarAccess.getTypeAccess().getMainParserRuleCall_8()); 
                    pushFollow(FOLLOW_ruleMain_in_rule__Type__Alternatives1107);
                    ruleMain();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getMainParserRuleCall_8()); 

                    }


                    }
                    break;
                case 10 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:524:6: ( ruleLib )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:524:6: ( ruleLib )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:525:1: ruleLib
                    {
                     before(grammarAccess.getTypeAccess().getLibParserRuleCall_9()); 
                    pushFollow(FOLLOW_ruleLib_in_rule__Type__Alternatives1124);
                    ruleLib();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getLibParserRuleCall_9()); 

                    }


                    }
                    break;
                case 11 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:530:6: ( ruleFlag )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:530:6: ( ruleFlag )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:531:1: ruleFlag
                    {
                     before(grammarAccess.getTypeAccess().getFlagParserRuleCall_10()); 
                    pushFollow(FOLLOW_ruleFlag_in_rule__Type__Alternatives1141);
                    ruleFlag();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getFlagParserRuleCall_10()); 

                    }


                    }
                    break;
                case 12 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:536:6: ( ruleVerbose )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:536:6: ( ruleVerbose )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:537:1: ruleVerbose
                    {
                     before(grammarAccess.getTypeAccess().getVerboseParserRuleCall_11()); 
                    pushFollow(FOLLOW_ruleVerbose_in_rule__Type__Alternatives1158);
                    ruleVerbose();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getVerboseParserRuleCall_11()); 

                    }


                    }
                    break;
                case 13 :
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:542:6: ( ruleDebug )
                    {
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:542:6: ( ruleDebug )
                    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:543:1: ruleDebug
                    {
                     before(grammarAccess.getTypeAccess().getDebugParserRuleCall_12()); 
                    pushFollow(FOLLOW_ruleDebug_in_rule__Type__Alternatives1175);
                    ruleDebug();
                    _fsp--;

                     after(grammarAccess.getTypeAccess().getDebugParserRuleCall_12()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Type__Alternatives


    // $ANTLR start rule__Classpath__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:555:1: rule__Classpath__Group__0 : ( '-cp' ) rule__Classpath__Group__1 ;
    public final void rule__Classpath__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:559:1: ( ( '-cp' ) rule__Classpath__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:560:1: ( '-cp' ) rule__Classpath__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:560:1: ( '-cp' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:561:1: '-cp'
            {
             before(grammarAccess.getClasspathAccess().getCpKeyword_0()); 
            match(input,11,FOLLOW_11_in_rule__Classpath__Group__01210); 
             after(grammarAccess.getClasspathAccess().getCpKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Classpath__Group__1_in_rule__Classpath__Group__01220);
            rule__Classpath__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Classpath__Group__0


    // $ANTLR start rule__Classpath__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:575:1: rule__Classpath__Group__1 : ( ( rule__Classpath__ValueAssignment_1 ) ) ;
    public final void rule__Classpath__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:579:1: ( ( ( rule__Classpath__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:580:1: ( ( rule__Classpath__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:580:1: ( ( rule__Classpath__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:581:1: ( rule__Classpath__ValueAssignment_1 )
            {
             before(grammarAccess.getClasspathAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:582:1: ( rule__Classpath__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:582:2: rule__Classpath__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Classpath__ValueAssignment_1_in_rule__Classpath__Group__11248);
            rule__Classpath__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getClasspathAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Classpath__Group__1


    // $ANTLR start rule__Javascript__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:596:1: rule__Javascript__Group__0 : ( '-js' ) rule__Javascript__Group__1 ;
    public final void rule__Javascript__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:600:1: ( ( '-js' ) rule__Javascript__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:601:1: ( '-js' ) rule__Javascript__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:601:1: ( '-js' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:602:1: '-js'
            {
             before(grammarAccess.getJavascriptAccess().getJsKeyword_0()); 
            match(input,12,FOLLOW_12_in_rule__Javascript__Group__01287); 
             after(grammarAccess.getJavascriptAccess().getJsKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Javascript__Group__1_in_rule__Javascript__Group__01297);
            rule__Javascript__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Javascript__Group__0


    // $ANTLR start rule__Javascript__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:616:1: rule__Javascript__Group__1 : ( ( rule__Javascript__ValueAssignment_1 ) ) ;
    public final void rule__Javascript__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:620:1: ( ( ( rule__Javascript__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:621:1: ( ( rule__Javascript__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:621:1: ( ( rule__Javascript__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:622:1: ( rule__Javascript__ValueAssignment_1 )
            {
             before(grammarAccess.getJavascriptAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:623:1: ( rule__Javascript__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:623:2: rule__Javascript__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Javascript__ValueAssignment_1_in_rule__Javascript__Group__11325);
            rule__Javascript__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getJavascriptAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Javascript__Group__1


    // $ANTLR start rule__Swf__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:637:1: rule__Swf__Group__0 : ( '-swf' ) rule__Swf__Group__1 ;
    public final void rule__Swf__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:641:1: ( ( '-swf' ) rule__Swf__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:642:1: ( '-swf' ) rule__Swf__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:642:1: ( '-swf' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:643:1: '-swf'
            {
             before(grammarAccess.getSwfAccess().getSwfKeyword_0()); 
            match(input,13,FOLLOW_13_in_rule__Swf__Group__01364); 
             after(grammarAccess.getSwfAccess().getSwfKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Swf__Group__1_in_rule__Swf__Group__01374);
            rule__Swf__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Swf__Group__0


    // $ANTLR start rule__Swf__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:657:1: rule__Swf__Group__1 : ( ( rule__Swf__ValueAssignment_1 ) ) ;
    public final void rule__Swf__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:661:1: ( ( ( rule__Swf__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:662:1: ( ( rule__Swf__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:662:1: ( ( rule__Swf__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:663:1: ( rule__Swf__ValueAssignment_1 )
            {
             before(grammarAccess.getSwfAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:664:1: ( rule__Swf__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:664:2: rule__Swf__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Swf__ValueAssignment_1_in_rule__Swf__Group__11402);
            rule__Swf__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getSwfAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Swf__Group__1


    // $ANTLR start rule__Swf9__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:678:1: rule__Swf9__Group__0 : ( '-swf9' ) rule__Swf9__Group__1 ;
    public final void rule__Swf9__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:682:1: ( ( '-swf9' ) rule__Swf9__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:683:1: ( '-swf9' ) rule__Swf9__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:683:1: ( '-swf9' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:684:1: '-swf9'
            {
             before(grammarAccess.getSwf9Access().getSwf9Keyword_0()); 
            match(input,14,FOLLOW_14_in_rule__Swf9__Group__01441); 
             after(grammarAccess.getSwf9Access().getSwf9Keyword_0()); 

            }

            pushFollow(FOLLOW_rule__Swf9__Group__1_in_rule__Swf9__Group__01451);
            rule__Swf9__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Swf9__Group__0


    // $ANTLR start rule__Swf9__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:698:1: rule__Swf9__Group__1 : ( ( rule__Swf9__ValueAssignment_1 ) ) ;
    public final void rule__Swf9__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:702:1: ( ( ( rule__Swf9__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:703:1: ( ( rule__Swf9__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:703:1: ( ( rule__Swf9__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:704:1: ( rule__Swf9__ValueAssignment_1 )
            {
             before(grammarAccess.getSwf9Access().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:705:1: ( rule__Swf9__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:705:2: rule__Swf9__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Swf9__ValueAssignment_1_in_rule__Swf9__Group__11479);
            rule__Swf9__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getSwf9Access().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Swf9__Group__1


    // $ANTLR start rule__Neko__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:719:1: rule__Neko__Group__0 : ( '-neko' ) rule__Neko__Group__1 ;
    public final void rule__Neko__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:723:1: ( ( '-neko' ) rule__Neko__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:724:1: ( '-neko' ) rule__Neko__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:724:1: ( '-neko' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:725:1: '-neko'
            {
             before(grammarAccess.getNekoAccess().getNekoKeyword_0()); 
            match(input,15,FOLLOW_15_in_rule__Neko__Group__01518); 
             after(grammarAccess.getNekoAccess().getNekoKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Neko__Group__1_in_rule__Neko__Group__01528);
            rule__Neko__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Neko__Group__0


    // $ANTLR start rule__Neko__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:739:1: rule__Neko__Group__1 : ( ( rule__Neko__ValueAssignment_1 ) ) ;
    public final void rule__Neko__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:743:1: ( ( ( rule__Neko__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:744:1: ( ( rule__Neko__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:744:1: ( ( rule__Neko__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:745:1: ( rule__Neko__ValueAssignment_1 )
            {
             before(grammarAccess.getNekoAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:746:1: ( rule__Neko__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:746:2: rule__Neko__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Neko__ValueAssignment_1_in_rule__Neko__Group__11556);
            rule__Neko__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getNekoAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Neko__Group__1


    // $ANTLR start rule__Php__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:760:1: rule__Php__Group__0 : ( '-php' ) rule__Php__Group__1 ;
    public final void rule__Php__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:764:1: ( ( '-php' ) rule__Php__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:765:1: ( '-php' ) rule__Php__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:765:1: ( '-php' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:766:1: '-php'
            {
             before(grammarAccess.getPhpAccess().getPhpKeyword_0()); 
            match(input,16,FOLLOW_16_in_rule__Php__Group__01595); 
             after(grammarAccess.getPhpAccess().getPhpKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Php__Group__1_in_rule__Php__Group__01605);
            rule__Php__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Php__Group__0


    // $ANTLR start rule__Php__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:780:1: rule__Php__Group__1 : ( ( rule__Php__ValueAssignment_1 ) ) ;
    public final void rule__Php__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:784:1: ( ( ( rule__Php__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:785:1: ( ( rule__Php__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:785:1: ( ( rule__Php__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:786:1: ( rule__Php__ValueAssignment_1 )
            {
             before(grammarAccess.getPhpAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:787:1: ( rule__Php__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:787:2: rule__Php__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Php__ValueAssignment_1_in_rule__Php__Group__11633);
            rule__Php__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getPhpAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Php__Group__1


    // $ANTLR start rule__Cpp__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:801:1: rule__Cpp__Group__0 : ( '-cpp' ) rule__Cpp__Group__1 ;
    public final void rule__Cpp__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:805:1: ( ( '-cpp' ) rule__Cpp__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:806:1: ( '-cpp' ) rule__Cpp__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:806:1: ( '-cpp' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:807:1: '-cpp'
            {
             before(grammarAccess.getCppAccess().getCppKeyword_0()); 
            match(input,17,FOLLOW_17_in_rule__Cpp__Group__01672); 
             after(grammarAccess.getCppAccess().getCppKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Cpp__Group__1_in_rule__Cpp__Group__01682);
            rule__Cpp__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Cpp__Group__0


    // $ANTLR start rule__Cpp__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:821:1: rule__Cpp__Group__1 : ( ( rule__Cpp__ValueAssignment_1 ) ) ;
    public final void rule__Cpp__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:825:1: ( ( ( rule__Cpp__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:826:1: ( ( rule__Cpp__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:826:1: ( ( rule__Cpp__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:827:1: ( rule__Cpp__ValueAssignment_1 )
            {
             before(grammarAccess.getCppAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:828:1: ( rule__Cpp__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:828:2: rule__Cpp__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Cpp__ValueAssignment_1_in_rule__Cpp__Group__11710);
            rule__Cpp__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getCppAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Cpp__Group__1


    // $ANTLR start rule__Xml__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:842:1: rule__Xml__Group__0 : ( '-xml' ) rule__Xml__Group__1 ;
    public final void rule__Xml__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:846:1: ( ( '-xml' ) rule__Xml__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:847:1: ( '-xml' ) rule__Xml__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:847:1: ( '-xml' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:848:1: '-xml'
            {
             before(grammarAccess.getXmlAccess().getXmlKeyword_0()); 
            match(input,18,FOLLOW_18_in_rule__Xml__Group__01749); 
             after(grammarAccess.getXmlAccess().getXmlKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Xml__Group__1_in_rule__Xml__Group__01759);
            rule__Xml__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Xml__Group__0


    // $ANTLR start rule__Xml__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:862:1: rule__Xml__Group__1 : ( ( rule__Xml__ValueAssignment_1 ) ) ;
    public final void rule__Xml__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:866:1: ( ( ( rule__Xml__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:867:1: ( ( rule__Xml__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:867:1: ( ( rule__Xml__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:868:1: ( rule__Xml__ValueAssignment_1 )
            {
             before(grammarAccess.getXmlAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:869:1: ( rule__Xml__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:869:2: rule__Xml__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Xml__ValueAssignment_1_in_rule__Xml__Group__11787);
            rule__Xml__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getXmlAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Xml__Group__1


    // $ANTLR start rule__Main__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:883:1: rule__Main__Group__0 : ( '-main' ) rule__Main__Group__1 ;
    public final void rule__Main__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:887:1: ( ( '-main' ) rule__Main__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:888:1: ( '-main' ) rule__Main__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:888:1: ( '-main' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:889:1: '-main'
            {
             before(grammarAccess.getMainAccess().getMainKeyword_0()); 
            match(input,19,FOLLOW_19_in_rule__Main__Group__01826); 
             after(grammarAccess.getMainAccess().getMainKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Main__Group__1_in_rule__Main__Group__01836);
            rule__Main__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Main__Group__0


    // $ANTLR start rule__Main__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:903:1: rule__Main__Group__1 : ( ( rule__Main__ValueAssignment_1 ) ) ;
    public final void rule__Main__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:907:1: ( ( ( rule__Main__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:908:1: ( ( rule__Main__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:908:1: ( ( rule__Main__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:909:1: ( rule__Main__ValueAssignment_1 )
            {
             before(grammarAccess.getMainAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:910:1: ( rule__Main__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:910:2: rule__Main__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Main__ValueAssignment_1_in_rule__Main__Group__11864);
            rule__Main__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getMainAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Main__Group__1


    // $ANTLR start rule__Lib__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:924:1: rule__Lib__Group__0 : ( '-lib' ) rule__Lib__Group__1 ;
    public final void rule__Lib__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:928:1: ( ( '-lib' ) rule__Lib__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:929:1: ( '-lib' ) rule__Lib__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:929:1: ( '-lib' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:930:1: '-lib'
            {
             before(grammarAccess.getLibAccess().getLibKeyword_0()); 
            match(input,20,FOLLOW_20_in_rule__Lib__Group__01903); 
             after(grammarAccess.getLibAccess().getLibKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Lib__Group__1_in_rule__Lib__Group__01913);
            rule__Lib__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Lib__Group__0


    // $ANTLR start rule__Lib__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:944:1: rule__Lib__Group__1 : ( ( rule__Lib__ValueAssignment_1 ) ) ;
    public final void rule__Lib__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:948:1: ( ( ( rule__Lib__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:949:1: ( ( rule__Lib__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:949:1: ( ( rule__Lib__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:950:1: ( rule__Lib__ValueAssignment_1 )
            {
             before(grammarAccess.getLibAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:951:1: ( rule__Lib__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:951:2: rule__Lib__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Lib__ValueAssignment_1_in_rule__Lib__Group__11941);
            rule__Lib__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getLibAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Lib__Group__1


    // $ANTLR start rule__Flag__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:965:1: rule__Flag__Group__0 : ( '-D' ) rule__Flag__Group__1 ;
    public final void rule__Flag__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:969:1: ( ( '-D' ) rule__Flag__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:970:1: ( '-D' ) rule__Flag__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:970:1: ( '-D' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:971:1: '-D'
            {
             before(grammarAccess.getFlagAccess().getDKeyword_0()); 
            match(input,21,FOLLOW_21_in_rule__Flag__Group__01980); 
             after(grammarAccess.getFlagAccess().getDKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Flag__Group__1_in_rule__Flag__Group__01990);
            rule__Flag__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Flag__Group__0


    // $ANTLR start rule__Flag__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:985:1: rule__Flag__Group__1 : ( ( rule__Flag__ValueAssignment_1 ) ) ;
    public final void rule__Flag__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:989:1: ( ( ( rule__Flag__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:990:1: ( ( rule__Flag__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:990:1: ( ( rule__Flag__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:991:1: ( rule__Flag__ValueAssignment_1 )
            {
             before(grammarAccess.getFlagAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:992:1: ( rule__Flag__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:992:2: rule__Flag__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Flag__ValueAssignment_1_in_rule__Flag__Group__12018);
            rule__Flag__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getFlagAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Flag__Group__1


    // $ANTLR start rule__Verbose__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1006:1: rule__Verbose__Group__0 : ( '-v' ) rule__Verbose__Group__1 ;
    public final void rule__Verbose__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1010:1: ( ( '-v' ) rule__Verbose__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1011:1: ( '-v' ) rule__Verbose__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1011:1: ( '-v' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1012:1: '-v'
            {
             before(grammarAccess.getVerboseAccess().getVKeyword_0()); 
            match(input,22,FOLLOW_22_in_rule__Verbose__Group__02057); 
             after(grammarAccess.getVerboseAccess().getVKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Verbose__Group__1_in_rule__Verbose__Group__02067);
            rule__Verbose__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Verbose__Group__0


    // $ANTLR start rule__Verbose__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1026:1: rule__Verbose__Group__1 : ( ( rule__Verbose__ValueAssignment_1 ) ) ;
    public final void rule__Verbose__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1030:1: ( ( ( rule__Verbose__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1031:1: ( ( rule__Verbose__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1031:1: ( ( rule__Verbose__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1032:1: ( rule__Verbose__ValueAssignment_1 )
            {
             before(grammarAccess.getVerboseAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1033:1: ( rule__Verbose__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1033:2: rule__Verbose__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Verbose__ValueAssignment_1_in_rule__Verbose__Group__12095);
            rule__Verbose__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getVerboseAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Verbose__Group__1


    // $ANTLR start rule__Debug__Group__0
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1047:1: rule__Debug__Group__0 : ( '-debug' ) rule__Debug__Group__1 ;
    public final void rule__Debug__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1051:1: ( ( '-debug' ) rule__Debug__Group__1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1052:1: ( '-debug' ) rule__Debug__Group__1
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1052:1: ( '-debug' )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1053:1: '-debug'
            {
             before(grammarAccess.getDebugAccess().getDebugKeyword_0()); 
            match(input,23,FOLLOW_23_in_rule__Debug__Group__02134); 
             after(grammarAccess.getDebugAccess().getDebugKeyword_0()); 

            }

            pushFollow(FOLLOW_rule__Debug__Group__1_in_rule__Debug__Group__02144);
            rule__Debug__Group__1();
            _fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Debug__Group__0


    // $ANTLR start rule__Debug__Group__1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1067:1: rule__Debug__Group__1 : ( ( rule__Debug__ValueAssignment_1 ) ) ;
    public final void rule__Debug__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1071:1: ( ( ( rule__Debug__ValueAssignment_1 ) ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1072:1: ( ( rule__Debug__ValueAssignment_1 ) )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1072:1: ( ( rule__Debug__ValueAssignment_1 ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1073:1: ( rule__Debug__ValueAssignment_1 )
            {
             before(grammarAccess.getDebugAccess().getValueAssignment_1()); 
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1074:1: ( rule__Debug__ValueAssignment_1 )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1074:2: rule__Debug__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Debug__ValueAssignment_1_in_rule__Debug__Group__12172);
            rule__Debug__ValueAssignment_1();
            _fsp--;


            }

             after(grammarAccess.getDebugAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Debug__Group__1


    // $ANTLR start rule__Model__ElementsAssignment
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1088:1: rule__Model__ElementsAssignment : ( ruleType ) ;
    public final void rule__Model__ElementsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1092:1: ( ( ruleType ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1093:1: ( ruleType )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1093:1: ( ruleType )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1094:1: ruleType
            {
             before(grammarAccess.getModelAccess().getElementsTypeParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleType_in_rule__Model__ElementsAssignment2210);
            ruleType();
            _fsp--;

             after(grammarAccess.getModelAccess().getElementsTypeParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Model__ElementsAssignment


    // $ANTLR start rule__Classpath__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1103:1: rule__Classpath__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Classpath__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1107:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1108:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1108:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1109:1: RULE_STRING
            {
             before(grammarAccess.getClasspathAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Classpath__ValueAssignment_12241); 
             after(grammarAccess.getClasspathAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Classpath__ValueAssignment_1


    // $ANTLR start rule__Javascript__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1118:1: rule__Javascript__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Javascript__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1122:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1123:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1123:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1124:1: RULE_STRING
            {
             before(grammarAccess.getJavascriptAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Javascript__ValueAssignment_12272); 
             after(grammarAccess.getJavascriptAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Javascript__ValueAssignment_1


    // $ANTLR start rule__Swf__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1133:1: rule__Swf__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Swf__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1137:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1138:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1138:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1139:1: RULE_STRING
            {
             before(grammarAccess.getSwfAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Swf__ValueAssignment_12303); 
             after(grammarAccess.getSwfAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Swf__ValueAssignment_1


    // $ANTLR start rule__Swf9__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1148:1: rule__Swf9__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Swf9__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1152:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1153:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1153:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1154:1: RULE_STRING
            {
             before(grammarAccess.getSwf9Access().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Swf9__ValueAssignment_12334); 
             after(grammarAccess.getSwf9Access().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Swf9__ValueAssignment_1


    // $ANTLR start rule__Neko__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1163:1: rule__Neko__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Neko__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1167:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1168:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1168:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1169:1: RULE_STRING
            {
             before(grammarAccess.getNekoAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Neko__ValueAssignment_12365); 
             after(grammarAccess.getNekoAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Neko__ValueAssignment_1


    // $ANTLR start rule__Php__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1178:1: rule__Php__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Php__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1182:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1183:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1183:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1184:1: RULE_STRING
            {
             before(grammarAccess.getPhpAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Php__ValueAssignment_12396); 
             after(grammarAccess.getPhpAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Php__ValueAssignment_1


    // $ANTLR start rule__Cpp__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1193:1: rule__Cpp__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Cpp__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1197:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1198:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1198:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1199:1: RULE_STRING
            {
             before(grammarAccess.getCppAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Cpp__ValueAssignment_12427); 
             after(grammarAccess.getCppAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Cpp__ValueAssignment_1


    // $ANTLR start rule__Xml__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1208:1: rule__Xml__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Xml__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1212:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1213:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1213:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1214:1: RULE_STRING
            {
             before(grammarAccess.getXmlAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Xml__ValueAssignment_12458); 
             after(grammarAccess.getXmlAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Xml__ValueAssignment_1


    // $ANTLR start rule__Main__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1223:1: rule__Main__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Main__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1227:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1228:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1228:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1229:1: RULE_STRING
            {
             before(grammarAccess.getMainAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Main__ValueAssignment_12489); 
             after(grammarAccess.getMainAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Main__ValueAssignment_1


    // $ANTLR start rule__Lib__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1238:1: rule__Lib__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Lib__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1242:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1243:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1243:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1244:1: RULE_STRING
            {
             before(grammarAccess.getLibAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Lib__ValueAssignment_12520); 
             after(grammarAccess.getLibAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Lib__ValueAssignment_1


    // $ANTLR start rule__Flag__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1253:1: rule__Flag__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Flag__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1257:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1258:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1258:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1259:1: RULE_STRING
            {
             before(grammarAccess.getFlagAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Flag__ValueAssignment_12551); 
             after(grammarAccess.getFlagAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Flag__ValueAssignment_1


    // $ANTLR start rule__Verbose__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1268:1: rule__Verbose__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Verbose__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1272:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1273:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1273:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1274:1: RULE_STRING
            {
             before(grammarAccess.getVerboseAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Verbose__ValueAssignment_12582); 
             after(grammarAccess.getVerboseAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Verbose__ValueAssignment_1


    // $ANTLR start rule__Debug__ValueAssignment_1
    // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1283:1: rule__Debug__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Debug__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1287:1: ( ( RULE_STRING ) )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1288:1: ( RULE_STRING )
            {
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1288:1: ( RULE_STRING )
            // ../eclihx.hxml.ui/src-gen/eclihx/hxml/contentassist/antlr/internal/InternalHXml.g:1289:1: RULE_STRING
            {
             before(grammarAccess.getDebugAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Debug__ValueAssignment_12613); 
             after(grammarAccess.getDebugAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end rule__Debug__ValueAssignment_1


 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel60 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Model__ElementsAssignment_in_ruleModel94 = new BitSet(new long[]{0x0000000000FFF802L});
    public static final BitSet FOLLOW_ruleClasspath_in_entryRuleClasspath121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClasspath128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Classpath__Group__0_in_ruleClasspath155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJavascript_in_entryRuleJavascript181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJavascript188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Javascript__Group__0_in_ruleJavascript215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwf_in_entryRuleSwf241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwf248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Swf__Group__0_in_ruleSwf275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwf9_in_entryRuleSwf9301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwf9308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Swf9__Group__0_in_ruleSwf9335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNeko_in_entryRuleNeko361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNeko368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Neko__Group__0_in_ruleNeko395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePhp_in_entryRulePhp421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePhp428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Php__Group__0_in_rulePhp455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCpp_in_entryRuleCpp481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCpp488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cpp__Group__0_in_ruleCpp515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXml_in_entryRuleXml541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleXml548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Xml__Group__0_in_ruleXml575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMain_in_entryRuleMain601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMain608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Main__Group__0_in_ruleMain635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLib_in_entryRuleLib661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLib668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lib__Group__0_in_ruleLib695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFlag_in_entryRuleFlag721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFlag728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Flag__Group__0_in_ruleFlag755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVerbose_in_entryRuleVerbose781 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVerbose788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Verbose__Group__0_in_ruleVerbose815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDebug_in_entryRuleDebug841 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDebug848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Debug__Group__0_in_ruleDebug875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType901 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Type__Alternatives_in_ruleType935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClasspath_in_rule__Type__Alternatives971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJavascript_in_rule__Type__Alternatives988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwf_in_rule__Type__Alternatives1005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwf9_in_rule__Type__Alternatives1022 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNeko_in_rule__Type__Alternatives1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePhp_in_rule__Type__Alternatives1056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCpp_in_rule__Type__Alternatives1073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleXml_in_rule__Type__Alternatives1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMain_in_rule__Type__Alternatives1107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLib_in_rule__Type__Alternatives1124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFlag_in_rule__Type__Alternatives1141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVerbose_in_rule__Type__Alternatives1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDebug_in_rule__Type__Alternatives1175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_rule__Classpath__Group__01210 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Classpath__Group__1_in_rule__Classpath__Group__01220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Classpath__ValueAssignment_1_in_rule__Classpath__Group__11248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Javascript__Group__01287 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Javascript__Group__1_in_rule__Javascript__Group__01297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Javascript__ValueAssignment_1_in_rule__Javascript__Group__11325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Swf__Group__01364 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Swf__Group__1_in_rule__Swf__Group__01374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Swf__ValueAssignment_1_in_rule__Swf__Group__11402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Swf9__Group__01441 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Swf9__Group__1_in_rule__Swf9__Group__01451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Swf9__ValueAssignment_1_in_rule__Swf9__Group__11479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Neko__Group__01518 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Neko__Group__1_in_rule__Neko__Group__01528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Neko__ValueAssignment_1_in_rule__Neko__Group__11556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Php__Group__01595 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Php__Group__1_in_rule__Php__Group__01605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Php__ValueAssignment_1_in_rule__Php__Group__11633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__Cpp__Group__01672 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Cpp__Group__1_in_rule__Cpp__Group__01682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Cpp__ValueAssignment_1_in_rule__Cpp__Group__11710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__Xml__Group__01749 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Xml__Group__1_in_rule__Xml__Group__01759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Xml__ValueAssignment_1_in_rule__Xml__Group__11787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Main__Group__01826 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Main__Group__1_in_rule__Main__Group__01836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Main__ValueAssignment_1_in_rule__Main__Group__11864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Lib__Group__01903 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Lib__Group__1_in_rule__Lib__Group__01913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Lib__ValueAssignment_1_in_rule__Lib__Group__11941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Flag__Group__01980 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Flag__Group__1_in_rule__Flag__Group__01990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Flag__ValueAssignment_1_in_rule__Flag__Group__12018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Verbose__Group__02057 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Verbose__Group__1_in_rule__Verbose__Group__02067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Verbose__ValueAssignment_1_in_rule__Verbose__Group__12095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__Debug__Group__02134 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Debug__Group__1_in_rule__Debug__Group__02144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Debug__ValueAssignment_1_in_rule__Debug__Group__12172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_rule__Model__ElementsAssignment2210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Classpath__ValueAssignment_12241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Javascript__ValueAssignment_12272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Swf__ValueAssignment_12303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Swf9__ValueAssignment_12334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Neko__ValueAssignment_12365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Php__ValueAssignment_12396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Cpp__ValueAssignment_12427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Xml__ValueAssignment_12458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Main__ValueAssignment_12489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Lib__ValueAssignment_12520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Flag__ValueAssignment_12551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Verbose__ValueAssignment_12582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Debug__ValueAssignment_12613 = new BitSet(new long[]{0x0000000000000002L});

}