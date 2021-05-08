/*input

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
    final static long pow4_100 = 100L * 100L * 100L * 100L;
    final static double eps = 1e-8;
    static void solve() {
    	for (int a = 1; a + a + a + a <= 20 * 100 && (long)a * a * a * a <= 20L * pow4_100; a++) {
    		for (int b = a; a + b + b + b <= 20 * 100 && (long)a * b * b * b <= 20L * pow4_100; b++) {
    			for (int c = b; a + b + c + c <= 20 * 100 && (long) a * b * c * c <= 20L * pow4_100; c++) {
    				long sum = (long)a + b + c, prod = (long)a * b * c;
    				//if ((prod * 100L) % pow4_100 != 0) continue;
    				double div = 100.0 * prod / pow4_100 - 1.0;
    				if (div == 0.0) continue;
    				double d_double = (double)sum / div;
    				if (Math.abs(d_double - (double)Math.round(d_double)) >= eps) continue;
    				int d = (int)Math.round(d_double);
    				if (d < c || a + b + c + d > 20 * 100 || (long)a * b * c * d > 20L * pow4_100) continue;
    				System.out.format("%.2f %.2f %.2f %.2f\n", (double)a / 100.0, (double)b / 100.0, (double)c / 100.0, (double)d / 100.0);
    			}
    		}	
    	}
    }
    public static void main(String[] args) {      
        runFromFile();
        //Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        solve();
    }
}