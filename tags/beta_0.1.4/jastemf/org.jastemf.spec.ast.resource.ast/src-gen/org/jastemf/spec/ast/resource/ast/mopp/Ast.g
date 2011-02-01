grammar Ast;

options {
	superClass = AstANTLRParserBase;
	backtrack = true;
	memoize = true;
}

@lexer::header {
	package org.jastemf.spec.ast.resource.ast.mopp;
}

@lexer::members {
	public java.util.List<org.antlr.runtime3_2_0.RecognitionException> lexerExceptions  = new java.util.ArrayList<org.antlr.runtime3_2_0.RecognitionException>();
	public java.util.List<java.lang.Integer> lexerExceptionsPosition = new java.util.ArrayList<java.lang.Integer>();
	
	public void reportError(org.antlr.runtime3_2_0.RecognitionException e) {
		lexerExceptions.add(e);
		lexerExceptionsPosition.add(((org.antlr.runtime3_2_0.ANTLRStringStream) input).index());
	}
}
@header{
	package org.jastemf.spec.ast.resource.ast.mopp;
}

@members{
	private org.jastemf.spec.ast.resource.ast.IAstTokenResolverFactory tokenResolverFactory = new org.jastemf.spec.ast.resource.ast.mopp.AstTokenResolverFactory();
	
	/**
	 * the index of the last token that was handled by collectHiddenTokens()
	 */
	@SuppressWarnings("unused")
	private int lastPosition;
	
	/**
	 * the index of the last token that was handled by retrieveLayoutInformation()
	 */
	private int lastPosition2;
	
	private org.jastemf.spec.ast.resource.ast.mopp.AstTokenResolveResult tokenResolveResult = new org.jastemf.spec.ast.resource.ast.mopp.AstTokenResolveResult();
	
	/**
	 * A flag that indicateds whether the parser should remember all expected
	 * elements. This flag is set to true when using the parse for code completion.
	 * Otherwise it is set to false.
	 */
	private boolean rememberExpectedElements = false;
	
	private java.lang.Object parseToIndexTypeObject;
	private int lastTokenIndex = 0;
	
	/**
	 * A list of expected elements the were collected while parsing the input stream.
	 * This list is only filled if <code>rememberExpectedElements</code> is set to
	 * true.
	 */
	private java.util.List<org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal> expectedElements = new java.util.ArrayList<org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal>();
	
	private int mismatchedTokenRecoveryTries = 0;
	private java.util.Map<?, ?> options;
	/**
	 * A helper list to allow a lexer to pass errors to its parser
	 */
	protected java.util.List<org.antlr.runtime3_2_0.RecognitionException> lexerExceptions = java.util.Collections.synchronizedList(new java.util.ArrayList<org.antlr.runtime3_2_0.RecognitionException>());
	
	/**
	 * Another helper list to allow a lexer to pass positions of errors to its parser
	 */
	protected java.util.List<java.lang.Integer> lexerExceptionsPosition = java.util.Collections.synchronizedList(new java.util.ArrayList<java.lang.Integer>());
	
	/**
	 * A stack for incomplete objects. This stack is used only when the parser is used
	 * for code completion. Whenever the parser starts to read an object it is pushed
	 * on the stack. Once the element was parser completely it is popped for the stack.
	 */
	protected java.util.Stack<org.eclipse.emf.ecore.EObject> incompleteObjects = new java.util.Stack<org.eclipse.emf.ecore.EObject>();
	
	private int stopIncludingHiddenTokens;
	private int stopExcludingHiddenTokens;
	/**
	 * A collection that is filled with commands to be exectued after parsing. This
	 * collection is cleared before parsing starts and returned as part of the parse
	 * result object.
	 */
	private java.util.Collection<org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>> postParseCommands;
	
	/**
	 * A flag to indicate that the parser should stop parsing as soon as possible. The
	 * flag is set to false before parsing starts. It can be set to true by invoking
	 * the terminateParsing() method from another thread. This feature is used, when
	 * documents are parsed in the background (i.e., while editing them). In order to
	 * cancel running parsers, the parsing process can be terminated. This is done
	 * whenever a document changes, because the previous content of the document is
	 * not valid anymore and parsing the old content is not necessary any longer.
	 */
	private boolean terminateParsing;
	
	private int tokenIndexOfLastCompleteElement;
	
	private int expectedElementsIndexOfLastCompleteElement;
	
	protected void addErrorToResource(final java.lang.String errorMessage, final int line, final int charPositionInLine, final int startIndex, final int stopIndex) {
		postParseCommands.add(new org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>() {
			public boolean execute(org.jastemf.spec.ast.resource.ast.IAstTextResource resource) {
				if (resource == null) {
					// the resource can be null if the parser is used for code completion
					return true;
				}
				resource.addProblem(new org.jastemf.spec.ast.resource.ast.IAstProblem() {
					public org.jastemf.spec.ast.resource.ast.AstEProblemType getType() {
						return org.jastemf.spec.ast.resource.ast.AstEProblemType.ERROR;
					}
					public java.lang.String getMessage() {
						return errorMessage;
					}
				}, line, charPositionInLine, startIndex, stopIndex);
				return true;
			}
		});
	}
	
	public void addExpectedElement(org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal expectedElement) {
		if (!this.rememberExpectedElements) {
			return;
		}
		setPosition(expectedElement, input.index());
		this.expectedElements.add(expectedElement);
	}
	
	protected void addMapEntry(org.eclipse.emf.ecore.EObject element, org.eclipse.emf.ecore.EStructuralFeature structuralFeature, org.jastemf.spec.ast.resource.ast.mopp.AstDummyEObject dummy) {
		java.lang.Object value = element.eGet(structuralFeature);
		java.lang.Object mapKey = dummy.getValueByName("key");
		java.lang.Object mapValue = dummy.getValueByName("value");
		if (value instanceof org.eclipse.emf.common.util.EMap<?, ?>) {
			org.eclipse.emf.common.util.EMap<java.lang.Object, java.lang.Object> valueMap = org.jastemf.spec.ast.resource.ast.util.AstMapUtil.castToEMap(value);
			if (mapKey != null && mapValue != null) {
				valueMap.put(mapKey, mapValue);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	
	public boolean addObjectToList(org.eclipse.emf.ecore.EObject element, int featureID, java.lang.Object proxy) {
		return ((java.util.List<java.lang.Object>) element.eGet(element.eClass().getEStructuralFeature(featureID))).add(proxy);
	}
	
	protected org.eclipse.emf.ecore.EObject apply(org.eclipse.emf.ecore.EObject target, java.util.List<org.eclipse.emf.ecore.EObject> dummyEObjects) {
		org.eclipse.emf.ecore.EObject currentTarget = target;
		for (org.eclipse.emf.ecore.EObject object : dummyEObjects) {
			assert(object instanceof org.jastemf.spec.ast.resource.ast.mopp.AstDummyEObject);
			org.jastemf.spec.ast.resource.ast.mopp.AstDummyEObject dummy = (org.jastemf.spec.ast.resource.ast.mopp.AstDummyEObject) object;
			org.eclipse.emf.ecore.EObject newEObject = dummy.applyTo(currentTarget);
			currentTarget = newEObject;
		}
		return currentTarget;
	}
	
	protected void collectHiddenTokens(org.eclipse.emf.ecore.EObject element) {
	}
	
	protected void copyLocalizationInfos(final org.eclipse.emf.ecore.EObject source, final org.eclipse.emf.ecore.EObject target) {
		postParseCommands.add(new org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>() {
			public boolean execute(org.jastemf.spec.ast.resource.ast.IAstTextResource resource) {
				org.jastemf.spec.ast.resource.ast.IAstLocationMap locationMap = resource.getLocationMap();
				if (locationMap == null) {
					// the locationMap can be null if the parser is used for code completion
					return true;
				}
				locationMap.setCharStart(target, locationMap.getCharStart(source));
				locationMap.setCharEnd(target, locationMap.getCharEnd(source));
				locationMap.setColumn(target, locationMap.getColumn(source));
				locationMap.setLine(target, locationMap.getLine(source));
				return true;
			}
		});
	}
	
	protected void copyLocalizationInfos(final org.antlr.runtime3_2_0.CommonToken source, final org.eclipse.emf.ecore.EObject target) {
		postParseCommands.add(new org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>() {
			public boolean execute(org.jastemf.spec.ast.resource.ast.IAstTextResource resource) {
				org.jastemf.spec.ast.resource.ast.IAstLocationMap locationMap = resource.getLocationMap();
				if (locationMap == null) {
					// the locationMap can be null if the parser is used for code completion
					return true;
				}
				if (source == null) {
					return true;
				}
				locationMap.setCharStart(target, source.getStartIndex());
				locationMap.setCharEnd(target, source.getStopIndex());
				locationMap.setColumn(target, source.getCharPositionInLine());
				locationMap.setLine(target, source.getLine());
				return true;
			}
		});
	}
	
	/**
	 * Sets the end character index and the last line for the given object in the
	 * location map.
	 */
	protected void setLocalizationEnd(java.util.Collection<org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>> postParseCommands , final org.eclipse.emf.ecore.EObject object, final int endChar, final int endLine) {
		postParseCommands.add(new org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>() {
			public boolean execute(org.jastemf.spec.ast.resource.ast.IAstTextResource resource) {
				org.jastemf.spec.ast.resource.ast.IAstLocationMap locationMap = resource.getLocationMap();
				if (locationMap == null) {
					// the locationMap can be null if the parser is used for code completion
					return true;
				}
				locationMap.setCharEnd(object, endChar);
				locationMap.setLine(object, endLine);
				return true;
			}
		});
	}
	
	public org.jastemf.spec.ast.resource.ast.IAstTextParser createInstance(java.io.InputStream actualInputStream, java.lang.String encoding) {
		try {
			if (encoding == null) {
				return new AstParser(new org.antlr.runtime3_2_0.CommonTokenStream(new AstLexer(new org.antlr.runtime3_2_0.ANTLRInputStream(actualInputStream))));
			} else {
				return new AstParser(new org.antlr.runtime3_2_0.CommonTokenStream(new AstLexer(new org.antlr.runtime3_2_0.ANTLRInputStream(actualInputStream, encoding))));
			}
		} catch (java.io.IOException e) {
			org.jastemf.spec.ast.resource.ast.mopp.AstPlugin.logError("Error while creating parser.", e);
			return null;
		}
	}
	
	/**
	 * This default constructor is only used to call createInstance() on it.
	 */
	public AstParser() {
		super(null);
	}
	
	protected org.eclipse.emf.ecore.EObject doParse() throws org.antlr.runtime3_2_0.RecognitionException {
		this.lastPosition = 0;
		// required because the lexer class can not be subclassed
		((AstLexer) getTokenStream().getTokenSource()).lexerExceptions = lexerExceptions;
		((AstLexer) getTokenStream().getTokenSource()).lexerExceptionsPosition = lexerExceptionsPosition;
		java.lang.Object typeObject = getTypeObject();
		if (typeObject == null) {
			return start();
		} else if (typeObject instanceof org.eclipse.emf.ecore.EClass) {
			org.eclipse.emf.ecore.EClass type = (org.eclipse.emf.ecore.EClass) typeObject;
			if (type.getInstanceClass() == org.jastemf.spec.ast.Grammar.class) {
				return parse_org_jastemf_spec_ast_Grammar();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.ASTDecl.class) {
				return parse_org_jastemf_spec_ast_ASTDecl();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.ListComponents.class) {
				return parse_org_jastemf_spec_ast_ListComponents();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.ListComponentsNTA.class) {
				return parse_org_jastemf_spec_ast_ListComponentsNTA();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.TokenComponent.class) {
				return parse_org_jastemf_spec_ast_TokenComponent();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.TokenComponentNTA.class) {
				return parse_org_jastemf_spec_ast_TokenComponentNTA();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.OptionalComponent.class) {
				return parse_org_jastemf_spec_ast_OptionalComponent();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.OptionalComponentNTA.class) {
				return parse_org_jastemf_spec_ast_OptionalComponentNTA();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.AggregateComponents.class) {
				return parse_org_jastemf_spec_ast_AggregateComponents();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.AggregateComponentsNTA.class) {
				return parse_org_jastemf_spec_ast_AggregateComponentsNTA();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.Abstract.class) {
				return parse_org_jastemf_spec_ast_Abstract();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.Id.class) {
				return parse_org_jastemf_spec_ast_Id();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.NameNode.class) {
				return parse_org_jastemf_spec_ast_NameNode();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.TokenId.class) {
				return parse_org_jastemf_spec_ast_TokenId();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.IdUse.class) {
				return parse_org_jastemf_spec_ast_IdUse();
			}
			if (type.getInstanceClass() == org.jastemf.spec.ast.IdDecl.class) {
				return parse_org_jastemf_spec_ast_IdDecl();
			}
		}
		throw new org.jastemf.spec.ast.resource.ast.mopp.AstUnexpectedContentTypeException(typeObject);
	}
	
	private org.jastemf.spec.ast.resource.ast.mopp.AstTokenResolveResult getFreshTokenResolveResult() {
		tokenResolveResult.clear();
		return tokenResolveResult;
	}
	
	public int getMismatchedTokenRecoveryTries() {
		return mismatchedTokenRecoveryTries;
	}
	
	public java.lang.Object getMissingSymbol(org.antlr.runtime3_2_0.IntStream arg0, org.antlr.runtime3_2_0.RecognitionException arg1, int arg2, org.antlr.runtime3_2_0.BitSet arg3) {
		mismatchedTokenRecoveryTries++;
		return super.getMissingSymbol(arg0, arg1, arg2, arg3);
	}
	
	protected java.util.Map<?,?> getOptions() {
		return options;
	}
	
	public org.jastemf.spec.ast.resource.ast.mopp.AstMetaInformation getMetaInformation() {
		return new org.jastemf.spec.ast.resource.ast.mopp.AstMetaInformation();
	}
	
	public java.lang.Object getParseToIndexTypeObject() {
		return parseToIndexTypeObject;
	}
	
	protected org.jastemf.spec.ast.resource.ast.mopp.AstReferenceResolverSwitch getReferenceResolverSwitch() {
		return (org.jastemf.spec.ast.resource.ast.mopp.AstReferenceResolverSwitch) getMetaInformation().getReferenceResolverSwitch();
	}
	
	protected java.lang.Object getTypeObject() {
		java.lang.Object typeObject = getParseToIndexTypeObject();
		if (typeObject != null) {
			return typeObject;
		}
		java.util.Map<?,?> options = getOptions();
		if (options != null) {
			typeObject = options.get(org.jastemf.spec.ast.resource.ast.IAstOptions.RESOURCE_CONTENT_TYPE);
		}
		return typeObject;
	}
	
	/**
	 * Implementation that calls {@link #doParse()} and handles the thrown
	 * RecognitionExceptions.
	 */
	public org.jastemf.spec.ast.resource.ast.IAstParseResult parse() {
		terminateParsing = false;
		postParseCommands = new java.util.ArrayList<org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>>();
		org.jastemf.spec.ast.resource.ast.mopp.AstParseResult parseResult = new org.jastemf.spec.ast.resource.ast.mopp.AstParseResult();
		try {
			org.eclipse.emf.ecore.EObject result =  doParse();
			if (lexerExceptions.isEmpty()) {
				parseResult.setRoot(result);
			}
		} catch (org.antlr.runtime3_2_0.RecognitionException re) {
			reportError(re);
		} catch (java.lang.IllegalArgumentException iae) {
			if ("The 'no null' constraint is violated".equals(iae.getMessage())) {
				// can be caused if a null is set on EMF models where not allowed. this will just
				// happen if other errors occurred before
			} else {
				iae.printStackTrace();
			}
		}
		for (org.antlr.runtime3_2_0.RecognitionException re : lexerExceptions) {
			reportLexicalError(re);
		}
		parseResult.getPostParseCommands().addAll(postParseCommands);
		return parseResult;
	}
	
	public java.util.List<org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal> parseToExpectedElements(org.eclipse.emf.ecore.EClass type, org.jastemf.spec.ast.resource.ast.IAstTextResource dummyResource) {
		rememberExpectedElements = true;
		parseToIndexTypeObject = type;
		final org.antlr.runtime3_2_0.CommonTokenStream tokenStream = (org.antlr.runtime3_2_0.CommonTokenStream) getTokenStream();
		org.jastemf.spec.ast.resource.ast.IAstParseResult result = parse();
		for (org.eclipse.emf.ecore.EObject incompleteObject : incompleteObjects) {
			org.antlr.runtime3_2_0.Lexer lexer = (org.antlr.runtime3_2_0.Lexer) tokenStream.getTokenSource();
			int endChar = lexer.getCharIndex();
			int endLine = lexer.getLine();
			setLocalizationEnd(result.getPostParseCommands(), incompleteObject, endChar, endLine);
		}
		if (result != null) {
			org.eclipse.emf.ecore.EObject root = result.getRoot();
			if (root != null) {
				dummyResource.getContents().add(root);
			}
			for (org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource> command : result.getPostParseCommands()) {
				command.execute(dummyResource);
			}
		}
		// remove all expected elements that were added after the last complete element
		expectedElements = expectedElements.subList(0, expectedElementsIndexOfLastCompleteElement + 1);
		int lastFollowSetID = expectedElements.get(expectedElementsIndexOfLastCompleteElement).getFollowSetID();
		java.util.Set<org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal> currentFollowSet = new java.util.LinkedHashSet<org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal>();
		java.util.List<org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal> newFollowSet = new java.util.ArrayList<org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal>();
		for (int i = expectedElementsIndexOfLastCompleteElement; i >= 0; i--) {
			org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal expectedElementI = expectedElements.get(i);
			if (expectedElementI.getFollowSetID() == lastFollowSetID) {
				currentFollowSet.add(expectedElementI);
			} else {
				break;
			}
		}
		int followSetID = 51;
		int i;
		for (i = tokenIndexOfLastCompleteElement; i < tokenStream.size(); i++) {
			org.antlr.runtime3_2_0.CommonToken nextToken = (org.antlr.runtime3_2_0.CommonToken) tokenStream.get(i);
			if (nextToken.getChannel() == 99) {
				// hidden tokens do not reduce the follow set
			} else {
				// now that we have found the next visible token the position for that expected
				// terminals can be set
				for (org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal nextFollow : newFollowSet) {
					lastTokenIndex = 0;
					setPosition(nextFollow, i);
				}
				newFollowSet.clear();
				// normal tokens do reduce the follow set - only elements that match the token are
				// kept
				for (org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal nextFollow : currentFollowSet) {
					if (nextFollow.getTerminal().getTokenName().equals(getTokenNames()[nextToken.getType()])) {
						// keep this one - it matches
						java.util.Collection<org.jastemf.spec.ast.resource.ast.util.AstPair<org.jastemf.spec.ast.resource.ast.IAstExpectedElement, org.eclipse.emf.ecore.EStructuralFeature[]>> newFollowers = nextFollow.getTerminal().getFollowers();
						for (org.jastemf.spec.ast.resource.ast.util.AstPair<org.jastemf.spec.ast.resource.ast.IAstExpectedElement, org.eclipse.emf.ecore.EStructuralFeature[]> newFollowerPair : newFollowers) {
							org.jastemf.spec.ast.resource.ast.IAstExpectedElement newFollower = newFollowerPair.getLeft();
							org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal newFollowTerminal = new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(newFollower, followSetID, newFollowerPair.getRight());
							newFollowSet.add(newFollowTerminal);
							expectedElements.add(newFollowTerminal);
						}
					}
				}
				currentFollowSet.clear();
				currentFollowSet.addAll(newFollowSet);
			}
			followSetID++;
		}
		// after the last token in the stream we must set the position for the elements
		// that were added during the last iteration of the loop
		for (org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal nextFollow : newFollowSet) {
			lastTokenIndex = 0;
			setPosition(nextFollow, i);
		}
		return this.expectedElements;
	}
	
	public void setPosition(org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal expectedElement, int tokenIndex) {
		int currentIndex = java.lang.Math.max(0, tokenIndex);
		for (int index = lastTokenIndex; index < currentIndex; index++) {
			if (index >= input.size()) {
				break;
			}
			org.antlr.runtime3_2_0.CommonToken tokenAtIndex = (org.antlr.runtime3_2_0.CommonToken) input.get(index);
			stopIncludingHiddenTokens = tokenAtIndex.getStopIndex() + 1;
			if (tokenAtIndex.getChannel() != 99) {
				stopExcludingHiddenTokens = tokenAtIndex.getStopIndex() + 1;
			}
		}
		lastTokenIndex = java.lang.Math.max(0, currentIndex);
		expectedElement.setPosition(stopExcludingHiddenTokens, stopIncludingHiddenTokens);
	}
	
	public java.lang.Object recoverFromMismatchedToken(org.antlr.runtime3_2_0.IntStream input, int ttype, org.antlr.runtime3_2_0.BitSet follow) throws org.antlr.runtime3_2_0.RecognitionException {
		if (!rememberExpectedElements) {
			return super.recoverFromMismatchedToken(input, ttype, follow);
		} else {
			return null;
		}
	}
	
	protected <ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject> void registerContextDependentProxy(final org.jastemf.spec.ast.resource.ast.mopp.AstContextDependentURIFragmentFactory<ContainerType, ReferenceType> factory, final ContainerType element, final org.eclipse.emf.ecore.EReference reference, final String id, final org.eclipse.emf.ecore.EObject proxy) {
		postParseCommands.add(new org.jastemf.spec.ast.resource.ast.IAstCommand<org.jastemf.spec.ast.resource.ast.IAstTextResource>() {
			public boolean execute(org.jastemf.spec.ast.resource.ast.IAstTextResource resource) {
				if (resource == null) {
					// the resource can be null if the parser is used for code completion
					return true;
				}
				resource.registerContextDependentProxy(factory, element, reference, id, proxy);
				return true;
			}
		});
	}
	
	/**
	 * Translates errors thrown by the parser into human readable messages.
	 */
	public void reportError(final org.antlr.runtime3_2_0.RecognitionException e)  {
		java.lang.String message = e.getMessage();
		if (e instanceof org.antlr.runtime3_2_0.MismatchedTokenException) {
			org.antlr.runtime3_2_0.MismatchedTokenException mte = (org.antlr.runtime3_2_0.MismatchedTokenException) e;
			java.lang.String tokenName = "<unknown>";
			if (mte.expecting == Token.EOF) {
				tokenName = "EOF";
			} else {
				tokenName = getTokenNames()[mte.expecting];
				tokenName = org.jastemf.spec.ast.resource.ast.util.AstStringUtil.formatTokenName(tokenName);
			}
			message = "Syntax error on token \"" + e.token.getText() + "\", \"" + tokenName + "\" expected";
		} else if (e instanceof org.antlr.runtime3_2_0.MismatchedTreeNodeException) {
			org.antlr.runtime3_2_0.MismatchedTreeNodeException mtne = (org.antlr.runtime3_2_0.MismatchedTreeNodeException) e;
			java.lang.String tokenName = "<unknown>";
			if (mtne.expecting == Token.EOF) {
				tokenName = "EOF";
			} else {
				tokenName = getTokenNames()[mtne.expecting];
			}
			message = "mismatched tree node: " + "xxx" + "; expecting " + tokenName;
		} else if (e instanceof org.antlr.runtime3_2_0.NoViableAltException) {
			message = "Syntax error on token \"" + e.token.getText() + "\", check following tokens";
		} else if (e instanceof org.antlr.runtime3_2_0.EarlyExitException) {
			message = "Syntax error on token \"" + e.token.getText() + "\", delete this token";
		} else if (e instanceof org.antlr.runtime3_2_0.MismatchedSetException) {
			org.antlr.runtime3_2_0.MismatchedSetException mse = (org.antlr.runtime3_2_0.MismatchedSetException) e;
			message = "mismatched token: " + e.token + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_2_0.MismatchedNotSetException) {
			org.antlr.runtime3_2_0.MismatchedNotSetException mse = (org.antlr.runtime3_2_0.MismatchedNotSetException) e;
			message = "mismatched token: " +  e.token + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_2_0.FailedPredicateException) {
			org.antlr.runtime3_2_0.FailedPredicateException fpe = (org.antlr.runtime3_2_0.FailedPredicateException) e;
			message = "rule " + fpe.ruleName + " failed predicate: {" +  fpe.predicateText + "}?";
		}
		// the resource may be null if the parse is used for code completion
		final java.lang.String finalMessage = message;
		if (e.token instanceof org.antlr.runtime3_2_0.CommonToken) {
			final org.antlr.runtime3_2_0.CommonToken ct = (org.antlr.runtime3_2_0.CommonToken) e.token;
			addErrorToResource(finalMessage, ct.getCharPositionInLine(), ct.getLine(), ct.getStartIndex(), ct.getStopIndex());
		} else {
			addErrorToResource(finalMessage, e.token.getCharPositionInLine(), e.token.getLine(), 1, 5);
		}
	}
	
	/**
	 * Translates errors thrown by the lexer into human readable messages.
	 */
	public void reportLexicalError(final org.antlr.runtime3_2_0.RecognitionException e)  {
		java.lang.String message = "";
		if (e instanceof org.antlr.runtime3_2_0.MismatchedTokenException) {
			org.antlr.runtime3_2_0.MismatchedTokenException mte = (org.antlr.runtime3_2_0.MismatchedTokenException) e;
			message = "Syntax error on token \"" + ((char) e.c) + "\", \"" + (char) mte.expecting + "\" expected";
		} else if (e instanceof org.antlr.runtime3_2_0.NoViableAltException) {
			message = "Syntax error on token \"" + ((char) e.c) + "\", delete this token";
		} else if (e instanceof org.antlr.runtime3_2_0.EarlyExitException) {
			org.antlr.runtime3_2_0.EarlyExitException eee = (org.antlr.runtime3_2_0.EarlyExitException) e;
			message ="required (...)+ loop (decision=" + eee.decisionNumber + ") did not match anything; on line " + e.line + ":" + e.charPositionInLine + " char=" + ((char) e.c) + "'";
		} else if (e instanceof org.antlr.runtime3_2_0.MismatchedSetException) {
			org.antlr.runtime3_2_0.MismatchedSetException mse = (org.antlr.runtime3_2_0.MismatchedSetException) e;
			message ="mismatched char: '" + ((char) e.c) + "' on line " + e.line + ":" + e.charPositionInLine + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_2_0.MismatchedNotSetException) {
			org.antlr.runtime3_2_0.MismatchedNotSetException mse = (org.antlr.runtime3_2_0.MismatchedNotSetException) e;
			message ="mismatched char: '" + ((char) e.c) + "' on line " + e.line + ":" + e.charPositionInLine + "; expecting set " + mse.expecting;
		} else if (e instanceof org.antlr.runtime3_2_0.MismatchedRangeException) {
			org.antlr.runtime3_2_0.MismatchedRangeException mre = (org.antlr.runtime3_2_0.MismatchedRangeException) e;
			message ="mismatched char: '" + ((char) e.c) + "' on line " + e.line + ":" + e.charPositionInLine + "; expecting set '" + (char) mre.a + "'..'" + (char) mre.b + "'";
		} else if (e instanceof org.antlr.runtime3_2_0.FailedPredicateException) {
			org.antlr.runtime3_2_0.FailedPredicateException fpe = (org.antlr.runtime3_2_0.FailedPredicateException) e;
			message ="rule " + fpe.ruleName + " failed predicate: {" + fpe.predicateText + "}?";
		}
		addErrorToResource(message, e.index, e.line, lexerExceptionsPosition.get(lexerExceptions.indexOf(e)), lexerExceptionsPosition.get(lexerExceptions.indexOf(e)));
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		this.options = options;
	}
	
	public void terminate() {
		terminateParsing = true;
	}
	
	protected void completedElement(java.lang.Object object, boolean isContainment) {
		if (isContainment && !this.incompleteObjects.isEmpty()) {
			this.incompleteObjects.pop();
		}
		if (object instanceof org.eclipse.emf.ecore.EObject) {
			this.tokenIndexOfLastCompleteElement = getTokenStream().index();
			this.expectedElementsIndexOfLastCompleteElement = expectedElements.size() - 1;
		}
	}
	
	/**
	 * Creates a dynamic Java proxy that mimics the interface of the given class.
	 */
	@SuppressWarnings("unchecked")
	
	public <T> T createDynamicProxy(java.lang.Class<T> clazz) {
		java.lang.Object proxy = java.lang.reflect.Proxy.newProxyInstance(this.getClass().getClassLoader(), new java.lang.Class<?>[]{clazz, org.eclipse.emf.ecore.EObject.class, org.eclipse.emf.ecore.InternalEObject.class}, new java.lang.reflect.InvocationHandler() {
			
			private org.eclipse.emf.ecore.EObject dummyObject = new org.eclipse.emf.ecore.impl.EObjectImpl() {};
			
			public java.lang.Object invoke(java.lang.Object object, java.lang.reflect.Method method, java.lang.Object[] args) throws java.lang.Throwable {
				// search in dummyObject for the requested method
				java.lang.reflect.Method[] methodsInDummy = dummyObject.getClass().getMethods();
				for (java.lang.reflect.Method methodInDummy : methodsInDummy) {
					boolean matches = true;
					if (methodInDummy.getName().equals(method.getName())) {
						java.lang.Class<?>[] parameterTypes = method.getParameterTypes();
						java.lang.Class<?>[] parameterTypesInDummy = methodInDummy.getParameterTypes();
						if (parameterTypes.length == parameterTypesInDummy.length) {
							for (int p = 0; p < parameterTypes.length; p++) {
								java.lang.Class<?> parameterType = parameterTypes[p];
								java.lang.Class<?> parameterTypeInDummy = parameterTypesInDummy[p];
								if (!parameterType.equals(parameterTypeInDummy)) {
									matches = false;
								}
							}
						} else {
							matches = false;
						}
					} else {
						matches = false;
					}
					if (matches) {
						return methodInDummy.invoke(dummyObject, args);
					}
				}
				return null;
			}
					});
		return (T) proxy;
	}
	
	protected void retrieveLayoutInformation(org.eclipse.emf.ecore.EObject element, org.jastemf.spec.ast.resource.ast.grammar.AstSyntaxElement syntaxElement, java.lang.Object object) {
		if (!(syntaxElement instanceof org.jastemf.spec.ast.resource.ast.grammar.AstPlaceholder) && !(syntaxElement instanceof org.jastemf.spec.ast.resource.ast.grammar.AstKeyword)) {
			return;
		}
		int currentPos = getTokenStream().index();
		if (currentPos == 0) {
			return;
		}
		int endPos = currentPos - 1;
		for (; endPos >= this.lastPosition2; endPos--) {
			org.antlr.runtime3_2_0.Token token = getTokenStream().get(endPos);
			int _channel = token.getChannel();
			if (_channel != 99) {
				break;
			}
		}
		java.lang.StringBuilder hiddenTokenText = new java.lang.StringBuilder();
		java.lang.StringBuilder visibleTokenText = new java.lang.StringBuilder();
		org.antlr.runtime3_2_0.CommonToken firstToken = null;
		for (int pos = this.lastPosition2; pos <= endPos; pos++) {
			org.antlr.runtime3_2_0.Token token = getTokenStream().get(pos);
			if (firstToken == null) {
				firstToken = (org.antlr.runtime3_2_0.CommonToken) token;
			}
			int _channel = token.getChannel();
			if (_channel == 99) {
				hiddenTokenText.append(token.getText());
			} else {
				visibleTokenText.append(token.getText());
			}
		}
		int offset = -1;
		if (firstToken != null) {
			offset = firstToken.getStartIndex();
		}
		org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformationAdapter layoutInformationAdapter = getLayoutInformationAdapter(element);
		layoutInformationAdapter.addLayoutInformation(new org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformation(syntaxElement, object, offset, hiddenTokenText.toString(), visibleTokenText.toString()));
		this.lastPosition2 = (endPos < 0 ? 0 : endPos + 1);
	}
	
	protected org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformationAdapter getLayoutInformationAdapter(org.eclipse.emf.ecore.EObject element) {
		for (org.eclipse.emf.common.notify.Adapter adapter : element.eAdapters()) {
			if (adapter instanceof org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformationAdapter) {
				return (org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformationAdapter) adapter;
			}
		}
		org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformationAdapter newAdapter = new org.jastemf.spec.ast.resource.ast.mopp.AstLayoutInformationAdapter();
		element.eAdapters().add(newAdapter);
		return newAdapter;
	}
	
}

start returns [ org.eclipse.emf.ecore.EObject element = null]
:
	{
		// follow set for start rule(s)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_0, 0, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_0, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_1, 0, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_2, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
		expectedElementsIndexOfLastCompleteElement = expectedElements.size() - 1;
	}
	(
		c0 = parse_org_jastemf_spec_ast_Grammar{ element = c0; }
	)
	EOF	
;

parse_org_jastemf_spec_ast_Grammar returns [org.jastemf.spec.ast.Grammar element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_jastemf_spec_ast_TypeDecl			{
				if (terminateParsing) {
					throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
				}
				if (element == null) {
					element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createGrammar();
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						addObjectToList(element, org.jastemf.spec.ast.AstPackage.GRAMMAR__TYPE_DECL, a0_0);
						completedElement(a0_0, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_0_0_0_0, a0_0);
					copyLocalizationInfos(a0_0, element); 				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_0, 1, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_0, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_1, 1, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_2, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
	}
	
;

parse_org_jastemf_spec_ast_ASTDecl returns [org.jastemf.spec.ast.ASTDecl element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_jastemf_spec_ast_Abstract			{
				if (terminateParsing) {
					throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
				}
				if (element == null) {
					element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createASTDecl();
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__ABSTRACT), a0_0);
						completedElement(a0_0, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_1_0_0_0, a0_0);
					copyLocalizationInfos(a0_0, element); 				}
			}
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_1, 2, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_2));
	}
	
	(
		a1_0 = parse_org_jastemf_spec_ast_IdDecl		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createASTDecl();
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__ID_DECL), a1_0);
					completedElement(a1_0, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_1_0_0_1, a1_0);
				copyLocalizationInfos(a1_0, element); 			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_2, 3));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_3, 3));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 3));
	}
	
	(
		(
			a2 = ':' {
				if (element == null) {
					element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createASTDecl();
					incompleteObjects.push(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_1_0_0_2_0_0_0, null);
				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_3));
			}
			
			(
				a3_0 = parse_org_jastemf_spec_ast_IdUse				{
					if (terminateParsing) {
						throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
					}
					if (element == null) {
						element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createASTDecl();
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AST_DECL__SUPER_CLASS_ID), a3_0);
							completedElement(a3_0, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_1_0_0_2_0_0_1, a3_0);
						copyLocalizationInfos(a3_0, element); 					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_3, 5));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 5));
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_3, 6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 6));
	}
	
	(
		(
			a4 = '::=' {
				if (element == null) {
					element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createASTDecl();
					incompleteObjects.push(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_1_0_0_3_0_0_0, null);
				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a4, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
			}
			
			(
				(
					a5_0 = parse_org_jastemf_spec_ast_Components					{
						if (terminateParsing) {
							throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
						}
						if (element == null) {
							element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createASTDecl();
						}
						if (a5_0 != null) {
							if (a5_0 != null) {
								addObjectToList(element, org.jastemf.spec.ast.AstPackage.AST_DECL__COMPONENTS, a5_0);
								completedElement(a5_0, true);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_1_0_0_3_0_0_1, a5_0);
							copyLocalizationInfos(a5_0, element); 						}
					}
				)
				
			)+			{
				// expected elements (follow set)
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 8, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 8, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 8, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 8, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 8, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 8, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 8, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 8, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 8));
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 9));
	}
	
	a6 = ';' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createASTDecl();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_1_0_0_4, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_0, 10, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_0, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_1, 10, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_2, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
	}
	
