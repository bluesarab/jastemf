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
public enum Type {
	Boolean,
	Integer,
	Real,
	ERROR_TYPE;
	public static Type computeCompatibleType(Type t1, Type t2) {
		if (t1 == t2)
			return t1;
		if (t1 == ERROR_TYPE || t2 == ERROR_TYPE)
			return ERROR_TYPE;
		if (t1 == Boolean || t2 == Boolean)
			return ERROR_TYPE;
		if (t1 == Real || t2 == Real)
			return Real;
		return Integer;
	}
}
