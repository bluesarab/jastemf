
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public class ASTDecl extends TypeDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        testCircular_String_visited = new java.util.HashSet(4);
        isCircular_visited = false;
        superClass_visited = false;
        superClass_computed = false;
        superClass_value = null;
        getSuperClassName_visited = false;
        getSuperClassName_computed = false;
        getSuperClassName_value = null;
        instanceOf_TypeDecl_visited = new java.util.HashSet(4);
        subclasses_visited = false;
        subclasses_computed = false;
        subclasses_value = null;
        fathers_visited = false;
        fathers_computed = false;
        fathers_value = null;
        tempComponents_visited = false;
        tempComponents_computed = false;
        tempComponents_value = null;
        getComponents_visited = false;
        redefinesTokenComponent_TokenComponent_visited = new java.util.HashSet(4);
        isRootNode_visited = false;
        collectAstErrors_visited = false;
        astError_visited = false;
        numNonNTAComponents_visited = false;
        numRegularChildren_visited = false;
        rewriteWithNoPhaseCondition_visited = false;
        rewritePhaseConditions_visited = false;
        lookupComponents_String_visited = new java.util.HashSet(4);
        lookupComponents_String_values = new java.util.HashMap(4);
        isASTNode_visited = false;
        hasCollEq_CollDecl_visited = new java.util.HashSet(4);
        collectErrors_visited = false;
        hasInhEq_String_visited = new java.util.HashSet(4);
        lookupSynDeclPrefix_String_visited = new java.util.HashSet(4);
        lookupInhDeclPrefix_String_visited = new java.util.HashSet(4);
        hasRewrites_visited = false;
        hasRewrites_computed = false;
        synEquations_visited = false;
        synEquations_computed = false;
        synEquations_value = null;
        synDeclarations_visited = false;
        synDeclarations_computed = false;
        synDeclarations_value = null;
        inhEquations_visited = false;
        inhEquations_computed = false;
        inhEquations_value = null;
        inhDeclarations_visited = false;
        inhDeclarations_computed = false;
        inhDeclarations_value = null;
        lookupSynDecl_String_visited = new java.util.HashSet(4);
        lookupSynDecl_String_values = new java.util.HashMap(4);
        lookupSynEq_String_visited = new java.util.HashSet(4);
        lookupSynEq_String_values = new java.util.HashMap(4);
        lookupInhDecl_String_visited = new java.util.HashSet(4);
        lookupInhDecl_String_values = new java.util.HashMap(4);
        lookupInhDeclSubclasses_String_visited = new java.util.HashSet(4);
        lookupInhDeclSubclasses_String_values = new java.util.HashMap(4);
        lookupInhEq_String_String_visited = new java.util.HashSet(4);
        lookupInhEq_String_String_values = new java.util.HashMap(4);
        hasInhEqFor_ASTDecl_String_Collection_visited = new java.util.HashSet(4);
        hasInhDeclFor_String_visited = new java.util.HashSet(4);
        hasInhDeclFor_String_values = new java.util.HashMap(4);
        subclassesLeafNodes_visited = false;
        subclassesLeafNodes_computed = false;
        subclassesLeafNodes_value = null;
        allTreeChildren_visited = false;
        allTreeChildren_computed = false;
        allTreeChildren_value = null;
        getInhDeclFor_String_Collection_visited = new java.util.HashSet(4);
    }
    public Object clone() throws CloneNotSupportedException {
        ASTDecl node = (ASTDecl)super.clone();
        node.testCircular_String_visited = new java.util.HashSet(4);
        node.isCircular_visited = false;
        node.superClass_visited = false;
        node.superClass_computed = false;
        node.superClass_value = null;
        node.getSuperClassName_visited = false;
        node.getSuperClassName_computed = false;
        node.getSuperClassName_value = null;
        node.instanceOf_TypeDecl_visited = new java.util.HashSet(4);
        node.subclasses_visited = false;
        node.subclasses_computed = false;
        node.subclasses_value = null;
        node.fathers_visited = false;
        node.fathers_computed = false;
        node.fathers_value = null;
        node.tempComponents_visited = false;
        node.tempComponents_computed = false;
        node.tempComponents_value = null;
        node.getComponents_visited = false;
        node.redefinesTokenComponent_TokenComponent_visited = new java.util.HashSet(4);
        node.isRootNode_visited = false;
        node.collectAstErrors_visited = false;
        node.astError_visited = false;
        node.numNonNTAComponents_visited = false;
        node.numRegularChildren_visited = false;
        node.rewriteWithNoPhaseCondition_visited = false;
        node.rewritePhaseConditions_visited = false;
        node.lookupComponents_String_visited = new java.util.HashSet(4);
        node.lookupComponents_String_values = new java.util.HashMap(4);
        node.isASTNode_visited = false;
        node.hasCollEq_CollDecl_visited = new java.util.HashSet(4);
        node.collectErrors_visited = false;
        node.hasInhEq_String_visited = new java.util.HashSet(4);
        node.lookupSynDeclPrefix_String_visited = new java.util.HashSet(4);
        node.lookupInhDeclPrefix_String_visited = new java.util.HashSet(4);
        node.hasRewrites_visited = false;
        node.hasRewrites_computed = false;
        node.synEquations_visited = false;
        node.synEquations_computed = false;
        node.synEquations_value = null;
        node.synDeclarations_visited = false;
        node.synDeclarations_computed = false;
        node.synDeclarations_value = null;
        node.inhEquations_visited = false;
        node.inhEquations_computed = false;
        node.inhEquations_value = null;
        node.inhDeclarations_visited = false;
        node.inhDeclarations_computed = false;
        node.inhDeclarations_value = null;
        node.lookupSynDecl_String_visited = new java.util.HashSet(4);
        node.lookupSynDecl_String_values = new java.util.HashMap(4);
        node.lookupSynEq_String_visited = new java.util.HashSet(4);
        node.lookupSynEq_String_values = new java.util.HashMap(4);
        node.lookupInhDecl_String_visited = new java.util.HashSet(4);
        node.lookupInhDecl_String_values = new java.util.HashMap(4);
        node.lookupInhDeclSubclasses_String_visited = new java.util.HashSet(4);
        node.lookupInhDeclSubclasses_String_values = new java.util.HashMap(4);
        node.lookupInhEq_String_String_visited = new java.util.HashSet(4);
        node.lookupInhEq_String_String_values = new java.util.HashMap(4);
        node.hasInhEqFor_ASTDecl_String_Collection_visited = new java.util.HashSet(4);
        node.hasInhDeclFor_String_visited = new java.util.HashSet(4);
        node.hasInhDeclFor_String_values = new java.util.HashMap(4);
        node.subclassesLeafNodes_visited = false;
        node.subclassesLeafNodes_computed = false;
        node.subclassesLeafNodes_value = null;
        node.allTreeChildren_visited = false;
        node.allTreeChildren_computed = false;
        node.allTreeChildren_value = null;
        node.getInhDeclFor_String_Collection_visited = new java.util.HashSet(4);
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    public ASTNode copy() {
      try {
          ASTDecl node = (ASTDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
    public ASTNode fullCopy() {
        ASTDecl res = (ASTDecl)copy();
        for(int i = 0; i < getNumChild(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in JaddCodeGen.jrag at line 66


  public void jjtGen(PrintWriter stream, String parserName, boolean rewriteEnabled) {
	    String s;

	    jjtGenConstructor(stream, parserName, rewriteEnabled);
	    
	    // Generate code common for all nodes by adding them to ASTNode
	    if(name().equals("ASTNode")) {
	      jjtGenASTNode(stream, parserName, rewriteEnabled);
	    }
	    else if(name().equals(ASTNode.listName)) {
	    	stream.print(jjtGenListAPI());
	    }
	    else if(name().equals(ASTNode.optName)) {
	      // do not override getNumChild with implementation below
	    }
	    else {
	     /* stream.println("  protected int " +name() + ".numChildren() {");
	      stream.println("    return " + numRegularChildren() + ";");
	      stream.println("  }");
	     
	      if(debugMode && isRootNode()) {
	        stream.println("    protected boolean " + name() + ".debugNodeAttachmentIsRoot() { return true; }");
	      }*/
	    }
	    if(rewriteEnabled) {
	      stream.println("    public boolean " + name() + ".mayHaveRewrite() {");
	      if(name().equals(ASTNode.listName))
	        stream.println("        return true;");
	      else if(!hasRewrites())
	        stream.println("        return false;");
	      else if(!stagedRewrites || rewriteWithNoPhaseCondition())
	        stream.println("        return true;");
	      else {
	        for(Iterator iter = rewritePhaseConditions().iterator(); iter.hasNext(); ) {
	          String condition = (String)iter.next();
	          stream.println("        if(" + condition + ") return true;");
	        }
	        stream.println("        return false;");
	      }
	      stream.println("    }");
	    }
	  }

    // Declared in JaddCodeGen.jrag at line 111

 
  
  
  public void jjtGenConstructor(PrintWriter stream, String parserName, boolean rewriteEnabled) {
    stream.println("    // Declared in " + getFileName() + " line " + getStartLine() + "\n");
    String s = "    public #ID#.#ID_IMPL#() {\n" + // L\u00ef\u00bf\u00bdgg till null f\u00ef\u00bf\u00bdr agg, tom lista f\u00ef\u00bf\u00bdr list, false f\u00ef\u00bf\u00bdr opt
    		   "        super();\n";
    s = s.replaceAll("#ID#", name());
    s = s.replaceAll("#ID_IMPL#", implName());
    stream.println(s);
   
    int i = 0;
    for(Iterator iter = getComponents(); iter.hasNext(); ) {
      Components c = (Components)iter.next();
      if(c instanceof ListComponents) {
        stream.println("        setChild(new " + ASTNode.listName + "(), " + String.valueOf(i) + ");");
        i++;
      }
      else if(c instanceof OptionalComponent) {
        stream.println("        setChild(new " + ASTNode.optName + "(), " + String.valueOf(i) + ");");
        i++;
      }
      else if(c instanceof AggregateComponents) {
        //stream.println("        setChild(null, " + String.valueOf(i) + ");");
        i++;
      }
    }
    
 
    if(rewriteEnabled && isRootNode()) {
      if(ASTNode.stagedRewrites)
    	stream.println("        is$Final(state().rewritePhase);\n");
      else
        stream.println("        is$Final(true);\n");
    }

    stream.println("    }\n");

    if(numNonNTAComponents() != 0) {
      stream.print(buildingConstructor());
      if(ASTNode.beaver)
        stream.print(buildingSymbolConstructor());
    }
    
    if(ASTNode.isBuiltInOpt(name())) {
      stream.println("     public " + ASTNode.optName + "." + ASTNode.optName + "(T opt) {");
      stream.println("         setChild(opt, 0);");
      stream.println("     }\n");
    }

  }

    // Declared in JaddCodeGen.jrag at line 160

  
  public String jjtGenListAPI() {
	  StringWriter stringWriter = new StringWriter();
	  PrintWriter stream = new PrintWriter(stringWriter);
      stream.println("     public #LISTTYPE#<T> #LISTTYPE#.add(T node) {");
      if(debugMode) {
          stream.println("        if(node instanceof #LISTTYPE#)");
          stream.println("            throw new RuntimeException(\"Lists can not have children of type #LISTTYPE#\");");
          stream.println("        if(node instanceof #OPTTYPE#)");
          stream.println("            throw new RuntimeException(\"Lists can not have children of type #OPTTYPE#\");");
        }
        stream.println("          addChild(node);");
        stream.println("          return this;");
        stream.println("     }\n");
       
        stream.println("     public void #LISTTYPE#.insertChild(T node, int i) {");
        stream.println("          list_touched = true;");
        stream.println("          super.insertChild(node, i);");
        stream.println("     }");
        
        stream.println("     public void #LISTTYPE#.addChild(T node) {");
        stream.println("          list_touched = true;");
        stream.println("          super.addChild(node);");
        stream.println("     }");

        stream.println("     public void #LISTTYPE#.removeChild(int i) {");
        stream.println("          list_touched = true;");
        stream.println("          super.removeChild(i);");
        stream.println("     }");

        stream.println("     public int #LISTTYPE#.getNumChild() {");
        stream.println("          if(list_touched) {");
        stream.println("              for(int i = 0; i < getNumChildNoTransform(); i++)");
        stream.println("                  getChild(i);");
        stream.println("              list_touched = false;");
        stream.println("          }");
        stream.println("          return getNumChildNoTransform();");
        stream.println("     }");
        stream.println("     private boolean #LISTTYPE#.list_touched = true;");
        
        stream.flush();
        String result = stringWriter.toString().replaceAll("#LISTTYPE#",ASTNode.listName);
        result = result.replaceAll("#OPTTYPE#",ASTNode.listName);
        return result;
  }

    // Declared in ContainmentGen.jadd at line 6

	 
	
	// Constructor to build trees bottom up
	public String buildingConstructor() {
	    // we only build constructors if there are components
	    if(!getComponents().hasNext())
	      return "";
	    StringBuffer s = new StringBuffer();
	    s.append("    // Declared in " + getFileName() + " line " + getStartLine() + "\n");
	    s.append("    public #ID#.#ID_IMPL#(");
	    int i = 0; // parameter index
	    for(Iterator iter = getComponents(); iter.hasNext(); ) {
	      Components c = (Components)iter.next();
	      if(!c.isNTA()) {
	        if(i != 0) s.append(", ");
	        s.append(computeImplName(c.constrParmType()) + " p" + i);
	        i++;
	      }
	    }
	    s.append(") {\n");
	    i = 0;
	    int j = 0;
	    for(Iterator iter = getComponents(); iter.hasNext(); ) {
	      Components c = (Components)iter.next();
	      if(!c.isNTA()) {
	        if(c instanceof TokenComponent) {
	          TokenComponent t = (TokenComponent)c;
	          s.append("        set" + t.getTokenId().getID() + "(p" + i + ");\n");
	        }
	        else {
	          s.append("        setChild(p" + String.valueOf(i) + ", " + j + ");\n");
	          j++;
	        }
	        i++;
	      }
	      else {
	        if(c instanceof ListComponents) {
	          s.append("        setChild(new " + ASTNode.listName + "(), " + j + ");\n");
	          j++;
	        }
	        else if(c instanceof OptionalComponent) {
	          s.append("        setChild(new " + ASTNode.optName + "(), " + j + ");\n");
	          j++;
	        }
	        else if(c instanceof AggregateComponents) {
	          s.append("        setChild(null, " + j + ");\n");
	          j++;
	        }
	      }
	    }
	    if(rewriteEnabled && isRootNode()) {
	      if(ASTNode.stagedRewrites)
	        s.append("        is$Final(java.lang.Integer.MAX_VALUE);\n");
	      else
	        s.append("        is$Final(true);\n");
	    }
	    s.append("    }\n\n");
	    //JastEMF beware: TypeDecl is different from printed code
	    return s.toString().replaceAll("#ID#", name()).replaceAll("#ID_IMPL#", implName());
	  }

    // Declared in ContainmentGen.jadd at line 64


	  public String buildingSymbolConstructor() {
	    // we only build constructors if there are components ...
	    if(!getComponents().hasNext())
	      return "";
	    // ... and one of these components is a string token component
	    boolean stringArg = false;
	    for(Iterator iter = getComponents(); !stringArg && iter.hasNext(); ) {
	      Components c = (Components)iter.next();
	      if(!c.isNTA() && c instanceof TokenComponent && c.constrParmType().equals("String") || c.constrParmType().equals("java.lang.String"))
	        stringArg = true;
	    }
	    if(!stringArg) return "";

	    StringBuffer s = new StringBuffer();
	    s.append("    // Declared in " + getFileName() + " line " + getStartLine() + "\n");
	    s.append("    public #ID#.#ID_IMPL#(");
	    int i = 0; // parameter index
	    for(Iterator iter = getComponents(); iter.hasNext(); ) {
	      Components c = (Components)iter.next();
	      if(!c.isNTA()) {
	        if(i != 0) s.append(", ");
	        if(c instanceof TokenComponent && c.constrParmType().equals("String") || c.constrParmType().equals("java.lang.String"))
	          s.append("beaver.Symbol p" + i);
	        else
	          s.append(computeImplName(c.constrParmType()) + " p" + i);
	        i++;
	      }
	    }
	    s.append(") {\n");
	    i = 0;
	    int j = 0;
	    for(Iterator iter = getComponents(); iter.hasNext(); ) {
	      Components c = (Components)iter.next();
	      if(!c.isNTA()) {
	        if(c instanceof TokenComponent) {
	          TokenComponent t = (TokenComponent)c;
	          s.append("        set" + t.getTokenId().getID() + "(p" + i + ");\n");
	        }
	        else {
	          s.append("        setChild(p" + String.valueOf(i) + ", " + j + ");\n");
	          j++;
	        }
	        i++;
	      }
	      else {
	        if(c instanceof ListComponents) {
	          s.append("        setChild(new " + ASTNode.listName + "(), " + j + ");\n");
	          j++;
	        }
	        else if(c instanceof OptionalComponent) {
	          s.append("        setChild(new " + ASTNode.optName +"(), " + j + ");\n");
	          j++;
	        }
	        else if(c instanceof AggregateComponents) {
	          s.append("        setChild(null, " + j + ");\n");
	          j++;
	        }
	      }
	    }
	    if(rewriteEnabled && isRootNode()) {
	      if(ASTNode.stagedRewrites)
	        s.append("        is$Final(java.lang.Integer.MAX_VALUE);\n");
	      else
	        s.append("        is$Final(true);\n");
	    }
	    s.append("    }\n\n");
	    return s.toString().replaceAll("#ID#", name()).replaceAll("#ID_IMPL#", implName());
	  }

    // Declared in ContainmentGen.jadd at line 133

	  
	  public void jjtGenFlushCache(PrintWriter stream) {
		    stream.print("    public void flushCache() {\n");
		    if(!name().equals("ASTNode"))
		      stream.print("        super.flushCache();\n");
		    for(int k = 0; k < getNumSynEq(); k++) {
		      AttrEq equ = getSynEq(k);
		      AttrDecl attr = equ.decl();
		      String u = attr.resetVisit() + attr.resetCache();
		      u = u.replaceAll("#NAME#", attr.attributeSignature());
		      stream.print(u);
		    }
		    for(int k = 0; k < getNumInhDecl(); k++) {
		      AttrDecl attr = getInhDecl(k);
		      String u = attr.resetVisit() + attr.resetCache();
		      u = u.replaceAll("#NAME#", attr.attributeSignature());
		      stream.print(u);
		    }
		    stream.print(flushCollectionCache());
		    stream.print("    }\n");

		    stream.print("    public void flushCollectionCache() {\n");
		    if(!name().equals("ASTNode"))
		      stream.print("        super.flushCollectionCache();\n");
		    stream.print(flushCollectionCache());
		    stream.print("    }\n");
	}

    // Declared in ContainmentGen.jadd at line 160

	  
	  public void jjtGenCloneNode(PrintWriter stream, String parserName, boolean rewriteEnabled) {

		    // covariant return type when using Java 5
		    String name = implName();
		    if(ASTNode.isBuiltInType(name))
		      name = name + "<T>";
		    stream.print("    " + ASTNode.suppressWarnings() + " public " + name + " clone() throws CloneNotSupportedException {\n");
		    stream.print("        " + implName() + " node = (" + implName() + ")super.clone();\n");

		    for(int k = 0; k < getNumSynEq(); k++) {
		      AttrEq equ = getSynEq(k);
		      AttrDecl attr = equ.decl();
		      String u = attr.resetVisit() + attr.resetCache();
		      u = u.replaceAll("#NAME#", "node." + attr.attributeSignature());
		      stream.print(u);
		    }
		    for(int k = 0; k < getNumInhDecl(); k++) {
		      AttrDecl attr = getInhDecl(k);
		      String u = attr.resetVisit() + attr.resetCache();
		      u = u.replaceAll("#NAME#", "node." + attr.attributeSignature());
		      stream.print(u);
		    }

		    if(ASTNode.rewriteEnabled) {
		      stream.print("        node.in$Circle(false);\n");
		      if(ASTNode.stagedRewrites)
		        stream.print("        node.is$Final(0);\n");
		      else
		        stream.print("        node.is$Final(false);\n");
		    }

		
		    stream.print("        return node;\n");
		    stream.print("    }\n");
		    
		    if(!hasAbstract()) {
		      String copyBody;
	
		        copyBody =
		          "      try {\n" +
		          "          #CLASS# node = (#CLASS#)clone();\n" +
		          "          if(children != null) node.children = (ASTNode[])children.clone();\n" +
		          "          return node;\n" +
		          "      } catch (CloneNotSupportedException e) {\n" +
		          "      }\n" +
		          "      System.err.println(\"Error: Could not clone node of type \" + getClass().getName() + \"!\");\n" +
		          "      return null;\n";
	
		      String s = "    #ANNOTATIONS# public #RETURN# copy() {\n" + 
		          copyBody +
		          "    }\n" +
		          "    #ANNOTATIONS# public #RETURN# fullCopy() {\n" + 
		          "        #ID# res = (#ID#)copy();\n" +
		          "        for(int i = 0; i < getNumChildNoTransform(); i++) {\n" + 
		          "          ASTNode node = getChildNoTransform(i);\n" +
		          "          if(node != null) node = node.fullCopy();\n" +
		          "          res.setChild(node, i);\n" +
		          "        }\n" +
		          "        return res;\n    }\n";

		      String returnName = implName();
		      if(ASTNode.isBuiltInType(name()))
		        returnName = returnName + "<T>";
		      s = s.replaceAll("#RETURN#", returnName );
		      s = s.replaceAll("#ANNOTATIONS#", ASTNode.suppressWarnings());
		      s = s.replaceAll("#CLASS#", implName());
		      s = s.replaceAll("#ID#", implName());
		      stream.print(s);
		    }
		  }

    // Declared in ASTNodeBodyGen.jadd at line 4

	
	public void jjtGenASTNode(PrintWriter stream, String parserName, boolean rewriteEnabled) {
	   //stream.println("   public static final boolean ASTNode.generatedWithCircularEnabled = " + ASTNode.circularEnabled + ";");
	   //stream.println("   public static final boolean ASTNode.generatedWithCacheCycle = " + ASTNode.cacheCycle + ";");
	   //stream.println("   public static final boolean ASTNode.generatedWithComponentCheck = " + ASTNode.componentCheck + ";");
	   
	   //jjtGenInternalState(stream);
	   
	   if(rewriteEnabled) {
	    // if(rewriteLimit > 0) {
	    //	 jjtGenRewriteDebugs(stream);
	    // }
	        
	     //jjtGenRewriteFields(stream);
	    // jjtGenGetChildRewrite(stream);
	   }
	   else {
	    // jjtGenGetChild(stream);
	   }
	      
	   //jjtGenGetChildNoTransform(stream);
	   
	   //jjtGenIndexOf(stream);
	      
	   //jjtGenNumChildren(stream);
	      
	   //jjtGenChildAdders(stream);
	      
	   //jjtGenChildRemove(stream);
	   
	   //jjtGenParentAccessors(stream);

	  // if(debugMode) {
	   //  jjtGenDebugAccessors(stream);
	  // }
	   
	   //jjtGenIteratorAccessor(stream);
	   
	   env().genRewriteOrderChecks(stream);
	   //env().genReset(stream);
	}

    // Declared in ASTNodeBodyGen.jadd at line 45

	
	public void jjtGenInternalState(PrintWriter stream){
	      if(ASTNode.noStatic) {
		        stream.println("    protected ASTNode$State ASTNode.state = null;");
		        stream.println("    public final ASTNode$State ASTNode.state() {");
		        stream.println("        if(state == null) {");
		        stream.println("            if(parent == null) {");
		        stream.println("                 state = new ASTNode$State();");
		        if(ASTNode.debugMode) {
		          // check if a new state object is created for a node that is not a root node
		          StringBuffer s = new StringBuffer();
		          s.append("if(");
		          boolean first = true;
		          for(Iterator iter = env().roots().iterator(); iter.hasNext(); ) {
		            ASTDecl root = (ASTDecl)iter.next();
		            if(!first)
		              s.append(" && ");
		            first = false;
		            s.append("!(this instanceof " + root.name() + ")");
		          }
		          s.append(") throw new RuntimeException(\"Trying to evaluate state in a node which is not attached to the main tree\");");
		          stream.println(s.toString());
		        }
		        stream.println("            }");
		        stream.println("            else {");
		        stream.println("                state = parent.state();");
		        stream.println("            }");
		        stream.println("        }");
		        stream.println("        return state;");
		        stream.println("    }");
		      }
		      else {
		        stream.println("   protected static ASTNode$State ASTNode.state = new ASTNode$State();");
		        stream.println("   public final ASTNode$State ASTNode.state() { return state; }");
		      }
	}

    // Declared in ASTNodeBodyGen.jadd at line 81

	
	public void jjtGenRewriteDebugs(PrintWriter stream){      
		stream.println("  public void ASTNode.debugRewrite(String info) {");
        stream.println("    if(!parent.is$Final()) return;");
        stream.println("    java.util.ArrayList key = new java.util.ArrayList(2);");
        stream.println("    key.add(getParent());");
        stream.println("    key.add(new Integer(getParent().getIndexOfChild(this)));");
        stream.println("    java.util.ArrayList list;");
        stream.println("    if(state().debugRewrite.containsKey(key))");
        stream.println("      list = (java.util.ArrayList)state().debugRewrite.get(key);");
        stream.println("    else {");
        stream.println("      list = new java.util.ArrayList();");
        stream.println("      state().debugRewrite.put(key, list);");
        stream.println("    }");
        stream.println("    list.add(info);");
        stream.println("    if(list.size() > " + rewriteLimit + ") {");
        stream.println("      StringBuffer buf = new StringBuffer(\"Iteration count exceeded for rewrite:\");");
        stream.println("      for(java.util.Iterator iter = list.iterator(); iter.hasNext(); ) buf.append(\"\\n\" + iter.next());");
        stream.println("      throw new RuntimeException(buf.toString());");
        stream.println("    }");
        stream.println("  }");
        
        stream.println("  public void ASTNode.debugRewriteRemove() {");
        stream.println("    java.util.ArrayList key = new java.util.ArrayList(2);");
        stream.println("    key.add(getParent());");
        stream.println("    key.add(new Integer(getParent().getIndexOfChild(this)));");
        stream.println("    state().debugRewrite.remove(key);");
        stream.println("  }\n");
	}

    // Declared in ASTNodeBodyGen.jadd at line 110

	
	public void jjtGenRewriteFields(PrintWriter stream){      
        stream.println("  public boolean ASTNode.in$Circle = false;");
        stream.println("  public boolean ASTNode.in$Circle() { return in$Circle; }");
        stream.println("  public void ASTNode.in$Circle(boolean b) { in$Circle = b; }");
        if(ASTNode.stagedRewrites) {
          stream.println("  public int ASTNode.is$Final = 0;");
          stream.println("  public boolean ASTNode.is$Final() { return is$Final >= state().rewritePhase; }");
          stream.println("  public void ASTNode.is$Final(int phase) { is$Final = phase; }");
          stream.println("  public void ASTNode.enterRewritePhase(int phase) { state().rewritePhase = phase; }");
          stream.println("  public boolean ASTNode.inRewritePhase(int phase) { return state().rewritePhase >= phase; }");
        }
        else {
          stream.println("  public boolean ASTNode.is$Final = false;");
          stream.println("  public boolean ASTNode.is$Final() { return is$Final; }");
          stream.println("  public void ASTNode.is$Final(boolean b) { is$Final = b; }");
        }
	}

    // Declared in ASTNodeBodyGen.jadd at line 128

	
	public void jjtGenGetChildRewrite(PrintWriter stream){
        stream.println("  @SuppressWarnings(\"cast\") public T ASTNode.getChild(int i) {");
        stream.println("    return (T)ASTNode.getChild(this, i);");
        stream.println("  }");

        stream.println("  public static ASTNode ASTNode.getChild(ASTNode that, int i) {");
        stream.println("    ASTNode node = that.getChildNoTransform(i);");
        stream.println("    if(node.is$Final()) return node;");
        stream.println("    if(!node.mayHaveRewrite()) {");
        if(ASTNode.stagedRewrites)
          stream.println("      node.is$Final(that.is$Final);");
        else
          stream.println("      node.is$Final(that.is$Final());");
        stream.println("      return node;");
        stream.println("    }");
        stream.println("    if(!node.in$Circle()) {");
        stream.println("      int rewriteState;");
        stream.println("      int num = that.state().boundariesCrossed;");
        stream.println("      do {");
        stream.println("        that.state().push(ASTNode$State.REWRITE_CHANGE);");
        stream.println("        ASTNode oldNode = node;");
        stream.println("        oldNode.in$Circle(true);");
        stream.println("        node = node.rewriteTo();");
        stream.println("        if(node != oldNode)");
        stream.println("          that.setChild(node, i);");
        stream.println("        oldNode.in$Circle(false);");
        stream.println("        rewriteState = that.state().pop();");
        stream.println("      } while(rewriteState == ASTNode$State.REWRITE_CHANGE);");
        stream.println("      if(rewriteState == ASTNode$State.REWRITE_NOCHANGE && that.is$Final()) {");
        if(ASTNode.stagedRewrites)
          stream.println("        node.is$Final(that.state().rewritePhase);");
        else
          stream.println("        node.is$Final(true);");
        stream.println("        that.state().boundariesCrossed = num;");
        if(rewriteLimit > 0)
          stream.println("        node.debugRewriteRemove();");
        stream.println("      }");
        stream.println("    }");
        stream.println("    else if(that.is$Final() != node.is$Final()) that.state().boundariesCrossed++;");
        stream.println("    return node;");
        stream.println("  }");
	}

    // Declared in ASTNodeBodyGen.jadd at line 171

	
	public void jjtGenGetChild(PrintWriter stream){
        stream.println("  @SuppressWarnings(\"cast\") public T ASTNode.getChild(int i) {");
        stream.println("    return (T)getChildNoTransform(i);");
        stream.println("  }");
	}

    // Declared in ASTNodeBodyGen.jadd at line 177

	
	public void jjtGenGetChildNoTransform(PrintWriter stream){
	      stream.println("  @SuppressWarnings(\"cast\") public final T ASTNode.getChildNoTransform(int i) {");
	      stream.println("    return (T)children[i];");
	      stream.println("  }");
	}

    // Declared in ASTNodeBodyGen.jadd at line 183

	
	public void jjtGenIndexOf(PrintWriter stream){
	    stream.println("  protected int ASTNode.childIndex;");
	    stream.println("  public int ASTNode.getIndexOfChild(ASTNode node) {");
	    stream.println("    if(node != null && node.childIndex < getNumChildNoTransform() && node == getChildNoTransform(node.childIndex))");
	    stream.println("      return node.childIndex;");
	    stream.println("    for(int i = 0; i < getNumChildNoTransform(); i++)");
	    stream.println("      if(getChildNoTransform(i) == node) {");
	    stream.println("        node.childIndex = i;");
	    stream.println("        return i;");
	    stream.println("      }");
	    stream.println("    return -1;");
	    stream.println("  }\n");
	}

    // Declared in ASTNodeBodyGen.jadd at line 197

	
	public void jjtGenNumChildren(PrintWriter stream){
        stream.println("  protected int ASTNode.numChildren;");
       
        stream.println("  protected int ASTNode.numChildren() {");
        stream.println("    return numChildren;");
        stream.println("  }");
        
        stream.println("  public int ASTNode.getNumChild() {");
        stream.println("    return numChildren();");
        stream.println("  }");
        
        stream.println("  public final int ASTNode.getNumChildNoTransform() {");
        stream.println("    return numChildren();");
        stream.println("  }");	
	}

    // Declared in ASTNodeBodyGen.jadd at line 213

	
	public void jjtGenChildAdders(PrintWriter stream){
	    //stream.println("  protected ASTNode[] ASTNode.children;");	
	    //stream.println("  public void ASTNode.addChild(T node) {");
	    //stream.println("    setChild(node, getNumChildNoTransform());");
	    //stream.println("  }");
	    
	    /*stream.println("  public void ASTNode.setChild(T node, int i) {");
	    if(debugMode)
	       stream.println("        debugNodeAttachment(node);");
	    stream.println("    if(children == null) {");
	    stream.println("      children = new ASTNode[i + 1];");
	    stream.println("    } else if (i >= children.length) {");
	    stream.println("      ASTNode c[] = new ASTNode[i << 1];");
	    stream.println("      System.arraycopy(children, 0, c, 0, children.length);");
	    stream.println("      children = c;");
	    stream.println("    }");
	    stream.println("    children[i] = node;");
	    stream.println("    if(i >= numChildren) numChildren = i+1;");
	    stream.println("    if(node != null) { node.setParent(this); node.childIndex = i; }");
	    stream.println("  }");

	    stream.println("  public void ASTNode.insertChild(T node, int i) {");	 
	    if(debugMode)
	       stream.println("        debugNodeAttachment(node);");
	    stream.println("    if(children == null) {");
	    stream.println("      children = new ASTNode[i + 1];");
	    stream.println("      children[i] = node;");
	    stream.println("    } else {");
	    stream.println("      ASTNode c[] = new ASTNode[children.length + 1];");
	    stream.println("      System.arraycopy(children, 0, c, 0, i);");
	    stream.println("      c[i] = node;");
	    stream.println("      if(i < children.length)");
	    stream.println("        System.arraycopy(children, i, c, i+1, children.length-i);");
	    stream.println("      children = c;");
	    stream.println("    }");
	    stream.println("    numChildren++;");
	    stream.println("    if(node != null) { node.setParent(this); node.childIndex = i; }");
	    stream.println("  }");*/
	}

    // Declared in ASTNodeBodyGen.jadd at line 253

	
	public void jjtGenChildRemove(PrintWriter stream){
        stream.println("  public void ASTNode.removeChild(int i) {");
        stream.println("    if(children != null) {");
        stream.println("      ASTNode child = (ASTNode)children[i];");
        stream.println("      if(child != null) {");
        stream.println("        child.setParent(null);");
        stream.println("        child.childIndex = -1;");
        stream.println("      }");
        stream.println("      System.arraycopy(children, i+1, children, i, children.length-i-1);");
        stream.println("      numChildren--;");
        stream.println("    }");
        stream.println("  }");	
	}

    // Declared in ASTNodeBodyGen.jadd at line 268

	
	
	public void jjtGenParentAccessors(PrintWriter stream){
		//stream.println("  protected ASTNode ASTNode.parent;");
		
		stream.println("  public ASTNode ASTNode.getParent() {");
        if(rewriteEnabled) {
          stream.println("    if(parent != null && ((ASTNode)parent).is$Final() != is$Final()) {");
          stream.println("      state().boundariesCrossed++;");
          stream.println("    }");
        }
        stream.println("    return (ASTNode)parent;");
        stream.println("  }");
        
        stream.println("  public void ASTNode.setParent(ASTNode node) {");
        stream.println("    parent = node;");
        stream.println("  }");
 	}

    // Declared in ASTNodeBodyGen.jadd at line 285

	
	public void jjtGenDebugAccessors(PrintWriter stream){
        stream.println("  protected boolean ASTNode.debugNodeAttachmentIsRoot() { return false; }");
        stream.println("    private static void ASTNode.debugNodeAttachment(ASTNode node) {");
        stream.println("    if(node == null) throw new RuntimeException(\"Trying to assign null to a tree child node\");");
        stream.println("    while(node != null && !node.debugNodeAttachmentIsRoot()) {");
        if(rewriteEnabled)
          stream.println("            if(node.in$Circle()) return;");
        stream.println("            ASTNode parent = (ASTNode)node.parent;");
        stream.println("            if(parent != null && parent.getIndexOfChild(node) == -1) return;");
        stream.println("            node = parent;");
        stream.println("    }");
        stream.println("    if(node == null) return;");
        stream.println("    throw new RuntimeException(\"Trying to insert the same tree at multiple tree locations\");");
        stream.println("    }");		
	}

    // Declared in ASTNodeBodyGen.jadd at line 301

	
	public void jjtGenIteratorAccessor(PrintWriter stream){
	     stream.println("    public java.util.Iterator<T> ASTNode.iterator() {");
	      stream.println("        return new java.util.Iterator<T>() {");
	      stream.println("            private int counter = 0;");
	      stream.println("            public boolean hasNext() {");
	      stream.println("                return counter < getNumChild();");
	      stream.println("            }");
	      stream.println("            @SuppressWarnings(\"unchecked\") public T next() {");
	      stream.println("                if(hasNext())");
	      stream.println("                    return (T)getChild(counter++);");
	      stream.println("                else");
	      stream.println("                    return null;");
	      stream.println("            }");
	      stream.println("            public void remove() {");
	      stream.println("                throw new UnsupportedOperationException();");
	      stream.println("            }");
	      stream.println("        };");
	      stream.println("    }");
	}

    // Declared in Attributes.jrag at line 919


  public boolean implementsInterface(String name) { // DRAGONS
    for(Iterator iter = implementsList.iterator(); iter.hasNext(); ) {
      jrag.AST.SimpleNode n = (jrag.AST.SimpleNode)iter.next();
      StringBuffer s = new StringBuffer();
      n.unparse(s, "NoClassName");
      String i = s.toString();
      int index = i.indexOf(name);
      if(index == -1)
        continue;
      if(index > 0 && Character.isJavaIdentifierPart(i.charAt(index-1)))
        continue;
      if(index + name.length() < i.length() && Character.isJavaIdentifierPart(i.charAt(index + name.length())))
        continue;
      return true;
    }
    return false;
  }

    // Declared in CollectionAttributes.jrag at line 8


  // code generation
  //eq CollDecl.visitCheckEnabled() = true;

  public String flushCollectionCache() {
    StringBuffer res = new StringBuffer();
    for(int i = 0; i < getNumCollDecl(); i++) {
      CollDecl attr = getCollDecl(i);
      String s = attr.resetVisit() + attr.resetCache();
      if(!attr.separateEvaluation() && !attr.onePhase())
        s += "    #NAME#_contributors = " + (lazyMaps ? "null" : ASTNode.createContributorSet) + ";\n";
      s = s.replaceAll("#NAME#", attr.attributeSignature());
      res.append(s);

    }
    res.append(collectionReset);
    return res.toString();
  }

    // Declared in CollectionAttributes.jrag at line 23


  public String genCollDecls() {
    StringBuffer buf = new StringBuffer();
    for(int i = 0; i < getNumCollDecl(); i++) {
      CollDecl attr = getCollDecl(i);
      String s;
      if(attr.circularCollection())
        s = genCircular(attr, attr.hostFileComment()) + attr.combineMethod();
      else
        s = genEquation(attr, attr.hostFileComment()) + attr.computeMethod();
      s = s.replaceAll("#TYPE#", attr.getType());
      s = s.replaceAll("#TYPEINSIGNATURE#", name());
      s = s.replaceAll("#COLLECTINGSIGNATURE#", attr.collectingSignature());
      if(!aspectJ)
        s = s.replaceAll("#CLASS#\\.", "");
      else
        s = s.replaceAll("#CLASS#", name());
      s = s.replaceAll("#NAME#", attr.attributeSignature());
      s = s.replaceAll("#METHODNAME#", attr.attributeName());
      s = s.replaceAll("#PARMDECL#", attr.parametersDecl());
      s = s.replaceAll("#PARM#", attr.parameters());
      buf.append(s);
    }
    return filterIterators(buf.toString());
  }

    // Declared in CollectionAttributes.jrag at line 70


  // only used by circular collection attributes
  public String genCircular(AttrDecl attr, String comment) {
    boolean needsCacheDeclaration = true;
    boolean separateEvaluation = attr.separateEvaluation();
    String rootType = attr.root().name();
    if(attr.getNumParameter() == 0)
      return attr.visitedDeclarations() + 
         "    protected boolean #CLASS#.#NAME#_computed = false;\n" + 
         "    protected boolean #CLASS#.#NAME#_initialized = false;\n" +
         "    protected #TYPE# #CLASS#.#NAME#_value;\n" + 
         (separateEvaluation ? "    protected #TYPE# #CLASS#.new_#NAME#_value;\n" : "") + 
         comment + suppressWarnings() +
         "    public #TYPE# #CLASS#.#METHODNAME#(#PARMDECL#) {\n" + 
         attr.parameterStructure() + 
         attr.cacheCheck() +
         "        ASTNode node = this;\n" +
         "        while(node.getParent() != null && !(node instanceof " + rootType + "))\n" +
         "            node = node.getParent();\n" +
         attr.collDebugString() +
         "        " + rootType + " root = (" + rootType + ")node;\n" +
         (separateEvaluation ? "" :
         ("        if(root.collecting_contributors_#COLLECTINGSIGNATURE#)\n" +
         "            throw new RuntimeException(\"Circularity during phase 1\");\n")) +
         attr.addComponentCheck() +
         (separateEvaluation ? "" : "        root.collect_contributors_#COLLECTINGSIGNATURE#();\n") + 
         "        if (!#NAME#_initialized) {\n" +
         "            #NAME#_initialized = true;\n" + 
         "            #NAME#_value = " + attr.getBottomValue().unparse() + ";\n" +
         "        }\n" +
         "        if (!state().IN_CIRCLE) {\n" +
         "            state().IN_CIRCLE = true;\n" +
         attr.addAddToComponent() +
         "    " + attr.cacheInitRewrite() + 
         "            state().CIRCLE_INDEX = 1;\n" +
         "            do {\n" +
         "                #NAME#_visited = state().CIRCLE_INDEX;\n" +
         "                state().CHANGE = false;\n" +
         (separateEvaluation ?
         "                new_#NAME#_value = " + attr.getBottomValue().unparse() + ";\n" :
         "                #TYPE# new_#NAME#_value = " + attr.getBottomValue().unparse() + ";\n") +
         (separateEvaluation ? "root.#TYPEINSIGNATURE#_#NAME#_nextIteration(this);\n" :
         "                combine_#NAME#_contributions(new_#NAME#_value);\n") +
         "                if (" + attr.differs("new_#NAME#_value", "#NAME#_value") + ")\n" +
         "                    state().CHANGE = true;\n" +
         "                #NAME#_value = new_#NAME#_value; \n" +
         "                state().CIRCLE_INDEX++;\n" +
         attr.cycleLimitCheck() +
         "            } while (state().CHANGE);\n" +
         "    " + attr.cacheStoreRewrite() + "{\n" +
         "            #NAME#_computed = true;\n" +
         attr.cacheCycleInit() +
         "            }\n" +
         "            else {\n" +
         attr.resetCycleInit() +
         "              #NAME#_computed = false;\n" +
         "              #NAME#_initialized = false;\n" + 
         "            }\n" +                
         "            state().IN_CIRCLE = false; \n" +
         "    " + attr.returnStmt() +
         "        }\n" +
         "        if(#NAME#_visited != state().CIRCLE_INDEX) {\n" +
         "            #NAME#_visited = state().CIRCLE_INDEX;\n" +
         attr.cacheCycleCheck() +
         attr.resetCycleCheck() +
         attr.addAddToComponent() +
         (separateEvaluation ?
         "            new_#NAME#_value = " + attr.getBottomValue().unparse() + ";\n" :
         "            #TYPE# new_#NAME#_value = " + attr.getBottomValue().unparse() + ";\n") +
         (separateEvaluation ? "root.#TYPEINSIGNATURE#_#NAME#_nextIteration(this);\n" :
         "            combine_#NAME#_contributions(new_#NAME#_value);\n") +
         "            if (" + attr.differs("new_#NAME#_value", "#NAME#_value") + ")\n" +
         "                state().CHANGE = true;\n" +
         "            #NAME#_value = new_#NAME#_value; \n" +
         "    " + attr.returnStmt() +
         "        }\n" +
         attr.returnStmt() + 
         "    }\n\n";
    else
      return attr.visitedDeclarations() + 
         "    protected " + typeDefaultSet + " #CLASS#.#NAME#_computed = " + createDefaultSet + ";\n" + 
         "    protected " + typeDefaultSet + " #CLASS#.#NAME#_initialized = " + createDefaultSet + ";\n" +
         "    protected " + typeDefaultMap + " #CLASS#.#NAME#_values = " + createDefaultMap + ";\n" +
         comment + suppressWarnings() + 
         "    public #TYPE# #CLASS#.#METHODNAME#(#PARMDECL#) {\n" + 
         attr.parameterStructure() + 
         attr.initLazyMaps() +
         attr.cacheCheck() + 
         attr.addComponentCheck() +
         "        if (!#NAME#_initialized.contains(_parameters)) {\n" +
         "            #NAME#_initialized.add(_parameters);\n" + 
         "            #NAME#_values.put(_parameters, " + toReferenceType(attr.getBottomValue().unparse(), attr.getType()) + ");\n" + 
         "        }\n" +
         "        if (!state().IN_CIRCLE) {\n" +
         "            state().IN_CIRCLE = true;\n" +
         "    " + attr.cacheInitRewrite() + 
         attr.addAddToComponent() +
         "            state().CIRCLE_INDEX = 1;\n" +
         "            #TYPE# new_#NAME#_value;\n" + 
         "            do {\n" +
         "                #NAME#_visited.put(_parameters, new Integer(state().CIRCLE_INDEX));\n" +
         "                state().CHANGE = false;\n" +
         "                new_#NAME#_value = " + attr.getBottomValue().unparse() + ";\n" +
         "                combine_#NAME#_contributions(new_#NAME#_value);\n" +
         "                if (" + attr.differs("new_#NAME#_value", fromReferenceType("#NAME#_values.get(_parameters)", attr.getType())) + ")\n" +
         "                    state().CHANGE = true;\n" +
         "                #NAME#_values.put(_parameters, " + toReferenceType("new_#NAME#_value", attr.getType()) + ");\n" +
         "                state().CIRCLE_INDEX++;\n" +
         attr.cycleLimitCheck() +
         "            } while (state().CHANGE);\n" +
         "    " + attr.cacheStoreRewrite() + "{\n" +
         "            #NAME#_computed.add(_parameters);\n" +
         attr.cacheCycleInit() +
         "            }\n" +
         "            else {\n" +
         attr.resetCycleInit() +
         "            #NAME#_computed.remove(_parameters);\n" +
         "            #NAME#_initialized.remove(_parameters);\n" + 
         "            }\n" +                
         "            state().IN_CIRCLE = false; \n" +
         "            return new_#NAME#_value;\n" + 
         "        }\n" +
         "        if(!new Interger(state().CIRCLE_INDEX).equals(#NAME#_visited.get(_parameters))) {\n" +
         "            #NAME#_visited.put(_paramters, new Integer(state().CIRCLE_INDEX));\n" +
         attr.cacheCycleCheck() +
         attr.resetCycleCheck() +
         "            #TYPE# new_#NAME#_value = " + attr.getBottomValue().unparse() + ";\n" +
         "            combine_#NAME#_contributions(new_#NAME#_value);\n" +
         "            if (" + attr.differs("new_#NAME#_value", fromReferenceType("#NAME#_values.get(_parameters)", attr.getType())) + ")\n" +
         "                state().CHANGE = true;\n" +
         "            #NAME#_values.put(_parameters, " + toReferenceType("new_#NAME#_value", attr.getType()) + ");\n" +
         "            return new_#NAME#_value;\n" + 
         "        }\n" +
         "        return " + fromReferenceType("#NAME#_values.get(_parameters)" , attr.getType()) + ";\n" + 
         "    }\n\n";
  }

    // Declared in CollectionAttributes.jrag at line 336


  public String genCollContributions() {
    // find all coll eq in this node and group them according to either the coll decl or group name
    StringBuffer result = new StringBuffer();
    result.append(collectContributors());
    result.append(contributeTo());
    // and similar code for separate evaluation
    result.append(nextIteration());
    return filterIterators(result.toString());
  }

    // Declared in CollectionAttributes.jrag at line 361



  private String collectContributors() {
    HashMap map = new LinkedHashMap();
    for(int i = 0; i < getNumCollEq(); i++) {
      CollEq attr = getCollEq(i);
      if(!attr.separateEvaluation()) {
        String signature = attr.collectingSignature();
        if(!map.containsKey(signature))
          map.put(signature, new ArrayList());
        ArrayList list = (ArrayList)map.get(signature);
        list.add(attr);
      }
    }

    StringBuffer result = new StringBuffer();
    for(Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
      String key = (String)iter.next();
      ArrayList list = (ArrayList)map.get(key);
      StringBuffer buf = new StringBuffer();
      buf.append("    public void #CLASS#.collect_contributors_" + key + "() {\n"); // once per group

      if(isRootNode()) {
        buf.append("        if(collect_contributors_" + key + ") return;\n");
      }
      for(Iterator i2 = list.iterator(); i2.hasNext(); ) {
        CollEq attr = (CollEq)i2.next();
        CollDecl decl = (CollDecl)attr.decl();
        String NAME = attr.attributeSignature();
        buf.append("    " + attr.hostFileComment());
        for(int j = 0; j < attr.getNumContribution(); j++) {
          Contribution c = attr.getContribution(j);
          if(decl.onePhase() || decl.separateEvaluation()) { // TODO: add || decl.separateEvaluation()
            if(c.getCondition() != null)
              buf.append("        if(" + c.getCondition().trim() + ") {\n");
            if(attr.getRefSet()) {
                buf.append("            for(java.util.Iterator iter = (" + attr.getReference() + ").iterator(); iter.hasNext(); ) {\n");
                buf.append("                " + attr.getTargetName() + " ref = (" + attr.getTargetName() + ")iter.next();\n");
                buf.append("                if(ref != null) {\n");
                buf.append("                  if(ref." + NAME + "_value == null) ref." + NAME + "_value = " + decl.getBottomValue().unparse() + ";\n");
                buf.append("                  ref." + NAME + "_value." + decl.getCombOp() + "(" + c.getValue().trim() + ");\n");
                buf.append("                }\n");
                buf.append("            }\n");
            }
            else {
                buf.append("            {\n");
                buf.append("                " + attr.getTargetName() + " ref = " + attr.getReference() + ";\n");
                buf.append("                if(ref != null) {\n");
                buf.append("                  if(ref." + NAME + "_value == null) ref." + NAME + "_value = " + decl.getBottomValue().unparse() + ";\n");
                buf.append("                  ref." + NAME + "_value." + decl.getCombOp() + "(" + c.getValue().trim() + ");\n");
                buf.append("                }\n");
                buf.append("            }\n");
            }
            if(c.getCondition() != null)
              buf.append("        }\n");
          }
          else {
            if(c.getCondition() != null && !decl.lazyCondition())
              buf.append("        if(" + c.getCondition().trim() + ") {\n");

            if(attr.getRefSet()) {
              buf.append("        for(java.util.Iterator iter = (" + attr.getReference() + ").iterator(); iter.hasNext(); ) {\n");
              buf.append("            " + attr.getTargetName() + " ref = (" + attr.getTargetName() + ")iter.next();\n");
              buf.append("            if(ref != null)\n");
              buf.append("            ref." + NAME + "_contributors().add(this);\n"); // one name per collection attribute
              buf.append("        }\n");
            }
            else {
              buf.append("        {\n");
              buf.append("            " + attr.getTargetName() + " ref = (" + attr.getTargetName() + ")(" + attr.getReference().trim() + ");\n");
              buf.append("            if(ref != null)\n");
              buf.append("                ref." + NAME + "_contributors().add(this);\n"); // one name per collection attribute
              buf.append("        }\n");
            }

            if(c.getCondition() != null && !decl.lazyCondition())            
              buf.append("        }\n");
          }
        }
      }
      if(isASTNode()) {
        buf.append("        for(int i = 0; i < getNumChild(); i++)\n");
        buf.append("            getChild(i).collect_contributors_" + key + "();\n"); // once per group
      }
      else {
        buf.append("        super.collect_contributors_" + key + "();\n");
        if(isRootNode()) {
          buf.append("        collect_contributors_" + key + " = true;\n");
        }
      }
      buf.append("    }\n");
      String s = buf.toString();
      if(!aspectJ)
        s = s.replaceAll("#CLASS#\\.", "");
      else
        s = s.replaceAll("#CLASS#", name());
      result.append(s);
    }
    return result.toString();
  }

    // Declared in CollectionAttributes.jrag at line 460


  private String contributeTo() {
    StringBuffer result = new StringBuffer();
    HashMap map = new LinkedHashMap();
    for(int i = 0; i < getNumCollEq(); i++) {
      CollEq attr = getCollEq(i);
      if(!attr.separateEvaluation() && !attr.onePhase()) {
        if(!map.containsKey(attr.decl()))
          map.put(attr.decl(), new ArrayList());
        ArrayList list = (ArrayList)map.get(attr.decl());
        list.add(attr);
      }
    }
    for(Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
      CollDecl decl = (CollDecl)iter.next();
      ArrayList list = (ArrayList)map.get(decl);
      StringBuffer buf = new StringBuffer();
      buf.append("    public void #CLASS#.contributeTo_#TYPEINSIGNATURE#_#NAME#(" + decl.getType() + " collection) {\n");
      buf.append("        super.contributeTo_#TYPEINSIGNATURE#_#NAME#(collection);\n");
      for(Iterator i2 = list.iterator(); i2.hasNext(); ) {
        CollEq attr = (CollEq)i2.next();
      for(int j = 0; j < attr.getNumContribution(); j++) {
        Contribution c = attr.getContribution(j);
        if(c.getCondition() != null/* && attr.getNumContribution() > 1*/) {
          buf.append("        if(" + c.getCondition().trim() + ")\n");
          buf.append("            collection." + decl.getCombOp() + "(" + c.getValue().trim() + ");\n");
        }
        else {
          buf.append("        collection." + decl.getCombOp() + "(" + c.getValue().trim() + ");\n");
        }
      }
      }
      buf.append("    }\n\n");
      String s = buf.toString();
      s = s.replaceAll("#TYPEINSIGNATURE#", decl.getTarget());
      s = s.replaceAll("#COLLECTINGSIGNATURE#", decl.collectingSignature());
      if(!aspectJ)
        s = s.replaceAll("#CLASS#\\.", "");
      else
        s = s.replaceAll("#CLASS#", name());
      s = s.replaceAll("#NAME#", decl.attributeSignature());
      result.append(s);
    }
    return result.toString();
  }

    // Declared in CollectionAttributes.jrag at line 505


  private String nextIteration() {
    HashMap map = new LinkedHashMap();
    for(int i = 0; i < getNumCollEq(); i++) {
      CollEq attr = getCollEq(i);
      if(attr.separateEvaluation()) {
        if(!map.containsKey(attr.contributionSignature()))
          map.put(attr.contributionSignature(), new ArrayList());
        ArrayList list = (ArrayList)map.get(attr.contributionSignature());
        list.add(attr);
      }
    }

    StringBuffer result = new StringBuffer();
    for(Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
      String key = (String)iter.next();
      ArrayList list = (ArrayList)map.get(key);
      StringBuffer buf = new StringBuffer();
      buf.append("    protected void #CLASS#.#TYPEINSIGNATURE#_#NAME#_nextIteration(#TYPEINSIGNATURE# n) {\n");
      CollDecl decl = null;
      CollEq attr = null;
      for(Iterator i2 = list.iterator(); i2.hasNext(); ) {
        attr = (CollEq)i2.next();
        decl = (CollDecl)attr.decl();
        String NAME = attr.attributeSignature();
        buf.append("    " + attr.hostFileComment());
        for(int j = 0; j < attr.getNumContribution(); j++) {
          Contribution c = attr.getContribution(j);
          if(c.getCondition() != null) {
            if(attr.lazyCondition()) {
              if(attr.getRefSet()) {
                buf.append("            for(java.util.Iterator iter = (" + attr.getReference() + ").iterator(); iter.hasNext(); ) {\n");
                buf.append("                " + attr.getTargetName() + " ref = (" + attr.getTargetName() + ")iter.next();\n");
                buf.append("                if(ref == n && (" + c.getCondition().trim() + ")) n.new_" + NAME + "_value." + decl.getCombOp() + "(" + c.getValue().trim() + ");\n");
                buf.append("            }\n");
              }
              else {
                buf.append("            if(" + attr.getReference() + " == n && (" + c.getCondition().trim() + ")) n.new_" + NAME + "_value." + decl.getCombOp() + "(" + c.getValue().trim() + ");\n");
              }
            }
            else {
              buf.append("        if(" + c.getCondition().trim() + ") {\n");
              if(attr.getRefSet()) {
                buf.append("            for(java.util.Iterator iter = (" + attr.getReference() + ").iterator(); iter.hasNext(); ) {\n");
                buf.append("                " + attr.getTargetName() + " ref = (" + attr.getTargetName() + ")iter.next();\n");
                buf.append("                if(ref == n) n.new_" + NAME + "_value." + decl.getCombOp() + "(" + c.getValue().trim() + ");\n");
                buf.append("            }\n");
              }
              else {
                buf.append("            if(" + attr.getReference() + " == n) n.new_" + NAME + "_value." + decl.getCombOp() + "(" + c.getValue().trim() + ");\n");
              }
              buf.append("        }\n");
            }
          }
          else {
            if(attr.getRefSet()) {
              buf.append("        for(java.util.Iterator iter = (" + attr.getReference() + ").iterator(); iter.hasNext(); ) {\n");
              buf.append("            " + attr.getTargetName() + " ref = (" + attr.getTargetName() + ")iter.next();\n");
              buf.append("            if(ref == n) n.new_" + NAME + "_value." + decl.getCombOp() + "(" + c.getValue().trim() + ");\n");
              buf.append("        }\n");
            }
            else
              buf.append("        if(" + attr.getReference() + " == n) n.new_" + NAME + "_value." + decl.getCombOp() + "(" + c.getValue().trim() + ");\n");
          }
        }
      }
      if(isASTNode()) {
        buf.append("        for(int i = 0; i < getNumChild(); i++)\n");
        buf.append("            getChild(i).#TYPEINSIGNATURE#_#NAME#_next_Iteration(n);\n"); // once per group
      }
      else
        buf.append("        super.#TYPEINSIGNATURE#_#NAME#_nextIteration(n);\n");
      buf.append("    }\n");
      String s = buf.toString();
      if(decl != null && !decl.isCircular()) { // TODO:replace decl.naive() with !decl.isCircular()
        s = s.replaceAll("n.new_", "n.");
      }
      s = s.replaceAll("#TYPE#", decl.getType());
      s = s.replaceAll("#TYPEINSIGNATURE#", decl.getTarget());
      s = s.replaceAll("#COLLECTINGSIGNATURE#", decl.collectingSignature());
      if(!aspectJ)
        s = s.replaceAll("#CLASS#\\.", "");
      else
        s = s.replaceAll("#CLASS#", name());
      s = s.replaceAll("#NAME#", attr.attributeSignature());
      s = s.replaceAll("#METHODNAME#", attr.attributeName());
      s = s.replaceAll("#PARMDECL#", attr.parametersDecl());
      s = s.replaceAll("#PARM#", attr.parameters());
      result.append(s);
    }
    return result.toString();
  }

    // Declared in CollectionAttributes.jrag at line 640


  public void weaveCollectionAttributes() {
    for(int i = 0; i < getNumCollDecl(); i++) {
      CollDecl attr = getCollDecl(i);
      boolean separateEvaluation = attr.separateEvaluation();
      TypeDecl astDecl = env().lookup("ASTNode");
      TypeDecl rootDecl = attr.root();
      if(astDecl != null && rootDecl != null) {
        boolean process = !astDecl.processedCollectingSignature(attr.collectingSignature());
        String s = "";
        if(!astDecl.hasCollEq(attr) && process) {
          if(separateEvaluation)
            s =
            "    protected void #CLASS#.#TYPEINSIGNATURE#_#NAME#_nextIteration(#TYPEINSIGNATURE# n) {\n" +
            "        for(int i = 0; i < getNumChild(); i++)\n" +
            "            getChild(i).#TYPEINSIGNATURE#_#NAME#_nextIteration(n);\n" +
            "    }\n\n";
          if(process && !separateEvaluation/*&& attr.isCircular() && !separateEvaluation*/) // TODO: replace !attr.naive() with !(!attr.isCircular() && separateEvaluation)
            s = s + 
            "    public void #CLASS#.collect_contributors_#COLLECTINGSIGNATURE#() {\n" +
            "        for(int i = 0; i < getNumChild(); i++)\n" +
            "            getChild(i).collect_contributors_#COLLECTINGSIGNATURE#();\n" +
            "    }\n\n";

          if(!separateEvaluation && (attr.isCircular() || !attr.onePhase())) // TODO: add && !attr.separateEvaluation()
          s = s +
            "    public void #CLASS#.contributeTo_#TYPEINSIGNATURE#_#NAME#(" + attr.getType() + " collection) {\n" +
            "    }\n";
          s = s.replaceAll("#TYPE#", attr.getType());
          s = s.replaceAll("#TYPEINSIGNATURE#", attr.getTarget());
          s = s.replaceAll("#COLLECTINGSIGNATURE#", attr.collectingSignature());
          if(!aspectJ)
            s = s.replaceAll("#CLASS#\\.", "");
          else
            s = s.replaceAll("#CLASS#", astDecl.name());
          s = s.replaceAll("#NAME#", attr.attributeSignature());
          s = s.replaceAll("#METHODNAME#", attr.attributeName());
          s = s.replaceAll("#PARMDECL#", attr.parametersDecl());
          s = s.replaceAll("#PARM#", attr.parameters());

          astDecl.classBodyDecls.add(buildClassBodyObject(s, attr.getFileName(), attr.getStartLine()));
        }
        if(process) {
          s = "";
          String flush = "";
          if(attr.circularCollection() && !separateEvaluation) { // TODO: remove && !attr.naive()
            s = 
              "    private boolean #CLASS#.collect_contributors_#COLLECTINGSIGNATURE# = false;\n" +
              "    public boolean #CLASS#.collecting_contributors_#COLLECTINGSIGNATURE# = false;\n" +
              "    public void #CLASS#.collect_contributors_#COLLECTINGSIGNATURE#() {\n" +
              "        if(collect_contributors_#COLLECTINGSIGNATURE#) return;\n" +
              "        collecting_contributors_#COLLECTINGSIGNATURE# = true;\n" +
              "        super.collect_contributors_#COLLECTINGSIGNATURE#();\n" +
              "        collecting_contributors_#COLLECTINGSIGNATURE# = false;\n" +
              "        collect_contributors_#COLLECTINGSIGNATURE# = true;\n" +
              "    }\n\n";
            flush =
              "    collect_contributors_#COLLECTINGSIGNATURE# = false;\n" +
              "    collecting_contributors_#COLLECTINGSIGNATURE# = false;\n";
          } else if(!attr.circularCollection() && !attr.separateEvaluation()) { // TODO: replace !attr.naive() with !attr.separateEvaluation()
            if(!rootDecl.hasCollEq(attr)) {
              s = 
              "    private boolean #CLASS#.collect_contributors_#COLLECTINGSIGNATURE# = false;\n" +
              "    public void #CLASS#.collect_contributors_#COLLECTINGSIGNATURE#() {\n" +
              "        if(collect_contributors_#COLLECTINGSIGNATURE#) return;\n" +
              "        super.collect_contributors_#COLLECTINGSIGNATURE#();\n" +
              "        collect_contributors_#COLLECTINGSIGNATURE# = true;\n" +
              "    }\n\n";
            }
            else {
              s = 
              "    private boolean #CLASS#.collect_contributors_#COLLECTINGSIGNATURE# = false;\n";
            }
            flush = 
              "    collect_contributors_#COLLECTINGSIGNATURE# = false;\n";
          }
          s = s.replaceAll("#TYPE#", attr.getType());
          s = s.replaceAll("#TYPEINSIGNATURE#", attr.getTarget());
          s = s.replaceAll("#COLLECTINGSIGNATURE#", attr.collectingSignature());
          if(!aspectJ)
            s = s.replaceAll("#CLASS#\\.", "");
          else
            s = s.replaceAll("#CLASS#", rootDecl.name());
          s = s.replaceAll("#NAME#", attr.attributeSignature());
          s = s.replaceAll("#METHODNAME#", attr.attributeName());
          s = s.replaceAll("#PARMDECL#", attr.parametersDecl());
          s = s.replaceAll("#PARM#", attr.parameters());
          rootDecl.classBodyDecls.add(buildClassBodyObject(s, attr.getFileName(), attr.getStartLine()));
          flush = flush.replaceAll("#COLLECTINGSIGNATURE#", attr.collectingSignature());
          rootDecl.collectionReset += flush;
        }
      }
    }
  }

    // Declared in CollectionAttributes.jrag at line 734


  private ClassBodyObject buildClassBodyObject(String data, String fileName, int line) {
    jrag.AST.SimpleNode n = new jrag.AST.ASTBlock(0);
    n.firstToken = n.lastToken = jrag.AST.Token.newToken(0);
    n.firstToken.image = data;
    return new ClassBodyObject(n, fileName, line, "<NoAspect>");
  }

    // Declared in Errorcheck.jrag at line 102


  public void checkInhEquations(StringBuffer result) {
    for(Iterator idIter = inhAttrSet(); idIter.hasNext(); ) {
      String attrId = (String)idIter.next();
      for(Iterator iter = inhAttrEqs(attrId); iter.hasNext(); ) {
        InhEq equ = (InhEq)iter.next();
        InhDecl decl = (InhDecl)equ.decl();
        if(decl != null && !decl.parametersDecl().equals(equ.parametersDecl())) {
          result.append(equ.getFileName() + ":" + equ.getStartLine() + " ");
          result.append("Equation must have the same parameter names as attribute declaration in ");
          result.append(decl.getFileName() + ":" + decl.getStartLine() + "\n");
        }
      }
    }
  }

    // Declared in Errorcheck.jrag at line 152

  public boolean checkSynEqs(String signature, StringBuffer result) {
    if(lookupSynEq(signature) != null) {
      return true;
    }
    boolean success = false;
    if(hasAbstract() && !subclasses().isEmpty()) {
      boolean found = true;
      for(Iterator iter = subclasses().iterator(); iter.hasNext(); ) {
        ASTDecl decl = (ASTDecl)iter.next();
        if(!decl.checkSynEqs(signature, result)) {
          found = false;
	}
      }
      success = found;
    }
    if(!success && !hasAbstract()) {
      result.append(" " + name());
      return false;
    }
    return success;
  }

    // Declared in JragCodeGen.jrag at line 118

  
  public void genAGCode(PrintStream s) {
    s.print(genImplementsList());
    s.print(genMembers());
    s.print(genAbstractSyns());
    s.print(genSynEquations());
    s.print(genInhDeclarations());
    s.print(genInhEquations());
  }

    // Declared in JragCodeGen.jrag at line 142

  public String genImplementsList() {
    if(ASTNode.aspectJ && ASTNode.parentInterface) {
      StringBuffer buf = new StringBuffer();
      Iterator iter = inhAttrSet();
      if(iter.hasNext()) {
        buf.append("    declare parents: " + name() + " implements " +
            "Defines_" + (String)iter.next());
        while(iter.hasNext()) {
          buf.append(", Defines_" + (String)iter.next());
        }
        buf.append(";\n\n");
      }
      return buf.toString();
    }
    else
      return "";
  }

    // Declared in JragCodeGen.jrag at line 199


  public String genMembers() {
    StringBuffer buf = new StringBuffer();
    for(Iterator iter = getClassBodyDecls(); iter.hasNext(); ) {
      ClassBodyObject o = (ClassBodyObject)iter.next();
      jrag.AST.SimpleNode n = o.node;
      buf.append("    // Declared in " + o.fileName + " at line " + o.line + "\n");
      if(!o.comments.equals(""))
        buf.append(o.comments + " ");
      n.unparseClassBodyDeclaration(buf, name(), aspectJ);
      buf.append("\n\n");
    }
    return buf.toString();
  }

    // Declared in JragCodeGen.jrag at line 239

  

  public String genAbstractSyns() {
    StringBuffer buf = new StringBuffer();
    for(int i = 0; i < getNumSynDecl(); i++) {
      AttrDecl attr = getSynDecl(i);
      boolean equ = false;
      for(int j = 0; j < getNumSynEq(); j++) {
        if(getSynEq(j).getName().equals(attr.getName())) {
          equ = true;
        }
      }
      if(!equ) {
        String s;
        s = attr.hostFileComment() + suppressWarnings() + 
        "    public abstract #TYPE# #CLASS#.#METHODNAME#(#PARMDECL#);\n";
        s = s.replaceAll("#TYPE#", attr.implType());
        s = s.replaceAll("#TYPEINSIGNATURE#", attr.getTypeInSignature());
        if(!aspectJ)
          s = s.replaceAll("#CLASS#\\.", "");
        else
          s = s.replaceAll("#CLASS#", implName());
        s = s.replaceAll("#METHODNAME#", attr.attributeName());
        s = s.replaceAll("#PARMDECL#", attr.parametersDecl());
        buf.append(s);
      }
    }
    return buf.toString();
  }

    // Declared in JragCodeGen.jrag at line 744

   // TODO: fixme cycleLimit > 0 ?
   //    "if(state().CIRCLE_INDEX > " + cycleLimit + ") throw new java.lang.RuntimeException(\"Iteration limit " + cycleLimit + " exceeded\");\n" : "";
  
  public String genEquation(AttrDecl attr, String comment) {
    boolean needsCacheDeclaration = true;
    if(attr instanceof SynDecl)
      needsCacheDeclaration = superClass() == null || !superClass().hasLazySynEqFor(attr);
    if (!attr.isCircular()) {
      return
           (needsCacheDeclaration ? (attr.visitedDeclarations() + attr.cacheDeclarations()) : "") + 
           comment +
           suppressWarnings() +
           "    public #TYPE# #CLASS#.#METHODNAME#(#PARMDECL#) {\n" + 
           attr.parameterStructure() +
           attr.traceBeginAttr() +
           attr.initLazyMaps() +
           attr.cacheCheck() + 
           (ASTNode.rewriteEnabled ? "        ASTNode$State state = state();\n" : "") +
           attr.addInterruptedCircleDeclaration() +
           attr.visitedCheck() + 
           attr.setVisited() +
           attr.addCheckInterruptedCircle() +
           attr.cacheInit() + 
           attr.callCompute() + 
           attr.higherOrderAttributeCode() + 
           attr.cacheStore() + 
           attr.clearVisited() +
           attr.addClearInterruptedCircle() +
           attr.traceEndAttr() +
           attr.returnStmt() + 
           "    }\n\n";
    }
    else {   
      if(attr.getNumParameter() == 0) {
        if(!needsCacheDeclaration && attr instanceof SynDecl &&
          superClass() != null && superClass().lookupSynEq(attr.signature()) != null
          && !superClass().lookupSynEq(attr.signature()).decl().isCircular())
          needsCacheDeclaration = true;
        return 
           (needsCacheDeclaration ?
           (
             attr.visitedDeclarations() + 
             "    protected boolean #CLASS#.#NAME#_computed = false;\n" + 
             "    protected boolean #CLASS#.#NAME#_initialized = false;\n" +
             "    protected #TYPE# #CLASS#.#NAME#_value;\n"
           ) : "" ) +
           comment +
           suppressWarnings() +
           "    public #TYPE# #CLASS#.#METHODNAME#(#PARMDECL#) {\n" + 
           attr.traceComputeContext() +
           attr.parameterStructure() + 
           attr.cacheCheck() +
           "        ASTNode$State state = state();\n" +
           attr.addComponentCheck() +
           "        if (!#NAME#_initialized) {\n" +
           "            #NAME#_initialized = true;\n" + 
           "            #NAME#_value = " + attr.getBottomValue().unparse() + ";\n" +
           "        }\n" +
           "        if (!state.IN_CIRCLE) {\n" +
           "            state.IN_CIRCLE = true;\n" +
           attr.tracePrintCycleBeginString() +
           attr.addAddToComponent() +
           "    " + attr.cacheInitRewrite() + 
        //   "            state().CIRCLE_INDEX = 1;\n" + // TODO: fixme
           "            do {\n" +
           "                #NAME#_visited = state.CIRCLE_INDEX;\n" +
           "                state.CHANGE = false;\n" +
           attr.inhDebugString() +
           attr.tracePrintBeginComputingValue() +
           "                #TYPE# new_#NAME#_value = " + attr.circularComputeCall() + ";\n" +
           "                if (" + attr.differs("new_#NAME#_value", "#NAME#_value") + ")\n" +
           "                    state.CHANGE = true;\n" +
           "                #NAME#_value = new_#NAME#_value; \n" +
           "                state.CIRCLE_INDEX++;\n" +
           attr.tracePrintStartingCycle() +
           attr.cycleLimitCheck() +
           "            } while (state.CHANGE);\n" +
           "    " + attr.cacheStoreRewrite() + "{\n" +
           "            #NAME#_computed = true;\n" +
           attr.cacheCycleInit() +
           "            }\n" +
           "            else {\n" +
           attr.resetCycleInit() +
	         "              #NAME#_computed = false;\n" +
           "              #NAME#_initialized = false;\n" + 
           "            }\n" +                
           "            state.IN_CIRCLE = false; \n" +
           attr.tracePrintReturnNewValue("#NAME#_value") +
           attr.tracePrintCycleEndString() +
           "    " + attr.returnStmt() +
           "        }\n" +
           "        if(#NAME#_visited != state.CIRCLE_INDEX) {\n" +
           "            #NAME#_visited = state.CIRCLE_INDEX;\n" +
           attr.cacheCycleCheck() +
           attr.resetCycleCheck() +
           attr.addAddToComponent() +
           attr.inhDebugString() +
           attr.tracePrintBeginComputingValue() +
           "            #TYPE# new_#NAME#_value = " + attr.circularComputeCall() + ";\n" +
           "            if (" + attr.differs("new_#NAME#_value", "#NAME#_value") + ")\n" +
           "                state.CHANGE = true;\n" +
           "            #NAME#_value = new_#NAME#_value; \n" +
           attr.tracePrintReturnNewValue("#NAME#_value") +
           "    " + attr.returnStmt() +
           "        }\n" +
           attr.tracePrintReturnPreviousValue("#NAME#_value") +
           attr.returnStmt() + 
           "    }\n\n";
      }
      else
        return
           (needsCacheDeclaration ?
           ( attr.visitedDeclarations() + 
             "    protected " + typeDefaultMap + " #CLASS#.#NAME#_values" + (ASTNode.lazyMaps ? "" : (" = " + createDefaultMap)) + ";\n"
           ) : "" ) +
           comment +
           suppressWarnings() + 
           "    public #TYPE# #CLASS#.#METHODNAME#(#PARMDECL#) {\n" + 
           attr.traceComputeContext() +
           attr.parameterStructure() + 
           attr.initLazyMaps() +
           "        ASTNode$State.CircularValue _value;\n" +
           attr.cacheCheck() + 
           "        else {\n" +
           "            _value = new ASTNode$State.CircularValue();\n" +
           "            #NAME#_values.put(_parameters, _value);\n" +
           "            _value.value = " + toReferenceType(attr.getBottomValue().unparse(), attr.getType()) + ";\n" + 
           "        }\n" +
           "        ASTNode$State state = state();\n" +
           attr.addComponentCheck() +
           "        if (!state.IN_CIRCLE) {\n" +
           "            state.IN_CIRCLE = true;\n" +
           attr.tracePrintCycleBeginString() +
           "    " + attr.cacheInitRewrite() +
           attr.addAddToComponent() +
  // TODO: fixme         "            state().CIRCLE_INDEX = 1;\n" +
           "            #TYPE# new_#NAME#_value;\n" + 
           "            do {\n" +
           "                _value.visited = new Integer(state.CIRCLE_INDEX);\n" +
           "                state.CHANGE = false;\n" +
           attr.inhDebugString() +
           attr.tracePrintBeginComputingValue() +
           "                new_#NAME#_value = " + attr.circularComputeCall() + ";\n" +
           "                if (" + attr.differs("new_#NAME#_value", fromReferenceType("_value.value", attr.getType())) + ") {\n" +
           "                    state.CHANGE = true;\n" +
           "                    _value.value = " + toReferenceType("new_#NAME#_value", attr.getType()) + ";\n" +
           "                }\n" +
           "                state.CIRCLE_INDEX++;\n" +
           attr.tracePrintStartingCycle() +
           attr.cycleLimitCheck() +
           "            } while (state.CHANGE);\n" +
           "    " + attr.cacheStoreRewrite() + "{\n" +
           "                #NAME#_values.put(_parameters, new_#NAME#_value);\n" +
           attr.cacheCycleInit() +
           "            }\n" +
           "            else {\n" +
           "                #NAME#_values.remove(_parameters);\n" + 
           attr.resetCycleInit() +
           "            }\n" +                
           "            state.IN_CIRCLE = false; \n" +
           attr.tracePrintReturnNewValue("new_#NAME#_value") +
           attr.tracePrintCycleEndString() +
           "            return new_#NAME#_value;\n" + 
           "        }\n" +
           "        if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {\n" +
           "            _value.visited = new Integer(state.CIRCLE_INDEX);\n" +
           attr.inhDebugString() +
           attr.tracePrintBeginComputingValue() +
           "            #TYPE# new_#NAME#_value = " + attr.circularComputeCall() + ";\n" +
           attr.cacheCycleCheck() +
           attr.resetCycleCheck() +
           "            else if (" + attr.differs("new_#NAME#_value", fromReferenceType("_value.value", attr.getType())) + ") {\n" +
           "                state.CHANGE = true;\n" +
           "                _value.value = new_#NAME#_value;\n" +
           "            }\n" +
           attr.tracePrintReturnNewValue("new_#NAME#_value") +
           "            return new_#NAME#_value;\n" + 
           "        }\n" +
           attr.tracePrintReturnPreviousValue(fromReferenceType("#NAME#_values.get(_parameters)" , attr.getType())) +
           "        return " + fromReferenceType("_value.value" , attr.getType()) + ";\n" + 
           "    }\n\n";
    }
  }

    // Declared in JragCodeGen.jrag at line 1050

  
  public String genSynEquations() {
    StringBuffer buf = new StringBuffer();
    for(int i = 0; i < getNumSynEq(); i++) {
       AttrEq equ = getSynEq(i);;
       AttrDecl attr = equ.decl();
       String s = genEquation(attr, equ.hostFileComment()) + equ.computeMethod();
       s = s.replaceAll("#TYPE#", attr.implType());
       s = s.replaceAll("#TYPEINSIGNATURE#", attr.getTypeInSignature());
       if(!aspectJ)
         s = s.replaceAll("#CLASS#\\.", "");
       else
         s = s.replaceAll("#CLASS#", implName());
       s = s.replaceAll("#NAME#", attr.attributeSignature());
       s = s.replaceAll("#METHODNAME#", attr.attributeName());
       s = s.replaceAll("#PARMDECL#", equ.parametersDecl());
       s = s.replaceAll("#PARM#", attr.parameters());
       s = s.replaceAll("#LISTTYPE#", ASTNode.listName);
       buf.append(s);
    }
    return buf.toString();
  }

    // Declared in JragCodeGen.jrag at line 1212

  
  public String genInhDeclarations() {
    StringBuffer buf = new StringBuffer();
    for(int i = 0; i < getNumInhDecl(); i++) {
       AttrDecl attr = getInhDecl(i);
       String s = genEquation(attr, attr.hostFileComment()) + attr.computeMethod();
       s = s.replaceAll("#TYPE#", attr.implType());
       s = s.replaceAll("#TYPEINSIGNATURE#", attr.getTypeInSignature());
       if(!aspectJ)
         s = s.replaceAll("#CLASS#\\.", "");
       else
         s = s.replaceAll("#CLASS#", implName());
       s = s.replaceAll("#NAME#", attr.attributeSignature());
       s = s.replaceAll("#METHODNAME#", attr.attributeName());
       s = s.replaceAll("#PARMDECL#", attr.parametersDecl());
       s = s.replaceAll("#PARM#", attr.parameters());
       s = s.replaceAll("#INTERFACEPARM#", attr.interfaceParameters());
       buf.append(s);
    }
    return buf.toString();
  }

    // Declared in JragCodeGen.jrag at line 1285



  public String genInhEquations() {
    StringBuffer buf = new StringBuffer();
    for(Iterator idIter = inhAttrSet(); idIter.hasNext(); ) {
      String attrId = (String)idIter.next();
      Iterator iter = inhAttrEqs(attrId);
      InhEq equ = (InhEq)iter.next();
      InhDecl decl = (InhDecl)equ.decl();

      String s;
      buf.append("    // Declared in " + equ.getFileName() + " at line " + equ.getStartLine() + "\n");
      s = "    public #TYPE# #CLASS#.Define_#TYPEINSIGNATURE#_#METHODNAME#(#INTERFACEPARMDECL#) {\n";
      s = s.replaceAll("#TYPE#", decl.implType());
      s = s.replaceAll("#TYPEINSIGNATURE#", decl.getTypeInSignature());
      if(!aspectJ)
        s = s.replaceAll("#CLASS#\\.", "");
      else
        s = s.replaceAll("#CLASS#", implName());
      s = s.replaceAll("#NAME#", decl.attributeSignature());
      s = s.replaceAll("#METHODNAME#", decl.attributeName());
      s = s.replaceAll("#PARMDECL#", decl.parametersDecl());
      s = s.replaceAll("#INTERFACEPARMDECL#", decl.interfaceParametersDecl());
      buf.append(s);
      
      do {
        Components c = equ.getComponents();
        if(c instanceof ListComponents) {
          s = "        if(caller == get#NAME#ListNoTransform())";
        }
        else if(c instanceof OptionalComponent) {
          s = "        if(caller == get#NAME#OptNoTransform())";
        }
        else if(c != null) {
          s = "        if(caller == get#NAME#NoTransform())";
        }
        else if(equ.getSonName().equals("getChild")) {
          s = "        if(true)";
        }
        else {
          AttrDecl attrDecl = equ.getSonAttrDecl();
          if(attrDecl.getNumParameter() == 0)
            s = "        if(caller == #NAME#_value)";
          else
            s = "        if(caller == " + attrDecl.signature() + "_list)";
        }
        
        s = s.replaceAll("#NAME#", String.valueOf(equ.sonName()));
        if(c instanceof ListComponents) {
          String childIndex = equ.hasIndex() ? equ.getIndex().getName() : "childIndex";
          if(equ.getRHS() instanceof ASTBlock) { // Block
            s = s + " { \n   int " + childIndex + " = caller.getIndexOfChild(child);\n" +
                    equ.getRHS().unparse() + "\n}\n";
          }
          else { // Expr
            s = s + " {\n" + 
                  "      int " + childIndex + " = caller.getIndexOfChild(child);\n" +
                  "            return " + equ.getRHS().unparse() + ";\n" +
                  "        }\n";
          }
          
        }
        else if(c != null) {
          if(equ.getRHS() instanceof ASTBlock) { // Block
            s = s + equ.getRHS().unparse() + "\n";
          }
          else { // Expr
            s = s + " {\n" + 
              "            return " + equ.getRHS().unparse() + ";\n" +
              "        }\n";
          }
        }
        else if(equ.getSonName().equals("getChild")) {
          String childIndex = equ.hasIndex() ? equ.getIndex().getName() : "childIndex";
          if(equ.getRHS() instanceof ASTBlock) { // Block
            s = s + " { \n   int " + childIndex + " = this.getIndexOfChild(caller);\n" +
                    equ.getRHS().unparse() + "\n}\n";
          }
          else { // Expr
            s = s + " {\n" + 
                  "      int " + childIndex + " = this.getIndexOfChild(caller);\n" +
                  "            return " + equ.getRHS().unparse() + ";\n" +
                  "        }\n";
          }
        }
        else {
          AttrDecl attrDecl = equ.getSonAttrDecl();
          s = s + "{\n";
          if(attrDecl.getNumParameter() != 0) {
          String childIndex = equ.hasIndex() ? equ.getIndex().getName() : "childIndex";
          s = s + "   int " + childIndex + " = caller.getIndexOfChild(child);\n";
          }
          if(equ.getRHS() instanceof ASTBlock) { // Block
            s = s + equ.getRHS().unparse() + "\n}\n";
          }
          else { // Expr
            s = s + "            return " + equ.getRHS().unparse() + ";\n" +
                    "        }\n";
          }
        }
        buf.append(s);

        if(iter.hasNext()) {
          equ = (InhEq)iter.next();
        }
        else {
          equ = null;
        }
      } while(equ != null);
      
      if(superClass() != null && superClass().hasInhEq(decl.name())) {
        s = "        return super.Define_#TYPEINSIGNATURE#_#METHODNAME#(#INTERFACEPARM#);\n" + 
            "    }\n\n";
      }
      else {
        // TODO: INH
        if(!ASTNode.parentInterface)
        s = decl.inhDebugString() + 
            "        return getParent().Define_#TYPEINSIGNATURE#_#METHODNAME#(" + decl.interfaceParametersContinue() + ");\n" +
            "    }\n\n";
        else
        s = "        ASTNode n = getParent();\n" + 
            "        caller = this;\n" + 
            "        child = null;\n" + 
            "        while(!(n instanceof Defines_#TYPEINSIGNATURE#_#NAME#)) {\n" +
            "            child = caller;\n" + 
            "            caller = n;\n" + 
            "            n = n.getParent();\n" + 
            "        }\n" +
            decl.inhDebugString() +
            "        return ((Defines_#TYPEINSIGNATURE#_#NAME#)n).Define_#TYPEINSIGNATURE#_#METHODNAME#(#INTERFACEPARM#);\n" + 
            "    }\n\n";
      }
      s = s.replaceAll("#NAME#", decl.attributeSignature());
      s = s.replaceAll("#METHODNAME#", decl.attributeName());
      s = s.replaceAll("#TYPE#", decl.implType());
      s = s.replaceAll("#TYPEINSIGNATURE#", decl.getTypeInSignature());
      s = s.replaceAll("#PARM#", decl.parameters());
      s = s.replaceAll("#INTERFACEPARM#", decl.interfaceParameters());
      buf.append(s);
    }
    return buf.toString();
  }

    // Declared in JragCodeGen.jrag at line 1486

  
    
  
  public String genRewrites() {
    StringBuffer buf = new StringBuffer();
    boolean unconditional = false;
    buf.append("public ASTNode rewriteTo() {\n");
    if(name().equals(ASTNode.listName)) {
      buf.append("    if(list_touched) {\n");
      buf.append("        for(int i = 0 ; i < getNumChildNoTransform(); i++)\n");
      buf.append("            getChild(i);\n");
      buf.append("        list_touched = false;\n");
      buf.append("        return this;\n");
      buf.append("    }\n");
    }
    for(int i = 0; i < getNumRewrite(); i++) {
      Rewrite r = getRewrite(i);
      if(r.genRewrite(buf, i))
        unconditional = true;
    }
    if(name().equals("ASTNode")) {
      buf.append("    if(state().peek() == ASTNode$State.REWRITE_CHANGE) {\n");
      buf.append("        state().pop();\n");
      buf.append("        state().push(ASTNode$State.REWRITE_NOCHANGE);\n");
      buf.append("    }\n");
      buf.append("    return this;\n");
    }
    else if(!unconditional)
      buf.append("    return super.rewriteTo();\n");
    buf.append("}\n\n");
    for(int i = 0; i < getNumRewrite(); i++) {
      Rewrite r = getRewrite(i);
      r.genRewritesExtra(buf, i);
    }
    return buf.toString();
  }

    // Declared in JragCodeGen.jrag at line 1647


  //  TODO: INH
  public void emitInhEqSignatures(PrintStream p) {
    if(ASTNode.parentInterface)
      return;
    if(name().equals("ASTNode")) {
      for(Iterator iter = env().inhEqMap().entrySet().iterator(); iter.hasNext(); ) {
        java.util.Map.Entry entry = (java.util.Map.Entry)iter.next();
        String attrId = (String)entry.getKey();
        AttrEq attr = (AttrEq)((LinkedList)entry.getValue()).get(0);
        if(!hasInhEq(attr.decl().name())) {
          String s =
            "    public #TYPE# Define_#TYPEINSIGNATURE#_#METHODNAME#(#INTERFACEPARMDECL#) {\n" +
            attr.decl().inhDebugString() +
            "        return getParent().Define_#TYPEINSIGNATURE#_#METHODNAME#(" + attr.interfaceParametersContinue() + ");\n" +
            "    }\n";
          s = s.replaceAll("#TYPE#", attr.implType());
          s = s.replaceAll("#TYPEINSIGNATURE#", attr.getTypeInSignature());
          s = s.replaceAll("#NAME#", attr.attributeSignature());
          s = s.replaceAll("#METHODNAME#", attr.attributeName());
          s = s.replaceAll("#PARMDECL#", attr.parametersDecl());
          s = s.replaceAll("#INTERFACEPARM#", attr.interfaceParameters());
          s = s.replaceAll("#INTERFACEPARMDECL#", attr.interfaceParametersDecl());
          p.print(s);
        }
      }
    }
    //else if(name().equals("Block") || name().equals("Binary") || name().equals("TypeDecl") || name().equals("Dot")) {
    /*
    else if(name().equals("Block")) {
      for(Iterator iter = env().inhEqMap().entrySet().iterator(); iter.hasNext(); ) {
        java.util.Map.Entry entry = (java.util.Map.Entry)iter.next();
        String attrId = (String)entry.getKey();
        AttrEq attr = (AttrEq)((LinkedList)entry.getValue()).get(0);
        if(inhAttrEqs(attrId) == null && lookupInhDecl(attr.signature()) == null && !attr.decl().isCircular()) {
          String s = genInhCache(attr.decl());
          s = s.replaceAll("#TYPE#", attr.type());
          s = s.replaceAll("#TYPEINSIGNATURE#", attr.type().replace('.', '_'));
          s = s.replaceAll("#NAME#", attr.attributeSignature());
          s = s.replaceAll("#METHODNAME#", attr.attributeName());
          s = s.replaceAll("#PARMDECL#", attr.parametersDecl());
          s = s.replaceAll("#INTERFACEPARM#", attr.interfaceParameters());
          s = s.replaceAll("#INTERFACEPARMDECL#", attr.interfaceParametersDecl());
          s = s.replaceAll("#CLASS#\\.", "");
          p.print(s);
        }
      }
    }
    */
  }

    // Declared in JragCodeGen.jrag at line 1695

  public String genInhCache(AttrDecl attr) {
      boolean isLazy = attr.getLazy();
      attr.setLazy(true);
      String s =
           attr.cacheDeclarations() + 
           "    public #TYPE# Define_#TYPEINSIGNATURE#_#METHODNAME#(#INTERFACEPARMDECL#) {\n" + 
           attr.parameterStructure() + 
           attr.initLazyMaps() +
           attr.cacheCheck() + 
           attr.cacheInit() + 
           attr.callCompute() + 
           attr.cacheStore() + 
           attr.returnStmt() + 
           "    }\n\n";
       attr.setLazy(isLazy);
       return s;
    }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 20

    public ASTDecl(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public ASTDecl(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public ASTDecl() {
        this(0);

        setChild(new Opt(), 0);
        setChild(null, 1);
        setChild(new Opt(), 2);
        setChild(new List(), 3);
        setChild(new List(), 4);
        setChild(new List(), 5);
        setChild(new List(), 6);
        setChild(new List(), 7);
        setChild(new List(), 8);
        setChild(new List(), 9);
        setChild(new List(), 10);
        setChild(new List(), 11);

    }

    // Declared in Ast.ast at line 29


    // Declared in Ast.ast line 20
    public ASTDecl(Opt p0, IdDecl p1, Opt p2, List p3, List p4, List p5, List p6, List p7, List p8, List p9, List p10, List p11, String p12, int p13, int p14, String p15) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
        setChild(p3, 3);
        setChild(p4, 4);
        setChild(p5, 5);
        setChild(p6, 6);
        setChild(p7, 7);
        setChild(p8, 8);
        setChild(p9, 9);
        setChild(p10, 10);
        setChild(p11, 11);
        setFileName(p12);
        setStartLine(p13);
        setEndLine(p14);
        setComment(p15);
    }

    // Declared in Ast.ast at line 48


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "ASTDecl"+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getComment() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 55


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 59


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 64


public void checkChild(Node n, int i) {
  if(i == 0) {
    if(!(n instanceof Opt)) throw new Error("Child number 0 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of Opt");
    if(((Opt)n).getNumChild() != 0 && !(((Opt)n).getChildNoTransform(0) instanceof Abstract)) throw new Error("Optional Abstract has the type " + ((Opt)n).getChildNoTransform(0).getClass().getName() + " which is not an instance of Abstract");
  }
  if(i == 1 && !(n instanceof IdDecl))  throw new Error("Child number 1 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of IdDecl");
  if(i == 2) {
    if(!(n instanceof Opt)) throw new Error("Child number 2 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of Opt");
    if(((Opt)n).getNumChild() != 0 && !(((Opt)n).getChildNoTransform(0) instanceof IdUse)) throw new Error("Optional SuperClass has the type " + ((Opt)n).getChildNoTransform(0).getClass().getName() + " which is not an instance of IdUse");
  }
  if(i == 3) {
    if(!(n instanceof List)) throw new Error("Child number 3 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Components)) throw new Error("Child number " + k + " in ComponentsList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Components");
  }
  if(i == 4) {
    if(!(n instanceof List)) throw new Error("Child number 4 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof SynDecl)) throw new Error("Child number " + k + " in SynDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of SynDecl");
  }
  if(i == 5) {
    if(!(n instanceof List)) throw new Error("Child number 5 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof SynEq)) throw new Error("Child number " + k + " in SynEqList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of SynEq");
  }
  if(i == 6) {
    if(!(n instanceof List)) throw new Error("Child number 6 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof InhDecl)) throw new Error("Child number " + k + " in InhDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of InhDecl");
  }
  if(i == 7) {
    if(!(n instanceof List)) throw new Error("Child number 7 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof InhEq)) throw new Error("Child number " + k + " in InhEqList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of InhEq");
  }
  if(i == 8) {
    if(!(n instanceof List)) throw new Error("Child number 8 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof ClassBodyDecl)) throw new Error("Child number " + k + " in ClassBodyDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of ClassBodyDecl");
  }
  if(i == 9) {
    if(!(n instanceof List)) throw new Error("Child number 9 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Rewrite)) throw new Error("Child number " + k + " in RewriteList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Rewrite");
  }
  if(i == 10) {
    if(!(n instanceof List)) throw new Error("Child number 10 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof CollDecl)) throw new Error("Child number " + k + " in CollDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of CollDecl");
  }
  if(i == 11) {
    if(!(n instanceof List)) throw new Error("Child number 11 of ASTDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof CollEq)) throw new Error("Child number " + k + " in CollEqList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of CollEq");
  }
}

    // Declared in Ast.ast at line 121


  public int getNumChild() {
    return 12;
  }

    // Declared in Ast.ast at line 124

  public boolean mayHaveRewrite() { return true; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setAbstractOpt(Opt opt) {
        setChild(opt, 0);
    }

    // Declared in Ast.ast at line 6


    public boolean hasAbstract() {
        return getAbstractOpt().getNumChild() != 0;
    }

    // Declared in Ast.ast at line 10


    public Abstract getAbstract() {
        return (Abstract)getAbstractOpt().getChild(0);
    }

    // Declared in Ast.ast at line 14


    public void setAbstract(Abstract node) {
        getAbstractOpt().setChild(node, 0);
    }

    // Declared in Ast.ast at line 17

    public Opt getAbstractOpt() {
        return (Opt)getChild(0);
    }

    // Declared in Ast.ast at line 21


    public Opt getAbstractOptNoTransform() {
        return (Opt)getChildNoTransform(0);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setIdDecl(IdDecl node) {
        setChild(node, 1);
    }

    // Declared in Ast.ast at line 5

    public IdDecl getIdDecl() {
        return (IdDecl)getChild(1);
    }

    // Declared in Ast.ast at line 9


    public IdDecl getIdDeclNoTransform() {
        return (IdDecl)getChildNoTransform(1);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setSuperClassOpt(Opt opt) {
        setChild(opt, 2);
    }

    // Declared in Ast.ast at line 6


    public boolean hasSuperClass() {
        return getSuperClassOpt().getNumChild() != 0;
    }

    // Declared in Ast.ast at line 10


    public IdUse getSuperClass() {
        return (IdUse)getSuperClassOpt().getChild(0);
    }

    // Declared in Ast.ast at line 14


    public void setSuperClass(IdUse node) {
        getSuperClassOpt().setChild(node, 0);
    }

    // Declared in Ast.ast at line 17

    public Opt getSuperClassOpt() {
        return (Opt)getChild(2);
    }

    // Declared in Ast.ast at line 21


    public Opt getSuperClassOptNoTransform() {
        return (Opt)getChildNoTransform(2);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setComponentsList(List list) {
        setChild(list, 3);
    }

    // Declared in Ast.ast at line 6


    public int getNumComponents() {
        return getComponentsList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public Components getComponents(int i) {
        return (Components)getComponentsList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addComponents(Components node) {
        List list = getComponentsList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setComponents(Components node, int i) {
        List list = getComponentsList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getComponentsList() {
        return (List)getChild(3);
    }

    // Declared in Ast.ast at line 27


    public List getComponentsListNoTransform() {
        return (List)getChildNoTransform(3);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setSynDeclList(List list) {
        setChild(list, 4);
    }

    // Declared in Ast.ast at line 6


    public int getNumSynDecl() {
        return getSynDeclList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public SynDecl getSynDecl(int i) {
        return (SynDecl)getSynDeclList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addSynDecl(SynDecl node) {
        List list = getSynDeclList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setSynDecl(SynDecl node, int i) {
        List list = getSynDeclList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getSynDeclList() {
        return (List)getChild(4);
    }

    // Declared in Ast.ast at line 27


    public List getSynDeclListNoTransform() {
        return (List)getChildNoTransform(4);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setSynEqList(List list) {
        setChild(list, 5);
    }

    // Declared in Ast.ast at line 6


    public int getNumSynEq() {
        return getSynEqList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public SynEq getSynEq(int i) {
        return (SynEq)getSynEqList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addSynEq(SynEq node) {
        List list = getSynEqList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setSynEq(SynEq node, int i) {
        List list = getSynEqList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getSynEqList() {
        return (List)getChild(5);
    }

    // Declared in Ast.ast at line 27


    public List getSynEqListNoTransform() {
        return (List)getChildNoTransform(5);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setInhDeclList(List list) {
        setChild(list, 6);
    }

    // Declared in Ast.ast at line 6


    public int getNumInhDecl() {
        return getInhDeclList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public InhDecl getInhDecl(int i) {
        return (InhDecl)getInhDeclList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addInhDecl(InhDecl node) {
        List list = getInhDeclList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setInhDecl(InhDecl node, int i) {
        List list = getInhDeclList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getInhDeclList() {
        return (List)getChild(6);
    }

    // Declared in Ast.ast at line 27


    public List getInhDeclListNoTransform() {
        return (List)getChildNoTransform(6);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setInhEqList(List list) {
        setChild(list, 7);
    }

    // Declared in Ast.ast at line 6


    public int getNumInhEq() {
        return getInhEqList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public InhEq getInhEq(int i) {
        return (InhEq)getInhEqList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addInhEq(InhEq node) {
        List list = getInhEqList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setInhEq(InhEq node, int i) {
        List list = getInhEqList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getInhEqList() {
        return (List)getChild(7);
    }

    // Declared in Ast.ast at line 27


    public List getInhEqListNoTransform() {
        return (List)getChildNoTransform(7);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setClassBodyDeclList(List list) {
        setChild(list, 8);
    }

    // Declared in Ast.ast at line 6


    public int getNumClassBodyDecl() {
        return getClassBodyDeclList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public ClassBodyDecl getClassBodyDecl(int i) {
        return (ClassBodyDecl)getClassBodyDeclList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addClassBodyDecl(ClassBodyDecl node) {
        List list = getClassBodyDeclList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setClassBodyDecl(ClassBodyDecl node, int i) {
        List list = getClassBodyDeclList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getClassBodyDeclList() {
        return (List)getChild(8);
    }

    // Declared in Ast.ast at line 27


    public List getClassBodyDeclListNoTransform() {
        return (List)getChildNoTransform(8);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setRewriteList(List list) {
        setChild(list, 9);
    }

    // Declared in Ast.ast at line 6


    public int getNumRewrite() {
        return getRewriteList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public Rewrite getRewrite(int i) {
        return (Rewrite)getRewriteList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addRewrite(Rewrite node) {
        List list = getRewriteList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setRewrite(Rewrite node, int i) {
        List list = getRewriteList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getRewriteList() {
        return (List)getChild(9);
    }

    // Declared in Ast.ast at line 27


    public List getRewriteListNoTransform() {
        return (List)getChildNoTransform(9);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setCollDeclList(List list) {
        setChild(list, 10);
    }

    // Declared in Ast.ast at line 6


    public int getNumCollDecl() {
        return getCollDeclList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public CollDecl getCollDecl(int i) {
        return (CollDecl)getCollDeclList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addCollDecl(CollDecl node) {
        List list = getCollDeclList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setCollDecl(CollDecl node, int i) {
        List list = getCollDeclList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getCollDeclList() {
        return (List)getChild(10);
    }

    // Declared in Ast.ast at line 27


    public List getCollDeclListNoTransform() {
        return (List)getChildNoTransform(10);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
    public void setCollEqList(List list) {
        setChild(list, 11);
    }

    // Declared in Ast.ast at line 6


    public int getNumCollEq() {
        return getCollEqList().getNumChild();
    }

    // Declared in Ast.ast at line 10


    public CollEq getCollEq(int i) {
        return (CollEq)getCollEqList().getChild(i);
    }

    // Declared in Ast.ast at line 14


    public void addCollEq(CollEq node) {
        List list = getCollEqList();
        list.setChild(node, list.getNumChild());
    }

    // Declared in Ast.ast at line 19


    public void setCollEq(CollEq node, int i) {
        List list = getCollEqList();
        list.setChild(node, i);
    }

    // Declared in Ast.ast at line 23

    public List getCollEqList() {
        return (List)getChild(11);
    }

    // Declared in Ast.ast at line 27


    public List getCollEqListNoTransform() {
        return (List)getChildNoTransform(11);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 20
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
    // Declared in Ast.ast line 20
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
    // Declared in Ast.ast line 20
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
    // Declared in Ast.ast line 20
    private String tokenString_Comment;

    // Declared in Ast.ast at line 3

    public void setComment(String value) {
        tokenString_Comment = value;
    }

    // Declared in Ast.ast at line 6

    public String getComment() {
        return tokenString_Comment;
    }

    protected java.util.Set testCircular_String_visited = new java.util.HashSet(4);
    // Declared in ClassRelations.jrag at line 13
    public boolean testCircular(String name) {
        Object _parameters = name;
boolean interruptedCircle = false;
        if(testCircular_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: testCircular in class: ");
        testCircular_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean testCircular_String_value = testCircular_compute(name);
        testCircular_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return testCircular_String_value;
    }

    private boolean testCircular_compute(String name)  {
    if(!hasSuperClass())
      return false;
    if(getSuperClassName().equals(name))
      return true;
    ASTDecl superClass = (ASTDecl)env().lookup(getSuperClassName());
    return superClass != null ? superClass.testCircular(name) : false;
  }

    protected boolean isCircular_visited = false;
    // Declared in ClassRelations.jrag at line 21
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

    private boolean isCircular_compute() {  return  testCircular(name());  }

    protected boolean superClass_visited = false;
    protected boolean superClass_computed = false;
    protected ASTDecl superClass_value;
    // Declared in ClassRelations.jrag at line 23
    public ASTDecl superClass() {
        if(superClass_computed)
            return superClass_value;
boolean interruptedCircle = false;
        if(superClass_visited)
            throw new RuntimeException("Circular definition of attr: superClass in class: ");
        superClass_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        superClass_value = superClass_compute();
        if(isFinal && num == boundariesCrossed)
            superClass_computed = true;
        superClass_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return superClass_value;
    }

    private ASTDecl superClass_compute() {  return  hasSuperClass() && !isCircular() ?
    (ASTDecl)env().lookup(getSuperClassName()) : null;  }

    protected boolean getSuperClassName_visited = false;
    protected boolean getSuperClassName_computed = false;
    protected String getSuperClassName_value;
    // Declared in ClassRelations.jrag at line 27
    public String getSuperClassName() {
        if(getSuperClassName_computed)
            return getSuperClassName_value;
boolean interruptedCircle = false;
        if(getSuperClassName_visited)
            throw new RuntimeException("Circular definition of attr: getSuperClassName in class: ");
        getSuperClassName_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        getSuperClassName_value = getSuperClassName_compute();
        if(isFinal && num == boundariesCrossed)
            getSuperClassName_computed = true;
        getSuperClassName_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return getSuperClassName_value;
    }

    private String getSuperClassName_compute() {  return  hasSuperClass() ?
    getSuperClass().name() : null;  }

    protected java.util.Set instanceOf_TypeDecl_visited = new java.util.HashSet(4);
    // Declared in ClassRelations.jrag at line 37
    public boolean instanceOf(TypeDecl c) {
        Object _parameters = c;
boolean interruptedCircle = false;
        if(instanceOf_TypeDecl_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: instanceOf in class: ");
        instanceOf_TypeDecl_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean instanceOf_TypeDecl_value = instanceOf_compute(c);
        instanceOf_TypeDecl_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return instanceOf_TypeDecl_value;
    }

    private boolean instanceOf_compute(TypeDecl c)  {
    if(c == this) {
      return true;
    }
    TypeDecl superClass = superClass();
    return superClass != null ? superClass.instanceOf(c) : false;
  }

    protected boolean subclasses_visited = false;
    protected boolean subclasses_computed = false;
    protected Collection subclasses_value;
    // Declared in ClassRelations.jrag at line 67
    public Collection subclasses() {
        if(subclasses_computed)
            return subclasses_value;
boolean interruptedCircle = false;
        if(subclasses_visited)
            throw new RuntimeException("Circular definition of attr: subclasses in class: ");
        subclasses_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        subclasses_value = subclasses_compute();
        if(isFinal && num == boundariesCrossed)
            subclasses_computed = true;
        subclasses_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return subclasses_value;
    }

    private Collection subclasses_compute() {  return  findSubclasses(this);  }

    protected boolean fathers_visited = false;
    protected boolean fathers_computed = false;
    protected Collection fathers_value;
    // Declared in ClassRelations.jrag at line 119
    public Collection fathers() {
        if(fathers_computed)
            return fathers_value;
boolean interruptedCircle = false;
        if(fathers_visited)
            throw new RuntimeException("Circular definition of attr: fathers in class: ");
        fathers_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        fathers_value = fathers_compute();
        if(isFinal && num == boundariesCrossed)
            fathers_computed = true;
        fathers_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return fathers_value;
    }

    private Collection fathers_compute() {  return  findFathers(this);  }

    protected boolean tempComponents_visited = false;
    // Declared in ComponentsUtil.jrag at line 5
    public Collection tempComponents() {
        if(tempComponents_computed)
            return tempComponents_value;
boolean interruptedCircle = false;
        if(tempComponents_visited)
            throw new RuntimeException("Circular definition of attr: tempComponents in class: ");
        tempComponents_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        tempComponents_value = tempComponents_compute();
        if(isFinal && num == boundariesCrossed)
            tempComponents_computed = true;
        tempComponents_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return tempComponents_value;
    }

    private Collection tempComponents_compute()  {
    LinkedList list = new LinkedList();
    if(superClass() != null) {
      list.addAll(superClass().tempComponents());
    }
    for(int i = 0; i < getNumComponents(); i++) {
      boolean done = false;
      for(ListIterator iter = list.listIterator(); !done && iter.hasNext(); ) {
        Components c = (Components)iter.next();
        if(c.name().equals(getComponents(i).name()) && c.type().equals(getComponents(i).type())) {
          iter.remove();
          done = true;
        }
      }
      if(getComponents(i).isNTA()) {
        list.add(getComponents(i));
      }
      else {
        int j = 0;
        while(j < list.size() && !((Components)list.get(j)).isNTA())
          j++;
        list.add(j, getComponents(i));
      }
    }
    return list;
  }

    protected boolean getComponents_visited = false;
    // Declared in ComponentsUtil.jrag at line 31
    public Iterator getComponents() {
boolean interruptedCircle = false;
        if(getComponents_visited)
            throw new RuntimeException("Circular definition of attr: getComponents in class: ");
        getComponents_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        Iterator getComponents_value = getComponents_compute();
        getComponents_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return getComponents_value;
    }

    private Iterator getComponents_compute() {  return  tempComponents().iterator();  }

    protected java.util.Set redefinesTokenComponent_TokenComponent_visited = new java.util.HashSet(4);
    // Declared in ComponentsUtil.jrag at line 33
    public boolean redefinesTokenComponent(TokenComponent c) {
        Object _parameters = c;
boolean interruptedCircle = false;
        if(redefinesTokenComponent_TokenComponent_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: redefinesTokenComponent in class: ");
        redefinesTokenComponent_TokenComponent_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean redefinesTokenComponent_TokenComponent_value = redefinesTokenComponent_compute(c);
        redefinesTokenComponent_TokenComponent_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return redefinesTokenComponent_TokenComponent_value;
    }

    private boolean redefinesTokenComponent_compute(TokenComponent c)  {
    if(c.hostClass() != this) // inherited component
      return false;
    if(superClass() == null) // no definition in superclass
      return true;
    for(Iterator iter = superClass().getComponents(); iter.hasNext(); ) {
      Components d = (Components)iter.next();
      if(d.name().equals(c.name()) && d instanceof TokenComponent && c.isNTA() == d.isNTA()) {
        return false;
      }
    }
    return true; // no equal definition in superclass
  }

    protected boolean isRootNode_visited = false;
    // Declared in Errorcheck.jrag at line 46
    public boolean isRootNode() {
boolean interruptedCircle = false;
        if(isRootNode_visited)
            throw new RuntimeException("Circular definition of attr: isRootNode in class: ");
        isRootNode_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean isRootNode_value = isRootNode_compute();
        isRootNode_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return isRootNode_value;
    }

    private boolean isRootNode_compute() {  return  !hasAbstract() && !name().equals("ASTNode") && !name().equals(ASTNode.optName) && !name().equals(ASTNode.listName) && fathers().isEmpty();  }

    protected boolean collectAstErrors_visited = false;
    // Declared in Errorcheck.jrag at line 50
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
    result.append(astError());
    for(int i = 0; i < getNumComponents(); i++) {
      result.append(getComponents(i).astError());
    }
    return result.toString();
  }

    protected boolean astError_visited = false;
    // Declared in Errorcheck.jrag at line 73
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
    
    if(env().lookup(name()) != this) {
      result.append(getFileName() + ":" + getStartLine() + " ");
      result.append("Multiple production rule for non-terminal " +
          name() + "\n");
    }
    
    if(isCircular()) {
      result.append(getFileName() + ":" + getStartLine() + " ");
      result.append(name() + " causes circular inheritance\n");
    }
    else if(hasSuperClass() && superClass() == null) {
      result.append(getFileName() + ":" + getStartLine() + " ");
      result.append(name() + " inherits from undeclared class " +
          getSuperClass().name() + "\n");
    }

      
    return result.toString();
  }

    protected boolean numNonNTAComponents_visited = false;
    // Declared in JaddCodeGen.jrag at line 44
    public int numNonNTAComponents() {
boolean interruptedCircle = false;
        if(numNonNTAComponents_visited)
            throw new RuntimeException("Circular definition of attr: numNonNTAComponents in class: ");
        numNonNTAComponents_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int numNonNTAComponents_value = numNonNTAComponents_compute();
        numNonNTAComponents_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return numNonNTAComponents_value;
    }

    private int numNonNTAComponents_compute()  {
    int num = 0;
    for(Iterator iter = getComponents(); iter.hasNext(); ) {
      Components c = (Components)iter.next();
      if(!c.isNTA()) {
        num++;
      }
    }
    return num;
  }

    protected boolean numRegularChildren_visited = false;
    // Declared in JaddCodeGen.jrag at line 55
    public int numRegularChildren() {
boolean interruptedCircle = false;
        if(numRegularChildren_visited)
            throw new RuntimeException("Circular definition of attr: numRegularChildren in class: ");
        numRegularChildren_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int numRegularChildren_value = numRegularChildren_compute();
        numRegularChildren_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return numRegularChildren_value;
    }

    private int numRegularChildren_compute()  {
    int i = 0;
    for(Iterator iter = getComponents(); iter.hasNext(); ) {
      Components c = (Components)iter.next();
      if(!c.isNTA() && !(c instanceof TokenComponent)) {
        i++;
      }
    }
    return i;
  }

    protected boolean rewriteWithNoPhaseCondition_visited = false;
    // Declared in JaddCodeGen.jrag at line 206
    public boolean rewriteWithNoPhaseCondition() {
boolean interruptedCircle = false;
        if(rewriteWithNoPhaseCondition_visited)
            throw new RuntimeException("Circular definition of attr: rewriteWithNoPhaseCondition in class: ");
        rewriteWithNoPhaseCondition_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean rewriteWithNoPhaseCondition_value = rewriteWithNoPhaseCondition_compute();
        rewriteWithNoPhaseCondition_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return rewriteWithNoPhaseCondition_value;
    }

    private boolean rewriteWithNoPhaseCondition_compute()  {
    for(int i = 0; i < getNumRewrite(); i++) {
      if(getRewrite(i).getCondition() == null)
       return true;
      String condition = getRewrite(i).getCondition().unparse();
      if(condition.indexOf("inRewritePhase") == -1 && condition.indexOf("inExactRewritePhase") == -1)
        return true;
    }
    return superClass() instanceof ASTDecl && ((ASTDecl)superClass()).rewriteWithNoPhaseCondition();
  }

    protected boolean rewritePhaseConditions_visited = false;
    // Declared in JaddCodeGen.jrag at line 217
    public java.util.Set rewritePhaseConditions() {
boolean interruptedCircle = false;
        if(rewritePhaseConditions_visited)
            throw new RuntimeException("Circular definition of attr: rewritePhaseConditions in class: ");
        rewritePhaseConditions_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        java.util.Set rewritePhaseConditions_value = rewritePhaseConditions_compute();
        rewritePhaseConditions_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return rewritePhaseConditions_value;
    }

    private java.util.Set rewritePhaseConditions_compute()  {
    java.util.Set set = new java.util.LinkedHashSet();
    Pattern p = Pattern.compile("inRewritePhase\\(\\w*\\)|inExactRewritePhase\\(\\w*\\)");
    for(int i = 0; i < getNumRewrite(); i++) {
      if(getRewrite(i).getCondition() != null) {
        String condition = getRewrite(i).getCondition().unparse();
        Matcher m = p.matcher(condition);
        while(m.find()) {
          String match = m.group();
          set.add(match);
        }
      }
    }
    if(superClass() instanceof ASTDecl)
      set.addAll(((ASTDecl)superClass()).rewritePhaseConditions());
    return set;
  }

    protected java.util.Set lookupComponents_String_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 47
    public Components lookupComponents(String name) {
        Object _parameters = name;
        if(lookupComponents_String_values.containsKey(_parameters))
            return (Components)lookupComponents_String_values.get(_parameters);
boolean interruptedCircle = false;
        if(lookupComponents_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: lookupComponents in class: ");
        lookupComponents_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        Components lookupComponents_String_value = lookupComponents_compute(name);
        if(isFinal && num == boundariesCrossed)
            lookupComponents_String_values.put(_parameters, lookupComponents_String_value);
        lookupComponents_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return lookupComponents_String_value;
    }

    private Components lookupComponents_compute(String name)  {
    for(Iterator iter = getComponents(); iter.hasNext(); ) {
      Components c = (Components)iter.next();
      if(c.name().equals(name))
        return c;
    }
    return null;
  }

    protected boolean isASTNode_visited = false;
    // Declared in CollectionAttributes.jrag at line 314
    public boolean isASTNode() {
boolean interruptedCircle = false;
        if(isASTNode_visited)
            throw new RuntimeException("Circular definition of attr: isASTNode in class: ");
        isASTNode_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean isASTNode_value = isASTNode_compute();
        isASTNode_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return isASTNode_value;
    }

    private boolean isASTNode_compute() {  return  name().equals("ASTNode");  }

    protected java.util.Set hasCollEq_CollDecl_visited = new java.util.HashSet(4);
    // Declared in CollectionAttributes.jrag at line 316
    public boolean hasCollEq(CollDecl decl) {
        Object _parameters = decl;
boolean interruptedCircle = false;
        if(hasCollEq_CollDecl_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: hasCollEq in class: ");
        hasCollEq_CollDecl_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean hasCollEq_CollDecl_value = hasCollEq_compute(decl);
        hasCollEq_CollDecl_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return hasCollEq_CollDecl_value;
    }

    private boolean hasCollEq_compute(CollDecl decl)  {
    for(int i = 0; i < getNumCollEq(); i++)
      if(getCollEq(i).decl() == decl)
        return true;
    return false;
  }

    protected boolean collectErrors_visited = false;
    // Declared in Errorcheck.jrag at line 28
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
    
    for(int i = 0; i < getNumSynDecl(); i++) {
      AttrDecl decl = getSynDecl(i);
      result.append(decl.error());
    }
    
    for(int i = 0; i < getNumSynEq(); i++) {
      AttrEq equ = getSynEq(i);
      result.append(equ.error());
    }
    
    
    for(int i = 0; i < getNumInhDecl(); i++) {
      AttrDecl decl = getInhDecl(i);
      result.append(decl.error());
    }
    
    
    for(int i = 0; i < getNumInhEq(); i++) {
      AttrEq equ = getInhEq(i);
      result.append(equ.error());
    }
    
    for(int i = 0; i < getNumCollDecl(); i++) {
    	  CollDecl decl  = getCollDecl(i);
    	  result.append(decl.error());
    	}
    	
    	for(int i = 0; i < getNumCollEq(); i++) {
    	  CollEq equ = getCollEq(i);
    	  result.append(equ.error());
    	}
    	

    checkInhEquations(result);
    
    return result.toString();
  }

    protected java.util.Set hasInhEq_String_visited = new java.util.HashSet(4);
    // Declared in JragCodeGen.jrag at line 1242
    public boolean hasInhEq(String attrName) {
        Object _parameters = attrName;
boolean interruptedCircle = false;
        if(hasInhEq_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: hasInhEq in class: ");
        hasInhEq_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean hasInhEq_String_value = hasInhEq_compute(attrName);
        hasInhEq_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return hasInhEq_String_value;
    }

    private boolean hasInhEq_compute(String attrName) {  return 
    super.hasInhEq(attrName) || superClass() != null && superClass().hasInhEq(attrName);  }

    protected java.util.Set lookupSynDeclPrefix_String_visited = new java.util.HashSet(4);
    // Declared in JragCodeGen.jrag at line 1265
    public SynDecl lookupSynDeclPrefix(String signature) {
        Object _parameters = signature;
boolean interruptedCircle = false;
        if(lookupSynDeclPrefix_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: lookupSynDeclPrefix in class: ");
        lookupSynDeclPrefix_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        SynDecl lookupSynDeclPrefix_String_value = lookupSynDeclPrefix_compute(signature);
        lookupSynDeclPrefix_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return lookupSynDeclPrefix_String_value;
    }

    private SynDecl lookupSynDeclPrefix_compute(String signature)  {
    SynDecl decl = super.lookupSynDeclPrefix(signature);
    if(decl != null || superClass() == null)
      return decl;
    return superClass().lookupSynDeclPrefix(signature);
  }

    protected java.util.Set lookupInhDeclPrefix_String_visited = new java.util.HashSet(4);
    // Declared in JragCodeGen.jrag at line 1277
    public InhDecl lookupInhDeclPrefix(String signature) {
        Object _parameters = signature;
boolean interruptedCircle = false;
        if(lookupInhDeclPrefix_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: lookupInhDeclPrefix in class: ");
        lookupInhDeclPrefix_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        InhDecl lookupInhDeclPrefix_String_value = lookupInhDeclPrefix_compute(signature);
        lookupInhDeclPrefix_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return lookupInhDeclPrefix_String_value;
    }

    private InhDecl lookupInhDeclPrefix_compute(String signature)  {
    InhDecl decl = super.lookupInhDeclPrefix(signature);
    if(decl != null || superClass() == null)
      return decl;
    return superClass().lookupInhDeclPrefix(signature);
  }

    protected boolean hasRewrites_visited = false;
    protected boolean hasRewrites_computed = false;
    protected boolean hasRewrites_value;
    // Declared in JragCodeGen.jrag at line 1644
    public boolean hasRewrites() {
        if(hasRewrites_computed)
            return hasRewrites_value;
boolean interruptedCircle = false;
        if(hasRewrites_visited)
            throw new RuntimeException("Circular definition of attr: hasRewrites in class: ");
        hasRewrites_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        hasRewrites_value = hasRewrites_compute();
        if(isFinal && num == boundariesCrossed)
            hasRewrites_computed = true;
        hasRewrites_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return hasRewrites_value;
    }

    private boolean hasRewrites_compute() {  return  getNumRewrite() > 0 || (superClass() != null && superClass().hasRewrites());  }

    protected boolean synEquations_visited = false;
    // Declared in NameBinding.jrag at line 71
    public Set synEquations() {
        if(synEquations_computed)
            return synEquations_value;
boolean interruptedCircle = false;
        if(synEquations_visited)
            throw new RuntimeException("Circular definition of attr: synEquations in class: ");
        synEquations_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        synEquations_value = synEquations_compute();
        if(isFinal && num == boundariesCrossed)
            synEquations_computed = true;
        synEquations_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return synEquations_value;
    }

    private Set synEquations_compute()  {
		Set set = super.synEquations();
		if(superClass() != null)
			set.addAll(superClass().synEquations());
		return set;
	}

    protected boolean synDeclarations_visited = false;
    // Declared in NameBinding.jrag at line 84
    public Set synDeclarations() {
        if(synDeclarations_computed)
            return synDeclarations_value;
boolean interruptedCircle = false;
        if(synDeclarations_visited)
            throw new RuntimeException("Circular definition of attr: synDeclarations in class: ");
        synDeclarations_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        synDeclarations_value = synDeclarations_compute();
        if(isFinal && num == boundariesCrossed)
            synDeclarations_computed = true;
        synDeclarations_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return synDeclarations_value;
    }

    private Set synDeclarations_compute()  {
		Set set = super.synDeclarations();
		if(superClass() != null)
			set.addAll(superClass().synDeclarations());
		return set;
	}

    protected boolean inhEquations_visited = false;
    // Declared in NameBinding.jrag at line 97
    public Set inhEquations() {
        if(inhEquations_computed)
            return inhEquations_value;
boolean interruptedCircle = false;
        if(inhEquations_visited)
            throw new RuntimeException("Circular definition of attr: inhEquations in class: ");
        inhEquations_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        inhEquations_value = inhEquations_compute();
        if(isFinal && num == boundariesCrossed)
            inhEquations_computed = true;
        inhEquations_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return inhEquations_value;
    }

    private Set inhEquations_compute()  {
		Set set = super.inhEquations();
		if(superClass() != null)
			set.addAll(superClass().inhEquations());
		return set;
	}

    protected boolean inhDeclarations_visited = false;
    // Declared in NameBinding.jrag at line 110
    public Set inhDeclarations() {
        if(inhDeclarations_computed)
            return inhDeclarations_value;
boolean interruptedCircle = false;
        if(inhDeclarations_visited)
            throw new RuntimeException("Circular definition of attr: inhDeclarations in class: ");
        inhDeclarations_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        inhDeclarations_value = inhDeclarations_compute();
        if(isFinal && num == boundariesCrossed)
            inhDeclarations_computed = true;
        inhDeclarations_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return inhDeclarations_value;
    }

    private Set inhDeclarations_compute()  {
		Set set = super.inhDeclarations();
		if(superClass() != null)
			set.addAll(superClass().inhDeclarations());
		return set;
	}

    protected java.util.Set lookupSynDecl_String_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 125
    public SynDecl lookupSynDecl(String signature) {
        Object _parameters = signature;
        if(lookupSynDecl_String_values.containsKey(_parameters))
            return (SynDecl)lookupSynDecl_String_values.get(_parameters);
boolean interruptedCircle = false;
        if(lookupSynDecl_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: lookupSynDecl in class: ");
        lookupSynDecl_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        SynDecl lookupSynDecl_String_value = lookupSynDecl_compute(signature);
        if(isFinal && num == boundariesCrossed)
            lookupSynDecl_String_values.put(_parameters, lookupSynDecl_String_value);
        lookupSynDecl_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return lookupSynDecl_String_value;
    }

    private SynDecl lookupSynDecl_compute(String signature)  {
    SynDecl decl = super.lookupSynDecl(signature);
    if(decl != null || superClass() == null)
      return decl;
    return superClass().lookupSynDecl(signature);
  }

    protected java.util.Set lookupSynEq_String_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 138
    public SynEq lookupSynEq(String signature) {
        Object _parameters = signature;
        if(lookupSynEq_String_values.containsKey(_parameters))
            return (SynEq)lookupSynEq_String_values.get(_parameters);
boolean interruptedCircle = false;
        if(lookupSynEq_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: lookupSynEq in class: ");
        lookupSynEq_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        SynEq lookupSynEq_String_value = lookupSynEq_compute(signature);
        if(isFinal && num == boundariesCrossed)
            lookupSynEq_String_values.put(_parameters, lookupSynEq_String_value);
        lookupSynEq_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return lookupSynEq_String_value;
    }

    private SynEq lookupSynEq_compute(String signature)  {
    SynEq equations = super.lookupSynEq(signature);
    if(equations != null || superClass() == null)
      return equations;
    return superClass().lookupSynEq(signature);
  }

    protected java.util.Set lookupInhDecl_String_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 151
    public InhDecl lookupInhDecl(String signature) {
        Object _parameters = signature;
        if(lookupInhDecl_String_values.containsKey(_parameters))
            return (InhDecl)lookupInhDecl_String_values.get(_parameters);
boolean interruptedCircle = false;
        if(lookupInhDecl_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: lookupInhDecl in class: ");
        lookupInhDecl_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        InhDecl lookupInhDecl_String_value = lookupInhDecl_compute(signature);
        if(isFinal && num == boundariesCrossed)
            lookupInhDecl_String_values.put(_parameters, lookupInhDecl_String_value);
        lookupInhDecl_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return lookupInhDecl_String_value;
    }

    private InhDecl lookupInhDecl_compute(String signature)  {
    InhDecl decl = super.lookupInhDecl(signature);
    if(decl != null || superClass() == null)
      return decl;
    return superClass().lookupInhDecl(signature);
  }

    protected java.util.Set lookupInhDeclSubclasses_String_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 160
    public InhDecl lookupInhDeclSubclasses(String signature) {
        Object _parameters = signature;
        if(lookupInhDeclSubclasses_String_values.containsKey(_parameters))
            return (InhDecl)lookupInhDeclSubclasses_String_values.get(_parameters);
boolean interruptedCircle = false;
        if(lookupInhDeclSubclasses_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: lookupInhDeclSubclasses in class: ");
        lookupInhDeclSubclasses_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        InhDecl lookupInhDeclSubclasses_String_value = lookupInhDeclSubclasses_compute(signature);
        if(isFinal && num == boundariesCrossed)
            lookupInhDeclSubclasses_String_values.put(_parameters, lookupInhDeclSubclasses_String_value);
        lookupInhDeclSubclasses_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return lookupInhDeclSubclasses_String_value;
    }

    private InhDecl lookupInhDeclSubclasses_compute(String signature)  {
    InhDecl decl = lookupInhDecl(signature);
    if(decl != null) return decl;
    for(Iterator iter = subclasses().iterator(); iter.hasNext(); ) {
      ASTDecl subclass = (ASTDecl)iter.next();
      decl = subclass.lookupInhDeclSubclasses(signature);
      if(decl != null) return decl;
    }
    return null;
  }

    protected java.util.Set lookupInhEq_String_String_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 180
    public InhEq lookupInhEq(String signature, String childName) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(signature);
        _parameters.add(childName);
        if(lookupInhEq_String_String_values.containsKey(_parameters))
            return (InhEq)lookupInhEq_String_String_values.get(_parameters);
boolean interruptedCircle = false;
        if(lookupInhEq_String_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: lookupInhEq in class: ");
        lookupInhEq_String_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        InhEq lookupInhEq_String_String_value = lookupInhEq_compute(signature, childName);
        if(isFinal && num == boundariesCrossed)
            lookupInhEq_String_String_values.put(_parameters, lookupInhEq_String_String_value);
        lookupInhEq_String_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return lookupInhEq_String_String_value;
    }

    private InhEq lookupInhEq_compute(String signature, String childName)  {
    InhEq equation = super.lookupInhEq(signature, childName);
    if(equation != null || superClass() == null)
      return equation;
    return superClass().lookupInhEq(signature, childName);
  }

    protected java.util.Set hasInhEqFor_ASTDecl_String_Collection_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 191
    public boolean hasInhEqFor(ASTDecl child, String signature, Collection visited) {
        java.util.List _parameters = new java.util.ArrayList(3);
        _parameters.add(child);
        _parameters.add(signature);
        _parameters.add(visited);
boolean interruptedCircle = false;
        if(hasInhEqFor_ASTDecl_String_Collection_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: hasInhEqFor in class: ");
        hasInhEqFor_ASTDecl_String_Collection_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        boolean hasInhEqFor_ASTDecl_String_Collection_value = hasInhEqFor_compute(child, signature, visited);
        hasInhEqFor_ASTDecl_String_Collection_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return hasInhEqFor_ASTDecl_String_Collection_value;
    }

    private boolean hasInhEqFor_compute(ASTDecl child, String signature, Collection visited)  {
    //System.out.println("  Has " + signature() + " InhEqFor " + child.signature() + "." + signature + ", " + visited.size());
    visited.add(this);
    int numChild = 0;
    int numSuccess = 0;
    for(Iterator children = getComponents(); children.hasNext(); ) {
      Components c = (Components)children.next();
      if(child.instanceOf(env().lookup(c.type()))) {
        numChild++;
        //System.out.println("    Candidate " + c.name());
        InhEq inhEq = lookupInhEq(signature, c.name());
        if(inhEq == null) {
          int numFather = 0;
          int numFatherSuccess = 0;
          //System.out.println("Begin process fathers");
          for(Iterator iter = fathers().iterator(); iter.hasNext(); ) {
            numFather++;
            ASTDecl father = (ASTDecl)iter.next();
            /*
               LinkedList list = (LinkedList)visited;
               boolean add = true;
               boolean vis = true;
               for(int f = 0; f < list.size() && vis; ) {
               ASTDecl element = (ASTDecl)list.get(f);
               if(father == element || this == element) {
               vis = false;
               add = false;
               }
               else {
               if(father.instanceOf(element)) {
               add = false;
               vis = false;
               }
               if(element.instanceOf(father))
               list.remove(element);
               else
               f++;
               }
               }
               if(add) {
               list.add(father);
               }

               if(!vis) {
             */
            if(visited.contains(father)) {
              numFatherSuccess++;
            }
            else {
              if(father.hasInhEqFor(this, signature, visited)) {
                numFatherSuccess++;
              }
              else {
                System.out.print(father.name() + "->"/* + "[" + c.name() + "]"*/);
                //for(Iterator i2 = father.fathers().iterator(); i2.hasNext(); )
                //  System.out.println(father.name() + " son of " + ((ASTDecl)i2.next()).name());
              }
            }
          }
          //System.out.println("End process fathers");
          if(numFather > 0 && numFather == numFatherSuccess) {
            numSuccess++;
          }
          if(numFather == 0)
            System.out.println();
          }
          else {
            numSuccess++;
          }
        }
      }
      //if(numChild > 0 && numSuccess == numChild)
      //  System.out.println(signature() + " hasInhEqFor " + child.name() + "." + signature);
    return numChild > 0 && numSuccess == numChild;
  }

    protected java.util.Set hasInhDeclFor_String_visited = new java.util.HashSet(4);
    protected java.util.Set hasInhDeclFor_String_computed = new java.util.HashSet(4);
    protected java.util.Set hasInhDeclFor_String_initialized = new java.util.HashSet(4);
    protected java.util.Map hasInhDeclFor_String_values = new java.util.HashMap(4);
    public boolean hasInhDeclFor(String signature) {
        Object _parameters = signature;
        if(hasInhDeclFor_String_computed.contains(_parameters))
            return ((Boolean)hasInhDeclFor_String_values.get(_parameters)).booleanValue();
     if(hasInhDeclFor_String_visited.contains(_parameters) && !containsEvalEntry(this, "hasInhDeclFor_String", _parameters))
       throw new java.lang.RuntimeException("XXX");
        if (!hasInhDeclFor_String_initialized.contains(_parameters)) {
            hasInhDeclFor_String_initialized.add(_parameters);
            hasInhDeclFor_String_values.put(_parameters, Boolean.valueOf(false));
        }
        if (!IN_CIRCLE) {
            IN_CIRCLE = true;
            hasInhDeclFor_String_visited.add(_parameters);
            int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
     addEvalEntry(this, "hasInhDeclFor_String", _parameters);
            boolean new_hasInhDeclFor_String_value;
            do {
                CHANGE = false;
                new_hasInhDeclFor_String_value = hasInhDeclFor_compute(signature);
                if (new_hasInhDeclFor_String_value!=((Boolean)hasInhDeclFor_String_values.get(_parameters)).booleanValue())
                    CHANGE = true;
                hasInhDeclFor_String_values.put(_parameters, Boolean.valueOf(new_hasInhDeclFor_String_value));
            } while (CHANGE);
            hasInhDeclFor_String_visited.remove(_parameters);
            if(isFinal && num == boundariesCrossed)
{
            hasInhDeclFor_String_computed.add(_parameters);
            LAST_CYCLE = true;
            hasInhDeclFor_compute(signature);
            LAST_CYCLE = false;
            }
            else {
            RESET_CYCLE = true;
            hasInhDeclFor_compute(signature);
            RESET_CYCLE = false;
            hasInhDeclFor_String_computed.remove(_parameters);
            hasInhDeclFor_String_initialized.remove(_parameters);
            }
            IN_CIRCLE = false; 
            return new_hasInhDeclFor_String_value;
        }
        if(!hasInhDeclFor_String_visited.contains(_parameters)) {
            if (LAST_CYCLE) {
                hasInhDeclFor_String_computed.add(_parameters);
                return hasInhDeclFor_compute(signature);
            }
            if (RESET_CYCLE) {
                hasInhDeclFor_String_computed.remove(_parameters);
                hasInhDeclFor_String_initialized.remove(_parameters);
                return ((Boolean)hasInhDeclFor_String_values.get(_parameters)).booleanValue();
            }
            hasInhDeclFor_String_visited.add(_parameters);
            boolean new_hasInhDeclFor_String_value = hasInhDeclFor_compute(signature);
            if (new_hasInhDeclFor_String_value!=((Boolean)hasInhDeclFor_String_values.get(_parameters)).booleanValue())
                CHANGE = true;
            hasInhDeclFor_String_values.put(_parameters, Boolean.valueOf(new_hasInhDeclFor_String_value));
            hasInhDeclFor_String_visited.remove(_parameters);
            return new_hasInhDeclFor_String_value;
        }
        return ((Boolean)hasInhDeclFor_String_values.get(_parameters)).booleanValue();
    }

    private boolean hasInhDeclFor_compute(String signature)  {
    if(lookupInhDecl(signature) != null)
      return true;
    for(Iterator iter = subclassesLeafNodes().iterator(); iter.hasNext(); ) {
      ASTDecl subclass = (ASTDecl)iter.next();
      if(subclass.lookupInhDecl(signature) != null)
        return true;
    }
    for(Iterator iter = allTreeChildren().iterator(); iter.hasNext(); ) {
      ASTDecl decl = (ASTDecl)iter.next();
      if(decl.hasInhDeclFor(signature))
        return true;
    }
    return false;
  }

    protected boolean subclassesLeafNodes_visited = false;
    protected boolean subclassesLeafNodes_computed = false;
    protected HashSet subclassesLeafNodes_value;
    // Declared in NameBinding.jrag at line 286
    public HashSet subclassesLeafNodes() {
        if(subclassesLeafNodes_computed)
            return subclassesLeafNodes_value;
boolean interruptedCircle = false;
        if(subclassesLeafNodes_visited)
            throw new RuntimeException("Circular definition of attr: subclassesLeafNodes in class: ");
        subclassesLeafNodes_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        subclassesLeafNodes_value = subclassesLeafNodes_compute();
        if(isFinal && num == boundariesCrossed)
            subclassesLeafNodes_computed = true;
        subclassesLeafNodes_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return subclassesLeafNodes_value;
    }

    private HashSet subclassesLeafNodes_compute()  {
    HashSet set = new LinkedHashSet();
    if(subclasses().isEmpty())
      set.add(this);
    for(Iterator iter = subclasses().iterator(); iter.hasNext(); ) {
      ASTDecl subclass = (ASTDecl)iter.next();
      set.addAll(subclass.subclassesLeafNodes());
    }
    return set;
  }

    protected boolean allTreeChildren_visited = false;
    protected boolean allTreeChildren_computed = false;
    protected HashSet allTreeChildren_value;
    // Declared in NameBinding.jrag at line 297
    public HashSet allTreeChildren() {
        if(allTreeChildren_computed)
            return allTreeChildren_value;
boolean interruptedCircle = false;
        if(allTreeChildren_visited)
            throw new RuntimeException("Circular definition of attr: allTreeChildren in class: ");
        allTreeChildren_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        allTreeChildren_value = allTreeChildren_compute();
        if(isFinal && num == boundariesCrossed)
            allTreeChildren_computed = true;
        allTreeChildren_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return allTreeChildren_value;
    }

    private HashSet allTreeChildren_compute()  {
    HashSet set = new LinkedHashSet();
    for(Iterator i1 = subclassesLeafNodes().iterator(); i1.hasNext(); ) {
      ASTDecl decl = (ASTDecl)i1.next();
      for(Iterator i2 = decl.getComponents(); i2.hasNext(); ) {
        Components c = (Components)i2.next();
        if(!(c instanceof TokenComponent)) {
          ASTDecl child = (ASTDecl)env().lookup(c.type());
          if(child != null)
            set.add(child);
        }
      }
    }
    return set;
  }

    protected java.util.Set getInhDeclFor_String_Collection_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 353
    public InhDecl getInhDeclFor(String signature, Collection visited) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(signature);
        _parameters.add(visited);
boolean interruptedCircle = false;
        if(getInhDeclFor_String_Collection_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: getInhDeclFor in class: ");
        getInhDeclFor_String_Collection_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        InhDecl getInhDeclFor_String_Collection_value = getInhDeclFor_compute(signature, visited);
        getInhDeclFor_String_Collection_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return getInhDeclFor_String_Collection_value;
    }

    private InhDecl getInhDeclFor_compute(String signature, Collection visited)  {
    visited.add(this);
    InhDecl inhDecl = lookupInhDecl(signature);
    if(inhDecl != null) {
      return inhDecl;
    }
    for(Iterator children = getComponents(); children.hasNext(); ) {
      Components c = (Components)children.next();
      if(!(c instanceof TokenComponent)) {
        TypeDecl decl = (ASTDecl)env().lookup(c.type());
        if(decl != null && !visited.contains(decl)) {
          InhDecl d = decl.getInhDeclFor(signature, visited);
          if(d != null) return d;
        }
      }
    }

  /*
	  for(Iterator iter = synDeclarations().iterator(); iter.hasNext(); ) {
      SynDecl synDecl = (SynDecl)iter.next();
      if(synDecl.declaredNTA()) {
        ASTDecl decl = (ASTDecl)env().lookup(synDecl.type());
        if(decl != null && !visited.contains(decl)) {
          InhDecl d = decl.getInhDeclFor(signature, visited);
          if(d != null) return d;
        }
      }
    }*/
    
    for(Iterator iter = subclasses().iterator(); iter.hasNext(); ) {
      ASTDecl subclass = (ASTDecl)iter.next();
      if(!visited.contains(subclass)) {
        InhDecl d = subclass.getInhDeclFor(signature, visited);
        if(d != null) return d;
      }
    }
    return null;
  }

    // Declared in CollectionAttributes.jrag at line 621
    public TypeDecl Define_TypeDecl_hostClass(ASTNode caller, ASTNode child) {
        if(caller == getCollEqListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return  this;
        }
        if(caller == getCollDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return  this;
        }
        return super.Define_TypeDecl_hostClass(caller, child);
    }

    // Declared in ClassRelations.jrag at line 31
    public Grammar Define_Grammar_env(ASTNode caller, ASTNode child) {
        if(caller == getSuperClassOptNoTransform()) {
            return  env();
        }
        return getParent().Define_Grammar_env(this, caller);
    }

public ASTNode rewriteTo() {
    // Declared in ClassRelations.jrag at line 6
    if(!hasSuperClass() && !name().equals("ASTNode")) {
        duringClassRelations++;
        ASTNode result = rewriteRule0();
        duringClassRelations--;
        return result;
    }

    return super.rewriteTo();
}

    // Declared in ClassRelations.jrag at line 6
    private ASTDecl rewriteRule0() {
      setSuperClass(new IdUse("ASTNode"));
      return this;
    }
}
