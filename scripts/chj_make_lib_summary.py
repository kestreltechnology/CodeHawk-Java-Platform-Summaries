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
"""Create a new summary template for a third-party library class."""

import argparse
import json
import os
import subprocess

import FileEnvironment as F

def parse():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('libversion',help='name of library version')
    parser.add_argument('classname',help='fully qualified classname')
    parser.add_argument('--legacysummaries',
                            help='use legacy summaries as starting point')
    parser.add_argument('--move',action='store_true',
                            help='move profile/supplement template file to target directory')
    parser.add_argument('--apimove',action='store_true',
                            help='move api template file to target directory')
    parser.add_argument('--api',action='store_true',
                            help='only create api template')
    parser.add_argument('--profile',action='store_true',
                            help='only create profile template')
    parser.add_argument('--supplement',action='store_true',
                            help='only create supplement template')
    args = parser.parse_args()
    return args

if __name__ == '__main__':

    args = parse()

    fenv = F.FileEnvironment()
    libname = fenv.get_libname(args.libversion + '.jar')

    fenv = F.PlatformLibFileEnvironment('ref_8.0_121',libname,args.libversion)

    allsummaries = not (args.api or args.profile or args.supplement)

    basecmd = [ fenv.templatecmd,
                '-classpath', fenv.refrtjar,
                '-classpath', fenv.refjcejar,
                '-classpath', fenv.refjssejar ]

    if not args.legacysummaries is None:
        basecmd.extend([ '-summaries', os.path.abspath(args.legacysummaries) ])

    targetdir = fenv.get_target_dir(args.classname)
    targetname = fenv.get_target_name(args.classname)
        
    deps = fenv.get_dependencies(fenv.refjar)

    basecmd.extend([ '-classpath', fenv.refjar ])
    for d in deps:
        basecmd.extend([ '-classpath', d ])

    if allsummaries or args.api:
        apidir = fenv.get_lib_api_dir(libname)
        if not os.path.isdir(apidir):
            os.makedirs(apidir)
        os.chdir(apidir)

        apicmd = basecmd[:]
        apicmd.extend([ '-api', args.classname ])
        result = subprocess.call(apicmd, cwd=fenv.apidir, stderr=subprocess.STDOUT)
        if result == 0:
            tgtfilename = os.path.join(targetdir,targetname)
            if args.apimove or (not os.path.isfile(tgtfilename)):
                if not os.path.isdir(targetdir):
                    os.makedirs(targetdir)
                os.rename(targetname,os.path.join(targetdir,targetname))
        else:
            print('*' * 80)
            print('Error in creating api summary')
            print('*' * 80)
            exit(1)

    if allsummaries or args.profile:
        if not os.path.isdir(fenv.libsumprofiledir):
            os.makedirs(fenv.libsumprofiledir)
        os.chdir(fenv.libsumprofiledir)

        profilecmd = basecmd[:]
        profilecmd.extend([ '-profile', args.classname ])
        result = subprocess.call(profilecmd, cwd=fenv.libsumprofiledir, stderr=subprocess.STDOUT)
        if  result == 0:
            tgtfilename = os.path.join(targetdir,targetname)            
            if args.move or (not os.path.isfile(tgtfilename)):
                if not os.path.isdir(targetdir):
                    os.makedirs(targetdir)
                os.rename(targetname,os.path.join(targetdir,targetname))
        else:
            print('*' * 80)
            print('Error in creating profile summary')
            print('*' * 80)
            exit(1)

    if allsummaries or args.supplement:
        if not os.path.isdir(fenv.libsumsupplementdir):
            os.makedirs(fenv.libsumsupplementdir)
        os.chdir(fenv.libsumsupplementdir)

        supplementcmd = basecmd[:]
        supplementcmd.extend([ '-supplement', args.classname ])
        result = subprocess.call(supplementcmd, cwd=fenv.libsumsupplementdir, stderr=subprocess.STDOUT)
        if result == 0:
            tgtfilename = os.path.join(targetdir,targetname)                                        
            if args.move or (not os.path.isfile(tgtfilename)):
                if not os.path.isdir(targetdir):
                    os.makedirs(targetdir)
                os.rename(targetname,os.path.join(targetdir,targetname))
        else:
            print('*' * 80)
            print('Error in creating supplement summary')
            print('*' * 80)
    
                    
