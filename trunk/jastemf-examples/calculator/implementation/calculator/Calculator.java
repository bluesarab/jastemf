/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package calculator;

import java.io.*;

import calculator.symbols.*;
import calculator.syntax.*;
import calculator.semantics.*;
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
		CompilationUnit ast = (CompilationUnit)parser.parse(scanner);
		State vm = ast.Interpret();
		System.out.println(vm.getStdOut());
	}
}
