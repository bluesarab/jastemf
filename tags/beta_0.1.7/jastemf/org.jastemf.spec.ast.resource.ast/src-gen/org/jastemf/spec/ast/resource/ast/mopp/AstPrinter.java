/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.jastemf.spec.ast.resource.ast.mopp;

public class AstPrinter implements org.jastemf.spec.ast.resource.ast.IAstTextPrinter {
	
	protected org.jastemf.spec.ast.resource.ast.IAstTokenResolverFactory tokenResolverFactory = new org.jastemf.spec.ast.resource.ast.mopp.AstTokenResolverFactory();
	protected java.io.OutputStream outputStream;
	/**
	 * Holds the resource that is associated with this printer. may be null if the
	 * printer is used stand alone.
	 */
	private org.jastemf.spec.ast.resource.ast.IAstTextResource resource;
	private java.util.Map<?, ?> options;
	
	public AstPrinter(java.io.OutputStream outputStream, org.jastemf.spec.ast.resource.ast.IAstTextResource resource) {
		super();
		this.outputStream = outputStream;
		this.resource = resource;
	}
	
	protected static int matchCount(java.util.Map<java.lang.String, java.lang.Integer> featureCounter, java.util.Collection<java.lang.String> needed){
		int pos = 0;
		int neg = 0;
		
		for(java.lang.String featureName:featureCounter.keySet()){
			if(needed.contains(featureName)){
				int value = featureCounter.get(featureName);
				if (value == 0) {
					neg += 1;
				} else {
					pos += 1;
				}
			}
		}
		return neg > 0 ? -neg : pos;
	}
	
