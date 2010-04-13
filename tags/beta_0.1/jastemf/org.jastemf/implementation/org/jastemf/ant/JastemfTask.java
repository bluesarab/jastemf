/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf.ant;

import java.io.*;

import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.common.util.*;

import org.apache.tools.ant.*;

import jastadd.JastAddTask;

import org.jastemf.*;
import org.jastemf.util.*;

/**
 * <i>Ant</i> task to support <i>JastEMF</i> integrations steered by build
 * scripts.
 * <p>
 * Example build script (The <tt>jastemf</tt> target is provided by the
 * taskdef specification in this package):
 * <pre>
 * &lt;jastemf
 *     genmodel="path/to/the/generator/model/to/use/modelname.genmodel"
 *     outpackage="package.for.the.integration.artifacts.generated.by.jastemf"
 *     astpackage="package.for.the.ast.classes.generated.by.jastadd"&gt;
 *     &lt;jastadd
 *         <i>Call to JastAdd's Ant task as specified by the</i> <tt>'jastadd.JastAddTask'</tt>
 *         <i>class in the class-path. The only difference is, that the</i> <tt>'outdir'</tt>
 *         <i>and</i> <tt>'package'</tt> <i>attributes</i> <b>do not matter</b> <i>, i.e. they
 *         should be left unspecified. Their values are already known
 *         (the</i> <tt>'outdir'</tt> <i>can be derived from the generator model).</i>
 *     /&gt;
 * /&gt;
 * </pre>
 * @author C. Bürger
 */
public class JastemfTask extends Task {
	private File genmodelFile;
	private GenModel genmodel;
	private String outpackage;
	private String astpackage;
	private JastAddTask jastadd;
	
	/** See {@link IIntegrationContext#genmodel()}. */
	public void setGenmodel(File mfile) {this.genmodelFile = mfile;}
	/** See {@link IIntegrationContext#outpackage()}. */
	public void setOutpackage(String outp) {this.outpackage = outp;}
	/** See {@link IIntegrationContext#astpackage()}. */
	public void setAstpackage(String astp) {this.astpackage = astp;}
	/**
	 * Support a nested <i>JastAdd</i> <i>Ant</i> task used by <i>JastEMF</i>
	 * to generate the evaluator to adapt.
	 */
	public void addJastadd(JastAddTask jastadd) {this.jastadd = jastadd;}
	
	/**
	 * Start the integration process for the given {@link
	 * IIntegrationContext#genmodel() generation model file}, {@link
	 * IIntegrationContext#astpackage() <i>JastAdd</i> evaluator package} and
	 * {@link IIntegrationContext#outpackage() <i>JastEMF</i> integration
	 * artifacts package}.
	 */
	public void execute() throws BuildException {
		try {
			/* Initialize integration resources */
			URI modelURI = IOSupport.createFileURI(
					genmodelFile.getAbsolutePath());
			genmodel = IOSupport.loadGenModel(modelURI);
			
			IIntegrationContext context = new AIntegrationContext() {
				public GenModel genmodel() {return genmodel;}
				public String outpackage() {return outpackage;}
				public String astpackage() {return astpackage;}
			};
			
			/* Perform integration */
			IntegrationManager.performIntegration(context, jastadd);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}
}
