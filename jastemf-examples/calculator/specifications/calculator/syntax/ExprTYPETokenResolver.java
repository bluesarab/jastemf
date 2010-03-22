/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package calculator.semantics.resource.expr.analysis;

import calculator.semantics.Type;

public class ExprTYPETokenResolver implements calculator.semantics.resource.expr.IExprTokenResolver {
	
	private calculator.semantics.resource.expr.analysis.ExprDefaultTokenResolver defaultTokenResolver = new calculator.semantics.resource.expr.analysis.ExprDefaultTokenResolver();
	
	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		if (value == Type.Bool){
			return "BOOL";
		}
		else if(value == Type.Integer){
			return "INT";
		}
		else if(value == Type.Float){
			return "FLOAT";
		}		
		return 
		null;
	}
	
	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, calculator.semantics.resource.expr.IExprTokenResolveResult result) {
		if ("BOOL".equals(lexem)){
			result.setResolvedToken(Type.Bool);
			return;
		}
		else if("INT".equals(lexem)){
			result.setResolvedToken(Type.Integer);
		}
		else if("FLOAT".equals(lexem)){
			result.setResolvedToken(Type.Float);
		}
		else{
			result.setErrorMessage(lexem+" is no valid type.");
		}

	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
