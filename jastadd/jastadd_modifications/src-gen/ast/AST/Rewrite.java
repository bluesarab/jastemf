
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class Rewrite extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
        aspectName_visited = false;
        aspectName_computed = false;
        aspectName_value = null;
    }
    public Object clone() throws CloneNotSupportedException {
        Rewrite node = (Rewrite)super.clone();
        node.aspectName_visited = false;
        node.aspectName_computed = false;
        node.aspectName_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          Rewrite node = (Rewrite)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        Rewrite res = (Rewrite)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Attributes.jrag at line 994


  public jrag.AST.SimpleNode condition;

    // Declared in Attributes.jrag at line 995

  public jrag.AST.SimpleNode getCondition() {
    return condition;
  }

    // Declared in Attributes.jrag at line 998

  public void setCondition(jrag.AST.SimpleNode c) {
    condition = c;
  }

    // Declared in Attributes.jrag at line 1002

  
  public jrag.AST.SimpleNode result;

    // Declared in Attributes.jrag at line 1003

  public jrag.AST.SimpleNode getResult() {
    return result;
  }

    // Declared in Attributes.jrag at line 1006

  public void setResult(jrag.AST.SimpleNode r) {
    result = r;
  }

    // Declared in Attributes.jrag at line 1010


  public String returnType;

    // Declared in Attributes.jrag at line 1011

  public String getReturnType() {
    return returnType;
  }

    // Declared in Attributes.jrag at line 1014

  public void setReturnType(String type) {
    returnType = type;
  }

    // Declared in JragCodeGen.jrag at line 1559


  public boolean genRewrite(StringBuffer buf, int index) {
      buf.append("    // Declared in " + getFileName() + " at line " + getStartLine() + "\n");
      if(getCondition() != null) {
        buf.append("    if(" + getCondition().unparse() + ") {\n");
        buf.append("        state().during" + aspectName() + "++;\n");
        buf.append("        ASTNode result = rewriteRule" + index + "();\n");
        buf.append("        state().during" + aspectName() + "--;\n");
        buf.append("        return result;\n");
        buf.append("    }\n");
        
        buf.append("\n");
        return false;
      }
      else {
        buf.append("        state().during" + aspectName() + "++;\n");
        buf.append("        ASTNode result = rewriteRule" + index + "();\n");
        buf.append("        state().during" + aspectName() + "--;\n");
        buf.append("        return result;\n");
        return true;
      }
  }

    // Declared in JragCodeGen.jrag at line 1608


  public void genRewritesExtra(StringBuffer buf, int index) {
    buf.append("    // Declared in " + getFileName() + " at line " + getStartLine() + "\n");
    if(getResult() instanceof jrag.AST.ASTBlock) {
      buf.append("    private " + getReturnType() + " rewriteRule" + index + "() {\n");
      if(rewriteLimit > 0)
        buf.append("         debugRewrite(\"Rewriting \" + getClass().getName() + \" using rule in " + getFileName() + " at line " + getStartLine() + "\");\n");
      buf.append(getResult().unparse());
      buf.append("    }\n");
    }
    else {
      buf.append("    private " + getReturnType() + " rewriteRule" + index + "() {\n");
      if(rewriteLimit > 0)
        buf.append("         debugRewrite(\"Rewriting \" + getClass().getName() + \" using rule in " + getFileName() + " at line " + getStartLine() + "\");\n");
      buf.append("        return " + getResult().unparse() + ";\n");
      buf.append("    }\n");
    }
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 57

    public Rewrite(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public Rewrite(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public Rewrite() {
        this(0);


    }

    // Declared in Ast.ast at line 17


    // Declared in Ast.ast line 57
    public Rewrite(String p0, int p1, int p2) {
        setFileName(p0);
        setStartLine(p1);
        setEndLine(p2);
    }

    // Declared in Ast.ast at line 23


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "Rewrite"+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 30


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 34


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 39


public void checkChild(Node n, int i) {
}

    // Declared in Ast.ast at line 42


  public int getNumChild() {
    return 0;
  }

    // Declared in Ast.ast at line 45

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 57
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
    // Declared in Ast.ast line 57
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
    // Declared in Ast.ast line 57
    private int tokenint_EndLine;

    // Declared in Ast.ast at line 3

    public void setEndLine(int value) {
        tokenint_EndLine = value;
    }

    // Declared in Ast.ast at line 6

    public int getEndLine() {
        return tokenint_EndLine;
    }

    protected boolean aspectName_visited = false;
    protected boolean aspectName_computed = false;
    protected String aspectName_value;
    // Declared in JragCodeGen.jrag at line 1481
    public String aspectName() {
        if(aspectName_computed)
            return aspectName_value;
boolean interruptedCircle = false;
        if(aspectName_visited)
            throw new RuntimeException("Circular definition of attr: aspectName in class: ");
        aspectName_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        aspectName_value = aspectName_compute();
        if(isFinal && num == boundariesCrossed)
            aspectName_computed = true;
        aspectName_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return aspectName_value;
    }

    private String aspectName_compute()  {
    String name = getFileName();
    if(name.endsWith(".jrag"))
      name = name.substring(0, name.length() - 5);
    else if(name.endsWith(".jadd"))
      name = name.substring(0, name.length() - 5);
    else if(name.endsWith(".ast"))
      name = name.substring(0, name.length() - 4);
    return name;
  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
