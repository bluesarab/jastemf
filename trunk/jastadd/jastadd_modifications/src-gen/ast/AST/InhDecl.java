
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class InhDecl extends AttrDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        error_visited = false;
        declaredNTA_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        InhDecl node = (InhDecl)super.clone();
        node.error_visited = false;
        node.declaredNTA_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          InhDecl node = (InhDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        InhDecl res = (InhDecl)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in JragCodeGen.jrag at line 656


  // TODO: INH
  public String callCompute() {
    if(ASTNode.parentInterface)
      return super.callCompute();
    else {
      if(getLazy() && getNumParameter() == 0)
        return inhDebugString() + 
               "        #NAME#_value = getParent().Define_#TYPEINSIGNATURE#_#METHODNAME#(" + interfaceParametersStart() + ");\n";
      else 
        return inhDebugString() + 
               "        #TYPE# #NAME#_value = getParent().Define_#TYPEINSIGNATURE#_#METHODNAME#(" + interfaceParametersStart() + ");\n";
    }
  }

    // Declared in JragCodeGen.jrag at line 927

  public String circularComputeCall() {
    if(!ASTNode.parentInterface)
      return "getParent().Define_#TYPEINSIGNATURE#_#METHODNAME#(" + interfaceParametersStart() + ")";
    else
      return "#METHODNAME#_compute(#PARM#)";
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 43

    public InhDecl(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public InhDecl(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public InhDecl() {
        this(0);

        setChild(new List(), 0);

    }

    // Declared in Ast.ast at line 18


    // Declared in Ast.ast line 43
    public InhDecl(List p0, String p1, String p2, boolean p3, String p4, int p5, int p6, boolean p7, boolean p8, String p9, String p10) {
        setChild(p0, 0);
        setName(p1);
        setType(p2);
        setLazy(p3);
        setFileName(p4);
        setStartLine(p5);
        setEndLine(p6);
        setFinal(p7);
        setNTA(p8);
        setComment(p9);
        setAspectName(p10);
    }

    // Declared in Ast.ast at line 32


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "InhDecl"+ "\"" + getName() + "\""+ "\"" + getType() + "\""+ "\"" + getLazy() + "\""+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getFinal() + "\""+ "\"" + getNTA() + "\""+ "\"" + getComment() + "\""+ "\"" + getAspectName() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 39


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 43


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 48


public void checkChild(Node n, int i) {
  if(i == 0) {
    if(!(n instanceof List)) throw new Error("Child number 0 of AttrDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Parameter)) throw new Error("Child number " + k + " in ParameterList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Parameter");
  }
}

    // Declared in Ast.ast at line 56


  public int getNumChild() {
    return 1;
  }

    // Declared in Ast.ast at line 59

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 40
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
    // Declared in Ast.ast line 40
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
    // Declared in Ast.ast line 40
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
    // Declared in Ast.ast line 40
    private boolean tokenboolean_Lazy;

    // Declared in Ast.ast at line 3

    public void setLazy(boolean value) {
        tokenboolean_Lazy = value;
    }

    // Declared in Ast.ast at line 6

    public boolean refined_Ast_getLazy() {
        return tokenboolean_Lazy;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 40
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
    // Declared in Ast.ast line 40
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
    // Declared in Ast.ast line 40
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
    // Declared in Ast.ast line 40
    private boolean tokenboolean_Final;

    // Declared in Ast.ast at line 3

    public void setFinal(boolean value) {
        tokenboolean_Final = value;
    }

    // Declared in Ast.ast at line 6

    public boolean getFinal() {
        return tokenboolean_Final;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 40
    private boolean tokenboolean_NTA;

    // Declared in Ast.ast at line 3

    public void setNTA(boolean value) {
        tokenboolean_NTA = value;
    }

    // Declared in Ast.ast at line 6

    public boolean getNTA() {
        return tokenboolean_NTA;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 40
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
    // Declared in Ast.ast line 40
    private String tokenString_AspectName;

    // Declared in Ast.ast at line 3

    public void setAspectName(String value) {
        tokenString_AspectName = value;
    }

    // Declared in Ast.ast at line 6

    public String getAspectName() {
        return tokenString_AspectName;
    }

    // Declared in JragCodeGen.jrag at line 97

    public boolean getLazy() {
    return (refined_Ast_getLazy() || cacheAll) && (!noCaching || declaredNTA());
  }

    protected boolean error_visited = false;
    // Declared in Errorcheck.jrag at line 223
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
    if(!ASTNode.noInhEqCheck) {
     if(hostClass().lookupInhDecl(signature()) != this) {
       result.append(getFileName() + ":" + getStartLine() + " ");
       result.append("Multiple declaration of attribute " + name() +
           " in class " + hostClass().name() + "\n");
     }
     else {
        //System.out.println("Processing " + name() + " in " + hostClass().name());
        HashSet set = new HashSet();
        //LinkedList set = new LinkedList();
        if(hostClass() instanceof ASTDecl) {
          for(Iterator iter = ((ASTDecl)hostClass()).fathers().iterator(); iter.hasNext(); ) {
            ASTDecl father = (ASTDecl)iter.next();
            if(!father.hasInhEqFor((ASTDecl)hostClass(), signature(), set)) {
              result.append(getFileName() + ":" + getStartLine() + " ");
              result.append("Missing inherited equation for attribute " + name() + 
                  " in class " + hostClass().name() + " when being child of " + father.name() + "\n");
              System.out.println(father.name() + "->" + hostClass().name());
              System.out.print(getFileName() + ":" + getStartLine() + " ");
              System.out.print("Missing inherited equation for attribute " + name() + 
                  " in class " + hostClass().name() + " when being child of " + father.name() + "\n");
            }
          }
        }
      }
    }
    return result.toString();
  }

    protected boolean declaredNTA_visited = false;
    // Declared in JragCodeGen.jrag at line 489
    public boolean declaredNTA() {
boolean interruptedCircle = false;
        if(declaredNTA_visited)
            throw new RuntimeException("Circular definition of attr: declaredNTA in class: ");
        declaredNTA_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean declaredNTA_value = declaredNTA_compute();
        declaredNTA_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return declaredNTA_value;
    }

    private boolean declaredNTA_compute() {  return  getNTA();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
