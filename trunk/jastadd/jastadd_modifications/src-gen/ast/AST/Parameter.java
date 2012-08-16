
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class Parameter extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
        getTypeInSignature_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        Parameter node = (Parameter)super.clone();
        node.getTypeInSignature_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          Parameter node = (Parameter)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        Parameter res = (Parameter)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in JragCodeGen.jrag at line 539

  
  public String toReferenceType() {
    return toReferenceType(getName(), getType());
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 47

    public Parameter(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public Parameter(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public Parameter() {
        this(0);


    }

    // Declared in Ast.ast at line 17


    // Declared in Ast.ast line 47
    public Parameter(String p0, String p1) {
        setType(p0);
        setName(p1);
    }

    // Declared in Ast.ast at line 22


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "Parameter"+ "\"" + getType() + "\""+ "\"" + getName() + "\"");
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
    // Declared in Ast.ast line 47
    private String tokenString_Type;

    // Declared in Ast.ast at line 3

    public void setType(String value) {
        tokenString_Type = value;
    }

    // Declared in Ast.ast at line 6

    public String getType() {
        return tokenString_Type;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 47
    private String tokenString_Name;

    // Declared in Ast.ast at line 3

    public void setName(String value) {
        tokenString_Name = value;
    }

    // Declared in Ast.ast at line 6

    public String getName() {
        return tokenString_Name;
    }

    protected boolean getTypeInSignature_visited = false;
    // Declared in NameBinding.jrag at line 19
    public String getTypeInSignature() {
boolean interruptedCircle = false;
        if(getTypeInSignature_visited)
            throw new RuntimeException("Circular definition of attr: getTypeInSignature in class: ");
        getTypeInSignature_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String getTypeInSignature_value = getTypeInSignature_compute();
        getTypeInSignature_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return getTypeInSignature_value;
    }

    private String getTypeInSignature_compute() {  return  convTypeNameToSignature(getType());  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
