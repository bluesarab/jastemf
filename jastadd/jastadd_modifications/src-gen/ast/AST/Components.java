
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public abstract class Components extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
        astError_visited = false;
        className_visited = false;
        hostClass_visited = false;
        env_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        Components node = (Components)super.clone();
        node.astError_visited = false;
        node.className_visited = false;
        node.hostClass_visited = false;
        node.env_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in ComponentsUtil.jrag at line 61

  public boolean isNTA() {
    return false;
  }

    // Declared in Errorcheck.jrag at line 113

  
  public boolean declared() {
    return env().lookup(type()) != null;
  }

    // Declared in JaddCodeGen.jrag at line 861

  public void jjtGenCheckTreeStructure(PrintWriter stream, int j) {
  }

    // Declared in JaddCodeGen.jrag at line 1164


  public abstract void jaddGen(PrintWriter stream, int index, boolean publicModifier, ASTDecl decl);

    // Declared in JaddCodeGen.jrag at line 1424


  protected void parse(String s) {
    jrag.AST.JragParser jp = new jrag.AST.JragParser(new java.io.StringReader(s));
    jp.root = hostClass().env();
    jp.setFileName(hostClass().getFileName());
    try {
      while(true)
        jp.AspectBodyDeclaration();
    } catch (Exception e) {
    }
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 60

    public Components(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public Components(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public Components() {
        this(0);


    }

    // Declared in Ast.ast at line 16


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "Components");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 23


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 27


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 32


public void checkChild(Node n, int i) {
}

    // Declared in Ast.ast at line 35


  public int getNumChild() {
    return 0;
  }

    // Declared in Ast.ast at line 38

  public boolean mayHaveRewrite() { return false; }

    // Declared in ComponentsUtil.jrag at line 79
    public abstract String constrParmType();
    // Declared in NameBinding.jrag at line 64
    public abstract String name();
    // Declared in NameBinding.jrag at line 80
    public abstract String type();
    protected boolean astError_visited = false;
    // Declared in Errorcheck.jrag at line 96
    public String astError() {
boolean interruptedCircle = false;
        if(astError_visited)
            throw new RuntimeException("Circular definition of attr: astError in class: ");
        astError_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String astError_value = astError_compute();
        astError_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return astError_value;
    }

    private String astError_compute()  {
    StringBuffer result = new StringBuffer();

    if(hostClass().lookupComponents(name()) != this) {
      result.append(hostClass().getFileName() + ":" + hostClass().getStartLine() + " ");
      result.append("Component name " + name() + " is not unique in " +
          "production rule for " + hostClass().name() + "\n");
    }

    if(!declared()) {
      result.append(hostClass().getFileName() + ":" + hostClass().getStartLine() + " ");
      result.append("Type " + type() + " of component " + name() +
          " in production " + hostClass().name() + " is not declared\n");
    }
    return result.toString();
  }

    protected boolean className_visited = false;
    // Declared in NameBinding.jrag at line 95
    public String className() {
boolean interruptedCircle = false;
        if(className_visited)
            throw new RuntimeException("Circular definition of attr: className in class: ");
        className_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String className_value = className_compute();
        className_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return className_value;
    }

    private String className_compute() {  return  hostClass().name();  }

    protected boolean hostClass_visited = false;
    // Declared in ComponentsUtil.jrag at line 87
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

    protected boolean env_visited = false;
    // Declared in NameBinding.jrag at line 7
    public Grammar env() {
boolean interruptedCircle = false;
        if(env_visited)
            throw new RuntimeException("Circular definition of attr: env in class: ");
        env_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        Grammar env_value = getParent().Define_Grammar_env(this, null);
        env_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return env_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
