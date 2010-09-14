/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.grammar;

public class AstWhiteSpace extends org.jastemf.spec.ast.resource.ast.grammar.AstFormattingElement {
	
	private final int amount;
	
	public AstWhiteSpace(int amount, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality) {
		super(cardinality);
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String toString() {
		return "#" + getAmount();
	}
	
}
