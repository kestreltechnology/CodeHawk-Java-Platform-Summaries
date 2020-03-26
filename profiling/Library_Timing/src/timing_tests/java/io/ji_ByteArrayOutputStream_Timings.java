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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ji_ByteArrayOutputStream_Timings extends Java_Timings{
	public ji_ByteArrayOutputStream_Timings(boolean verbose) {
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
			case "close" :
				total_time = run_close(repeats, (int)nvals[0]);
				break;
			case "flush" :
				total_time = run_flush(repeats);
				break;
			case "size" :
				total_time = run_size(repeats, (int)nvals[0]);
				break;
			case "toByteArray" :
				total_time = run_toByteArray(repeats, (int)nvals[0]);
				break;
			case "write_byteARRAY" :
				total_time = run_write_byteARRAY(repeats);
				break;
			case "write_byteARRAY_int_int" :
				total_time = run_write_byteARRAY_int_int(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
				break;
			case "write_int" :
				total_time = run_write_int(repeats, (int)nvals[0], (int)nvals[1]);
				break;
			case "writeTo_OutputStream" :
				total_time = run_writeTo_OutputStream(repeats);
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
		ByteArrayOutputStream new_stream;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_stream = new ByteArrayOutputStream();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_close(int count, int size) {
		try {
			ByteArrayOutputStream new_baos = Utilities.get_some_bytearrayoutputstream(size);

			long start_time, end_time, total_time = 0;
			for(int i = 0; i < count; i++) {
				start_time = System.nanoTime();
				new_baos.close();
				end_time = System.nanoTime();
				total_time += end_time - start_time;
			}
			return total_time / count;
		} catch (IOException e) {
			return -1;	
		}
	}

	public static long run_flush(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_size(int count, int size) {
		ByteArrayOutputStream new_baos = Utilities.get_some_bytearrayoutputstream(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_baos.size();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_toByteArray(int count, int size) {
		ByteArrayOutputStream new_baos = Utilities.get_some_bytearrayoutputstream(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_baos.toByteArray();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_write_byteARRAY(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_write_byteARRAY_int_int(int count, int size, int bytearraysize, int offset, int length) {
		ByteArrayOutputStream new_baos = Utilities.get_some_bytearrayoutputstream(size);
		byte[] new_bytearray = new byte[bytearraysize];

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_baos.write(new_bytearray, offset, length);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_write_int(int count, int size, int input) {
		ByteArrayOutputStream new_baos = Utilities.get_some_bytearrayoutputstream(size);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			new_baos.write(input);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_writeTo_OutputStream(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
