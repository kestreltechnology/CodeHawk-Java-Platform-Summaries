package Java_Timing;

import org.apache.commons.math3.stat.regression.*;

public class Multiple_Regression {
    public static void main(String[] args) {
        System.out.println(args[0]);

        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.setNoIntercept(true);

        double[] Y;
        double[][] X;

        Tuple<double[][], double[]> some_data = Handle_Data_File.get_results(args[0]);
        Y = some_data.y;
        X = some_data.x;
        double normal = Y[0];
        Y = normalize(Y, Y[0]);

        regression.newSampleData(Y, X);

        double[] parameters = regression.estimateRegressionParameters();
        double[] residuals = regression.estimateResiduals();

        System.out.println("----------------------------------------\n");
        System.out.format("Intercept : %s\n", normal);
        for(int i = 0; i < parameters.length; i++) {
            System.out.format("Regression Parameter %d : %s\n", i, parameters[i]);
        }
        System.out.println("");
        System.out.println("----------------------------------------\n");

        for(int i = 0; i < residuals.length; i++){
            System.out.format("Residual %d : %s\n", i, residuals[i]);
        }
    }

    public static double[] normalize(double[] values, double normal) {
        double[] normalized_values = new double[values.length];
        for(int i = 0; i < normalized_values.length; i++) {
            normalized_values[i] = values[i] - normal;
        }
        return normalized_values;
    }
}
