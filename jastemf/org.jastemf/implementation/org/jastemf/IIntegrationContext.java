/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf;

import java.net.*;

import org.eclipse.emf.codegen.ecore.genmodel.*;

/**
 * Container class for project information needed throughout integration.
 * @author C. Bürger
 */
public interface IIntegrationContext {
	/**
	 * The <i>EMF</i> generator model used for integration.
	 * @return The generator model to use to generate a model implementation.
	 */
	GenModel genmodel();
	
	/**
	 * The source code folder for the generated <i>EMF</i> model
	 * implementation, the <i>JastAdd</i> AST classes and the <i>JastEMF</i>
	 * integration artifacts.
	 * @return Absolute URI to the source code folder of the project to
	 * integrate.
	 */
	URI srcfolder();
	
	/**
	 * Construct an absolute <tt>URI</tt> to {@link IIntegrationContext this
	 * context's} {@link #astpackage() AST package}.
	 * @return <tt>URI</tt> to the folder containing the AST classes
	 * <i>JastAdd</i> generated.
	 */
	URI astfolder();
	
	/**
	 * Construct -- based on {@link IIntegrationContext this context's} {@link
	 * #srcfolder() source folder} -- an absolute <tt>URI</tt> to the folder
	 * containing all interfaces of the given generator model package.
	 * @param genPackage The package for which to compute its interface folder.
	 * @return <tt>URI</tt> to the given package's interface folder.
	 */
	URI interfacefolder(GenPackage genPackage);
	
	/**
	 * Construct -- based on {@link IIntegrationContext this context's} {@link
	 * #srcfolder() source folder} -- an absolute <tt>URI</tt> to the folder
	 * containing all implementation classes of the given generator model
	 * package.
	 * @param genPackage The package for which to compute its implementation
	 * folder.
	 * @return <tt>URI</tt> to the given package's implementation folder.
	 */
	URI classfolder(GenPackage genPackage);
	
	/**
	 * Construct an absolute <tt>URI</tt> to the given package w.r.t.
	 * {@link IIntegrationContext this context's} {@link #srcfolder()
	 * source folder}.
	 * <p>
	 * <b>Example</b>: Assume the {@link #srcfolder() source folder
	 * <tt>URI</tt>} is <tt>file:/D:/path/to/source</tt> and the given
	 * <tt>Package</tt> parameter is <tt>my.package</tt>. The returned
	 * <tt>URI</tt> is <tt>file:/D:/path/to/source/my/package</tt>.
	 * @param Package The package for which to construct an <tt>URI</tt>.
	 * @return <tt>URI</tt> to the folder representing the given package.
	 */
	URI packagefolder(String Package);
	
	/**
	 * The project's package into which <i>JastEMF</i> will generate
	 * all integration artifacts.
	 * @return The integration artifact package.
	 */
	String outpackage();
	
	/**
	 * The project's package containing the AST classes <i>JastAdd</i>
	 * generated.
	 * @return The AST classes' package.
	 */
	String astpackage();
}
