package ByteCode_Timing;

import org.apache.commons.cli.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Profiling_Parser{
    public String[] args;

    public Profiling_Parser(String[] args) {
        this.args = args;
    }

    public CommandLine parse() throws org.apache.commons.cli.ParseException {
        Options options = new Options();

        Option first_arg = new Option("r", "repeats", true, "Number of times to execute target bytecode");
        first_arg.setType(int.class);
        first_arg.setRequired(true);
        options.addOption(first_arg);

        Option second_arg = new Option("b", "target_bytecode", true, "Name of bytecode to explore");
        second_arg.setRequired(true);
        options.addOption(second_arg);

        Option values = new Option("v", "values", true, "Values to use as input");
        options.addOption(values);

        Option output_file = new Option("f", "output_file", true, "Name of the output file");
        options.addOption(output_file);

        Option help = new Option("h", "help", false, "Show help message");
        options.addOption(help);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        cmd = parser.parse(options, args);
        
        if(cmd.hasOption("help")) {
            String header = "Profile java bytecode with inputs\n\n";
            String footer = "\n------------------------------------------------------\n\n";
            formatter.printHelp("Bytecode_Profiler", header, options, footer, true);
        }

        return cmd;
    }
}
