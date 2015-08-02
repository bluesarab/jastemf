# List of the SVN's JastEMF Examples #

In the following we shortly summarise the example [integration projects](Approach#Integration_Projects.md) included in JastEMF's source code repository. Each example is a separate Java project within the SVN's _"jastemf-examples"_ folder.

## SiPLE (Simple Imperative Programming Language Example) ##

SiPLE is a simple imperative programming language. Its language concepts are:
  * Integer, real and Boolean arithmetics
  * Pointers (including pointers to procedures and pointers)
  * A block-structured name space and nested procedures
  * Lexically-scoped first-class functions (i.e., lexical closures)
  * `While` and `If` control-flow statements
  * Automatic integer to real type coercions (e.g., when assigning an integer value to a variable of type real or when adding an integer to a real value)
  * A save interpreter, that calmly terminates in the presence of errors
SiPLE is strongly typed, such that a static type analysis can be performed.

Throughout the example we use different specification-driven compiler construction approaches to generate complete language toolings --- from the lexer over syntactic and static semantic analysis to interpreters and editors --- for SiPLE.

Example Objectives:
  * To show, how JastEMF can be used to generate a SiPLE EMF metamodel implementation with appropriate static and execution semantics.
  * To show, how EMF standard editors can be used to create SiPLE program models and the benefits of _semantics integrated metamodelling_.
  * To show, how other EMF tools can benefit from such semantic metamodel implementations by the example of [xText](http://www.eclipse.org/Xtext/) --- a text to model parser generator.

Details and a tutorial for the example can be found [here](SIPLE.md).

## SiPLE-Statemachine ##

SiPLE-Statemachine is a graphical language to specify finite state machines. Besides labeled states and transitions, it supports global variables, entry actions, transition conditions and transition actions in the form of annotated SiPLE code. State machines are modeled in a graphical Eclipse editor, that can also visualize state's reachability (i.e., their transitive closure). The correctness of annotated actions is checked reusing SiPLE's metamodel semantics. Last but not least, SiPLE implementations for specified state machines can be generated.

Throughout the example we use JastAdd to specify the SiPLE-Statemachine language's static semantics. Further, we generate a [GMF](http://www.eclipse.org/gmf) editor and integrate SiPLE's semantic metamodel implementation.

Example Objectives:
  * To show, how JastEMF can be used to realize a graphical modeling language's static semantics.
  * To show the benefits of semantics integrated metamodelling w.r.t. reuse and tool integration.

Details and a tutorial for the example can be found [here](Statemachine.md).

## PicoJava ##

PicoJava can be considered as a very small subset of the Java programming language. It supports nested class declarations with inhertance, variable declarations and while loops. The example and its JastAdd sources have been taken form the PicoJava tutorial from the [JastAdd website](http://jastadd.org/old/examples/picojava-checker), which can be consulted for details.

Troubleshoot: If the class hierarchies are not merged correctly in PicoJava, please close all other projects and re-run the integration again.

Example Objectives:
  * This example demontrates, how an Ecore representation can be automatically derived from a set of existing JastAdd specification using JastEMF.
  * Furthermore, it demonstrates how a very powerful textual editor for PicoJava can be immediately created by briding the JastAdd semantics implementation and a corresponding [EMFText](http://www.emftext.org) grammer using JastEMF.