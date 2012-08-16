
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;
 // RightHandSide?
public class InhEq extends AttrEq implements Cloneable {
    public void flushCache() {
        super.flushCache();
        error_visited = false;
        getComponents_visited = false;
        sonName_visited = false;
        getSonAttrDecl_visited = false;
        decl_visited = false;
        decl_computed = false;
        decl_value = null;
    }
    public Object clone() throws CloneNotSupportedException {
        InhEq node = (InhEq)super.clone();
        node.error_visited = false;
        node.getComponents_visited = false;
        node.sonName_visited = false;
        node.getSonAttrDecl_visited = false;
        node.decl_visited = false;
        node.decl_computed = false;
        node.decl_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          InhEq node = (InhEq)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        InhEq res = (InhEq)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 51

    public InhEq(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public InhEq(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public InhEq() {
        this(0);

        setChild(new List(), 0);
        setChild(new Opt(), 1);

    }

    // Declared in Ast.ast at line 19


    // Declared in Ast.ast line 51
    public InhEq(List p0, String p1, String p2, int p3, int p4, String p5, String p6, String p7, Opt p8) {
        setChild(p0, 0);
        setName(p1);
        setFileName(p2);
        setStartLine(p3);
        setEndLine(p4);
        setComment(p5);
        setAspectName(p6);
        setSonName(p7);
        setChild(p8, 1);
    }

    // Declared in Ast.ast at line 31


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "InhEq"+ "\"" + getName() + "\""+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getComment() + "\""+ "\"" + getAspectName() + "\""+ "\"" + getSonName() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 38


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 42


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 47


public void checkChild(Node n, int i) {
  if(i == 0) {
    if(!(n instanceof List)) throw new Error("Child number 0 of InhEq has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Parameter)) throw new Error("Child number " + k + " in ParameterList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Parameter");
  }
  if(i == 1) {
    if(!(n instanceof Opt)) throw new Error("Child number 1 of InhEq has the type " + n.getClass().getName() + " which is not an instance of Opt");
    if(((Opt)n).getNumChild() != 0 && !(((Opt)n).getChildNoTransform(0) instanceof Parameter)) throw new Error("Optional Index has the type " + ((Opt)n).getChildNoTransform(0).getClass().getName() + " which is not an instance of Parameter");
  }
}

    // Declared in Ast.ast at line 59


  public int getNumChild() {
    return 2;
  }

    // Declared in Ast.ast at line 62

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 51
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
    // Declared in Ast.ast line 51
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
    // Declared in Ast.ast line 51
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
    // Declared in Ast.ast line 51
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
    // Declared in Ast.ast line 51
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
    // Declared in Ast.ast line 51
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
    // Declared in Ast.ast line 51
    private String tokenString_AspectName;

    // Declared in Ast.ast at line 3

    public void setAspectName(String value) {
        tokenString_AspectName = value;
    }

    // Declared in Ast.ast at line 6

    public String getAspectName() {
        return tokenString_AspectName;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 51
    private String tokenString_SonName;

    // Declared in Ast.ast at line 3

    public void setSonName(String value) {
        tokenString_SonName = value;
    }

    // Declared in Ast.ast at line 6

    public String getSonName() {
        return tokenString_SonName;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 51
    public void setIndexOpt(Opt opt) {
        setChild(opt, 1);
    }

    // Declared in Ast.ast at line 6


    public boolean hasIndex() {
        return getIndexOpt().getNumChild() != 0;
    }

    // Declared in Ast.ast at line 10


    public Parameter getIndex() {
        return (Parameter)getIndexOpt().getChild(0);
    }

    // Declared in Ast.ast at line 14


    public void setIndex(Parameter node) {
        getIndexOpt().setChild(node, 0);
    }

    // Declared in Ast.ast at line 17

    public Opt getIndexOpt() {
        return (Opt)getChild(1);
    }

    // Declared in Ast.ast at line 21


    public Opt getIndexOptNoTransform() {
        return (Opt)getChildNoTransform(1);
    }

    protected boolean error_visited = false;
    // Declared in Errorcheck.jrag at line 174
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
    if(getComponents() == null && getSonName().startsWith("get") && !getSonName().equals("getChild")) {
      result.append(getFileName() + ":" + getStartLine() + " ");
      result.append("Inherited equation for unknown son " + sonName() 
          + " in class " + hostClass().name() + "\n");
      for(Iterator iter = hostClass().getComponents(); iter.hasNext(); ) {
        Components c = (Components)iter.next();
        result.append(c.type() + " " + c.name() + "\n");
      }
    }
    else if(getComponents() == null && !getSonName().equals("getChild")) {
      AttrDecl decl = getSonAttrDecl();
      if(decl == null) {
        result.append(getFileName() + ":" + getStartLine() + " ");
        result.append("Inherited equation for unknown NTA " + sonName()
             + " in class " + hostClass().name() + "\n");
      }
      else if(!decl.getNTA()) {
        result.append(getFileName() + ":" + getStartLine() + " ");
        result.append("Inherited equation for attribute " + sonName() + " which is not a NTA");
      }
    }
    else {
      InhEq equation = hostClass().lookupInhEq(signature(), sonName());
      if(equation != null && equation != this) {
        result.append(getFileName() + ":" + getStartLine() + " ");
          result.append("Multiple equation for inherited attribute " +
              sonName() + "." + name() + " in class " + hostClass().name());
          result.append(" and " + equation.name() + " in class " + equation.hostClass().name() +
             " in " + equation.getFileName() + ":" + equation.getStartLine()
          + "\n");
      }
      //else if(!((ASTDecl)hostClass().env().lookup(getComponents().type())).hasInhDeclFor(signature(), new HashSet())) {
        /*
      else if(!((ASTDecl)hostClass().env().lookup(getComponents().type())).hasInhDeclFor(signature())) {
        result.append(getFileName() + ":" + getStartLine() + " ");
        result.append("Missing declaration for equation " + getComponents().name() + "." + name() +
          " in class " + hostClass().name() + "\n");
      }*/
      else if(hasIndex() && !(getComponents() instanceof ListComponents) && !getSonName().equals("getChild")) {
        result.append(getFileName() + ":" + getStartLine() + " ");
        result.append("May not supply index for non list child " + sonName() + 
          " in class " + hostClass().name() + "\n");
      }
    }
    return result.toString();
  }

    protected boolean getComponents_visited = false;
    // Declared in JragCodeGen.jrag at line 1271
    public Components getComponents() {
boolean interruptedCircle = false;
        if(getComponents_visited)
            throw new RuntimeException("Circular definition of attr: getComponents in class: ");
        getComponents_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        Components getComponents_value = getComponents_compute();
        getComponents_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return getComponents_value;
    }

    private Components getComponents_compute()  {
    TypeDecl c = hostClass();
    if(c != null)
      return c.components(sonName());
    return  null;
  }

    protected boolean sonName_visited = false;
    // Declared in JragCodeGen.jrag at line 1277
    public String sonName() {
boolean interruptedCircle = false;
        if(sonName_visited)
            throw new RuntimeException("Circular definition of attr: sonName in class: ");
        sonName_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String sonName_value = sonName_compute();
        sonName_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return sonName_value;
    }

    private String sonName_compute() {  return  getSonName().startsWith("get") ? getSonName().substring(3) : getSonName();  }

    protected boolean getSonAttrDecl_visited = false;
    // Declared in JragCodeGen.jrag at line 1279
    public AttrDecl getSonAttrDecl() {
boolean interruptedCircle = false;
        if(getSonAttrDecl_visited)
            throw new RuntimeException("Circular definition of attr: getSonAttrDecl in class: ");
        getSonAttrDecl_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        AttrDecl getSonAttrDecl_value = getSonAttrDecl_compute();
        getSonAttrDecl_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return getSonAttrDecl_value;
    }

    private AttrDecl getSonAttrDecl_compute()  {
    AttrDecl decl = hostClass().lookupSynDeclPrefix(sonName());
    if(decl == null) decl = hostClass().lookupInhDeclPrefix(sonName());
    return decl;
  }

    protected boolean decl_visited = false;
    protected boolean decl_computed = false;
    protected AttrDecl decl_value;
    // Declared in NameBinding.jrag at line 397
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

    private AttrDecl decl_compute() {  return  hostClass().getInhDeclFor(signature(), new LinkedHashSet());  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
