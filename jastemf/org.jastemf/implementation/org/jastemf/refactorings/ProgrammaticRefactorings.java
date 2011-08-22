package org.jastemf.refactorings;

import java.util.Collection;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.corext.refactoring.rename.JavaRenameProcessor;
import org.eclipse.jdt.internal.corext.refactoring.rename.RenameFieldProcessor;
import org.eclipse.jdt.internal.corext.refactoring.rename.RenameCompilationUnitProcessor;
import org.eclipse.jdt.internal.corext.refactoring.rename.RenameNonVirtualMethodProcessor;
import org.eclipse.jdt.internal.corext.refactoring.rename.RenameVirtualMethodProcessor;
import org.eclipse.ltk.core.refactoring.CheckConditionsOperation;
import org.eclipse.ltk.core.refactoring.CreateChangeOperation;
import org.eclipse.ltk.core.refactoring.PerformChangeOperation;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;
import org.jastemf.IIntegrationContext;
import org.jastemf.JastEMFException;
import org.jastemf.util.IOSupport;

import java.util.logging.Logger;




/**
 * Implementation of the API renamings required for merging JastAdd classes and EMF classes. 
 * 
 * @author Sven Karol
 *
 */
@SuppressWarnings("restriction")
public class ProgrammaticRefactorings {	
	
