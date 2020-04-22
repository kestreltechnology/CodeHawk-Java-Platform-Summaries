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

package ByteCode_Timing;

import org.apache.commons.cli.*;

import java.lang.String;
import java.util.Hashtable;

/* Catalogue of assumptions :
 * 1) We assume that executing a bytecode as part of a loop with flag -Xint does not alter the execution
 *   time of that bytecode, not does the execution time vary depending on the current iteration of the loop
 *   ( the first iteration will take the same amount of time as the 1000000th )
 *
 * 2) We assume that the execution time of a bytecode is not dependent on other nearby (or remote) bytecodes
 *
 * 3) To some extent, we assume that the execution time of a bytecode does not depend on the stack values 
 *    consumed by the execution of that bytecode.
 *
 */

public class ByteCode_Timing {
    public static Hashtable<String, Long> Timings = new Hashtable<String, Long>();

    public static long get_timings(String bytecode_name, int iterations) {
        int[] temparray = { 0 };
        return get_timings(bytecode_name, iterations, temparray);
    }

    public static long get_timings(String bytecode_name, int iterations, int[] extraData) {
        long totalTime = -1;
        if(Timings.containsKey(bytecode_name)) {
            totalTime = Timings.get(bytecode_name);
            return totalTime;
        }
        else {
            switch(bytecode_name) {
                case "EmptyLoop" : 
                    totalTime = Timing_Classes.callCommand(new Timing_Classes.EmptyLoop(), iterations); 
                    break;
                case "default_las" : get_base_store_timings(bytecode_name, iterations);
                    break;
                case "aaload" : totalTime = get_aaload_timings(bytecode_name, iterations);
                    break;
                case "aastore" : totalTime = get_aastore_timings(bytecode_name, iterations);
                    break;
                case "aconst_null" : totalTime = get_aconst_null_timings(bytecode_name, iterations);
                    break;
                case "aload" : totalTime = get_aload_timings(bytecode_name, iterations);
                    break;
                case "aload_0" : totalTime = get_aload_0_timings(bytecode_name, iterations);
                    break;
                case "aload_1" : totalTime = get_aload_1_timings(bytecode_name, iterations);
                    break;
                case "aload_2" : totalTime = get_aload_2_timings(bytecode_name, iterations);
                    break;
                case "aload_3" : totalTime = get_aload_3_timings(bytecode_name, iterations);
                    break;
                case "anewarray" : totalTime = get_anewarray_timings(bytecode_name, iterations, extraData);
                    break;
                case "areturn" : totalTime = get_areturn_timings(bytecode_name, iterations);
                    break;
                case "arraylength" : totalTime = get_arraylength_timings(bytecode_name, iterations);
                    break;
                case "astore" : totalTime = get_astore_timings(bytecode_name, iterations);
                    break;
                case "astore_0" : totalTime = get_astore_0_timings(bytecode_name, iterations);
                    break;
                case "astore_1" : totalTime = get_astore_1_timings(bytecode_name, iterations);
                    break;
                case "astore_2" : totalTime = get_astore_2_timings(bytecode_name, iterations);
                    break;
                case "astore_3" : totalTime = get_astore_3_timings(bytecode_name, iterations);
                    break;
                case "athrow" : totalTime = get_athrow_timings(bytecode_name, iterations);
                    break;
                case "baload" : totalTime = get_baload_timings(bytecode_name, iterations);
                    break;
                case "bastore" : totalTime = get_bastore_timings(bytecode_name, iterations);
                    break;
                case "bipush" : totalTime = get_bipush_timings(bytecode_name, iterations);
                    break;
                case "breakpoint" : totalTime = get_breakpoint_timings(bytecode_name, iterations);
                    break;
                case "caload" : totalTime = get_caload_timings(bytecode_name, iterations);
                    break;
                case "castore" : totalTime = get_castore_timings(bytecode_name, iterations);
                    break;
                case "checkcast" : totalTime = get_checkcast_timings(bytecode_name, iterations);
                    break;
                case "d2f" : totalTime = get_d2f_timings(bytecode_name, iterations);
                    break;
                case "d2i" : totalTime = get_d2i_timings(bytecode_name, iterations);
                    break;
                case "d2l" : totalTime = get_d2l_timings(bytecode_name, iterations);
                    break;
                case "dadd" : totalTime = get_dadd_timings(bytecode_name, iterations);
                    break;
                case "daload" : totalTime = get_daload_timings(bytecode_name, iterations);
                    break;
                case "dastore" : totalTime = get_dastore_timings(bytecode_name, iterations);
                    break;
                case "dcmpg" : totalTime = get_dcmpg_timings(bytecode_name, iterations);
                    break;
                case "dcmpl" : totalTime = get_dcmpl_timings(bytecode_name, iterations);
                    break;
                case "dconst_0" : totalTime= get_dconst_0_timings(bytecode_name, iterations);
                    break;
                case "dconst_1" : totalTime = get_dconst_1_timings(bytecode_name, iterations);
                    break;
                case "ddiv" : totalTime = get_ddiv_timings(bytecode_name, iterations);
                    break;
                case "dload" : totalTime = get_dload_timings(bytecode_name, iterations);
                    break;
                case "dload_0" : totalTime = get_dload_0_timings(bytecode_name, iterations);
                    break;
                case "dload_1" : totalTime = get_dload_1_timings(bytecode_name, iterations);
                    break;
                case "dload_2" : totalTime = get_dload_2_timings(bytecode_name, iterations);
                    break;
                case "dload_3" : totalTime = get_dload_0_timings(bytecode_name, iterations);
                    break;
                case "dmul" : totalTime = get_dmul_timings(bytecode_name, iterations);
                    break;
                case "dneg" : totalTime = get_dneg_timings(bytecode_name, iterations);
                    break;
                case "drem" : totalTime = get_drem_timings(bytecode_name, iterations);
                    break;
                case "dreturn" : totalTime = get_dreturn_timings(bytecode_name, iterations);
                    break;
                case "dstore" : totalTime = get_dstore_timings(bytecode_name, iterations);
                    break;
                case "dstore_0" : totalTime = get_dstore_0_timings(bytecode_name, iterations);
                    break;
                case "dstore_1" : totalTime = get_dstore_1_timings(bytecode_name, iterations);
                    break;
                case "dstore_2" : totalTime = get_dstore_2_timings(bytecode_name, iterations);
                    break;
                case "dstore_3" : totalTime = get_dstore_3_timings(bytecode_name, iterations);
                    break;
                case "dsub" : totalTime = get_dsub_timings(bytecode_name, iterations);
                    break;
                case "dup" : totalTime = get_dup_timings(bytecode_name, iterations);
                    break;
                case "dup_x1" : totalTime = get_dup_x1_timings(bytecode_name, iterations);
                    break;
                case "dup_x2" : totalTime = get_dup_x2_timings(bytecode_name, iterations);
                    break;
                case "dup2" : totalTime = get_dup2_timings(bytecode_name, iterations);
                    break;
                case "dup2_x1" : totalTime = get_dup2_x1_timings(bytecode_name, iterations);
                    break;
                case "dup2_x2" : totalTime = get_dup2_x2_timings(bytecode_name, iterations);
                    break;
                case "f2d" : totalTime = get_f2d_timings(bytecode_name, iterations);
                    break;
                case "f2i" : totalTime = get_f2i_timings(bytecode_name, iterations);
                    break;
                case "f2l" : totalTime = get_f2l_timings(bytecode_name, iterations);
                    break;
                case "fadd" : totalTime = get_fadd_timings(bytecode_name, iterations);
                    break;
                case "faload" : totalTime = get_faload_timings(bytecode_name, iterations);
                    break;
                case "fastore" : totalTime = get_fastore_timings(bytecode_name, iterations);
                    break;
                case "fcmpg" : totalTime = get_fcmpg_timings(bytecode_name, iterations);
                    break;
                case "fcmpl" : totalTime = get_fcmpl_timings(bytecode_name, iterations);
                    break;
                case "fconst_0" : totalTime = get_fconst_0_timings(bytecode_name, iterations);
                    break;
                case "fconst_1" : totalTime = get_fconst_1_timings(bytecode_name, iterations);
                    break;
                case "fconst_2" : totalTime = get_fconst_2_timings(bytecode_name, iterations);
                    break;
                case "fdiv" : totalTime = get_fdiv_timings(bytecode_name, iterations);
                    break;
                case "fload" : totalTime = get_fload_timings(bytecode_name, iterations);
                    break;
                case "fload_0" : totalTime = get_fload_0_timings(bytecode_name, iterations);
                    break;
                case "fload_1" : totalTime = get_fload_1_timings(bytecode_name, iterations);
                    break;
                case "fload_2" : totalTime = get_fload_2_timings(bytecode_name, iterations);
                    break;
                case "fload_3" : totalTime = get_fload_3_timings(bytecode_name, iterations);
                    break;
                case "fmul" : totalTime = get_fmul_timings(bytecode_name, iterations);
                    break;
                case "fneg" : totalTime = get_fneg_timings(bytecode_name, iterations);
                    break;
                case "frem" : totalTime = get_frem_timings(bytecode_name, iterations);
                    break;
                case "freturn" : totalTime = get_freturn_timings(bytecode_name, iterations);
                    break;
                case "fstore" : totalTime = get_fstore_timings(bytecode_name, iterations);
                    break;
                case "fstore_0" : totalTime = get_fstore_0_timings(bytecode_name, iterations);
                    break;
                case "fstore_1" : totalTime = get_fstore_1_timings(bytecode_name, iterations);
                    break;
                case "fstore_2" : totalTime = get_fstore_2_timings(bytecode_name, iterations);
                    break;
                case "fstore_3" : totalTime = get_fstore_3_timings(bytecode_name, iterations);
                    break;
                case "fsub" : totalTime = get_fsub_timings(bytecode_name, iterations);
                    break;
                case "getfield" : totalTime = get_getfield_timings(bytecode_name, iterations);
                    break;
                case "getstatic" : totalTime = get_getstatic_timings(bytecode_name, iterations);
                    break;
                case "goto" : totalTime = get_goto_timings(bytecode_name, iterations);
                    break;
                case "goto_w" : totalTime = get_goto_w_timings(bytecode_name, iterations);
                    break;
                case "i2b" : totalTime = get_i2b_timings(bytecode_name, iterations);
                    break;
                case "i2c" : totalTime = get_i2c_timings(bytecode_name, iterations);
                    break;
                case "i2d" : totalTime = get_i2d_timings(bytecode_name, iterations);
                    break;
                case "i2f" : totalTime = get_i2f_timings(bytecode_name, iterations);
                    break;
                case "i2l" : totalTime = get_i2l_timings(bytecode_name, iterations);
                    break;
                case "i2s" : totalTime = get_i2s_timings(bytecode_name, iterations);
                    break;
                case "iadd" : totalTime = get_iadd_timings(bytecode_name, iterations);
                    break;
                case "iaload" : totalTime = get_iaload_timings(bytecode_name, iterations);
                    break;
                case "iand" : totalTime = get_iand_timings(bytecode_name, iterations);
                    break;
                case "iastore" : totalTime = get_iastore_timings(bytecode_name, iterations);
                    break;
                case "iconst_m1" : totalTime = get_iconst_m1_timings(bytecode_name, iterations);
                    break;
                case "iconst_0" : totalTime = get_iconst_0_timings(bytecode_name, iterations);
                    break;
                case "iconst_1" : totalTime = get_iconst_1_timings(bytecode_name, iterations);
                    break;
                case "iconst_2" : totalTime = get_iconst_2_timings(bytecode_name, iterations);
                    break;
                case "iconst_3" : totalTime = get_iconst_3_timings(bytecode_name, iterations);
                    break;
                case "iconst_4" : totalTime = get_iconst_4_timings(bytecode_name, iterations);
                    break;
                case "iconst_5" : totalTime = get_iconst_5_timings(bytecode_name, iterations);
                    break;
                case "idiv" : totalTime = get_idiv_timings(bytecode_name, iterations);
                    break;
                case "if_acmpeq" : totalTime = get_if_acmpeq_timings(bytecode_name, iterations);
                    break;
                case "if_acmpne" : totalTime = get_if_acmpne_timings(bytecode_name, iterations);
                    break;
                case "if_icmpeq" : totalTime = get_if_icmpeq_timings(bytecode_name, iterations);
                    break;
                case "if_icmpge" : totalTime = get_if_icmpge_timings(bytecode_name, iterations);
                    break;
                case "if_icmpgt" : totalTime = get_if_icmpgt_timings(bytecode_name, iterations);
                    break;
                case "if_icmple" : totalTime = get_if_icmple_timings(bytecode_name, iterations);
                    break;
                case "if_icmplt" : totalTime = get_if_icmplt_timings(bytecode_name, iterations);
                    break;
                case "if_icmpne" : totalTime = get_if_icmpne_timings(bytecode_name, iterations);
                    break;
                case "ifeq" : totalTime = get_ifeq_timings(bytecode_name, iterations);
                    break;
                case "ifge" : totalTime = get_ifge_timings(bytecode_name, iterations);
                    break;
                case "ifgt" : totalTime = get_ifgt_timings(bytecode_name, iterations);
                    break;
                case "ifle" : totalTime = get_ifle_timings(bytecode_name, iterations);
                    break;
                case "iflt" : totalTime = get_iflt_timings(bytecode_name, iterations);
                    break;
                case "ifne" : totalTime = get_ifne_timings(bytecode_name, iterations);
                    break;
                case "ifnonnull" : totalTime = get_ifnonnull_timings(bytecode_name, iterations);
                    break;
                case "ifnull" : totalTime = get_ifnull_timings(bytecode_name, iterations);
                    break;
                case "iinc" : totalTime = get_iinc_timings(bytecode_name, iterations);
                    break;
                case "iload" : totalTime = get_iload_timings(bytecode_name, iterations);
                    break;
                case "iload_0" : totalTime = get_iload_0_timings(bytecode_name, iterations);
                    break;
                case "iload_1" : totalTime = get_iload_1_timings(bytecode_name, iterations);
                    break;
                case "iload_2" : totalTime = get_iload_2_timings(bytecode_name, iterations);
                    break;
                case "iload_3" : totalTime = get_iload_3_timings(bytecode_name, iterations);
                    break;
                case "imul" : totalTime = get_imul_timings(bytecode_name, iterations);
                    break;
                case "ineg" : totalTime = get_ineg_timings(bytecode_name, iterations);
                    break;
                case "instanceof" : totalTime = get_instanceof_timings(bytecode_name, iterations);
                    break;
                case "invokedynamic" : totalTime = get_invokedynamic_timings(bytecode_name, iterations);
                    break;
                case "invokeinterface" : totalTime = get_invokeinterface_timings(bytecode_name, iterations);
                    break;
                case "invokespecial" : totalTime = get_invokespecial_timings(bytecode_name, iterations);
                    break;
                case "invokestatic" : totalTime = get_invokestatic_timings(bytecode_name, iterations);
                    break;
                case "invokevirtual" : totalTime = get_invokevirtual_timings(bytecode_name, iterations);
                    break;
                case "ior" : totalTime = get_ior_timings(bytecode_name, iterations);
                    break;
                case "irem" : totalTime = get_irem_timings(bytecode_name, iterations);
                    break;
                case "ireturn" : totalTime = get_ireturn_timings(bytecode_name, iterations);
                    break;
                case "ishl" : totalTime = get_ishl_timings(bytecode_name, iterations);
                    break;
                case "ishr" : totalTime = get_ishr_timings(bytecode_name, iterations);
                    break;
                case "istore" : totalTime = get_istore_timings(bytecode_name, iterations);
                    break;
                case "istore_0" : totalTime = get_istore_0_timings(bytecode_name, iterations);
                    break;
                case "istore_1" : totalTime = get_istore_1_timings(bytecode_name, iterations);
                    break;
                case "istore_2" : totalTime = get_istore_2_timings(bytecode_name, iterations);
                    break;
                case "istore_3" : totalTime = get_istore_3_timings(bytecode_name, iterations);
                    break;
                case "isub" : totalTime = get_isub_timings(bytecode_name, iterations);
                    break;
                case "iushr" : totalTime = get_iushr_timings(bytecode_name, iterations);
                    break;
                case "ixor" : totalTime = get_ixor_timings(bytecode_name, iterations);
                    break;
                case "l2d" : totalTime = get_l2d_timings(bytecode_name, iterations);
                    break;
                case "l2f" : totalTime = get_l2f_timings(bytecode_name, iterations);
                    break;
                case "l2i" : totalTime = get_l2i_timings(bytecode_name, iterations);
                    break;
                case "ladd" : totalTime = get_ladd_timings(bytecode_name, iterations);
                    break;
                case "laload" : totalTime = get_laload_timings(bytecode_name, iterations);
                    break;
                case "land" : totalTime = get_land_timings(bytecode_name, iterations);
                    break;
                case "lastore" : totalTime = get_lastore_timings(bytecode_name, iterations);
                    break;
                case "lcmp" : totalTime = get_lcmp_timings(bytecode_name, iterations);
                    break;
                case "lconst_0" : totalTime = get_lconst_0_timings(bytecode_name, iterations);
                    break;
                case "lconst_1" : totalTime = get_lconst_1_timings(bytecode_name, iterations);
                    break;
                case "ldc" : totalTime = get_ldc_timings(bytecode_name, iterations);
                    break;
                case "ldc_w" : totalTime = get_ldc_w_timings(bytecode_name, iterations);
                    break;
                case "ldc2_w" : totalTime = get_ldc2_w_timings(bytecode_name, iterations);
                    break;
                case "ldiv" : totalTime = get_ldiv_timings(bytecode_name, iterations);
                    break;
                case "lload" : totalTime = get_lload_timings(bytecode_name, iterations);
                    break;
                case "lload_0" : totalTime = get_lload_0_timings(bytecode_name, iterations);
                    break;
                case "lload_1" : totalTime = get_lload_1_timings(bytecode_name, iterations);
                    break;
                case "lload_2" : totalTime = get_lload_2_timings(bytecode_name, iterations);
                    break;
                case "lload_3" : totalTime = get_lload_3_timings(bytecode_name, iterations);
                    break;
                case "lmul" : totalTime = get_lmul_timings(bytecode_name, iterations);
                    break;
                case "lneg" : totalTime = get_lneg_timings(bytecode_name, iterations);
                    break;
                case "lookupswitch" : totalTime = get_lookupswitch_timings(bytecode_name, iterations);
                    break;
                case "lor" : totalTime = get_lor_timings(bytecode_name, iterations);
                    break;
                case "lrem" : totalTime = get_lrem_timings(bytecode_name, iterations);
                    break;
                case "lreturn" : totalTime = get_lreturn_timings(bytecode_name, iterations);
                    break;
                case "lshl" : totalTime = get_lshl_timings(bytecode_name, iterations);
                    break;
                case "lshr" : totalTime = get_lshr_timings(bytecode_name, iterations);
                    break;
                case "lstore" : totalTime = get_lstore_timings(bytecode_name, iterations);
                    break;
                case "lstore_0" : totalTime = get_lstore_0_timings(bytecode_name, iterations);
                    break;
                case "lstore_1" : totalTime = get_lstore_1_timings(bytecode_name, iterations);
                    break;
                case "lstore_2" : totalTime = get_lstore_2_timings(bytecode_name, iterations);
                    break;
                case "lstore_3" : totalTime = get_lstore_3_timings(bytecode_name, iterations);
                    break;
                case "lsub" : totalTime = get_lsub_timings(bytecode_name, iterations);
                    break;
                case "lushr" : totalTime = get_lushr_timings(bytecode_name, iterations);
                    break;
                case "lxor" : totalTime = get_lxor_timings(bytecode_name, iterations);
                    break;
                case "monitorenter" : totalTime = get_monitorenter_timings(bytecode_name, iterations);
                    break;
                case "monitorexit" : totalTime = get_monitorexit_timings(bytecode_name, iterations);
                    break;
                case "multianewarray" : totalTime = get_multianewarray_timings(bytecode_name, iterations, extraData);
                    break;
                case "new" : totalTime = get_new_timings(bytecode_name, iterations);
                    break;
                case "newarray" : totalTime = get_newarray_timings(bytecode_name, iterations, extraData);
                    break;
                case "nop" : totalTime = get_nop_timings(bytecode_name, iterations);
                    break;
                case "pop" : totalTime = get_pop_timings(bytecode_name, iterations);
                    break;
                case "pop2" : totalTime = get_pop2_timings(bytecode_name, iterations);
                    break;
                case "putfield" : totalTime = get_putfield_timings(bytecode_name, iterations);
                    break;
                case "putstatic" : totalTime = get_putstatic_timings(bytecode_name, iterations);
                    break;
                case "ret" : totalTime = get_ret_timings(bytecode_name, iterations);
                    break;
                case "return" : totalTime = get_return_timings(bytecode_name, iterations);
                    break;
                case "saload" : totalTime = get_saload_timings(bytecode_name, iterations);
                    break;
                case "sastore" : totalTime = get_sastore_timings(bytecode_name, iterations);
                    break;
                case "sipush" : totalTime = get_sipush_timings(bytecode_name, iterations);
                    break;
                case "swap" : totalTime = get_swap_timings(bytecode_name, iterations);
                    break;
                case "tableswitch" : totalTime = get_tableswitch_timings(bytecode_name, iterations);
                    break;
                case "wide" : totalTime = get_wide_timings(bytecode_name, iterations);
                    break;
                default : break; 
            }
        }
        if(totalTime != -1) {
            Timings.put(bytecode_name, totalTime);
        }
        else {
            System.out.format("Failed to get timing information for %s\n", bytecode_name); 
            System.exit(0);
        }
        float individual_time = (float)totalTime / (float)iterations;
        System.out.format("Time for %s, accounting for dependencies : %f ns\n", bytecode_name, individual_time);
        return totalTime;
    }

