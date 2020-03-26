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

public class cgp_GeneratedMessageV3_Timings extends Java_Timings{
	public cgp_GeneratedMessageV3_Timings(boolean verbose) {
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
			case "computeStringSize_int_Object" :
				total_time = run_computeStringSize_int_Object(repeats);
				break;
			case "computeStringSizeNoTag_Object" :
				total_time = run_computeStringSizeNoTag_Object(repeats);
				break;
			case "makeExtensionsImmutable" :
				total_time = run_makeExtensionsImmutable(repeats);
				break;
			case "parseDelimitedWithIOException_Parser_InputStream" :
				total_time = run_parseDelimitedWithIOException_Parser_InputStream(repeats);
				break;
			case "parseDelimitedWithIOException_Parser_InputStream_ExtensionRegistryLite" :
				total_time = run_parseDelimitedWithIOException_Parser_InputStream_ExtensionRegistryLite(repeats);
				break;
			case "parseUnknownField_CodedInputStream_UnknownFieldSet$Builder_ExtensionRegistryLite_int" :
				total_time = run_parseUnknownField_CodedInputStream_UnknownFieldSet$Builder_ExtensionRegistryLite_int(repeats);
				break;
			case "parseWithIOException_Parser_CodedInputStream" :
				total_time = run_parseWithIOException_Parser_CodedInputStream(repeats);
				break;
			case "parseWithIOException_Parser_CodedInputStream_ExtensionRegistryLite" :
				total_time = run_parseWithIOException_Parser_CodedInputStream_ExtensionRegistryLite(repeats);
				break;
			case "parseWithIOException_Parser_InputStream" :
				total_time = run_parseWithIOException_Parser_InputStream(repeats);
				break;
			case "parseWithIOException_Parser_InputStream_ExtensionRegistryLite" :
				total_time = run_parseWithIOException_Parser_InputStream_ExtensionRegistryLite(repeats);
				break;
			case "writeString_CodedOutputStream_int_Object" :
				total_time = run_writeString_CodedOutputStream_int_Object(repeats);
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

	public static long run_computeStringSize_int_Object(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_computeStringSizeNoTag_Object(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_makeExtensionsImmutable(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_parseDelimitedWithIOException_Parser_InputStream(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_parseDelimitedWithIOException_Parser_InputStream_ExtensionRegistryLite(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_parseUnknownField_CodedInputStream_UnknownFieldSet$Builder_ExtensionRegistryLite_int(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_parseWithIOException_Parser_CodedInputStream(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_parseWithIOException_Parser_CodedInputStream_ExtensionRegistryLite(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_parseWithIOException_Parser_InputStream(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_parseWithIOException_Parser_InputStream_ExtensionRegistryLite(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}

	public static long run_writeString_CodedOutputStream_int_Object(int count) {
		long start_time, end_time, total_time = 0;
		for(int i = 0; i < count; i++) {
			start_time = System.nanoTime();
		
			end_time = System.nanoTime();
			total_time += end_time - start_time;
		}
		return total_time / count;
	}
}
