
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;

  public class CircularEvalEntry extends java.lang.Object {
    // Declared in ASTNode.ast at line 45
  	 ASTNode node;

    // Declared in ASTNode.ast at line 46
  	 String attrName;

    // Declared in ASTNode.ast at line 47
  	 Object parameters;

    // Declared in ASTNode.ast at line 48
  	 public CircularEvalEntry(ASTNode node, String attrName, Object parameters) {
  	   this.node = node;
   	 this.attrName = attrName;
  		 this.parameters = parameters;
  	 }

    // Declared in ASTNode.ast at line 53
  	 public boolean equals(Object rhs) {
  	   CircularEvalEntry s = (CircularEvalEntry) rhs;
  		 if (parameters == null && s.parameters == null)
  			 return node == s.node && attrName.equals(s.attrName);
  		 else if (parameters != null && s.parameters != null)
  			 return node == s.node && attrName.equals(s.attrName) && parameters.equals(s.parameters);
  		 else
  			 return false;
  	 }

    // Declared in ASTNode.ast at line 62
  	 public int hashCode() {
  		 return node.hashCode();
  	 }


}
