package jastadd;

import ast.AST.*;
import jrag.AST.*;
import java.util.*;
import java.io.*;

import javax.swing.DebugGraphics;

import org.jastemf.template.CodeGenException;
import org.jastemf.template.RAGTemplate;
import ast.Util;

public class JastAdd {

	public static final String VERSION = "JastAdd II (http://jastadd.cs.lth.se) version R20101210";
	public static final String VERSIONINFO = "\n// Generated with " + VERSION
			+ "\n\n";

	protected java.util.List<String> files;
	protected java.util.List<String> cacheFiles;

	protected Grammar root; // root of the ast for the ast-grammar file
	protected String pack;
	protected File outputDir;
	protected String grammar;
	protected boolean publicModifier;

	public static void main(String[] args) {
		new JastAdd().compile(args);
		Runtime.getRuntime().gc();
	}

	public void compile(String[] args) {
		try {
			files = new ArrayList<String>();
			cacheFiles = new ArrayList<String>();
			if (readArgs(args))
				System.exit(1);
			;

			long time = System.currentTimeMillis();

			root = new Grammar();
				//parse and analyse ast-grammar
			Collection<String> errors = addInternalASTSpecs(root);
			errors.addAll(Util.parseASTFiles(files,root));
			
			checkProblemsAndExit(errors);

			long astErrorTime = System.currentTimeMillis() - time;

			ASTNode.resetGlobalErrors();

			// Parse all jrag files and build tables
			errors.addAll(Util.parseAGFiles(files,root));
			
			errors = addInternalStateSpec(root);
			
			checkProblemsAndExit(errors);
			
			errors = addInternalSpecs(root);
			
			checkProblemsAndExit(errors);

			
			long jragParseTime = System.currentTimeMillis() - time
					- astErrorTime;
			
			addContainmentAPI(root,grammar,publicModifier);
			
			root.processRefinements();
			root.weaveInterfaceIntroductions();
			
			errors = parseCacheFiles(cacheFiles, root);
			
			checkProblemsAndExit(errors);
			
			root.weaveCollectionAttributes();

			String err = root.errors();
			if (!err.equals("") || !ASTNode.globalErrors.equals("")) {
				System.out.println("Semantic errors: \n" + err
						+ ASTNode.globalErrors);
				System.exit(1);
			}

			long jragErrorTime = System.currentTimeMillis() - time
					- jragParseTime;

			root.jastAddGen(outputDir, grammar, pack, publicModifier);
			try {
				root.createInterfaces(outputDir, pack);
			} catch (FileNotFoundException e) {
				System.out.println("File error: Output directory " + outputDir
						+ " does not exist or is write protected");
				System.exit(1);
			}
			long codegenTime = System.currentTimeMillis() - time
					- jragErrorTime;

			// System.out.println("AST parse time: " + astParseTime +
			// ", AST error check: " + astErrorTime + ", JRAG parse time: " +
			// jragParseTime + ", JRAG error time: " + jragErrorTime +
			// ", Code generation: " + codegenTime);
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}


	/*private static Collection<String> addConstructors(Grammar grammar,ASTDecl decl) throws IOException{
		Collection<String> problems = new LinkedList<String>();
		if(decl.getComponents().hasNext()){
			String specURI = "/ast/codebase/contaiment/Constructor.internal.jrag";
			RAGTemplate tpl =  new RAGTemplate(ast.Util.readStream(ast.Util.class.getResourceAsStream(specURI)));
			tpl.bind("ID",decl.name());
			tpl.bind("ID_IMPL", decl.implName());		
			tpl.bind("FILENAME",decl.getFileName());
			tpl.bind("LINE",String.valueOf(decl.getStartLine()));
			int i = 0;
			int j = 0;
			Iterator<Components> it = (Iterator<Components>)decl.getComponents();
			while(it.hasNext()){
				//parameterless default constructor
				Components component = it.next();
				if(component instanceof ListComponents){
					tpl.instanciatePrototype("SETCHILD", "ARGTYPE", ASTNode.listName,"ARRAY_IDX",String.valueOf(j)); 		
				}
				else if(component instanceof OptionalComponent){
					tpl.instanciatePrototype("SETCHILD", "ARGTYPE", ASTNode.optName,"ARRAY_IDX",String.valueOf(j)); 		
				}
				
				String separator = "";
				//constructor with parameters
				tpl.instanciatePrototype("SETVARIANTS","ARRAY_IDX",String.valueOf(j));
				if(!component.isNTA()){
					tpl.instanciatePrototype("ARGLIST","ARGTYPE", ASTNode.listName,"IDX",separator+String.valueOf(i));
					separator = ",";
				    if(component instanceof TokenComponent) {
				          TokenComponent token = (TokenComponent)component;
				          tpl.bind("NAME",token.getTokenId().getID());
				          tpl.remove("ARGTYPE");
				          tpl.extractVariant("SETCHILD", "TERMINAL");
				    }
				        else {
				         // s.append("        setChild(p" + String.valueOf(i) + ", " + j + ");\n");
				          j++;
				        }
				}
				i++;
			}
			tpl.removePrototype("SETCHILD");
			tpl.removePrototype("ARGLIST");
			

		}
		return problems;
	}*/
	
	
	private void addContainmentAPI(Grammar grammar,String grammarName,boolean usePublicModifier) throws IOException{
		for (int i = 0; i < grammar.getNumTypeDecl(); i++) {
			if (grammar.getTypeDecl(i) instanceof ASTDecl) {
				ASTDecl decl = (ASTDecl) grammar.getTypeDecl(i);
				addInternalConstructors(decl, grammar);
				
				StringWriter writer = new StringWriter();
				
				decl.jjtGen(new PrintWriter(writer), grammarName,
						ASTNode.rewriteEnabled);

				JragParser jp = new jrag.AST.JragParser(
						new java.io.StringReader(writer.toString()));
				jp.root = grammar;
				jp.setFileName(decl.getFileName());
				jp.className = "ASTNode";
				jp.pushTopLevelOrAspect(true);
				try {
					while (true)
						jp.AspectBodyDeclaration();
				} catch (Exception e) {
					//String s = e.getMessage();
				}
				jp.popTopLevelOrAspect();

				int j = 0;
				for (Iterator iter = decl.getComponents(); iter.hasNext();) {
					Components components = (Components) iter.next();

					if (components instanceof TokenComponent) {
						if (components.hostClass().equals(decl))
							components.jaddGen(null, j, usePublicModifier, decl);
					} else {
						if (components.hostClass().equals(decl))
							components.jaddGen(null, j, usePublicModifier, decl);
						j++;
					}
				}

			}
		}

	}
	
	private static Collection<String> parseCacheFiles(Collection<String> fileNames, Grammar grammar){
		// Parse all jrag files and build tables
		Collection<String> problems = new LinkedList<String>();
		for (Iterator<String> iter = fileNames.iterator(); iter.hasNext();) {
			String fileName = iter.next();
			System.out.println("Processing cache file: " + fileName);
			try {
				FileInputStream inputStream = new FileInputStream(fileName);
				JragParser jp = new JragParser(inputStream);
				jp.inputStream = inputStream; // Hack to make input stream
												// visible for ast-parser
				jp.root = grammar;
				jp.setFileName(new File(fileName).getName());
				jp.CacheDeclarations();
			} catch (jrag.AST.ParseException e) {
				StringBuffer msg = new StringBuffer();
				msg.append("Syntax error in " + fileName + " at line "
						+ e.currentToken.next.beginLine + ", column "
						+ e.currentToken.next.beginColumn);
				problems.add((msg.toString()));
				System.exit(1);
			} catch (FileNotFoundException e) {
				problems.add("File error: Aspect file " + fileName
						+ " not found");
			}
		}

		return problems;
	}
	
	
	private Collection<String> addInternalConstructors(ASTDecl decl, Grammar grammar) throws IOException{
		String specURI = "/ast/codebase/containment/Constructor.internal.jrag";
		if(grammar.debugMode){
			System.out.println("Handling internal spec "+ specURI +".");
		}
		String content = Util.readStream(ast.Util.class.getResourceAsStream(specURI));
		RAGTemplate tpl = new RAGTemplate(content);
		tpl.bind("*.ID", decl.name());
		tpl.bind("*.ID_IMPL", decl.implName());
		tpl.bind("*.FILENAME",decl.getFileName());
		tpl.bind("*.LINE",String.valueOf(decl.getStartLine()));
		boolean hasRegularComponents = decl.numNonNTAComponents()>0;
		
		if(hasRegularComponents){
			tpl.instanciatePrototype("ValueConstructor");
		}
    	tpl.remove("ValueConstructor");
		
		@SuppressWarnings("rawtypes")
		Iterator it = decl.getComponents();
		int index = 0;
		String sep = "";
		
		while(it.hasNext()){
			Components component = (Components) it.next();
			if(hasRegularComponents){
				tpl.instanciatePrototype("SETCHILDVARIANTS");
				tpl.bind("SETCHILD.*.IDX",String.valueOf(index));
				if(!component.isNTA()){
					tpl.instanciatePrototype("ARGLIST", "ARGLIST.ARGTYPE",component.constrParmType(),"ARGLIST.IDX",String.valueOf(index)+sep);
					sep = ",";
				}
			}
			
			if(component instanceof TokenComponent){
				//TODO Check if body hook would be more readable than variants tag
				tpl.extractVariant("SETCHILD", "TERMINAL");
				tpl.bind("ARG",component.name());
			}
			else if(component instanceof AggregateComponents){
				if(!component.isNTA()){
					tpl.extractVariant("SETCHILD", "NONTERMINAL");
				}
				else{
					tpl.extractVariant("SETCHILD", "NTA2");
				}
			}
			else if(component instanceof ListComponents||component instanceof OptionalComponent) {
			    String consName = component instanceof ListComponents?ASTNode.listName:ASTNode.optName;
				tpl.instanciatePrototype("SETCHILD","SETCHILD.CONS",consName,"SETCHILD.IDX",String.valueOf(index));
				if(!component.isNTA()){
					tpl.extractVariant("SETCHILD", "NONTERMINAL");
				}
				else{
					tpl.extractVariant("SETCHILD", "NTA1");
					tpl.bind("CONS",consName);
				}
			}

			index++;
		}
		tpl.removePrototype("SETCHILDVARIANTS");
		tpl.removePrototype("ARGLIST");
		tpl.removePrototype("SETCHILD");
		System.out.println(tpl.getGenCode());
		return addToGrammar(tpl.getGenCode(),specURI,grammar);
	}
	
	private Collection<String> addToGrammar(String code, String specURI,Grammar grammar) {
		Collection<String> problems = new LinkedList<String>();
		InputStream stream = new ByteArrayInputStream(code.getBytes());
		String problem = Util.parseAGSpecFromStream(stream, specURI, grammar);
		if(problem!=null){
			problems.add(problem);		
		}
		if(grammar.debugMode){
			File debugFile = new File(outputDir.getAbsolutePath() + "/debug" + specURI);
			try {
				if(!debugFile.exists()){
					debugFile.getParentFile().mkdirs();
					debugFile.createNewFile();
				}
				FileWriter w = new FileWriter(debugFile);
				w.write(code);
				w.flush();
				w.close();
			} catch (IOException e) {
				problems.add("Unable to write debug output to '" + debugFile.getAbsolutePath() + "'. Reason: "+e.getMessage());
			}
		}		

		return problems;
	}
	
	private Collection<String> addInternalStateSpec(Grammar grammar) throws IOException{
		/*StringWriter writer = new StringWriter();
		JragParser jp = new jrag.AST.JragParser(
				new java.io.StringReader(writer.toString()));
		jp.root = grammar;
		jp.setFileName("ASTNode");
		jp.className = "ASTNode";
		TODO: check if push command needed
		jp.pushTopLevelOrAspect(true);
		try {
			while (true)
				jp.AspectBodyDeclaration();
		} catch (Exception e) {
			//String s = e.getMessage();
		}
		jp.popTopLevelOrAspect(); */
		String specURI = "/ast/codebase/common/StateAPI.internal.jrag";
		String spec =  ast.Util.readStream(ast.Util.class.getResourceAsStream(specURI));
		RAGTemplate tpl = new RAGTemplate(spec);			
		tpl.bind("*.TYPEDEFAULTSET",ASTNode.typeDefaultSet);
		tpl.bind("*.CREATEDEFAULTSET",ASTNode.createDefaultSet);
		tpl.extractVariant("HASHSET", "NONDETERMINISTIC");
		tpl.extractVariant("*.CYCLE", ASTNode.cacheCycle?"ENABLED":"DISABLED");
		tpl.extractVariant("*.COMPONENTCHECK", ASTNode.componentCheck?"ENABLED":"DISABLED");
		tpl.extractVariant("CIRCULAR", ASTNode.circularEnabled?"ENABLED":"DISABLED");

		@SuppressWarnings("rawtypes")
		Iterator it = grammar.rewriteAspects().iterator();
		while(it.hasNext()){
		  String name = (String)it.next();
		  tpl.instanciatePrototype("DURINGRESET", "DURINGRESET.ASPECTNAME", name);
		}
		tpl.removePrototype("DURINGRESET");
		return addToGrammar(tpl.getGenCode(),specURI,grammar);
	  }
	
	
	//added by JastEMF
	private static Collection<String> addInternalASTSpecs(Grammar grammar) throws IOException{
		Collection<String> problems = new LinkedList<String>();
		Collection<String> internalSpecs = new LinkedList<String>();
		internalSpecs.add("/ast/codebase/common/Base.internal.ast");
		for(String specURI:internalSpecs){
			if(grammar.debugMode){
				System.out.println("Handling internal spec "+ specURI +".");
			}
			InputStream stream = JastAdd.class.getResourceAsStream(specURI);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			RAGTemplate tpl = new RAGTemplate(Util.readStream(reader));
			tpl.bind("LISTTYPE", ASTNode.listName);
		    tpl.bind("OPTTYPE", ASTNode.optName);
			stream = new ByteArrayInputStream(tpl.getGenCode().getBytes());
			problems.addAll(Util.parseASTSpecFromStream(stream, specURI, grammar));
		}

		return problems;
	}
	
	//added by JastEMF
	private Collection<String> addInternalSpecs(Grammar grammar) {
		Collection<String> problems = new LinkedList<String>();
		Collection<String> internalSpecs = new LinkedList<String>();
		internalSpecs.add("/ast/codebase/common/ASTNodeAPI.internal.jrag");
		internalSpecs.add("/ast/codebase/common/ResolveAccess.internal.jrag");
		internalSpecs.add("/ast/codebase/common/ListAPI.internal.jrag");
		internalSpecs.add("/ast/codebase/common/OptAPI.internal.jrag");
		if("plain".equals(ASTNode.backend)){
			internalSpecs.add("/ast/codebase/common/ASTNodeAPI.internal.plain.jrag");
		}
		else if("emf".equals(ASTNode.backend)){
			internalSpecs.add("/ast/codebase/common/ASTNodeAPI.internal.emf.jrag");
		}
		for(String specURI:internalSpecs){
			if(grammar.debugMode){
				System.out.println("Handling internal spec "+ specURI +".");
			}
			InputStream stream = JastAdd.class.getResourceAsStream(specURI);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			RAGTemplate tpl;
			try {
				tpl = new RAGTemplate(Util.readStream(reader));
				tpl.bind("LISTTYPE", ASTNode.listName);
				tpl.bind("OPTTYPE", ASTNode.optName);
				tpl.bind("ASTNode_circularEnabled", Boolean.toString(ASTNode.circularEnabled));
				tpl.bind("ASTNode_cacheCycle", Boolean.toString(ASTNode.cacheCycle));
				tpl.bind("ASTNode_componentCheck", Boolean.toString(ASTNode.componentCheck));
				problems.addAll(addToGrammar(tpl.getGenCode(),specURI,grammar));

			} catch (IOException e) {
			  problems.add("Exception during template expansion of '" + specURI + "'. Reason: " + e.getMessage());
			}
		}

		return problems;
	}
	
	private static void checkProblemsAndExit(Collection<String> problems){
		if (!problems.isEmpty()) {
			for (Iterator<String> iter = problems.iterator(); iter.hasNext();)
				System.out.println(iter.next());
			System.exit(1);
		}
	}
	
	/* Read and process commandline */
	public boolean readArgs(String[] args) {
		CommandLineArguments cla = new CommandLineArguments(args);
		grammar = cla.getLongOptionValue("grammar", "Unknown");

		ASTNode.createDefaultMap = cla.getLongOptionValue("defaultMap",
				"new java.util.HashMap(4)");
		ASTNode.createDefaultSet = cla.getLongOptionValue("defaultSet",
				"new java.util.HashSet(4)");

		ASTNode.lazyMaps = cla.hasLongOption("lazyMaps");

		publicModifier = !cla.hasLongOption("private");

		ASTNode.rewriteEnabled = cla.hasLongOption("rewrite");
		ASTNode.beaver = cla.hasLongOption("beaver");
		ASTNode.visitCheckEnabled = !cla.hasLongOption("novisitcheck");
		ASTNode.cacheCycle = !cla.hasLongOption("noCacheCycle");
		ASTNode.componentCheck = !cla.hasLongOption("noComponentCheck");

		ASTNode.noInhEqCheck = cla.hasLongOption("noInhEqCheck");

		ASTNode.suppressWarnings = cla.hasLongOption("suppressWarnings");
		ASTNode.parentInterface = cla.hasLongOption("parentInterface");

		ASTNode.refineLegacy = cla.hasLongOption("refineLegacy");

		ASTNode.stagedRewrites = cla.hasLongOption("stagedRewrites");

		ASTNode.doc = cla.hasLongOption("doc");

		ASTNode.license = "";
		if (cla.hasLongOption("license")) {
			String fileName = cla.getLongOptionValue("license", null);
			try {
				if (fileName != null) {
					ASTNode.license = Util.readFile(fileName);
				}
			} catch (java.io.IOException e) {
				System.err.println("Error loading license file " + fileName);
				System.exit(1);
			}
		}

		if (cla.hasLongOption("debug")) {
			ASTNode.debugMode = true;
			ASTNode.cycleLimit = 100;
			ASTNode.rewriteLimit = 100;
			ASTNode.visitCheckEnabled = true;
		}

		// ASTNode.block = cla.hasLongOption("synch");

		ASTNode.noStatic = cla.hasLongOption("noStatic");

		ASTNode.deterministic = cla.hasLongOption("deterministic");
		if (ASTNode.deterministic) {
			ASTNode.createDefaultMap = "new java.util.LinkedHashMap(4)";
			ASTNode.createDefaultSet = "new java.util.LinkedHashSet(4)";
		}

		String outputDirName = cla.getLongOptionValue("o",
				System.getProperty("user.dir"));
		outputDir = new File(outputDirName);

		if (!outputDir.exists()) {
			System.out.println("Output directory does not exist");
			System.exit(1);
		}
		if (!outputDir.isDirectory()) {
			System.out.println("Output directory is not a directory");
			System.exit(1);
		}
		if (!outputDir.canWrite()) {
			System.out.println("Output directory is write protected");
			System.exit(1);
		}

		ASTNode.tracing = cla.hasLongOption("tracing");
		ASTNode.cacheAll = cla.hasLongOption("cacheAll");
		ASTNode.noCaching = cla.hasLongOption("noCaching");

		pack = cla.getLongOptionValue("package", "").replace('/', '.');
		int n = cla.getNumOperands();
		for (int k = 0; k < n; k++) {
			String fileName = cla.getOperand(k);
			if (fileName.endsWith(".ast") || fileName.endsWith(".jrag")
					|| fileName.endsWith(".jadd")) {
				files.add(fileName);
			} else if (fileName.endsWith(".caching")) {
				cacheFiles.add(fileName);
			} else {
				System.out.println("FileError: " + fileName
						+ " is of unknown file type");
				return true;
			}
		}

		if (cla.hasLongOption("version")) {
			System.out.println(VERSION);
			return true;
		}

		if (cla.hasLongOption("help") || files.isEmpty()) {
			System.out.println(VERSION + "\n");
			printHelp();
			return true;
		}
		return false;
	}
	



	private static void checkMem() {
		Runtime runtime = Runtime.getRuntime();
		long total = runtime.totalMemory();
		long free = runtime.freeMemory();
		long use = total - free;
		System.out.println("Before GC: Total " + total + ", use " + use);
		runtime.gc();
		total = runtime.totalMemory();
		free = runtime.freeMemory();
		use = total - free;
		System.out.println("After GC: Total " + total + ", use " + use);
	}

	/**
	 * Print help
	 */
	public static void printHelp() {
		System.out
				.println("This program reads a number of .jrag, .jadd, and .ast files");
		System.out.println("and creates the nodes in the abstract syntax tree");
		System.out.println();
		System.out
				.println("The .jrag source files may contain declarations of synthesized ");
		System.out
				.println("and inherited attributes and their corresponding equations.");
		System.out
				.println("It may also contain ordinary Java methods and fields.");
		System.out.println();
		System.out
				.println("Source file syntax can be found at http://jastadd.cs.lth.se");
		System.out.println();
		System.out.println("Options:");
		System.out.println("  --help (prints this text and stops)");
		System.out
				.println("  --version (prints version information and stops)");
		System.out
				.println("  --package=PPP (optional package for generated files, default is none)");
		System.out
				.println("  --o=DDD (optional base output directory, default is current directory");
		System.out.println("  --beaver (use beaver base node)");
		System.out.println("  --rewrite (enable ReRAGs support)");
		System.out
				.println("  --novisitcheck (disable circularity check for attributes)");
		System.out
				.println("  --noCacheCycle (disable cache cyle optimization for circular attributes)");
		System.out
				.println("  --license=LICENSE (include the file LICENSE in each generated file)");
		System.out.println();
		System.out.println("Arguments:");
		System.out.println("Names of .ast, .jrag and .jadd source files");
		System.out.println();
		System.out
				.println("Example: The following command reads and translates files NameAnalysis.jrag");
		System.out
				.println("and TypeAnalysis.jrag, weaves PrettyPrint.jadd into the abstract syntax tree");
		System.out.println("defined in the grammar Toy.ast.");
		System.out
				.println("The result is the generated classes for the nodes in the AST that are placed");
		System.out.println("in the package ast.");
		System.out.println();
		System.out
				.println("JastAdd --package=ast Toy.ast NameAnalysis.jrag TypeAnalysis.jrag PrettyPrinter.jadd");
		System.out.println();
		System.out.println("Stopping program");
	}
	

	
}
