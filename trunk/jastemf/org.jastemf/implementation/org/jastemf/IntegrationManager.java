/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf;

import java.io.*;
import java.net.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;

import jastadd.JastAdd;

import org.jastemf.util.*;
import org.jastemf.refactorings.*;

/**
 * <i>JastEMF's</i> main class to {@link
 * #performIntegration(IIntegrationContext) integrate} a
 * <i>JastAdd</i> evaluator and an <i>EMF</i> metamodel implementation.
 * @author C. Bürger
 */
final public class IntegrationManager {
	/**
	 * Perform the integration of a project represented by an {@link
	 * IIntegrationContext integration context}.
	 * @param context Information about the project to integrate, which are
	 * required by the integration process.
	 */
	public synchronized static void performIntegration(
			IIntegrationContext context)
	throws JastEMFException {
		refreshIntegrationArtifacts(context);
		
		// Generate adaptation aspects
		WorkflowManager.executeWorkflow(context,
				"org/jastemf/aspects/workflow.oaw");
		
		// Generate refactoring scripts and artifacts
		WorkflowManager.executeWorkflow(context,
				"org/jastemf/refactorings/workflow.oaw");
		
		// Generate JastAdd evaluator
		String[] args = new String[5 + context.jragspecs().size()];
		args[0] = "--rewrite";
		args[1] = "--package=" + context.astpackage();
		args[2] = "--o=" + new File(context.srcfolder()).getAbsolutePath();
		int i = 3;
		for (String spec:context.jragspecs())
			args[i++] = spec;
		args[i++] = new File(
				context.packagefolder(
						context.outpackage())).getAbsolutePath() +
			File.separator + "RepositoryAdaptations.jrag";
		args[i++] = new File(
				context.packagefolder(
						context.outpackage())).getAbsolutePath() +
			File.separator + "Refactorings.jrag";
		JastAdd.main(args);
		refreshIntegrationArtifacts(context);
		
		// Execute refactorings
		RefactoringManager.applyRefactoringScript(URI.create(
				context.packagefolder(context.outpackage()) +
				"/Refactorings.xml"));
		RefactoringManager.performASTNodeAdaptations(context);
		RefactoringManager.performASTNodeStateAdaptations(context);
		RefactoringManager.performASTListAdaptations(context);
		RefactoringManager.performOptAdaptations(context);
		RefactoringManager.performASTClassesAdaptations(context);
		
		// Generate EMF model implementation
		IOSupport.generateGenModelCode(context.genmodel());
		RefactoringManager.beautifyGenPackages(context);
		
		refreshIntegrationArtifacts(context);
	}
	
	private static void refreshIntegrationArtifacts(
			IIntegrationContext context) throws JastEMFException {
		try {
			ResourcesPlugin.getWorkspace().getRoot().getFolder(
					new Path(context.genmodel().getModelDirectory())).
					refreshLocal(
							IResource.DEPTH_INFINITE,
							new NullProgressMonitor());
		} catch (CoreException e) {
			throw new JastEMFException(e);
		}
	}
}