;

parse_org_jastemf_spec_ast_ListComponents returns [org.jastemf.spec.ast.ListComponents element = null]
@init{
}
:
	(
		a0_0 = parse_org_jastemf_spec_ast_Id		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createListComponents();
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.LIST_COMPONENTS__ID), a0_0);
					completedElement(a0_0, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_2_0_0_0, a0_0);
				copyLocalizationInfos(a0_0, element); 			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_13, 11));
	}
	
	a1 = '*' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createListComponents();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_2_0_0_1, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 12, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 12, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 12, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 12, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 12, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 12, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 12, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 12, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 12));
	}
	
	|//derived choice rules for sub-classes: 
	
	c0 = parse_org_jastemf_spec_ast_ListComponentsNTA{ element = c0; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_jastemf_spec_ast_ListComponentsNTA returns [org.jastemf.spec.ast.ListComponentsNTA element = null]
@init{
}
:
	a0 = '/' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createListComponentsNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_3_0_0_0, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 13, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 13, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5));
	}
	
	(
		a1_0 = parse_org_jastemf_spec_ast_Id		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createListComponentsNTA();
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.LIST_COMPONENTS_NTA__ID), a1_0);
					completedElement(a1_0, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_3_0_0_1, a1_0);
				copyLocalizationInfos(a1_0, element); 			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_14, 14));
	}
	
	a2 = '*' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createListComponentsNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_3_0_0_2, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_15, 15));
	}
	
	a3 = '/' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createListComponentsNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_3_0_0_3, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 16, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 16, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 16, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 16, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 16, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 16, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 16, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 16, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 16));
	}
	
