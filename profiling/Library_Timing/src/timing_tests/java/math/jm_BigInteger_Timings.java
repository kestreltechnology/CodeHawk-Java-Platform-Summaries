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

import java.math.BigInteger;
import java.lang.String;
import java.util.Random;

public class jm_BigInteger_Timings extends Java_Timings{
    public jm_BigInteger_Timings(boolean verbose) {
        super(verbose);
    }

    public long get_timing(int repeats, String selection, Value_Store values){
        double[] nvals = values.numeric_vals;
        String[] svals = values.string_vals;

        long total_time = 0;
        switch(selection) {
            case "emptyloop" :
                total_time = run_emptyloop(repeats);
                break;
            /*
            case "init_int_bytearray":
                total_time = run_BigInteger_init_int_bytearray(repeats, (int));
                break;
            */
            case "init_int_Random":
                total_time = run_BigInteger_init_int_Random(repeats, (int)nvals[0]);
                break;
            case "init_String":
                total_time = run_BigInteger_init_String(repeats, svals[0]);
                break;
            case "init_String_int":
                total_time = run_BigInteger_init_String_int(repeats, svals[0], (int)nvals[0]);
                break;
            case "add_BigInteger":
                total_time = run_BigInteger_add_BigInteger(repeats, svals[0], svals[1]);
                break;
            case "and_BigInteger":
                total_time = run_BigInteger_and_BigInteger(repeats, svals[0], svals[1]);
                break;
            case "bitLength":
                total_time = run_BigInteger_bitLength(repeats, svals[0]);
                break;
            case "compareTo_BigInteger":
                total_time = run_BigInteger_compareTo_BigInteger(repeats, svals[0], svals[1]);
                break;
            case "equals_Object":
                total_time = run_BigInteger_equals_object(repeats, svals[0], (int)nvals[0], (int)nvals[1]);
                break;
            case "gcd_BigInteger":
                total_time = run_BigInteger_gcd_BigInteger(repeats, svals[0], svals[1]);
                break;
            case "hashCode":
                total_time = run_BigInteger_hashCode(repeats, svals[0]);
                break;
            case "intValue":
                total_time = run_BigInteger_intValue(repeats, svals[0]);
                break;
            case "mod_BigInteger":
                total_time = run_BigInteger_mod_BigInteger(repeats, svals[0], svals[1]);
                break;
            case "modInverse_BigInteger":
                total_time = run_BigInteger_modInverse_BigInteger(repeats, svals[0], svals[1]);
                break;
            case "multiply_BigInteger":
                total_time = run_BigInteger_multiply_BigInteger(repeats, svals[0], svals[1]);
                break;
            case "pow_int":
                total_time = run_BigInteger_pow_int(repeats, svals[0], (int)nvals[0]);
                break;
            case "probablePrime_int_Random":
                total_time = run_BigInteger_probablePrime_int_Random(repeats, (int)nvals[0]);
                break;
            case "shiftLeft_int":
                total_time = run_BigInteger_shiftLeft_int(repeats, svals[0], (int)nvals[0]);
                break;
            case "shiftRight_int":
                total_time = run_BigInteger_shiftRight_int(repeats, svals[0], (int)nvals[0]);
                break;
            case "subtract_BigInteger":
                total_time = run_BigInteger_subtract_BigInteger(repeats, svals[0], svals[1]);
                break;
            case "testBit_int":
                total_time = run_BigInteger_testBit_int(repeats, svals[0], (int)nvals[0]);
                break;
            case "toByteArray":
                total_time = run_BigInteger_toByteArray(repeats, svals[0]);
                break;
            case "toString":
                total_time = run_BigInteger_toString(repeats, svals[0]);
                break;
            case "toString_int":
                total_time = run_BigInteger_toString_int(repeats, svals[0], (int)nvals[0]);
                break;
            case "valueOf_long":
                total_time = run_BigInteger_valueOf_long(repeats, (long)nvals[0]);
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

    /*
    public static long run_BigInteger_init_int_bytearray(int count) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
    */

    public static long run_BigInteger_init_int_Random(int count, int numBits) {
        Random newRnd = new Random();
        BigInteger bigint;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint = new BigInteger(numBits, newRnd);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_init_String(int count, String val) {
        BigInteger bigint;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint = new BigInteger(val);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_init_String_int(int count, String val, int radix) {
        BigInteger bigint;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint = new BigInteger(val, radix);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_add_BigInteger(int count, String val1, String val2) {
        BigInteger bigint1 = new BigInteger(val1);
        BigInteger bigint2 = new BigInteger(val2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.add(bigint2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_and_BigInteger(int count, String val1, String val2) {
        BigInteger bigint1 = new BigInteger(val1);
        BigInteger bigint2 = new BigInteger(val2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.and(bigint2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_bitLength(int count, String val1) {
        BigInteger bigint1 = new BigInteger(val1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.bitLength();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_compareTo_BigInteger(int count, String val1, String val2) {
        BigInteger bigint1 = new BigInteger(val1);
        BigInteger bigint2 = new BigInteger(val2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.compareTo(bigint2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_equals_object(int count, String val1, int type, int parameter) {
        try {
            Object some_object = Utilities.get_some_object(type, parameter);
            BigInteger bigint1 = new BigInteger(val1);

            long start_time, end_time, total_time = 0;
            for(int i = 0; i < count; i++) {
                start_time = System.nanoTime();
                bigint1.equals(some_object);
                end_time = System.nanoTime();
                total_time += end_time - start_time;
            }
            return total_time / count;
        }
        catch(BadParameterException e) {
            return -1;
        }
    }

    public static long run_BigInteger_gcd_BigInteger(int count, String val1, String val2) {
        BigInteger bigint1 = new BigInteger(val1);
        BigInteger bigint2 = new BigInteger(val2);
        
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.gcd(bigint2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_hashCode(int count, String val1) {
        BigInteger bigint1 = new BigInteger(val1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.hashCode();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_intValue(int count, String val1) {
        BigInteger bigint1 = new BigInteger(val1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.intValue();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_mod_BigInteger(int count, String val1, String val2) {
        BigInteger bigint1 = new BigInteger(val1);
        BigInteger bigint2 = new BigInteger(val2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.mod(bigint2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_modInverse_BigInteger(int count, String val1, String val2) {
        BigInteger bigint1 = new BigInteger(val1);
        BigInteger bigint2 = new BigInteger(val2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.modInverse(bigint2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_multiply_BigInteger(int count, String val1, String val2) {
        BigInteger bigint1 = new BigInteger(val1);
        BigInteger bigint2 = new BigInteger(val2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.multiply(bigint2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
    public static long run_BigInteger_pow_int(int count, String val1, int exp) {
        BigInteger bigint1 = new BigInteger(val1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.pow(exp);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_probablePrime_int_Random(int count, int bitLength) {
        Random rnd = new Random();

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            BigInteger.probablePrime(bitLength, rnd);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_shiftLeft_int(int count, String val1, int shift) {
        BigInteger bigint1 = new BigInteger(val1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.shiftLeft(shift);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_shiftRight_int(int count, String val1, int shift) {
        BigInteger bigint1 = new BigInteger(val1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.shiftRight(shift);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_subtract_BigInteger(int count, String val1, String val2) {
        BigInteger bigint1 = new BigInteger(val1);
        BigInteger bigint2 = new BigInteger(val2);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.subtract(bigint2);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_testBit_int(int count, String val1, int bit) {
        BigInteger bigint1 = new BigInteger(val1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.testBit(bit);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_toByteArray(int count, String val1) {
        BigInteger bigint1 = new BigInteger(val1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.toByteArray();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_toString(int count, String val1) {
        BigInteger bigint1 = new BigInteger(val1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.toString();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_toString_int(int count, String val1, int radix) {
        BigInteger bigint1 = new BigInteger(val1);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            bigint1.toString(radix);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_BigInteger_valueOf_long(int count, long value) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            BigInteger.valueOf(value);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
}
