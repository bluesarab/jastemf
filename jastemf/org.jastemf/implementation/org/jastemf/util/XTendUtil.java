package org.jastemf.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClassifier;


/**
 * This class holds JAVA extensions for OAW that can be used in xTend files (*.ext).
 * 
 * @author skarol
 */

public class XTendUtil {
	
	
	/**
	 * Converts a camel case name into a upper case name. Parts in a name 
	 * are separated with underscores.
	 * @param camelCaseName , e.g. blihBlahBlub
	 * @return Upper case name with underscores, e.g. BLIH_BLAH_BLUB.
	 */
	
	public static String toUpperCaseUnderline(String camelCaseName){	
		char[] camelCaseNameArray = camelCaseName.toCharArray();
		String result = "";
		for(int i=0;i<camelCaseNameArray.length;i++){
			if(i>0&&isUpperCase(camelCaseNameArray[i])){
				if(!isUpperCase(camelCaseNameArray[i-1]))
					result += "_";
			}
			result += camelCaseNameArray[i];
		}
		return result.toUpperCase();
	}
	
	private static boolean isUpperCase(char c){
		return c>='A'&&c<='Z';
	}
	
	/**
	 * Checks if an EClassifier occurs in a list of GenPackages.
	 * 
	 * @param genPackageList The list of GenPackages to be searched.
	 * @param classifier The EClassifier that has to be looked-up.
	 * @return True, if the classifier is included in the list of GenPackages.
	 */
	
	public static boolean classifierInGenPackages(List<GenPackage> genPackageList, EClassifier classifier){
		for(GenPackage genPackage:genPackageList){
			for(EClassifier eClassifier:genPackage.getEcorePackage().getEClassifiers()){
				if(eClassifier.equals(classifier))
					return true;
			}
		}
		return false;
	}

	/**
	 * Compute a string representation of the current date w.r.t. the default
	 * time zone and locale.
	 * 
	 * @return The current date.
	 */
	public static String timeStamp() {
		Calendar calendar = Calendar.getInstance();
		return new SimpleDateFormat().format(calendar.getTime());
	}
	
	/**
	 * Derives the simple instance type name from a given classifier.
	 * 
	 * 
	 * @param type
	 * @return
	 */
	public static String getSimpleInstanceTypeName(EClassifier type){
		String result = null;
		if(type.getInstanceClass()!=null){
			result =  type.getInstanceClass().getSimpleName();
		}
		else if(type.getInstanceClassName()!=null){
			result = type.getInstanceClassName();
			if(result.lastIndexOf('.')>-1){
				result = result.substring(result.lastIndexOf('.')+1);
			}
			
		}
		else {
			result = type.getName();
		}
		return result;
	}
	
}
