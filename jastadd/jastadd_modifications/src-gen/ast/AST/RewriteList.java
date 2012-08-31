
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;

public class RewriteList extends Rewrite implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public Object clone() throws CloneNotSupportedException {
        RewriteList node = (RewriteList)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          RewriteList node = (RewriteList)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        RewriteList res = (RewriteList)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Attributes.jrag at line 1018


  public String parentName;

    // Declared in Attributes.jrag at line 1019

  public String getParentName() {
    return parentName;
  }

    // Declared in Attributes.jrag at line 1022

  public void setParentName(String name) {
    parentName = name;
  }

    // Declared in Attributes.jrag at line 1025

  public String childName;

    // Declared in Attributes.jrag at line 1026

  public String getChildName() {
    return childName;
  }

    // Declared in Attributes.jrag at line 1029

  public void setChildName(String name) {
    childName = name;
  }

    // Declared in JragCodeGen.jrag at line 1541

  public boolean genRewrite(StringBuffer buf, int index) {
      buf.append("    // Declared in " + getFileName() + " at line " + getStartLine() + "\n");
      buf.append("    if(getParent().getParent() instanceof " + getParentName() + " && \n");
      buf.append("        ((" + getParentName() + ")getParent().getParent())." + getChildName() + "ListNoTransform() == getParent()");
      if(getCondition() != null) {
        buf.append(" && " + getCondition().unparse() + ") {\n");
      }
      else {
        buf.append(") {\n");
      }

      buf.append("        state().during" + aspectName() + "++;\n");
      
      buf.append("      " + ASTNode.listName + " list = (" + ASTNode.listName + ")getParent();\n");
      buf.append("      int i = list.getIndexOfChild(this);\n");
      buf.append("      " + ASTNode.listName + " newList = rewrite" + getParentName() + "_" + getChildName() + "();\n");
      // the first child is set by the normal rewrite loop
      //buf.append("      list.setChild(newList.getChildNoTransform(0), i);\n");
      buf.append("      for(int j = 1; j < newList.getNumChildNoTransform(); j++)\n");
      buf.append("        list.insertChild(newList.getChildNoTransform(j), ++i);\n");

      buf.append("        state().during" + aspectName() + "--;\n");
      
      buf.append("      return newList.getChildNoTransform(0);\n");
      buf.append("    }\n");
      return false;
  }

    // Declared in JragCodeGen.jrag at line 1586

  public void genRewritesExtra(StringBuffer buf, int index) {
    buf.append("    // Declared in " + getFileName() + " at line " + getStartLine() + "\n");
    if(getResult() instanceof jrag.AST.ASTBlock) {
      buf.append("    private " + getReturnType() + " rewrite" + getParentName() + "_" + getChildName() + "() {\n");
      if(rewriteLimit > 0)
        buf.append("         debugRewrite(\"Rewriting \" + getClass().getName() + \" using rule in " + getFileName() + " at line " + getStartLine() + "\");\n");
      buf.append(getResult().unparse());
      buf.append("    }\n");
    }
    else {
      buf.append("    private " + getReturnType() + " rewrite" + getParentName() + "_" + getChildName() + "() {\n");
      if(rewriteLimit > 0)
        buf.append("         debugRewrite(\"Rewriting \" + getClass().getName() + \" using rule in " + getFileName() + " at line " + getStartLine() + "\");\n");
      buf.append("        return " + getResult().unparse() + ";\n");
      buf.append("    }\n");
    }
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 58

    public RewriteList(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public RewriteList(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public RewriteList() {
        this(0);


    }

    // Declared in Ast.ast at line 17


    // Declared in Ast.ast line 58
    public RewriteList(String p0, int p1, int p2) {
        setFileName(p0);
        setStartLine(p1);
        setEndLine(p2);
    }

    // Declared in Ast.ast at line 23


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "RewriteList"+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\"");
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

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
