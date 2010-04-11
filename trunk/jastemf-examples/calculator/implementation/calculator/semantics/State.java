package calculator.semantics;

import java.util.*;

import calculator.semantics.ast.*;

/**
 * @author C. Bürger
 */
public final class State {
	private Stack<Frame> stack = new Stack<Frame>();
	
	public State() {stack.push(new Frame());}
	
	public Object lookUpValue(VariableDeclaration decl) {
		for (int i = stack.size() - 1; i >= 0; i--) {
			final Object value = stack.get(i).env.get(decl);
			if (value != null)
				return value;
		}
		throw new IllegalStateException(
				"State Error: Read access to unallocated variable.");
	}
	
	public void setValue(VariableDeclaration decl, Object newValue) {
		for (int i = stack.size() - 1; i >= 0; i--) {
			if (stack.get(i).env.containsKey(decl)) {
				stack.get(i).env.put(decl, newValue);
				return;
			}
		}
		throw new IllegalStateException(
				"State Error: Write access to unallocated variable.");
	}
	
	public void allocateVariable(VariableDeclaration decl) {
		if (stack.peek().env.containsKey(decl))
			throw new IllegalStateException(
					"State Error: Dupliated variable allocation.");
		stack.peek().env.put(decl, null);
	}
	
	public Object getReturnValue(Object value) {
		if (stack.peek().returnValue == null)
			throw new IllegalStateException(
					"State Error: Read access to unspecified return value.");
		return stack.peek().returnValue;
	}
	
	public void setReturnValue(Object value) {
		if (stack.peek().returnValue != null)
			throw new IllegalStateException(
					"State Error: Duplicated return value specification.");
		stack.peek().returnValue = value;
	}
	
	public void newFrame() {stack.push(new Frame());}
	public void deleteFrame() {stack.pop();}
	
	private static final class Frame {
		private Map<VariableDeclaration, Object> env =
			new TreeMap<VariableDeclaration, Object>();
		private Object returnValue;
	}
}
