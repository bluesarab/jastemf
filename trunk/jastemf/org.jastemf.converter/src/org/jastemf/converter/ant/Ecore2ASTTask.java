package org.jastemf.converter.ant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.jastemf.JastEMFException;
import org.jastemf.converter.ecore.Ecore2JastAdd;
import org.jastemf.converter.ecore.JastAddSpec;
import org.jastemf.converter.jastadd.EcoreSerializer;

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
		URI modelURI = URI.createFileURI(
				modelFile.getAbsolutePath());
		
		try {
			GenModel model = EcoreSerializer.loadGenModelRoot(modelURI);
			Collection<JastAddSpec> specs = Ecore2JastAdd.convertPackages2AST(model);
			if(!outputDirectory.exists())
				outputDirectory.mkdirs();
			for(JastAddSpec spec:specs){
				File current = new File(outputDirectory,spec.getResourceName());
				OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(current),fileEncoding);
				out.write(spec.getContents());
				out.flush();
				out.close();
			}
			
		} catch (JastEMFException e) {
			e.printStackTrace();
			throw new BuildException(e);
		} catch (IOException e) {
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
