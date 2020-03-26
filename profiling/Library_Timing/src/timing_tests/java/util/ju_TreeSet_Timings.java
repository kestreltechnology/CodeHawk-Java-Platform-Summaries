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

import java.util.TreeSet;

public class ju_TreeSet_Timings extends Java_Timings{
	public ju_TreeSet_Timings(boolean verbose) {
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
			case "init_Comparator" :
				total_time = run_init_Comparator(repeats);
				break;
			case "add_Object" :
				total_time = run_add_Object(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "contains_Object" :
				total_time = run_contains_Object(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "clone" :
				total_time = run_clone(repeats, (int)nvals[0]);
				break;
			case "isEmpty" :
				total_time = run_isEmpty(repeats, (int)nvals[0]);
				break;
			case "iterator" :
				total_time = run_iterator(repeats, (int)nvals[0]);
				break;
			case "size" :
				total_time = run_size(repeats, (int)nvals[0]);
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
        TreeSet<String> new_treeset;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
	        new_treeset = new TreeSet<String>();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_init_Comparator(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_add_Object(int count, int size, int string_size) {
        TreeSet<String> new_treeset = Utilities.get_string_treeset(size);
        String new_string = Utilities.get_some_string(string_size); 

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_treeset.add(new_string);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_clone(int count, int size) {
        TreeSet<String> new_treeset = Utilities.get_string_treeset(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
	        new_treeset.clone();	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_contains_Object(int count, int size, int string_size) {
        TreeSet<String> new_treeset = Utilities.get_string_treeset(size);
        String new_string = Utilities.get_some_string(string_size); 

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
	        new_treeset.contains(new_string);	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_isEmpty(int count, int size) {
        TreeSet<String> new_treeset = Utilities.get_string_treeset(size); 

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
	        new_treeset.isEmpty();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_iterator(int count, int size) {
        TreeSet<String> new_treeset = Utilities.get_string_treeset(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
	        new_treeset.iterator();	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_size(int count, int size) {
        TreeSet<String> new_treeset = Utilities.get_string_treeset(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
	        new_treeset.size();	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
