JAVA=javac -Xlint:unchecked
JAVAOPTS=-classpath ../lib/commons-cli-1.3.jar:../lib/commons-math3-3.6.1.jar:. -d bin

-include Makefile.common

PRE_LIBRARIES = $(addprefix src/timing_tests/, $(LIBRARIES)) 

ESSENTIALS = \
	src/Java_Timings.java \
	src/Profiling_Controller.java \
	src/Profiling_Class_Selector.java \
	src/Profiling_Parser.java \
	src/util/Test_Thread.java \
	src/util/Slow_Thread.java \

UTILITIES = \
	src/util/Get_Next_String.java \
	src/util/Tuple.java \
	src/util/Utilities.java \
	src/util/Value_Store.java \
	src/BadParameterException.java \

REGRESSION = \
	src/Handle_Data_File.java \
	src/Do_Regression_Standalone.java \
	src/Multiple_Regression.java \

all: SB_Timings

setup: 
	@mkdir -p bin

#regression: setup
#	$(JAVA) $(JAVAOPTS) Handle_Data_File.java Do_Regression.java

regression: setup
	$(JAVA) $(JAVAOPTS) $(UTILITIES) $(REGRESSION)

SB_Timings: setup
	$(JAVA) $(JAVAOPTS) $(UTILITIES) $(ESSENTIALS) $(PRE_LIBRARIES)

clean:
	rm -f bin/*.class
	rm -f bin/Java_Timing/*.class
