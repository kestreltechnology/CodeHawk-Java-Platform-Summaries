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
"""Integrates api, profiled, and supplement jdk summaries."""

import argparse
import os
import subprocess
import time

from contextlib import contextmanager

import FileEnvironment as F

# The following classes are not documented in the Oracle API, and therefore
# not included in the jdk-api, but all taint transfer information has been
# added to the supplement class, to ensure soundness wrt taint transfer.
validated_platform_classnames = [
    "java.util.concurrent.ConcurrentHashMap$CollectionView",
    "java.util.concurrent.ConcurrentHashMap$EntrySetView",
    "java.util.concurrent.ConcurrentHashMap$KeySetView",
    "java.util.concurrent.ConcurrentHashMap$Node",
    "java.util.concurrent.ConcurrentHashMap$ValuesView",
    "java.util.HashMap$EntrySet",
    "java.util.HashMap$EntrySpliterator",
    "java.util.HashMap$HashMapSpliterator",
    "java.util.HashMap$KeySet",
    "java.util.HashMap$KeySpliterator",
    "java.util.HashMap$Node",
    "java.util.HashMap$TreeNode",
    "java.util.HashMap$Values",
    "java.util.HashMap$ValueSpliterator",
    "java.util.TreeMap$Entry",
    "java.util.TreeMap$KeySet",
    "java.util.WeakHashMap$Entry"
    ]

def parse():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--platform',
                            help='name of platform', default='ref_8.0_121')
    args = parser.parse_args()
    return args

@contextmanager
def timing(activity):
    t0 = time.time()
    yield
    print('\n' + ('=' * 80) + 
          '\nCompleted ' + activity + ' in ' + str(time.time() - t0) + ' secs' +
          '\n' + ('=' * 80))

def changedir(d):
    try:
        F.check_directory(d)
        os.chdir(d)
    except F.CHJError as e:
        print(str(e.wrap()))
        exit(1)

if __name__ == '__main__':

    args = parse()

    try:
        fenv = F.PlatformEnvironment(args.platform)
        F.check_file(fenv.jdkapijar)
        F.check_file(fenv.refrtjar)
        F.check_file(fenv.refjcejar)
        F.check_file(fenv.refjssejar)
    except F.CHJError as e:
        print(str(e.wrap()))
        exit(1)

    # collect classnames from summaries present in api jar
    jartfcmd = [ 'jar', 'tf', fenv.jdkapijar ]
    result = subprocess.check_output(jartfcmd)
    apiresult = result.split('\n')

    # collect classnames from reference jars
    refresult = []
    for r in [ fenv.refrtjar, fenv.refjcejar, fenv.refjssejar ]:
        jartfcmd = [ 'jar', 'tf', r ]
        result = subprocess.check_output(jartfcmd)
        refresult.extend(result.split('\n'))

    apiclassnames = []
    for n in apiresult:
        if n.endswith('xml'):
            apiclassnames.append(n.replace('/','.')[:-4])

    refclassnames = []
    for n in refresult:
        if n.endswith('class'):
            refclassnames.append(n.replace('/','.')[:-6])

    # create jars for the profiled and supplemented classes
    changedir(fenv.jdksumprofiled)
    cmd = [ 'jar', 'cfm', fenv.jdksumprofiledjar, fenv.manifestfile, '-C', fenv.jdksumprofiled ]
    cmd.extend(fenv.jdkprofileddirs)
    result = subprocess.call(cmd,cwd=fenv.jdksumprofiled,stderr=subprocess.STDOUT)
    if not (result == 0):
        print('*' * 80)
        print('Error in creating profiled jdk summary jar')
        print('*' * 80)
        exit(1)

    changedir(fenv.jdksumsupplement)
    cmd = [ 'jar', 'cfm', fenv.jdksumsupplementjar, fenv.manifestfile, '-C', fenv.jdksumsupplement ]
    cmd.extend(fenv.jdksupplementdirs)
    result = subprocess.call(cmd,cwd=fenv.jdksumsupplement,stderr=subprocess.STDOUT)
    if not (result == 0):
        print('*' * 80)
        print('Error in creating supplement jdk summary jar')
        print('*' * 80)
        exit(1)

    # collect classnames from summaries present in profiled jar
    jartfcmd = [ 'jar', 'tf', fenv.jdksumprofiledjar ]
    result = subprocess.check_output(jartfcmd)
    profiledresult = result.split('\n')

    # collect classnames from summaries present in supplement jar
    jartfcmd = [ 'jar', 'tf', fenv.jdksumsupplementjar ]
    result = subprocess.check_output(jartfcmd)
    supplementresult = result.split('\n')

    profiledclassnames = []
    for n in profiledresult:
        if n.endswith('xml'):
            profiledclassnames.append(n.replace('/','.')[:-4])

    supplementclassnames = []
    for n in supplementresult:
        if n.endswith('xml'):
            supplementclassnames.append(n.replace('/','.')[:-4])

    allclassnames = apiclassnames + validated_platform_classnames

    # call CodeHawk on each api summary to combine the three summaries into one
    basecmd = [ fenv.integratecmd,
                '-apis', fenv.jdkapijar,
                '-profiles', fenv.jdksumprofiledjar,
                '-supplements', fenv.jdksumsupplementjar,
                '-classpath', fenv.refrtjar,
                '-classpath', fenv.refjcejar,
                '-classpath', fenv.refjssejar ]

    if not os.path.isdir(fenv.jdksumintegrated):
        os.makedirs(fenv.jdksumintegrated)
    os.chdir(fenv.jdksumintegrated)

    with timing('processing ' + str(len(apiclassnames)) + ' jdk class files'):
        for classname in allclassnames:
            if not classname in refclassnames: continue
            cmd = basecmd + [ classname ]
            targetdir = fenv.get_target_dir(classname)
            targetname = fenv.get_target_name(classname)

            result = subprocess.call(cmd,cwd=fenv.jdksumintegrated,stderr=subprocess.STDOUT)
            if result == 0:
                if not os.path.isdir(targetdir):
                    os.makedirs(targetdir)
                os.rename(targetname,os.path.join(targetdir,targetname))
            else:
                print('*' * 80)
                print('Error in creating integrated summary for ' + classname)
                print('*' * 80)
                exit(1)

    # jar up the newly integrated summaries
    os.chdir(fenv.jdksumintegrated)
    cmd = [ 'jar', 'cfm', fenv.jdksumjar, fenv.manifestfile, '-C', fenv.jdksumintegrated ]
    cmd.extend(fenv.jdkdirs)
    result = subprocess.call(cmd, cwd=fenv.jdksumintegrated, stderr=subprocess.STDOUT)
    if not (result == 0):
        print('*' * 80)
        print('Error in creating jdk summary jar')
        print('*' * 80)
        exit(1)

    print('=' * 80)
    print('Successfully created ' + fenv.jdksumjar)
    print('=' * 80)

    print('\nClasses not included:')
    for name in profiledclassnames:
        if not name in allclassnames:
            print('  Profiled: ' + name)
    for name in supplementclassnames:
        if not name in allclassnames:
            print('  Supplement: ' + name)

