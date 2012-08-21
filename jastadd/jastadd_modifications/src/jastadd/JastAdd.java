package jastadd;

import ast.AST.*;

import jrag.AST.*;

import java.util.*;
import java.io.*;

import jrag.*;

public class JastAdd {

	public static final String VERSION = "JastAdd II (http://jastadd.cs.lth.se) version R20101210";
	public static final String VERSIONINFO = "\n// Generated with " + VERSION
			+ "\n\n";

	protected java.util.List files;
	protected java.util.List cacheFiles;

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
			files = new ArrayList();
			cacheFiles = new ArrayList();
			if (readArgs(args))
				System.exit(1);
			;

			long time = System.currentTimeMillis();

			root = new Grammar();
			root.abstractAncestors();

			// Parse ast-grammar
			Collection errors = new ArrayList();
			for (Iterator iter = files.iterator(); iter.hasNext();) {
				String fileName = (String) iter.next();
				if (fileName.endsWith(".ast")) {
					try {
						Ast parser = new Ast(new FileInputStream(fileName));
						parser.fileName = new File(fileName).getName();
						Grammar g = parser.Grammar();
						for (int i = 0; i < g.getNumTypeDecl(); i++) {
							root.addTypeDecl(g.getTypeDecl(i));
						}
						for (Iterator errorIter = parser.getErrors(); errorIter
								.hasNext();) {
							String[] s = ((String) errorIter.next()).split(";");
							errors.add("Syntax error in " + fileName
									+ " at line " + s[0] + ", column " + s[1]);
						}

					} catch (ast.AST.TokenMgrError e) {
						System.out.println("Lexical error in " + fileName
								+ ": " + e.getMessage());
						System.exit(1);
					} catch (ast.AST.ParseException e) {
						// Exceptions actually caught by error recovery in
						// parser
					} catch (FileNotFoundException e) {
						System.out
								.println("File error: Abstract syntax grammar file "
										+ fileName + " not found");
						System.exit(1);
					}
				}
			}

			if (!errors.isEmpty()) {
				for (Iterator iter = errors.iterator(); iter.hasNext();)
					System.out.println(iter.next());
				System.exit(1);
			}

			long astParseTime = System.currentTimeMillis() - time;

			String astErrors = root.astErrors();

			long astErrorTime = System.currentTimeMillis() - time
					- astParseTime;

			if (!astErrors.equals("")) {
				System.out.println("Semantic error:");
				System.out.println(astErrors);
				System.exit(1);
			}

			ASTNode.resetGlobalErrors();

			{
				java.io.StringWriter writer = new java.io.StringWriter();
				root.jjtGenASTNode$State(new PrintWriter(writer), grammar,
						ASTNode.rewriteEnabled);

				jrag.AST.JragParser jp = new jrag.AST.JragParser(
						new java.io.StringReader(writer.toString()));
				jp.root = root;
				jp.setFileName("ASTNode");
				jp.className = "ASTNode";
				jp.pushTopLevelOrAspect(true);
				try {
					while (true)
						jp.AspectBodyDeclaration();
				} catch (Exception e) {
					String s = e.getMessage();
				}
				jp.popTopLevelOrAspect();
			}

			// Parse all jrag files and build tables
			for (Iterator iter = files.iterator(); iter.hasNext();) {
				String fileName = (String) iter.next();
				if (fileName.endsWith(".jrag") || fileName.endsWith(".jadd")) {
					try {
						FileInputStream inputStream = new FileInputStream(
								fileName);
						JragParser jp = new JragParser(inputStream);
						jp.inputStream = inputStream; // Hack to make input
														// stream visible for
														// ast-parser
						jp.root = root;
						jp.setFileName(new File(fileName).getName());
						ASTCompilationUnit au = jp.CompilationUnit();
						root.addCompUnit(au);
					} catch (jrag.AST.ParseException e) {
						StringBuffer msg = new StringBuffer();
						msg.append("Syntax error in " + fileName + " at line "
								+ e.currentToken.next.beginLine + ", column "
								+ e.currentToken.next.beginColumn);
						System.out.println(msg.toString());
						System.exit(1);
					} catch (FileNotFoundException e) {
						System.out.println("File error: Aspect file "
								+ fileName + " not found");
						System.exit(1);
					}
				}
			}

			long jragParseTime = System.currentTimeMillis() - time
					- astErrorTime;

			for (int i = 0; i < root.getNumTypeDecl(); i++) {
				if (root.getTypeDecl(i) instanceof ASTDecl) {
					ASTDecl decl = (ASTDecl) root.getTypeDecl(i);
					java.io.StringWriter writer = new java.io.StringWriter();
					decl.jjtGen(new PrintWriter(writer), grammar,
							ASTNode.rewriteEnabled);

					jrag.AST.JragParser jp = new jrag.AST.JragParser(
							new java.io.StringReader(writer.toString()));
					jp.root = root;
					jp.setFileName(decl.getFileName());
					jp.className = "ASTNode";
					jp.pushTopLevelOrAspect(true);
					try {
						while (true)
							jp.AspectBodyDeclaration();
					} catch (Exception e) {
						String s = e.getMessage();
					}
					jp.popTopLevelOrAspect();

					int j = 0;
					for (Iterator iter = decl.getComponents(); iter.hasNext();) {
						Components c = (Components) iter.next();

						// TODO Modified by skarol, avoiding generation of
						// getters and
						// setters which already exist in superclasses

						/*
						 * if(c instanceof TokenComponent) { c.jaddGen(null, j,
						 * publicModifier, decl); } else { c.jaddGen(null, j,
						 * publicModifier, decl); j++; }
						 */

						if (c instanceof TokenComponent) {
							if (c.hostClass().equals(decl))
								c.jaddGen(null, j, publicModifier, decl);
						} else {
							if (c.hostClass().equals(decl))
								c.jaddGen(null, j, publicModifier, decl);
							j++;
						}
					}

				}
			}

			root.processRefinements();
			root.weaveInterfaceIntroductions();

			for (Iterator iter = cacheFiles.iterator(); iter.hasNext();) {
				String fileName = (String) iter.next();
				System.out.println("Processing cache file: " + fileName);
				try {
					FileInputStream inputStream = new FileInputStream(fileName);
					JragParser jp = new JragParser(inputStream);
					jp.inputStream = inputStream; // Hack to make input stream
													// visible for ast-parser
					jp.root = root;
					jp.setFileName(new File(fileName).getName());
					jp.CacheDeclarations();
				} catch (jrag.AST.ParseException e) {
					StringBuffer msg = new StringBuffer();
					msg.append("Syntax error in " + fileName + " at line "
							+ e.currentToken.next.beginLine + ", column "
							+ e.currentToken.next.beginColumn);
					System.out.println(msg.toString());
					System.exit(1);
				} catch (FileNotFoundException e) {
					System.out.println("File error: Aspect file " + fileName
							+ " not found");
					System.exit(1);
				}
			}

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
					ASTNode.license = readFile(fileName);
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

	private String readFile(String name) throws java.io.IOException {
		StringBuffer buf = new StringBuffer();
		java.io.Reader reader = new java.io.BufferedReader(
				new java.io.FileReader(name));
		char[] cbuf = new char[1024];
		int i = 0;
		while ((i = reader.read(cbuf)) != -1)
			buf.append(String.valueOf(cbuf, 0, i));
		reader.close();
		return buf.toString();
	}

	private void checkMem() {
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
	public void printHelp() {
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
		System.out
				.println("  --jjtree (use jjtree base node, this requires --grammar to be set)");
		System.out
				.println("  --grammar=GGG (the parser for the grammar is called GGG, required when using jjtree)");
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
