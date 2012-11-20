/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.grammar;

/**
 * A class to represent a keyword in the grammar.
 */
public class AstKeyword extends org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement {
	
	private final String value;
	
	public AstKeyword(String value, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality) {
		super(cardinality, null);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return value;
	}
	
}
