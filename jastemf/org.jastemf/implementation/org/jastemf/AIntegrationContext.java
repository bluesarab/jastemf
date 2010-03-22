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
import org.eclipse.emf.codegen.ecore.genmodel.*;

/**
 * Abstract integration context, that supports default implementations for all
 * access methods derivable from the integration's {@link
 * IIntegrationContext#genmodel() generator model}, {@link
 * IIntegrationContext#astpackage() AST class package} and {@link
 * IIntegrationContext#outpackage() integration artifacts package}.
 * @author C. Bürger
 */
public abstract class AIntegrationContext implements IIntegrationContext {
	/**
	 * See {@link IIntegrationContext#srcfolder()}.
	 */
	public URI srcfolder() {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final URI workspaceURI = workspace.getRoot().getLocationURI();
		return URI.create(workspaceURI.toString() +
				genmodel().getModelDirectory());
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
}
