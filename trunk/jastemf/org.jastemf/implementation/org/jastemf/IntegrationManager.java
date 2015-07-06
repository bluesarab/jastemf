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
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.jastadd.Configuration;
import org.jastadd.JastAdd;
import org.jastadd.ast.AST.Grammar;
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
		
		// Generate JastAdd evaluator TODO: use JastAdd2 configuration!
		ArrayList<String> args = new ArrayList<String>(32);
		//int argCount = context.useProgrammaticRefactorings()?5:6; 
		args.add("--rewrite");
		args.add("--package=" + context.astpackage());
		args.add("--emf");
		args.add("--List=ASTList");
		args.add("--Opt=ASTOpt");
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
		
		Configuration conf = new GenModelConfiguration(args.toArray(new String[0]),System.err,context.genmodel());
		
		JastAdd jastAdd = new JastAdd(conf);
		jastAdd.compile(System.out,System.err);
		refreshIntegrationArtifacts(context);
		
		ProgrammaticRefactorings.renameASTClasses(context);
		
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
	
	private static class GenModelConfiguration extends Configuration {
		
		private GenModel genModel = null;
		
		public GenModelConfiguration(String[] args, PrintStream out, GenModel genModel){
			super(args,out);
			this.genModel = genModel;
		}
		
		public Grammar buildRoot(){
			Grammar grammar = super.buildRoot();
			grammar.setGeneratorModel(genModel);
			return grammar;
		}
	}
}
