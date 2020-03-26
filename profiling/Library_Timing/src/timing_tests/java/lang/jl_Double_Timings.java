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

import java.lang.Double;

public class jl_Double_Timings extends Java_Timings{
    public jl_Double_Timings(boolean verbose) {
        super(verbose);
    }

    public long get_timing(int repeats, String selection, Value_Store values){
        double[] nvals = values.numeric_vals;
        String[] svals = values.string_vals;

        long total_time = 0;
        switch(selection) {
            case "emptyloop" :
                total_time = run_emptyloop(repeats);
                break;
            case "compareTo_Double":
                total_time = run_compareTo_Double(repeats, nvals[0], nvals[1]);
                break;
            case "doubleToLongBits_double":
                total_time = run_doubleToLongBits_double(repeats, nvals[0]);
                break;
            case "doubleValue":
                total_time = run_doubleValue(repeats, nvals[0]);
                break;
            case "intValue":
                total_time = run_intValue(repeats, nvals[0]);
                break;
            case "isInfinite":
                total_time = run_isInfinite(repeats, svals[0]);
                break;
            case "isNaN":
                total_time = run_isNaN(repeats, svals[0]);
                break;
            case "longBitsToDouble_long":
                total_time = run_longBitsToDouble_long(repeats, (long)nvals[0]);
                break;
            case "parseDouble_String":
                total_time = run_parseDouble_String(repeats, svals[0]);
                break;
            case "valueOf_double":
                total_time = run_valueOf_double(repeats, nvals[0]);
                break;
            case "valueOf_String":
                total_time = run_valueOf_String(repeats, svals[0]);
                break;
            default:
                System.out.format("Couldn't find method %s, falling through\n", selection);
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

    public static long run_compareTo_Double(int count, double value1, double value2) {
        Double double1 = new Double(value1);
        Double double2 = new Double(value2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            double1.compareTo(double2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_doubleToLongBits_double(int count, double initial_value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Double.doubleToLongBits(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_doubleValue(int count, double initial_value) {
        Double initial_double = new Double(initial_value);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            initial_double.doubleValue();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_intValue(int count, double initial_value) {
        Double initial_double = new Double(initial_value);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            initial_double.intValue();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_isInfinite(int count, String initial_value) {
        Double initial_double = Double.parseDouble(initial_value);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            initial_double.isInfinite();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_isNaN(int count, String initial_value) {
        Double initial_double = Double.parseDouble(initial_value);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            initial_double.isNaN();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_longBitsToDouble_long(int count, long initial_value ) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Double.longBitsToDouble(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_parseDouble_String(int count, String initial_value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Double.parseDouble(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_valueOf_double(int count, double initial_value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Double.valueOf(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_valueOf_String(int count, String initial_value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Double.valueOf(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }



}
