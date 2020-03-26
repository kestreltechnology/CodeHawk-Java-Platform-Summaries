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

import java.lang.String;
import java.lang.Character;

public class jl_Character_Timings extends Java_Timings{
	public jl_Character_Timings(boolean verbose) {
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
			case "init_char" :
				total_time = run_init_char(repeats, svals[0]);
				break;
			case "charValue" :
				total_time = run_charValue(repeats, svals[0]);
				break;
			case "digit_char_int" :
				total_time = run_digit_char_int(repeats, svals[0], (int)nvals[0]);
				break;
			case "forDigit_int_int" :
				total_time = run_forDigit_int_int(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "isAlphabetic_int" :
				total_time = run_isAlphabetic_int(repeats, (int)nvals[0]);
				break;
			case "isDigit_char" :
				total_time = run_isDigit_char(repeats, svals[0]);
				break;
			case "isLowerCase_char" :
				total_time = run_isLowerCase_char(repeats, svals[0]);
				break;
			case "isUpperCase_char" :
				total_time = run_isUpperCase_char(repeats, svals[0]);
				break;
			case "isWhitespace_char" :
				total_time = run_isWhitespace_char(repeats, svals[0]);
				break;
			case "toLowerCase_char" :
				total_time = run_toLowerCase_char(repeats, svals[0]);
				break;
			case "toString_char" :
				total_time = run_toString_char(repeats, svals[0]);
				break;
			case "toUpperCase_char" :
				total_time = run_toUpperCase_char(repeats, svals[0]);
				break;
			case "valueOf_char" :
				total_time = run_valueOf_char(repeats, svals[0]);
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

	public static long run_init_char(int count, String initial_char) {
		char new_char = initial_char.charAt(0);
		Character new_acter;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_acter = new Character(new_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_charValue(int count, String initial_char) {
		Character new_char = new Character(initial_char.charAt(0));

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_char.charValue();	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_digit_char_int(int count, String initial_char, int radix) {
		char new_char = initial_char.charAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.digit(new_char, radix);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_forDigit_int_int(int count, int digit, int radix) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.forDigit(digit, radix);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_isAlphabetic_int(int count, int initial_char) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.isAlphabetic(initial_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_isDigit_char(int count, String initial_char) {
		char new_char = initial_char.charAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.isDigit(new_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_isLowerCase_char(int count, String initial_char) {
		char new_char = initial_char.charAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.isLowerCase(new_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_isUpperCase_char(int count, String initial_char) {
		char new_char = initial_char.charAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.isUpperCase(new_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_isWhitespace_char(int count, String initial_char) {
		char new_char = initial_char.charAt(0);
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.isWhitespace(new_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toLowerCase_char(int count, String initial_char) {
		char new_char = initial_char.charAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.toLowerCase(new_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toString_char(int count, String initial_char) {
		char new_char = initial_char.charAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.toString(new_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toUpperCase_char(int count, String initial_char) {
		char new_char = initial_char.charAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.toUpperCase(new_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_valueOf_char(int count, String initial_char) {
		char new_char = initial_char.charAt(0);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Character.valueOf(new_char);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
