package org.jastemf.converter.jastadd;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.jastemf.JastEMFException;

import org.jastadd.ast.AST.ASTDecl;
import org.jastadd.ast.AST.AggregateComponents;
import org.jastadd.ast.AST.AttrDecl;
import org.jastadd.ast.AST.Components;
import org.jastadd.ast.AST.Grammar;
import org.jastadd.ast.AST.InhDecl;
import org.jastadd.ast.AST.List;
import org.jastadd.ast.AST.ListComponents;
import org.jastadd.ast.AST.OptionalComponent;
import org.jastadd.ast.AST.TokenComponent;
import org.jastadd.ast.AST.TypeDecl;
import org.jastadd.ast.AST.SynDecl;
import org.jastadd.ast.AST.Parameter;

/**
 * This class realizes a mapping from JastAdd2 to Ecore.
 * 
 * @author skarol
 * 
 */
public class JastAdd2Ecore {

	private HashMap<String, EPackage> ePackagesMap;
	private EcoreFactory factory = EcoreFactory.eINSTANCE;
	private HashMap<TypeDecl, EClass> eClassMap;
	private HashMap<String, EDataType> eDataTypeMap;
	private Grammar grammar;
	private boolean ignoreASTTypes = false;

	public Collection<EPackage> convertGrammar(Grammar g,boolean ignoreASTTypes)
			throws JastEMFException {
		ePackagesMap = new HashMap<String, EPackage>();
		eClassMap = new HashMap<TypeDecl, EClass>();
		eDataTypeMap = new HashMap<String, EDataType>();
		grammar = g;
		this.ignoreASTTypes = ignoreASTTypes;
		List l = g.getTypeDeclList();
		for (int i = 0; i < l.getNumChild(); i++) {

			if (l.getChild(i) instanceof ASTDecl) {
				ASTDecl astDecl = (ASTDecl) l.getChild(i);
				if(ignoreASTTypes && isASTType(astDecl.name())){
					continue;
				}
				String ePackageKey = astDecl.getFileName();
				if (!ePackagesMap.containsKey(ePackageKey)) {
					EPackage pck = factory.createEPackage();
					String lastFragment = ePackageKey.substring(ePackageKey
							.lastIndexOf('/') + 1);
					String packageName = lastFragment.lastIndexOf('.') == -1 ? lastFragment
							: lastFragment.substring(0,
									lastFragment.lastIndexOf('.'));
					pck.setName(packageName);
					pck.setNsPrefix(packageName);
					pck.setNsURI(ePackageKey);
					ePackagesMap.put(ePackageKey, pck);
				}
				EPackage currentPackage = ePackagesMap.get(ePackageKey);
				EClass currentEClass = convertASTDecl(astDecl);
				currentPackage.getEClassifiers().add(currentEClass);
			}
		}
		Collection<EPackage> packages = ePackagesMap.values();
		// adding newly created primitive type declarations to first ePackage
		if (!eDataTypeMap.isEmpty()) {
			EPackage firstPackage = packages.iterator().next();
			for(EDataType dataType:eDataTypeMap.values()){
				if(dataType.getEPackage()==null){
					firstPackage.getEClassifiers().add(dataType);
				}
			}
		}
		return packages;
	}

	private EClass convertASTDecl(ASTDecl astDecl) throws JastEMFException {
		convertASTFeatures(astDecl);
		return convertAttributionFeatures(astDecl);
	}

