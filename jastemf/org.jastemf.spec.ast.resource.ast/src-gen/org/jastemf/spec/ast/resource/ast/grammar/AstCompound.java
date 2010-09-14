/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.grammar;

public class AstCompound extends org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement {
	
	public AstCompound(org.jastemf.spec.ast.resource.ast.grammar.AstChoice choice, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality) {
		super(cardinality, new org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement[] {choice});
	}
	
	public String toString() {
		return "(" + getChildren()[0] + ")";
	}
	
}
