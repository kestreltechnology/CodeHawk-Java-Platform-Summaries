#!/usr/bin/python3

import os
import subprocess
import argparse
import sys

# So that we can use negative numbers as input arguments, we use @ instead of - as our argument prefix
#
# Therefore run : 
# > ./profile_lib_method.py @h
# to see a full help message

def parse():
    parser = argparse.ArgumentParser(prefix_chars='@')
    parser.add_argument('repeats',help='Number of times ot execute the method of interest')
    parser.add_argument('classname',help='Fully qualified classname of interest')
    parser.add_argument('method',help='Method of interest')
    parser.add_argument('@@num_values',nargs='*',default=[],help='Input arguments to pass to the method')
    parser.add_argument('@@str_values',nargs='*',default=[],help='Input argument to pass to the method')
    parser.add_argument('@@input_file',default=None,help='Input file to parse')
    parser.add_argument('@@output_file',default=None,help='Output file to write results')
    parser.add_argument('@@outer_loops',default=None,help='Outer loops to use')
    args = parser.parse_args()
    return args

if __name__ == '__main__':
    args = parse()

    java_cmd = [ 'java', '-Xmx128m', '-Xint', '-classpath', '../lib/commons-cli-1.3.jar:bin/', 'Java_Timing/Profiling_Controller' ]
    java_cmd.extend(['-r', args.repeats])
    java_cmd.extend(['-c', args.classname])
    java_cmd.extend(['-m', args.method])
    if args.input_file != None:
        java_cmd.extend(['-f', args.input_file])
    if args.output_file != None:
        java_cmd.extend(['-o', args.output_file])
    if args.outer_loops != None:
        java_cmd.extend(['-a', args.outer_loops])
    for value in args.num_values:
        java_cmd.extend(['-q', value])
    for value in args.str_values:
        java_cmd.extend(['-s', value])
    java_cmd.extend(['-v'])

    print(java_cmd)
    subprocess.call(java_cmd)
