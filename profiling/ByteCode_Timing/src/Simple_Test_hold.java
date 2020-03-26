package ByteCode_Timing;

import java.lang.String;
import java.io.*;

public class Simple_Test {
    public static void main(String args[]) {
        int iterations = Integer.parseInt(args[0]);
        long[] data = new long[iterations];
        for(int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            long endTime = System.nanoTime();
            long estimatedTime = endTime - startTime;
            System.out.format("%d\n", estimatedTime);
            data[i] = estimatedTime;
        }
        try {
            FileWriter fileWriter = new FileWriter("simple_test_output.txt");

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
}
