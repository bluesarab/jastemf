
package ast.AST;
import ast.AST.*;import java.util.*;import java.io.*;import java.util.regex.*;import jrag.AST.ASTExpression;import jrag.*;import jrag.AST.ASTCompilationUnit;import jrag.AST.ASTBlock;import jastadd.JastAdd;


public abstract class TypeDecl extends ASTNode implements Cloneable {
    public void flushCache() {
        super.flushCache();
        instanceOf_TypeDecl_visited = new java.util.HashSet(4);
        tempComponents_visited = false;
        tempComponents_computed = false;
        tempComponents_value = null;
        isRootNode_visited = false;
        collectAstErrors_visited = false;
        astError_visited = false;
        lookupComponents_String_visited = new java.util.HashSet(4);
        lookupComponents_String_values = new java.util.HashMap(4);
        implName_visited = false;
        name_visited = false;
        components_String_visited = new java.util.HashSet(4);
        components_String_values = new java.util.HashMap(4);
        hasCollEq_CollDecl_visited = new java.util.HashSet(4);
        collectErrors_visited = false;
        hasInhEq_String_visited = new java.util.HashSet(4);
        lookupSynDeclPrefix_String_visited = new java.util.HashSet(4);
        lookupInhDeclPrefix_String_visited = new java.util.HashSet(4);
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
        getInhDeclFor_String_Collection_visited = new java.util.HashSet(4);
        inhEqMap_visited = false;
        inhEqMap_computed = false;
        inhEqMap_value = null;
        findSubclasses_ASTDecl_visited = new java.util.HashSet(4);
        findSubclasses_ASTDecl_values = new java.util.HashMap(4);
        findFathers_ASTDecl_visited = new java.util.HashSet(4);
        env_visited = false;
    }
    public Object clone() throws CloneNotSupportedException {
        TypeDecl node = (TypeDecl)super.clone();
        node.instanceOf_TypeDecl_visited = new java.util.HashSet(4);
        node.tempComponents_visited = false;
        node.tempComponents_computed = false;
        node.tempComponents_value = null;
        node.isRootNode_visited = false;
        node.collectAstErrors_visited = false;
        node.astError_visited = false;
        node.lookupComponents_String_visited = new java.util.HashSet(4);
        node.lookupComponents_String_values = new java.util.HashMap(4);
        node.implName_visited = false;
        node.name_visited = false;
        node.components_String_visited = new java.util.HashSet(4);
        node.components_String_values = new java.util.HashMap(4);
        node.hasCollEq_CollDecl_visited = new java.util.HashSet(4);
        node.collectErrors_visited = false;
        node.hasInhEq_String_visited = new java.util.HashSet(4);
        node.lookupSynDeclPrefix_String_visited = new java.util.HashSet(4);
        node.lookupInhDeclPrefix_String_visited = new java.util.HashSet(4);
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
        node.getInhDeclFor_String_Collection_visited = new java.util.HashSet(4);
        node.inhEqMap_visited = false;
        node.inhEqMap_computed = false;
        node.inhEqMap_value = null;
        node.findSubclasses_ASTDecl_visited = new java.util.HashSet(4);
        node.findSubclasses_ASTDecl_values = new java.util.HashMap(4);
        node.findFathers_ASTDecl_visited = new java.util.HashSet(4);
        node.env_visited = false;
        node.in$Circle(false);
        node.is$Final(false);
    return node;
    }
    // Declared in ComponentsUtil.jrag at line 48

  public Iterator getComponents() {
    return tempComponents().iterator();
  }

    // Declared in Attributes.jrag at line 228


  public String modifiers = "";

    // Declared in Attributes.jrag at line 293

  
  public Iterator getClassBodyDecls() {
    return classBodyDecls.iterator();
  }

    // Declared in Attributes.jrag at line 297


  public Collection classBodyDecls = new LinkedHashSet();

    // Declared in Attributes.jrag at line 328



  public LinkedList refinedSynEqs = new LinkedList();

    // Declared in Attributes.jrag at line 370

  public LinkedList replacedSynEqs = new LinkedList();

    // Declared in Attributes.jrag at line 371

  public void processSynEqReplacements() {
    boolean change = true;
    ArrayList list = new ArrayList();
    while(change && !replacedSynEqs.isEmpty()) {
      change = false;
      for(Iterator iter = refinedSynEqs.iterator(); iter.hasNext(); ) {
        SynEq decl = (SynEq)iter.next();
        SynEq refinedDecl = null;
        for(Iterator outerIter = replacedSynEqs.iterator(); outerIter.hasNext(); ) {
          SynEq refinedCandidate = (SynEq)outerIter.next();
          boolean legacyCondition = ASTNode.refineLegacy ? decl.legacyAspectName().equals(refinedCandidate.replacesAspect) : false;
          if(decl.signature().equals(refinedCandidate.signature()) 
             && (decl.aspectName().equals(refinedCandidate.replacesAspect) || legacyCondition)) {
            change = true;
            if(refinedDecl == null) {
              refinedDecl = refinedCandidate;
            }
            else {
              error("Multiply defined refinement at " + refinedDecl.getFileName() + " line " + refinedDecl.getStartLine() +
              " and " + refinedCandidate.getFileName() + " line " + refinedCandidate.getStartLine());
            }
            outerIter.remove();
          }
        }
        if(refinedDecl != null) {
          iter.remove();
          refinedDecl.replacesAspect = null;
          list.add(refinedDecl);
        }
      }
      refinedSynEqs.addAll(list);
    }
    for(Iterator iter = replacedSynEqs.iterator(); iter.hasNext(); ) {
      SynEq decl = (SynEq)iter.next();
      error("Can not find equation to refine in " + decl.getFileName() + " at line " + decl.getStartLine());
    }
  }

    // Declared in Attributes.jrag at line 415


