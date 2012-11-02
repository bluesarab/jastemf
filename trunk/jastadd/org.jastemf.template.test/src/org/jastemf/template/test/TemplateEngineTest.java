package org.jastemf.template.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.jastemf.template.CodeGenException;
import org.jastemf.template.RAGTemplate;

public class TemplateEngineTest {
	
	
	@Test
	public void testSlotBinding() throws FileNotFoundException, CodeGenException{
		String fileName = "./testdata/engine/Slots.jrag";
		String resultFileName = "./testdata/engine/Slots_result.jrag";
		RAGTemplate template = new RAGTemplate(RAGTemplate.readContent(new File(fileName)));
		template.bind("LISTTYPE","ASTList");
		template.bind("OPTTYPE_WRONG","ASTOpt");
		template.bind("OPTTYPE","ASTOpt");
		String expectedResultCode = RAGTemplate.readContent(new File(resultFileName));
		assertEquals(expectedResultCode,template.getGenCode());
		assertFalse(template.hasPoints());
	}
	
	@Test
	public void testVariantsSelection() throws FileNotFoundException, CodeGenException{
		String fileName = "./testdata/engine/Variants.jrag";
		String resultFileName = "./testdata/engine/Variants_result_1.jrag";
		RAGTemplate template = new RAGTemplate(RAGTemplate.readContent(new File(fileName)));
		assertTrue(template.extractVariant("TOSTRING", "STANDARD"));
		assertTrue(template.extractVariant("CIRCULAR", "NoneOfBoth"));
		assertFalse(template.extractVariant("NESTED","VARIANT2"));
		assertTrue(template.bind("blub","test2;"));
		assertTrue(template.removeOne("NESTED"));
		assertTrue(template.hasPoint("SomeSlot"));
		String expectedResultCode = RAGTemplate.readContent(new File(resultFileName));
		assertEquals(expectedResultCode,template.getGenCode());
	}
	
	@Test
	public void testPrototypeInstanciation() throws FileNotFoundException, CodeGenException{
		String fileName = "./testdata/engine/Prototypes.jrag";
		String resultFileName = "./testdata/engine/Prototypes_result_1.jrag";
		RAGTemplate template = new RAGTemplate(RAGTemplate.readContent(new File(fileName)));
		assertTrue(template.instanciatePrototype("DURINGRESET","ASPECTNAME","testMe3"));		
		assertTrue(template.instanciatePrototype("DURINGRESET","ASPECTNAME","testMe4"));
		assertTrue(template.removePrototype("DURINGRESET"));
		assertFalse(template.hasPoint("ASPECTNAME"));
		assertNotNull(template.hasPoint("DURINGRESET"));
		assertTrue(template.instanciatePrototype("DURINGRESET"));	
		assertTrue(template.instanciatePrototype("DURINGRESET"));	
		assertTrue(template.removePrototype("DURINGRESET"));
		assertTrue(template.extractVariant("TEST","A"));
		
		//String expectedResultCode = RAGTemplate.readContent(new File(resultFileName));
		//assertEquals(expectedResultCode,template.getGenCode());
	}
	

}
