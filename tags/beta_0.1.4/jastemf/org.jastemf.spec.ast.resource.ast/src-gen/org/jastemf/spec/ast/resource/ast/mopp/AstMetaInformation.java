/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstMetaInformation implements org.jastemf.spec.ast.resource.ast.IAstMetaInformation {
	
	public java.lang.String getSyntaxName() {
		return "ast";
	}
	
	public java.lang.String getURI() {
		return "http://www.jastemf.org/spec/ast";
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTextScanner createLexer() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstAntlrScanner(new org.jastemf.spec.ast.resource.ast.mopp.AstLexer());
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTextParser createParser(java.io.InputStream inputStream, java.lang.String encoding) {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstParser().createInstance(inputStream, encoding);
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTextPrinter createPrinter(java.io.OutputStream outputStream, org.jastemf.spec.ast.resource.ast.IAstTextResource resource) {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstPrinter2(outputStream, resource);
	}
	
	public org.eclipse.emf.ecore.EClass[] getClassesWithSyntax() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstSyntaxCoverageInformationProvider().getClassesWithSyntax();
	}
	
	public org.eclipse.emf.ecore.EClass[] getStartSymbols() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstSyntaxCoverageInformationProvider().getStartSymbols();
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstReferenceResolverSwitch getReferenceResolverSwitch() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstReferenceResolverSwitch();
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTokenResolverFactory getTokenResolverFactory() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstTokenResolverFactory();
	}
	
	public java.lang.String getPathToCSDefinition() {
		return "org.jastemf.spec.ast/specifications/syntax/ast.cs";
	}
	
	public java.lang.String[] getTokenNames() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstParser(null).getTokenNames();
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTokenStyle getDefaultTokenStyle(java.lang.String tokenName) {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstTokenStyleInformationProvider().getDefaultTokenStyle(tokenName);
	}
	
	public java.util.Collection<org.jastemf.spec.ast.resource.ast.IAstBracketPair> getBracketPairs() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstBracketInformationProvider().getBracketPairs();
	}
	
	public org.eclipse.emf.ecore.EClass[] getFoldableClasses() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstFoldingInformationProvider().getFoldableClasses();
	}
	
	public org.eclipse.emf.ecore.resource.Resource.Factory createResourceFactory() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstResourceFactory();
	}
	
	public org.jastemf.spec.ast.resource.ast.mopp.AstNewFileContentProvider getNewFileContentProvider() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstNewFileContentProvider();
	}
	
}
