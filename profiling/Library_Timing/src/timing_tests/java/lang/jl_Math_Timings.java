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

import java.lang.Math;
import java.util.Random;

public class jl_Math_Timings extends Java_Timings{
    public jl_Math_Timings(boolean verbose) {
        super(verbose);
    }

    public long get_timing(int repeats, String selection, Value_Store values){
        double[] nvals = values.numeric_vals;

        long total_time = 0;
        switch(selection) {
            case "emptyloop" :
                total_time = run_emptyloop(repeats);
                break;
            case "abs_double" :
                total_time = run_abs_double(repeats, nvals[0]);
                break;
            case "abs_int" :
                total_time = run_abs_int(repeats, (int)nvals[0]);
                break;
            case "atan2_double_double" :
                total_time = run_atan2_double_double(repeats, nvals[0], nvals[1]);
                break;
            case "ceil_double" :
                total_time = run_ceil_double(repeats, nvals[0]);
                break;
            case "cos_double" :
                total_time = run_cos_double(repeats, nvals[0]);
                break;
            case "floor_double" :
                total_time = run_floor_double(repeats, nvals[0]);
                break;
			case "getExponent_float" :
				total_time = run_getExponent_float(repeats, (float)nvals[0]);
				break;
            case "max_int_int" :
                total_time = run_max_int_int(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "max_long_long" :
                total_time = run_max_long_long(repeats, (long)nvals[0], (long)nvals[1]);
                break;
            case "min_float_float" :
                total_time = run_min_float_float(repeats, (float)nvals[0], (int)nvals[1]);
                break;
            case "min_int_int" :
                total_time = run_min_int_int(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "min_long_long" :
                total_time = run_min_long_long(repeats, (long)nvals[0], (long)nvals[1]);
                break;
			case "pow_double_double" :
				total_time = run_pow_double_double(repeats, nvals[0], nvals[1]);
				break;
            case "random" :
                total_time = run_random(repeats);
                break;
            case "round_double" :
                total_time = run_round_double(repeats, nvals[0]);
                break;
            case "round_float" :
                total_time = run_round_float(repeats, (float)nvals[0]);
                break;
			case "signum_double" :
				total_time = run_signum_double(repeats, nvals[0]);
				break;
            case "sin_double" :
                total_time = run_sin_double(repeats, nvals[0]);
                break;
            case "sqrt_double" :
                total_time = run_sqrt_double(repeats, nvals[0]);
                break;
            case "toIntExact_long":
                total_time = run_toIntExact_long(repeats, (long)nvals[0]);
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

    public static long run_abs_double(int count, double initial_value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.abs(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
    
    public static long run_abs_int(int count, int initial_value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.abs(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_atan2_double_double(int count, double x_coord, double y_coord) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.atan2(x_coord, y_coord);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
    
    public static long run_ceil_double(int count, double initial_value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.ceil(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_cos_double(int count, double initial_value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.cos(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_floor_double(int count, double initial_value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.floor(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_getExponent_float(int count, float initial_value) {
		float tinymod = initial_value / (float)count;
        float value = initial_value;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.getExponent(value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;

			value += tinymod;
        }
        return total_time / count;
    }

    public static long run_max_int_int(int count, int value1, int value2) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.max(value1, value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_max_long_long(int count, long value1, long value2) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.max(value1, value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_min_float_float(int count, float value1, float value2) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.min(value1, value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_min_int_int(int count, int value1, int value2) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.min(value1, value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_min_long_long(int count, long value1, long value2) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.min(value1, value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_pow_double_double(int count, double value1, double value2) {
        double tinymod1 = value1 / (double)count;
        double tinymod2 = value2 / (double)count;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.pow(value1, value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;

			value1 += tinymod1;
			value2 += tinymod2;
        }
        return total_time / count;
    }

    public static long run_random(int count) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.random();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_round_double(int count, double value1) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.round(value1);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_round_float(int count, float value1) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.round(value1);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

	public static long run_signum_double(int count, double value1) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.signum(value1);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;	
	}

    public static long run_sin_double(int count, double value1) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.sin(value1);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_sqrt_double(int count, double value1) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Math.sqrt(value1);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_toIntExact_long(int count, long value1) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            /*Math.toIntExact(value1);*/
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    } 
}
