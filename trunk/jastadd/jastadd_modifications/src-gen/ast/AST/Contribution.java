
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;

public class Contribution extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public Object clone() throws CloneNotSupportedException {
        Contribution node = (Contribution)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          Contribution node = (Contribution)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        Contribution res = (Contribution)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 53

    public Contribution(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public Contribution(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public Contribution() {
        this(0);


    }

    // Declared in Ast.ast at line 17


    // Declared in Ast.ast line 53
    public Contribution(String p0, String p1) {
        setValue(p0);
        setCondition(p1);
    }

    // Declared in Ast.ast at line 22


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "Contribution"+ "\"" + getValue() + "\""+ "\"" + getCondition() + "\"");
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
}

    // Declared in Ast.ast at line 41


  public int getNumChild() {
    return 0;
  }

    // Declared in Ast.ast at line 44

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 53
    private String tokenString_Value;

    // Declared in Ast.ast at line 3

    public void setValue(String value) {
        tokenString_Value = value;
    }

    // Declared in Ast.ast at line 6

    public String getValue() {
        return tokenString_Value;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 53
    private String tokenString_Condition;

    // Declared in Ast.ast at line 3

    public void setCondition(String value) {
        tokenString_Condition = value;
    }

    // Declared in Ast.ast at line 6

    public String getCondition() {
        return tokenString_Condition;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
