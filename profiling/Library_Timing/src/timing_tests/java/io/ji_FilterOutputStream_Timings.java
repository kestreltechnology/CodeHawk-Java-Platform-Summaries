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

import java.io.File;
import java.io.FilterOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ji_FilterOutputStream_Timings extends Java_Timings{
	public ji_FilterOutputStream_Timings(boolean verbose) {
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
			case "close" :
				total_time = run_close(repeats);
				break;
			case "flush" :
				total_time = run_flush(repeats);
				break;
			case "write_int" :
				total_time = run_write_int(repeats, (int)nvals[0]);
				break;
			case "write_byteARRAY" :
				total_time = run_write_byteARRAY(repeats, (int)nvals[0]);
				break;
			case "write_byteARRAY_int_int" :
				total_time = run_write_byteARRAY_int_int(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
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

	public static long run_close(int count) {
        File f = Utilities.get_canonical_file();

        try {
		    long start_time, end_time, total_time = 0;
		    for(int i = 0; i < count; i++) {
                f.createNewFile();
                FilterOutputStream new_fos = Utilities.get_some_filteroutputstream(f);

			    start_time = System.nanoTime();
		        new_fos.close();
			    end_time = System.nanoTime();
			    total_time += end_time - start_time;

                Utilities.delete_this_file(f);
		    }
		    return total_time / count;
        }
        catch(IOException e) {
            e.printStackTrace();
            return -1;
        }
	}

	public static long run_flush(int count) {
        File f = Utilities.get_canonical_file();

        try {
		    long start_time, end_time, total_time = 0;
		    for(int i = 0; i < count; i++) {
                f.createNewFile();
                FilterOutputStream new_fos = Utilities.get_some_filteroutputstream(f);

			    start_time = System.nanoTime();
		        new_fos.flush();
			    end_time = System.nanoTime();
			    total_time += end_time - start_time;

                new_fos.close();
                Utilities.delete_this_file(f);
		    }
		    return total_time / count;
        }
        catch(IOException e) {
            e.printStackTrace();
            return -1;
        }
	}

	public static long run_write_int(int count, int value) {
        File f = Utilities.get_canonical_file();

        try {
		    long start_time, end_time, total_time = 0;
		    for(int i = 0; i < count; i++) {
                f.createNewFile();
                FilterOutputStream new_fos = Utilities.get_some_filteroutputstream(f);

			    start_time = System.nanoTime();
		        new_fos.write(value);
			    end_time = System.nanoTime();
			    total_time += end_time - start_time;

                new_fos.close();
                Utilities.delete_this_file(f);
		    }
		    return total_time / count;
        }
        catch(IOException e) {
            e.printStackTrace();
            return -1;
        }
	}

	public static long run_write_byteARRAY(int count, int size) {
        File f = Utilities.get_canonical_file();
        byte[] new_bytearray = new byte[size];

        try {
		    long start_time, end_time, total_time = 0;
		    for(int i = 0; i < count; i++) {
                f.createNewFile();
                FilterOutputStream new_fos = Utilities.get_some_filteroutputstream(f);

			    start_time = System.nanoTime();
		        new_fos.write(new_bytearray);
			    end_time = System.nanoTime();
			    total_time += end_time - start_time;

                new_fos.close();
                Utilities.delete_this_file(f);
		    }
		    return total_time / count;
        }
        catch(IOException e) {
            e.printStackTrace();
            return -1;
        }
	}

	public static long run_write_byteARRAY_int_int(int count, int size, int offset, int length) {
        File f = Utilities.get_canonical_file();
        byte[] new_bytearray = new byte[size];

        try {
		    long start_time, end_time, total_time = 0;
		    for(int i = 0; i < count; i++) {
                f.createNewFile();
                FilterOutputStream new_fos = Utilities.get_some_filteroutputstream(f);

			    start_time = System.nanoTime();
		        new_fos.write(new_bytearray, offset ,length);
			    end_time = System.nanoTime();
			    total_time += end_time - start_time;

                new_fos.close();
                Utilities.delete_this_file(f);
		    }
		    return total_time / count;
        }
        catch(IOException e) {
            e.printStackTrace();
            return -1;
        }
	}
}
