/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package siple.semantics.resource.siple.analysis;

public class SipleIDENTIFIERTokenResolver implements siple.semantics.resource.siple.ISipleTokenResolver {
	
	private siple.semantics.resource.siple.analysis.SipleDefaultTokenResolver defaultTokenResolver = new siple.semantics.resource.siple.analysis.SipleDefaultTokenResolver();
	
	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		java.lang.String result = defaultTokenResolver.deResolve(value, feature, container);
		return result;
	}
	
	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, siple.semantics.resource.siple.ISipleTokenResolveResult result) {
		defaultTokenResolver.resolve(lexem, feature, result);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
