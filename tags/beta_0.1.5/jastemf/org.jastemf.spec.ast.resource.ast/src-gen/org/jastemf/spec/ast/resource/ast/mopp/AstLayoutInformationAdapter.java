/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstLayoutInformationAdapter implements org.eclipse.emf.common.notify.Adapter {
	
	private org.eclipse.emf.common.notify.Notifier target;
	private java.util.List<org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformation> layoutInformations = new java.util.ArrayList<org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformation>();
	
	public org.eclipse.emf.common.notify.Notifier getTarget() {
		return target;
	}
	
	public boolean isAdapterForType(java.lang.Object type) {
		return false;
	}
	
	public void notifyChanged(org.eclipse.emf.common.notify.Notification notification) {
	}
	
	public void setTarget(org.eclipse.emf.common.notify.Notifier newTarget) {
		this.target = newTarget;
	}
	
	public java.util.List<org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformation> getLayoutInformations() {
		return layoutInformations;
	}
	
	public void addLayoutInformation(org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformation layoutInformation) {
		layoutInformations.add(layoutInformation);
	}
	
	public void replaceProxy(org.eclipse.emf.ecore.EObject proxy, org.eclipse.emf.ecore.EObject target) {
		for (org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformation layoutInformation : layoutInformations) {
			layoutInformation.replaceProxy(proxy, target);
		}
	}
	
}
