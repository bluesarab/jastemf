/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package rtt.adaptation;

import java.util.*;

import rtt.annotations.*;

import calculator.semantics.ast.*;
import calculator.semantics.impl.*;

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
		List<ASTNode> result = new LinkedList<ASTNode>();
		for (int i=0; i < getNumChild(); i++)
			result.add(getChild(i));
		return result;
	}
	
	// Compare nodes' terminals:
	declare @method:
		public * VariableAssignmentImpl+.getLValue() : @Parser.Node.Compare;
	declare @method:
		public * VariableDeclarationImpl+.getName() : @Parser.Node.Compare;
	declare @method:
		public * VariableDeclarationImpl+.getVariableType() : @Parser.Node.Compare;
	declare @method:
		public * ReferenceImpl+.getName() : @Parser.Node.Compare;
	
	// Compare nodes' type and address:
	declare @method:
		public * ASTNode+.toString() : @Parser.Node.Compare;
	
	// Compare variable declarations' type:
	declare @method:
		public * VariableDeclarationImpl+.getType() : @Parser.Node.Compare;
	// Compare the declaration associated with assignments' left hand:
	declare @method:
		public * VariableAssignmentImpl+.getDeclaration() : @Parser.Node.Compare;
	// Compare assignments' type:
	declare @method:
		public * VariableAssignmentImpl+.getType() : @Parser.Node.Compare;
	// Compare assignments' value:
	declare @method:
		public * VariableAssignmentImpl+.getValue() : @Parser.Node.Compare;
	// Compare expressions' type:
	declare @method:
		public * ExpressionImpl+.getType() : @Parser.Node.Compare;
	// Compare expressions' value:
	declare @method:
		public * ExpressionImpl+.getValue() : @Parser.Node.Compare;
	// Compare references' associated declaration:
	declare @method:
		public * ReferenceImpl+.getDeclaration() : @Parser.Node.Compare;
}
