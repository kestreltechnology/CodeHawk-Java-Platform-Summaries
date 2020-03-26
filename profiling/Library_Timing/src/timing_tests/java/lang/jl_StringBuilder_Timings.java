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

import java.lang.StringBuilder;
import java.lang.Math;
import java.util.TreeMap;
import org.apache.commons.cli.*;

public class jl_StringBuilder_Timings extends Java_Timings{
    public jl_StringBuilder_Timings(boolean verbose) {
        super(verbose);
    }

    public long get_timing(int repeats, String selection, Value_Store values){
        double[] nvals = values.numeric_vals;
        long total_time = 0; 
        switch(selection){
            /* test */
            case "emptyloop" :
                total_time = run_emptyloop(repeats);
                break;
            /* java.langStringBuilder.append(Ljava/lang/String:)Java/lang/StringBuilder; */
            case "append_string" : 
                total_time = run_stringbuilder_append_string(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            /* java.lang.StringBuilder.toString()Ljava/lang/String; */
          	case "toString" : 
                total_time = run_stringbuilder_toString(repeats, (int)nvals[0]);
                break;
            case "stringbuilder_toString_from_parts":
                total_time = run_stringbuilder_toString_from_parts(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            /* java.lang.StringBuilder.<init>()V */
            case "init" : 
                total_time =  run_stringbuilder_init(repeats);
                break;
            /* java.lang.StringBuilder.append(I)Ljava/lang/StringBuilder; */
            case "append_int" :
                total_time = run_stringbuilder_append_int(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            /* java.lang.StringBuilder.append(Ljava/lang/Object;)Ljava/lang/StringBuilder; */
            case "append_object" : run_stringbuilder_append_object(repeats);
                /* TODO */
                break;
            /* java.lang.StringBuilder.append(C)Ljava/lang/StringBuilder; */
            case "append_char" :
                total_time = run_stringbuilder_append_char(repeats, (int)nvals[0]);
                break; 
            /* java.lang.StringBuilder.append(J)Ljava/lang/StringBuilder; */
            case "append_long" :
                total_time = run_stringbuilder_append_long(repeats, (int)nvals[0], (long)nvals[1]);
                break;
            /* java.lang.StringBuilder.append(D)LJava/lang/StringBuilder */
            case "append_double" :
                total_time = run_stringbuilder_append_double(repeats, (int)nvals[0], (double)nvals[1]);
                break;
            /* java.lang.StringBuilder.append(Ljava/lang/CharSequence;II)Ljava/lang/StringBuilder; */
            case "append_charsequence_int_int" : run_stringbuilder_append_charsequence_int_int(repeats);
                /* TODO */
                break;
           	/* java.lang.StringBuilder.append(Z)Ljava/lang/StringBuilder; */
            case "append_boolean" :
                total_time = run_stringbuilder_append_boolean(repeats, (int)nvals[0]);
                break;
            /* java.lang.StringBuilder.append(F)Ljava/lang/StringBuilder */
            case "append_float" :
                total_time = run_stringbuilder_append_float(repeats, (int)nvals[0], (float)nvals[1]);
                break;
            /* java.lang.StringBuilder.<init>(I)V */
            case "init_int" :
                total_time = run_stringbuilder_init_int(repeats, (int)nvals[0]);
                break;
            /* java.lang.StringBuilder.<init>(Ljava/lang/String;)V */
            case "init_string" : 
                total_time = run_stringbuilder_init_string(repeats, (int)nvals[0]);
                break;
            /* java.lang.StringBuilder.length()I */
            case "length" : 
                total_time = run_stringbuilder_length(repeats, (int)nvals[0]);
                break;
            /* java.lang.StringBuilder.append([CII)Ljava/lang/StringBuilder; */
            case "append_chararray_int_int" : 
                total_time = run_stringbuilder_append_chararray_int_int(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
          	    break;     
           	/* java.lang.StringBuilder.setLength(I)V */
           	case "setLength" : 
                total_time = run_stringbuilder_setLength(repeats, (int)nvals[0], (int)nvals[1]);
          	    break;
            /* java.lang.StringBuilder.delete(II)Ljava/lang/StringBuilder; */
            case "delete" : 
                total_time = run_stringbuilder_delete(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
                break;
            /* java.lang.StringBuilder.indexOf(Ljava/lang/String;I)I */
            case "indexOf" : 
                total_time = run_stringbuilder_indexOf(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
          	    break;
            /* java.lang.StringBuilder.substring(II)Ljava/lang/String; */
            case "substring" : 
                total_time = run_stringbuilder_substring(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
                break;
            /* java.lang.StringBuilder.appendCodePoint(I)Ljava/lang/StringBuilder; */
            case "appendCodePoint" : 
                total_time = run_stringbuilder_appendCodePoint(repeats, (int)nvals[0], (int)nvals[1]);
          	    break;
            /* java.lang.StringBuilder.append([C)Ljava/lang/StringBuilder; */
            case "append_chararray" : 
                total_time = run_stringbuilder_append_chararray(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            /* java.lang.StringBuilder.replace(IILjava/lang/String;)Ljava/lang/StringBuilder; */
            case "replace" : 
                total_time = run_stringbuilder_replace(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
                break;
            default: 
                System.out.println("Couldn't find method specified, falling through");
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

    public static long run_stringbuilder_append_string(int count, int initial_size, int append_size){
        String target_string = Utilities.get_some_string(initial_size);
        String source_string = Utilities.get_some_string(append_size);
        StringBuilder stringy;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(target_string);
            start_time = System.nanoTime();
            stringy.append(source_string);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
     
    public static long run_stringbuilder_toString(int count, int initial_size){
        /* TODO : What if this depends on the structure of the StringBuilder */

        String source_string = Utilities.get_some_string(initial_size);
        StringBuilder stringy;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(source_string);
            start_time = System.nanoTime();
            stringy.toString();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_toString_from_parts(int count, int initial_size, int part_size){
        String source_string = Utilities.get_some_string(part_size);
        StringBuilder stringy = new StringBuilder();
        
        for(int i = 0; i < (initial_size / part_size); i++) {
            stringy.append(source_string);
        }
 
        long start_time = System.nanoTime();
        for(int i = 0; i < count; i++) {
            stringy.toString();
        }
        long end_time = System.nanoTime();
	    
        return end_time - start_time;
    }

    public static long run_stringbuilder_init(int count){
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++){
            start_time = System.nanoTime();
            StringBuilder stringy = new StringBuilder();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }

        return total_time / count;
    }

    public static long run_stringbuilder_append_int(int count, int initial_size, int append_value){
        String target_string = Utilities.get_some_string(initial_size);
        StringBuilder stringy;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(target_string);
            start_time = System.nanoTime();
            stringy.append(append_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_append_object(int count){return 0;}

    public static long run_stringbuilder_append_char(int count, int initial_size){
        String source_string = Utilities.get_some_string(initial_size);

        StringBuilder stringy;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(source_string);
            start_time = System.nanoTime();
            stringy.append('a');
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_append_long(int count, int initial_size, long append_value){
        String source_string = Utilities.get_some_string(initial_size);
       
        StringBuilder stringy;
 
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(source_string);
            start_time = System.nanoTime();
            stringy.append(append_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_append_double(int count, int initial_size, double append_value){
        String source_string = Utilities.get_some_string(initial_size);

        StringBuilder stringy;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(source_string);
            start_time = System.nanoTime();
            stringy.append(append_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_append_charsequence_int_int(int count){return 0;}

    public static long run_stringbuilder_append_boolean(int count, int initial_size){
        boolean [] bools = Utilities.get_random_bools(count);
        String source_string = Utilities.get_some_string(initial_size);
	    boolean testbool = bools[0];

        StringBuilder stringy;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(source_string);
            start_time = System.nanoTime();
            stringy.append(testbool);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_append_float(int count, int initial_size, float append_value){
        String source_string = Utilities.get_some_string(initial_size);
    
        StringBuilder stringy;
 
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++){
            stringy = new StringBuilder(source_string);
            start_time = System.nanoTime();
            stringy.append(append_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_init_int(int count, int value){
       long start_time, end_time, total_time = 0;
       for(int j = 0; j < count; j++)
       {
            start_time = System.nanoTime();
            StringBuilder string = new StringBuilder(value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
       }
       return total_time / count;
    }

    public static long run_stringbuilder_init_string(int count, int stringsize){
       String target_string = Utilities.get_some_string(stringsize);
       /* TODO : Make strings actually vary */

       long start_time, end_time, total_time = 0;
       for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            StringBuilder stringy = new StringBuilder(target_string);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
       }
       return total_time / count;
    }

    public static long run_stringbuilder_length(int count, int initial_size){
        StringBuilder stringy = new StringBuilder(initial_size);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            stringy.length();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }     
        return total_time / count;
    }

    public static long run_stringbuilder_append_chararray_int_int(int count, int initial_size, int initial_size_2, int segment_start, int segment_size){
        String test_string = Utilities.get_some_string(initial_size);
        char[] test_chars = Utilities.get_uniform_chararray(initial_size_2);

        /* Check that the region with the chararray is legal */
        if(initial_size_2 < segment_start + segment_size) {
            System.out.println("Segment mismatch");
            return -1l;
        }

        StringBuilder stringy;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(test_string);
            start_time = System.nanoTime();
            stringy.append(test_chars, segment_start, segment_size);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_setLength(int count, int initial_size, int new_size){
        String source_string = Utilities.get_some_string(initial_size);

        StringBuilder stringy;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(source_string);
            start_time = System.nanoTime();
            stringy.setLength(new_size);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_delete(int count, int initial_size, int segment_start, int segment_size){
        int segment_end = segment_start + segment_size;
        String test_string = Utilities.get_some_string(initial_size);
        StringBuilder stringy;        

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(test_string);
            start_time = System.nanoTime();
            stringy.delete(segment_start, segment_end);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_indexOf(int count, int initial_size, int initial_size_2, int offset, int startpoint){
        String test_string = Utilities.get_oneoff_string(initial_size, offset);
        String other_string = Utilities.get_oneoff_string(initial_size_2, 0);
        StringBuilder stringy;       

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(test_string);
            start_time = System.nanoTime();
            stringy.indexOf(other_string, startpoint);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_substring(int count, int initial_size, int segment_start, int segment_size){
        int segment_end = segment_start + segment_size;
        String test_string = Utilities.get_some_string(initial_size);
        StringBuilder stringy;

        /* Check that substring is legal */
        if(initial_size < segment_end) {
            System.out.println("Segment mismatch");
            return -1l;
        }

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(test_string);
            start_time = System.nanoTime();
            stringy.substring(segment_start, segment_end);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_appendCodePoint(int count, int initial_size, int append_value){
        String source_string = Utilities.get_some_string(initial_size);
        StringBuilder stringy;       
 
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(source_string);
            start_time = System.nanoTime();
            stringy.appendCodePoint(append_value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_append_chararray(int count, int initial_size, int append_value){
        String source_string = Utilities.get_some_string(initial_size);
        char[] chars = Utilities.get_uniform_chararray(append_value);
        StringBuilder stringy;       
 
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(source_string.length() + append_value);
            stringy.append(source_string);

            start_time = System.nanoTime();
            stringy.append(chars);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_stringbuilder_replace(int count, int initial_size, int segment_start, int segment_size, int initial_size_2){
        int segment_end = segment_start + segment_size;
        String test_string = Utilities.get_some_string(initial_size);
        String new_string = Utilities.get_some_string(initial_size_2);
        StringBuilder stringy;        

        /* Check that replacement is legal */
        if(initial_size < segment_end) {
            System.out.println("Segment mismatch");
            return -1l;
        }

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            stringy = new StringBuilder(test_string);
            start_time = System.nanoTime();
            stringy.replace(segment_start, segment_end, new_string);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
}
