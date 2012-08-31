package ast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Primitive template engine based on string matching with regular expressions.
 *  
 * @author skarol
 *
 */
public class Template {
	
	private String codeBlock = null;
	private static final String JAVA_CHARS = "[\\w\\s\\(\\),\\+\\-\\*;_\\.\\{\\}=&\\$\"\'\\]\\[!]";
	
	public Template(String templateCode){
		this.codeBlock = templateCode;
	}
	
	public String getCodeBlock() {
		return codeBlock;
	}

	public void setCodeBlock(String codeBlock) {
		this.codeBlock = codeBlock;
	}
	
	private String buildVariantsPattern(String variantListPattern){
		String variantsTag = "#VARIANTS#"+variantListPattern+"#";
		String innerVarPattern = "#VAR#(\\w)+#(" + JAVA_CHARS + ")+";
		String patternString = variantsTag + "(\\s)*(" + innerVarPattern + ")*" +  variantsTag;
		return patternString;
	}
	
	
	public boolean extractVariant(String variantListName, String variantName){
		String innerInstancePattern = "#VAR#" + variantName + "#((" + JAVA_CHARS + ")+)";
		String patternString = buildVariantsPattern(variantListName);
		System.out.println(patternString);
		Pattern outerPattern = Pattern.compile(patternString);
		Matcher outerMatcher = outerPattern.matcher(codeBlock);
		Pattern innerPattern = Pattern.compile(innerInstancePattern);
		boolean patternFound = false;
		while(outerMatcher.find()){
			String currentGroup = outerMatcher.group();
			Matcher innerMatcher = innerPattern.matcher(currentGroup);
			if(innerMatcher.find()){
				String variantString = innerMatcher.group(1);
				codeBlock = codeBlock.substring(0,outerMatcher.start()) + 
						    variantString.trim()+
						    codeBlock.substring(outerMatcher.end(),codeBlock.length());
				outerMatcher = outerPattern.matcher(codeBlock);
				patternFound = true;
			}
			else{
				//inner pattern missed
			}		
		}
		return patternFound;
	}
	
	public boolean bind(String slotName, String replacement){
		String pointName =  "#" + slotName + "#";
		if(codeBlock.contains(pointName)){
			codeBlock = codeBlock.replaceAll(pointName,replacement);
			return true;
		}		
		return false;
	}
	
	public boolean bindOne(String slotName, String replacement){
		String pointName =  "#" + slotName + "#";
		if(codeBlock.contains(pointName)){
			codeBlock = codeBlock.replaceFirst(pointName, replacement);
			return true;
		}
		return false;
	}
	
	public boolean extend(String hookName, String argument){
		String pointName =  "#" + hookName + "#";
		if(codeBlock.contains(pointName)){
			codeBlock = codeBlock.replaceAll(pointName, pointName + "\n" + argument);
			return true;
		}
		return false;
	}
	
	public boolean extendOne(String hookName, String argument){
		String pointName =  "#" + hookName + "#";
		if(codeBlock.contains(pointName)){
			codeBlock = codeBlock.replaceFirst(pointName, pointName + "\n" + argument);
			return true;
		}
		return false;
	}
	
	public boolean remove(String pointName){
		return bind(pointName,"");
	}
	
	public boolean removeOne(String pointName){
		return bindOne(pointName,"");
	}
	
	/** 
	 * @param pointName
	 * @return true, iff a slot or hook with the given name is available in template 
	 */
	public boolean hasPoint(String pointName){
		String hedgedPointName =  "#" + pointName + "#";
		if(codeBlock.contains(hedgedPointName))
			return true;
		return false;
	}
	
	/** 
	 * @param pointName
	 * @return true, iff any slot or hook with the given name is available in template, 
	 * note that currently #variants# and #var# are also matched as slots. 
	 */
	public boolean hasPoints(){
		return hasPoint("(" + JAVA_CHARS + ")+");
	}
	
	/**
	 * @return true, iff variants with the given name without inner points or variants 
	 * exist in template
	 */
	public boolean hasVariant(String variantListName){
		Pattern outerPattern = Pattern.compile(buildVariantsPattern(variantListName));
		Matcher outerMatcher = outerPattern.matcher(codeBlock);
		return outerMatcher.find();
	}
	
	/**
	 * @return true, iff variants without inner points or variants are available in template
	 */
	public boolean hasVariants(){
		Pattern outerPattern = Pattern.compile(buildVariantsPattern( "(" + JAVA_CHARS + ")+" ));
		Matcher outerMatcher = outerPattern.matcher(codeBlock);
		return outerMatcher.find();
	}
	
	public String toString(){
		return codeBlock.toString();
	}
	
}
