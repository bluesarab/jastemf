package org.jastemf.template.test.rtt;

import org.jastemf.template.test.ParserAdapter;
import rtt.annotations.*;


public privileged aspect AdaptParser {
	// Specify parser class:
	declare @type: ParserAdapter : @Parser;
	
	// Insert AST access method:
	declare @method:
	    public Object ParserAdapter.parse() : @Parser.AST;
	

}
