/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast;

/**
 * A simple interface for commands that can be executed and that return
 * information about the success of their execution.
 */
public interface IAstCommand<ContextType> {
	
	public boolean execute(ContextType context);
}
