// $ANTLR ${project.version} ${buildNumber}

	package org.jastemf.spec.ast.resource.ast.mopp;


import org.antlr.runtime3_2_0.*;
import java.util.HashMap;
public class AstParser extends AstANTLRParserBase {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "QIDENT", "COMMENT", "ML_COMMENT", "WHITESPACE", "LINEBREAK", "':'", "'::='", "';'", "'*'", "'/'", "'<'", "'>'", "'['", "']'", "'abstract'"
    };
    public static final int QIDENT=5;
    public static final int WHITESPACE=8;
    public static final int EOF=-1;
    public static final int T__19=19;
    public static final int ML_COMMENT=7;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__10=10;
    public static final int IDENT=4;
    public static final int COMMENT=6;
    public static final int LINEBREAK=9;

    // delegates
    // delegators


        public AstParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public AstParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[38+1];
             
             
        }
        

    public String[] getTokenNames() { return AstParser.tokenNames; }
    public String getGrammarFileName() { return "Ast.g"; }


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
    	



    // $ANTLR start "start"
    // Ast.g:674:1: start returns [ org.eclipse.emf.ecore.EObject element = null] : (c0= parse_org_jastemf_spec_ast_Grammar ) EOF ;
    public final org.eclipse.emf.ecore.EObject start() throws RecognitionException {
        org.eclipse.emf.ecore.EObject element =  null;
        int start_StartIndex = input.index();
        org.jastemf.spec.ast.Grammar c0 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return element; }
            // Ast.g:675:1: ( (c0= parse_org_jastemf_spec_ast_Grammar ) EOF )
            // Ast.g:676:2: (c0= parse_org_jastemf_spec_ast_Grammar ) EOF
            {
            if ( state.backtracking==0 ) {

              		// follow set for start rule(s)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_0, 0, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_0, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_1, 0, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_2, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
              		expectedElementsIndexOfLastCompleteElement = expectedElements.size() - 1;
              	
            }
            // Ast.g:682:2: (c0= parse_org_jastemf_spec_ast_Grammar )
            // Ast.g:683:3: c0= parse_org_jastemf_spec_ast_Grammar
            {
            pushFollow(FOLLOW_parse_org_jastemf_spec_ast_Grammar_in_start82);
            c0=parse_org_jastemf_spec_ast_Grammar();

            state._fsp--;
            if (state.failed) return element;
            if ( state.backtracking==0 ) {
               element = c0; 
            }

            }

            match(input,EOF,FOLLOW_EOF_in_start89); if (state.failed) return element;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 1, start_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "start"


    // $ANTLR start "parse_org_jastemf_spec_ast_Grammar"
    // Ast.g:688:1: parse_org_jastemf_spec_ast_Grammar returns [org.jastemf.spec.ast.Grammar element = null] : ( (a0_0= parse_org_jastemf_spec_ast_TypeDecl ) )* ;
    public final org.jastemf.spec.ast.Grammar parse_org_jastemf_spec_ast_Grammar() throws RecognitionException {
        org.jastemf.spec.ast.Grammar element =  null;
        int parse_org_jastemf_spec_ast_Grammar_StartIndex = input.index();
        org.jastemf.spec.ast.TypeDecl a0_0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return element; }
            // Ast.g:691:1: ( ( (a0_0= parse_org_jastemf_spec_ast_TypeDecl ) )* )
            // Ast.g:692:2: ( (a0_0= parse_org_jastemf_spec_ast_TypeDecl ) )*
            {
            // Ast.g:692:2: ( (a0_0= parse_org_jastemf_spec_ast_TypeDecl ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IDENT||LA1_0==19) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Ast.g:693:3: (a0_0= parse_org_jastemf_spec_ast_TypeDecl )
            	    {
            	    // Ast.g:693:3: (a0_0= parse_org_jastemf_spec_ast_TypeDecl )
            	    // Ast.g:694:4: a0_0= parse_org_jastemf_spec_ast_TypeDecl
            	    {
            	    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_TypeDecl_in_parse_org_jastemf_spec_ast_Grammar121);
            	    a0_0=parse_org_jastemf_spec_ast_TypeDecl();

            	    state._fsp--;
            	    if (state.failed) return element;
            	    if ( state.backtracking==0 ) {

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

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_0, 1, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_0, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_1, 1, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_2, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
              	
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 2, parse_org_jastemf_spec_ast_Grammar_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_Grammar"


    // $ANTLR start "parse_org_jastemf_spec_ast_ASTDecl"
    // Ast.g:720:1: parse_org_jastemf_spec_ast_ASTDecl returns [org.jastemf.spec.ast.ASTDecl element = null] : ( (a0_0= parse_org_jastemf_spec_ast_Abstract ) )? (a1_0= parse_org_jastemf_spec_ast_IdDecl ) ( (a2= ':' (a3_0= parse_org_jastemf_spec_ast_IdUse ) ) )? ( (a4= '::=' ( (a5_0= parse_org_jastemf_spec_ast_Components ) )+ ) )? a6= ';' ;
    public final org.jastemf.spec.ast.ASTDecl parse_org_jastemf_spec_ast_ASTDecl() throws RecognitionException {
        org.jastemf.spec.ast.ASTDecl element =  null;
        int parse_org_jastemf_spec_ast_ASTDecl_StartIndex = input.index();
        Token a2=null;
        Token a4=null;
        Token a6=null;
        org.jastemf.spec.ast.Abstract a0_0 = null;

        org.jastemf.spec.ast.IdDecl a1_0 = null;

        org.jastemf.spec.ast.IdUse a3_0 = null;

        org.jastemf.spec.ast.Components a5_0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return element; }
            // Ast.g:723:1: ( ( (a0_0= parse_org_jastemf_spec_ast_Abstract ) )? (a1_0= parse_org_jastemf_spec_ast_IdDecl ) ( (a2= ':' (a3_0= parse_org_jastemf_spec_ast_IdUse ) ) )? ( (a4= '::=' ( (a5_0= parse_org_jastemf_spec_ast_Components ) )+ ) )? a6= ';' )
            // Ast.g:724:2: ( (a0_0= parse_org_jastemf_spec_ast_Abstract ) )? (a1_0= parse_org_jastemf_spec_ast_IdDecl ) ( (a2= ':' (a3_0= parse_org_jastemf_spec_ast_IdUse ) ) )? ( (a4= '::=' ( (a5_0= parse_org_jastemf_spec_ast_Components ) )+ ) )? a6= ';'
            {
            // Ast.g:724:2: ( (a0_0= parse_org_jastemf_spec_ast_Abstract ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==19) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // Ast.g:725:3: (a0_0= parse_org_jastemf_spec_ast_Abstract )
                    {
                    // Ast.g:725:3: (a0_0= parse_org_jastemf_spec_ast_Abstract )
                    // Ast.g:726:4: a0_0= parse_org_jastemf_spec_ast_Abstract
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_Abstract_in_parse_org_jastemf_spec_ast_ASTDecl171);
                    a0_0=parse_org_jastemf_spec_ast_Abstract();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {

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

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_1, 2, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_2));
              	
            }
            // Ast.g:749:2: (a1_0= parse_org_jastemf_spec_ast_IdDecl )
            // Ast.g:750:3: a1_0= parse_org_jastemf_spec_ast_IdDecl
            {
            pushFollow(FOLLOW_parse_org_jastemf_spec_ast_IdDecl_in_parse_org_jastemf_spec_ast_ASTDecl201);
            a1_0=parse_org_jastemf_spec_ast_IdDecl();

            state._fsp--;
            if (state.failed) return element;
            if ( state.backtracking==0 ) {

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

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_2, 3));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_3, 3));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 3));
              	
            }
            // Ast.g:774:2: ( (a2= ':' (a3_0= parse_org_jastemf_spec_ast_IdUse ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==10) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // Ast.g:775:3: (a2= ':' (a3_0= parse_org_jastemf_spec_ast_IdUse ) )
                    {
                    // Ast.g:775:3: (a2= ':' (a3_0= parse_org_jastemf_spec_ast_IdUse ) )
                    // Ast.g:776:4: a2= ':' (a3_0= parse_org_jastemf_spec_ast_IdUse )
                    {
                    a2=(Token)match(input,10,FOLLOW_10_in_parse_org_jastemf_spec_ast_ASTDecl228); if (state.failed) return element;
                    if ( state.backtracking==0 ) {

                      				if (element == null) {
                      					element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createASTDecl();
                      					incompleteObjects.push(element);
                      				}
                      				collectHiddenTokens(element);
                      				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_1_0_0_2_0_0_0, null);
                      				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a2, element);
                      			
                    }
                    if ( state.backtracking==0 ) {

                      				// expected elements (follow set)
                      				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_3));
                      			
                    }
                    // Ast.g:790:4: (a3_0= parse_org_jastemf_spec_ast_IdUse )
                    // Ast.g:791:5: a3_0= parse_org_jastemf_spec_ast_IdUse
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_IdUse_in_parse_org_jastemf_spec_ast_ASTDecl254);
                    a3_0=parse_org_jastemf_spec_ast_IdUse();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {

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

                    }

                    if ( state.backtracking==0 ) {

                      				// expected elements (follow set)
                      				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_3, 5));
                      				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 5));
                      			
                    }

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_3, 6));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 6));
              	
            }
            // Ast.g:822:2: ( (a4= '::=' ( (a5_0= parse_org_jastemf_spec_ast_Components ) )+ ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==11) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // Ast.g:823:3: (a4= '::=' ( (a5_0= parse_org_jastemf_spec_ast_Components ) )+ )
                    {
                    // Ast.g:823:3: (a4= '::=' ( (a5_0= parse_org_jastemf_spec_ast_Components ) )+ )
                    // Ast.g:824:4: a4= '::=' ( (a5_0= parse_org_jastemf_spec_ast_Components ) )+
                    {
                    a4=(Token)match(input,11,FOLLOW_11_in_parse_org_jastemf_spec_ast_ASTDecl304); if (state.failed) return element;
                    if ( state.backtracking==0 ) {

                      				if (element == null) {
                      					element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createASTDecl();
                      					incompleteObjects.push(element);
                      				}
                      				collectHiddenTokens(element);
                      				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_1_0_0_3_0_0_0, null);
                      				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a4, element);
                      			
                    }
                    if ( state.backtracking==0 ) {

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
                    // Ast.g:845:4: ( (a5_0= parse_org_jastemf_spec_ast_Components ) )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==IDENT||(LA4_0>=14 && LA4_0<=15)||LA4_0==17) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // Ast.g:846:5: (a5_0= parse_org_jastemf_spec_ast_Components )
                    	    {
                    	    // Ast.g:846:5: (a5_0= parse_org_jastemf_spec_ast_Components )
                    	    // Ast.g:847:6: a5_0= parse_org_jastemf_spec_ast_Components
                    	    {
                    	    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_Components_in_parse_org_jastemf_spec_ast_ASTDecl337);
                    	    a5_0=parse_org_jastemf_spec_ast_Components();

                    	    state._fsp--;
                    	    if (state.failed) return element;
                    	    if ( state.backtracking==0 ) {

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

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt4 >= 1 ) break loop4;
                    	    if (state.backtracking>0) {state.failed=true; return element;}
                                EarlyExitException eee =
                                    new EarlyExitException(4, input);
                                throw eee;
                        }
                        cnt4++;
                    } while (true);

                    if ( state.backtracking==0 ) {

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

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 9));
              	
            }
            a6=(Token)match(input,12,FOLLOW_12_in_parse_org_jastemf_spec_ast_ASTDecl390); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createASTDecl();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_1_0_0_4, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a6, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_0, 10, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_0, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_1, 10, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_2, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_1));
              	
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 3, parse_org_jastemf_spec_ast_ASTDecl_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_ASTDecl"


    // $ANTLR start "parse_org_jastemf_spec_ast_ListComponents"
    // Ast.g:902:1: parse_org_jastemf_spec_ast_ListComponents returns [org.jastemf.spec.ast.ListComponents element = null] : ( (a0_0= parse_org_jastemf_spec_ast_Id ) a1= '*' | c0= parse_org_jastemf_spec_ast_ListComponentsNTA );
    public final org.jastemf.spec.ast.ListComponents parse_org_jastemf_spec_ast_ListComponents() throws RecognitionException {
        org.jastemf.spec.ast.ListComponents element =  null;
        int parse_org_jastemf_spec_ast_ListComponents_StartIndex = input.index();
        Token a1=null;
        org.jastemf.spec.ast.Id a0_0 = null;

        org.jastemf.spec.ast.ListComponentsNTA c0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return element; }
            // Ast.g:905:1: ( (a0_0= parse_org_jastemf_spec_ast_Id ) a1= '*' | c0= parse_org_jastemf_spec_ast_ListComponentsNTA )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==IDENT) ) {
                alt6=1;
            }
            else if ( (LA6_0==14) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return element;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // Ast.g:906:2: (a0_0= parse_org_jastemf_spec_ast_Id ) a1= '*'
                    {
                    // Ast.g:906:2: (a0_0= parse_org_jastemf_spec_ast_Id )
                    // Ast.g:907:3: a0_0= parse_org_jastemf_spec_ast_Id
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_ListComponents423);
                    a0_0=parse_org_jastemf_spec_ast_Id();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {

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

                    }

                    if ( state.backtracking==0 ) {

                      		// expected elements (follow set)
                      		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_13, 11));
                      	
                    }
                    a1=(Token)match(input,13,FOLLOW_13_in_parse_org_jastemf_spec_ast_ListComponents441); if (state.failed) return element;
                    if ( state.backtracking==0 ) {

                      		if (element == null) {
                      			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createListComponents();
                      			incompleteObjects.push(element);
                      		}
                      		collectHiddenTokens(element);
                      		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_2_0_0_1, null);
                      		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a1, element);
                      	
                    }
                    if ( state.backtracking==0 ) {

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

                    }
                    break;
                case 2 :
                    // Ast.g:953:2: c0= parse_org_jastemf_spec_ast_ListComponentsNTA
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_ListComponentsNTA_in_parse_org_jastemf_spec_ast_ListComponents460);
                    c0=parse_org_jastemf_spec_ast_ListComponentsNTA();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c0; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 4, parse_org_jastemf_spec_ast_ListComponents_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_ListComponents"


    // $ANTLR start "parse_org_jastemf_spec_ast_ListComponentsNTA"
    // Ast.g:957:1: parse_org_jastemf_spec_ast_ListComponentsNTA returns [org.jastemf.spec.ast.ListComponentsNTA element = null] : a0= '/' (a1_0= parse_org_jastemf_spec_ast_Id ) a2= '*' a3= '/' ;
    public final org.jastemf.spec.ast.ListComponentsNTA parse_org_jastemf_spec_ast_ListComponentsNTA() throws RecognitionException {
        org.jastemf.spec.ast.ListComponentsNTA element =  null;
        int parse_org_jastemf_spec_ast_ListComponentsNTA_StartIndex = input.index();
        Token a0=null;
        Token a2=null;
        Token a3=null;
        org.jastemf.spec.ast.Id a1_0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return element; }
            // Ast.g:960:1: (a0= '/' (a1_0= parse_org_jastemf_spec_ast_Id ) a2= '*' a3= '/' )
            // Ast.g:961:2: a0= '/' (a1_0= parse_org_jastemf_spec_ast_Id ) a2= '*' a3= '/'
            {
            a0=(Token)match(input,14,FOLLOW_14_in_parse_org_jastemf_spec_ast_ListComponentsNTA485); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createListComponentsNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_3_0_0_0, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 13, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 13, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_5));
              	
            }
            // Ast.g:976:2: (a1_0= parse_org_jastemf_spec_ast_Id )
            // Ast.g:977:3: a1_0= parse_org_jastemf_spec_ast_Id
            {
            pushFollow(FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_ListComponentsNTA503);
            a1_0=parse_org_jastemf_spec_ast_Id();

            state._fsp--;
            if (state.failed) return element;
            if ( state.backtracking==0 ) {

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

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_14, 14));
              	
            }
            a2=(Token)match(input,13,FOLLOW_13_in_parse_org_jastemf_spec_ast_ListComponentsNTA521); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createListComponentsNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_3_0_0_2, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a2, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_15, 15));
              	
            }
            a3=(Token)match(input,14,FOLLOW_14_in_parse_org_jastemf_spec_ast_ListComponentsNTA535); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createListComponentsNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_3_0_0_3, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a3, element);
              	
            }
            if ( state.backtracking==0 ) {

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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 5, parse_org_jastemf_spec_ast_ListComponentsNTA_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_ListComponentsNTA"


    // $ANTLR start "parse_org_jastemf_spec_ast_TokenComponent"
    // Ast.g:1037:1: parse_org_jastemf_spec_ast_TokenComponent returns [org.jastemf.spec.ast.TokenComponent element = null] : (a0= '<' (a1_0= parse_org_jastemf_spec_ast_TokenId ) a2= '>' | c0= parse_org_jastemf_spec_ast_TokenComponentNTA );
    public final org.jastemf.spec.ast.TokenComponent parse_org_jastemf_spec_ast_TokenComponent() throws RecognitionException {
        org.jastemf.spec.ast.TokenComponent element =  null;
        int parse_org_jastemf_spec_ast_TokenComponent_StartIndex = input.index();
        Token a0=null;
        Token a2=null;
        org.jastemf.spec.ast.TokenId a1_0 = null;

        org.jastemf.spec.ast.TokenComponentNTA c0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return element; }
            // Ast.g:1040:1: (a0= '<' (a1_0= parse_org_jastemf_spec_ast_TokenId ) a2= '>' | c0= parse_org_jastemf_spec_ast_TokenComponentNTA )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==15) ) {
                alt7=1;
            }
            else if ( (LA7_0==14) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return element;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // Ast.g:1041:2: a0= '<' (a1_0= parse_org_jastemf_spec_ast_TokenId ) a2= '>'
                    {
                    a0=(Token)match(input,15,FOLLOW_15_in_parse_org_jastemf_spec_ast_TokenComponent564); if (state.failed) return element;
                    if ( state.backtracking==0 ) {

                      		if (element == null) {
                      			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponent();
                      			incompleteObjects.push(element);
                      		}
                      		collectHiddenTokens(element);
                      		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_4_0_0_0, null);
                      		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
                      	
                    }
                    if ( state.backtracking==0 ) {

                      		// expected elements (follow set)
                      		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_16, 17, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_8));
                      	
                    }
                    // Ast.g:1055:2: (a1_0= parse_org_jastemf_spec_ast_TokenId )
                    // Ast.g:1056:3: a1_0= parse_org_jastemf_spec_ast_TokenId
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_TokenId_in_parse_org_jastemf_spec_ast_TokenComponent582);
                    a1_0=parse_org_jastemf_spec_ast_TokenId();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {

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

                    }

                    if ( state.backtracking==0 ) {

                      		// expected elements (follow set)
                      		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 18));
                      	
                    }
                    a2=(Token)match(input,16,FOLLOW_16_in_parse_org_jastemf_spec_ast_TokenComponent600); if (state.failed) return element;
                    if ( state.backtracking==0 ) {

                      		if (element == null) {
                      			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponent();
                      			incompleteObjects.push(element);
                      		}
                      		collectHiddenTokens(element);
                      		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_4_0_0_2, null);
                      		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a2, element);
                      	
                    }
                    if ( state.backtracking==0 ) {

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

                    }
                    break;
                case 2 :
                    // Ast.g:1102:2: c0= parse_org_jastemf_spec_ast_TokenComponentNTA
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_TokenComponentNTA_in_parse_org_jastemf_spec_ast_TokenComponent619);
                    c0=parse_org_jastemf_spec_ast_TokenComponentNTA();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c0; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 6, parse_org_jastemf_spec_ast_TokenComponent_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_TokenComponent"


    // $ANTLR start "parse_org_jastemf_spec_ast_TokenComponentNTA"
    // Ast.g:1106:1: parse_org_jastemf_spec_ast_TokenComponentNTA returns [org.jastemf.spec.ast.TokenComponentNTA element = null] : a0= '/' a1= '<' (a2_0= parse_org_jastemf_spec_ast_TokenId ) a3= '>' a4= '/' ;
    public final org.jastemf.spec.ast.TokenComponentNTA parse_org_jastemf_spec_ast_TokenComponentNTA() throws RecognitionException {
        org.jastemf.spec.ast.TokenComponentNTA element =  null;
        int parse_org_jastemf_spec_ast_TokenComponentNTA_StartIndex = input.index();
        Token a0=null;
        Token a1=null;
        Token a3=null;
        Token a4=null;
        org.jastemf.spec.ast.TokenId a2_0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return element; }
            // Ast.g:1109:1: (a0= '/' a1= '<' (a2_0= parse_org_jastemf_spec_ast_TokenId ) a3= '>' a4= '/' )
            // Ast.g:1110:2: a0= '/' a1= '<' (a2_0= parse_org_jastemf_spec_ast_TokenId ) a3= '>' a4= '/'
            {
            a0=(Token)match(input,14,FOLLOW_14_in_parse_org_jastemf_spec_ast_TokenComponentNTA644); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponentNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_5_0_0_0, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_18, 20));
              	
            }
            a1=(Token)match(input,15,FOLLOW_15_in_parse_org_jastemf_spec_ast_TokenComponentNTA658); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponentNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_5_0_0_1, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a1, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_16, 21, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_8));
              	
            }
            // Ast.g:1138:2: (a2_0= parse_org_jastemf_spec_ast_TokenId )
            // Ast.g:1139:3: a2_0= parse_org_jastemf_spec_ast_TokenId
            {
            pushFollow(FOLLOW_parse_org_jastemf_spec_ast_TokenId_in_parse_org_jastemf_spec_ast_TokenComponentNTA676);
            a2_0=parse_org_jastemf_spec_ast_TokenId();

            state._fsp--;
            if (state.failed) return element;
            if ( state.backtracking==0 ) {

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

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 22));
              	
            }
            a3=(Token)match(input,16,FOLLOW_16_in_parse_org_jastemf_spec_ast_TokenComponentNTA694); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponentNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_5_0_0_3, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a3, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_20, 23));
              	
            }
            a4=(Token)match(input,14,FOLLOW_14_in_parse_org_jastemf_spec_ast_TokenComponentNTA708); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenComponentNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_5_0_0_4, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a4, element);
              	
            }
            if ( state.backtracking==0 ) {

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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 7, parse_org_jastemf_spec_ast_TokenComponentNTA_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_TokenComponentNTA"


    // $ANTLR start "parse_org_jastemf_spec_ast_OptionalComponent"
    // Ast.g:1199:1: parse_org_jastemf_spec_ast_OptionalComponent returns [org.jastemf.spec.ast.OptionalComponent element = null] : (a0= '[' (a1_0= parse_org_jastemf_spec_ast_Id ) a2= ']' | c0= parse_org_jastemf_spec_ast_OptionalComponentNTA );
    public final org.jastemf.spec.ast.OptionalComponent parse_org_jastemf_spec_ast_OptionalComponent() throws RecognitionException {
        org.jastemf.spec.ast.OptionalComponent element =  null;
        int parse_org_jastemf_spec_ast_OptionalComponent_StartIndex = input.index();
        Token a0=null;
        Token a2=null;
        org.jastemf.spec.ast.Id a1_0 = null;

        org.jastemf.spec.ast.OptionalComponentNTA c0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return element; }
            // Ast.g:1202:1: (a0= '[' (a1_0= parse_org_jastemf_spec_ast_Id ) a2= ']' | c0= parse_org_jastemf_spec_ast_OptionalComponentNTA )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==17) ) {
                alt8=1;
            }
            else if ( (LA8_0==14) ) {
                alt8=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return element;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // Ast.g:1203:2: a0= '[' (a1_0= parse_org_jastemf_spec_ast_Id ) a2= ']'
                    {
                    a0=(Token)match(input,17,FOLLOW_17_in_parse_org_jastemf_spec_ast_OptionalComponent737); if (state.failed) return element;
                    if ( state.backtracking==0 ) {

                      		if (element == null) {
                      			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponent();
                      			incompleteObjects.push(element);
                      		}
                      		collectHiddenTokens(element);
                      		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_6_0_0_0, null);
                      		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
                      	
                    }
                    if ( state.backtracking==0 ) {

                      		// expected elements (follow set)
                      		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 25, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_9));
                      		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 25, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_9));
                      	
                    }
                    // Ast.g:1218:2: (a1_0= parse_org_jastemf_spec_ast_Id )
                    // Ast.g:1219:3: a1_0= parse_org_jastemf_spec_ast_Id
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_OptionalComponent755);
                    a1_0=parse_org_jastemf_spec_ast_Id();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {

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

                    }

                    if ( state.backtracking==0 ) {

                      		// expected elements (follow set)
                      		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_21, 26));
                      	
                    }
                    a2=(Token)match(input,18,FOLLOW_18_in_parse_org_jastemf_spec_ast_OptionalComponent773); if (state.failed) return element;
                    if ( state.backtracking==0 ) {

                      		if (element == null) {
                      			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponent();
                      			incompleteObjects.push(element);
                      		}
                      		collectHiddenTokens(element);
                      		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_6_0_0_2, null);
                      		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a2, element);
                      	
                    }
                    if ( state.backtracking==0 ) {

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

                    }
                    break;
                case 2 :
                    // Ast.g:1265:2: c0= parse_org_jastemf_spec_ast_OptionalComponentNTA
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_OptionalComponentNTA_in_parse_org_jastemf_spec_ast_OptionalComponent792);
                    c0=parse_org_jastemf_spec_ast_OptionalComponentNTA();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c0; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 8, parse_org_jastemf_spec_ast_OptionalComponent_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_OptionalComponent"


    // $ANTLR start "parse_org_jastemf_spec_ast_OptionalComponentNTA"
    // Ast.g:1269:1: parse_org_jastemf_spec_ast_OptionalComponentNTA returns [org.jastemf.spec.ast.OptionalComponentNTA element = null] : a0= '/' a1= '[' (a2_0= parse_org_jastemf_spec_ast_Id ) a3= ']' a4= '/' ;
    public final org.jastemf.spec.ast.OptionalComponentNTA parse_org_jastemf_spec_ast_OptionalComponentNTA() throws RecognitionException {
        org.jastemf.spec.ast.OptionalComponentNTA element =  null;
        int parse_org_jastemf_spec_ast_OptionalComponentNTA_StartIndex = input.index();
        Token a0=null;
        Token a1=null;
        Token a3=null;
        Token a4=null;
        org.jastemf.spec.ast.Id a2_0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return element; }
            // Ast.g:1272:1: (a0= '/' a1= '[' (a2_0= parse_org_jastemf_spec_ast_Id ) a3= ']' a4= '/' )
            // Ast.g:1273:2: a0= '/' a1= '[' (a2_0= parse_org_jastemf_spec_ast_Id ) a3= ']' a4= '/'
            {
            a0=(Token)match(input,14,FOLLOW_14_in_parse_org_jastemf_spec_ast_OptionalComponentNTA817); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponentNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_7_0_0_0, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_22, 28));
              	
            }
            a1=(Token)match(input,17,FOLLOW_17_in_parse_org_jastemf_spec_ast_OptionalComponentNTA831); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponentNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_7_0_0_1, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a1, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 29, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_9));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 29, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_9));
              	
            }
            // Ast.g:1302:2: (a2_0= parse_org_jastemf_spec_ast_Id )
            // Ast.g:1303:3: a2_0= parse_org_jastemf_spec_ast_Id
            {
            pushFollow(FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_OptionalComponentNTA849);
            a2_0=parse_org_jastemf_spec_ast_Id();

            state._fsp--;
            if (state.failed) return element;
            if ( state.backtracking==0 ) {

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

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_23, 30));
              	
            }
            a3=(Token)match(input,18,FOLLOW_18_in_parse_org_jastemf_spec_ast_OptionalComponentNTA867); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponentNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_7_0_0_3, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a3, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_24, 31));
              	
            }
            a4=(Token)match(input,14,FOLLOW_14_in_parse_org_jastemf_spec_ast_OptionalComponentNTA881); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createOptionalComponentNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_7_0_0_4, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a4, element);
              	
            }
            if ( state.backtracking==0 ) {

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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 9, parse_org_jastemf_spec_ast_OptionalComponentNTA_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_OptionalComponentNTA"


    // $ANTLR start "parse_org_jastemf_spec_ast_AggregateComponents"
    // Ast.g:1363:1: parse_org_jastemf_spec_ast_AggregateComponents returns [org.jastemf.spec.ast.AggregateComponents element = null] : ( (a0_0= parse_org_jastemf_spec_ast_Id ) | c0= parse_org_jastemf_spec_ast_AggregateComponentsNTA );
    public final org.jastemf.spec.ast.AggregateComponents parse_org_jastemf_spec_ast_AggregateComponents() throws RecognitionException {
        org.jastemf.spec.ast.AggregateComponents element =  null;
        int parse_org_jastemf_spec_ast_AggregateComponents_StartIndex = input.index();
        org.jastemf.spec.ast.Id a0_0 = null;

        org.jastemf.spec.ast.AggregateComponentsNTA c0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return element; }
            // Ast.g:1366:1: ( (a0_0= parse_org_jastemf_spec_ast_Id ) | c0= parse_org_jastemf_spec_ast_AggregateComponentsNTA )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==IDENT) ) {
                alt9=1;
            }
            else if ( (LA9_0==14) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return element;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // Ast.g:1367:2: (a0_0= parse_org_jastemf_spec_ast_Id )
                    {
                    // Ast.g:1367:2: (a0_0= parse_org_jastemf_spec_ast_Id )
                    // Ast.g:1368:3: a0_0= parse_org_jastemf_spec_ast_Id
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_AggregateComponents914);
                    a0_0=parse_org_jastemf_spec_ast_Id();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {

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

                    }

                    if ( state.backtracking==0 ) {

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

                    }
                    break;
                case 2 :
                    // Ast.g:1400:2: c0= parse_org_jastemf_spec_ast_AggregateComponentsNTA
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_AggregateComponentsNTA_in_parse_org_jastemf_spec_ast_AggregateComponents937);
                    c0=parse_org_jastemf_spec_ast_AggregateComponentsNTA();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c0; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 10, parse_org_jastemf_spec_ast_AggregateComponents_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_AggregateComponents"


    // $ANTLR start "parse_org_jastemf_spec_ast_AggregateComponentsNTA"
    // Ast.g:1404:1: parse_org_jastemf_spec_ast_AggregateComponentsNTA returns [org.jastemf.spec.ast.AggregateComponentsNTA element = null] : a0= '/' (a1_0= parse_org_jastemf_spec_ast_Id ) a2= '/' ;
    public final org.jastemf.spec.ast.AggregateComponentsNTA parse_org_jastemf_spec_ast_AggregateComponentsNTA() throws RecognitionException {
        org.jastemf.spec.ast.AggregateComponentsNTA element =  null;
        int parse_org_jastemf_spec_ast_AggregateComponentsNTA_StartIndex = input.index();
        Token a0=null;
        Token a2=null;
        org.jastemf.spec.ast.Id a1_0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return element; }
            // Ast.g:1407:1: (a0= '/' (a1_0= parse_org_jastemf_spec_ast_Id ) a2= '/' )
            // Ast.g:1408:2: a0= '/' (a1_0= parse_org_jastemf_spec_ast_Id ) a2= '/'
            {
            a0=(Token)match(input,14,FOLLOW_14_in_parse_org_jastemf_spec_ast_AggregateComponentsNTA962); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createAggregateComponentsNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_9_0_0_0, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_6, 34, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_4, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_10));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 34, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_10));
              	
            }
            // Ast.g:1423:2: (a1_0= parse_org_jastemf_spec_ast_Id )
            // Ast.g:1424:3: a1_0= parse_org_jastemf_spec_ast_Id
            {
            pushFollow(FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_AggregateComponentsNTA980);
            a1_0=parse_org_jastemf_spec_ast_Id();

            state._fsp--;
            if (state.failed) return element;
            if ( state.backtracking==0 ) {

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

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_25, 35));
              	
            }
            a2=(Token)match(input,14,FOLLOW_14_in_parse_org_jastemf_spec_ast_AggregateComponentsNTA998); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createAggregateComponentsNTA();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_9_0_0_2, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a2, element);
              	
            }
            if ( state.backtracking==0 ) {

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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 11, parse_org_jastemf_spec_ast_AggregateComponentsNTA_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_AggregateComponentsNTA"


    // $ANTLR start "parse_org_jastemf_spec_ast_Abstract"
    // Ast.g:1470:1: parse_org_jastemf_spec_ast_Abstract returns [org.jastemf.spec.ast.Abstract element = null] : a0= 'abstract' ;
    public final org.jastemf.spec.ast.Abstract parse_org_jastemf_spec_ast_Abstract() throws RecognitionException {
        org.jastemf.spec.ast.Abstract element =  null;
        int parse_org_jastemf_spec_ast_Abstract_StartIndex = input.index();
        Token a0=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return element; }
            // Ast.g:1473:1: (a0= 'abstract' )
            // Ast.g:1474:2: a0= 'abstract'
            {
            a0=(Token)match(input,19,FOLLOW_19_in_parse_org_jastemf_spec_ast_Abstract1027); if (state.failed) return element;
            if ( state.backtracking==0 ) {

              		if (element == null) {
              			element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createAbstract();
              			incompleteObjects.push(element);
              		}
              		collectHiddenTokens(element);
              		retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_10_0_0_0, null);
              		copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a0, element);
              	
            }
            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_1, 37, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_2));
              	
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 12, parse_org_jastemf_spec_ast_Abstract_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_Abstract"


    // $ANTLR start "parse_org_jastemf_spec_ast_Id"
    // Ast.g:1490:1: parse_org_jastemf_spec_ast_Id returns [org.jastemf.spec.ast.Id element = null] : ( ( (a0_0= parse_org_jastemf_spec_ast_NameNode ) a1= ':' ) )? (a2_0= parse_org_jastemf_spec_ast_IdUse ) ;
    public final org.jastemf.spec.ast.Id parse_org_jastemf_spec_ast_Id() throws RecognitionException {
        org.jastemf.spec.ast.Id element =  null;
        int parse_org_jastemf_spec_ast_Id_StartIndex = input.index();
        Token a1=null;
        org.jastemf.spec.ast.NameNode a0_0 = null;

        org.jastemf.spec.ast.IdUse a2_0 = null;




        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return element; }
            // Ast.g:1493:1: ( ( ( (a0_0= parse_org_jastemf_spec_ast_NameNode ) a1= ':' ) )? (a2_0= parse_org_jastemf_spec_ast_IdUse ) )
            // Ast.g:1494:2: ( ( (a0_0= parse_org_jastemf_spec_ast_NameNode ) a1= ':' ) )? (a2_0= parse_org_jastemf_spec_ast_IdUse )
            {
            // Ast.g:1494:2: ( ( (a0_0= parse_org_jastemf_spec_ast_NameNode ) a1= ':' ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==IDENT) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==10) ) {
                    alt10=1;
                }
            }
            switch (alt10) {
                case 1 :
                    // Ast.g:1495:3: ( (a0_0= parse_org_jastemf_spec_ast_NameNode ) a1= ':' )
                    {
                    // Ast.g:1495:3: ( (a0_0= parse_org_jastemf_spec_ast_NameNode ) a1= ':' )
                    // Ast.g:1496:4: (a0_0= parse_org_jastemf_spec_ast_NameNode ) a1= ':'
                    {
                    // Ast.g:1496:4: (a0_0= parse_org_jastemf_spec_ast_NameNode )
                    // Ast.g:1497:5: a0_0= parse_org_jastemf_spec_ast_NameNode
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_NameNode_in_parse_org_jastemf_spec_ast_Id1071);
                    a0_0=parse_org_jastemf_spec_ast_NameNode();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {

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

                    }

                    if ( state.backtracking==0 ) {

                      				// expected elements (follow set)
                      				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_26, 38));
                      			
                    }
                    a1=(Token)match(input,10,FOLLOW_10_in_parse_org_jastemf_spec_ast_Id1099); if (state.failed) return element;
                    if ( state.backtracking==0 ) {

                      				if (element == null) {
                      					element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createId();
                      					incompleteObjects.push(element);
                      				}
                      				collectHiddenTokens(element);
                      				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_11_0_0_0_0_0_1, null);
                      				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a1, element);
                      			
                    }
                    if ( state.backtracking==0 ) {

                      				// expected elements (follow set)
                      				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 39, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7));
                      			
                    }

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_5, 40, org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.FEATURE_7));
              	
            }
            // Ast.g:1540:2: (a2_0= parse_org_jastemf_spec_ast_IdUse )
            // Ast.g:1541:3: a2_0= parse_org_jastemf_spec_ast_IdUse
            {
            pushFollow(FOLLOW_parse_org_jastemf_spec_ast_IdUse_in_parse_org_jastemf_spec_ast_Id1136);
            a2_0=parse_org_jastemf_spec_ast_IdUse();

            state._fsp--;
            if (state.failed) return element;
            if ( state.backtracking==0 ) {

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

            }

            if ( state.backtracking==0 ) {

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

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 13, parse_org_jastemf_spec_ast_Id_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_Id"


    // $ANTLR start "parse_org_jastemf_spec_ast_NameNode"
    // Ast.g:1578:1: parse_org_jastemf_spec_ast_NameNode returns [org.jastemf.spec.ast.NameNode element = null] : (a0= IDENT ) ;
    public final org.jastemf.spec.ast.NameNode parse_org_jastemf_spec_ast_NameNode() throws RecognitionException {
        org.jastemf.spec.ast.NameNode element =  null;
        int parse_org_jastemf_spec_ast_NameNode_StartIndex = input.index();
        Token a0=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return element; }
            // Ast.g:1581:1: ( (a0= IDENT ) )
            // Ast.g:1582:2: (a0= IDENT )
            {
            // Ast.g:1582:2: (a0= IDENT )
            // Ast.g:1583:3: a0= IDENT
            {
            a0=(Token)match(input,IDENT,FOLLOW_IDENT_in_parse_org_jastemf_spec_ast_NameNode1173); if (state.failed) return element;
            if ( state.backtracking==0 ) {

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

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_26, 42));
              	
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 14, parse_org_jastemf_spec_ast_NameNode_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_NameNode"


    // $ANTLR start "parse_org_jastemf_spec_ast_TokenId"
    // Ast.g:1618:1: parse_org_jastemf_spec_ast_TokenId returns [org.jastemf.spec.ast.TokenId element = null] : (a0= IDENT ) ( (a1= ':' ( (a2= IDENT ) | (a3= QIDENT ) ) ) )? ;
    public final org.jastemf.spec.ast.TokenId parse_org_jastemf_spec_ast_TokenId() throws RecognitionException {
        org.jastemf.spec.ast.TokenId element =  null;
        int parse_org_jastemf_spec_ast_TokenId_StartIndex = input.index();
        Token a0=null;
        Token a1=null;
        Token a2=null;
        Token a3=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return element; }
            // Ast.g:1621:1: ( (a0= IDENT ) ( (a1= ':' ( (a2= IDENT ) | (a3= QIDENT ) ) ) )? )
            // Ast.g:1622:2: (a0= IDENT ) ( (a1= ':' ( (a2= IDENT ) | (a3= QIDENT ) ) ) )?
            {
            // Ast.g:1622:2: (a0= IDENT )
            // Ast.g:1623:3: a0= IDENT
            {
            a0=(Token)match(input,IDENT,FOLLOW_IDENT_in_parse_org_jastemf_spec_ast_TokenId1213); if (state.failed) return element;
            if ( state.backtracking==0 ) {

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

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_27, 43));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 43));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 43));
              	
            }
            // Ast.g:1658:2: ( (a1= ':' ( (a2= IDENT ) | (a3= QIDENT ) ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==10) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Ast.g:1659:3: (a1= ':' ( (a2= IDENT ) | (a3= QIDENT ) ) )
                    {
                    // Ast.g:1659:3: (a1= ':' ( (a2= IDENT ) | (a3= QIDENT ) ) )
                    // Ast.g:1660:4: a1= ':' ( (a2= IDENT ) | (a3= QIDENT ) )
                    {
                    a1=(Token)match(input,10,FOLLOW_10_in_parse_org_jastemf_spec_ast_TokenId1243); if (state.failed) return element;
                    if ( state.backtracking==0 ) {

                      				if (element == null) {
                      					element = org.jastemf.spec.ast.AstFactory.eINSTANCE.createTokenId();
                      					incompleteObjects.push(element);
                      				}
                      				collectHiddenTokens(element);
                      				retrieveLayoutInformation(element, org.jastemf.spec.ast.resource.ast.grammar.AstGrammarInformationProvider.AST_13_0_0_1_0_0_0, null);
                      				copyLocalizationInfos((org.antlr.runtime3_2_0.CommonToken)a1, element);
                      			
                    }
                    if ( state.backtracking==0 ) {

                      				// expected elements (follow set)
                      				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_28, 44));
                      				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_29, 44));
                      			
                    }
                    // Ast.g:1675:4: ( (a2= IDENT ) | (a3= QIDENT ) )
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==IDENT) ) {
                        alt11=1;
                    }
                    else if ( (LA11_0==QIDENT) ) {
                        alt11=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return element;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;
                    }
                    switch (alt11) {
                        case 1 :
                            // Ast.g:1676:5: (a2= IDENT )
                            {
                            // Ast.g:1676:5: (a2= IDENT )
                            // Ast.g:1677:6: a2= IDENT
                            {
                            a2=(Token)match(input,IDENT,FOLLOW_IDENT_in_parse_org_jastemf_spec_ast_TokenId1276); if (state.failed) return element;
                            if ( state.backtracking==0 ) {

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

                            }

                            if ( state.backtracking==0 ) {

                              					// expected elements (follow set)
                              					addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 45));
                              					addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 45));
                              				
                            }

                            }
                            break;
                        case 2 :
                            // Ast.g:1712:10: (a3= QIDENT )
                            {
                            // Ast.g:1712:10: (a3= QIDENT )
                            // Ast.g:1713:6: a3= QIDENT
                            {
                            a3=(Token)match(input,QIDENT,FOLLOW_QIDENT_in_parse_org_jastemf_spec_ast_TokenId1332); if (state.failed) return element;
                            if ( state.backtracking==0 ) {

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

                            }

                            if ( state.backtracking==0 ) {

                              					// expected elements (follow set)
                              					addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 46));
                              					addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 46));
                              				
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                      				// expected elements (follow set)
                      				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 47));
                      				addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 47));
                      			
                    }

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_17, 48));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_19, 48));
              	
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 15, parse_org_jastemf_spec_ast_TokenId_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_TokenId"


    // $ANTLR start "parse_org_jastemf_spec_ast_IdUse"
    // Ast.g:1764:1: parse_org_jastemf_spec_ast_IdUse returns [org.jastemf.spec.ast.IdUse element = null] : (a0= IDENT ) ;
    public final org.jastemf.spec.ast.IdUse parse_org_jastemf_spec_ast_IdUse() throws RecognitionException {
        org.jastemf.spec.ast.IdUse element =  null;
        int parse_org_jastemf_spec_ast_IdUse_StartIndex = input.index();
        Token a0=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return element; }
            // Ast.g:1767:1: ( (a0= IDENT ) )
            // Ast.g:1768:2: (a0= IDENT )
            {
            // Ast.g:1768:2: (a0= IDENT )
            // Ast.g:1769:3: a0= IDENT
            {
            a0=(Token)match(input,IDENT,FOLLOW_IDENT_in_parse_org_jastemf_spec_ast_IdUse1416); if (state.failed) return element;
            if ( state.backtracking==0 ) {

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

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_3, 49));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 49));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_13, 49));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_14, 49));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_21, 49));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_23, 49));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_25, 49));
              	
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 16, parse_org_jastemf_spec_ast_IdUse_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_IdUse"


    // $ANTLR start "parse_org_jastemf_spec_ast_IdDecl"
    // Ast.g:1810:1: parse_org_jastemf_spec_ast_IdDecl returns [org.jastemf.spec.ast.IdDecl element = null] : (a0= IDENT ) ;
    public final org.jastemf.spec.ast.IdDecl parse_org_jastemf_spec_ast_IdDecl() throws RecognitionException {
        org.jastemf.spec.ast.IdDecl element =  null;
        int parse_org_jastemf_spec_ast_IdDecl_StartIndex = input.index();
        Token a0=null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return element; }
            // Ast.g:1813:1: ( (a0= IDENT ) )
            // Ast.g:1814:2: (a0= IDENT )
            {
            // Ast.g:1814:2: (a0= IDENT )
            // Ast.g:1815:3: a0= IDENT
            {
            a0=(Token)match(input,IDENT,FOLLOW_IDENT_in_parse_org_jastemf_spec_ast_IdDecl1456); if (state.failed) return element;
            if ( state.backtracking==0 ) {

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

            }

            if ( state.backtracking==0 ) {

              		// expected elements (follow set)
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_2, 50));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_3, 50));
              		addExpectedElement(new org.jastemf.spec.ast.resource.ast.mopp.AstExpectedTerminal(org.jastemf.spec.ast.resource.ast.grammar.AstFollowSetProvider.TERMINAL_4, 50));
              	
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 17, parse_org_jastemf_spec_ast_IdDecl_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_IdDecl"


    // $ANTLR start "parse_org_jastemf_spec_ast_TypeDecl"
    // Ast.g:1852:1: parse_org_jastemf_spec_ast_TypeDecl returns [org.jastemf.spec.ast.TypeDecl element = null] : c0= parse_org_jastemf_spec_ast_ASTDecl ;
    public final org.jastemf.spec.ast.TypeDecl parse_org_jastemf_spec_ast_TypeDecl() throws RecognitionException {
        org.jastemf.spec.ast.TypeDecl element =  null;
        int parse_org_jastemf_spec_ast_TypeDecl_StartIndex = input.index();
        org.jastemf.spec.ast.ASTDecl c0 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return element; }
            // Ast.g:1853:1: (c0= parse_org_jastemf_spec_ast_ASTDecl )
            // Ast.g:1854:2: c0= parse_org_jastemf_spec_ast_ASTDecl
            {
            pushFollow(FOLLOW_parse_org_jastemf_spec_ast_ASTDecl_in_parse_org_jastemf_spec_ast_TypeDecl1488);
            c0=parse_org_jastemf_spec_ast_ASTDecl();

            state._fsp--;
            if (state.failed) return element;
            if ( state.backtracking==0 ) {
               element = c0; /* this is a subclass or primitive expression choice */ 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 18, parse_org_jastemf_spec_ast_TypeDecl_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_TypeDecl"


    // $ANTLR start "parse_org_jastemf_spec_ast_Components"
    // Ast.g:1858:1: parse_org_jastemf_spec_ast_Components returns [org.jastemf.spec.ast.Components element = null] : (c0= parse_org_jastemf_spec_ast_ListComponents | c1= parse_org_jastemf_spec_ast_ListComponentsNTA | c2= parse_org_jastemf_spec_ast_TokenComponent | c3= parse_org_jastemf_spec_ast_TokenComponentNTA | c4= parse_org_jastemf_spec_ast_OptionalComponent | c5= parse_org_jastemf_spec_ast_OptionalComponentNTA | c6= parse_org_jastemf_spec_ast_AggregateComponents | c7= parse_org_jastemf_spec_ast_AggregateComponentsNTA );
    public final org.jastemf.spec.ast.Components parse_org_jastemf_spec_ast_Components() throws RecognitionException {
        org.jastemf.spec.ast.Components element =  null;
        int parse_org_jastemf_spec_ast_Components_StartIndex = input.index();
        org.jastemf.spec.ast.ListComponents c0 = null;

        org.jastemf.spec.ast.ListComponentsNTA c1 = null;

        org.jastemf.spec.ast.TokenComponent c2 = null;

        org.jastemf.spec.ast.TokenComponentNTA c3 = null;

        org.jastemf.spec.ast.OptionalComponent c4 = null;

        org.jastemf.spec.ast.OptionalComponentNTA c5 = null;

        org.jastemf.spec.ast.AggregateComponents c6 = null;

        org.jastemf.spec.ast.AggregateComponentsNTA c7 = null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return element; }
            // Ast.g:1859:1: (c0= parse_org_jastemf_spec_ast_ListComponents | c1= parse_org_jastemf_spec_ast_ListComponentsNTA | c2= parse_org_jastemf_spec_ast_TokenComponent | c3= parse_org_jastemf_spec_ast_TokenComponentNTA | c4= parse_org_jastemf_spec_ast_OptionalComponent | c5= parse_org_jastemf_spec_ast_OptionalComponentNTA | c6= parse_org_jastemf_spec_ast_AggregateComponents | c7= parse_org_jastemf_spec_ast_AggregateComponentsNTA )
            int alt13=8;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // Ast.g:1860:2: c0= parse_org_jastemf_spec_ast_ListComponents
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_ListComponents_in_parse_org_jastemf_spec_ast_Components1509);
                    c0=parse_org_jastemf_spec_ast_ListComponents();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c0; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;
                case 2 :
                    // Ast.g:1861:4: c1= parse_org_jastemf_spec_ast_ListComponentsNTA
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_ListComponentsNTA_in_parse_org_jastemf_spec_ast_Components1519);
                    c1=parse_org_jastemf_spec_ast_ListComponentsNTA();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c1; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;
                case 3 :
                    // Ast.g:1862:4: c2= parse_org_jastemf_spec_ast_TokenComponent
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_TokenComponent_in_parse_org_jastemf_spec_ast_Components1529);
                    c2=parse_org_jastemf_spec_ast_TokenComponent();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c2; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;
                case 4 :
                    // Ast.g:1863:4: c3= parse_org_jastemf_spec_ast_TokenComponentNTA
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_TokenComponentNTA_in_parse_org_jastemf_spec_ast_Components1539);
                    c3=parse_org_jastemf_spec_ast_TokenComponentNTA();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c3; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;
                case 5 :
                    // Ast.g:1864:4: c4= parse_org_jastemf_spec_ast_OptionalComponent
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_OptionalComponent_in_parse_org_jastemf_spec_ast_Components1549);
                    c4=parse_org_jastemf_spec_ast_OptionalComponent();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c4; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;
                case 6 :
                    // Ast.g:1865:4: c5= parse_org_jastemf_spec_ast_OptionalComponentNTA
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_OptionalComponentNTA_in_parse_org_jastemf_spec_ast_Components1559);
                    c5=parse_org_jastemf_spec_ast_OptionalComponentNTA();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c5; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;
                case 7 :
                    // Ast.g:1866:4: c6= parse_org_jastemf_spec_ast_AggregateComponents
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_AggregateComponents_in_parse_org_jastemf_spec_ast_Components1569);
                    c6=parse_org_jastemf_spec_ast_AggregateComponents();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c6; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;
                case 8 :
                    // Ast.g:1867:4: c7= parse_org_jastemf_spec_ast_AggregateComponentsNTA
                    {
                    pushFollow(FOLLOW_parse_org_jastemf_spec_ast_AggregateComponentsNTA_in_parse_org_jastemf_spec_ast_Components1579);
                    c7=parse_org_jastemf_spec_ast_AggregateComponentsNTA();

                    state._fsp--;
                    if (state.failed) return element;
                    if ( state.backtracking==0 ) {
                       element = c7; /* this is a subclass or primitive expression choice */ 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 19, parse_org_jastemf_spec_ast_Components_StartIndex); }
        }
        return element;
    }
    // $ANTLR end "parse_org_jastemf_spec_ast_Components"

    // $ANTLR start synpred13_Ast
    public final void synpred13_Ast_fragment() throws RecognitionException {   
        org.jastemf.spec.ast.ListComponents c0 = null;


        // Ast.g:1860:2: (c0= parse_org_jastemf_spec_ast_ListComponents )
        // Ast.g:1860:2: c0= parse_org_jastemf_spec_ast_ListComponents
        {
        pushFollow(FOLLOW_parse_org_jastemf_spec_ast_ListComponents_in_synpred13_Ast1509);
        c0=parse_org_jastemf_spec_ast_ListComponents();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_Ast

    // $ANTLR start synpred14_Ast
    public final void synpred14_Ast_fragment() throws RecognitionException {   
        org.jastemf.spec.ast.ListComponentsNTA c1 = null;


        // Ast.g:1861:4: (c1= parse_org_jastemf_spec_ast_ListComponentsNTA )
        // Ast.g:1861:4: c1= parse_org_jastemf_spec_ast_ListComponentsNTA
        {
        pushFollow(FOLLOW_parse_org_jastemf_spec_ast_ListComponentsNTA_in_synpred14_Ast1519);
        c1=parse_org_jastemf_spec_ast_ListComponentsNTA();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred14_Ast

    // $ANTLR start synpred15_Ast
    public final void synpred15_Ast_fragment() throws RecognitionException {   
        org.jastemf.spec.ast.TokenComponent c2 = null;


        // Ast.g:1862:4: (c2= parse_org_jastemf_spec_ast_TokenComponent )
        // Ast.g:1862:4: c2= parse_org_jastemf_spec_ast_TokenComponent
        {
        pushFollow(FOLLOW_parse_org_jastemf_spec_ast_TokenComponent_in_synpred15_Ast1529);
        c2=parse_org_jastemf_spec_ast_TokenComponent();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred15_Ast

    // $ANTLR start synpred16_Ast
    public final void synpred16_Ast_fragment() throws RecognitionException {   
        org.jastemf.spec.ast.TokenComponentNTA c3 = null;


        // Ast.g:1863:4: (c3= parse_org_jastemf_spec_ast_TokenComponentNTA )
        // Ast.g:1863:4: c3= parse_org_jastemf_spec_ast_TokenComponentNTA
        {
        pushFollow(FOLLOW_parse_org_jastemf_spec_ast_TokenComponentNTA_in_synpred16_Ast1539);
        c3=parse_org_jastemf_spec_ast_TokenComponentNTA();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred16_Ast

    // $ANTLR start synpred17_Ast
    public final void synpred17_Ast_fragment() throws RecognitionException {   
        org.jastemf.spec.ast.OptionalComponent c4 = null;


        // Ast.g:1864:4: (c4= parse_org_jastemf_spec_ast_OptionalComponent )
        // Ast.g:1864:4: c4= parse_org_jastemf_spec_ast_OptionalComponent
        {
        pushFollow(FOLLOW_parse_org_jastemf_spec_ast_OptionalComponent_in_synpred17_Ast1549);
        c4=parse_org_jastemf_spec_ast_OptionalComponent();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred17_Ast

    // $ANTLR start synpred18_Ast
    public final void synpred18_Ast_fragment() throws RecognitionException {   
        org.jastemf.spec.ast.OptionalComponentNTA c5 = null;


        // Ast.g:1865:4: (c5= parse_org_jastemf_spec_ast_OptionalComponentNTA )
        // Ast.g:1865:4: c5= parse_org_jastemf_spec_ast_OptionalComponentNTA
        {
        pushFollow(FOLLOW_parse_org_jastemf_spec_ast_OptionalComponentNTA_in_synpred18_Ast1559);
        c5=parse_org_jastemf_spec_ast_OptionalComponentNTA();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred18_Ast

    // $ANTLR start synpred19_Ast
    public final void synpred19_Ast_fragment() throws RecognitionException {   
        org.jastemf.spec.ast.AggregateComponents c6 = null;


        // Ast.g:1866:4: (c6= parse_org_jastemf_spec_ast_AggregateComponents )
        // Ast.g:1866:4: c6= parse_org_jastemf_spec_ast_AggregateComponents
        {
        pushFollow(FOLLOW_parse_org_jastemf_spec_ast_AggregateComponents_in_synpred19_Ast1569);
        c6=parse_org_jastemf_spec_ast_AggregateComponents();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred19_Ast

    // Delegated rules

    public final boolean synpred17_Ast() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_Ast_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_Ast() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_Ast_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_Ast() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_Ast_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_Ast() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_Ast_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred18_Ast() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_Ast_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred19_Ast() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred19_Ast_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_Ast() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_Ast_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\40\uffff";
    static final String DFA13_eofS =
        "\1\uffff\1\6\11\uffff\1\6\24\uffff";
    static final String DFA13_minS =
        "\3\4\2\uffff\1\4\2\uffff\1\4\1\12\2\4\1\12\1\4\1\16\1\0\1\12\1"+
        "\4\1\16\1\15\1\0\1\uffff\1\16\1\4\2\20\1\0\1\uffff\1\0\1\22\2\uffff";
    static final String DFA13_maxS =
        "\3\21\2\uffff\1\4\2\uffff\1\4\1\16\1\4\1\21\1\20\1\4\1\16\1\0\1"+
        "\22\1\5\2\16\1\0\1\uffff\1\16\1\4\2\20\1\0\1\uffff\1\0\1\22\2\uffff";
    static final String DFA13_acceptS =
        "\3\uffff\1\3\1\5\1\uffff\1\7\1\1\15\uffff\1\10\5\uffff\1\2\2\uffff"+
        "\1\4\1\6";
    static final String DFA13_specialS =
        "\17\uffff\1\1\4\uffff\1\3\5\uffff\1\0\1\uffff\1\2\3\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\1\11\uffff\1\2\1\3\1\uffff\1\4",
            "\1\6\5\uffff\1\5\1\uffff\1\6\1\7\2\6\1\uffff\1\6",
            "\1\11\12\uffff\1\10\1\uffff\1\12",
            "",
            "",
            "\1\13",
            "",
            "",
            "\1\14",
            "\1\15\2\uffff\1\16\1\17",
            "\1\20",
            "\1\6\7\uffff\1\6\1\7\2\6\1\uffff\1\6",
            "\1\21\5\uffff\1\22",
            "\1\23",
            "\1\24",
            "\1\uffff",
            "\1\27\7\uffff\1\26",
            "\1\30\1\31",
            "\1\32",
            "\1\16\1\17",
            "\1\uffff",
            "",
            "\1\34",
            "\1\35",
            "\1\22",
            "\1\22",
            "\1\uffff",
            "",
            "\1\uffff",
            "\1\26",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1858:1: parse_org_jastemf_spec_ast_Components returns [org.jastemf.spec.ast.Components element = null] : (c0= parse_org_jastemf_spec_ast_ListComponents | c1= parse_org_jastemf_spec_ast_ListComponentsNTA | c2= parse_org_jastemf_spec_ast_TokenComponent | c3= parse_org_jastemf_spec_ast_TokenComponentNTA | c4= parse_org_jastemf_spec_ast_OptionalComponent | c5= parse_org_jastemf_spec_ast_OptionalComponentNTA | c6= parse_org_jastemf_spec_ast_AggregateComponents | c7= parse_org_jastemf_spec_ast_AggregateComponentsNTA );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_26 = input.LA(1);

                         
                        int index13_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred15_Ast()) ) {s = 3;}

                        else if ( (synpred16_Ast()) ) {s = 30;}

                         
                        input.seek(index13_26);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA13_15 = input.LA(1);

                         
                        int index13_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred19_Ast()) ) {s = 6;}

                        else if ( (true) ) {s = 21;}

                         
                        input.seek(index13_15);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA13_28 = input.LA(1);

                         
                        int index13_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_Ast()) ) {s = 4;}

                        else if ( (synpred18_Ast()) ) {s = 31;}

                         
                        input.seek(index13_28);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA13_20 = input.LA(1);

                         
                        int index13_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_Ast()) ) {s = 7;}

                        else if ( (synpred14_Ast()) ) {s = 27;}

                         
                        input.seek(index13_20);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_Grammar_in_start82 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_start89 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_TypeDecl_in_parse_org_jastemf_spec_ast_Grammar121 = new BitSet(new long[]{0x0000000000080012L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_Abstract_in_parse_org_jastemf_spec_ast_ASTDecl171 = new BitSet(new long[]{0x0000000000081C10L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_IdDecl_in_parse_org_jastemf_spec_ast_ASTDecl201 = new BitSet(new long[]{0x0000000000001C00L});
    public static final BitSet FOLLOW_10_in_parse_org_jastemf_spec_ast_ASTDecl228 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_IdUse_in_parse_org_jastemf_spec_ast_ASTDecl254 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_11_in_parse_org_jastemf_spec_ast_ASTDecl304 = new BitSet(new long[]{0x000000000002C010L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_Components_in_parse_org_jastemf_spec_ast_ASTDecl337 = new BitSet(new long[]{0x000000000002D010L});
    public static final BitSet FOLLOW_12_in_parse_org_jastemf_spec_ast_ASTDecl390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_ListComponents423 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_parse_org_jastemf_spec_ast_ListComponents441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_ListComponentsNTA_in_parse_org_jastemf_spec_ast_ListComponents460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_parse_org_jastemf_spec_ast_ListComponentsNTA485 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_ListComponentsNTA503 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_parse_org_jastemf_spec_ast_ListComponentsNTA521 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_parse_org_jastemf_spec_ast_ListComponentsNTA535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_parse_org_jastemf_spec_ast_TokenComponent564 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_TokenId_in_parse_org_jastemf_spec_ast_TokenComponent582 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_parse_org_jastemf_spec_ast_TokenComponent600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_TokenComponentNTA_in_parse_org_jastemf_spec_ast_TokenComponent619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_parse_org_jastemf_spec_ast_TokenComponentNTA644 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_parse_org_jastemf_spec_ast_TokenComponentNTA658 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_TokenId_in_parse_org_jastemf_spec_ast_TokenComponentNTA676 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_parse_org_jastemf_spec_ast_TokenComponentNTA694 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_parse_org_jastemf_spec_ast_TokenComponentNTA708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_parse_org_jastemf_spec_ast_OptionalComponent737 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_OptionalComponent755 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_parse_org_jastemf_spec_ast_OptionalComponent773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_OptionalComponentNTA_in_parse_org_jastemf_spec_ast_OptionalComponent792 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_parse_org_jastemf_spec_ast_OptionalComponentNTA817 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_parse_org_jastemf_spec_ast_OptionalComponentNTA831 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_OptionalComponentNTA849 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_parse_org_jastemf_spec_ast_OptionalComponentNTA867 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_parse_org_jastemf_spec_ast_OptionalComponentNTA881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_AggregateComponents914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_AggregateComponentsNTA_in_parse_org_jastemf_spec_ast_AggregateComponents937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_parse_org_jastemf_spec_ast_AggregateComponentsNTA962 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_Id_in_parse_org_jastemf_spec_ast_AggregateComponentsNTA980 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_parse_org_jastemf_spec_ast_AggregateComponentsNTA998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_parse_org_jastemf_spec_ast_Abstract1027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_NameNode_in_parse_org_jastemf_spec_ast_Id1071 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_10_in_parse_org_jastemf_spec_ast_Id1099 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_IdUse_in_parse_org_jastemf_spec_ast_Id1136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_parse_org_jastemf_spec_ast_NameNode1173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_parse_org_jastemf_spec_ast_TokenId1213 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_10_in_parse_org_jastemf_spec_ast_TokenId1243 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_IDENT_in_parse_org_jastemf_spec_ast_TokenId1276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QIDENT_in_parse_org_jastemf_spec_ast_TokenId1332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_parse_org_jastemf_spec_ast_IdUse1416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_parse_org_jastemf_spec_ast_IdDecl1456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_ASTDecl_in_parse_org_jastemf_spec_ast_TypeDecl1488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_ListComponents_in_parse_org_jastemf_spec_ast_Components1509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_ListComponentsNTA_in_parse_org_jastemf_spec_ast_Components1519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_TokenComponent_in_parse_org_jastemf_spec_ast_Components1529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_TokenComponentNTA_in_parse_org_jastemf_spec_ast_Components1539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_OptionalComponent_in_parse_org_jastemf_spec_ast_Components1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_OptionalComponentNTA_in_parse_org_jastemf_spec_ast_Components1559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_AggregateComponents_in_parse_org_jastemf_spec_ast_Components1569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_AggregateComponentsNTA_in_parse_org_jastemf_spec_ast_Components1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_ListComponents_in_synpred13_Ast1509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_ListComponentsNTA_in_synpred14_Ast1519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_TokenComponent_in_synpred15_Ast1529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_TokenComponentNTA_in_synpred16_Ast1539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_OptionalComponent_in_synpred17_Ast1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_OptionalComponentNTA_in_synpred18_Ast1559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parse_org_jastemf_spec_ast_AggregateComponents_in_synpred19_Ast1569 = new BitSet(new long[]{0x0000000000000002L});

}