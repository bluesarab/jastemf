
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public abstract class AttrEq extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
        parametersDecl_visited = false;
        parameters_visited = false;
        interfaceParameters_visited = false;
        interfaceParametersDecl_visited = false;
        interfaceParametersStart_visited = false;
        interfaceParametersContinue_visited = false;
        separateEvaluation_visited = false;
        lazyCondition_visited = false;
        onePhase_visited = false;
        naive_visited = false;
        implType_visited = false;
        getTypeInSignature_visited = false;
        name_visited = false;
        type_visited = false;
        signature_visited = false;
        hostClass_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        AttrEq node = (AttrEq)super.clone();
        node.parametersDecl_visited = false;
        node.parameters_visited = false;
        node.interfaceParameters_visited = false;
        node.interfaceParametersDecl_visited = false;
        node.interfaceParametersStart_visited = false;
        node.interfaceParametersContinue_visited = false;
        node.separateEvaluation_visited = false;
        node.lazyCondition_visited = false;
        node.onePhase_visited = false;
        node.naive_visited = false;
        node.implType_visited = false;
        node.getTypeInSignature_visited = false;
        node.name_visited = false;
        node.type_visited = false;
        node.signature_visited = false;
        node.hostClass_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in Attributes.jrag at line 300



  public String aspectName() {
    return getAspectName();
  }

    // Declared in Attributes.jrag at line 303

  public String legacyAspectName() {
    String name = getFileName();
     if(name.endsWith(".jrag"))
      name = name.substring(0, name.length() - 5);
    else if(name.endsWith(".jadd"))
      name = name.substring(0, name.length() - 5);
    else if(name.endsWith(".ast"))
      name = name.substring(0, name.length() - 4);
    String pattern = File.separator.equals("\\") ? pattern = "\\\\" : File.separator;
    String[] names = name.split(pattern);
    return names[names.length-1];
  }

    // Declared in Attributes.jrag at line 369

  public String replacesAspect = null;

    // Declared in Attributes.jrag at line 409


  public String refinesAspect = null;

    // Declared in Attributes.jrag at line 940



  public jrag.AST.SimpleNode rhs;

    // Declared in Attributes.jrag at line 941

  public void setRHS(jrag.AST.SimpleNode node) {
    rhs = node;
  }

    // Declared in Attributes.jrag at line 944

  public jrag.AST.SimpleNode getRHS() {
    return rhs;
  }

    // Declared in JragCodeGen.jrag at line 282

  
  public String attributeSignature() {
    if(getNumParameter() == 0)
      return getName();
    StringBuffer s = new StringBuffer();
    s.append(getName());
    for(int i = 0; i < getNumParameter(); i++) {
      s.append("_" + getParameter(i).getTypeInSignature());
    }
    return s.toString();
  }

    // Declared in JragCodeGen.jrag at line 293


  public String attributeName() {
    return getName();
  }

    // Declared in JragCodeGen.jrag at line 312

  public String hostFileComment() {
    String comment = getComment().trim();
    if(comment.equals(""))
      comment = decl().getComment().trim();
    if(comment.equals("") || comment.indexOf("/**") == -1)
      return "    // Declared in " + getFileName() + " at line " + getStartLine() + "\n";
    else {
      int index = comment.indexOf("*/");
      StringBuffer res = new StringBuffer();
      res.append(comment.substring(0, index));
      res.append("\n");
      res.append("    Declared in " + getFileName() + " at line " + getStartLine() + "\n");
      res.append(comment.substring(index, comment.length()));
      res.append("\n");
      return res.toString();
    }
  }

    // Declared in JragCodeGen.jrag at line 695

  
  public String computeMethod() {
    return "";
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 49

    public AttrEq(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public AttrEq(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public AttrEq() {
        this(0);

        setChild(new List(), 0);

    }

    // Declared in Ast.ast at line 18


    // Declared in Ast.ast line 49
    public AttrEq(List p0, String p1, String p2, int p3, int p4, String p5, String p6) {
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
        pStream.println(indent + "AttrEq"+ "\"" + getName() + "\""+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getComment() + "\""+ "\"" + getAspectName() + "\"");
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
    if(!(n instanceof List)) throw new Error("Child number 0 of AttrEq has the type " + n.getClass().getName() + " which is not an instance of List");
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
    // Declared in Ast.ast line 49
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
    // Declared in Ast.ast line 49
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
    // Declared in Ast.ast line 49
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
    // Declared in Ast.ast line 49
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
    // Declared in Ast.ast line 49
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
    // Declared in Ast.ast line 49
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
    // Declared in Ast.ast line 49
    private String tokenString_AspectName;

    // Declared in Ast.ast at line 3

    public void setAspectName(String value) {
        tokenString_AspectName = value;
    }

    // Declared in Ast.ast at line 6

    public String getAspectName() {
        return tokenString_AspectName;
    }

    // Declared in Errorcheck.jrag at line 72
    public abstract String error();
    // Declared in NameBinding.jrag at line 396
    public abstract AttrDecl decl();
    protected boolean parametersDecl_visited = false;
    // Declared in Attributes.jrag at line 948
    public String parametersDecl() {
boolean interruptedCircle = false;
        if(parametersDecl_visited)
            throw new RuntimeException("Circular definition of attr: parametersDecl in class: ");
        parametersDecl_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String parametersDecl_value = parametersDecl_compute();
        parametersDecl_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return parametersDecl_value;
    }

    private String parametersDecl_compute()  {
    StringBuffer s = new StringBuffer();
    for(int i = 0; i < getNumParameter(); i++) {
      Parameter p = getParameter(i);
      s.append(p.getType() + " " + p.getName());
      if(i < getNumParameter() - 1) s.append(", ");
    }
    return s.toString();
  }

    protected boolean parameters_visited = false;
    // Declared in Attributes.jrag at line 958
    public String parameters() {
boolean interruptedCircle = false;
        if(parameters_visited)
            throw new RuntimeException("Circular definition of attr: parameters in class: ");
        parameters_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String parameters_value = parameters_compute();
        parameters_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return parameters_value;
    }

    private String parameters_compute()  {
    StringBuffer s = new StringBuffer();
    for(int i = 0; i < getNumParameter(); i++) {
      Parameter p = getParameter(i);
      s.append(p.getName());
      if(i < getNumParameter() - 1) s.append(", ");
    }
    return s.toString();
  }

    protected boolean interfaceParameters_visited = false;
    // Declared in Attributes.jrag at line 991
    public String interfaceParameters() {
boolean interruptedCircle = false;
        if(interfaceParameters_visited)
            throw new RuntimeException("Circular definition of attr: interfaceParameters in class: ");
        interfaceParameters_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String interfaceParameters_value = interfaceParameters_compute();
        interfaceParameters_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return interfaceParameters_value;
    }

    private String interfaceParameters_compute() {  return  "caller, child" + (parameters().equals("") ? "" : (", " + parameters()));  }

    protected boolean interfaceParametersDecl_visited = false;
    // Declared in Attributes.jrag at line 992
    public String interfaceParametersDecl() {
boolean interruptedCircle = false;
        if(interfaceParametersDecl_visited)
            throw new RuntimeException("Circular definition of attr: interfaceParametersDecl in class: ");
        interfaceParametersDecl_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String interfaceParametersDecl_value = interfaceParametersDecl_compute();
        interfaceParametersDecl_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return interfaceParametersDecl_value;
    }

    private String interfaceParametersDecl_compute() {  return  "ASTNode caller, ASTNode child" + (parametersDecl().equals("") ? "" : (", " + parametersDecl()));  }

    protected boolean interfaceParametersStart_visited = false;
    // Declared in Attributes.jrag at line 1036
    public String interfaceParametersStart() {
boolean interruptedCircle = false;
        if(interfaceParametersStart_visited)
            throw new RuntimeException("Circular definition of attr: interfaceParametersStart in class: ");
        interfaceParametersStart_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String interfaceParametersStart_value = interfaceParametersStart_compute();
        interfaceParametersStart_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return interfaceParametersStart_value;
    }

    private String interfaceParametersStart_compute() {  return  "this, null" + (parameters().equals("") ? "" : (", " + parameters()));  }

    protected boolean interfaceParametersContinue_visited = false;
    // Declared in Attributes.jrag at line 1037
    public String interfaceParametersContinue() {
boolean interruptedCircle = false;
        if(interfaceParametersContinue_visited)
            throw new RuntimeException("Circular definition of attr: interfaceParametersContinue in class: ");
        interfaceParametersContinue_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String interfaceParametersContinue_value = interfaceParametersContinue_compute();
        interfaceParametersContinue_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return interfaceParametersContinue_value;
    }

    private String interfaceParametersContinue_compute() {  return  "this, caller" + (parameters().equals("") ? "" : (", " + parameters()));  }

    protected boolean separateEvaluation_visited = false;
    // Declared in CollectionAttributes.jrag at line 326
    public boolean separateEvaluation() {
boolean interruptedCircle = false;
        if(separateEvaluation_visited)
            throw new RuntimeException("Circular definition of attr: separateEvaluation in class: ");
        separateEvaluation_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean separateEvaluation_value = separateEvaluation_compute();
        separateEvaluation_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return separateEvaluation_value;
    }

    private boolean separateEvaluation_compute() {  return  decl().separateEvaluation();  }

    protected boolean lazyCondition_visited = false;
    // Declared in CollectionAttributes.jrag at line 328
    public boolean lazyCondition() {
boolean interruptedCircle = false;
        if(lazyCondition_visited)
            throw new RuntimeException("Circular definition of attr: lazyCondition in class: ");
        lazyCondition_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean lazyCondition_value = lazyCondition_compute();
        lazyCondition_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return lazyCondition_value;
    }

    private boolean lazyCondition_compute() {  return  decl().lazyCondition();  }

    protected boolean onePhase_visited = false;
    // Declared in CollectionAttributes.jrag at line 331
    public boolean onePhase() {
boolean interruptedCircle = false;
        if(onePhase_visited)
            throw new RuntimeException("Circular definition of attr: onePhase in class: ");
        onePhase_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean onePhase_value = onePhase_compute();
        onePhase_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return onePhase_value;
    }

    private boolean onePhase_compute() {  return  decl().onePhase();  }

    protected boolean naive_visited = false;
    // Declared in CollectionAttributes.jrag at line 334
    public boolean naive() {
boolean interruptedCircle = false;
        if(naive_visited)
            throw new RuntimeException("Circular definition of attr: naive in class: ");
        naive_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean naive_value = naive_compute();
        naive_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return naive_value;
    }

    private boolean naive_compute() {  return  decl().naive();  }

    protected boolean implType_visited = false;
    // Declared in NameBinding.jrag at line 16
    public String implType() {
boolean interruptedCircle = false;
        if(implType_visited)
            throw new RuntimeException("Circular definition of attr: implType in class: ");
        implType_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String implType_value = implType_compute();
        implType_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return implType_value;
    }

    private String implType_compute() {  return  computeImplName(type());  }

    protected boolean getTypeInSignature_visited = false;
    // Declared in NameBinding.jrag at line 30
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

    private String getTypeInSignature_compute()  {
    if(decl() != null) return decl().getTypeInSignature();
    throw new Error(getFileName() + ":" + getStartLine() + " could not find decl for AttrEq " + signature() + " in " + hostClass().name());
  }

    protected boolean name_visited = false;
    // Declared in NameBinding.jrag at line 35
    public String name() {
boolean interruptedCircle = false;
        if(name_visited)
            throw new RuntimeException("Circular definition of attr: name in class: ");
        name_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String name_value = name_compute();
        name_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return name_value;
    }

    private String name_compute() {  return  getName();  }

    protected boolean type_visited = false;
    // Declared in NameBinding.jrag at line 36
    public String type() {
boolean interruptedCircle = false;
        if(type_visited)
            throw new RuntimeException("Circular definition of attr: type in class: ");
        type_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String type_value = type_compute();
        type_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return type_value;
    }

    private String type_compute()  {
    if(decl() != null) return decl().type();
    throw new Error(getFileName() + ":" + getStartLine() + " could not find decl for AttrEq " + signature() + " in " + hostClass().name());
  }

    protected boolean signature_visited = false;
    // Declared in NameBinding.jrag at line 51
    public String signature() {
boolean interruptedCircle = false;
        if(signature_visited)
            throw new RuntimeException("Circular definition of attr: signature in class: ");
        signature_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String signature_value = signature_compute();
        signature_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return signature_value;
    }

    private String signature_compute()  {
    if(getNumParameter() == 0)
      return getName();
    StringBuffer s = new StringBuffer();
    s.append(getName());
    for(int i = 0; i < getNumParameter(); i++) {
      s.append("_" + getParameter(i).getTypeInSignature());
    }
    return s.toString();
  }

    protected boolean hostClass_visited = false;
    // Declared in NameBinding.jrag at line 7
    public TypeDecl hostClass() {
boolean interruptedCircle = false;
        if(hostClass_visited)
            throw new RuntimeException("Circular definition of attr: hostClass in class: ");
        hostClass_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        TypeDecl hostClass_value = getParent().Define_TypeDecl_hostClass(this, null);
        hostClass_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return hostClass_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
