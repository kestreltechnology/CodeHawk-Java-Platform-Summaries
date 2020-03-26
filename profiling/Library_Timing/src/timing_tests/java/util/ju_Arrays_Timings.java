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

import java.util.Arrays;

public class ju_Arrays_Timings extends Java_Timings{
	public ju_Arrays_Timings(boolean verbose) {
		super(verbose);
	}

	public long get_timing(int repeats, String selection, Value_Store values){
		double[] nvals = values.numeric_vals;
		String[] svals = values.string_vals;

		long total_time = 0;
		switch(selection) {
            case "fill_intarray_int" :
                total_time = run_fill_intarray_int(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "copyof_tarray_int":
                total_time = run_copyof_tarray_int(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
                break;
			case "emptyloop":
				total_time = run_emptyloop(repeats);
				break;
			case "asList_ObjectARRAY" :
				total_time = run_asList_ObjectARRAY(repeats);
				break;
			case "copyOf_byteARRAY_int" :
				total_time = run_copyOf_byteARRAY_int(repeats);
				break;
			case "copyOf_charARRAY_int" :
				total_time = run_copyOf_charARRAY_int(repeats);
				break;
			case "copyOf_intARRAY_int" :
				total_time = run_copyOf_intARRAY_int(repeats);
				break;
			case "copyOf_ObjectARRAY_int" :
				total_time = run_copyOf_ObjectARRAY_int(repeats);
				break;
			case "copyOf_longARRAY_int" :
				total_time = run_copyOf_longARRAY_int(repeats);
				break;
			case "copyOfRange_byteARRAY_int_int" :
				total_time = run_copyOfRange_byteARRAY_int_int(repeats);
				break;
			case "copyOfRange_ObjectARRAY_int_int" :
				total_time = run_copyOfRange_ObjectARRAY_int_int(repeats);
				break;
			case "deepEquals_ObjectARRAY_ObjectARRAY" :
				total_time = run_deepEquals_ObjectARRAY_ObjectARRAY(repeats);
				break;
			case "deepHashCode_ObjectARRAY" :
				total_time = run_deepHashCode_ObjectARRAY(repeats);
				break;
			case "deepToString_ObjectARRAY" :
				total_time = run_deepToString_ObjectARRAY(repeats);
				break;
			case "equals_byteARRAY_byteARRAY" :
				total_time = run_equals_byteARRAY_byteARRAY(repeats);
				break;
			case "fill_ObjectARRAY_Object" :
				total_time = run_fill_ObjectARRAY_Object(repeats);
				break;
			case "hashCode_byteARRAY" :
				total_time = run_hashCode_byteARRAY(repeats);
				break;
			case "sort_ObjectARRAY_Comparator" :
				total_time = run_sort_ObjectARRAY_Comparator(repeats);
				break;
			case "stream_ObjectARRAY" :
				total_time = run_stream_ObjectARRAY(repeats);
				break;
			case "toString_byteARRAY" :
				total_time = run_toString_byteARRAY(repeats);
				break;
			case "toString_ObjectARRAY" :
				total_time = run_toString_ObjectARRAY(repeats);
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

    public static long run_copyof_tarray_int(int count, int initial_size, int index, int parameter, int new_value) {
        Object some_object;
        try {
            some_object = Utilities.get_some_object(index, parameter);
        }
        catch (BadParameterException e) {
            return -1;
        }
        Object[] some_array = new Object[initial_size];
        Arrays.fill(some_array, some_object);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            Object[] new_array = Arrays.copyOf(some_array, new_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_fill_intarray_int(int count, int initial_size, int new_value) {
        int[] array;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            array = new int[initial_size];
            start_time = System.nanoTime();
            Arrays.fill(array, new_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

	public static long run_asList_ObjectARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_copyOf_byteARRAY_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_copyOf_charARRAY_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_copyOf_intARRAY_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_copyOf_ObjectARRAY_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_copyOf_longARRAY_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_copyOfRange_byteARRAY_int_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_copyOfRange_ObjectARRAY_int_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_deepEquals_ObjectARRAY_ObjectARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_deepHashCode_ObjectARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_deepToString_ObjectARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_equals_byteARRAY_byteARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_fill_ObjectARRAY_Object(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_hashCode_byteARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_sort_ObjectARRAY_Comparator(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_stream_ObjectARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toString_byteARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toString_ObjectARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
