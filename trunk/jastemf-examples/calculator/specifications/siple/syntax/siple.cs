// This program and the accompanying materials are made available under the
// terms of the BSD 3-clause license which accompanies this distribution.

SYNTAXDEF siple
FOR <http://siple/1.0><../model/siple.genmodel>
START CompilationUnit

OPTIONS {
	//generateCodeFromGeneratorModel = "true";
	usePredefinedTokens = "false";
}
  
TOKENS {
	DEFINE TYPE $('Integer' | 'Real' | 'Boolean')$;
	DEFINE VALUE $(('0'..'9')+) | (('0'..'9')+ ( '.' ('0'..'9')+ )) | 'true' | 'false'$;
	DEFINE IDENTIFIER $('A'..'Z'|'a'..'z') ('A'..'Z'|'a'..'z'|'0'..'9'|'_')*$;
	DEFINE COMMENT $'%'(~('\n'|'\r'))*$;
	DEFINE WHITESPACE $(' '|'\t'|'\f')$;
	DEFINE LINEBREAK $('\r\n'|'\r'|'\n')$;
}

RULES { 
	CompilationUnit ::= ( Declaration ";" )*;
	
	Block ::= (Statement:VariableDeclaration,ProcedureDeclaration,VariableAssignment,Expression,If,While,ProcedureReturn,Write ";" | "Begin" Statement:Block "End" ";")*;
		
	If ::= 	"If" Condition "Then" Body ("Else" Alternative)? "Fi";
	
	While ::= "While" Condition "Do" Body "oD";
	
	Write ::= "Write" Expression;
	
	Read ::= "Read" LValue[IDENTIFIER];
	
	ProcedureReturn ::= "Return" (Expression)?;
	
	VariableDeclaration ::= "Var" Name[IDENTIFIER] ":" DeclaredType[TYPE];
	
	ProcedureDeclaration ::= "Procedure" Name[IDENTIFIER] "(" ( Parameter ("," Parameter)* )? ")" (":" ReturnType[TYPE] )? "Begin" Body:Block "End";
	
	// We do not use the standard reference resolving mechanism,
	// since we are using JastEMF
	
	VariableAssignment ::= LValue[IDENTIFIER] ":=" RValue; 
	
	@operator(type="binary_left_associative",weight="101",superclass="Expression")
	Or ::= Operand1 "Or" Operand2;
	
	@operator(type="binary_left_associative",weight="102",superclass="Expression")
	And ::= Operand1 "And" Operand2;
	
	@operator(type="binary_left_associative",weight="104",superclass="Expression")
	Equal ::= Operand1 "=" Operand2;
	
	@operator(type="binary_left_associative",weight="104",superclass="Expression")
	GreaterThan ::= Operand1 ">" Operand2;
	
	@operator(type="binary_left_associative",weight="104",superclass="Expression")
	LesserThan ::= Operand1 "<" Operand2;
	
	@operator(type="binary_left_associative",weight="104",superclass="Expression")
	GreaterThanEqual ::= Operand1 ">=" Operand2;
	
	@operator(type="binary_left_associative",weight="104",superclass="Expression")
	LesserThanEqual ::= Operand1 "<=" Operand2;
	
	@operator(type="binary_left_associative",weight="201",superclass="Expression")
	Addition ::= Operand1 "+" Operand2;
	
	@operator(type="binary_left_associative",weight="201",superclass="Expression")
	Subtraction ::= Operand1 "-" Operand2;
	
	@operator(type="binary_left_associative",weight="202",superclass="Expression")
	Multiplication ::= Operand1 "*" Operand2;
	
	@operator(type="binary_left_associative",weight="202",superclass="Expression")
	Division ::= Operand1 "/" Operand2;
	
	@operator(type="unary_prefix",weight="301",superclass="Expression")
	Not ::= "Not" Operand;
	
	@operator(type="unary_prefix",weight="302",superclass="Expression")
	UMinus ::= "-" Operand;
	
	@operator(type="primitive",weight="303",superclass="Expression")
	Constant ::= Lexem[VALUE];
	
	// We do not use the standard reference revolving mechanism,
	// since we are using JastEMF
	@operator(type="primitive",weight="303",superclass="Expression")
	Reference ::= Name[IDENTIFIER];
	
	@operator(type="primitive",weight="303",superclass="Expression")
	NestedExpression ::= "(" Expression ")";
	
	@operator(type="primitive",weight="303",superclass="Expression")
	ProcedureCall ::= Name[IDENTIFIER] "(" ( Argument ("," Argument)*)? ")";
}