  // Multiple refinements done in order specified by the selected aspect name
  // error detection:
  //   detect attempts to refine equation using the wrong aspect name
  //   detect multiple attempts to refine an equation using the same aspect name
  public void processRefinedSynEqs() {
    boolean change = true;
    while(change && !refinedSynEqs.isEmpty()) {
      change = false;
      for(int i = 0; i < getNumSynEq(); i++) {
        SynEq equ = getSynEq(i);
        SynEq refinedEqu = null;
        for(Iterator outerIter = refinedSynEqs.iterator(); outerIter.hasNext(); ) {
          SynEq refinedCandidate = (SynEq)outerIter.next();
          boolean legacyCondition = ASTNode.refineLegacy ? equ.legacyAspectName().equals(refinedCandidate.refinesAspect) : false;
          if(equ.signature().equals(refinedCandidate.signature()) 
             && (equ.aspectName().equals(refinedCandidate.refinesAspect) || legacyCondition)) {
            change = true;
            if(refinedEqu == null) {
              refinedEqu = refinedCandidate;
            }
            else {
              error("Multiply defined refinement at " + refinedEqu.getFileName() + " line " + refinedEqu.getStartLine() +
              " and " + refinedCandidate.getFileName() + " line " + refinedCandidate.getStartLine());
            }
            outerIter.remove();
          }
        }
        if(refinedEqu != null)
          refineWith(equ, refinedEqu);
      }
    }
    for(Iterator iter = refinedSynEqs.iterator(); iter.hasNext(); ) {
      SynEq equ = (SynEq)iter.next();
      error("Can not find syn equation to refine in " + equ.getFileName() + " at line " + equ.getStartLine());
    }
  }

    // Declared in Attributes.jrag at line 448


  public void refineWith(SynEq equ, SynEq refinedEqu) {
    StringBuffer s = new StringBuffer();
    String newMethodName = "refined_" + equ.aspectName() + "_" + name() + "_" + equ.signature();
    s.append("private " + equ.decl().getType() + " " + newMethodName + "(" +
      equ.parametersDecl() + ")\n");
    if(equ.getRHS() instanceof ASTBlock)
      s.append(equ.getRHS().unparse());
    else
      s.append("{ return " + equ.getRHS().unparse() + "; }");
    jrag.AST.SimpleNode n = new jrag.AST.ASTBlock(0);
    n.firstToken = n.lastToken = jrag.AST.Token.newToken(0);
    n.firstToken.image = s.toString();
    ClassBodyObject object = new ClassBodyObject(n, equ.getFileName(), equ.getStartLine(), equ.getAspectName());
    // TODO:

    // change references to original equation in refined body
    if(refinedEqu.getRHS() instanceof jrag.AST.ASTBlock)
      n = new jrag.AST.ASTBlock(0);
    else
      n = new jrag.AST.SimpleNode(0);
      
    n.firstToken = n.lastToken = jrag.AST.Token.newToken(0);
    s = new StringBuffer();
    refinedEqu.getRHS().unparseClassBodyDeclaration(s, name(), false);

    String pattern = "refined";
    if(ASTNode.refineLegacy)
      pattern = "(" + pattern + ")|("
      + equ.legacyAspectName() + "\\." + "[a-zA-Z0-9]+" + "\\." + equ.attributeName() + ")";
    java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(s.toString());
    if(matcher.find()) {
      n.firstToken.image = matcher.replaceAll(newMethodName);
      classBodyDecls.add(object);
    }
    else {
      n.firstToken.image = s.toString();
    }
    // change body of original equation to the refined body
    equ.setRHS(n);
    equ.setFileName(refinedEqu.getFileName());
    equ.setStartLine(refinedEqu.getStartLine());
    equ.setEndLine(refinedEqu.getEndLine());
    equ.setAspectName(refinedEqu.getAspectName());
  }

    // Declared in Attributes.jrag at line 493

  
  public LinkedList refinedInhEqs = new LinkedList();

    // Declared in Attributes.jrag at line 519

  public void processRefinedInhEqs() {
    boolean change = true;
    while(change && !refinedInhEqs.isEmpty()) {
      change = false;
      for(int i = 0; i < getNumInhEq(); i++) {
        InhEq equ = getInhEq(i);
        InhEq refinedEqu = null;
        for(Iterator outerIter = refinedInhEqs.iterator(); outerIter.hasNext(); ) {
          InhEq refinedCandidate = (InhEq)outerIter.next();
          boolean legacyCondition = ASTNode.refineLegacy ? equ.legacyAspectName().equals(refinedCandidate.refinesAspect) : false;
          if(equ.signature().equals(refinedCandidate.signature()) &&
             equ.sonName().equals(refinedCandidate.sonName()) &&
             (equ.aspectName().equals(refinedCandidate.refinesAspect) || legacyCondition)) {
            change = true;
            if(refinedEqu == null) {
              refinedEqu = refinedCandidate;
            }
            else {
              error("Multiply defined refinement at " + refinedEqu.getFileName() + " line " + refinedEqu.getStartLine() +
              " and " + refinedCandidate.getFileName() + " line " + refinedCandidate.getStartLine());
            }
            outerIter.remove();
          }
        }
        if(refinedEqu != null)
          refineWith(equ, refinedEqu);
      }
    }
    for(Iterator iter = refinedInhEqs.iterator(); iter.hasNext(); ) {
      InhEq equ = (InhEq)iter.next();
      error("Can not find inh equation to refine in " + equ.getFileName() + " at line " + equ.getStartLine());
    }
  }

    // Declared in Attributes.jrag at line 553


