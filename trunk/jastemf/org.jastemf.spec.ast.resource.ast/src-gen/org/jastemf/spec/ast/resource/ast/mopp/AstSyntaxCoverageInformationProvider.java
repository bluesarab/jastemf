/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstSyntaxCoverageInformationProvider {
	
	public org.eclipse.emf.ecore.EClass[] getClassesWithSyntax() {
		return new org.eclipse.emf.ecore.EClass[] {
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getGrammar(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getASTDecl(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getListComponents(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getListComponentsNTA(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getTokenComponent(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getTokenComponentNTA(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getOptionalComponent(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getOptionalComponentNTA(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getAggregateComponents(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getAggregateComponentsNTA(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getAbstract(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getId(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getNameNode(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getTokenId(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getIdUse(),
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getIdDecl(),
		};
	}
	
	public org.eclipse.emf.ecore.EClass[] getStartSymbols() {
		return new org.eclipse.emf.ecore.EClass[] {
			org.jastemf.spec.ast.AstPackage.eINSTANCE.getGrammar(),
		};
	}
	
}
