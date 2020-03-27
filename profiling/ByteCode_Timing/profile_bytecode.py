import os
import subprocess
import argparse
import sys

def parse():
    parser = argparse.ArgumentParser()
    parser.add_argument('repeats',help='Number of iterations to run')
    parser.add_argument('bytecode',help='ByteCode of interest')
    parser.add_argument('--values',nargs='*',default=[],help='Input arguments to pass to the method')
    args = parser.parse_args()
    return args

if __name__ == '__main__':
    args = parse()

    #-Xmx512m
    java_cmd = [ 'java', '-Xmx128m', '-Xint', '-classpath', '../lib/commons-cli-1.3.jar:bin/', 'ByteCode_Timing/ByteCode_Timing' ]
    java_cmd.extend(['-r', args.repeats])
    java_cmd.extend(['-b', args.bytecode])
    for value in args.values:
        java_cmd.extend(['-v', value])

    print java_cmd
    subprocess.call(java_cmd)
