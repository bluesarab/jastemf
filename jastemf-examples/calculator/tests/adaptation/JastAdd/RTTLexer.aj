package rtt.adaptation;

import rtt.annotations.*;

import calculator.symbols.*;

/**
 * AspectJ specification to adapt the Caclulator's lexer and its tokens
 * for RTT.
 * @author C. Bürger
 */
public aspect RTTLexer {
	// Lexer annotations:
	declare @type: CalculatorLexer : @Lexer;
	
	declare @method:
		public * CalculatorLexer.nextToken() : @Lexer.NextToken;
	
	// Token annotations:
	declare parents:
		beaver.Symbol implements TokenClass;
}
