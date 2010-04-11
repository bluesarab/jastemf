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
 * Dummy class, whose instances represent results of erroneous computations.
 * E.g. the value of "true && 3" is an {@link ErrorValue}.
 * @author C. Bürger
 */
public final class ErrorValue {
	public String toString() {return "Error Value";}
}
