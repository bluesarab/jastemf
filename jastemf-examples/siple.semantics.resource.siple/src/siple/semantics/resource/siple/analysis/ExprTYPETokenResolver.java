/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package siple.semantics.resource.siple.analysis;

import java.util.*;

import org.eclipse.emf.ecore.*;

import siple.semantics.*;
import siple.semantics.resource.siple.*;

public class ExprTYPETokenResolver implements ISipleTokenResolver {

	private SipleDefaultTokenResolver defaultTokenResolver =
		new SipleDefaultTokenResolver();

	public java.lang.String deResolve(Object value, EStructuralFeature feature,
			EObject container) {
		if (value == Type.Boolean) {
			return "Boolean";
		} else if (value == Type.Integer) {
			return "Integer";
		} else if (value == Type.Real) {
			return "Real";
		}
		return null;
	}

	public void resolve(String lexem, EStructuralFeature feature,
			ISipleTokenResolveResult result) {
		if ("Boolean".equals(lexem)) {
			result.setResolvedToken(Type.Boolean);
			return;
		} else if ("Integer".equals(lexem)) {
			result.setResolvedToken(Type.Integer);
		} else if ("Real".equals(lexem)) {
			result.setResolvedToken(Type.Real);
		} else {
			result.setErrorMessage(lexem + " is no valid type.");
		}
	}

	public void setOptions(Map<?, ?> options) {
		defaultTokenResolver.setOptions(options);
	}
}
