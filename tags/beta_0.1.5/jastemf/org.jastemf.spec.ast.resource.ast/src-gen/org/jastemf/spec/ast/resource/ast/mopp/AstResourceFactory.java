/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstResourceFactory implements org.eclipse.emf.ecore.resource.Resource.Factory {
	
	public AstResourceFactory() {
		super();
	}
	
	public org.eclipse.emf.ecore.resource.Resource createResource(org.eclipse.emf.common.util.URI uri) {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstResource(uri);
	}
}
