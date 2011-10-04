/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package org.jastemf.siple.ui;

import java.util.*;


import org.eclipse.core.resources.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.ui.*;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import org.jastemf.siple.SipleStandaloneSetup;
import org.jastemf.siple.interfaces.*;
import org.jastemf.siple.semantics.State;

import com.google.inject.Injector;

//import siple.emftext.support.*;

public class InterpretSipleProgramAction implements IObjectActionDelegate {
	private ISelection selection;
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {}
	
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> i = ((IStructuredSelection) selection).iterator(); i
					.hasNext();) {
				Object o = i.next();
				if (o instanceof IFile) {
					IFile file = (IFile) o;
					if (file.getFileExtension().startsWith("siple")) {
						process(file);
					}
				}
			}
		}

	}

	private void process(IFile file) {		
		Injector injector = new
				SipleStandaloneSetup().createInjectorAndDoEMFRegistration();
		ResourceSet set = injector.getInstance(XtextResourceSet.class);
		Resource res = set.getResource(URI.createURI(file.getLocationURI().toString()),true);
		IParseResult result = ((XtextResource)res).getParseResult();
		
		try {
			CompilationUnit prog = (CompilationUnit)result.getRootASTElement();
			if (prog != null){
				State vm = prog.Interpret();
				System.out.println(vm.stdOut);				
			}
			else System.out.println("Parse Error!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
