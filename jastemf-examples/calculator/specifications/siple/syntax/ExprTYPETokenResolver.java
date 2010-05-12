/**
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 */
package siple.semantics.resource.expr.analysis;

import siple.semantics.Type;

public class ExprTYPETokenResolver implements siple.semantics.resource.expr.IExprTokenResolver {
	
	private siple.semantics.resource.expr.analysis.ExprDefaultTokenResolver defaultTokenResolver = new siple.semantics.resource.expr.analysis.ExprDefaultTokenResolver();
	
	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		if (value == Type.Boolean){
			return "Boolean";
		}
		else if(value == Type.Integer){
			return "Integer";
		}
		else if(value == Type.Real){
			return "Real";
		}		
		return 
		null;
	}
	
	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, siple.semantics.resource.expr.IExprTokenResolveResult result) {
		if ("Boolean".equals(lexem)){
			result.setResolvedToken(Type.Boolean);
			return;
		}
		else if("Integer".equals(lexem)){
			result.setResolvedToken(Type.Integer);
		}
		else if("Real".equals(lexem)){
			result.setResolvedToken(Type.Real);
		}
		else{
			result.setErrorMessage(lexem+" is no valid type.");
		}

	}
	
	public void setOptions(java.util.Map<?,?> options) {
		defaultTokenResolver.setOptions(options);
	}
	
}
