/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf.siple.resource.siple.analysis;

public class SipleIDENTIFIERTokenResolver implements org.jastemf.siple.resource.siple.ISipleTokenResolver {
	
	private org.jastemf.siple.resource.siple.analysis.SipleDefaultTokenResolver defaultTokenResolver = new org.jastemf.siple.resource.siple.analysis.SipleDefaultTokenResolver();
	
	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		java.lang.String result = defaultTokenResolver.deResolve(value, feature, container);
		return result;
	}
	
	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, org.jastemf.siple.resource.siple.ISipleTokenResolveResult result) {
		defaultTokenResolver.resolve(lexem, feature, result);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
