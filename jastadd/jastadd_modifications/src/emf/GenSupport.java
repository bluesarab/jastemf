package emf;

public class GenSupport {
	
	public static String computeVarName(String astName){
		return astName.toLowerCase();
	}
	
	public static String computeAccName(String astName){
		String result = "";
		if(astName.length()>0){
			result = astName.substring(0, 1).toUpperCase();
			result += astName.substring(1);
		}
		return result;
	}

}
