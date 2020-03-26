/*-----------------------------------------------------------------------------
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

import java.lang.Integer;

public class jl_Integer_Timings extends Java_Timings{
	public jl_Integer_Timings(boolean verbose) {
		super(verbose);
	}

	public long get_timing(int repeats, String selection, Value_Store values){
		double[] nvals = values.numeric_vals;
		String[] svals = values.string_vals;

		long total_time = 0;
		switch(selection) {
			case "emptyloop":
				total_time = run_emptyloop(repeats);
				break;
			case "init_int" :
				total_time = run_init_int(repeats, (int)nvals[0]);
				break;
			case "compareTo_Integer" :
				total_time = run_compareTo_Integer(repeats, (int)nvals[0], (int)nvals[1]);
				break;
            case "equals_object":
                total_time = run_integer_equals_object(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
                break;
			case "getInteger_String_int" :
				total_time = run_getInteger_String_int(repeats, svals[0], (int)nvals[0]);
				break;
			case "intValue" :
				total_time = run_intValue(repeats, (int)nvals[0]);
				break;
			case "min_int_int" :
				total_time = run_min_int_int(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "numberOfLeadingZeros_int" :
				total_time = run_numberOfLeadingZeros_int(repeats, (int)nvals[0]);
				break;
			case "parseInt_String" :
				total_time = run_parseInt_String(repeats, svals[0]);
				break;
			case "parseInt_String_int" :
				total_time = run_parseInt_String_int(repeats, svals[0], (int)nvals[0]);
				break;
			case "signum_int" :
				total_time = run_signum_int(repeats, (int)nvals[0]);
				break;
			case "toBinaryString_int" :
				total_time = run_toBinaryString_int(repeats, (int)nvals[0]);
				break;
			case "toHexString_int" :
				total_time = run_toHexString_int(repeats, (int)nvals[0]);
				break;
			case "toString_int" :
				total_time = run_toString_int(repeats, (int)nvals[0]);
				break;
			case "toString_int_int" :
				total_time = run_toString_int_int(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "valueOf_int" :
				total_time = run_valueOf_int(repeats, (int)nvals[0]);
				break;
			case "valueOf_String" :
				total_time = run_valueOf_String(repeats, svals[0]);
				break;
			default:
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

	public static long run_init_int(int count, int initial_int) {
		Integer new_int;		

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_int = new Integer(initial_int);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_compareTo_Integer(int count, int int1, int int2) {
		Integer new_int1 = new Integer(int1);
		Integer new_int2 = new Integer(int2);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_int1.compareTo(new_int2);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

    public static long run_integer_equals_object(int count, int initial_value, int index, int parameter) {
        Object some_object;
        Integer initial_int = new Integer(initial_value);

        try {
            some_object = Utilities.get_some_object(index, parameter);

            long start_time, end_time, total_time = 0;
            for(int i = 0; i < count; i++) {
                start_time = System.nanoTime();
                initial_int.equals(some_object);
                end_time = System.nanoTime();
                total_time += end_time - start_time;
            }
            return total_time / count;
        }
        catch(BadParameterException e) {
            return -1;
        }
    }

	public static long run_getInteger_String_int(int count, String name, int initial_int) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.getInteger(name, initial_int);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_intValue(int count, int initial_int) {
		Integer new_int = new Integer(initial_int);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_int.intValue();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_min_int_int(int count, int int1, int int2) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			/*Integer.min(int1, int2);*/
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_numberOfLeadingZeros_int(int count, int initial_int) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.numberOfLeadingZeros(initial_int);	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_parseInt_String(int count, String initial_string) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.parseInt(initial_string);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_parseInt_String_int(int count, String initial_string, int radix) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.parseInt(initial_string, radix);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_signum_int(int count, int initial_int) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.signum(initial_int);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toBinaryString_int(int count, int initial_int) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.toBinaryString(initial_int);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toHexString_int(int count, int initial_int) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.toHexString(initial_int);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toString_int(int count, int initial_int) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.toString(initial_int);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toString_int_int(int count, int initial_int, int radix) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.toString(initial_int, radix);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_valueOf_int(int count, int initial_int) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.valueOf(initial_int);	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_valueOf_String(int count, String initial_string) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Integer.valueOf(initial_string);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
