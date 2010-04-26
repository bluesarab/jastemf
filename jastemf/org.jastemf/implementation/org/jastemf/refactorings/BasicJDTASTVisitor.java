package org.jastemf.refactorings;

import org.eclipse.jdt.core.dom.*;

import org.jastemf.*;

/**
 * Basic <i>JDT</i> AST visitor which realises common <i>JastEMF</i>
 * adaptations, especially the processing of its internal source code
 * annotations. In detail these are:<br/>
 * For each FieldDeclaration in a compilation unit<br/>
 * <ul>
 * <li>If the field has a <tt>CanibalicCopycat</tt> annotation, find a twin
 * field with the same name and type within the same TypeDeclaration and replace
 * it with the annotated field.</li>
 * <li>If the field has a <tt>SuicideCopycat</tt> annotation, find a twin field
 * with the same name and type within the same TypeDeclaration and delete it.
 * Furthermore, delete the annotated field.</li>
 * </ul>
 * For each MethodDeclaration in a compilation unit<br/>
 * <ul>
 * <li>If the method has a <tt>CanibalicCopycat</tt> annotation, find a twin
 * method with the same name and parameters within the same TypeDeclaration and
 * replace it with the annotated method.</li>
 * <li>If the method has a <tt>SuicideCopycat</tt> annotation, find a twin
 * method with the same name and type within the same TypeDeclaration and delete
 * it. Further more delete the annotated method.</li>
 * <li>
 * If the method has a <tt>MutatingMethod</tt> annotation, replace its method
 * body with the code given as argument of the annotation.</li>
 * <li>
 * Adaptation for collection attributes: If the method starts with a
 * <tt>contributeTo_</tt> prefix and its visibility is protected, its visibility
 * is changed to public.</li>
 * </ul>
 * 
 * @author S. Karol
 */
public class BasicJDTASTVisitor extends ASTVisitor {
	private IIntegrationContext context;

	public BasicJDTASTVisitor(IIntegrationContext context) {
		this.context = context;
	}

	/**
	 * See {@link BasicJDTASTVisitor introduction}.
	 */
	public boolean visit(MethodDeclaration methodDecl) {
		if (!handleCanibalicCopycatMethod(methodDecl, context)) {
			if (!handleSuicideCopycatMethod(methodDecl, context)) {
				if (!handleMutatingMethod(methodDecl, context)) {
					handleContributeToMethodVisibility(methodDecl, context);
				}
			}
		}
		return true;
	}

	/**
	 * See {@link BasicJDTASTVisitor introduction}.
	 */
	public boolean visit(FieldDeclaration decl) {
		if (!handleCanibalicCopycatField(decl, context)) {
			handleSuicideCopycatField(decl, context);
		}
		return true;
	}

	/** JastEMF-Annotation driven refactorings **/

	private static boolean handleMutatingMethod(MethodDeclaration methodDecl,
			IIntegrationContext context) {
		final String anoPrefix = context.outpackage() != null
				&& context.outpackage().length() > 0 ? context.outpackage()
				+ "." : "";
		Annotation ano = JDTSupport.findAnnotation(methodDecl, anoPrefix
				+ "JastEMFAnnotations.MutatingMethod");
		if (ano != null) {
			SingleMemberAnnotation nano = (SingleMemberAnnotation) ano;
			String value = nano.getValue().resolveConstantExpressionValue()
					.toString();
			Block block = JDTSupport.parseBlock(value);
			if (block != null) {
				Block blockCopy = (Block) ASTNode.copySubtree(methodDecl
						.getAST(), block);
				methodDecl.setBody(blockCopy);
				ano.delete();
				return true;
			} else {
				MethodDeclaration mDecl = JDTSupport
						.parseMethodDeclaration(value);
				if (mDecl != null) {
					MethodDeclaration mDeclCopy = (MethodDeclaration) ASTNode
							.copySubtree(methodDecl.getAST(), mDecl);
					TypeDeclaration parent = (TypeDeclaration) methodDecl
							.getParent();
					parent.bodyDeclarations().add(mDeclCopy);
					ano.delete();
					methodDecl.delete();
					return true;
				}
			}

		}
		return false;
	}

	private static boolean handleCanibalicCopycatMethod(
			MethodDeclaration methodDecl, IIntegrationContext context) {
		final String anoPrefix = context.outpackage() != null
				&& context.outpackage().length() > 0 ? context.outpackage()
				+ "." : "";
		Annotation ano = JDTSupport.findAnnotation(methodDecl, anoPrefix
				+ "JastEMFAnnotations.CannibalicCopycat");
		if (ano != null) {
			for (MethodDeclaration copy : JDTSupport
					.findTwinMethods(methodDecl))
				copy.delete();
			ano.delete();
			return true;
		}
		return false;
	}

	private static boolean handleCanibalicCopycatField(
			FieldDeclaration fieldDecl, IIntegrationContext context) {
		final String anoPrefix = context.outpackage() != null
				&& context.outpackage().length() > 0 ? context.outpackage()
				+ "." : "";
		Annotation ano = JDTSupport.findAnnotation(fieldDecl, anoPrefix
				+ "JastEMFAnnotations.CannibalicCopycat");
		if (ano != null) {
			for (VariableDeclarationFragment copyVar : JDTSupport
					.findTwinFields(fieldDecl)) {
				copyVar.delete();
				FieldDeclaration copy = (FieldDeclaration) copyVar.getParent();
				if (copy.fragments().isEmpty())
					copy.delete();
			}
			ano.delete();
			return true;
		}
		return false;
	}

	private static boolean handleSuicideCopycatMethod(
			MethodDeclaration methodDecl, IIntegrationContext context) {
		final String anoPrefix = context.outpackage() != null
				&& context.outpackage().length() > 0 ? context.outpackage()
				+ "." : "";
		Annotation ano = JDTSupport.findAnnotation(methodDecl, anoPrefix
				+ "JastEMFAnnotations.SuicideCopycat");
		if (ano != null) {
			for (MethodDeclaration copy : JDTSupport
					.findTwinMethods(methodDecl))
				copy.delete();
			methodDecl.delete();
			return true;
		}
		return false;
	}

	private static boolean handleSuicideCopycatField(
			FieldDeclaration fieldDecl, IIntegrationContext context) {
		final String anoPrefix = context.outpackage() != null
				&& context.outpackage().length() > 0 ? context.outpackage()
				+ "." : "";
		Annotation ano = JDTSupport.findAnnotation(fieldDecl, anoPrefix
				+ "JastEMFAnnotations.SuicideCopycat");
		if (ano != null) {
			for (VariableDeclarationFragment copyVar : JDTSupport
					.findTwinFields(fieldDecl)) {
				copyVar.delete();
				FieldDeclaration copy = (FieldDeclaration) copyVar.getParent();
				if (copy.fragments().isEmpty())
					copy.delete();
			}
			fieldDecl.delete();
			return true;
		}
		return false;
	}

	/** Default refactorings related to collection attributes **/

	private static boolean handleContributeToMethodVisibility(
			MethodDeclaration methodDecl, IIntegrationContext context) {
		if (methodDecl.getName().getIdentifier().startsWith("contributeTo_")) {
			Modifier modifier = JDTSupport.findModifier(methodDecl,
					Modifier.ModifierKeyword.PROTECTED_KEYWORD);
			if (modifier != null) {
				modifier.setKeyword(Modifier.ModifierKeyword.PUBLIC_KEYWORD);
				return true;
			}
		}
		return false;
	}
}
