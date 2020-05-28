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
import json
import os
import subprocess

import FileEnvironment as F

def parse():
    parser = argparse.ArgumentParser()
    parser.add_argument('libname',help='name of library')
    args = parser.parse_args()
    return args

if __name__ == '__main__':

    args = parse()

    fenv = F.PlatformEnvironment('ref_8.0_121')

    try:
        libdir = fenv.get_lib_api_dir(args.libname)
        refjar = fenv.get_api_refjar(args.libname)
        print('refjar: ' + refjar)
        sumjarname = os.path.join(libdir,args.libname + '-api.jar')
        jardirs = fenv.get_api_directories(args.libname)
    except F.CHJError as e:
        print(str(e.wrap()))
        exit(1)

    os.chdir(libdir)
    jarcmd = [ 'jar', 'cf', os.path.basename(sumjarname) ]
    jarcmd.extend(jardirs)
    print(str(jarcmd))
    result = subprocess.check_call(jarcmd,cwd=libdir,stderr=subprocess.STDOUT)

    if os.path.isfile(sumjarname):

        cmd = [ fenv.inspectcmd,
                    '-classpath', fenv.refrtjar,
                    '-classpath', fenv.refjcejar,
                    '-classpath', fenv.refjssejar,
                    '-classpath', refjar ]

        cmd.append('-showimmutable')
        cmd.append('-showinvalid')
        cmd.append('-showranges')
        cmd.append('-showstringsinks')
        cmd.append(sumjarname)

        result = subprocess.call(cmd, cwd=fenv.thisdir, stderr=subprocess.STDOUT)

        jartfcmd = [ 'jar', 'tf', sumjarname ]
        result = subprocess.check_output(jartfcmd)
        result = result.split('\n')

        classnames = []
        for n in result:
            if n.endswith('xml'):
                classnames.append(n.replace('/','.')[:-4])

        print('\n\n#### Classes included')
        for c in classnames:
            print('- '  + c)
            

    

    
