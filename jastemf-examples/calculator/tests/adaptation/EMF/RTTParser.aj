package rtt.adaptation;

import java.io.*;

import rtt.annotations.*;

import calculator.symbols.*;
import calculator.syntax.*;

/**
 * AspectJ specification to adapt the Caclulator's Parser for RTT.
 * @author C. Bürger
 */
public aspect RTTParser {
	// Specify parser class:
	declare @type: CalculatorParser : @Parser;

	// Insert initialization method:
	declare @method:
		public void CalculatorParser.initParser(InputStream) : @Parser.Initialize;
	private CalculatorLexer CalculatorParser.lexer;
	public void CalculatorParser.initParser(InputStream is) {
		lexer = new CalculatorLexer(is);
	}
	
	// Insert AST access method:
	declare @method:
		public Object CalculatorParser.getAst() : @Parser.AST;
	public Object CalculatorParser.getAst() throws Exception, IOException {
		return parse(lexer);
	}
}
