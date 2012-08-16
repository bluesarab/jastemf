
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class OptionalComponentNTA extends OptionalComponent implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public Object clone() throws CloneNotSupportedException {
        OptionalComponentNTA node = (OptionalComponentNTA)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          OptionalComponentNTA node = (OptionalComponentNTA)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        OptionalComponentNTA res = (OptionalComponentNTA)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in ComponentsUtil.jrag at line 67

  public boolean isNTA() {
    return true;
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 68

    public OptionalComponentNTA(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public OptionalComponentNTA(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public OptionalComponentNTA() {
        this(0);

        setChild(null, 0);

    }

    // Declared in Ast.ast at line 18


    // Declared in Ast.ast line 68
    public OptionalComponentNTA(Id p0) {
        setChild(p0, 0);
    }

    // Declared in Ast.ast at line 22


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "OptionalComponentNTA");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 29


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 33


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 38


public void checkChild(Node n, int i) {
  if(i == 0 && !(n instanceof Id))  throw new Error("Child number 0 of OptionalComponent has the type " + n.getClass().getName() + " which is not an instance of Id");
}

    // Declared in Ast.ast at line 42


  public int getNumChild() {
    return 1;
  }

    // Declared in Ast.ast at line 45

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 66
    public void setId(Id node) {
        setChild(node, 0);
    }

    // Declared in Ast.ast at line 5

    public Id getId() {
        return (Id)getChild(0);
    }

    // Declared in Ast.ast at line 9


    public Id getIdNoTransform() {
        return (Id)getChildNoTransform(0);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