	private EClass convertAttributionFeatures(ASTDecl astDecl)
			throws JastEMFException {
		EClass eClass = lookupEClassLocal(astDecl);
		
		Collection attrDeclarations = getAllAttrDecls(astDecl);
	
		for (Object o : attrDeclarations) {
			AttrDecl attrDecl = (AttrDecl) o;
			
			//We filter attr decls duplicated in subclasses since this is not allowed in 
			//Ecore (execept for Operations). However, Ecore still generated stubs for such features, hence
			//it should not be a problem if we ignore such attributes
			//Note: There might be multiple reasons for duplicate declarations:
				//first is the internal JastAdd impl does some rewrites on the specification
				//to distribute all decls also to subclasses
				//second the user might have redeclared an attribute, which might cause problems
			if(isInSuper(astDecl, attrDecl))
				continue;
			
			//if we have attributes with > 0 parameters, we can only map to EOperations
			if (attrDecl.getParameterList().getNumChild() == 0) {
				//can be set to false if deriving an EClass for non containments fails (=> no reference attribute)
				boolean typeInAST = true; 
				if(!attrDecl.isPrimitive()) {
					EReference ref = null;
					
					// is reference or NTA attribute
					if (attrDecl.isNTA()) {
						//JastAdd has duplicate entries for ntas if it is declared in the AST spec
						// we have to exclude one from the mapping
						// (may also be required to be checked over the whole hierarchy)
						if(hasNTAFeatureFromAST(attrDecl, eClass)){
							System.out.println("Skipping already added NTA '"+attrDecl.signature()+"'.");
							continue;
						}
						// is NTA attribute -> we have to check if it refers to
						// List or Opt childs
						GenericType type = getGenericTypeRepresentation(attrDecl
								.type());
						if (isASTListType(attrDecl.type())) {
							ref = createContainmentList();
							if(type.hasTypeArgs()){
								ref.setEType(lookupEClass(type.getTypeArgs()
										.iterator().next().getType()));							
							}
							else{
								System.out.println("Warning: nta declaration "+attrDecl.signature()+" has List type but has no type argument - using EObject");
								ref.setEType(lookupEClass("EObject"));
							}
						} else if (isASTOptType(attrDecl.type())) {
							ref = createContainmentOpt();
							if(type.hasTypeArgs()){
								ref.setEType(lookupEClass(type.getTypeArgs()
										.iterator().next().getType()));
							}
							else{
								System.out.println("Warning: nta declaration "+attrDecl.signature()+" has Opt type but has so type argument - using EObject");								
								ref.setEType(lookupEClass("EObject"));

							}
						} else {
							//A normal NTA is mapped to a containment.
							//however there are some "hacks" in JastAddJ that declare a pseudo
							//NTA terminal which actually is not a NTA, e.g.: /<ID:String>/
							//hence, in such cases, we map to an EAttribute 
							EClass refType = lookupEClass(type.getType());
							if(refType!=null){
								ref = createContainmentAggregate();
								ref.setEType(refType);
							}
							else{
								typeInAST = false;
							}
							
						}
						if(ref!=null)
							setNTA(ref);

					} else {
						EClass refType = lookupEClass(attrDecl.type());
						if(refType!=null){
							ref = createNonContainment();
							ref.setEType(lookupEClass(attrDecl.type()));							
						}
						else{
							typeInAST = false;
						}
					}
					if(typeInAST){
						ref.setName(attrDecl.name());
						if (attrDecl.lazyCondition()) {
							ref.setChangeable(true);
						}
					
						//System.out.println("Adding ref to EClass "+ eClass.getName() +": " +ref.getName() +":"+(ref.getEType()!=null?ref.getEType().getName():"[NULL]"));
						ref.getEAnnotations().add(createJastEMFAnnotation(attrDecl));
						eClass.getEStructuralFeatures().add(ref);						
					}
				}
				
				//an attribute with primitive return value is mapped to an EAttribute
				if (attrDecl.isPrimitive()||!typeInAST) {
					//System.out.println("Decl "+ attrDecl.getName() +": "+attrDecl.getType()+" is primitive.");
					EAttribute attr = factory.createEAttribute();
					attr.setName(attrDecl.name());
					attr.setEType(lookUpEDataType(attrDecl.type()));
					eClass.getEStructuralFeatures().add(attr);
					attr.setDerived(true);
					if (!attrDecl.getLazy()) {
						attr.setChangeable(false);
					}
					attr.setTransient(true);
					attr.getEAnnotations().add(createJastEMFAnnotation(attrDecl));
					eClass.getEStructuralFeatures().add(attr);

				}

			} else {
				EOperation eop = factory.createEOperation();
				eop.setName(attrDecl.name());
				if(attrDecl.isPrimitive()||(lookupEClass(attrDecl.type())==null)){
					eop.setEType(lookUpEDataType(attrDecl.type()));
				}
				else{
					eop.setEType(lookupEClass(attrDecl.type()));
				}
				for (int i = 0; i < attrDecl.getParameterList().getNumChild(); i++) {
					Parameter param = attrDecl.getParameter(i);
					EParameter eParameter = factory.createEParameter();
					eParameter.setName(param.getName());
					// we do not consider list parameters!
					EClassifier eType = lookupEClass(param.getType());
					if (eType == null) {
						eType = lookUpEDataType(param.getType());
					}
					eParameter.setEType(eType);
					eop.getEParameters().add(eParameter);
				}
				eop.getEAnnotations().add(createJastEMFAnnotation(attrDecl));
				eClass.getEOperations().add(eop);
			}

		}
		return eClass;
	}
	
	
	/**
	 * Maps all features of an AST declaration in JastAdd to a corresponding
	 * EClass in Ecore.
	 * 
	 * Normal Components are mapped to EReferences with containment=true and
	 * with appropriate boundaries. NTAComponents will be marked as derived,
	 * transient and unchangeable. Terminals are mapped to EAttributes while
	 * EDataType declarations are reused from ECore if possible, otherwise
	 * EDataType declarations are generated on the fly.
	 * 
	 * @param astDecl
	 *            - a JastAdd ASTDecl
	 * @return An EClass with an Ecore representation of the ASTDecl.
	 */
	private EClass convertASTFeatures(ASTDecl astDecl) {
		EClass eClass = lookupEClassLocal(astDecl);
		eClass.setAbstract(astDecl.getAbstractOpt().getNumChild() > 0);

		if (astDecl.hasSuperClass()) {
			if(!ignoreASTTypes||!isASTType(astDecl.getSuperClass().getID())){
				EClass eSuperClass = lookupEClass(astDecl.getSuperClass().getID());
				eClass.getESuperTypes().add(eSuperClass);				
			}
		}

		@SuppressWarnings("rawtypes")
		Iterator it = astDecl.getComponents();
		while (it.hasNext()) {
			Components components = (Components) it.next();
			//checking if feature already declared in superclass, if yes we skip it,
			//since this is not compatible with Ecore 
			//this even occurs if a feature is only declared in superclass
			if(isInSuper(astDecl,components))
				continue;
			if (components instanceof TokenComponent) {
				TokenComponent token = (TokenComponent) components;
				EAttribute attr = factory.createEAttribute();
				attr.setName(token.name());
				attr.setEType(lookUpEDataType(token.type()));
				eClass.getEStructuralFeatures().add(attr);
			} else {
				EReference ref = null;

				if (components instanceof ListComponents) {
					ListComponents list = (ListComponents) components;
					ref = createContainmentList();
					ref.setName(list.getId().name());
					ref.setEType(lookupEClass(list.type()));
				} else if (components instanceof OptionalComponent) {
					OptionalComponent optional = (OptionalComponent) components;
					ref = createContainmentOpt();
					ref.setName(optional.getId().name());
					ref.setEType(lookupEClass(optional.type()));
				} else if (components instanceof AggregateComponents) {
					AggregateComponents aggregate = (AggregateComponents) components;
					ref = createContainmentAggregate();
					ref.setName(aggregate.getId().name());
					ref.setEType(lookupEClass(aggregate.type()));
				}

				// setting to derived if declared as NTA
				if (components.isNTA()) {
					setNTA(ref);
				}

				eClass.getEStructuralFeatures().add(ref);

			}
		}
		return eClass;

	}
	
