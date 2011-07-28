package org.jastemf.converter.jastadd;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcoreFactory;
import org.jastemf.JastEMFException;

import ast.AST.ASTDecl;
import ast.AST.AggregateComponents;
import ast.AST.Components;
import ast.AST.Grammar;
import ast.AST.List;
import ast.AST.ListComponents;
import ast.AST.OptionalComponent;
import ast.AST.TokenComponent;
import ast.AST.TypeDecl;
import ast.AST.SynDecl;


/**
 * This class realizes a mapping from JastAdd2 to Ecore.
 * 
 * @author skarol
 *
 */
public class JastAdd2Ecore {
	
	private HashMap<String,EPackage> ePackagesMap;
	private EcoreFactory factory = EcoreFactory.eINSTANCE;
	private HashMap<TypeDecl,EClass> eClassMap;
	private HashMap<String,EDataType> eDataTypeMap;
	private Grammar grammar;
	
	public Collection<EPackage> convertGrammar(Grammar g) throws JastEMFException{
		ePackagesMap = new HashMap<String,EPackage>();
		eClassMap = new HashMap<TypeDecl,EClass>();
		eDataTypeMap = new HashMap<String,EDataType>();
		grammar = g;
		List l = g.getTypeDeclList();
		for(int i=0;i<l.getNumChild();i++){
			
			if(l.getChild(i) instanceof ASTDecl){
				ASTDecl astDecl = (ASTDecl) l.getChild(i);
				String ePackageKey = astDecl.getFileName();
				if(!ePackagesMap.containsKey(ePackageKey)){
					EPackage pck = factory.createEPackage();
					String lastFragment = ePackageKey.substring(ePackageKey.lastIndexOf('/')+1);
					String packageName = lastFragment.lastIndexOf('.')==-1?lastFragment:lastFragment.substring(0,lastFragment.lastIndexOf('.'));
					pck.setName(packageName);
					pck.setNsPrefix(packageName);
					pck.setNsURI(ePackageKey);
					ePackagesMap.put(ePackageKey,pck);
				}
				EPackage currentPackage = ePackagesMap.get(ePackageKey);
				EClass currentEClass = convertASTDecl(astDecl);
				currentPackage.getEClassifiers().add(currentEClass);
			}
		}
		Collection<EPackage> packages = ePackagesMap.values();
		//adding primitive type declarations to first epackage
		if(!eDataTypeMap.isEmpty()){
			packages.iterator().next().getEClassifiers().addAll(eDataTypeMap.values());
		}
		return packages;
	}
	
	private EClass convertASTDecl(ASTDecl astDecl) throws JastEMFException{
		convertASTFeatures(astDecl);		
		return convertAttributionFeatures(astDecl);
	}
	 
