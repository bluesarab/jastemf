package org.jastemf.template.test;

public abstract class ParsingError {
	
	public abstract String getMessage();
	
	public String toString(){
		return getMessage();
	}
	
}
