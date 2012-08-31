
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class TokenComponent extends Components implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constrParmType_visited = false;
        name_visited = false;
        type_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        TokenComponent node = (TokenComponent)super.clone();
        node.constrParmType_visited = false;
        node.name_visited = false;
        node.type_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          TokenComponent node = (TokenComponent)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        TokenComponent res = (TokenComponent)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Errorcheck.jrag at line 117


  public boolean declared() {
    return true;
  }

    // Declared in ContainmentGen.jadd at line 346



	  private boolean called = false;

    // Declared in ContainmentGen.jadd at line 347

	  public void jaddGen(PrintWriter stream, int index, boolean publicModifier, ASTDecl decl) {
	    String name = decl.name();
	    StringBuffer buf = new StringBuffer();
	    buf.append("    // Declared in " + hostClass().getFileName() + " line " + hostClass().getStartLine() + "\n");
	    if(decl.redefinesTokenComponent(this)) {
	        buf.append("    protected #TYPE# #HOST#.#VARID#;\n");
	    }
	    // Generate attribute, get- and set-method for the token string
	    buf.append(
	        "    public void #HOST#.set#ID#(#TYPE# value) {\n" +
	        "        #VARID# = value;\n" +
	        "    }\n"
	    );
	    if(!isNTA()) {
	      boolean isStringToken = getTokenId().getTYPE().equals("String") || getTokenId().getTYPE().equals("java.lang.String");
	      if(isStringToken && ASTNode.beaver) {
	        if(decl.redefinesTokenComponent(this)) {
	          buf.append(
	          "    public int #HOST#.#VARID#start;\n" +
	          "    public int #HOST#.#VARID#end;\n"
	          );
	        }
	        buf.append(
	        "    public void #HOST#.set#ID#(beaver.Symbol symbol) {\n" +
	        "        if(symbol.value != null && !(symbol.value instanceof String))\n" +
	        "          throw new UnsupportedOperationException(\"set#ID# is only valid for String lexemes\");\n" +
	        "        #VARID# = (String)symbol.value;\n" +
	        "        #VARID#start = symbol.getStart();\n" +
	        "        #VARID#end = symbol.getEnd();\n" +
	        "    }\n"
	        );
	      }
	      if(isStringToken)
	        buf.append(
	        "    public #TYPE# #HOST#.get#ID#() {\n" +
	        "        return #VARID# != null ? #VARID# : \"\";\n" +
	        "    }\n\n");
	      else
	        buf.append(
	        "    public #TYPE# #HOST#.get#ID#() {\n" +
	        "        return #VARID#;\n" +
	        "    }\n\n");
	    }
	    String s = buf.toString();
	    s = s.replaceAll("#ID#", emf.GenSupport.computeAccName(getTokenId().getID()));
	    s = s.replaceAll("#VARID#", emf.GenSupport.computeVarName(getTokenId().getID()));
	    s = s.replaceAll("#TYPE#", getTokenId().getTYPE());
	    s = s.replaceAll("#TYPEINSIGNATURE#", ASTNode.convTypeNameToSignature(getTokenId().getTYPE()));
	    s = s.replaceAll("#HOST#", name);
	    if(!publicModifier)
	      s = s.replaceAll("    public ", "    private ");
	    //stream.println(s);
	    parse(s);

	  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 70

    public TokenComponent(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public TokenComponent(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public TokenComponent() {
        this(0);

        setChild(null, 0);

    }

    // Declared in Ast.ast at line 18


    // Declared in Ast.ast line 70
    public TokenComponent(TokenId p0) {
        setChild(p0, 0);
    }

    // Declared in Ast.ast at line 22


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "TokenComponent");
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
  if(i == 0 && !(n instanceof TokenId))  throw new Error("Child number 0 of TokenComponent has the type " + n.getClass().getName() + " which is not an instance of TokenId");
}

    // Declared in Ast.ast at line 42


  public int getNumChild() {
    return 1;
  }

    // Declared in Ast.ast at line 45

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 70
    public void setTokenId(TokenId node) {
        setChild(node, 0);
    }

    // Declared in Ast.ast at line 5

    public TokenId getTokenId() {
        return (TokenId)getChild(0);
    }

    // Declared in Ast.ast at line 9


    public TokenId getTokenIdNoTransform() {
        return (TokenId)getChildNoTransform(0);
    }

    protected boolean constrParmType_visited = false;
    // Declared in ComponentsUtil.jrag at line 82
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

    private String constrParmType_compute() {  return  getTokenId().getTYPE();  }

    protected boolean name_visited = false;
    // Declared in NameBinding.jrag at line 67
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

    private String name_compute() {  return  getTokenId().name();  }

    protected boolean type_visited = false;
    // Declared in NameBinding.jrag at line 83
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

    private String type_compute() {  return  getTokenId().type();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
