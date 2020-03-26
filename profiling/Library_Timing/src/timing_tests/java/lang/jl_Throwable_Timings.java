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

import java.lang.Throwable;

public class jl_Throwable_Timings extends Java_Timings{
    public Throwable our_throw = new Throwable();

	public jl_Throwable_Timings(boolean verbose) {
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
			case "addSuppressed_Throwable" :
				total_time = run_addSuppressed_Throwable(repeats);
				break;
            case "fillInStackTrace" :
                total_time = run_fillInStackTrace_aux(repeats, (int)nvals[0]);
                break;
            case "fillInStackTrace_2" :
                total_time = run_fillInStackTrace_2_aux(repeats, (int)nvals[0]);
                break;
			case "getCause" :
				total_time = run_getCause(repeats);
				break;
			case "getLocalizedMessage" :
				total_time = run_getLocalizedMessage(repeats);
				break;
			case "getMessage" :
				total_time = run_getMessage(repeats);
				break;
			case "getStackTrace" :
				total_time = run_getStackTrace(repeats);
				break;
			case "printStackTrace" :
				total_time = run_printStackTrace(repeats, (int)nvals[0]);
				break;
			case "printStackTrace_PrintStream" :
				total_time = run_printStackTrace_PrintStream(repeats);
				break;
			case "printStackTrace_PrintWriter" :
				total_time = run_printStackTrace_PrintWriter(repeats);
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

	public static long run_addSuppressed_Throwable(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

    public long run_fillInStackTrace_aux(int count, int depth) {
        int a = 1;
        int b = 2;
        String c = new String("Stringo");
        double d = 1.1;
        float e = 2.4f;
        Object f = new Object();
        int[] g = {1, 2};
        int h = 11;
        int i = 12;
        int j = 13;

        if(depth > 0) {
            long temp_long = run_fillInStackTrace_aux(count, depth - 1);
            /* if(depth == 10) {
                Throwable cur_throw = new Throwable();
                System.out.println("Stored Stack Trace");
                this.our_throw.printStackTrace();
                System.out.println("Current Stack Trace");
                cur_throw.printStackTrace();
            } */
            return temp_long;
        }
        else {
            return run_fillInStackTrace(count);
        }

        
    }

	public long run_fillInStackTrace(int count) {
        Throwable new_throw = new Throwable();
        new_throw.printStackTrace();

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
            new_throw = new Throwable();

			start_time = System.nanoTime();
		    new_throw.fillInStackTrace();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}

        this.our_throw = new_throw;
		return total_time / count;
	}

    public long run_fillInStackTrace_2_aux(int count, int depth) {
        return run_fillInStackTrace_2_aux2(count, depth - 1);
    }

    public long run_fillInStackTrace_2_aux2(int count, int depth) {
        if(depth > 0) {
            return run_fillInStackTrace_2_aux2(count, depth - 1);
        }
        else {
            return run_fillInStackTrace_2(count);
        }
    }

	public long run_fillInStackTrace_2(int count){
        Throwable new_throw = new Throwable();
        int a = 1;
        int b = 2;
        String c = new String("Stringo");
        double d = 1.1;
        float e = 2.4f;
        Object f = new Object();
        int[] g = {1, 2};
        int h = 11;
        int j = 12;
        int k = 13;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
            new_throw = new Throwable();

			start_time = System.nanoTime();
		    new_throw.fillInStackTrace();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}

        this.our_throw = new_throw;
		return total_time / count;
	}


	public static long run_getCause(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_getLocalizedMessage(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_getMessage(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_getStackTrace(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_printStackTrace(int count, int depth) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_printStackTrace_PrintStream(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_printStackTrace_PrintWriter(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
