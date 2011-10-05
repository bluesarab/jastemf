/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast;

public interface IAstBuilder {
	
	public boolean isBuildingNeeded(org.eclipse.emf.common.util.URI uri);
	
	public org.eclipse.core.runtime.IStatus build(org.jastemf.spec.ast.resource.ast.mopp.AstResource resource, org.eclipse.core.runtime.IProgressMonitor monitor);
}
