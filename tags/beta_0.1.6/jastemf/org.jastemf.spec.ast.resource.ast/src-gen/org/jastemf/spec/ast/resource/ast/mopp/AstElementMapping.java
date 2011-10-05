/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

/**
 * A basic implementation of the
 * org.jastemf.spec.ast.resource.ast.IAstElementMapping interface.
 * 
 * @param <ReferenceType> the type of the reference that can be mapped to
 */
public class AstElementMapping<ReferenceType> implements org.jastemf.spec.ast.resource.ast.IAstElementMapping<ReferenceType> {
	
	private final ReferenceType target;
	private String identifier;
	private String warning;
	
	public AstElementMapping(String identifier, ReferenceType target, String warning) {
		super();
		this.target = target;
		this.identifier = identifier;
		this.warning = warning;
	}
	
	public ReferenceType getTargetElement() {
		return target;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getWarning() {
		return warning;
	}
}
