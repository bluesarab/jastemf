/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public abstract class AstANTLRParserBase extends org.antlr.runtime3_2_0.Parser implements org.jastemf.spec.ast.resource.ast.IAstTextParser {
	
	public AstANTLRParserBase(org.antlr.runtime3_2_0.TokenStream input) {
		super(input);
	}
	
	public AstANTLRParserBase(org.antlr.runtime3_2_0.TokenStream input, org.antlr.runtime3_2_0.RecognizerSharedState state) {
		super(input, state);
	}
	
}
