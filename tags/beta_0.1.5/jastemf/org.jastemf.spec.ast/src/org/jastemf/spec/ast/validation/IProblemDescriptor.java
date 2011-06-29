package org.jastemf.spec.ast.validation;

import org.jastemf.spec.ast.ast.ASTNode;


/**
 * Interface for collecting errors including an error message and the actual 
 * cause for the problem.
 * 
 * @author skarol
 */

public interface IProblemDescriptor {
	
	public String getMessage();
	
	public ASTNode<?> getCause();
	
}
