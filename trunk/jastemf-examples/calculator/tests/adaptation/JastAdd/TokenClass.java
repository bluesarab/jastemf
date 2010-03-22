/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package rtt.adaptation;

import rtt.annotations.*;

import calculator.syntax.*;

/**
 * Support class woven by an aspect into Beaver's <tt>beaver.Symbol</tt> class
 * to adapt the Calculator for RTT.
 * @author C. Bürger
 */
@Lexer.Token
public abstract class TokenClass {
	public abstract short getId();
	public abstract int getStart();
	public abstract int getEnd();
	
	@Lexer.Token.EOF
	public boolean isEof() {
		return getId() == 0;
	}
	
	@Lexer.Token.Compare
	public String getType() {
		return CalculatorParser.Terminals.NAMES[getId()];
	}
	
	@Lexer.Token.Informational
	public int getStartLine() {
		return getStart();
	}
	
	@Lexer.Token.Informational
	public int getEndLine() {
		return getEnd();
	}
}
