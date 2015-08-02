# Introduction #
This page discusses the mapping between Ecore and JastAdd features.

# Mapping between JastAdd and Ecore #
## Properties of Ecore `EStructuralFeatures` ##
Each structural feature of an Ecore model's metaclasses can be configured by a bunch of options. Some of them even directly influence Ecore code generation. The following figure highlights which of them are important for the integration of the generated Ecore and JastAdd code generation.

![http://jastemf.googlecode.com/svn/wiki/Examples/Mapping/ecoreParameters.png](http://jastemf.googlecode.com/svn/wiki/Examples/Mapping/ecoreParameters.png)

The following table summarizes the effects of these options in JastEMF.

|Option|Semantics in Ecore|Effect in JastEMF|
|:-----|:-----------------|:----------------|
|derived|**true:** the feature's value is meant to be _derived_ from other features |The feature is computed by an attribute (except containments).|
|transient|**true:** the feature is excluded from serialization | All derived features that should be computed by an attribute must be transient. |
|volatile|**true:** setters and getters have to be implemented manually in generated code |                 |
|containment|**true:** the feature belongs to the underlying containment hierarchy | AST child node (only non-containments and EAttributes can be specified by an attribute). |
|changeable|**true:** the feature can be set manually | In combination with derived=**true**, this results in the _imperative mode_. |

_Imperative mode_: Using a model editor (e.g., the default editor shipped with the EMF) one can interactively create and modify model instances. Of course, also the value of derived model features --- i.e., the value of attributes --- can be manually changed. To change a derived value is called imperative mode. User given values shadow derived ones. If a derived feature's value is imperatively set by the user, dependent semantics are computed w.r.t. the user given value instead of the feature's semantics.

## Mapping ##
|Ecore Concept|Ecore option|Corresponding JastAdd Concept|
|:------------|:-----------|:----------------------------|
|EClass       |interface=**false**,abstract=**false**|non-terminal                 |
|             |interface=**false**,abstract=**true**|abstract non-terminal        |
|EReference   |containment=**false**, derived=**true**|reference attribute          |
|             |containment=**false**, derived=**true**, changeable=**true**|lazy reference attribute     |
|             |containment=**true**|parent-child relation in AST |
|EAttribute   |derived=**false**|terminal                     |
|             |derived=**true**|synthesized/inherited attribute|
|             |derived=**true**,changeable=**true**|lazy attribute (imperative mode)|
|EOperation   |            |attribute,java method        |