/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstParseResult implements org.jastemf.spec.ast.resource.ast.IAstParseResult {
	
	private org.eclipse.emf.ecore.EObject root;
	private java.util.Collection<org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>> commands = new java.util.ArrayList<org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>>();
	
	public AstParseResult() {
		super();
	}
	
	public void setRoot(org.eclipse.emf.ecore.EObject root) {
		this.root = root;
	}
	
	public org.eclipse.emf.ecore.EObject getRoot() {
		return root;
	}
	
	public java.util.Collection<org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>> getPostParseCommands() {
		return commands;
	}
	
}
