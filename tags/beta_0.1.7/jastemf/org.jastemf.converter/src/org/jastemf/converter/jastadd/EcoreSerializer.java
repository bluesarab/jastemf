package org.jastemf.converter.jastadd;


import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.jastemf.JastEMFException;

public class EcoreSerializer {
	
	public static void serializeEPackages(Collection<EPackage> ePackages, URI locationURI, boolean allInOne, String rootPackageName) throws JastEMFException{
		ResourceSet rs = new ResourceSetImpl();
			
		if(allInOne){
			Resource resource = rs.getResource(locationURI,true);
			if(resource==null){	
				throw new JastEMFException("Option 'allInOne' requires an URI pointing to a registered resource type.");
			}
			EPackage rootPackage = EcoreFactory.eINSTANCE.createEPackage();
			rootPackage.setName(rootPackageName);
			rootPackage.setNsPrefix(rootPackageName);
			rootPackage.setNsURI(locationURI.toString());
			resource.getContents().clear();
			resource.getContents().add(rootPackage);
			for(EPackage currentPackage:ePackages){
				rootPackage.getESubpackages().add(currentPackage);
			}
			try {
				resource.save(null);
			} catch (IOException e) {
				throw new JastEMFException("JastEMF was unable to serialize EPackage.",e);
			}
		}
		else{
			//first ensure that all packages have a resource
			for(EPackage currentPackage:ePackages){
				Resource resource = rs.createResource(locationURI.appendSegment(currentPackage.getName()+".ecore"));
				resource.getContents().clear();
				resource.getContents().add(currentPackage);
			}
			//now serialize each of them
			for(EPackage currentPackage:ePackages){			
				try {
					currentPackage.eResource().save(null);

				} catch (IOException e) {
					throw new JastEMFException(
							"JastEMF was unable to serialize EPackage to "
									+ currentPackage.eResource().getURI()
											.toString() + ".", e);
				}
			}

		}
		
	
	}
	
}
