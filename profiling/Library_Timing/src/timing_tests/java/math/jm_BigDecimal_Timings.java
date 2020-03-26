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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class jm_BigDecimal_Timings extends Java_Timings{
    public jm_BigDecimal_Timings(boolean verbose) {
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
            case "init_double":
                total_time = run_init_double(repeats, nvals[0]);
                break;
            case "init_int":
                total_time = run_init_int(repeats, (int)nvals[0]);
                break;
            case "init_String":
                total_time = run_init_String(repeats, svals[0]);
                break;
            case "abs":
                total_time = run_abs(repeats, svals[0]);
                break;
            case "add_BigDecimal":
                total_time = run_add_BigDecimal(repeats, svals[0], svals[1]);
                break;
            case "compareTo_BigDecimal":
                total_time = run_compareTo_BigDecimal(repeats, svals[0], svals[1]);
                break;
            case "divide_BigDecimal_MathContext":
                total_time = run_divide_BigDecimal_MathContext(repeats, svals[0], svals[1], (int)nvals[0], (int)nvals[1]);
                break;
            case "doubleValue":
                total_time = run_doubleValue(repeats, svals[0]);
                break;
            case "multiply_BigDecimal":
                total_time = run_multiply_BigDecimal(repeats, svals[0], svals[1], (int)nvals[0], (int)nvals[1]);
                break;
            case "multiply_BigDecimal_MathContext":
                total_time = run_multiply_BigDecimal_MathContext(repeats, svals[0], svals[1], (int)nvals[0]);
                break;
            case "negate":
                total_time = run_negate(repeats, svals[0]);
                break;
            case "pow_int":
                total_time = run_pow_int(repeats, svals[0], (int)nvals[0]);
                break;
            case "round_MathContext":
                total_time = run_round_MathContext(repeats, svals[0], (int)nvals[0]);
                break;
            case "scale":
                total_time = run_scale(repeats, svals[0], (int)nvals[0]);
                break;
            case "subtract_BigDecimal":
                total_time = run_subtract_BigDecimal(repeats, svals[0], svals[1], (int)nvals[0], (int)nvals[1]);
                break;
            case "subtract_BigDecimal_MathContext":
                total_time = run_subtract_BigDecimal_MathContext(repeats, svals[0], svals[1], (int)nvals[0]);
                break;
			case "toPlainString":
				total_time = run_toPlainString(repeats, svals[0]);
				break;
            case "unscaledValue":
                total_time = run_unscaledValue(repeats, svals[0]);
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

    public static long run_init_double(int count, double initial_value) {
		BigDecimal big_value;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value = new BigDecimal(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_init_int(int count, int initial_value) {
		BigDecimal big_value;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value = new BigDecimal(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_init_String(int count, String initial_value) {
		BigDecimal big_value;
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value = new BigDecimal(initial_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_abs(int count, String initial_value) {
		BigDecimal big_value = new BigDecimal(initial_value);
		BigDecimal new_value;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value.abs();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_add_BigDecimal(int count, String value1, String value2) {
		BigDecimal big_value1 = new BigDecimal(value1);
		BigDecimal big_value2 = new BigDecimal(value2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value1.add(big_value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_compareTo_BigDecimal(int count, String value1, String value2) {
		BigDecimal big_value1 = new BigDecimal(value1);
		BigDecimal big_value2 = new BigDecimal(value2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value1.compareTo(big_value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_divide_BigDecimal_MathContext(int count, String value1, String value2, int prec1, int prec2) {
		MathContext context1 = new MathContext(prec1);
		MathContext context2 = new MathContext(prec2);
		BigDecimal big_value1 = new BigDecimal(value1, context1);
		BigDecimal big_value2 = new BigDecimal(value2, context2);
		

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value1.divide(big_value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_doubleValue(int count, String value1) {
		BigDecimal big_value1 = new BigDecimal(value1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value1.doubleValue();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_multiply_BigDecimal(int count, String value1, String value2, int prec1, int prec2) {
		MathContext context1 = new MathContext(prec1);
		MathContext context2 = new MathContext(prec2);
		BigDecimal big_value1 = new BigDecimal(value1, context1);
		BigDecimal big_value2 = new BigDecimal(value2, context2);

	    long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value1.multiply(big_value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_multiply_BigDecimal_MathContext(int count, String value1, String value2, int prec1) {
		MathContext context1 = new MathContext(prec1);
		BigDecimal big_value1 = new BigDecimal(value1);
		BigDecimal big_value2 = new BigDecimal(value2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value1.multiply(big_value2, context1);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_negate(int count, String initial_value) {
		BigDecimal big_value1 = new BigDecimal(initial_value);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value1.negate();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_pow_int(int count, String initial_value, int exp) {
		BigDecimal big_value1 = new BigDecimal(initial_value);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value1.pow(exp);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_round_MathContext(int count, String initial_value, int precision) {
		BigDecimal big_value = new BigDecimal(initial_value);
		MathContext context1 = new MathContext(precision);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value.round(context1);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_scale(int count, String initial_value, int precision) {
		MathContext context1 = new MathContext(precision);
		BigDecimal big_value = new BigDecimal(initial_value, context1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value.scale();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_subtract_BigDecimal(int count, String value1, String value2, int prec1, int prec2) {
		MathContext context1 = new MathContext(prec1);
		MathContext context2 = new MathContext(prec2);
		BigDecimal big_value1 = new BigDecimal(value1, context1);
		BigDecimal big_value2 = new BigDecimal(value2, context2);
   
	    long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value1.subtract(big_value2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_subtract_BigDecimal_MathContext(int count, String value1, String value2, int precision) {
		MathContext context = new MathContext(precision);
		BigDecimal big_value1 = new BigDecimal(value1);
		BigDecimal big_value2 = new BigDecimal(value2);
   
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value1.subtract(big_value2, context);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_toPlainString(int count, String initial_value) {
		BigDecimal big_value = new BigDecimal(initial_value);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_value.toPlainString();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_unscaledValue(int count, String initial_value) {
		BigDecimal big_value = new BigDecimal(initial_value);
		BigInteger big_int;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
			big_int = big_value.unscaledValue();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

}
