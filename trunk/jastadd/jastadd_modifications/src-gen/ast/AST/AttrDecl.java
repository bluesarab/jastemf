
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public abstract class AttrDecl extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
        parametersDecl_visited = false;
        parameters_visited = false;
        interfaceParameters_visited = false;
        interfaceParametersDecl_visited = false;
        interfaceParametersStart_visited = false;
        interfaceParametersContinue_visited = false;
        isCircular_visited = false;
        hasBottomValue_visited = false;
        getBottomValue_visited = false;
        root_visited = false;
        collDebugString_visited = false;
        separateEvaluation_visited = false;
        lazyCondition_visited = false;
        onePhase_visited = false;
        naive_visited = false;
        collectingSignature_visited = false;
        collectingSignature_computed = false;
        collectingSignature_value = null;
        visitCheckEnabled_visited = false;
        declaredNTA_visited = false;
        inhDebugString_visited = false;
        cycleLimitCheck_visited = false;
        tracePrintReturnPreviousValue_String_visited = new java.util.HashSet(4);
        tracePrintReturnNewValue_String_visited = new java.util.HashSet(4);
        tracePrintBeginComputingValue_visited = false;
        tracePrintCycleBeginString_visited = false;
        tracePrintCycleEndString_visited = false;
        tracePrintStartingCycle_visited = false;
        traceComputeContext_visited = false;
        traceSignature_visited = false;
        traceBeginAttr_visited = false;
        traceEndAttr_visited = false;
        traceEndCachedAttr_visited = false;
        name_visited = false;
        type_visited = false;
        implType_visited = false;
        getTypeInSignature_visited = false;
        signature_visited = false;
        hostClass_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        AttrDecl node = (AttrDecl)super.clone();
        node.parametersDecl_visited = false;
        node.parameters_visited = false;
        node.interfaceParameters_visited = false;
        node.interfaceParametersDecl_visited = false;
        node.interfaceParametersStart_visited = false;
        node.interfaceParametersContinue_visited = false;
        node.isCircular_visited = false;
        node.hasBottomValue_visited = false;
        node.getBottomValue_visited = false;
        node.root_visited = false;
        node.collDebugString_visited = false;
        node.separateEvaluation_visited = false;
        node.lazyCondition_visited = false;
        node.onePhase_visited = false;
        node.naive_visited = false;
        node.collectingSignature_visited = false;
        node.collectingSignature_computed = false;
        node.collectingSignature_value = null;
        node.visitCheckEnabled_visited = false;
        node.declaredNTA_visited = false;
        node.inhDebugString_visited = false;
        node.cycleLimitCheck_visited = false;
        node.tracePrintReturnPreviousValue_String_visited = new java.util.HashSet(4);
        node.tracePrintReturnNewValue_String_visited = new java.util.HashSet(4);
        node.tracePrintBeginComputingValue_visited = false;
        node.tracePrintCycleBeginString_visited = false;
        node.tracePrintCycleEndString_visited = false;
        node.tracePrintStartingCycle_visited = false;
        node.traceComputeContext_visited = false;
        node.traceSignature_visited = false;
        node.traceBeginAttr_visited = false;
        node.traceEndAttr_visited = false;
        node.traceEndCachedAttr_visited = false;
        node.name_visited = false;
        node.type_visited = false;
        node.implType_visited = false;
        node.getTypeInSignature_visited = false;
        node.signature_visited = false;
        node.hostClass_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in Circular.jrag at line 4

	ASTExpression bottomValue;

    // Declared in Circular.jrag at line 12

	
	public void setBottomValue(ASTExpression v) {
		bottomValue = v;
	}

    // Declared in CollectionAttributes.jrag at line 785


  public boolean hasAnnotation(String s) { return false; }

    // Declared in JragCodeGen.jrag at line 267

  
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

    // Declared in JragCodeGen.jrag at line 278


  public String attributeName() {
    return getName();
  }

    // Declared in JragCodeGen.jrag at line 297

  
  public String hostFileComment() {
    String comment = getComment().trim();
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

    // Declared in JragCodeGen.jrag at line 330

  
  public String initLazyMaps() {
    StringBuffer s = new StringBuffer();
    if(ASTNode.lazyMaps) {
      if(!isCircular()) {
        if(getNumParameter() != 0 && visitCheckEnabled() && rewriteEnabled) {
          s.append("if(#NAME#_visited == null) #NAME#_visited = " + createDefaultMap + ";\n");
        }
        else if(getNumParameter() != 0 && visitCheckEnabled()) {
          s.append("if(#NAME#_visited == null) #NAME#_visited = " + createDefaultSet + ";\n");
        }
      }
      if(getNumParameter() != 0 && (getLazy() || isCircular())) {
        s.append("if(#NAME#_values == null) #NAME#_values = " + createDefaultMap + ";\n");
      }
    }
    return s.toString();
  }

    // Declared in JragCodeGen.jrag at line 350

  
  public String visitedDeclarations() {
    if(isCircular()) {
      if(getNumParameter() == 0)
        return "    protected int #CLASS#.#NAME#_visited = -1;\n";
      else {
        // the visited flag is part of the State$Value object
        return "";
      }
    }
    if(!visitCheckEnabled() && !isCircular()) return "";
    if(rewriteEnabled) {
      if(getNumParameter() == 0)
        return "    protected int #CLASS#.#NAME#_visited = -1;\n";
      else {
        if(ASTNode.lazyMaps)
          return "    protected " + typeDefaultMap + " #CLASS#.#NAME#_visited;\n";
        else
          return "    protected " + typeDefaultMap + " #CLASS#.#NAME#_visited = " + createDefaultMap + ";\n";
      }
    }
    if(getNumParameter() == 0)
      return "    protected boolean #CLASS#.#NAME#_visited = false;\n";
    else {
      if(ASTNode.lazyMaps)
        return "    protected " + typeDefaultSet + " #CLASS#.#NAME#_visited;\n";
      else
        return "    protected " + typeDefaultSet + " #CLASS#.#NAME#_visited = " + createDefaultSet + ";\n";
    }
  }

    // Declared in JragCodeGen.jrag at line 380

  
  public String visitedException() {
    if(!visitCheckEnabled()) return "";
    if(!traceVisitCheck)
      return "            throw new RuntimeException(\"Circular definition of attr: #METHODNAME# in class: #CLASS#.\");\n";
    else
      return "            System.out.println(\"Circular definition of attr: #METHODNAME# in class: \" + getClass().getName());\n";
  }

    // Declared in JragCodeGen.jrag at line 388

      
  public String resetVisit() {
    if(isCircular()) {
      if(getNumParameter() == 0)
        return "        #NAME#_visited = -1;\n";
      else {
        // visited is handled in value object
        return "";
      }
    }
    if(!visitCheckEnabled()) return "";
    else if(rewriteEnabled) {
      if(getNumParameter() == 0)
        return "        #NAME#_visited = -1;\n";
      else {
        if(ASTNode.lazyMaps)
          return "        #NAME#_visited = null;\n";
        else
          return "        #NAME#_visited = " + createDefaultMap + ";\n";
      }
    }
    if(getNumParameter() == 0)
      return "        #NAME#_visited = false;\n";
    else {
      if(ASTNode.lazyMaps)
        return "        #NAME#_visited = null;\n";
      else
        return "        #NAME#_visited = " + createDefaultSet + ";\n";
    }
  }

    // Declared in JragCodeGen.jrag at line 418

      
  public String visitedCheck() {
    if(!visitCheckEnabled()) return "";
    if(rewriteEnabled) {
      if(getNumParameter() == 0)
        return "        if(#NAME#_visited == state().boundariesCrossed)\n" + 
                          visitedException();
      else {
          return "        if(Integer.valueOf(state().boundariesCrossed).equals(#NAME#_visited.get(_parameters)))\n" + 
                          visitedException();
      }
    }
    if(getNumParameter() == 0)
      return "        if(#NAME#_visited)\n" + 
                          visitedException();
    else
      return "        if(#NAME#_visited.contains(_parameters))\n" + 
                          visitedException();
  }

    // Declared in JragCodeGen.jrag at line 437

  
  public String setVisited() {
    if(!visitCheckEnabled() && !isCircular()) return "";
    if(rewriteEnabled) {
      if(getNumParameter() == 0)
        return "        #NAME#_visited = state().boundariesCrossed;\n";
      else {
        return "        #NAME#_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));\n";
      }
    }
    if(getNumParameter() == 0)
      return "        #NAME#_visited = true;\n";
    else
      return "        #NAME#_visited.add(_parameters);\n";
  }

    // Declared in JragCodeGen.jrag at line 452

  
  public String clearVisited() {
    if(!visitCheckEnabled() && !isCircular()) return "";
    if(rewriteEnabled) {
      if(getNumParameter() == 0)
        return "        #NAME#_visited = -1;\n";
      else
        return "        #NAME#_visited.remove(_parameters);\n";
    }
    if(getNumParameter() == 0)
      return "        #NAME#_visited = false;\n";
    else
      return "        #NAME#_visited.remove(_parameters);\n";
  }

    // Declared in JragCodeGen.jrag at line 466
  
  
  public String cacheDeclarations() {
    if(!getLazy())
      return "";
    if(getNumParameter() == 0)
      return "    protected boolean #CLASS#.#NAME#_computed = false;\n" + 
             "    protected #TYPE# #CLASS#.#NAME#_value;\n";
    else if(declaredNTA()) {
      if(ASTNode.lazyMaps)
        return "    protected " + typeDefaultMap + " #CLASS#.#NAME#_values;\n" +
               "    protected " + ASTNode.listName + " #CLASS#.#NAME#_list;\n";
      else
        return "    protected " + typeDefaultMap + " #CLASS#.#NAME#_values = " + createDefaultMap + ";\n" +
               "    protected " + ASTNode.listName + " #CLASS#.#NAME#_list;\n";
    }
    else {
      if(ASTNode.lazyMaps)
        return "    protected " + typeDefaultMap + " #CLASS#.#NAME#_values;\n";
      else
        return "    protected " + typeDefaultMap + " #CLASS#.#NAME#_values = " + createDefaultMap + ";\n";
    }
  }

    // Declared in JragCodeGen.jrag at line 491


  public String resetCache() {
    if(!getLazy() && !isCircular())
      return "";
    if(getNumParameter() == 0) {
      if(isCircular()) {
        if(isPrimitive())
          return "        #NAME#_computed = false;\n" +
                 "        #NAME#_initialized = false;\n";
        else
          return "        #NAME#_computed = false;\n" +
                 "        #NAME#_initialized = false;\n" +
                 "        #NAME#_value = null;\n";
      }
      if(isPrimitive())
        return "        #NAME#_computed = false;\n";
      else
        return "        #NAME#_computed = false;\n" +
               "        #NAME#_value = null;\n";
    }
    else if(isCircular()) {
        if(ASTNode.lazyMaps)
          return "        #NAME#_values = null;\n";
        else
          return "        #NAME#_values = " + createDefaultMap + ";\n";
    }
    else {
      if(ASTNode.lazyMaps)
        return "        #NAME#_values = null;\n";
      else
        return "        #NAME#_values = " + createDefaultMap + ";\n";
    }
  }

    // Declared in JragCodeGen.jrag at line 565


  public boolean isPrimitive() {
	String type = getType();
	return type.equals("int") || type.equals("short") || type.equals("long") || 
	       type.equals("float") || type.equals("double") || type.equals("boolean") ||
	       type.equals("char") || type.equals("byte");
  }

    // Declared in JragCodeGen.jrag at line 571

  public boolean isPrimitive(String type) {
	  return type.equals("int") || type.equals("short") || type.equals("long") || 
	       type.equals("float") || type.equals("double") || type.equals("boolean") ||
	       type.equals("char") || type.equals("byte");
  }

    // Declared in JragCodeGen.jrag at line 577

  
  public String cacheCheck() {
    if(!getLazy() && !isCircular())
      return "";
    if(isNTA() && !(findCorrespondingNTA() instanceof TokenComponent)) {
      int index = indexNTAchild();
      if(getNumParameter() == 0)
        return "        if(#NAME#_computed) {\n" +
               traceEndCachedAttr() +
               "            return (" + getType() + ")ASTNode.getChild(this, #NAME#ChildPosition());\n" +
               "        }\n";
      else
        return "        if(#NAME#_values.containsKey(_parameters)) {\n" + 
               traceEndCachedAttr() +
               "            return (" + getType() + ")ASTNode.getChild(this, #NAME#ChildPosition()));\n" +
               "        }\n";
    }
    if(getNumParameter() == 0)
      return "        if(#NAME#_computed) {\n" + 
             traceEndCachedAttr() +
             "            return #NAME#_value;\n" +
             "        }\n";
    else {
      if(isCircular())
        return "        if(#NAME#_values.containsKey(_parameters)) {\n" + 
               "            Object _o = #NAME#_values.get(_parameters);\n" +
               "            if(!(_o instanceof ASTNode$State.CircularValue)) {\n" +
               traceEndCachedAttr() +
               "                return " + fromReferenceType("_o", getType()) + ";\n" +
               "            }\n" +
               "            else\n" +
               "                _value = (ASTNode$State.CircularValue)_o;\n" +
               "        }\n";
      else
        return "        if(#NAME#_values.containsKey(_parameters)) {\n" + 
               traceEndCachedAttr() +
               "            return " + fromReferenceType("#NAME#_values.get(_parameters)", getType()) + ";\n" +
               "        }\n";
    }
  }

    // Declared in JragCodeGen.jrag at line 617

  
  public String parameterStructure() {
    if(getNumParameter() == 0 || (!getLazy() && !isCircular() && !visitCheckEnabled()))
      return "";
    else if(getNumParameter() == 1)
      return "        Object _parameters = " + getParameter(0).toReferenceType() + ";\n";
    else {
      StringBuffer s = new StringBuffer();
        s.append("        java.util.List _parameters = new java.util.ArrayList(" + getNumParameter() + ");\n");
      for(int i = 0; i < getNumParameter(); i++) {
        s.append("        _parameters.add(" + getParameter(i).toReferenceType() + ");\n");
      }
      return s.toString();
    }
  }

    // Declared in JragCodeGen.jrag at line 632

  
  public String cacheInit() {
    if(!getLazy())
      return "";
    return cacheInitRewrite();
  }

    // Declared in JragCodeGen.jrag at line 638

  
  public String cacheInitRewrite() {
    if(!ASTNode.rewriteEnabled) {
      return "";
    }
    else {
      return "        int num = state.boundariesCrossed;\n" + 
             "        boolean isFinal = this.is$Final();\n";
    }
  }

    // Declared in JragCodeGen.jrag at line 648

  
  public String callCompute() {
    if(getLazy() && getNumParameter() == 0)
      return "        #NAME#_value = #METHODNAME#_compute(#PARM#);\n";
    else 
      return "        #TYPE# #NAME#_value = #METHODNAME#_compute(#PARM#);\n";
  }

    // Declared in JragCodeGen.jrag at line 669

  
  public String cacheStoreRewrite() {
    if(!ASTNode.rewriteEnabled || getFinal())
      return   "        if(true)\n";
    else
      return   "        if(isFinal && num == state().boundariesCrossed)\n";
  }

    // Declared in JragCodeGen.jrag at line 676


  public String cacheStore() {
    if(!getLazy())
      return "";
	if(getNumParameter() == 0)
	  return cacheStoreRewrite() +
	         "            #NAME#_computed = true;\n";
	else
	  return cacheStoreRewrite() + 
             "            #NAME#_values.put(_parameters, " + toReferenceType("#NAME#_value", getType()) + ");\n";
  }

    // Declared in JragCodeGen.jrag at line 687

  
  public String returnStmt() {
    if(isNTA() && !(findCorrespondingNTA() instanceof TokenComponent)) {
      int index = indexNTAchild();
      return "        return (" + getType() + ")ASTNode.getChild(this, #NAME#ChildPosition());\n";
    }
    return "        return #NAME#_value;\n";
  }

    // Declared in JragCodeGen.jrag at line 706

  
  public String computeMethod() {
    // TODO: INH
    if(!ASTNode.parentInterface)
      return  "";
    return  "    private #TYPE# #CLASS#.#METHODNAME#_compute(#PARMDECL#) {\n" +
            "            ASTNode n = getParent();\n" + 
            "            ASTNode caller = this;\n" + 
            "            ASTNode child = null;\n" + 
            "            while(!(n instanceof Defines_#TYPEINSIGNATURE#_#NAME#) && n != null) {\n" +
            "                child = caller;\n" + 
            "                caller = n;\n" + 
            "                n = n.getParent();\n" + 
            "            }\n" +
            inhDebugString() +
            "            return ((Defines_#TYPEINSIGNATURE#_#NAME#)n).Define_#TYPEINSIGNATURE#_#METHODNAME#(#INTERFACEPARM#);\n" + 
            "    }\n\n";
  }

    // Declared in JragCodeGen.jrag at line 925


  public String circularComputeCall() { return ""; }

    // Declared in JragCodeGen.jrag at line 934

  
  public String resetCycleInit() {
     return
        "            state.RESET_CYCLE = true;\n" +
        inhDebugString() +
        "            " + circularComputeCall() + ";\n" +
        "            state.RESET_CYCLE = false;\n";
  }

    // Declared in JragCodeGen.jrag at line 942


  public String resetCycleCheck() {
     if(getNumParameter() == 0)
       return
         "            if (state.RESET_CYCLE) {\n" +
         "                #NAME#_computed = false;\n" +
         "                #NAME#_initialized = false;\n" + 
         "                #NAME#_visited = -1;\n" +
         "        " + returnStmt() + 
         "            }\n";
     else
       return
         "            if (state.RESET_CYCLE) {\n" +
         "                #NAME#_values.remove(_parameters);\n" + 
         "            }\n";
  }

    // Declared in JragCodeGen.jrag at line 958


  public String cacheCycleInit() {
     if (! cacheCycle) return "";
     return
        "            state.LAST_CYCLE = true;\n" +
        inhDebugString() +
        "            " + circularComputeCall() + ";\n" +
        "            state.LAST_CYCLE = false;\n";
  }

    // Declared in JragCodeGen.jrag at line 967


  public String cacheCycleCheck() {
     if (! cacheCycle) return "";
     if(getNumParameter() == 0)
       return
         "            if (state.LAST_CYCLE) {\n" +
         "                #NAME#_computed = true;\n" +
         inhDebugString() +
         "                return " + circularComputeCall() + ";\n" +
         "            }\n";
     else
       return
         "            if (state.LAST_CYCLE) {\n" +
         inhDebugString() +
         "                #NAME#_values.put(_parameters, new_#NAME#_value);\n" +
         "            }\n";
  }

    // Declared in JragCodeGen.jrag at line 984


  public String addComponentCheck() {
    if(!componentCheck || !visitCheckEnabled) return "";
    String arg = getNumParameter() == 0 ? "null" : "_parameters";
    if(getNumParameter() == 0) {
      if(rewriteEnabled)
        return 
        "     if(#NAME#_visited == state.boundariesCrossed && !state.containsEvalEntry(this, \"#NAME#\", null))\n" +
        "       throw new java.lang.RuntimeException(\"XXX\");\n";
      else
        return 
        "     if(#NAME#_visited && !state.containsEvalEntry(this, \"#NAME#\", null))\n" +
        "       throw new java.lang.RuntimeException(\"XXX\");\n";
    }
    else {
      if(rewriteEnabled)
        return
        "     if(new Integer(state.boundariesCrossed).equals(_value.visited) && !state.containsEvalEntry(this, \"#NAME#\", _parameters))\n" +
        "       throw new java.lang.RuntimeException(\"XXX\");\n";
      else
        return
        "     if(_value.visited && !state.containsEvalEntry(this, \"#NAME#\", _parameters))\n" +
        "       throw new java.lang.RuntimeException(\"XXX\");\n";
    }
  }

    // Declared in JragCodeGen.jrag at line 1009


  public String addAddToComponent() {
    if(!componentCheck || !visitCheckEnabled) return "";
    if(getNumParameter() == 0)
      return
        "     state.addEvalEntry(this, \"#NAME#\", null);\n";
    else
      return
        "     state.addEvalEntry(this, \"#NAME#\", _parameters);\n";
  }

    // Declared in JragCodeGen.jrag at line 1019


  public String addInterruptedCircleDeclaration() {
    if(!componentCheck) return "";
    return "boolean interruptedCircle = false;\n";
  }

    // Declared in JragCodeGen.jrag at line 1024


  public String addCheckInterruptedCircle() {
    if(!componentCheck) return "";
    return 
      "if(state.IN_CIRCLE) {\n" + 
      "  interruptedCircle = true;\n" +
      "  state.IN_CIRCLE = false;\n" +
      "  state.pushEvalStack();\n" +
      "}\n";
  }

    // Declared in JragCodeGen.jrag at line 1034


  public String addClearInterruptedCircle() {
    if(!componentCheck) return "";
    return 
      "if(interruptedCircle) {\n" + 
      "  state.IN_CIRCLE = true;\n" +
      "  state.popEvalStack();\n" +
      "}\n";
  }

    // Declared in JragCodeGen.jrag at line 1043

  
  public String differs(String s1, String s2) {
    if (isPrimitive())
      return s1 + "!=" + s2;
    else
      return "(" + s1 + "==null && " + s2 + "!=null)" + " || " + "(" + s1 + "!=null && " + "!" + s1 + ".equals(" + s2 + ")" + ")";
  }

    // Declared in JragCodeGen.jrag at line 1073


  
  public boolean isNTA() {
    return false;
  }

    // Declared in JragCodeGen.jrag at line 1081


  public int indexNTAchild() {
    Components comp = findCorrespondingNTA();
    TypeDecl c = hostClass();
    while(c != null) {
      int index = 0;
      for(Iterator iter = c.getComponents(); iter.hasNext(); ) {
        Components next = (Components)iter.next();
        if(next == comp)
          return index;
        if(!(next instanceof TokenComponent))
          index++;
      }
      c = c instanceof ASTDecl ? ((ASTDecl)c).superClass() : null;
    }
    return -1;
  }

    // Declared in JragCodeGen.jrag at line 1098


  public Components findCorrespondingNTA() {
    if(!getName().startsWith("get"))
      return null;
    String attrName = getName().substring(3);
    TypeDecl c = hostClass();
    while(c != null) {
      for(Iterator iter = c.getComponents(); iter.hasNext(); ) {
        Components comp = (Components)iter.next();
        if(comp.name().equals(attrName) && (
            comp instanceof OptionalComponentNTA
            || comp instanceof TokenComponentNTA
            || comp instanceof AggregateComponentsNTA )) {
          return comp;
        }
        if(attrName.equals(comp.name() + "Opt") && comp instanceof OptionalComponentNTA) {
          return comp;
        }
        if(attrName.equals(comp.name() + "List") && comp instanceof ListComponentsNTA) {
          return comp;
        }
      }
      c = c instanceof ASTDecl ? ((ASTDecl)c).superClass() : null;
    }
    return null;
  }

    // Declared in JragCodeGen.jrag at line 1124

  
  public String higherOrderAttributeCode() {
    return "";
  }

    // Declared in JragCodeGen.jrag at line 1765


  protected String trace(String s) {
    if(!tracing)
      return "";
    StringBuilder b = new StringBuilder();
    b.append("System.out.println(\"");
    b.append(s);
    b.append("\");\n");
    return b.toString();
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 40

    public AttrDecl(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public AttrDecl(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public AttrDecl() {
        this(0);

        setChild(new List(), 0);

    }

    // Declared in Ast.ast at line 18


    // Declared in Ast.ast line 40
    public AttrDecl(List p0, String p1, String p2, boolean p3, String p4, int p5, int p6, boolean p7, boolean p8, String p9, String p10) {
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
    }

    // Declared in Ast.ast at line 32


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "AttrDecl"+ "\"" + getName() + "\""+ "\"" + getType() + "\""+ "\"" + getLazy() + "\""+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getFinal() + "\""+ "\"" + getNTA() + "\""+ "\"" + getComment() + "\""+ "\"" + getAspectName() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 39


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 43


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 48


public void checkChild(Node n, int i) {
  if(i == 0) {
    if(!(n instanceof List)) throw new Error("Child number 0 of AttrDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Parameter)) throw new Error("Child number " + k + " in ParameterList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Parameter");
  }
}

    // Declared in Ast.ast at line 56


  public int getNumChild() {
    return 1;
  }

    // Declared in Ast.ast at line 59

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

    // Declared in JragCodeGen.jrag at line 90


    public boolean getLazy() {
    return (refined_Ast_getLazy() || cacheAll) && (!noCaching || declaredNTA());
  }

    // Declared in Errorcheck.jrag at line 73
    public abstract String error();
    protected boolean parametersDecl_visited = false;
    // Declared in Attributes.jrag at line 967
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
    // Declared in Attributes.jrag at line 977
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
    // Declared in Attributes.jrag at line 988
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
    // Declared in Attributes.jrag at line 989
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
    // Declared in Attributes.jrag at line 1034
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
    // Declared in Attributes.jrag at line 1035
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

    protected boolean isCircular_visited = false;
    // Declared in Circular.jrag at line 6
    public boolean isCircular() {
boolean interruptedCircle = false;
        if(isCircular_visited)
            throw new RuntimeException("Circular definition of attr: isCircular in class: ");
        isCircular_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean isCircular_value = isCircular_compute();
        isCircular_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return isCircular_value;
    }

    private boolean isCircular_compute() {  return  hasBottomValue();  }

    protected boolean hasBottomValue_visited = false;
    // Declared in Circular.jrag at line 8
    public boolean hasBottomValue() {
boolean interruptedCircle = false;
        if(hasBottomValue_visited)
            throw new RuntimeException("Circular definition of attr: hasBottomValue in class: ");
        hasBottomValue_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean hasBottomValue_value = hasBottomValue_compute();
        hasBottomValue_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return hasBottomValue_value;
    }

    private boolean hasBottomValue_compute() {  return  bottomValue != null;  }

    protected boolean getBottomValue_visited = false;
    // Declared in Circular.jrag at line 10
    public ASTExpression getBottomValue() {
boolean interruptedCircle = false;
        if(getBottomValue_visited)
            throw new RuntimeException("Circular definition of attr: getBottomValue in class: ");
        getBottomValue_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        ASTExpression getBottomValue_value = getBottomValue_compute();
        getBottomValue_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return getBottomValue_value;
    }

    private ASTExpression getBottomValue_compute() {  return  bottomValue;  }

    protected boolean root_visited = false;
    // Declared in CollectionAttributes.jrag at line 59
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

    private TypeDecl root_compute() {  return  hostClass().env().root();  }

    protected boolean collDebugString_visited = false;
    // Declared in CollectionAttributes.jrag at line 62
    public String collDebugString() {
boolean interruptedCircle = false;
        if(collDebugString_visited)
            throw new RuntimeException("Circular definition of attr: collDebugString in class: ");
        collDebugString_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String collDebugString_value = collDebugString_compute();
        collDebugString_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return collDebugString_value;
    }

    private String collDebugString_compute()  {
    if(!debugMode)
      return "";
    else
      return "        if(node == null) throw new RuntimeException(\"Trying to evaluate collection attribute in subtree not attached to main tree\");";
  }

    protected boolean separateEvaluation_visited = false;
    // Declared in CollectionAttributes.jrag at line 325
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

    private boolean separateEvaluation_compute() {  return  hasAnnotation("@SeparateEvaluation") || naive();  }

    protected boolean lazyCondition_visited = false;
    // Declared in CollectionAttributes.jrag at line 327
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

    private boolean lazyCondition_compute() {  return  hasAnnotation("@LazyCondition");  }

    protected boolean onePhase_visited = false;
    // Declared in CollectionAttributes.jrag at line 330
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

    private boolean onePhase_compute() {  return  hasAnnotation("@OnePhase");  }

    protected boolean naive_visited = false;
    // Declared in CollectionAttributes.jrag at line 333
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

    private boolean naive_compute() {  return  hasAnnotation("@Naive");  }

    protected boolean collectingSignature_visited = false;
    protected boolean collectingSignature_computed = false;
    protected String collectingSignature_value;
    // Declared in CollectionAttributes.jrag at line 599
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

    private String collectingSignature_compute() {  return  attributeSignature();  }

    protected boolean visitCheckEnabled_visited = false;
    // Declared in JragCodeGen.jrag at line 348
    public boolean visitCheckEnabled() {
boolean interruptedCircle = false;
        if(visitCheckEnabled_visited)
            throw new RuntimeException("Circular definition of attr: visitCheckEnabled in class: ");
        visitCheckEnabled_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean visitCheckEnabled_value = visitCheckEnabled_compute();
        visitCheckEnabled_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return visitCheckEnabled_value;
    }

    private boolean visitCheckEnabled_compute() {  return  visitCheckEnabled;  }

    protected boolean declaredNTA_visited = false;
    // Declared in JragCodeGen.jrag at line 487
    public boolean declaredNTA() {
boolean interruptedCircle = false;
        if(declaredNTA_visited)
            throw new RuntimeException("Circular definition of attr: declaredNTA in class: ");
        declaredNTA_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean declaredNTA_value = declaredNTA_compute();
        declaredNTA_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return declaredNTA_value;
    }

    private boolean declaredNTA_compute() {  return  false;  }

    protected boolean inhDebugString_visited = false;
    // Declared in JragCodeGen.jrag at line 724
    public String inhDebugString() {
boolean interruptedCircle = false;
        if(inhDebugString_visited)
            throw new RuntimeException("Circular definition of attr: inhDebugString in class: ");
        inhDebugString_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String inhDebugString_value = inhDebugString_compute();
        inhDebugString_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return inhDebugString_value;
    }

    private String inhDebugString_compute()  {
    if(!debugMode) return "";
    if(ASTNode.parentInterface)
      return "        if(n == null) throw new RuntimeException(\"Trying to evaluate attribute in subtree not attached to main tree\");\n";
    else
      return "        if(getParent() == null) throw new RuntimeException(\"Trying to evaluate attribute in subtree not attached to main tree\");\n";
  }

    protected boolean cycleLimitCheck_visited = false;
    // Declared in JragCodeGen.jrag at line 740
    public String cycleLimitCheck() {
boolean interruptedCircle = false;
        if(cycleLimitCheck_visited)
            throw new RuntimeException("Circular definition of attr: cycleLimitCheck in class: ");
        cycleLimitCheck_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String cycleLimitCheck_value = cycleLimitCheck_compute();
        cycleLimitCheck_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return cycleLimitCheck_value;
    }

    private String cycleLimitCheck_compute() {  return  "";  }

    protected java.util.Set tracePrintReturnPreviousValue_String_visited = new java.util.HashSet(4);
    // Declared in JragCodeGen.jrag at line 1714
    public String tracePrintReturnPreviousValue(String varName) {
        Object _parameters = varName;
boolean interruptedCircle = false;
        if(tracePrintReturnPreviousValue_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: tracePrintReturnPreviousValue in class: ");
        tracePrintReturnPreviousValue_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String tracePrintReturnPreviousValue_String_value = tracePrintReturnPreviousValue_compute(varName);
        tracePrintReturnPreviousValue_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return tracePrintReturnPreviousValue_String_value;
    }

    private String tracePrintReturnPreviousValue_compute(String varName) {  return  "";  }

    protected java.util.Set tracePrintReturnNewValue_String_visited = new java.util.HashSet(4);
    // Declared in JragCodeGen.jrag at line 1715
    public String tracePrintReturnNewValue(String varName) {
        Object _parameters = varName;
boolean interruptedCircle = false;
        if(tracePrintReturnNewValue_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: tracePrintReturnNewValue in class: ");
        tracePrintReturnNewValue_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String tracePrintReturnNewValue_String_value = tracePrintReturnNewValue_compute(varName);
        tracePrintReturnNewValue_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return tracePrintReturnNewValue_String_value;
    }

    private String tracePrintReturnNewValue_compute(String varName) {  return  "";  }

    protected boolean tracePrintBeginComputingValue_visited = false;
    // Declared in JragCodeGen.jrag at line 1716
    public String tracePrintBeginComputingValue() {
boolean interruptedCircle = false;
        if(tracePrintBeginComputingValue_visited)
            throw new RuntimeException("Circular definition of attr: tracePrintBeginComputingValue in class: ");
        tracePrintBeginComputingValue_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String tracePrintBeginComputingValue_value = tracePrintBeginComputingValue_compute();
        tracePrintBeginComputingValue_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return tracePrintBeginComputingValue_value;
    }

    private String tracePrintBeginComputingValue_compute() {  return  "";  }

    protected boolean tracePrintCycleBeginString_visited = false;
    // Declared in JragCodeGen.jrag at line 1717
    public String tracePrintCycleBeginString() {
boolean interruptedCircle = false;
        if(tracePrintCycleBeginString_visited)
            throw new RuntimeException("Circular definition of attr: tracePrintCycleBeginString in class: ");
        tracePrintCycleBeginString_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String tracePrintCycleBeginString_value = tracePrintCycleBeginString_compute();
        tracePrintCycleBeginString_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return tracePrintCycleBeginString_value;
    }

    private String tracePrintCycleBeginString_compute() {  return  "";  }

    protected boolean tracePrintCycleEndString_visited = false;
    // Declared in JragCodeGen.jrag at line 1718
    public String tracePrintCycleEndString() {
boolean interruptedCircle = false;
        if(tracePrintCycleEndString_visited)
            throw new RuntimeException("Circular definition of attr: tracePrintCycleEndString in class: ");
        tracePrintCycleEndString_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String tracePrintCycleEndString_value = tracePrintCycleEndString_compute();
        tracePrintCycleEndString_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return tracePrintCycleEndString_value;
    }

    private String tracePrintCycleEndString_compute() {  return  "";  }

    protected boolean tracePrintStartingCycle_visited = false;
    // Declared in JragCodeGen.jrag at line 1719
    public String tracePrintStartingCycle() {
boolean interruptedCircle = false;
        if(tracePrintStartingCycle_visited)
            throw new RuntimeException("Circular definition of attr: tracePrintStartingCycle in class: ");
        tracePrintStartingCycle_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String tracePrintStartingCycle_value = tracePrintStartingCycle_compute();
        tracePrintStartingCycle_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return tracePrintStartingCycle_value;
    }

    private String tracePrintStartingCycle_compute() {  return  "";  }

    protected boolean traceComputeContext_visited = false;
    // Declared in JragCodeGen.jrag at line 1720
    public String traceComputeContext() {
boolean interruptedCircle = false;
        if(traceComputeContext_visited)
            throw new RuntimeException("Circular definition of attr: traceComputeContext in class: ");
        traceComputeContext_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String traceComputeContext_value = traceComputeContext_compute();
        traceComputeContext_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return traceComputeContext_value;
    }

    private String traceComputeContext_compute() {  return  "";  }

    protected boolean traceSignature_visited = false;
    // Declared in JragCodeGen.jrag at line 1722
    public String traceSignature() {
boolean interruptedCircle = false;
        if(traceSignature_visited)
            throw new RuntimeException("Circular definition of attr: traceSignature in class: ");
        traceSignature_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String traceSignature_value = traceSignature_compute();
        traceSignature_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return traceSignature_value;
    }

    private String traceSignature_compute()  {
    StringBuilder b = new StringBuilder();
    b.append(getAspectName());
    b.append(": ");
    if(this instanceof SynDecl)
      b.append("syn ");
    if(this instanceof InhDecl)
      b.append("inh ");
    if(getLazy())
      b.append("lazy ");
    if(isCircular())
      b.append(" circular");
    
    b.append(getType());
    b.append(" ");
    b.append(hostClass().name());
    b.append(".");
    b.append(name());
    b.append("(");
    for(int i = 0; i < getNumParameter(); i++) {
      if(i != 0)
        b.append(", ");
      b.append(getParameter(i).getType());
      b.append(" ");
      b.append(getParameter(i).getName());
    }
    b.append(")");
    b.append(", ");
    b.append("this = \" + this.getClass().getName() + \"@\"+ Integer.toHexString(this.hashCode()) + \"");
    for(int i = 0; i < getNumParameter(); i++) {
      String name = getParameter(i).getName();
      String type = getParameter(i).getType();
      b.append(", ");
      b.append(name);
      b.append(" = ");
      if(isPrimitive(type) || type.equals("String") || type.equals("java.lang.String"))
        b.append("\" + " + name + " + \"");
      else
        b.append("\" + " + name + ".getClass().getName() + \"@\" + Integer.toHexString(" + name + ".hashCode()) + \"");
    }
    return b.toString();
  }

    protected boolean traceBeginAttr_visited = false;
    // Declared in JragCodeGen.jrag at line 1775
    public String traceBeginAttr() {
boolean interruptedCircle = false;
        if(traceBeginAttr_visited)
            throw new RuntimeException("Circular definition of attr: traceBeginAttr in class: ");
        traceBeginAttr_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String traceBeginAttr_value = traceBeginAttr_compute();
        traceBeginAttr_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return traceBeginAttr_value;
    }

    private String traceBeginAttr_compute() {  return 
    trace("begin " + traceSignature());  }

    protected boolean traceEndAttr_visited = false;
    // Declared in JragCodeGen.jrag at line 1777
    public String traceEndAttr() {
boolean interruptedCircle = false;
        if(traceEndAttr_visited)
            throw new RuntimeException("Circular definition of attr: traceEndAttr in class: ");
        traceEndAttr_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String traceEndAttr_value = traceEndAttr_compute();
        traceEndAttr_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return traceEndAttr_value;
    }

    private String traceEndAttr_compute() {  return 
    trace("end " + traceSignature());  }

    protected boolean traceEndCachedAttr_visited = false;
    // Declared in JragCodeGen.jrag at line 1779
    public String traceEndCachedAttr() {
boolean interruptedCircle = false;
        if(traceEndCachedAttr_visited)
            throw new RuntimeException("Circular definition of attr: traceEndCachedAttr in class: ");
        traceEndCachedAttr_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String traceEndCachedAttr_value = traceEndCachedAttr_compute();
        traceEndCachedAttr_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return traceEndCachedAttr_value;
    }

    private String traceEndCachedAttr_compute() {  return 
    trace("end cached " + traceSignature());  }

    protected boolean name_visited = false;
    // Declared in NameBinding.jrag at line 11
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
    // Declared in NameBinding.jrag at line 12
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

    private String type_compute() {  return  getType();  }

    protected boolean implType_visited = false;
    // Declared in NameBinding.jrag at line 15
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

    private String implType_compute() {  return  computeImplName(getType());  }

    protected boolean getTypeInSignature_visited = false;
    // Declared in NameBinding.jrag at line 18
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

    private String getTypeInSignature_compute() {  return  convTypeNameToSignature(type());  }

    protected boolean signature_visited = false;
    // Declared in NameBinding.jrag at line 41
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
    // Declared in NameBinding.jrag at line 6
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
