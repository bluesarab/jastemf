package org.jastemf.refactorings;

import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.formatter.*;
import org.eclipse.jface.text.*;
import org.eclipse.text.edits.*;

import org.jastemf.*;
import org.jastemf.util.*;

public final class JDTSupport {
	/**
	 * Load a Java CompilationUnit from a given workspace file in a java project
	 * and set the project for resolving dependencies.
	 * @param javaFile The file to parse.
	 * @return The CompilationUnit representation of the parsed file.
	 * @throws JastEMFException
	 */
	public static CompilationUnit loadCompilationUnit(IFile javaFile)
			throws JastEMFException {
		ICompilationUnit compilationUnitDescriptor = JavaCore
				.createCompilationUnitFrom(javaFile);
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(compilationUnitDescriptor);
		parser.setProject(compilationUnitDescriptor.getJavaProject());
		parser.setResolveBindings(true);
		CompilationUnit compilationUnit = (CompilationUnit) parser
				.createAST(new NullProgressMonitor());
		return compilationUnit;
	}
	
	/**
	 * Parses a list of statements to a block. In the case the statement list is 
	 * empty or contains an error, null will be returned. If the block or parts of the 
	 * block are intended to be added to another AST, they have to be copied first.
	 * 
	 * @param statements
	 * @return a block containing AST representations of the given statement list in the string 
	 */
	public static Block parseBlock(String statements){
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(statements.toCharArray());
		parser.setResolveBindings(false);
		parser.setKind(ASTParser.K_STATEMENTS);
		Block block = (Block)parser.createAST(new NullProgressMonitor());	
		if(block.statements().isEmpty())
			return null;
		return block;
	}
	
	/**
	 * Parses a method declaration string to a MethodDeclaration. If the string contains
	 * a syntax error or is not a method null is returned.
	 * 
	 * @param method
	 * @return a method declaration AST representation of the given method declaration string 
	 */
	public static MethodDeclaration parseMethodDeclaration(String method){
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(method.toCharArray());
		parser.setResolveBindings(false);
		parser.setKind(ASTParser.K_CLASS_BODY_DECLARATIONS);
		TypeDeclaration td = (TypeDeclaration)parser.createAST(new NullProgressMonitor());
		MethodDeclaration[] mDecls = td.getMethods();
		if(mDecls.length==0)
			return null; 
		return mDecls[0];
	}

	/**
	 * Apply a CompilationUnit's pending rewrites and save the result to the
	 * given workspace file.
	 * @param compilationUnit The CompilationUnit with pending rewrites.
	 * @param file The target workspace file.
	 * @throws JastEMFException
	 */
	public static void applyRewritesAndSave(CompilationUnit compilationUnit,
			IFile file) throws JastEMFException {
		try {
			Document document = new Document(compilationUnit.getTypeRoot()
					.getSource());
			TextEdit edit = compilationUnit.rewrite(document, null);
			edit.apply(document);
			IOSupport.save(document.get(), file);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JastEMFException(e);
		}
	}

	/**
	 * Format the given Java 1.5 CompilationUnit.
	 * @param code The CompilationUnit code.
	 * @return The formatted String
	 * @throws MalformedTreeException
	 * @throws BadLocationException
	 */
	public static String formatJavaCompilationUnit(String code)
			throws MalformedTreeException, BadLocationException {
		Document document = new Document(code);
		Properties options = new Properties();
		options.put("org.eclipse.jdt.core.compiler.compliance", "1.5");
		options.put("org.eclipse.jdt.core.compiler.codegen.targetPlatform",
				"1.5");
		options.put("org.eclipse.jdt.core.compiler.source", "1.5");
		CodeFormatter formatter = ToolFactory.createCodeFormatter(options);
	
		TextEdit edit = formatter.format(CodeFormatter.K_COMPILATION_UNIT,
				code, 0, code.length(), 0, null);
		edit.apply(document);
		return document.get();
	}
	
	public static Modifier findModifier(BodyDeclaration methodDecl,
			Modifier.ModifierKeyword modifierKeyword){
		for(Object o:methodDecl.modifiers()){
			Modifier modifier = (Modifier)o;
			if(modifier.getKeyword() == modifierKeyword)
				return modifier;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Annotation findAnnotation(BodyDeclaration decl,
			String annotation) {
		for (IExtendedModifier mod : (List<IExtendedModifier>) decl.modifiers()) {
			if (mod.isAnnotation()) {
				Annotation ano = (Annotation) mod;
				if (ano.getTypeName().getFullyQualifiedName()
						.equals(annotation))
					return ano;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<MethodDeclaration> findTwinMethods(
			MethodDeclaration methodDecl) {
		List<MethodDeclaration> result = new LinkedList<MethodDeclaration>();
		TypeDeclaration classDecl = (TypeDeclaration) methodDecl.getParent();
		for (BodyDeclaration bodyDecl : (List<BodyDeclaration>) classDecl
				.bodyDeclarations()) {
			if (bodyDecl.getNodeType() == ASTNode.METHOD_DECLARATION
					&& bodyDecl != methodDecl) {
				MethodDeclaration maybeCopy = (MethodDeclaration) bodyDecl;
				if (maybeCopy.getName().getFullyQualifiedName().equals(
						methodDecl.getName().getFullyQualifiedName())
						&& maybeCopy.parameters().size() == methodDecl
								.parameters().size()) {
					Iterator<SingleVariableDeclaration> para1Iterator = methodDecl
							.parameters().iterator();
					for (SingleVariableDeclaration para2 : (List<SingleVariableDeclaration>) maybeCopy
							.parameters()) {
						SingleVariableDeclaration para1 = para1Iterator.next();
						boolean match = para2.getType().subtreeMatch(
								new ASTMatcher(), para1.getType());
						if (!match) {
							ITypeBinding para1Binding = para1.getType()
									.resolveBinding();
							ITypeBinding para2Binding = para2.getType()
									.resolveBinding();
							if (para1Binding != null && para2Binding != null
									&& para1Binding.getQualifiedName() != null)
								match = para1Binding.getQualifiedName().equals(
										para2Binding.getQualifiedName());
						}
						if (!match) {
							break;
						}
					}
					result.add(maybeCopy);
				}
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static List<VariableDeclarationFragment> findTwinFields(
			FieldDeclaration fieldDecl) {
		List<VariableDeclarationFragment> result = new LinkedList<VariableDeclarationFragment>();
		TypeDeclaration classDecl = (TypeDeclaration) fieldDecl.getParent();
		for (VariableDeclarationFragment ccVar : (List<VariableDeclarationFragment>) fieldDecl
				.fragments()) {
			for (BodyDeclaration bodyDecl : (List<BodyDeclaration>) classDecl
					.bodyDeclarations()) {
				if (bodyDecl.getNodeType() == ASTNode.FIELD_DECLARATION
						&& bodyDecl != fieldDecl) {
					FieldDeclaration fieldDeclCopy = (FieldDeclaration) bodyDecl;
					for (VariableDeclarationFragment ccVarCopy : (List<VariableDeclarationFragment>) fieldDeclCopy
							.fragments()) {
						if (ccVar.getName().getFullyQualifiedName().equals(
								ccVarCopy.getName())) {
							result.add(ccVarCopy);
						}
					}
				}
			}
		}
		return result;
	}
}
