# Profiling
## Approach
We take similar approaches to profiling both library methods and bytecode based on collecting the 
execution time of many executions of the object of interest and taking the average 
of these times as our result. We use slightly different methods for each type of object, as
explained below.

### Library Methods 
For each library method we wish to profile we produce a custom profiling method. This method must be 
able to call the library method repeatedly, collect the execution time of each run, and pass
arguments to the method as needed. The average execution time is taken as a basis for further
analysis.

We use System.nanoTime to get a point in time. In the case of library methods we do the obvious
thing and make calls to System.nanoTime immediately before and after executing the method.
It is important to recognize that a call to System.nanoTime actually takes places over a range of time
and returns some time from within this range. To attempt to account for this variability we profile a separate
loop containing only two immediately sequential calls to System.nanoTime. This value is subtracted
from the total value obtained by the profiling method, eliminating the portion of the measured time
that bookends the library call of interest but is not part of it.

While in general it is not possible to generate a profiling method automatically, we provide utilities
to generate both the skeleton of a profiling method and the glue code needed to integrate the profiling
method with existing methods. With a little experience profiling methods and information can be
generated quite quickly.

We also have facilities to automatically profile a method against many different inputs, in either
one or more variables, collect the outputs, and perform a regression over the outputs to
establish an execution time as a linear expression of the method's inputs.

### Bytecode
We used similar methods to profile bytecodes but take additional care but must take additional care
to account for the extremely short execution time of most bytecode.
We write a profiling method similar to those used for library methods, but in this case call
System.nanoTime only twice in the exterior of the loop. Otherwise, the timing characteristics
of the calls to System.nanoTime itself has the potential to obscure the execution time of the bytecode.

We measure the entire execution of the loop, including both the control flow and body of the loop.
To account for the contribution of the loop's control flow to the timing of the bytecode, we separately measure
a loop entirely devoid of instructions, and subtract the execution time of this loop from the execution time
of our profiling loop.
    
Finally, the portion of the loop contained within the timing measurement may contain additional bytecodes,
either by necessity or as an artifact of the code chosen to exericse the bytecode.
In this case we do a separate analysis of the dependent bytecode when possible and subtract this time
from the total. If separating the bytecodes is not possible we use a different strategy to determine
an exact timing, as described below.

## Challenges
### Dependent Opcodes
In general it is not possible to isolate each bytecode from all others for observation, some 
pairs of bytecodes always appear together. In these cases it is not possible to precisely determine a timing
for each bytecode by our methods.

These pairs are as follows:
Bytecodes that load from a local variable and store to a local variable. Example: lload and lstore
Bytecodes that push a constant onto the stack  and store to a local variable. Example: lconst_0 and lstore
Bytecodes that invoke a method and return a value Example: invokestatic and areturn

In the first two cases we rather lazily split the execution time of the pair of bytecodes and assign half to each.
in all cases, this results in an execution time of about 2ns for each bytecode. As the bytecodes always appear 
together this should have no impact on the computed execution time of a method containing them.

In the final case we notice two useful subcases. 
First, the execution time of an invoke instruction and the return instruction (which returns void) shows an execution
time of at least 12ns.
Otherwise, an invoke instruction and any other return instruction (areturn, dreturn, freturn, ireturn, lreturn) shows
an execution time of at least 160ns. 
We therefore take invoke instructions to have an execution time of at least 6ns and return
instructions to have an execution time of either 6ns or 154ns. In all such tests our invoke instructions are calling
an extremely small method, so we take 6ns to be only the lower bound on such instructions. It would be useful in the
future to measure invoke instructions in more detail and within more environments.

## Results
For tables of the profiling results, please see the following:

[ Bytecode ]( Opcode_Timing_Table.md )

[ Library Methods ]( Library_Timing_Table.md )

### Confidence in results
Low, the way in which we profiled both bytecode instructions and library methods ignores any influence by the 
structure of the code or nearby bytecodes. In particular all of our profiling methods contain a small loop and the
execution of the contents of such a loop may differ from the execution of the same contents in other environments.

### Recommendations
#### Testing against real world code
To verify the validity of our profiling results we need to measure predicted values against real world examples and make
adjustments to the profiling process accordingly.

#### Automatic regeneration of timings
We could speed up the profiling process greatly by adding the ability to regenerate timings and integrate them 
with the cost analysis automatically. This would aid in the speed with which can expand the set of
profiled library methods as well as allowing us to extend our cost analysis, for both library methods and bytecodes,
to systems other than the STAC reference platform.

#### Extended expressions
While the majority of library methods are fairly simple and amenable to being automatically modeled as either constant
or linear expressions, in general they may be anything at all. While we already have the facility to establish linear
expressions measuring the execution time of a library method, this strategy is not accurate for methods with unusual
timing profiles. It would be useful to extend our existing capabilities to model these profiles.