  public void refineWith(InhEq equ, InhEq refinedEqu) {
    // build a ClassBodyObject from the original equation
    StringBuffer s = new StringBuffer();
    String newMethodName = "refined_" + equ.aspectName() + "_" + name() + "_" + equ.sonName()
      + "_" + equ.signature();
      
    String indexName = "";
    String indexDecl = "";
    boolean isList = equ.getComponents() instanceof ListComponents;
    if(isList) {
      indexName = equ.hasIndex() ? equ.getIndex().getName() : "childIndex";
      indexDecl = "int " + indexName;
      if(equ.getNumParameter() != 0) {
        indexName += ", ";
        indexDecl += ", ";
      }
    }
    
    s.append("private " + equ.decl().getType() + " " + newMethodName + "(" +
      indexDecl + equ.parametersDecl() + ")\n");
    if(equ.getRHS() instanceof ASTBlock)
      s.append(equ.getRHS().unparse());
    else
      s.append("{ return " + equ.getRHS().unparse() + "; }");
    jrag.AST.SimpleNode n = new jrag.AST.ASTBlock(0);
    n.firstToken = n.lastToken = jrag.AST.Token.newToken(0);
    n.firstToken.image = s.toString();
    ClassBodyObject object = new ClassBodyObject(n, equ.getFileName(), equ.getStartLine(), equ.getAspectName());
    // change references to original equation in refined body
    if(refinedEqu.getRHS() instanceof jrag.AST.ASTBlock)
      n = new jrag.AST.ASTBlock(0);
    else
      n = new jrag.AST.SimpleNode(0);
    n.firstToken = n.lastToken = jrag.AST.Token.newToken(0);
    s = new StringBuffer();
    refinedEqu.getRHS().unparseClassBodyDeclaration(s, name(), false);

    String pattern = "refined\\(";
    if(ASTNode.refineLegacy)
      pattern = "(" + pattern + ")|("
      + equ.legacyAspectName() + "\\." + "[a-zA-Z0-9]+" + "\\.get"
      + equ.sonName() + "\\([^\\)]*\\)\\." + equ.attributeName() + "\\("
      + ")";

    java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(s.toString());
    if(matcher.find()) {
      n.firstToken.image = matcher.replaceAll(newMethodName + "(" + indexName);
      classBodyDecls.add(object);
    }
    else {
      n.firstToken.image = s.toString();
    }

    // change body of original equation to the refined body
    equ.setRHS(n);
    equ.setFileName(refinedEqu.getFileName());
    equ.setStartLine(refinedEqu.getStartLine());
    equ.setEndLine(refinedEqu.getEndLine());
    equ.setAspectName(refinedEqu.getAspectName());
  }

    // Declared in Attributes.jrag at line 614

  
  public LinkedList refinedClassBodyDecls = new LinkedList();

    // Declared in Attributes.jrag at line 652

  /*
  process all refine - to 
    if there are multiple refines to that target the same class body decl then error
    if there is a single refine to then
      remove that target class body decl
      turn the refine into a class body decl
  process all normal refines
    if there are multiple refine that target the same class body decl then error
    if there is a single refine then
      change the name of the target class body decl
      turn the refine into a class body decl
        replace all delegations of refined class body decl
  */
  public void processRefinedClassBodyDecls() {
    boolean change = true;
    ArrayList list = new ArrayList();
    while(change && !refinedClassBodyDecls.isEmpty()) {
      change = false;
      for(Iterator iter = classBodyDecls.iterator(); iter.hasNext(); ) {
        ClassBodyObject decl = (ClassBodyObject)iter.next();
        ClassBodyObject refinedDecl = null;
        for(Iterator outerIter = refinedClassBodyDecls.iterator(); outerIter.hasNext(); ) {
          ClassBodyObject refinedCandidate = (ClassBodyObject)outerIter.next();
          boolean legacyCondition = ASTNode.refineLegacy ? decl.legacyAspectName().equals(refinedCandidate.refinesAspect) : false;
          if(decl.signature().equals(refinedCandidate.signature()) 
             && (decl.aspectName().equals(refinedCandidate.refinesAspect) || legacyCondition)) {
            change = true;
            if(refinedDecl == null) {
              refinedDecl = refinedCandidate;
            }
            else {
              error("Multiply defined refinement at " + refinedDecl.getFileName() + " line " + refinedDecl.getStartLine() +
              " and " + refinedCandidate.getFileName() + " line " + refinedCandidate.getStartLine());
            }
            outerIter.remove();
          }
        }
        if(refinedDecl != null) {
          if(!refineWith(decl, refinedDecl))
            iter.remove();
          list.add(refinedDecl);
        }
      }
      classBodyDecls.addAll(list);
    }
    for(Iterator iter = refinedClassBodyDecls.iterator(); iter.hasNext(); ) {
      ClassBodyObject decl = (ClassBodyObject)iter.next();
      error("Can not find method to refine in " + decl.getFileName() + " at line " + decl.getStartLine());
    }
  }

    // Declared in Attributes.jrag at line 689

  public LinkedList replacements = new LinkedList();

    // Declared in Attributes.jrag at line 690

  public void processReplacements() {
    boolean change = true;
    ArrayList list = new ArrayList();
    while(change && !replacements.isEmpty()) {
      change = false;
      for(Iterator iter = refinedClassBodyDecls.iterator(); iter.hasNext(); ) {
        ClassBodyObject decl = (ClassBodyObject)iter.next();
        ClassBodyObject refinedDecl = null;
        for(Iterator outerIter = replacements.iterator(); outerIter.hasNext(); ) {
          ClassBodyObject refinedCandidate = (ClassBodyObject)outerIter.next();
          boolean legacyCondition = ASTNode.refineLegacy ? decl.legacyAspectName().equals(refinedCandidate.replaceAspect) : false;
          if(decl.signature().equals(refinedCandidate.signature()) 
             && (decl.aspectName().equals(refinedCandidate.replaceAspect) || legacyCondition)) {
            change = true;
            if(refinedDecl == null) {
              refinedDecl = refinedCandidate;
            }
            else {
              error("Multiply defined refinement at " + refinedDecl.getFileName() + " line " + refinedDecl.getStartLine() +
              " and " + refinedCandidate.getFileName() + " line " + refinedCandidate.getStartLine());
            }
            outerIter.remove();
          }
        }
        if(refinedDecl != null) {
          iter.remove();
          replaceWith(refinedDecl);
          list.add(refinedDecl);
        }
      }
      refinedClassBodyDecls.addAll(list);
    }
    for(Iterator iter = replacements.iterator(); iter.hasNext(); ) {
      ClassBodyObject decl = (ClassBodyObject)iter.next();
      error("Can not find method to refine in " + decl.getFileName() + " at line " + decl.getStartLine());
    }
  }

