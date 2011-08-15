/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf.util;

import java.io.*;
import java.util.*;
import java.text.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;

import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.emf.codegen.ecore.generator.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.generator.*;

import org.jastemf.*;

/**
 * Collection of support methods to ease resource and <i>EMF model</i> handling.
 * 
 * @author C. Bürger
 */
final public class IOSupport {
	/**
	 * Load an <i>Ecore</i> model using an EMF URI.
	 * 
	 * @param uri
	 *            The model's URI.
	 * @return The loaded model.
	 * @throws IOException
	 *             Thrown, iff the model cannot be loaded.
	 */
	public static EObject loadModel(URI uri) throws IOException {
		ResourceSet rs = new ResourceSetImpl();
		Resource r = rs.getResource(uri, true);
		r.load(null);
		if (r.getContents().size() > 0) {
			return r.getContents().get(0);
		}
		return null;
	}
	
	/**
	 * Convenience method. Load an <i>Ecore</i> model using a <b>java.net.URI</b>.
	 * 
	 * @param uri
	 *            The model's URI.
	 * @return The loaded model.
	 * @throws IOException
	 *             Thrown, iff the model cannot be loaded.
	 */
	public static EObject loadModel(java.net.URI uri) throws IOException {
		return loadModel(URI.createURI(uri.toString()));
	}

	/**
	 * Load an <i>EMF</i> generator model using an URI.
	 * 
	 * @param uri
	 *            The generator model's URI.
	 * @return The loaded generator model.
	 * @throws IOException
	 *             Thrown, iff the model cannot be loaded.
	 */
	public static GenModel loadGenModel(URI uri) throws IOException {
		EObject eo = loadModel(uri);
		if (eo != null && eo instanceof GenModel) {
			return (GenModel) eo;
		}
		return null;
	}
	
	/**
	 * Convenience method. Load an <i>EMF</i> generator model using an <b>java.net.URI</b>.
	 * 
	 * @param uri
	 *            The generator model's URI.
	 * @return The loaded generator model.
	 * @throws IOException
	 *             Thrown, iff the model cannot be loaded.
	 */
	public static GenModel loadGenModel(java.net.URI uri) throws IOException {
		return loadGenModel(URI.createURI(uri.toString()));
	}

	/**
	 * Use an <i>EMF</i> generator model to generate an <i>Ecore</i> metamodel's
	 * implementation. Iff implementations for model constructs already exist,
	 * <i>JMerge</i> is used to combine the existing classes with the newly
	 * generated ones.
	 * 
	 * @param genModel
	 *            The generator model to use for generation.
	 * @param generateEditCode
	 *            flag to activate edit code generation
	 */
	public static Diagnostic generateGenModelCode(GenModel genModel, boolean generateEditCode) {		
		genModel.setCanGenerate(true);	
		Generator generator = new Generator();
		Generator.Options options = generator.getOptions();
		options.mergeRulesURI = "platform:/plugin/org.eclipse.emf.codegen.ecore/templates/emf-merge.xml";
		generator.setInput(genModel);
		Diagnostic diagnostic = generator.generate(
				genModel,
				GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
				BasicMonitor.toMonitor(new NullProgressMonitor()));
		System.out.println("Generated EMF Model Code");
		if (generateEditCode && diagnostic.getCode() == Diagnostic.OK) {
			diagnostic = generator.generate(
					genModel,
					GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE,
					BasicMonitor.toMonitor(new NullProgressMonitor()));	
		System.out.println("Generated EMF Edit Code");
		}
		return 	diagnostic;
	}

	/**
	 * Completely read an {@link java.io.Reader text input stream}, store its
	 * content into a {@link StringBuilder buffer},
	 * {@link java.io.InputStream#close() close} the stream and return the
	 * buffer.
	 * 
	 * @param input
	 *            The text input stream to read.
	 * @return A buffer filled with the stream's content.
	 * @throws IOException
	 *             Thrown, iff the stream cannot be read.
	 */
	public static StringBuilder textStream2String(InputStream input)
			throws IOException {
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			char[] buf = new char[65536];
			int read = 0;
			while ((read = in.read(buf, 0, 65536)) > 0) {
				sb.append(buf, 0, read);
			}
			return sb;
		} finally {
			input.close();
		}
	}

	/**
	 * Helper method that saves a String to the given workspace file by
	 * replacing its contents. Note that the file is assumed to already exist in
	 * the workspace.
	 * 
	 * @throws CoreException
	 *             If the file does not exist.
	 * 
	 */
	public static void save(String contents, IFile file) throws CoreException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(outStream);
		try {
			writer.print(contents);
			writer.flush();
			if(file.exists()){
				file.setContents(new ByteArrayInputStream(outStream.toByteArray()),
						IFile.NONE, null);				
			}
			else{
				file.create(new ByteArrayInputStream(outStream.toByteArray()),IFile.NONE, null);
			}
		} finally {
			writer.close();
		}
	}

	/**
	 * Helper method that creates a workspace file handle from a given absolute
	 * java.net.URI pointing to a folder in the workspace and a file name.
	 * 
	 * The file object may be only a handle if the file does not yet exist in
	 * the workspace.
	 * 
	 * @param folder
	 *            The absolute URI pointing to a folder in the current
	 *            workspace.
	 * @param file
	 *            The file name.
	 * @return A file handle.
	 * @throws JastEMFException
	 *             If the Eclipse ResourcesPlugin can only map none or more than
	 *             one files to the URI, an exception is thrown.
	 */
	public static IFile getFile(java.net.URI folder, String file)
			throws JastEMFException {
		final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile[] files = root.findFilesForLocationURI(java.net.URI.create(folder
				.toString()
				+ "/" + file));
		if (files.length != 1) {
			throw new JastEMFException("File [" + file + "] in ["
					+ folder.toString() + "] has " + files.length
					+ " correspondences in Workspace.");
		}
		return files[0];
	}

}
