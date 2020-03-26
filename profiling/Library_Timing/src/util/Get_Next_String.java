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
import java.lang.StringBuilder;
import java.lang.Character;

public class Get_Next_String {
	public char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	public StringBuilder stringo;

	public Get_Next_String() {
		this.alphabet = alphabet;
		this.stringo = new StringBuilder("");
	}

	private char get_next_char(char current) {
		Character current_val = new Character(current);
		if(current == 'z') {
			System.out.println("THERE IS NO SUCH CHARACTER!");
		}
		else {
			return this.alphabet[Character.getNumericValue(current_val) - 9];
		}
		return 'z';
	}

	public String next_string() {
		int end = this.stringo.length() - 1;

		if(this.stringo.length() == 0) {
			this.stringo.append("a");
		}
		else if(this.stringo.charAt(end) == 'z') {
			int index = end;
			while(index >= 0) {
				if(this.stringo.charAt(index) == 'z') {
					this.stringo.setCharAt(index, 'a');
					if(index == 0) {
						stringo.append('a');
					}
				}
				else {
					this.stringo.setCharAt(index, get_next_char(this.stringo.charAt(index)));
					break;
				}
				index--;
			}
		}
		else {
			this.stringo.setCharAt(end, get_next_char(this.stringo.charAt(end)));
		}
		return stringo.toString();
	}
}
