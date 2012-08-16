
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class Id extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
        name_visited = false;
        type_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        Id node = (Id)super.clone();
        node.name_visited = false;
        node.type_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          Id node = (Id)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        Id res = (Id)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 78

    public Id(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public Id(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public Id() {
        this(0);

        setChild(new Opt(), 0);
        setChild(null, 1);

    }

    // Declared in Ast.ast at line 19


    // Declared in Ast.ast line 78
    public Id(Opt p0, IdUse p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in Ast.ast at line 24


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "Id");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 31


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 35


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 40


public void checkChild(Node n, int i) {
  if(i == 0) {
    if(!(n instanceof Opt)) throw new Error("Child number 0 of Id has the type " + n.getClass().getName() + " which is not an instance of Opt");
    if(((Opt)n).getNumChild() != 0 && !(((Opt)n).getChildNoTransform(0) instanceof NameNode)) throw new Error("Optional NameNode has the type " + ((Opt)n).getChildNoTransform(0).getClass().getName() + " which is not an instance of NameNode");
  }
  if(i == 1 && !(n instanceof IdUse))  throw new Error("Child number 1 of Id has the type " + n.getClass().getName() + " which is not an instance of IdUse");
}

    // Declared in Ast.ast at line 48


  public int getNumChild() {
    return 2;
  }

    // Declared in Ast.ast at line 51

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 78
    public void setNameNodeOpt(Opt opt) {
        setChild(opt, 0);
    }

    // Declared in Ast.ast at line 6


    public boolean hasNameNode() {
        return getNameNodeOpt().getNumChild() != 0;
    }

    // Declared in Ast.ast at line 10


    public NameNode getNameNode() {
        return (NameNode)getNameNodeOpt().getChild(0);
    }

    // Declared in Ast.ast at line 14


    public void setNameNode(NameNode node) {
        getNameNodeOpt().setChild(node, 0);
    }

    // Declared in Ast.ast at line 17

    public Opt getNameNodeOpt() {
        return (Opt)getChild(0);
    }

    // Declared in Ast.ast at line 21


    public Opt getNameNodeOptNoTransform() {
        return (Opt)getChildNoTransform(0);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 78
    public void setIdUse(IdUse node) {
        setChild(node, 1);
    }

    // Declared in Ast.ast at line 5

    public IdUse getIdUse() {
        return (IdUse)getChild(1);
    }

    // Declared in Ast.ast at line 9


    public IdUse getIdUseNoTransform() {
        return (IdUse)getChildNoTransform(1);
    }

    protected boolean name_visited = false;
    // Declared in NameBinding.jrag at line 70
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

    private String name_compute() {  return  hasNameNode() ?
    getNameNode().name() : getIdUse().name();  }

    protected boolean type_visited = false;
    // Declared in NameBinding.jrag at line 86
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

    private String type_compute() {  return  getIdUse().name();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