	private boolean hasNTAFeatureFromAST(AttrDecl ntaAttrDecl, EClass eClass){
		String nameInAST;
		//realizes the NTA naming scheme required in jrag files
		if(ntaAttrDecl.name().endsWith("List")&&ntaAttrDecl.name().startsWith("get")){
			nameInAST = ntaAttrDecl.name().substring(3,ntaAttrDecl.name().length()-4);
		}
		else if(ntaAttrDecl.name().startsWith("get")){
			nameInAST = ntaAttrDecl.name().substring(3);
		}
		else{
			return false;
		}
		
		for(EStructuralFeature feature: eClass.getEStructuralFeatures()){
			if(feature.getName().equals(nameInAST))
				return true;
		}
		
		return false;
	}
	
	private boolean isInSuper(ASTDecl astDecl, Components components){
		String superClassName = astDecl.getSuperClassName();		
		while(superClassName!=null&&!isASTNodeType(superClassName)){
			TypeDecl typeDecl = grammar.lookup(superClassName);
			Iterator it = typeDecl.getComponents();
			while (it.hasNext()) {
				Components currentComponents = (Components) it.next();
				if(currentComponents.name().equals(components.name()))
					return true;
			}
			superClassName = ((ASTDecl)typeDecl).getSuperClassName();
		}
		return false;
	}
	
	
	private boolean isInSuper(ASTDecl astDecl, AttrDecl attrDecl){
		String superClassName = astDecl.getSuperClassName();		
		while(superClassName!=null&&!isASTNodeType(superClassName)){
			TypeDecl typeDecl = grammar.lookup(superClassName);		
			//System.out.println(attrDecl.signature());
			for(AttrDecl currentAttrDecl:getAllAttrDecls((ASTDecl)(typeDecl))){
				if(currentAttrDecl.getParameterList().getNumChild()==
						attrDecl.getParameterList().getNumChild()){
					if(attrDecl.signature().equals(currentAttrDecl.signature())){
						return true;
					}
			
					if(attrDecl.name().equals(currentAttrDecl.name())){
						System.out.println("Warning: "+attrDecl.signature()+" overloads "+currentAttrDecl.signature() + ". Duplicate names are not allowed in Ecore");
						return true;
					}

				}
					
			}
			superClassName = ((ASTDecl)typeDecl).getSuperClassName();
		}
		return false;
	}
	
