/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast;

public interface IAstReferenceCache {
	public java.lang.Object get(java.lang.String identifier);
	public void put(java.lang.String identifier, java.lang.Object target);
}
