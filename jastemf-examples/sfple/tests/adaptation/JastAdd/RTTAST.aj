package rtt.adaptation;

import calculator.semantics.ast.*;

/**
 * AspectJ specification to adapt the Caclulator's AST for RTT.
 * @author C. Bürger
 */
public aspect RTTAST {
	// Support meaningful toString() results for AST nodes:
	public String ASTNode.toString() {
		return "(Type: " + nodeType() + ", Id: " + NodeAddress() + ")";
	}
	
	declare @type: ASTNode+ : @rtt.annotations.Parser.Node;
	
	// Compare node types:
	declare @method:
		public * ASTNode+.nodeType() : @rtt.annotations.Parser.Node.Compare;
	public String ASTNode.nodeType() {return getClass().toString();}
	// Compare constants' value:
	declare @method:
		public * Constant+.Print() : @rtt.annotations.Parser.Node.Compare;
	// Compare vectors' length:
	declare @method:
		public * Vector+.Length() : @rtt.annotations.Parser.Node.Compare;
	// Compare references' definition:
	declare @method:
		public * Reference+.Definition() : @rtt.annotations.Parser.Node.Compare;
	// Compare nodes' target definition:
	declare @method:
		public * ASTNode+.Defines() : @rtt.annotations.Parser.Node.Compare;
	// Compare definitions' dependency information:
	declare @method:
		public * Definition+.IsCyclic() : @rtt.annotations.Parser.Node.Compare;
	
	// Compare node children:
	declare @method:
		public * ASTNode+.getChildren() : @rtt.annotations.Parser.Node.Child;
	public java.util.List<ASTNode<?>> ASTNode.getChildren() {
		java.util.List<ASTNode<?>> result =
			new java.util.LinkedList<ASTNode<?>>();
		for (int i = 0; i < getNumChild(); i++)
			result.add(getChild(i));
		return result;
	}
}
