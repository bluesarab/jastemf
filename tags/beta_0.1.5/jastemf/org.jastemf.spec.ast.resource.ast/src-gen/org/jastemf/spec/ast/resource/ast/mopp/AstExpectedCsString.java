/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

/**
 * A representation for a range in a document where a keyword (i.e., a static
 * string) is expected.
 */
public class AstExpectedCsString extends org.jastemf.spec.ast.resource.ast.mopp.AstAbstractExpectedElement {
	
	private org.jastemf.spec.ast.resource.ast.grammar.AstKeyword keyword;
	
	public AstExpectedCsString(org.jastemf.spec.ast.resource.ast.grammar.AstKeyword keyword) {
		super(keyword.getMetaclass());
		this.keyword = keyword;
	}
	
	public String getValue() {
		return keyword.getValue();
	}
	
	public String getTokenName() {
		return "'" + getValue() + "'";
	}
	
	public String toString() {
		return "CsString \"" + getValue() + "\"";
	}
	
	public boolean equals(Object o) {
		if (o instanceof AstExpectedCsString) {
			return getValue().equals(((AstExpectedCsString) o).getValue());
		}
		return false;
	}
	
}
