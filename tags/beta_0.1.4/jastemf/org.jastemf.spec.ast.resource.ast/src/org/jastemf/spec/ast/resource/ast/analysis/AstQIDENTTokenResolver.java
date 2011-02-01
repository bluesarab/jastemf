/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.analysis;

public class AstQIDENTTokenResolver implements org.jastemf.spec.ast.resource.ast.IAstTokenResolver {
	
	private org.jastemf.spec.ast.resource.ast.analysis.AstDefaultTokenResolver defaultTokenResolver = new org.jastemf.spec.ast.resource.ast.analysis.AstDefaultTokenResolver();
	
	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		java.lang.String result = defaultTokenResolver.deResolve(value, feature, container);
		return result;
	}
	
	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, org.jastemf.spec.ast.resource.ast.IAstTokenResolveResult result) {
		defaultTokenResolver.resolve(lexem, feature, result);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
