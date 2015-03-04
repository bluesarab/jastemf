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
import java.util.ArrayList;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.jastadd.JastAdd;
import org.jastemf.util.*;
import org.jastemf.refactorings.*;

/**
 * <i>JastEMF's</i> main class to {@link
 * #performIntegration(IIntegrationContext) integrate} a
 * <i>JastAdd</i> evaluator and an <i>EMF</i> metamodel implementation.
 * @author C. Bürger
 * @author Sven Karol
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
		
//		// Generate adaptation aspects
//		WorkflowManager.executeWorkflow(context,
//				"org/jastemf/aspects/workflow.oaw");
//		
//		if(!context.useProgrammaticRefactorings()){
//			// Generate refactoring scripts and artifacts
//			WorkflowManager.executeWorkflow(context,
//					"org/jastemf/refactorings/workflow.oaw");
//		}

		// Generate JastAdd evaluator TODO: use JastAdd2 configuration!
		ArrayList<String> args = new ArrayList<String>(32);
		//int argCount = context.useProgrammaticRefactorings()?5:6; 
		args.add("--rewrite");
		args.add("--package=" + context.astpackage());
		args.add("--emf");
		args.add("--List=ASTList");
		args.add("--Opt=ASTOpt");
		args.add("--ASTNodeSuper=EObject");
		args.add("--o=" + new File(context.srcfolder()).getAbsolutePath());
		for (String spec:context.jragspecs())
			args.add(spec);
		/*args.add(new File(
				context.packagefolder(
						context.outpackage())).getAbsolutePath() +
			File.separator + "RepositoryAdaptations.jrag");	*/		
		
		if(!context.useProgrammaticRefactorings()){
			args.add(new File(
				context.packagefolder(
						context.outpackage())).getAbsolutePath() +
			File.separator + "Refactorings.jrag");
		}
		JastAdd.main(args.toArray(new String[0]));
		refreshIntegrationArtifacts(context);
		
		// Execute refactorings
		if(context.useProgrammaticRefactorings()){
			ProgrammaticRefactorings.renameASTClasses(context);			
		}
		else{
			RefactoringManager.applyRefactoringScript(URI.create(
					context.packagefolder(context.outpackage()) +
					"/Refactorings.xml"));			
		}
		RefactoringManager.performASTNodeAdaptations(context);
		RefactoringManager.performASTNodeStateAdaptations(context);
		RefactoringManager.performASTListAdaptations(context);
		RefactoringManager.performOptAdaptations(context);
		RefactoringManager.performASTClassesAdaptations(context);
		
		// Generate EMF model implementation
		IOSupport.generateGenModelCode(context.genmodel(), context.generateEditCode());
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
