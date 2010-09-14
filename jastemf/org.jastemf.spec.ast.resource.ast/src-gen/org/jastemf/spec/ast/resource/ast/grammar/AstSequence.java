/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.grammar;

public class AstSequence extends org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement {
	
	public AstSequence(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality, org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement... elements) {
		super(cardinality, elements);
	}
	
	public String toString() {
		return org.jastemf.spec.ast.resource.ast.util.AstStringUtil.explode(getChildren(), " ");
	}
	
}
