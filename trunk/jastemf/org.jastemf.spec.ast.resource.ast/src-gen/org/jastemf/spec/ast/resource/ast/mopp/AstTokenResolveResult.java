/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

/**
 * A basic implementation of the ITokenResolveResult interface.
 */
public class AstTokenResolveResult implements org.jastemf.spec.ast.resource.ast.IAstTokenResolveResult {
	
	private String errorMessage;
	private Object resolvedToken;
	
	public AstTokenResolveResult() {
		super();
		clear();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public Object getResolvedToken() {
		return resolvedToken;
	}
	
	public void setErrorMessage(String message) {
		errorMessage = message;
	}
	
	public void setResolvedToken(Object resolvedToken) {
		this.resolvedToken = resolvedToken;
	}
	
	public void clear() {
		errorMessage = "Can't resolve token.";
		resolvedToken = null;
	}
}
