# CodeHawk-Java-Platform-Summaries
Platform-dependent library method summaries for the JDK and third-party libraries

## Overview

Library functions can be characterized by their functional behavior,
such as pre- and postconditions, and non-functional behavior, such
as performance in terms of time and memory usage. The functional
behavior of library functions is usually specified by their API
documentation and is (ideally) stable across different versions of
a library. The non-functional behavior must be inferred from the
actual implementation of the library, and may vary widely across
different versions of a library.

To accomodate both api and implementation-dependent characteristics
of library functions we provide three sets of summaries:
- *api*: these summaries are based on the API documentation (if
  available) and contain pre- and postconditions, taint transfer
  relations, side effects, exceptions thrown (as documented),
  string sinks, and resource sinks. Only one set of api summaries
  is provided per library;
- *profiled*: these summaries contain CPU time cost relations
  obtained via dynamic analysis, that is, direct profiling of
  the methods in various contexts. A separate set of summaries
  is provided for each version of a library;
- *supplement*: these summaries contain supplemental information
  obtained by CodeHawk static analysis of the particular version
  of the library, including CPU time cost based on cost model analysis,
  but potentially also more precise taint transfer information
  based on private fields not exposed in the API documentation.
  A separate set of summaries is provided for each version of a
  library.

Python scripts are provided to generate templates for these three
sets of summaries and to integrate them into a summaries that combine
the information, giving preference to information obtained from
dynamic analysis over information obtained from static analysis, if
both are available.

## Organization

This repository is organized as follows:

### bin
The bin directory holds the CodeHawk executables called by the scripts
to generate summary temlates, integrate the api, profiled, and
supplement version into a combined summary, and to inspect the
resulting library-summary jar for inconsistencies. The source code
for these executables is in the
[codehawk](https://github.com/kestreltechnology/codehawk) repository.

### lib-api
The [lib-api](lib-api/README.md) directory holds api-summaries for
third party libraries, such as the apache libraries. Currently 26
libraries are provided covering 441 classes. Jdk-api summaries are
included with the CodeHawk Java Analyzer source code in the
[codehawk](https://github.com/kestreltechnology/codehawk)
repository (currently covering close to 1000 classes and 10,000
methods).

### lib-reference
The lib-reference directory holds the different versions of the
library jars (with class files) that have been (partially) summarized.
These jars are the basis for generating the summary templates,
performing the profiling, and statically computing cost models for
the supplemental summaries.

### platforms
The platforms directory is intended to hold different combinations
of machines (JVM) and JDK's. At this point we include only platform,
the reference machine used in the DARPA STAC program with OpenJDK 8
version 121 installed.

The information for each platform is organized as follows:

- **export**: summary jars with integrated (api+profiled+supplement)
  summaries
  for each version of a library. These jars can be directly imported
  in the CodeHawk Java Analyzer.
- **jdk-reference**: class file jars associated with the platform
  (typically, rt.jar, jsse.jar, jce.jar)
- **jdk-summaries**: profiled and supplement summaries for the
  platform JDK
- **lib-summaries**: profiled and supplement summaries for third-party
  libraries running on this platform

### profiling
Java code infrastructure to perform profiling.

### scripts
Python scripts to generate, integrate, and inspect jdk and library
summaries.



