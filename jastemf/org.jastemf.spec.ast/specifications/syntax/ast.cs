SYNTAXDEF ast
FOR <http://www.jastemf.org/spec/ast><../metamodel/ast.genmodel>
START Grammar

OPTIONS {	
	tokenspace = "1";
	usePredefinedTokens = "false";
	overridePluginXML = "false";
}

TOKENS {
	DEFINE COMMENT $'//'(~('\n'|'\r'|'\uffff'))*$;
	DEFINE ML_COMMENT $'/*'.*'*/'$;
	DEFINE IDENT $('a'..'z'|'A'..'Z'|'_')('0'..'9'|'a'..'z'|'A'..'Z'|'_')*$;
	DEFINE WHITESPACE $(' '|'\t'|'\f')$;
	DEFINE LINEBREAK $('\r\n'|'\r'|'\n')$;
}
			
TOKENSTYLES {
	"abstract" COLOR #7aF005, BOLD;
}
			 
			
RULES {
	Grammar ::=  TypeDecl*;
				
	ASTDecl ::= Abstract? IdDecl (":" SuperClassId)? ( "::=" Components+)? ";";
				
	ListComponents ::= Id "*" ;
	ListComponentsNTA ::= "/" Id "*" "/"  ;
				
	TokenComponent ::= "<" TokenId ">";
	TokenComponentNTA ::= "/" "<" TokenId ">" "/" ;
				
	OptionalComponent ::= "[" Id "]";
	OptionalComponentNTA ::= "/" "[" Id "]" "/"  ;
				
	AggregateComponents ::= Id;
	AggregateComponentsNTA ::= "/" Id "/"  ;
				
	Abstract ::= "abstract"   ;
				
    Id ::= (NameNode ":")? IdUse ;

	NameNode ::= ID[IDENT] ;
				
	TokenId ::= ID[IDENT] ( ":" TYPE[IDENT] )? ;
				
	IdUse ::=   ID[IDENT];
	IdDecl ::=  ID[IDENT];
}