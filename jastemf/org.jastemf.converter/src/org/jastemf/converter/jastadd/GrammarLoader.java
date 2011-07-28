package org.jastemf.converter.jastadd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ast.AST.ASTDecl;
import ast.AST.ASTNode;
import ast.AST.Ast;
import ast.AST.Components;
import ast.AST.Grammar;
import ast.AST.TokenComponent;

import jrag.AST.ASTCompilationUnit;
import jrag.AST.JragParser;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;

public class GrammarLoader {

	public static Grammar loadAstSpec(URI resource, final Collection<String> errors) {
		InputStream stream = null;
		String resourceName = resource.toString();
		URIConverter uriConverter = URIConverter.INSTANCE;
		try {
			stream = uriConverter.createInputStream(resource);
			Ast parser = new Ast(stream);
			parser.fileName = resourceName;
			Grammar g = parser.Grammar();
			for (Iterator<String> errorIter = parser.getErrors(); errorIter.hasNext();) {
				String[] s = errorIter.next().split(";");
				errors.add("Syntax error in " + parser.fileName + " at line "
						+ s[0] + ", column " + s[1]);
			}
			return g;

		} catch (ast.AST.TokenMgrError e) {
			errors.add(("Lexical error in " + resourceName + ": "
					+ e.getMessage()));
		} catch (ast.AST.ParseException e) {
			// Exceptions actually caught by error recovery in parser
		} catch (IOException e) {
			errors.add("File error: Abstract syntax grammar file "
					+ resourceName + " not found");
		} finally {
			if (stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					// do nothing
				}
		}
		return null;

	}
	
