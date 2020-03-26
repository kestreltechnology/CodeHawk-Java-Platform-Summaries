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
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Double;
import java.lang.String;
import java.util.ArrayList;


public class Profiling_Parser{
    public String[] args;

    public Profiling_Parser(String[] args) {
        this.args = args;
    }

    public CommandLine parse() throws ParseException {
        Options options = new Options();

        Option first_arg = new Option("r", "repeats", true, "Number of times to execute target method");
        first_arg.setType(int.class);
        first_arg.setRequired(true);
        options.addOption(first_arg);

        Option second_arg = new Option("m", "target_method", true, "Name of the method to be Profiled");
        second_arg.setRequired(true);
        options.addOption(second_arg);

        Option third_arg = new Option("c", "target_class", true, "Name of the class containing the method of interest");
        third_arg.setRequired(true);
        options.addOption(third_arg);

        Option values = new Option("q", "values", true, "List of values to use as input");
        /* TODO : Does setting type do anything for us */
        /*values.setType(double[].class);*/
        options.addOption(values);

        Option str_values = new Option("s", "str_values", true, "List of string to use as input");
        options.addOption(str_values);

        Option raw_input_file = new Option("f", "input_file", true, "Name of input file");
        options.addOption(raw_input_file);

        Option output_file = new Option("o", "output_file", true, "Name of the output file");
        options.addOption(output_file);

        Option outer_loops = new Option("a", "outer_loops", true, "Number of outer loops to use");
        options.addOption(outer_loops);

        Option verbose = new Option("v", "verbose", false, "Provide verbose output");
        options.addOption(verbose);

        Option help = new Option("h", "help", false, "Show help message");
        options.addOption(help);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        cmd = parser.parse(options, args);

        if(cmd.hasOption("help")){
            String header = "Profile java method with some inputs\n\n";
            String footer = "\n----------------------------------------------------------------------\n\n";
            formatter.printHelp("Timing_Profiler", header, options, footer, true);
        }

        return cmd;
    }

	public static Value_Store simple_input_parser(File input_file, String classname, String methodname) {
		ArrayList<Double> num_list = new ArrayList<Double>();
		ArrayList<String> str_list = new ArrayList<String>();
		String line;

		try {
			FileReader fileReader = new FileReader(input_file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int is_string = 1;

			while((line = bufferedReader.readLine()) != null) {
				try {
					if(line.trim().equals("")) {
						is_string = 0;
						continue;
					}

					if(is_string > 0) {
						str_list.add(line.trim());	
					}
					else {
						num_list.add(Double.parseDouble(line.trim()));
					}
				}
				catch(NullPointerException e) {
					System.out.println("Raw input file appears to be formatted incorrectly");
					System.exit(1);
				}
				catch(NumberFormatException e) {
					System.out.println("Raw inputfile appears to be formatted incorrectly");
					System.exit(1);
				}
				
			}

		}
        catch(FileNotFoundException ex) {
            System.out.format("Unable to open file : %s", input_file.getName());
            System.exit(1);
        }
        catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
		
		Double[] num_values_temp = new Double[num_list.size()];
		String[] str_values = new String[str_list.size()];
		num_values_temp = num_list.toArray(num_values_temp);
		str_values = str_list.toArray(str_values);

		double[] num_values = new double[num_values_temp.length];
		for(int i = 0; i < num_values_temp.length; i++) {
			num_values[i] = num_values_temp[i].doubleValue();
		}

		Value_Store values = new Value_Store(classname, methodname, num_values, str_values);

		return values;
	} 

    public static double[][] raw_input_parser(File raw_input_file){
        ArrayList<double[]> new_args = new ArrayList<double[]>();
        int standardLength = 0;
        double temp;
        double[] data_array;
        String line;
        String[] datum;

        try { 
            FileReader fileReader = new FileReader(raw_input_file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            if(validate_raw_input(raw_input_file) == false){
                System.out.println("Config file is invalid!");
                System.exit(1);
            }

            while((line = bufferedReader.readLine()) != null) {
                datum = line.split("\\s+");
                standardLength = datum.length;
                data_array = new double[datum.length];

                for(int i = 0; i < datum.length; i++) {
                    try {
                        temp = Double.parseDouble(datum[i]);
                        data_array[i] = temp;
                    }
                    catch(NullPointerException e) {
                        System.out.println("Raw input file appears to be formatted incorrectly");
                        System.exit(1);
                    }
                    catch(NumberFormatException e) {
                        System.out.println("Raw inputfile appears to be formatted incorrectly");
                        System.exit(1);
                    }
                }
                new_args.add(data_array);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.format("Unable to open file : %s", raw_input_file.getName());
            System.exit(1);
        }
        catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        double[][] reference_data = new double[new_args.size()][standardLength];
        System.out.format("%d\n", reference_data.length);
        return new_args.toArray(reference_data);
    }

    public static boolean validate_raw_input(File raw_input_file) {
        int acceptedlength = -1;
        boolean validity = true;
        String line;
        String[] datum;
        try {
            FileReader fileReader = new FileReader(raw_input_file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int line_number = 0;
            while((line = bufferedReader.readLine()) != null) {
                datum = line.split("\\s+");
                if(acceptedlength == -1){acceptedlength = datum.length;}
                else if(acceptedlength != -1 && acceptedlength != datum.length) {
                    System.out.format("Problem with line %d\n", line_number);
                    validity = false;
                }
                line_number += 1;
                 
                double temp;
                for(int i = 0; i < datum.length; i++) {
                    try {
                        temp = Double.parseDouble(datum[i]);
                    }
                    catch(NullPointerException e) {
                        validity = false;
                    }
                    catch(NumberFormatException e) {
                        validity = false;
                    }
                }
            }
            
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + raw_input_file + "'");
            System.exit(1);
        }
        catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return validity; 
    }
}
