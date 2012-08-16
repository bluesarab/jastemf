
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;

public class SynEq extends AttrEq implements Cloneable {
    public void flushCache() {
        super.flushCache();
        error_visited = false;
        decl_visited = false;
        decl_computed = false;
        decl_value = null;
    }
    public Object clone() throws CloneNotSupportedException {
        SynEq node = (SynEq)super.clone();
        node.error_visited = false;
        node.decl_visited = false;
        node.decl_computed = false;
        node.decl_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          SynEq node = (SynEq)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        SynEq res = (SynEq)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in JragCodeGen.jrag at line 720

  
  public String computeMethod() {
    if(getRHS() instanceof ASTBlock) // Block
      return "    private #TYPE# #CLASS#.#METHODNAME#_compute(#PARMDECL#) " + getRHS().unparse() + "\n\n";
    else // Expr
      return "    private #TYPE# #CLASS#.#METHODNAME#_compute(#PARMDECL#) {  return " + getRHS().unparse() + ";  }\n\n";
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 50

    public SynEq(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public SynEq(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public SynEq() {
        this(0);

        setChild(new List(), 0);

    }

    // Declared in Ast.ast at line 18


    // Declared in Ast.ast line 50
    public SynEq(List p0, String p1, String p2, int p3, int p4, String p5, String p6) {
        setChild(p0, 0);
        setName(p1);
        setFileName(p2);
        setStartLine(p3);
        setEndLine(p4);
        setComment(p5);
        setAspectName(p6);
    }

    // Declared in Ast.ast at line 28


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "SynEq"+ "\"" + getName() + "\""+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getComment() + "\""+ "\"" + getAspectName() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 35


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 39


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 44


public void checkChild(Node n, int i) {
  if(i == 0) {
    if(!(n instanceof List)) throw new Error("Child number 0 of SynEq has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Parameter)) throw new Error("Child number " + k + " in ParameterList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Parameter");
  }
}

    // Declared in Ast.ast at line 52


  public int getNumChild() {
    return 1;
  }

    // Declared in Ast.ast at line 55

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 50
    public void setParameterList(List list) {
        setChild(list, 0);
    }

    // Declared in Ast.ast at line 6


    public int getNumParameter() {
        return getParameterList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public Parameter getParameter(int i) {
        return (Parameter)getParameterList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addParameter(Parameter node) {
        List list = getParameterList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setParameter(Parameter node, int i) {
        List list = getParameterList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getParameterList() {
        return (List)getChild(0);
    }

    // Declared in Ast.ast at line 27


    public List getParameterListNoTransform() {
        return (List)getChildNoTransform(0);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 50
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
    // Declared in Ast.ast line 50
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
    // Declared in Ast.ast line 50
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
    // Declared in Ast.ast line 50
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
    // Declared in Ast.ast line 50
    private String tokenString_Comment;

    // Declared in Ast.ast at line 3

    public void setComment(String value) {
        tokenString_Comment = value;
    }

    // Declared in Ast.ast at line 6

    public String getComment() {
        return tokenString_Comment;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 50
    private String tokenString_AspectName;

    // Declared in Ast.ast at line 3

    public void setAspectName(String value) {
        tokenString_AspectName = value;
    }

    // Declared in Ast.ast at line 6

    public String getAspectName() {
        return tokenString_AspectName;
    }

    protected boolean error_visited = false;
    // Declared in Errorcheck.jrag at line 75
    public String error() {
boolean interruptedCircle = false;
        if(error_visited)
            throw new RuntimeException("Circular definition of attr: error in class: ");
        error_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String error_value = error_compute();
        error_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return error_value;
    }

    private String error_compute()  {
    StringBuffer result = new StringBuffer();
    if(hostClass().lookupSynDecl(signature()) == null) {
      result.append(getFileName() + ":" + getStartLine() + " ");
      result.append("Synthesized attribute " + name() + 
          " assigned in class " + hostClass().name() + " is not declared\n");
    }
    else {
      SynEq equation = hostClass().lookupSynEq(signature());
      if(equation != null && equation != this) {
        result.append(getFileName() + ":" + getStartLine() + " ");
        result.append("Multiple equations for synthesized attribute " + name() +
          " in class " + hostClass().name());
        result.append(" and " + equation.name() + " in class " + equation.hostClass().name() +
           " in " + equation.getFileName() + ":" + equation.getStartLine()
          + "\n");
      }
      SynDecl decl = hostClass().lookupSynDecl(signature());
      if(!decl.parametersDecl().equals(parametersDecl())) {
        result.append(getFileName() + ":" + getStartLine() + " ");
        result.append("Equation must have the same parameter names as attribute declaration in ");
        result.append(decl.getFileName() + ":" + decl.getStartLine() + "\n");
      }
    }
    return result.toString();
  }

    protected boolean decl_visited = false;
    protected boolean decl_computed = false;
    protected AttrDecl decl_value;
    // Declared in NameBinding.jrag at line 399
    public AttrDecl decl() {
        if(decl_computed)
            return decl_value;
boolean interruptedCircle = false;
        if(decl_visited)
            throw new RuntimeException("Circular definition of attr: decl in class: ");
        decl_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        decl_value = decl_compute();
        if(isFinal && num == boundariesCrossed)
            decl_computed = true;
        decl_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return decl_value;
    }

    private AttrDecl decl_compute() {  return  hostClass().lookupSynDecl(signature());  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
