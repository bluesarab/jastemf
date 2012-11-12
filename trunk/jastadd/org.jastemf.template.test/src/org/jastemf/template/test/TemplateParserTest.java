package org.jastemf.template.test;
  
import static org.junit.Assert.*;

import org.junit.Test;

//Problem for AJ tooling in combination with annotations in AdaptAst.aj, we use reflection instead
//import org.jastemf.template.ast.GenericSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;


import java.util.Scanner;

public class TemplateParserTest {
	
	@Test
	public void test() throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {		
		String fileName = "./testdata/SimpleAspect.jrag";
		File file = new File(fileName);
		String content = readContent(file);
		testParseAndReprint(content, fileName);
	}
	
	@Test
	public void testWithSlots() throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {		
		String fileName = "./testdata/AspectWithSlot.jrag";
		File file = new File(fileName);
		String content = readContent(file);
		testParseAndReprint(content, fileName);
	}
	
	
	@Test
	public void testWithVariants() throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {		
		String fileName = "./testdata/AspectWithVariants.jrag";
		File file = new File(fileName);
		String content = readContent(file);
		testParseAndReprint(content, fileName);
	}
	
	@Test
	public void testWithPrototype() throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {		
		String fileName = "./testdata/AspectWithPrototype.jrag";
		File file = new File(fileName);
		String content = readContent(file);
		testParseAndReprint(content, fileName);
	}
	
	@Test
	public void testNoPoints() throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {		
		String fileName = "./testdata/AspectWithTextOnly.jrag";
		File file = new File(fileName);
		String content = readContent(file);
		testParseAndReprint(content, fileName);
	}
	
	
	private void testParseAndReprint(String content, String fileName) throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringReader reader = new StringReader(content);
		ParserAdapter parser = new ParserAdapter(reader,fileName);
		Object result = parser.parse();
		assertNotNull(result);

		if(result instanceof ParsingError){
			System.out.println("Parsing " + fileName + ": "+ result);
		}  
		//instanceof GenericSource causes static aj compiler NullPointerException
		assertTrue(result.getClass().getName().equals("org.jastemf.template.ast.GenericSource"));
		System.out.println("Parsing " + fileName + ": OK");
		String source = (String) result.getClass().getMethod("genString").invoke(result);
		//System.out.println(content.replaceAll("\\#","#"));
		assertEquals(content.replaceAll("\\\\#","#").replaceAll("\\\\<\\?", "<?").replaceAll("\\\\\\?>", "?>").replaceAll("\\\\\\[\\[", "[[").replaceAll("\\\\\\]\\]", "]]"), source);		

	}
	
	private String readContent(File file) throws FileNotFoundException{
		String content = new Scanner(file).useDelimiter("\\Z").next();
		return content;
	}

}
