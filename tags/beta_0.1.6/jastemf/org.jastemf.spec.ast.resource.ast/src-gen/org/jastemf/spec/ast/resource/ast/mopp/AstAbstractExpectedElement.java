/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

/**
 * Abstract super class for all expected elements. Provides methods to add
 * followers.
 */
public abstract class AstAbstractExpectedElement implements org.jastemf.spec.ast.resource.ast.IAstExpectedElement {
	
	private org.eclipse.emf.ecore.EClass ruleMetaclass;
	private java.util.Set<org.jastemf.spec.ast.resource.ast.util.AstPair<org.jastemf.spec.ast.resource.ast.IAstExpectedElement, org.eclipse.emf.ecore.EStructuralFeature[]>> followers = new java.util.LinkedHashSet<org.jastemf.spec.ast.resource.ast.util.AstPair<org.jastemf.spec.ast.resource.ast.IAstExpectedElement, org.eclipse.emf.ecore.EStructuralFeature[]>>();
	
	public AstAbstractExpectedElement(org.eclipse.emf.ecore.EClass ruleMetaclass) {
		super();
		this.ruleMetaclass = ruleMetaclass;
	}
	
	public org.eclipse.emf.ecore.EClass getRuleMetaclass() {
		return ruleMetaclass;
	}
	
	public void addFollower(org.jastemf.spec.ast.resource.ast.IAstExpectedElement follower, org.eclipse.emf.ecore.EStructuralFeature[] path) {
		followers.add(new org.jastemf.spec.ast.resource.ast.util.AstPair<org.jastemf.spec.ast.resource.ast.IAstExpectedElement, org.eclipse.emf.ecore.EStructuralFeature[]>(follower, path));
	}
	
	public java.util.Collection<org.jastemf.spec.ast.resource.ast.util.AstPair<org.jastemf.spec.ast.resource.ast.IAstExpectedElement, org.eclipse.emf.ecore.EStructuralFeature[]>> getFollowers() {
		return followers;
	}
	
}
