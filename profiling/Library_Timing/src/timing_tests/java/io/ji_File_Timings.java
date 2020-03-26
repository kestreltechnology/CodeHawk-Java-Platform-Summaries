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

import java.io.File;
import java.io.IOException;

public class ji_File_Timings extends Java_Timings{
    public ji_File_Timings(boolean verbose) {
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
            case "equals_object":
                total_time = run_file_equals_object(repeats, (int)nvals[0], (int)nvals[1], (int)nvals[2]);
                break;
            case "init_String":
                total_time = run_init_String(repeats, svals[0]);
                break;
            case "init_File_String":
                total_time = run_init_File_String(repeats, svals[0], svals[1]);
                break;
            case "canRead":
                total_time = run_canRead(repeats, svals[0]);
                break;
            case "canWrite":
                total_time = run_canWrite(repeats, svals[0]);
                break;
            case "createNewFile":
                total_time = run_createNewFile(repeats, svals[0], (int)nvals[0]);
                break;
            case "createTempFile":
                total_time = run_createTempFile_String_String(repeats, svals[0], svals[1]);
                break;
            case "delete":
                total_time = run_delete(repeats, svals[0]);
                break;
            case "deleteOnExit":
                total_time = run_deleteOnExit(repeats, svals[0]);
                break;
            case "exists":
                total_time = run_exists(repeats, svals[0], (int)nvals[0]);
                break;
            case "getAbsolutePath":
                total_time = run_getAbsolutePath(repeats, svals[0]);
                break;
            case "getName":
                total_time = run_getName(repeats, svals[0]);
                break;
            case "getParentFile":
                total_time = run_getParentFile(repeats, svals[0]);
                break;
            case "getPath":
                total_time = run_getPath(repeats, svals[0]);
                break;
            case "getUsableSpace":
                total_time = run_getUsableSpace(repeats, svals[0]);
                break;
            case "isDirectory":
                total_time = run_isDirectory(repeats, svals[0]);
                break;
            case "length":
                total_time = run_length(repeats, svals[0]);
                break;
            case "list":
                total_time = run_list(repeats, svals[0]);
                break;
            case "listFiles":
                total_time = run_listFiles(repeats, svals[0]);
                break;
            /*
            case "listFiles_FilenameFilter":
                total_time = run_listFiles_FilenameFilter(repeats, svals[0], svals[1]);
                break;
            */
            case "mkdir":
                total_time = run_mkdir(repeats, svals[0]);
                break;
            case "mkdirs":
                total_time = run_mkdirs(repeats, svals[0]);
                break;
            case "setExecutable_boolean":
                total_time = run_setExecutable_boolean(repeats, svals[0], (int)nvals[0]);
                break;
            case "setReadable_boolean":
                total_time = run_setReadable_boolean(repeats, svals[0], (int)nvals[0]);
                break;
            case "setWriteable_boolean":
                total_time = run_setWriteable_boolean(repeats, svals[0], (int)nvals[0]);
                break;
            case "toPath":
                total_time = run_toPath(repeats, svals[0]);
                break;
            case "toURI":
                total_time = run_toURI(repeats, svals[0]);
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

    public static long run_file_equals_object(int count, int initial_size, int index, int parameter){
        Object some_object;
        try {
            some_object = Utilities.get_some_object(index, parameter);   
        } 
        catch(BadParameterException e)
        {
            return -1;
        }
        File new_file = new File("/", "a");

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            new_file.equals(some_object);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_init_File_String(int count, String file_name, String val1) {
        File control_file = new File(file_name);
        File new_file;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            new_file = new File(control_file, val1);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_init_String(int count, String val1) {
        File new_file;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            new_file = new File(val1);
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_canRead(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.canRead();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_canWrite(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.canWrite();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_createNewFile(int count, String file_name, int reset) {
        File control_file = new File(file_name);

        try {
            long start_time, end_time, total_time = 0;
            for(int i = 0; i < count; i++) {
                start_time = System.nanoTime();
                control_file.createNewFile();
                end_time = System.nanoTime();
                total_time += end_time - start_time;

                if(reset != 0){control_file.delete();}
            }
            return total_time / count;
        }
        catch(IOException e){
            return -1;
        }
    }

    public static long run_createTempFile_String_String(int count, String prefix, String suffix) {
        File new_file;  

        try {
            long start_time, end_time, total_time = 0;
            for(int i = 0; i < count; i++) {
                start_time = System.nanoTime();
                new_file = File.createTempFile(prefix, suffix);
                end_time = System.nanoTime();
                total_time += end_time - start_time;

                new_file.delete();
            }
            return total_time / count;
        }
        catch(IOException e) {
            return -1;
        }
    }

    public static long run_delete(int count, String file_name) {
        File control_file = new File(file_name);

        try {
            long start_time, end_time, total_time = 0;
            for(int i = 0; i < count; i++) {
                control_file.createNewFile();
 
                start_time = System.nanoTime();
                control_file.delete();
                end_time = System.nanoTime();
                total_time += end_time - start_time;
            }
            return total_time / count;
        }
        catch(IOException e) {
            return -1;
        }
    }

    public static long run_deleteOnExit(int count, String file_name) {
        File control_file = new File(file_name);

        try {
            control_file.createNewFile();

            long start_time, end_time, total_time = 0;
            for(int i = 0; i < count; i++) {
                start_time = System.nanoTime();
                control_file.deleteOnExit();
                end_time = System.nanoTime();
                total_time += end_time - start_time;
            }
            return total_time / count;
        }
        catch(IOException e) {
            return -1;
        }
    }

    public static long run_exists(int count, String file_name, int make) {
        File control_file = new File(file_name);

        try {
            if(make != 0){control_file.createNewFile();}

            long start_time, end_time, total_time = 0;
            for(int i = 0; i < count; i++) {
                start_time = System.nanoTime();
                control_file.exists();
                end_time = System.nanoTime();
                total_time += end_time - start_time;
            }
            return total_time / count;
        }
        catch(IOException e) {
            return -1;
        }
    }

    public static long run_getAbsolutePath(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.getAbsolutePath();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_getName(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.getName();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_getParentFile(int count, String file_name) {
        File control_file = new File(file_name);    

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.getParentFile();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_getPath(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.getPath();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_getUsableSpace(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.getUsableSpace();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_isDirectory(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.isDirectory();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_length(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.length();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_list(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.list();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_listFiles(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.listFiles();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    /*
    public static long <SomeMethod>(int count, <SomeParameters>) {
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();

            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
    */

    public static long run_mkdir(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.mkdir();
            end_time = System.nanoTime();
            total_time += end_time - start_time;

            control_file.delete();
        }
        return total_time / count;
    }

    public static long run_mkdirs(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.mkdir();
            end_time = System.nanoTime();
            total_time += end_time - start_time;

            control_file.delete();
        }
        return total_time / count;
    }

    public static long run_setExecutable_boolean(int count, String file_name, int val1) {
        File control_file = new File(file_name);
        boolean bool = val1 != 0;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.setExecutable(bool);
            end_time = System.nanoTime();
            total_time += end_time - start_time;

            control_file.delete();
        }
        return total_time / count;
    }

    public static long run_setReadable_boolean(int count, String file_name, int val1) {
        File control_file = new File(file_name);
        boolean bool = val1 != 0;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.setReadable(bool);
            end_time = System.nanoTime();
            total_time += end_time - start_time;

            control_file.delete();
        }
        return total_time / count;
    }

    public static long run_setWriteable_boolean(int count, String file_name, int val1) {
        File control_file = new File(file_name);
        boolean bool = val1 != 0;

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.setWritable(bool);
            end_time = System.nanoTime();
            total_time += end_time - start_time;

            control_file.delete();
        }
        return total_time / count;
    }

    public static long run_toPath(int count, String file_name) {
        File control_file = new File(file_name);
        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.toPath();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }

    public static long run_toURI(int count, String file_name) {
        File control_file = new File(file_name);

        long start_time, end_time, total_time = 0;
        for(int i = 0; i < count; i++) {
            start_time = System.nanoTime();
            control_file.toURI();
            end_time = System.nanoTime();
            total_time += end_time - start_time;
        }
        return total_time / count;
    }
    
    
}
