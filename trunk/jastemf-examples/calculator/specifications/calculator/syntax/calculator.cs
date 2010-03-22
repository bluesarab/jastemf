SYNTAXDEF expr
FOR <http://calculator/1.0><../model/calculator.genmodel>
START CompilationUnit

OPTIONS {
//  generateCodeFromGeneratorModel = "true";
  usePredefinedTokens = "false";
   
} 

TOKENS {
  DEFINE TYPE $('INT' | 'FLOAT' | 'BOOL')$;
  DEFINE VALUE $(('0'..'9')+) | (('0'..'9')+ ( '.' ('0'..'9')+ )) | 'true' | 'false'$;
  DEFINE IDENTIFIER $('A'..'Z'|'a'..'z') ('A'..'Z'|'a'..'z'|'0'..'9'|'_')*$;
  DEFINE COMMENT $'%'(~('\n'|'\r'))*$;
  DEFINE WHITESPACE $(' '|'\t'|'\f')$;
  DEFINE LINEBREAK $('\r\n'|'\r'|'\n')$;
}

RULES {
  CompilationUnit ::= Block ;

  Block ::= (( "BEGIN" Statement:Block "END" ";")|(Statement:VariableDeclaration,VariableAssignment,Expression ";"))*;

  VariableDeclaration ::= "VAR" Name[IDENTIFIER] ":" VariableType[TYPE];

  //We do not use the standard reference revolving mechanism, 
  //since we are using JastEMF
  
  VariableAssignment ::= LValue[IDENTIFIER] "=" RValue;
   
  
  @operator(type="binary_left_associative",weight="101",identifier="Expression")
  Or ::= Operand1 "||" Operand2;
  
  @operator(type="binary_left_associative",weight="102",identifier="Expression")
  And ::= Operand1 "&&" Operand2;
		
  @operator(type="binary_left_associative",weight="104",identifier="Expression")
  Equal ::= Operand1 "==" Operand2;
  
  @operator(type="binary_left_associative",weight="201",identifier="Expression")
  Addition ::= Operand1 "+" Operand2;
  
  @operator(type="binary_left_associative",weight="201",identifier="Expression")
  Subtraction ::= Operand1 "-" Operand2;
  
  @operator(type="binary_left_associative",weight="202",identifier="Expression")
  Multiplication ::= Operand1 "*" Operand2;
  
  @operator(type="binary_left_associative",weight="202",identifier="Expression")
  Division ::= Operand1 "/" Operand2;
  
  @operator(type="unary",weight="301",identifier="Expression")
  Not ::= "!" Operand;	
  
  @operator(type="unary",weight="302",identifier="Expression")
  UMinus ::= "-" Operand;
  
  @operator(type="primitive",weight="303",identifier="Expression")
  Constant ::= ConstantValue[VALUE];
  
  //We do not use the standard reference revolving mechanism, 
  //since we are using JastEMF
  @operator(type="primitive",weight="303",identifier="Expression")
  Reference ::= Name[IDENTIFIER];
  
  @operator(type="primitive",weight="303",identifier="Expression")
  NestedExpression ::= "(" Expression ")";   
 
}