package rtt.adaptation;

import calculator.syntax.Parser;

import rtt.annotations.Lexer;

/**
 * Support class woven by an aspect into Beaver's <tt>beaver.Symbol</tt>
 * class to adapt the Calculator for RTT.
 * @author C. Bürger
 */
@Lexer.Token
public abstract class Token {
	public abstract short getId();
	public abstract int getStart();
	public abstract int getEnd();
	
	@Lexer.Token.EOF
	public boolean isEof() {
		return getId() == 0;
	}
	
	@Lexer.Token.Compare
	public String getType() {
		return Parser.Terminals.NAMES[getId()];
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
