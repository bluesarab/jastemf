package org.jastemf.siple.validation;

import org.eclipse.xtext.validation.Check;
import org.jastemf.siple.impl.ExpressionImpl;
import org.jastemf.siple.interfaces.Declaration;
import org.jastemf.siple.interfaces.Expression;
import org.jastemf.siple.interfaces.Reference;
import org.jastemf.siple.meta.SiplePackage;
import org.jastemf.siple.semantics.Type;
 

public class SipleJavaValidator extends AbstractSipleJavaValidator {

	@Check
	public void checkReference(Reference reference){
		if(reference.getDeclaration()==null){
			error("Unable to find declaration with name '" + reference.getName() + "'.",SiplePackage.Literals.REFERENCE__DECLARATION);
		}
	}
	
	@Check
	public void checkType(Expression expression){
		ExpressionImpl impl = (ExpressionImpl)(expression);
		if(impl.getType()==Type.ERROR_TYPE){
			error("Unable to determine the expression's type.",SiplePackage.Literals.EXPRESSION__TYPE);
		}
	}
	
	@Check
	public void checkType(Declaration declaration){
		if(declaration.getType()==Type.ERROR_TYPE){
			error("Unable to determine the declarations's type.",SiplePackage.Literals.EXPRESSION__TYPE);			
		}
	}
	


}
