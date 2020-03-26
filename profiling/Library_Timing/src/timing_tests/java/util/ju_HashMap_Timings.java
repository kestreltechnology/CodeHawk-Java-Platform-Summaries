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

import java.util.HashMap;

public class ju_HashMap_Timings extends Java_Timings{
	public ju_HashMap_Timings(boolean verbose) {
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
			case "init_Map" :
				total_time = run_init_Map(repeats);
				break;
			case "containsKey_Object" :
				total_time = run_containsKey_Object(repeats, (int)nvals[0], svals[0]);
				break;
			case "entrySet" :
				total_time = run_entrySet(repeats);
				break;
			case "get_Object" :
				total_time = run_get_Object(repeats, (int)nvals[0], svals[0]);
				break;
			case "keySet" :
				total_time = run_keySet(repeats, (int)nvals[0]);
				break;
			case "put_Object_Object" :
				total_time = run_put_Object_Object(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "putAll_Map" :
				total_time = run_putAll_Map(repeats);
				break;
			case "remove_Object" :
				total_time = run_remove_Object(repeats);
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
		HashMap<String, String> new_hashmap;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_hashmap = new HashMap<String, String>();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_init_Map(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_containsKey_Object(int count, int size, String target) {
		HashMap<String, String> new_hashmap = new HashMap<String, String>();
		Get_Next_String The_Next_String = new Get_Next_String();
		String next;
		for(int i = 0; i < size; i++) {
			next = The_Next_String.next_string();
			new_hashmap.put(next, next);
		}

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_hashmap.containsKey(target);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_entrySet(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_get_Object(int count, int size, String target) {
		HashMap<String, String> new_hashmap = new HashMap<String, String>();
		Get_Next_String The_Next_String = new Get_Next_String();
		String next;
		for(int i = 0; i < size; i++) {
			next = The_Next_String.next_string();
			new_hashmap.put(next, next);
		}

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_hashmap.get(target);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_keySet(int count, int size) {
		HashMap<String, String> new_hashmap = new HashMap<String, String>();
		Get_Next_String The_Next_String = new Get_Next_String();
		String next;
		for(int i = 0; i < size; i++) {
			next = The_Next_String.next_string();
			new_hashmap.put(next, next);
		}

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_hashmap.keySet();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_put_Object_Object(int count, int size1, int size2) {
		String string1 = Utilities.get_some_string(size1);
		String string2 = Utilities.get_some_string(size2);
		HashMap<String, String> new_hashmap = new HashMap<String, String>();

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_hashmap.put(string1, string2);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_putAll_Map(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_remove_Object(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_size(int count, int size) {
		HashMap<String, String> new_hashmap = new HashMap<String, String>();
		Get_Next_String The_Next_String = new Get_Next_String();
		String next;
		for(int i = 0; i < size; i++) {
			next = The_Next_String.next_string();
			new_hashmap.put(next, next);
		}

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_hashmap.size();	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