	protected void doPrint(org.eclipse.emf.ecore.EObject element, java.io.PrintWriter out, java.lang.String globaltab) {
		if (element == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write.");
		}
		if (out == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write on.");
		}
		
		if (element instanceof org.jastemf.spec.ast.Grammar) {
			print_org_jastemf_spec_ast_Grammar((org.jastemf.spec.ast.Grammar) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.ASTDecl) {
			print_org_jastemf_spec_ast_ASTDecl((org.jastemf.spec.ast.ASTDecl) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.ListComponentsNTA) {
			print_org_jastemf_spec_ast_ListComponentsNTA((org.jastemf.spec.ast.ListComponentsNTA) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.TokenComponentNTA) {
			print_org_jastemf_spec_ast_TokenComponentNTA((org.jastemf.spec.ast.TokenComponentNTA) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.OptionalComponentNTA) {
			print_org_jastemf_spec_ast_OptionalComponentNTA((org.jastemf.spec.ast.OptionalComponentNTA) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.AggregateComponentsNTA) {
			print_org_jastemf_spec_ast_AggregateComponentsNTA((org.jastemf.spec.ast.AggregateComponentsNTA) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.Abstract) {
			print_org_jastemf_spec_ast_Abstract((org.jastemf.spec.ast.Abstract) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.Id) {
			print_org_jastemf_spec_ast_Id((org.jastemf.spec.ast.Id) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.NameNode) {
			print_org_jastemf_spec_ast_NameNode((org.jastemf.spec.ast.NameNode) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.TokenId) {
			print_org_jastemf_spec_ast_TokenId((org.jastemf.spec.ast.TokenId) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.IdUse) {
			print_org_jastemf_spec_ast_IdUse((org.jastemf.spec.ast.IdUse) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.IdDecl) {
			print_org_jastemf_spec_ast_IdDecl((org.jastemf.spec.ast.IdDecl) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.ListComponents) {
			print_org_jastemf_spec_ast_ListComponents((org.jastemf.spec.ast.ListComponents) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.TokenComponent) {
			print_org_jastemf_spec_ast_TokenComponent((org.jastemf.spec.ast.TokenComponent) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.OptionalComponent) {
			print_org_jastemf_spec_ast_OptionalComponent((org.jastemf.spec.ast.OptionalComponent) element, globaltab, out);
			return;
		}
		if (element instanceof org.jastemf.spec.ast.AggregateComponents) {
			print_org_jastemf_spec_ast_AggregateComponents((org.jastemf.spec.ast.AggregateComponents) element, globaltab, out);
			return;
		}
		
		addWarningToResource("The printer can not handle " + element.eClass().getName() + " elements", element);
	}
	
	protected org.jastemf.spec.ast.resource.ast.mopp.AstReferenceResolverSwitch getReferenceResolverSwitch() {
		return (org.jastemf.spec.ast.resource.ast.mopp.AstReferenceResolverSwitch) new org.jastemf.spec.ast.resource.ast.mopp.AstMetaInformation().getReferenceResolverSwitch();
	}
	
	protected void addWarningToResource(final java.lang.String errorMessage, org.eclipse.emf.ecore.EObject cause) {
		org.jastemf.spec.ast.resource.ast.IAstTextResource resource = getResource();
		if (resource == null) {
			// the resource can be null if the printer is used stand alone
			return;
		}
		resource.addProblem(new org.jastemf.spec.ast.resource.ast.mopp.AstProblem(errorMessage, org.jastemf.spec.ast.resource.ast.AstEProblemType.ERROR), cause);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		this.options = options;
	}
	
	public java.util.Map<?,?> getOptions() {
		return options;
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTextResource getResource() {
		return resource;
	}
	
	/**
	 * Calls {@link #doPrint(EObject, PrintWriter, String)} and writes the result to
	 * the underlying output stream.
	 */
	public void print(org.eclipse.emf.ecore.EObject element) {
		java.io.PrintWriter out = new java.io.PrintWriter(new java.io.BufferedOutputStream(outputStream));
		doPrint(element, out, "");
		out.flush();
		out.close();
	}
	
	public void print_org_jastemf_spec_ast_Grammar(org.jastemf.spec.ast.Grammar element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.GRAMMAR__TYPE_DECL));
		printCountingMap.put("TypeDecl", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("TypeDecl");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.GRAMMAR__TYPE_DECL));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				java.lang.Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("TypeDecl", 0);
		}
	}
	
	public void print_org_jastemf_spec_ast_ASTDecl(org.jastemf.spec.ast.ASTDecl element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(9);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__FILE_NAME));
		printCountingMap.put("FileName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__START_LINE));
		printCountingMap.put("StartLine", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__END_LINE));
		printCountingMap.put("EndLine", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__COMMENT));
		printCountingMap.put("Comment", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__ABSTRACT));
		printCountingMap.put("Abstract", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__ID_DECL));
		printCountingMap.put("IdDecl", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__SUPER_CLASS_ID));
		printCountingMap.put("SuperClassId", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__COMPONENTS));
		printCountingMap.put("Components", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__SUPER_CLASS));
		printCountingMap.put("superClass", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("Abstract");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__ABSTRACT));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("Abstract", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("IdDecl");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__ID_DECL));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("IdDecl", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(printCountingMap);
		print_org_jastemf_spec_ast_ASTDecl_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(printCountingMap);
		print_org_jastemf_spec_ast_ASTDecl_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	public void print_org_jastemf_spec_ast_ASTDecl_0(org.jastemf.spec.ast.ASTDecl element, java.lang.String outertab, java.io.PrintWriter out, java.util.Map<java.lang.String, java.lang.Integer> printCountingMap){
		java.lang.String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("SuperClassId");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__SUPER_CLASS_ID));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("SuperClassId", count - 1);
		}
	}
	public void print_org_jastemf_spec_ast_ASTDecl_1(org.jastemf.spec.ast.ASTDecl element, java.lang.String outertab, java.io.PrintWriter out, java.util.Map<java.lang.String, java.lang.Integer> printCountingMap){
		java.lang.String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("::=");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("Components");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__COMPONENTS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				java.lang.Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("Components", 0);
		}
	}
	
	public void print_org_jastemf_spec_ast_ListComponents(org.jastemf.spec.ast.ListComponents element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.LIST_COMPONENTS__ID));
		printCountingMap.put("Id", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("Id");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.LIST_COMPONENTS__ID));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("Id", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("*");
		out.print(" ");
	}
	
	public void print_org_jastemf_spec_ast_ListComponentsNTA(org.jastemf.spec.ast.ListComponentsNTA element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.LIST_COMPONENTS_NTA__ID));
		printCountingMap.put("Id", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("/");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("Id");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.LIST_COMPONENTS_NTA__ID));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("Id", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("*");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("/");
		out.print(" ");
	}
	
	public void print_org_jastemf_spec_ast_TokenComponent(org.jastemf.spec.ast.TokenComponent element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_COMPONENT__TOKEN_ID));
		printCountingMap.put("TokenId", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("TokenId");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_COMPONENT__TOKEN_ID));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("TokenId", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
	}
	
	public void print_org_jastemf_spec_ast_TokenComponentNTA(org.jastemf.spec.ast.TokenComponentNTA element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_COMPONENT_NTA__TOKEN_ID));
		printCountingMap.put("TokenId", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("/");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("<");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("TokenId");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_COMPONENT_NTA__TOKEN_ID));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("TokenId", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(">");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("/");
		out.print(" ");
	}
	
	public void print_org_jastemf_spec_ast_OptionalComponent(org.jastemf.spec.ast.OptionalComponent element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.OPTIONAL_COMPONENT__ID));
		printCountingMap.put("Id", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("[");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("Id");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.OPTIONAL_COMPONENT__ID));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("Id", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("]");
		out.print(" ");
	}
	
	public void print_org_jastemf_spec_ast_OptionalComponentNTA(org.jastemf.spec.ast.OptionalComponentNTA element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.OPTIONAL_COMPONENT_NTA__ID));
		printCountingMap.put("Id", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("/");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("[");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("Id");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.OPTIONAL_COMPONENT_NTA__ID));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("Id", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("]");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("/");
		out.print(" ");
	}
	
	public void print_org_jastemf_spec_ast_AggregateComponents(org.jastemf.spec.ast.AggregateComponents element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AGGREGATE_COMPONENTS__ID));
		printCountingMap.put("Id", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("Id");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AGGREGATE_COMPONENTS__ID));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("Id", count - 1);
		}
	}
	
	public void print_org_jastemf_spec_ast_AggregateComponentsNTA(org.jastemf.spec.ast.AggregateComponentsNTA element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AGGREGATE_COMPONENTS_NTA__ID));
		printCountingMap.put("Id", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("/");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("Id");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AGGREGATE_COMPONENTS_NTA__ID));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("Id", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("/");
		out.print(" ");
	}
	
	public void print_org_jastemf_spec_ast_Abstract(org.jastemf.spec.ast.Abstract element, java.lang.String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("abstract");
		out.print(" ");
	}
	
	public void print_org_jastemf_spec_ast_Id(org.jastemf.spec.ast.Id element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(2);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID__NAME_NODE));
		printCountingMap.put("NameNode", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID__ID_USE));
		printCountingMap.put("IdUse", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(printCountingMap);
		print_org_jastemf_spec_ast_Id_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("IdUse");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID__ID_USE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("IdUse", count - 1);
		}
	}
	public void print_org_jastemf_spec_ast_Id_0(org.jastemf.spec.ast.Id element, java.lang.String outertab, java.io.PrintWriter out, java.util.Map<java.lang.String, java.lang.Integer> printCountingMap){
		java.lang.String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("NameNode");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID__NAME_NODE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("NameNode", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
	}
	
	public void print_org_jastemf_spec_ast_NameNode(org.jastemf.spec.ast.NameNode element, java.lang.String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.NAME_NODE__ID));
		printCountingMap.put("ID", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("ID");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.NAME_NODE__ID));
			if (o != null) {
				org.jastemf.spec.ast.resource.ast.IAstTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((java.lang.Object) o, element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.NAME_NODE__ID), element));
				out.print(" ");
			}
			printCountingMap.put("ID", count - 1);
		}
	}
	
	public void print_org_jastemf_spec_ast_TokenId(org.jastemf.spec.ast.TokenId element, java.lang.String outertab, java.io.PrintWriter out) {
		java.lang.String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(2);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__ID));
		printCountingMap.put("ID", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__TYPE));
		printCountingMap.put("TYPE", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("ID");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__ID));
			if (o != null) {
				org.jastemf.spec.ast.resource.ast.IAstTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((java.lang.Object) o, element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__ID), element));
				out.print(" ");
			}
			printCountingMap.put("ID", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(printCountingMap);
		print_org_jastemf_spec_ast_TokenId_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	public void print_org_jastemf_spec_ast_TokenId_0(org.jastemf.spec.ast.TokenId element, java.lang.String outertab, java.io.PrintWriter out, java.util.Map<java.lang.String, java.lang.Integer> printCountingMap){
		java.lang.String localtab = outertab;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_jastemf_spec_ast_TokenId_0_0(element, localtab, out, printCountingMap);
	}
	public void print_org_jastemf_spec_ast_TokenId_0_0(org.jastemf.spec.ast.TokenId element, java.lang.String outertab, java.io.PrintWriter out, java.util.Map<java.lang.String, java.lang.Integer> printCountingMap){
		int count;
		int alt = -1;
		alt=0;
		int matches=		matchCount(printCountingMap, java.util.Arrays.asList(		"TYPE"		));
		int tempMatchCount;
		tempMatchCount=		matchCount(printCountingMap, java.util.Arrays.asList(		"TYPE"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("TYPE");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__TYPE));
					if (o != null) {
						org.jastemf.spec.ast.resource.ast.IAstTokenResolver resolver = tokenResolverFactory.createTokenResolver("QIDENT");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((java.lang.Object) o, element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__TYPE), element));
						out.print(" ");
					}
					printCountingMap.put("TYPE", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
			count = printCountingMap.get("TYPE");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__TYPE));
				if (o != null) {
					org.jastemf.spec.ast.resource.ast.IAstTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENT");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((java.lang.Object) o, element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__TYPE), element));
					out.print(" ");
				}
				printCountingMap.put("TYPE", count - 1);
			}
		}
	}
	
	public void print_org_jastemf_spec_ast_IdUse(org.jastemf.spec.ast.IdUse element, java.lang.String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_USE__ID));
		printCountingMap.put("ID", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("ID");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_USE__ID));
			if (o != null) {
				org.jastemf.spec.ast.resource.ast.IAstTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((java.lang.Object) o, element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_USE__ID), element));
				out.print(" ");
			}
			printCountingMap.put("ID", count - 1);
		}
	}
	
	public void print_org_jastemf_spec_ast_IdDecl(org.jastemf.spec.ast.IdDecl element, java.lang.String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<java.lang.String, java.lang.Integer> printCountingMap = new java.util.LinkedHashMap<java.lang.String, java.lang.Integer>(1);
		java.lang.Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_DECL__ID));
		printCountingMap.put("ID", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("ID");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_DECL__ID));
			if (o != null) {
				org.jastemf.spec.ast.resource.ast.IAstTokenResolver resolver = tokenResolverFactory.createTokenResolver("IDENT");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((java.lang.Object) o, element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_DECL__ID), element));
				out.print(" ");
			}
			printCountingMap.put("ID", count - 1);
		}
	}
	
}
