/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

/**
 * A representation for a range in a document where a structural feature (e.g., a
 * reference) is expected.
 */
public class AstExpectedStructuralFeature extends org.jastemf.spec.ast.resource.ast.mopp.AstAbstractExpectedElement {
	
	private org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder placeholder;
	
	public AstExpectedStructuralFeature(org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder placeholder) {
		super(placeholder.getMetaclass());
		this.placeholder = placeholder;
	}
	
	public org.eclipse.emf.ecore.EStructuralFeature getFeature() {
		return placeholder.getFeature();
	}
	
	public String getTokenName() {
		return placeholder.getTokenName();
	}
	
	public java.lang.String toString() {
		return "EFeature " + getFeature().getEContainingClass().getName() + "." + getFeature().getName();
	}
	
	public boolean equals(java.lang.Object o) {
		if (o instanceof AstExpectedStructuralFeature) {
			return getFeature().equals(((AstExpectedStructuralFeature) o).getFeature());
		}
		return false;
	}
}
