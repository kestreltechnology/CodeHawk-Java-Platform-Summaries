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

import java.lang.StringBuilder;
import java.lang.String;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Set;

/* To Add Profiling for a new class, this class need to be extended.
 * For an example, see Java_StringBuilder_Timings.java 
 */

public abstract class Java_Timings {
    protected TreeMap<Value_Store, Long> Timings = new TreeMap<Value_Store, Long>();
    public Value_Store index_timing;
    public boolean is_verbose = false;
    public String output_file = "default_output.txt";

    public Java_Timings(boolean verbose) {
        this.is_verbose = verbose;
    }

    public Java_Timings(boolean verbose, String output_file) {
        this.is_verbose = verbose;
        this.output_file = output_file;
    }

    public abstract long get_timing(int repeats, String selection, Value_Store values);

    public void add_value_to_table(Value_Store values, long timing){
        Timings.put(values, timing);
    } 
    
    public static String get_key(String selection, Value_Store values){
        StringBuilder key = new StringBuilder();
        for(int i = 0; i < values.numeric_vals.length; i++) {
            key.append(values.numeric_vals[i]);
            key.append(" ");
        }
        for(int i = 0; i < values.string_vals.length; i++) {
            key.append(values.string_vals[i]);
            key.append(" ");
        }
        return key.toString();
    }

    public long get_timing_wrapper(int repeats, int outer_loops, String selection, Value_Store values){
        long total_time = 0, accumulated_time = 0, average_time;

        if(Timings.containsKey(values)){
            average_time = Timings.get(values);
        }
        else {
            for(int i = 0; i < outer_loops; i++) {
                total_time = get_timing(repeats, selection, values);
                accumulated_time += total_time;
                if(is_verbose == true) {System.out.format("Timing for iterations %d : %d\n", i, total_time);}

                print_timing_to_file(values, total_time);
            }
        }

        average_time = accumulated_time / outer_loops;
        System.out.format("Average Time : %d ns\n", average_time);

        /* Assume the first timing collected provides a reference value.
        We will assume this as the intercept during regression */   
        if(Timings.size() == 0){index_timing = values;}

        add_value_to_table(values, average_time);

        return average_time;
    }

	public void print_timing_to_file(Value_Store key, Long value) {
		File f = null;
		FileWriter printer = null;

		try {
			f = new File(this.output_file);
			printer = new FileWriter(f, true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

        try {
            StringBuilder output = new StringBuilder();
            output = output.append(key.toString()).append(" ").append(value.toString()).append("\n");
            printer.append(output.toString());
            printer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
	}

    public void print_timings_to_file() {
        File f = null;
        FileWriter printer = null;

        try {
            f = new File(this.output_file);
            printer = new FileWriter(f, true);
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        try {
            Long value = Timings.get(index_timing);

            StringBuilder output = new StringBuilder();
            output.append(index_timing.toString()).append(" ").append(value.toString()).append("\n");
            printer.append(output.toString());

            Timings.remove(index_timing);

            Set<Value_Store> keys = Timings.keySet();
            for(Value_Store key: keys) {
                value = Timings.get(key);

                output = new StringBuilder();
                output.append(key.toString()).append(" ").append(value.toString()).append("\n");
                printer.append(output.toString());
            }

            printer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /* The purpose of this method is to measure the timing between two invocations of System.nanoTime().
    *  Typically, this pair of invocations wraps a call to some method of interest. Because the cost of
    *  these invocations may themselves be significant, it may be useful to account for this cost, particularly
    *  when measuring method calls that return very quickly. 
    */ 
    public long run_emptyloop(int count) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
}
