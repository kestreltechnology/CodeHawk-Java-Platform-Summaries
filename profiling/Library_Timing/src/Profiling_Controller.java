/* ------------------------------------------------------------------------------
 Tool to profile Java library methods
 Author: Andrew McGraw
 ------------------------------------------------------------------------------
 The MIT License (MIT)
 Copyright (c) 2016-2017 Kestrel Technology LLC

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
*/

package Java_Timing;

import org.apache.commons.cli.*;
import java.util.TreeMap;
import java.lang.String;
import java.io.File;

public class Profiling_Controller{
    
    public static void main(String[] args){
        Profiling_Parser pparser;
        pparser = new Profiling_Parser(args);

        CommandLine cmd;
        try{
            cmd = pparser.parse();
        }
        catch (ParseException e){
            System.out.println(e.getMessage());

            System.exit(1);
            return;
        }

        String[] unparsed_values;
        double[] parsed_values = new double[0];
        String[] parsed_str_values = new String[0];

        int repeats = Integer.parseInt(cmd.getOptionValue("repeats"));
        String methodname = cmd.getOptionValue("target_method");
        String classname = cmd.getOptionValue("target_class");
        boolean is_verbose = cmd.hasOption("verbose");
        int outer_loops = cmd.hasOption("outer_loops") ? Integer.parseInt(cmd.getOptionValue("outer_loops")) : 10;
        String output_file = cmd.hasOption("output_file") ? cmd.getOptionValue("output_file") : "default_output.txt";

		Value_Store values;
		if(cmd.hasOption("input_file")) {
			File input_file = new File(cmd.getOptionValue("input_file"));
			values = Profiling_Parser.simple_input_parser(input_file, classname, methodname);
		}
		else
		{
        	if(cmd.hasOption("values")) {
            	unparsed_values = cmd.getOptionValues("values");
            	parsed_values = new double[unparsed_values.length];
            	for(int i = 0; i < parsed_values.length; i++) {
                	parsed_values[i] = Double.parseDouble(unparsed_values[i]);
            	}
        	}
        	if(cmd.hasOption("str_values")) {
            	parsed_str_values = cmd.getOptionValues("str_values");
        	}

			values = new Value_Store(classname, methodname, parsed_values, parsed_str_values);

		}

        System.out.format("Iterations : %d\n", repeats);

		Profiling_Class_Selector selector = new Profiling_Class_Selector();
		Java_Timings profiler = selector.get_profiler(classname, output_file, is_verbose);

        if (profiler != null) {
            for(int i = 0; i < values.numeric_vals.length; i++) {
                System.out.format("Value %d : %e\n", i, values.numeric_vals[i]);
            }

            for(int i = 0; i < values.string_vals.length; i++) {
                System.out.format("Value %d : %s\n", i, values.string_vals[i]);
            }

            long timing = profiler.get_timing_wrapper(repeats, outer_loops, methodname, values);           
			profiler.print_timing_to_file(values, timing);
		}

        /*
        if (profiler != null) {
            for(int i = 0; i < parsed_values.length; i++) {
                for(int j = 0; j < parsed_values[i].length; j++) {
                    System.out.format("Value %d : %f\n", j, parsed_values[i][j]);
                }
                profiler.get_timing_wrapper(repeats, methodname, parsed_values[i]);
            }
            profiler.print_timings_to_file(output_file);
        }
        */
    }
}
