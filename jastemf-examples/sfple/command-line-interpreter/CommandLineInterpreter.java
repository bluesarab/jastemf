/**
 * This program and the accompanying materials are made available under the
 * terms of the MIT license (X11 license) which accompanies this distribution.
 */
package org.jastemf.sfple;

import java.io.*;

import org.jastemf.sfple.symbols.*;
import org.jastemf.sfple.syntax.*;
import org.jastemf.sfple.syntax.Parser.*;
import org.jastemf.sfple.semantics.ast.*;

/**
 * SfPLE command line interpreter.
 * @author C. Bürger
 */
public class CommandLineInterpreter {
	/**
	 * Start command line interpreter. Every expression or definition entered must be
	 * finished by ".". Global bindings can be changed by just entering new
	 * definitions for already defined entities. To terminate the interpreter enter "exit.".
	 */
	public static void main(String[] args) throws IOException, beaver.Parser.Exception {
		List<Definition> globalDefs = new List<Definition>();
		Computation globalProg = new Computation(globalDefs, new BooleanValue(true));
		new CompilationUnit(globalProg);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringBuffer input = new StringBuffer();
			input.append('\n');
			while (input.charAt(input.length() - 1) != '.') {
				input.append('\n');
				input.append(in.readLine());
			}
			input.deleteCharAt(input.length() - 1); // delete the "."
			if (input.toString().equals("\n\nexit")) {
				break;
			} else {
				Reader r = new StringReader(input.toString());
				try {
					try {
						Definition read = (Definition)new Parser().parse(new Lexer(r), AltGoals.Definition);
						for (int i = 0; i < globalDefs.getNumChildNoTransform(); i++)
							if (((Definition)globalDefs.getChildNoTransform(i)).getLValue().equals(read.getLValue()))
								globalDefs.removeChild(i);
						//read.setParent(globalDefs);
						globalDefs.add(read);
						System.out.println(read.getLValue());
					} catch (Parser.Exception e) {
						r.reset();
						Expression read =  (Expression)new Parser().parse(new Lexer(r), AltGoals.Expression);
						//read.setParent(globalProg);
						globalProg.setExpression(read);
						System.out.println(globalProg.getExpression().AsConstant().Print());
					}
				} catch (IOException e) {
					System.out.println("IO-ERROR: " + e.getLocalizedMessage());
				} catch (Parser.Exception e) {
					System.out.println("SYNTAX-ERROR");
				} finally {r.close();}
			}
		}
		in.close();
	}
}
