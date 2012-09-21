package ast;

import java.io.IOException;

import ast.Util;
import ast.AST.ASTNode;

public class UtilTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String code = Util.readStream(Util.class.getResourceAsStream("/ast/codebase/common/StateAPI.internal.jrag"));
		Template template = new Template(code);
		
		//System.out.println(template.hasVariants());
		System.out.println(template.extractVariant("CYCLE", "ENABLED"));
		System.out.println(template.extractVariant("HASHSET", "NONDETERMINISTIC"));
		System.out.println(template.bind("TYPEDEFAULTSET",ASTNode.typeDefaultSet));
		System.out.println(template.bind("CREATEDEFAULTSET",ASTNode.createDefaultSet));
		System.out.println(template.extractVariant("COMPONENTCHECK", "ENABLED"));
		System.out.println(template.extractVariant("CIRCULAR", "ENABLED"));
		System.out.println(template.instanciatePrototype("DURINGRESET", "ASPECTNAME", "MyAspect"));
		System.out.println(template.removePrototype("DURINGRESET"));
		System.out.println(template);
		//code = Util.extractVariant(code,"CYCLE","ENABLED");
		
		
		
		//System.out.println(code);
		//System.out.println(Util.extractVariant(code,"SETCHILD","LIST"));

	}

}
