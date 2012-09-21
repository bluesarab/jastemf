package ast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Primitive template engine based on string matching with regular expressions.
 * 
 * TODO: replace implementation with better lexer
 *  
 * @author skarol
 *
 */
public class Template {
	
	private String codeBlock = null;
	private static final String JAVA_CHARS = "[\\w\\s\\(\\),\\+\\-\\*;:_\\.\\{\\}=&\\$\"\'\\]\\[!§]";
	private static final String INNER_VAR_PATTERN = "#VAR#(\\w)+#(" + JAVA_CHARS + ")+";
	private static final String VARIANTS_TAG = "#VARIANTS#";
	private static final String VARIANT_TAG = "#VAR#";
	private static final String PROTOTYPE_TAG = "#PROTOTYPE#";
	
	
	public Template(String templateCode){
		this.codeBlock = templateCode;
	}
	
	public String getCodeBlock() {
		return codeBlock;
	}

	public void setCodeBlock(String codeBlock) {
		this.codeBlock = codeBlock;
	}
	
	private String buildVariantsPattern(String name){
		final String variantsTag = VARIANTS_TAG + name + "#";
		final String patternString = variantsTag + "(\\s)*(" + INNER_VAR_PATTERN + ")*" +  variantsTag;
		return patternString;
	}
	
	private static String buildPrototypePattern(String name){
		final String prototypeTag = buildPrototypeTag(name);
		final String patternString = prototypeTag + "(" + JAVA_CHARS + ")*"+ prototypeTag;
		return patternString;
	}
	
	private static String buildPrototypeTag(String name){
		return  PROTOTYPE_TAG + name + "#";
	}
	
	public boolean extractVariant(String variantListName, String variantName){
		String innerInstancePattern = VARIANT_TAG + variantName + "#((" + JAVA_CHARS + ")+)";
		String patternString = buildVariantsPattern(variantListName);
		//System.out.println(patternString);
		Pattern outerPattern = Pattern.compile(patternString);
		Matcher outerMatcher = outerPattern.matcher(codeBlock);
		Pattern innerPattern = Pattern.compile(innerInstancePattern);
		boolean patternFound = false;
		while(outerMatcher.find()){
			String currentGroup = outerMatcher.group();
			Matcher innerMatcher = innerPattern.matcher(currentGroup);
			if(innerMatcher.find()){
				String variantString = innerMatcher.group(1).replaceAll("§", "#");
				codeBlock = codeBlock.substring(0,outerMatcher.start()) + 
						    variantString +
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
	
	public boolean instanciatePrototype(String prototypeName, String... param){
		Map<String,String> map = new HashMap<String, String>();
		for(int i=0;i<param.length;i++){
			map.put(param[i++], param[i]);
		}
		return instanciatePrototype(prototypeName,map);
	}
	
	public boolean instanciatePrototype(String prototypeName, String slotName, String value){
		Map<String,String> map = new HashMap<String, String>();
		map.put(slotName, value);
		return instanciatePrototype(prototypeName,map);
	}
	
	public boolean instanciatePrototype(String prototypeName, Map<String,String> instanceValues){
		String tag = buildPrototypeTag(prototypeName);
		String patternString = buildPrototypePattern(prototypeName);
		Pattern pattern = Pattern.compile(patternString);
		//System.out.println(patternString);
		Matcher matcher = pattern.matcher(codeBlock);
		boolean patternFound = false;
		if(matcher.find()){
			String current = matcher.group();
			current = current.substring(tag.length(),current.length()-tag.length()).replaceAll("§", "#");
			//System.out.println(current);
			Template t = new Template(current);
			for(String slotName:instanceValues.keySet()){
				t.bind(slotName,instanceValues.get(slotName));
			}
			codeBlock = codeBlock.substring(0,matcher.end()+1) + 
				    t.getCodeBlock() +
				    codeBlock.substring(matcher.end()+1,codeBlock.length());
	
			patternFound = true;
		}	
		return patternFound;
	}
	
	public boolean removePrototype(String prototypeName){
		String patternString = buildPrototypePattern(prototypeName);
		Pattern pattern = Pattern.compile(patternString);
		//System.out.println(patternString);
		Matcher matcher = pattern.matcher(codeBlock);
		boolean patternFound = false;
		if(matcher.find()){
			codeBlock = codeBlock.substring(0,matcher.start()) + 
				    codeBlock.substring(matcher.end()+1,codeBlock.length());
			patternFound = true;
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
