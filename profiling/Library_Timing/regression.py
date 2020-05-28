import os
import subprocess
import argparse
import sys

def parse():
    parser = argparse.ArgumentParser()
    parser.add_argument('--input_file',default=None,help='Input file to parse')
    args = parser.parse_args()
    return args

if __name__ == '__main__':
    args = parse()

    java_cmd = [ 'java', '-classpath', '../lib/commons-math3-3.6.1.jar:bin/', 'Java_Timing/Multiple_Regression' ]
    if args.input_file == None:
        sys.exit()
    else:
        java_cmd.append(args.input_file)

    print(java_cmd)
    subprocess.call(java_cmd)

