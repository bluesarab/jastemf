package rtt.adaptation;

import calculator.symbols.*;

/**
 * AspectJ specification to adapt the Caclulator's lexer
 * and its tokens for RTT.
 * @author C. Bürger
 */
public aspect RTTLexer {
	// Lexer annotations:
	declare @type:
		Lexer : @rtt.annotations.Lexer;
	declare @method:
		public * Lexer.nextToken() : @rtt.annotations.Lexer.NextToken;
	
	// Token annotations:
	declare parents:
		beaver.Symbol implements Token;
}
