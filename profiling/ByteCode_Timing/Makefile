JAVA=javac -Xlint:unchecked
JAVAOPTS=-classpath ../lib/commons-cli-1.3.jar:. -d bin

JAVAFILES = \
	Dog.java \
	Dog2.java \
	ByteCodes_Array.java \
	Timing_Classes.java \
	ByteCode_Timing.java \
	Profiling_Parser.java \
	Profiling_Printer.java \

PREFIXED_JAVAFILES = $(addprefix src/, $(JAVAFILES))

all: main 

setup: 
	@mkdir -p bin

jar:
	@jar -cf bin/Timing_Classes.jar -C bin/ByteCode_Timing/ .

main: setup
	$(JAVA) $(JAVAOPTS) $(PREFIXED_JAVAFILES)

clean:
	rm -f bin/*.class
	rm -f bin/ByteCode_Timing/*.class
