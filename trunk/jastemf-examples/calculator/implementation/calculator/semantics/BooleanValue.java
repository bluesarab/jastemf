/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package calculator.semantics;

/**
 * @author C. Bürger
 */
public class BooleanValue extends Value {
	public boolean value;
	public BooleanValue(boolean v) {value = v;}
	public BooleanValue asBooleanValue() {return this;}
	public String toString() {return ""+ value;}
}
