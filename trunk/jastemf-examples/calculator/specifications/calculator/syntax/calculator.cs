// This program and the accompanying materials are made available under the
// terms of the BSD 3-clause license which accompanies this distribution.

SYNTAXDEF expr
FOR <http://calculator/1.0><../model/calculator.genmodel>
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
	
	Block ::= "Begin" (Statement:Block,VariableDeclaration,ProcedureDeclaration,VariableAssignment,Expression,If,While,ProcedureReturn,Write ";")* "End";
		
	If ::= 	"If" Condition "Then" Body ("Else" Alternative)? "Fi";
	
	While ::= "While" Condition "Do" Body "oD";
	
	Write ::= "Write" Expression;
	
	ProcedureReturn ::= "Return" (Expression)?;
	
	VariableDeclaration ::= "Var" Name[IDENTIFIER] ":" DeclaredType[TYPE];
	
	ProcedureDeclaration ::= "Procedure" Name[IDENTIFIER] "(" ( Parameter ("," Parameter)* )? ")" (":" ReturnType[TYPE] )? Body:Block ;
	
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