;

parse_org_jastemf_spec_ast_TokenComponent returns [org.jastemf.spec.ast.TokenComponent element = null]
@init{
}
:
	a0 = '<' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponent();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_4_0_0_0, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_16, 17, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_8));
	}
	
	(
		a1_0 = parse_org_jastemf_spec_ast_TokenId		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponent();
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_COMPONENT__TOKEN_ID), a1_0);
					completedElement(a1_0, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_4_0_0_1, a1_0);
				copyLocalizationInfos(a1_0, element); 			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 18));
	}
	
	a2 = '>' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponent();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_4_0_0_2, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 19, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 19, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 19, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 19, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 19, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 19, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 19, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 19, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 19));
	}
	
	|//derived choice rules for sub-classes: 
	
	c0 = parse_org_jastemf_spec_ast_TokenComponentNTA{ element = c0; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_jastemf_spec_ast_TokenComponentNTA returns [org.jastemf.spec.ast.TokenComponentNTA element = null]
@init{
}
:
	a0 = '/' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponentNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_5_0_0_0, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_18, 20));
	}
	
	a1 = '<' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponentNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_5_0_0_1, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_16, 21, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_8));
	}
	
	(
		a2_0 = parse_org_jastemf_spec_ast_TokenId		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponentNTA();
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_COMPONENT_NTA__TOKEN_ID), a2_0);
					completedElement(a2_0, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_5_0_0_2, a2_0);
				copyLocalizationInfos(a2_0, element); 			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 22));
	}
	
	a3 = '>' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponentNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_5_0_0_3, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_20, 23));
	}
	
	a4 = '/' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponentNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_5_0_0_4, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 24, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 24, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 24, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 24, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 24, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 24, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 24, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 24, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 24));
	}
	
