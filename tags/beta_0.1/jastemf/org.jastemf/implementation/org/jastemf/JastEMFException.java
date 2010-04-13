/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf;

import java.io.IOException;

/**
 * {@link IOException Exception type thrown}, always an integration fails
 * because the given integration resources ({@link
 * IIntegrationContext#genmodel() the <i>EMF</i> generator model}, {@link
 * IIntegrationContext#astpackage() the <i>JastAdd</i> AST package} and its
 * contained classes and {@link IIntegrationContext#outpackage() the output
 * directory for the generated integration artifacts}) do not satisfy
 * integration requirements or cannot be accessed.
 * @author C. Bürger
 */
public class JastEMFException extends IOException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construct a new <i>JastEMF</i> exception with the given error message.
	 * @param message The exception's integration error message.
	 */
	public JastEMFException(String message) {super(message);}
	
	/**
	 * Construct a new <i>JastEMF</i> exception with the given exception as
	 * cause.
	 * @param e The exception's cause. 
	 */
	public JastEMFException(Exception e) {
		super(e);
	}
}
