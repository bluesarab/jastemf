package rtt.adaptation;

import java.io.*;

import calculator.symbols.*;
import calculator.syntax.*;

/**
 * AspectJ specification to adapt the Caclulator's parser for RTT.
 * @author C. Bürger
 */
public aspect RTTParser {
	// Specify parser class:
	declare @type: Parser : @rtt.annotations.Parser;

	// Insert initialization method:
	declare @method:
		public void Parser.initParser(InputStream) : @rtt.annotations.Parser.Initialize;
	private Lexer Parser.lexer;
	public void Parser.initParser(InputStream is) {
		lexer = new Lexer(is);
	}
	
	// Insert AST access method:
	declare @method:
		public Object Parser.getAst() : @rtt.annotations.Parser.AST;
	public Object Parser.getAst() throws Exception, IOException {
		return parse(lexer);
	}
}
