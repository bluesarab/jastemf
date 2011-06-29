/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.grammar;

public class AstContainment extends org.jastemf.spec.ast.resource.ast.grammar.AstTerminal {
	
	public AstContainment(org.eclipse.emf.ecore.EStructuralFeature feature, org.jastemf.spec.ast.resource.ast.grammar.AstCardinality cardinality, int mandatoryOccurencesAfter) {
		super(feature, cardinality, mandatoryOccurencesAfter);
	}
	
	public String toString() {
		return getFeature().getName();
	}
	
}