	public static ASTCompilationUnit loadJragSpec(URI resource,Grammar root,Collection<String> errors){
		InputStream inputStream = null;
		String resourceName = resource.toString();
		URIConverter uriConverter = URIConverter.INSTANCE;
		try {
			inputStream = uriConverter.createInputStream(resource);
			JragParser jp = new JragParser(inputStream);
			jp.inputStream = inputStream; // Hack to make input
											// stream visible for
											// ast-parser
			jp.root = root;
			jp.setFileName(resourceName);
			ASTCompilationUnit au = jp.CompilationUnit();
			return au;
			
		} catch (jrag.AST.ParseException e) {
			StringBuffer msg = new StringBuffer();
			msg.append("Syntax error in " + resourceName + " at line "
					+ e.currentToken.next.beginLine + ", column "
					+ e.currentToken.next.beginColumn);
			errors.add(msg.toString());
			return null;
		} catch (IOException e) {
			errors.add("File error: Aspect file "
					+ resourceName + " not found");
			return null;
		}
		finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					//do nothing
				}
			}
		}
	}

	public static Grammar loadJastAddGrammar(List<URI> files) {
		Grammar root = null;
		String grammarName = "test";
		boolean publicModifier = true;
		java.util.List cacheFiles = new LinkedList();

		
		try {
			long time = System.currentTimeMillis();

			root = new Grammar();
			root.abstractAncestors();

			// Parse ast-grammar
			Collection<String> errors = new LinkedList<String>();
			for (Iterator<URI> iter = files.iterator(); iter.hasNext();) {
				URI fileURI = iter.next();

				if (fileURI.toString().endsWith(".ast")) {
					Grammar astGrammar = loadAstSpec(fileURI,errors);
					if(errors.isEmpty()){
						for (int i = 0; i < astGrammar.getNumTypeDecl(); i++) {
							root.addTypeDecl(astGrammar.getTypeDecl(i));
						}
					}
				}
			}

			if (!errors.isEmpty()) {
				for (Iterator<String> iter = errors.iterator(); iter.hasNext();)
					System.out.println(iter.next());
				return null;
			}

			long astParseTime = System.currentTimeMillis() - time;

			String astErrors = root.astErrors();

			long astErrorTime = System.currentTimeMillis() - time
					- astParseTime;

			if (!astErrors.equals("")) {
				System.out.println("Semantic error:");
				System.out.println(astErrors);
				return null;
			}

			ASTNode.resetGlobalErrors();

			//Prints and re-parses the ASTNode type
			{
				java.io.StringWriter writer = new java.io.StringWriter();
				root.jjtGenASTNode$State(new PrintWriter(writer), grammarName,
						ASTNode.jjtree, ASTNode.rewriteEnabled);

				jrag.AST.JragParser jp = new jrag.AST.JragParser(
						new java.io.StringReader(writer.toString()));
				jp.root = root;
				jp.setFileName("ASTNode");
				jp.className = "ASTNode";
				jp.pushTopLevelOrAspect(true);
				try {
					while (true)
						jp.AspectBodyDeclaration();
				} catch (Exception e) {
					String s = e.getMessage();
				}
				jp.popTopLevelOrAspect();
			}

			// Parse all jrag files and build tables
			for (Iterator<URI> iter = files.iterator(); iter.hasNext();) {
				URI fileURI = iter.next();
				if (fileURI.toString().endsWith(".jrag") || fileURI.toString().endsWith(".jadd")) {
					ASTCompilationUnit au = loadJragSpec(fileURI,root,errors);
					if(errors.isEmpty()){
						root.addCompUnit(au);	
					}
				}
			}

			long jragParseTime = System.currentTimeMillis() - time
					- astErrorTime;

			for (int i = 0; i < root.getNumTypeDecl(); i++) {
				if (root.getTypeDecl(i) instanceof ASTDecl) {
					ASTDecl decl = (ASTDecl) root.getTypeDecl(i);
					java.io.StringWriter writer = new java.io.StringWriter();
					decl.jjtGen(new PrintWriter(writer), grammarName,
							ASTNode.jjtree, ASTNode.rewriteEnabled);

					jrag.AST.JragParser jp = new jrag.AST.JragParser(
							new java.io.StringReader(writer.toString()));
					jp.root = root;
					jp.setFileName(decl.getFileName());
					jp.className = "ASTNode";
					jp.pushTopLevelOrAspect(true);
					try {
						while (true)
							jp.AspectBodyDeclaration();
					} catch (Exception e) {
						String s = e.getMessage();
					}
					jp.popTopLevelOrAspect();

					int j = 0;
					for (Iterator iter = decl.getComponents(); iter.hasNext();) {
						Components c = (Components) iter.next();
						if (c instanceof TokenComponent) {
							c.jaddGen(null, j, publicModifier, decl);
						} else {
							c.jaddGen(null, j, publicModifier, decl);
							j++;
						}
					}
				}
			}

			root.processRefinements();
			root.weaveInterfaceIntroductions();

			for (Iterator iter = cacheFiles.iterator(); iter.hasNext();) {
				String fileName = (String) iter.next();
				System.out.println("Processing cache file: " + fileName);
				try {
					FileInputStream inputStream = new FileInputStream(fileName);
					JragParser jp = new JragParser(inputStream);
					jp.inputStream = inputStream; // Hack to make input stream
													// visible for ast-parser
					jp.root = root;
					jp.setFileName(new File(fileName).getName());
					jp.CacheDeclarations();
				} catch (jrag.AST.ParseException e) {
					StringBuffer msg = new StringBuffer();
					msg.append("Syntax error in " + fileName + " at line "
							+ e.currentToken.next.beginLine + ", column "
							+ e.currentToken.next.beginColumn);
					System.out.println(msg.toString());
					System.exit(1);
				} catch (FileNotFoundException e) {
					System.out.println("File error: Aspect file " + fileName
							+ " not found");
					System.exit(1);
				}
			}

			root.weaveCollectionAttributes();

			String err = root.errors();
			if (!err.equals("") || !ASTNode.globalErrors.equals("")) {
				System.out.println("Semantic errors: \n" + err
						+ ASTNode.globalErrors);
				return null;
			}

			long jragErrorTime = System.currentTimeMillis() - time
					- jragParseTime;

			/*root.jastAddGen(outputDir, grammarName, pack, publicModifier);
			try {
				root.createInterfaces(outputDir, pack);
			} catch (FileNotFoundException e) {
				System.out.println("File error: Output directory " + outputDir
						+ " does not exist or is write protected");
				System.exit(1);
			}
			long codegenTime = System.currentTimeMillis() - time
					- jragErrorTime;
		*/
			// System.out.println("AST parse time: " + astParseTime +
			// ", AST error check: " + astErrorTime + ", JRAG parse time: " +
			// jragParseTime + ", JRAG error time: " + jragErrorTime +
			// ", Code generation: " + codegenTime);
			return root;
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
