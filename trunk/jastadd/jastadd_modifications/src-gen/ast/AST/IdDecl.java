
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class IdDecl extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
        name_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        IdDecl node = (IdDecl)super.clone();
        node.name_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          IdDecl node = (IdDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        IdDecl res = (IdDecl)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 84

    public IdDecl(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public IdDecl(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public IdDecl() {
        this(0);


    }

    // Declared in Ast.ast at line 17


    // Declared in Ast.ast line 84
    public IdDecl(String p0) {
        setID(p0);
    }

    // Declared in Ast.ast at line 21


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "IdDecl"+ "\"" + getID() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 28


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 32


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 37


public void checkChild(Node n, int i) {
}

    // Declared in Ast.ast at line 40


  public int getNumChild() {
    return 0;
  }

    // Declared in Ast.ast at line 43

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 84
    private String tokenString_ID;

    // Declared in Ast.ast at line 3

    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in Ast.ast at line 6

    public String getID() {
        return tokenString_ID;
    }

    protected boolean name_visited = false;
    // Declared in NameBinding.jrag at line 62
    public String name() {
boolean interruptedCircle = false;
        if(name_visited)
            throw new RuntimeException("Circular definition of attr: name in class: ");
        name_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String name_value = name_compute();
        name_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return name_value;
    }

    private String name_compute() {  return  getID();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
