/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
package statemachine.siple.codegeneration;

import java.io.*;
import java.util.*;
import javax.annotation.*;

import org.eclipse.core.resources.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.ui.*;
import org.eclipse.xpand2.*;
import org.eclipse.xtend.expression.*;
import org.eclipse.xtend.typesystem.*;
import org.eclipse.xtend.typesystem.emf.*;

import statemachine.*;
import statemachine.impl.*;

public class GenerateSipleCodeAction implements IObjectActionDelegate {
	private ISelection selection;
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {}
	
	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> i = ((IStructuredSelection) selection).iterator(); i
					.hasNext();) {
				Object o = i.next();
				if (o instanceof IFile) {
					IFile file = (IFile) o;
					if (file.getFileExtension().startsWith("statemachine")) {
						process(file);
					}
				}
			}
		}
	}

	private void process(IFile file) {
		System.out.println(file);
		ResourceSet rs = new ResourceSetImpl();
		org.eclipse.emf.ecore.resource.Resource stmResource = rs.getResource(
				URI.createURI(file.getLocationURI().toString()), true);
		EObject root = stmResource.getContents().get(0);
		if (root != null == root instanceof StateMachineImpl) {
			StateMachineImpl machine = (StateMachineImpl) root;
			OutputImpl output = new OutputImpl();
			String outputContainer = file.getParent().getLocation()
					.toPortableString();
			Outlet outlet = new Outlet(outputContainer);
			String targetFile = file.getName() + ".siple";
			outlet.setOverwrite(true);
			output.addOutlet(outlet);
			
			// create execution context
			XpandExecutionContextImpl execCtx = new XpandExecutionContextImpl(
					output, null);
			MetaModel mm = new EmfRegistryMetaModel();
			
			// generate
			execCtx.registerMetaModel(mm);
			
			XpandFacade facade = XpandFacade.create(execCtx);
			String templatePath = "statemachine::siple::codegeneration::SipleCodegen::main";
			
			facade.evaluate(templatePath, machine, targetFile);
			System.out.println(targetFile);
		}
	}
	
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