	private Collection<AttrDecl> getAllAttrDecls(ASTDecl astDecl){
		//putting all syn and inh declarations in one collection
		Collection attrDeclarations = astDecl.synDeclarations();
		attrDeclarations.addAll(astDecl.inhDeclarations());
		for(int i=0;i<astDecl.getCollDeclList().getNumChild();i++){
			attrDeclarations.add(astDecl.getCollDecl(i));
		}
		return new LinkedList<AttrDecl>(attrDeclarations);
	}
	

	private EReference createNonContainment() {
		EReference ref = factory.createEReference();
		ref.setContainment(false);
		ref.setDerived(true);
		ref.setChangeable(false);
		return ref;
	}

	private EReference createContainment() {
		EReference ref = factory.createEReference();
		ref.setContainment(true);
		return ref;
	}

	private EReference createContainmentOpt() {
		EReference ref = createContainment();
		ref.setLowerBound(0);
		ref.setUpperBound(1);
		return ref;
	}

	private EReference createContainmentAggregate() {
		EReference ref = createContainment();
		ref.setLowerBound(1);
		ref.setUpperBound(1);
		return ref;
	}

	private EReference createContainmentList() {
		EReference ref = createContainment();
		ref.setLowerBound(0);
		ref.setUpperBound(-1);
		return ref;
	}

	private void setNTA(EReference ref) {
		ref.setDerived(true);
		ref.setTransient(true);
		ref.setChangeable(false);
	}

	private boolean isASTListType(String typeString) {
		if (typeString.startsWith("List"))
			return true;
		return false;
	}

	private boolean isASTOptType(String typeString) {
		if (typeString.startsWith("Opt"))
			return true;
		return false;
	}

	private boolean isASTNodeType(String typeString) {
		if (typeString.startsWith("ASTNode"))
			return true;
		return false;
	}
	
	private boolean isASTType(String typeString) {
		if(isASTListType(typeString)||isASTOptType(typeString)||isASTNodeType(typeString))
			return true;
		return false;
	}

	private static GenericType getGenericTypeRepresentation(String type)
			throws JastEMFException {
		return extractGenericTypeArgs(new StringBuffer(type)).iterator().next();
	}

