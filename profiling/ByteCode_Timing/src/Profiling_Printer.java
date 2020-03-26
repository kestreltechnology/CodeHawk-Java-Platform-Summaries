package ByteCode_Timing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Profiling_Printer {
    public static void print_timing_to_file(String filename, long timing, int[] values) {
        StringBuilder output_string = new StringBuilder();;

        int acc = 1;
        for(int value: values) {
            acc = acc * value;
            output_string.append(Integer.toString(acc)).append(" ");
        }
        output_string.append(Long.toString(timing)).append(" ");

        output_string.append("\n");

        File f = null;
        FileWriter printer = null;

        try {
            f = new File(filename);
            printer = new FileWriter(f, true);

            printer.append(output_string.toString());
            printer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
