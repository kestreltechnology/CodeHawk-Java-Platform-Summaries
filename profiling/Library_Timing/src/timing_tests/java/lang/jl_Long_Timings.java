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

import java.lang.Long;

public class jl_Long_Timings extends Java_Timings{
    public jl_Long_Timings(boolean verbose) {
        super(verbose);
    }

    public long get_timing(int repeats, String selection, Value_Store values){
        double[] nvals = values.numeric_vals;

        long total_time = 0;
        switch(selection) {
            case "emptyloop" :
                total_time = run_emptyloop(repeats);
                break;
            case "compareTo_long" :
                total_time = run_long_compareTo_long((int)nvals[0], (long)nvals[1], (long)nvals[2]);
                break;
            case "equals_object":
                total_time = run_long_equals_object(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
                break;
            case "longValue":
                total_time = run_long_longValue(repeats, (int)nvals[0]);
                break;
            case "parseLong_String":
                total_time = run_long_parseLong_String(repeats, (int)nvals[0]);
                break;
            case "valueOf_long":
                total_time = run_long_valueOf_long(repeats, (int)nvals[0]);
                break;
            default:
                System.out.format("Couldn't find method %s, falling through", selection);
                break;
        }

        if(selection == "emptyloop")
        {
            return total_time;
        }
        else
        {
            return total_time - get_timing(repeats, "emptyloop", values);
        }
    }

    public static long run_long_compareTo_long(int count, long initial_value, long new_value) {
        Long initial_long = new Long((long)initial_value);
        Long new_long = new Long((long)new_value);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            initial_long.compareTo(new_long);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }

        return total_time / count;
    }

    public static long run_long_equals_object(int count, int initial_value, int index, int parameter) {
        Object some_object;
        Long initial_long = new Long((long)initial_value);

        try {
            some_object = Utilities.get_some_object(index, parameter);

            long start_time, end_time, total_time = 0;
            for(int i = 0; i < count; i++) {
                start_time = System.nanoTime();
                initial_long.equals(some_object);
                end_time = System.nanoTime();
                total_time += end_time - start_time;
            }
            return total_time / count;
        }
        catch(BadParameterException e) {
            return -1;
        }
    }

    public static long run_long_longValue(int count, int initial_value){
        Long initial_long = new Long((long)initial_value);
  
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++){
            start_time = System.nanoTime();
            initial_long.longValue();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }

        return total_time / count;
    }

    public static long run_long_parseLong_String(int count, long initial_value){
        Long initial_long = new Long(initial_value);
        String initial_string = new String(initial_long.toString());

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Long.parseLong(initial_string);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_long_valueOf_long(int count, int initial_value){
        Long initial_long = new Long((long)initial_value);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Long.valueOf(initial_long);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }

        return total_time / count;
    }
}