	private static Collection<GenericType> extractGenericTypeArgs(
			StringBuffer typeString) throws JastEMFException {
		int typeArgStart = typeString.indexOf("<");
		int typeListStart = typeString.indexOf(",");
		int typeListEnd = typeString.indexOf(">");
		if (typeArgStart == 0 || typeListStart == 0 || typeListEnd == 0) {
			throw new JastEMFException("Unexpected character '"
					+ typeString.charAt(0) + "', name expected.)");
		}
		int nextIndex = typeString.length();

		Collection<GenericType> genericType = new LinkedList<GenericType>();

		nextIndex = typeArgStart != -1 && typeArgStart < nextIndex ? typeArgStart
				: nextIndex;
		nextIndex = typeListStart != -1 && typeListStart < nextIndex ? typeListStart
				: nextIndex;
		nextIndex = typeListEnd != -1 && typeListEnd < nextIndex ? typeListEnd
				: nextIndex;

		String typeName = typeString.substring(0, nextIndex);
		typeString.delete(0, nextIndex);
		if (typeString.length() == 0) {
			genericType.add(new GenericType(typeName, null));
		} else {
			char next = typeString.charAt(0);
			if (next == '<') {
				typeString.deleteCharAt(0);
				genericType.add(new GenericType(typeName,
						extractGenericTypeArgs(typeString)));
				if (typeString.charAt(0) != '>')
					throw new JastEMFException("Unexpected character '"
							+ typeString.charAt(0) + "', expected '>')");
				typeString.deleteCharAt(0);

				if (typeString.length() > 0 && typeString.charAt(0) == ',') {
					typeString.deleteCharAt(0);
					genericType.addAll(extractGenericTypeArgs(typeString));
				}

			} else if (next == ',') {
				typeString.deleteCharAt(0);
				genericType.add(new GenericType(typeName, null));
				genericType.addAll(extractGenericTypeArgs(typeString));
			} else if (next == '>') {
				genericType.add(new GenericType(typeName, null));
			}

		}
		return genericType;
	}

	private static class GenericType {

		private String type;
		private Collection<GenericType> typeArgs;

		public GenericType(String type, Collection<GenericType> typeArgs) {
			this.type = type;
			this.typeArgs = typeArgs;
		}

		public String getType() {
			return type;
		}

		public Collection<GenericType> getTypeArgs() {
			return typeArgs;
		}

		public boolean hasTypeArgs() {
			return (typeArgs != null) && (!getTypeArgs().isEmpty());
		}

		public String toString() {
			String result = type;
			if (hasTypeArgs()) {
				result = result + "<";
				Iterator<GenericType> it = getTypeArgs().iterator();
				while (it.hasNext()) {
					GenericType next = it.next();
					result = result + next.toString();
					if (it.hasNext())
						result = result + ",";
				}
				result += ">";
			}
			return result;
		}
	}

	/**
	 * Looks up an EClass for a given type declaration in JastAdd. If the lookup
	 * fails, i.e., iff the type declaration has not yet a corresponding EClass,
	 * a new one is created, added to the map and returned.
	 * 
	 * @param typeDecl
	 *            - a JastAdd TypeDecl
	 * @return The corresponding EClass for the given TypeDecl.
	 */

	private EClass lookupEClassLocal(TypeDecl typeDecl) {
		EClass eClass = null;
		if(typeDecl instanceof ASTDecl){
			if(ignoreASTTypes&&!"ASTNode".equals(typeDecl.name())){
				if (!eClassMap.containsKey(typeDecl)) {
					EClass clz = EcoreFactory.eINSTANCE.createEClass();
					clz.setName(typeDecl.getIdDecl().getID());
					eClassMap.put(typeDecl, clz);
				}
				eClass = eClassMap.get(typeDecl);
				return eClass;
			}
		}
		return null;
	}
	
	private EClass lookupEClassLocal(String typeName) {
		return grammar.lookup(typeName) != null ? lookupEClassLocal(grammar
				.lookup(typeName)) : null;
	}

