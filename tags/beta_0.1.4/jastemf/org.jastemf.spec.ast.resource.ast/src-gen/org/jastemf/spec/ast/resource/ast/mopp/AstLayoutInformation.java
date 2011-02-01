/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstLayoutInformation {
	
	private final org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement syntaxElement;
	private final int startOffset;
	private final java.lang.String hiddenTokenText;
	private final java.lang.String visibleTokenText;
	private java.lang.Object object;
	private boolean wasResolved;
	
	public AstLayoutInformation(org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement syntaxElement, java.lang.Object object, int startOffset, java.lang.String hiddenTokenText, java.lang.String visibleTokenText) {
		super();
		this.syntaxElement = syntaxElement;
		this.object = object;
		this.startOffset = startOffset;
		this.hiddenTokenText = hiddenTokenText;
		this.visibleTokenText = visibleTokenText;
	}
	
	public org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement getSyntaxElement() {
		return syntaxElement;
	}
	
	public int getStartOffset() {
		return startOffset;
	}
	
	public java.lang.Object getObject(org.eclipse.emf.ecore.EObject container) {
		if (wasResolved) {
			return object;
		}
		// we need to try to resolve proxy objects again, because the proxy might have
		// been resolved before this adapter existed, which means we missed the
		// replaceProxy() notification
		if (object instanceof org.eclipse.emf.ecore.InternalEObject) {
			org.eclipse.emf.ecore.InternalEObject internalObject = (org.eclipse.emf.ecore.InternalEObject) object;
			if (internalObject.eIsProxy()) {
				if (container instanceof org.eclipse.emf.ecore.InternalEObject) {
					org.eclipse.emf.ecore.InternalEObject internalContainer = (org.eclipse.emf.ecore.InternalEObject) container;
					org.eclipse.emf.ecore.EObject resolvedObject = internalContainer.eResolveProxy(internalObject);
					if (resolvedObject != internalObject) {
						object = resolvedObject;
						wasResolved = true;
					}
				}
			}
		} else {
			wasResolved = true;
		}
		return object;
	}
	
	public java.lang.String getHiddenTokenText() {
		return hiddenTokenText;
	}
	
	public java.lang.String getVisibleTokenText() {
		return visibleTokenText;
	}
	
	public void replaceProxy(org.eclipse.emf.ecore.EObject proxy, org.eclipse.emf.ecore.EObject target) {
		if (this.object == proxy) {
			this.object = target;
		}
	}
	
}
