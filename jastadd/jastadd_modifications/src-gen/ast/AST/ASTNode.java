
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;

// Generated with JastAdd II (http://jastadd.cs.lth.se) version R20071019

public class ASTNode extends SimpleNode  implements Cloneable {
    public void flushCache() {
        env_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        ASTNode node = (ASTNode)super.clone();
        node.env_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          ASTNode node = (ASTNode)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        ASTNode res = (ASTNode)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Attributes.jrag at line 10

  public static void resetGlobalErrors() { globalErrors = ""; }

    // Declared in Attributes.jrag at line 11

  public static String globalErrors = "";

    // Declared in Attributes.jrag at line 12

  public static void error(String s) {
    globalErrors += s + "\n";
  }

    // Declared in CollectionAttributes.jrag at line 3


  public static String createContributorSet = "new ASTNode$State.IdentityHashSet(4)";

    // Declared in CollectionAttributes.jrag at line 350


  static String filterIterators(String s) {
    if(!j2me)
      return s;
    // if we are using iterators we need to replace them enumerations
    // this is a dirty hack but gets the job done

    s = s.replaceAll("java\\.util\\.Iterator", "java.util.Enumeration");
    s = s.replaceAll("iter\\.hasNext", "iter.hasMoreElements");
    s = s.replaceAll("iter\\.next", "iter.nextElement");
    s = s.replaceAll("iterator", "elements");

    return s;
  }

    // Declared in JragCodeGen.jrag at line 14


  public static boolean jjtree;

    // Declared in JragCodeGen.jrag at line 15

  public static boolean beaver;

    // Declared in JragCodeGen.jrag at line 16

  public static boolean lazyMaps;

    // Declared in JragCodeGen.jrag at line 17

  public static boolean noInhEqCheck;

    // Declared in JragCodeGen.jrag at line 19


  public static boolean aspectJ = false;

    // Declared in JragCodeGen.jrag at line 20

  public static boolean rewriteEnabled = false;

    // Declared in JragCodeGen.jrag at line 21

  public static boolean circularEnabled = true;

    // Declared in JragCodeGen.jrag at line 22

  public static boolean visitCheckEnabled = true;

    // Declared in JragCodeGen.jrag at line 23

  public static boolean traceVisitCheck = false;

    // Declared in JragCodeGen.jrag at line 24

  public static boolean cacheCycle = true;

    // Declared in JragCodeGen.jrag at line 25

  public static boolean componentCheck = true;

    // Declared in JragCodeGen.jrag at line 27


  public static boolean suppressWarnings = false;

    // Declared in JragCodeGen.jrag at line 29


  public static boolean parentInterface = false;

    // Declared in JragCodeGen.jrag at line 31


  public static boolean doc = false;

    // Declared in JragCodeGen.jrag at line 33


  public static String license = "";

    // Declared in JragCodeGen.jrag at line 35


  public static boolean java5 = true;

    // Declared in JragCodeGen.jrag at line 37


  public static boolean refineLegacy = false;

    // Declared in JragCodeGen.jrag at line 38

  public static boolean stagedRewrites = false;

    // Declared in JragCodeGen.jrag at line 41

  
  //--->added options and helpers for JastEMF
  public static String listName = "List";

    // Declared in JragCodeGen.jrag at line 42

  public static String nodeName = "ASTNode";

    // Declared in JragCodeGen.jrag at line 43

  public static String optName = "Opt";

    // Declared in JragCodeGen.jrag at line 44

  public static String implSuffix = "Impl";

    // Declared in JragCodeGen.jrag at line 46

  
  public static boolean isBuiltInType(String typeName) {
	  return ASTNode.listName.equals(typeName)||
			  ASTNode.nodeName.equals(typeName)||
			  ASTNode.optName.equals(typeName);
  }

    // Declared in JragCodeGen.jrag at line 52

  
  public static boolean isBuiltInList(String typeName) {
	  return ASTNode.listName.equals(typeName);
  }

    // Declared in JragCodeGen.jrag at line 56

  
  public static boolean isBuiltInNode(String typeName) {
	  return ASTNode.nodeName.equals(typeName);
  }

    // Declared in JragCodeGen.jrag at line 60

  
  public static boolean isBuiltInOpt(String typeName) {
	  return ASTNode.optName.equals(typeName);
  }

    // Declared in JragCodeGen.jrag at line 67

  
  public String computeImplName(String typeName) {
	  return ASTNode.isBuiltInType(typeName)||env().lookup(typeName)==null?typeName:typeName+implSuffix;
  }

    // Declared in JragCodeGen.jrag at line 76

  //<---JastEMF additions end
  
  //public static int ASTNode.cycleLimit = 100;
  //public static int ASTNode.rewriteLimit = 100;
  //public static boolean ASTNode.debugMode = true;

  public static int cycleLimit = 0;

    // Declared in JragCodeGen.jrag at line 77

  public static int rewriteLimit = 0;

    // Declared in JragCodeGen.jrag at line 78

  public static boolean debugMode = false;

    // Declared in JragCodeGen.jrag at line 80


  public static boolean block = false;

    // Declared in JragCodeGen.jrag at line 81

  public static String blockBegin = "        synchronized(ASTNode.class) {\n";

    // Declared in JragCodeGen.jrag at line 82

  public static String blockEnd =   "        }\n";

    // Declared in JragCodeGen.jrag at line 84


  public static boolean noStatic = false;

    // Declared in JragCodeGen.jrag at line 86

  
  public static String createDefaultMap = "new java.util.HashMap(4)";

    // Declared in JragCodeGen.jrag at line 87

  public static String createDefaultSet = "new java.util.HashSet(4)";

    // Declared in JragCodeGen.jrag at line 88

  public static String typeDefaultMap = "java.util.Map";

    // Declared in JragCodeGen.jrag at line 89

  public static String typeDefaultSet = "java.util.Set";

    // Declared in JragCodeGen.jrag at line 91


  public static boolean deterministic;

    // Declared in JragCodeGen.jrag at line 92

  public static boolean j2me;

    // Declared in JragCodeGen.jrag at line 94


  public static boolean tracing;

    // Declared in JragCodeGen.jrag at line 95

  public static boolean cacheAll = false;

    // Declared in JragCodeGen.jrag at line 96

  public static boolean noCaching = false;

    // Declared in JragCodeGen.jrag at line 221


  public static String suppressWarnings() {
    return (suppressWarnings || java5) ? " @SuppressWarnings({\"unchecked\", \"cast\"}) " : "";
  }

    // Declared in JragCodeGen.jrag at line 543

  
  public String toReferenceType(String value, String type) {
    if(type.equals("int"))
      return java5 ? "Integer.valueOf(" + value + ")" : "new Integer(" + value + ")";
    else if(type.equals("short"))
      return java5 ? "Short.valueOf(" + value + ")" : "new Short(" + value + ")";
    else if(type.equals("long"))
      return java5 ? "Long.valueOf(" + value + ")" : "new Long(" + value + ")";
    else if(type.equals("float"))
      return java5 ? "Float.valueOf(" + value + ")" : "new Float(" + value + ")";
    else if(type.equals("double"))
      return java5 ? "Double.valueOf(" + value + ")" : "new Double(" + value + ")";
    else if(type.equals("boolean"))
      return j2me ? ("new Boolean(" + value + ")") : ("Boolean.valueOf(" + value + ")");
    else if(type.equals("char"))
      return java5 ? "Character.valueOf(" + value + ")" : "new Character(" + value + ")";
    else return value;
  }

    // Declared in JragCodeGen.jrag at line 561

  
  public String fromReferenceType(String value, String type) {
    if(type.equals("int"))
      return "((Integer)" + value + ").intValue()";
    else if(type.equals("short"))
      return "((Short)" + value + ").shortValue()";
    else if(type.equals("long"))
      return "((Long)" + value + ").longValue()";
    else if(type.equals("float"))
      return "((Float)" + value + ").floatValue()";
    else if(type.equals("double"))
      return "((Double)" + value + ").doubleValue()";
    else if(type.equals("boolean"))
      return "((Boolean)" + value + ").booleanValue()";
    else if(type.equals("char"))
      return "((Character)" + value + ").charValue()";
    else
      return "(" + type + ")" + value;
  }

    // Declared in NameBinding.jrag at line 20

  public static String convTypeNameToSignature(String s) {
    s = s.replace('.', '_');
    s = s.replace(' ', '_');
    s = s.replace(',', '_');
    s = s.replace('<', '_');
    s = s.replace('>', '_');
    s = s.replace('[', '_');
    s = s.replace(']', 'a');
    return s;
  }

    // Declared in ASTNode.ast at line 3
    // Declared in ASTNode.ast line 0

    public ASTNode(int i) {
        super(i);
    }

    // Declared in ASTNode.ast at line 6

    public ASTNode(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in ASTNode.ast at line 10

    public ASTNode() {
        this(0);


    }

    // Declared in ASTNode.ast at line 16


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "ASTNode");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in ASTNode.ast at line 23


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in ASTNode.ast at line 27


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in ASTNode.ast at line 32


public void checkChild(Node n, int i) {
}

    // Declared in ASTNode.ast at line 35


   static public boolean generatedWithCircularEnabled = true;

    // Declared in ASTNode.ast at line 36

   static public boolean generatedWithCacheCycle = true;

    // Declared in ASTNode.ast at line 37

   static public boolean generatedWithComponentCheck = true;

    // Declared in ASTNode.ast at line 38

  static public boolean IN_CIRCLE = false;

    // Declared in ASTNode.ast at line 39

  static public boolean CHANGE = false;

    // Declared in ASTNode.ast at line 40

  static public boolean LAST_CYCLE = false;

    // Declared in ASTNode.ast at line 41

  static public boolean RESET_CYCLE = false;

    // Declared in ASTNode.ast at line 42

  static public java.util.Set circularEvalSet = new java.util.HashSet();

    // Declared in ASTNode.ast at line 43

  static public java.util.Stack circularEvalStack = new java.util.Stack();

    // Declared in ASTNode.ast at line 66

  public static void addEvalEntry(ASTNode node, String attrName, Object parameters) {
    circularEvalSet.add(new CircularEvalEntry(node,attrName,parameters));
  }

    // Declared in ASTNode.ast at line 69

  public static boolean containsEvalEntry(ASTNode node, String attrName, Object parameters) {
    return circularEvalSet.contains(new CircularEvalEntry(node,attrName,parameters));
  }

    // Declared in ASTNode.ast at line 80

  public void pushEvalStack() {
  	 circularEvalStack.push(new CircularStackEntry(circularEvalSet, CHANGE));
  	 circularEvalSet = new java.util.HashSet();
  	 CHANGE = false;
  }

    // Declared in ASTNode.ast at line 85

  public void popEvalStack() {
  	 CircularStackEntry c = (CircularStackEntry) circularEvalStack.pop();
  	 circularEvalSet = c.circularEvalSet;
  	 CHANGE = c.changeValue;
  }

    // Declared in ASTNode.ast at line 90

  public static int boundariesCrossed = 0;

    // Declared in ASTNode.ast at line 117

  protected static State state = new State();

    // Declared in ASTNode.ast at line 118

  public boolean in$Circle = false;

    // Declared in ASTNode.ast at line 119

  public boolean in$Circle() { return in$Circle; }

    // Declared in ASTNode.ast at line 120

  public void in$Circle(boolean b) { in$Circle = b; }

    // Declared in ASTNode.ast at line 121

  public boolean is$Final = false;

    // Declared in ASTNode.ast at line 122

  public boolean is$Final() { return is$Final; }

    // Declared in ASTNode.ast at line 123

  public void is$Final(boolean b) { is$Final = b; }

    // Declared in ASTNode.ast at line 124

  protected static final int REWRITE_CHANGE = 1;

    // Declared in ASTNode.ast at line 125

  protected static final int REWRITE_NOCHANGE = 2;

    // Declared in ASTNode.ast at line 126

  protected static final int REWRITE_INTERRUPT = 3;

    // Declared in ASTNode.ast at line 127

  public ASTNode getChild(int i) {
    return ASTNode.getChild(this, i);
  }

    // Declared in ASTNode.ast at line 130

  public static ASTNode getChild(ASTNode that, int i) {
    ASTNode node = that.getChildNoTransform(i);
    if(node.is$Final()) return node;
    if(!node.mayHaveRewrite()) {
      node.is$Final(that.is$Final());
      return node;
    }
    if(!node.in$Circle()) {
      int rewriteState;
      int num = ASTNode.boundariesCrossed;
      do {
        ASTNode.state.push(ASTNode.REWRITE_CHANGE);
        ASTNode oldNode = node;
        oldNode.in$Circle(true);
        node = node.rewriteTo();
        oldNode.in$Circle(false);
        that.setChild(node, i);
        rewriteState = state.pop();
      } while(rewriteState == ASTNode.REWRITE_CHANGE);
      if(rewriteState == ASTNode.REWRITE_NOCHANGE && that.is$Final()) {
        node.is$Final(true);
        ASTNode.boundariesCrossed = num;
      }
    }
    else if(that.is$Final() != node.is$Final()) boundariesCrossed++;
    return node;
  }

    // Declared in ASTNode.ast at line 157

  private int childIndex;

    // Declared in ASTNode.ast at line 158

  public int getIndexOfChild(ASTNode node) {
    if(node != null && node.childIndex < getNumChild() && node == getChildNoTransform(node.childIndex))
      return node.childIndex;
    for(int i = 0; i < getNumChild(); i++)
      if(getChildNoTransform(i) == node) {
        node.childIndex = i;
        return i;
      }
    return -1;
  }

    // Declared in ASTNode.ast at line 169


  public void addChild(ASTNode node) {
    setChild(node, getNumChild());
  }

    // Declared in ASTNode.ast at line 172

  public ASTNode getChildNoTransform(int i) {
    return (ASTNode)children[i];
  }

    // Declared in ASTNode.ast at line 175

  public int getNumChild() {
    return (children == null) ? 0 : children.length;
  }

    // Declared in ASTNode.ast at line 178

  public void setChild(ASTNode node, int i) {
    if(children == null) {
      children = new ASTNode[i + 1];
    } else if (i >= children.length) {
      ASTNode c[] = new ASTNode[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = node;
    if(node != null) { node.setParent(this); node.childIndex = i; }
  }

    // Declared in ASTNode.ast at line 189

  public void insertChild(ASTNode node, int i) {
    if(children == null) {
      children = new ASTNode[i + 1];
      children[i] = node;
    } else {
      ASTNode c[] = new ASTNode[children.length + 1];
      System.arraycopy(children, 0, c, 0, i);
      c[i] = node;
      if(i < children.length)
        System.arraycopy(children, i, c, i+1, children.length-i);
      children = c;
    }
    if(node != null) { node.setParent(this); node.childIndex = i; }
  }

    // Declared in ASTNode.ast at line 203

  public ASTNode getParent() {
    if(parent != null && ((ASTNode)parent).is$Final() != is$Final()) {
      boundariesCrossed++;
    }
    return (ASTNode)parent;
  }

    // Declared in ASTNode.ast at line 209

  public void setParent(ASTNode node) {
    parent = node;
  }

    // Declared in ASTNode.ast at line 212

    protected static int duringClassRelations = 0;

    // Declared in ASTNode.ast at line 213

    protected static boolean duringClassRelations() {
        if(duringClassRelations == 0) {
            return false;
        }
        else {
            state.pop();
            state.push(ASTNode.REWRITE_INTERRUPT);
            return true;
        }
    }

    // Declared in ASTNode.ast at line 223

    public static void reset() {
        IN_CIRCLE = false;
        CHANGE = false;
        LAST_CYCLE = false;
        circularEvalSet = new java.util.HashSet();
        circularEvalStack = new java.util.Stack();
        boundariesCrossed = 0;
        state = new State();
        if(duringClassRelations != 0) {
            System.out.println("Warning: resetting duringClassRelations");
            duringClassRelations = 0;
        }
    }

    // Declared in ASTNode.ast at line 236

  public boolean mayHaveRewrite() { return false; }

    // Declared in CollectionAttributes.jrag at line 759
    protected void collect_contributors_CollDecl_uses() {
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).collect_contributors_CollDecl_uses();
    }

    protected void contributeTo_CollDecl_CollDecl_uses(HashSet collection) {
    }


    protected boolean env_visited = false;
    // Declared in JragCodeGen.jrag at line 64
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
    if(state.peek() == ASTNode.REWRITE_CHANGE) {
        state.pop();
        state.push(ASTNode.REWRITE_NOCHANGE);
    }
    return this;
}

    public TypeDecl Define_TypeDecl_hostClass(ASTNode caller, ASTNode child) {
        return getParent().Define_TypeDecl_hostClass(this, caller);
    }
    public Collection Define_Collection_findSubclasses(ASTNode caller, ASTNode child, ASTDecl target) {
        return getParent().Define_Collection_findSubclasses(this, caller, target);
    }
    public Collection Define_Collection_findFathers(ASTNode caller, ASTNode child, ASTDecl node) {
        return getParent().Define_Collection_findFathers(this, caller, node);
    }
    public Grammar Define_Grammar_env(ASTNode caller, ASTNode child) {
        return getParent().Define_Grammar_env(this, caller);
    }
}
