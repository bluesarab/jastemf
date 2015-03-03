/*
The copied parts of the source code in this class adhere to the following copyright statements:

Copyright (c) 2005, The JastAdd Team
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice,
      this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
      this list of conditions and the following disclaimer in the documentation
      and/or other materials provided with the distribution.
 * Neither the name of the Lund University nor the names of its contributors
      may be used to endorse or promote products derived from this software
      without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.jastemf.converter.jastadd;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jastadd.Configuration;
import org.jastadd.JastAddUtil;
import org.jastadd.Problem;
import org.jastadd.ast.AST.Grammar;
import org.jastadd.ast.AST.SynDecl;
import org.jastadd.ast.AST.SynEq;
import org.jastadd.ast.AST.TypeDecl;
import org.jastadd.jrag.AST.ASTCompilationUnit;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;

/**
 * This is a more accessible version of the JastAdd standard
 * {jastadd.JastAdd.compile} procedure which is
 * normally executed from commandline. Most parts of the code have been copied
 * from there and adhere to the JastAdd.
 * 
 * @author skarol
 * @author The JastAdd Team
 */

public class GrammarLoader {
	
	public static final Configuration config = new Configuration();
	
	public static void loadAstSpec(URI resource, Grammar root,
			final Collection<Problem> errors) {
		InputStream stream = null;
		String resourceName = resource.toString();
		URIConverter uriConverter = URIConverter.INSTANCE;
		try {
			stream = uriConverter.createInputStream(resource);
			InputStreamReader in = new InputStreamReader(stream);
			JastAddUtil.parseASTSpec(in, resourceName, root,errors);

		} catch (IOException e) {
			errors.add(new Problem.Error("File error: Abstract syntax grammar file "
					+ resourceName + " was not loaded: "+e.getMessage()));
		} finally {
			if (stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					// do nothing
				}
		}
	}

	public static ASTCompilationUnit loadJragSpec(URI resource, Grammar root,
			Collection<Problem> errors) {
		InputStream inputStream = null;
		String resourceName = resource.toString();
		try {
			inputStream = URIConverter.INSTANCE.createInputStream(resource);
			InputStreamReader in = new InputStreamReader(inputStream);
			return JastAddUtil.parseJRAGSpec(in,resourceName,errors,root);
		} catch (IOException e) {
			errors.add(new Problem.Error("File error: Aspect file " + resourceName + " was not loaded: " + e.getMessage()));
			return null;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	public static Grammar loadJastAddGrammar(List<URI> files) {
		Grammar root = null;
		try {
			
			root = config.buildRoot();
	        root.addImplicitNodeTypes();

			// parse AST files 
			Collection<Problem> problems = new LinkedList<Problem>();
			for (Iterator<URI> iter = files.iterator(); iter.hasNext();) {
				URI fileURI = iter.next();
				if (fileURI.toString().endsWith(".ast")) {
					loadAstSpec(fileURI, root, problems);
				}
			}

			if (!problems.isEmpty()) {
				for (Iterator<Problem> iter = problems.iterator(); iter.hasNext();)
					System.out.println(iter.next());
				return null;
			}

			// parse all jrag files and build tables
			for (Iterator<URI> iter = files.iterator(); iter.hasNext();) {
				URI fileURI = iter.next();
				if (fileURI.toString().endsWith(".jrag")
						|| fileURI.toString().endsWith(".jadd")) {
					loadJragSpec(fileURI, root, problems);
				}
			}
			
			moveSynAttributes(root);
			root.processRefinements();
			root.weaveInterfaceIntroductions();
			root.weaveCollectionAttributes();
			
			problems = root.problems();
			if(problems!=null && !problems.isEmpty()){
				System.out.println("Semantic errors:");
				for(Problem p:problems){
					System.out.println(p.toString());
				}	
				return null;				
			}
			
			return root;
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// partially from JastAdd.java
	protected static void moveSynAttributes(Grammar grammar) {
	    for (SynDecl decl: grammar.synDecls) {
	      TypeDecl clazz = grammar.lookup(decl.hostName);
	      if (clazz != null) 
	        clazz.addSynDecl(decl);
	    }
	    grammar.synDecls.clear();
	    for (SynEq equ: grammar.synEqs) {
	      TypeDecl clazz = grammar.lookup(equ.hostName);
	      if (clazz != null) 
	        clazz.addSynEq(equ);
	    }
	    grammar.synEqs.clear();
	  }
}
