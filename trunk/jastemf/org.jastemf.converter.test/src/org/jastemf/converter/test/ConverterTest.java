package org.jastemf.converter.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;


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

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreValidator;
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
		File modelDir = new File("./specifications/jastadd2ecore-test/model");
		File semanticsDir = new File("./specifications/jastadd2ecore-test/semantics");
		URI testURI = URI.createURI(testDir.toURI().toString());
		java.util.List<URI> resources = new LinkedList<URI>();
		
		File[] modelFiles = modelDir.listFiles();
		for(int i=0;i<modelFiles.length;i++){
			if(modelFiles[i].isFile()){
				System.out.println("Adding "+modelFiles[i].toString()+" ...");
				resources.add(URI.createURI(modelFiles[i].toURI().toString()));
			}
		}
		
		File[] semanticsFiles = semanticsDir.listFiles();
		for(int i=0;i<semanticsFiles.length;i++){
			if(semanticsFiles[i].isFile()){
				System.out.println("Adding "+semanticsFiles[i].toString()+" ...");
				resources.add(URI.createURI(semanticsFiles[i].toURI().toString()));
			}
		}
		
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
		
		BasicDiagnostic diagnostics = new BasicDiagnostic();
		boolean valid = true;
		
		for(EPackage ePackage:ePackages){
			Iterator<EObject> it = ePackage.eAllContents();
			while(it.hasNext()){
				EObject eo = it.next();
			    Map<Object, Object> context = new HashMap<Object, Object>();
			    valid &= Diagnostician.INSTANCE.validate(eo, diagnostics, context);
			}
		}
	
		Assert.assertTrue("Generated model is not valid.",diagnostics.getChildren().size()<=2);
		
	}

}
