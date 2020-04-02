## Third-party library summaries with time cost data

The table below shows the number of methods for which an estimated
CPU time cost was computed for a given library version, and the break down of
those costs in constant cost, a range of values, or a symbolic (linenar)
expression.

### Obtained by Profiling
- **apache commons libraries**
  | library jar | methods | constant | range | symbolic |
  | :--- | ---: |  ---: | ---: | ---: |
  | commons-math3-3.6.1 | 13 | 11 | 2 | 0 | 0 |


### Computed with CodeHawk Cost Model

- **apache commons libraries**
  | library jar | methods | constant | range | symbolic |
  | :--- | ---: |  ---: | ---: | ---: |
  | commons-cli-1.3 | 61 | 56 | 5  | 0  |
  | commons-fileupload-1.3.3 | 11 | 11 | 0 | 0 |
  | commons-io-2.2 | 7  | 3 | 4 | 0 |

- **nanohttpd**
  | library jar | methods | constant | range | symbolic |
  | :--- | ---: |  ---: | ---: | ---: |
  | nanohttpd-2.2.0 | 22 | 21  | 1 | 0 |

- **servlet-api**
  | library jar | methods | constant | range | symbolic |
  | :--- | ---: |  ---: | ---: | ---: |
  | javax.servlet-api-3.1.0 | 44 | 38 | 6 | 0 |

- **spark**
  | library jar | methods | constant | range | symbolic |
  | :--- | ---: |  ---: | ---: | ---: |
  | spark-core-2.6.0.RC0 | 48 | 24 | 24 | 0 |
