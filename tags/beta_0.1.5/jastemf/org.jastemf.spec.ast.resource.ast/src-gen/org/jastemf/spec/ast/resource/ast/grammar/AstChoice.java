/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.grammar;

public class AstChoice extends org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement {
	
	public AstChoice(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality, org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement... choices) {
		super(cardinality, choices);
	}
	
	public String toString() {
		return org.jastemf.spec.ast.resource.ast.util.AstStringUtil.explode(getChildren(), "|");
	}
	
}
