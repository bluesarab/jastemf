// $ANTLR ${project.version} ${buildNumber}

	package org.jastemf.spec.ast.resource.ast.mopp;


import org.antlr.runtime3_2_0.*;

public class AstLexer extends Lexer {
    public static final int ML_COMMENT=6;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int IDENT=4;
    public static final int WHITESPACE=7;
    public static final int COMMENT=5;
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int LINEBREAK=8;

    	public java.util.List<org.antlr.runtime3_2_0.RecognitionException> lexerExceptions  = new java.util.ArrayList<org.antlr.runtime3_2_0.RecognitionException>();
    	public java.util.List<java.lang.Integer> lexerExceptionsPosition = new java.util.ArrayList<java.lang.Integer>();
    	
    	public void reportError(org.antlr.runtime3_2_0.RecognitionException e) {
    		lexerExceptions.add(e);
    		lexerExceptionsPosition.add(((org.antlr.runtime3_2_0.ANTLRStringStream) input).index());
    	}


    // delegates
    // delegators

    public AstLexer() {;} 
    public AstLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public AstLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Ast.g"; }

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:16:6: ( ':' )
            // Ast.g:16:8: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:17:7: ( '::=' )
            // Ast.g:17:9: '::='
            {
            match("::="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:18:7: ( ';' )
            // Ast.g:18:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:19:7: ( '*' )
            // Ast.g:19:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:20:7: ( '/' )
            // Ast.g:20:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:21:7: ( '<' )
            // Ast.g:21:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:22:7: ( '>' )
            // Ast.g:22:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:23:7: ( '[' )
            // Ast.g:23:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:24:7: ( ']' )
            // Ast.g:24:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:25:7: ( 'abstract' )
            // Ast.g:25:9: 'abstract'
            {
            match("abstract"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:1826:8: ( '//' (~ ( '\\n' | '\\r' | '\\uffff' ) )* )
            // Ast.g:1827:2: '//' (~ ( '\\n' | '\\r' | '\\uffff' ) )*
            {
            match("//"); 

            // Ast.g:1827:6: (~ ( '\\n' | '\\r' | '\\uffff' ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\u0000' && LA1_0<='\t')||(LA1_0>='\u000B' && LA1_0<='\f')||(LA1_0>='\u000E' && LA1_0<='\uFFFE')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Ast.g:1827:7: ~ ( '\\n' | '\\r' | '\\uffff' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "ML_COMMENT"
    public final void mML_COMMENT() throws RecognitionException {
        try {
            int _type = ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:1830:11: ( '/*' ( . )* '*/' )
            // Ast.g:1831:2: '/*' ( . )* '*/'
            {
            match("/*"); 

            // Ast.g:1831:6: ( . )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='*') ) {
                    int LA2_1 = input.LA(2);

                    if ( (LA2_1=='/') ) {
                        alt2=2;
                    }
                    else if ( ((LA2_1>='\u0000' && LA2_1<='.')||(LA2_1>='0' && LA2_1<='\uFFFF')) ) {
                        alt2=1;
                    }


                }
                else if ( ((LA2_0>='\u0000' && LA2_0<=')')||(LA2_0>='+' && LA2_0<='\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Ast.g:1831:6: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match("*/"); 

             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ML_COMMENT"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:1833:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )* )
            // Ast.g:1834:2: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // Ast.g:1834:25: ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Ast.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENT"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:1835:11: ( ( ' ' | '\\t' | '\\f' ) )
            // Ast.g:1836:2: ( ' ' | '\\t' | '\\f' )
            {
            if ( input.LA(1)=='\t'||input.LA(1)=='\f'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "LINEBREAK"
    public final void mLINEBREAK() throws RecognitionException {
        try {
            int _type = LINEBREAK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:1839:10: ( ( '\\r\\n' | '\\r' | '\\n' ) )
            // Ast.g:1840:2: ( '\\r\\n' | '\\r' | '\\n' )
            {
            // Ast.g:1840:2: ( '\\r\\n' | '\\r' | '\\n' )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\r') ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1=='\n') ) {
                    alt4=1;
                }
                else {
                    alt4=2;}
            }
            else if ( (LA4_0=='\n') ) {
                alt4=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // Ast.g:1840:3: '\\r\\n'
                    {
                    match("\r\n"); 


                    }
                    break;
                case 2 :
                    // Ast.g:1840:10: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 3 :
                    // Ast.g:1840:15: '\\n'
                    {
                    match('\n'); 

                    }
                    break;

            }

             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINEBREAK"

    public void mTokens() throws RecognitionException {
        // Ast.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | COMMENT | ML_COMMENT | IDENT | WHITESPACE | LINEBREAK )
        int alt5=15;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // Ast.g:1:10: T__9
                {
                mT__9(); 

                }
                break;
            case 2 :
                // Ast.g:1:15: T__10
                {
                mT__10(); 

                }
                break;
            case 3 :
                // Ast.g:1:21: T__11
                {
                mT__11(); 

                }
                break;
            case 4 :
                // Ast.g:1:27: T__12
                {
                mT__12(); 

                }
                break;
            case 5 :
                // Ast.g:1:33: T__13
                {
                mT__13(); 

                }
                break;
            case 6 :
                // Ast.g:1:39: T__14
                {
                mT__14(); 

                }
                break;
            case 7 :
                // Ast.g:1:45: T__15
                {
                mT__15(); 

                }
                break;
            case 8 :
                // Ast.g:1:51: T__16
                {
                mT__16(); 

                }
                break;
            case 9 :
                // Ast.g:1:57: T__17
                {
                mT__17(); 

                }
                break;
            case 10 :
                // Ast.g:1:63: T__18
                {
                mT__18(); 

                }
                break;
            case 11 :
                // Ast.g:1:69: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 12 :
                // Ast.g:1:77: ML_COMMENT
                {
                mML_COMMENT(); 

                }
                break;
            case 13 :
                // Ast.g:1:88: IDENT
                {
                mIDENT(); 

                }
                break;
            case 14 :
                // Ast.g:1:94: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;
            case 15 :
                // Ast.g:1:105: LINEBREAK
                {
                mLINEBREAK(); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\1\uffff\1\16\2\uffff\1\21\4\uffff\1\12\10\uffff\6\12\1\31\1\uffff";
    static final String DFA5_eofS =
        "\32\uffff";
    static final String DFA5_minS =
        "\1\11\1\72\2\uffff\1\52\4\uffff\1\142\10\uffff\1\163\1\164\1\162"+
        "\1\141\1\143\1\164\1\60\1\uffff";
    static final String DFA5_maxS =
        "\1\172\1\72\2\uffff\1\57\4\uffff\1\142\10\uffff\1\163\1\164\1\162"+
        "\1\141\1\143\1\164\1\172\1\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\3\1\4\1\uffff\1\6\1\7\1\10\1\11\1\uffff\1\15\1\16\1"+
        "\17\1\2\1\1\1\13\1\14\1\5\7\uffff\1\12";
    static final String DFA5_specialS =
        "\32\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\13\1\14\1\uffff\1\13\1\14\22\uffff\1\13\11\uffff\1\3\4\uffff"+
            "\1\4\12\uffff\1\1\1\2\1\5\1\uffff\1\6\2\uffff\32\12\1\7\1\uffff"+
            "\1\10\1\uffff\1\12\1\uffff\1\11\31\12",
            "\1\15",
            "",
            "",
            "\1\20\4\uffff\1\17",
            "",
            "",
            "",
            "",
            "\1\22",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\23",
            "\1\24",
            "\1\25",
            "\1\26",
            "\1\27",
            "\1\30",
            "\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32\12",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | COMMENT | ML_COMMENT | IDENT | WHITESPACE | LINEBREAK );";
        }
    }
 

}