	private EClass convertAttributionFeatures(ASTDecl astDecl) throws JastEMFException{
		EClass eClass = lookupEClass(astDecl);
		@SuppressWarnings("rawtypes")
		Collection synDeclarations = astDecl.synDeclarations();
		for(Object o:synDeclarations){ 
			SynDecl synDecl = (SynDecl)o;
		//	System.out.println(astDecl.name()+"."+synDecl.name()+":"+synDecl.type()+"-->NTA:"+synDecl.isNTA());
			if(synDecl.isPrimitive()){
				
			}
			else{
				EReference ref = null;
				//is reference or NTA attribute
				if(synDecl.isNTA()){				
					//is NTA attribute -> we have to check if it refers to List or Opt childs
					GenericType type = getGenericTypeRepresentation(synDecl.type());
					if(isASTListType(synDecl.type())){
						ref = createContainmentList();
						ref.setEType(lookupEClass(type.getTypeArgs().iterator().next().getType()));
					}
					else if(isASTOptType(synDecl.type())){
						ref = createContainmentOpt();
						ref.setEType(lookupEClass(type.getTypeArgs().iterator().next().getType()));
					}
					else{
						ref = createContainmentAggregate();
						ref.setEType(lookupEClass(type.getType()));
					}
					setNTA(ref);
				}
				else{
					ref = createNonContainment();
					ref.setEType(lookupEClass(synDecl.type()));
				}	
				ref.setName(synDecl.name());
				if(synDecl.lazyCondition()){
					ref.setChangeable(true);
				}
				eClass.getEStructuralFeatures().add(ref);
			}
		
		}
		return eClass;
	}
	
	
	/**
	 * Maps all features of an AST declaration in JastAdd to a corresponding EClass in Ecore.
	 * 
	 * Normal Components are mapped to EReferences with containment=true and with appropriate boundaries.
	 * NTAComponents will be marked as derived, transient and unchangeable. Terminals are mapped to 
	 * EAttributes while EDataType declarations are reused from ECore if possible, otherwise EDataType
	 * declarations are generated on the fly.
	 * 
	 * @param astDecl - a JastAdd ASTDecl
	 * @return An EClass with an Ecore representation of the ASTDecl. 
	 */
	private EClass convertASTFeatures(ASTDecl astDecl){
		EClass eClass = lookupEClass(astDecl);
		eClass.setAbstract(astDecl.getAbstractOpt().getNumChild()>0);
		
		if(astDecl.hasSuperClass()){
			EClass eSuperClass = lookupEClass(astDecl.getSuperClass().getID());
			eClass.getESuperTypes().add(eSuperClass);
		}
		
		@SuppressWarnings("rawtypes")
		Iterator it = astDecl.getComponents();
		while(it.hasNext()){
			Components components = (Components)it.next();
			if(components instanceof TokenComponent){
				TokenComponent token = (TokenComponent)components;
				EAttribute attr = factory.createEAttribute();
				attr.setName(token.name());
				attr.setEType(lookUpEDataType(token.type()));
				eClass.getEStructuralFeatures().add(attr);
			}
			else{
				EReference ref = null;			
				
				if(components instanceof ListComponents){
					ListComponents list = (ListComponents)components;
					ref = createContainmentList();
					ref.setName(list.getId().name());
					ref.setEType(lookupEClass(list.type()));
				}
				else if(components instanceof OptionalComponent){
					OptionalComponent optional = (OptionalComponent)components;
					ref = createContainmentOpt();
					ref.setName(optional.getId().name());
					ref.setEType(lookupEClass(optional.type()));
				}
				else if(components instanceof AggregateComponents){
					AggregateComponents aggregate = (AggregateComponents)components;
					ref = createContainmentAggregate();
					ref.setName(aggregate.getId().name());
					ref.setEType(lookupEClass(aggregate.type()));
				}
				
				//setting to derived if declared as NTA
				if(components.isNTA()){
					setNTA(ref);
				}				
				
		
				eClass.getEStructuralFeatures().add(ref);
		
			}
		}
		return eClass;

	}
	
	private EReference createNonContainment(){
		EReference ref = factory.createEReference();
		ref.setContainment(false);
		ref.setDerived(true);
		ref.setChangeable(false);
		return ref;
	}
	
	private EReference createContainment(){
		EReference ref = factory.createEReference();
		ref.setContainment(true);
		return ref;
	}
	
	private EReference createContainmentOpt(){
		EReference ref = createContainment();
		ref.setLowerBound(0);
		ref.setUpperBound(1);
		return ref;
	}
	
	private EReference createContainmentAggregate(){
		EReference ref = createContainment();
		ref.setLowerBound(1);
		ref.setUpperBound(1);
		return ref;
	}
	
	private EReference createContainmentList(){
		EReference ref = createContainment();
		ref.setLowerBound(0);
		ref.setUpperBound(-1);	
		return ref;
	}
	
	private void setNTA(EReference ref){
		ref.setDerived(true);
		ref.setTransient(true);
		ref.setChangeable(false);	
	}
	
	private boolean isASTListType(String typeString){
		if(typeString.startsWith("List"))
			return true;
		return false;
	}
	
	private boolean isASTOptType(String typeString){
		if(typeString.startsWith("Opt"))
			return true;
		return false;
	}
	
	private boolean isASTNodeType(String typeString){
		if(typeString.startsWith("ASTNode"))
			return true;
		return false;
	}

	
	
	private static GenericType getGenericTypeRepresentation(String type) throws JastEMFException{
		return extractGenericTypeArgs(new StringBuffer(type)).iterator().next();
	}
	
