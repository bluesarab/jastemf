/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast;

/**
 * An element that is expected at a given position in a resource stream.
 */
public interface IAstExpectedElement {
	
	public java.lang.String getTokenName();
	public org.eclipse.emf.ecore.EClass getRuleMetaclass();
	public void addFollower(org.jastemf.spec.ast.resource.ast.IAstExpectedElement follower, org.eclipse.emf.ecore.EStructuralFeature[] path);
	public java.util.Collection<org.jastemf.spec.ast.resource.ast.util.AstPair<org.jastemf.spec.ast.resource.ast.IAstExpectedElement, org.eclipse.emf.ecore.EStructuralFeature[]>> getFollowers();
}
