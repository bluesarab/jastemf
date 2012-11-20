/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.grammar;

/**
 * A class to represent placeholders in a grammar.
 */
public class AstPlaceholder extends org.jastemf.spec.ast.resource.ast.grammar.AstTerminal {
	
	private final java.lang.String tokenName;
	
	public AstPlaceholder(org.eclipse.emf.ecore.EStructuralFeature feature, java.lang.String tokenName, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
		this.tokenName = tokenName;
	}
	
	public java.lang.String getTokenName() {
		return tokenName;
	}
	
}
