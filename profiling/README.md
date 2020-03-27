# Profiling of Bytecode Instruction and Library Functions

## Scripts to profile bytecodes and library methods

### Overview
- [profile_bytecode.py](ByteCode_Timing/profile_bytecode.py)
- [profile_lib_method.py](Library_Timing/profile_lib_method.py)

### Scripts

#### profile\_bytecode
Executes a very small method designed to exercise a particular bytecode many times, and then uses some logic to separate the execution time of that bytecode from surrounding bytecodes. This is only intended to determine the execution time of a bytecode without JIT enabled.

- positional arguments:
  - *repeats*: Number of times to execute the loop that exercises a particular bytecode. 10,000 is typical.
  - *bytecode*: Name of the java bytecode to examine.    Example : iload_1

#### profile\_lib\_method
Executes calls to a method with given arguments in a small loop. This is only intended to determine the execution time of a method without JIT enabled.

- positional arguments:
  - *repeats*: Number of times to execute the loop that exercises a method. 10,000 is typical.
  - *classname*: Fully qualified classname of the class containing the method of interest. Example : java.lang.StringBuilder
  - *method*: Name of the method of interest. Because methods may have the same name with different arguments, arguments are appended to the method name as an underscore separated list of types. Example : append_chararray_int_int which represents the StringBuilder method append(char[] a, int b, int y).

Note that keyword argument are prefixed with @ instead of the normal -. This is so negative numbers can be passed as values.

- keyword argument:
  - *@@num_values*: List of numerical values to be used in profiling. How these are used depends on the method.
  - *@@str_values*: List of string values to be used in profiling. How these are used depends on the method.
  - *@@output_file*: Name of output file to write profiling results to.
  - *@@outer_loops*: Number of times to reenter the profiling method. This can be used to test if/how caching impacts the method execution time.
