/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package siple.emftext.gui;

import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.ui.*;

import siple.semantics.interfaces.*;

import siple.emftext.support.*;

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
		ResourceSet rs = new ResourceSetImpl();
		org.eclipse.emf.ecore.resource.Resource stmResource = 
			rs.getResource(URI.createURI(file.getLocationURI().toString()), true);
		
		ParserHelper parser = new ParserHelper();
		try {
			java.io.InputStream input = file.getContents();
			try {
				CompilationUnit prog = parser.parseProgram(stmResource, input);
				if (prog != null)
					prog.Interpret();
				else System.out.println("Parse Error!");
			} finally {
				input.close();
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
