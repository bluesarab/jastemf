

# Introduction to Statemachine Language #

The statemachine language is a language to describe the behaviour of systems using a state-based abstraction. It provides a graphical syntax that was built using the Graphical Modeling Framework [GMF](http://www.eclipse.org/modeling/gmf/). The semantics specification is derived from the JastAdd tutorial presented at GTTSE 2009 by Görel Hedin (original source: http://www.cs.lth.se/~gorel/2009-gttse-statemachine.html)

Fig.1 shows an statemachine describing an exemplary phone system. The phone can be in different states (rounded rectangles), e.g. _On-hook, Ringing, Ready, Dialing, and Connected_.  Furthermore the statemachine describes transitions possibile between states of the phone, e.g. from _Connected_ to _On-hook_ when the phone is hung up (_hang up_).

![http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/phone.png](http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/phone.png)

**Figure 1: Statemachine Example describing a Phone System**

# Deployment and Configuration #

## Requirements ##

To work with the statemachine language you need to install !JastEMF. Please follow the [Installation](Installation.md) guide.

Additionally, the statemachine language is based on the [SiPLE example](SIPLE.md). Statemachines' semantics are defined in an operational style, by a transformation from statemachines to plain, executable SiPLE code. Therefore, the statemachine metamodel reuses [SiPLE's metamodel](SIPLE#EMF_Version.md).

## Installation ##

The statemachine language can be obtained from our SVN repository. You find an Eclipse project named _"statemachine"_ and the subfolder  _"jastemf-examples"_.

You should check it out with your favourite SVN client.  If not already imported with Eclipse you need to import the project into you Eclipse Workspace (File > Import... > Existing Projects into Workspace)

## Configuration ##

After deploying the statemachine language to your Eclipse workspace you can start the build process. We provide a buildscript that runs the JastEMF generator. The build script must be executed within the same JRE as Eclipse. Therefore, select _Run > External Tools > External Tool Configurations..._ and create a new Ant Build. As _Buildfile_ select _/statemachine/specifications/build.xml_. Now switch to the configuration tab _JRE_ and activate the option _Run in the same JRE as the workspace_. In the _Targets_ tab  check that the target `EMF Semantic [default]` is activated. Finally, you should select _Run_ and the JastEMF code generation should be executed.

Second, it is required to run the GMF Codegeneration that derives a graphical editor for the statemachine language. Therefore, select _Generate diagram code_ in the context menu of the file _statemachine/specifications/metamodel/Statemachine.gmfgen_.



# Project Overview #
In this section we will discuss the project structure of our !JastEMF-based statemachine implementation.

|![http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/pluginsStructure.png](http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/pluginsStructure.png)|![http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/projectStructure.png](http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/projectStructure.png)|![http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/specificationsStructure.png](http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/specificationsStructure.png)|
|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Fig. 3a) Statemachine Language Projects**                                                                                                                           | **Fig. 3b) Statemachine Core Project**                                                                                                                                | **Fig. 3c) Statemachine Specification**                                                                                                                                             |

#### Statemachine Language Projects ####
Our statemachine implementation consists of three Eclipse plug-ins (cf. Fig. 3a) ):
  * _statemachine_: contains the specification and implementation of EMF abstract syntax and JastAdd semantics for the statemachine language
  * _statemachine.edit_: contains the implementation of the EMF edit infrastructure for the statemachine language
  * statemachine.diagram_: contains the implementation of the GMF graphical editor for the statemachine language_

#### Statemachine Core Project ####
The _statemachine_ project contains the core implementation and specification for the statemachine language. As depicted in Fig. 3b) it consists of the following folders:
  * _src-gen_: implementation of abstract syntax (EMF metamodel) and semantics (JastAdd) of the statemachine language
  * _src_: manually added implementation artefacts for testing the statemachine language (!JUnit)
  * examples_: exemplary statemachine and statemachine diagrams
  *_specificications: the specification artifacts to generate abstract syntax, semantics and diagram editor for the statemachine language
  * _tools_: additional libraries required (junit.jar)

