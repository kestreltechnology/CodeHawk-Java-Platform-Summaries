/* ------------------------------------------------------------------------------
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

import java.util.Arrays;
import java.nio.CharBuffer;

public class jn_CharBuffer_Timings extends Java_Timings{
    public jn_CharBuffer_Timings(boolean verbose) {
        super(verbose);
    }

    public long get_timing(int repeats, String selection, Value_Store values){
        double[] nvals = values.numeric_vals;

        long total_time = 0;
        switch(selection) {
            case "emptyloop" :
                total_time = run_emptyloop(repeats);
                break;
            case "equals_object":
                total_time = run_equals_object(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
                break;
            default:
                System.out.format("Couldn't find method %s, falling through\n", selection);
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

    public static long run_equals_object(int count, int charbuffer_size1, int charbuffer_size2, int offset) {
        CharBuffer charbuffer_1 = CharBuffer.allocate(charbuffer_size1);
        CharBuffer charbuffer_2 = CharBuffer.allocate(charbuffer_size2);
        char[] array1 = new char[charbuffer_size1];
        char[] array2 = new char[charbuffer_size2];
        Arrays.fill(array1, 'a');
        Arrays.fill(array2, 'a');
        if(offset > -1 && offset < array2.length){array2[offset] = 'b';}
        charbuffer_1 = charbuffer_1.wrap(array1);
        charbuffer_2 = charbuffer_2.wrap(array2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            charbuffer_1.equals(charbuffer_2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
}
