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
"""Integrates api, profiled, and supplement library summaries."""

import argparse
import os
import subprocess

import FileEnvironment as F

def parse():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('libname',help='name of library')
    parser.add_argument('libversion',help='name of library version')
    args = parser.parse_args()
    return args

if __name__ == '__main__':

    args = parse()

    fenv = F.PlatformLibFileEnvironment('ref_8.0_121',args.libname,args.libversion)
    
    deps = fenv.get_dependencies(fenv.refjar)

    # collect classnames from summaries present in api jar    
    jartfcmd = [ 'jar', 'tf', fenv.apijar ]
    result = subprocess.check_output(jartfcmd)
    result = result.split('\n')

    classnames = []
    for n in result:
        if n.endswith('xml'):
            classnames.append(n.replace('/','.')[:-4])

    # call CodeHawk on each api summary to combine the three summaries into one
    basecmd = [ fenv.integratecmd,
                '-apis', fenv.apijar,
                '-profiles', fenv.libsumprofilejar,
                '-supplements', fenv.libsumsupplementjar,
                '-classpath', fenv.refjar,
                '-classpath', fenv.refrtjar,
                '-classpath', fenv.refjcejar,
                '-classpath', fenv.refjssejar ]
        
    for d in deps:
        cmd.extend([ '-classpath', d ])

    if not os.path.isdir(fenv.libsumintegrateddir):
        os.makedirs(fenv.libsumintegrateddir)
    os.chdir(fenv.libsumintegrateddir)

    for classname in classnames:
        cmd = basecmd + [ classname ]
        targetdir = fenv.get_target_dir(classname)
        targetname = fenv.get_target_name(classname)

        result = subprocess.call(cmd, cwd=fenv.libsumintegrateddir, stderr=subprocess.STDOUT)
        if result == 0:
            if not os.path.isdir(targetdir):
                os.makedirs(targetdir)
            os.rename(targetname,os.path.join(targetdir,targetname))
        else:
            print('*' * 80)
            print('Error in creating integrated summary for ' + classname)
            print('*' * 80)
            exit(1)

    # jar up the newly created integrated summaries
    directories = fenv.get_directories(fenv.refjar)
            
    os.chdir(fenv.libsumintegrateddir)
    cmd = [ 'jar', 'cfm', fenv.libsumjar, fenv.manifestfile, '-C', fenv.libsumintegrateddir ]
    cmd.extend(directories)
    print(' '.join(cmd))
    result = subprocess.call(cmd, cwd=fenv.libsumintegrateddir, stderr=subprocess.STDOUT)
    if not (result == 0):
        print('*' * 80)
        print('Error in creating integrated jar')
        print('*' * 80)
        exit(1)
