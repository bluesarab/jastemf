/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf.refactorings;

import java.io.*;
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

public class RefactoringManager {
	
	/**
	 * Executes the refactoring script under the given absolute URI.
	 * 
	 * @param scriptURI
	 * @throws JastEMFException
	 */
	public static void applyRefactoringScript(URI scriptURI)
			throws JastEMFException {
		IRefactoringHistoryService historyService = RefactoringCore
				.getHistoryService();
		try {
			RefactoringHistory history = historyService.readRefactoringHistory(
					scriptURI.toURL().openStream(), 0);
			PerformRefactoringHistoryOperation operation = new PerformRefactoringHistoryOperation(
					history);
			operation.run(new NullProgressMonitor());
			RefactoringStatus status = operation.getExecutionStatus();
			for (RefactoringStatusEntry entry : status.getEntries()) {
				System.out.println(entry.toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new JastEMFException(e);
		} catch (CoreException e) {
			e.printStackTrace();
			throw new JastEMFException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JastEMFException(e);
		}
	}
	
	/**
	 * <p>Executes necessary manual adaptations on the JastAdd generated AST classes to prepare 
	 * them for the merge. Each AST class is expected to have exactly one corresponding 
	 * EClass in exactly one EPackage referenced by the GenModel which is provided by the 
	 * given IIntegrationContext, i.e., there is a 1:1 mapping between AST and ECore classes.</p>  
	 * The following adaptations are conducted:
	 * <ul>
	 *		<li>Each generated JastAdd class is updated to implement its
	 *			associated EMF interface.</li>
	 *		<li>Additionally: see {@link BasicJDTASTVisitor}</li>
	 * </ul>
	 * @param context The current generation context.
	 * @throws JastEMFException If some IO error occurs or a rewrite cannot be applied. 
	 */
	public static void performASTClassesAdaptations(
			final IIntegrationContext context) throws JastEMFException {

		for (final GenPackage genPackage : context.genmodel().getGenPackages()) {
			for (final GenClass genClass : genPackage.getGenClasses()) {
				if (genClass.isInterface())
					continue;
				final IFile compilationUnitFile = IOSupport.getFile(
						context.classfolder(genPackage),
						genClass.getClassName() + ".java");
				
				final CompilationUnit compilationUnit = JDTSupport.loadCompilationUnit(compilationUnitFile);
				compilationUnit.recordModifications();

				ASTVisitor visitor = new BasicJDTASTVisitor(context) {
					@SuppressWarnings("unchecked")
					public boolean visit(TypeDeclaration decl) {
						System.out.println("Visiting type:"+decl.getName());
						if (decl.isInterface())
							return false;
						//Setting generated interface as super interface
						if (decl.getName().getIdentifier().equals(
								genClass.getClassName())) {
							AST ast = decl.getAST();
							List interfaces = (List) decl
									.getStructuralProperty(TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY);
							interfaces.add(ast.newSimpleType(ast
									.newName(genClass.getQualifiedInterfaceName())));
							return true;
						}
						return false;
					}
				};

				compilationUnit.accept(visitor);

				JDTSupport.applyRewritesAndSave(compilationUnit, compilationUnitFile);
			}
		}
	}
	


	
	
	/**
	 * //TODO Christoff: Ich würde vorschlagen, dass ebenfalls mit copycats zu machen, wie in der vorhergehenden Methode vorgeschlagen.
	 * 			Christoff: Argh, JastAdd unterstützt keine annotierten intertypedeclarations für Felder. Ich habe mal an Tobjörn Ekman einen Bugfix/Featurerequest geschrieben ... mal sehen ob das schnell gefixt wird
	 * 
	 * <p>Executes necessary manual adaptations of the JastAdd generated ASTNode$State.java 
	 * which are required because of moving the generated AST classes to their ECore GenPackage
	 * targets.</p> 
	 * The following adaptations are conducted:
	 * <ul>
	 * 		<li>Protected fields are made public. Currently these
	 * 			 are ASTNode$State.duringInterpretation,
	 * 				ASTNode$State.CircularValue.value,
	 * 				ASTNode$State.CircularValue.visited</li>
	 * 		<li>Additionally: see {@link BasicJDTASTVisitor}</li>
	 * </ul>
	 * 
	 * @param context The current generation context.
	 * @throws JastEMFException JastEMFException If some IO error occurs or a rewrite cannot be applied.
	 */	
	public static void performASTNodeStateAdaptations(
			IIntegrationContext context) throws JastEMFException {
		final IFile compilationUnitFile = IOSupport.getFile(context.astfolder(),
				"ASTNode$State.java");
		final CompilationUnit compilationUnit = JDTSupport.loadCompilationUnit(compilationUnitFile);
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
							modifier
									.setKeyword(Modifier.ModifierKeyword.PUBLIC_KEYWORD);
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
				if("IdentityHashSet".equals(decl.getName().getIdentifier())){
					Modifier modifier = JDTSupport.findModifier(decl,ModifierKeyword.PROTECTED_KEYWORD);
					if(modifier!=null){
						modifier.setKeyword(ModifierKeyword.PUBLIC_KEYWORD);
					}
					else{
						Modifier newModifier = decl.getAST().newModifier(ModifierKeyword.PUBLIC_KEYWORD);
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
	 * <p>Executes necessary manual adaptations of the JastAdd generated ASTNode class.</p> 
	 * The following adaptations are conducted:
	 * <ul>
	 * 		<li>EObjectImpl is set as super type.</li>
	 * 		<li>Changes visibility of ASTNode.childIndex from private to public</li>
	 * 		<li>Deletes the children array</li>
	 * 		<li>Additionally: see {@link BasicJDTASTVisitor}</li>
	 * </ul>
	 * 
	 * @param context The current generation context.
	 * @throws JastEMFException JastEMFException If some IO error occurs or a rewrite cannot be applied.
	 */	
	public static void performASTNodeAdaptations(final IIntegrationContext context)
			throws JastEMFException {
		final IFile compilationUnitFile = IOSupport.getFile(context.astfolder(),
				"ASTNode.java");
		final CompilationUnit compilationUnit = JDTSupport.loadCompilationUnit(compilationUnitFile);
		compilationUnit.recordModifications();
		
		ASTVisitor visitor = new BasicJDTASTVisitor(context) {
			public boolean visit(TypeDeclaration decl) {
				AST ast = decl.getAST();
				if (!decl.isInterface()) {
					if ("ASTNode".equals(decl.getName().getIdentifier())) {
						decl.setSuperclassType(ast.newSimpleType(ast.newName(
								"org.eclipse.emf.ecore.impl.EObjectImpl")));
						return true;
					}
				}			
				return false;	
			}
	
			public boolean visit(VariableDeclarationFragment declFragment) {
				if("childIndex".equals(declFragment.getName().getIdentifier())){
					FieldDeclaration decl = (FieldDeclaration)declFragment.getParent();
					for(Object o:decl.modifiers()){
						Modifier modifier = (Modifier)o;
						if(modifier.isPrivate()){
							modifier.setKeyword(Modifier.ModifierKeyword.PUBLIC_KEYWORD);
							break;
						}
					}
				}
				else if(("children").equals(declFragment.getName().getIdentifier())){
					FieldDeclaration decl = (FieldDeclaration)declFragment.getParent();
					decl.delete();
				}
				return false;
			}
		};
		
		compilationUnit.accept(visitor);

		JDTSupport.applyRewritesAndSave(compilationUnit, compilationUnitFile);
	}
	
	/**
	 * Performs basic adaptations of the JastAdd List class. See {@link BasicJDTASTVisitor}.
	 * 
	 * @param context The current generation context.
	 * @throws JastEMFException JastEMFException If some IO error occurs or a rewrite cannot be applied.
	 */
	public static void performASTListAdaptations(final IIntegrationContext context)
	throws JastEMFException {
		final IFile compilationUnitFile = IOSupport.getFile(context.astfolder(),
		"ASTList.java");
		final CompilationUnit compilationUnit = JDTSupport.loadCompilationUnit(compilationUnitFile);
		compilationUnit.recordModifications();
		ASTVisitor visitor = new BasicJDTASTVisitor(context) {};
		compilationUnit.accept(visitor);
		JDTSupport.applyRewritesAndSave(compilationUnit, compilationUnitFile);
	}

	/**
	 * Performs basic adaptations of the JastAdd OPT class. See {@link BasicJDTASTVisitor}.
	 * 
	 * @param context The current generation context.
	 * @throws JastEMFException JastEMFException If some IO error occurs or a rewrite cannot be applied.
	 */
	public static void performOptAdaptations(final IIntegrationContext context)
	throws JastEMFException {
		final IFile compilationUnitFile = IOSupport.getFile(context.astfolder(),
		"Opt.java");
		final CompilationUnit compilationUnit = JDTSupport.loadCompilationUnit(compilationUnitFile);
		compilationUnit.recordModifications();
		ASTVisitor visitor = new BasicJDTASTVisitor(context) {};
		compilationUnit.accept(visitor);
		JDTSupport.applyRewritesAndSave(compilationUnit, compilationUnitFile);
	}

	/**
	 * Runs the code formatter on all merged classes. 
	 * 
	 * @param context The current generation context.
	 * @throws JastEMFException JastEMFException If some IO error occurs or a rewrite cannot be applied.
	 */
	public static void beautifyGenPackages(IIntegrationContext context)throws JastEMFException{
		GenModel genModel = context.genmodel();
		for(GenPackage genPackage:genModel.getGenPackages()){
			URI genPackageURI = context.classfolder(genPackage);
			for(GenClass genClass:genPackage.getGenClasses()){
				IFile javaFile = IOSupport.getFile(genPackageURI,genClass.getClassName() + ".java");
				ICompilationUnit compilationUnitDescriptor = JavaCore.createCompilationUnitFrom(javaFile);
				try {
					String formattedContent = JDTSupport.formatJavaCompilationUnit(compilationUnitDescriptor.getSource());
					IOSupport.save(formattedContent,javaFile);
				} catch (Exception e){
					e.printStackTrace();
					throw new JastEMFException(e);
				}
			}
		}
	}
}
