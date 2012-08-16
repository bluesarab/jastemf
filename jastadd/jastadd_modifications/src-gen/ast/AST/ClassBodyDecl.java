
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class ClassBodyDecl extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public Object clone() throws CloneNotSupportedException {
        ClassBodyDecl node = (ClassBodyDecl)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          ClassBodyDecl node = (ClassBodyDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        ClassBodyDecl res = (ClassBodyDecl)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 55

    public ClassBodyDecl(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public ClassBodyDecl(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public ClassBodyDecl() {
        this(0);


    }

    // Declared in Ast.ast at line 17


    // Declared in Ast.ast line 55
    public ClassBodyDecl(String p0, String p1, int p2, int p3, String p4) {
        setName(p0);
        setFileName(p1);
        setStartLine(p2);
        setEndLine(p3);
        setAspectName(p4);
    }

    // Declared in Ast.ast at line 25


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "ClassBodyDecl"+ "\"" + getName() + "\""+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getAspectName() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 32


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 36


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 41


public void checkChild(Node n, int i) {
}

    // Declared in Ast.ast at line 44


  public int getNumChild() {
    return 0;
  }

    // Declared in Ast.ast at line 47

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 55
    private String tokenString_Name;

    // Declared in Ast.ast at line 3

    public void setName(String value) {
        tokenString_Name = value;
    }

    // Declared in Ast.ast at line 6

    public String getName() {
        return tokenString_Name;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 55
    private String tokenString_FileName;

    // Declared in Ast.ast at line 3

    public void setFileName(String value) {
        tokenString_FileName = value;
    }

    // Declared in Ast.ast at line 6

    public String getFileName() {
        return tokenString_FileName;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 55
    private int tokenint_StartLine;

    // Declared in Ast.ast at line 3

    public void setStartLine(int value) {
        tokenint_StartLine = value;
    }

    // Declared in Ast.ast at line 6

    public int getStartLine() {
        return tokenint_StartLine;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 55
    private int tokenint_EndLine;

    // Declared in Ast.ast at line 3

    public void setEndLine(int value) {
        tokenint_EndLine = value;
    }

    // Declared in Ast.ast at line 6

    public int getEndLine() {
        return tokenint_EndLine;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 55
    private String tokenString_AspectName;

    // Declared in Ast.ast at line 3

    public void setAspectName(String value) {
        tokenString_AspectName = value;
    }

    // Declared in Ast.ast at line 6

    public String getAspectName() {
        return tokenString_AspectName;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
