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
"""Generates a template jdk summary class for profiled or supplement info."""

import argparse
import os
import subprocess

import FileEnvironment as F

def parse():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('classname',help='fully qualitified classname')
    parser.add_argument('--platform',help='name of platform',default='ref_8.0_121')
    parser.add_argument('--move',action='store_true',
                            help='move summary file to target directory')
    parser.add_argument('--profile',action='store_true',
                            help='only create profile summary')
    parser.add_argument('--supplement',action='store_true',
                            help='only create supplement summary')
    args = parser.parse_args()
    return args

if __name__ == '__main__':

    args = parse()

    fenv = F.PlatformEnvironment(args.platform)

    allsummaries = not (args.profile or args.supplement)

    basecmd = [ fenv.templatecmd , '-summaries', fenv.jdkapijar,
                '-classpath', fenv.refrtjar,
                '-classpath', fenv.refjcejar,
                '-classpath', fenv.refjssejar ]

    targetdir = fenv.get_target_dir(args.classname)
    targetname = fenv.get_target_name(args.classname)
    
    if allsummaries or args.profile:
        os.chdir(fenv.jdksumprofiled)

        profilecmd = basecmd[:]
        profilecmd.extend([ '-profile', args.classname ])
        result = subprocess.call(profilecmd, cwd=fenv.jdksumprofiled, stderr=subprocess.STDOUT)
        if result == 0:
            if args.move:
                if not os.path.isdir(targetdir):
                    os.makedirs(targetdir)
                os.rename(targetname,os.path.join(targetdir,targetname))
        else:
            print('Error in creating profile summary')
            
    if allsummaries or args.supplement:
        os.chdir(fenv.jdksumsupplement)

        suppcmd = basecmd[:]
        suppcmd.extend([ '-supplement', args.classname ])
        result = subprocess.call(suppcmd, cwd=fenv.jdksumsupplement, stderr=subprocess.STDOUT)
        if result == 0:
            if args.move:
                if not os.path.isdir(targetdir):
                    os.makedirs(targetdir)
                os.rename(targetname,os.path.join(targetdir,targetname))
        else:
            print('Error in creating supplement summary')
    
    
    
    
