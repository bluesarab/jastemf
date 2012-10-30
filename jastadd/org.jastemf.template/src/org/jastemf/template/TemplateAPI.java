package org.jastemf.template;

import java.util.Map;

public interface TemplateAPI {

	/**
	 * @return Original code block.
	 */
	public abstract String getCodeBlock();
	
	/**
	 * @return Modified code block.
	 */
	public abstract String getGenCode();

	public abstract void setCodeBlock(String codeBlock) throws CodeGenException;

	public abstract boolean extractVariant(String variantListName,
			String variantName);

	public abstract boolean instanciatePrototype(String prototypeName,
			String... param);

	public abstract boolean instanciatePrototype(String prototypeName,
			String slotName, String value);

	public abstract boolean instanciatePrototype(String prototypeName,
			Map<String, String> instanceValues);

	public abstract boolean removePrototype(String prototypeName);

	public abstract boolean bind(String slotName, String replacement);

	public abstract boolean bindOne(String slotName, String replacement);

	public abstract boolean extend(String hookName, String argument);

	public abstract boolean extendOne(String hookName, String argument);

	public abstract boolean remove(String pointName);

	public abstract boolean removeOne(String pointName);

	/** 
	 * @param pointName
	 * @return true, iff a slot or hook with the given name is available in template 
	 */
	public abstract boolean hasPoint(String pointName);

	/** 
	 * @param pointName
	 * @return true, iff any slot or hook with the given name is available in template, 
	 * note that currently #variants# and #var# are also matched as slots. 
	 */
	public abstract boolean hasPoints();

	/**
	 * @return true, iff variants with the given name without inner points or variants 
	 * exist in template
	 */
	public abstract boolean hasVariant(String variantListName);

	/**
	 * @return true, iff variants without inner points or variants are available in template
	 */
	public abstract boolean hasVariants();

}