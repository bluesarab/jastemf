package statemachine.siple;

import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.ui.*;

import siple.semantics.*;

public class InterpretSipleProgramAction implements IObjectActionDelegate {

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
		EObject root = stmResource.getContents().get(0);
		
		ParserHelper parser = new ParserHelper();
		CompilationUnit prog = parser.parseProgram(stmResource, file.getContents());
		try {
			if (prog != null)
				prog.Interpret();
			else System.out.println("Parse Error!");
		} catch (InterpretationException e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
