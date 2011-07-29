package org.jastemf.converter.ant;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.jastemf.JastEMFException;
import org.jastemf.converter.jastadd.EcoreSerializer;
import org.jastemf.converter.jastadd.GrammarLoader;
import org.jastemf.converter.jastadd.JastAdd2Ecore;

import ast.AST.Grammar;

public class JastAdd2EcoreTask extends Task {

	private Collection<FileSet> jastAddSpecs = new LinkedList<FileSet>();
	private boolean oneFilePerPackage = false;
	private boolean ignoreASTTypes = true;
	private File target = null;
	private String rootPackageName = null;
	
	
	public FileSet createFileSet(){
		FileSet fileSet = new FileSet();
		jastAddSpecs.add(fileSet);
		return fileSet;
	}
	
	public void execute(){
		if(jastAddSpecs.isEmpty())
			throw new BuildException("Please provide some JastAdd source files using nested file sets.");
		if(target==null)
			throw new BuildException("The parameter 'target' needs to be specified.");
		if(oneFilePerPackage){
			if(!target.isDirectory())
				throw new BuildException("The parameter 'target' needs to be a directory since 'oneFilePerPackage' is true.");
		}
		else{
			if(!target.isFile()){
				throw new BuildException("The parameter 'target' needs to point to a file since 'oneFilePerPackage' is false.");
			}
		}
		
		if(rootPackageName==null)
			rootPackageName = "root";
		
		List<URI> specURIs = new LinkedList<URI>();
		
		for(FileSet fileSet:jastAddSpecs){
			String[] files = fileSet.getDirectoryScanner().getIncludedFiles();
			URI dirURI = URI.createURI(fileSet.getDir().toURI().toString());
			for(int i=0;i<files.length;i++){
				URI fileURI = URI.createFileURI(files[i]);
				if("ast".equals(fileURI.fileExtension())||
					"jrag".equals(fileURI.fileExtension())||
					"jadd".equals(fileURI.fileExtension()))
					specURIs.add(fileURI.resolve(dirURI));
			}
		}
		
		if(specURIs.isEmpty()){
			throw new BuildException("Please provide some JastAdd source files (*.ast,*.jadd,*.jrag) using nested file sets.");
		}
		
		Grammar g = GrammarLoader.loadJastAddGrammar(specURIs);
		
		Collection<EPackage> ePackages = null;
		
		try {
			ePackages = new JastAdd2Ecore().convertGrammar(g,ignoreASTTypes);
		} catch (JastEMFException e) {
			throw new BuildException("An Error occured during JastAdd grammar to Ecore conversion.",e);
		}
		
		URI resultURI = URI.createURI(target.toURI().toString());
		
		try {
			EcoreSerializer.serializeEPackages(ePackages, resultURI, !oneFilePerPackage,rootPackageName);
		} catch (JastEMFException e) {
			throw new BuildException("An Error occured during ecore model serialization.",e);

		}

	}

	public void setOneFilePerPackage(boolean oneFilePerPackage) {
		this.oneFilePerPackage = oneFilePerPackage;
	}

	public boolean isIgnoreASTTypes() {
		return ignoreASTTypes;
	}

	public void setIgnoreASTTypes(boolean ignoreASTTypes) {
		this.ignoreASTTypes = ignoreASTTypes;
	}

	public File getTarget() {
		return target;
	}

	public void setTarget(File target) {
		this.target = target;
	}

	public String getRootPackageName() {
		return rootPackageName;
	}

	public void setRootPackageName(String rootPackageName) {
		this.rootPackageName = rootPackageName;
	}
	
}
