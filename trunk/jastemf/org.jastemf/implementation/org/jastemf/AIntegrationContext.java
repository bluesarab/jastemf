/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf;

import java.net.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

/**
 * Abstract integration context, that supports default implementations for all
 * access methods derivable from the integration's {@link
 * IIntegrationContext#genmodel() generator model}, {@link
 * IIntegrationContext#astpackage() AST class package} and {@link
 * IIntegrationContext#outpackage() integration artifacts package}.
 * @author C. Bürger
 * @author Sven Karol
 */
public abstract class AIntegrationContext implements IIntegrationContext {
	
	private IProject project = null;
	private IJavaProject jProject = null;
	
	/**
	 * See {@link IIntegrationContext#srcfolder()}.
	 */
	public URI srcfolder() {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		
		IResource modelDirectory = workspace.getRoot().findMember(genmodel().getModelDirectory());
		return URI.create(modelDirectory.getLocationURI().toString());
	}
	
	/**
	 * See {@link IIntegrationContext#packagefolder(String)}.
	 */
	public URI packagefolder(String Package){
		URI srcURI = srcfolder();
		if (Package == null || Package.isEmpty())
			return srcURI;
		String path = Package.replace('.', '/');
		return URI.create(srcURI.toString() + "/" + path);
	}
	
	/**
	 * See {@link IIntegrationContext#interfacefolder(GenPackage)}.
	 */ 
	public URI interfacefolder(GenPackage genPackage){
		String interfacePackageName = genPackage.getInterfacePackageName();
		return packagefolder(interfacePackageName);
	}
	
	/**
	 * See {@link IIntegrationContext#classfolder(GenPackage)}.
	 */ 
	public URI classfolder(GenPackage genPackage){
		String interfacePackageName = genPackage.getClassPackageName();
		return packagefolder(interfacePackageName);
	}
	
	/**
	 * See {@link IIntegrationContext#astfolder()}.
	 */ 
	public URI astfolder(){
		return packagefolder(astpackage());
	}
	
	public IProject workspaceProject(){
		if(project==null)
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(genmodel().getModelDirectory().split("/")[1]);
		return project;
	}
	
	public IJavaProject javaProject(){
		if(jProject==null)
			jProject = JavaCore.create(workspaceProject());
		return jProject;
	}
	
	public IPackageFragment astPackageFragment(){
		return packageFragment(astpackage());
	}
	
	public IPackageFragment packageFragment(String packageName){
		try {
			return (IPackageFragment)javaProject().findElement(new Path(packageName.replace('.','/')));
		} catch (JavaModelException e) {
			return null;
		}
	}
	
}
