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

/* NOT AN ACTUAL JAVA FILE, USE AS A TEMPLATE */

package Java_Timing;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class jn_ByteBuffer_Timings extends Java_Timings{
    public jn_ByteBuffer_Timings(boolean verbose) {
        super(verbose);
    }

    public long get_timing(int repeats, String selection, Value_Store values){
        double[] nvals = values.numeric_vals;

        long total_time = 0;
        switch(selection) {
            case "emptyloop" :
                total_time = run_emptyloop(repeats);
                break;
            case "allocate_int":
                total_time = run_allocate_int(repeats, (int)nvals[0]);
                break;
            case "allocateDirect_int":
                total_time = run_allocateDirect_int(repeats, (int)nvals[0]);
                break;
            case "array":
                total_time = run_array(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "arrayOffset":
                total_time = run_arrayOffset(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "asIntBuffer":
                total_time = run_asIntBuffer(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "capacity":
                total_time = run_capacity(repeats, (int)nvals[0]);
                break;
            case "clear":
                total_time = run_clear(repeats, (int)nvals[0]);
                break;
            case "flip":
                total_time = run_flip(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "get":
                total_time = run_get(repeats, (int)nvals[0]);
                break;
            case "get_bytearray":
                total_time = run_get_bytearray(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "get_bytearray_int_int":
                total_time = run_get_bytearray_int_int(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
                break;
            case "get_int":
                total_time = run_get_int(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "getInt":
                total_time = run_getInt(repeats, (int)nvals[0]);
                break;
            case "getLong":
                total_time = run_getLong(repeats, (int)nvals[0]);
                break;
            case "getShort":
                total_time = run_getShort(repeats, (int)nvals[0]);
                break;
            case "hasArray":
                total_time = run_hasArray(repeats, (int)nvals[0]);
                break;
            case "isDirect":
                total_time = run_isDirect(repeats, (int)nvals[0]);
                break;
            case "limit":
                total_time = run_limit(repeats, (int)nvals[0]);
                break;
            case "limit_int":
                total_time = run_limit_int(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "order_ByteOrder":
                total_time = run_order_ByteOrder(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "position":
                total_time = run_position(repeats, (int)nvals[0]);
                break;
            case "position_int":
                total_time = run_position_int(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "put_byte":
                total_time = run_put_byte(repeats, (int)nvals[0]);
                break;
            case "put_bytearray":
                total_time = run_put_bytearray(repeats, (int)nvals[0], (int)nvals[2]);
                break;
            case "put_bytearray_int_int":
                total_time = run_put_bytearray_int_int(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2], (int)nvals[3]);
                break;
            case "putInt_int":
                total_time = run_putInt_int(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "putLong_long":
                total_time = run_putLong_long(repeats, (int)nvals[0], (long)nvals[1]);
                break;
            case "putShort_short":
                total_time = run_putShort_short(repeats, (int)nvals[0], (short)nvals[1]);
                break;
            case "remaining":
                total_time = run_remaining(repeats, (int)nvals[0], (int)nvals[1]);
                break;
            case "wrap_bytearray":
                total_time = run_wrap_bytearray(repeats, (int)nvals[0]);
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

    public static long run_allocate_int(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            ByteBuffer.allocate(capacity);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_allocateDirect_int(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            ByteBuffer.allocateDirect(capacity);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_array(int count, int capacity, int fill) {
        byte[] array1 = new byte[fill];
        Arrays.fill(array1, (byte)'a');

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            ByteBuffer buffer1 = ByteBuffer.allocate(capacity);
            buffer1 = buffer1.wrap(array1);    

            start_time = System.nanoTime();
            buffer1.array();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_arrayOffset(int count, int capacity, int index) {
        ByteBuffer buffer1 = ByteBuffer.allocate(capacity);
        buffer1.putChar(index, 'a');

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            buffer1.arrayOffset();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_asIntBuffer(int count, int capacity, int fill) {
        byte[] array1 = new byte[fill];
        Arrays.fill(array1, (byte)'a');

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            ByteBuffer buffer1 = ByteBuffer.allocate(capacity).wrap(array1);

            start_time = System.nanoTime();
            buffer1.asIntBuffer();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_capacity(int count, int capacity) {
        ByteBuffer buffer1 = ByteBuffer.allocate(capacity);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            buffer1.capacity();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_clear(int count, int capacity) {
        ByteBuffer buffer1 = ByteBuffer.allocate(capacity);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            buffer1.clear();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_flip(int count, int capacity, int index) {
        ByteBuffer buffer1 = ByteBuffer.allocate(capacity);
        buffer1.position(index);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            buffer1.flip();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_get_int(int count, int capacity, int index) {
        byte[] array1 = new byte[capacity];
        Arrays.fill(array1, (byte)'a');

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            ByteBuffer buffer1 = ByteBuffer.allocate(capacity).wrap(array1);

            start_time = System.nanoTime();
            buffer1.get(index);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_get_bytearray(int count, int capacity, int size) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_get_bytearray_int_int(int count, int capacity, int size, int offset, int length) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_get(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_getInt(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_getLong(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_getShort(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_hasArray(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_isDirect(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_limit(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_limit_int(int count, int capacity, int value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_order_ByteOrder(int count, int capacity, int value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_position(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_position_int(int count, int capacity, int value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_put_byte(int count, int capacity) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_put_bytearray(int count, int capacity, int size) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_put_bytearray_int_int(int count, int capacity, int size, int offset, int length) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_putInt_int(int count, int capacity, int value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_putLong_long(int count, int capacity, long value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_putShort_short(int count, int capacity, short value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_remaining(int count, int capactiy, int index) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_wrap_bytearray(int count, int capacity) {
        byte[] array1 = new byte[capacity];
        Arrays.fill(array1, (byte)'a');

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            ByteBuffer bytebuffer_1 = ByteBuffer.allocate(capacity);

            start_time = System.nanoTime();
            bytebuffer_1 = bytebuffer_1.wrap(array1);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_equals_object(int count, int bytebuffer_size1, int bytebuffer_size2, int offset) {
        ByteBuffer bytebuffer_1 = ByteBuffer.allocate(bytebuffer_size1);
        ByteBuffer bytebuffer_2 = ByteBuffer.allocate(bytebuffer_size2);
        byte[] array1 = new byte[bytebuffer_size1];
        byte[] array2 = new byte[bytebuffer_size2];
        Arrays.fill(array1, (byte)'a');
        Arrays.fill(array2, (byte)'a');
        if(offset > 0 && offset < array2.length){array2[offset] = (byte)'b';}
        bytebuffer_1 = bytebuffer_1.wrap(array1);
        bytebuffer_2 = bytebuffer_2.wrap(array2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bytebuffer_1.equals(bytebuffer_2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
}

