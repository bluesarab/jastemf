
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;
public class Grammar extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
        subclassMap_visited = false;
        subclassMap_computed = false;
        subclassMap_value = null;
        fatherMap_visited = false;
        fatherMap_computed = false;
        fatherMap_value = null;
        astErrors_visited = false;
        collectAstErrors_visited = false;
        roots_visited = false;
        lookup_String_visited = new java.util.HashSet(4);
        root_visited = false;
        errors_visited = false;
        collectErrors_visited = false;
        env_visited = false;
        inhEqMap_visited = false;
        inhEqMap_computed = false;
        inhEqMap_value = null;
        rewriteAspects_visited = false;
        rewriteAspects_computed = false;
        rewriteAspects_value = null;
    collect_contributors_CollDecl_uses = false;
    }
    public Object clone() throws CloneNotSupportedException {
        Grammar node = (Grammar)super.clone();
        node.subclassMap_visited = false;
        node.subclassMap_computed = false;
        node.subclassMap_value = null;
        node.fatherMap_visited = false;
        node.fatherMap_computed = false;
        node.fatherMap_value = null;
        node.astErrors_visited = false;
        node.collectAstErrors_visited = false;
        node.roots_visited = false;
        node.lookup_String_visited = new java.util.HashSet(4);
        node.root_visited = false;
        node.errors_visited = false;
        node.collectErrors_visited = false;
        node.env_visited = false;
        node.inhEqMap_visited = false;
        node.inhEqMap_computed = false;
        node.inhEqMap_value = null;
        node.rewriteAspects_visited = false;
        node.rewriteAspects_computed = false;
        node.rewriteAspects_value = null;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          Grammar node = (Grammar)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        Grammar res = (Grammar)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in JaddCodeGen.jrag at line 15

  public void abstractAncestors() {
    ASTDecl cl;
    IdDecl name;
    
    // Add ASTNode
    cl = new ASTDecl();
    name = new IdDecl();
    name.setID(ASTNode.nodeName);
    cl.setIdDecl(name);
    cl.setFileName(ASTNode.nodeName+".ast");
    addTypeDecl(cl);
    
    // Add List
    cl = new ASTDecl();
    name = new IdDecl();
    name.setID(ASTNode.listName);
    cl.setIdDecl(name);
    cl.setFileName(ASTNode.listName+".ast");
    addTypeDecl(cl);

    // Add Opt
    cl = new ASTDecl();
    name = new IdDecl();
    name.setID(ASTNode.optName);
    cl.setIdDecl(name);
    cl.setFileName(ASTNode.optName+".ast");
    addTypeDecl(cl);
  }

    // Declared in JaddCodeGen.jrag at line 198


  public void genReset(PrintWriter stream) {
    stream.println("    public void ASTNode$State.reset() {");
    if(ASTNode.block) stream.print(ASTNode.blockBegin);
    if(circularEnabled) {
      stream.println("        IN_CIRCLE = false;");
      stream.println("        CIRCLE_INDEX = 1;"); // TOBBE
      stream.println("        CHANGE = false;");
      if(cacheCycle)
          stream.println("        LAST_CYCLE = false;");
      if(componentCheck) {
          stream.println("        circularEvalSet = " + ASTNode.createDefaultSet + ";");
          stream.println("        circularEvalStack = new java.util.Stack();");
      }
    }
    if(rewriteEnabled) {
      stream.println("        boundariesCrossed = 0;");
      genResetDuringCounters(stream);
      if(rewriteLimit > 0)
        stream.println("        debugRewrite = " + ASTNode.createDefaultMap + ";\n");
      if(stagedRewrites)
        stream.println("        rewritePhase = 1;\n");
    }
    if(ASTNode.block) stream.print(ASTNode.blockEnd);
    stream.println("    }");
  }

    // Declared in JaddCodeGen.jrag at line 224


  public void jjtGenASTNode$State(PrintWriter stream, String parserName, boolean jjtree, boolean rewriteEnabled) {
      stream.println("   static public class ASTNode$State {");
      if(circularEnabled) {
         stream.println("  public boolean IN_CIRCLE = false;");
         stream.println("  public int CIRCLE_INDEX = 1;");
         stream.println("  public boolean CHANGE = false;");
         if(cacheCycle) {
            stream.println("  public boolean LAST_CYCLE = false;");
         }
         stream.println("  public boolean RESET_CYCLE = false;");

         stream.println("  static public class CircularValue {");
         stream.println("    Object value;");
         stream.println("    int visited = -1;");
         stream.println("  }");

         if(componentCheck) {
           stream.println("  public " + ASTNode.typeDefaultSet + " circularEvalSet = " + ASTNode.createDefaultSet + ";");
           stream.println("  public java.util.Stack circularEvalStack = new java.util.Stack();");

           stream.println("  static class CircularEvalEntry {");
           stream.println("  	 ASTNode node;");
           stream.println("  	 String attrName;");
           stream.println("  	 Object parameters;");

           stream.println("  	 public CircularEvalEntry(ASTNode node, String attrName, Object parameters) {");
           stream.println("  	   this.node = node;");
           stream.println("   	 this.attrName = attrName;");
           stream.println("  		 this.parameters = parameters;");
           stream.println("  	 }");

           stream.println("  	 public boolean equals(Object rhs) {");
           stream.println("  	   CircularEvalEntry s = (CircularEvalEntry) rhs;");
           stream.println("  		 if (parameters == null && s.parameters == null)");
           stream.println("  			 return node == s.node && attrName.equals(s.attrName);");
           stream.println("  		 else if (parameters != null && s.parameters != null)");
           stream.println("  			 return node == s.node && attrName.equals(s.attrName) && parameters.equals(s.parameters);");
           stream.println("  		 else");
           stream.println("  			 return false;");
           stream.println("  	 }");

           stream.println("  	 public int hashCode() {");
           stream.println("  		 return node.hashCode();");
           stream.println("  	 }");
           stream.println("  }");

           stream.println("  public void addEvalEntry(ASTNode node, String attrName, Object parameters) {");
           stream.println("    circularEvalSet.add(new CircularEvalEntry(node,attrName,parameters));");
           stream.println("  }");

           stream.println("  public boolean containsEvalEntry(ASTNode node, String attrName, Object parameters) {");
           stream.println("    return circularEvalSet.contains(new CircularEvalEntry(node,attrName,parameters));");
           stream.println("  }");

           stream.println("  static class CircularStackEntry {");
           stream.println("    " + ASTNode.typeDefaultSet + " circularEvalSet;");
           stream.println("  	 boolean changeValue;");

           stream.println("  	 public CircularStackEntry(" + ASTNode.typeDefaultSet + " set, boolean change) {");
           stream.println("  		 circularEvalSet = set;");
           stream.println("  		 changeValue = change;");
           stream.println("  	 }");
           stream.println("  }");

           stream.println("  public void pushEvalStack() {");
           stream.println("  	 circularEvalStack.push(new CircularStackEntry(circularEvalSet, CHANGE));");
           stream.println("  	 circularEvalSet = " + ASTNode.createDefaultSet + ";");
           stream.println("  	 CHANGE = false;");
           stream.println("  }");

           stream.println("  public void popEvalStack() {");
           stream.println("  	 CircularStackEntry c = (CircularStackEntry) circularEvalStack.pop();");
           stream.println("  	 circularEvalSet = c.circularEvalSet;");
           stream.println("  	 CHANGE = c.changeValue;");
           stream.println("  }");

         }
      }
      if(rewriteEnabled) {
        stream.println("  public static final int REWRITE_CHANGE = 1;");
        stream.println("  public static final int REWRITE_NOCHANGE = 2;");
        stream.println("  public static final int REWRITE_INTERRUPT = 3;");
        if(rewriteLimit > 0) {
          stream.println("  public java.util.HashMap debugRewrite = " + ASTNode.createDefaultMap + ";\n");
        }
        stream.println("  public int boundariesCrossed = 0;\n");
        if(stagedRewrites)
          stream.println("public int rewritePhase = 1;");
        // state code
        stream.println("   private int[] stack;");
        stream.println("   private int pos;");
        stream.println("   public ASTNode$State() {");
        stream.println("     stack = new int[64];");
        stream.println("     pos = 0;");
        stream.println("   }");
        stream.println("   private void ensureSize(int size) {");
        stream.println("     if(size < stack.length)");
        stream.println("       return;");
        stream.println("     int[] newStack = new int[stack.length * 2];");
        stream.println("     System.arraycopy(stack, 0, newStack, 0, stack.length);");
        stream.println("     stack = newStack;");
        stream.println("   }");
        stream.println("   public void push(int i) {");
        stream.println("     ensureSize(pos+1);");
        stream.println("     stack[pos++] = i;");
        stream.println("   }");
        stream.println("   public int pop() {");
        stream.println("     return stack[--pos];");
        stream.println("   }");
        stream.println("   public int peek() {");
        stream.println("     return stack[pos-1];");
        stream.println("   }");
      }

      if(j2me) {
        stream.println("  static class HashtableBasedSet  {");
        stream.println("    public HashtableBasedSet(int initialCapacity) {");
        stream.println("      map = new java.util.Hashtable(initialCapacity);");
        stream.println("    }");
        stream.println("    private java.util.Hashtable map;");
        stream.println("    private static final Object PRESENT = new Object();");
        stream.println("    public java.util.Enumeration elements() { return map.keys(); }");
        stream.println("    public int size() { return map.size(); }");
        stream.println("    public boolean isEmpty() { return map.isEmpty(); }");
        stream.println("    public boolean contains(Object o) { return map.containsKey(o); }");
        stream.println("    public boolean add(Object o) { return map.put(o, PRESENT)==null; }");
        stream.println("    public boolean remove(Object o) { return map.remove(o)==PRESENT; }");
        stream.println("    public void clear() { map.clear(); }");
        stream.println("  }");
      }
      else if(deterministic) {
        stream.println("  static class IdentityHashSet extends java.util.LinkedHashSet {");
        stream.println("    public IdentityHashSet(int initialCapacity) { super(initialCapacity); }");
        stream.println("    public java.util.Iterator iterator() {");
        stream.println("      final java.util.Iterator i = super.iterator();");
        stream.println("      return new java.util.Iterator() {");
        stream.println("        public boolean hasNext() { return i.hasNext(); }");
        stream.println("        public Object next() { return ((Wrapper)i.next()).o; }");
        stream.println("        public void remove() { i.remove(); }");
        stream.println("      };");
        stream.println("    }");
        stream.println("    public boolean contains(Object o) { return super.contains(wrap(o)); }");
        stream.println("    public boolean add(Object o) { return super.add(wrap(o)); }");
        stream.println("    public boolean remove(Object o) { return super.remove(wrap(o)); }");
        stream.println("    private Wrapper wrap(Object o) { return new Wrapper(o); }");
        stream.println("    private static class Wrapper {");
        stream.println("      private Object o;");
        stream.println("      private Wrapper(Object o) { this.o = o; }");
        stream.println("      public boolean equals(Object other) { return other instanceof Wrapper && ((Wrapper)other).o == o; }");
        stream.println("      public int hashCode() { return System.identityHashCode(o); }");
        stream.println("    }");
        stream.println("  }");
      }
      else {
        stream.println("  static class IdentityHashSet extends java.util.AbstractSet implements java.util.Set {");
        stream.println("    public IdentityHashSet(int initialCapacity) {");
        stream.println("      map = new java.util.IdentityHashMap(initialCapacity);");
        stream.println("    }");
        stream.println("    private java.util.IdentityHashMap map;");
        stream.println("    private static final Object PRESENT = new Object();");
        stream.println("    public java.util.Iterator iterator() { return map.keySet().iterator(); }");
        stream.println("    public int size() { return map.size(); }");
        stream.println("    public boolean isEmpty() { return map.isEmpty(); }");
        stream.println("    public boolean contains(Object o) { return map.containsKey(o); }");
        stream.println("    public boolean add(Object o) { return map.put(o, PRESENT)==null; }");
        stream.println("    public boolean remove(Object o) { return map.remove(o)==PRESENT; }");
        stream.println("    public void clear() { map.clear(); }");
        stream.println("  }");
      }

      stream.println("  }"); // End ASTNode$State
  }

    // Declared in NameBinding.jrag at line 21


  public TypeDecl findClassDecl(String name, String comment) {
    comment = comment.trim();
    if(!comment.startsWith("/"))
      comment = "";
    
    TypeDecl t = lookup(name);
    if(t == null) {
      t = new ClassDecl(new IdDecl(name), new List(), new List(), new List(), new List(), new List(), new List(), new List(), "", 0 ,0, comment);
      addTypeDecl(t);
    }
    return t;
  }

    // Declared in NameBinding.jrag at line 34

  
  public TypeDecl findInterfaceDecl(String name, String comment) {
    comment = comment.trim();
    if(!comment.startsWith("/"))
      comment = "";
    TypeDecl t = lookup(name);
    if(t == null) {
      t = new InterfaceDecl(new IdDecl(name), new List(), new List(), new List(), new List(), new List(), new List(), new List(), "", 0, 0, comment);
      addTypeDecl(t);
    }
    return t;
  }

    // Declared in Attributes.jrag at line 17


  // Add attributes to AST
  public void addRewriteList(String className, jrag.AST.SimpleNode condition, jrag.AST.SimpleNode result, String type, String fileName, int startLine, int endLine, String parentName, String childName) {
    TypeDecl c = lookup(className);
    if(c != null && c instanceof ASTDecl) {
      RewriteList r = new RewriteList();
      r.setFileName(fileName);
      r.setStartLine(startLine);
      r.setEndLine(endLine);
      r.setCondition(condition);
      r.setResult(result);
      r.setReturnType(type);
      r.setParentName(parentName);
      r.setChildName(childName);
      ((ASTDecl)c).addRewrite(r);
    }
    else if(c != null) {
      error("Can not rewrite to non AST class " + className + " in " + fileName + " at line " + startLine);
    }
    else {
      error("Can not rewrite to unknown class " + className + " in " + fileName + " at line " + startLine);
    }
  }

    // Declared in Attributes.jrag at line 40

  
 
  public void addRewrite(String className, jrag.AST.SimpleNode condition, jrag.AST.SimpleNode result, String type, String fileName, int startLine, int endLine) {
    TypeDecl c = lookup(className);
    if(c != null && c instanceof ASTDecl) {
      Rewrite r = new Rewrite();
      r.setFileName(fileName);
      r.setStartLine(startLine);
      r.setEndLine(endLine);
      r.setCondition(condition);
      r.setResult(result);
      r.setReturnType(type);
      ((ASTDecl)c).addRewrite(r);
    }
    else if(c != null) {
      error("Can not rewrite to non AST class " + className + " in " + fileName + " at line " + startLine);
    }
    else {
      error("Can not rewrite to unknown class " + className + " in " + fileName + " at line " + startLine);
    }
  }

    // Declared in Attributes.jrag at line 60

  
  public void setSynDeclLazy(String aspectName, String className, String name, ast.AST.List parameterList, String fileName, int startLine) {
    TypeDecl c = lookup(className);
    if(c != null) {
      for(int i = 0; i < c.getNumSynDecl(); i++) {
        SynDecl s = c.getSynDecl(i);
        if(s.getName().equals(name) && s.getAspectName().equals(aspectName) && parameterList.getNumChild() == s.getNumParameter()) {
          boolean paramOK = true;
          for(int j = 0; j < s.getNumParameter(); j++) {
            Parameter p1 = (Parameter)parameterList.getChild(j);
            if(!p1.getType().equals(s.getParameter(j).getType()) || !p1.getName().equals(s.getParameter(j).getName()))
              paramOK = false;
          }
          if(paramOK) {
            if(s.getLazy())
              System.out.println("Warning: attribute " + name + " in " + className + " defined in aspect " + aspectName + " is already lazy");
            else {
              s.setLazy(true);
              System.out.println("Making attribute " + name + " in " + className + " defined in aspect " + aspectName + " lazy");
            }
            return;
          }
        }
      }
      error("Could not find attribute " + name + " in " + className + " defined in aspect " + aspectName);
    }
    else
      error("Can not find synthesized attribute " + name + " in unknown class " + className + " in " + fileName + " at line " + startLine);
  }

    // Declared in Attributes.jrag at line 88

  public void setInhDeclLazy(String aspectName, String className, String name, ast.AST.List parameterList, String fileName, int startLine) {
    TypeDecl c = lookup(className);
    if(c != null) {
      for(int i = 0; i < c.getNumInhDecl(); i++) {
        InhDecl s = c.getInhDecl(i);
        if(s.getName().equals(name) && s.getAspectName().equals(aspectName) && parameterList.getNumChild() == s.getNumParameter()) {
          boolean paramOK = true;
          for(int j = 0; j < s.getNumParameter(); j++) {
            Parameter p1 = (Parameter)parameterList.getChild(j);
            if(!p1.getType().equals(s.getParameter(j).getType()) || !p1.getName().equals(s.getParameter(j).getName()))
              paramOK = false;
          }
          if(paramOK) {
            if(s.getLazy())
              System.out.println("Warning: attribute " + name + " in " + className + " defined in aspect " + aspectName + " is already lazy");
            else {
              s.setLazy(true);
              System.out.println("Making attribute " + name + " in " + className + " defined in aspect " + aspectName + " lazy");
            }
            return;
          }
        }
      }
      error("Could not find attribute " + name + " in " + className + " defined in aspect " + aspectName);
    }
    else
      error("Can not find inherited attribute " + name + " in unknown class " + className + " in " + fileName + " at line " + startLine);
  }

    // Declared in Attributes.jrag at line 118



  public void addSynDecl(String name, String type, String className, boolean isLazy, 
  		String fileName, int startLine, int endLine, ast.AST.List parameterList, ASTExpression bottomValue, boolean isFinal, boolean isNTA, jrag.AST.SimpleNode node, String aspectName) {
    TypeDecl c = lookup(className);
    if(c != null) {
      SynDecl decl = new SynDecl(
        parameterList,
        name,
        type,
        isLazy | isNTA,
        fileName,
        startLine,
        endLine,
        isFinal | isNTA,
        isNTA,
        node.unparseComment(),
        aspectName
      );
      decl.setBottomValue(bottomValue);
      c.addSynDecl(decl);
    }
    else {
      error("Can not add synthesized attribute " + type + " " + name + " to unknown class " + className + " in " + fileName + " at line " + startLine);
    }
  }

    // Declared in Attributes.jrag at line 143


  public void addSynEq(String name, String className, jrag.AST.SimpleNode rhs, String fileName, int startLine, int endLine, ast.AST.List list, jrag.AST.SimpleNode node, String aspectName) {
    TypeDecl c = lookup(className);
    if(c != null) {
      SynEq equ = new SynEq();
      equ.setName(name);
      equ.setFileName(fileName);
      equ.setStartLine(startLine);
      equ.setEndLine(endLine);
      equ.setRHS(rhs);
      equ.setParameterList(list);
      equ.setComment(node.unparseComment());
      equ.setAspectName(aspectName);
      c.addSynEq(equ);
    }
    else {
      error("Can not add equation for synthesized attribute " + name + " to unknown class " + className + " in " + fileName + " at line " + startLine);
    }
  }

    // Declared in Attributes.jrag at line 162

  
  public void addInhDecl(String name, String type, String className, boolean isLazy, String fileName,
       int startLine, int endLine, ast.AST.List parameterList, ASTExpression bottomValue, boolean isFinal, boolean isNTA, jrag.AST.SimpleNode node, String aspectName) {
    TypeDecl c = lookup(className);
    if(c != null) {
      InhDecl decl = new InhDecl(
        parameterList,
        name,
        type,
        isLazy | isNTA,
        fileName,
        startLine,
        endLine,
        isFinal | isNTA,
        isNTA,
        node.unparseComment(),
        aspectName
      );
      decl.setBottomValue(bottomValue);
      c.addInhDecl(decl);
    }
    else {
      error("Can not add inherited attribute " + type + " " + name + " to unknown class " + className + " in " + fileName + " at line " + startLine);
    }
  }

    // Declared in Attributes.jrag at line 187


  public void addInhEq(String sonName, String name, String className, jrag.AST.SimpleNode rhs, String fileName, int startLine, int endLine, ast.AST.List list, jrag.AST.SimpleNode node, String aspectName) {
    addInhEq(sonName, name, className, rhs, fileName, startLine, endLine, list, null, node, aspectName);
  }

    // Declared in Attributes.jrag at line 191

  
  public void addInhEq(String sonName, String name, String className, jrag.AST.SimpleNode rhs, String fileName, int startLine, int endLine, ast.AST.List list, Parameter p, jrag.AST.SimpleNode node, String aspectName) {
    TypeDecl c = lookup(className);
    if(c != null) {
        InhEq equ = new InhEq();
        equ.setName(name);
        equ.setFileName(fileName);
        equ.setStartLine(startLine);
        equ.setEndLine(endLine);
        equ.setRHS(rhs);
        equ.setSonName(sonName);
        equ.setParameterList(list);
        equ.setComment(node.unparseComment());
        equ.setAspectName(aspectName);
        if(p != null) equ.setIndex(p);
        c.addInhEq(equ); // Sortera i bokstavsordning d\u00ef\u00bf\u00bdrefter non NTA f\u00ef\u00bf\u00bdrst
    }
    else {
      error("Can not add equation for inherited attribute " + name + " to unknown class " + className + " in " + fileName + " at line " + startLine);
    }
  }

    // Declared in Attributes.jrag at line 212


  public void addComponents(String className, ast.AST.List componentsList) {
    TypeDecl d = lookup(className);
    if(d != null) {
      d.setComponentsList(componentsList);
    }
  }

    // Declared in Attributes.jrag at line 230


  public void addCompUnit(jrag.AST.ASTCompilationUnit compUnit) {
    compUnits.add(compUnit);
  }

    // Declared in Attributes.jrag at line 234


  public Iterator getCompUnits() {
    return compUnits.iterator();
  }

    // Declared in Attributes.jrag at line 238


  public void addClassBodyDecl(jrag.AST.SimpleNode n, String className, String fileName, String aspectName) {
    addClassBodyDecl(n, className, fileName, "", aspectName);
  }

    // Declared in Attributes.jrag at line 242


  public void addClassBodyDecl(jrag.AST.SimpleNode n, String className, String fileName, String comments, String aspectName) {
    TypeDecl c = lookup(className);
    if(c != null) {
      c.classBodyDecls.add(new ClassBodyObject(n, fileName, n.firstToken.beginLine, comments, aspectName));
    }
    else {
      error("Cannot add member to unknown class " + className + " in " + fileName + " at line " + n.firstToken.beginLine);
    }
    //else {
      //TypeDecl t = findTypeDecl(className);
      //t.classBodyDecls.add(new ClassBodyObject(n, fileName, n.firstToken.beginLine));
    //}
  }

    // Declared in Attributes.jrag at line 256


  public void addInterface(jrag.AST.SimpleNode nameList, String className, String fileName) {
    if(nameList == null) {
      System.out.println("Panic");
    }
    else {
        
    TypeDecl c = lookup(className);
    if(c != null) {
      c.implementsList.add(nameList);
    }
    else {
      int line = nameList.firstToken.beginLine;
      error("Can not add interface to unknown class " + className + " in " + fileName + " at line " + line);
    }
    }
  }

    // Declared in Attributes.jrag at line 273


  public void extendInterface(jrag.AST.SimpleNode nameList, String className, String fileName) {
    if(nameList == null) {
      System.out.println("Panic");
    }
    else {
      TypeDecl c = lookup(className);
      if(c instanceof InterfaceDecl) {
        c.implementsList.add(nameList);
      }
      else if(c != null) {
        int line = nameList.firstToken.beginLine;
        error(className + " is not an interface and can therefore not be extended  in " + fileName + " at line " + line);
      }
      else {
        int line = nameList.firstToken.beginLine;
        error("Can not add interface to unknown interface " + className + " in " + fileName + " at line " + line);
      }
    }
  }

    // Declared in Attributes.jrag at line 316


  public void processRefinements() {
    for(int i = 0; i < getNumTypeDecl(); i++) {
      TypeDecl typeDecl = getTypeDecl(i);
      typeDecl.processReplacements();
      typeDecl.processRefinedClassBodyDecls();
      typeDecl.processSynEqReplacements();
      typeDecl.processRefinedSynEqs();
      typeDecl.processRefinedInhEqs();
    }
  }

    // Declared in Attributes.jrag at line 329

  public void addRefinedSynEq(String name, String className, jrag.AST.SimpleNode rhs, String fileName, int startLine, int endLine, ast.AST.List list, String aspectName, jrag.AST.SimpleNode node, String declaredAspect) {
    TypeDecl c = lookup(className);
    if(c != null) {
      SynEq equ = new SynEq();
      equ.setName(name);
      equ.setFileName(fileName);
      equ.setStartLine(startLine);
      equ.setEndLine(endLine);
      equ.setRHS(rhs);
      equ.setParameterList(list);
      equ.refinesAspect = aspectName;
      equ.setComment(node.unparseComment());
      equ.setAspectName(declaredAspect);
      c.refinedSynEqs.add(equ);
    }
    else {
      error("Can not add equation for synthesized attribute " + name + " to unknown class " + className + " in " + fileName + " at line " + startLine);
    }
  }

    // Declared in Attributes.jrag at line 349


  public void addReplacedSynEq(String name, String className, jrag.AST.SimpleNode rhs, String fileName, int startLine, int endLine, ast.AST.List list, String aspectName, String secondAspectName, jrag.AST.SimpleNode node, String declaredAspect) {
    TypeDecl c = lookup(className);
    if(c != null) {
      SynEq equ = new SynEq();
      equ.setName(name);
      equ.setFileName(fileName);
      equ.setStartLine(startLine);
      equ.setEndLine(endLine);
      equ.setRHS(rhs);
      equ.setParameterList(list);
      equ.refinesAspect = aspectName;
      equ.replacesAspect = secondAspectName;
      equ.setComment(node.unparseComment());
      equ.setAspectName(declaredAspect);
      c.replacedSynEqs.add(equ);
    }
    else {
      error("Can not add equation for synthesized attribute " + name + " to unknown class " + className + " in " + fileName + " at line " + startLine);
    }
  }

    // Declared in Attributes.jrag at line 494

  public void addRefinedInhEq(String sonName, String name, String className, jrag.AST.SimpleNode rhs, String fileName, int startLine, int endLine, ast.AST.List list, String aspectName, jrag.AST.SimpleNode node, String declaredAspect) {
    addRefinedInhEq(sonName, name, className, rhs, fileName, startLine, endLine, list, null, aspectName, node, declaredAspect);
  }

    // Declared in Attributes.jrag at line 498

  
  public void addRefinedInhEq(String sonName, String name, String className, jrag.AST.SimpleNode rhs, String fileName, int startLine, int endLine, ast.AST.List list, Parameter p, String aspectName, jrag.AST.SimpleNode node, String declaredAspect) {
    TypeDecl c = lookup(className);
    if(c != null) {
        InhEq equ = new InhEq();
        equ.setName(name);
        equ.setFileName(fileName);
        equ.setStartLine(startLine);
        equ.setEndLine(endLine);
        equ.setRHS(rhs);
        equ.setSonName(sonName);
        equ.setParameterList(list);
        equ.refinesAspect = aspectName;
        equ.setComment(node.unparseComment());
        equ.setAspectName(declaredAspect);
        if(p != null) equ.setIndex(p);
        c.refinedInhEqs.add(equ); // Sortera i bokstavsordning d\u00ef\u00bf\u00bdrefter non NTA f\u00ef\u00bf\u00bdrst
    }
    else {
      error("Can not add equation for inherited attribute " + name + " to unknown class " + className + " in " + fileName + " at line " + startLine);
    }
  }

    // Declared in Attributes.jrag at line 615

  public void addRefinedClassBodyDecl(jrag.AST.SimpleNode n, String className, String fileName, String aspectName, String declaredAspect) {
    TypeDecl c = lookup(className);
    if(c != null) {
      ClassBodyObject o = new ClassBodyObject(n, fileName, n.firstToken.beginLine, declaredAspect);
      o.refinesAspect = aspectName;
      c.refinedClassBodyDecls.add(o);
    }
    else {
      error("Can not add member to unknown class " + className + " in " + fileName);
    }
  }

    // Declared in Attributes.jrag at line 626

  public void addReplacedClassBodyDecl(jrag.AST.SimpleNode n, String className, String fileName, String aspectName,
    String replacedAspect, jrag.AST.SimpleNode replacedSignature, String declaredAspect) {
    TypeDecl c = lookup(className);
    if(c != null) {
      ClassBodyObject o = new ClassBodyObject(n, fileName, n.firstToken.beginLine, declaredAspect);
      o.refinesAspect = aspectName;
      o.replaceAspect = replacedAspect;
      c.replacements.add(o);
    }
    else {
      error("Can not add member to unknown class " + className + " in " + fileName);
    }
  }

    // Declared in Attributes.jrag at line 937

  
  public LinkedList compUnits = new LinkedList();

    // Declared in CollectionAttributes.jrag at line 627


  public void weaveCollectionAttributes() {
    for(int i = 0; i < getNumTypeDecl(); i++)
      getTypeDecl(i).weaveCollectionAttributes();
  }

    // Declared in CollectionAttributes.jrag at line 763


  // build AST from parser
  public void addCollDecl(String name, String type, String className, String fileName, int startLine, int endLine, ASTExpression startValue, String combOp, boolean isCircular, ArrayList annotations, jrag.AST.SimpleNode node, String root) {
    TypeDecl c = lookup(className);
    if(c != null/* && c instanceof ASTDecl*/) {
      CollDecl decl = new CollDecl();
      decl.setName(name);
      decl.setType(type);
      decl.setLazy(true);
      decl.setFileName(fileName);
      decl.setStartLine(startLine);
      decl.setEndLine(endLine);
      decl.setParameterList(new List());
      decl.setStartValue(startValue);
      decl.setCombOp(combOp);
      decl.setAnnotations(annotations);
      decl.setCircularCollection(isCircular || annotations.contains("@Circular"));
      decl.setComment(node.unparseComment());
      decl.setTarget(className);
      decl.root = root;
      ((TypeDecl)c).addCollDecl(decl);
    }
    else
      error("Can not add collection attribute " + type + " " + name + " to unknown class " + className + " in " + fileName + " at line " + startLine);
  }

    // Declared in CollectionAttributes.jrag at line 819



  public void addCollEq(String targetName, String targetAttributeName, String attributeType, String reference, ast.AST.List contributionList, String fileName, int startLine, int endLine, boolean refSet, jrag.AST.SimpleNode node, String aspectName) {
    TypeDecl c = lookup(attributeType);
    if(c != null && c instanceof ASTDecl) {
      CollEq collEq = new CollEq(new List(), targetName, fileName, startLine, endLine, node.unparseComment(), aspectName, contributionList, targetName, targetAttributeName, refSet, reference);
      ((ASTDecl)c).addCollEq(collEq);
    }
    else
      error("Can not add collection contribution to unknown class " + attributeType + " in " + fileName + " at line " + startLine);
  }

    // Declared in JragCodeGen.jrag at line 112


  public void genAGCode(PrintStream s, String aspectName) {
    aspectJ = true;
    
    s.print(genImportsList());
    s.print("aspect " + aspectName + " {\n");
    for(int i = 0; i < getNumTypeDecl(); i++) {
      getTypeDecl(i).genAGCode(s);
    }
    s.print("}\n");
  }

    // Declared in JragCodeGen.jrag at line 135


  public String genImportsList() {
    LinkedHashSet set = new LinkedHashSet();
    for(Iterator iter = getCompUnits(); iter.hasNext(); ) {
        jrag.AST.ASTCompilationUnit u = (jrag.AST.ASTCompilationUnit)iter.next();
        String[] imports = u.getImports().split(";");
        for(int i = 0; i < imports.length; i++)
          if(imports[i] != null && !imports[i].equals(""))
            set.add(imports[i] + ";");
    }
    StringBuffer buf = new StringBuffer();
    for(Iterator iter = set.iterator(); iter.hasNext(); )
      buf.append(iter.next());
    buf.append("\n");
    return buf.toString();
  }

    // Declared in JragCodeGen.jrag at line 1453


  public Iterator inhAttrSet() {
    return inhEqMap().keySet().iterator();
  }

    // Declared in JragCodeGen.jrag at line 1492


  public void genResetDuringCounters(PrintWriter out) {
    for(Iterator iter = rewriteAspects().iterator(); iter.hasNext(); ) {
      String name = (String)iter.next();
      String s = 
                 "        if(during" + name + " != 0) {\n" + 
                 "            System.out.println(\"Warning: resetting during" + name + "\");\n" +
                 "            during" + name + " = 0;\n" +
                 "        }\n";
      out.print(s);
    }

  }

    // Declared in JragCodeGen.jrag at line 1505


  public void genRewriteOrderChecks(PrintWriter out) {
    for(Iterator iter = rewriteAspects().iterator(); iter.hasNext(); ) {
      String name = (String)iter.next();
      String s = "    protected int ASTNode$State.during" + name + " = 0;\n" +
                 "    protected boolean ASTNode.during" + name + "() {\n" + 
                 "        if(state().during" + name + " == 0) {\n" + 
                 "            return false;\n" +
                 "        }\n" + 
                 "        else {\n" + 
                 "            state().pop();\n" +
                 "            state().push(ASTNode$State.REWRITE_INTERRUPT);\n" +
                 "            return true;\n" +
                 "        }\n" + 
                 "    }\n";
      out.print(s);
    }
  }

    // Declared in JragCodeGen.jrag at line 1644

  

  public void createInterfaces(File outputDir, String pack)
    throws FileNotFoundException {
      if(!ASTNode.parentInterface)
        return;
    for(Iterator iter = inhEqMap().entrySet().iterator(); iter.hasNext(); ) {
      java.util.Map.Entry entry = (java.util.Map.Entry)iter.next();
      String attrId = (String)entry.getKey();
      AttrEq attr = (AttrEq)((LinkedList)entry.getValue()).get(0);
      String interfaceName;
      if(pack.equals("")) {
        interfaceName = "Defines_" + attrId;                                                                                                                      
      }                                                                                                                                                           
      else {                                                                                                                                                      
        interfaceName = pack.replace('.', File.separatorChar) + File.separator +                                                                                  
          "Defines_" + attrId;                                                                                                                                    
      }                                                                                                                                                           
      File file = new File(outputDir, interfaceName + ".java");                                                                                                   
          PrintStream p = new PrintStream(new FileOutputStream(file));                                                                                            
          if(!pack.equals("")) {                                                                                                                                  
            p.println("package " + pack + ";");                                                                                                                   
            p.println();                                                                                                                                          
          }                                                                                                                                                       
                                                                                                                                                                  
      p.print(genImportsList());                                                                                                                                  
                                                                                                                                                                  
      String s;                                                                                                                                                   
      s = "public interface Defines_#TYPEINSIGNATURE#_#NAME# {\n" +                                                                                               
          "    public #TYPE# Define_#TYPEINSIGNATURE#_#METHODNAME#(#INTERFACEPARMDECL#);\n" +                                                                     
          "}\n";
      s = s.replaceAll("#TYPE#", attr.implType());
      s = s.replaceAll("#TYPEINSIGNATURE#", attr.getTypeInSignature());
      s = s.replaceAll("#NAME#", attr.attributeSignature());
      s = s.replaceAll("#METHODNAME#", attr.attributeName());
      s = s.replaceAll("#PARMDECL#", attr.parametersDecl());
      s = s.replaceAll("#INTERFACEPARMDECL#", attr.interfaceParametersDecl());
      p.print(s);
    }
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 1

    public Grammar(int i) {
        super(i);
        is$Final(true);
    }

    // Declared in Ast.ast at line 7

    public Grammar(Ast p, int i) {
        this(i);
        parser = p;
        is$Final(true);
    }

    // Declared in Ast.ast at line 12

    public Grammar() {
        this(0);

        setChild(new List(), 0);
        is$Final(true);

    }

    // Declared in Ast.ast at line 21


    // Declared in Ast.ast line 1
    public Grammar(List p0) {
        setChild(p0, 0);
        is$Final(true);
    }

    // Declared in Ast.ast at line 26


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "Grammar");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 33


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 37


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 42


public void checkChild(Node n, int i) {
  if(i == 0) {
    if(!(n instanceof List)) throw new Error("Child number 0 of Grammar has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof TypeDecl)) throw new Error("Child number " + k + " in TypeDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of TypeDecl");
  }
}

    // Declared in Ast.ast at line 50


  public int getNumChild() {
    return 1;
  }

    // Declared in Ast.ast at line 53

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 1
    public void setTypeDeclList(List list) {
        setChild(list, 0);
    }

    // Declared in Ast.ast at line 6


    public int getNumTypeDecl() {
        return getTypeDeclList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public TypeDecl getTypeDecl(int i) {
        return (TypeDecl)getTypeDeclList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addTypeDecl(TypeDecl node) {
        List list = getTypeDeclList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setTypeDecl(TypeDecl node, int i) {
        List list = getTypeDeclList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getTypeDeclList() {
        return (List)getChild(0);
    }

    // Declared in Ast.ast at line 27


    public List getTypeDeclListNoTransform() {
        return (List)getChildNoTransform(0);
    }

    // Declared in CollectionAttributes.jrag at line 759
    private boolean collect_contributors_CollDecl_uses = false;
    protected void collect_contributors_CollDecl_uses() {
        if(collect_contributors_CollDecl_uses) return;
        super.collect_contributors_CollDecl_uses();
        collect_contributors_CollDecl_uses = true;
    }



    protected boolean subclassMap_visited = false;
    protected boolean subclassMap_computed = false;
    protected HashMap subclassMap_value;
    // Declared in ClassRelations.jrag at line 47
    public HashMap subclassMap() {
        if(subclassMap_computed)
            return subclassMap_value;
boolean interruptedCircle = false;
        if(subclassMap_visited)
            throw new RuntimeException("Circular definition of attr: subclassMap in class: ");
        subclassMap_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        subclassMap_value = subclassMap_compute();
        if(isFinal && num == boundariesCrossed)
            subclassMap_computed = true;
        subclassMap_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return subclassMap_value;
    }

    private HashMap subclassMap_compute()  {
    HashMap map = new HashMap();
    for(int j = 0; j < getNumTypeDecl(); j++) {
      if(getTypeDecl(j) instanceof ASTDecl) {
        ASTDecl decl = (ASTDecl)getTypeDecl(j);
        map.put(decl, new ArrayList());
      }
    }
    for(int j = 0; j < getNumTypeDecl(); j++) {
      if(getTypeDecl(j) instanceof ASTDecl) {
        ASTDecl decl = (ASTDecl)getTypeDecl(j);
        if(decl.superClass() != null) {
          ((ArrayList)map.get(decl.superClass())).add(decl);
        }
      }
    }
    return map;
  }

    protected boolean fatherMap_visited = false;
    protected boolean fatherMap_computed = false;
    protected HashMap fatherMap_value;
    // Declared in ClassRelations.jrag at line 86
    public HashMap fatherMap() {
        if(fatherMap_computed)
            return fatherMap_value;
boolean interruptedCircle = false;
        if(fatherMap_visited)
            throw new RuntimeException("Circular definition of attr: fatherMap in class: ");
        fatherMap_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        fatherMap_value = fatherMap_compute();
        if(isFinal && num == boundariesCrossed)
            fatherMap_computed = true;
        fatherMap_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return fatherMap_value;
    }

    private HashMap fatherMap_compute()  {
    HashMap map = new LinkedHashMap();
    for(int j = 0; j < getNumTypeDecl(); j++) {
      if(getTypeDecl(j) instanceof ASTDecl) {
        ASTDecl decl = (ASTDecl)getTypeDecl(j);
        map.put(decl, new LinkedHashSet());
      }
    }
        
    for(int j = 0; j < getNumTypeDecl(); j++) {
      if(getTypeDecl(j) instanceof ASTDecl) {
        ASTDecl decl = (ASTDecl)getTypeDecl(j);
        for(Iterator iter = decl.getComponents(); iter.hasNext(); ) {
          Components c = (Components)iter.next();
          TypeDecl t = lookup(c.type());
          if(t != null) {
            ((HashSet)map.get(t)).add(decl);
          }
        }
      }
    }
    return map;
  }

    protected boolean astErrors_visited = false;
    // Declared in Errorcheck.jrag at line 8
    public String astErrors() {
boolean interruptedCircle = false;
        if(astErrors_visited)
            throw new RuntimeException("Circular definition of attr: astErrors in class: ");
        astErrors_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String astErrors_value = astErrors_compute();
        astErrors_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return astErrors_value;
    }

    private String astErrors_compute()  {
    return collectAstErrors();
  }

    protected boolean collectAstErrors_visited = false;
    // Declared in Errorcheck.jrag at line 12
    public String collectAstErrors() {
boolean interruptedCircle = false;
        if(collectAstErrors_visited)
            throw new RuntimeException("Circular definition of attr: collectAstErrors in class: ");
        collectAstErrors_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String collectAstErrors_value = collectAstErrors_compute();
        collectAstErrors_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return collectAstErrors_value;
    }

    private String collectAstErrors_compute()  {
    StringBuffer result = new StringBuffer();
    for(int i = 0; i < getNumTypeDecl(); i++) {
      result.append(getTypeDecl(i).collectAstErrors());
    }
    
    ArrayList l = roots();
    if(l.isEmpty())
      result.append("No root node available\n");
    /*
    if(l.size() > 1) {
      result.append("Multiple root nodes: ");
      Iterator iter = l.iterator();
      result.append(((ASTDecl)iter.next()).name());
      while(iter.hasNext())
        result.append(", " + ((ASTDecl)iter.next()).name());
      result.append("\n");
    }*/

    return result.toString();
  }

    protected boolean roots_visited = false;
    // Declared in Errorcheck.jrag at line 34
    public ArrayList roots() {
boolean interruptedCircle = false;
        if(roots_visited)
            throw new RuntimeException("Circular definition of attr: roots in class: ");
        roots_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        ArrayList roots_value = roots_compute();
        roots_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return roots_value;
    }

    private ArrayList roots_compute()  {
    ArrayList l = new ArrayList();
    for(int i = 0; i < getNumTypeDecl(); i++) {
      if(getTypeDecl(i) instanceof ASTDecl) {
        ASTDecl decl = (ASTDecl)getTypeDecl(i);
        if(decl.isRootNode())
          l.add(decl);
      }
    }
    return l;
  }

    protected java.util.Set lookup_String_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 13
    public TypeDecl lookup(String name) {
        Object _parameters = name;
boolean interruptedCircle = false;
        if(lookup_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: lookup in class: ");
        lookup_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        TypeDecl lookup_String_value = lookup_compute(name);
        lookup_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return lookup_String_value;
    }

    private TypeDecl lookup_compute(String name)  {
    for(int i = 0; i < getNumTypeDecl(); i++) {
      if(getTypeDecl(i).name().equals(name))
        return getTypeDecl(i);
    }
    return null;
  }

    protected boolean root_visited = false;
    // Declared in CollectionAttributes.jrag at line 623
    public ASTDecl root() {
boolean interruptedCircle = false;
        if(root_visited)
            throw new RuntimeException("Circular definition of attr: root in class: ");
        root_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        ASTDecl root_value = root_compute();
        root_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return root_value;
    }

    private ASTDecl root_compute() {  return  (ASTDecl)roots().iterator().next();  }

    protected boolean errors_visited = false;
    // Declared in Errorcheck.jrag at line 10
    public String errors() {
boolean interruptedCircle = false;
        if(errors_visited)
            throw new RuntimeException("Circular definition of attr: errors in class: ");
        errors_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String errors_value = errors_compute();
        errors_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return errors_value;
    }

    private String errors_compute()  {
    return collectErrors();
  }

    protected boolean collectErrors_visited = false;
    // Declared in Errorcheck.jrag at line 14
    public String collectErrors() {
boolean interruptedCircle = false;
        if(collectErrors_visited)
            throw new RuntimeException("Circular definition of attr: collectErrors in class: ");
        collectErrors_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String collectErrors_value = collectErrors_compute();
        collectErrors_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return collectErrors_value;
    }

    private String collectErrors_compute()  {
    StringBuffer result = new StringBuffer();
    //result.append(collectAstErrors());
    for(int i = 0; i < getNumTypeDecl(); i++) {
      result.append(getTypeDecl(i).collectErrors());
    }
    return result.toString();
  }

    protected boolean env_visited = false;
    // Declared in JragCodeGen.jrag at line 65
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
        Grammar env_value = env_compute();
        env_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return env_value;
    }

    private Grammar env_compute() {  return  this;  }

    protected boolean inhEqMap_visited = false;
    protected boolean inhEqMap_computed = false;
    protected HashMap inhEqMap_value;
    // Declared in JragCodeGen.jrag at line 1457
    public HashMap inhEqMap() {
        if(inhEqMap_computed)
            return inhEqMap_value;
boolean interruptedCircle = false;
        if(inhEqMap_visited)
            throw new RuntimeException("Circular definition of attr: inhEqMap in class: ");
        inhEqMap_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        inhEqMap_value = inhEqMap_compute();
        if(isFinal && num == boundariesCrossed)
            inhEqMap_computed = true;
        inhEqMap_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return inhEqMap_value;
    }

    private HashMap inhEqMap_compute()  {
    HashMap map = new LinkedHashMap();
    for(int i = 0; i < getNumTypeDecl(); i++) {
      if(getTypeDecl(i) instanceof ASTDecl) {
        map.putAll(((ASTDecl)getTypeDecl(i)).inhEqMap());
      }
    }
    return map;
  }

    protected boolean rewriteAspects_visited = false;
    protected boolean rewriteAspects_computed = false;
    protected Collection rewriteAspects_value;
    // Declared in JragCodeGen.jrag at line 1467
    public Collection rewriteAspects() {
        if(rewriteAspects_computed)
            return rewriteAspects_value;
boolean interruptedCircle = false;
        if(rewriteAspects_visited)
            throw new RuntimeException("Circular definition of attr: rewriteAspects in class: ");
        rewriteAspects_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        rewriteAspects_value = rewriteAspects_compute();
        if(isFinal && num == boundariesCrossed)
            rewriteAspects_computed = true;
        rewriteAspects_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return rewriteAspects_value;
    }

    private Collection rewriteAspects_compute()  {
    Set set = new LinkedHashSet();
    for(int i = 0; i < getNumTypeDecl(); i++) {
      if(getTypeDecl(i) instanceof ASTDecl) {
        ASTDecl decl = (ASTDecl)getTypeDecl(i);
        for(int j = 0; j < decl.getNumRewrite(); j++) {
          Rewrite r = decl.getRewrite(j);
          set.add(r.aspectName());
        }
      }
    }
    return set;
  }

    // Declared in ClassRelations.jrag at line 69
    public Collection Define_Collection_findSubclasses(ASTNode caller, ASTNode child, ASTDecl target) {
        if(caller == getTypeDeclListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
 {
    return (Collection)subclassMap().get(target);
    /*
    ArrayList list = new ArrayList();
    for(int j = 0; j < getNumTypeDecl(); j++) {
      if(getTypeDecl(j) instanceof ASTDecl) {
        ASTDecl decl = (ASTDecl)getTypeDecl(j);
        if(target.equals(decl.superClass())) {
          list.add(decl);
        }
      }
    }
    return list;*/
  }
}
        return getParent().Define_Collection_findSubclasses(this, caller, target);
    }

    // Declared in ClassRelations.jrag at line 110
    public Collection Define_Collection_findFathers(ASTNode caller, ASTNode child, ASTDecl node) {
        if(caller == getTypeDeclListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
 {
    HashSet set = new LinkedHashSet();
    set.addAll((HashSet)fatherMap().get(node));
    if(node.superClass() != null)
      set.addAll(node.superClass().fathers());
    return set;
  }
}
        return getParent().Define_Collection_findFathers(this, caller, node);
    }

    // Declared in NameBinding.jrag at line 5
    public Grammar Define_Grammar_env(ASTNode caller, ASTNode child) {
        if(caller == getTypeDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return  this;
        }
        return getParent().Define_Grammar_env(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