	private static Collection<GenericType> extractGenericTypeArgs(StringBuffer typeString) throws JastEMFException{
		int typeArgStart = typeString.indexOf("<");
		int typeListStart = typeString.indexOf(",");
		int typeListEnd = typeString.indexOf(">");
		if(typeArgStart==0||typeListStart==0||typeListEnd==0){
			throw new JastEMFException("Unexpected character '"+typeString.charAt(0)+"', name expected.)");
		}
		int nextIndex = typeString.length();

		Collection<GenericType> genericType = new LinkedList<GenericType>();
		
		nextIndex = typeArgStart!=-1&&typeArgStart<nextIndex?typeArgStart:nextIndex;
		nextIndex = typeListStart!=-1&&typeListStart<nextIndex?typeListStart:nextIndex;
		nextIndex = typeListEnd!=-1&&typeListEnd<nextIndex?typeListEnd:nextIndex;

		String typeName = typeString.substring(0,nextIndex);
		typeString.delete(0,nextIndex);
		if(typeString.length()==0){
			genericType.add(new GenericType(typeName,null));
		}
		else{
			char next = typeString.charAt(0);
			if(next=='<'){
				typeString.deleteCharAt(0);
				genericType.add(new GenericType(typeName,extractGenericTypeArgs(typeString)));
				if(typeString.charAt(0)!='>')
					throw new JastEMFException("Unexpected character '"+typeString.charAt(0)+"', expected '>')");
				typeString.deleteCharAt(0);
			
				if(typeString.length()>0&&typeString.charAt(0)==','){
					typeString.deleteCharAt(0);
					genericType.addAll(extractGenericTypeArgs(typeString));	
				}
								
			}
			else if(next==','){
				typeString.deleteCharAt(0);
				genericType.add(new GenericType(typeName,null));
				genericType.addAll(extractGenericTypeArgs(typeString));			
			}
			else if(next=='>'){
				genericType.add(new GenericType(typeName,null));
			}

		}
		return genericType;
	}
	

	
	private static class GenericType{
		
		private String type;
		private Collection<GenericType> typeArgs;
		
		public GenericType(String type,Collection<GenericType> typeArgs){
			this.type = type;
			this.typeArgs = typeArgs;
		}
		
		public String getType(){
			return type;
		}
		
		public Collection<GenericType> getTypeArgs(){
			return typeArgs;
		}
		
		public boolean hasTypeArgs(){
			return (typeArgs!=null)&&(!getTypeArgs().isEmpty());
		}
		
		public String toString(){
			String result = type;
			if(hasTypeArgs()){
				result=result+"<";
				Iterator<GenericType> it = getTypeArgs().iterator();
				while(it.hasNext()){
					GenericType next = it.next();
					result=result+next.toString();
					if(it.hasNext())
						result=result+",";
				}
				result+=">";
			}
			return result;
		}
	}
	
	
	/**
	 * Looks up an EClass for a given type declaration in JastAdd. If the lookup fails, i.e., iff the 
	 * type declaration has not yet a corresponding EClass, a new one is created, added to the map and
	 * returned. 
	 * 
	 * @param typeDecl - a JastAdd TypeDecl
	 * @return The corresponding EClass for the given TypeDecl.
	 */
	
	private EClass lookupEClass(TypeDecl typeDecl){
		EClass eClass = null;
		if(!eClassMap.containsKey(typeDecl)){
			EClass clz = EcoreFactory.eINSTANCE.createEClass();
			clz.setName(typeDecl.getIdDecl().getID());
			eClassMap.put(typeDecl,clz);
		}
		eClass = eClassMap.get(typeDecl);
		return eClass;
	}
	
	private EClass lookupEClass(String typeName){
		return grammar.lookup(typeName)!=null?lookupEClass(grammar.lookup(typeName)):null;
	}
	
	
	/**
	 * Realizes the mapping of non AST types in Jastadd to EDataTypes in Ecore.
	 * 
	 * @param typeName
	 * @return
	 */
	private EDataType lookUpEDataType(String typeName){
		if(factory.getEcorePackage().getEClassifier(typeName)!=null){
			return (EDataType) factory.getEcorePackage().getEClassifier(typeName);
		}
		else {
			String eTypeName = "E".concat(typeName.substring(0,1).toUpperCase().concat(typeName.substring(1)));
			if(factory.getEcorePackage().getEClassifier(eTypeName)!=null){
				return (EDataType) factory.getEcorePackage().getEClassifier(eTypeName);
			}
			else{
				if(!eDataTypeMap.containsKey(typeName)){
					EDataType eDataType = factory.createEDataType();
					eDataType.setName(typeName);
					eDataType.setInstanceTypeName(typeName);
					eDataTypeMap.put(typeName, eDataType);
				}
				return eDataTypeMap.get(typeName);
			}
		}
				
	}

}