    // Declared in Attributes.jrag at line 727

  private void replaceWith(ClassBodyObject o) {
    o.replaceAspect = null;

    jrag.AST.SimpleNode node = o.node;
    // the first two children contain the extra signature in the refine to declaration
    node.firstToken = ((jrag.AST.SimpleNode)node.jjtGetChild(1)).lastToken.next.next.next; // skip tokens "to refine"
    node.jjtAddChild(node.jjtGetChild(2), 0);
    node.jjtAddChild(node.jjtGetChild(3), 1);
    node.jjtAddChild(node.jjtGetChild(4), 2);
    // clear remaining children
    for(int i = 3; i < node.jjtGetNumChildren(); i++)
      node.jjtAddChild(null, i);
/*
    jrag.AST.SimpleNode node = o.node;
    jrag.AST.SimpleNode block = (jrag.AST.SimpleNode)node.jjtGetChild(4);
    node.jjtAddChild(block, 2);
    node.lastToken = block.lastToken;
    ((jrag.AST.SimpleNode)node.jjtGetChild(1)).lastToken.next = block.firstToken;
    for(int i = 3; i < node.jjtGetNumChildren(); i++)
      node.jjtAddChild(null, i);
 */
  }

    // Declared in Attributes.jrag at line 749

  public boolean refineWith(ClassBodyObject decl, ClassBodyObject refinedDecl) {
          jrag.AST.SimpleNode node = refinedDecl.node;
          boolean keep = true;

          if(node instanceof jrag.AST.ASTAspectConstructorDeclaration || node instanceof jrag.AST.ASTAspectRefineConstructorDeclaration) {
            // the name of a constructor is the same as the type name
            String methodName = name();

            // add prefix void refined_
            jrag.AST.Token t1 = ((jrag.AST.SimpleNode)decl.node.jjtGetChild(0)).firstToken;
            jrag.AST.Token t2 = ((jrag.AST.SimpleNode)decl.node).firstToken;
            while(t2.next != t1)
              t2 = t2.next;
            t2.image = "void refined_" + decl.aspectName() + "_" + name() + "_" + t2.image;
            
            // find block node
            jrag.AST.SimpleNode parent = node;
            boolean first = true;
            keep = false;
            for(int index = 1; index < parent.jjtGetNumChildren(); index++) {
              jrag.AST.SimpleNode child = (jrag.AST.SimpleNode)parent.jjtGetChild(index);
              if(child instanceof jrag.AST.ASTBlockStatement || child instanceof jrag.AST.ASTExplicitConstructorInvocation) {
                node = child;
                // replace "aspectName.typeName.methodName" in refinedDecl with "refined_aspectName_methodName"
                StringBuffer buf = new StringBuffer();
                node.unparseClassBodyDeclaration(buf, name(), false);
                String s = buf.toString();

                String pattern = "refined";
                if(ASTNode.refineLegacy)
                  pattern = "(" + pattern + ")|("
                    + decl.legacyAspectName() + "\\." + "[a-zA-Z0-9]+" + "\\." + methodName
                    + ")";
                String newContents = "refined_" + decl.aspectName() + "_" + name() + "_" + methodName;
                //s = s.replaceAll(pattern, newContents);
                // TODO: update keep to false if no strings are replaced

                java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(s);
                if(matcher.find()) {
                  s = matcher.replaceAll(newContents);
                  keep = true;
                }

                if(first) {
                  s = " {" + s;
                  first = false;
                }
                if(index == (parent.jjtGetNumChildren() - 1)) {
                  s = s + "\n}\n";
                }

                jrag.AST.Token token = jrag.AST.Token.newToken(0);
                token.image = s;

                ((jrag.AST.SimpleNode)parent.jjtGetChild(index-1)).lastToken.next = token;
                token.next = token;
                node = new jrag.AST.ASTBlock(0);
                parent.lastToken = token;
                node.firstToken = node.lastToken = token;
                parent.jjtAddChild(node, index);
                node.jjtSetParent(parent);
              }
            }

            parent = decl.node;
            first = true;
            for(int index = 1; index < parent.jjtGetNumChildren(); index++) {
              jrag.AST.SimpleNode child = (jrag.AST.SimpleNode)parent.jjtGetChild(index);
              if(child instanceof jrag.AST.ASTExplicitConstructorInvocation) {
                node = child;
                // replace "aspectName.typeName.methodName" in refinedDecl with "refined_aspectName_methodName"
                StringBuffer buf = new StringBuffer();
                node.unparseClassBodyDeclaration(buf, name(), false);
                String s = buf.toString();
                if(child instanceof jrag.AST.ASTExplicitConstructorInvocation) {
                  s = "";
                }
                if(first) {
                  s = " {" + s;
                  first = false;
                }
                /*
                if(index == (parent.jjtGetNumChildren() - 1)) {
                  s = s + "\n}\n";
                }
                */

                jrag.AST.Token token = jrag.AST.Token.newToken(0);
                token.image = s;

                ((jrag.AST.SimpleNode)parent.jjtGetChild(index-1)).lastToken.next = token;
                token.next = node.lastToken.next;
                node = new jrag.AST.ASTExplicitConstructorInvocation(0);
                //parent.lastToken = token;
                node.firstToken = node.lastToken = token;
                parent.jjtAddChild(node, index);
                node.jjtSetParent(parent);
              }
            }
          }
          else if(node instanceof jrag.AST.ASTAspectMethodDeclaration || node instanceof jrag.AST.ASTAspectRefineMethodDeclaration) {
            // retrieve methodName
            // AspectMethodDeclaration -> MethodDeclarator -> <IDENTIFIER>
            String idDecl = ((jrag.AST.SimpleNode)decl.node.jjtGetChild(1)).firstToken.image;
            String methodName = idDecl.trim();

            // add prefix refined_aspectName_
            idDecl = idDecl.replaceAll(methodName, "refined_" + decl.aspectName() + "_" + name() + "_" + methodName);
            ((jrag.AST.SimpleNode)decl.node.jjtGetChild(1)).firstToken.image = idDecl;

            jrag.AST.SimpleNode parent = node;
            int index = 2;
            while(index < node.jjtGetNumChildren() && !(node.jjtGetChild(index) instanceof jrag.AST.ASTBlock))
              index++;
            if(index >= node.jjtGetNumChildren())
              throw new Error("Could not find block node");
            node = (jrag.AST.SimpleNode)node.jjtGetChild(index);

            // replace "aspectName.typeName.methodName" in refinedDecl with "refined_aspectName_methodName"
            StringBuffer buf = new StringBuffer();
            node.unparseClassBodyDeclaration(buf, name(), false);
            String s = buf.toString();
            String pattern = "refined";
            if(ASTNode.refineLegacy)
              pattern = "(" + pattern + ")|("
                + decl.legacyAspectName() + "\\." + "[a-zA-Z0-9]+" + "\\." + methodName
                + ")";
            String newContents = "refined_" + decl.aspectName() + "_" + name() + "_" + methodName;
            //s = s.replaceAll(pattern, newContents);
            // TODO: update keep to false if no strings are replaced

            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(s);
            if(matcher.find()) {
              s = matcher.replaceAll(newContents);
            }
            else {
              keep = false;
            }

            jrag.AST.Token token = jrag.AST.Token.newToken(0);
            token.image = s;

            ((jrag.AST.SimpleNode)parent.jjtGetChild(index-1)).lastToken.next = token;
            token.next = token;
            node = new jrag.AST.ASTBlock(0);
            parent.lastToken = token;
            node.firstToken = node.lastToken = token;
            parent.jjtAddChild(node, index);
            node.jjtSetParent(parent);
          }
          else {
            throw new Error("Unexpected node type " + node.getClass().getName());
          }

          
          /*
          jrag.AST.SimpleNode n = new jrag.AST.ASTBlock(0);
          n.firstToken = n.lastToken = jrag.AST.Token.newToken(0);
          n.firstToken.image = s;
          // store signature explicitly in decl since the node is replaced.
          String signature = refinedDecl.signature();
          refinedDecl.node = n;
          refinedDecl.setSignature(signature);
         */

         return keep;
  }

