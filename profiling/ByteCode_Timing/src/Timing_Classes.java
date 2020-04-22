/* ------------------------------------------------------------------------------
 Java program to profile individual java bytecodes
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

/*
  Note: For most load and store instructions, we simply mirror a method that tests
  a load or store instruction of the correct class, rather than the specific load or
  store instruction being queried. We do this under the assumption that all load/store
  instructions of a particular type have the same runtime, although it would be better
  to develop more specific tests.
*/

package ByteCode_Timing;

import java.lang.String;
import java.lang.Exception;
import java.util.Calendar;

public class Timing_Classes {
    public interface Command {
        public void execute(Object data);
    }

    public interface Command_onearg {
        public void execute(Object data, int arg);
    }

    public static long callCommand(Command command, Object data){
        int iterations = 10;
        long startTime, endTime, accumulatedTime = 0, averageTime;
        for(int i = 0; i < iterations; i++) {
            startTime = System.nanoTime();
            command.execute(data);
            endTime = System.nanoTime();
            accumulatedTime = accumulatedTime + (endTime - startTime);
            System.out.format("Time of iteration %d : (%d)\n", i, endTime - startTime);
        }
        averageTime = accumulatedTime / iterations;
        System.out.format("Average Time : %d\n", averageTime);
        return averageTime;
        
    }

    /*
    public static long callCommand(Command_onearg command, Object data, int arg){
        int iterations = 10;
        long startTime, endTime, accumulatedTime = 0, averageTime;
        for(int i = 0; i < iterations; i++) {
            startTime = System.nanoTime();
            command.execute(data, arg);
            endTime = System.nanoTime();
            accumulatedTime = accumulatedTime + (endTime - startTime);
            System.out.format("Time of iteration %d : (%d)\n", i, endTime - startTime);
        }
        averageTime = accumulatedTime / iterations;
        System.out.format("Average Time : %d\n", averageTime);
        return averageTime;
        
    }
    */

