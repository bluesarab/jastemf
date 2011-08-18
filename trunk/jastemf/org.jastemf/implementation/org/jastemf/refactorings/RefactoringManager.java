/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf.refactorings;

import java.net.*;
import java.util.*;

import org.eclipse.ltk.core.refactoring.history.*;
import org.eclipse.ltk.core.refactoring.*;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import org.jastemf.*;
import org.jastemf.util.*;

/**
 * The refactoring manager supports methods to execute <i>JDT</i> refactoring
 * scripts, to process <i>JastEMF</i> source code annotations and to perform
 * generic -- i.e. concrete integration project independent -- adaptations of
 * classes JastAdd always generates (<tt>ASTNode, List, Opt</tt>).
 * 
 * @author Sven Karol
 * 
 */
public class RefactoringManager {

	/**
	 * Execute a <i>JDT</i> refactoring script located at a certain URI.
	 * 
	 * @param scriptURI
	 *            The refactoring script's URI.
	 * @throws JastEMFException
	 *             Thrown, iff the script cannot be accessed.
	 */
	public static void applyRefactoringScript(URI scriptURI)
			throws JastEMFException {
		IRefactoringHistoryService historyService = RefactoringCore
				.getHistoryService();
		PerformRefactoringHistoryOperation operation = null;
		try {
			RefactoringHistory history = historyService.readRefactoringHistory(
					scriptURI.toURL().openStream(), 0);
			operation = new PerformRefactoringHistoryOperation(
					history);

			operation.run(new NullProgressMonitor());
		} catch (Exception e) {
			e.printStackTrace();
			throw new JastEMFException(e);
		}
		finally{
			if(operation!=null){
				RefactoringStatus status = operation.getExecutionStatus();
				for (RefactoringStatusEntry entry : status.getEntries()) {
					IOSupport.log(entry.toString());
				}
				
			}
		}
	}

	/**
	 * <p>
	 * Execute adaptations of the JastAdd generated AST classes to prepare them
	 * for their merge with the metamodel implementation. Each AST class is
	 * expected to have exactly one corresponding <tt>EClass</tt> in exactly one
	 * <tt>EPackage</tt> in the generator model provided by the given
	 * {@link IIntegrationContext integration context}, i.e., there is a 1:1
	 * mapping between AST and <i>Ecore</i> classes.
	 * </p>
	 * The following adaptations are conducted:
	 * <ul>
	 * <li>Each generated JastAdd class is updated to implement its associated
	 * EMF interface.</li>
	 * <li>Additionally: See {@link BasicJDTASTVisitor}</li>
	 * </ul>
	 * 
	 * @param context
	 *            The current integration context.
	 * @throws JastEMFException
	 *             Thrown, Iff an IO error occurs or an adaptation cannot be
	 *             applied.
	 */
	public static void performASTClassesAdaptations(
			final IIntegrationContext context) throws JastEMFException {

		for (final GenPackage genPackage : context.genmodel().getGenPackages()) {
			performASTClassesAdaptations(context, genPackage);
		}
	}

	private static void performASTClassesAdaptations(
			final IIntegrationContext context, GenPackage genPackage)
			throws JastEMFException {
		for (final GenClass genClass : genPackage.getGenClasses()) {
			if (genClass.isInterface())
				continue;
			final IFile compilationUnitFile = IOSupport.getFile(
					context.classfolder(genPackage), genClass.getClassName()
							+ ".java");

			final CompilationUnit compilationUnit = JDTSupport
					.loadCompilationUnit(compilationUnitFile);
			compilationUnit.recordModifications();

			ASTVisitor visitor = new BasicJDTASTVisitor(context) {
				@SuppressWarnings("unchecked")
				public boolean visit(TypeDeclaration decl) {
					IOSupport.log("Visiting " + decl.getName() + " ...");
					if (decl.isInterface())
						return false;
					// Setting generated interface as super interface
					if (decl.getName().getIdentifier()
							.equals(genClass.getClassName())) {
						AST ast = decl.getAST();
						List interfaces = (List) decl
								.getStructuralProperty(TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY);
						interfaces.add(ast.newSimpleType(ast.newName(genClass
								.getQualifiedInterfaceName())));
						return true;
					}
					return false;
				}
			};

			compilationUnit.accept(visitor);

			JDTSupport.applyRewritesAndSave(compilationUnit,
					compilationUnitFile);
		}
		for (GenPackage childPackage : genPackage.getNestedGenPackages()) {
			performASTClassesAdaptations(context, childPackage);
		}

	}

