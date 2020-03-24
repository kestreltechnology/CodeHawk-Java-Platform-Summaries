### Scripts to generate summaries that integrate api-and platform-dependent information

### Overview

- [chj_make_lib_summary.py](#chj_make_lib_summary)

### Scripts

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

