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
public class IntegerValue extends Value {
	public int value;
	public IntegerValue(int v) {value = v;}
	public IntegerValue asIntegerValue() {return this;}
	public String toString() {return ""+ value;}
}
