
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


  public class State extends java.lang.Object {
    // Declared in ASTNode.ast at line 93
   private int[] stack;

    // Declared in ASTNode.ast at line 94
   private int pos;

    // Declared in ASTNode.ast at line 95
   public State() {
     stack = new int[64];
     pos = 0;
   }

    // Declared in ASTNode.ast at line 99
   private void ensureSize(int size) {
     if(size < stack.length)
       return;
     int[] newStack = new int[stack.length * 2];
     System.arraycopy(stack, 0, newStack, 0, stack.length);
     stack = newStack;
   }

    // Declared in ASTNode.ast at line 106
   public void push(int i) {
     ensureSize(pos+1);
     stack[pos++] = i;
   }

    // Declared in ASTNode.ast at line 110
   public int pop() {
     return stack[--pos];
   }

    // Declared in ASTNode.ast at line 113
   public int peek() {
     return stack[pos-1];
   }


}
