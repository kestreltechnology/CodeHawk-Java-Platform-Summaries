### Scripts to generate summaries that integrate api-and platform-dependent information

### Overview

- [chj_inspect_api_summaries.py](#chj_inspect_api_summaries)
- [chj_inspect_jdk_summaries.py](#chj_inspect_jdk_summaries)
- [chj_inspect_lib_summaries.py](#chj_inspect_lib_summaries)
- [chj_integrate_jdk.py](#chj_integrate_jdk)
- [chj_make_jdk_summary.py](#chj_make_jdk_summary)
- [chj_make_lib_jars.py](#chj_make_lib_jars)
- [chj_make_lib_summary.py](#chj_make_lib_summary)

### Scripts

#### chj_inspect_api_summaries
Checks the validity of the summaries of a third-party library
and extracts statistics on the number of methods and pre- and
postconditions.
- positional arguments:
  *libname*: name of the library, e.g., commons-math3

#### chj_inspect_jdk_summaries
Checks the validity of the JDK library summaries and extracts
statistics on the availability of time cost information.
- positional arguments:
  *version*: choices: profiled, supplement, or integrated;
  Note: the profiled and supplement summaries are checked in in
  this repository; the integrated summaries must be generated
  with the script chj_integrate_jdk.py first.

- keyword arguments:
  - *--platform*: the name of the platform. Default is ref_8.0_121,
    which for now is the only platform

#### chj_inspect_lib_summaries
Checks the validity of the method summaries of a third-party
library and extracts some statistics on the availability of time
cost information.
- positional arguments:
  *libversion*: name of the third-party library, e.g., commons-cli-1.3

#### chj_integrate_jdk
Combines api, profiled, and supplemented jdk summaries into summaries
that integrate the information from all three. It expects the presence
of the jdk-api summaries as a jar, named jdk-api.jar, in the jdk-api
directory; this file can be obtained from the
CodeHawk/CHJ/jdk-summaries directory in the codehawk repository.
The script will create jars for the platform specific summaries in
the jdk-summaries/profiled and jdk-summaries/supplement directories.
Integrated summaries are created in the jdk-summaries/integrated
directory and bundled into a jar file, jdk-summaries/jdk.jar.
Note that this script may take up to 5 minutes to run, as more
than 1000 classes are processed.
- positional arguments: none
- keyword arguments:
  - *--platform*: name of the platform; this currently defaults
  to ref_8.0_121 as it is the only platform present.

#### chj_make_jdk_summary
Creates a new summary template for a JDK library class. By
default two templates are created: one intended to hold profiling
data and one intended to hold supplemental data obtainted from,
e.g., a costmodel. The class template includes all fields and
all methods including private and default fields and methods.
Command-line options are provided to restrict the creation to
only one of the two. By default the template is saved in the
toplevel target directory (profiled or supplement) to avoid
overwriting existing class summaries. This can be overridden
by the move command-line option, which will cause the class
template to be saved at its proper location according to class
hierarchy.
- positiontal arguments:
  - *classname*: fully qualified name of the class (e.g., java.util.Random)

- keyword arguments:
  - *--platform*: name of the platform to save the template. Default is
    ref_8.0_121, currently the only available platform.
  - *--move*: move the template to its proper location according to
    class hierarchy, possibly overwriting the existing file.
  - *--profile*: only create a template for the profiled directory
  - *--supplement*: only create a template for the supplement directory


#### chj_make_lib_summary
Creates a new summary template for a third-party library class. By
default three templates are created: a version-independent api
template that includes protected and public methods and fields
only, a version-dependent template intended for profiling information
that includes all methods and fields, and a version-dependent template
intended for supplementary information, such as computed costs,
private fields, that also includes all methods and fields.
Command-line options are provided to restrict the templates generated.
By default the templates are moved to their target directory only if
they do not overwrite an existing template (so to avoid overwriting
manually added information); this can be overridden by the --move and
--apimove command-line options.
- positional arguments:
  - *libversion*: name of the library jar, including version, e.g.,
    commons-cli-1.3
  - *classname*: fully qualified classname, e.g.,
    org.apache.commons.cli.CommandLine

- keyword arguments:
  - *--legacysummaries*: name of jar file with existing summaries to
    bootstrap the new template with taint transfer, and pre- and
    postconditions;
  - *--move*: move profile/supplement template to target directory
    even if they overwrite existing templates;
  - *--apimove*: move api template to target directory even if it
    overwrites an existing template;
  - *--api*: only generate an api template;
  - *--profile*: only generate a profile template;
  - *--supplement*: only generate a supplement template.