    public static class EmptyLoop implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++){}
        }
    }

    /* aaload */
    /* aastore */

    public static class run_aconst_null implements Command {
        public void execute(Object count) {
            Object a;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = null;
            }
        }
    }

    /* aload */
    /* aload_0 */

    public static class run_aload_1 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
            	count = count;
            }
        }
    }

    public static class run_aload_2 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            String a = "a";
            for(int i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }
        
    /* aload_3 */

    public static class run_anewarray implements Command {
        public void execute(Object count) {
            Object[] strarray;
            int[] info = (int[])count;
            int repeats = info[0];
            int size = info[1];
            for(int i = 0; i < repeats; i++) {
                strarray = new Object[size];
            }
        }
    }

    public static class run_areturn implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            int[] a = new int[5];
            for(int i = 0; i < new_count; i ++){
                run_areturn_sub(a);
            }
        }
    }

    public static int[] run_areturn_sub(int[] value) {
        return value;
    }

    public static class run_arraylength implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            int[] intarray = {1, 2, 3, 4, 5};
            int b;
            for(int i = 0; i < new_count; i++) {
                b = intarray.length;
            }
        }
    }

    /* astore */
    /* astore_0 */

    /* astore_1 */
    public static class run_astore_1 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
            	count = count;
            }
        }
    }

    /* astore_2 */
    /* astore_3 */

    public static class run_athrow implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                try {
                    throw new Exception();
                } catch(Exception a) {}
            }
        }
    }

    public static class run_baload implements Command {
        public void execute(Object count) {
            boolean[] barray = { true, false, true, false, true };
            boolean a;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = barray[0];
            }
        }
    }

    public static class run_bastore implements Command {
        public void execute(Object count) {
            boolean[] barray = new boolean[5];
            boolean a = true;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                barray[0] = a;
            }
        }
    } 

    public static class run_bipush implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            byte a = 'a';
            for(int i = 0; i < new_count; i++) {
                a = 'a';
            }
        }
    }

    /* breakpoint */

    public static class run_caload implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            char[] carray = {'a', 'b', 'c', 'd', 'e'};
            char a;
            for(int i =0; i < new_count; i++) {
                a = carray[0];
            }
        }
    }
    
    public static class run_castore implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            char[] carray = new char[5];
            char a = 'a';
            for(int i = 0; i < new_count; i++) {
                carray[0] = a;
            }
        }
    }
 
    public static class run_d2f implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            float b;
            for(int i = 0; i < new_count; i++) {
                b = (float)a;
            }
        }
    }

    public static class run_d2i implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            int b;
            for(int i = 0; i < new_count; i++) {
                b = (int)a;
            }
        }
    }

    public static class run_d2l implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            long b;
            for(int i = 0; i < new_count; i++) {
                b = (long)a;
            }
        }
    }

    public static class run_dadd implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a + 1.0d;
            }
        }
    }

    public static class run_daload implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double[] darray = { 1.0d, 2.0d, 3.0d, 4.0d, 5.0d };
            double a;
            for(int i = 0; i < new_count; i++) {
                a = darray[0];
            }
        }
    }

    public static class run_dastore implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double[] darray = new double[5];
            double a = 1.0d;
            for(int i = 0; i < new_count; i++) {
                darray[0] = a;
            }
        }
    }

    public static class run_dconst_0 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 1.0d;
            for(int i = 0; i < new_count; i++) {
                a = 0.0d;
            }
        }
    }

    public static class run_dconst_1 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = 1.0d;
            }
        }
    }

    public static class run_dcmpg implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            double b = 0.0d;
            for(int i = 0; i < new_count; i++) {
                if(a < b); {}
            }
        }
    }

    public static class run_dcmpl implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            double b = 0.0d;
            for(int i = 0; i < new_count; i++) {
                if(a > b); {}
            }
        }
    }

    public static class run_ddiv implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 1.0d;
            for(int i = 0; i < new_count; i++) {
                a = a / 1.0d;
            }
        }
    }

    /* dload */
    public static class run_dload implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* dload_0 */
    public static class run_dload_0 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* dload_1 */
    public static class run_dload_1 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    } 

    /* dload_2 */
    public static class run_dload_2 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* dload_3 */
    public static class run_dload_3 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    public static class run_dmul implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a * 1.0d;
            }
        }
    }

    public static class run_dneg implements Command {
        public void execute(Object count) {
            double a = 1.0d;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = -a;
            }
        }
    }

    public static class run_drem implements Command {
        public void execute(Object count) {
            double a = 1.0d;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a % 1.0d;
            }
        }
    }

    public static class run_dreturn implements Command {
        public void execute(Object count) {
            double a = 1.0d;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                run_dreturn_sub(a);
            }
        }
    }

    public static double run_dreturn_sub(double value) {
        return value;
    }

    /* dstore */
    public static class run_dstore implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* dstore_0 */
    public static class run_dstore_0 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* dstore_1 */
    public static class run_dstore_1 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* dstore_2 */
    public static class run_dstore_2 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* dstore_3 */
    public static class run_dstore_3 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0d;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    public static class run_dup implements Command {
        public void execute(Object count) {
            int a = 1;
            int new_count = (int)count;
            for(int i = 0 ; i < new_count; i++) {
                a = a = a;
            }
        }
    }

    /* dup_x1 */
    /* dup_x2 */

    public static class run_dup2 implements Command {
        public void execute(Object count) {
            double a = 1.0d;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a = a;
            }
        }
    }

    /* dup2_x1 */
    /* dup2_x2 */

    public static class run_f2d implements Command {
        public void execute(Object count) {
            float a = 0;
            double b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (double)a;
            }
        }
    }
    
    public static class run_f2i implements Command {
        public void execute(Object count) {
            float a = 0;
            int b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (int)a;
            }
        }
    }

    public static class run_f2l implements Command {
        public void execute(Object count) {
            float a = 0;
            long b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (long)a;
            }
        }
    }

    public static class run_fadd implements Command {
        public void execute(Object count) {
            float a = 0.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a + 1.0f;
            }
        }
    }

    public static class run_faload implements Command {
        public void execute(Object count) {
            float[] farray = { 1.0f, 2.0f, 3.0f, 4.0f, 5.0f };
            float a;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = farray[0];
            }
        }
    }

    public static class run_fastore implements Command {
        public void execute(Object count) {
            float[] farray = new float[5];
            float a = 1.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                farray[0] = a;
            }
        }
    }

    public static class run_fcmpg implements Command {
        public void execute(Object count) {
            float a = 0.0f;
            float b = 0.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a < b); {}
            }
        }
    }

    public static class run_fcmpl implements Command {
        public void execute(Object count) {
            float a = 0.0f;
            float b = 0.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a > b); {}
            }
        }
    }

    public static class run_fconst_0 implements Command {
        public void execute(Object count) {
            float a = 0.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 0.0f;
            }
        }
    }
    
    public static class run_fconst_1 implements Command {
        public void execute(Object count) {
            float a = 0.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 1.0f;
            }
        }
    }

    public static class run_fconst_2 implements Command {
        public void execute(Object count) {
            float a = 0.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 2.0f;
            }
        }
    }

    public static class run_fdiv implements Command {
        public void execute(Object count) {
            float a = 1.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a / 1.0f;
            }
        }
    }

    /* fload */
    public static class run_fload implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            float a = 0.0f;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* fload_0 */
    public static class run_fload_0 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0f;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* fload_1 */
    public static class run_fload_1 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0f;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* fload_2 */
    public static class run_fload_2 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0f;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* fload_3 */
    public static class run_fload_3 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0f;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    public static class run_fmul implements Command {
        public void execute(Object count) {
            float a = 0.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a * 1.0f;
            }
        }
    }

    public static class run_fneg implements Command {
        public void execute(Object count) {
            float a = 1.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = -a;
            }
        }
    }

    public static class run_frem implements Command {
        public void execute(Object count) {
            float a = 1.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a % 1.0f;
            }
        }
    }

    public static class run_freturn implements Command {
        public void execute(Object count) {
            float a = 1.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                run_freturn_sub(a);
            }
        }
    }

    public static float run_freturn_sub(float value) {
        return value;
    }

    /* fstore */
    public static class run_fstore implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0f;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* fstore_0 */
    public static class run_fstore_0 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0f;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* fstore_1 */
    public static class run_fstore_1 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0f;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* fstore_2 */
    public static class run_fstore_2 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0f;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    /* fstore_3 */
    public static class run_fstore_3 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            double a = 0.0f;
            for(int i = 0; i < new_count; i++) {
                a = a;
            } 
        }
    }

    public static class run_fsub implements Command {
        public void execute(Object count) {
            float a = 0.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a - 1.0f;
            }
        }
    }

    public static class run_getfield implements Command {
        public void execute(Object count) {
            Dog doggy = new Dog();
            doggy.ageC = 10;
            int currentage;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                currentage = doggy.ageC;
            }
        }
    }

    public static class run_getstatic implements Command {
        public void execute(Object count) {
            Dog doggy = new Dog();
            boolean reality;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                reality = doggy.aredogsreal;
            }
        }
    }

    public static class run_i2c implements Command {
        public void execute(Object count) {
            int a = 0;
            char b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (char)a;
            }
        }
    }

    public static class run_i2b implements Command {
        public void execute(Object count) {
            int a = 0;
            byte b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (byte)a;
            }
        }
    }

    public static class run_i2d implements Command {
        public void execute(Object count) {
            int a = 0;
            double b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (double)a;
            }
        }
    }

    public static class run_i2f implements Command {
        public void execute(Object count) {
            int a = 0;
            float b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (float)a;
            }
        }
    }

    public static class run_i2l implements Command {
        public void execute(Object count) {
            int a = 0;
            long b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (long)a;
            }
        }
    }

    public static class run_i2s implements Command {
        public void execute(Object count) {
            int a = 0;
            short b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (short)a;
            }
        }
    }

    public static class run_iadd implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a + 1;
            }
        }
    }

    public static class run_iaload implements Command {
        public void execute(Object count) {
            int[] iarray = { 1, 2, 3, 4, 5 };
            int a;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = iarray[0];
            }
        }
    }

    public static class run_iand implements Command {
        public void execute(Object count) {
            int a = 1;
            int b = 2;
            int c;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                c = a & b;
            }
        }
    }

    public static class run_iastore implements Command {
        public void execute(Object count) {
            int[] iarray = new int[5];
            int a = 1;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                iarray[0] = a;
            }
        }
    }

    public static class run_iconst_0 implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 0;
            }
        }
    }

   public static class run_iconst_1 implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 1;
            }
        }
    }

    public static class run_iconst_2 implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 2;
            }
        }
    }

    public static class run_iconst_3 implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 3;
            }
        }
    }
        
    public static class run_iconst_4 implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 4;
            }
        }
    }

    public static class run_iconst_5 implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 5;
            }
        }
    }

    public static class run_iconst_m1 implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = -1;
            }
        }
    }

   public static class run_idiv implements Command {
        public void execute(Object count) {
            int a = 1;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a / 1;
            } 
        }
    }

    public static class run_if_acmpeq implements Command {
        public void execute(Object count) {
            Object a = new Object();
            Object b = a;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a != b) {}
            }
        }
    }

    public static class run_if_acmpne implements Command {
        public void execute(Object count) {
            Object a = new Object();
            Object b = new Object();
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a == b) {}
            }
        }
    }
        
    public static class run_if_icmpeq implements Command {
        public void execute(Object count) {
            int a = 1;
            int b = 1;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a != b) {}
            }
        }
    }

    public static class run_if_icmpge implements Command {
        public void execute(Object count) {
            int a = 2;
            int b = 1;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a < b) {}
            }
        }
    }

    public static class run_if_icmpgt implements Command {
        public void execute(Object count) {
            int a = 2;
            int b = 1;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a <= b) {}
            }
        }
    }

   public static class run_if_icmple implements Command {
        public void execute(Object count) {
            int a = 1;
            int b = 2;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a > b) {}
            }
        }
    }

    public static class run_if_icmplt implements Command {
        public void execute(Object count) {
            int a = 1;
            int b = 2;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a >= b) {}
            }
        }
    }

    public static class run_if_icmpne implements Command {
        public void execute(Object count) {
            int a = 1;
            int b = 2;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a == b) {}
            }
        }
    }
        
    public static class run_ifeq implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a != 0) {}
            }
        }
    }

    public static class run_ifge implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a < 0) {}
            }
        }
    }

    public static class run_ifgt implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a <= 0) {}
            }
        }
    }

   public static class run_ifle implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a > 0) {}
            }
        }
    }

    public static class run_iflt implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a >= 0) {}
            }
        }
    }

    public static class run_ifne implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a == 0) {}
            }
        }
    }
        
    public static class run_ifnonnull implements Command {
        public void execute(Object count) {
            Object a = null;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a == null) {}
            }
        }
    }

    public static class run_ifnull implements Command {
        public void execute(Object count) {
            Object a = null;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a != null) {}
            }
        }
    }

    public static class run_iinc implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a++;
            }
        }
    }

    public static class run_iload implements Command {
        public void execute(Object count) {
            int i;
            int a;
            int b;
            int c = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                c = c;
            }
        }
    }

    public static class run_iload_0 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                count = count;
            }
        }
    }

    public static class run_iload_1 implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                i = i;
            }
        }
    }

    public static class run_iload_2 implements Command {
        public void execute(Object count) {
            int i;
            int a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    public static class run_iload_3 implements Command {
        public void execute(Object count) {
            int i;
            int a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    public static class run_iload_more implements Command {
        public void execute(Object count) {
            int i;
            int a = 1;
            int b = 2;
            int c = 3;
            int d = 4;
            int e = 5;
            int f = 6;
            int g = 7;
            int h = 7;
            int j = 8;
            int k = 9;
            int l = 10;
            int m = 11;
            int n = 12;
            int o = 13;
            int p = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                p = p;
            }
        }
    }

    public static class run_imul implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a * 1;
            }
        }
    }

    public static class run_imul_repeat implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a * a;
            }
        }
    }

    public static class run_ineg implements Command {
        public void execute(Object count) {
            int a = 1;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = -a;
            }
        }
    }

    public static class run_invokedynamic implements Command {
        public void execute(Object count) {
            Dog2 doggy = new Dog2();
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                doggy.somethingelse();
            }
        }
    }

    public static class run_invokevirtual implements Command {
        public void execute(Object count) {
            Dog doggy = new Dog();
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                doggy.barking();
            }
        }
    }

    public static class run_instanceof implements Command {
        public void execute(Object count) {
            String a = "hello";
            boolean b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = a instanceof String;
            }
        }
    }

    public static class run_invokespecial implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                String a = "a";
            }
        }
    }

    /* invokeinterface */
    /* invokestatic */

    public static class run_ior implements Command {
        public void execute(Object count) {
            int a = 1;
            int b = 2;
            int c;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                c = a | b;
            }
        }
    }

    public static class run_irem implements Command {
        public void execute(Object count) {
            int a = 1;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a % 1;
            }
        }
    }

    public static class run_ireturn implements Command {
        public void execute(Object count) {
            int a = 1;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                run_ireturn_sub(a);
            }
        }
    }

    public static int run_ireturn_sub(int value) {
        return value;
    }

    public static class run_ishl implements Command {
        public void execute(Object count) {
            int a = 1;
            int b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = a << a;
            }
        }
    }

    /* ishr */
    /* istore */
    /* istore_0 */
    /* istore_1 */
    /* istore_2 */
    /* istore_3 */
    public static class run_istore_3 implements Command {
        public void execute(Object count) {
            int i;
            int a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    public static class run_ixshr implements Command {
        public void execute(Object count) {
            int a = 1;
            int b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = a >> a;
            }
        }
    }

    public static class run_isub implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a - 1;
            }
        }
    }

    public static class run_ixor implements Command {
        public void execute(Object count) {
            int a = 1;
            int b = 2;
            int c;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                c = a ^ b;
            }
        }
    }

    public static class run_l2d implements Command {
        public void execute(Object count) {
            long a = 0;
            double b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (double)a;
            }
        }
    }

    public static class run_l2f implements Command {
        public void execute(Object count) {
            long a = 0;
            float b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (float)a;
            }
        }
    }

    public static class run_l2i implements Command {
        public void execute(Object count) {
            long a = 0;
            int b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = (int)a;
            }
        }
    }

    public static class run_ladd implements Command {
        public void execute(Object count) {
            long a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a + 1;
            }
        }
    }

    public static class run_laload implements Command {
        public void execute(Object count) {
            long[] larray = { 1l, 2l, 3l, 4l, 5l };
            long a;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = larray[0];
            }
        }
    }

    public static class run_land implements Command {
        public void execute(Object count) {
            long a = 0;
            long b = 1;
            long c;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                c = a & b;
            }
        }
    }

    public static class run_lastore implements Command {
        public void execute(Object count) {
            long[] larray = new long[5];
            long a = 1l;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                larray[0] = a;
            }
        }
    }

    public static class run_lcmp implements Command {
        public void execute(Object count) {
            long a = 0l;
            long b = 0l;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                if(a > b); {}
            }
        }
    }

    public static class run_lconst_0 implements Command {
        public void execute(Object count) {
            long a = 0l;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 0l;
            }
        }
    }

    public static class run_lconst_1 implements Command {
        public void execute(Object count) {
            long a = 0l;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 1l;
            }
        }
    }

    public static class run_ldc implements Command {
        public void execute(Object count) {
            String a;
            int new_count = (int)count;
            for(int i = 0 ; i < new_count; i++) {
                a = "d2f";
            }
        }
    }

    public static class run_ldc_w implements Command {
        public void execute(Object count) {
            String a;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = "a";
            }
        }
    }

    public static class run_ldc2_w implements Command {
        public void execute(Object count) {
            double a;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = 5.0d;
            }
        }
    }

    public static class run_ldiv implements Command {
        public void execute(Object count) {
            long a = 1l;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a / 1l;
            }
        }
    }

    /* lload */
    public static class run_lload implements Command {
        public void execute(Object count) {
            int i;
            long a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    /* lload_0 */
    public static class run_lload_0 implements Command {
        public void execute(Object count) {
            int i;
            long a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    /* lload_1 */
    public static class run_lload_1 implements Command {
        public void execute(Object count) {
            int i;
            long a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    /* lload_2 */
    public static class run_lload_2 implements Command {
        public void execute(Object count) {
            int i;
            long a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    /* lload_3 */
    public static class run_lload_3 implements Command {
        public void execute(Object count) {
            int i;
            long a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    public static class run_lmul implements Command {
        public void execute(Object count) {
            long a = 0l;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a * 1l;
            }
        }
    }

    public static class run_lneg implements Command {
        public void execute(Object count) {
            long a = 1l;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = -a;
            }
        }
    }

    /* lookupswitch */

    public static class run_lor implements Command {
        public void execute(Object count) {
            long a = 0;
            long b = 1;
            long c;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                c = a | b;
            }
        }
    }

    public static class run_lrem implements Command {
        public void execute(Object count) {
            long a = 1l;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a % 1l;
            }
        }
    }

    public static class run_lreturn implements Command {
        public void execute(Object count) {
            long a = 1l;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                run_lreturn_sub(a);
            }
        }
    }

    public static long run_lreturn_sub(long value) {
        return value;
    }

    public static class run_lshl implements Command {
        public void execute(Object count) {
            long a = 5;
            long b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = a << 1;
            }
        }
    }

    public static class run_lshr implements Command {
        public void execute(Object count) {
            long a = 5;
            long b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = a >> 1;
            }
        }
    }

    /* lstore */
    public static class run_lstore implements Command {
        public void execute(Object count) {
            int i;
            long a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    /* lstore_0 */
    public static class run_lstore_0 implements Command {
        public void execute(Object count) {
            int i;
            long a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    /* lstore_1 */
    public static class run_lstore_1 implements Command {
        public void execute(Object count) {
            int i;
            long a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    /* lstore_2 */
    public static class run_lstore_2 implements Command {
        public void execute(Object count) {
            int i;
            long a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    /* lstore_3 */
    public static class run_lstore_3 implements Command {
        public void execute(Object count) {
            int i;
            long a = 0;
            int new_count = (int)count;
            for(i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    public static class run_lsub implements Command {
        public void execute(Object count) {
            long a = 0l;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a - 1l;
            }
        }
    }

    public static class run_lushr implements Command {
        public void execute(Object count) {
            long a = 5;
            long b;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                b = a >> 1;
            }
        }
    }

    public static class run_lxor implements Command {
        public void execute(Object count) {
            long a = 1;
            long b = 2;
            long c;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                c = a ^ b;
            }
        }
    }

    /* monitorenter */
    /* monitorexit */

    public static class run_multianewarray implements Command {
        public void execute(Object count) {
            int[] info = (int[])count;
            int repeats = info[0];
            int size1 = info[1];
            int size2 = info[2];
            int size3 = info[3];
            String[][][] strarray;

            for(int i = 0; i < repeats; i++) {
                /*strarray = new String[size1][size2];*/
                strarray = new String[size1][size2][size3];
            }
        }
    }

    public static class run_new implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                String a = new String();
            }
        }
    }

    public static class run_newarray implements Command {
        public void execute(Object count) {
            int[] info = (int[])count;
            int repeats = info[0];
            int size = info[1];

            double[] newarray;
            for(int i = 0; i < repeats; i++) {
                newarray = new double[repeats];
            }
        }
    }

    public static class run_putfield implements Command {
        public void execute(Object count) {
            Dog doggy = new Dog();
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                doggy.ageC = 10;
            }
        }
    }

    public static class run_putstatic implements Command {
        public void execute(Object count) {
            Dog doggy = new Dog();
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
            doggy.aredogsreal = true;
            }
        }
    }

    /* ret */

    public static class run_return implements Command {
        public void execute(Object count) {
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                run_return_sub();
            }
        }
    }

    public static void run_return_sub(){
        return;
    }

    public static class run_saload implements Command {
        public void execute(Object count) {
            short[] sarray = { 1, 2, 3, 4, 5 };
            short a;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = sarray[0];
            }
        }
    }

    public static class run_sastore implements Command {
        public void execute(Object count) {
            short[] sarray = new short[5];
            short a = 1;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                sarray[0] = a;
            }
        }
    }

    /* sipush */
    /* swap */
    /* tableswitch */
    /* wide */

    public static class run_trivial_assignment_double implements Command {
            public void execute(Object count) {
            double a = 0.0d;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    public static class run_trivial_assignment_float implements Command {
        public void execute(Object count) {
            float a = 0.0f;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    public static class run_trivial_assignment_int implements Command {
        public void execute(Object count) {
            int a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }

    public static class run_trivial_assignment_long implements Command {
        public void execute(Object count) {
            long a = 0;
            int new_count = (int)count;
            for(int i = 0; i < new_count; i++) {
                a = a;
            }
        }
    }
}