    // Declared in Attributes.jrag at line 917


  public LinkedList implementsList = new LinkedList();

    // Declared in CollectionAttributes.jrag at line 631

  public void weaveCollectionAttributes() { }

    // Declared in CollectionAttributes.jrag at line 633


  private HashSet processedCollectingSignatures = null;

    // Declared in CollectionAttributes.jrag at line 634

  protected boolean processedCollectingSignature(String signature) {
    if(processedCollectingSignatures == null)
      processedCollectingSignatures = new LinkedHashSet();
    if(processedCollectingSignatures.contains(signature))
      return true;
    processedCollectingSignatures.add(signature);
    return false;
  }

    // Declared in CollectionAttributes.jrag at line 642

  protected String collectionReset = "";

    // Declared in Errorcheck.jrag at line 146


  public boolean checkSynEqs(String signature, StringBuffer result) {
    if(lookupSynEq(signature) != null) {
      return true;
    }
    return false;
  }

    // Declared in JragCodeGen.jrag at line 123


  public void genAGCode(PrintStream s) {
  }

    // Declared in JragCodeGen.jrag at line 205


  public String genMembers() { return ""; }

    // Declared in JragCodeGen.jrag at line 225

  
  public String genAbstractSyns() {
    StringBuffer buf = new StringBuffer();
    for(int i = 0; i < getNumSynDecl(); i++) {
      AttrDecl attr = getSynDecl(i);
      String s = attr.hostFileComment() + suppressWarnings() +
      "    public #TYPE# #CLASS#.#METHODNAME#(#PARMDECL#);\n";
      s = s.replaceAll("#TYPE#", attr.implType());
      s = s.replaceAll("#TYPEINSIGNATURE#", attr.getTypeInSignature());
      if(!aspectJ){
        s = s.replaceAll("#CLASS#\\.", "");
      
      }
      else
        s = s.replaceAll("#CLASS#", implName());
      s = s.replaceAll("#METHODNAME#", attr.attributeName());
      s = s.replaceAll("#PARMDECL#", attr.parametersDecl());
      buf.append(s);
    }
    return buf.toString();
  }

    // Declared in JragCodeGen.jrag at line 753


  public boolean hasLazySynEqFor(AttrDecl attr) {
    if(attr instanceof SynDecl) {
      SynEq synEq = lookupSynEq(attr.signature());
      return synEq != null && (synEq.decl().getLazy() || synEq.decl().isCircular()) ;
    }
    return false;
  }

    // Declared in JragCodeGen.jrag at line 1215
    

