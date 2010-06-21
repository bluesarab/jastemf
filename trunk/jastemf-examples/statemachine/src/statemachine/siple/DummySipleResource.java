package statemachine.siple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;

import siple.semantics.resource.siple.ISipleContextDependentURIFragmentFactory;
import siple.semantics.resource.siple.ISipleLocationMap;
import siple.semantics.resource.siple.ISipleMetaInformation;
import siple.semantics.resource.siple.ISipleProblem;
import siple.semantics.resource.siple.ISipleTextResource;
import siple.semantics.resource.siple.mopp.SipleResource;

public class DummySipleResource implements ISipleTextResource {

	private List<String> problemList = new ArrayList<String>();

	@Override
	public void addProblem(ISipleProblem problem, int column, int line,
			int charStart, int charEnd) {
		this.problemList.add(problem.getMessage());
	}
	
	@Override
	public void addProblem(ISipleProblem problem, org.eclipse.emf.ecore.EObject element) {
		this.problemList.add(problem.getMessage());
	}
	
	@Override
	public void addError(String message, org.eclipse.emf.ecore.EObject cause) {
		this.problemList.add(message);
	}

	@Override
	public void addWarning(String message, org.eclipse.emf.ecore.EObject cause) {
		this.problemList.add(message);
	};
	
	public List<String> getAllProblems() {
		return this.problemList;
	}

	@Override
	public void cancelReload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISipleLocationMap getLocationMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <ContainerType extends EObject, ReferenceType extends EObject> void registerContextDependentProxy(
			ISipleContextDependentURIFragmentFactory<ContainerType, ReferenceType> factory,
			ContainerType container, EReference reference, String id,
			EObject proxyElement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload(InputStream stream, Map<?, ?> options)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Map<?, ?> options) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TreeIterator<EObject> getAllContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<EObject> getContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EObject getEObject(String uriFragment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<Diagnostic> getErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceSet getResourceSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTimeStamp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getURIFragment(EObject eObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<Diagnostic> getWarnings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isModified() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTrackingModification() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void load(Map<?, ?> options) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(InputStream inputStream, Map<?, ?> options)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Map<?, ?> options) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(OutputStream outputStream, Map<?, ?> options)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModified(boolean isModified) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTimeStamp(long timeStamp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTrackingModification(boolean isTrackingModification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setURI(URI uri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EList<Adapter> eAdapters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eDeliver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eNotify(Notification notification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eSetDeliver(boolean deliver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ISipleMetaInformation getMetaInformation() {
		// TODO Auto-generated method stub
		return null;
	}
}
