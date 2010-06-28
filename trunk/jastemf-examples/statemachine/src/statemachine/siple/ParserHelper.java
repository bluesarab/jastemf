package statemachine.siple;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.antlr.runtime3_2_0.ANTLRInputStream;
import org.antlr.runtime3_2_0.CommonTokenStream;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;

import siple.semantics.interfaces.CompilationUnit;
import siple.semantics.interfaces.Expression;
import siple.semantics.interfaces.Statement;
import siple.semantics.resource.siple.ISipleCommand;
import siple.semantics.resource.siple.ISipleParseResult;
import siple.semantics.resource.siple.ISipleTextResource;
import siple.semantics.resource.siple.mopp.SipleLexer;
import siple.semantics.resource.siple.mopp.SipleParser;

public class ParserHelper {
	
	public CompilationUnit parseProgram(final Resource resource,
			String program) {
		if (program == null || program.isEmpty())
			return null;
		ISipleParseResult result = null;
		result = parse(program);
		if (result == null)
			return null;
		extractErrors(resource, result);
		if (result.getRoot() != null
				&& result.getRoot() instanceof CompilationUnit)
			return result;
		return null;
	}
	
	public Statement parseStatement(final Resource resource,
			String stringStatement) {
		if (stringStatement == null || stringStatement.isEmpty())
			return null;

		stringStatement = prependStatementPrefix(stringStatement);
		stringStatement = appendStatementSuffix(stringStatement);

		ISipleParseResult result = null;
		result = parse(stringStatement);
		if (result == null)
			return null;
		extractErrors(resource, result);
		if (result.getRoot() != null
				&& result.getRoot() instanceof CompilationUnit) {
			Statement statement = extractStatement((CompilationUnit) result
					.getRoot());
			return statement;
		}

		return null;
	}
	
	private String appendStatementSuffix(String stringStatement) {
		stringStatement = stringStatement + "End;";
		return stringStatement;
	}

	private String prependStatementPrefix(String stringStatement) {
		stringStatement = "Procedure main() Begin " + stringStatement;
		return stringStatement;
	}

	private Statement extractStatement(CompilationUnit root) {
		Statement stm = root.getMainProcedure().getBody();
		return stm;
	}

	private ISipleParseResult parse(String stringStatement) {
		InputStream inputStream = new ByteArrayInputStream(stringStatement
				.getBytes());
		return parse(inputStream);
	}
	
	private ISipleParseResult parse(InputStream inputStream) {
		ANTLRInputStream antlrInputStream;
		try {
			antlrInputStream = new ANTLRInputStream(inputStream);

			SipleLexer sipleLexer = new SipleLexer(antlrInputStream);
			CommonTokenStream tokenStream = new CommonTokenStream(sipleLexer);
			SipleParser parser = new SipleParser(tokenStream);
			ISipleParseResult result = parser.parse();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void extractErrors(final Resource resource, ISipleParseResult result) {
		Collection<ISipleCommand<ISipleTextResource>> postParseCommands = result
				.getPostParseCommands();
		DummySipleResource dummyResource = new DummySipleResource();
		for (ISipleCommand<ISipleTextResource> iSipleCommand : postParseCommands) {
			iSipleCommand.execute(dummyResource);

		}
		List<String> errors = dummyResource.getAllProblems();
		for (final String error : errors) {
			Diagnostic newDiagnostic = new Diagnostic() {

				@Override
				public String getMessage() {
					String message = error;
					return message;
				}

				@Override
				public String getLocation() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public int getLine() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public int getColumn() {
					// TODO Auto-generated method stub
					return 0;
				}
			};
			if (resource != null) {
				resource.getErrors().add(newDiagnostic);
			} else {
				System.err.println(newDiagnostic.getMessage());
			}
		}
	}

	

	public Expression parseExpression(Resource resource, String guardExpression) {
		if (guardExpression == null || guardExpression.isEmpty())
			return null;

		guardExpression = prependStatementPrefix(guardExpression);
		guardExpression = appendExpressionSuffix(guardExpression);
		guardExpression = appendStatementSuffix(guardExpression);

		ISipleParseResult result = null;
		result = parse(guardExpression);
		if (result == null)
			return null;
		extractErrors(resource, result);
		if (result.getRoot() != null
				&& result.getRoot() instanceof CompilationUnit) {
			Expression exp = extractExpression((CompilationUnit) result
					.getRoot());
			return exp;
		}
		return null;
	}

	private String appendExpressionSuffix(String guardExpression) {
		guardExpression += ";";
		return guardExpression;
	}

	private Expression extractExpression(CompilationUnit root) {
		Statement stm = root.getMainProcedure().getBody().getStatement().get(0);
		if (stm instanceof Expression) return (Expression) stm;
		return null;
	}

}
