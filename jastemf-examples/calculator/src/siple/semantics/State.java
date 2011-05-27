/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package siple.semantics;

import java.util.*;

import siple.semantics.ast.*;

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
	private Stack<Frame> stack = new Stack<Frame>();
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
	 * Return an entities current value.
	 * @param decl The entity for which to look up its value.
	 * @return The entities' value.
	 * @throws InterpretationException Thrown, iff the given entity is not
	 * allocated.
	 */
	public Object lookUpValue(Declaration decl)
	throws InterpretationException {
		for (int i = stack.size() - 1; i >= 0; i--)
			if (stack.get(i).env.containsKey(decl))
				return stack.get(i).env.get(decl);
		throw new InterpretationException(
				"Read access to unallocated variable ["+ decl.getName() +"].");
	}
	
	/**
	 * Set an entities' value.
	 * @param decl The entity for which to set its value.
	 * @param newValue The entities' new value.
	 * @throws InterpretationException Thrown, iff the given entity is not
	 * allocated.
	 */
	public void setValue(Declaration decl, Object newValue)
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
	 * Allocate memory for the given entity in the current frame and
	 * initialize its value.
	 * 
	 * To avoid reallocation errors in programs like<br>
	 * <tt>While </tt>a condition<tt> Do<br>
	 *  Var j:Integer; % Variable only allocated once throughout the loop<br>
	 * Od;</tt><br>
	 * the state is not changed, iff the entity is already allocated <b>in the
	 * current frame</b>.
	 * @param decl The entity to allocate and initialize.
	 * @param value The entities' initialization value.
	 */
	public void allocateVariable(Declaration decl, Object value) {
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
		private Map<Declaration, Object> env =
			new TreeMap<Declaration, Object>();
		private Object returnValue;
	}
}
