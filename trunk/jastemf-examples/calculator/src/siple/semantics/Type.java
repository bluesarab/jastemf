/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package siple.semantics;

/**
 * Enumeration of all primitive <i>SIPLE</i> types.
 * @author C. Bürger
 */
public enum Type {
	Boolean,
	Integer,
	Real,
	Undefined,
	ERROR_TYPE;
	/**
	 * Given two primitive types, select the more general which can represent
	 * values of both types' co-domain. Iff neither type subsumes the other
	 * one, the result is {@link #ERROR_TYPE}.
	 * @param t1 The first type to compare.
	 * @param t2 The second type to compare.
	 * @return The more general type or {@link #ERROR_TYPE} iff both are
	 * incompatible.
	 */
	public static Type computeCompatibleType(Type t1, Type t2) {
		if (t1 == ERROR_TYPE || t2 == ERROR_TYPE)
			return ERROR_TYPE;
		if (t1 == Undefined || t2 == Undefined)
			return ERROR_TYPE;
		if (t1 == t2)
			return t1;
		if (t1 == Boolean || t2 == Boolean)
			return ERROR_TYPE;
		if (t1 == Real || t2 == Real)
			return Real;
		return Integer;
	}
}