	/**
	 * <p>
	 * Execute adaptations of the <tt>ASTNode$State</tt> class JastAdd
	 * generates. Such adaptations are required because generated AST classes
	 * are moved throughout the integration process into different packages,
	 * which results in shadowed <tt>ASTNode$State</tt> fields and methods.
	 * </p>
	 * The following adaptations are conducted:
	 * <ul>
	 * <li>Protected fields are made public. Currently these are
	 * ASTNode$State.duringInterpretation, ASTNode$State.CircularValue.value,
	 * ASTNode$State.CircularValue.visited</li>
	 * <li>Additionally: See {@link BasicJDTASTVisitor}</li>
	 * </ul>
	 * 
	 * @param context
	 *            The current integration context.
	 * @throws JastEMFException
	 *             Thrown, iff an IO error occurs or an adaptation cannot be
	 *             applied.
	 */
	public static void performASTNodeStateAdaptations(
			IIntegrationContext context) throws JastEMFException {
		final IFile compilationUnitFile = IOSupport.getFile(
				context.astfolder(), "ASTNode$State.java");
		final CompilationUnit compilationUnit = JDTSupport
				.loadCompilationUnit(compilationUnitFile);
		compilationUnit.recordModifications();
		ASTVisitor visitor = new BasicJDTASTVisitor(context) {
			@SuppressWarnings("unchecked")
			public boolean visit(FieldDeclaration decl) {
				super.visit(decl);
				AST ast = decl.getAST();
				// making protected fields duringInterpretation,value,visited
				// and all others (!) public

				TypeDeclaration typeDecl = (TypeDeclaration) decl.getParent();
				String ident = typeDecl.getName().getIdentifier();
				if (("ASTNode$State").equals(ident)
						|| ("CircularValue").equals(ident)) {
					ListIterator<?> it = decl.modifiers().listIterator();
					boolean isPublicOrPrivate = false;
					while (it.hasNext()) {
						Modifier modifier = (Modifier) it.next();

						if (modifier.isPublic() || modifier.isPrivate()) {
							isPublicOrPrivate = true;
							break;
						}
						if (modifier.isProtected()) {
							modifier.setKeyword(Modifier.ModifierKeyword.PUBLIC_KEYWORD);
							isPublicOrPrivate = true;
							break;
						}
					}
					if (!isPublicOrPrivate) {
						Modifier newModifier = ast
								.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD);
						decl.modifiers().add(newModifier);
					}
				}
				return false;
			}

			@SuppressWarnings("unchecked")
			public boolean visit(TypeDeclaration decl) {
				if ("IdentityHashSet".equals(decl.getName().getIdentifier())) {
					Modifier modifier = JDTSupport.findModifier(decl,
							ModifierKeyword.PROTECTED_KEYWORD);
					if (modifier != null) {
						modifier.setKeyword(ModifierKeyword.PUBLIC_KEYWORD);
					} else {
						Modifier newModifier = decl.getAST().newModifier(
								ModifierKeyword.PUBLIC_KEYWORD);
						decl.modifiers().add(newModifier);
					}

				}
				return true;
			}
		};

		compilationUnit.accept(visitor);

