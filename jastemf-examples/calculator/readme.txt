							  Calculator Compiler
							      Version 1.0
					    Christoff.Buerger@tu-dresden.de
					    Technische Universität Dresden
				 Institut für Software- und Multimediatechnik
					     Lehrstuhl Softwaretechnologie
===============================================================================

Abstract: This application is a demonstrator for the specification of an ECore
	metamodel's semantic using JastEMF. Based on the metamodel's containment
	references and the spanning tree they define for each model instance
	the attribute grammar tool JastAdd is used to specify an evaluator that
	computes model instances' semantic. Using JastEMF, the generated evaluator
	and EMF model implementation can be combined, such that a model
	implementation with semantic results, i.e. a model implementation whose
	derived attributes, non-containment references and operations are defined
	and implemented.

							    I Installation
-------------------------------------------------------------------------------

Three different Calculator compiler versions can be generated:
	1) A standard compiler implemented based on JFlex, Beaver and JastAdd.
	2) A compiler with an EMF repository implemented based on JFlex, Beaver,
	   JastAdd and JastEMF.
	3) A compiler with EMF repository and an Eclipse editor with syntax
	   highlighting, code completion and semantic property view implemented
	   based on EMFText (including AntLR), JastAdd and JastEMF.

Each one has increasing demands on your Eclipse configuration:
	1) The first version can be generated out-of-the-box. It even doesn't
	   required Eclipse. Only the Ant build tool is required beside the JDK.
	2) The second version requires the EMF modeling tools. It can be generated
	   out-of-the-box with the Eclipse Modeling Tools distribution.
	3) The third version additionally requires the EMFText plugin. You can find
	   it in the "sources/applications/emftext-plugins" folder. Just copy and
	   paste the content into Eclipse's "dropins" folder.

If you like to run the regression tests supported for versions 1 and 2, the
AspectJ weaving tools for Eclipse are required.

					      II Building the Calculator
-------------------------------------------------------------------------------

To generate one of the Calculator versions just execute the appropriate Ant
task in the "specifications/calculator/build.xml" Ant script.

Important: Remember that the versions 2 and 3 have to be generated within the
	same JRE in which Eclipse is executed.

						   III Using the Calculator
-------------------------------------------------------------------------------

Version 1) The "calculator.Calculator.java" class supports a main function you
	can use to run the Calculator from command line. Just specify the program
	to execute (You can find example programs in the
	"test/source_code_examples/correct" folder).

Version 2) You can use the EMF model editor to construct a program model (The
	Calculator's metamodel is specified in the
	"specifications/calculator/calculator.ecore" file). Also, the command line
	interface of version 1 is supported.

Version 3) Open the Eclipse editor project generated for the Calculator
	(The project is named "calculator.semantics.resource.expr". If you can't
	find it in your workspace after generating version 3, Eclipse missed
	to open it. Just import it and the generated AntLR runtime project
	manually. Both are located in your workspace folder.). Open its
	"plugin.xml" and launch it ("Launch an Eclipse application"). Ignore the
	error warning and proceed. Its only because the command line interface's
	implementation is not correct for version 3. Create a project in the new
	Eclipse instance. Within projects you can now create Calculator programs.
	To do so select "new->other->EMFText File->EMFText .expr file". Enjoy the
	editor.
	
	PS: Dont't forget to open the properties view to investigate the program's
		semantic while editing. When you select an expression (e.g. the "||" of
		the expression "false && (true || false)") you can see its type and
		value. You can also use control + space for code completion.

				  IV Using the Calculator's Regression Tests
-------------------------------------------------------------------------------

The Calculator versions 1 and 2 share a regression test suite managed by the
Regression Test Tool (RTT; "http://st.inf.tu-dresden.de/rtt"). All regression
test related task can be executed using the "tests/build.xml" Ant script. The
test suite checks that both Calculator versions compute the same semantic for
the same input.

RTT test archives are a good source for details about compiler internals. To
see what exactly the Calculator computed for an input program, just generate a
test archive using the program as test case and open it (RTT test archives are
ordinary Zip files.). Inside the test archive you find a directory "output"
containing a sub-directory named like the configuration you used for the test
suite's generation. Open the configuration folder and select the appropriate
test suite folder. Within it you find XML files containing the test case's
expected results, i.e. its lexic and semantic analyse's results.
