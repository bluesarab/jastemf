/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package siple.emftext.support;

import java.io.*;
import java.util.*;

import org.antlr.runtime3_3_0.*;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.Resource.*;

import siple.semantics.impl.*;
import siple.semantics.interfaces.*;
import siple.semantics.resource.siple.*;
import siple.semantics.resource.siple.mopp.*;

public class ParserHelper {
	private ISipleParseResult parse(String stringStatement) {
		return parse(new ByteArrayInputStream(stringStatement.getBytes()));
	}
	
	private ISipleParseResult parse(InputStream inputStream) {
		try {
			ANTLRInputStream antlrInputStream =
				new ANTLRInputStream(inputStream);
			SipleLexer sipleLexer = new SipleLexer(antlrInputStream);
			CommonTokenStream tokenStream = new CommonTokenStream(sipleLexer);
			SipleParser parser = new SipleParser(tokenStream);
			ISipleParseResult result = parser.parse();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public CompilationUnit parseProgram(final Resource resource,
			String program) {
		if (program == null || program.isEmpty())
			return null;
		return parseProgram(
				resource,
				new ByteArrayInputStream(program.getBytes()));
	}
	
	public CompilationUnit parseProgram(final Resource resource,
			InputStream program) {
		if (program == null)
			return null;
		ISipleParseResult result = parse(program);
		if (result == null)
			return null;
		extractErrors(resource, result);
		if (result.getRoot() != null
				&& result.getRoot() instanceof CompilationUnit)
			return (CompilationUnit) result.getRoot();
		return null;
	}
	
	public Statement parseStatements(final Resource resource, String stmt) {
		if (stmt == null || stmt.isEmpty())
			return null;
		stmt = "Procedure main() Begin " + stmt + "End;";
		ISipleParseResult result = parse(stmt);
		if (result == null)
			return null;
		extractErrors(resource, result);
		if (result.getRoot() != null
				&& result.getRoot() instanceof CompilationUnit) {
			Statement statement = ((CompilationUnit) result.getRoot())
					.getMainProcedure().getBody();
			return statement;
		}
		return null;
	}
	
	public Expression parseExpression(Resource resource, String guardExpr) {
		if (guardExpr == null || guardExpr.isEmpty())
			return null;
		guardExpr = "Procedure main() Begin " + guardExpr + ";" + "End;";
		ISipleParseResult result = parse(guardExpr);
		if (result == null)
			return null;
		extractErrors(resource, result);
		if (result.getRoot() != null
				&& result.getRoot() instanceof CompilationUnit) {
			Block body = ((CompilationUnit) result.getRoot())
					.getMainProcedure().getBody();
			return body.getStatement().get(0) instanceof Expression ?
					(Expression)body.getStatement().get(0) : null;
		}
		return null;
	}
	
	private void extractErrors(final Resource resource, ISipleParseResult result) {
		DummySipleResource dummyResource = new DummySipleResource();
		for (ISipleCommand<ISipleTextResource> iSipleCommand :
			result.getPostParseCommands()) {
			iSipleCommand.execute(dummyResource);
		}
		List<String> errors = dummyResource.getAllProblems();
		for (final String error : errors) {
			Diagnostic newDiagnostic = new Diagnostic() {
				public String getMessage() {
					String message = error;
					return message;
				}
				public String getLocation() {return null;}
				public int getLine() {return 0;}
				public int getColumn() {return 0;}
			};
			if (resource != null) {
				resource.getErrors().add(newDiagnostic);
			} else {
				System.err.println(newDiagnostic.getMessage());
			}
		}
	}
}
