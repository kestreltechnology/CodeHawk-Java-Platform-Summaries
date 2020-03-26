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

import java.lang.Runnable;

public class jl_Thread_Timings extends Java_Timings{
	public jl_Thread_Timings(boolean verbose) {
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
			case "init_Runnable" :
				total_time = run_init_Runnable(repeats);
				break;
			case "currentThread" :
				total_time = run_currentThread(repeats);
				break;
			case "dumpStack" :
				total_time = run_dumpStack(repeats);
				break;
			case "getContextClassLoader" :
				total_time = run_getContextClassLoader(repeats);
				break;
			case "getThreadGroup" :
				total_time = run_getThreadGroup(repeats);
				break;
			case "interrupt" :
				total_time = run_interrupt(repeats, (long)nvals[0]);
				break;
			case "isAlive" :
				total_time = run_isAlive(repeats, (long)nvals[0]);
				break;
			case "join" :
				total_time = run_join(repeats);
				break;
			case "setDaemon_boolean" :
				total_time = run_setDaemon_boolean(repeats);
				break;
			case "setPriority_int" :
				total_time = run_setPriority_int(repeats, (long)nvals[0], (int)nvals[1]);
				break;
			case "sleep_long" :
				total_time = run_sleep_long(repeats, (long)nvals[0]);
				break;
			case "start" :
				total_time = run_start(repeats);
				break;
			case "stop" :
				total_time = run_stop(repeats);
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

	public static long run_init_Runnable(int count) {
		Test_Thread a_test_thread = new Test_Thread();
		Thread a_thread;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			a_thread = new Thread(a_test_thread);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
			try {
				a_thread.join();
			}
			catch(InterruptedException e){
				return -1;
			}
		}
		return total_time / count;
	}

	public static long run_currentThread(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Thread.currentThread();		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_dumpStack(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			Thread.dumpStack();	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_getContextClassLoader(int count) {
		Test_Thread a_test_thread = new Test_Thread();
		Thread a_thread = new Thread(a_test_thread);

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			a_thread.getContextClassLoader();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
			try {
				a_thread.join();
			}
			catch(InterruptedException e){
				return -1;
			}			
		}
		return total_time / count;
	}

	public static long run_getThreadGroup(int count) {
		Test_Thread a_test_thread = new Test_Thread();
		Thread a_thread = new Thread(a_test_thread);	

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			a_thread.getThreadGroup();
			end_time = System.nanoTime();
			total_time += end_time - start_time;
			try {
				a_thread.join();
			}
			catch(InterruptedException e){
				return -1;
			}	
		}
		return total_time / count;
	}

	public static long run_interrupt(int count, long timing) {
		Slow_Thread a_test_Thread;
		Thread a_thread;		

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			a_test_Thread = new Slow_Thread(timing);
			a_thread = new Thread(a_test_Thread);
			a_thread.start();

			start_time = System.nanoTime();
			a_thread.interrupt();
			end_time = System.nanoTime();
			total_time += end_time - start_time;

			while(a_thread.isAlive()) {}
		}
		return total_time / count;
	}

	public static long run_isAlive(int count, long timing) {
		Slow_Thread a_test_Thread;
		Thread a_thread;			

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			a_test_Thread = new Slow_Thread(timing);
			a_thread = new Thread(a_test_Thread);
			a_thread.start();

			start_time = System.nanoTime();
			a_thread.isAlive();
			end_time = System.nanoTime();
			total_time += end_time - start_time;

			try {
				a_thread.join();
			}
			catch(InterruptedException e){
				return -1;
			}	
		}
		return total_time / count;
	}

	public static long run_join(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
			/*Timing depends entirely on the thread in question */	
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_setDaemon_boolean(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_setPriority_int(int count, long timing, int priority) {
		Slow_Thread a_test_Thread;
		Thread a_thread;

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			a_test_Thread = new Slow_Thread(timing);
			a_thread = new Thread(a_test_Thread);
			a_thread.start();

			start_time = System.nanoTime();
			a_thread.setPriority(priority);
			end_time = System.nanoTime();
			total_time += end_time - start_time;
			try {
				a_thread.join();
			}
			catch(InterruptedException e){
				return -1;
			}	
		}
		return total_time / count;
	}

	public static long run_sleep_long(int count, long timing) {
		try {
			long start_time, end_time, total_time = 0;
			for(int i = 0; i < count; i++) {
				start_time = System.nanoTime();
				Thread.sleep(timing);
				end_time = System.nanoTime();
				total_time += end_time - start_time;
			}
			return total_time / count;
		} catch(InterruptedException e) {
			System.out.println("Interrupted : That wasn't supposed to happen");
			return -1000;
		}
	}

	public static long run_start(int count) {
		Test_Thread a_test_thread = new Test_Thread();
		Thread a_thread;		

		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			a_thread = new Thread(a_test_thread);

			start_time = System.nanoTime();
			a_thread.start();		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
			try {
				a_thread.join();
			}
			catch(InterruptedException e){
				return -1;
			}	
		}
		return total_time / count;
	}

	public static long run_stop(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
