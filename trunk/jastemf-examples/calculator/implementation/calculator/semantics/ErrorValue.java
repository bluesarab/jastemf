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
public class ErrorValue extends Value {
	public ErrorValue asErrorValue() {return this;}
	public String toString() {return "Error";}
}
