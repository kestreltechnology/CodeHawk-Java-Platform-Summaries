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


/* Storing this here just because it is very big and ugly */

package ByteCode_Timing;
    
public class ByteCodes_Array {
    public static String[] Interesting_ByteCodes = {"aaload", "aastore", "aconst_null", "aload", "aload_0", "aload_1", "aload_2", "aload_3", "anewarray", "areturn", "arraylength", "astore", "astore_0", "astore_1", "astore_2", "astore_3", "athrow", "baload", "bastore", "bipush" , "caload", "castore", "checkcast", "d2f", "d2i", "d2l", "dadd", "daload", "dastore", "dcmpg", "dcmpl", "dconst_0", "dconst_1", "ddiv", "dload", "dload_0", "dload_1", "dload_2", "dload_3", "dmul", "dneg", "drem", "dreturn", "dstore", "dstore_0", "dstore_1", "dstore_2", "dstore_3", "dsub", "dup", "dup_x1", "dup_x2", "dup2", "dup2_x1", "dup2_x2", "f2d", "f2i", "f2l", "fadd", "faload", "fastore", "fcmpg", "fcmpl", "fconst_0", "fconst_1", "fconst_2", "fdiv", "fload", "fload_0", "fload_1", "fload_2", "fload_3", "fmul", "fneg", "frem", "freturn", "fstore", "fstore_0", "fstore_1", "fstore_2", "fstore_3", "fsub", "getfield", "getstatic", "goto", "goto_w", "i2b", "i2c", "i2d", "i2f", "i2l", "i2s", "iadd", "iaload", "iand", "iastore", "iconst_m1", "iconst_0", "iconst_1", "iconst_2", "iconst_3", "iconst_4", "iconst_5", "idiv", "if_acmpeq", "if_acmpne", "if_icmpeq", "if_icmpge", "if_icmpgt", "if_icmple", "if_icmplt", "if_icmpne", "ifeq", "ifge", "ifgt", "ifle", "iflt", "ifne", "ifnonnull", "ifnull", "iinc", "iload", "iload_0", "iload_1", "iload_2", "iload_3", "imul", "ineg", "instanceof", "invokedynamic", "invokeinterface", "invokespecial", "invokestatic", "invokevirtual", "ior", "irem", "ireturn", "ishl", "ishr", "istore", "istore_0", "istore_1", "istore_2", "istore_3", "isub", "iushr", "ixor", "l2d", "l2f", "l2i", "ladd", "laload", "land", "lastore", "lcmp", "lconst_0", "lconst_1", "ldc", "ldc_w", "ldc2_w", "ldiv", "lload", "lload_0", "lload_1", "lload_2", "lload_3", "lmul", "lneg", "lookupswitch", "lor", "lrem", "lreturn", "lshl", "lshr", "lstore", "lstore_0", "lstore_1", "lstore_2", "lstore_3", "lsub", "lushr", "lxor", "multianewarray", "new", "newarray", "nop", "pop", "pop2", "putfield", "putstatic", "return", "saload", "sastore", "swap", "tableswitch", "wide" };
}
