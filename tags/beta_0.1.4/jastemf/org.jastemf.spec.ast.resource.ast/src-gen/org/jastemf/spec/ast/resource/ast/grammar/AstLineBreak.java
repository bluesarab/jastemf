/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.grammar;

public class AstLineBreak extends org.jastemf.spec.ast.resource.ast.grammar.AstFormattingElement {
	
	private final int tabs;
	
	public AstLineBreak(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality, int tabs) {
		super(cardinality);
		this.tabs = tabs;
	}
	
	public int getTabs() {
		return tabs;
	}
	
	public String toString() {
		return "!" + getTabs();
	}
	
}
