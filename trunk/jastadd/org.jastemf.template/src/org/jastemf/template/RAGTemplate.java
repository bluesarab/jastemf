package org.jastemf.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jastemf.template.ast.ASTNode;
import org.jastemf.template.ast.GenericSource;
import org.jastemf.template.ast.Prototype;
import org.jastemf.template.ast.RootElement;
import org.jastemf.template.ast.Slot;
import org.jastemf.template.ast.VariantList;

import xtc.parser.ParseError;
import xtc.parser.Result;

public class RAGTemplate implements TemplateAPI {
	
	private String codeBlock = null;
	private GenericSource ast = null;
	private String name = null;
	
	public RAGTemplate(String code) throws CodeGenException{
		initialize(code,"<internal.file>");
	}
	
	public RAGTemplate(String code, String name) throws CodeGenException{
		initialize(code,name);
	}
	
	private synchronized void initialize(String code, String name) throws CodeGenException{
		codeBlock = code;
		StringReader reader = new StringReader(code);
		TemplateParser parser = new TemplateParser(reader,name);
		Result result = null;
		try {
			result = parser.pGenericSource_Declaration(0);
		} catch (IOException e) {
			throw new CodeGenException("Could not initialize template because of IO problem.",e);
		}
		
		if(result instanceof ParseError){
			String message = "Problem in template code:"+result.parseError().msg+"("+parser.location(result.index).line+","+parser.location(result.index).column+")";
			throw new CodeGenException(message);
		}
		else if(!(result.semanticValue() instanceof GenericSource)){
			throw new CodeGenException("Unexpected result type from parser ("+result.semanticValue().getClass().getName()+").");
		}
		else{
			ast = (GenericSource)result.semanticValue();
		}
		this.name = name;
	}
	
	private synchronized void clear(){
		codeBlock = null;
		ast = null;
		name = null;
	}
	
	@Override
	public String getCodeBlock() {
		return codeBlock;
	}

	@Override
	public void setCodeBlock(String codeBlock) throws CodeGenException {
		String name = this.name;
		clear();
		initialize(codeBlock,name);
	}

	@Override
	public boolean extractVariant(String variantListName, String variantName) {
		VariantList variants = (VariantList) ast.findVariants(variantListName);
		while(variants!=null){
			variants.extract(variantName);
			variants = (VariantList) ast.findVariants(variantListName);
		}
		return false;
	}

	@Override
	public boolean instanciatePrototype(String prototypeName, String... param) {
		Map<String,String> map = new HashMap<String, String>();
		for(int i=0;i<param.length;i++){
			map.put(param[i++], param[i]);
		}
		return instanciatePrototype(prototypeName,map);
	}

	@Override
	public boolean instanciatePrototype(String prototypeName, String slotName,
			String value) {
		Map<String,String> valueMap = new HashMap<String,String>();
		valueMap.put(slotName, value);	
		return instanciatePrototype(prototypeName, valueMap);
	}

	@Override
	public boolean instanciatePrototype(String prototypeName,
			Map<String, String> instanceValues) {
		Prototype prototype = (Prototype) ast.findPrototype(prototypeName);
		if(prototype!=null){
			return prototype.instanciate(instanceValues);
		}
		return false;
	}

	@Override
	public boolean removePrototype(String prototypeName) {
		Prototype prototype = (Prototype) ast.findPrototype(prototypeName);
		if(prototype!=null){
			return prototype.removeSelf();
		}
		return false;
	}

	@Override
	public boolean bind(String slotName, String replacement) {
		boolean oneBound = false;
		while(bindOne(slotName,replacement)){
			oneBound = true;
		}	
		return oneBound;
	}

	@Override
	public boolean bindOne(String slotName, String replacement) {
		Slot slot = (Slot) ast.findSlot(slotName);
		if(slot!=null){
			return slot.bind(replacement);
		}
		else
			return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean extend(String hookName, String argument) {
		java.util.List<ASTNode> slots = ast.Slots();
		for(ASTNode<?> node:slots){
			Slot slot = (Slot)node;
			slot.extend(argument);
		}
		
		return slots.size()>0;
	}

	@Override
	public boolean extendOne(String hookName, String argument) {
		Slot slot = (Slot) ast.findSlot(hookName);
		if(slot!=null){
			slot.extend(argument);
		}
		return false;
	}

	@Override
	public boolean remove(String pointName) {
		boolean oneRemoved = false;
		while(removeOne(pointName)){
			oneRemoved = true;
		}
		
		return oneRemoved;
	}
	
	@Override
	public boolean removeOne(String pointName) {
		RootElement node = (RootElement)ast.findPoint(pointName);
		if(node!=null)
			return node.removeSelf();
		else
			return false;
	}

	@Override
	public boolean hasPoint(String pointName) {
		return ast.findPrototype(pointName)!=null||ast.findVariants(pointName)!=null||ast.findSlot(pointName)!=null;
	}

	@Override
	public boolean hasPoints() {
		return ast.Variants().size()>0||ast.Slots().size()>0||ast.Prototypes().size()>0;
	}

	@Override
	public boolean hasVariant(String variantListName) {
		return ast.findVariants(variantListName)!=null;
	}

	@Override
	public boolean hasVariants() {
		return ast.Variants().size()>0;
	}
	
	public static String readContent(File file) throws FileNotFoundException {
		String content = new Scanner(file).useDelimiter("\\Z").next();
		return content;
	}

	@Override
	public String getGenCode() {
		return ast.genString();
	}
	
	
	
}
