package org.jastemf.refactorings;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.corext.refactoring.rename.JavaRenameProcessor;
import org.eclipse.jdt.internal.corext.refactoring.rename.RenameFieldProcessor;
import org.eclipse.jdt.internal.corext.refactoring.rename.RenameCompilationUnitProcessor;
import org.eclipse.jdt.internal.corext.refactoring.rename.RenameVirtualMethodProcessor;
import org.eclipse.ltk.core.refactoring.CheckConditionsOperation;
import org.eclipse.ltk.core.refactoring.CreateChangeOperation;
import org.eclipse.ltk.core.refactoring.PerformChangeOperation;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;
import org.jastemf.IIntegrationContext;
import org.jastemf.JastEMFException;
import org.jastemf.util.IOSupport;

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
	 *  NOTE: this class is intended to be removed as soon as the class names are also 
	 *  handled by the EMF backend of JastAdd.
	 * 
	 * @param context
	 * @throws JastEMFException
	 */
	public static void renameASTClasses(IIntegrationContext context) throws JastEMFException{
		IPackageFragment packageFragment = context.astPackageFragment();
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
					throw new JastEMFException("Could not rename AST class '" + genClass.getClassName() + "'.",e);
				}
				
				try {
					ICompilationUnit renamedCU = packageFragment.getCompilationUnit(genClass.getClassName()+".java");
					IPackageFragmentRoot packageRoot = (IPackageFragmentRoot) packageFragment.getParent();
					IPackageFragment targetPackage = packageRoot.getPackageFragment(genPackage.getClassPackageName());
					if(!targetPackage.exists())
						targetPackage = packageRoot.createPackageFragment(genPackage.getClassPackageName(),true,null);
					renamedCU.move(targetPackage, null,null,true,null);
				} catch (JavaModelException e) {
					throw new JastEMFException("Could not move AST class '" + genClass.getClassName() + "'.",e);
				}
			}
			renameClasses(genPackage.getNestedGenPackages(), packageFragment);
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
	    long time1 = System.currentTimeMillis();
		RenameCompilationUnitProcessor processor = new RenameCompilationUnitProcessor(cu);
		boolean result = performRename(processor, newname);
		long time2 = System.currentTimeMillis();
		long resultingTime = time2-time1;
		IOSupport.getLogger().info("Compilation unit rename took " +resultingTime+"ms.");
		return result;
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
