/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstBracketInformationProvider {
	
	public class BracketPair implements org.jastemf.spec.ast.resource.ast.IAstBracketPair {
		
		private String opening;
		private String closing;
		private boolean closingEnabledInside;
		
		public BracketPair(String opening, String closing, boolean closingEnabledInside) {
			super();
			this.opening = opening;
			this.closing = closing;
			this.closingEnabledInside = closingEnabledInside;
		}
		
		public String getOpeningBracket() {
			return opening;
		}
		
		public String getClosingBracket() {
			return closing;
		}
		
		public boolean isClosingEnabledInside() {
			return closingEnabledInside;
		}
	}
	
	public java.util.Collection<org.jastemf.spec.ast.resource.ast.IAstBracketPair> getBracketPairs() {
		java.util.Collection<org.jastemf.spec.ast.resource.ast.IAstBracketPair> result = new java.util.ArrayList<org.jastemf.spec.ast.resource.ast.IAstBracketPair>();
		result.add(new BracketPair("<", ">", true));
		result.add(new BracketPair("[", "]", true));
		return result;
	}
	
}
