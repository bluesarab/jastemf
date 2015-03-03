package org.jastemf.converter.jastadd;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.jastemf.JastEMFException;
import org.jastemf.util.IOSupport;

public class EcoreSerializer {

	
	/**
	 * Load an EPackage root from a given Ecore file. The EPackage may contain
	 * arbitrarily nested sub packages. 
	 * 
	 * @param locationURI The location of the .ecore file.
	 * @return The EPackage root.
	 * @throws JastEMFException
	 */
	public static EPackage loadEPackageRoot(URI locationURI)
			throws JastEMFException {
		EPackage pck = null;
		try {
			EObject root = IOSupport.loadModel(locationURI);
			if (root == null)
				throw new JastEMFException(
						"Package was null for unknown reasons.");
			if (!(root instanceof EPackage)) {
				throw new JastEMFException("Expected EPackage but found "
						+ root.eClass().getName() + ".");
			}
			pck = (EPackage) root;
		} catch (IOException e) {
			throw new JastEMFException("JastEMF was unable to load EPackage.",
					e);
		}
		return pck;
	}
	
	/**
	 * Load a GenModel root from a given GenModel file. The GenModel may contain
	 * arbitrarily nested sub packages. 
	 * 
	 * @param locationURI The location of the .gemodel file.
	 * @return The GenModel root.
	 * @throws JastEMFException
	 */
	public static GenModel loadGenModelRoot(URI locationURI)
			throws JastEMFException {
		GenModel model = null;
		try {
			EObject root = IOSupport.loadModel(locationURI);
			if (root == null)
				throw new JastEMFException(
						"Package was null for unknown reasons.");
			if (!(root instanceof GenModel)) {
				throw new JastEMFException("Expected GenModel but found "
						+ root.eClass().getName() + ".");
			}
			model = (GenModel) root;
		} catch (IOException e) {
			throw new JastEMFException("JastEMF was unable to load GenModel.",
					e);
		}
		return model;
	}
	
	/**
	 * Stores given EPackage objects in one or more Ecore files.
	 * 
	 * @param ePackages
	 *            The list of packages to be serialized.
	 * @param locationURI
	 *            The target directory to store the packages.
	 * @param allInOne
	 *            If true, all packages are stored in the same Ecore file with a
	 *            common root package.
	 * @param rootPackageName
	 *            The name of the root package.
	 * @throws JastEMFException
	 */
	public static void serializeEPackages(Collection<EPackage> ePackages,
			URI locationURI, boolean allInOne, String rootPackageName)
			throws JastEMFException {
		ResourceSet rs = new ResourceSetImpl();

		if (allInOne) {
			Resource resource = rs.getResource(locationURI, true);
			if (resource == null) {
				throw new JastEMFException(
						"Option 'allInOne' requires an URI pointing to a registered resource type.");
			}
			EPackage rootPackage = EcoreFactory.eINSTANCE.createEPackage();
			rootPackage.setName(rootPackageName);
			rootPackage.setNsPrefix(rootPackageName);
			rootPackage.setNsURI(locationURI.toString());
			resource.getContents().clear();
			resource.getContents().add(rootPackage);
			for (EPackage currentPackage : ePackages) {
				rootPackage.getESubpackages().add(currentPackage);
			}
			try {
				resource.save(null);
			} catch (IOException e) {
				throw new JastEMFException(
						"JastEMF was unable to serialize EPackage.", e);
			}
		} else {
			// first ensure that all packages have a resource
			for (EPackage currentPackage : ePackages) {
				Resource resource = rs.createResource(locationURI
						.appendSegment(currentPackage.getName() + ".ecore"));
				resource.getContents().clear();
				resource.getContents().add(currentPackage);
			}
			// now serialize each of them
			for (EPackage currentPackage : ePackages) {
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
