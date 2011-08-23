SYNTAXDEF picojava
FOR <file:/D:/development/workspaces/jastadd-pico-java/org.jastemf.picojava/specifications/model/picojava.ast> <../model/picojava.genmodel>
START Program


OPTIONS {
	//generateCodeFromGeneratorModel = "true";
	usePredefinedTokens = "false";
	overridePluginXML = "false";
	overrideManifest = "false";
}


TOKENS {
	DEFINE COMMENT $'//'(~('\n'|'\r'|'\uffff'))*$;
	DEFINE INTEGER $('-')?('1'..'9')('0'..'9')*|'0'$;
	DEFINE FLOAT $('-')?(('1'..'9') ('0'..'9')* | '0') '.' ('0'..'9')+ $;
	DEFINE LIT_BOOLEAN $('true'|'false')$;
	DEFINE IDENTIFIER $('A'..'Z'|'a'..'z') ('A'..'Z'|'a'..'z'|'0'..'9'|'_')*$;
	DEFINE WHITESPACE $(' '|'\t'|'\f')$;
	DEFINE LINEBREAK $('\r\n'|'\r'|'\n')$;
}


TOKENSTYLES {
	
}


RULES {
	Program ::= "{" Block  "}";
	
	Block ::= BlockStmt*;
	
	ClassDecl ::= ("public")? "class"  Name[IDENTIFIER]  ("extends" SuperclassId)? "{" Body "}";
	
	VarDecl ::= TypeAccess  Name[IDENTIFIER] ";";
	
	AssignStmt ::=  Variable "=" Value ";";
	
	WhileStmt ::= "while" "(" Condition ")"  ( "{"  Body  "}" | Body );
	
	Use ::= Name[IDENTIFIER] ;
	 
	Dot ::= ObjectReference:IdUse "." IdUse;
	
	BooleanLiteral ::=  Value[LIT_BOOLEAN];
	
	TypeUse ::= Name[IDENTIFIER];
	
	VariableUse ::= Name[IDENTIFIER] ;
}