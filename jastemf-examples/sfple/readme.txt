Internal JastEMF project testing EMF-adaptation in the presence of complex JastAdd rewrites. Officially,
JastEMF does not support JastAdd rewrites.

========================================================================================================
|                                              WARNING                                                 |
|                                        The test project's                                            |
|                              EMF INTEGRATED INTERPRETER IS ERRONEOUS                                 |
|                             because of rewriting issues within the EMF.                              |
========================================================================================================

Example error: {1,2,3,4,5} * {1,2,3,4,5} should yield {1,4,9,16,25} but yields {1,9,25}.

The original, not EMF-integrated interpreter works as intended---it is the reference to test against.
