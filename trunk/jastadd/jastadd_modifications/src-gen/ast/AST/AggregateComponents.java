
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class AggregateComponents extends Components implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constrParmType_visited = false;
        name_visited = false;
        type_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        AggregateComponents node = (AggregateComponents)super.clone();
        node.constrParmType_visited = false;
        node.name_visited = false;
        node.type_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          AggregateComponents node = (AggregateComponents)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        AggregateComponents res = (AggregateComponents)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in JaddCodeGen.jrag at line 886

  public void jjtGenCheckTreeStructure(PrintWriter stream, int j) {
    stream.println("  if(i == " + j + " && !(n instanceof " + type() + ")) " + 
        " throw new Error(\"Child number " + j + " of " + hostClass().name() +
        " has the type \" + n.getClass().getName() + \" which is not an instance of " + type() + "\");");
  }

    // Declared in JaddCodeGen.jrag at line 1381


  public void jaddGen(PrintWriter stream, int index, boolean publicModifier, ASTDecl decl) {
    String name = decl.name();
    String s;
    // Generate get-method for component
    s = "    // Declared in " + hostClass().getFileName() + " line " + hostClass().getStartLine() + "\n" +
        "    public void #HOST#.set#NAME#(#TYPE# node) {\n" + 
        (ASTNode.block ? ASTNode.blockBegin : "") +
        "        setChild(node, #INDEX#);\n" + 
        (ASTNode.block ? ASTNode.blockEnd : "") +
        "    }\n";
    if(!isNTA()) {
      s = s +
        "    public #TYPE# #HOST#.get#NAME#() {\n" +
        (ASTNode.block ? ASTNode.blockBegin : "") +
        "        return (#TYPE#)getChild(#INDEX#);\n" + 
        (ASTNode.block ? ASTNode.blockEnd : "") +
        "    }\n\n" + 
        "    public #TYPE# #HOST#.get#NAME#NoTransform() {\n" +
        (ASTNode.block ? ASTNode.blockBegin : "") +
        "        return (#TYPE#)getChildNoTransform(#INDEX#);\n" + 
        (ASTNode.block ? ASTNode.blockEnd : "") +
        "    }\n\n";
    }
    else {
        s = s + "    public #TYPE# #HOST#.get#NAME#NoTransform() {\n" +
        (ASTNode.block ? ASTNode.blockBegin : "") +
        "        return (#TYPE#)getChildNoTransform(#INDEX#);\n" + 
        (ASTNode.block ? ASTNode.blockEnd : "") +
        "    }\n\n" +
        "    protected int #HOST#.get#NAME#ChildPosition() {\n" +
        "        return #INDEX#;\n" +
        "    }\n\n";
    }
    s = s.replaceAll("#TYPE#", computeImplName(getId().type()));
    s = s.replaceAll("#NAME#", getId().name());
    s = s.replaceAll("#INDEX#", String.valueOf(index));
    s = s.replaceAll("#HOST#", name);
    if(!publicModifier)
      s = s.replaceAll("    public ", "    private ");
    //stream.println(s);
    parse(s);
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 74

    public AggregateComponents(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public AggregateComponents(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public AggregateComponents() {
        this(0);

        setChild(null, 0);

    }

    // Declared in Ast.ast at line 18


    // Declared in Ast.ast line 74
    public AggregateComponents(Id p0) {
        setChild(p0, 0);
    }

    // Declared in Ast.ast at line 22


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "AggregateComponents");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 29


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 33


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 38


public void checkChild(Node n, int i) {
  if(i == 0 && !(n instanceof Id))  throw new Error("Child number 0 of AggregateComponents has the type " + n.getClass().getName() + " which is not an instance of Id");
}

    // Declared in Ast.ast at line 42


  public int getNumChild() {
    return 1;
  }

    // Declared in Ast.ast at line 45

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 74
    public void setId(Id node) {
        setChild(node, 0);
    }

    // Declared in Ast.ast at line 5

    public Id getId() {
        return (Id)getChild(0);
    }

    // Declared in Ast.ast at line 9


    public Id getIdNoTransform() {
        return (Id)getChildNoTransform(0);
    }

    protected boolean constrParmType_visited = false;
    // Declared in ComponentsUtil.jrag at line 83
    public String constrParmType() {
boolean interruptedCircle = false;
        if(constrParmType_visited)
            throw new RuntimeException("Circular definition of attr: constrParmType in class: ");
        constrParmType_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String constrParmType_value = constrParmType_compute();
        constrParmType_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return constrParmType_value;
    }

    private String constrParmType_compute() {  return  getId().type();  }

    protected boolean name_visited = false;
    // Declared in NameBinding.jrag at line 68
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

    private String name_compute() {  return  getId().name();  }

    protected boolean type_visited = false;
    // Declared in NameBinding.jrag at line 84
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

    private String type_compute() {  return  getId().type();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
