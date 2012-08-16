package emf;

import jrag.AST.GTToken;
import jrag.AST.JragParserConstants;
import jrag.AST.Token;

public aspect TokenExtensions {

	//Token.GTToken was moved into extra class in package jrag.AST
	//jrag jj file and UnParse.aj have been modified

	Token around(int ofKind): execution(Token Token.newToken(int)) && args(ofKind){
		switch (ofKind) {
		default:
			return new Token();
		case JragParserConstants.RUNSIGNEDSHIFT:
		case JragParserConstants.RSIGNEDSHIFT:
		case JragParserConstants.GT:
			return new GTToken();
		}
	}

}
