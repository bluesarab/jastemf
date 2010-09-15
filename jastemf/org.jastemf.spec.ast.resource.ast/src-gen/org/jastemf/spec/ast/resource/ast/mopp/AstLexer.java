// $ANTLR ${project.version} ${buildNumber}

	package org.jastemf.spec.ast.resource.ast.mopp;


import org.antlr.runtime3_2_0.*;

public class AstLexer extends Lexer {
    public static final int QIDENT=5;
    public static final int WHITESPACE=8;
    public static final int EOF=-1;
    public static final int ML_COMMENT=7;
    public static final int T__19=19;
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
    public static final int COMMENT=6;
    public static final int LINEBREAK=9;

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

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:16:7: ( ':' )
            // Ast.g:16:9: ':'
            {
            match(':'); 

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
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
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
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
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
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
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
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
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
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
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
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
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
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
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
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
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
    // $ANTLR end "T__19"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:1871:8: ( '//' (~ ( '\\n' | '\\r' | '\\uffff' ) )* )
            // Ast.g:1872:2: '//' (~ ( '\\n' | '\\r' | '\\uffff' ) )*
            {
            match("//"); 

            // Ast.g:1872:6: (~ ( '\\n' | '\\r' | '\\uffff' ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\u0000' && LA1_0<='\t')||(LA1_0>='\u000B' && LA1_0<='\f')||(LA1_0>='\u000E' && LA1_0<='\uFFFE')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Ast.g:1872:7: ~ ( '\\n' | '\\r' | '\\uffff' )
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
            // Ast.g:1875:11: ( '/*' ( . )* '*/' )
            // Ast.g:1876:2: '/*' ( . )* '*/'
            {
            match("/*"); 

            // Ast.g:1876:6: ( . )*
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
            	    // Ast.g:1876:6: .
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
            // Ast.g:1878:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )* )
            // Ast.g:1879:2: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // Ast.g:1879:25: ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
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

    // $ANTLR start "QIDENT"
    public final void mQIDENT() throws RecognitionException {
        try {
            int _type = QIDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:1880:7: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )* ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )* )+ )
            // Ast.g:1881:2: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )* ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )* )+
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // Ast.g:1881:25: ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
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
            	    break loop4;
                }
            } while (true);

            // Ast.g:1881:58: ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )* )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='.') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // Ast.g:1881:59: '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            	    {
            	    match('.'); 
            	    if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}

            	    // Ast.g:1881:85: ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            	    loop5:
            	    do {
            	        int alt5=2;
            	        int LA5_0 = input.LA(1);

            	        if ( ((LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
            	            alt5=1;
            	        }


            	        switch (alt5) {
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
            	    	    break loop5;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QIDENT"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Ast.g:1882:11: ( ( ' ' | '\\t' | '\\f' ) )
            // Ast.g:1883:2: ( ' ' | '\\t' | '\\f' )
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
            // Ast.g:1886:10: ( ( '\\r\\n' | '\\r' | '\\n' ) )
            // Ast.g:1887:2: ( '\\r\\n' | '\\r' | '\\n' )
            {
            // Ast.g:1887:2: ( '\\r\\n' | '\\r' | '\\n' )
            int alt7=3;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\r') ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1=='\n') ) {
                    alt7=1;
                }
                else {
                    alt7=2;}
            }
            else if ( (LA7_0=='\n') ) {
                alt7=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // Ast.g:1887:3: '\\r\\n'
                    {
                    match("\r\n"); 


                    }
                    break;
                case 2 :
                    // Ast.g:1887:10: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 3 :
                    // Ast.g:1887:15: '\\n'
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
        // Ast.g:1:8: ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | COMMENT | ML_COMMENT | IDENT | QIDENT | WHITESPACE | LINEBREAK )
        int alt8=16;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // Ast.g:1:10: T__10
                {
                mT__10(); 

                }
                break;
            case 2 :
                // Ast.g:1:16: T__11
                {
                mT__11(); 

                }
                break;
            case 3 :
                // Ast.g:1:22: T__12
                {
                mT__12(); 

                }
                break;
            case 4 :
                // Ast.g:1:28: T__13
                {
                mT__13(); 

                }
                break;
            case 5 :
                // Ast.g:1:34: T__14
                {
                mT__14(); 

                }
                break;
            case 6 :
                // Ast.g:1:40: T__15
                {
                mT__15(); 

                }
                break;
            case 7 :
                // Ast.g:1:46: T__16
                {
                mT__16(); 

                }
                break;
            case 8 :
                // Ast.g:1:52: T__17
                {
                mT__17(); 

                }
                break;
            case 9 :
                // Ast.g:1:58: T__18
                {
                mT__18(); 

                }
                break;
            case 10 :
                // Ast.g:1:64: T__19
                {
                mT__19(); 

                }
                break;
            case 11 :
                // Ast.g:1:70: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 12 :
                // Ast.g:1:78: ML_COMMENT
                {
                mML_COMMENT(); 

                }
                break;
            case 13 :
                // Ast.g:1:89: IDENT
                {
                mIDENT(); 

                }
                break;
            case 14 :
                // Ast.g:1:95: QIDENT
                {
                mQIDENT(); 

                }
                break;
            case 15 :
                // Ast.g:1:102: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;
            case 16 :
                // Ast.g:1:113: LINEBREAK
                {
                mLINEBREAK(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\1\16\2\uffff\1\21\4\uffff\2\25\7\uffff\2\25\2\uffff\5"+
        "\25\1\34\1\uffff";
    static final String DFA8_eofS =
        "\35\uffff";
    static final String DFA8_minS =
        "\1\11\1\72\2\uffff\1\52\4\uffff\2\56\7\uffff\2\56\2\uffff\6\56"+
        "\1\uffff";
    static final String DFA8_maxS =
        "\1\172\1\72\2\uffff\1\57\4\uffff\2\172\7\uffff\2\172\2\uffff\6"+
        "\172\1\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\3\1\4\1\uffff\1\6\1\7\1\10\1\11\2\uffff\1\17\1\20\1"+
        "\2\1\1\1\13\1\14\1\5\2\uffff\1\16\1\15\6\uffff\1\12";
    static final String DFA8_specialS =
        "\35\uffff}>";
    static final String[] DFA8_transitionS = {
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
            "\1\24\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\1\23"+
            "\1\22\30\23",
            "\1\24\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32"+
            "\23",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\24\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\22"+
            "\23\1\26\7\23",
            "\1\24\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32"+
            "\23",
            "",
            "",
            "\1\24\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\23"+
            "\23\1\27\6\23",
            "\1\24\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\21"+
            "\23\1\30\10\23",
            "\1\24\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\1\31"+
            "\31\23",
            "\1\24\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\2\23"+
            "\1\32\27\23",
            "\1\24\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\23"+
            "\23\1\33\6\23",
            "\1\24\1\uffff\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32"+
            "\23",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | COMMENT | ML_COMMENT | IDENT | QIDENT | WHITESPACE | LINEBREAK );";
        }
    }
 

}