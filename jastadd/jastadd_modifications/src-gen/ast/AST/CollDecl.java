
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class CollDecl extends AttrDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        root_visited = false;
        collectingSignature_visited = false;
        collectingSignature_computed = false;
        collectingSignature_value = null;
        attributeSignature_visited = false;
        attributeSignature_computed = false;
        attributeSignature_value = null;
        error_visited = false;
        knownAnnotation_String_visited = new java.util.HashSet(4);
        CollDecl_uses_visited = false;
        CollDecl_uses_computed = false;
        CollDecl_uses_value = null;
    CollDecl_uses_contributors = new java.util.HashSet();
    }
    public Object clone() throws CloneNotSupportedException {
        CollDecl node = (CollDecl)super.clone();
        node.root_visited = false;
        node.collectingSignature_visited = false;
        node.collectingSignature_computed = false;
        node.collectingSignature_value = null;
        node.attributeSignature_visited = false;
        node.attributeSignature_computed = false;
        node.attributeSignature_value = null;
        node.error_visited = false;
        node.knownAnnotation_String_visited = new java.util.HashSet(4);
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          CollDecl node = (CollDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        CollDecl res = (CollDecl)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in CollectionAttributes.jrag at line 209


  public String cacheCycleCheck() {
    if(!separateEvaluation())
      return super.cacheCycleCheck();
    if (!cacheCycle) return "";
    if(getNumParameter() == 0)
      return
         "            if (state().LAST_CYCLE) {\n" +
         setVisited() +
         circularComputeCall() + ";\n" +
         clearVisited() +
         "                #NAME#_computed = true;\n" +
         "                return #NAME#_value;\n" +
         "            }\n";
     else
       return
         "            if (state().LAST_CYCLE) {\n" +
         setVisited() +
         circularComputeCall() + ";\n" + 
         clearVisited() +
         "                #NAME#_computed.add(_parameters);\n" +
         "                return " + fromReferenceType("#NAME#_values.get(_parameters)" , getType()) + ";\n" + 
         "            }\n";
  }

    // Declared in CollectionAttributes.jrag at line 234


  // only used by circular collection attributes
  public String circularComputeCall() {
    if(separateEvaluation())
      return "new_#NAME#_value = " + getBottomValue().unparse() + ";\n" +
             "root.#TYPEINSIGNATURE#_#NAME#_nextIteration(this)";
    return "#NAME#_value = combine_#NAME#_contributions(" + getBottomValue().unparse() + ")";
  }

    // Declared in CollectionAttributes.jrag at line 242


  // only used by non-circular collection attributes
  public String computeMethod() {
    String rootType = root().name();
    if(separateEvaluation()) // TODO: replace naive() with separateEvaluation()
      return 
      "    private #TYPE# #CLASS#.#METHODNAME#_compute(#PARMDECL#) {\n" +
      "        ASTNode node = this;\n" +
      "        while(node.getParent() != null && !(node instanceof " + rootType + "))\n" +
      "            node = node.getParent();\n" +
      collDebugString() +
      "        " + rootType + " root = (" + rootType + ")node;\n" +
      "        #NAME#_value = " + getBottomValue().unparse() + ";\n" +
      "        root.#TYPEINSIGNATURE#_#NAME#_nextIteration(this);\n" +
      "        return #NAME#_value;\n" +
      "    }\n\n";
    else if(onePhase())
      return 
      "    private #TYPE# #CLASS#.#METHODNAME#_compute(#PARMDECL#) {\n" +
      "        ASTNode node = this;\n" +
      "        while(node.getParent() != null && !(node instanceof " + rootType + "))\n" +
      "            node = node.getParent();\n" +
      collDebugString() +
      "        " + rootType + " root = (" + rootType + ")node;\n" +
      "        root.collect_contributors_#COLLECTINGSIGNATURE#();\n" +
      "        if(#NAME#_value == null) #NAME#_value = " + getBottomValue().unparse() + ";\n" +
      "        return #NAME#_value;\n" +
      "    }\n\n";
    else
      return 
      "    " + typeDefaultSet + " #CLASS#.#NAME#_contributors" + (lazyMaps ? "" : (" = " + ASTNode.createContributorSet)) + ";\n" +
      "    public " + typeDefaultSet + " #CLASS#.#NAME#_contributors() {\n" +
      (lazyMaps ? ("        if(#NAME#_contributors == null) #NAME#_contributors  = " + ASTNode.createContributorSet + ";\n") : "") +
      "        return #NAME#_contributors;\n" +
      "    }\n" +
      "    private #TYPE# #CLASS#.#METHODNAME#_compute(#PARMDECL#) {\n" +
      "        ASTNode node = this;\n" +
      "        while(node.getParent() != null && !(node instanceof " + rootType + "))\n" +
      "            node = node.getParent();\n" +
      collDebugString() +
      "        " + rootType + " root = (" + rootType + ")node;\n" +
      "        root.collect_contributors_#COLLECTINGSIGNATURE#();\n" +
      "        #NAME#_value = " + getBottomValue().unparse() + ";\n" +
      (lazyMaps ? "        if(#NAME#_contributors != null)\n" : "") +
      "        for(java.util.Iterator iter = #NAME#_contributors.iterator(); iter.hasNext(); ) {\n" +
      "            ASTNode contributor = (ASTNode)iter.next();\n" +
      "            contributor.contributeTo_" + getTarget() + "_#NAME#(#NAME#_value);\n" +
      "        }\n" +
      // TODO: disabled temporarily since collections may not be cached
      //(lazyMaps ? "        #NAME#_contributors = null;\n" : "") +
      "        return #NAME#_value;\n" +
      "    }\n\n";
  }

    // Declared in CollectionAttributes.jrag at line 294

  // only used by circular collection attributes
  public String combineMethod() {
    if(separateEvaluation())
      return "";
    
    String rootType = root().name();
    String s = 
      "    " + typeDefaultSet + " #CLASS#.#NAME#_contributors" + (lazyMaps ? "" : (" = " + ASTNode.createContributorSet)) + ";\n" +
      "    public " + typeDefaultSet + " #CLASS#.#NAME#_contributors() {\n" +
      (lazyMaps ? ("        if(#NAME#_contributors == null) #NAME#_contributors  = " + ASTNode.createContributorSet + ";\n") : "") +
      "        return #NAME#_contributors;\n" +
      "    }\n" +
      "    private #TYPE# #CLASS#.combine_#NAME#_contributions(#TYPE# h) {\n" +
      (lazyMaps ? "        if(#NAME#_contributors != null)\n" : "") +
      "        for(java.util.Iterator iter = #NAME#_contributors.iterator(); iter.hasNext(); ) {\n" +
      "            ASTNode contributor = (ASTNode)iter.next();\n" +
      "            contributor.contributeTo_" + getTarget() + "_#NAME#(h);\n" +
      "        }\n" +
      // TODO: disabled temporarily since collections may not be cached
      //(lazyMaps ? "        #NAME#_contributors = null;\n" : "") +
      "        return h;\n" +
      "    }\n\n";
      return s;
  }

    // Declared in CollectionAttributes.jrag at line 787


  public String root = null;

    // Declared in CollectionAttributes.jrag at line 790

  public boolean hasAnnotation(String s) {
  	if(annotations == null) return false;
  	for(Iterator iter = annotations.iterator(); iter.hasNext(); ) {
  		String a = (String)iter.next();
  		if(a.equals(s) || a.startsWith(s + "("))
  			return true;
  	}
  	return false;
  }

    // Declared in CollectionAttributes.jrag at line 799

  public String getAnnotationValue(String s) {
    for(Iterator iter = annotations.iterator(); iter.hasNext(); ) {
      String key = (String)iter.next();
      key = key.replace('(', ',');
      key = key.replace(')', ',');
      if(key.startsWith(s + ",")) {
        String[] strs = key.split(",");
        if(strs.length > 1) {
          return strs[1].substring(1,strs[1].length()-1); // remove quotes
        }
      }
    }
    return null;
  }

    // Declared in CollectionAttributes.jrag at line 813

  private ArrayList annotations;

    // Declared in CollectionAttributes.jrag at line 814

  public void setAnnotations(ArrayList list) {
    annotations = list;
  }

    // Declared in CollectionAttributes.jrag at line 829


  private ASTExpression startValue;

    // Declared in CollectionAttributes.jrag at line 830

  public void setStartValue(ASTExpression e) { startValue = e; }

    // Declared in CollectionAttributes.jrag at line 831

  public ASTExpression getBottomValue() { return startValue; }

    // Declared in CollectionAttributes.jrag at line 832

  private String combOp;

    // Declared in CollectionAttributes.jrag at line 833

  public void setCombOp(String s) { combOp = s; }

    // Declared in CollectionAttributes.jrag at line 834

  public String getCombOp() { return combOp; }

    // Declared in CollectionAttributes.jrag at line 835

  private boolean circularCollection;

    // Declared in CollectionAttributes.jrag at line 836

  public void setCircularCollection(boolean b) { circularCollection = b; }

    // Declared in CollectionAttributes.jrag at line 837

  public boolean circularCollection() { return circularCollection; }

    // Declared in CollectionAttributes.jrag at line 838

  public boolean isCircular() { return circularCollection(); }

    // Declared in CollectionAttributes.jrag at line 872


  private static boolean isValidIdentifierPart(String s) {
    for(int i = 0; i < s.length(); i++)
      if(!Character.isJavaIdentifierPart(s.charAt(i)))
        return false;
    return true;
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 45

    public CollDecl(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public CollDecl(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public CollDecl() {
        this(0);

        setChild(new List(), 0);

    }

    // Declared in Ast.ast at line 18


    // Declared in Ast.ast line 45
    public CollDecl(List p0, String p1, String p2, boolean p3, String p4, int p5, int p6, boolean p7, boolean p8, String p9, String p10, String p11) {
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
        setTarget(p11);
    }

    // Declared in Ast.ast at line 33


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "CollDecl"+ "\"" + getName() + "\""+ "\"" + getType() + "\""+ "\"" + getLazy() + "\""+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getFinal() + "\""+ "\"" + getNTA() + "\""+ "\"" + getComment() + "\""+ "\"" + getAspectName() + "\""+ "\"" + getTarget() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 40


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 44


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 49


public void checkChild(Node n, int i) {
  if(i == 0) {
    if(!(n instanceof List)) throw new Error("Child number 0 of AttrDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Parameter)) throw new Error("Child number " + k + " in ParameterList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Parameter");
  }
}

    // Declared in Ast.ast at line 57


  public int getNumChild() {
    return 1;
  }

    // Declared in Ast.ast at line 60

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

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 45
    private String tokenString_Target;

    // Declared in Ast.ast at line 3

    public void setTarget(String value) {
        tokenString_Target = value;
    }

    // Declared in Ast.ast at line 6

    public String getTarget() {
        return tokenString_Target;
    }

    // Declared in JragCodeGen.jrag at line 108

    public boolean getLazy() {
    return (refined_Ast_getLazy() || cacheAll) && !noCaching;
  }

    protected boolean root_visited = false;
    // Declared in CollectionAttributes.jrag at line 60
    public TypeDecl root() {
boolean interruptedCircle = false;
        if(root_visited)
            throw new RuntimeException("Circular definition of attr: root in class: ");
        root_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        TypeDecl root_value = root_compute();
        root_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return root_value;
    }

    private TypeDecl root_compute() {  return  root != null ? hostClass().env().lookup(root) : super.root();  }

    protected boolean collectingSignature_visited = false;
    // Declared in CollectionAttributes.jrag at line 604
    public String collectingSignature() {
        if(collectingSignature_computed)
            return collectingSignature_value;
boolean interruptedCircle = false;
        if(collectingSignature_visited)
            throw new RuntimeException("Circular definition of attr: collectingSignature in class: ");
        collectingSignature_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        collectingSignature_value = collectingSignature_compute();
        if(isFinal && num == boundariesCrossed)
            collectingSignature_computed = true;
        collectingSignature_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return collectingSignature_value;
    }

    private String collectingSignature_compute()  {
    String value = getAnnotationValue("@CollectionGroup");
    if(value != null)
      return "CollectionGroup_" + value;
    return attributeSignature();
  }

    protected boolean attributeSignature_visited = false;
    protected boolean attributeSignature_computed = false;
    protected String attributeSignature_value;
    // Declared in CollectionAttributes.jrag at line 612
    public String attributeSignature() {
        if(attributeSignature_computed)
            return attributeSignature_value;
boolean interruptedCircle = false;
        if(attributeSignature_visited)
            throw new RuntimeException("Circular definition of attr: attributeSignature in class: ");
        attributeSignature_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        attributeSignature_value = attributeSignature_compute();
        if(isFinal && num == boundariesCrossed)
            attributeSignature_computed = true;
        attributeSignature_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return attributeSignature_value;
    }

    private String attributeSignature_compute()  {
    StringBuffer s = new StringBuffer();
    s.append(getTarget());
    s.append("_");
    s.append(name());
    for(int i = 0; i < getNumParameter(); i++) {
      s.append("_" + getParameter(i).getType());
    }
    return s.toString();
  }

    protected boolean error_visited = false;
    // Declared in CollectionAttributes.jrag at line 841
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
    for(Iterator iter = annotations.iterator(); iter.hasNext(); ) {
      String annotation = (String)iter.next();
      if(!knownAnnotation(annotation)) {
        result.append(getFileName() + ":" + getStartLine() + " ");
        result.append("Unknown annotation: " + annotation + "\n");
      }
    }
    if(hasAnnotation("@CollectionGroup")) {
      String value = getAnnotationValue("@CollectionGroup");
      if(value == null)
        result.append(getFileName() + ":" + getStartLine() + " Missing CollectionGroup argument\n");
      else if(value.equals(""))
        result.append(getFileName() + ":" + getStartLine() + " CollectionGroup argument can not be empty\n");
      else if(!isValidIdentifierPart(value))
        result.append(getFileName() + ":" + getStartLine() + " CollectionGroup argument must be a valid identifier part\n");
    } 
    if(root == null && hostClass().env().roots().size() != 1) {
      result.append(getFileName() + ":" + getStartLine() + " Multiple tree roots to search for contributions. Please explicitly select one of");
      for(Iterator iter = hostClass().env().roots().iterator(); iter.hasNext(); ) {
        ASTDecl decl = (ASTDecl)iter.next();
        result.append(" " + decl.name());
      }
      result.append("\n");
    }
    if(uses().isEmpty())
      result.append(getFileName() + ":" + getStartLine() + " No contributions for this collection attribute\n");
    return result.toString();
  }

    protected java.util.Set knownAnnotation_String_visited = new java.util.HashSet(4);
    // Declared in CollectionAttributes.jrag at line 879
    public boolean knownAnnotation(String s) {
        Object _parameters = s;
boolean interruptedCircle = false;
        if(knownAnnotation_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: knownAnnotation in class: ");
        knownAnnotation_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean knownAnnotation_String_value = knownAnnotation_compute(s);
        knownAnnotation_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return knownAnnotation_String_value;
    }

    private boolean knownAnnotation_compute(String s)  {
    if(s.equals("@OnePhase"))
      return true;
    if(s.equals("@SeparateEvaluation"))
      return true;
    if(s.equals("@LazyCondition"))
      return true;
    if(s.equals("@Circular"))
      return true;
    if(s.equals("@CollectionGroup") || s.startsWith("@CollectionGroup("))
      return true;
    if(s.equals("@Naive"))
      return true;
    return false;
  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

    protected boolean CollDecl_uses_visited = false;
    protected boolean CollDecl_uses_computed = false;
    protected HashSet CollDecl_uses_value;
    // Declared in CollectionAttributes.jrag at line 759
    public HashSet uses() {
        if(CollDecl_uses_computed)
            return CollDecl_uses_value;
boolean interruptedCircle = false;
        if(CollDecl_uses_visited)
            throw new RuntimeException("Circular definition of attr: uses in class: ");
        CollDecl_uses_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        CollDecl_uses_value = uses_compute();
        if(isFinal && num == boundariesCrossed)
            CollDecl_uses_computed = true;
        CollDecl_uses_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return CollDecl_uses_value;
    }

    java.util.HashSet CollDecl_uses_contributors = new java.util.HashSet();
    private HashSet uses_compute() {
        ASTNode node = this;
        while(node.getParent() != null)
            node = node.getParent();
        Grammar root = (Grammar)node;
        root.collect_contributors_CollDecl_uses();
        CollDecl_uses_value = new LinkedHashSet();
        for(java.util.Iterator iter = CollDecl_uses_contributors.iterator(); iter.hasNext(); ) {
            ASTNode contributor = (ASTNode)iter.next();
            contributor.contributeTo_CollDecl_CollDecl_uses(CollDecl_uses_value);
        }
        return CollDecl_uses_value;
    }

}
