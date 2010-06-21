package statemachine.siple.codegeneration;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.xpand2.XpandExecutionContextImpl;
import org.eclipse.xpand2.XpandFacade;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xpand2.output.OutputImpl;
import org.eclipse.xtend.expression.TypeSystem;
import org.eclipse.xtend.typesystem.MetaModel;
import org.eclipse.xtend.typesystem.Type;
import org.eclipse.xtend.typesystem.emf.EmfRegistryMetaModel;

import statemachine.StateMachine;
import statemachine.impl.StateMachineImpl;

public class GenerateSipleCodeAction implements IObjectActionDelegate {

	private ISelection selection;

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}

	@Override
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
		org.eclipse.emf.ecore.resource.Resource stmResource = 
			rs.getResource(URI.createURI(file.getLocationURI().toString()), true);
		EObject root = stmResource.getContents().get(0);
		if (root != null == root instanceof StateMachine) {
			StateMachine machine = (StateMachine) root;
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

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