;

parse_org_jastemf_spec_ast_OptionalComponent returns [org.jastemf.spec.ast.OptionalComponent element = null]
@init{
}
:
	a0 = '[' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponent();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_6_0_0_0, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 25, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_9));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 25, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_9));
	}
	
	(
		a1_0 = parse_org_jastemf_spec_ast_Id		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponent();
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.OPTIONAL_COMPONENT__ID), a1_0);
					completedElement(a1_0, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_6_0_0_1, a1_0);
				copyLocalizationInfos(a1_0, element); 			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_21, 26));
	}
	
	a2 = ']' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponent();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_6_0_0_2, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 27, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 27, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 27, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 27, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 27, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 27, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 27, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 27, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 27));
	}
	
	|//derived choice rules for sub-classes: 
	
	c0 = parse_org_jastemf_spec_ast_OptionalComponentNTA{ element = c0; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_jastemf_spec_ast_OptionalComponentNTA returns [org.jastemf.spec.ast.OptionalComponentNTA element = null]
@init{
}
:
	a0 = '/' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponentNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_7_0_0_0, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_22, 28));
	}
	
	a1 = '[' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponentNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_7_0_0_1, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 29, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_9));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 29, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_9));
	}
	
	(
		a2_0 = parse_org_jastemf_spec_ast_Id		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponentNTA();
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.OPTIONAL_COMPONENT_NTA__ID), a2_0);
					completedElement(a2_0, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_7_0_0_2, a2_0);
				copyLocalizationInfos(a2_0, element); 			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_23, 30));
	}
	
	a3 = ']' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponentNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_7_0_0_3, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_24, 31));
	}
	
	a4 = '/' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponentNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_7_0_0_4, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 32, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 32, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 32, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 32, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 32, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 32, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 32, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 32, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 32));
	}
	
