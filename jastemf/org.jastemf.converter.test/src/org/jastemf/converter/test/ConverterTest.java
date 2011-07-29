package org.jastemf.converter.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.jastemf.JastEMFException;
import org.jastemf.converter.jastadd.EcoreSerializer;
import org.jastemf.converter.jastadd.GrammarLoader;
import org.jastemf.converter.jastadd.JastAdd2Ecore;

import ast.AST.ASTDecl;
import ast.AST.Grammar;
import ast.AST.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.EcoreFactory;


public class ConverterTest {
	
	
	@Before
	public void setUp(){
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
	               "ecore", new  XMIResourceFactoryImpl()
	           );
	}
	
	@Test
	public void test() throws JastEMFException {
		File testDir = new File("./specifications/jastadd2ecore-test");
		URI testURI = URI.createURI(testDir.toURI().toString());
		java.util.List<URI> resources = new LinkedList<URI>();
		
		resources.add(URI.createURI("model/picojava.ast").resolve(testURI));
		resources.add(URI.createURI("semantics/ErrorCheck.jadd").resolve(testURI));
		resources.add(URI.createURI("semantics/NameResolution.jrag").resolve(testURI));
		resources.add(URI.createURI("semantics/NullObjects.jrag").resolve(testURI));
		resources.add(URI.createURI("semantics/PredefinedTypes.jrag").resolve(testURI));
		resources.add(URI.createURI("semantics/TypeAnalysis.jrag").resolve(testURI));
		
		Grammar g = GrammarLoader.loadJastAddGrammar(resources);
		
		Assert.assertNotNull(g);
		
		List l = g.getTypeDeclList();
		for(int i=0;i<l.getNumChild();i++){
			
			if(l.getChild(i) instanceof ASTDecl){
				ASTDecl astDecl = (ASTDecl) l.getChild(i);
			}
		}
		
		Collection<EPackage> ePackages = new JastAdd2Ecore().convertGrammar(g,true);
		
		Assert.assertNotNull(ePackages);
		Assert.assertTrue(!ePackages.isEmpty());
		
		URI resultURI = URI.createURI("result").resolve(testURI);
		
		EcoreSerializer.serializeEPackages(ePackages, resultURI, false, null);
		
	}

}
