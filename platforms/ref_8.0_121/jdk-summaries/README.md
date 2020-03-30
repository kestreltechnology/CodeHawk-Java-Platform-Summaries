## Platform-dependent JDK library method summaries

The platform-dependent JDK library method summaries provide estimates
for running time of methods. Two sets of summaries are provided, one
with time estimates based on profiling these methods directly on the
reference platform, and one based on costmodels created from the
time-costs of individual instructions (which themselves were, again,
obtained by profiling on the reference platform).

### Profiled

| package | classes | methods | constant | range | linear expression |
| :--- | ---: | ---: | ---: | ---: | ---: |
| java.awt.image | 1 | 1 | 1 | 0 | 0 |
| java.io | 4  | 36 | 34 | 0 | 2 |
| java.lang | 16 | 93 | 89 | 0 | 4 |
| java.math | 3 | 32 | 32 | 0 | 1 |
| java.net | 1 | 1 | 1 | 0 | 0 |
| java.time | 1 | 2 | 2  | 0 | 0 |
| java.util | 8 | 40 | 37 | 0 | 3 |
| java.util.logging | 1 | 1 | 1 | 0 | 0 |
| org.w3c.dom | 1 | 2 | 2 | 0 | 0 |


### Computed with CodeHawk cost model
| package | classes | methods | constant | range | linear expression |
| :--- | ---: | ---: | ---: | ---: | ---: |
| java.awt | 2 | 25 | 9 | 16 | 0 |
| java.awt.image | 2 | 30  | 30 | 0 | 0 |
| java.lang | 14 | 113 | 78 | 35 | 0 |
| java.lang.invoke | 1 | 5 | 5 | 0 | 0 |
| java.util | 26 | 81 | 46 | 35 | 0 |
| java.util.concurrent | 8 | 21 | 17 | 4 | 0 |
| java.util.concurrent.atomic | 1 | 8 | 8 | 0 | 0 |
| java.util.concurrent.locks | 1 | 1 | 1 | 0 | 0 |
