# ------------------------------------------------------------------------------
# CodeHawk Java Analyzer
# Author: Henny Sipma
# ------------------------------------------------------------------------------
# The MIT License (MIT)
#
# Copyright (c) 2016-2020 Kestrel Technology LLC
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
# 
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.
# ------------------------------------------------------------------------------

import argparse
import os
import subprocess

import FileEnvironment as F

def parse():
    parser = argparse.ArgumentParser()
    parser.add_argument('version',choices=['profiled','supplement','integrated'],
                            help='version to be inspected')
    parser.add_argument('--platform',help='name of platform',default='ref_8.0_121')
    args = parser.parse_args()
    return args

def inspect(jardir,jarfile):

    if os.path.isdir(jardir) and os.path.isfile(jarfile):

        cmd = [ fenv.inspectcmd,
                    '-classpath', fenv.refrtjar,
                    '-classpath', fenv.refjcejar,
                    '-classpath', fenv.refjssejar ]

        cmd.append(jarfile)

        result = subprocess.call(cmd, cwd=jardir,stderr=subprocess.STDOUT)

    else:
        print('*' * 80)
        print('Error in jar directory or jarfile')
        print('*' * 80)
        exit(1)

if __name__ == '__main__':

    args = parse()

    fenv = F.PlatformEnvironment(args.platform)

    profileddir = fenv.jdksumprofiled
    supplementdir = fenv.jdksumsupplement
    integrateddir = fenv.jdksumintegrated

    if args.version == 'profiled':
    
        try:
            F.check_directory(profileddir)
            os.chdir(profileddir)
        except F.CHJError as e:
            print(str(e.wrap()))
            exit(1)

        cmd = [ 'jar', 'cfm', 'jdk-profiled.jar', fenv.manifestfile, 'java', 'org' ]

        result = subprocess.check_call(cmd,cwd=profileddir,stderr=subprocess.STDOUT)
        if not (result == 0):
            print('*' * 80)
            print('Error in creating jdk-profiled jar')
            print('*' * 80)
            exit(1)

        if os.path.isfile('jdk-profiled.jar'):

            inspect(profileddir,'jdk-profiled.jar')


    elif args.version == 'supplement':
        try:
            F.check_directory(supplementdir)
            os.chdir(supplementdir)
        except F.CHJError as e:
            print(str(e.wrap()))
            exit(1)

        cmd = [ 'jar', 'cfm', 'jdk-supplement.jar', fenv.manifestfile, 'java' ]

        result = subprocess.check_call(cmd,cwd=supplementdir,stderr=subprocess.STDOUT)
        if not (result == 0):
            print('*' * 80)
            print('Error in creating jdk-supplement jar')
            print('*' * 80)
            exit(1)

        if os.path.isfile('jdk-supplement.jar'):

            inspect(supplementdir,'jdk-supplement.jar')

    elif args.version == 'integrated':
    
        try:
            F.check_directory(integrateddir)
            os.chdir(integrateddir)
        except F.CHJError as e:
            print(str(e.wrap()))
            exit(1)

        cmd = [ 'jar', 'cfm', 'jdk.jar', fenv.manifestfile ]
        cmd.extend(fenv.jdkdirs)

        result = subprocess.check_call(cmd,cwd=integrateddir,stderr=subprocess.STDOUT)
        if not (result == 0):
            print('*' * 80)
            print('Error in creating jdk jar')
            print('*' * 80)
            exit(1)

        if os.path.isfile('jdk.jar'):

            inspect(integrateddir,'jdk.jar')