  public String genInhDeclarations() {
    StringBuffer buf = new StringBuffer();
    for(int i = 0; i < getNumInhDecl(); i++) {
       AttrDecl attr = getInhDecl(i);
       buf.append(attr.hostFileComment());
       
       String s = "    public #TYPE# #CLASS#.#METHODNAME#(#PARMDECL#);\n";
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

    // Declared in NameBinding.jrag at line 427


  public Iterator inhAttrSet() {
    return inhEqMap().keySet().iterator();
  }

    // Declared in NameBinding.jrag at line 431


  public Iterator inhAttrEqs(String id) {
    LinkedList list = (LinkedList)inhEqMap().get(id);
    return list != null ? list.iterator() : null;
  }

    // Declared in Ast.ast at line 3
    // Declared in Ast.ast line 3

    public TypeDecl(int i) {
        super(i);
    }

    // Declared in Ast.ast at line 6

    public TypeDecl(Ast p, int i) {
        this(i);
        parser = p;
    }

    // Declared in Ast.ast at line 10

    public TypeDecl() {
        this(0);

        setChild(null, 0);
        setChild(new List(), 1);
        setChild(new List(), 2);
        setChild(new List(), 3);
        setChild(new List(), 4);
        setChild(new List(), 5);
        setChild(new List(), 6);
        setChild(new List(), 7);

    }

    // Declared in Ast.ast at line 25


    // Declared in Ast.ast line 3
    public TypeDecl(IdDecl p0, List p1, List p2, List p3, List p4, List p5, List p6, List p7, String p8, int p9, int p10, String p11) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
        setChild(p3, 3);
        setChild(p4, 4);
        setChild(p5, 5);
        setChild(p6, 6);
        setChild(p7, 7);
        setFileName(p8);
        setStartLine(p9);
        setEndLine(p10);
        setComment(p11);
    }

    // Declared in Ast.ast at line 40


    public void dumpTree(String indent, java.io.PrintStream pStream) {
        pStream.println(indent + "TypeDecl"+ "\"" + getFileName() + "\""+ "\"" + getStartLine() + "\""+ "\"" + getEndLine() + "\""+ "\"" + getComment() + "\"");
        String childIndent = indent + "  ";
        for(int i = 0; i < getNumChild(); i++)
            getChild(i).dumpTree(childIndent, pStream);
    }

    // Declared in Ast.ast at line 47


    public Object jjtAccept(AstVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    // Declared in Ast.ast at line 51


public void jjtAddChild(Node n, int i) {
  checkChild(n, i);
  super.jjtAddChild(n, i);
}

    // Declared in Ast.ast at line 56


public void checkChild(Node n, int i) {
  if(i == 0 && !(n instanceof IdDecl))  throw new Error("Child number 0 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of IdDecl");
  if(i == 1) {
    if(!(n instanceof List)) throw new Error("Child number 1 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof ClassBodyDecl)) throw new Error("Child number " + k + " in ClassBodyDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of ClassBodyDecl");
  }
  if(i == 2) {
    if(!(n instanceof List)) throw new Error("Child number 2 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof SynDecl)) throw new Error("Child number " + k + " in SynDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of SynDecl");
  }
  if(i == 3) {
    if(!(n instanceof List)) throw new Error("Child number 3 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof SynEq)) throw new Error("Child number " + k + " in SynEqList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of SynEq");
  }
  if(i == 4) {
    if(!(n instanceof List)) throw new Error("Child number 4 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof InhDecl)) throw new Error("Child number " + k + " in InhDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of InhDecl");
  }
  if(i == 5) {
    if(!(n instanceof List)) throw new Error("Child number 5 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof InhEq)) throw new Error("Child number " + k + " in InhEqList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of InhEq");
  }
  if(i == 6) {
    if(!(n instanceof List)) throw new Error("Child number 6 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof Components)) throw new Error("Child number " + k + " in ComponentsList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of Components");
  }
  if(i == 7) {
    if(!(n instanceof List)) throw new Error("Child number 7 of TypeDecl has the type " + n.getClass().getName() + " which is not an instance of List");
    for(int k = 0; k < ((List)n).getNumChild(); k++)
      if(!(((List)n).getChildNoTransform(k) instanceof CollDecl)) throw new Error("Child number " + k + " in CollDeclList has the type " + ((List)n).getChildNoTransform(k).getClass().getName() + " which is not an instance of CollDecl");
  }
}

    // Declared in Ast.ast at line 95


  public int getNumChild() {
    return 8;
  }

    // Declared in Ast.ast at line 98

  public boolean mayHaveRewrite() { return false; }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setIdDecl(IdDecl node) {
        setChild(node, 0);
    }

    // Declared in Ast.ast at line 5

    public IdDecl getIdDecl() {
        return (IdDecl)getChild(0);
    }

    // Declared in Ast.ast at line 9


    public IdDecl getIdDeclNoTransform() {
        return (IdDecl)getChildNoTransform(0);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setClassBodyDeclList(List list) {
        setChild(list, 1);
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
        return (List)getChild(1);
    }

    // Declared in Ast.ast at line 27


    public List getClassBodyDeclListNoTransform() {
        return (List)getChildNoTransform(1);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setSynDeclList(List list) {
        setChild(list, 2);
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
        return (List)getChild(2);
    }

    // Declared in Ast.ast at line 27


    public List getSynDeclListNoTransform() {
        return (List)getChildNoTransform(2);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setSynEqList(List list) {
        setChild(list, 3);
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
        return (List)getChild(3);
    }

    // Declared in Ast.ast at line 27


    public List getSynEqListNoTransform() {
        return (List)getChildNoTransform(3);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setInhDeclList(List list) {
        setChild(list, 4);
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
        return (List)getChild(4);
    }

    // Declared in Ast.ast at line 27


    public List getInhDeclListNoTransform() {
        return (List)getChildNoTransform(4);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setInhEqList(List list) {
        setChild(list, 5);
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
        return (List)getChild(5);
    }

    // Declared in Ast.ast at line 27


    public List getInhEqListNoTransform() {
        return (List)getChildNoTransform(5);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setComponentsList(List list) {
        setChild(list, 6);
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
        return (List)getChild(6);
    }

    // Declared in Ast.ast at line 27


    public List getComponentsListNoTransform() {
        return (List)getChildNoTransform(6);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
    public void setCollDeclList(List list) {
        setChild(list, 7);
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
        return (List)getChild(7);
    }

    // Declared in Ast.ast at line 27


    public List getCollDeclListNoTransform() {
        return (List)getChildNoTransform(7);
    }

    // Declared in Ast.ast at line 2
    // Declared in Ast.ast line 3
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
    // Declared in Ast.ast line 3
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
    // Declared in Ast.ast line 3
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
    // Declared in Ast.ast line 3
    private String tokenString_Comment;

    // Declared in Ast.ast at line 3

    public void setComment(String value) {
        tokenString_Comment = value;
    }

    // Declared in Ast.ast at line 6

    public String getComment() {
        return tokenString_Comment;
    }

    protected java.util.Set instanceOf_TypeDecl_visited = new java.util.HashSet(4);
    // Declared in ClassRelations.jrag at line 35
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

    private boolean instanceOf_compute(TypeDecl c) {  return  c == this;  }

    protected boolean tempComponents_visited = false;
    protected boolean tempComponents_computed = false;
    protected Collection tempComponents_value;
    // Declared in ComponentsUtil.jrag at line 51
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
    for(int i = 0; i < getNumComponents(); i++) {
      list.add(getComponents(i));
    }
    return list;
  }

    protected boolean isRootNode_visited = false;
    // Declared in Errorcheck.jrag at line 45
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

    private boolean isRootNode_compute() {  return  false;  }

    protected boolean collectAstErrors_visited = false;
    // Declared in Errorcheck.jrag at line 48
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

    private String collectAstErrors_compute() {  return  astError();  }

    protected boolean astError_visited = false;
    // Declared in Errorcheck.jrag at line 62
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
      //result.append(getFileName() + ":" + getStartLine() + " "); TODO: FIXME
      result.append("Multiple production rule for non-terminal " +
          name() + "\n");
    }
    return result.toString();
  }

    protected java.util.Set lookupComponents_String_visited = new java.util.HashSet(4);
    protected java.util.Map lookupComponents_String_values = new java.util.HashMap(4);
    // Declared in NameBinding.jrag at line 46
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

    private Components lookupComponents_compute(String name) {  return  null;  }

    protected boolean implName_visited = false;
    // Declared in NameBinding.jrag at line 59
    public String implName() {
boolean interruptedCircle = false;
        if(implName_visited)
            throw new RuntimeException("Circular definition of attr: implName in class: ");
        implName_visited = true;
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        String implName_value = implName_compute();
        implName_visited = false;
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return implName_value;
    }

    private String implName_compute() {  return  	computeImplName(name());  }

    protected boolean name_visited = false;
    // Declared in NameBinding.jrag at line 61
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

    private String name_compute() {  return  getIdDecl().name();  }

    protected java.util.Set components_String_visited = new java.util.HashSet(4);
    protected java.util.Map components_String_values = new java.util.HashMap(4);
    // Declared in Attributes.jrag at line 219
    public Components components(String name) {
        Object _parameters = name;
        if(components_String_values.containsKey(_parameters))
            return (Components)components_String_values.get(_parameters);
boolean interruptedCircle = false;
        if(components_String_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: components in class: ");
        components_String_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        Components components_String_value = components_compute(name);
        if(isFinal && num == boundariesCrossed)
            components_String_values.put(_parameters, components_String_value);
        components_String_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return components_String_value;
    }

    private Components components_compute(String name)  {
    for(Iterator iter = getComponents(); iter.hasNext(); ) {
      Components c = (Components)iter.next();
      if(c.name().equals(name))
        return c;
    }
    return null;
  }

    protected java.util.Set hasCollEq_CollDecl_visited = new java.util.HashSet(4);
    // Declared in CollectionAttributes.jrag at line 319
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

    private boolean hasCollEq_compute(CollDecl decl) {  return  false;  }

    protected boolean collectErrors_visited = false;
    // Declared in Errorcheck.jrag at line 23
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
    return result.toString();
  }

    protected java.util.Set hasInhEq_String_visited = new java.util.HashSet(4);
    // Declared in JragCodeGen.jrag at line 1259
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

    private boolean hasInhEq_compute(String attrName)  {
    for(int i = 0; i < getNumInhEq(); i++) {
      InhEq equ = getInhEq(i);
      if(equ.getName().equals(attrName)) {
        return true;
      }
    }
    return false;
  }

    protected java.util.Set lookupSynDeclPrefix_String_visited = new java.util.HashSet(4);
    // Declared in JragCodeGen.jrag at line 1285
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
    for(int i = 0; i < getNumSynDecl(); i++)
      if(getSynDecl(i).signature().equals(signature) || getSynDecl(i).signature().startsWith(signature + "_"))
        return getSynDecl(i);
    return null;
  }

    protected java.util.Set lookupInhDeclPrefix_String_visited = new java.util.HashSet(4);
    // Declared in JragCodeGen.jrag at line 1297
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
    for(int i = 0; i < getNumInhDecl(); i++)
      if(getInhDecl(i).signature().equals(signature) || getInhDecl(i).signature().startsWith(signature + "_"))
        return getInhDecl(i);
    return null;
  }

    protected boolean synEquations_visited = false;
    protected boolean synEquations_computed = false;
    protected Set synEquations_value;
    // Declared in NameBinding.jrag at line 64
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
		Set set = new LinkedHashSet();
		for(int i = 0; i < getNumSynEq(); i++) {
			set.add(getSynEq(i));
		}
    return set;
  }

    protected boolean synDeclarations_visited = false;
    protected boolean synDeclarations_computed = false;
    protected Set synDeclarations_value;
    // Declared in NameBinding.jrag at line 77
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
		Set set = new LinkedHashSet();
		for(int i = 0; i < getNumSynDecl(); i++) {
			set.add(getSynDecl(i));
		}
		return set;
  }

    protected boolean inhEquations_visited = false;
    protected boolean inhEquations_computed = false;
    protected Set inhEquations_value;
    // Declared in NameBinding.jrag at line 90
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
		Set set = new LinkedHashSet();
		for(int i = 0; i < getNumInhEq(); i++) {
			set.add(getInhEq(i));
		}
		return set;
  }

    protected boolean inhDeclarations_visited = false;
    protected boolean inhDeclarations_computed = false;
    protected Set inhDeclarations_value;
    // Declared in NameBinding.jrag at line 103
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
		Set set = new LinkedHashSet();
		for(int i = 0; i < getNumInhDecl(); i++) {
			set.add(getInhDecl(i));
		}
		return set;
	}

    protected java.util.Set lookupSynDecl_String_visited = new java.util.HashSet(4);
    protected java.util.Map lookupSynDecl_String_values = new java.util.HashMap(4);
    // Declared in NameBinding.jrag at line 119
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
    for(int i = 0; i < getNumSynDecl(); i++)
      if(getSynDecl(i).signature().equals(signature))
        return getSynDecl(i);
    return null;
  }

    protected java.util.Set lookupSynEq_String_visited = new java.util.HashSet(4);
    protected java.util.Map lookupSynEq_String_values = new java.util.HashMap(4);
    // Declared in NameBinding.jrag at line 132
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
    for(int i = 0; i < getNumSynEq(); i++)
      if(getSynEq(i).signature().equals(signature))
        return getSynEq(i);
    return null;
  }

    protected java.util.Set lookupInhDecl_String_visited = new java.util.HashSet(4);
    protected java.util.Map lookupInhDecl_String_values = new java.util.HashMap(4);
    // Declared in NameBinding.jrag at line 145
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
    for(int i = 0; i < getNumInhDecl(); i++)
      if(getInhDecl(i).signature().equals(signature))
        return getInhDecl(i);
    return null;
  }

    protected java.util.Set lookupInhDeclSubclasses_String_visited = new java.util.HashSet(4);
    protected java.util.Map lookupInhDeclSubclasses_String_values = new java.util.HashMap(4);
    // Declared in NameBinding.jrag at line 158
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

    private InhDecl lookupInhDeclSubclasses_compute(String signature) {  return 
    lookupInhDecl(signature);  }

    protected java.util.Set lookupInhEq_String_String_visited = new java.util.HashSet(4);
    protected java.util.Map lookupInhEq_String_String_values = new java.util.HashMap(4);
    // Declared in NameBinding.jrag at line 171
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
    for(int i = 0; i < getNumInhEq(); i++)
      if(getInhEq(i).signature().equals(signature) && getInhEq(i).sonName().equals(childName))
        return getInhEq(i);
    for(int i = 0; i < getNumInhEq(); i++)
      if(getInhEq(i).signature().equals(signature) && getInhEq(i).sonName().equals("Child"))
        return getInhEq(i);
    return null;
  }

    protected java.util.Set hasInhEqFor_ASTDecl_String_Collection_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 190
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

    private boolean hasInhEqFor_compute(ASTDecl child, String signature, Collection visited) {  return  false;  }

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

    private boolean hasInhDeclFor_compute(String signature) {  return 
    lookupInhDecl(signature) != null;  }

    protected java.util.Set getInhDeclFor_String_Collection_visited = new java.util.HashSet(4);
    // Declared in NameBinding.jrag at line 334
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
    return null;
  }

    protected boolean inhEqMap_visited = false;
    protected boolean inhEqMap_computed = false;
    protected HashMap inhEqMap_value;
    // Declared in NameBinding.jrag at line 404
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
    for(int i = 0; i < getNumInhEq(); i++) {
      InhEq equ = getInhEq(i);
      String id = equ.type() + "_" + equ.attributeSignature();
      LinkedList list = (LinkedList)map.get(id);
      if(list == null) {
        list = new LinkedList();
        map.put(id, list);
      }
      if(equ.getSonName().equals("getChild")) {
        list.add(equ); // insert last
      }
      else if(equ.getComponents() != null && equ.getComponents().isNTA()) {
        list.add(0, equ); // insert first
      }
      else {
        list.add(0, equ); // insert first
      }
    }
    return map;
  }

