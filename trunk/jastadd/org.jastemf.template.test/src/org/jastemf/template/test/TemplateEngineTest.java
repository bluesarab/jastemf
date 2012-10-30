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
		template.bind("OPTTYPE","ASTOpt");
		String expectedResultCode = RAGTemplate.readContent(new File(resultFileName));
		assertEquals(expectedResultCode,template.getGenCode());
		assertFalse(template.hasPoints());
	}
	

}
