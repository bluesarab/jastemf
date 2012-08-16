
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class ClassDecl extends TypeDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public Object clone() throws CloneNotSupportedException {
        ClassDecl node = (ClassDecl)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          ClassDecl node = (ClassDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        ClassDecl res = (ClassDecl)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in ComponentsUtil.jrag at line 89


  public String extendsName = "java.lang.Object";

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 17

    public ClassDecl(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public ClassDecl(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public ClassDecl() {
        this(0);

        setChild(null, 0);
        setChild(new List(), 1);
        setChild(new List(), 2);
        setChild(new List(), 3);
        setChild(new List(), 4);
        setChild(new List(), 5);
        setChild(new List(), 6);
        setChild(new List(), 7);

    }

    // Declared in Ast.ast at line 25


    // Declared in Ast.ast line 17
    public ClassDecl(IdDecl p0, List p1, List p2, List p3, List p4, List p5, List p6, List p7, String p8, int p9, int p10, String p11) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
        setChild(p3, 3);
        setChild(p4, 4);
        setChild(p5, 5);
        setChild(p6, 6);
        setChild(p7, 7);
        setFileName(p8);
        setStartLine(p9);
        setEndLine(p10);
        setComment(p11);
    }

    // Declared in Ast.ast at line 40


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "ClassDecl"+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getComment() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 47


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 51


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 56


public void checkChild(Node n, int i) {
  if(i == 0 && !(n instanceof IdDecl))  throw new Error("Child number 0 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of IdDecl");
  if(i == 1) {
    if(!(n instanceof List)) throw new Error("Child number 1 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof ClassBodyDecl)) throw new Error("Child number " + k + " in ClassBodyDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of ClassBodyDecl");
  }
  if(i == 2) {
    if(!(n instanceof List)) throw new Error("Child number 2 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof SynDecl)) throw new Error("Child number " + k + " in SynDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of SynDecl");
  }
  if(i == 3) {
    if(!(n instanceof List)) throw new Error("Child number 3 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof SynEq)) throw new Error("Child number " + k + " in SynEqList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of SynEq");
  }
  if(i == 4) {
    if(!(n instanceof List)) throw new Error("Child number 4 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof InhDecl)) throw new Error("Child number " + k + " in InhDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of InhDecl");
  }
  if(i == 5) {
    if(!(n instanceof List)) throw new Error("Child number 5 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof InhEq)) throw new Error("Child number " + k + " in InhEqList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of InhEq");
  }
  if(i == 6) {
    if(!(n instanceof List)) throw new Error("Child number 6 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Components)) throw new Error("Child number " + k + " in ComponentsList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Components");
  }
  if(i == 7) {
    if(!(n instanceof List)) throw new Error("Child number 7 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof CollDecl)) throw new Error("Child number " + k + " in CollDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of CollDecl");
  }
}

    // Declared in Ast.ast at line 95


  public int getNumChild() {
    return 8;
  }

    // Declared in Ast.ast at line 98

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setIdDecl(IdDecl node) {
        setChild(node, 0);
    }

    // Declared in Ast.ast at line 5

    public IdDecl getIdDecl() {
        return (IdDecl)getChild(0);
    }

    // Declared in Ast.ast at line 9


    public IdDecl getIdDeclNoTransform() {
        return (IdDecl)getChildNoTransform(0);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setClassBodyDeclList(List list) {
        setChild(list, 1);
    }

    // Declared in Ast.ast at line 6


    public int getNumClassBodyDecl() {
        return getClassBodyDeclList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public ClassBodyDecl getClassBodyDecl(int i) {
        return (ClassBodyDecl)getClassBodyDeclList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addClassBodyDecl(ClassBodyDecl node) {
        List list = getClassBodyDeclList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setClassBodyDecl(ClassBodyDecl node, int i) {
        List list = getClassBodyDeclList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getClassBodyDeclList() {
        return (List)getChild(1);
    }

    // Declared in Ast.ast at line 27


    public List getClassBodyDeclListNoTransform() {
        return (List)getChildNoTransform(1);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setSynDeclList(List list) {
        setChild(list, 2);
    }

    // Declared in Ast.ast at line 6


    public int getNumSynDecl() {
        return getSynDeclList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public SynDecl getSynDecl(int i) {
        return (SynDecl)getSynDeclList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addSynDecl(SynDecl node) {
        List list = getSynDeclList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setSynDecl(SynDecl node, int i) {
        List list = getSynDeclList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getSynDeclList() {
        return (List)getChild(2);
    }

    // Declared in Ast.ast at line 27


    public List getSynDeclListNoTransform() {
        return (List)getChildNoTransform(2);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setSynEqList(List list) {
        setChild(list, 3);
    }

    // Declared in Ast.ast at line 6


    public int getNumSynEq() {
        return getSynEqList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public SynEq getSynEq(int i) {
        return (SynEq)getSynEqList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addSynEq(SynEq node) {
        List list = getSynEqList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setSynEq(SynEq node, int i) {
        List list = getSynEqList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getSynEqList() {
        return (List)getChild(3);
    }

    // Declared in Ast.ast at line 27


    public List getSynEqListNoTransform() {
        return (List)getChildNoTransform(3);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setInhDeclList(List list) {
        setChild(list, 4);
    }

    // Declared in Ast.ast at line 6


    public int getNumInhDecl() {
        return getInhDeclList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public InhDecl getInhDecl(int i) {
        return (InhDecl)getInhDeclList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addInhDecl(InhDecl node) {
        List list = getInhDeclList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setInhDecl(InhDecl node, int i) {
        List list = getInhDeclList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getInhDeclList() {
        return (List)getChild(4);
    }

    // Declared in Ast.ast at line 27


    public List getInhDeclListNoTransform() {
        return (List)getChildNoTransform(4);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setInhEqList(List list) {
        setChild(list, 5);
    }

    // Declared in Ast.ast at line 6


    public int getNumInhEq() {
        return getInhEqList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public InhEq getInhEq(int i) {
        return (InhEq)getInhEqList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addInhEq(InhEq node) {
        List list = getInhEqList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setInhEq(InhEq node, int i) {
        List list = getInhEqList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getInhEqList() {
        return (List)getChild(5);
    }

    // Declared in Ast.ast at line 27


    public List getInhEqListNoTransform() {
        return (List)getChildNoTransform(5);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setComponentsList(List list) {
        setChild(list, 6);
    }

    // Declared in Ast.ast at line 6


    public int getNumComponents() {
        return getComponentsList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public Components getComponents(int i) {
        return (Components)getComponentsList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addComponents(Components node) {
        List list = getComponentsList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setComponents(Components node, int i) {
        List list = getComponentsList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getComponentsList() {
        return (List)getChild(6);
    }

    // Declared in Ast.ast at line 27


    public List getComponentsListNoTransform() {
        return (List)getChildNoTransform(6);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setCollDeclList(List list) {
        setChild(list, 7);
    }

    // Declared in Ast.ast at line 6


    public int getNumCollDecl() {
        return getCollDeclList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public CollDecl getCollDecl(int i) {
        return (CollDecl)getCollDeclList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addCollDecl(CollDecl node) {
        List list = getCollDeclList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setCollDecl(CollDecl node, int i) {
        List list = getCollDeclList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getCollDeclList() {
        return (List)getChild(7);
    }

    // Declared in Ast.ast at line 27


    public List getCollDeclListNoTransform() {
        return (List)getChildNoTransform(7);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
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
    // Declared in Ast.ast line 3
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
    // Declared in Ast.ast line 3
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
    // Declared in Ast.ast line 3
    private String tokenString_Comment;

    // Declared in Ast.ast at line 3

    public void setComment(String value) {
        tokenString_Comment = value;
    }

    // Declared in Ast.ast at line 6

    public String getComment() {
        return tokenString_Comment;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