    protected java.util.Set findSubclasses_ASTDecl_visited = new java.util.HashSet(4);
    protected java.util.Map findSubclasses_ASTDecl_values = new java.util.HashMap(4);
    // Declared in ClassRelations.jrag at line 66
    public Collection findSubclasses(ASTDecl target) {
        Object _parameters = target;
        if(findSubclasses_ASTDecl_values.containsKey(_parameters))
            return (Collection)findSubclasses_ASTDecl_values.get(_parameters);
boolean interruptedCircle = false;
        if(findSubclasses_ASTDecl_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: findSubclasses in class: ");
        findSubclasses_ASTDecl_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        int num = boundariesCrossed;
        boolean isFinal = this.is$Final();
        Collection findSubclasses_ASTDecl_value = getParent().Define_Collection_findSubclasses(this, null, target);
        if(isFinal && num == boundariesCrossed)
            findSubclasses_ASTDecl_values.put(_parameters, findSubclasses_ASTDecl_value);
        findSubclasses_ASTDecl_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return findSubclasses_ASTDecl_value;
    }

    protected java.util.Set findFathers_ASTDecl_visited = new java.util.HashSet(4);
    // Declared in ClassRelations.jrag at line 118
    public Collection findFathers(ASTDecl node) {
        Object _parameters = node;
boolean interruptedCircle = false;
        if(findFathers_ASTDecl_visited.contains(_parameters))
            throw new RuntimeException("Circular definition of attr: findFathers in class: ");
        findFathers_ASTDecl_visited.add(_parameters);
if(IN_CIRCLE) {
  interruptedCircle = true;
  IN_CIRCLE = false;
  pushEvalStack();
}
        Collection findFathers_ASTDecl_value = getParent().Define_Collection_findFathers(this, null, node);
        findFathers_ASTDecl_visited.remove(_parameters);
if(interruptedCircle) {
  IN_CIRCLE = true;
  popEvalStack();
}
        return findFathers_ASTDecl_value;
    }

    protected boolean env_visited = false;
    // Declared in NameBinding.jrag at line 9
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

    // Declared in NameBinding.jrag at line 5
    public TypeDecl Define_TypeDecl_hostClass(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return  this;
        }
        return getParent().Define_TypeDecl_hostClass(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