;

parse_org_jastemf_spec_ast_AggregateComponents returns [org.jastemf.spec.ast.AggregateComponents element = null]
@init{
}
:
	(
		a0_0 = parse_org_jastemf_spec_ast_Id		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createAggregateComponents();
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AGGREGATE_COMPONENTS__ID), a0_0);
					completedElement(a0_0, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_8_0_0_0, a0_0);
				copyLocalizationInfos(a0_0, element); 			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 33, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 33, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 33, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 33, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 33, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 33, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 33, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 33, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 33));
	}
	
	|//derived choice rules for sub-classes: 
	
	c0 = parse_org_jastemf_spec_ast_AggregateComponentsNTA{ element = c0; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_jastemf_spec_ast_AggregateComponentsNTA returns [org.jastemf.spec.ast.AggregateComponentsNTA element = null]
@init{
}
:
	a0 = '/' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createAggregateComponentsNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_9_0_0_0, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 34, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_10));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 34, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_10));
	}
	
	(
		a1_0 = parse_org_jastemf_spec_ast_Id		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createAggregateComponentsNTA();
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.AGGREGATE_COMPONENTS_NTA__ID), a1_0);
					completedElement(a1_0, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_9_0_0_1, a1_0);
				copyLocalizationInfos(a1_0, element); 			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_25, 35));
	}
	
	a2 = '/' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createAggregateComponentsNTA();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_9_0_0_2, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 36, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 36, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 36, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 36, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 36, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 36, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 36, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 36, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 36));
	}
	
