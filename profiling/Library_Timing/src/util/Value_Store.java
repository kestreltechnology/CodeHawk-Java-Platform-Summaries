/* ----------------------------------------------------------------------------
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

import java.lang.String;

public class Value_Store implements Comparable {
	public String classname;
	public String methodname;
    public double[] numeric_vals;
    public String[] string_vals;

	public Value_Store(String classname, String methodname) {
		this.classname = classname;
		this.methodname = methodname;
		this.numeric_vals = new double[0];
		this.string_vals = new String[0];
	}

    public Value_Store(String classname, String methodname, double[] numeric_vals, String[] string_vals) {
		this.classname = classname;
		this.methodname = methodname;
        this.numeric_vals = numeric_vals;
        this.string_vals = string_vals;
    }

	public void print_store() {
		System.out.println("Reporting Value_Store ...");
		for(int i = 0; i < numeric_vals.length; i++) {
			System.out.format("%f\n", numeric_vals[i]);
		}
		for(int i = 0; i < string_vals.length; i++) {
			System.out.format("%s\n", string_vals[i]);
		}
	}

	@Override
	public int compareTo(Object o) {
		if(this.equals(o)) {return 0;}
		else{return -1;}
	}

	@Override
	public boolean equals(Object val2) {
		if(val2 instanceof Value_Store == false) {return false;}

		Value_Store other_values = (Value_Store)val2;
		if(this.classname.equals(other_values.classname)) {
			if(this.methodname.equals(other_values.methodname)) {
				if(this.numeric_vals.length != other_values.numeric_vals.length) {return false;}
				if(this.string_vals.length != other_values.string_vals.length) {return false;}
				for(int i = 0; i < this.numeric_vals.length; i++) {
					if(this.numeric_vals[i] != other_values.numeric_vals[i]) {return false;}
				}
				for(int i = 0; i < this.string_vals.length; i++) {
					if(!this.string_vals[i].equals(other_values.string_vals[i])) {return false;}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder value_store_str = new StringBuilder();
		value_store_str.append(classname).append(" ").append(methodname).append(" ");
		for(int i = 0; i < numeric_vals.length; i++) {
			Double numeric_val = new Double(numeric_vals[i]);
			value_store_str.append(numeric_val.toString()).append(" ");
		}
		for(int i = 0; i < string_vals.length; i++) {
			value_store_str.append(string_vals[i]).append(" ");
		}

		return value_store_str.toString();
	}
}
