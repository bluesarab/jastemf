/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */

/**
 * @author C. Bürger
 */
package calculator.symbols;

import beaver.Symbol;
import beaver.Scanner;

import calculator.syntax.CalculatorParser.Terminals;

%%

%class CalculatorLexer
%extends Scanner
%public
%function nextToken
%type Symbol
%yylexthrow Scanner.Exception
%eofval{
	return new Symbol(Terminals.EOF, "end-of-file");
%eofval}
%line
%column

LineTerminator = \r|\n|\r\n
Comment        = "%" [^\r\n]*
WhiteSpace     = {LineTerminator} | [ \t\f] | {Comment}
Identifier	   = [a-zA-Z] [a-zA-Z0-9]*
RealNumber     = [0-9]+[.][0-9]+
IntegerNumber  = [0-9]+

%{
	private Symbol createPrimitiveToken(short id) {
		return new Symbol(id, yyline, yycolumn, yylength(), yytext());
	}
%}

%%

<YYINITIAL> {
	"VAR"			{ return createPrimitiveToken(Terminals.kVAR); }
	"BEGIN"			{ return createPrimitiveToken(Terminals.kBEGIN); }
	"END"			{ return createPrimitiveToken(Terminals.kEND); }
	"INT"			{ return createPrimitiveToken(Terminals.kINT); }
	"FLOAT"			{ return createPrimitiveToken(Terminals.kFLOAT); }
	"BOOL"			{ return createPrimitiveToken(Terminals.kBOOL); }
	"("				{ return createPrimitiveToken(Terminals.pBRACKETOPENROUND); }
	")"				{ return createPrimitiveToken(Terminals.pBRACKETCLOSEROUND); }
	"*"				{ return createPrimitiveToken(Terminals.pSTAR); }
	"+"				{ return createPrimitiveToken(Terminals.pPLUS); }
	"-"				{ return createPrimitiveToken(Terminals.pMINUS); }
	"/"				{ return createPrimitiveToken(Terminals.pSLASH); }
	"!"				{ return createPrimitiveToken(Terminals.pEXCLAMATIONMARK); }
	"="				{ return createPrimitiveToken(Terminals.pEQUALS); }
	":"				{ return createPrimitiveToken(Terminals.pCOLON); }
	";"				{ return createPrimitiveToken(Terminals.pSEMICOLON); }
	"&&"			{ return createPrimitiveToken(Terminals.pAND_AND); }
	"||"			{ return createPrimitiveToken(Terminals.pLINEVERTICAL_LINEVERTICAL); }
	"=="			{ return createPrimitiveToken(Terminals.pEQUALS_EQUALS); }
	"true"			{ return createPrimitiveToken(Terminals.CONSTANT); }
	"false"			{ return createPrimitiveToken(Terminals.CONSTANT); }
	{RealNumber}	{ return createPrimitiveToken(Terminals.CONSTANT); }
	{IntegerNumber}	{ return createPrimitiveToken(Terminals.CONSTANT); }
	{Identifier}	{ return createPrimitiveToken(Terminals.IDENTIFIER); }
	{WhiteSpace}	{ /* ignore */ }
}
.					{ throw new Scanner.Exception("Unknown symbol!"); }
