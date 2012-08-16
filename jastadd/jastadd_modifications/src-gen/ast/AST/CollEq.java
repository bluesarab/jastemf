
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;
 // RightHandSide?
public class CollEq extends AttrEq implements Cloneable {
    public void flushCache() {
        super.flushCache();
        contributionSignature_visited = false;
        contributionSignature_computed = false;
        contributionSignature_value = null;
        collectingSignature_visited = false;
        attributeSignature_visited = false;
        decl_visited = false;
        decl_computed = false;
        decl_value = null;
        error_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        CollEq node = (CollEq)super.clone();
        node.contributionSignature_visited = false;
        node.contributionSignature_computed = false;
        node.contributionSignature_value = null;
        node.collectingSignature_visited = false;
        node.attributeSignature_visited = false;
        node.decl_visited = false;
        node.decl_computed = false;
        node.decl_value = null;
        node.error_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          CollEq node = (CollEq)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        CollEq res = (CollEq)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 52

    public CollEq(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public CollEq(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public CollEq() {
        this(0);

        setChild(new List(), 0);
        setChild(new List(), 1);

    }

    // Declared in Ast.ast at line 19


    // Declared in Ast.ast line 52
    public CollEq(List p0, String p1, String p2, int p3, int p4, String p5, String p6, List p7, String p8, String p9, boolean p10, String p11) {
        setChild(p0, 0);
        setName(p1);
        setFileName(p2);
        setStartLine(p3);
        setEndLine(p4);
        setComment(p5);
        setAspectName(p6);
        setChild(p7, 1);
        setTargetName(p8);
        setTargetAttributeName(p9);
        setRefSet(p10);
        setReference(p11);
    }

    // Declared in Ast.ast at line 34


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "CollEq"+ "\"" + getName() + "\""+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getComment() + "\""+ "\"" + getAspectName() + "\""+ "\"" + getTargetName() + "\""+ "\"" + getTargetAttributeName() + "\""+ "\"" + getRefSet() + "\""+ "\"" + getReference() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 41


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 45


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 50


public void checkChild(Node n, int i) {
  if(i == 0) {
    if(!(n instanceof List)) throw new Error("Child number 0 of CollEq has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Parameter)) throw new Error("Child number " + k + " in ParameterList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Parameter");
  }
  if(i == 1) {
    if(!(n instanceof List)) throw new Error("Child number 1 of CollEq has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Contribution)) throw new Error("Child number " + k + " in ContributionList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Contribution");
  }
}

    // Declared in Ast.ast at line 63


  public int getNumChild() {
    return 2;
  }

    // Declared in Ast.ast at line 66

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 52
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
    // Declared in Ast.ast line 52
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
    // Declared in Ast.ast line 52
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
    // Declared in Ast.ast line 52
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
    // Declared in Ast.ast line 52
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
    // Declared in Ast.ast line 52
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
    // Declared in Ast.ast line 52
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
    // Declared in Ast.ast line 52
    public void setContributionList(List list) {
        setChild(list, 1);
    }

    // Declared in Ast.ast at line 6


    public int getNumContribution() {
        return getContributionList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public Contribution getContribution(int i) {
        return (Contribution)getContributionList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addContribution(Contribution node) {
        List list = getContributionList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setContribution(Contribution node, int i) {
        List list = getContributionList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getContributionList() {
        return (List)getChild(1);
    }

    // Declared in Ast.ast at line 27


    public List getContributionListNoTransform() {
        return (List)getChildNoTransform(1);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 52
    private String tokenString_TargetName;

    // Declared in Ast.ast at line 3

    public void setTargetName(String value) {
        tokenString_TargetName = value;
    }

    // Declared in Ast.ast at line 6

    public String getTargetName() {
        return tokenString_TargetName;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 52
    private String tokenString_TargetAttributeName;

    // Declared in Ast.ast at line 3

    public void setTargetAttributeName(String value) {
        tokenString_TargetAttributeName = value;
    }

    // Declared in Ast.ast at line 6

    public String getTargetAttributeName() {
        return tokenString_TargetAttributeName;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 52
    private boolean tokenboolean_RefSet;

    // Declared in Ast.ast at line 3

    public void setRefSet(boolean value) {
        tokenboolean_RefSet = value;
    }

    // Declared in Ast.ast at line 6

    public boolean getRefSet() {
        return tokenboolean_RefSet;
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 52
    private String tokenString_Reference;

    // Declared in Ast.ast at line 3

    public void setReference(String value) {
        tokenString_Reference = value;
    }

    // Declared in Ast.ast at line 6

    public String getReference() {
        return tokenString_Reference;
    }

    protected boolean contributionSignature_visited = false;
    protected boolean contributionSignature_computed = false;
    protected String contributionSignature_value;
    // Declared in CollectionAttributes.jrag at line 327
    public String contributionSignature() {
        if(contributionSignature_computed)
            return contributionSignature_value;
boolean interruptedCircle = false;
        if(contributionSignature_visited)
            throw new RuntimeException("Circular definition of attr: contributionSignature in class: ");
        contributionSignature_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        contributionSignature_value = contributionSignature_compute();
        if(isFinal && num == boundariesCrossed)
            contributionSignature_computed = true;
        contributionSignature_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return contributionSignature_value;
    }

    private String contributionSignature_compute() {  return  ((CollDecl)decl()).getTarget() + "_" + attributeSignature();  }

    protected boolean collectingSignature_visited = false;
    // Declared in CollectionAttributes.jrag at line 602
    public String collectingSignature() {
boolean interruptedCircle = false;
        if(collectingSignature_visited)
            throw new RuntimeException("Circular definition of attr: collectingSignature in class: ");
        collectingSignature_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String collectingSignature_value = collectingSignature_compute();
        collectingSignature_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return collectingSignature_value;
    }

    private String collectingSignature_compute() {  return  decl().collectingSignature();  }

    protected boolean attributeSignature_visited = false;
    // Declared in CollectionAttributes.jrag at line 611
    public String attributeSignature() {
boolean interruptedCircle = false;
        if(attributeSignature_visited)
            throw new RuntimeException("Circular definition of attr: attributeSignature in class: ");
        attributeSignature_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String attributeSignature_value = attributeSignature_compute();
        attributeSignature_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return attributeSignature_value;
    }

    private String attributeSignature_compute() {  return  decl().attributeSignature();  }

    protected boolean decl_visited = false;
    protected boolean decl_computed = false;
    protected AttrDecl decl_value;
    // Declared in CollectionAttributes.jrag at line 748
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

    private AttrDecl decl_compute()  {
    TypeDecl typeDecl = hostClass().env().lookup(getTargetName());
    if(typeDecl != null /*&& typeDecl instanceof ASTDecl*/) {
      TypeDecl astDecl = (TypeDecl)typeDecl;
      for(int i = 0; i < astDecl.getNumCollDecl(); i++)
        if(astDecl.getCollDecl(i).getName().equals(getTargetAttributeName()))
          return astDecl.getCollDecl(i);
    }
    return null;
  }

    protected boolean error_visited = false;
    // Declared in CollectionAttributes.jrag at line 895
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
	if(decl() == null) {
      result.append(getFileName() + ":" + getStartLine() + " ");
      result.append("Undeclared collection attribute " + "\n");
	}
    return result.toString();
  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

    protected void collect_contributors_CollDecl_uses() {
        // Declared in CollectionAttributes.jrag at line 760
        if(decl() != null) {
        {
            CollDecl ref = (CollDecl)(decl());
            if(ref != null)
                ref.CollDecl_uses_contributors.add(this);
        }
        }
        super.collect_contributors_CollDecl_uses();
    }
    protected void contributeTo_CollDecl_CollDecl_uses(HashSet collection) {
        if(decl() != null)
            collection.add(this);
    }

}
