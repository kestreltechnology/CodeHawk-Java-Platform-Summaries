/* ------------------------------------------------------------------------------
*   Tool to profile Java library methods
*   Author: Andrew McGraw
*   ------------------------------------------------------------------------------
*   The MIT License (MIT)
*   Copyright (c) 2016-2017 Kestrel Technology LLC
*           
*   Permission is hereby granted, free of charge, to any person obtaining a copy
*   of this software and associated documentation files (the "Software"), to deal
*   in the Software without restriction, including without limitation the rights
*   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*   copies of the Software, and to permit persons to whom the Software is
*   furnished to do so, subject to the following conditions:
*                   
*   The above copyright notice and this permission notice shall be included in all
*   copies or substantial portions of the Software.
*
*   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*   SOFTWARE.
*/

package Java_Timing;

import org.apache.commons.math3.stat.regression.*;

public class Do_Regression {
    public void do_simple_regression(String filename) {
        SimpleRegression regression = new SimpleRegression();

        double[][] some_data;

        some_data = Handle_Data_File.read_data_file("number_data.txt");

        regression.addData(some_data);

        System.out.format("Intercept : %s \n", regression.getIntercept());
        System.out.format("Slope : %s \n", regression.getSlope());

        /* TODO : Read error, provide some commentary on this */
    }

    public void do_simple_regression(double[][] data_point) {
        SimpleRegression regression = new SimpleRegression();

        regression.addData(some_data);

        System.out.format("Intercept : %s \n", regression.getIntercept());
        System.out.format("Slope : %s \n", regression.getSlope());
        /* TODO : Read error here too */
    }

    /* public do_multiple_regression(double[][] data_point){} */

    /* public do_multiple_regression(String filename){} */
}
