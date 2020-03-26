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

import java.lang.Boolean;
import java.lang.String;

public class jl_Boolean_Timings extends Java_Timings{
	public jl_Boolean_Timings(boolean verbose) {
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
			case "booleanValue" :
				total_time = run_booleanValue(repeats, svals[0]);
				break;
            case "equals_object":
                total_time = run_boolean_equals_object(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
                break;
			case "getBoolean_String" :
				total_time = run_getBoolean_String(repeats, svals[0]);
				break;
			case "hashCode" :
				total_time = run_hashCode(repeats, svals[0]);
				break;
			case "parseBoolean_String" :
				total_time = run_parseBoolean_String(repeats, svals[0]);
				break;
			case "toString" :
				total_time = run_toString(repeats, svals[0]);
				break;
			case "toString_boolean" :
				total_time = run_toString_boolean(repeats, svals[0]);
				break;
			case "valueOf_boolean" :
				total_time = run_valueOf_boolean(repeats, svals[0]);
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

	public static long run_booleanValue(int count, String value) {
		Boolean new_bool = Boolean.parseBoolean(value);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_bool.booleanValue();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

    public static long run_boolean_equals_object(int count, int initial_value, int index, int parameter) {
        long start_time, end_time, total_time = 0;
        Boolean initial_bool = new Boolean(initial_value == 1);
        Object some_object;

        try {
            some_object = Utilities.get_some_object(index, parameter);

            for(int i = 0; i < count; i++) {
                start_time = System.nanoTime();
                initial_bool.equals(some_object);
                end_time = System.nanoTime();
                total_time += end_time - start_time;
            }
            return total_time / count;
        }
        catch(BadParameterException e) {
            return -1;
        }
    }

	public static long run_getBoolean_String(int count, String value) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Boolean.getBoolean(value);	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_hashCode(int count, String value) {
		Boolean new_bool = Boolean.parseBoolean(value);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_bool.hashCode();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_parseBoolean_String(int count, String value) {
		Boolean new_bool;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Boolean.parseBoolean(value);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toString(int count, String value) {
		Boolean new_bool = Boolean.parseBoolean(value);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_bool.toString();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toString_boolean(int count, String value) {
		Boolean new_bool = Boolean.parseBoolean(value);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Boolean.toString(new_bool);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_valueOf_boolean(int count, String value) {
		Boolean temp_bool = Boolean.parseBoolean(value);
		boolean new_bool = temp_bool.booleanValue();

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Boolean.valueOf(new_bool);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_valueOf_String(int count, String value) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Boolean.valueOf(value);	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
