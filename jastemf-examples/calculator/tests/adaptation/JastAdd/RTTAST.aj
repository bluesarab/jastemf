/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package rtt.adaptation;

import rtt.annotations.*;

import calculator.semantics.ast.*;

/**
 * AspectJ specification to adapt the Caclulator's AST for RTT.
 * @author C. Bürger
 */
public aspect RTTAST {
	// Support meaningful toString() results for AST nodes:
	String around(ASTNode node):
	execution(String ASTNode+.toString()) && target(node) {
		String type = node.getClass().toString();
		if (type.endsWith("Impl"))
			type = type.substring(0, type.length() - 4);
		type = type.substring(type.lastIndexOf('.') + 1);
		if (type.equals("ASTList"))
			type = "List";
		return "(Node-Type: " + type + " | Node-Address: " + node.NodeAddress() + ")";
	}
	public String ASTNode.toString() {return null;}
	
	declare @type: ASTNode+ : @Parser.Node;
	
	// Compare nodes' children:
	declare @method:
		public * ASTNode.getChildren() : @Parser.Node.Child;
	@SuppressWarnings("unchecked")
	public java.util.List<ASTNode> ASTNode.getChildren() {
		java.util.List<ASTNode> result = new java.util.LinkedList<ASTNode>();
		for (int i=0; i < getNumChild(); i++)
			result.add(getChild(i));
		return result;
	}
	
	// Compare nodes' terminals:
	declare @method:
		public * VariableAssignment+.getLValue() : @Parser.Node.Compare;
	declare @method:
		public * VariableDeclaration+.getName() : @Parser.Node.Compare;
	declare @method:
		public * VariableDeclaration+.getDeclaredType() : @Parser.Node.Compare;
	declare @method:
		public * Reference+.getName() : @Parser.Node.Compare;
	
	// Compare nodes' type and address:
	declare @method:
		public * ASTNode+.toString() : @Parser.Node.Compare;
	
	// Compare variable declarations' type:
	declare @method:
		public * VariableDeclaration+.Type() : @Parser.Node.Compare;
	// Compare the declaration associated with assignments' left hand:
	declare @method:
		public * VariableAssignment+.Declaration() : @Parser.Node.Compare;
	// Compare assignments' type:
	declare @method:
		public * VariableAssignment+.Type() : @Parser.Node.Compare;
	// Compare assignments' value:
	declare @method:
		public * VariableAssignment+.Value() : @Parser.Node.Compare;
	// Compare expressions' type:
	declare @method:
		public * Expression+.Type() : @Parser.Node.Compare;
	// Compare expressions' value:
	declare @method:
		public * Expression+.Value() : @Parser.Node.Compare;
	// Compare references' associated declaration:
	declare @method:
		public * Reference+.Declaration() : @Parser.Node.Compare;
}
