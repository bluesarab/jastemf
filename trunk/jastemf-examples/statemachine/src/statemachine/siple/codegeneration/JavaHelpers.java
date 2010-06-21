package statemachine.siple.codegeneration;

public class JavaHelpers {

	public static String convertToValidOperationname(String toConvert) {
		toConvert = toConvert.replaceAll(" ", "_");
		toConvert = toConvert.replaceAll("-", "_");
		toConvert = toConvert.replaceAll(">", "_");
		return toConvert;
		
	}
	
}
