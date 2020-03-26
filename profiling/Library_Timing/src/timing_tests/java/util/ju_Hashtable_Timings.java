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

import java.util.Hashtable;

public class ju_Hashtable_Timings extends Java_Timings{
	public ju_Hashtable_Timings(boolean verbose) {
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
			case "init" :
				total_time = run_init(repeats);
				break;
			case "containsKey_Object" :
				total_time = run_containsKey_Object(repeats, (int)nvals[0], svals[0]);
				break;
			case "keySet" :
				total_time = run_keySet(repeats, (int)nvals[0]);
				break;
			case "get_Object" :
				total_time = run_get_Object(repeats, (int)nvals[0], svals[0]);
				break;
			case "put_Object_Object" :
				total_time = run_put_Object_Object(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
				break;
			case "size" :
				total_time = run_size(repeats, (int)nvals[0]);
				break;
			case "toString" :
				total_time = run_toString(repeats, (int)nvals[0]);
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

	public static long run_init(int count) {
        Hashtable<String, String> new_hashtable;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_hashtable = new Hashtable<String, String>();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_containsKey_Object(int count, int size, String test) {
        Hashtable<String, String> new_hashtable = Utilities.get_string_hashtable(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_hashtable.containsKey(test);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_get_Object(int count, int size, String test) {
        Hashtable<String, String> new_hashtable = Utilities.get_string_hashtable(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_hashtable.get(test);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_keySet(int count, int size) {
        Hashtable<String, String> new_hashtable = Utilities.get_string_hashtable(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_hashtable.keySet();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_put_Object_Object(int count, int size, int size1, int size2) {
        Hashtable<String, String> new_hashtable = Utilities.get_string_hashtable(size);
        String string1 = Utilities.get_some_string(size1);
        String string2 = Utilities.get_some_string(size2);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_hashtable.put(string1, string2);
			end_time = System.nanoTime();
			total_time += end_time - start_time;

            new_hashtable.remove(string1);
		}
		return total_time / count;
	}

	public static long run_size(int count, int size) {
        Hashtable<String, String> new_hashtable = Utilities.get_string_hashtable(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_hashtable.size();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toString(int count, int size) {
        Hashtable<String, String> new_hashtable = Utilities.get_string_hashtable(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_hashtable.toString();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
    }	
}
