# Basic User Interface #

Users interact with JastEMF in a black-box manner via a single Ant task --- the `jastemf` task. The complete integration process is started by calling the task and afterwards handled automatically by JastEMF. The task is registered in Eclipse by the JastEMF plugin, if it is [correctly deployed](Installation.md).

Besides the `jastemf` task, which starts the generation of a semantics integrated metamodel implementation based on an Ecore metamodel and a JastAdd reference attribute grammar (RAG), also [JastAdd's original Ant task](http://jastadd.org/jastadd-reference-manual/running-jastadd-under-eclipse#ANT) is provided by JastEMF.

## `jastemf` Task ##

The task is configurated with the generator model to use (`genmodel`), the package for the generated integration artefacts (`outpackage`), the package for the JastAdd generated AST classes (`astpackage`), optional arguments for JastAdd (`jastadd`) and arbitrary many filesets containing the semantic specifications. The optional arguments can be any option known by [JastAdd's command line interface](http://jastadd.org/jastadd-reference-manual/running-jastadd-from-the-command-line) and are used to configure JastAdd when internally called by JastEMF throughout the integration process.

```
<jastemf
    genmodel="path/to/the/ecore/generator/model/to/use/modelname.genmodel"
    outpackage="package.for.the.integration.artifacts.generated.by.jastemf"
    astpackage="package.for.the.ast.classes.generated.by.jastadd"
    jastadd="optional JastAdd command line options">
    <fileset dir="base/directory/containing/jastadd/semantic/specifications">
        <include name="**/*.ast"/>
        <include name="**/*.jrag"/>
        <include name="**/*.jadd"/>
    </fileset>
</jastemf>
```

The embedded JastAdd call has not to specify the AST classes' package and output directory again. Also, the `--rewrite` flag is always set by JastEMF.

Ant build scripts using JastEMF must be executed within the same JRE as Eclipse (Select _Run => External Tools => External Tools Configurations =>_ select the Ant build script containing the `jastemf` task _=> JRE => Run in the same JRE as the workspace_).

**IMPORTANT NOTE**: This task requires some time. JastEMF uses complex Eclipse-based refactorings to merge the class hierarchies and this is slow. Future versions may provide a much faster implementation.

### Advanced Options in Version 0.1.6 ###

We're currently improving the JastEMF integration process with JastAdd. The new alternative implementation can be enabled and tweaked using the following options in the Ant task:

```
programmaticrefactorings="true"
loglevel="ALL"
reconcile="false"
```

The `programmticrefactorings` switch enables the new refactoring processor. It can be up to 4 times faster. However, the API changes slightly differ from the original implementation. `loglevel` can be used to adjust to log output of the new implementation using standard Java logging API levels, e.g., ALL, INFO, WARN, OFF. `reconcile` is also new. Setting it to true, forces JastEMF to reconcile your EMF GenModel, which may be required in the build process, e.g., if some ecore files are regenerated.

The new version is available from the Live update-site:
**http://jastemf.googlecode.com/svn/trunk/build/org.jastemf.build/distribution**

## `jastadd` Task ##

JastEMF also provides the [Ant user interface known from JastAdd](http://jastadd.org/jastadd-reference-manual/running-jastadd-under-eclipse#ANT). The only difference is, that the `jastadd` task is registered by the JastEMF plugin in Eclipse, such that it can be used in Ant build scripts without being explicitely defined.

# The Converter's User Interface #

The JastEMF Converter plug-in provides some automated ANT tasks that allow to derive attribute grammar stubs from a given Ecore model or to derive a corresponding Ecore model from a set of attribute grammar specifications.

## `jastemf.ecore2ast` Task ##
This task allows you to derive AST grammars from a given metamodel. The metamodel must be revealed by the task's `modelFile` parameter. The transformation result is stored in the directory passed via `outputDirectory` to the task. Note that each `EPackage` in the provided model will get its own AST file, which is named like the package.

```
<jastemf.ecore2ast
	modelFile="your/model/directory/yourmodel.ecore"
	outputDirectory="target/directory/for/derived/ast/specs">
</jastemf.ecore2ast>
```

The resulting AST specifications correspond to the syntactic interface, i.e., the containment hierarchy of the given metamodel.

## `jastemf.ecore2jrag` Task ##
This task derives a set of very basic attibute declrations from a given metamodel. The specification has to be completed manually to form a usable attribution.

```
<jastemf.ecore2jrag
	modelFile="your/model/directory/yourmodel.ecore"
	outputDirectory="target/directory/for/derived/jrag/specs">
</jastemf.ecore2jrag>
```

The resulting specifications correspond to the semantic interface of the metamodel, i.e., its non-containment `EReferences`, derived `EAttributes`, `EOperations` and derived containment `EReferences`.

## `jastemf.jastadd2ecore` ##
This powerful task allows you to derive an Ecore model from a given set of JastAdd specification files. The `target` parameter takes a directory or file path where the generated resources should be serialized. The directory or file depends on the `onFilePerPackage` option which specifies if each AST file should get its own `EPackage` file (i.e., a directory is required) or if all packages should be put into one file. The optional `rootPackageName` parameter specifies the root package name for the one file case. One or more nested `filesets` provide the required set of specification files.

```
<jastemf.jastadd2ecore
	target="your/result/directory/or/file.ecore"
	ignoreASTTypes="[true|false]"
	oneFilePerPackage="[true|false]">
	     <fileset dir="your/spec/dir">
		<include name="**/*.ast" />
		<include name="**/*.jrag" />
		<include name="**/*.jadd" />
	    </fileset>
</jastemf.jastadd2ecore>
```

The generated Ecore model(s) using this task will contain the syntactic interface given by the AST specifications and the semantic interface given by the attribute declarations in the attribute grammar files. Especially, each `EStructuralFeature` in the semantic interface contains an `EAnnotation` with additional metainformation about its kind (e.g. inh) and origin.