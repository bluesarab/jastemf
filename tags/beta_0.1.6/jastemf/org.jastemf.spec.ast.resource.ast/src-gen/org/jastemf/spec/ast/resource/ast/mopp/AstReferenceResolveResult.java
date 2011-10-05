/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

/**
 * A basic implementation of the
 * org.jastemf.spec.ast.resource.ast.IAstReferenceResolveResult interface that
 * collects mappings in a list.
 * 
 * @param <ReferenceType> the type of the references that can be contained in this
 * result
 */
public class AstReferenceResolveResult<ReferenceType> implements org.jastemf.spec.ast.resource.ast.IAstReferenceResolveResult<ReferenceType> {
	
	private java.util.Collection<org.jastemf.spec.ast.resource.ast.IAstReferenceMapping<ReferenceType>> mappings;
	private String errorMessage;
	private boolean resolveFuzzy;
	
	public AstReferenceResolveResult(boolean resolveFuzzy) {
		super();
		this.resolveFuzzy = resolveFuzzy;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public java.util.Collection<org.jastemf.spec.ast.resource.ast.IAstReferenceMapping<ReferenceType>> getMappings() {
		return mappings;
	}
	
	public boolean wasResolved() {
		return mappings != null;
	}
	
	public boolean wasResolvedMultiple() {
		return mappings != null && mappings.size() > 1;
	}
	
	public boolean wasResolvedUniquely() {
		return mappings != null && mappings.size() == 1;
	}
	
	public void setErrorMessage(String message) {
		errorMessage = message;
	}
	
	public void addMapping(String identifier, ReferenceType target) {
		if (!resolveFuzzy && target == null) {
			throw new IllegalArgumentException("Mapping references to null is only allowed for fuzzy resolution.");
		}
		addMapping(identifier, target, null);
	}
	
	public void addMapping(String identifier, ReferenceType target, String warning) {
		if (mappings == null) {
			mappings = new java.util.ArrayList<org.jastemf.spec.ast.resource.ast.IAstReferenceMapping<ReferenceType>>();
		}
		mappings.add(new org.jastemf.spec.ast.resource.ast.mopp.AstElementMapping<ReferenceType>(identifier, target, warning));
		errorMessage = null;
	}
	
	public void addMapping(String identifier, org.eclipse.emf.common.util.URI uri) {
		addMapping(identifier, uri, null);
	}
	
	public void addMapping(String identifier, org.eclipse.emf.common.util.URI uri, String warning) {
		if (mappings == null) {
			mappings = new java.util.ArrayList<org.jastemf.spec.ast.resource.ast.IAstReferenceMapping<ReferenceType>>();
		}
		mappings.add(new org.jastemf.spec.ast.resource.ast.mopp.AstURIMapping<ReferenceType>(identifier, uri, warning));
	}
}
