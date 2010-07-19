/**
 * <copyright>
 *
 * This program and the accompanying materials are made available under the
 * terms of the BSD 3-clause license which accompanies this distribution.
 *
 * </copyright>
 */
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