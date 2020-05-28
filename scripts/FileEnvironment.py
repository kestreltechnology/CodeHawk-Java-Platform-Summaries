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
import json
import os

class CHError(Exception):

    def wrap(self):
        lines = []
        lines.append('*' * 80)
        lines.append(self.__str__())
        lines.append('*' * 80)
        return '\n'.join(lines)

class CHJError(CHError):

    def __init__(self,msg):
        CHError.__init__(self,msg)

class CHJFileNotFoundError(CHJError):

    def __init__(self,filename):
        CHJError.__init__(self,'File ' + filename + ' not found')
        self.filename =  filename

class CHJDirectoryNotFoundError(CHJError):

    def __init__(self,dirname):
        CHJError.__init__(self,'Directory ' + dirname + ' not found')
        self.dirname = dirname

class CHJProcessError(CHJError):

    def __init__(self,msg):
        CHJError.__init__(self,msg)

    def __str__(self):
        return 'Process error: ' + self.str()

class CHJJSONParseError(CHJError):

    def __init__(self,filename,e):
        CHJError.__init__(self,'JSON parse error')
        self.filename = filename
        self.valueerror = e

    def __str__(self):
        return ('JSON parse error in file: ' + self.filename + ': '
                    + str(self.valueerror))

def check_file(filename):
    if not os.path.isfile(filename):
        raise CHJFileNotFoundError(filename)

def check_directory(pathname):
    if not os.path.isdir(pathname):
        raise CHJDirectoryNotFoundError(pathname)

class FileEnvironment(object):

    def __init__(self):
        self.thisdir = os.path.dirname(os.path.abspath(__file__))
        self.repodir = os.path.dirname(self.thisdir)

        # jdk summaries
        self.jdkapidir = os.path.join(self.repodir,'jdk-api')
        self.jdkapijar = os.path.join(self.jdkapidir,'jdk-api.jar')
        self.jdkdirs = [ 'com', 'java', 'javax', 'org', 'sun' ]

        # third-party library summaries
        self.librefdir = os.path.join(self.repodir,'lib-reference')
        self.libapidir = os.path.join(self.repodir,'lib-api')
        self.dependenciesfile = os.path.join(self.thisdir,'dependencies.json')
        self.apidependenciesfile = os.path.join(self.thisdir,'api_dependencies.json')

        # platform-specific summaries
        self.platformsdir = os.path.join(self.repodir,'platforms')
                                             
        # CodeHawk executables to generate and integrate summaries
        self.bindir = os.path.join(self.repodir,'bin')
        self.templatecmd = os.path.join(self.bindir,'chj_template')
        self.integratecmd = os.path.join(self.bindir,'chj_integrate')
        self.inspectcmd = os.path.join(self.bindir,'chj_inspect')
        self.apidependencies = self._load_api_dependencies()
        self.dependencies = self._load_dependencies()
        self.manifestfile = os.path.join(self.thisdir,'Manifest.txt')

    def get_api_refjar(self,libname):
        if libname in self.apidependencies:
            refjar = self.apidependencies[libname]['refjar']
            refjar = os.path.join(self.librefdir,refjar)
            if os.path.isfile(refjar):
                return refjar
            else:
                msg = ('Api reference jar: ' + refjar
                           + ' not found in ' + self.librefdir)
                raise CHJError(msg)
        else:
            msg = 'No record for ' + libname + ' found in api_dependencies.json'
            raise CHJError(msg)

    def get_dependencies(self,jar):
        jarname = os.path.basename(jar)
        if jarname in self.dependencies:
            jarrec = self.dependencies[jarname]
            if 'deps' in jarrec:
                result = []
                for d in jarrec['deps']:
                    depjar = os.path.join(self.librefdir,d)
                    if not os.path.isfile(depjar):
                        msg = 'Dependent jar: ' + depjar + ' not found'
                        raise CHJError(msg)
                    result.append(depjar)
                return result
        return []

    def get_directories(self,jar):
        jarname = os.path.basename(jar)
        if jarname in self.dependencies:
            jarrec = self.dependencies[jarname]
            if 'dirs' in jarrec:
                return jarrec['dirs']
            else:
                msg = 'No directories specified for ' + jarname
                raise CHJError(msg)
        else:
            msg = 'No dependencies record found for '  + jarname + ' to get directories'
            raise CHJError(msg)

    def get_api_directories(self,libname):
        if libname in self.apidependencies:
            return self.apidependencies[libname]['dirs']
        else:
            msg = 'No dependencies record for ' + libname + ' to get api-directories'
            raise CHJError(msg)

    def get_libname(self,jarname):
        if jarname in self.dependencies:
            jarrec = self.dependencies[jarname]
            if 'path' in jarrec:
                return jarrec['path']
            else:
                msg = 'No path specified for ' + jarname + ' to get libname'
                raise CHJError(msg)
        else:
            msg = 'No dependencies record found for '  + jarname + ' to get libname'
            raise CHJError(msg)

    def get_export(self,jar):
        jarname = os.path.basename(jar)
        if jarname in self.dependencies:
            jarrec = self.dependencies[jarname]
            if 'export' in jarrec:
                return jarrec['export']
            else:
                msg = 'No export path specified for ' + jarname
                raise CHJError(msg)
        else:
            msg = 'No dependencies record found for '  + jarname + ' to get export'
            raise CHJError(msg)

    def get_lib_api_dir(self,libname):
        return os.path.join(self.libapidir,libname)

    def get_target_dir(self,classname):
        targetdir = classname.split('.')[:-1]
        return '/'.join(targetdir)

    def get_target_name(self,classname):
        return classname.split('.')[-1] + '.xml'

    def _load_dependencies(self):
        filename = self.dependenciesfile
        with open(filename,'r') as fp:
            try:
                deps = json.load(fp)
                return deps['libraries']
            except ValueError as e:
                raise CHJJSONParseError(filename,e)

    def _load_api_dependencies(self):
        filename = self.apidependenciesfile
        with open(filename,'r') as fp:
            try:
                deps = json.load(fp)
                return deps['libraries']
            except ValueError as e:
                raise CHJJSONParseError(filename,e)

