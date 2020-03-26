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

import java.util.AbstractMap;
import java.util.HashMap;

public class ju_AbstractMap_Timings extends Java_Timings{
    public ju_AbstractMap_Timings(boolean verbose) {
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

    /* Because AbstractMap is abstract, we cannot call its equals method directly.
    *  Instead we compare HashMaps, which inherit their equals method from AbstractMap
    */
 
    public static long run_equals_object(int count, int hashmap_size1, int hashmap_size2, int offset) {
        HashMap hashmap_1 = Utilities.get_some_hashmap(hashmap_size1, -1);
        HashMap hashmap_2 = Utilities.get_some_hashmap(hashmap_size2, offset);

        System.out.format("%b\n", hashmap_1.size() == hashmap_2.size());

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            hashmap_1.equals(hashmap_2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
}