#### Statemachine Specification ####
The _statemachine_ project contains several specification artifacts to generate abstract syntax, semantics and diagram editor for the statemachine language. As depicted in Fig. 3c) it consists of the following elements:
  * _metamodel_: EMF and GMF specifications for the generation of abstract syntax (EMF) and diagram editor (GMF) of the statemachine language
    * _Statemachine.ecore_: specification of EMF-based metamodel
    * _Statemachine.genmodel_: code generation options for EMF metamodel (destination directory, etc.)
    * _Statemachine.gmfgen_: code generation options for GMF editor (destination directory, etc.)
    * _Statemachine.gmfgraph_: specification of graphical elements for the statemachine editor
    * _Statemachine.gmfmap_: specification of connection betwen graphical elements and metamodel elements
    * _Statemachine.gmftool_: specification of palettes and menus for our statemachine editor
  * _semantics_: semantics specifications for the statemachine language
    * _!EMFBoilerplates.jrag_:
    * _Exercises.jrag_:
    * _Graph.jrag_:
    * _NameAnalysis.jrag_: specification of of name analysis for state machine
    * _PrintReachable.jrag_:
    * _Reachability.jrag_: specification of reachability analysis to compute reachable states
    * _Statemachine.ast_:

# Specification Details #
The SiPLE-Statemachine implementation consists of a static semantics part, which is realised by JastEMF, and a execution semantics part. Execution semantics is realised by first translating a statmachine into equivalent SiPLE code using an XPand-based model-to-text transformation and then executing the generated program using the SiPLE interpreter.
In the following, we will first investigate the statemachine API. Afterwards, we discuss the XPand-based code generator.

## JastAdd specifications and Ecore metamodel ##
The following Listing shows the JastAdd AST specification, which models the
containment structure/AST of the attribute evaluator.
```
StateMachine ::=
		declarations:Declaration*
		machineVariables:StateMachineVariable*;
  
abstract Declaration;

StateMachineVariable ::= <name:String> <type:String>;

abstract State:Declaration ::= <label:String>;

NormalState:State ::= [entry:Action];
InitialState:State;
FinalState:State;

Transition:Declaration ::=
		<label:String>
		<sourceLabel:String>
		<targetLabel:String>
		<guardLabel:String>
		<actionLabel:String>;

Action ::= <actionLabel:String>;

```
It specifies that:
  * A `StateMachine` node consists of an abitrary number of `Declarations` and `StateMachine` variables.
  * `Transitions` and `States` are `Declarations`.
  * There are `NormalStates`, `InitialStates` representing the execution's start state and `FinalStates` idicating that the execution stops.
  * A `Transition` has a _label_, a _sourceLabel_ containing the origin `State's` _label_ and a _targetLabel_ containing the outgoing `State's` _label_. Furthermore, it has a _guardLabel_ which contains a String representation of boolean SiPLE expression which determines if a transition is viable or not. The _actionLabel_ contains a String represenation of a SiPLE statement which is executed as soon as the transition is executed. SiPLE statements may modify declared statemachine variables while SiPLE expressions may read them.
  * Each `NormalState` may be associated to an `Action` which is executed when the state is entered.
  * Similar to `Transitions`, each `Action` has an _actionLabel_.

Based on the basic AST structure, we can now specify the actual static semantics of our statemachine language. Although in this case we do not have a concrete core jrag specification, we present parts that belong to the Compiler API in the following listing. Adhering the principles of extensible compiler construction, each aspect declaration can be extended with attribute equations. For the complete specifications, please have a look on the statemachine project in the JastEMF SVN repository.


