package Java_Timing;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Do_Regression_Standalone {
    public static void main(String[] args) {
        String input_file = "number_data.txt";
        if(args.length > 0) {
            input_file = args[0];
        }
        System.out.format("Opening %s ... \n", input_file);

        SimpleRegression regression = new SimpleRegression(false);

        double[][] some_data;

        some_data = Handle_Data_File.read_data_file("number_data.txt");

        for(int i = 0; i < some_data.length; i++) {
            regression.addData(some_data[i][0], some_data[i][1]);
        }

        System.out.format("Intercept : %s \n", regression.getIntercept());
        System.out.format("Slope : %s \n", regression.getSlope());
    }
}
