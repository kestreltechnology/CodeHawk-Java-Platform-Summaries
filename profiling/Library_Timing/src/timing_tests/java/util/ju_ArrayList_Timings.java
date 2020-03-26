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

import java.util.ArrayList;

public class ju_ArrayList_Timings extends Java_Timings{
	public ju_ArrayList_Timings(boolean verbose) {
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
			case "init_Collection" :
				total_time = run_init_Collection(repeats);
				break;
			case "add_Object" :
				total_time = run_add_Object(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "addAll_Collection" :
				total_time = run_addAll_Collection(repeats);
				break;
			case "get_int" :
				total_time = run_get_int(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "iterator" :
				total_time = run_iterator(repeats, (int)nvals[0]);
				break;
			case "listIterator" :
				total_time = run_listIterator(repeats);
				break;
			case "listIterator_int" :
				total_time = run_listIterator_int(repeats);
				break;
			case "remove_int" :
				total_time = run_remove_int(repeats);
				break;
			case "set_int_Object" :
				total_time = run_set_int_Object(repeats);
				break;
			case "size" :
				total_time = run_size(repeats, (int)nvals[0]);
				break;
			case "sort_Comparator" :
				total_time = run_sort_Comparator(repeats);
				break;
			case "subList_int_int" :
				total_time = run_subList_int_int(repeats);
				break;
			case "toArray_ObjectARRAY" :
				total_time = run_toArray_ObjectARRAY(repeats);
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
        ArrayList<String> new_arraylist;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_arraylist = new ArrayList<String>();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_init_Collection(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_add_Object(int count, int string_size, int size) {
        ArrayList<String> new_arraylist = new ArrayList<String>();
        Get_Next_String the_next_string = new Get_Next_String();
        for(int i = 0; i < size; i++) {
            new_arraylist.add(the_next_string.next_string());
        }

        String new_string = Utilities.get_some_string(string_size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_arraylist.add(new_string);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_addAll_Collection(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_get_int(int count, int index, int size) {
        ArrayList<String> new_arraylist = new ArrayList<String>();
        Get_Next_String the_next_string = new Get_Next_String();
        for(int i = 0; i < size; i++) {
            new_arraylist.add(the_next_string.next_string());
        }

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_arraylist.get(index);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_iterator(int count, int size) {
        ArrayList<String> new_arraylist = new ArrayList<String>();
        Get_Next_String the_next_string = new Get_Next_String();
        for(int i = 0; i < size; i++) {
            new_arraylist.add(the_next_string.next_string());
        }	

        long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_arraylist.iterator();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_listIterator(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_listIterator_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_remove_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_set_int_Object(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_size(int count, int size) {
        ArrayList<String> new_arraylist = new ArrayList<String>();
        Get_Next_String the_next_string = new Get_Next_String();
        for(int i = 0; i < size; i++) {
            new_arraylist.add(the_next_string.next_string());
        }

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_arraylist.size();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_sort_Comparator(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_subList_int_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toArray_ObjectARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
