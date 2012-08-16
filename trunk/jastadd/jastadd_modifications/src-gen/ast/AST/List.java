
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;
public class List extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public Object clone() throws CloneNotSupportedException {
        List node = (List)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          List node = (List)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        List res = (List)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in List.ast at line 3
    // Declared in List.ast line 0

    public List(int i) {
        super(i);
    }

    // Declared in List.ast at line 6

    public List(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in List.ast at line 10

    public List() {
        this(0);


    }

    // Declared in List.ast at line 16


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "List");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in List.ast at line 23


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in List.ast at line 27


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in List.ast at line 32


public void checkChild(Node n, int i) {
  if(!(n instanceof ASTNode)) throw new Error("The node type of child " + i + " must be an instance of ASTNode");
}

    // Declared in List.ast at line 36


     public List add(ASTNode node) {
          addChild(node);
          return this;
     }

    // Declared in List.ast at line 41


  public boolean mayHaveRewrite() { return false; }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