;

parse_org_jastemf_spec_ast_Abstract returns [org.jastemf.spec.ast.Abstract element = null]
@init{
}
:
	a0 = 'abstract' {
		if (element == null) {
			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createAbstract();
			incompleteObjects.push(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_10_0_0_0, null);
		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_1, 37, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_2));
	}
	
;

parse_org_jastemf_spec_ast_Id returns [org.jastemf.spec.ast.Id element = null]
@init{
}
:
	(
		(
			(
				a0_0 = parse_org_jastemf_spec_ast_NameNode				{
					if (terminateParsing) {
						throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
					}
					if (element == null) {
						element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createId();
					}
					if (a0_0 != null) {
						if (a0_0 != null) {
							element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID__NAME_NODE), a0_0);
							completedElement(a0_0, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_11_0_0_0_0_0_0, a0_0);
						copyLocalizationInfos(a0_0, element); 					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_26, 38));
			}
			
			a1 = ':' {
				if (element == null) {
					element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createId();
					incompleteObjects.push(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_11_0_0_0_0_0_1, null);
				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 39, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7));
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 40, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7));
	}
	
	(
		a2_0 = parse_org_jastemf_spec_ast_IdUse		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createId();
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID__ID_USE), a2_0);
					completedElement(a2_0, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_11_0_0_1, a2_0);
				copyLocalizationInfos(a2_0, element); 			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_13, 41));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_14, 41));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_21, 41));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_23, 41));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 41, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 41, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_7, 41, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_8, 41, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_9, 41, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_10, 41, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_11, 41, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_12, 41, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_6));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 41));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_25, 41));
	}
	
