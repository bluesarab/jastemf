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

final public class IOSupport {
	public static EObject loadModel(URI uri) throws IOException {
		ResourceSet rs = new ResourceSetImpl();
		Resource r = rs.getResource(uri, true);
		r.load(null);
		if (r.getContents().size() > 0) {
			return r.getContents().get(0);
		}
		return null;
	}
	
	public static GenModel loadGenModel(URI uri) throws IOException {
		EObject eo = loadModel(uri);
		if (eo != null && eo instanceof GenModel) {
			return (GenModel)eo;
		}
		return null;
	}
	
	/**
	 * Use an <i>EMF</i> generator model to generate an <i>ECore</i>
	 * metamodel's implementation. Iff implementations for model constructs
	 * already exist, <i>JMerge</i> is used to combine the existing classes
	 * with the newly generated ones.
	 * @param genModel The generator model to use for generation.
	 */
	public static Diagnostic generateGenModelCode(GenModel genModel) {		
		genModel.setCanGenerate(true);	
		Generator generator = new Generator();
		Generator.Options options = generator.getOptions();
		options.mergeRulesURI = "platform:/plugin/org.eclipse.emf.codegen.ecore/templates/emf-merge.xml";
		generator.setInput(genModel);
		return generator.generate(
				genModel,
				GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
				BasicMonitor.toMonitor(new NullProgressMonitor()));
	}
	
	/**
	 * Create a file URI if you want to access Ecore models
	 * relatively to your current working directory.  
	 */
	public static URI createFileURI(String path) {
		return URI.createFileURI(path);	
	}
	
	/**
	 * Create a platform URI if you want to access Ecore models in 
	 * deployed plugins / OSGI bundles.  
	 */
	public static URI createPlatformURI(String path) {
		return URI.createPlatformPluginURI(path, false);
	}
	
	/**
	 * Create a resource URI if you want to access Ecore models in 
	 * a project in your workspace.  
	 */
	public static URI createProjectURI(String path) {
		return URI.createPlatformResourceURI(path, false);
	}
	
	/**
	 * Completely read an {@link java.io.Reader text input stream}, store
	 * its content into a {@link StringBuilder buffer}, {@link
	 * java.io.InputStream#close() close} the stream and return the buffer.
	 * @param input The text input stream to read.
	 * @return A buffer filled with the stream's content.
	 * @throws IOException Thrown, iff the stream cannot be read.
	 */
	public static StringBuilder textStream2String (InputStream input)
	throws IOException {
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(input));
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
	 * Compute a string representation of the current date w.r.t. the
	 * default time zone and locale.
	 * @return The current date.
	 */
	public static String timeStamp() {
		Calendar calendar = Calendar.getInstance();
		return new SimpleDateFormat().format(calendar.getTime());
	}

	/**
	 * Saves a String to the given workspace file.
	 * 
	 */
	public static void save(String contents, IFile file) throws CoreException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(outStream);
		try {
			writer.print(contents);
			writer.flush();
			file.setContents(new ByteArrayInputStream(outStream.toByteArray()),
					IFile.NONE, null);
		} finally {writer.close();}
	}
	
	/**
	 * Creates a workspace file handle from a given absolute java.net.URI relative to the workspace.
	 * The file may be a handle only if the file does not exist yet.
	 * @param folder The absolute URI pointing to a folder in the current workspace. 
	 * @param file The file name.
	 * @return A file handle.
	 * @throws JastEMFException If no or more than one files were mapped to the URI,
	 * 		an exception is thrown.
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
