/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstAntlrScanner implements org.jastemf.spec.ast.resource.ast.IAstTextScanner {
	
	private org.antlr.runtime3_2_0.Lexer antlrLexer;
	
	public AstAntlrScanner(org.antlr.runtime3_2_0.Lexer antlrLexer) {
		this.antlrLexer = antlrLexer;
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTextToken getNextToken() {
		if (antlrLexer.getCharStream() == null) {
			return null;
		}
		final org.antlr.runtime3_2_0.Token current = antlrLexer.nextToken();
		org.jastemf.spec.ast.resource.ast.IAstTextToken result = new org.jastemf.spec.ast.resource.ast.mopp.AstTextToken(current);
		return result;
	}
	
	public void setText(java.lang.String text) {
		antlrLexer.setCharStream(new org.antlr.runtime3_2_0.ANTLRStringStream(text));
	}
	
}
