
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class OptionalComponent extends Components implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constrParmType_visited = false;
        name_visited = false;
        type_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        OptionalComponent node = (OptionalComponent)super.clone();
        node.constrParmType_visited = false;
        node.name_visited = false;
        node.type_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          OptionalComponent node = (OptionalComponent)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        OptionalComponent res = (OptionalComponent)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in ContainmentGen.jadd at line 299


	  public void jaddGen(PrintWriter stream, int index, boolean publicModifier, ASTDecl decl) {
	    String name = decl.name();
	    String s;
	    // Generate has- and get-method for the optional component
	    s = "    // Declared in " + hostClass().getFileName() + " line " + hostClass().getStartLine() + "\n" +
	        "    public void #HOST#.set#NAME#Opt(#OPTTYPE# opt) {\n" +
	        "        setChild(opt, #INDEX#);\n" + 
	        "    }\n\n" + 
	        "    public boolean #HOST#.has#NAME#() {\n" + 
	        "        return get#NAME#Opt().getNumChild() != 0;\n" +
	        "    }\n\n" +
	        "    #ANNOTATIONS# public #TYPE# #HOST#.get#NAME#() {\n" +
	        "        return (#TYPE#)get#NAME#Opt().getChild(0);\n" +
	        "    }\n\n" + 
	        "    public void #HOST#.set#NAME#(#TYPE# node) {\n" + 
	        "        get#NAME#Opt().setChild(node, 0);\n" +
	        "    }\n";
	    if(!isNTA()) {
	      s = s +
	        "    #ANNOTATIONS# public #OPTTYPE# #HOST#.get#NAME#Opt() {\n" +
	        "        return (#OPTTYPE#)getChild(#INDEX#);\n" +
	        "    }\n\n" +
	        "    #ANNOTATIONS# public #OPTTYPE# #HOST#.get#NAME#OptNoTransform() {\n" +
	        "        return (#OPTTYPE#)getChildNoTransform(#INDEX#);\n" +
	        "    }\n\n";
	    }
	    else {
	        s = s + "    #ANNOTATIONS# public #OPTTYPE# #HOST#.get#NAME#OptNoTransform() {\n" +
	        "        return (#OPTTYPE#)getChildNoTransform(#INDEX#);\n" +
	        "    }\n\n" +
	        "    protected int #HOST#.get#NAME#OptChildPosition() {\n" +
	        "        return #INDEX#;\n" +
	        "    }\n\n";
	    }
	    s = s.replaceAll("#OPTTYPE#", ASTNode.optName + "<" + computeImplName(getId().type()) + ">" );
	    s = s.replaceAll("#ANNOTATIONS#", ASTNode.suppressWarnings());
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
    // Declared in Ast.ast line 66

    public OptionalComponent(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public OptionalComponent(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public OptionalComponent() {
        this(0);

        setChild(null, 0);

    }

    // Declared in Ast.ast at line 18


    // Declared in Ast.ast line 66
    public OptionalComponent(Id p0) {
        setChild(p0, 0);
    }

    // Declared in Ast.ast at line 22


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "OptionalComponent");
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
  if(i == 0 && !(n instanceof Id))  throw new Error("Child number 0 of OptionalComponent has the type " + n.getClass().getName() + " which is not an instance of Id");
}

    // Declared in Ast.ast at line 42


  public int getNumChild() {
    return 1;
  }

    // Declared in Ast.ast at line 45

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 66
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
    // Declared in ComponentsUtil.jrag at line 81
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

    private String constrParmType_compute() {  return  ASTNode.optName + "<" + computeImplName(getId().type()) + ">";  }

    protected boolean name_visited = false;
    // Declared in NameBinding.jrag at line 66
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
    // Declared in NameBinding.jrag at line 82
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