```

aspect NameAnalysis {
	syn lazy State Transition.source() = lookup(getSourceLabel()); 
	syn lazy State Transition.target() = lookup(getTargetLabel());
	inh State Declaration.lookup(String label); 
	syn State Declaration.localLookup(String label) = null;
}


aspect Graph {
	coll Set<Transition> State.transitions() [new HashSet<Transition>()] with add;
	syn EList State.successors() circular
		[new EObjectEList(State.class,this,StatemachinePackage.STATE__SUCCESSORS)];
}


aspect Reachability {
	syn EList State.reachable() circular
	  [new EObjectEList(State.class, this,StatemachinePackage.STATE__REACHABLE)];
}


aspect PrintReachable {
	public void StateMachine.printReachable() {...}
	public void Declaration.printReachable() {}
	public void State.printReachable() {...}
	public String State.listOfReachableStateLabels() {...}
}


aspect SipleComposition {
	inh int State.id();
     	public Statement Action.getActionStatement() {...}
	public Expression Transition.getGuardExpression() {...}
	public Statement Transition.getActionStatement() {...}

}

```
It states that:

  * A `NameAnalyis` aspect maps transition _sourceLabels_ and _targetLabels_ to the actual states. Note that _source_ and _target_ are declared as lazy attribute which means that the computed values are cached in the model until the cache is flushed.
  * The `Graph` aspect augments the tree structure to a graph. The _transition_ collection attribute collects references to all outgoing transitions of a graph (for more detail on collection attributes see the [JastAdd reference manual](http://jastadd.org/jastadd-reference-manual/attributes)). A `State's` successor relation is computed by the successor reference attribute. Note that the attribute has to be declared as circular because of side effects in the EMF caused by notifications.
  * The `Reachability` aspect provides an implementation of the _successor_ relation's closure, i.e., the attribute _reachable_ computes all directly or indirectly reachable successors of a `State`. Since it has to be computed by a fix point computation, _reachable_ has to be declared as a circular attribute.
  * `PrintReachable` provides some helper functions to print the reachability information to the console.
  * `SipleComposition` realizes parsing and instanciation of actions and guard conditions as SiPLE statements and expression. SiPLE statements and expressions are not part of the statemachine AST specification since they belong to different EPackages in the EMF and a package structure is not supported by JastAdd.

The following figure shows the corresponding Ecore model diagram. Note that the JastAdd AST structure is modeled by containment relations in Ecore that must be isomorphic to the tree structure in the AST spec. The JastEMF converter project provides support for automatically generating an appropriate AST spec for a given Ecore model.

![http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/statemachineMetamodel.png](http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/statemachineMetamodel.png)

## XPand specification ##
The following listing shows the XPand transformation we use to generate executable SiPLE. semantically equivalent code from statemachines.
```
«IMPORT statemachine»
«DEFINE main(String outpath) FOR StateMachine»
«FILE outpath-»
% Generated SIPLE state machine implementation
% Main procedure
Procedure main() Begin
	Var current_state:Integer;
	current_state := «this.declarations.typeSelect(statemachine::InitialState).get(0).successors.get(0).id»;
«IF this.machineVariables.size > 0-»
	% Global variables:
«ENDIF-»
«FOREACH this.machineVariables AS var-»
	Var «var.name»:«var.type»;
«ENDFOREACH-»
«LET this.declarations.typeSelect(Transition).select(t|t.source == this.declarations.typeSelect(statemachine::InitialState).get(0)).get(0) AS initTransition-»
«IF initTransition.actionLabel != null && initTransition.actionLabel.trim().length > 0-»
	% Initial transition action:
	«initTransition.actionLabel»
«ENDIF-»
«ENDLET-»
	
	While Not (current_state = «this.declarations.typeSelect(statemachine::FinalState).get(0).id») Do
		Var done:Boolean;
		done := false;
«FOREACH this.declarations.typeSelect(statemachine::NormalState) AS state-»
		If Not done And current_state = «state.id» Then
«IF state.entry != null-»
			«state.entry.actionLabel»
«ENDIF-»
«FOREACH this.declarations.typeSelect(Transition).select(t|t.source == state) AS transition-»
			If Not done And «IF transition.guardLabel != null && transition.guardLabel.trim().length > 0-»«transition.guardLabel»«ELSE-»true«ENDIF-» Then
«IF transition.actionLabel != null && transition.actionLabel.trim().length > 0-»
				«transition.actionLabel»
«ENDIF-»
				current_state := «transition.target.id»;
				done := true;
			Fi;
«ENDFOREACH-»
		Fi;
«ENDFOREACH-»
	Od;
End;
«ENDFILE-»
«ENDDEFINE»
```
It states that:

  * A `main()` procedure containing the statmachine program is generated.
  * For each statemachine variable a SiPLE variable is generated.
  * A variable `current_state` holds the current state's identifier. Inititally, this the `InitialState`'s id.
  * The statemachine is executed by a `while` loop as long as no `FinalState` is reached.
  * During each iteration, the current state's `actionLabel` is executed, afterwards, each of the current outgoing transitions' guard conditions (`guardLabel`) is evaluated until the first one that yields `true`.
  * Transitions are conducted by executing the `actionLabel`'s SiPLE statements and by stetting `current_state` to the next state's identifier.

# An Example Application #
To use the statemachine language you need to deploy the generated plug-ins or start a new Eclipse Application runtime. Therefore select _Run > Run Configurations ..._, create a new _Eclipse Application\_and_Run_it. This starts a new Eclipse instance where the plug-ins are deployed and the statemachine language ready to use._

To create a new statemachine select _File > New > Other... > Statemachine Diagram_. This opens a new empty diagram view. Fig. 2 shows the generated diagram editor in Action. The toolbar contains buttons to create new diagram objects (e.g. _State_) and connections (e.g. Transitions) in the diagram.

![http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/editor.png](http://jastemf.googlecode.com/svn/wiki/Examples/Statemachine/editor.png)

**Figure 2: The generated GMF Editor for the statemachine language**

The creation and manipulation of the diagram results in a direct adaptation of the underlying statemachine model.
The statemachine in Figure 2 contains four states --- two `NormalStates`, an `InitialState` and a `FinalState`. It uses a _counter_ variable for counting up to a value of 10 and prints the intermediate results. `Transitions` are represented by continous arrows. The `Properties` view allows to inspect current attribute and terminal values, e.g., State _B_ of Figure 2 has _NormalState 0_ (which is _A_) and _FinalState 5_ (which is _End_) in its successor relations. Since they are directly reachable from _B_, both of them are also included in the transitive closure of _B_ computed by the `Reachable` attribute. Since _B_ itself is reachable from itself over _A_, `Reachable` also added _B_ to the relation. In the editor, `Reachable` is visualised by dotted arrows.

To generate executable SiPLE code from a statemachine, we added an _Action_ to the Eclipse context menu. The _Action_ can be triggered by _right clicking_ on the model file (not the diagram file!) and selecting _Generate SiPLE Code_ from the opened context menu. As a result, a _modelname_.siple file is created. The following listing shows the SiPLE code for the counting statemaching in Figure 2.

```
% Generated SIPLE state machine implementation
Procedure main() Begin
	Var current_state:Integer;
	current_state := 0;
	% Global variables:
	Var counter:Integer;
	% Initial transition action:
	counter := 0;
	
	While Not (current_state = 5) Do
		Var done:Boolean;
		done := false;
		If Not done And current_state = 0 Then
			If Not done And true Then
				counter := counter +1;
				current_state := 1;
				done := true;
			Fi;
		Fi;
		If Not done And current_state = 1 Then
			If Not done And counter>10 Then
				current_state := 5;
				done := true;
			Fi;
			If Not done And counter<11 Then
				current_state := 0;
				done := true;
			Fi;
		Fi;
	Od;
End;

```

# Summary and Conclusion #
The statemachine example shows that typical grammarware technology (here JastAdd RAGs) and typical modelware technology (here EMF, GMF and XPand) can be combined beneficially. A modular RAG is used to specify static statemachine semantics while the EMF provides a smooth integration into the Eclipse platform and allows to also reuse the generated SiPLE implementation. The GMF enabled us to generate a powerful graphical editor on top of the semantics integrated metamodel. While the editor allows create and modify statemachines, it also provides means to inspect attributes using the _Properties_ view or even to visulise attributes by symbolising them in the editor (e.g., the `Reachable` attribute). The template language XPand is used to generate code from statemachines that can be executed by the SiPLE interpreter.