    public static long get_aaload_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_aastore_timings(String bytecode_name, int iterations) {return 0;}

    //aconst_null
    public static long get_aconst_null_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("astore_2", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_aconst_null(), iterations);    
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    public static long get_aload_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_aload_0_timings(String bytecode_name, int iterations) {return 0;}
    
    /*aload_1*/
    public static long get_aload_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("astore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_aload_1(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    //aload_2
    // TODO : fix
    public static long get_aload_2_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("astore_2", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_aload_2(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    public static long get_aload_3_timings(String bytecode_name, int iterations) {return 0;}

    //anewarray
    public static long get_anewarray_timings(String bytecode_name, int iterations, int[] extraData) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("iload", iterations);
        dependentTime += get_timings("astore_2", iterations);
        int[] tempArray = {iterations, extraData[0]};
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_anewarray(), tempArray);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    public static long get_areturn_timings(String bytecode_name, int iterations) {return 0;}
    
    //arraylength
    public static long get_arraylength_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("astore_2", iterations);
        dependentTime += get_timings("istore_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_arraylength(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }
    public static long get_astore_timings(String bytecode_name, int iterations) {
        return get_base_store_timings(bytecode_name, iterations);
    }
    public static long get_astore_0_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_astore_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("aload_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_astore_1(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_astore_2_timings(String bytecode_name, int iterations) {
        return get_base_store_timings(bytecode_name, iterations);
    }
    public static long get_astore_3_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_athrow_timings(String bytecode_name, int iterations) {return 0;}

    //baload
    public static long get_baload_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("aload_2", iterations);
        dependentTime += get_timings("iconst_0", iterations);
        dependentTime += get_timings("istore_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_baload(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    public static long get_bastore_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("aload_2", iterations);
        dependentTime += get_timings("iconst_0", iterations);
        dependentTime += get_timings("iload_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_bastore(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    public static long get_bipush_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_breakpoint_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_caload_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_castore_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_checkcast_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_d2f_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("dload_3", iterations);
        dependentTime += get_timings("istore", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_d2f(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }
    public static long get_d2i_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("dload_3", iterations);
        dependentTime += get_timings("fstore", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_d2i(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }
    public static long get_d2l_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("dload_3", iterations);
        dependentTime += get_timings("lstore", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_d2l(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }
    public static long get_dadd_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_daload_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dastore_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dcmpg_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dcmpl_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dconst_0_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dconst_1_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ddiv_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dload_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_dload(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_dload_0_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_0", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_dload_0(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_dload_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_dload_1(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_dload_2_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_2", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_dload_2(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_dload_3_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_dload_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_dmul_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dneg_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_drem_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dreturn_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dstore_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_dstore(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    public static long get_dstore_0_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_dstore_0(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    public static long get_dstore_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_dstore_1(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    
    public static long get_dstore_2_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_dstore_2(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    public static long get_dstore_3_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_dstore_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    public static long get_dsub_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dup_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dup_x1_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dup_x2_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dup2_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dup2_x1_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_dup2_x2_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_f2d_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_f2i_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_f2l_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fadd_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_faload_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fastore_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fcmpg_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fcmpl_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fconst_0_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fconst_1_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fconst_2_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fdiv_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fload_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_fload(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_fload_0_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_fload_0(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_fload_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_fload_1(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_fload_2_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_fload_2(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_fload_3_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_fload_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_fmul_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fneg_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_frem_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_freturn_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_fstore_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_fstore(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_fstore_0_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_fstore_0(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_fstore_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_fstore_1(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_fstore_2_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_fstore_2(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_fstore_3_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("dstore_1", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_fstore_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_fsub_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_getfield_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_getstatic_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_goto_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_goto_w_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_i2b_timings(String bytecode_name, int iterations) {return 0;}

    //i2c
    public static long get_i2c_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("iload_2", iterations);
        dependentTime += get_timings("istore_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iconst_1(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }
    public static long get_i2d_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_i2f_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_i2l_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_i2s_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_iadd_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_iaload_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_iand_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_iastore_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_iconst_m1_timings(String bytecode_name, int iterations) {return 0;}

    //iconst_0
    public static long get_iconst_0_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("istore_2", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iconst_0(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    //iconst_1
    public static long get_iconst_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("istore_2", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iconst_1(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    //iconst_2
    public static long get_iconst_2_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("istore_2", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iconst_2(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    //iconst_3
    public static long get_iconst_3_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("istore_2", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iconst_3(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    //iconst_4
    public static long get_iconst_4_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("istore_2", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iconst_4(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    //iconst_5
    public static long get_iconst_5_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("istore_2", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iconst_5(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    public static long get_idiv_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_if_acmpeq_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_if_acmpne_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_if_icmpeq_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_if_icmpge_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_if_icmpgt_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_if_icmple_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_if_icmplt_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_if_icmpne_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ifeq_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ifge_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ifgt_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ifle_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_iflt_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ifne_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ifnonnull_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ifnull_timings(String bytecode_name, int iterations) {return 0;}

    //iinc
    public static long get_iinc_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iinc(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }

    public static long get_iload_timings(String bytecode_name, int iterations) {
        return get_base_store_timings(bytecode_name, iterations);
    }

    public static long get_iload_0_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("iload_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iload_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    public static long get_iload_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("iload_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iload_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    public static long get_iload_2_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("iload_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iload_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    public static long get_iload_3_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("iload_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_iload_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    public static long get_imul_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ineg_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_instanceof_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_invokedynamic_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_invokeinterface_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_invokespecial_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_invokestatic_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_invokevirtual_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ior_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_irem_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ireturn_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ishl_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ishr_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_istore_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_istore_0_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("iload_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_istore_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;

    }

    public static long get_istore_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("iload_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_istore_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    
    public static long get_istore_2_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("iload_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_istore_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }

    public static long get_istore_3_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("iload_3", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_istore_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_isub_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_iushr_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ixor_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_l2d_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_l2f_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_l2i_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ladd_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_laload_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_land_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lastore_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lcmp_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lconst_0_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lconst_1_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ldc_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ldc_w_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ldc2_w_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ldiv_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lload_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("lstore", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lload(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_lload_0_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("lstore", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lload_0(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_lload_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("lstore", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lload_1(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_lload_2_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("lstore", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lload_2(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_lload_3_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("lstore", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lload_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_lmul_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lneg_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lookupswitch_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lor_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("lload_2", iterations);
        dependentTime += get_timings("lload", iterations);
        dependentTime += get_timings("lstore", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lor(), iterations);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }
    public static long get_lrem_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lreturn_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lshl_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lshr_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lstore_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("lload", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lstore(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_lstore_0_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("lload", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lstore_0(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_lstore_1_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("lload", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lstore_1(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_lstore_2_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("lload", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lstore_2(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_lstore_3_timings(String bytecode_name, int iterations) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        //dependentTime += get_timings("lload", iterations);
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_lstore_3(), iterations);
        long finalTime = (testTime - dependentTime) / 2;
        return finalTime;
    }
    public static long get_lsub_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lushr_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_lxor_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_monitorenter_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_monitorexit_timings(String bytecode_name, int iterations) {return 0;}

    //multianewarray
    public static long get_multianewarray_timings(String bytecode_name, int iterations, int[] extraData) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        /*dependentTime += get_timings("", iterations);
        dependentTime += get_Timings("", iterations);*/

        int[] tempArray = new int[extraData.length + 1];
        tempArray[0] = iterations;
        for(int i = 1; i < tempArray.length; i++) {
            tempArray[i] = extraData[i - 1];
        }
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_multianewarray(), tempArray);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }
    public static long get_new_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_newarray_timings(String bytecode_name, int iterations, int[] extraData) {
        long dependentTime = get_timings("EmptyLoop", iterations);
        dependentTime += get_timings("iload_3", iterations);
        dependentTime += get_timings("astore", iterations);
        int[] tempArray = {iterations, extraData[0]};
        long testTime = Timing_Classes.callCommand(new Timing_Classes.run_anewarray(), tempArray);
        long finalTime = testTime - dependentTime;
        return finalTime;
    }
    public static long get_nop_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_pop_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_pop2_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_putfield_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_putstatic_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_ret_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_return_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_saload_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_sastore_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_sipush_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_swap_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_tableswitch_timings(String bytecode_name, int iterations) {return 0;}
    public static long get_wide_timings(String bytecode_name, int iterations) {return 0;}

    public static long get_base_store_timings(String bytecode_name, int iterations) {
        long totalTime = 2 * iterations;
        System.out.format("Timing for %s, by assumptions : %d\n", bytecode_name, totalTime / iterations);
        return totalTime;
    }

    public static void get_all_timings(int iterations){
        long[] Timings_Array = new long[ByteCodes_Array.Interesting_ByteCodes.length];
        for(int i = 0; i < ByteCodes_Array.Interesting_ByteCodes.length; i++) {
            int[] tempArray = { 0 };
            Timings_Array[i] = get_timings(ByteCodes_Array.Interesting_ByteCodes[i], iterations, tempArray);
        }
    }

    public static void main(String [] args){
        Profiling_Parser pparser;
        pparser = new Profiling_Parser(args);

        CommandLine cmd;
        try {
            cmd = pparser.parse();
        }
        catch(org.apache.commons.cli.ParseException e) {
            System.out.println(e.getMessage());

            System.exit(1);
            return;
        }

        int repeats = Integer.parseInt(cmd.getOptionValue("repeats"));
        String bytecode_name = cmd.getOptionValue("target_bytecode");
        String output_file = cmd.hasOption("output_file") ? cmd.getOptionValue("output_file") : "default_output.txt";

        int[] parsed_values = {0};
        if(cmd.hasOption("values")) {
            String[] unparsed_values = cmd.getOptionValues("values");
            parsed_values = new int[unparsed_values.length];
            for(int i = 0; i < parsed_values.length; i++) {
                parsed_values[i] = Integer.parseInt(unparsed_values[i]);
            }
        } 
    
        long total_time;
        if(bytecode_name.trim().compareTo("all") == 0){
            get_all_timings(repeats);
        }
        else {
            total_time = get_timings(bytecode_name, repeats, parsed_values) / repeats;
            Profiling_Printer.print_timing_to_file(output_file, total_time, parsed_values);
        }
    }
}
