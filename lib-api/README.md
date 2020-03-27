## Platform-independent third-party library method summaries

The table shows the libraries for which api summaries are included in
this directory. Generation of summaries has been demand-driven by the
Engagement problems of the DARPA STAC program, hence coverage is only
partial. More summaries may be added over time.

The table shows for each library the number of class files for which
summaries were generated and the total number of public and protected
methods included in those classes. Method summaries are used only if
taint transfer has been specified, so the number of methods shown in
the taint transfer column indicate the number of methods actually
summarized. For some, again driven by demand and dependent on the
availability of API documentation, we have also added pre
and postconditions, as well as string sinks and resource sinks.


| Library | classes | methods | taint transfer | pre | post | string sinks | resource sinks |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| antlr4-runtime | 64 | 739 |  488 |  2 | 30 | 0 | 0 |
| apfloat |         5 | 300 |   88 |  0 |  3 | 4 | 0 |
|  bcprov-jdk15on | 3 | 19 | 9 | 0 | 0 | 0 | 0 |
| camel-core | 56 | 1577 | 288 | 0 | 14 | 4 | 0 |
| commons-cli | 8 | 136 | 121 | 0 | 15 | 6 | 0 |
| commons-codec | 7 | 49 | 32 | 0 | 1 | 0 | 0 |
| commons-compress | 11 | 105 | 61 | 1 | 1 | 0 | 0 |
| commons-fileupload | 11 | 76 | 38 | 0 | 0 | 0 | 0 |
| commons-io | 4 | 272 | 58 | 0 | 0 | 0 | 0 |
| commons-lang3 | 8 | 610 | 266 | 0 | 0 | 0 | 0 |
| commons-logging | 1 | 10 | 0 | 0 | 0 | 0 | 0 |
| commons-math3 | 41 | 697 | 129 | 0 | 6 | 0 | 0 |
| concurrent-trees | 17 | 132 | 97 | 0 | 5  | 1 | 0 |
| gson | 24 | 266 | 131 | 0 | 24 | 0 | 1 |
| guava | 12 | 88 | 25 | 14 | 7 | 0 | 0 |
| httpclient | 1 | 14 | 10 | 0 | 0 | 0 | 0 |
| httpcore | 1 | 2 | 2 | 0 | 0 | 0 | 0 |
| ieee-odm-schema | 102 | 1105 | 150 | 0 | 0 | 48 | 0 |
| javassist | 20 | 340 | 52 | 0 | 12 | 4 | 0 |
