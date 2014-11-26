package ast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jrag.AST.ASTCompilationUnit;
import jrag.AST.JragParser;
import ast.AST.Ast;
import ast.AST.Grammar;

public class Util {
	
	public static String computeVarName(String astName){
		return astName.toLowerCase();
	}
	
	public static String computeAccName(String astName){
		String result = "";
		if(astName.length()>0){
			result = astName.substring(0, 1).toUpperCase();
			result += astName.substring(1);
		}
		return result;
	}
	

	/**
	 * Parses a list of AST specs given as file names and returns a list of error messages if
	 * problems occur. Correctly parsed specs are added to the given grammar root node.
	 * 
	 * @param fileNames
	 * @param grammar
	 * @return
	 */
	public static Collection<String> parseASTFiles(Collection<String> fileNames, Grammar grammar){
		// Parse ast-grammar
		Collection<String> errors = new ArrayList<String>();
		for (Iterator<String> iter = fileNames.iterator(); iter.hasNext();) {
			String fileName = (String) iter.next();
			if (fileName.endsWith(".ast")) {
				try {
					InputStream stream = new FileInputStream(fileName);
					errors.addAll(parseASTSpecFromStream(stream,fileName,grammar));
				} catch (FileNotFoundException e) {
					errors.add("File error: Abstract syntax grammar file "
									+ fileName + " not found");
				}
			}
		}
		
		if(!errors.isEmpty()){
			return errors;
		}
		
		String semanticErrors = grammar.astErrors();
		if(semanticErrors!=null && semanticErrors.length()>0){
			errors.add("Semantic error:\n"+semanticErrors);			
		}
		return errors;
	
	}

	/**
	 * Parses a list of AG specs given as file names and returns a list of error messages if
	 * problems occur. The correctly parsed specs are added to the given grammar.
	 * 
	 * @param fileNames
	 * @param grammar
	 * @return
	 */
	public static Collection<String> parseAGFiles(Collection<String> fileNames, Grammar grammar){
		// Parse all jrag files and build tables
		Collection<String> problems = new LinkedList<String>();
		for (Iterator<String> iter = fileNames.iterator(); iter.hasNext();) {
			String fileName = iter.next();
			if (fileName.endsWith(".jrag") || fileName.endsWith(".jadd")) {
				try {
					FileInputStream inputStream = new FileInputStream(
							fileName);
					String problem = parseAGSpecFromStream(inputStream,fileName,grammar);
					if(problem!=null)
						problems.add(problem);
					
				} catch (FileNotFoundException e) {
					problems.add("File error: Aspect file "
							+ fileName + " not found");
				}
			}
		}
		return problems;
	}

	/**
	 * Parses an AG spec from the given stream and adds it to the given grammar node.
	 * 
	 * 
	 * @param stream An error message.
	 * @param sourceName
	 * @param grammar
	 * @return
	 */
	public static String parseAGSpecFromStream(InputStream stream, String sourceName, Grammar grammar){
		try {
			JragParser jp = new JragParser(stream);
			jp.inputStream = stream; // Hack to make input
											// stream visible for
											// ast-parser
			jp.root = grammar;
			jp.setFileName(new File(sourceName).getName());
			ASTCompilationUnit au = jp.CompilationUnit();
			grammar.addCompUnit(au);
		} catch (jrag.AST.ParseException e) {
			StringBuffer msg = new StringBuffer();
			msg.append("Syntax error in " + sourceName + " at line "
					+ e.currentToken.next.beginLine + ", column "
					+ e.currentToken.next.beginColumn);
			return (msg.toString());
			
		} catch (jrag.AST.TokenMgrError e){
			return "Problem with lexer in " + sourceName +"." + e.getMessage();
		}
		return null;
	}

	/**
	 * Parses an AST spec from the given stream and adds it to the given grammar object.
	 * 
	 * 
	 * @param stream
	 * @param sourceName
	 * @param grammar
	 * @return
	 */	
	public static Collection<String> parseASTSpecFromStream(InputStream stream, String sourceName, Grammar grammar){
		Collection<String> errors = new LinkedList<String>();
		try {
			Ast parser = new Ast(stream);
			parser.fileName = sourceName;
			Grammar g = parser.Grammar();
			for (int i = 0; i < g.getNumTypeDecl(); i++) {
				grammar.addTypeDecl(g.getTypeDecl(i));
			}
			for (
			Iterator<String> errorIter = parser.getErrors(); errorIter
					.hasNext();) {
				String[] s = errorIter.next().split(";");
				errors.add("Syntax error in " + sourceName
						+ " at line " + s[0] + ", column " + s[1]);
			}
	
		} catch (ast.AST.TokenMgrError e) {
			errors.add("Lexical error in " + sourceName
					+ ": " + e.getMessage());
		} catch (ast.AST.ParseException e) {
			// Exceptions actually caught by error recovery in
			// parser
		} 
		
		return errors;
	}
	
	public static String readFile(String name) throws java.io.IOException {
		java.io.Reader reader = new java.io.BufferedReader(
				new java.io.FileReader(name));
		return readStream(reader);
	}
	
	public static String readFile(File file) throws java.io.IOException {
		java.io.Reader reader = new java.io.BufferedReader(
				new java.io.FileReader(file));
		return readStream(reader);
	}
	
	public static String readStream(Reader reader) throws java.io.IOException {
		StringBuffer buf = new StringBuffer();
		char[] cbuf = new char[1024];
		int i = 0;
		while ((i = reader.read(cbuf)) != -1)
			buf.append(String.valueOf(cbuf, 0, i));
		reader.close();
		return buf.toString();
	}
	
	public static String readStream(InputStream stream) throws java.io.IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		return readStream(reader);
	}
}
