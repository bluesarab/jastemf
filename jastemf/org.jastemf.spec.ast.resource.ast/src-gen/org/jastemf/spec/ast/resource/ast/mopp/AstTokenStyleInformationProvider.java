/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstTokenStyleInformationProvider {
	
	public class TokenStyleImpl implements org.jastemf.spec.ast.resource.ast.IAstTokenStyle {
		
		private int[] color;
		private boolean bold;
		private boolean italic;
		private boolean strikethrough;
		private boolean underline;
		
		public TokenStyleImpl(int[] color, boolean bold, boolean italic, boolean striketrough, boolean underline) {
			super();
			this.color = color;
			this.bold = bold;
			this.italic = italic;
			this.strikethrough = striketrough;
			this.underline = underline;
		}
		
		public int[] getColorAsRGB() {
			return color;
		}
		
		public boolean isBold() {
			return bold;
		}
		
		public boolean isItalic() {
			return italic;
		}
		
		public boolean isStrikethrough() {
			return strikethrough;
		}
		
		public boolean isUnderline() {
			return underline;
		}
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTokenStyle getDefaultTokenStyle(java.lang.String tokenName) {
		if ("abstract".equals(tokenName)) {
			return new TokenStyleImpl(new int[] {0x7a, 0xF0, 0x05}, true, false, false, false);
		}
		if ("ML_COMMENT".equals(tokenName)) {
			return new TokenStyleImpl(new int[] {0x3F, 0x80, 0x5D}, false, false, false, false);
		}
		return null;
	}
	
}
