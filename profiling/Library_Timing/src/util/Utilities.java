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

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.lang.Integer;
import java.lang.String;
import java.lang.StringBuilder;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.TreeMap;
import java.util.TreeSet;


public class Utilities{
    public static StringBuilder initialize_stringbuilder(int count) {
        char[] array = new char[count];
        Arrays.fill(array, 'a');
        String stringy = new String(array);
        return new StringBuilder(stringy);
    }

    public static boolean[] get_random_bools(int quantity) {
        boolean[] bools = new boolean[quantity];
        for(int i = 0; i < quantity; i++) {
            bools[i] = i % 3 == 0;
        }
        return bools;
    }

    public static ArrayList<Integer> get_some_arraylist(int size, int offset) {
        ArrayList<Integer> new_arraylist = new ArrayList<Integer>(size);
        for(int i = 0; i < size; i++){
            new_arraylist.add(i);
        }
        if(offset > -1 && offset < new_arraylist.size()){
            new_arraylist.set(offset, -1);
        }
        return new_arraylist;
    }

    public static File get_canonical_file() {
        File f = null;
        try {
            f = new File("temporaries/temp.txt");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static void delete_this_file(File f) {
        if(f.exists()) {
            f.delete();
        }
    }

    public static File get_some_file(String s) {
        File f = null;
        try {
            f = new File(s);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static ByteArrayOutputStream get_some_bytearrayoutputstream(int size) {
        ByteArrayOutputStream new_baos = new ByteArrayOutputStream(size);
        byte[] new_bytearray = new byte[size];

        /*new_baos.write(new_bytearray, 0, new_bytearray.length);*/

        return new_baos;
    }

    public static FileOutputStream get_some_fileoutputstream() {
        try {
            FileOutputStream new_fileoutputstream = new FileOutputStream(get_canonical_file());
            return new_fileoutputstream;
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static FilterOutputStream get_some_filteroutputstream(File f) {
        try {
            FileOutputStream new_fileoutputstream = new FileOutputStream(f);
            FilterOutputStream new_filteroutputstream = new FilterOutputStream(new_fileoutputstream);
            return new_filteroutputstream;
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static PrintStream get_some_printstream(File f) {
        try {
            PrintStream new_printstream = new PrintStream(f);
            return new_printstream;
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<Integer, Integer> get_some_hashmap(int size, int offset) {
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>(size);
        for(int i = 0; i < size; i++) {
            h.put(i, i);
        }
        if(offset > -1 && offset < h.size()){
            h.remove(offset);
            h.put(offset, -1);
        }    
        return h;
    } 

    public static HashSet<String> get_string_hashset(int size) {
        HashSet<String> new_hashset = new HashSet<String>();
        Get_Next_String the_next_string = new Get_Next_String();
        String new_string;
        for(int i = 0; i < size; i++) {
            new_string = the_next_string.next_string();
            new_hashset.add(new_string);
        }

        return new_hashset;
    }

    public static Hashtable<String, String> get_string_hashtable(int size) {
        Hashtable<String, String> new_hashtable = new Hashtable<String, String>();
        Get_Next_String the_next_string = new Get_Next_String();
        String new_string;
        for(int i = 0; i < size; i++) {
            new_string = the_next_string.next_string();
            new_hashtable.put(new_string, new_string);
        }

        return new_hashtable;
    }

    public static LinkedList<String> get_string_linkedlist(int size) {
        LinkedList<String> new_linkedlist = new LinkedList<String>();
        Get_Next_String the_next_string = new Get_Next_String();
        String new_string;
        for(int i = 0; i < size; i++) {
            new_string = the_next_string.next_string();
            new_linkedlist.add(new_string);
        }

        return new_linkedlist;
    }

    public static TreeMap<String, String> get_string_treemap(int size) {
        TreeMap<String, String> new_treemap = new TreeMap<String, String>();
        Get_Next_String the_next_string = new Get_Next_String();
        String new_string;
        for(int i = 0; i < size; i++) {
            new_string = the_next_string.next_string();
            new_treemap.put(new_string, new_string);
        }

        return new_treemap;
    }

    public static TreeSet<String> get_string_treeset(int size) {
        TreeSet<String> new_treeset = new TreeSet<String>();
        Get_Next_String the_next_string = new Get_Next_String();
        String new_string;
        for(int i = 0; i < size; i++) {
            new_string = the_next_string.next_string();
            new_treeset.add(new_string);
        }

        return new_treeset;
    }

    public static Object get_some_object(int index, int parameter) throws BadParameterException {
        switch(index){
            case 1: /* String */
                return get_some_string(parameter);
            case 2: /* int */
                return parameter;
            case 3: /* ArrayList */
                return get_some_arraylist(parameter, -1);
            case 4: /* Boolean */
                boolean newbool = parameter == 1;
                return new Boolean(newbool);
            case 5: /* Long */
                return (long)parameter;
            case 6: /* int */
                return parameter;
            case 7: /* File */
                return new File("/", "a");
            case 8: /* Parse */
                return parameter == 1 ? Level.parse("FINE") : Level.parse("FINER");
            default:
                System.out.format("Failed to find object %d, falling through", index);
                throw new BadParameterException();
        }
    }

	public static OutputStream select_outputstream(String name) throws BadParameterException {
		try {
			switch(name) {
				case "ByteArrayOutputStream":
					return new ByteArrayOutputStream();
				case "FileOutputStream":
					return new FileOutputStream(get_canonical_file());
                default:
                    System.out.println("Failed to find OutputStream, failing through");
                    throw new BadParameterException();	
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

    public static OutputStream get_some_outputstream(int index, int parameter) throws BadParameterException{
        try {
            switch(index) {
                case 1:
                    return new FileOutputStream(get_canonical_file());
                case 2:
                    FileOutputStream temp_stream = new FileOutputStream(get_canonical_file());
                    return new BufferedOutputStream(temp_stream);
                default:
                    System.out.println("Failed to find object, failing through");
                    throw new BadParameterException();
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String get_some_string(int length){
        char[] array = new char[length];
        Arrays.fill(array, 'a');
        String new_string = new String(array);
        return new_string;
    }

    public static String[] get_some_strings(int quantity, int length){
        String[] strings = new String[quantity];
        for(int i = 0; i < quantity; i++){
            char[] array = new char[length];
            Arrays.fill(array, 'a');
            strings[i] = new String(array);
        }
        return strings;
    }

    public static String get_oneoff_string(int length, int offset) {
        if(offset > length) {
            System.out.println("Offset greater than length! This is not good");
            return "";
        }
        char[] array = new char[length];
        Arrays.fill(array, 'a');
        array[offset] = 'b';
        String stringy = new String(array);
        return stringy;
    }

    public static char[] get_uniform_chararray(int length){
        char[] chars = new char[length];
        Arrays.fill(chars, 'a');
        return chars;
    }

    public static char[][] get_random_chararrays(int quantity, int length){
        char[][] chars = new char[quantity][length];
        for(int i = 0; i < quantity; i++) {
            Arrays.fill(chars[i], 'a');
        }
        return chars;
    }
}
