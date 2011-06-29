/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

/**
 * A factory for ContextDependentURIFragments. Given a feasible reference
 * resolver, this factory returns a matching fragment that used the resolver to
 * resolver proxy objects.
 * 
 * @param <ContainerType> the type of the class containing the reference to be
 * resolved
 * @param <ReferenceType> the type of the reference to be resolved
 */
public class AstContextDependentURIFragmentFactory<ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject>  implements org.jastemf.spec.ast.resource.ast.IAstContextDependentURIFragmentFactory<ContainerType, ReferenceType> {
	
	private final org.jastemf.spec.ast.resource.ast.IAstReferenceResolver<ContainerType, ReferenceType> resolver;
	
	public AstContextDependentURIFragmentFactory(org.jastemf.spec.ast.resource.ast.IAstReferenceResolver<ContainerType, ReferenceType> resolver) {
		this.resolver = resolver;
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstContextDependentURIFragment<?> create(String identifier, ContainerType container, org.eclipse.emf.ecore.EReference reference, int positionInReference, org.eclipse.emf.ecore.EObject proxy) {
		
		return new org.jastemf.spec.ast.resource.ast.mopp.AstContextDependentURIFragment<ContainerType, ReferenceType>(identifier, container, reference, positionInReference, proxy) {
			public org.jastemf.spec.ast.resource.ast.IAstReferenceResolver<ContainerType, ReferenceType> getResolver() {
				return resolver;
			}
		};
	}
}
