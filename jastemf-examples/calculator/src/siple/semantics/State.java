package siple.semantics;

import java.util.*;

import siple.semantics.ast.*;
import siple.semantics.impl.*;

/**
 * Instances of this class represent <i>SIPLE</i> interpreter states. The
 * interpretation of a <i>SIPLE</i> program starts with {@link #State() the
 * empty state consisting of a single frame} and finishes either, with a state
 * consisting of a single frame that contains the program's results or an
 * {@link InterpretationException interpretation exception}, iff the program is
 * erroneous. Since <i>SIPLE</i> is Turing complete, the interpretation might
 * also not terminate at all.
 * @author C. Bürger
 */
public final class State {
	private final Stack<Frame> stack = new Stack<Frame>();
	private final StringBuilder stdOut = new StringBuilder();
	
	/**
	 * Construct a new state, consisting of a single empty frame.
	 */
	public State() {stack.push(new Frame());}
	
	/**
	 * Return the buffer containing the state's standard output.
	 * @return The state's standard output.
	 */
	public StringBuilder getStdOut() {return stdOut;}
	
	/**
	 * Return the given variable's current value.
	 * @param decl The variable for which to look up its value.
	 * @return The variable's value.
	 * @throws InterpretationException Thrown, iff the given variable is not
	 * allocated.
	 */
	public Object lookUpValue(VariableDeclaration decl)
	throws InterpretationException {
		for (int i = stack.size() - 1; i >= 0; i--)
			if (stack.get(i).env.containsKey(decl))
				return stack.get(i).env.get(decl);
		throw new InterpretationException(
				"Read access to unallocated variable.");
	}
	
	/**
	 * Set the given variable's value.
	 * @param decl The variable for which to set its value.
	 * @param newValue The variable's new value.
	 * @throws InterpretationException Thrown, iff the given variable is not
	 * allocated.
	 */
	public void setValue(VariableDeclaration decl, Object newValue)
	throws InterpretationException {
		for (int i = stack.size() - 1; i >= 0; i--) {
			if (stack.get(i).env.containsKey(decl)) {
				stack.get(i).env.put(decl, newValue);
				return;
			}
		}
		throw new InterpretationException(
				"Write access to unallocated variable.");
	}
	
	/**
	 * Allocate memory for the given variable in the current frame and
	 * initialize its value. The state is not changed iff the variable is
	 * already allocated <b>in the current frame</b>.
	 * @param decl The variable to allocate and initialize.
	 * @param value The variable's initialization value.
	 */
	public void allocateVariable(VariableDeclaration decl, Object value) {
		if (!stack.peek().env.containsKey(decl))
			stack.peek().env.put(decl, value);
	}
	
	/**
	 * Get the current frame's return value. The result can be <tt>null</tt>.
	 * @return The current frame's return value.
	 */
	public Object getReturnValue() {
		return stack.peek().returnValue;
	}
	
	/**
	 * Set the current frame's return value.
	 * @param value The new return value.
	 */
	public void setReturnValue(Object value) {
		stack.peek().returnValue = value;
	}
	
	/**
	 * Push a new frame on top of the stack.
	 */
	public void newFrame() {stack.push(new Frame());}
	
	/**
	 * Delete the state's topmost frame. It is not permitted to delete the
	 * global scope frame.
	 * @throws InterpretationException Thrown, iff the state already consists
	 * only of a single frame - the global scope frame.
	 */
	public void deleteFrame() throws InterpretationException {
		if (stack.size() <= 1)
			throw new InterpretationException(
					"Deletion of the global scope frame.");
		stack.pop();
	}
	
	private static final class Frame {
		private Map<VariableDeclaration, Object> env =
			new TreeMap<VariableDeclaration, Object>();
		private Object returnValue;
	}
}
