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

import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.selectors.*;

import jastadd.JastAddTask;

import org.jastemf.util.*;
import org.jastemf.refactorings.*;

/**
 * <i>JastEMF's</i> main class to {@link
 * #performIntegration(IIntegrationContext, JastAddTask) integrate} a
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
			IIntegrationContext context, JastAddTask jastAddTask)
	throws JastEMFException {
		refreshIntegrationArtifacts(context);
		
		// Generate adaptation aspects
		WorkflowManager.executeWorkflow(context,
				"org/jastemf/aspects/workflow.oaw");
		
		// Generate refactoring scripts and artifacts
		WorkflowManager.executeWorkflow(context,
				"org/jastemf/refactorings/workflow.oaw");
		
		// Generate JastAdd evaluator
		jastAddTask.setPackage(context.astpackage());
		jastAddTask.setOutdir(context.srcfolder().getPath());
		FileSet jastemfAspects = new FileSet();
		jastemfAspects.setDir(new File(
				context.packagefolder(context.outpackage()).getPath()));
		FilenameSelector selector = new FilenameSelector();
		selector.setName("*.jrag");
		jastemfAspects.add(selector);
		jastAddTask.addConfiguredFileSet(jastemfAspects);
		jastAddTask.execute();
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
