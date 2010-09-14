/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.util;

/**
 * This class provides basic infrastructure to interpret models. To implement
 * concrete interpreters, subclass this abstract interpreter and override the
 * interprete_* methods. The interpretation can be customized by binding the two
 * type parameters (ResultType, ContextType). The former is returned by all
 * interprete_* methods, while the latter is passed from method to method while
 * traversing the model. The concrete traversal strategy can also be exchanged.
 * One can use a static traversal strategy by pushing all objects to interpret on
 * the interpretation stack (using addObjectToInterprete()) before calling
 * interprete(). Alternatively, the traversal strategy can be dynamic by pushing
 * objects on the interpretation stack during interpretation.
 */
public class AbstractAstInterpreter<ResultType, ContextType> {
	
	private java.util.Stack<org.eclipse.emf.ecore.EObject> interpretationStack = new java.util.Stack<org.eclipse.emf.ecore.EObject>();
	
	public ResultType interprete(ContextType context) {
		ResultType result = null;
		while (!interpretationStack.empty()) {
			org.eclipse.emf.ecore.EObject next = interpretationStack.pop();
			result = interprete(next, context);
			if (!continueInterpretation(result)) {
				break;
			}
		}
		return result;
	}
	
	/**
	 * Override this method to stop the overall interpretation depending on the result
	 * of the interpretation of a single model elements.
	 */
	public boolean continueInterpretation(ResultType result) {
		return true;
	}
	
	public ResultType interprete(org.eclipse.emf.ecore.EObject object, ContextType context) {
		ResultType result = null;
		if (object instanceof org.jastemf.spec.ast.AggregateComponentsNTA) {
			result = interprete_org_jastemf_spec_ast_AggregateComponentsNTA((org.jastemf.spec.ast.AggregateComponentsNTA) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.AggregateComponents) {
			result = interprete_org_jastemf_spec_ast_AggregateComponents((org.jastemf.spec.ast.AggregateComponents) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.ASTDecl) {
			result = interprete_org_jastemf_spec_ast_ASTDecl((org.jastemf.spec.ast.ASTDecl) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.IdUse) {
			result = interprete_org_jastemf_spec_ast_IdUse((org.jastemf.spec.ast.IdUse) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.OptionalComponentNTA) {
			result = interprete_org_jastemf_spec_ast_OptionalComponentNTA((org.jastemf.spec.ast.OptionalComponentNTA) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.OptionalComponent) {
			result = interprete_org_jastemf_spec_ast_OptionalComponent((org.jastemf.spec.ast.OptionalComponent) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.NameNode) {
			result = interprete_org_jastemf_spec_ast_NameNode((org.jastemf.spec.ast.NameNode) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.Abstract) {
			result = interprete_org_jastemf_spec_ast_Abstract((org.jastemf.spec.ast.Abstract) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.ListComponents) {
			result = interprete_org_jastemf_spec_ast_ListComponents((org.jastemf.spec.ast.ListComponents) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.Grammar) {
			result = interprete_org_jastemf_spec_ast_Grammar((org.jastemf.spec.ast.Grammar) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.IdDecl) {
			result = interprete_org_jastemf_spec_ast_IdDecl((org.jastemf.spec.ast.IdDecl) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.TokenComponentNTA) {
			result = interprete_org_jastemf_spec_ast_TokenComponentNTA((org.jastemf.spec.ast.TokenComponentNTA) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.ListComponentsNTA) {
			result = interprete_org_jastemf_spec_ast_ListComponentsNTA((org.jastemf.spec.ast.ListComponentsNTA) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.Components) {
			result = interprete_org_jastemf_spec_ast_Components((org.jastemf.spec.ast.Components) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.TokenId) {
			result = interprete_org_jastemf_spec_ast_TokenId((org.jastemf.spec.ast.TokenId) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.Id) {
			result = interprete_org_jastemf_spec_ast_Id((org.jastemf.spec.ast.Id) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.TokenComponent) {
			result = interprete_org_jastemf_spec_ast_TokenComponent((org.jastemf.spec.ast.TokenComponent) object, context);
		}
		if (result != null) {
			return result;
		}
		if (object instanceof org.jastemf.spec.ast.TypeDecl) {
			result = interprete_org_jastemf_spec_ast_TypeDecl((org.jastemf.spec.ast.TypeDecl) object, context);
		}
		if (result != null) {
			return result;
		}
		return result;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_TypeDecl(org.jastemf.spec.ast.TypeDecl object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_TokenComponent(org.jastemf.spec.ast.TokenComponent object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_Id(org.jastemf.spec.ast.Id object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_TokenId(org.jastemf.spec.ast.TokenId object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_ListComponentsNTA(org.jastemf.spec.ast.ListComponentsNTA object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_TokenComponentNTA(org.jastemf.spec.ast.TokenComponentNTA object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_IdDecl(org.jastemf.spec.ast.IdDecl object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_Grammar(org.jastemf.spec.ast.Grammar object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_Components(org.jastemf.spec.ast.Components object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_ListComponents(org.jastemf.spec.ast.ListComponents object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_Abstract(org.jastemf.spec.ast.Abstract object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_NameNode(org.jastemf.spec.ast.NameNode object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_OptionalComponentNTA(org.jastemf.spec.ast.OptionalComponentNTA object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_OptionalComponent(org.jastemf.spec.ast.OptionalComponent object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_IdUse(org.jastemf.spec.ast.IdUse object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_ASTDecl(org.jastemf.spec.ast.ASTDecl object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_AggregateComponentsNTA(org.jastemf.spec.ast.AggregateComponentsNTA object, ContextType context) {
		return null;
	}
	
	public ResultType interprete_org_jastemf_spec_ast_AggregateComponents(org.jastemf.spec.ast.AggregateComponents object, ContextType context) {
		return null;
	}
	
	public void addObjectToInterprete(org.eclipse.emf.ecore.EObject object) {
		interpretationStack.push(object);
	}
	
}
