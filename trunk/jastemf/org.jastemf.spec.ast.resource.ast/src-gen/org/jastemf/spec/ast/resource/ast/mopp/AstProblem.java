/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstProblem implements org.jastemf.spec.ast.resource.ast.IAstProblem {
	
	private java.lang.String message;
	private org.jastemf.spec.ast.resource.ast.AstEProblemType type;
	
	public AstProblem(java.lang.String message, org.jastemf.spec.ast.resource.ast.AstEProblemType type) {
		super();
		this.message = message;
		this.type = type;
	}
	
	public org.jastemf.spec.ast.resource.ast.AstEProblemType getType() {
		return type;
	}
	
	public java.lang.String getMessage() {
		return message;
	}
	
}
