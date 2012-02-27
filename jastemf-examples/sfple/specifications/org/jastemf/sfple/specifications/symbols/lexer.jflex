/**
 * This program and the accompanying materials are made available under the
 * terms of the MIT license (X11 license) which accompanies this distribution.
 */

/**
 * @author C. Bürger
 */

package org.jastemf.sfple.symbols;

import beaver.Symbol;
import beaver.Scanner;

import org.jastemf.sfple.syntax.Parser.Terminals;

%%

%class Lexer
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

LineTerminator	= \r|\n|\r\n
Comment			= "--" [^\r\n]*
WhiteSpace		= {LineTerminator} | [ \t\f] | {Comment}
Variable		= [A-Z] ([a-zA-Z0-9]|"_")*
Function		= [a-z] ([a-z0-9]|"_")*

%{
	private Symbol createToken(short id) {
		return new Symbol(id, yyline, yycolumn, yylength(), yytext());
	}
	private Symbol createToken(short id, Object value) {
		return new Symbol(id, yyline, yycolumn, yylength(), value);
	}
%}

%%

<YYINITIAL> {
	"Let"			{ return createToken(Terminals.kLET); }
	"In"			{ return createToken(Terminals.kIN); }
	"If"			{ return createToken(Terminals.kIF); }
	"Then"			{ return createToken(Terminals.kTHEN); }
	"Else"			{ return createToken(Terminals.kELSE); }
	"Fi"			{ return createToken(Terminals.kFI); }
	"not"			{ return createToken(Terminals.kNOT); }
	"and"			{ return createToken(Terminals.kAND); }
	"or"			{ return createToken(Terminals.kOR); }
	"["				{ return createToken(Terminals.pBRACKETOPENSQUARE); }
	"]"				{ return createToken(Terminals.pBRACKETCLOSESQUARE); }
	"("				{ return createToken(Terminals.pBRACKETOPENROUND); }
	")"				{ return createToken(Terminals.pBRACKETCLOSEROUND); }
	"{"				{ return createToken(Terminals.pBRACKETOPENCURLY); }
	"}"				{ return createToken(Terminals.pBRACKETCLOSECURLY); }
	"<="			{ return createToken(Terminals.pANGLEBRACKETLEFT_EQUALS); }
	">="			{ return createToken(Terminals.pANGLEBRACKETRIGHT_EQUALS); }
	"<"				{ return createToken(Terminals.pANGLEBRACKETLEFT); }
	">"				{ return createToken(Terminals.pANGLEBRACKETRIGHT); }
	"+"				{ return createToken(Terminals.pPLUS); }
	"-"				{ return createToken(Terminals.pMINUS); }
	"*"				{ return createToken(Terminals.pSTAR); }
	"/"				{ return createToken(Terminals.pSLASH); }
	"="				{ return createToken(Terminals.pEQUALS); }
	"#"				{ return createToken(Terminals.pSHARP); }
	","				{ return createToken(Terminals.pCOMMA); }
	".."			{ return createToken(Terminals.pPOINT_POINT); }
	":="			{ return createToken(Terminals.pCOLON_EQUALS); }
	"true"			{ return createToken(Terminals.BOOLEAN, new Boolean(true)); }
	"false"			{ return createToken(Terminals.BOOLEAN, new Boolean(false)); }
	[0-9]+"."[0-9]+	{ return createToken(Terminals.REAL, new Float(Float.parseFloat(yytext()))); }
	[0-9]+			{ return createToken(Terminals.INTEGER, new Integer(Integer.parseInt(yytext()))); }
	{Variable}		{ return createToken(Terminals.IDENTIFIER, yytext()); }
	{Function}		{ return createToken(Terminals.FUN, yytext()); }
	{WhiteSpace}	{ /* ignore */ }
}
.					{ throw new Scanner.Exception("Unknown symbol!"); }