class PlatformEnvironment(FileEnvironment):

    def __init__(self,name):
        FileEnvironment.__init__(self)
        self.name = name
        self.platformdir = os.path.join(self.platformsdir,name)

        self.jdkrefdir = os.path.join(self.platformdir,'jdk-reference')
        self.refrtjar = os.path.join(self.jdkrefdir,'rt.jar')
        self.refjcejar = os.path.join(self.jdkrefdir,'jce.jar')
        self.refjssejar = os.path.join(self.jdkrefdir,'jsse.jar')

        self.jdksumdir = os.path.join(self.platformdir,'jdk-summaries')
        self.jdksumintegrated = os.path.join(self.jdksumdir,'integrated')
        self.jdksumprofiled = os.path.join(self.jdksumdir,'profiled')
        self.jdksumsupplement = os.path.join(self.jdksumdir,'supplement')

        self.jdkprofileddirs = [ 'java', 'org' ]
        self.jdksupplementdirs = [ 'java' ]

        self.jdksumjar = os.path.join(self.jdksumdir,'jdk.jar')
        self.jdksumprofiledjar = os.path.join(self.jdksumprofiled,'jdk-profiled.jar')
        self.jdksumsupplementjar = os.path.join(self.jdksumsupplement,'jdk-supplement.jar')

        self.libsumdir = os.path.join(self.platformdir,'lib-summaries')
        self.libsumintegrated = os.path.join(self.libsumdir,'integrated')
        self.libsumprofiled = os.path.join(self.libsumdir,'profiled')
        self.libsumsupplement = os.path.join(self.libsumdir,'supplement')

        self.exportdir = os.path.join(self.platformdir,'export')

class PlatformLibFileEnvironment(PlatformEnvironment):

    def __init__(self,name,libname,libversion):
        PlatformEnvironment.__init__(self,name)
        self.libname = libname                       # e.g., commons-cli
        self.libversion = libversion                 # e.g., commons-cli-1.3
        
        self.apidir = os.path.join(self.libapidir,self.libname)
        self.apijar = os.path.join(self.apidir,self.libname + '-api.jar')
        self.refjar = os.path.join(self.librefdir,self.libversion + '.jar')

        self.libdir = os.path.join(self.libsumdir,self.libname)        
        self.libversiondir = os.path.join(self.libdir,self.libversion)
        self.libjar = os.path.join(self.libversiondir,self.libversion + '.jar')

        self.libsumjar = os.path.join(self.libversiondir,self.libversion + '-chlib.jar')
        self.libsumintegrateddir = os.path.join(self.libversiondir,'integrated')
        self.libsumprofiledir = os.path.join(self.libversiondir,'profiled')
        self.libsumprofilejar = os.path.join(self.libsumprofiledir,
                                                  self.libversion + '-profiled.jar')
        self.libsumsupplementdir = os.path.join(self.libversiondir,'supplement')
        self.libsumsupplementjar = os.path.join(self.libsumsupplementdir,
                                                    self.libversion + '-supplement.jar')

