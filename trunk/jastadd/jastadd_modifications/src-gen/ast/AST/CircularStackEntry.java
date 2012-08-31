
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;

  public class CircularStackEntry extends java.lang.Object {
    // Declared in ASTNode.ast at line 73
    java.util.Set circularEvalSet;

    // Declared in ASTNode.ast at line 74
  	 boolean changeValue;

    // Declared in ASTNode.ast at line 75
  	 public CircularStackEntry(java.util.Set set, boolean change) {
  		 circularEvalSet = set;
  		 changeValue = change;
  	 }


}
