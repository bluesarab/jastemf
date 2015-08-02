# JastEMF Requirements #

JastEMF requires the _[Eclipse Modeling Framework (EMF)](http://www.eclipse.org/modeling/emf/)_ for _Ecore_ metamodel handling and _[openArchitectureware](http://www.openarchitectureware.org/)_ to execute integration artefacts generating templates. The easiest way to satisfy all requirements of JastEMF is to deploy it as plugin in a ([fresh installed](http://www.eclipse.org/downloads/)) Eclipse (Version >= 3.6) modelling distribution.

In detail JastEMF requires at least:
  * Apache Ant version 1.7 or higher
  * Eclipse version 3.6 or higher with installed
    * EMF SDK version 2.6 or higher (Update site http://download.eclipse.org/modeling/emf/updates/releases/) and
    * Eclipse Modelling Weaving Engine (including _xTend_ and _xPand_) version 1.0.2 or higher

# Installing JastEMF from Update-Site (recommended) #
JastEMF provides different Update Sites:
## JastEMF for Eclipse Juno (recommended) ##

  * The update-site for JastEMF 0.1.7 can be found below. The version has been tested with Eclipse 4.2 (Juno). It also installs on Eclipse 4.3 (Kepler) and 4.4 (Luna), however this has not been tested further yet.
    * http://svn.codespot.com/a/eclipselabs.org/jastemf/tags/beta_0.1.7/build/org.jastemf.build/distribution

## JastEMF for Eclipse Indigo ##
  * The update-site for JastEMF 0.1.6 (tested with Eclipse 3.6 and 3.7) can be found here:
    * http://svn.codespot.com/a/eclipselabs.org/jastemf/tags/beta_0.1.6/build/org.jastemf.build/distribution

## Trunk Version (not stable) ##
  * An update-site providing intermediate (potentially unstable) builds of the current trunk, that we do from time to time, can be found here:
    * http://svn.codespot.com/a/eclipselabs.org/jastemf/trunk/build/org.jastemf.build/distribution

# Installing JastEMF Manually (not recommended) #

We highly recommend to use the [Eclipse Update Site](#Installing_JastEMF_from_Update-Site.md). To install JastEMF manually:
  1. Download, install and configure an Eclipse distribibution
  1. Download the most recent _org.jastemf_ plugin JAR from our [download](http://code.google.com/a/eclipselabs.org/p/jastemf/downloads/list) section and move it to the eclipse dropins directory (_DIR_/dropins).
  1. Optionally download the most recent _org.jastemf.converter_ plugin JAR from our [download](http://code.google.com/a/eclipselabs.org/p/jastemf/downloads/list) section and move it to _DIR_/dropins.
  1. Run Eclipse and select an arbitrary workspace. Check if all JastEMF ANT tasks have been properly registered to Eclipse by investigating _Preferences => Ant => Runtime =>_ Tab _Tasks_. You should find the `jastemf` task and, if you installed the converter, the `jastemf.ecore2ast` and `jastemf.ecore2jrag` tasks.

# Quickstart with the SIPLE Example #

A good starting point for learning how to use JastEMF is to investigate the [SIPLE example](Examples.md) (Simple Imperative Programming Language Example). To build and run it you first need to install JastEMF as described above. Afterwards, SIPLE can be checked out from JastEMF's SVN repository under _"jastemf-examples/calculator"_. For further instructions consult the [example web page](Examples.md).

# Troubleshooting #

If you cannot find the Ant tasks, make sure you installed the correct Eclipse version and distribution and that you do not have an out-dated or multiple Ant version(s) installed. If everything seems right, try starting Eclipse with the `-clean` commandline option. If this still does not work, please report us your issue with detailed information.