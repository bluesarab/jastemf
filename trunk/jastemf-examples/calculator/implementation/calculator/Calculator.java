package calculator;

import java.io.*;

import calculator.symbols.*;
import calculator.syntax.*;
import calculator.semantics.ast.*;
import calculator.semantics.impl.*;

/**
 * Class for command line execution.
 * @author C. Bürger
 *
 */
public class Calculator {

	/**
	 * Interpret the source file <tt>args[0]</tt>, i.e. do a lexic, syntactic
	 * and semantic analysis and finally call the root node's
	 * {@link calculator.semantics.ast.ASTNode.Interpret()} method.
	 * @param args The source file to interpret.
	 */
	public static void main(String[] args) throws IOException, beaver.Parser.Exception {
		CalculatorLexer scanner = new CalculatorLexer(new FileReader(new File(args[0])));
		CalculatorParser parser = new CalculatorParser();
		CompilationUnitImpl ast = (CompilationUnitImpl)parser.parse(scanner);
		System.out.println(ast.Interpret());
	}
}
