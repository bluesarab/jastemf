/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast;

/**
 * An interface used to access the result of parsing a document.
 */
public interface IAstParseResult {
	
	/**
	 * Returns the root object of the document.
	 */
	public org.eclipse.emf.ecore.EObject getRoot();
	
	/**
	 * Returns a list of commands that must be executed after parsing the document.
	 */
	public java.util.Collection<org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>> getPostParseCommands();
	
}
