package calculator.semantics;

import java.util.*;

import calculator.semantics.ast.*;

/**
 * @author C. Bürger
 */
public class State {
	private Map<VariableDeclaration, Object> env =
		new HashMap<VariableDeclaration, Object>();
	
	public Object lookUpValue(VariableDeclaration decl) {
		final Object value = env.get(decl);
		return value != null ? value : new ErrorValue();
	}
}
