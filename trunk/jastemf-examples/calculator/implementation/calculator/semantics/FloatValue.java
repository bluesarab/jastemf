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
public class FloatValue extends Value {
	public float value;
	public FloatValue(float v) {value = v;}
	public FloatValue asFloatValue() {return this;}
	public String toString() {return ""+ value;}
}
