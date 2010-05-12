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
	
	ProcedureReturn ::= "Return" (Expression)?;
	
	VariableDeclaration ::= "Var" Name[IDENTIFIER] ":" DeclaredType[TYPE];
	
	ProcedureDeclaration ::= "Procedure" Name[IDENTIFIER] "(" ( Parameter ("," Parameter)* )? ")" (":" ReturnType[TYPE] )? "Begin" Body:Block "End";
	
	// We do not use the standard reference resolving mechanism,
	// since we are using JastEMF
	
	VariableAssignment ::= LValue[IDENTIFIER] ":=" RValue; 
	
	@operator(type="binary_left_associative",weight="101",identifier="Expression")
	Or ::= Operand1 "Or" Operand2;
	
	@operator(type="binary_left_associative",weight="102",identifier="Expression")
	And ::= Operand1 "And" Operand2;
	
	@operator(type="binary_left_associative",weight="104",identifier="Expression")
	Equal ::= Operand1 "=" Operand2;
	
	@operator(type="binary_left_associative",weight="104",identifier="Expression")
	GreaterThan ::= Operand1 ">" Operand2;
	
	@operator(type="binary_left_associative",weight="104",identifier="Expression")
	LesserThan ::= Operand1 "<" Operand2;
	
	@operator(type="binary_left_associative",weight="104",identifier="Expression")
	GreaterThanEqual ::= Operand1 ">=" Operand2;
	
	@operator(type="binary_left_associative",weight="104",identifier="Expression")
	LesserThanEqual ::= Operand1 "<=" Operand2;
	
	@operator(type="binary_left_associative",weight="201",identifier="Expression")
	Addition ::= Operand1 "+" Operand2;
	
	@operator(type="binary_left_associative",weight="201",identifier="Expression")
	Subtraction ::= Operand1 "-" Operand2;
	
	@operator(type="binary_left_associative",weight="202",identifier="Expression")
	Multiplication ::= Operand1 "*" Operand2;
	
	@operator(type="binary_left_associative",weight="202",identifier="Expression")
	Division ::= Operand1 "/" Operand2;
	
	@operator(type="unary",weight="301",identifier="Expression")
	Not ::= "Not" Operand;
	
	@operator(type="unary",weight="302",identifier="Expression")
	UMinus ::= "-" Operand;
	
	@operator(type="primitive",weight="303",identifier="Expression")
	Constant ::= Lexem[VALUE];
	
	// We do not use the standard reference revolving mechanism,
	// since we are using JastEMF
	@operator(type="primitive",weight="303",identifier="Expression")
	Reference ::= Name[IDENTIFIER];
	
	@operator(type="primitive",weight="303",identifier="Expression")
	NestedExpression ::= "(" Expression ")";
	
	@operator(type="primitive",weight="303",identifier="Expression")
	ProcedureCall ::= Name[IDENTIFIER] "(" ( Argument ("," Argument)*)? ")";
}
