/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast;

/**
 * Implementors of this interface can provide a post-processor for text resources.
 */
public interface IAstResourcePostProcessorProvider {
	
	/**
	 * Returns the processor that shall be called after text resource are successfully
	 * parsed.
	 */
	public org.jastemf.spec.ast.resource.ast.IAstResourcePostProcessor getResourcePostProcessor();
	
}
