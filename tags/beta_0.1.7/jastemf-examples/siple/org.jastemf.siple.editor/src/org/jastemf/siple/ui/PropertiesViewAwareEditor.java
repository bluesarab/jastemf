package org.jastemf.siple.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IPostSelectionProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

/**
 * Extension of the normal xText editor that maps elements from the text selection to 
 * an EMF item provider aware structural selection that is displayed in the Eclipse
 * properties view.   
 * 
 * @see SipleUiModule#bindEditor()
 * 
 * @author skarol
 */
public class PropertiesViewAwareEditor extends XtextEditor {

	private ISelectionChangedListener propertiesViewUpdater;
	private ComposedAdapterFactory adapterFactory;
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		propertiesViewUpdater = createPropertiesViewUpdater();
		getSelectionProvider().addSelectionChangedListener(
				propertiesViewUpdater);
		if(getSelectionProvider() instanceof IPostSelectionProvider)
			((IPostSelectionProvider)getSelectionProvider()).addPostSelectionChangedListener(
				propertiesViewUpdater);
	}

	@Override
	public void dispose() {
		getSelectionProvider().removeSelectionChangedListener(
				propertiesViewUpdater);
		adapterFactory = null;
		super.dispose();
	}

	private ISelectionChangedListener createPropertiesViewUpdater() {
		return new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				try {
					ISelection selection = event.getSelection();
					if (!selection.isEmpty()
							&& selection instanceof ITextSelection) {
						final ITextSelection textSelection = (ITextSelection) selection;

						getDocument().readOnly(
								new IUnitOfWork.Void<XtextResource>() {
									public void process(XtextResource resource) throws Exception {
										IStructuredSelection structuredSelection =  mapToStructuredSelection(textSelection,resource);
										updatePropertiesView(structuredSelection);
									}
								});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			
			private void updatePropertiesView(IStructuredSelection selection){
				try {
					PropertySheet propertiesView = (PropertySheet) PlatformUI
							.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage()
							.findView(IPageLayout.ID_PROP_SHEET);
					if (propertiesView != null) {
						propertiesView.partActivated(PropertiesViewAwareEditor.this);
						propertiesView.selectionChanged(
								PropertiesViewAwareEditor.this, selection);
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		};
	}

	private IStructuredSelection mapToStructuredSelection(ITextSelection textSelection,XtextResource resource) {
		IParseResult parseResult = resource.getParseResult();
		if (parseResult == null)
			return null;
		ICompositeNode rootNode = parseResult.getRootNode();
		int offset = textSelection.getOffset();
		INode node = NodeModelUtils.findLeafNodeAtOffset(rootNode,offset);
		final EObject object = NodeModelUtils.findActualSemanticObjectFor(node);
		final IItemPropertySource source = (IItemPropertySource) adapterFactory.adapt(object, IItemPropertySource.class);
		return new StructuredSelection(){
			public Object[] toArray() {  
	    	  return new Object[]{	
	    			  new PropertySource(object,source) {
					protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor) {
						return new PropertyDescriptor(object, itemPropertyDescriptor) {
							public CellEditor createPropertyEditor(Composite composite) {
								return null;
							}
						};
					}
				}};
	      }
	    };
	}

}