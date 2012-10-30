package org.jastemf.template.test.rtt;

import rtt.annotations.*;
import org.jastemf.template.ast.*;
import org.jastemf.template.test.ParsingError;

public aspect AdaptAst { 
	
	declare @type: ASTNode+ : @Parser.Node;
	
	declare @type: ParsingError+ : @Parser.Node;  
	
	// Compare nodes' children:
	declare @method:
	    public * ASTNode.getChildren() : @Parser.Node.Child;
	    
	public java.util.List<ASTNode> ASTNode.getChildren() {
	    java.util.List<ASTNode> result = new java.util.LinkedList<ASTNode>();
	    for (int i=0; i < getNumChild(); i++)
	        result.add(getChild(i));
	    return result;
	} 
	
	// Compare nodes' terminals:
	declare @method:
	    public * ParsingError+.toString() : @Parser.Node.Compare;
	
	// Compare nodes' terminals:
	declare @method:
	    public * ASTNode+.genString() : @Parser.Node.Compare;
	
	    
	// Compare nodes' terminals:
	declare @method:
	   public * RootElement+.getname() : @Parser.Node.Compare;
	
}
