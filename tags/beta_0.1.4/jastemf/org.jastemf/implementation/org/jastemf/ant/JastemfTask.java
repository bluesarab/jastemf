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
import java.util.*;

import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.common.util.*;

import org.apache.tools.ant.*;
import org.apache.tools.ant.types.*;

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
 *     astpackage="package.for.the.ast.classes.generated.by.jastadd"
 *     jastadd="optional JastAdd command line options"&gt;
 *     &lt;fileset dir="path/to/the/semantic/specifications/base/dir"&gt;
 *         &lt;include name="specification/to/include/spec.jrag"/&gt;
 *     &lt;/fileset&gt;
 * /&gt;
 * </pre>
 * @author C. Bürger
 */
public class JastemfTask extends Task {
	private File genmodelFile;
	private GenModel genmodel;
	private String outpackage;
	private String astpackage;
	private String jaddcmd;
	private LinkedHashSet<String> jragspecs = new LinkedHashSet<String>();
	protected boolean genEditCode = false;
	
	
	/** See {@link IIntegrationContext#genmodel()}. */
	public void setGenmodel(File mfile) {this.genmodelFile = mfile;}
	/** See {@link IIntegrationContext#outpackage()}. */
	public void setOutpackage(String outp) {this.outpackage = outp;}
	/** See {@link IIntegrationContext#astpackage()}. */
	public void setAstpackage(String astp) {this.astpackage = astp;}
	/** See {@link IIntegrationContext#jaddcmd()}. */
	public void setJastadd(String jaddcmd) {this.jaddcmd = jaddcmd;}
	/** See {@link IIntegrationContext#jragspecs()}. */
	public void addConfiguredFileSet(FileSet fileset) {
		DirectoryScanner s = fileset.getDirectoryScanner(getProject());
		String[] files = s.getIncludedFiles();
		String baseDir = s.getBasedir().getPath();
		for (int i = 0; i < files.length; i++)
			this.jragspecs.add(baseDir + File.separator + files[i]);
	}
	/** See {@link IIntegrationContext#generateEditCode()}. */
	public void setGenerateeditcode(boolean flag) {this.genEditCode = flag;}
	
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
			URI modelURI = URI.createFileURI(
					genmodelFile.getAbsolutePath());
			genmodel = IOSupport.loadGenModel(modelURI);
			
			IIntegrationContext context = new AIntegrationContext() {
				public GenModel genmodel() {return genmodel;}
				public String outpackage() {return outpackage;}
				public String astpackage() {return astpackage;}
				public String jaddcmd() {return jaddcmd;}
				public Set<String> jragspecs() {return jragspecs;}
				public boolean generateEditCode() {return genEditCode;}
			};
			
			/* Perform integration */
			IntegrationManager.performIntegration(context);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}
}
