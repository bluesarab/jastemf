package org.jastemf.converter.ant;

import java.io.File;
import java.util.HashMap;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.mwe.core.resources.OsgiResourceLoader;

import org.jastemf.JastEMFException;
import org.jastemf.util.IOSupport;
import org.jastemf.util.WorkflowManager;

public class Ecore2ASTTask extends Task {
	
	private File modelFile = null;
	private File outputDirectory = null;
	private String fileEncoding ="UTF-8"; 
	
	public void execute() throws BuildException {
		if(modelFile==null)
			throw new BuildException("The parameter 'modelFile' needs to be specified.");
		if(outputDirectory==null)
			throw new BuildException("The parameter 'outputDirectory' needs to be specified.");
		
		/* Initialize integration resources */
		URI modelURI = IOSupport.createFileURI(
				modelFile.getAbsolutePath());
		URI outputDirectoryURI = IOSupport.createFileURI(outputDirectory.getAbsolutePath());
		
		
		HashMap<String,String> properties = new HashMap<String,String>();
		properties.put("modelFile",modelURI.toString());
		properties.put("outputDirectory",outputDirectoryURI.path());
		properties.put("fileEncoding",fileEncoding);
		
		OsgiResourceLoader resourceLoader = 
		    new OsgiResourceLoader("org.jastemf.converter",this.getClass().getClassLoader());
		
		try {
			WorkflowManager.executeWorkflow("org/jastemf/converter/ecore/ASTGenerator.oaw", properties, resourceLoader);
		} catch (JastEMFException e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}
	
	public void setModelFile(File modelFile){
		this.modelFile = modelFile;
	}
	
	
	public void setOutputDirectory(File outputDirectory){
		this.outputDirectory = outputDirectory;
	}
	
	public void setFileEncoding(String fileEncoding){
		this.fileEncoding  = fileEncoding;
	}
}
