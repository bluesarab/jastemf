

# Tutorial Introduction #

In this tutorial we will present SiPLE --- a simple imperative programming language example --- and generate different SiPLE implementations, each with additional language tooling:

  * **[JastAdd Version](#JastAdd_Version.md):** A standard compiler implemented based on [JFlex](http://jflex.de/), [Beaver](http://beaver.sourceforge.net/) and [JastAdd](http://www.jastadd.org/).
  * **[EMF Version](#EMF_Version.md):** A compiler with an EMF repository implemented based on JFlex, Beaver, JastAdd and JastEMF.
  * **[xText Version](#xText_Version.md):** A compiler with EMF repository and an Eclipse editor with syntax highlighting, code completion and semantic property view implemented based on [xText](http://www.eclipse.org/Xtext/) (including [ANTLR](http://www.antlr.org/)), JastAdd and JastEMF.

Of course, our main focus is on the EMF integrated versions. The tutorial will not only describe how to generate these using JastEMF, but also how to use the semantic metamodel implementation and what its benefits are. The JastAdd version, which represents an ordinary compiler project without any reference to metamodelling and the EMF, is used for validation issues. Its SiPLE compiler and the EMF integrated one share the same JastAdd specifications for SiPLE's semantics and are compared by a [regression test suite](#Regression_Tests.md), to show, that the integration performed by JastEMF is correct.

For all SiPLE versions the same concrete syntax is supported. Also, all SiPLE versions have the same static semantics, including a nested block structured name analysis supporting nested procedure declarations and lexic scopes (closures) and a strong type analysis. Additionally, all SiPLE versions support the interpretation of programs. The JastAdd and EMF versions support a command line interpreter and the xText version integrates this interpreter into Eclipse so it can be started from `*.siple` files' right-click menu. Last but not least, the JastAdd and EMF versions are regression tested to each other to validate JastEMF's correctness.

The following table summarises and forecloses the features of the different SiPLE implementations investigated in this tutorial:

| **Version** | **Semantics (Static & Execution)** | **Interpreter** | **Tests** | **Metamodel** | **Eclipse Model Editor** | **Eclipse Text Editor** |
|:------------|:-----------------------------------|:----------------|:----------|:--------------|:-------------------------|:------------------------|
| JastAdd     | Yes                                | Yes (Command line) | Yes       | No            | No                       | No                      |
| EMF         | Yes                                | Yes (Command line) | Yes       | Yes           | Yes                      | No                      |
| xText       | Yes                                | Yes (Eclipse Menu Extension) | Supports the EMF version's tests | Yes           | Yes                      | Yes                     |

## A First Look at SiPLE ##

To get an impression of SiPLE, consider the following programm given in concrete syntax:
```
Procedure main() Begin
    Write fibunacci(10);
    Write mr(10);
End;

Procedure fibonacci(Var n:Integer):Integer Begin
    Var result:Integer;
    If (n > 1) Then
        result := fibonacci(n-1) + fibonacci(n-2);
    Else
        result := 1;
    Fi;
    Return result;
End;

Procedure mr(Var n:Integer) Begin
    Procedure mr1(Var lb:Integer, Var ub:Integer) Begin
        If lb >= 0 Then
            Write lb;
            mr2(lb - 1, ub);
        Fi;
    End;
    
    Procedure mr2(Var lb:Integer, Var ub:Integer) Begin
        Write ub;
        mr1(lb, ub + 1);
    End;	
    
    mr1(n, n);
End;
```
It consists of two procedures. The first computes the well-known [Fibonacci numbers](http://en.wikipedia.org/wiki/Fibonacci_number) and the second writes, starting from a natural number, a sequence of its predecessors and successors. In the end the program prints out the Fibonacci number of `10` and the sequence `10, 10, 9, 11, 8, 12, 7, 13, 6, 14, 5, 15, 4, 16, 3, 17, 2, 18, 1, 19, 0, 20`.

# Deployment and Configuration #

## Installation ##

The SiPLE tutorial can be checked out from the project's SVN repository. It is named _"org.jastemf.siple"_ and a subfolder of _"jastemf-examples/siple"_. It is recommended to check it out within Eclipse, e.g. using [Subclipse](http://subclipse.tigris.org/). The xText SiPLE version additionally requires the _"org.jastemf.siple.editor"_ project (same SVN), into which the xText parser is generated. To get a stable version of SiPLE it should be checked out from http://jastemf.googlecode.com/svn/tags/beta_0.1.6/jastemf-examples.

The xText SiPLE version additionally requires the [xText](http://www.xtext.org) plugin to be deployed. The xText update site is _"http://download.eclipse.org/modeling/tmf/updates/releases/"_.

Further, [RTT](http://code.google.com/p/rtt/) is used to regression test the EMF SiPLE version against the JastAdd version. RTT's `jar` distribution is already contained in and referenced by the project. However, the integration of RTT is done using [AspectJ](http://www.eclipse.org/aspectj/). Thus, the [AspectJ Development Tools](http://www.eclipse.org/ajdt/) for Eclipse are required. Just install them via their update site _"http://download.eclipse.org/tools/ajdt/35/update"_.

Last but not least, JastEMF is required to integrate SiPLE's Ecore metamodel and its JastAdd semantics (for an installation tutorial see [here](Installation.md)).

The following table summarises the tools used by the SiPLE project, their purpose, whether they must be manually installed or configurated and if they are already contained in the project's repository:

| **Tool** | **Purpose** | **Must be Installed** | **Shiped with SiPLE** |
|:---------|:------------|:----------------------|:----------------------|
| JastEMF  | To integrate JastAdd semantics into EMF metamodel implementations | [Yes](Installation.md) | No                    |
| [xText](http://www.xtext.org) | Required to generate the xText SiPLE editor | Yes                   | No                    |
| [AspectJ](http://www.eclipse.org/ajdt/) | To weave regression test support into the JastAdd and EMF SiPLE versions | Yes                   | No                    |
| [RTT](http://code.google.com/p/rtt/) | Regression test tool | No                    | Yes                   |
| [Beaver](http://beaver.sourceforge.net/) | LALR(1) parsergenerator | No                    | Yes                   |
| [JFlex](http://jflex.de/) | Lexer generator | No                    | Yes                   |

## Configuration ##

After checking out SiPLE, deploying xText and installing the Eclipse AspectJ tools and JastEMF no further configurations are required. The different SiPLE versions can be immediately generated using [Ant](http://ant.apache.org/) build scripts. It is only important to remember, that all build scripts must be executed within the JRE in which Eclipse is executed.

To execute a build script within the same JRE, select _Run => External Tools => External Tool Configurations =>_ double click _Ant Build =>_ give the script a name and select it (_Buildfile:_ e.g. `${workspace_loc:/org.jastemf.siple/specifications/siple/build.xml}`) _=> JRE => Run in the same JRE as the workspace => Apply_. Of course, also the desired target has to be selected under the _Targets_ tab. In SiPLE's case it is also recommended to automatically refresh and build the workspace after generation (_Refresh => The project containing the selected resource => Recoursively include sub-folders_ should be selected; _Build => The project containing the selected resource_).

Further steps, how to deploy and use generated implementations, are given in the respective version's description.

# Project Repository Overview #

Before we investigate how to generate and use the different SiPLE versions, let us take a short look on the project's structure. The project consists of five relevant resources:
  * Generated SiPLE implementation parts (source code folder _"src-gen"_)
  * Hand-written SiPLE implementation parts (source code folder _"src"_)
  * SiPLE specifications (source code folder _"specifications"_)
  * SiPLE regression tests (folder _"tests"_)
  * Required libraries and other resources (folder _"sources"_)


The following screenshot gives an impression of the example's repository:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/repository_structure.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/repository_structure.png)

## Implementation ##

The actual **SiPLE implementation** is in the _"src"_  and _"src-gen"_ source code folders. The contents of _"src-gen"_ will be generated as shown later. Its general package structure is as follows:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/repository_implementation.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/repository_implementation.png)

  * _src/beaver_: Contains source code required by Beaver generated parsers (Beaver runtime).
  * _src/org.jastemf.siple_: Contains a simple `Interpreter` class to execute SiPLE programs from command line. It expects only one argument --- the program file to interpret. Given a program to interpret the class just instantiates a SiPLE parser and lexer, which are used to produce the program's model. Afterwards, the model itself is executed by calling its execution semantics.
  * _src/org.jastemf.siple.semantics_: Contains handwritten helper classes required during SiPLE interpretation, like `State` and `Type` which represent interpreter states and SiPLE types respectively.
  * _src-gen/jastemf.adaptation_: Package containing integration artefacts generated by JastEMF. These artefacts are not required by the implementation, i.e. they are not required at runtime. They are only stored for convenient reasons for developers interested which changes exactly JastEMF performed. Of course, only the EMF and xText versions are generated by JastEMF.
  * _src-gen/rtt.adaptation_: Package containing artefacts required for regression testing via RTT. Since RTT's integration depends on the generated SiPLE version, the package content is generated.
  * _src-gen/org.jastemf.siple.`*`_: These packages contain the generated semantic metamodel implementation .
  * _src-gen/siple.symbols_: Package containing generated artefacts for lexic analysis.
  * _src-gen/siple.syntax_: Package containing generated artefacts for syntactic analysis.

## Specifications ##

To generate the desired SiPLE implementation, of course, appropriate specifications are required. Thus, the second important project resource are **SiPLE's specifications**, which are in the _"specifications/siple"_ folder. The specifcations are grouped w.r.t. different implementation concerns. For each concern an equal named folder containing all its specifications exists:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/repository_specifications.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/repository_specifications.png)

  * _model_: Specification of the SiPLE compiler's API --- i.e. its metamodel (_siple.ecore_) --- and how to generate an appropriate EMF implementation (_siple.genmodel_). Also a diagram of the metamodel is given (_siple.ecorediag_). **Note:** Only the EMF version has an Ecore metamodel.
  * _semantics_: Specification of the SiPLE compiler's static semantics and its interpreter functionallities (dynamic/execution semantics). The semantics are separated w.r.t. SiPLE's different semantic concerns, which are name analysis, type analysis, type coercions, constraint checking, interpretation and helper methods subsumed in the access support concern. To declare all these concerns a so called compiler core is defined, which specifies the functionallity each concern must support, i.e. the compiler core declares all supported semantics whereas each concern's JastAdd specification (the _`*`.jrag_ files) specifies it.
  * _syntax_: Specification of the SiPLE compiler's parser. In the JastAdd and EMF version a LALR(1) Beaver parser is used (_parser.beaver_). The xText version is based on an LL(`*`) ANTLR parser, which is generated based on an xText concrete syntax definition (_siple.xtext_).
  * _symbols_: Specification of an appropriate JFlex lexer (_lexer.jflex_) for the Beaver parser (_parser.beaver_).

The generation process of a SiPLE version from its respective specifications is steered by a single [Ant](http://ant.apache.org/) build script (_"build.xml"_). To clean up generated artefacts the `Clean` task can be used. **Important:** The build script must be executed within the JRE in which Eclipse is executed (For details see [above](#Configuration.md)).

## Tests ##

Another important project resource are test programs that check SiPLE's implementation, especially the correctness of its static semantics. All artefacts related to testing are contained in the _"tests"_ folder, which consists of three subfolders:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/repository_tests.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/repository_tests.png)

  * _adaptation_: Contains AspectJ aspects, that prepare the JastAdd and EMF version's implementation for regression tests with RTT. The respective adaptation aspects are copied verbatim into the `rtt.adaptation` package while generating a concrete SiPLE version.
  * _source`_`code`_`examples_: Contains example SiPLE programs used for testing. The programs are classified, whether they are correct or contain static semantic errors. Each program (i.e. testcase) tests a certain aspect of the SiPLE language. All example programs are packed together into a single RTT testsuite.
  * _testsuites_: Contains the RTT testsuite against which the JastAdd and EMF versions are regression tested.

Similar to the generation of a SiPLE implementation from its specifications, also the generation, updating and execution of SiPLE's RTT testsuite is steered by a single Ant build script (_"build.xml"_ in the _"tests"_ directory). Again, the build script must be executed within the JRE in which Eclipse is executed (For details see [above](#Configuration.md)).

## Libraries and other Resources ##

The last important project resource are third party libraries SiPLE depends and which are shipped with it (For a summary of each library's purpose see the table [above](#Installation.md)). They are contained in sub folders of the _"sources"_ directory:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/repository_sources.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/repository_sources.png)

  * _libraries_: Contains `jar` libraries explicitely called by SiPLE's Ant build scripts or on which SiPLE's implementation depends.
  * _applications_: Contains third party applications that have to be installed manually as described [above](#Installation.md). If for any of the _libraries_ folder's `jar` a more comprehensive end user distribution exists, also it is contained.
  * _support_: Contains support artefacts, that ease the development of SiPLE and SiPLE programs in third party tools (e.g. [TextPad](http://www.textpad.com/) syntax highlighting specifications).

# JastAdd Version #

## Introduction ##

The first SiPLE implementation we investigate has a straightforward compiler construction approach. It consist of a generated lexer, a generated LALR(1) parser, a generated attributed abstract syntax tree (AST) and a generated attribute evaluator. The attribute evaluator is part of the AST --- i.e. the AST implementation contains the semantics implementation. The lexer is specified by a single [JFlex](http://jflex.de/) specification (_"specifications/siple/symbols/lexer.jflex"_), the parser by a single [Beaver](http://beaver.sourceforge.net/) specification (_"specifcations/siple/syntax/parser.beaver"_) and the AST by several [JastAdd](http://www.jastadd.org/) AST and `jrag` specifications (_"specifications/siple/semantics/_`*`_"_). As known from compiler construction, it is the lexer's purpose to transform a stream of characters (an input program) into a stream of tokens, it is the parser's purpose to transform the stream of tokens into an AST and it is the attribute evaluator's purpose to evaluate the AST's semantics. In the following our focus is on semantics, since their JastAdd specifications are the same for all SiPLE versions, whether they have an Ecore metamodel and are EMF integrated or not.

As mentioned before, the semantics are part of the AST generated by JastAdd. For each specified AST node type a equal named class is generated --- i.e. the AST implementation is an object oriented class hierarchy --- and each attribute becomes a method of the class representing the node type it is associated with. If an attribute's method is called, its semantics are evaluated. Thus, JastAdd generates demand driven RAG evaluators and the compiler's API are the AST classes and their attribute methods. Besides attributes for static semantics, also methods for interpretation purpose are specified for SiPLE and woven by JastAdd into the AST. These methods are based on the specified static semantics and are part of the semantic specifications. Thus, interpretation is not done on a special intermediate representation, but rather on the AST. In contrast, e.g. to Java and the JVM, where the JVM interprets bytecode programs and not Java source code programs, SiPLE's interpreter is executed on the source code's AST. Therefore, it is no problem to instantiate its lexer and parser, let them construct a program's AST and just invoke the AST root node's `Interpretation` attribute to execute the program. A default implementation performing exactly these steps is the `org.jastemf.siple.Interpreter` class's `main` method, which expects as argument the path of the program to interpret.

## Compiler API: A Quick Look at SiPLE's JastAdd AST and `jrag` Specifications ##

Before we generate and execute the JastAdd SiPLE version, let us have a short look on the specification of its semantics. As sketched above, to specify semantics we use JastAdd RAGs. Thus, semantics are realised by synthesized, inherited and collection attributes. The AST these attributes are associated with is given by JastAdd AST specifications (_"specifications/siple/semantics/ast.ast"_). The following is a specification excerpt:

```
CompilationUnit ::= Declaration*;

abstract Statement;
    
    If:Statement ::= Condition:Expression Body:Block [Alternative:Block];
    Block:Statement ::= Statement*;
    
    abstract Declaration:Statement ::= <Name:String>;
        
        ProcedureDeclaration:Declaration ::=
                Parameter:VariableDeclaration*
                <ReturnType:Type>
                Body:Block;
        VariableDeclaration:Declaration ::=
                <DeclaredType:Type>;
    
    abstract Expression:Statement;    
    
    ...
```

It specifies, that:

  * A `CompilationUnit` node type has arbitrary many `Declaration` child nodes.
  * `Statement` and `Expression` are abstract node types. Statements and expressions as such do not exist; only specific kinds of statements and expressions exist. Also, in SiPLE every expression is a statement.
  * `If` is a `Statement` with an `Expression` child named _Condition_, a `Block` child named _Body_ and an optional `Block` child named _Alternative_.
  * `Block` is a `Statement` that has arbitrary many `Statement` child nodes.
  * `Declaration` is an abstract node type that is a `Statement`. Every `Declaration` has a terminal child named _Name_ of type `String`.
  * `ProcedureDeclaration` and `VariableDeclaration` are special `Declaration` types. Thus, both have a _Name_.

The following diagram summarises the class hierarchy JastAdd generates for the specification excerpt:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/jastadd_ast_spec.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/jastadd_ast_spec.png)

As can be seen, besides the raw classes, also AST construction, manipulation and traversal methods are generated. They are an important part of the compiler's API.

Another important part are the semantic methods woven into the AST classes. For each  attribute specified a method with equal name and signature is woven into the AST class representing the node type the attribute is associated with. E.g. consider SiPLE's name analysis. Variable references (represented by the `Reference` node type) have a synthesized `Declaration()` attribute that returns the associated `Declaration` node or `null`, if the variable is not declared or several times declared. Also, all node types (`ASTNode`) have an inherited `LookUp(String name)` attribute, which returns a collection containing all the declarations for the given name which are in scope. The **declaration** of these two attributes in JastAdd is (**Note**: We will not investigate attribute equations in this tutorial; For details consult the JastAdd [website](http://www.jastadd.org).):

```
inh Collection<Declaration> ASTNode.LookUp(String name);
syn Declaration Reference.Declaration();
```

All attributes and their equations are specified in JastAdd `jrag` files. To support the modularization across semantic concerns, JastAdd permits the distribution of attribute declarations and equations across arbitrary many files. Each specification file can contain arbitrary many aspects. The idea is to use intuitive aspect names and specification file decompositions, such that different compiler concerns can be specified separately and the resulting specifications are much more convenient. Also, extensible compiler construction becomes possible, since new language constructs' semantics can be specified in additional `jrag` files. JastAdd weaves all specified semantics together, checks their consistency and completeness and generates a single evaluator implementation.

In SiPLE we use these techniques to specify a clear compiler API --- the _compiler core_. The compiler core consists of a single `jrag` specification file, that **declares** all semantic concerns (i.e. aspects) and their semantics (i.e. attributes) that must be supported by a valid SiPLE implementation (Of course, the compiler core also consists of the AST specifications (see above), that define the structure into which the concerns' attributes are woven). The actual definitions (i.e. equations) of the attributes declared in the compiler core are **not** part of it. Each concern still must be defined (See the different `jrag` specification files in the _"specifications/siple/semantics"_ folder, each named after the concern it implements). This way, on the one hand each concern can be specified as a separate module and on the other hand its specifiaction can depend on any other semantics declared in the compiler core. The following is an excerpt of SiPLE's compiler core:

```
aspect NameAnalysis {
    inh Collection<Declaration> ASTNode.LookUp(String name);
    syn Declaration Reference.Declaration();
    ...
}

aspect TypeAnalysis {
    syn Type Expression.Type();
    syn Type VariableAssignment.Type();
    syn Type ProcedureReturn.Type();
    ...
}

aspect Interpretation {
    public abstract Object Expression.Value(State vm)
        throws InterpretationException;
    
    syn State CompilationUnit.Interpret();
    public abstract void Statement.Interpret(State vm)
        throws InterpretationException;
}
...
```

Besides the above mentioned name analysis API (Each reference has a method that returns its associated declaration or `null` in the case of an error) it specifies, that:

  * SiPLE implementations support a type analysis:
    * Each expression has a type.
    * It can be statically checked which type an assignment has, i.e. if an assignment's right hand side can be assigned to the variable referenced on its left hand side.
    * It can be statically checked if a return statement's type is valid w.r.t. the signature of the procedure containing it.
  * SiPLE implementations support an interpreter:
    * Complete programs can be interpreterd by just calling the root node's `Interpret` method.
    * Single program statements can be executed based on a predefined (that includes user defined) execution state. Interpretation results are captured by changes of the input state.

It is obvious, that JastAdd AST and `jrag` specifications define the API of the generated evaluator, such that compiler end users can browse semantics by just calling appropriate methods.

## Generation ##

To generate the JastAdd version's implementation just open the _"specifications/siple/build.xml"_ Ant script, configure it to be executed within Eclipse's JRE as described [above](#Configuration.md) and execute the _"JastAdd Version"_ task. After generation the project should be error free (If there are errors remaining, ensure you [installed and configurated](#Deployment_and_Configuration.md) everything correctly). If everything performed well the console output should look like below:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/console_buildprocess_jastadd_version.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/console_buildprocess_jastadd_version.png)

**Note:** If you generated the EMF version before and like to switch back to the JastAdd version, you have to clean the project before its generation. To do so open the _"specifications/siple/build.xml"_ Ant script and execute its _Clean_ task. Afterwards right click the project and select _Team => Revert => OK_.

## Application: Interpretation of SiPLE Programs via Command Line ##

To test the just generated implementation you can on the one hand execute the regression tests as described [later](#Regression_Tests.md), investigate the regression test's expected results to see each attribute's value --- i.e. browse the compiler internal semantics --- or execute the SiPLE interpreter. For now we will focus on the interpreter. The EMF/xText versions are much better suited to browse compiler internal semantics.

Assume you have the following SiPLE program saved in a file (e.g. in _"tests/source_`_`_code_`_`_examples/test.siple"_):

```
Procedure main() Begin
    Write fac(12);
End;

Procedure fac(Var n:Integer):Integer Begin
    If n = 0 Then
        Return 1;
    Fi;
    Return n * fac(n - 1);
End;
```

To execute it open the `org.jastemf.siple.Interpreter` class. Select _Run => Run Configurations =>_ double click _Java Application =>_ select a name, e.g. `Interpreter` _=> Arguments => Program arguments:_ `"path/to/org.jastemf.siple/tests/source_code_examples/test.siple"` _=> Apply => Run_. As expected, the `Write` statement on line 2 prints the faculty of 12, i.e. `479001600`.

Let us introduce some error into the program, e.g. a reference to an undeclared variable `a` in the recursive `fac` method invocation:

```
Procedure main() Begin
    Write fac(12);
End;

Procedure fac(Var n:Integer):Integer Begin
    If n = 0 Then
        Return 1;
    Fi;
    Return n * fac(a - 1);
End;
```

Of course, the interpretation fails. After all the program is erroneous. But it does not fail randomly. It fails with a `org.jastemf.siple.semantics.InterpretationException`:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/console_interpretation_exception.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/console_interpretation_exception.png)

Let us keep the error, but additionally change the `fac(12)` procedure invocation to `fac(0)`:

```
Procedure main() Begin
    Write fac(0);
End;

Procedure fac(Var n:Integer):Integer Begin
    If n = 0 Then
        Return 1;
    Fi;
    Return n * fac(a - 1);
End;
```

If executed, the program now succeeds and prints `1`. That might look strange. After all the program still is erroneous. But it makes sense if you remember, that we are doing a source code interpretation. And of course, the recursive `fac` invocation is never encountered throughout interpretation for n = 0. Thus, the static semantics of a program construct are only computed while interpretation, if the construct is encountered. One might conclude, that this is a very inefficient solution, since program constructs that are executed several times always must be semantically evaluated. But that is not a necessity, since attribute values can be cached in JastAdd using the `lazy` attribute modifier.

## Conclusion ##

Even if we did not investigate the RAG specifications of SiPLE's semantics in detail, the so far described implementation should have made clear, that RAGs are a very well-suited and known approach to specify semantics. The interested reader might have already browsed SiPLE's semantic specifications further and recognized, that:

  * Semantics can be nicely decomposed into different concerns (Called aspects in JastAdd)
  * It is no problem to monotonic extend languages, i.e. to add further language constructs' semantics as modules. Thus, RAGs permit the specification-driven development of extensible compilers (E.g. see Tobjörn Ekmans PhD. thesis _"Extensible Compiler Construction"_ which describes the implementation of a Java compiler using JastAdd).
  * The complicated part of semantics --- the distribution of local information w.r.t. certain constraints accross a structure, to combine such information and further redistribute the results --- can be very convenient specified using RAGs. In essence attribute equations are specified w.r.t. a local --- i.e. easy to understand --- context and resulting complicated tree traversals and dependencies are automatically handled by the evaluator, such that there is no need to worry about traversal and dependency strategies (It is the manual implementation/specification of such traversal and dependency strategies that make semantics complicated. And many approaches that do not scale and only fit for toy examples do so because of this issue.).

So, from a compiler developers viewpoint (to conveniently implement a reliable, easy maintainable and extensible/changeable compiler) the JastAdd SiPLE version is fine. However, from a tool developers and metamodelers viewpoint, who is interested to integrate SiPLE into existing environments and likes to provide a convenient tool API and repository, the just presented solution is not so well. Its main problem is the proprietary API (generated based on the JastAdd AST and `jrag` specifications) and repository.

In the next section we present the EMF version, which improves in that direction. It has the same semantics as the just presented JastAdd version and additionally an Ecore metamodel, which defines its API.

# EMF Version #

## Introduction ##

The second SiPLE version we investigate is an extension of the just presented [JastAdd version](#JastAdd_Version.md) and has its focus on tool integration and language API. It complements the JastAdd version's lexic, syntactic and semantic specifications and the components generated based on them with an appropriate Ecore metamodel, such that the SiPLE compiler as well as SiPLE programs are integrated in the EMF world. To realise this integration --- i.e. an Ecore metamodel implementation with semantics --- JastEMF is used.

**Note:** The viewpoint, that we first developed a compiler with JastAdd and now extend it with an appropriate API is just by chance. We can also do it the other way around --- i.e. the metamodeler's way --- and first specify the desired tool API and functionality via a metamodel and than extend it with semantics using JastAdd.

**Note:** It should be clear by now, that the JastEMF and JastAdd versions' semantics are exactly the same. The JastEMF version reuses all JastAdd specifications of the JastAdd version. The same holds for the JFlex lexer and the Beaver parser specifications.

## Compiler API: A Quick Look at SiPLE's Ecore Metamodel ##

If we like to integrate SiPLE into the EMF world and take advantage of other EMF tools, we must provide an appropriate Ecore metamodel. Of course, we already know its components and their supported semantics from the compiler core specified using JastAdd and presented in the previous section. The following screenshot shows the corresponding Ecore metamodel's diagram (_"specifications/siple/model/siple.ecorediag"_):

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/metamodel_diagram.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/metamodel_diagram.png)

The diagram is based on the _"specifications/siple/model/siple.ecore"_ metamodel. Consider the following excerpt of it shown in Eclipse's _Sample Ecore Model Editor_:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/metamodel_tree_editor.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/metamodel_tree_editor.png)

It becomes obvious, that:

  * All AST node types have to be modelled as **metaclasses**. Abstract AST node types must also be **abstract metaclasses** in the metamodel.
  * The subtypes of an AST node type have to **inherit** from its metaclass in the metamodel.
  * AST terminals are **non-derived properties** of equal type in the metamodel (To avoid confusion, we call Ecore attributes properties).
  * Child nodes are **non-derived containment references** of equal type in the metamodel.
  * Parameterless attributes are **derived properties** of equal type in the metamodel.
  * Attributes with parameters have to be modeled as **operations** with equal signature in the metamodel.
  * Reference attributes are **derived non-containment references** in the metamodel.

Summing up, the metamodels' abstract syntax and the JastAdd AST specifications' must be isomorph and the semantics of derived properties, non-containment references and operations of the metamodel must be specified by appropriate attributes (Or methods woven by JastAdd).

**Note:** The mapping between JastAdd and Ecore is not necessary bijective. Whereas the semantics' JastAdd specifications must be complete, the focus of metamodels is to specify only the interface important for users. Thus, many attributes --- e.g. ones that are used to ease the specification of complicated semantics --- may not be specified in the Ecore metamodel. Nethertheless, the specified JastAdd AST structure and the metamodel's abstract syntax must be isomorph.

Details about the mapping between JastAdd and Ecore can be found [here](Mapping.md) (**TODO** The mapping page is still under development). We also strongly recommend the reader to take some time and explore SiPLE's JastAdd specifications --- especially the compiler core (_"specifications/siple/semantics/Core.jrag"_) --- and its metamodel to get a feeling for the mapping between JastAdd and Ecore and how JastAdd is used to specify metamodel semantics.

## Generation ##

It should be clear by now, that SiPLE is a valid [JastEMF integration project](Approach#Integration_Projects.md). To generate its Ecore metamodel implementation and JastAdd semantics and perform the integration, again the _"specifications/build.xml"_ Ant build script is used. Of course, this time the _"EMF Version"_ task must be executed. [As used to](#Configuration.md), the script must be executed within Eclipse's JRE.

**Note:** If you generated the JastAdd version presented in the previous section, you have to clean the project before generating the EMF version. To do so, open the _"specifications/build.xml"_ Ant build script and execute its _Clean_ task.

Even if the generated implementation already is a valid metamodel and semantics implementation, it still does not provide any means (besides the standard model factories) to construct program models or investigate them. Therefore, we like to generate an Eclipse editor, that can be used to construct, manipulate and investigate SiPLE programs. To generate the Eclipse model editor, open the metamodel's generator model (_"specifications/siple/model/siple.genmodel"_), right click its root element and select _Generate Editor Code_ as shown below:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/emf_genmodel_generate_dialog.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/emf_genmodel_generate_dialog.png)

Eclipse's standard EMF tooling will generate a plugin project named _siple.editor_.

## Application: Editing SiPLE Programs as Models via generated Eclipse Model Editors ##

The Eclipse editor, generated in the previous section, is based on the JastEMF integrated SiPLE implementation. Thus, one can browse SiPLE programs' static semantics --- i.e. derived properties and non-containment references --- while editing them.

To use the editor, it must be deployed as Eclipse plugin. We avoid any deployment process and just start an appropriate configured Eclipse by selecting _Launch an Eclipse application_ within the _Overview_ tab of the _siple.editor_ plugin's _"plugin.xml"_.

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/editor_pluginxml_launch.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/editor_pluginxml_launch.png)

**Note:** If you have other projects in your workspace, that are erroneous, you will get an error message. Just ignore it and select _Proceed_.

Create a new empty project in the started Eclipse (_New => Other => General => Project_). Within projects you can now create SiPLE program models and edit them with the SiPLE model editor. Let us create a SiPLE model named `mymodel.siple` and do an example editor session to construct the following model:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/model_tree_editor.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/model_tree_editor.png)

  1. Create the model (_New => Other => Example EMF Model Creation Wizard => Semantics Model => Next => File name:_ `mymodel.siple` _=> Next => Model Object:_ `CompilationUnit` _=> Finish_). **Note:** Eclipse's Mac distribution has a nasty Creation Wizard bug. After selecting a _Model Object_ via the pull down menu the _Finish_ button still remains disabled. Just delete a character of the selected _Model Object_ and manually write it again and the button should work (The problem is caused by an erroneous listener implementation within Eclipse).
  1. Add a procedure `proc` to the model (Right click the `CompilationUnit` node and select _New Child => Procedure Declaration_. Afterwards open the properties view (_Window => Show view => Other => General => Properties_) and change the procedure declaration's name to (_Name:_ `proc`).
  1. Add a parameter of type real and named `para` to `proc` (Right click the procedure declaration and select _New Child => Variable Declaration_ + _Name:_ `para` + _Declared Type:_ `Real`).
  1. Add a body (i.e., implementation) to `proc` (_New Child => Block_).
  1. We like `proc` to print on the console while its execution whether the value of `x` and its parameter `para` is equal. Therefore we have to...
    1. Add a write statement (_New Child => Write_).
    1. Add an equality test (_New Child => Equal_).
    1. Add a reference to `x` as first operand of the test (_New Child => Operand1 Reference_ + _Name:_ `x`).
    1. Add a reference to `para` as second operand of the test (_New Child => Operand2 Reference_ + _Name:_ `para`).
  1. Now is a good time to browse the model's semantics (model elements' semantics are shown in their properties view). We know, the program should be erroneous, because `x` is not declared at all. You can see that by...
    * Selecting the reference to `x`. In its properties view its JastAdd computed semantics are shown. It becomes obvious, that it is not declared (the _Declaration_ shows nothing) and has no valid type (_Type:_ `ERROR_TYPE`).
    * If you select the reference to `para`, its associated declaration (`proc`'s parameter) and type (`Real`) are correctly computed.
    * Of course, if you select the equality test or the write statement, their types will be an error type, since both depend on the erroneous reference to `x`.
  1. Let us fix the errors by adding a declaration for `x` (Right click the `CompilationUnit` and select _New Child => Variable Declaration_ + _Name:_ `x` + _Declared Type:_ `Integer`).
  1. If you now browse the write statement's, equality test's or first operand's semantics, they are appropriate updated.
  1. If you change the type of `x` to be a `Boolean`, the equality test's and consequently write statement's type will be an error, since boolean and real values cannot be compared.

We think the short editor session demonstrated, that JastEMF successfully integrated the semantics specified in JastAdd into the EMF generated metamodel implementation. Just feel free to play around with the SiPLE editor and test its integrated semantics (E.g., try nested procedures and lexical scopes).

## Conclusion ##

By providing an Ecore metamodel for the JastAdd semantics presented in the previous section, JastEMF enabled us to generate a metamodel implementation with integrated semantics. The resulting SiPLE implementation has on the one hand a very convenient semantics specification and on the other hand an appropriate metamodel. Thus, it satisfies both compiler developers' concerns (to conveniently implement a reliable, easy maintainable and extensible/changeable compiler) and metamodellers' concerns (convenient tool API, repository and integration into a common software development environment).

# EMF + Editor Version #

## Introduction ##

W.r.t. _semantics integrated metamodelling_, the EMF SiPLE version demonstrated reference attribute grammars practicability to specify metamodel semantics. To further elaborate and exemplify the benefits of _semantics integrated metamodelling_ we used [xText](http://www.xtext.org) to develop a text to model parser for SiPLE, that supports an Eclipse editor with syntax highlighting, code completion and integrated SiPLE semantics (note that in previous versions we used EMFText instead of xText). The only difference between the xText and the plain EMF SiPLE version is that xText complements SiPLE's existing textual user interface --- i.e., the Beaver parser and JFlex lexer --- by a generated text editor.

xText specifications basically specify a set of regular expressions representing tokens and a context-free grammar to describe a concrete syntax, whereas non-terminals refer to metaclasses of a metamodel, such that generated parsers construct model instances while parsing. Therefore, we reused the context-free grammar specification of the previous SiPLE versions' Beaver parser to develop an xText specification for SiPLE (_"specifications/siple/syntax/siple.xtext"_). xText automatically generates an Eclipse editor from this specification.

The semantic specifications of the previous SiPLE versions are reused. The generated editor immediately benefits from these semantics, such that its properties view shows SiPLE programs' semantics while editing them.

## Generation ##

The xText code can be generated using the pre-configured MWE script (_"specifications/siple/syntax/sipleeditor.mwe2"_). The execution of the script can be started by right clicking on the mwe2 file => Run as.. => Click on MWE2 Launch => Create a new configuration => Select 'Run in the same JRE as the workspace' => Click Run

Since the _"org.jastemf.siple.editor"_ project already exists (checked out from SVN) it is not newly created. Instead, the parser artefacts are generated into the existing project. The checked out components just reuse the generated parser to realize an Eclipse extension that provides an `Interpret` right-click menu entry for `*.siple` files, such that it is possible to execute SiPLE programs within Eclipse.

## Application: Editing SiPLE Programs as Models in a generated Eclipse Text Editor ##

To use the generated editor open the _"org.jastemf.siple.editor"_ project's _plugin.xml_ and select _Launch an Eclipse application_. Similar to the EMF version, you can now edit SiPLE programs using the generated editor.

Let us demonstrate the editor using the second faculty numbers example from above. Just create a new SiPLE program (_File => New => Other => General => File => Next => Container:_ Select any project within the workspace _=> File name:_ `faculty.siple` _=> Finish_). Open the xText siple editor for the program (Right click the `faculty.siple` file and select _Open With => Other => Internal editors => Siple editor => OK_) and copy and paste the program, which was:
```
Procedure main() Begin
    Write fac(12);
End;

Procedure fac(Var n:Integer):Integer Begin
    If n = 0 Then
        Return 1;
    Fi;
    Return n * fac(a - 1);
End;
```
If you now select the recursive `fac` invocation, its semantics in the properties view show a type error:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/emftext_editor_1.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/emftext_editor_1.png)

Let us fix the error by changing the `a` to `n`. Further, let us improve the program by not computing a fixed faculty, but rather ask the user for a faculty to compute. To do so add the variable declaration `Var n:Integer;` followed by a `Read n;` before the write statement in the `main` procedure. Remember, that you can use the editor's code completion functionalities by pressing _Control + Space_:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/emftext_editor_2.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/emftext_editor_2.png)

You can also use the outline view to see to which textual parts model elements belong:

![http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/emftext_editor_3.png](http://jastemf.googlecode.com/svn/wiki/Examples/SIPLE/emftext_editor_3.png)

Last but not least, you can still open SiPLE programs in the editor generated for the EMF version (Right click the program and select _Open With => Other => Internal editors => Semantics Model Editor_) and manipulate them, e.g., to move program parts. As soon as changes are saved, all other editors are automatically updated.

## Conclusion ##

The xText SiPLE version demonstrates that EMF tools and compiler projects benefit from _semantics integrated metamodelling_. The great advantage of JastEMF's approach is: It does not matter which editor or EMF tool you use. Since JastEMF integrates a metamodel's semantics into its implementation, all tools working with model instances of the metamodel benefit from its semantics and can reuse them for their own tasks.