;

parse_org_jastemf_spec_ast_NameNode returns [org.jastemf.spec.ast.NameNode element = null]
@init{
}
:
	(
		a0 = IDENT		
		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createNameNode();
			}
			if (a0 != null) {
				org.jastemf.spec.ast.resource.ast.IAstTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENT");
				tokenResolver.setOptions(getOptions());
				org.jastemf.spec.ast.resource.ast.IAstTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.NAME_NODE__ID), result);
				java.lang.Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_2_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_2_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_2_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_2_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String)resolvedObject;
				if (resolved != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.NAME_NODE__ID), resolved);
					completedElement(resolved, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_12_0_0_0, resolved);
				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_26, 42));
	}
	
;

parse_org_jastemf_spec_ast_TokenId returns [org.jastemf.spec.ast.TokenId element = null]
@init{
}
:
	(
		a0 = IDENT		
		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenId();
			}
			if (a0 != null) {
				org.jastemf.spec.ast.resource.ast.IAstTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENT");
				tokenResolver.setOptions(getOptions());
				org.jastemf.spec.ast.resource.ast.IAstTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__ID), result);
				java.lang.Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_2_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_2_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_2_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_2_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String)resolvedObject;
				if (resolved != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__ID), resolved);
					completedElement(resolved, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_13_0_0_0, resolved);
				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_27, 43));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 43));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 43));
	}
	
	(
		(
			a1 = ':' {
				if (element == null) {
					element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenId();
					incompleteObjects.push(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_13_0_0_1_0_0_0, null);
				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_28, 44));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_29, 44));
			}
			
			(
				(
					a2 = IDENT					
					{
						if (terminateParsing) {
							throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
						}
						if (element == null) {
							element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenId();
						}
						if (a2 != null) {
							org.jastemf.spec.ast.resource.ast.IAstTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENT");
							tokenResolver.setOptions(getOptions());
							org.jastemf.spec.ast.resource.ast.IAstTokenResolveResult result = getFreshTokenResolveResult();
							tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__TYPE), result);
							java.lang.Object resolvedObject = result.getResolvedToken();
							if (resolvedObject == null) {
								addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_2_0.CommonToken) a2).getLine(), ((org.antlr.runtime3_2_0.CommonToken) a2).getCharPositionInLine(), ((org.antlr.runtime3_2_0.CommonToken) a2).getStartIndex(), ((org.antlr.runtime3_2_0.CommonToken) a2).getStopIndex());
							}
							java.lang.String resolved = (java.lang.String)resolvedObject;
							if (resolved != null) {
								element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__TYPE), resolved);
								completedElement(resolved, false);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_13_0_0_1_0_0_1_0_0_0, resolved);
							copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken) a2, element);
						}
					}
				)
				{
					// expected elements (follow set)
					addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 45));
					addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 45));
				}
				
				
				|				(
					a3 = QIDENT					
					{
						if (terminateParsing) {
							throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
						}
						if (element == null) {
							element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenId();
						}
						if (a3 != null) {
							org.jastemf.spec.ast.resource.ast.IAstTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("QIDENT");
							tokenResolver.setOptions(getOptions());
							org.jastemf.spec.ast.resource.ast.IAstTokenResolveResult result = getFreshTokenResolveResult();
							tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__TYPE), result);
							java.lang.Object resolvedObject = result.getResolvedToken();
							if (resolvedObject == null) {
								addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_2_0.CommonToken) a3).getLine(), ((org.antlr.runtime3_2_0.CommonToken) a3).getCharPositionInLine(), ((org.antlr.runtime3_2_0.CommonToken) a3).getStartIndex(), ((org.antlr.runtime3_2_0.CommonToken) a3).getStopIndex());
							}
							java.lang.String resolved = (java.lang.String)resolvedObject;
							if (resolved != null) {
								element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.TOKEN_ID__TYPE), resolved);
								completedElement(resolved, false);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_13_0_0_1_0_0_1_0_1_0, resolved);
							copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken) a3, element);
						}
					}
				)
				{
					// expected elements (follow set)
					addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 46));
					addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 46));
				}
				
			)
			{
				// expected elements (follow set)
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 47));
				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 47));
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 48));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 48));
	}
	
