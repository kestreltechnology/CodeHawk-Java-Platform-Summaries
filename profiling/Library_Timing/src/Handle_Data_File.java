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

import java.lang.Integer;
import java.lang.String;
import java.io.*;

/* Reads a data file in the form of space separated pairs of doubles such as :
 *
 * 1.0 1.0
 * 2.0 3.0
 * 4.0 4.5
 *
 */

public class Handle_Data_File {
    public static Tuple<double[][], double[]> get_results(String fileName) {
        String line = null;
        double[][] parameters = {};
        double[] results = {};
        String[] data_point;
        Tuple<double[][], double[]> data;

        try {
            Tuple<Integer, Integer> file_data = get_file_length(fileName);
            int sample_count = file_data.x;
            int line_length = file_data.y;
            int parameters_count = line_length - 1;
            int linenumber = 0;

            parameters = new double[sample_count][parameters_count];
            results = new double[sample_count];

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            /*
            String header = "";
            line = bufferedReader.readLine();
            if(line != null) {
                data_point = line.split("\\s+");
                for(int i = 0; i < data_point.length; i++) {
                    System.out.format("%s ", data_point[i]);
                }
                System.out.format("\n");
                System.out.println("----------------------------------------");
                header = String.format("Intercept %s %s", data_point[0], data_point[1]);
            }
            */

            while((line = bufferedReader.readLine()) != null) {
                data_point = line.split("\\s+");
                for(int i = 0; i < data_point.length - 1; i++) {
                    parameters[linenumber][i] = Double.parseDouble(data_point[i]);
                }
                results[linenumber] = Double.parseDouble(data_point[data_point.length - 1]);

                linenumber += 1;
            }

            data = new Tuple<double[][], double[]>(parameters, results);
            bufferedReader.close();

            for(int i = 0; i < sample_count; i++) {
                for(int j = 0; j < parameters_count; j++) {
                    System.out.format("%s ", parameters[i][j]);
                }
                System.out.format("%s\n", results[i]);
            }
            System.out.println("");
            /*System.out.println(header);*/

            return data;
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex){
            ex.printStackTrace();
        }

        parameters = new double[0][0];
        results = new double[0];
        data = new Tuple<double[][], double[]>(parameters, results);
        return data;
    }

    /* Reads the data file and returns an array of pairs of doubles */

    public static double[][] read_data_file(String fileName) {
        String line = null;
        int position = 0; int file_length = 0; int line_length = 0;
        double[][] data = {};
        String[] data_point;
        try {
            Tuple<Integer, Integer> file_data = get_file_length(fileName);
            file_length = file_data.x;
            line_length = file_data.y;

            data = new double[file_length][line_length];
       
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                data_point = line.split("\\s+");
                for(int i = 0; i < data[position].length; i++){
                    data[position][i] = Double.parseDouble(data_point[i]);
                }
                position += 1;
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'"); 
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return data;
    }

    public static void write_data_file(String fileName, long[] data) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(int i = 0; i < data.length; i++) {
                String target_string = Long.toString(data[i]);
                bufferedWriter.write(target_string);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
    }

    public static Tuple<Integer, Integer> get_file_length(String fileName) {
        String line = null;
        int fileLength = 0;
        int lineLength = -1;
        String[] datum;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                datum = line.split("\\s+");
                if(lineLength ==  -1 && fileLength > 0){
                    lineLength = datum.length;
                }
                else if(lineLength != -1 && lineLength != datum.length && fileLength > 0) {
                    System.out.println("Data file appears to be ill formed.");
                    System.exit(1);
                }

                fileLength += 1;
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        Tuple<Integer, Integer> file_data = new Tuple<Integer, Integer>(fileLength, lineLength);
        return file_data;
    }
}
