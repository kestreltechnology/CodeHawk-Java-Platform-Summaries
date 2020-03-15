import argparse
import os
import subprocess
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
"""Creates jars for the api, profile, and supplement summaries for a third-party library."""

import argparse
import FileEnvironment as F

def parse():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('libname',help='name of library')
    parser.add_argument('libversion',help='name of library version')
    parser.add_argument('--api',action='store_true',
                            help='only create api jar')
    parser.add_argument('--profile',action='store_true',
                            help='only create profile jar')
    parser.add_argument('--supplement',action='store_true',
                            help='only create supplement jar')
    parser.add_argument('--integrate',action='store_true',
                            help='only create integrated summary jar')
    args = parser.parse_args()
    return args

if __name__ == '__main__':

    args = parse()

    fenv = F.PlatformLibFileEnvironment('ref_8.0_121',args.libname,args.libversion)

    allsupport = not (args.integrate or args.api or args.supplement or args.profile)

    directories = fenv.get_directories(fenv.refjar)

    if allsupport or args.api:
        apidir = fenv.get_lib_api_dir(args.libname)
        os.chdir(apidir)
        cmd = [ 'jar', 'cfm', fenv.apijar, fenv.manifestfile, '-C', apidir ]
        cmd.extend(directories)
        result = subprocess.call(cmd, cwd=fenv.apidir, stderr=subprocess.STDOUT)
        if not (result == 0):
            print('*' * 80)
            print('Error in creating api jar')
            print('*' * 80)
            exit(1)

    if allsupport or args.profile:

        os.chdir(fenv.libsumprofiledir)
        cmd = [ 'jar', 'cfm', fenv.libsumprofilejar, fenv.manifestfile, '-C', fenv.libsumprofiledir ]
        cmd.extend(directories)
        result = subprocess.call(cmd, cwd=fenv.libsumprofiledir, stderr=subprocess.STDOUT)
        if not (result == 0):
            print('*' * 80)
            print('Error in creating profile jar')
            print('*' * 80)
            exit(1)

    if allsupport or args.supplement:

        os.chdir(fenv.libsumsupplementdir)
        cmd = [ 'jar', 'cfm', fenv.libsumsupplementjar, fenv.manifestfile, '-C', fenv.libsumsupplementdir ]
        cmd.extend(directories)
        result = subprocess.call(cmd, cwd=fenv.libsumsupplementdir, stderr=subprocess.STDOUT)
        if not (result == 0):
            print('*' * 80)
            print('Error in creating supplement jar')
            print('*' * 80)
            exit(1)

    if args.integrate:

        os.chdir(fenv.libsumintegrateddir)
        cmd = [ 'jar', 'cfm', fenv.libsumjar, fenv.manifestfile, '-C', fenv.libsumintegrateddir ]
        cmd.extend(directories)
        print(' '.join(cmd))
        result = subprocess.call(cmd, cwd=fenv.libsumintegrateddir, stderr=subprocess.STDOUT)
        if not (result == 0):
            print('*' * 80)
            print('Error in creating integrated jar')
            print(;*' * 80)
            exit(1)
