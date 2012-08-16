package emf;

import java.lang.reflect.Constructor;

import jrag.AST.Node;
import jrag.AST.Token;
import jrag.AST.SimpleNode;

/**
 * This aspects just factors out code that was added manually to the generated SimpleNode class
 * in JavaCC.   
 */

public privileged aspect SimpleNodeExtensions {
	 	
	  public Token SimpleNode.firstToken;
	  public Token SimpleNode.lastToken; //added

	  public SimpleNode SimpleNode.copy() {
	      try {
	          Constructor c = getClass().getDeclaredConstructor(new Class[] { int.class });
	          SimpleNode node = (SimpleNode)c.newInstance(new Object[] { new Integer(id) });
	          if(children != null) node.children = (Node[])children.clone();
	          return node;
	      } catch (Exception e) {
	      }
	      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
	      return null;
	    }
	    public SimpleNode SimpleNode.fullCopy() {
	        SimpleNode res = (SimpleNode)copy();
	        for(int i = 0; i < jjtGetNumChildren(); i++) {
	          SimpleNode node = (SimpleNode)jjtGetChild(i);
	          if(node != null) node = node.fullCopy();
	          node.jjtSetParent(res);
	          res.jjtAddChild(node, i);
	        }
	        return res;
	    }
	
}
