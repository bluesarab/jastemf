/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package rtt.adaptation;

import java.io.*;

import rtt.annotations.*;

import siple.symbols.*;
import siple.syntax.*;

/**
 * AspectJ specification to adapt the Parser for RTT.
 * @author C. Bürger
 */
public aspect RTTParser {
	// Specify parser class:
	declare @type: SIPLEParser : @Parser;

	// Insert initialization method:
	declare @method:
		public void SIPLEParser.initParser(InputStream) : @Parser.Initialize;
	private SIPLELexer SIPLEParser.lexer;
	public void SIPLEParser.initParser(InputStream is) {
		lexer = new SIPLELexer(is);
	}
	
	// Insert AST access method:
	declare @method:
		public Object SIPLEParser.getAst() : @Parser.AST;
	public Object SIPLEParser.getAst() throws Exception, IOException {
		return parse(lexer);
	}
}
