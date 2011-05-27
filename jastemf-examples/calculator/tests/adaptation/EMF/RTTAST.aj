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

import siple.semantics.ast.*;
import siple.semantics.impl.*;

/**
 * AspectJ specification to adapt the syntax and semantics for RTT.
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
	
	/** AST Structure */
	
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
		public * DeclarationImpl+.getName() : @Parser.Node.Compare;
	declare @method:
		public * ReferenceImpl+.getName() : @Parser.Node.Compare;
	declare @method:
		public * ConstantImpl+.getLexem() : @Parser.Node.Compare;
		
	// Compare nodes' type and address:
	declare @method:
		public * ASTNode+.toString() : @Parser.Node.Compare;
	
	/** Name Analysis */
	
	// Compare the program's main procedure:
	declare @method:
		public * CompilationUnitImpl+.MainProcedure() : @Parser.Node.Compare;
	// Compare references' associated declaration:
	declare @method:
		public * ReferenceImpl+.Declaration() : @Parser.Node.Compare;
	
	/** Type Analysis */
	
	// Compare declarations' type:
	declare @method:
		public * DeclarationImpl+.getType() : @Parser.Node.Compare;
	// Compare expressions' type:
	declare @method:
		public * ExpressionImpl+.getType() : @Parser.Node.Compare;
	
	/** Constraint Checking */
	
	// Compare programs' correctness:
	declare @method:
		public * ASTNode+.isIsCorrectLocal() : @Parser.Node.Compare("IsCorrectLocal");
	
	/** Interpretation **/
	
	declare @method:
		public * CompilationUnitImpl+.rttInterpret() : @Parser.Node.Compare;
	public String CompilationUnitImpl.rttInterpret() {
		return IsCorrect() ? Interpret().getStdOut().toString() :
			"RTT: Not interpreted; Program contains errors.";
	}
}
