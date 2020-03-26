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

import java.io.UnsupportedEncodingException;

public class jl_String_Timings extends Java_Timings{
	public jl_String_Timings(boolean verbose) {
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
            case "init":
                total_time = run_init(repeats);
                break;
			case "init_charARRAY" :
				total_time = run_init_charARRAY(repeats, (int)nvals[0]);
				break;
			case "init_charARRAY_int_int" :
				total_time = run_init_charARRAY_int_int(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
				break;
			case "charAt_int" :
				total_time = run_charAt_int(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "compareTo_String" :
				total_time = run_compareTo_String(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
				break;
			case "compareToIgnoreCase_String" :
				total_time = run_compareToIgnoreCase_String(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
				break;
			case "contains_CharSequence" :
				total_time = run_contains_CharSequence(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
				break;
			case "endsWith_String" :
				total_time = run_endsWith_String(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
				break;
			case "equals_object" :
                total_time = run_equals_object(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
                break;
			case "equalsIgnoreCase_String" :
				total_time = run_equalsIgnoreCase_String(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
				break;
			case "format_String_ObjectARRAY" :
				total_time = run_format_String_ObjectARRAY(repeats);
				break;
			case "getBytes" :
				total_time = run_getBytes(repeats, (int)nvals[0]);
				break;
			case "getBytes_String" :
				total_time = run_getBytes_String(repeats, (int)nvals[0], svals[0]);
				break;
			case "getBytes_Charset" :
				total_time = run_getBytes_Charset(repeats, (int)nvals[0], svals[0]);
				break;
			case "hashCode" :
				total_time = run_hashCode(repeats, (int)nvals[0]);
				break;
			case "indexOf_int" :
				total_time = run_indexOf_int(repeats, (int)nvals[0], (int)nvals[1], svals[0]);
				break;
			case "indexOf_int_int" :
				total_time = run_indexOf_int_int(repeats, (int)nvals[0], (int)nvals[1], svals[0], (int)nvals[2]);
				break;
			case "indexOf_String" :
				total_time = run_indexOf_String(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
				break;
			case "indexOf_String_int" :
				total_time = run_indexOf_String_int(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3], (int)nvals[4]);
				break;
			case "isEmpty" :
				total_time = run_isEmpty(repeats, (int)nvals[0]);
				break;
			case "lastIndexOf_int" :
				total_time = run_lastIndexOf_int(repeats, (int)nvals[0], (int)nvals[1], svals[0]);
				break;
			case "lastIndexOf_String" :
				total_time = run_lastIndexOf_String(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
				break;
			case "length" :
				total_time = run_length(repeats, (int)nvals[0]);
				break;
			case "matches_String" :
				total_time = run_matches_String(repeats);
				break;
			case "replace_CharSequence_CharSequence" :
				total_time = run_replace_CharSequence_CharSequence(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
				break;
			case "replaceAll_String_String" :
				total_time = run_replaceAll_String_String(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
				break;
			case "replaceFirst_String_String" :
				total_time = run_replaceFirst_String_String(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
				break;
			case "split_String" :
				total_time = run_split_String(repeats, svals[0], svals[1]);
				break;
			case "startsWith_String" :
				total_time = run_startsWith_String(repeats, svals[0], svals[1]);
				break;
			case "substring_int" :
				total_time = run_substring_int(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "substring_int_int" :
				total_time = run_substring_int_int(repeats, svals[0], (int)nvals[0], (int)nvals[1]);
				break;
			case "toCharArray" :
				total_time = run_toCharArray(repeats, (int)nvals[0]);
				break;
			case "toLowerCase" :
				total_time = run_toLowerCase(repeats, svals[0]);
				break;
			case "toLowerCase_Locale" :
				total_time = run_toLowerCase_Locale(repeats);
				break;
			case "toString" :
				total_time = run_toString(repeats, (int)nvals[0]);
				break;
			case "toUpperCase" :
				total_time = run_toUpperCase(repeats, svals[0]);
				break;
			case "trim" :
				total_time = run_trim(repeats, svals[0]);
				break;
			case "valueOf_char" :
				total_time = run_valueOf_char(repeats, svals[0]);
				break;
			case "valueOf_charARRAY" :
				total_time = run_valueOf_charARRAY(repeats, (int)nvals[0]);
				break;
			case "valueOf_int" :
				total_time = run_valueOf_int(repeats, (int)nvals[0]);
				break;
			case "valueOf_Object" :
				total_time = run_valueOf_Object(repeats);
				break;
			case "valueOf_long" :
				total_time = run_valueOf_long(repeats, (long)nvals[0]);
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
		String new_string = new String();

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new String();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_init_charARRAY(int count, int size) {
		char[] new_array = Utilities.get_uniform_chararray(size);
		String new_string;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_string = new String(new_array);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_init_charARRAY_int_int(int count, int size, int offset, int length) {
		char[] new_array = Utilities.get_uniform_chararray(size);
		String new_string;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_string = new String(new_array, offset, length);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_charAt_int(int count, int size, int index) {
		String new_string = Utilities.get_some_string(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_string.charAt(index);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_compareTo_String(int count, int size1, int offset1, int size2, int offset2) {
        String string1 = Utilities.get_oneoff_string(size1, offset1);
        String string2 = Utilities.get_oneoff_string(size2, offset2);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			string1.compareTo(string2);	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_compareToIgnoreCase_String(int count, int size1, int offset1, int size2, int offset2) {
        String string1 = Utilities.get_oneoff_string(size1, offset1);
        String string2 = Utilities.get_oneoff_string(size2, offset2);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			string1.compareToIgnoreCase(string2);	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_contains_CharSequence(int count, int size1, int offset1, int size2, int offset2) {
		String string1 = Utilities.get_oneoff_string(size1, offset1);
        String string2 = Utilities.get_oneoff_string(size2, offset2);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			string1.contains(string2);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_endsWith_String(int count, int size1, int offset1, int size2, int offset2) {
		String string1 = Utilities.get_oneoff_string(size1, offset1);
        String string2 = Utilities.get_oneoff_string(size2, offset2);	

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			string1.endsWith(string2);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

    public static long run_equals_object(int count, int initial_size, int index, int parameter) {
        String source_string = Utilities.get_some_string(initial_size);
        Object some_object;
        try {
            some_object = Utilities.get_some_object(index, parameter);

            long start_time, end_time, total_time = 0;
            for(int i = 0; i < count; i++) {
                start_time = System.nanoTime();
                source_string.equals(some_object);
                end_time = System.nanoTime();
                total_time += end_time - start_time;
            }
            return total_time / count;
        }
        catch(BadParameterException e) {
            return -1;
        }
    }

	public static long run_equalsIgnoreCase_String(int count, int size1, int offset1, int size2, int offset2) {
		String string1 = Utilities.get_oneoff_string(size1, offset1);
        String string2 = Utilities.get_oneoff_string(size2, offset2);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			string1.equalsIgnoreCase(string2);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_format_String_ObjectARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_getBytes(int count, int size) {
		String new_string = Utilities.get_some_string(size);
		byte[] new_bytes;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_bytes = new_string.getBytes();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_getBytes_String(int count, int size, String locale) {
		String new_string = Utilities.get_some_string(size);
		byte[] new_bytes;

		try {
			long start_time, end_time, total_time = 0;

			for(int i = 0; i < count; i++) {
				start_time = System.nanoTime();
				new_bytes = new_string.getBytes(locale);
				end_time = System.nanoTime();
				total_time += end_time - start_time;
			}

			return total_time / count;
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException!");
			return -1000;	
		}
	}

	public static long run_getBytes_Charset(int count, int size, String locale) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_hashCode(int count, int size) {
        String new_string = Utilities.get_some_string(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
            new_string.hashCode();	        	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_indexOf_int(int count, int size1, int offset1, String initial_char) {
        String new_string = Utilities.get_oneoff_string(size1, offset1);
        int new_codePoint = initial_char.codePointAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_string.indexOf(new_codePoint);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_indexOf_int_int(int count, int size1, int offset1, String initial_char, int fromIndex) {
        String new_string = Utilities.get_oneoff_string(size1, offset1);
        int new_codePoint = initial_char.codePointAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_string.indexOf(new_codePoint, fromIndex);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_indexOf_String(int count, int size1, int offset1, int size2, int offset2) {
		String string1 = Utilities.get_oneoff_string(size1, offset1);
        String string2 = Utilities.get_oneoff_string(size2, offset2);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    string1.indexOf(string2);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_indexOf_String_int(int count, int size1, int offset1, int size2, int offset2, int fromindex) {
		String string1 = Utilities.get_oneoff_string(size1, offset1);
        String string2 = Utilities.get_oneoff_string(size2, offset2);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    string1.indexOf(string2, fromindex);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_isEmpty(int count, int size) {
        String new_string = Utilities.get_some_string(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    new_string.isEmpty();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_lastIndexOf_int(int count, int size, int offset, String initial_char) {
		String new_string = Utilities.get_oneoff_string(size, offset);
        int new_char = initial_char.codePointAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_string.lastIndexOf(new_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_lastIndexOf_String(int count, int size, int offset, int size2, int offset2) {
		String new_string = Utilities.get_oneoff_string(size, offset);
        String substring = Utilities.get_oneoff_string(size2, offset2);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_string.lastIndexOf(substring);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_length(int count, int size) {
		String new_string = Utilities.get_some_string(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_string.length();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_matches_String(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_replace_CharSequence_CharSequence(int count, int size, int size1, int size2) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_replaceAll_String_String(int count, int size, int size1, int size2) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_replaceFirst_String_String(int count, int size, int size1, int size2) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_split_String(int count, String initial_string, String split) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_startsWith_String(int count, String initial_string, String initial) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_substring_int(int count, int initial_size, int offset) {
		String test_string = Utilities.get_some_string(initial_size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			test_string.substring(offset);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_substring_int_int(int count, String initial_string, int x, int y) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toCharArray(int count, int size) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toLowerCase(int count, String initial_string) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toLowerCase_Locale(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toString(int count, int size) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toUpperCase(int count, String initial_string) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_trim(int count, String initial_string) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_valueOf_char(int count, String initial_char) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_valueOf_charARRAY(int count, int size) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_valueOf_int(int count, int initial_int) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		    String.valueOf(initial_int);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_valueOf_Object(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_valueOf_long(int count, long initial_long) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
