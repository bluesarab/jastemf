package org.jastemf.template;

public class CodeGenException extends Exception{

	private static final long serialVersionUID = 1659807396698064740L;
	
	
	public CodeGenException(String message){
		super(message);
	}
	
	public CodeGenException(String message, Throwable cause){
		super(message,cause);
	}
	
}