	/**
	 * 	Rename each JastAdd AST access method to prevent a name clash with its
	 * 	associated EMF containment element access method. Additionally, rename
	 * 	JastAdd attribute methods that represent derived attributes or
	 *  non-containment references w.r.t. EMF naming schemes (e.g. "get"
	 *	prefixes for ordinary attributes and "is" prefixes for attributes returning
	 *	a boolean value). Furthermore, rename JastAdd attribute methods that
	 *	represent metamodel operations iff one of their parameter types or their
	 *	return type consist of an AST node type (e.g. as part of a generic type
	 *	parameter). That is neccessary, since refactorings will change this type to
	 *	its concrete EMF implementation and consequently the refactored JastAdd
	 *	attribute method is not type-compatible with its associated metamodel
	 *	declaration.
	 * 
	 * 
	 * @param context
	 * @throws JastEMFException
	 */
	public static void renameASTClasses(IIntegrationContext context) throws JastEMFException{
		IPackageFragment packageFragment = context.astPackageFragment();
		ICompilationUnit icu = packageFragment.getCompilationUnit("List.java");
		try {
			performRename(icu, "ASTList");
		} catch (CoreException e) {
			e.printStackTrace();
		}
		renameClassMembers(context.genmodel().getGenPackages(),packageFragment);
		renameClasses(context.genmodel().getGenPackages(),packageFragment);
		
	}
	
	
	private static void renameClasses(EList<GenPackage> genPackages,
			IPackageFragment packageFragment) throws JastEMFException {
		for(GenPackage genPackage:genPackages){
			for(GenClass genClass:genPackage.getGenClasses()){
				String newname = genClass.getClassName();
				ICompilationUnit correspondingCU = packageFragment.getCompilationUnit(genClass.getName()+".java");
				if(correspondingCU!=null)
				try {
					IOSupport.log("Renaming AST class " + genClass.getClassName() + ".");
					performRename(correspondingCU, newname);
				} catch (CoreException e) {
					throw new JastEMFException("Could not rename GenClass '" + genClass.getClassName() + "'.",e);
				}
				
				try {
					ICompilationUnit renamedCU = packageFragment.getCompilationUnit(genClass.getClassName()+".java");
					IPackageFragmentRoot packageRoot = (IPackageFragmentRoot) packageFragment.getParent();
					IPackageFragment targetPackage = packageRoot.getPackageFragment(genPackage.getPackageName());
					if(!targetPackage.exists())
						targetPackage = packageRoot.createPackageFragment(genPackage.getClassPackageName(),true,null);
					renamedCU.move(targetPackage, null,null,true,null);
				} catch (JavaModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			renameClasses(genPackage.getNestedGenPackages(), packageFragment);
		}
	}


	private static void renameClassMembers(Collection<GenPackage> genPackages, IPackageFragment astPackageFragment) throws JastEMFException{
		
		for(GenPackage genPackage:genPackages){
			for(GenClass genClass:genPackage.getGenClasses()){
				
				IOSupport.log("Accessing members of "+genClass.getName());
			
				//System.out.println("Accessing members of "+genClass.getName());
				ICompilationUnit correspondingCU = astPackageFragment.getCompilationUnit(genClass.getName()+".java");
				if(correspondingCU!=null){
					try {
						IOSupport.log("Adjusting methods ...");
						renameAccessMethods(genClass,correspondingCU);
						IOSupport.log("Renaming fields ...");
						renameFields(genClass,correspondingCU);
						IOSupport.log("Renaming operations ...");
						renameOperationAccessors(genClass,correspondingCU);

					} catch (CoreException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			renameClassMembers(genPackage.getNestedGenPackages(), astPackageFragment);
		}
	
	}
	
	public static void renameAccessMethods(GenClass genClass, ICompilationUnit icu) throws CoreException{
		IType type = icu.findPrimaryType();

		for(GenFeature genFeature:genClass.getGenFeatures()){
			
			//renaming accessors that correspond to EAttributes in Ecore
			if(genFeature.getEcoreFeature() instanceof EAttribute){
				replaceEAttributeAccessors(genFeature, type);
			}
			else if(genFeature.getEcoreFeature() instanceof EReference){
				renameEReferenceAccessor(genFeature, type);
			}
		}
	}
	
	/**
	 * The following renames JastAdd attributes and terminals that correspond to EReferences in the metamodel.
	 * <br/><b>Case 1</b>: For non-containments, an attribute accessor 'acc()' has to be renamed to the corresponding accessor 
	 * 'getAcc()' in the code generated by the EMF.
	 * <br/>// <b>Case 2</b>: For derived containments (nonterminal attributes), an attribute accessor 'acc()' has to be renamed to the corresponding accessor 
	 * // 'getAcc()' in the code generated by the EMF.
	 * <br/><b>Case 3</b>: For containment nonterminals with upper bound = 1, we rename the JastAdd specific accessors with the prefix 'jastadd_get' 
	 * to ensure that 1., The EMF methods are inserted appropriately when merging the classes (i.e., 'getAcc()' will be 
	 * implemented by generated EMF code and will have the Interface type as return type) and 2., to still have the 
	 * original jastadd getters available which have the implementation class as return type. Note that the jastadd 
	 * getters still use getChild(int i) to obtain the actual child node.
	 * <br/>(<b>Case 4</b>: We do not need to handle containment nonterminals with unbounded lists because name clashes with 
	 * getters do not occur, because for a terminal 'acc' JastAdd generates 'getaccList()', 'getaccListNoTransform()', getacc(int i),
	 * 'getaccs()', 'getaccNoTransform()' while the EMF only generates 'getAcc()'.) 
	 * 
	 * @param genFeature
	 * @param type
	 * @throws CoreException
	 */
	public static void renameEReferenceAccessor(GenFeature genFeature,
			IType type) throws CoreException {
		EReference eReference = (EReference) genFeature.getEcoreFeature();
		String oldGetterName = null;
		String newGetterName = null;
		//case 1: reference attribute
		if(!eReference.isContainment()){
			oldGetterName = genFeature.getName();
			newGetterName = "get"+genFeature.getAccessorName();
			if(!oldGetterName.equals(newGetterName)){
				IMethod getter = type.getMethod(oldGetterName, new String[0]);
				if(getter.exists()){
					IOSupport.log("Adding new delegatee for " + oldGetterName + ".");
					String getterReturnType = Signature.getSignatureSimpleName( getter.getReturnType());
					String newMethod = "public " + getterReturnType +" "+newGetterName+"(){ return " + oldGetterName + "();}";
					type.createMethod(newMethod,null,true,null);
				}
				else{
					IOSupport.warn("No acccessor for non-containment reference '"+oldGetterName+"' found, skipping.");
				}
			}
		}
		//case 2: nonterminal attribute
		else if(eReference.isContainment()&&genFeature.isDerived()){
			oldGetterName = "get"+genFeature.getName();
			newGetterName = "get"+genFeature.getAccessorName();
			if(!oldGetterName.equals(newGetterName)){
				IMethod getter = type.getMethod(oldGetterName, new String[0]);
				if(getter.exists()){
					IOSupport.log("Adding new delegatee for " + oldGetterName + ".");
					String getterReturnType = Signature.getSignatureSimpleName( getter.getReturnType());
					String newMethod = "public " + getterReturnType +" "+newGetterName+"(){ return " + oldGetterName + "();}";
					type.createMethod(newMethod,null,true,null);
				}
				else{
					IOSupport.warn("No acccessor for NTA non-containment reference '"+oldGetterName+"' found, skipping.");
				}
			}
		
		}
		//case 3: usual containments
		else if(!eReference.isMany()){
			//if signatures are in conflict, add jastadd_ prefix 
			if(genFeature.getName().equals(genFeature.getAccessorName())){
			
				oldGetterName = genFeature.getGetAccessor();
				IOSupport.log("Inserting replacement for '"+oldGetterName+"' for safe containment access.");
				IMethod getter = type.getMethod(oldGetterName, new String[0]);
				String getterReturnType = Signature.getSignatureSimpleName( getter.getReturnType());
				String newMethod = "public " + getterReturnType +" "+oldGetterName+"(){ return ("+genFeature.getTypeGenClassifier().getName()+")"+genFeature.getSafeName()+";}";
				getter.delete(false,null);
				type.createMethod(newMethod, null,true,null);
				
				//case 3a rename setters
				String oldSetterName = "set" + genFeature.getName();
				String[] parameterSignature = new String[1];
				String typeName = getRefactoringTypeString(eReference.getEGenericType());
				if(typeName==null)
					typeName = eReference.getEReferenceType().getName();
				parameterSignature[0] = typeName;
		
				IMethod setter = type.getMethod(oldSetterName, parameterSignature);
				if(setter.exists()){
					String paramName = setter.getParameterNames()[0];
					String paramType = Signature.getSignatureSimpleName(setter.getParameterTypes()[0]);
					setter.delete(true,null);
					String newSetterMethod = "public void "+oldSetterName+"("+paramType+" "+paramName+"){ this." + genFeature.getSafeName() + " = "+paramName+";}";		
					type.createMethod(newSetterMethod,null,true,null);
				}
			}

		}
		else{
			//case 4: list containments ... do not rename	
		}
	}


	/**
	 * TODO: Outdated documentation, getter/setter delegatees are introduced
	 * TODO: Could also be moved to repository adaptations, see line 515 there(!)
	 * The following renames accessor methods for attributes in JastAdd to match with the derived EAttributes accessors
	 * in ECore.
	 * <br/> 
     * <b>Case 1</b>: Consider a String attribute 'name' in JastAdd. The generated accessor will be 'name()'. 
     * In contrast, the EMF always generates camel case getters for EAttributes, i.e., the EMF generates 'getName()'
     * as accessor method (if 'name' was a boolean attribute, it would generate 'isName()').
	 * <br/>
	 * <b>Case 2</b>: Consider a String terminal 'name' in JastAdd. The generated accessor will be 'getname()'. 
	 * The EMF will generate the same camel case accessors as mentioned above. Hence, we also have to 
	 * rename the accessors for terminals (= non derived EAttributes). 
	 * 
	 * @param genFeature
	 * @param type
	 * @throws CoreException
	 */
	public static void replaceEAttributeAccessors(GenFeature genFeature,IType type) throws CoreException{
		EAttribute eAttribute = (EAttribute)genFeature.getEcoreFeature();
		//replacing getter			
		{
			String oldGetterName = (genFeature.isDerived()?"":"get")+genFeature.getName();
		
			String newGetterName = null;
			if(eAttribute.getEType().getName().equals("EBoolean")&&
					!eAttribute.isMany()){
				newGetterName = "is" + toFirstUpper(genFeature.getName());
			}
			else{
				newGetterName = "get" + toFirstUpper(genFeature.getName());
			}
			
			if(!oldGetterName.equals(newGetterName)){
				IMethod getter = type.getMethod(oldGetterName, new String[0]);
				if(getter.exists()){
					String getterReturnType =Signature.getSignatureSimpleName( getter.getReturnType());
					String newMethod = null;
					if(eAttribute.isDerived()){
						//Attribute: EMF getter needs to call attribute computation method of JastAdd
						newMethod = "public "+ getterReturnType +" "+newGetterName+"(){ return " + oldGetterName + "();}";	
					}
					else{
						//Terminal: Access new repository by delegating to EMF getter
						getter.delete(true,null);
						newMethod = "public "+ getterReturnType +" "+oldGetterName+"(){ return " + newGetterName + "();}";												
					}
					type.createMethod(newMethod,null,true,null);
				}
			}
		}
		
		//replacing setter
		if(!genFeature.isDerived()&&!genFeature.getEcoreFeature().isMany()){
			String oldSetterName = "set" + genFeature.getName();
			String newSetterName = "set" + toFirstUpper(genFeature.getName());
		
			String[] parameterSignature = new String[1];
			parameterSignature[0] = getSimpleSignature(eAttribute.getEAttributeType());
			
			if(!oldSetterName.equals(newSetterName)){
				IMethod setter = type.getMethod(oldSetterName, parameterSignature);
				if(setter.exists()){
					String paramName = setter.getParameterNames()[0];
					String paramType = Signature.getSignatureSimpleName(setter.getParameterTypes()[0]);
					setter.delete(true,null);
					String newMethod = "public void "+oldSetterName+"("+paramType+" "+paramName+"){ "+ newSetterName + "(" + paramName + ");}";		
					type.createMethod(newMethod,null,true,null);
				}
			}

		}
	}
	
	/**
	 * Generates refactorings for supporting imperative mode, i.e., setting values of lazy attributes.
	 * Lazy attributes are by default stored in a field attr_value which needs to be value only. 
	 * 
	 * @param genClass
	 * @param icu
	 * @throws CoreException 
	 */
	public static void renameFields(GenClass genClass, ICompilationUnit icu) throws CoreException{
		IType type = icu.findPrimaryType();
		for(GenFeature genFeature:genClass.getGenFeatures()){
			if(genFeature.getEcoreFeature() instanceof EReference){
				if(genFeature.isDerived() && genFeature.isChangeable()){
					String oldFieldName = genFeature.getName()+"_value";
					String newFieldName = genFeature.getName();
					IField field = type.getField(oldFieldName);
					if(field.exists()){
						performRename(field,newFieldName);
					}
					else{
						IOSupport.warn("Field '"+ oldFieldName + "' was expected for '"+genFeature.getName()+"' (changeable and derived). Potential reason: attribute not declared lazy.");
					}
				}
			}
		}
	}
	
	public static void renameOperationAccessors(GenClass genClass, ICompilationUnit icu) throws CoreException{
		IType type = icu.findPrimaryType();
		for(GenOperation genOperation:genClass.getGenOperations()){
			EOperation eOperation = genOperation.getEcoreOperation();
			
			if(eOperation.getETypeParameters().size()>0||eOperation.isMany()){
				String oldName = genOperation.getName();
				String newName = "jastadd_"+oldName;
				String[] parameters = new String[eOperation.getEParameters().size()];
				
				for(int i=0;i<parameters.length;i++){
					parameters[i] = getRefactoringTypeString(eOperation.getEParameters().get(i));
				}
				
				IMethod method = type.getMethod(oldName,parameters);
				if(method.exists()){
					performRename(method,newName);
				}
			}
		}
	}
	
	public static String getSimpleSignature(EDataType eDataType){
		String simpleName = null;
		if(eDataType.getInstanceClass()!=null){
			simpleName = eDataType.getInstanceClass().getSimpleName();
		}
		else{
			simpleName = eDataType.getInstanceClassName().substring(eDataType.getInstanceClassName().lastIndexOf('.')+1);
		}
		return Signature.createTypeSignature(simpleName,false);
	}
	
	public static String getRefactoringTypeString(EParameter parameter){
		return parameter.isMany()?
				("QEList\\&lt;"+getRefactoringTypeString(parameter.getEGenericType())+"&gt;;")
						:getRefactoringTypeString(parameter.getEGenericType());
	}
	
	public static String getRefactoringTypeString(EGenericType genericType){
		String simpleType = null;
		if(genericType.getEClassifier().getInstanceClass()!=null){
			simpleType = genericType.getEClassifier().getInstanceClass().getSimpleName();
		}
		else{
			
			simpleType = genericType.getEClassifier().getName();
		}
		String result = "Q"+simpleType;
		if(!genericType.getETypeArguments().isEmpty()){
			result = result + "\\&lt;";
			for(EGenericType typeArg:genericType.getETypeArguments()){
				result = result + getRefactoringTypeString(typeArg);
			}
			result = result + "&gt;";
		}
		result = result + ";";
		return result;
	}
	
	public static boolean performRename(ICompilationUnit cu, String newname) throws CoreException {
		RenameCompilationUnitProcessor processor = new RenameCompilationUnitProcessor(cu);
		return performRename(processor, newname);
	}
	
	public static boolean performRename(IMethod method, String newname) throws CoreException {		 
		if(method.getElementName().equals(newname))
			return true;
		JavaRenameProcessor renameProcessor =new RenameVirtualMethodProcessor(method);
		return performRename(renameProcessor, newname);
	}
	
	public static boolean performRename(IField field, String newname) throws CoreException {		 
		if(field.getElementName().equals(newname))
			return true;
		RenameFieldProcessor processor = new RenameFieldProcessor(field);
		return performRename(processor, newname);
	}
	
	public static boolean performRename(JavaRenameProcessor processor, String newname) throws CoreException{
		RenameRefactoring refactoring = new RenameRefactoring(processor);
		processor.setNewElementName(newname);
		
		/*CheckConditionsOperation conditionChecker = new CheckConditionsOperation(refactoring, CheckConditionsOperation.FINAL_CONDITIONS){
			
			RefactoringStatus status = RefactoringStatus.createInfoStatus("OK");
			
			public void run(IProgressMonitor pm) throws CoreException {
				if (pm == null)
					pm= new NullProgressMonitor();
				try {
					//this.getRefactoring().checkAllConditions(pm);
					//fStatus=RefactoringStatus.createInfoStatus("OK");
					
				} finally {
					pm.done();
				}
			}
			
			public RefactoringStatus getStatus() {
				return status;
			}
		};*/
		 
		CheckConditionsOperation conditionChecker = new CheckConditionsOperation(refactoring, CheckConditionsOperation.FINAL_CONDITIONS);
		CreateChangeOperation create = new CreateChangeOperation(conditionChecker, RefactoringStatus.FATAL);
		PerformChangeOperation change = new PerformChangeOperation(create);
		 
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.run(change, new NullProgressMonitor());
		  
		return change.changeExecuted();
	}
	
	public static String toFirstUpper(String string){
		if(string.length()==0)
			return string;
		String upperLetter = string.substring(0,1).toUpperCase();
		if(string.length()==1)
			return upperLetter;
		return upperLetter + string.substring(1);
	}
	
}
