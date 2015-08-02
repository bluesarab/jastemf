

# Overview and Application Domain #

_JastEMF_ is a tool to support the integrated use of the _[JastAdd](http://www.jastadd.org)_ system --- a rewriteable, circular, reference attribute grammar (ReCRAG) generator --- and the _[Eclipse Modeling Framework (EMF)](http://www.eclipse.org/modeling/emf/)_ --- a Java based metamodeling framework built around the _Ecore_ metamodeling language. The objective of JastEMF is to permit the specification of an Ecore metamodel's semantics using a JastAdd _ReCRAG_. Given an Ecore metamodel and an appropriate JastAdd _abstract syntax tree (AST)_ the metamodel's semantics --- i.e. the value and behavior of its _derived attributes_, _non-containment references_ and _operations_ --- can be specified using JastAdd. In essence, JastAdd can be used to **define** the metamodel elements that are only **declared** in the metamodel.

_JastEMF_ has three main use-cases, summarized in Figure I:
  * Given a metamodel one likes to specify its semantics, i.e. implement the evaluation and resolution routines for some or all of its derived attributes, non-containment references and operations.
  * Given a metamodel one likes to build a tool that somehow reasons about model instances, e.g. to produce some model dependent output or to manipulate the model.
  * Given a _JastAdd_ compiler one likes to integrate it into the _EMF_ world, i.e. provide the compiler internal API for other _EMF_ based tools by supporting a metamodel for the compiler.

![http://jastemf.googlecode.com/svn/trunk/jastemf/org.jastemf/implementation/JastEMF_Use_Cases.png](http://jastemf.googlecode.com/svn/trunk/jastemf/org.jastemf/implementation/JastEMF_Use_Cases.png)

**Fig. I:** _JastEMF's_ Use-Cases

For convenient reasons we will call the first two use-cases the specification of metamodel semantics and the last use-case a _JastAdd_ evaluator adaptation against the _EMF_. Since _JastEMF's_ integration process is equal for all its use-cases, we will in the following just speak about the specification of metamodel semantics.

# Metamodels, ReCRAGs and Metamodel Semantics #

To understand _JastEMF's_ usage, application area and integration process for _Ecore_ metamodels and _JastAdd ReCRAGs_ it is important to define what we consider metamodel semantics --- i.e. which parts of an _Ecore_ metamodel are further specified using a _JastAdd ReCRAG_:

**Definition (Metamodel Semantics):** Let <i>MM</i> be a metamodel
and <i>MME(MM)</i> be the finite set of its elements. Let <i>MME_syntax(MM)</i>
and <i> MME_semantic(MM)</i> be disjunct subsets of <i>MME(MM)</i>, whereas <i>MME(MM) =<br>
MME_syntax(MM) + MME_semantic(MM)</i>. Let <i>ME(M)</i> be the set of entities of a
model instance <i>M\MM</i>. Since <i>M\MM</i>, all
entities <i>e\ME(M)</i> have a type <i>t(e)\MME(MM)</i>. Let <i>S(MM)</i> be a function that
defines for all <i>M\MM</i> for
each entity <i>e\ME(M)</i> with <i>t(e)\E_semantic(MM)</i> the value of <i>e</i>.
We call <i>S(MM)</i> a metamodel semantic for <i>MM</i>. Iff <i>MME_syntax(MM)</i>
specifies a spanning tree for each <i>M\MM</i>, <i>S(MM)</i> can be specified
with a reference attribute grammar (RAG).

W.r.t. that definition we split the _Ecore_ modeling language as follow in syntactic and semantic parts:
  * <i>MME_syntax</i> = {metaclasses, containment references, non derived attributes}
  * <i>MME_semantic</i> = {non-containment references, derived attributes, operations}

It is obvious, that the elements <i>MME_syntax</i> of an _Ecore_ metamodel specify a tree structure for each model instance whereas the ones of <i>MME_semantic</i> arbitrarily depend on and manipulate the instance's spanning tree --- i.e. impose a graph on top of it. Thus, we consider an _Ecore_ metamodel's non-containment references, derived attributes and operations its semantics. And _JastEMF's_ approach is to use a _JastAdd ReCRAG_ to specify their value and implementation w.r.t. the metamodel.

# Integration Projects #

Starting point for _JastEMF_ is a project containing an _Ecore_ metamodel --- represented by a generator model (GenModel) --- and arbitrary many _JastAdd ReCRAG_ specifications for its semantics. As context-free structure for the _ReCRAG_ specifications --- i.e. the AST the project's _rewriteable, circular, reference, attribute grammar_ is based on --- the metamodel's containment references are used. Thus, **the _ReCRAG's_ AST must be isomorph to the metamodel's containment reference graph**.

We call a project satisfying these conditions an integration project. Using only _EMF_ tools and _JastAdd_ a developer can generate an _EMF_ implementation for the metamodel and a _JastAdd_ evaluator for the _ReCRAG_, but he cannot use the evaluator to reason about model instances. Both, the metamodel and the evaluator, have absolutely no knowledge about each other. Even worse, the evaluator maintains its own AST repository and the model has only method skeletons for its [semantics](#Metamodels,_ReCRAGs_and_Metamodel_Semantics.md). However, if _JastEMF_ is used to steer the model and evaluator generation --- i.e. to call _EMF_ and _JastAdd_ throughout its execution --- the result would be an _EMF_ model whose semantic methods are implemented. The integrated project has the API of the metamodel and the semantics of the evaluator.

Additionally, _JastEMF_ produces a tightly integration. With tightly, we mean that the result is one class hierarchy. The evaluator is woven into the _EMF_ model. There is no need for adapters from the evaluator to the model or vice versa. In fact, _JastAdd's_ internal AST repository (non-terminals and terminals) is completely deleted and instead the model repository is used, such that _EMF's_ model manipulation semantics --- notifications, interfaces etc. --- are satisfied. On the other hand, any semantic constructs within the model are deleted from the model's implementation and instead the ones generated by _JastAdd_ are used. In the end, both, repository and semantics, are integrated within the same classes --- thus, it is a tightly integration.

# Integration Process #

The complete integration process is observed and steered by an _integration manager_, which is responsible to interact with _JastAdd_, the _EMF_ and the components that generate integration related artefacts like refactoring scripts or repository adaptations. The following figure summarizes _JastEMF's_ integration process:

![http://jastemf.googlecode.com/svn/trunk/jastemf/org.jastemf/implementation/JastEMF_Integration_Process.png](http://jastemf.googlecode.com/svn/trunk/jastemf/org.jastemf/implementation/JastEMF_Integration_Process.png)

**Fig. II:** _JastEMF's_ Integration Process

It consists of three phases. Starting from the project to integrate, **phase I** generates based on the generator model a _JastAdd Repository Adaptation Specification_ and a _JDT Refactoring Script_. The _Repository Adaptation Specification_ adapts the _JastAdd_ evaluator to generate in such a way, that is uses instead of its own internal repository the one of the _EMF_ metamodel. It consists not only of attribute specifications, but also of many intertype declarations --- especially a set of _JastEMF_ internal source code annotations that mark and provide information for evaluator parts to adapt later. _JastEMF_ uses this _Repository Adaptation Specification_ together with the developed metamodel semantics (_JastAdd Attribute Grammar Specifications_) to trigger _JastAdd's_ generation process and retrieve a _Repository adapted Evaluator_. In **phase II** this evaluator is refactored in such a way, that it satisfies the metamodel's and _JastAdd's API_. First, the, generated _JDT Refactoring Script_ is applied. It incorporates metamodel naming conventions and package structures. After doing so, the previously mentioned _JastEMF_ annotations --- introduced by the repository adaptations --- are processed using the _JDT_. The result is an _Integration Prepared Evaluator_. Finally, in **phase III**, _EMF_ and its _JMerge_ tool are used to generate a metamodel implementation, that is immediately merged with the prepared evaluator. The result is an _EMF_ metamodel implementation with the evaluator's semantics.

# User Interface #

The user has not to be aware of the integration process. For him _JastEMF_ is a black-box he can call using an [Ant task](Ant_Tasks.md). The task is configurated with the generator model to use, the package for the generated integration artefacts, the package for the _JastAdd_ generated AST classes and an embedded call to _JastAdd's_ command line interface, such that it is no problem to configurate _JastAdd_ as used to. Of course, the embedded _JastAdd_ call has not to specify the AST classes' package and output directory again.