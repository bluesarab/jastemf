/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package statemachine.siple.codegeneration;

public class JavaHelpers {
	public static String convertToValidOperationname(String toConvert) {
		toConvert = toConvert.replaceAll(" ", "_");
		toConvert = toConvert.replaceAll("-", "_");
		toConvert = toConvert.replaceAll(">", "_");
		return toConvert;
		
	}
}