		JDTSupport.applyRewritesAndSave(compilationUnit, compilationUnitFile);
	}

	/**
	 * <p>
	 * Execute adaptations of the <tt>ASTNode</tt> class JastAdd generates.
	 * </p>
	 * The following adaptations are conducted:
	 * <ul>
	 * <li><tt>EObjectImpl</tt> is set as super type.</li>
	 * <li>Change visibility of <tt>ASTNode.childIndex</tt> from private to
	 * public.</li>
	 * <li>Delete the children array.</li>
	 * <li>Additionally: See {@link BasicJDTASTVisitor}</li>
	 * </ul>
	 * 
	 * @param context
	 *            The current integration context.
	 * @throws JastEMFException
	 *             Thrown, iff an IO error occurs or an adaptation cannot be
	 *             applied.
	 */
	public static void performASTNodeAdaptations(
			final IIntegrationContext context) throws JastEMFException {
		final IFile compilationUnitFile = IOSupport.getFile(
				context.astfolder(), "ASTNode.java");
		final CompilationUnit compilationUnit = JDTSupport
				.loadCompilationUnit(compilationUnitFile);
		compilationUnit.recordModifications();

		ASTVisitor visitor = new BasicJDTASTVisitor(context) {
			public boolean visit(TypeDeclaration decl) {
				AST ast = decl.getAST();
				if (!decl.isInterface()) {
					if ("ASTNode".equals(decl.getName().getIdentifier())) {
						decl.setSuperclassType(ast.newSimpleType(ast
								.newName("org.eclipse.emf.ecore.impl.EObjectImpl")));
						return true;
					}
				}
				return false;
			}

			public boolean visit(VariableDeclarationFragment declFragment) {

				if ("childIndex".equals(declFragment.getName().getIdentifier())) {
					FieldDeclaration decl = null;
					if (declFragment.getParent() instanceof FieldDeclaration) {
						decl = (FieldDeclaration) declFragment.getParent();
					} else {
						//This is just a debug information for users.
						IOSupport.warn("Declaration of childIndex is not a FieldDeclaration but a "
										+ declFragment.getParent().getClass()
												.getName()
										+ ". This may be caused by a declaration of a local variable with the same name. " +
												"Ignoring this declaration.");
						return false;
					}

					for (Object o : decl.modifiers()) {
						Modifier modifier = (Modifier) o;
						if (modifier.isPrivate()) {
							modifier.setKeyword(Modifier.ModifierKeyword.PUBLIC_KEYWORD);
							break;
						}
					}
				} else if (("children").equals(declFragment.getName()
						.getIdentifier())) {
					FieldDeclaration decl = (FieldDeclaration) declFragment
							.getParent();
					decl.delete();
				}
				return false;
			}
		};

		IOSupport.log("Visiting ASTNode ...");
		compilationUnit.accept(visitor);

		JDTSupport.applyRewritesAndSave(compilationUnit, compilationUnitFile);
	}

	/**
	 * Perform adaptations of the <tt>List</tt> class JastAdd generates. See
	 * {@link BasicJDTASTVisitor}.
	 * 
	 * @param context
	 *            The current integration context.
	 * @throws JastEMFException
	 *             Thrown, iff an IO error occurs or an adaptation cannot be
	 *             applied.
	 */
	public static void performASTListAdaptations(
			final IIntegrationContext context) throws JastEMFException {
		final IFile compilationUnitFile = IOSupport.getFile(
				context.astfolder(), "ASTList.java");
		final CompilationUnit compilationUnit = JDTSupport
				.loadCompilationUnit(compilationUnitFile);
		compilationUnit.recordModifications();
		ASTVisitor visitor = new BasicJDTASTVisitor(context) {
		};
		IOSupport.log("Visiting ASTList ...");
		compilationUnit.accept(visitor);
		JDTSupport.applyRewritesAndSave(compilationUnit, compilationUnitFile);
	}

	/**
	 * Performs adaptations of the <tt>Opt</tt> class JastAdd generates. See
	 * {@link BasicJDTASTVisitor}.
	 * 
	 * @param context
	 *            The current integration context.
	 * @throws JastEMFException
	 *             Thrown, iff an IO error occurs or an adaptation cannot be
	 *             applied.
	 */
	public static void performOptAdaptations(final IIntegrationContext context)
			throws JastEMFException {
		final IFile compilationUnitFile = IOSupport.getFile(
				context.astfolder(), "Opt.java");
		final CompilationUnit compilationUnit = JDTSupport
				.loadCompilationUnit(compilationUnitFile);
		compilationUnit.recordModifications();
		ASTVisitor visitor = new BasicJDTASTVisitor(context) {
		};
		compilationUnit.accept(visitor);
		JDTSupport.applyRewritesAndSave(compilationUnit, compilationUnitFile);
	}

	/**
	 * Format the code of all classes declared in the integration context's
	 * generator model.
	 * 
	 * @param context
	 *            The current integration context.
	 * @throws JastEMFException
	 *             JastEMFException If an IO error occurs.
	 */
	public static void beautifyGenPackages(IIntegrationContext context)
			throws JastEMFException {
		GenModel genModel = context.genmodel();
		for (GenPackage genPackage : genModel.getGenPackages()) {
			beautifyGenPackage(context, genPackage);
		}
	}

	private static void beautifyGenPackage(IIntegrationContext context,
			GenPackage genPackage) throws JastEMFException {
		URI genPackageURI = context.classfolder(genPackage);
		for (GenClass genClass : genPackage.getGenClasses()) {
			IFile javaFile = IOSupport.getFile(genPackageURI,
					genClass.getClassName() + ".java");
			ICompilationUnit compilationUnitDescriptor = JavaCore
					.createCompilationUnitFrom(javaFile);
			try {
				String formattedContent = JDTSupport
						.formatJavaCompilationUnit(compilationUnitDescriptor
								.getSource());
				IOSupport.save(formattedContent, javaFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new JastEMFException(e);
			}
		}
		for (GenPackage childPackage : genPackage.getNestedGenPackages())
			beautifyGenPackage(context, childPackage);
	}

}
