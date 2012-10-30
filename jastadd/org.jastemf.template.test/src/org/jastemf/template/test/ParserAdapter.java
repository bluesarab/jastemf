package org.jastemf.template.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.jastemf.template.TemplateParser;

import xtc.parser.ParseError;
import xtc.parser.Result;

public class ParserAdapter {
	
	private TemplateParser delegatee = null;
	
	public ParserAdapter(InputStream in){
		delegatee = new TemplateParser(in);
	}
	
	public ParserAdapter(Reader in, String resourceName){
		delegatee = new TemplateParser(in,resourceName);
	}
	
	public Object parse() throws IOException{
		final Result result = delegatee.pGenericSource_Declaration(0);
		if(result instanceof ParseError){
			return new ParsingError() {
				
				@Override
				public String getMessage() {
					return "ERROR:"+result.parseError().msg+"("+delegatee.location(result.index).line+","+delegatee.location(result.index).column+")";
				}
			};
		}
		return delegatee.pGenericSource_Declaration(0).semanticValue();
	}
	
}
