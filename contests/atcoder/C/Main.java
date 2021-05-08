/*input
1333
*/
import java.util.*;
import java.io.*;

public class Main {
    static void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming/io";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        long num = inp.nextLong();
        int len = String.valueOf(num).length();
        long lim = 0;
        for (int i = 0; i < len / 2; i++) lim = 10L * lim + 9;
        //System.out.println(lim);
        long ans = 0;
        for (long x = 1; x <= lim; x++) {
        	String x_str = String.valueOf(x);
           // System.out.println(x_str + x_str);
        	ans += Long.parseLong(x_str + x_str) <= num? 1 : 0;
        }
        System.out.println(ans);

        //out.close();
    }
}