	private EClass lookupEClass(String typeName) {
		EClass eClass = lookupEClassLocal(typeName);
		if(eClass!=null){
			//we have found a generated class from AST spec.
			return eClass;
		}
		else{
			//we did not found any local class
			if("Object".equals(typeName)||"ASTNode".equals(typeName)){
				return (EClass) factory.getEcorePackage().getEClassifier("EObject");
			}
			else{
				for(EClassifier candidate:factory.getEcorePackage().getEClassifiers()){
					if(candidate.getName().equals(typeName)){
						if(candidate instanceof EClass)
							return (EClass)candidate;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Realizes the mapping of non AST types in Jastadd to EDataTypes in Ecore.
	 * 
	 * @param typeName
	 * @return
	 */
	private EDataType lookUpEDataType(final String typeName) {
		if(eDataTypeMap.containsKey(typeName)){
			return eDataTypeMap.get(typeName);
		}
		else{
			String currentTypeName = typeName;
			if("java.lang.String".equals(currentTypeName))
				currentTypeName = "String";
			
			//first checking if there is an EDataType with the original name in Ecore
			if (factory.getEcorePackage().getEClassifier(currentTypeName) != null) {
				EClassifier classifier = 
						factory.getEcorePackage().getEClassifier(currentTypeName);
				if(classifier!=null && classifier instanceof EDataType){
					eDataTypeMap.put(typeName,(EDataType)classifier);
					return (EDataType)classifier;
				}
			}	
			else{
			//second checking if there is an EDataType with a prefixed E in Ecore
		
				String currentETypeName = "E".concat(currentTypeName.substring(0, 1)
						.toUpperCase().concat(currentTypeName.substring(1)));
				if (factory.getEcorePackage().getEClassifier(currentETypeName) != null) {
					EClassifier classifier = 
							factory.getEcorePackage().getEClassifier(currentETypeName);
						
					if(classifier!=null && classifier instanceof EDataType){
						eDataTypeMap.put(typeName,(EDataType)classifier);
						return (EDataType)classifier;
					}
				}
			}
			
			//we did not find an EDataType --> create one
			EDataType eDataType = factory.createEDataType();
			String newEDataTypeName = typeName;
			//someone used an ASTNodeType in a terminal decl..
			if(lookupEClassLocal(typeName)!=null){
				newEDataTypeName = newEDataTypeName + "_EDataType";
			}
			eDataType.setName(newEDataTypeName);
			eDataType.setInstanceTypeName(getInstanceTypeName(typeName));
			eDataTypeMap.put(typeName, eDataType);
			return eDataType;
		}
	}
	
	private static String getInstanceTypeName(String plainName){
		if("String".equals(plainName)){
			return "java.lang.String";
		}
		else if("Collection".equals(plainName)){
			return "java.util.Collection";
		}
		else if("Map".equals(plainName)){
			return "java.util.Map";
		}
		else if("HashMap".equals(plainName)){
			return "java.util.HashMap";
		}
		else if("List".equals(plainName)){
			return "java.util.List";
		}
		else if("LinkedList".equals(plainName)){
			return "java.util.LinkedList";
		}
		else if("ArrayList".equals(plainName)){
			return "java.util.ArrayList";
		}
		else if("Set".equals(plainName)){
			return "java.util.Set";
		}
		else if("HashSet".equals(plainName)){
			return "java.util.HashSet";
		}
		return plainName;
	}
	
	public static final String ANNOTATION_IS_NTA = "is_nta";
	public static final String ANNOTATION_KIND = "kind";
	public static final String ANNOTATION_KIND_INH = "inh";
	public static final String ANNOTATION_KIND_SYN = "syn";
	public static final String ANNOTATION_KIND_COLL = "coll";
	public static final String ANNOTATION_IS_CACHED = "is_cached";
	public static final String ANNOTATION_IS_PRIMITIVE = "is_primitive";
	public static final String ANNOTATION_IS_CIRCULAR= "is_circular";
	public static final String ANNOTATION_ASPECT = "aspect";
	public static final String ANNOTATION_FILE = "file";

	public static final String ANNOTATION_NAMESPACE = "http://www.jastemf.org/jastemf-annotations";
	
	private EAnnotation createJastEMFAnnotation(AttrDecl attrDecl){
		EAnnotation annotation = factory.createEAnnotation();
		annotation.setSource(ANNOTATION_NAMESPACE);
		annotation.getDetails().put(ANNOTATION_IS_NTA,""+attrDecl.isNTA());
		annotation.getDetails().put(ANNOTATION_KIND,(attrDecl instanceof SynDecl)?ANNOTATION_KIND_SYN:(attrDecl instanceof InhDecl?ANNOTATION_KIND_INH:ANNOTATION_KIND_COLL));
		annotation.getDetails().put(ANNOTATION_IS_CACHED,""+attrDecl.getLazy());
		annotation.getDetails().put(ANNOTATION_IS_PRIMITIVE,""+attrDecl.isPrimitive());
		annotation.getDetails().put(ANNOTATION_IS_CIRCULAR,""+attrDecl.isCircular());
		annotation.getDetails().put(ANNOTATION_ASPECT, attrDecl.getAspectName());
		annotation.getDetails().put(ANNOTATION_FILE, attrDecl.getFileName());
		return annotation;
	}
	


}
