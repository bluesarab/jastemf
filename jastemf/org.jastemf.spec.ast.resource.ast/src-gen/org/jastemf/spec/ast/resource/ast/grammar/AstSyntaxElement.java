/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.grammar;

/**
 * The abstract super class for all elements of a grammar. This class provides
 * methods to traverse the grammar rules.
 */
public abstract class AstSyntaxElement {
	
	private AstSyntaxElement[] children;
	private AstSyntaxElement parent;
	private org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality;
	
	public AstSyntaxElement(org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality, AstSyntaxElement[] children) {
		this.cardinality = cardinality;
		this.children = children;
		if (this.children != null) {
			for (AstSyntaxElement child : this.children) {
				child.setParent(this);
			}
		}
	}
	
	public void setParent(AstSyntaxElement parent) {
		assert this.parent == null;
		this.parent = parent;
	}
	
	public AstSyntaxElement[] getChildren() {
		if (children == null) {
			return new AstSyntaxElement[0];
		}
		return children;
	}
	
	public org.eclipse.emf.ecore.EClass getMetaclass() {
		return parent.getMetaclass();
	}
	
	public org.jastemf.spec.ast.resource.ast.grammar.AstCardinality getCardinality() {
		return cardinality;
	}
	
}