;

parse_org_jastemf_spec_ast_IdUse returns [org.jastemf.spec.ast.IdUse element = null]
@init{
}
:
	(
		a0 = IDENT		
		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createIdUse();
			}
			if (a0 != null) {
				org.jastemf.spec.ast.resource.ast.IAstTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENT");
				tokenResolver.setOptions(getOptions());
				org.jastemf.spec.ast.resource.ast.IAstTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_USE__ID), result);
				java.lang.Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_2_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_2_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_2_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_2_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String)resolvedObject;
				if (resolved != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_USE__ID), resolved);
					completedElement(resolved, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_14_0_0_0, resolved);
				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_3, 49));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 49));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_13, 49));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_14, 49));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_21, 49));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_23, 49));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_25, 49));
	}
	
;

parse_org_jastemf_spec_ast_IdDecl returns [org.jastemf.spec.ast.IdDecl element = null]
@init{
}
:
	(
		a0 = IDENT		
		{
			if (terminateParsing) {
				throw new org.jastemf.spec.ast.resource.ast.mopp.AstTerminateParsingException();
			}
			if (element == null) {
				element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createIdDecl();
			}
			if (a0 != null) {
				org.jastemf.spec.ast.resource.ast.IAstTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENT");
				tokenResolver.setOptions(getOptions());
				org.jastemf.spec.ast.resource.ast.IAstTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_DECL__ID), result);
				java.lang.Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((org.antlr.runtime3_2_0.CommonToken) a0).getLine(), ((org.antlr.runtime3_2_0.CommonToken) a0).getCharPositionInLine(), ((org.antlr.runtime3_2_0.CommonToken) a0).getStartIndex(), ((org.antlr.runtime3_2_0.CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String)resolvedObject;
				if (resolved != null) {
					element.eSet(element.eClass().getEStructuralFeature(org.jastemf.spec.ast.AstPackage.ID_DECL__ID), resolved);
					completedElement(resolved, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_15_0_0_0, resolved);
				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_2, 50));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_3, 50));
		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 50));
	}
	
;

parse_org_jastemf_spec_ast_TypeDecl returns [org.jastemf.spec.ast.TypeDecl element = null]
:
	c0 = parse_org_jastemf_spec_ast_ASTDecl{ element = c0; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_jastemf_spec_ast_Components returns [org.jastemf.spec.ast.Components element = null]
:
	c0 = parse_org_jastemf_spec_ast_ListComponents{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_jastemf_spec_ast_ListComponentsNTA{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_jastemf_spec_ast_TokenComponent{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_jastemf_spec_ast_TokenComponentNTA{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_jastemf_spec_ast_OptionalComponent{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_jastemf_spec_ast_OptionalComponentNTA{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_jastemf_spec_ast_AggregateComponents{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_jastemf_spec_ast_AggregateComponentsNTA{ element = c7; /* this is a subclass or primitive expression choice */ }
	
;

COMMENT:
	'//'(~('\n'|'\r'|'\uffff'))*
	{ _channel = 99; }
;
ML_COMMENT:
	'/*'.*'*/'	{ _channel = 99; }
;
IDENT:
	('a'..'z'|'A'..'Z'|'_')('0'..'9'|'a'..'z'|'A'..'Z'|'_')*;
QIDENT:
	('a'..'z'|'A'..'Z'|'_')('0'..'9'|'a'..'z'|'A'..'Z'|'_')*('.'('a'..'z'|'A'..'Z'|'_')('0'..'9'|'a'..'z'|'A'..'Z'|'_')*)+;
WHITESPACE:
	(' '|'\t'|'\f')
	{ _channel = 99; }
;
LINEBREAK:
	('\r\n'|'\r'|'\n')
	{ _channel = 99; }
;

