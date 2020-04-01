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
| :--- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
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
| jetty-http | 13 | 214 | 159 | 0 | 2 | 14 | 1 |
| jetty-io |  4 |  66 | 17 | 0 | 1 | 0 | 0 |
| jetty-security | 7 | 58 | 22 | 0 | 0 | 0 | 0 |
| jetty-server | 56 | 1156 | 116 | 0 | 1 | 6 | 0 |
| jetty-servlet | 15 | 249 | 31 | 0 | 0 | 2 | 0 |
| jetty-util  | 37 | 449 | 79 | 0 | 2 | 0 | 0 |
| JLargeArrays  | 4 | 164 | 35 | 0 | 0 | 0 | 0 |
| jline | 17 | 221 | 143 | 0  | 1 | 1 | 0 |
| jsoup | 12 | 321 | 73 | 0 | 1 | 14 | 0 |
| JTransforms | 2 | 29 | 5 | 0 | 0 | 1 | 0 |
| junit-platform-commons | 5 | 37 | 8 | 4 | 0 | 0 | 0 |
| junit | 7 | 161 | 142 | 0 | 1 | 0 | 0 |
| log4j | 4 | 72 | 72 | 0 | 0 | 0 | 0 |
| mail | 28 | 475 | 235 | 0 | 4 | 0 | 0 |
| mapdb | 51 | 762 | 171 | 0 | 25 | 0 | 0 |
| nanohttpd | 10 | 76 | 54 | 0 | 0 | 1 | 0 |
| netty-all | 118 | 1747 | 265 | 0 | 41 | 0 | 0 |
| portlet-api | 5 | 64 | 8 | 0 | 0 | 0 | 0 |
| protobuf-java | 118 | 2544 | 572 | 0 | 58 | 0 | 0 |
| servlet-api | 36 | 418 | 18 | 0 | 0 | 1 | 0 |
| slf4j-api | 8 | 98 | 81 | 0 | 0 | 0 | 0 |
| slf4j-ext | 3 | 71 | 14 | 0 | 0 | 0 | 0 |
| spark-core | 20 | 411 | 163 | 0 | 1 | 0 | 0 |
| spring-aop | 7 | 6 | 0 | 0 | 0 | 0 | 0 |
| spring-beans | 7 | 28 | 0 | 0 | 0 | 0 | 0 |
| spring-boot | 5 | 52 | 27 | 0  | 0 | 1 | 0 |
| spring-context | 12 | 46 | 3 | 0 | 0 | 0 | 0 |
| spring-core | 10 | 65 | 8 | 0 | 0 | 0 | 0 |
| spring-security-config | 37 | 308 | 30 | 0 | 0 | 3 | 0  |
| spring-security-core | 45 | 213 | 55 | 0 | 0 | 1 | 0 |
| spring-security-crypto | 1  | 5 | 5 | 0 | 0 | 0 | 0 |
| spring-security-web | 6 | 8 | 0 | 0 | 0 | 0 | 0 |
| spring-web | 10 | 107 | 48 | 0 | 0 | 24 | 0 |
| vaadin-server | 191 | 2365 | 544 | 1 | 23 | 18 | 0 |
