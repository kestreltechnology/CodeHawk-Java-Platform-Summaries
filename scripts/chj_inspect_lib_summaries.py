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
    parser.add_argument('libversion',help='name of library version')
    args = parser.parse_args()
    return args

if __name__ == '__main__':

    args = parse()

    try:
        fenv = F.FileEnvironment()        
        libname = fenv.get_libname(args.libversion + '.jar')
        fenv = F.PlatformLibFileEnvironment('ref_8.0_121',libname,args.libversion)
        exportdir = os.path.join(fenv.exportdir,fenv.get_export(fenv.refjar))
        chlibjar = os.path.join(exportdir,os.path.basename(fenv.libsumjar))
    except F.CHJError as e:
        print(str(e.wrap()))
        exit(1)
    
    if os.path.isfile(chlibjar):

        cmd = [ fenv.inspectcmd,
                    '-classpath', fenv.refrtjar,
                    '-classpath', fenv.refjcejar,
                    '-classpath', fenv.refjssejar,
                    '-classpath', fenv.refjar ]

        try:
            deps = fenv.get_dependencies(fenv.refjar)
        except F.CHJError as e:
            print(str(e.wrap()))
            exit(1)

        for d in deps:
            cmd.extend([ '-classpath', d ])

        cmd.append(chlibjar)
        print('Inspecting ' + chlibjar)
        
        result = subprocess.call(cmd, cwd=fenv.thisdir, stderr=subprocess.STDOUT)
        if not (result == 0):
            print('Error in inspecting jar file')
            exit(1)
                    
    else:
        print('*' * 80)
        print(chlibjar + " not found!")
        print('*' * 80)
        exit(1)
